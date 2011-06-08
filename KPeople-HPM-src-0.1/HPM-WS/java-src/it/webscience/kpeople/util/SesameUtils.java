package it.webscience.kpeople.util;

import it.webscience.kpeople.dal.Singleton;

import java.util.Vector;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Namespace;
import org.dom4j.dtd.InternalEntityDecl;
import org.dom4j.tree.DefaultDocumentType;

public class SesameUtils {

    /** logger. */
    private static Logger logger = Logger
            .getLogger(SesameUtils.class.getName());

    /**
     * Metodo per creare lo scheletro del documento rdf contenente i riferimenti
     * ai necessari namespace.
     * @param rdfDocument
     *            documento che deve essere popolato
     * @return un oggetto di tipo Document con lo scheletro del documento rdf.
     */
    public static Document createSesameDocSchema(final Document rdfDocument) {

        if (logger.isDebugEnabled()) {
            logger.debug("createSesameDocSchema - start");
        }

        Document doc = rdfDocument;
        Element rdfRoot = doc.getRootElement();

        String terms = SesamePropertyKey.TERMS;
        String termsValue = Singleton.getInstance().getProperty(terms);

        String owl = SesamePropertyKey.OWL;
        String owlValue = Singleton.getInstance().getProperty(owl);

        String xsd = SesamePropertyKey.XSD;
        String xsdValue = Singleton.getInstance().getProperty(xsd);

        String rdfs = SesamePropertyKey.RDFS;
        String rdfsValue = Singleton.getInstance().getProperty(rdfs);

        String rdf = SesamePropertyKey.RDF;
        String rdfValue = Singleton.getInstance().getProperty(rdf);

        String kpbase = SesamePropertyKey.KPBASE;
        String kpbaseValue = Singleton.getInstance().getProperty(kpbase);

        String rdfDoctype = SesamePropertyKey.RDF_DOCTYPE;
        String rdfDoctypeValue =
                Singleton.getInstance().getProperty(rdfDoctype);

        Vector<InternalEntityDecl> entityDeclList =
                new Vector<InternalEntityDecl>();
        entityDeclList.add(new InternalEntityDecl(terms, termsValue));

        entityDeclList.add(new InternalEntityDecl(owl, owlValue));

        entityDeclList.add(new InternalEntityDecl(xsd, xsdValue));

        entityDeclList.add(new InternalEntityDecl(rdfs, rdfsValue));

        entityDeclList.add(new InternalEntityDecl(rdf, rdfValue));

        entityDeclList.add(new InternalEntityDecl(kpbase, kpbaseValue));

        DefaultDocumentType docType = new DefaultDocumentType();
        docType.setInternalDeclarations(entityDeclList);
        docType.setName(rdfDoctypeValue);
        doc.setDocType(docType);

        rdfRoot = doc.addElement(rdfDoctypeValue);
        Namespace namespace = new Namespace("", kpbaseValue);
        rdfRoot.addAttribute("xml:base", kpbaseValue);
        Namespace namespacerdfs = new Namespace(rdfs, rdfsValue);
        Namespace namespacekpbase = new Namespace(kpbase, kpbaseValue);
        Namespace namespaceterms = new Namespace(terms, termsValue);
        Namespace namespaceowl = new Namespace(owl, owlValue);
        Namespace namespacexsd = new Namespace(xsd, xsdValue);
        Namespace namespacerdf = new Namespace(rdf, rdfValue);

        // add the created namespaces to the document</span>
        doc.getRootElement().add(namespace);

        doc.getRootElement().add(namespacerdfs);
        doc.getRootElement().add(namespacekpbase);
        doc.getRootElement().add(namespaceterms);
        doc.getRootElement().add(namespaceowl);
        doc.getRootElement().add(namespacexsd);
        doc.getRootElement().add(namespacerdf);

        if (logger.isDebugEnabled()) {
            logger.debug("createSesameDocSchema - end");
        }
        return doc;
    }
}
