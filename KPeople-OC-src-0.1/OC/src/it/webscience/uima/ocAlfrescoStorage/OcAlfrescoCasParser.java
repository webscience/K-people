package it.webscience.uima.ocAlfrescoStorage;

import it.webscience.uima.annotations.action.ActionReferenceAnnotation;
import it.webscience.uima.annotations.action.ActionTypeAnnotation;
import it.webscience.uima.annotations.action.EventIdAnnotation;
import it.webscience.uima.annotations.action.SystemAnnotation;
import it.webscience.uima.annotations.eventData.AttachmentAnnotation;
import it.webscience.uima.annotations.eventData.DateCreationDateAnnotation;
import it.webscience.uima.annotations.eventData.PropertyAnnotation;
import it.webscience.uima.ocStorageAbstract.OcStorageAttachmentRequest;
import it.webscience.uima.ocStorageAbstract.OcStoragePropertyRequest;
import it.webscience.uima.ocStorageAbstract.OcStorageRequest;

import org.apache.log4j.Logger;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.cas.Type;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;

/** Gestisce il parse della cas in ingresso dall'adapter agent.
 *
 */
public class OcAlfrescoCasParser {

    /** logger. */
    private Logger logger = Logger.getLogger(this.getClass().getName());

    /** Esegue il parsing dell'xml.
     * @param cas cas da parserizzare
     * @return oggetto da inviare ad Alfresco
     */
    public final OcStorageRequest parseCas(final JCas cas)  {
        OcStorageRequest sr = new OcAlfrescoRequest();
        sr.setDocumentText(cas.getDocumentText());
        setEventId(sr, cas);

        addActionTypeProperty(cas, sr);
        addActionReferenceProperty(cas, sr);
        addSystemProperties(cas, sr);
        addCreationDateProperty(cas, sr);

        setProperties(cas, sr);
        setAttachments(cas, sr);

        return sr;
    }

    /** estrae l'event id dall'annotation contenuta nella cas.
     * @param sr storare request
     * @param cas cas da elaborare
     */
    private void setEventId(
            final OcStorageRequest sr,
            final JCas cas) {

        Type annotationType = cas.getTypeSystem().
            getType(EventIdAnnotation.class.getCanonicalName());

        FSIterator<Annotation> it =
            cas.getAnnotationIndex(annotationType).iterator();

        EventIdAnnotation annotation = (EventIdAnnotation) it.next();

        sr.setEventId(annotation.getValue());
    }

    /** recupera gli attachments associati all'evento.
     * e li associa all'oggetto OcStorageRequest
     * @param cas cas da parserizzare
     * @param sr OcStorageRequest
     */
    private void setAttachments(
            final JCas cas, final OcStorageRequest sr) {

        logger.debug("Estrazione attachments per lo evento " + sr.getEventId());

//      estraggo le annotations di tipo AttachmentAnnotation
        Type annotationType = cas.getTypeSystem().
            getType(AttachmentAnnotation.class.getCanonicalName());

        FSIterator<Annotation> it =
            cas.getAnnotationIndex(annotationType).iterator();

        while (it.hasNext()) {
            AttachmentAnnotation annotation = (AttachmentAnnotation) it.next();

            String mimeType = OcAlfrescoMimeTypeMap.getMimeType(
                    annotation.getAttachmentType());

            OcStorageAttachmentRequest sar = new OcStorageAttachmentRequest();
            sar.setId(annotation.getId());
            sar.setData(annotation.getAttachmentData());
            sar.setName(annotation.getAttachmentName());
            sar.setType(mimeType);
            sar.setHashcode(annotation.getHashcode());

            sr.getAttachments().add(sar);
        }
    }

    /** aggiunge la proprietà creation-date.
     * @param cas cas da parserizzare
     * @param sr OcStorageRequest
     */
    private void addCreationDateProperty(
            final JCas cas,
            final OcStorageRequest sr) {
        Type annotationType = cas.getTypeSystem().getType(
                DateCreationDateAnnotation.class.getCanonicalName());
        FSIterator<Annotation> it = cas.getAnnotationIndex(annotationType)
            .iterator();
        DateCreationDateAnnotation annotation =
            (DateCreationDateAnnotation) it.next();

        String propertyName = "creation-date";
        String propertyValue = annotation.getTimeInMillis() + "";
        String propertyType = "d:datetime";

        addProperty(sr, propertyName, propertyValue, propertyType);
    }

    /** aggiunge le system properties.
     * @param cas cas da parserizzare
     * @param sr OcStorageRequest
     */
    private void addSystemProperties(
            final JCas cas,
            final OcStorageRequest sr) {
        Type annotationType = cas.getTypeSystem().getType(
                SystemAnnotation.class.getCanonicalName());
        FSIterator<Annotation> it = cas.getAnnotationIndex(annotationType)
            .iterator();
        SystemAnnotation annotation = (SystemAnnotation) it.next();

        addProperty(sr, "system-id", annotation.getSystemId(), "d:text");
        addProperty(sr, "system-type", annotation.getSystemType(), "d:text");
    }

    /** aggiunge la proprietà action-reference.
     * @param cas cas da parserizzare
     * @param sr OcStorageRequest
     */
    private void addActionReferenceProperty(
            final JCas cas,
            final OcStorageRequest sr) {
        Type annotationType = cas.getTypeSystem().getType(
                ActionReferenceAnnotation.class.getCanonicalName());
        FSIterator<Annotation> it = cas.getAnnotationIndex(annotationType)
            .iterator();
        ActionReferenceAnnotation annotation =
            (ActionReferenceAnnotation) it.next();

        String propertyName = "action-reference";
        String propertyValue = annotation.getValue();
        String propertyType = "d:text";

        addProperty(sr, propertyName, propertyValue, propertyType);
    }

    /** aggiunge la proprietà action-type.
     * @param cas cas da parserizzare
     * @param sr OcStorageRequest
     */
    private void addActionTypeProperty(
            final JCas cas,
            final OcStorageRequest sr) {
        Type annotationType = cas.getTypeSystem().getType(
                ActionTypeAnnotation.class.getCanonicalName());
        FSIterator<Annotation> it = cas.getAnnotationIndex(annotationType)
            .iterator();
        ActionTypeAnnotation annotation = (ActionTypeAnnotation) it.next();

        String propertyName = "action-type";
        String propertyValue = annotation.getValue();
        String propertyType = "d:text";

        addProperty(sr, propertyName, propertyValue, propertyType);
    }

    /** aggiunge una OcStoragePropertyRequest all'ggetto OcStorageRequest.
     * @param sr oggetto OcStorageRequest
     * @param propertyName nome proprietà
     * @param propertyValue valore proprietà
     * @param propertyType tipo di dato
     */
    private void addProperty(
            final OcStorageRequest sr,
            final String propertyName,
            final String propertyValue,
            final String propertyType) {
        OcStoragePropertyRequest property = new OcStoragePropertyRequest();
        property.setName(propertyName);
        property.setValue(propertyValue);
        property.setType(propertyType);

        sr.getProperties().add(property);
    }

    /** recupera le proprietà associate all'evento.
     * e le associa all'oggetto OcStorageRequest
     * @param cas cas da parserizzare
     * @param sr OcStorageRequest
     */
    private void setProperties(
            final JCas cas, final OcStorageRequest sr) {

        logger.debug(
            "Estrazione delle properties per lo evento " + sr.getEventId());

        Type annotationType = cas.getTypeSystem().
               getType(PropertyAnnotation.class.getCanonicalName());
        FSIterator<Annotation> it =
            cas.getAnnotationIndex(annotationType).iterator();

        while (it.hasNext()) {
            PropertyAnnotation annotation = (PropertyAnnotation) it.next();

            OcStoragePropertyRequest spr = new OcStoragePropertyRequest();
            spr.setName(annotation.getKey());
            spr.setValue(annotation.getValue());
            spr.setType("d:text");

            sr.getProperties().add(spr);
        }
    }
}
