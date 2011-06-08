package it.webscience.kpeople.service.datatypes.converter;

import java.util.List;

public class ProcessEventConverter {

    /**
     * Costruttore.
     */
    private ProcessEventConverter() {

    }

    /**
     * Converte la lista di ProcessEvent BE in un array di ProcessEvent Service.
     * @param in Lista di ProcessEvent.
     * @return Array di ProcessEvent.
     */
    public static it.webscience.kpeople.service.datatypes.
    ProcessEvent[] toService(
            final List<it.webscience.kpeople.be.ProcessEvent> in) {

        if (in == null) {
            return null;
        }

        it.webscience.kpeople.service.datatypes.ProcessEvent[] out =
            new it.webscience.kpeople.service.datatypes.ProcessEvent[in.size()];
        for (int i = 0; i < in.size(); i++) {
            out[i] = toService(in.get(i));
            }
        return out;
    }

    /**
     * Converte ProcessEvent BE in ProcessEvent service.
     * @param in ProcessEvent BE.
     * @return ProcessEvent service.
     */
    private static it.webscience.kpeople.service.datatypes.
    ProcessEvent toService(final it.webscience.kpeople.be.ProcessEvent in) {

        if (in == null) {
            return null;
        }

        it.webscience.kpeople.service.datatypes.ProcessEvent out =
            new it.webscience.kpeople.service.datatypes.ProcessEvent();

        out.setHpmProcessId(in.getHpmProcessId());
        out.setHpmEventId(in.getHpmEventId());
        return out;
    }


}
