package it.webscience.uima.ocAlfrescoStorage;

/** Contiene i tipi di dati gestiti in Alfresco.
 * Fornisce un metodo per recuperare il mime type partendo dall'estensione
 * @author dellanna
 */
public class OcAlfrescoMimeTypeMap {
    /** mime type binario generico. */
    public static final String MIMETYPE_BINARY = "application/octet-stream";

    /** mime type txt plain. */
    public static final String MIMETYPE_TEXT_PLAIN = "text/plain";

    /** mime type css. */
    public static final String MIMETYPE_TEXT_CSS = "text/css";

    /** mime type XML. */
    public static final String MIMETYPE_XML = "text/xml";

    /** mime type HTML. */
    public static final String MIMETYPE_HTML = "text/html";

    /** mime type PDF. */
    public static final String MIMETYPE_PDF = "application/pdf";

    /** mime type Microsoft Word. */
    public static final String MIMETYPE_WORD = "application/msword";

    /** mime type Microsoft Excel. */
    public static final String MIMETYPE_EXCEL = "application/vnd.excel";

    /** mime type Microsoft Powerpoint. */
    public static final String MIMETYPE_PPT = "application/vnd.powerpoint";

    /** mime type Flash. */
    public static final String MIMETYPE_FLASH = "application/x-shockwave-flash";

    /** mime type GIF. */
    public static final String MIMETYPE_IMAGE_GIF = "image/gif";

    /** mime type JPEG. */
    public static final String MIMETYPE_IMAGE_JPEG = "image/jpeg";

    /** mime type MP3. */
    public static final String  MIMETYPE_MP3 = "audio/x-mpeg";

    /** Restituisce il mime type associato all'estensione.
     * @param extension estensione da analizzare
     * @return mime type associato
     */
    public static final String getMimeType(final String extension) {
        String mimeType = "";

        if (extension.equals("txt")) {
            mimeType = MIMETYPE_TEXT_PLAIN;
        } else if (extension.equals("doc")) {
            mimeType = MIMETYPE_WORD;
        } else if (extension.equals("jpg")) {
            mimeType = MIMETYPE_IMAGE_JPEG;
        } else if (extension.equals("ppt")) {
            mimeType = MIMETYPE_PPT;
        } else if (extension.equals("css")) {
            mimeType = MIMETYPE_TEXT_CSS;
        } else if (extension.equals("xml")) {
            mimeType = MIMETYPE_XML;
        } else if (extension.equals("html")) {
            mimeType = MIMETYPE_HTML;
        } else if (extension.equals("pdf")) {
            mimeType = MIMETYPE_PDF;
        } else if (extension.equals("xls")) {
            mimeType = MIMETYPE_EXCEL;
        } else if (extension.equals("gif")) {
            mimeType = MIMETYPE_IMAGE_GIF;
        } else if (extension.equals("mp3")) {
            mimeType = MIMETYPE_MP3;
        } else {
            mimeType = MIMETYPE_BINARY;
        }

        return mimeType;
    }
}
