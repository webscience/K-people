package it.webscience.kpeople.service.datatypes.converter;

import it.webscience.kpeople.be.Attachment;
import it.webscience.kpeople.be.CommunicationEvent;
import it.webscience.kpeople.be.Document;
import it.webscience.kpeople.be.Event;
import it.webscience.kpeople.be.Pattern;
import it.webscience.kpeople.be.Process;
import it.webscience.kpeople.be.User;
import it.webscience.kpeople.bll.impl.DocumentManager;
import it.webscience.kpeople.dal.util.DataAccessConstants;
import it.webscience.kpeople.service.datatypes.dto.KPeopleGenericDTO;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.lang.StringEscapeUtils;

/**
 * Classe di conversione tra una business entity generica a un
 * KpeopleGenericDTO.
 */
public final class GenericConverter {

    /** Default constructor. */
    private GenericConverter() {
    }

    /**
     * Converte da List BE a array KpeopleGenericDTO Service.
     * @param in
     *            lista di oggetti BE generici
     * @return array di oggetti KPeopleGenericDTO
     */
    public static KPeopleGenericDTO[] toService(final List<Object> in) {
        KPeopleGenericDTO[] out = new KPeopleGenericDTO[in.size()];

        for (int i = 0; i < in.size(); i++) {
            out[i] = toService(in.get(i));
        }
        return out;
    }

    /**
     * Converte da generico oggetto BE a KpeopleGenericDTO Service.
     * @param in
     *            oggetto BE generico
     * @return oggetto di tipo KPeopleGenericDTO
     */
    private static KPeopleGenericDTO toService(final Object in) {
        if (in instanceof Process) {
            Process process = (Process) in;
            return toService(process);
        } else if (in instanceof Attachment) {
            Attachment attachment = (Attachment) in;
            return toService(attachment);
        } else if (in instanceof Document) {
            Document document = (Document) in;
            return toService(document);
        } else if (in instanceof Event) {
            Event event = (Event) in;
            return toService(event);
        } else if (in instanceof Pattern) {
            Pattern pattern = (Pattern) in;
            return toService(pattern);
        } else if (in instanceof User) {
            User user = (User) in;
            return toService(user);
        }

        return null;
    }

    /**
     * Converte da Attachment a KPeopleGenericDTO.
     * @param in
     *            Attachment
     * @return KPeopleGenericDTO
     */
    private static KPeopleGenericDTO toService(final Attachment in) {
        KPeopleGenericDTO dto = new KPeopleGenericDTO();

        if (in instanceof Document) {
            Document doc = (Document) in;
            dto.setType(DataAccessConstants.SOLR_TYPE_DOCUMENT);
            dto.setName(doc.getName());
            dto.setGuid(doc.getGuid());
            dto.setDescription(doc.getDescription());
            dto.getCreationDate().setTime((in.getFirstActionDate()));
            dto.setCreator(doc.getAuthor());
            dto.setHpmId(doc.getHpmAttachmentId());
            dto.setAttachmentId(in.getIdAttachment());
            return dto;
        }
        if (in instanceof Pattern) {
            return toService((Pattern) in);
        }

        return null;
    }

    /**
     * Converte da Process BE a KPeopleGenericDTO.
     * @param in
     *            Processo
     * @return KPeopleGenericDTO
     */
    private static KPeopleGenericDTO toService(final Process in) {
        KPeopleGenericDTO dto = new KPeopleGenericDTO();

        dto.setHpmId(in.getHpmProcessId());
        dto.setType(DataAccessConstants.SOLR_TYPE_PROCESS);
        dto.setName(in.getName());
        dto.setDescription(in.getDescription());
        dto.getCreationDate().setTime((in.getFirstActionDate()));
        if (in.getOwner() != null) {
            dto.setCreator(in.getOwner().getFullName());
            dto.setCreatorHpmId(in.getOwner().getHpmUserId());
        }
        return dto;
    }

    /**
     * Converte da Document BE a KPeopleGenericDTO.
     * @param in
     *            Document
     * @return KPeopleGenericDTO
     */
    private static KPeopleGenericDTO toService(final Document in) {
        KPeopleGenericDTO dto = new KPeopleGenericDTO();

        dto.setType(DataAccessConstants.SOLR_TYPE_DOCUMENT);
        dto.setName(in.getName());
        dto.setGuid(in.getGuid());
        dto.setDescription(in.getDescription());
        dto.getCreationDate().setTime((in.getFirstActionDate()));
        dto.setCreator(in.getAuthor());
        
        dto.setHpmId(in.getHpmAttachmentId());
        return dto;
    }

    /**
     * Converte da Event BE a KPeopleGenericDTO.
     * @param in
     *            Event
     * @return KPeopleGenericDTO
     */
    private static KPeopleGenericDTO toService(final Event in) {

        String eventType = null;

        for (int j = 0; j < in.getEventMetadata().size(); j++) {
            if (in.getEventMetadata().get(j).getKeyname()
                    .equalsIgnoreCase("action-type")) {
                eventType = in.getEventMetadata().get(j).getValue();
                break;
            }
        }

        KPeopleGenericDTO dto = new KPeopleGenericDTO();

        dto.setHpmId(in.getHpmEventId());
        dto.setHpmProcessRefId(in.getHpmProcessId());

        dto.getCreationDate().setTime((in.getFirstActionDate()));
        if (in.getUser() != null) {
            dto.setCreator(in.getUser().getFullName());
            dto.setCreatorHpmId(in.getUser().getHpmUserId());
        }

        if (in.getAttachments() != null && in.getAttachments().size() > 0) {
            if (in.getAttachments().get(0) instanceof CommunicationEvent) {
                if (eventType
                        .equalsIgnoreCase(DataAccessConstants.DOCUMENT_DOWNLOAD_ACTION_TYPE)) {
                    dto.setType(DataAccessConstants.SOLR_TYPE_DOWN_EVENT);
                } else {
                    dto.setType(DataAccessConstants.SOLR_TYPE_COMM_EVENT);
                }
                dto =
                        toService(dto,
                                (CommunicationEvent) (in.getAttachments()
                                        .get(0)));
            } else if (in.getAttachments().get(0) instanceof Document) {
                dto = toService(dto, (Document) (in.getAttachments().get(0)));
            }
        }

        return dto;
    }

    /**
     * Converte da CommunicationEvent BE a KPeopleGenericDTO.
     * @param dto
     *            oggetto da riempire
     * @param email
     *            oggetto di tipo CommunicatinEvent
     * @return dto con i campi valorizzati
     */
    private static KPeopleGenericDTO toService(final KPeopleGenericDTO dto,
            final CommunicationEvent email) {

        dto.setName(email.getObject());
        dto.setDescription(StringEscapeUtils.unescapeXml(email.getBody()));

        // mittente
        String from = "";
        if (email.getUserFrom() != null) {
            if (email.getUserFrom().getScreenName() != null) {
                from = email.getUserFrom().getScreenName();
            } else {
                from = email.getUserFrom().getEmail();
            }
        }
        dto.setCreator(from);

        // recupero i destinatari della mail (CC)
        String receiversCc = "";
        List<User> ccUsers = email.getCcUser();
        if (ccUsers != null) {
            for (User ccUser : ccUsers) {
                if (!receiversCc.isEmpty()) {
                    receiversCc += ", ";
                }

                String screenName = ccUser.getScreenName();
                if (screenName != null) {
                    receiversCc += screenName;
                } else {
                    receiversCc += ccUser.getEmail();
                }
            }
        }

        dto.setOtherProvider(receiversCc);

        // recupero i destinatari della mail (TO)
        String receivers = "";
        List<User> toUsers = email.getToUser();
        if (toUsers != null) {
            for (User toUser : toUsers) {
                if (!receivers.isEmpty()) {
                    receivers += ", ";
                }

                String screenName = toUser.getScreenName();
                if (screenName != null) {
                    receivers += screenName;
                } else {
                    receivers += toUser.getEmail();
                }
            }
        }

        dto.setProvider(receivers);

        return dto;
    }

    /**
     * Converte da Document BE a KPeopleGenericDTO.
     * @param dto
     *            oggetto da riempire
     * @param document
     *            oggetto di tipo Document
     * @return dto con i campi valorizzati
     */
    private static KPeopleGenericDTO toService(final KPeopleGenericDTO dto,
            final Document document) {
        dto.setType(DataAccessConstants.SOLR_TYPE_DOC_EVENT);
        dto.setGuid(document.getGuid());
        dto.setName(document.getName());
        dto.setAttachmentId(document.getIdAttachment());
        return dto;
    }

    /**
     * Converte da Pattern BE a KPeopleGenericDTO.
     * @param in
     *            Pattern
     * @return KPeopleGenericDTO
     */
    private static KPeopleGenericDTO toService(final Pattern in) {
        KPeopleGenericDTO dto = new KPeopleGenericDTO();

        dto.setHpmId(in.getHpmPatternId());
        dto.setHpmProcessRefId(in.getHpmProcessRefId());
        dto.setType(DataAccessConstants.SOLR_TYPE_PATTERN);
        dto.setName(in.getName());
        dto.setDescription(in.getDescription());
        dto.getCreationDate().setTime((in.getFirstActionDate()));
        if (in.getPatternRequestor() != null) {
            dto.setCreator(in.getPatternRequestor().getFullName());
            dto.setCreatorHpmId(in.getPatternRequestor().getHpmUserId());
        }
        if (in.getPatternProvider() != null) {
            dto.setProvider(in.getPatternProvider().getFullName());
            dto.setProviderHpmId(in.getPatternProvider().getHpmUserId());
        }

        return dto;
    }

    /**
     * Converte da User BE a KPeopleGenericDTO.
     * @param in
     *            User
     * @return KPeopleGenericDTO
     */
    private static KPeopleGenericDTO toService(final User in) {
        KPeopleGenericDTO dto = new KPeopleGenericDTO();

        dto.setHpmId(in.getHpmUserId());
        dto.setName(in.getFullName());
        dto.setEmail(in.getEmail());
        dto.setType(DataAccessConstants.SOLR_TYPE_USER);

        return dto;
    }
}
