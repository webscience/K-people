package it.webscience.kpeople.service.datatypes;

/**
 * @author depascalis
 */
public class UserEvent {

    /**
     * hpmUserId dell'utente creatore dell'evento o dell'utente
     * impegnato in communicationTo o communication CC.
     */
    private String hpmUserId;

    /**
     * hpmEventId dell'evento.
     */
    private String hpmEventId;

    /**
     * @return the hpmUserId.
     */
    public final String getHpmUserId() {
        return hpmUserId;
    }

    /**
     * @param in the hpmUserId to set.
     */
    public final void setHpmUserId(final String in) {
        this.hpmUserId = in;
    }

    /**
     * @return the hpmEventId
     */
    public final String getHpmEventId() {
        return hpmEventId;
    }

    /**
     * @param in the hpmEventId to set
     */
    public final void setHpmEventId(final String in) {
        this.hpmEventId = in;
    }

}



