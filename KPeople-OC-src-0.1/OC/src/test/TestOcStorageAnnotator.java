package test;

import java.io.BufferedInputStream;
import java.io.File;

import org.apache.uima.UIMAFramework;
import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.cas.CAS;
import org.apache.uima.resource.ResourceSpecifier;
import org.apache.uima.util.FileUtils;
import org.apache.uima.util.XMLInputSource;

/** Classe per testare l'OcStorageAnnotator.
 * @author dellanna
 */
public class TestOcStorageAnnotator {
    /** xml inviato all'AE. */
    private String xmlEsempio;

    /**
     * @param args parametri di ingresso.
     * @throws Exception ex
     */
    public static void main(final String[] args) throws Exception {
        TestOcStorageAnnotator test = new TestOcStorageAnnotator(args);

        String xmlEsempio = test.getXmlEsempio();
        if (xmlEsempio == null) {
            xmlEsempio =
                "C:\\WebScience\\Progetti\\K-People\\OntologyController_UIMA"
                + "\\apache-uima\\examples\\descriptors\\webscience\\"
                + "analysisEngines\\ocControllerAnnotator.xml";
        }

//      get Resource Specifier from XML file
        XMLInputSource in = new XMLInputSource(xmlEsempio);

        ResourceSpecifier specifier =
            UIMAFramework.getXMLParser().parseResourceSpecifier(in);

//      create Analysis Engine
        AnalysisEngine ae = UIMAFramework.produceAnalysisEngine(specifier);

//      create a CAS
        CAS cas = ae.newCAS();

        File xmlTest = new File(
            "C:\\WebScience\\Progetti\\K-People\\OntologyController_UIMA\\"
            + "apache-uima\\examples\\src\\test\\event-2491.xml");

        test.processFile(xmlTest, ae, cas);

        ae.destroy();
    }

    /** Processes a single XML file and prints annotations to System.out.
     * @param aFile
     *          file to process
     * @param aAE
     *          Analysis Engine that will process the file
     * @param aCAS
     *          CAS that will be used to hold analysis results
     * @throws Exception ex
     */
    private void processFile(
            final File aFile,
            final AnalysisEngine aAE,
            final CAS aCAS)
        throws Exception {
      System.out.println("Processing file " + aFile.getName());
      BufferedInputStream fis = null;

      try {
        String document = FileUtils.file2String(aFile);
        document = document.trim();

        // put document text in CAS
        aCAS.setDocumentText(document);

        // process
        aAE.process(aCAS);

        // print annotations to System.out
//        PrintAnnotations.printAnnotations(aCAS, System.out);

        // reset the CAS to prepare it for processing the next document
        aCAS.reset();
      } finally {
        try {
          if (fis != null) {
            fis.close();
          }
        } catch (Exception ex) {
          ex.printStackTrace();
        }
      }
    }

    /** costruttore parametrizzato.
     * @param args parametri di inizializzazione
     */
    public TestOcStorageAnnotator(final String[] args) {
        super();

        if (args != null && args.length > 0) {
            xmlEsempio = args[0];
        }
    }

    /**
     * @return the xmlEsempio
     */
    public final String getXmlEsempio() {
        return xmlEsempio;
    }
}
