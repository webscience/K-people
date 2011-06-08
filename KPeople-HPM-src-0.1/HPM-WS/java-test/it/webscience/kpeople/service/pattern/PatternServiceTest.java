package it.webscience.kpeople.service.pattern;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;
import it.webscience.kpeople.dal.cross.AttachmentTypeDAO;
import it.webscience.kpeople.dal.cross.UserDAO;
import it.webscience.kpeople.dal.pattern.PatternDAO;
import it.webscience.kpeople.dal.pattern.PatternStateDAO;
import it.webscience.kpeople.dal.pattern.PatternTypeDAO;
import it.webscience.kpeople.service.cross.UserService;
import it.webscience.kpeople.service.datatypes.AttachmentType;
import it.webscience.kpeople.service.datatypes.Document;
import it.webscience.kpeople.service.datatypes.Pattern;
import it.webscience.kpeople.service.datatypes.PatternMetadata;
import it.webscience.kpeople.service.datatypes.PatternState;
import it.webscience.kpeople.service.datatypes.PatternType;
import it.webscience.kpeople.service.datatypes.Process;
import it.webscience.kpeople.service.datatypes.User;
import it.webscience.kpeople.service.datatypes.converter.AttachmentTypeConverter;
import it.webscience.kpeople.service.datatypes.converter.PatternStateConverter;
import it.webscience.kpeople.service.datatypes.converter.PatternTypeConverter;
import it.webscience.kpeople.service.datatypes.converter.UserConverter;
import it.webscience.kpeople.service.exception.KPeopleServiceException;
import it.webscience.kpeople.service.process.ProcessService;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

/**
 * JUnit del dao PatternService.
 * @author gnoni
 */
public class PatternServiceTest {

    /**
     * test del metodo DAO getPatternTypes.
     * @throws Exception
     *             eccezione generica
     */
    @Test
    public final void getPatternTypesTest() throws Exception {

        PatternService ps = new PatternService();
        PatternType[] patternTypes = null;
        UserDAO userDao = new UserDAO();
        it.webscience.kpeople.be.User user =
                userDao.getUserByHpmUserId("bolognese@kpeople.webscience.it");
        User userDT = UserConverter.toService(user);
        patternTypes = ps.getPatternTypes(true, userDT);
        assertEquals(6, patternTypes.length);
    }
    
    private PatternType[] getPatternTypes() throws Exception {
        User psUser = new User();
        psUser.setIdUser(1045);

        PatternService ps = new PatternService();
        PatternType[] patternTypeResults = ps.getPatternTypes(false, psUser);
        
        String hpmProcessId = "app1";

        return patternTypeResults;
    }

    private PatternType getPatternType(
            final String patternTypeId)
            throws Exception {

        PatternType[] patternTypes = getPatternTypes();

        PatternType result = null;
        for (PatternType pt : patternTypes) {
            int id = pt.getIdPatternType();
            if (String.valueOf(id).equals(patternTypeId)) {
                result = pt;
            }
        }
        return result;
    }

    @Test
    public final void testStartPattern() throws Exception {
        Pattern psPattern = new Pattern();

        // set requestor
        User psUserRequestor = new User();
        psUserRequestor.setIdUser(1048);
        psUserRequestor.setHpmUserId("filieri@kpeople.webscience.it");
        psPattern.setPatternRequestor(psUserRequestor);

        // set user to
        User psUserProvider = new User();
        String patternProvider = "spalluto@kpeople.webscience.it";
        psUserProvider.setHpmUserId(patternProvider);
        psPattern.setPatternProvider(psUserProvider);

        // set user cc
        //User psUserCc = new User();
        //String patternCc = "dellanna@kpeople.webscience.it";
        //psUserCc.setHpmUserId(patternCc);

//        User[] ccUser = new User[1];
//        ccUser[0] = psUserRequestor;
//        psPattern.setCcUsers(ccUser);

        // create process
        String hpmProcessId = "app1";
        Process psProcess = new Process();
        psProcess.setHpmProcessId(hpmProcessId);

        // create patternType
        String patternTypeId = "1";
        PatternType patternType =
                getPatternType(patternTypeId);
        psPattern.setPatternType(patternType);

        // create attachmentType
        AttachmentType psAttType =
                new AttachmentType();
        psAttType.setIdAttachmentType(3);
        psAttType.setName("pattern");
        psPattern.setAttachmentType(psAttType);

        // create PatternState
        PatternState psPatternState =
                new PatternState();
        psPatternState.setIdPatternState(1);
        psPatternState.setState("Inviata");
        psPattern.setPatternState(psPatternState);

        PatternMetadata[] patternMetadata = new PatternMetadata[1];
        PatternMetadata metadata = new PatternMetadata();
        String requestTypeValue = "Documento";
        metadata.setKeyname("Tipo di richiesta");
        metadata.setValue(requestTypeValue);
        metadata.setActivitiProcessMetadata(false);
        patternMetadata[0] = metadata;
        psPattern.setPatternMetadatas(patternMetadata);

        String patternName = "test title";
        psPattern.setName(patternName);

        String patternDescription = "desci....";
        psPattern.setDescription(patternDescription);

        psPattern.setDueDate(new Date());

        psPattern.setStartDate(new Date());

//      recupero gli attachments
 //       setPatternDoclist(psPattern);

        PatternService ps = new PatternService();
        ps.startPattern(psPattern, psUserRequestor, psProcess);
    }

    /**
     * converte un File in un byte arrayu
     * @param file
     * @return
     * @throws FileNotFoundException 
     */
    private byte[] readFile(File file) throws FileNotFoundException {
        FileInputStream fis = new FileInputStream(file);
        //System.out.println(file.exists() + "!!");
        //InputStream in = resource.openStream();
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] buf = new byte[1024];
        try {
            for (int readNum; (readNum = fis.read(buf)) != -1;) {
                bos.write(buf, 0, readNum); //no doubt here is 0
                //Writes len bytes from the specified byte array starting at offset off to this byte array output stream.
            }
        } catch (IOException ex) {
           
        }
        byte[] bytes = bos.toByteArray(); 
        
        return bytes;
    }

    /**
     * Recupero degli attachments per un pattern.
     * @param psPattern pattern
     */
    private void setPatternDoclist(
            final Pattern psPattern) throws FileNotFoundException {

        List<Document> docList = new ArrayList<Document>();

        File file = new File("C:\\WebScience\\varie\\logo.jpg");
        Document doc = new Document();
        doc.setFileName("logo.jpg");
        doc.setData(readFile(file));
        doc.setHashcode("hashcode....");
        docList.add(doc);

        File file2 = new File("C:\\fabio.tmp");
        Document doc2 = new Document();
        doc2.setData(readFile(file2));
        doc2.setFileName("fabio.tmp");
        doc2.setHashcode("hashcode2....");
        docList.add(doc2);

        psPattern.setDocList(
                (Document[])
                docList.toArray(new Document[0]));
    }

    @Test
    public final void getPatternTypeByPatternTypeIdTest() throws Exception {
    	PatternService ps = new PatternService();
    	User user = (new UserService()).getUserByHpmUserId("bolognese@kpeople.webscience.it");
    	PatternType pt = new PatternType();
    	pt.setIdPatternType(1);
    	pt = ps.getPatternTypeByPatternTypeId(pt, user);
    	assertEquals(1, pt.getIdPatternType());
		assertEquals("Richiedi contributo", pt.getName());
		assertEquals("RCONTR", pt.getPatternTypeCode());
		assertEquals("richiestaContributoFlow:1:115", pt.getActivitiProcessDefinitionId());
		assertEquals("richiestaContributoFlow:1:115", pt.getHpmPatternTypeId());
    }
    /*
     * @Test public final void startPatternTest() throws Exception {
     * PatternService ps = new PatternService(); Pattern p=new Pattern();
     * UserDAO userDao=new UserDAO(); User providerBE =
     * UserConverter.toService(userDao.
     * getUserByHpmUserId("nicoli@kpeople.webscience.it")); User requestorBE =
     * UserConverter.toService(userDao.
     * getUserByHpmUserId("bolognese@kpeople.webscience.it"));
     * p.setPatternProvider(providerBE); p.setPatternRequestor(requestorBE);
     * PatternType pt=new PatternType(); pt.setIdPatternType(1);
     * pt.setName("Pattern di test");
     * pt.setActivitiProcessDefinitionId("vacationRequest:1:38");
     * p.setPatternType(pt); Pattern p1=ps.startPattern(p); assertNotSame("",
     * p1.getActivitiProcessInstanceId()); }
     */
    
    @Test
    public final void startPatternTest() throws Exception {
        PatternService patternService = new PatternService();

        User provider = new User();
        provider.setHpmUserId("dellanna@kpeople.webscience.it");
        
        UserService service = new UserService();
        User requestor = service.getUserByHpmUserId("filieri@kpeople.webscience.it");
        
        User cc1 = new User();
        cc1.setHpmUserId("ustabeqiri@kpeople.webscience.it");
            
        User cc2  = new User();
        cc2.setHpmUserId("spalluto@kpeople.webscience.it");

        User[] userCC = new User[2];
        userCC[0] = cc1;
        userCC[1] = cc2;
        
        Pattern pattern = new Pattern();
        pattern.setCcUsers(userCC);

        pattern.setPatternProvider(provider);
        pattern.setPatternRequestor(requestor);

        ProcessService processService = new ProcessService();
        Process process = processService.getProcessByHpmId("app10", requestor);
        
        PatternTypeDAO jj = new PatternTypeDAO();
        
        PatternType pt = PatternTypeConverter.toService(jj.getPatternTypeById(1));
        pattern.setPatternType(pt);

        AttachmentTypeDAO attTypeDao = new AttachmentTypeDAO();
        AttachmentType attType = AttachmentTypeConverter.toService(
        		attTypeDao.getAttachmentTypeByIdAttachmentType(3));
        //AttachmentType attType = new AttachmentType();
        //attType.setIdAttachmentType(3);
        //attType.setName("pattern");
        
        pattern.setAttachmentType(attType);
        
        PatternStateDAO psDao = new PatternStateDAO();
        PatternState ps = PatternStateConverter.toService(psDao.getPatternStateById(1));
        
        //PatternState ps=new PatternState();
        //ps.setIdPatternState(1);
        //ps.setState("Accettata");
        pattern.setPatternState(ps);

        pattern.setName("nome di prova");
        pattern.setDescription("descrizione di prova");
        pattern.setStartDate(new Date());

        PatternMetadata[] patternMetadatas = new PatternMetadata[1];
        PatternMetadata patternMetadata = new PatternMetadata();

        patternMetadata.setKeyname("Tipo di richiesta");
        patternMetadata.setValue("informazioni");
        patternMetadata.setActivitiProcessMetadata(false);
        
        patternMetadatas[0] = patternMetadata;
        pattern.setPatternMetadatas(patternMetadatas);
        

        Pattern p1 = patternService.startPattern(pattern, requestor, process);

        assertNotSame("", p1.getActivitiProcessInstanceId());
    }

    @Test
    public void closePatternFromActivitiTest() throws Exception {
    	
    	PatternService pServ = new PatternService();
    	boolean ret = pServ.closePatternFromActiviti("2470", "filieri@kpeople.webscience.it");
    	Assert.assertEquals(false, ret);
    }

    @Test
    public void testPatternDetailByHpmPatternId() throws Exception {
        PatternService pServ = new PatternService();

        Pattern pPattern = new Pattern();
        pPattern.setHpmPatternId("app27-PATRICCONTR1524");
        User pLoggedUser = new User();

        pServ.patternDetailByHpmPatternId(pPattern, pLoggedUser);
    }
}
