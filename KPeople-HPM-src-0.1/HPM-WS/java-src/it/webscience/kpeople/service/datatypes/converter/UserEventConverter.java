package it.webscience.kpeople.service.datatypes.converter;

import java.util.List;

public class UserEventConverter {
    
    /**
     * Costruttore.
     */
    private UserEventConverter() {

    }

    /**
     * Converte la lista di UserEvent BE in un array di UserEvent Service.
     * @param in Lista di UserEvent.
     * @return Array di UserEvent.
     */
    public static it.webscience.kpeople.service.datatypes.
    UserEvent[] toService(
            final List<it.webscience.kpeople.be.UserEvent> in) {

        if (in == null) {
            return null;
        }

        it.webscience.kpeople.service.datatypes.UserEvent[] out =
            new it.webscience.kpeople.service.datatypes.UserEvent[in.size()];
        for (int i = 0; i < in.size(); i++) {
            out[i] = toService(in.get(i));
            }
        return out;
    }

    /**
     * Converte UserEvent BE in UserEvent service.
     * @param in UserEvent BE.
     * @return UserEvent service.
     */
    private static it.webscience.kpeople.service.datatypes.
    UserEvent toService(final it.webscience.kpeople.be.UserEvent in) {

        if (in == null) {
            return null;
        }

        it.webscience.kpeople.service.datatypes.UserEvent out =
            new it.webscience.kpeople.service.datatypes.UserEvent();

        out.setHpmEventId(in.getHpmEventId());
        out.setHpmUserId(in.getHpmUserId());
        return out;
    }

}
