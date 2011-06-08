package it.webscience.kpeople.service.datatypes.converter;

import java.util.ArrayList;
import java.util.List;

import com.ontotext.trree.owlim_ext.i;

import it.webscience.kpeople.be.AttachmentType;
import it.webscience.kpeople.service.datatypes.Pattern;
import it.webscience.kpeople.service.datatypes.PatternState;
import it.webscience.kpeople.service.datatypes.Process;


public class PatternConverter {
	
	/**
     * Costruttore privato.
     */
    private PatternConverter() {
    }
    
    /**
     * Converte da Service a BE.
     * @param in oggetto Pattern Service
     * @return oggetto Pattern BE
     */
    public static it.webscience.kpeople.be.Pattern toBE(
    		final Pattern in) {

        it.webscience.kpeople.be.Pattern out =
            new it.webscience.kpeople.be.Pattern();

      //Valori classe madre Event
        out.setIdAttachment(in.getIdAttachment());
        
        if (in.getDocList() != null) {
            out.setDocList(DocumentConverter.toBE(in.getDocList()));
        }
        
        if (in.getAttachmentType() != null) {
            out.setAttachmentType(
            		AttachmentTypeConverter.toBE(in.getAttachmentType()));
        }
        
        out.setDescription(in.getDescription());
        out.setName(in.getName());
                
        //Valori classe Pattern
        
        if (in.getPatternType() != null) {
            out.setPatternType(PatternTypeConverter.toBE(in.getPatternType()));
        }

        if (in.getPatternState() != null) {
            out.setPatternState(PatternStateConverter.toBE(
            		in.getPatternState()));
        }
        
        if (in.getPatternMetadatas() != null) {
            out.setPatternMetadatas(PatternMetadataConverter.toBE(
            		in.getPatternMetadatas()));
        }
        
        if (in.getPatternRequestor() != null) {
            out.setPatternRequestor(
                    UserConverter.toBE(in.getPatternRequestor()));
        }
        
        if (in.getPatternProvider() != null) {
            out.setPatternProvider(
            		UserConverter.toBE(in.getPatternProvider()));
        }
        
        if (in.getCcUsers() != null) {
            out.setCcUsers(UserConverter.toBE(
            		in.getCcUsers()));
        }
        
        out.setStartDate(in.getStartDate());
        out.setEndDate(in.getEndDate());
        out.setDueDate(in.getDueDate());
        out.setActivitiProcessInstanceId(in.getActivitiProcessInstanceId());
        out.setHpmPatternId(in.getHpmPatternId());
        out.setWaitingActivity(in.isWaitingActivity());
        out.setWaitingActivityDate(in.getWaitingActivityDate());
        

        return out;
    }
    
    /**
     * Converte da array Service a List BE.
     * @param in array di oggetti Pattern Service
     * @return Lista BE
     */
    public static List<it.webscience.kpeople.be.Pattern>
        toBE(final Pattern[] in) {

        List<it.webscience.kpeople.be.Pattern> out =
            new ArrayList<it.webscience.kpeople.be.Pattern>();

        for (int i = 0; i < in.length; i++) {
            out.add(toBE(in[i]));
        }

        return out;
    }
    
    /**
     * Converte da List Process BE a array Process Service.
     * @param in List Process BE
     * @return array Process Service
     */
    public static Pattern[] toService(
            final List<it.webscience.kpeople.be.Pattern> in) {

        Pattern[] out = new Pattern[in.size()];

        for (int i = 0; i < in.size(); i++) {
            out[i] = toService(in.get(i));
        }
        return out;
    }
    
    /**
     * Converte oggetto Process BE in Service.
     * @param in oggetto Process BE
     * @return oggetto Process Service
     */
    public static Pattern toService(
            final it.webscience.kpeople.be.Pattern in) {
        Pattern out = new Pattern();

        out.setIdAttachment(in.getIdAttachment());
        out.setAttachmentType(AttachmentTypeConverter.toService(in.getAttachmentType()));
        out.setDescription(in.getDescription());
        out.setName(in.getName());

        out.setDocList(DocumentConverter.toService(in.getDocList()));

        out.setPatternType(PatternTypeConverter.toService(
        		in.getPatternType()));
        out.setPatternState(PatternStateConverter.toService(
        		in.getPatternState()));
        out.setPatternMetadatas(PatternMetadataConverter.toService(
        		in.getPatternMetadatas()));
        out.setPatternRequestor(UserConverter.toService(
        		in.getPatternRequestor()));
        out.setPatternProvider(UserConverter.toService(
        		in.getPatternProvider()));

        if (in.getCcUsers() != null) {
            out.setCcUsers(UserConverter.toService(in.getCcUsers()));
        }
        
        out.setStartDate(in.getStartDate());
        out.setEndDate(in.getEndDate());
        out.setDueDate(in.getDueDate());
        out.setActivitiProcessInstanceId(in.getActivitiProcessInstanceId());
        out.setHpmPatternId(in.getHpmPatternId());
        out.setWaitingActivity(in.isWaitingActivity());
        out.setWaitingActivityDate(in.getWaitingActivityDate());
        
        
        return out;
    }
}
