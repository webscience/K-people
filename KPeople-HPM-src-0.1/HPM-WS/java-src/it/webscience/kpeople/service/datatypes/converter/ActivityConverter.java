package it.webscience.kpeople.service.datatypes.converter;

import it.webscience.kpeople.service.datatypes.Activity;
import it.webscience.kpeople.service.datatypes.ActivityType;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe di conversione tra la classe Activity BE e Activity Service.
 */
public final class ActivityConverter {

	/**
     * Costruttore privato.
     */
    private ActivityConverter() {
    }

    /**
     * Converte da Service a BE.
     * @param in oggetto Activity Service
     * @return oggetto Activity BE
     */
    public static it.webscience.kpeople.be.Activity toBE(final Activity in) {

        it.webscience.kpeople.be.Activity out =
            new it.webscience.kpeople.be.Activity();

        out.setIdPattern(in.getIdPattern());

        if (in.getDocList() != null) {
            out.setDocList(DocumentConverter.toBE(in.getDocList()));
        }
        
        if (in.getPattern() != null) {
        	out.setPattern(PatternConverter.toBE(in.getPattern()));
        }
        
        if (in.getActivityType() != null) {
        	out.setActivityType(ActivityTypeConverter.toBE(in.getActivityType()));
        }
        
        out.setTitle(in.getTitle());
        out.setDescription(in.getDescription());
        
        if (in.getActivityOwner() != null) {
        	out.setActivityOwner(UserConverter.toBE(in.getActivityOwner()));
        }
        
        if (in.getActivityRequestor() != null) {
        	out.setActivityRequestor(UserConverter.toBE(in.getActivityRequestor()));
        }
        
        out.setCreateDate(in.getCreateDate());
        out.setDueDate(in.getDueDate());
        out.setClosed(in.isClosed());
        out.setClosedDate(in.getClosedDate());
        
        if (in.getActivityState() != null) {
        	out.setActivityState(ActivityStateConverter.toBE(in.getActivityState()));
        }
        
        out.setActivitiProcessTaskId(in.getActivitiProcessTaskId());
        out.setHpmActivityId(in.getHpmActivityId());
        
        if (in.getActivityMetadata() != null) {
            out.setActivityMetadata(ActivityMetadataConverter.toBE(
            		in.getActivityMetadata()));
        }
        
        DataTraceClassConverter.toBE(in, out);

        return out;
    }
    
    /**
     * Converte da array Service a List BE.
     * @param in array di oggetti Activity Service
     * @return Lista BE
     */
    public static List<it.webscience.kpeople.be.Activity>
        toBE(final Activity[] in) {

        List<it.webscience.kpeople.be.Activity> out =
            new ArrayList<it.webscience.kpeople.be.Activity>();

        for (int i = 0; i < in.length; i++) {
            out.add(toBE(in[i]));
        }

        return out;
    }
    
    /**
     * Converte da List Process BE a array Activity Service.
     * @param in List Activity BE
     * @return array Activity Service
     */
    public static Activity[] toService(
            final List<it.webscience.kpeople.be.Activity> in) {

        Activity[] out = new Activity[in.size()];

        for (int i = 0; i < in.size(); i++) {
            out[i] = toService(in.get(i));
        }
        return out;
    }
    
    /**
     * Converte oggetto Activity BE in Service.
     * @param in oggetto Activity BE
     * @return oggetto Activity Service
     */
    public static Activity toService(
            final it.webscience.kpeople.be.Activity in) {
        Activity out = new Activity();

        out.setIdPattern(in.getIdPattern());
        
        if (in.getDocList() != null) {
            out.setDocList(DocumentConverter.toService(in.getDocList()));
        }
        
        if (in.getPattern() != null) {
        	out.setPattern(PatternConverter.toService(in.getPattern()));
        }
        
        if (in.getActivityType() != null) {
        	out.setActivityType(ActivityTypeConverter.toService(
        		in.getActivityType()));
        }
        
        out.setTitle(in.getTitle());
        out.setDescription(in.getDescription());
        
        if (in.getActivityOwner() != null) {
        out.setActivityOwner(
        		UserConverter.toService(in.getActivityOwner()));
        }
        
        if (in.getActivityRequestor() != null) {
        out.setActivityRequestor(
        		UserConverter.toService(in.getActivityRequestor()));
        }
        
        out.setCreateDate(in.getCreateDate());
        out.setDueDate(in.getDueDate());
        out.setClosed(in.isClosed());
        out.setClosedDate(in.getClosedDate());
        
        if (in.getActivityState() != null) {
        	out.setActivityState(ActivityStateConverter.toService(in.getActivityState()));
        }	
        
        out.setActivitiProcessTaskId(in.getActivitiProcessTaskId());
        out.setHpmActivityId(in.getHpmActivityId());
        
        if (in.getActivityMetadata() != null) {
            out.setActivityMetadata(ActivityMetadataConverter.toService(
            		in.getActivityMetadata()));
        }
        
        DataTraceClassConverter.toService(in, out);

        return out;
    }
}
