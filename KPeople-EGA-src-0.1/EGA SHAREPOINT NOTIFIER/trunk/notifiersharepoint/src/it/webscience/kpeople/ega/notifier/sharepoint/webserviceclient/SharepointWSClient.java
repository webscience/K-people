package it.webscience.kpeople.ega.notifier.sharepoint.webserviceclient;

import it.webscience.kpeople.ega.notifier.sharepoint.KpeopleLogger;
import it.webscience.kpeople.ega.notifier.sharepoint.soap.ListsStub;
import it.webscience.kpeople.ega.notifier.sharepoint.util.configuration.SharepointConfigurationManager;
import it.webscience.kpeople.ega.notifier.sharepoint.util.configuration.SharepointNotifierParameters;
import it.webscience.kpeople.ega.notifier.sharepoint.util.parser.DocumentDeletedParser;
import it.webscience.kpeople.ega.notifier.sharepoint.util.parser.DocumentModifiedParser;
import it.webscience.kpeople.logging.serviceInterface.KpeopleLog;

import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import java.util.List;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLStreamException;

import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMFactory;

import org.apache.axis2.AxisFault;

import org.apache.axis2.context.ConfigurationContext;
import org.apache.axis2.context.ConfigurationContextFactory;
import org.apache.axis2.databinding.ADBException;

import org.apache.axis2.transport.http.HTTPConstants;
import org.apache.axis2.transport.http.HttpTransportProperties;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;

import com.microsoft.schemas.sharepoint.soap.GetListItemChanges;
import com.microsoft.schemas.sharepoint.soap.GetListItemChangesResponse;
import com.microsoft.schemas.sharepoint.soap.GetListItemChangesResult_type0;
import com.microsoft.schemas.sharepoint.soap.GetListItems;
import com.microsoft.schemas.sharepoint.soap.GetListItemsResponse;
import com.microsoft.schemas.sharepoint.soap.GetListItemsResult_type0;
import com.microsoft.schemas.sharepoint.soap.QueryOptions_type0;

/**
 * Classe per la costruzione del client per la connessione ai webservice esposti
 * da Sharepoint al fine di recuperare le informazioni sui documenti contenuti
 * nelle liste da monitorare.
 * 
 * @author filieri
 */
public class SharepointWSClient implements Runnable {

    /**
     * Variabile booleana per gestire lo stop del Thread in esecuzione.
     */
    private boolean stopped;
    
    private static KpeopleLog logService = KpeopleLogger.getInstance().getService();

    
    public SharepointWSClient() {
             
    }
    
    
    /**
     * Metodo invocato dal metodo run() per consentire l'avvio effettivo del
     * Thread.
     */
    public void call() {

        ConfigurationContext configuration = null;
        try {
            configuration = setConfiguration();
        } catch (Exception e1) {
            e1.printStackTrace();
        }

        int numberList = Integer.parseInt(SharepointNotifierParameters
                .getNumberList());
        String sharepointServer = SharepointNotifierParameters
                .getSharepointServer();
        for (int x = 1; x <= numberList; x++) {
            String endpointAndList = SharepointNotifierParameters
                    .getEndpointAndList(x);

            String endpoint = endpointAndList.split("@")[0];

            logService.logInfo(SharepointWSClient.class.getName(),
                    " : Monitor..... " + endpoint);

            String list = endpointAndList.split("@")[1];

            String[] listsToMonitor = list.split(",");
            Date sinceDate = SharepointNotifierParameters.getSinceDate();

            SimpleDateFormat df = new SimpleDateFormat(
                    "yyyy-MM-d'T'HH:mm:ss'Z'");
            String sinceDateString = df.format(sinceDate);

            ListsStub service;
            try {
                service = new ListsStub(configuration, endpoint);
                long soTimeout = 2 * 60 * 1000; // Two minutes
                service._getServiceClient().getOptions().setTimeOutInMilliSeconds(soTimeout);

                for (String listName : listsToMonitor) {
                    listName = listName.trim();
                    // richiama il metodo per l'individuazione dei cambiamenti
                    // inserimenti e modifiche sulla lista in esame.
                    OMElement lastChangedItems = retrieveLastChange(service,
                            listName, sinceDateString);
                    if (lastChangedItems != null) {
                        logService.logDebug(SharepointWSClient.class.getName(),
                                " : response retrieveLastChange " + lastChangedItems.toStringWithConsume());
                        //DocumentModifiedParser.parse(lastChangedItems,
                        //        sharepointServer);
                        DocumentModifiedParser.parse(lastChangedItems,
                                endpoint, sharepointServer);

                    } else {
                        logService.logDebug(SharepointWSClient.class.getName(),
                                " : la lista in esame non ha subito modifiche da "
                                        + sinceDate);
                    }

                    // richiama il metodo per il recupero di tutti i documenti
                    // contenuti nella lista in esame.
                    OMElement allItemsInList = retriveAllItemsList(service,
                            listName);

                    if (allItemsInList != null) {
                        DocumentDeletedParser.parse(allItemsInList,
                                sharepointServer);
                        logService.logDebug(SharepointWSClient.class.getName(),
                                " : response retriveAllItemsList" + allItemsInList.toStringWithConsume());
                    }

                }
            } catch (AxisFault e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Metodo per recuperare dal web service esposto da Sharepoint la lista dei
     * documenti che sono stati modificati successivamente alla data fornita
     * come parametro in ingresso.
     * 
     * @param service
     *            - istanza del servizio
     * @param listName
     *            - nome della lista sharepoint da monitorare.
     * @param since
     *            - data a partire dalla quale si richiede l'individuazione di
     *            eventuali cambiamenti.
     * @throws RemoteException
     *             - eccezione che si verifica in caso di problemi nella
     *             chiamata remota al web service sharepoint.
     * @return OMElement - elemento che rappresenta la risposta fornita dal web
     *         service sharepoint.
     */
    private static OMElement retrieveLastChange(final ListsStub service,
            final String listName, final String since) throws RemoteException {

        OMElement lastChangedItems = null;
        GetListItemChanges items = new GetListItemChanges();
        items.setListName(listName);
        items.setSince(since);

        GetListItemChangesResponse response;
        try {
            response = service.getListItemChanges(items);
        } catch (RemoteException e) {

            e.printStackTrace();
            throw e;
        }

        GetListItemChangesResult_type0 result = response
                .getGetListItemChangesResult();
        OMElement elements = result.getExtraElement();

        OMElement lastChanged = (OMElement) elements.getFirstOMChild();
        QName itemCount = new QName("ItemCount");

        String count = lastChanged.getAttributeValue(itemCount);
        if (!count.equalsIgnoreCase("0")) {
            lastChangedItems = lastChanged;
        }

        return lastChangedItems;
    }

    /**
     * Classe per impostare i parametri di configurazione e autenticazione verso
     * i webservice esposti da sharepoint.
     * @return ConfigurationContext - @see
     *         org.apache.axis2.context.ConfigurationContext
     * @throws Exception
     */
    private static ConfigurationContext setConfiguration() throws Exception {

        HttpTransportProperties.Authenticator authenticator
        = new HttpTransportProperties.Authenticator();

        List<String> auth = new ArrayList<String>();

       // auth.add(HttpTransportProperties.Authenticator.NTLM);
        auth.add(HttpTransportProperties.Authenticator.NTLM);

        authenticator.setAuthSchemes(auth);

        String username = SharepointNotifierParameters.getUsername();
        authenticator.setUsername(username);

        String password = SharepointNotifierParameters.getPassword();
        authenticator.setPassword(password);

        String domain = SharepointNotifierParameters.getDomain();
        authenticator.setDomain(domain);

        String host = SharepointNotifierParameters.getHost();
        authenticator.setHost(host);

        authenticator.setPreemptiveAuthentication(true);

        ConfigurationContext configuration;
        try {
            configuration = ConfigurationContextFactory
                    .createDefaultConfigurationContext();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

        configuration.setProperty(HTTPConstants.AUTHENTICATE, authenticator);

        return configuration;

    }

    /**
     * @param service
     * @param listName
     * @return
     * @throws RemoteException
     * @throws XMLStreamException
     */
    private static OMElement retriveAllItemsList(final ListsStub service,
            final String listName) throws RemoteException, XMLStreamException {

        // List<OMElement> allItemsResult = new LinkedList<OMElement>();
        OMElement itemsResult = null;

        GetListItems items = new GetListItems();
        items.setListName(listName);

        OMFactory fac = OMAbstractFactory.getOMFactory();

        QName options = new QName("QueryOptions");
        OMElement queryOptions = fac.createOMElement(options);

        QName viewAttributes = new QName("ViewAttributes");
        OMElement attributes = fac.createOMElement(viewAttributes);
        attributes.addAttribute("Scope", "Recursive", null);
        queryOptions.addChild(attributes);

        QueryOptions_type0 param = new QueryOptions_type0();
        param.setExtraElement(queryOptions);

        items.setQueryOptions(param);

        try {
            OMElement omElement = items.getOMElement(items.MY_QNAME,
                    OMAbstractFactory.getOMFactory());
            String omElementString = omElement.toStringWithConsume();
            
            logService.logDebug(DocumentModifiedParser.class.getName(),
                    " request " + omElementString);
        } catch (ADBException e1) {
            e1.printStackTrace();
        }

        GetListItemsResponse response;
        try {
            response = service.getListItems(items);
        } catch (RemoteException e) {
            e.printStackTrace();
            throw e;
        }

        GetListItemsResult_type0 result = response.getGetListItemsResult();
        OMElement allItems = result.getExtraElement();

        OMElement allItemsResult = (OMElement) allItems.getFirstElement();
        QName itemCount = new QName("ItemCount");

        String count = allItemsResult.getAttributeValue(itemCount);
        if (!count.equalsIgnoreCase("0")) {
            itemsResult = allItemsResult;

        }

        return itemsResult;
    }

    /**
     * Quando un oggetto della classe SharepointWSClient, che implementa
     * l'interfaccia Runnable, è usato per creare un thread, il metodo start,
     * invocato sull'oggetto determina il richiamo automatico al metodo run.
     * 
     * @see java.lang.Runnable#run()
     */
    public void run() {
        while (true) {
            try {
                call();
                long threadSleep = Long.parseLong(SharepointNotifierParameters
                        .getThreadSleep());
                Thread.sleep(threadSleep);
                synchronized (this) {
                    if (stopped) {
                        break;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    /**
     * Metodo per effettuare lo stop effettivo del thread in esecuzione.
     */
    synchronized void stop() {
        stopped = true;

        notify();
    }

    /**
     * Metodo invocato dal metodo stop del bundle per terminare l'esecuzione del
     * thread.
     */
    public void kill() {
        stop();
    }

}
