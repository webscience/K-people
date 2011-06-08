package it.webscience.kpeople.be;

/**
 * Tabella USER.
 */
public class User extends DataTraceClass {
    /** Chiave primaria.
     * Identificativo univoco per un record della tabella USER */
    private int idUser;

    /**  Valore testuale relativo allo username che un utente
     * assume all'interno del sistema KPEople. */
    private String username;

    /** valore testuale relativo all'account associato
     * all'utente sul sistema LDAP aziendale.
     */
    private String account;

    /**
     * Valore testuale.
     * Identificativo univoco per identificare una keyword in tutte le
     * componenti del sistema.
     */
    private String hpmUserId;

    /**
     * costruttore.
     * @param in campo idUser
     */
    public User(final int in) {
        super();
        this.idUser = in;
    }

    /** costruttore. */
    public User() {
        super();
    }

    /**
     * @return the idUser
     */
    public final int getIdUser() {
        return idUser;
    }

    /**
     * @param in the idUser to set
     */
    public final void setIdUser(final int in) {
        this.idUser = in;
    }

    /**
     * @return the username
     */
    public final String getUsername() {
        return username;
    }

    /**
     * @param in the username to set
     */
    public final void setUsername(final String in) {
        this.username = in;
    }

    /**
     * @return the account
     */
    public final String getAccount() {
        return account;
    }

    /**
     * @param in the account to set
     */
    public final void setAccount(final String in) {
        this.account = in;
    }

    /**
     * @return the hpmUserId
     */
    public final String getHpmUserId() {
        return hpmUserId;
    }

    /**
     * @param in the hpmUserId to set
     */
    public final void setHpmUserId(final String in) {
        this.hpmUserId = in;
    }
}
