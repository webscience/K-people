package it.webscience.kpeople.service.datatypes.dto;

import java.util.Calendar;

/**
 * Classe dto per un oggetto generico.
 */
public class KPeopleGenericDTO {

    /** hpmId dell'oggetto. */
    private String hpmId;

    /** hpmId dell'oggetto. */
    private int attachmentId;

    /** hpmId del processo a cui appartiene l'oggetto. */
    private String hpmProcessRefId;

    /** tipologia. */
    private String type;

    /** nome o titolo. */
    private String name;

    /** descrizione. */
    private String description;

    /** data di creazione. */
    private Calendar creationDate;

    /** nome e cognome dell'utente creatore o requestor. */
    private String creator;

    /** hpmId dell'utente creatore/requestor. */
    private String creatorHpmId;

    /** nome e cognome dell'utente provider. */
    private String provider;

    /** hpmId dell'utente provider. */
    private String providerHpmId;

    /** nome e cognome dell'utente provider. */
    private String otherProvider;

    /** email dell'utente. */
    private String email;

    /** guid del documento. */
    private String guid;

    /** setter dell'hpmId.
     * @param in hpmId
     */
    public final void setHpmId(final String in) {
        this.hpmId = in;
    }

    /** getter dell'hpmId.
     * @return hpmId
     */
    public final String getHpmId() {
        return hpmId;
    }

    /** getter dell'hpmProcessRefId.
     * @return hpmProcessRefId
     */
    public final String getHpmProcessRefId() {
        return hpmProcessRefId;
    }

    /** setter dell'hpmProcessRefId.
     * @param in hpmProcessRefId
     */
    public final void setHpmProcessRefId(final String in) {
        this.hpmProcessRefId = in;
    }

    /** setter del type.
     * @param in type
     */
    public final void setType(final String in) {
        this.type = in;
    }

    /** getter del type.
     * @return type
     */
    public final String getType() {
        return type;
    }

    /** setter del name.
     * @param in name
     */
    public final void setName(final String in) {
        this.name = in;
    }

    /** getter del name.
     * @return name
     */
    public final String getName() {
        return name;
    }

    /** setter della description.
     * @param in description
     */
    public final void setDescription(final String in) {
        this.description = in;
    }

    /** getter della description.
     * @return description
     */
    public final String getDescription() {
        return description;
    }

    /** setter del creationDate.
     * @param in creationDate
     */
    public final void setCreationDate(final Calendar in) {
        this.creationDate = in;
    }

    /** getter del creationDate.
     * @return creationDate
     */
    public final Calendar getCreationDate() {
        if (creationDate == null) {
            creationDate = Calendar.getInstance();
        }
        return creationDate;
    }

    /** setter del creator.
     * @param in creator
     */
    public final void setCreator(final String in) {
        this.creator = in;
    }

    /** getter del creator.
     * @return creator
     */
    public final String getCreator() {
        return creator;
    }

    /** setter del creatorHpmId.
     * @param in creatorHpmId
     */
    public final void setCreatorHpmId(final String in) {
        this.creatorHpmId = in;
    }

    /** getter del creatorHpmId.
     * @return creatorHpmId
     */
    public final String getCreatorHpmId() {
        return creatorHpmId;
    }

    /** getter del provider.
     * @return provider
     */
    public final String getProvider() {
        return provider;
    }

    /** setter del provider.
     * @param in provider
     */
    public final void setProvider(final String in) {
        this.provider = in;
    }

    /** getter del providerHpmId.
     * @return providerHpmId
     */
    public final String getProviderHpmId() {
        return providerHpmId;
    }

    /** setter del providerHpmId.
     * @param in providerHpmId
     */
    public final void setProviderHpmId(final String in) {
        this.providerHpmId = in;
    }

    /** getter del otherProvider.
     * @return otherProvider
     */
    public final String getOtherProvider() {
        return otherProvider;
    }

    /** setter del otherProvider.
     * @param in otherProvider
     */
    public final void setOtherProvider(final String in) {
        this.otherProvider = in;
    }

    /** getter dell'email.
     * @return email
     */
    public final String getEmail() {
        return email;
    }

    /** setter dell'email.
     * @param in email
     */
    public final void setEmail(final String in) {
        this.email = in;
    }

    /** getter del guid.
     * @return guid
     */
    public final String getGuid() {
        return guid;
    }

    /** setter del guid.
     * @param in guid
     */
    public final  void setGuid(final String in) {
        this.guid = in;
    }

    /** getter dell'attachmentId.
     * @return attachmentId
     */
    public final int getAttachmentId() {
        return attachmentId;
    }

    /** setter dell'attachmentId.
     * @param in guid
     */
    public final void setAttachmentId(final int in) {
        this.attachmentId = in;
    }
}
