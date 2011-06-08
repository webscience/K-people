package it.webscience.uima.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringEscapeUtils;



/** estrae una lista di oggetti Split.
 * Dopo aver impostato le stringhe da splittare e lanciato il metodo
 * "process", la classe estrae gli oggetti di tipo Split
 * @author dellanna
 */
public class SplitList {
    /** regular exp. da elaborare in cascata. */
    private List<String> regEx;

    /** indice dello split da estrarre. */
    private List<Integer> arrayIndex;

    /** margine sinistro dello split. */
    private String leftMargin;

    /** margine destro dello split. */
    private String rightMargin;

    /** stringa da elaborare. */
    private String documentText;

    /** lista di oggetti Split.
     * popolata dal metodo "process"
     * @see process()
     */
    private List<Split> splitList;

    /** valore dell'annotation. */
    private String value;

    /** Costruttore. */
    public SplitList() {
        super();
        splitList = new ArrayList<Split>();
        regEx = new ArrayList<String>();
        arrayIndex = new ArrayList<Integer>();
    }

    /** elabora il documento. */
    public final void process() {
//      ricavo la sezione di testo da elaborare
        Split mainTextSplit = new Split();
        mainTextSplit.setArrayIndex(arrayIndex);
        mainTextSplit.setRegEx(regEx);
        mainTextSplit.setDocumentText(documentText);
        mainTextSplit.process();

//      index di inizio della zona da elaborare
        int begin = mainTextSplit.getBegin();
        if (begin != -1) {
            String mainText = mainTextSplit.getValue();

//          primo elemento valido se mainText inizia con leftMargin
            boolean firstElementValid = false;
            if (mainText.startsWith(leftMargin)) {
                firstElementValid = true;
            }

//          eseguo l'escape dell'xml
            mainText = StringEscapeUtils.unescapeXml(mainText);

            String[] splitAreas = mainText.split(leftMargin);
            for (int i = 0; i < splitAreas.length; i++) {
                if (!firstElementValid && i == 0) {
                    begin += splitAreas[0].length();
                    continue;
                }

//              da ogni splitArea viene generato un oggetto Split
                String splitValue = splitAreas[i].split(rightMargin)[0];

    //          shifto il begin al carattere dopo il leftMargin
                begin += leftMargin.length();

                Split split = new Split(
                        begin,
                        begin + splitValue.length(),
                        splitValue);
                splitList.add(split);

                begin += splitAreas[i].length();
            }
        }
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
     * @return the documentText
     */
    public final String getDocumentText() {
        return documentText;
    }

    /**
     * @param in the documentText to set
     */
    public final void setDocumentText(final String in) {
        this.documentText = in;
    }

    /**
     * @return the value
     */
    public final String getValue() {
        return value;
    }

    /**
     * @return the splitList
     */
    public final List<Split> getSplitList() {
        return splitList;
    }

    /**
     * @param in the leftMargin to set
     */
    public final void setLeftMargin(final String in) {
        this.leftMargin = in;
    }

    /**
     * @param in the rightMargin to set
     */
    public final void setRightMargin(final String in) {
        this.rightMargin = in;
    }
}
