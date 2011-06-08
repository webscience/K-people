package it.webscience.uima.ocSesameAdapter.sesameAdapterAgent;

import it.webscience.uima.ocControl.SesamePropertyKeys;
import it.webscience.uima.ocControl.ocSesameServerUtility.SesameServerUtil;
import it.webscience.uima.ocSesameAdapter.sesameAdapterConfigurationManager.SesameAdapterConfigurationManager;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.uima.cas.CAS;
import org.dom4j.Document;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.openrdf.repository.Repository;
import org.openrdf.repository.RepositoryConnection;
import org.openrdf.repository.RepositoryException;
import org.openrdf.rio.RDFFormat;
import org.openrdf.rio.RDFParseException;

/**
 * @author filieri
 */
public class SesameAdapterAgent {
    /** logger. */
    private Logger logger;
    
    /**
     * istanza del server sesame.
     */
    private static Repository sesameRepository = null;

    /**
     * sesame application properties.
     */
    private Properties sesameProps = new Properties();

    /**
     * istanza della classe di configurazione del server sesame.
     */
    private static SesameAdapterConfigurationManager configInstance = null;

    /**
     * Costruttore di default che istanzia il server sesame.
     */
    public SesameAdapterAgent() {
        logger = Logger.getLogger(this.getClass().getName());

        configInstance = SesameAdapterConfigurationManager.getInstance();
        sesameRepository = SesameServerUtil.getRdfRepository();
        sesameProps = configInstance.getProperties();
    }

    /**
     * Metodo per la creazione dei metadati da inserire all'interno
     * dell'ontologia.
     * @param cas
     *            - cas che deve essere elaborata.
     */
    public final void indexEvent(final CAS cas) {
        if (logger.isDebugEnabled()) {
            logger.debug("index event");
        }

        SesameMetadataInterpreterFactory metadataInterpreterFactory =
                new SesameMetadataInterpreterFactory();
        Document rdfDocument = metadataInterpreterFactory.interpreter(cas);
        commitToSesame(rdfDocument);

        if (logger.isDebugEnabled()) {
            logger.debug("index event - end");

        }
    }

    /**
     * Metodo per l'invio del file rdf, rappresentativo dei dati da inserire
     * all'interno dell'ontologia presente sul server Sesame.
     * @param rdfDocument
     *            - file rdf che deve essere inviato.
     */
    private void commitToSesame(final Document rdfDocument) {
        if (logger.isDebugEnabled()) {
            logger.debug("commit to sesame - begin");
        }

        File rdfFile = null;
        try {
            String rdfPath = configInstance.getFilePath();
            String fileName =
                    configInstance
                           .getProperty(SesamePropertyKeys.SESAME_RDF_FILENAME);

            if (rdfPath != null) {
                rdfFile =
                        new File(rdfPath + SesamePropertyKeys.SEPARATOR
                                + fileName);

                if (logger.isDebugEnabled()) {
                    logger.debug("rdfFile = "
                            + rdfFile.toString());
                }
            }

            StringWriter sw = new StringWriter();
            OutputFormat format = OutputFormat.createPrettyPrint();
            XMLWriter output = new XMLWriter(sw, format);
            output.write(rdfDocument);
            output.close();

            FileWriter fileWriter = new FileWriter(rdfFile);
            String content = sw.toString();
            content = content.replaceAll("&amp;", "&");
            fileWriter.write(content);
            fileWriter.close();

        } catch (IOException e) {
            logger.error(e.getMessage());
        }

        RepositoryConnection con;

        String baseURI = sesameProps.getProperty(
                SesamePropertyKeys.SESAME_SERVER_BASEURI);

        try {
            con = sesameRepository.getConnection();
            con.add(rdfFile, baseURI, RDFFormat.RDFXML);
            con.commit();
        } catch (RepositoryException e) {
            logger.error(e.getMessage());
        } catch (RDFParseException e) {
            logger.error(e.getMessage());
        } catch (IOException e) {
            logger.error(e.getMessage());
        }

        if (logger.isDebugEnabled()) {
            logger.debug("commit to sesame - end");
        }
    }

}
