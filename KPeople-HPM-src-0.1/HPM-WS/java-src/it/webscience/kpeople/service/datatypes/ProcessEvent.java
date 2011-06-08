package it.webscience.kpeople.service.datatypes;

import java.util.List;

public class ProcessEvent {

    /**
     * HpmProcessId del processo.
     */
    private String hpmProcessId;

    /**
     * Array degli hpmEventId degli eventi relativi al processo.
     */
    private String hpmEventId;


    /**
     * Costruttore.
     */
    public ProcessEvent() {

    }
    /**
     * @return the hpmProcessId
     */
    public final String getHpmProcessId() {
        return hpmProcessId;
    }
    /**
     * @param in the hpmProcessId to set
     */
    public final void setHpmProcessId(final String in) {
        this.hpmProcessId = in;
    }

    /**
     * @return the hpmEventId
     */
    public final String getHpmEventId() {
        return hpmEventId;
    }
    /**
     * @param in the hpmEventId to set.
     */
    public final void setHpmEventId(final String in) {
        this.hpmEventId = in;
    }
}
