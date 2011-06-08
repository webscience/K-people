package it.webscience.kpeople.be;

import java.util.ArrayList;
import java.util.List;

/**
 * @author depascalis.
 */
public class ProcessEvent {

    /**
     * HpmProcessId del processo.
     */
    private String hpmProcessId;

    /**
     *  hpmEventId dell' evento relativo al processo.
     */
    private String hpmEventId;

    /**
     * Costruttore.
     */
    public ProcessEvent() {

    }

    /**
     * @return the hpmProcessId.
     */
    public final String getHpmProcessId() {
        return hpmProcessId;
    }

    /**
     * @param in
     *            the hpmProcessId to set.
     */
    public final void setHpmProcessId(final String in) {
        this.hpmProcessId = in;
    }

    /**
     * @return the hpmEventId.
     */
    public final String getHpmEventId() {
        return hpmEventId;
    }

    /**
     * @param in hpmEventId to set.
     */
    public final void setHpmEventId(final String in) {
        this.hpmEventId = in;
    }

}
