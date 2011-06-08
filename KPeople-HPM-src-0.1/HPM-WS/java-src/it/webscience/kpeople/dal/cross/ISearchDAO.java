package it.webscience.kpeople.dal.cross;

import it.webscience.kpeople.dal.exception.KPeopleDAOException;

import java.util.List;

/**
 * Interfaccia della classe SearchDAO per la ricerca full text globale.
 */
public interface ISearchDAO {

    /**
     * Esegue la ricerca full text su tutte le business entity.
     * @param freeText testo da cercare
     * @return lista delle business entity trovate
     * @throws KPeopleDAOException eccezione durante la ricerca
     */
    List<Object> find(final String freeText) throws KPeopleDAOException;
}
