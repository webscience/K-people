package it.webscience.kpeople;

import it.webscience.kpeople.datatypes.AlfrescoAttachment;
import it.webscience.uima.ocAlfrescoStorage.OcAlfrescoResponse;
import it.webscience.uima.ocAlfrescoStorage.OcAlfrescoStorageConfiguration;
import it.webscience.uima.ocStorageAbstract.OcStorageAttachmentRequest;
import it.webscience.uima.ocStorageAbstract.OcStorageExitCodes;
import it.webscience.uima.ocStorageAbstract.OcStoragePropertyRequest;
import it.webscience.uima.ocStorageAbstract.OcStorageRequest;
import it.webscience.uima.ocStorageAbstract.OcStorageResponse;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.activation.DataHandler;
import javax.xml.stream.XMLStreamException;

import org.alfresco.model.ContentModel;
import org.alfresco.repo.content.MimetypeMap;
import org.alfresco.repo.model.Repository;
import org.alfresco.service.ServiceRegistry;
import org.alfresco.service.cmr.model.FileExistsException;
import org.alfresco.service.cmr.model.FileFolderService;
import org.alfresco.service.cmr.repository.ContentWriter;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.search.SearchParameters;
import org.alfresco.service.cmr.search.SearchService;
import org.alfresco.service.cmr.security.AuthenticationService;
import org.alfresco.service.namespace.NamespaceService;
import org.alfresco.service.namespace.QName;
import org.apache.axiom.om.OMText;
import org.apache.axiom.om.util.AXIOMUtil;
import org.apache.log4j.Logger;
import org.springframework.extensions.webscripts.AbstractWebScript;
import org.springframework.extensions.webscripts.Status;
import org.springframework.extensions.webscripts.WebScriptRequest;
import org.springframework.extensions.webscripts.WebScriptResponse;

import com.ibm.icu.util.Calendar;

/** Questo web script gestisce il salvataggio degli eventi su Alfresco.
 * Lo script deserializza l'oggetto OcStorageRequest ricevuto via POST,
 * salva attachments e proprietà associate all'evento
 * e risponde con un oggetto OcStorageResponse contenente la risposta
 * 
 * @author dellanna
 *
 */
public class StoreEventWebScript extends AbstractWebScript {
    /** default bulk fetch size. */
    private static final int BULK_FETCH_SIZE = 10;

    /** K-People namespace. */
    private static final String KP_MODEL_NAMESPACE = 
        "{http://www.webscience.it/kpeople/model/content/1.0}";
    
    /** serviceRegistry istanziato via spring. */
    private ServiceRegistry serviceRegistry;
    
    /** repository istanziato via spring. */
    private Repository repository;
    
    /** storage request inviata dall'annotator. */
    private OcStorageRequest storageRequest;
    
    /** storage configuration inviata dall'annotator. */
    private OcAlfrescoStorageConfiguration configuration;
    
    /** risposta da inviare all'annotator. */
    private OcStorageResponse storageResponse;
    
    /** logger. */
    private Logger logger = Logger.getLogger(this.getClass().getName());

    /** metodo che implementa la chiamata al web script.
     * @param req WebScriptRequest representing the request to this service
     * @param res WebScriptResponse encapsulating the result of this service
     * @throws
     */
    public final void execute(
            final WebScriptRequest req,
            final WebScriptResponse res) {
        logger.debug("Execute - Start");	

//      inizializza la storage response
        storageResponse = new OcAlfrescoResponse();
        storageResponse.setExitCode(OcStorageExitCodes.EXIT_OK);
        
        readInputStreamObjects(req);

        try {
            doEventAnalysis();
            
            ObjectOutputStream outStream = new ObjectOutputStream(
                    res.getOutputStream());
            outStream.writeObject(storageResponse);
            outStream.flush();
            outStream.close();
        } catch (IOException e) {
            storageResponse.setExitCode(OcStorageExitCodes.IO_EXCEPTION);
            storageResponse.setErrorMessage(e.getMessage());

            logger.fatal(e.getStackTrace());
            
            StringWriter sWriter = new StringWriter();
            e.printStackTrace(new PrintWriter(sWriter));
            logger.fatal(sWriter.getBuffer().toString());
            
            res.setStatus(Status.STATUS_SERVICE_UNAVAILABLE);
        }  
    }

    /** analizza la storage request: genera cartelle ed attachments.
     * @throws IOException errore nel salvataggio dell'attachment
     */
    private void doEventAnalysis() throws IOException {
        if (storageResponse.getExitCode() != OcStorageExitCodes.EXIT_OK) {
            return;
        }

        logger.debug("Analisi dell'evento: " + storageRequest.getEventId());

        String spaceStore = "workspace://SpacesStore/";
        
        NodeRef todayFolder = getTodayFolder();
        NodeRef eventFolder = createEventFolder(storageRequest, todayFolder);
        if (eventFolder != null) {
            logger.debug("NodeRef folder evento: " + eventFolder.getId());
            
//          salvo l'xml associato all'evento e recupero l'url per accedervi
            storeDocumentText(eventFolder);

            List<AlfrescoAttachment> attachments =
                addAttachmentsToFolder(storageRequest, eventFolder);
 
            storageResponse.setEventNodeRefId(
                    spaceStore + eventFolder.getId());
            
            for (AlfrescoAttachment aa : attachments) {
                String id = aa.getId();
                String fileName = aa.getFileName();
                String url = "/alfresco/d/d/workspace/SpacesStore/"
                    + aa.getNodeRef().getId() + "/" + fileName;
                
                storageResponse.getUrlMap().put(id, url);
            }
        }
    }
    
    /** salva l'xml legato all'evento nella cartella.
     * @param eventFolder cartella associata all'evento
     * @return nodeRef dell'xml
     * @throws IOException eccezione legata al salvataggio del documento
     */
    private NodeRef storeDocumentText(final NodeRef eventFolder)
            throws IOException {
        String fileName = serviceRegistry.getNodeService().getProperty(
                eventFolder, ContentModel.PROP_NAME).toString() + ".xml";

        NodeRef nodeRef = createNode(
                MimetypeMap.MIMETYPE_XML, 
                null, 
                storageRequest.getDocumentText(), 
                eventFolder, 
                fileName, 
                false);  

        String url = "/alfresco/d/d/workspace/SpacesStore/"
            + nodeRef.getId() + "/" + fileName;
        
        storageResponse.setEventUrl(url);
        
        return nodeRef;
    }

    /** popola gli oggetti ricevuti dalla request.
     * @param req WebScriptRequest representing the request to this service
     */
    private void readInputStreamObjects(
            final WebScriptRequest req) {
        try {
            ObjectInputStream in = new ObjectInputStream(
                    req.getContent().getInputStream());
            
            storageRequest = (OcStorageRequest) in.readObject();
            configuration = (OcAlfrescoStorageConfiguration) in.readObject();
        } catch (ClassNotFoundException e) {
            storageResponse.setExitCode(
                    OcStorageExitCodes.CLASS_NOT_FOUND_EXCEPTION);
            storageResponse.setErrorMessage(e.getMessage());
            
            logger.error(e.getStackTrace());
        } catch (IOException e) {
            storageResponse.setExitCode(OcStorageExitCodes.IO_EXCEPTION);
            storageResponse.setErrorMessage(e.getMessage());

            logger.error(e.getStackTrace());
        } 
    }
    
    /** Salva il documento all'interno della cartella associata all'evento.
     * @param mimeType MIME type del documento
     * @param hashcode hashcode del documento
     * @param data binario associato al documento
     * @param folder NodeRef della cartella relativa all'evento
     * @param name nome del file
     * @return noderef del documento salvato in Alfresco
     * @throws IOException eccezione legata al salvataggio del binario
     */
    private NodeRef storeFile(
            final String mimeType,
            final String hashcode,
            final String data,
            final NodeRef folder,
            final String name) throws IOException {
        
        NodeRef file = null;

        /** gestione dei files dupliati. */
        boolean duplicateDocsAllowed = configuration.isHasDuplicateDocs();
        if (duplicateDocsAllowed) {
            file = createNode(mimeType, hashcode, data, folder, name, true);
        } else {
//          ricerca di un attachment con lo stesso hashcode
            String query = " +@kp\\:hashcode:\"" + hashcode + "\" ";
            
            SearchParameters sp = new SearchParameters();
            sp.setBulkFetch(false);
            sp.setBulkFetchSize(BULK_FETCH_SIZE);
            sp.addStore(repository.getCompanyHome().getStoreRef());
            sp.setLanguage(SearchService.LANGUAGE_LUCENE);
            sp.setQuery(query); 

            List<NodeRef> nodes = serviceRegistry.getSearchService().
                    query(sp).getNodeRefs();
            if (nodes.size() > 0) {
//              è presente un file con lo stesso hashcode:
//              creo uno shortcut al file
                String originalNodeId = nodes.get(0).getId();
                
                String linkName = name + ".url";
               
                Map<QName, Serializable> props =
                    new HashMap<QName, Serializable>();
                props.put(ContentModel.PROP_NAME, linkName);
                props.put(ContentModel.PROP_TITLE, name);
                props.put(ContentModel.PROP_DESCRIPTION, name);
                props.put(ContentModel.PROP_LINK_DESTINATION,
                        "workspace://SpacesStore/" + originalNodeId);

                QName cmUri = QName.createQName(
                        NamespaceService.CONTENT_MODEL_1_0_URI, linkName);
                
                QName fileLink = QName.createQName(
                        NamespaceService.APP_MODEL_1_0_URI, "filelink");
               
//              creo il link simbolico
                serviceRegistry.getNodeService().createNode(
                        folder, 
                        ContentModel.ASSOC_CONTAINS, 
                        cmUri,
                        fileLink,
                        props).getChildRef();
                
//              il file ritornato è quello del file originale
                file = nodes.get(0);
            } else {
                file = createNode(mimeType, hashcode, data, folder, name, true);
            }
        }
        
        return file;
    }

    /** crea un nodo in Alfresco.
     * @param mimeType MIME type del documento
     * @param hashcode hashcode del documento
     * @param data binario associato al documento
     * @param eventFolder NodeRef della cartella in cui salvare il file
     * @param name nome del file
     * @param mtomContent il dato 
     * @return noderef del documento salvato in Alfresco
     * @throws IOException eccezione legata al salvataggio del binario
     */
    private NodeRef createNode(
            final String mimeType, 
            final String hashcode,
            final String data, 
            final NodeRef eventFolder, 
            final String name, 
            final boolean mtomContent)
            throws IOException {
        NodeRef file;
        QName cmUri = QName.createQName(
                NamespaceService.CONTENT_MODEL_1_0_URI, name);

        Map<QName, Serializable> props = new HashMap<QName, Serializable>();
        props.put(ContentModel.PROP_NAME, name);
        props.put(
                QName.createQName(KP_MODEL_NAMESPACE + "hashcode"), 
                hashcode);
   
        file = serviceRegistry.getNodeService().createNode(
                eventFolder, 
                ContentModel.ASSOC_CONTAINS, 
                cmUri,
                ContentModel.TYPE_CONTENT, 
                props).getChildRef(); 
   
        ContentWriter contentWriter = serviceRegistry.getContentService().
            getWriter(file, ContentModel.PROP_CONTENT, true);
        contentWriter.setMimetype(mimeType);

        if (mtomContent) {
            contentWriter.putContent(getMtomInputStream(data));
        } else {
            contentWriter.putContent(data);
        }

        logger.debug("NodeRef del file " + name + ": " + file.toString());
        return file;
    }
    
    /** converte il documento MTOM in un InputStream.
     * @param data binario del documento
     * @return InputStream associato all'
     * @throws IOException eccezione di I/O
     */
    private InputStream getMtomInputStream(final String data) 
            throws IOException {
        OMText binaryNode = null;
        try {
            binaryNode = (OMText) AXIOMUtil.
                stringToOM("<data>" + data + "</data>").getFirstOMChild();
        } catch (XMLStreamException e) {
            storageResponse.setExitCode(
                    OcStorageExitCodes.IO_EXCEPTION);
            storageResponse.setErrorMessage(e.getMessage());
            throw new IOException();
        }
        binaryNode.setOptimize(true);
        DataHandler actualDH = (DataHandler) binaryNode.getDataHandler();
        InputStream is = actualDH.getInputStream();

        return is;
        
    }
    
    /** Crea i files all'interno della cartella 'evento'.
     * @param sr OcStorageRequest da analizzare
     * @param eventFolder nodeRef del nodo associato all'evento
     * @return vettore di AlfrescoAttachment relativi ai files
     * @throws IOException eccezione legata al salvataggio dell'attachment
     */
    private List<AlfrescoAttachment> addAttachmentsToFolder(
            final OcStorageRequest sr,
            final NodeRef eventFolder) throws IOException {
        
        List<AlfrescoAttachment> outList = new ArrayList<AlfrescoAttachment>();
   
        for (OcStorageAttachmentRequest sar : sr.getAttachments()) {
            String type = sar.getType();
            String data = sar.getData();
            String hashcode = sar.getHashcode();
            
            String name = new Date().getTime() + "";
            if (sar.getName() != null) {
                name = sar.getName();
            }
            
            NodeRef file = storeFile(type, hashcode, data, eventFolder, name);
            
            AlfrescoAttachment aa = new AlfrescoAttachment();
            aa.setId(sar.getId());
            aa.setNodeRef(file);
            aa.setFileName(name);
            
            outList.add(aa);
        }

        return outList;
    }

    /** crea la directory legata all'evento ed associa l'aspect alla directory.
     * @param sr rootElement dell'xml passato in POST
     * @param todayFolder nodeRef della directory del giorno corrente
     * @return nodeRef della directory creata
     */
    private NodeRef createEventFolder(
            final OcStorageRequest sr,
            final NodeRef todayFolder) {

//      crea la directory associata all'evento. 
//      il nome della directory corrisponde all'ID evento
        String folderName = sr.getEventId().
            replaceAll(" ", "_").
            replaceAll(":", "");
        NodeRef eventFolder = null;
       
        try {
            eventFolder = serviceRegistry.getFileFolderService().create(
                todayFolder, folderName, ContentModel.TYPE_FOLDER).getNodeRef();

//	    le properties dell'aspect sono associate alla directory
            HashMap<QName, Serializable> props =
                new HashMap<QName, Serializable>();
            for (OcStoragePropertyRequest property : sr.getProperties()) {
                addPropertyToAspect(props, property); 
            }

//	    associo l'aspect alla directory
            serviceRegistry.getNodeService().addAspect(
                eventFolder,
                QName.createQName(KP_MODEL_NAMESPACE + "event"), 
                props);
        } catch (FileExistsException e) {
            storageResponse.setExitCode(OcStorageExitCodes.EXISTING_FOLDER);
            storageResponse.setErrorMessage(
                    "L'evento \"" + sr.getEventId() + "\" è già presente");
            
            logger.error("Existing folder: " + folderName);
            
            logger.error(e.getStackTrace());
        }

        return eventFolder;
    }

    /** aggiunge la property dell'evento.
     * @param props mappa delle proprietà
     * @param property proprietà da aggiungere
     */
    private void addPropertyToAspect(
            final HashMap<QName, Serializable> props,
            final OcStoragePropertyRequest property) {
        String propertyName = property.getName();
        String propertyValue = property.getValue();
        String propertyType = property.getType();

//      costruisce la proprietà in base al tipo
        Serializable value = null;
        if (propertyType.equals("d:text")) {
            value = propertyValue;
        } else if (propertyType.equals("d:datetime")) {
            value = new Date(Long.parseLong(propertyValue));
        } else if (propertyType.equals("d:boolean")) {
            value = Boolean.parseBoolean(propertyValue);
        } else if (propertyType.equals("d:float")) {
            value = Float.parseFloat(propertyValue);
        } else if (propertyType.equals("d:int")) {
            value = Integer.parseInt(propertyValue);
        }

//    	aggiunge la proprietà
        if (value != null) {
            props.put(
                    QName.createQName(KP_MODEL_NAMESPACE + propertyName), 
                    value);
            
            logger.debug(
                    "Proprietà: \"" + propertyName + "\" Valore: " + value);
        }
    }

    /**
     * Recupera il riferimento alla directory del giorno corrente.
     * Se non ancora presenti, il metodo crea le directory di 
     * anno, mese e giorno
     * @return nodeRef della directory
     */
    private NodeRef getTodayFolder() {
        GregorianCalendar now = new GregorianCalendar();
        now.setTimeInMillis(new Date().getTime());
        int anno = now.get(Calendar.YEAR);
        int mese = now.get(Calendar.MONTH) + 1;
        int giorno = now.get(Calendar.DAY_OF_MONTH);

        String adminUsername = configuration.getAdminUsername();
        char[] adminPassword = configuration.getAdminPassword().toCharArray();

        AuthenticationService authenticationService =
            serviceRegistry.getAuthenticationService();
        authenticationService.authenticate(adminUsername, adminPassword);

        FileFolderService fileFolderService =
            serviceRegistry.getFileFolderService();

        NodeRef companyHome = repository.getCompanyHome();

//	recupero la directory "events"
        NodeRef eventsFolder = fileFolderService.searchSimple(
                companyHome, "events");
        if (eventsFolder == null) {
            eventsFolder = fileFolderService.create(
                    companyHome,
                    "events",
                    ContentModel.TYPE_FOLDER).getNodeRef();
        }

//	recupero la directory dell'anno corrente
        NodeRef yearFolder = fileFolderService.searchSimple(
                eventsFolder, anno + "");
        if (yearFolder == null) {
            yearFolder = fileFolderService.create(
                    eventsFolder,
                    anno + "",
                    ContentModel.TYPE_FOLDER).getNodeRef();
        }

//	recupero la directory del mese corrente
        NodeRef monthFolder = fileFolderService.searchSimple(
                yearFolder, mese + "");
        if (monthFolder == null) {
            monthFolder = fileFolderService.create(
                    yearFolder,
                    mese + "",
                    ContentModel.TYPE_FOLDER).getNodeRef();
        }

//	recupero la directory del giorno corrente
        NodeRef dayFolder = fileFolderService.searchSimple(
                monthFolder, giorno + "");
        if (dayFolder == null) {
            dayFolder = fileFolderService.create(
                    monthFolder,
                    giorno + "",
                    ContentModel.TYPE_FOLDER).getNodeRef();
        }

        return dayFolder;
    }

    /**
     * @return the serviceRegistry
     */
    public final ServiceRegistry getServiceRegistry() {
        return serviceRegistry;
    }

    /**
     * @param in the serviceRegistry to set
     */
    public final void setServiceRegistry(final ServiceRegistry in) {
        this.serviceRegistry = in;
    }

    /**
     * @return the repository
     */
    public final Repository getRepository() {
        return repository;
    }

    /** 
     * @param in the repository to set
     */
    public final void setRepository(final Repository in) {
        this.repository = in;
    }
}