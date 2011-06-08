package it.webscience.uima.annotators.ldap;

import it.webscience.uima.CasErrorManager;
import it.webscience.uima.Singleton;
import it.webscience.uima.annotations.AmbiguousUserAnnotation;
import it.webscience.uima.annotations.UserAnnotation;
import it.webscience.uima.ocLdapAbstract.OcLdapAdapterAgent;
import it.webscience.uima.ocLdapAbstract.OcLdapUser;
import it.webscience.uima.ocOpenLdap.OcOpenLdapFactory;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.uima.UimaContext;
import org.apache.uima.analysis_component.AnalysisComponent;
import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.cas.Type;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;
import org.apache.uima.resource.ResourceInitializationException;

/**
 * Annotator che si occupa dell'interrogazione del server LDAP.
 */
public class OcLdapAnnotator extends JCasAnnotator_ImplBase {
    /**interfaccia al factory. */
    private OcLdapFactory ocLdapFactory;

    /** logger. */
    private Logger logger = Logger.getLogger(this.getClass().getName());

    /**
     * @see AnalysisComponent#initialize(UimaContext)
     * @param aContext context
     * @throws ResourceInitializationException exception
     */
    public final void initialize(final UimaContext aContext)
            throws ResourceInitializationException {

        if (aContext != null) {
            super.initialize(aContext);
        }

        createOcLdapFactory();
    }

    /** esegue l'analisi della singola annotation.
     * @param cas cas
     * @param userAnnotation annotation da analizzare
     * @param ambiguousGroupId valore da utilizzare nel caso di ambiguità
     */
    private void process(
            final JCas cas,
            final UserAnnotation userAnnotation,
            final int ambiguousGroupId) {

        try {

            OcLdapUser ocLdapUser = parse(userAnnotation);

            OcLdapAdapterAgent ocLdapAdapterAgent =
                ocLdapFactory.createLdapAdapterAgent();
            List<OcLdapUser> response = ocLdapAdapterAgent.search(ocLdapUser);

            if (response.size() > 1) {
    //          ambiguità
                userAnnotation.setAmbiguousGroupId(ambiguousGroupId);
                for (OcLdapUser ldapUser : response) {
                    createAmbiguousUserAnnotation(
                            cas,
                            userAnnotation,
                            ldapUser,
                            ambiguousGroupId);
                }
            } else if (response.size() == 1) {
                OcLdapUser ldapUser = response.get(0);

                userAnnotation.setUsername(ldapUser.getUsername());
                userAnnotation.setEmail(ldapUser.getEmail());
                userAnnotation.setName(ldapUser.getName());
                userAnnotation.setDn(ldapUser.getDn());
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    /** crea una AmbiguousUserAnnotation.
     * @param cas cas
     * @param userAnnotation user annotation origine dell'ambiguità
     * @param ldapUser utente LDAP
     * @param ambiguousGroupId id per l'identificazione del gruppo
     */
    private void createAmbiguousUserAnnotation(
            final JCas cas,
            final UserAnnotation userAnnotation,
            final OcLdapUser ldapUser,
            final int ambiguousGroupId) {

        AmbiguousUserAnnotation ann = new AmbiguousUserAnnotation(cas);
        ann.setBegin(userAnnotation.getBegin());
        ann.setEnd(userAnnotation.getEnd());
        ann.setUsername(ldapUser.getUsername());
        ann.setEmail(ldapUser.getEmail());
        ann.setName(ldapUser.getName());
        ann.setDn(ldapUser.getDn());
        ann.setValue(userAnnotation.getValue());
        ann.setAmbiguousGroupId(ambiguousGroupId);
        ann.addToIndexes();
    }

    /**
     * @see JCasAnnotator_ImplBase#process(JCas)
     * @param cas cas
     * @throws AnalysisEngineProcessException exception
     */
    public final void process(final JCas cas)
            throws AnalysisEngineProcessException {
        logger.debug("Start process");

        try {
    //      estraggo le annotations di tipo DateAnnotation
            Type annotationType = cas.getTypeSystem().
                getType(UserAnnotation.class.getCanonicalName());
            FSIterator<Annotation> it = cas.getAnnotationIndex(annotationType).
                iterator();

            int ambiguousGroupId = 1;
            while (it.hasNext()) {
                UserAnnotation annotation = (UserAnnotation) it.next();
                process(cas, annotation, ambiguousGroupId);

                ambiguousGroupId++;
            }
        } catch (Exception e) {

            logger.fatal("CAS non elaborata correttamente: ");
            logger.fatal("Salvataggio della cas "
                    + cas.toString()
                    + " su file");

            StringWriter sWriter = new StringWriter();
            e.printStackTrace(new PrintWriter(sWriter));
            logger.fatal(sWriter.getBuffer().toString());

            CasErrorManager cem = new CasErrorManager();
            cem.storeCasInFile(cas);

            throw new AnalysisEngineProcessException();
        }

        logger.debug("End process");
    }

    /** costruisce un oggetto OcLdapUser a partire da un UserAnnotation.
     * @param annotation annotation da analizzare
     * @return oggetto OcLdapUser
     */
    private OcLdapUser parse(final UserAnnotation annotation) {
        OcLdapUser olu = new OcLdapUser();

        if (annotation.getEmail() != null) {
            olu.setEmail(annotation.getEmail());
        }

        if (annotation.getUsername() != null) {
            olu.setUsername(annotation.getUsername());
        }

        if (annotation.getName() != null) {
            olu.setName(annotation.getName());
        }

        return olu;
    }

    /** costruisce il factory legato a proprieta' esterne. */
    private void createOcLdapFactory() {
        String agent = Singleton.getInstance()
            .getProperty("ldap-agent");

        if (agent.equals("OpenLDAP")) {
            ocLdapFactory = new OcOpenLdapFactory();
        }
    }
}
