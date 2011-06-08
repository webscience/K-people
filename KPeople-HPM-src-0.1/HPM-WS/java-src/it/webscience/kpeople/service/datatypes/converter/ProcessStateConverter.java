package it.webscience.kpeople.service.datatypes.converter;

import it.webscience.kpeople.service.datatypes.ProcessState;

/**
 * Classe di conversione tra la classe ProcessState BE e ProcessState Service.
 */
public final class ProcessStateConverter {

    /**
     * Costruttore privato.
     */
    private ProcessStateConverter() {
    }

    /**
     * Converte da Service a BE.
     * @param in oggetto ProcessState Service
     * @return oggetto ProcessState BE
     */
    public static it.webscience.kpeople.be.ProcessState toBE(
            final ProcessState in) {

        if (in == null) {
            return null;
        }

        it.webscience.kpeople.be.ProcessState out =
            new it.webscience.kpeople.be.ProcessState();

        out.setDescription(in.getDescription());
        out.setIdProcessState(in.getIdProcessState());
        out.setProcessState(in.getProcessState());

        return out;
    }


    /**
     * Converte da ProcessState BE in ProcessState Service.
     * @param in oggetto ProcessState BE
     * @return oggetto ProcessState Service
     */
    public static ProcessState toService(
            final it.webscience.kpeople.be.ProcessState in) {

        if (in == null) {
            return null;
        }

        ProcessState out = new ProcessState();
        out.setDescription(in.getDescription());
        out.setIdProcessState(in.getIdProcessState());
        out.setProcessState(in.getProcessState());

        return out;
    }
}
