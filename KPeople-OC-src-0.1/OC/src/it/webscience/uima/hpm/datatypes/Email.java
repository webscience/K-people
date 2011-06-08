package it.webscience.uima.hpm.datatypes;

import java.util.ArrayList;
import java.util.List;

/**
 * Tabella EMAIL.
 * @author dellanna
 *
 */
public class Email extends Attachment {

    /** Oggetto della email. */
    private String emailObject;

    /** Testo e corpo della email. */
    private String emailBody;

    /** indirizzo email presente nel campo CC della EMAIL. */
    private List<String> emailCc;

    /** Indirizzo email presente nel campo CCN della EMAIL. */
    private List<String> emailCcn;

    /** indirizzo email presente nel campo FROM della EMAIL. */
    private List<String> emailFrom;

    /** indirizzo email presente nel campo TO della EMAIL. */
    private List<String> emailTo;

    /** documenti associati ad una email. */
    private List<Document> documents;

    /** Costruttore. */
    public Email() {
        super();
        documents = new ArrayList<Document>();
        emailCc = new ArrayList<String>();
        emailCcn = new ArrayList<String>();
        emailFrom = new ArrayList<String>();
        emailTo = new ArrayList<String>();
    }


    /**
     * @return the emailObject
     */
    public final String getEmailObject() {
        return emailObject;
    }

    /**
     * @param in the emailObject to set
     */
    public final void setEmailObject(final String in) {
        this.emailObject = in;
    }

    /**
     * @return the emailBody
     */
    public final String getEmailBody() {
        return emailBody;
    }

    /**
     * @param in the emailBody to set
     */
    public final void setEmailBody(final String in) {
        this.emailBody = in;
    }

    /**
     * @return the documents
     */
    public final List<Document> getDocuments() {
        return documents;
    }

    /**
     * @param in the documents to set
     */
    public final void setDocuments(final List<Document> in) {
        this.documents = in;
    }

    /**
     * @return the emailCc
     */
    public final List<String> getEmailCc() {
        return emailCc;
    }

    /**
     * @param in the emailCc to set
     */
    public final void setEmailCc(final List<String> in) {
        this.emailCc = in;
    }

    /**
     * @return the emailCcn
     */
    public final List<String> getEmailCcn() {
        return emailCcn;
    }

    /**
     * @param in the emailCcn to set
     */
    public final void setEmailCcn(final List<String> in) {
        this.emailCcn = in;
    }

    /**
     * @return the emailFrom
     */
    public final List<String> getEmailFrom() {
        return emailFrom;
    }

    /**
     * @param in the emailFrom to set
     */
    public final void setEmailFrom(final List<String> in) {
        this.emailFrom = in;
    }

    /**
     * @return the emailTo
     */
    public final List<String> getEmailTo() {
        return emailTo;
    }

    /**
     * @param in the emailTo to set
     */
    public final void setEmailTo(final List<String> in) {
        this.emailTo = in;
    }
}
