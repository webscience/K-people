package it.webscience.kpeople.service.document;

import static org.junit.Assert.fail;
import it.webscience.kpeople.service.datatypes.Document;
import it.webscience.kpeople.service.datatypes.DocumentFilter;
import it.webscience.kpeople.service.datatypes.User;

import org.junit.Test;

/**
 * Classe di test del DocumentService.
 *
 */
public class DocumentServiceTest {

    /**
     * Test del metodo getOwners.
     */
    @Test
    public final void testFindDocuments() {

        try {
            DocumentService service = new DocumentService();

            DocumentFilter filter = new DocumentFilter();
            filter.setHpmProcessId("prg1154");

            User user = new User();

            Document[] documents = service.findDocuments(filter, user);
            System.out.println(documents);

        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }
}
