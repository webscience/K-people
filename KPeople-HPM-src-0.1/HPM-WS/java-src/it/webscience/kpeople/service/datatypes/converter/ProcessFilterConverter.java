package it.webscience.kpeople.service.datatypes.converter;

import it.webscience.kpeople.service.datatypes.ProcessFilter;

/**
 * Classe di conversione tra la classe ProcessFilter BE e ProcessFilter Service.
 */
public final class ProcessFilterConverter {

    /**
     * Costruttore privato.
     */
    private ProcessFilterConverter() {
    }

    /**
     * Converte da Service a BE.
     * @param in oggetto ProcessFilter Service
     * @return oggetto ProcessFilter BE
     */
    public static it.webscience.kpeople.be.ProcessFilter toBE(
            final ProcessFilter in) {

        if (in == null) {
            return null;
        }

        it.webscience.kpeople.be.ProcessFilter out =
            new it.webscience.kpeople.be.ProcessFilter();

        out.setDeleted(in.getDeleted());
        out.setFreeText(in.getFreeText());
        out.setSort(SortCriteriaConverter.toBE(in.getSort()));
        out.setState(in.getState());
        out.setType(in.getType());
        out.setUserId(in.getUserId());
        out.setVisibility(in.getVisibility());
        out.setCreationDateFrom(in.getCreationDateFrom());
        out.setCreationDateTo(in.getCreationDateTo());
        out.setDueDateFrom(in.getDueDateFrom());
        out.setDueDateTo(in.getDueDateTo());
        out.setShowReserved(in.isShowReserved());
        out.setShowInTime(in.isShowInTime());
        out.setShowLate(in.isShowLate());

        return out;
    }
}
