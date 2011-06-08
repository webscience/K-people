package it.webscience.kpeople.ega.adapter.Postfix.kpeopleeventmodel;

/**
 * @author filieri
 */
public class Author {
    /**
     * identificativo univoco dell'autore.
     */
    protected String userId;

    /**
     * username dell'autore.
     */
    protected String username;

    /**
     * nome dell'autore.
     */
    protected String name;

    /**
     * indirizzo email dell'autore.
     */
    protected String email;
    
    /**
     * @return the userId
     */
    public String getUserId() {
        return userId;
    }
    /**
     * @param userId the userId to set
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }
    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }
    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }
    /**
     * @return the name
     */
    public String getName() {
        return name;
    }
    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }
    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

}
