package it.webscience.kpeople.service.datatypes.converter;

import it.webscience.kpeople.service.datatypes.DataTraceClass;

/**
 * Classe di conversione tra la classe DataTraceClass BE e
 * DataTraceClass Service.
 */
public final class DataTraceClassConverter {

    /**
     * Costruttore privato.
     */
    private DataTraceClassConverter() {
    }

    /**
     * Converte da BE a Service.
     * @param in oggetto DataTraceClass BE
     * @param out oggetto DataTraceClass Service
     */
    public static void toService(
            final it.webscience.kpeople.be.DataTraceClass in,
            final DataTraceClass out) {

        out.setDeleted(in.isDeleted());
        out.setDeletedDate(in.getDeletedDate());
        out.setFirstActionDate(in.getFirstActionDate());
        out.setLastActionDate(in.getLastActionDate());

        if (in.getDeletedBy() != null) {
            out.setDeletedBy(
                    UserConverter.toService(in.getDeletedBy()));
        }

        if (in.getFirstActionPerformer() != null) {
            out.setFirstActionPerformer(
                    UserConverter.toService(in.getFirstActionPerformer()));
        }

        if (in.getLastActionPerformer() != null) {
            out.setLastActionPerformer(
                    UserConverter.toService(in.getLastActionPerformer()));
        }
    }

    /**
     * Converte da Service a BE.
     * @param in oggetto DataTraceClass Service
     * @param out oggetto DataTraceClass BE
     */
    public static void toBE(
            final DataTraceClass in,
            final it.webscience.kpeople.be.DataTraceClass out) {

        out.setDeleted(in.isDeleted());
        out.setDeletedDate(in.getDeletedDate());
        out.setFirstActionDate(in.getFirstActionDate());
        out.setLastActionDate(in.getLastActionDate());

        if (in.getDeletedBy() != null) {
            out.setDeletedBy(UserConverter.toBE(in.getDeletedBy()));
        }

        if (in.getFirstActionPerformer() != null) {
            out.setFirstActionPerformer(
                    UserConverter.toBE(in.getFirstActionPerformer()));
        }

        if (in.getLastActionPerformer() != null) {
            out.setLastActionPerformer(
                    UserConverter.toBE(in.getLastActionPerformer()));
        }
    }
}
