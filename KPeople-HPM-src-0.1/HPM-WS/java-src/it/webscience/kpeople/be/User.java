package it.webscience.kpeople.be;

import it.webscience.kpeople.bll.exception.KPeopleBLLException;

/**
 * Tabella USER.
 */
public class User extends DataTraceClass {
    /**
     * Chiave primaria. Identificativo univoco per un record della tabella USER
     */
    private int idUser;

    /**
     * Valore testuale relativo allo username che un utente assume all'interno
     * del sistema KPEople.
     */
    private String username;

    /**
     * valore testuale relativo all'account associato all'utente sul sistema.
     * LDAP aziendale.
     */
    private String account;

    /**
     * Valore testuale. Identificativo univoco per identificare una keyword in
     * tutte le componenti del sistema.
     */
    private String hpmUserId;

    /** Nome dell'utente. */
    private String firstName;

    /** Cognome dell'utente. */
    private String lastName;

    /** Screen name dell'utente. */
    private String screenName;

    /** email dell'utente. */
    private String email;

    /**
     * confronto tra due oggetti User.
     * @param obj
     *            utente da confrontare
     * @return true se i due User coincidono
     * @throws KPeopleBLLException
     *             non è possibile confrontare i due User
     */
    public final boolean equals(final User obj) throws KPeopleBLLException {
        User other = (User) obj;

        boolean sameUser = false;

        if (idUser > 0 && other.getIdUser() > 0) {
            if (idUser == other.getIdUser()) {
                sameUser = true;
            }

        } else if (username != null && other.getUsername() != null) {
            sameUser = username.equals(other.getUsername());
        } else {
            throw new KPeopleBLLException("Impossibile confrontare gli utenti");
        }

        return sameUser;
    }

    /**
     * costruttore.
     * @param in
     *            campo idUser
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
     * @param in
     *            the idUser to set
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
     * @param in
     *            the username to set
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
     * @param in
     *            the account to set
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
     * @param in
     *            the hpmUserId to set
     */
    public final void setHpmUserId(final String in) {
        this.hpmUserId = in;
    }

    /**
     * @return the firstName
     */
    public final String getFirstName() {
        return firstName;
    }

    /**
     * @param in
     *            the firstName to set
     */
    public final void setFirstName(final String in) {
        this.firstName = in;
    }

    /**
     * @return the lastName
     */
    public final String getLastName() {
        return lastName;
    }

    /**
     * @param in
     *            the lastName to set
     */
    public final void setLastName(final String in) {
        this.lastName = in;
    }

    /**
     * Costruisce il nome completo dell'utente.
     * @return nome completo dell'utente
     */
    public final String getFullName() {
        return this.firstName + " " + this.lastName;
    }

    /**
     * @return the screenName
     */
    public final String getScreenName() {
        return screenName;
    }

    /**
     * @param in
     *            the screenName to set
     */
    public final void setScreenName(final String in) {
        this.screenName = in;
    }

    /**
     * @return the email
     */
    public final String getEmail() {
        return email;
    }

    /**
     * @param in the email to set
     */
    public final void setEmail(final String in) {
        this.email = in;
    }
}
