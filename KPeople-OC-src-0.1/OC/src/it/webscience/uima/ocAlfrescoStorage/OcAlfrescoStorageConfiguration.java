package it.webscience.uima.ocAlfrescoStorage;

import java.io.Serializable;

/** Contiene i parametri di configurazione di Alfresco.
 * @author dellanna
 *
 */
public class OcAlfrescoStorageConfiguration implements Serializable {
    /** serialVersionUID. */
    private static final long serialVersionUID = 7887877024422502408L;

    /** proprietà per la gestione dei docs con hash duplicato.
     * true: è consentita la creazione di files con lo stesso hashcode
     * false: viene creato solo un file. i successivi saranno link simbolici
     */
    private boolean hasDuplicateDocs = false;

    /** username dell'utente administrator in Alfresco. */
    private String adminUsername;

    /** password dell'utente administrator in Alfresco. */
    private String adminPassword;


    /**
     * @return the hasDuplicateDocs
     */
    public final boolean isHasDuplicateDocs() {
        return hasDuplicateDocs;
    }

    /**
     * @param in the hasDuplicateDocs to set
     */
    public final void setHasDuplicateDocs(final boolean in) {
        this.hasDuplicateDocs = in;
    }

    /**
     * @return the serialversionuid
     */
    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    /**
     * @return the adminUsername
     */
    public final String getAdminUsername() {
        return adminUsername;
    }

    /**
     * @param in the adminUsername to set
     */
    public final void setAdminUsername(final String in) {
        this.adminUsername = in;
    }

    /**
     * @return the adminPassword
     */
    public final String getAdminPassword() {
        return adminPassword;
    }

    /**
     * @param in the adminPassword to set
     */
    public final void setAdminPassword(final String in) {
        this.adminPassword = in;
    }
}
