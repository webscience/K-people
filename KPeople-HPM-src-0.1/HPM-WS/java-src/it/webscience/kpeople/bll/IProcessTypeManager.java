package it.webscience.kpeople.bll;

import it.webscience.kpeople.be.ProcessType;
import it.webscience.kpeople.be.User;
import it.webscience.kpeople.bll.exception.KPeopleBLLException;

import java.util.List;

/**
 * Interfaccia del manager delle ricerche.
 */
public interface IProcessTypeManager {

    /**
     * Restituisce gli l'elenco completo dei ProcessType.
     * @param user user che esegue la chiamata
     * @return elenco di ProcessType
     * @throws KPeopleBLLException eccezione durante l'elaborazione
     */
    List<ProcessType> getProcessTypes(final User user)
            throws KPeopleBLLException;
}
