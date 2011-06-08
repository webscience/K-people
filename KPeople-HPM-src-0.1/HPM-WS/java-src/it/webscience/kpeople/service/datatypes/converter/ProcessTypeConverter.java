package it.webscience.kpeople.service.datatypes.converter;

import it.webscience.kpeople.service.datatypes.ProcessType;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe di conversione tra la classe ProcessType BE e ProcessType Service.
 */
public final class ProcessTypeConverter {

    /**
     * Costruttore privato.
     */
    private ProcessTypeConverter() {
    }

    /**
     * Converte da Service a BE.
     * @param in
     *            oggetto ProcessType Service
     * @return oggetto ProcessType BE
     */
    public static it.webscience.kpeople.be.ProcessType
            toBE(final ProcessType in) {

        if (in == null) {
            return null;
        }

        it.webscience.kpeople.be.ProcessType out =
                new it.webscience.kpeople.be.ProcessType();

        out.setIdProcessType(in.getIdProcessType());
        out.setName(in.getName());
        out.setDeleted(in.isDeleted());

        if (in.getDeletedBy() != null) {
            out.setDeletedBy(UserConverter.toBE(in.getDeletedBy()));
        }

        out.setDeletedDate(in.getDeletedDate());

        if (in.getFirstActionPerformer() != null) {
            out.setFirstActionPerformer(UserConverter.toBE(in
                    .getFirstActionPerformer()));
        }

        out.setFirstActionDate(in.getFirstActionDate());

        if (in.getLastActionPerformer() != null) {
            out.setLastActionPerformer(UserConverter.toBE(in
                    .getLastActionPerformer()));
        }

        out.setLastActionDate(in.getLastActionDate());
        out.setProcessTypeCode(in.getProcessTypeCode());

        return out;
    }

    /**
     * Converte da array Service a List BE.
     * @param in
     *            array di oggetti Document Service
     * @return Lista BE
     */
    public static List<it.webscience.kpeople.be.ProcessType> toBE(
            final ProcessType[] in) {

        if (in == null) {
            return null;
        }

        List<it.webscience.kpeople.be.ProcessType> out =
                new ArrayList<it.webscience.kpeople.be.ProcessType>();

        for (int i = 0; i < in.length; i++) {
            out.add(toBE(in[i]));
        }

        return out;
    }

    /**
     * Converte da ProcessType BE in ProcessType Service.
     * @param in
     *            oggetto ProcessType BE
     * @return oggetto ProcessType Service
     */
    public static ProcessType toService(
            final it.webscience.kpeople.be.ProcessType in) {

        if (in == null) {
            return null;
        }

        ProcessType out = new ProcessType();

        out.setIdProcessType(in.getIdProcessType());
        out.setName(in.getName());

        DataTraceClassConverter.toService(in, out);

        return out;
    }

    /**
     * Converte da List ProcessType BE a array ProcessType Service.
     * @param in
     *            List ProcessType BE
     * @return array ProcessType Service
     */
    public static ProcessType[] toService(
            final List<it.webscience.kpeople.be.ProcessType> in) {

        ProcessType[] out = new ProcessType[in.size()];

        for (int i = 0; i < in.size(); i++) {
            out[i] = toService(in.get(i));
        }
        return out;
    }
}
