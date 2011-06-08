package it.webscience.kpeople.bll.impl;

import it.webscience.kpeople.be.Document;
import it.webscience.kpeople.be.User;
import  it.webscience.kpeople.be.Process;
import it.webscience.kpeople.bll.exception.KPeopleBLLException;

/**
 * Interfaccia per la DocumentService.
 *
 */
public interface IDocumentManager {

     /**
      * salva nella tabella dello storico download l'evento di download corrente.
      * @param document documento scaricato
      * @param process processo a cui è associato il documento
      * @param user utente che effettua il download
      * @throws KPeopleBLLException eccezione durante l'elaborazione
      */
     void saveDocumentDownloadHistory(
             final Document document,
             final Process process,
             final User user) throws KPeopleBLLException;

}
