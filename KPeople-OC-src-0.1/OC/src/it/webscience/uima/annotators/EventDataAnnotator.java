package it.webscience.uima.annotators;

import it.webscience.uima.annotations.UserAnnotation;
import it.webscience.uima.annotations.eventData.AbstractAnnotation;
import it.webscience.uima.annotations.eventData.AttachmentAnnotation;
import it.webscience.uima.annotations.eventData.BodyAnnotation;
import it.webscience.uima.annotations.eventData.DateCreationDateAnnotation;
import it.webscience.uima.annotations.eventData.DetailsAnnotation;
import it.webscience.uima.annotations.eventData.PropertyAnnotation;
import it.webscience.uima.annotations.eventData.TitleAnnotation;
import it.webscience.uima.annotations.eventData.UserAuthorAnnotation;
import it.webscience.uima.annotations.eventData.UserReceiverBccAnnotation;
import it.webscience.uima.annotations.eventData.UserReceiverCcAnnotation;
import it.webscience.uima.annotations.eventData.UserReceiverToAnnotation;
import it.webscience.uima.util.Split;
import it.webscience.uima.util.SplitList;

import java.util.List;

import org.apache.log4j.Logger;
import org.apache.uima.UimaContext;
import org.apache.uima.analysis_component.AnalysisComponent;
import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;

/** Analizza il nodo <event-data> ed applica le annotations relative.
 */
public class EventDataAnnotator extends JCasAnnotator_ImplBase {

    /** logger. */
    private Logger logger = Logger.getLogger(this.getClass().getName());

    /**
     * @see AnalysisComponent#initialize(UimaContext)
     * @param aContext context
     * @throws ResourceInitializationException exception
     */
    public final void initialize(final UimaContext aContext)
            throws ResourceInitializationException {

        if (aContext != null) {
            super.initialize(aContext);
        }
    }

    /** Analizza il documento della CAS ed applica le annotations.
     * @param cas CAS da elaborare
     * @throws AnalysisEngineProcessException errore nell'analisi del documento
     * @see JCasAnnotator_ImplBase#process(JCas)
     */
    public final void process(final JCas cas)
            throws AnalysisEngineProcessException {
        logger.debug("Start process");

      //  logger.debug("message: \n" + cas.getDocumentText());

        try {
            addCreationDate(cas);
            addTitle(cas);
            addAbstractAnnotation(cas);
            addAuthor(cas);
            addBody(cas);
            addDetails(cas);

    //      receiver-to, receiver-cc, receiver-bcc
            addEmailReceivers(cas);

    //      annotations multipli
            addAttachments(cas);
            addProperties(cas);
        } catch (Exception e) {
            logger.fatal(e.getMessage());
            throw new AnalysisEngineProcessException(e);
        }
    }

    /** aggiunge le annotations sui destinatari delle mail.
     *  @param cas CAS da elaborare
     */
    private void addEmailReceivers(final JCas cas) {
        addUserReceiverToAnnotation(cas);
        addUserReceiverCcAnnotation(cas);
        addUserReceiverBccAnnotation(cas);
    }

    /** aggiunge le annotations AttachmentAnnotation alla CAS.
     *  Vengono estratte le sezioni <receiver-bcc> contenute in
     *  //event/event-data/attachments
     *  @param cas CAS da elaborare
     */
    private void addAttachments(final JCas cas) {
        SplitList sl = new SplitList();
        sl.setDocumentText(cas.getDocumentText());
        sl.add("<event-data>", 1);
        sl.add("<attachments>", 1);
        sl.add("</attachments>", 0);
        sl.setLeftMargin("<attachment id=\"");
        sl.setRightMargin("</attachment>");
        sl.process();

//      lista degli attachment
        List<Split> attachmentList = sl.getSplitList();

        for (Split split : attachmentList) {
            String txt = split.getValue();

            String id = txt.split("\"")[0];
            String data = txt.split("<attachment-data>")[1].
                split("</attachment-data>")[0];
            String name = txt.split("<attachment-name>")[1].
                split("</attachment-name>")[0];
            String type = txt.split("<attachment-type>")[1].
                split("</attachment-type>")[0];
            String hashcode = txt.split("<hashcode>")[1].
            split("</hashcode>")[0];

//          begin viene shiftato dopo la chiusura del tag <attachment>
            int begin = split.getBegin() + id.length() + 2;

            AttachmentAnnotation ann = new AttachmentAnnotation(cas);
            ann.setBegin(begin);
            ann.setEnd(split.getEnd());
            ann.setId(id);
            ann.setAttachmentData(data);
            ann.setAttachmentName(name);
            ann.setAttachmentType(type);
            ann.setHashcode(hashcode);

            ann.addToIndexes();
        }
    }

    /** aggiunge le annotations UserReceiverBccAnnotation alla CAS.
     *  Vengono estratte le sezioni <receiver-bcc> contenute in
     *  //event/event-data/details
     *  @param cas CAS da elaborare
     */
    private void addUserReceiverBccAnnotation(final JCas cas) {
        SplitList sl = new SplitList();
        sl.setDocumentText(cas.getDocumentText());
        sl.add("<event-data>", 1);
        sl.add("<details>", 1);
        sl.add("</details>", 0);
        sl.setLeftMargin("<receiver-bcc>");
        sl.setRightMargin("</receiver-bcc>");
        sl.process();

//      lista degli utenti "receiver-bcc"
        List<Split> receiverBccList = sl.getSplitList();
        logger.debug("receiverBccList: "
                + receiverBccList.toString());

        for (Split split : receiverBccList) {
            UserReceiverBccAnnotation ann = new UserReceiverBccAnnotation(cas);
            ann.setBegin(split.getBegin());
            ann.setEnd(split.getEnd());
            ann.setValue(split.getValue());
            ann.setEmail(split.getValue());

            setUserAnnotations(split.getValue(), ann);

            ann.addToIndexes();
        }
    }

    /** aggiunge le annotations UserReceiverCcAnnotation alla CAS.
     *  Vengono estratte le sezioni <receiver-cc> contenute in
     *  //event/event-data/details
     *  @param cas CAS da elaborare
     */
    private void addUserReceiverCcAnnotation(final JCas cas) {
        SplitList sl = new SplitList();
        sl.setDocumentText(cas.getDocumentText());
        sl.add("<event-data>", 1);
        sl.add("<details>", 1);
        sl.add("</details>", 0);
        sl.setLeftMargin("<receiver-cc>");
        sl.setRightMargin("</receiver-cc>");
        sl.process();

//      lista degli utenti "receiver-cc"
        List<Split> receiverCcList = sl.getSplitList();
        logger.debug("receiverCcList: "
                + receiverCcList.toString());

        for (Split split : receiverCcList) {
            UserReceiverCcAnnotation ann = new UserReceiverCcAnnotation(cas);
            ann.setBegin(split.getBegin());
            ann.setEnd(split.getEnd());
            ann.setValue(split.getValue());
            ann.setEmail(split.getValue());

            setUserAnnotations(split.getValue(), ann);

            ann.addToIndexes();
        }
    }

    /** aggiunge le annotations UserReceiverToAnnotation alla CAS.
    * Vengono estratte le sezioni <receiver-to> contenute in
    * //event/event-data/details
    * @param cas CAS da elaborare
    */
   private void addUserReceiverToAnnotation(final JCas cas) {
       SplitList sl = new SplitList();
       sl.setDocumentText(cas.getDocumentText());
       sl.add("<event-data>", 1);
       sl.add("<details>", 1);
       sl.add("</details>", 0);
       sl.setLeftMargin("<receiver-to>");
       sl.setRightMargin("</receiver-to>");
       sl.process();

//     lista degli utenti "receiver-to"
       List<Split> receiverToList = sl.getSplitList();
       logger.debug("receiverToList: "
               + receiverToList.toString());

       for (Split split : receiverToList) {
           UserReceiverToAnnotation ann = new UserReceiverToAnnotation(cas);
           ann.setBegin(split.getBegin());
           ann.setEnd(split.getEnd());
           ann.setValue(split.getValue());
           ann.setEmail(split.getValue());

           setUserAnnotations(split.getValue(), ann);

           ann.addToIndexes();
       }
   }

    /** aggiunge l'annotation DetailsAnnotation alla CAS.
    *
    * @param cas CAS da elaborare
    */
   private void addDetails(final JCas cas) {
       Split split = new Split();
       split.setDocumentText(cas.getDocumentText());
       split.add("<event-data>", 1);
       split.add("<details>", 1);
       split.add("</details>", 0);
       split.process();

       if (split.getBegin() != -1) {
           DetailsAnnotation ann = new DetailsAnnotation(cas);
           ann.setBegin(split.getBegin());
           ann.setEnd(split.getEnd());
           ann.setValue(split.getValue());
           ann.addToIndexes();
       }
   }

    /** aggiunge l'annotation BodyAnnotation alla CAS.
    *
    * @param cas CAS da elaborare
    */
   private void addBody(final JCas cas) {
//     provo con l'estrazione body type="text"
       Split body = new Split();
       body.setDocumentText(cas.getDocumentText());
       body.add("<event-data>", 1);
       body.add("<body type=\"text\">", 1);
       body.add("</body>", 0);
       body.process();

//     se non presente, provo con body type="html"
       if (body.getBegin() == -1) {
           body = new Split();
           body.setDocumentText(cas.getDocumentText());
           body.add("<event-data>", 1);
           body.add("<body type=\"html\">", 1);
           body.add("</body>", 0);
           body.process();
       }

       if (body.getBegin() != -1) {
           String type = cas.getDocumentText().split("<event-data>")[1]
              .split("<body")[1].split("type=\"")[1].split("\"")[0];

           BodyAnnotation ann = new BodyAnnotation(cas);
           ann.setBegin(body.getBegin());
           ann.setEnd(body.getEnd());
           ann.setBodyType(type);
           ann.setValue(body.getValue());
           ann.addToIndexes();
       }
   }

   /** aggiunge l'annotation UserAuthorAnnotation alla CAS.
    *
    * @param cas CAS da elaborare
    */
   private void addAuthor(final JCas cas) {
       Split split = new Split();
       split.setDocumentText(cas.getDocumentText());
       split.add("<event-data>", 1);
       split.add("<author>", 1);
       split.add("</author>", 0);
       split.process();

       if (split.getBegin() != -1) {
           String value = split.getValue();

           UserAuthorAnnotation ann = new UserAuthorAnnotation(cas);
           ann.setBegin(split.getBegin());
           ann.setEnd(split.getEnd());
           ann.setValue(value);

           setUserAnnotations(value, ann);

           ann.addToIndexes();
       }
   }

   /** imposta i campi username, email e name per un userAnnotation.
    * @param value contenuto del nodo
    * @param ann annotation
    */
   private void setUserAnnotations(
           final String value,
           final UserAnnotation ann) {
       if (value.indexOf("<username>") != -1) {
           String username = value.split("<username>")[1].
           split("</username>")[0];
           ann.setUsername(username);
       }

       if (value.indexOf("<name>") != -1) {
           String name = value.split("<name>")[1].split("</name>")[0];
           ann.setName(name);
       }

       if (value.indexOf("<email>") != -1) {
           String email = value.split("<email>")[1].split("</email>")[0];
           ann.setEmail(email);
       }
   }

    /** aggiunge l'annotation DateCreationDateAnnotation alla CAS.
    *
    * @param cas CAS da elaborare
    */
   private void addCreationDate(final JCas cas) {
       Split split = new Split();
       split.setDocumentText(cas.getDocumentText());
       split.add("<event-data>", 1);
       split.add("<creation-date>", 1);
       split.add("</creation-date>", 0);
       split.process();

       if (split.getBegin() != -1) {
           DateCreationDateAnnotation ann = new DateCreationDateAnnotation(cas);
           ann.setBegin(split.getBegin());
           ann.setEnd(split.getEnd());
           ann.setValue(split.getValue());
           ann.addToIndexes();
       }
   }

   /** aggiunge l'annotation TitleAnnotation alla CAS.
   *
   * @param cas CAS da elaborare
   */
   private void addTitle(final JCas cas) {
//     test estrazione del titolo con <event-data>,<title>,</title>
       Split split = new Split();
       split.setDocumentText(cas.getDocumentText());
       split.add("<event-data>", 1);
       split.add("<title>", 1);
       split.add("</title>", 0);
       split.process();

       if (split.getBegin() != -1) {
           TitleAnnotation ann = new TitleAnnotation(cas);
           ann.setBegin(split.getBegin());
           ann.setEnd(split.getEnd());
           ann.setValue(split.getValue());
           ann.addToIndexes();
       } else {
//         test estrazione del titolo con <event-data>,<title/>
           int index = cas.getDocumentText().
               split("<event-data>")[1].indexOf("<title />");

           if (index != -1) {
               TitleAnnotation ann = new TitleAnnotation(cas);
               ann.setBegin(index);
               ann.setEnd(index);
               ann.setValue("");
               ann.addToIndexes();
           }
       }
   }

   /** aggiunge l'annotation AbstractAnnotation alla CAS.
   *
   * @param cas CAS da elaborare
   */
   private void addAbstractAnnotation(final JCas cas) {
       Split split = new Split();
       split.setDocumentText(cas.getDocumentText());
       split.add("<event-data>", 1);
       split.add("<abstract>", 1);
       split.add("</abstract>", 0);
       split.process();

       if (split.getBegin() != -1) {
           AbstractAnnotation ann = new AbstractAnnotation(cas);
           ann.setBegin(split.getBegin());
           ann.setEnd(split.getEnd());
           ann.setValue(split.getValue());
           ann.addToIndexes();
       }
   }

   /** aggiunge le annotations PropertyAnnotation alla CAS.
    *  Vengono estratte le sezioni <receiver-bcc> contenute in
    *  //event/event-data/properties
    *  @param cas CAS da elaborare
    */
   private void addProperties(final JCas cas) {
       SplitList sl = new SplitList();
       sl.setDocumentText(cas.getDocumentText());
       sl.add("<event-data>", 1);
       sl.add("<properties>", 1);
       sl.add("</properties>", 0);
       sl.setLeftMargin("<property>");
       sl.setRightMargin("</property>");
       sl.process();

//     lista degli utenti "receiver-to"
       List<Split> propertiesList = sl.getSplitList();

       for (Split split : propertiesList) {
           String txt = split.getValue();

           String key = txt.split("<key>")[1].split("</key>")[0];
           String value = txt.split("<value>")[1].split("</value>")[0];

           PropertyAnnotation ann = new PropertyAnnotation(cas);
           ann.setBegin(split.getBegin());
           ann.setEnd(split.getEnd());
           ann.setKey(key);
           ann.setValue(value);

           ann.addToIndexes();
       }
   }
}
