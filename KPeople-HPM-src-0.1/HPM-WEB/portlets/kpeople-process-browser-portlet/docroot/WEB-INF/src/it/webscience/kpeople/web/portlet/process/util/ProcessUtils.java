package it.webscience.kpeople.web.portlet.process.util;

import java.util.Date;

public class ProcessUtils {

    /**
     * Metodo per calcolare la diffenza in giorni, tra la data attuale e la data
     * di scadenza del processo.
     * @param dateDue data di scadenza del processo.
     * @return
     */
    public static long getDaysToProcessDue(Date dateDue) {

        long processDateDue = dateDue.getTime();
        long actualDate = new Date().getTime();
        long diff = processDateDue - actualDate;
        long diffDays = diff / (24 * 60 * 60 * 1000);
        return (diffDays + 1) * - 1;
    }

}
