package it.webscience.kpeople.dal.activity;

import it.webscience.kpeople.be.ActivityMetadata;

import java.sql.SQLException;
import java.util.List;

/**
 * Interfaccia per la classe ActivityMetadataDAO.
 * @author javano
 *
 */
public interface IActivityMetadataDAO {

	/**
	 * Ottiene la lista dei metadati associati ad una attività.
	 * @param pActivityId identificativo dell'attività di cui estrarre 
	 * i metadati
	 * @return lista di ActivityMetadata
	 * @throws SQLException eccezione
	 */
	List<ActivityMetadata> getActivityMetadatasByActivityId(int pActivityId) 
		throws SQLException;
	
	/**
	 * Estrae i dati della business entiti ActivityMetadata dato un id.
	 * @param pIdActivityMetadata identificativo BE da estrarre.
	 * @return Info relative al ActivityMetadata
	 * @throws SQLException eccezione
	 */
	ActivityMetadata getActivityMetadataById(int pIdActivityMetadata)
		throws SQLException;
}
