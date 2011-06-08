package it.webscience.kpeople.dal.pattern;

import it.webscience.kpeople.be.PatternMetadata;

import java.sql.SQLException;
import java.util.List;

/**
 * Interfaccia per la classe PatternMetadataDAO.
 * @author gnoni
 *
 */
public interface IPatternMetadataDAO {

	/**
	 * Ottiene la lista dei metadati associati ad un pattern.
	 * @param pPatternId identificativo del pattern di cui estrarre i metadati
	 * @return lista di PatternMetadata
	 * @throws SQLException eccezione
	 */
	List<PatternMetadata> getPatternMetadatasByPatternId(int pPatternId) 
		throws SQLException;
	
	/**
	 * Estrae i dati della business entiti PatternMetadata dato un id.
	 * @param pIdPatternMetadata identificativo BE da estrarre.
	 * @return Info relative al PatternMetadata
	 * @throws SQLException eccezione
	 */
	PatternMetadata getPatternMetadataById(int pIdPatternMetadata)
		throws SQLException;
}
