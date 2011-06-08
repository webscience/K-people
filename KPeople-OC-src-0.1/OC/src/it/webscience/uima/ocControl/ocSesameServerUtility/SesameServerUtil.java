package it.webscience.uima.ocControl.ocSesameServerUtility;

import org.openrdf.repository.Repository;

public class SesameServerUtil {

    /**
     * @return Repository - istanza del repository all'interno del repository
     *         sesame.
     */
    public static Repository getRdfRepository() {
        return OCRdfRepository.getInstance().getSesameRepository();
    }

}
