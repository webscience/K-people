package it.webscience.uima.util;

import java.util.ArrayList;
import java.util.List;


/** estrae una lista di oggetti Split.
 * Dopo aver impostato le stringhe da splittare e lanciato il metodo
 * "process", la classe estrae gli oggetti di tipo Split
 * @author dellanna
 */
public class Split {
    private final int PATH_NOT_FOUND_VALUE = -1;

    /** regular exp. da elaborare in cascata. */
    private List<String> regEx;

    /** indice dello split da estrarre. */
    private List<Integer> arrayIndex;

    /** stringa da elaborare. */
    private String documentText;

    /** inizio dell'annotation. */
    private int begin;
    /** fine dell'annotation. */
    private int end;

    /** valore dell'annotation. */
    private String value;

    /** Costruttore. */
    public Split() {
        super();
        regEx = new ArrayList<String>();
        arrayIndex = new ArrayList<Integer>();
    }

    /** costruttore parametrizzato.
     * @param b begin
     * @param e end
     * @param v value
     */
    public Split(final int b, final int e, final String v) {
        super();
        this.begin = b;
        this.end = e;
        this.value = v;
    }

    /** elabora il documento. */
    public final void process() {
        begin = 0;

        String procDocument = documentText;

        for (int i = 0; i < regEx.size(); i++) {
            String currentRexEx = regEx.get(i);
            int currentIndex = arrayIndex.get(i);

            String[] split = procDocument.split(currentRexEx, currentIndex + 2);

//          controllo sull'esistenza dell'indice.
            if (split.length <= currentIndex) {
                begin = PATH_NOT_FOUND_VALUE;
                return;
            }

            for (int j = 0; j < currentIndex; j++) {
                begin += split[j].length();
                begin += currentRexEx.length();
            }
            procDocument = split[currentIndex];
        }

        value = procDocument;
        end = begin + procDocument.length();
    }

    /** aggiunge uno split in coda.
     * @param regExp regular expression
     * @param splitIndex indice da estrarre dopo lo split
     */
    public final void add(final String regExp, final Integer splitIndex) {
        regEx.add(regExp);
        arrayIndex.add(splitIndex);
    }

    /**
     * @param in the documentText to set
     */
    public final void setDocumentText(final String in) {
        this.documentText = in;
    }

    /**
     * @return the begin
     */
    public final int getBegin() {
        return begin;
    }

    /**
     * @return the end
     */
    public final int getEnd() {
        return end;
    }

    /**
     * @return the value
     */
    public final String getValue() {
        return value;
    }

    /**
     * @param in the regEx to set
     */
    public final void setRegEx(final List<String> in) {
        this.regEx = in;
    }

    /**
     * @param in the arrayIndex to set
     */
    public final void setArrayIndex(final List<Integer> in) {
        this.arrayIndex = in;
    }
}
