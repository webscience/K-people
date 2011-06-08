package it.webscience.kpeople.web.portlet.pattern.util;

import java.util.Date;

public class PatternUtils {

    /**
     * Metodo per calcolare la diffenza in giorni, tra la data attuale e la data
     * di scadenza del pattern.
     * @param dateDue data di scadenza del processo.
     * @return
     */
    public static long getDaysToPatternDue(Date dateDue) {

        long patternDateDue = dateDue.getTime();
        long actualDate = new Date().getTime();
        long diff = patternDateDue - actualDate;
        long diffDays = diff / (24 * 60 * 60 * 1000);
        return (diffDays + 1) * - 1;
    }

}
