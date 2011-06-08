package it.webscience.kpeople.ega.adapter.Postfix.util;

import it.webscience.kpeople.ega.adapter.Postfix.KpeopleLabel;
import it.webscience.kpeople.ega.adapter.Postfix.KpeopleLogger;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * @author filieri
 */
public class ActionDataProcessorUtil {

    /**
     * @param id
     *            identificativo univoco del sistema verticale.
     * @return un oggetto di tipo string indicante la tipologia di sistema
     *         verticale
     * @throws Exception
     *             eccezione generate
     */
    public static String getSystemType(final String id) throws Exception {
        String systemType = null;
        String[] idSplitted = id.split("\\.");

        try {
            if (idSplitted[0] != null) {
                systemType = idSplitted[0];
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

        return systemType;

    }

    /**
     * @param file
     *            oggetto che rappresenta il filename
     */
    public static void deleteEventFile(final File file) {

        // A File object to represent the filename

        // Make sure the file or directory exists and isn't write protected
        if (!file.exists()) {
            throw new IllegalArgumentException(
                    "Delete: no such file or directory: " + file);
        }

        if (!file.canWrite()) {
            throw new IllegalArgumentException("Delete: write protected: "
                    + file);
        }

        // Attempt to delete it
        boolean success = file.delete();
        if (success) {
            KpeopleLogger.getInstance().getService().logInfo(KpeopleLabel.getSystemId() , "Deleted: " + file);
        }

        if (!success) {
            throw new IllegalArgumentException("Delete: deletion failed");
        }
    }

    /**
     * @param path indica il percorso all'interno
     * del file system che deve essere controllato.
     * @return un oggetto di tipo String con la corretta
     * formattazione per il percorso analizzato.
     * @throws Exception eccezione generata.
     */
    public static String checkTmpPath(final String path) throws Exception {

        File dir = new File(path);
        String correctedPath = null;
        if (!dir.exists()) {
            boolean created = dir.mkdir();
            if (!created) {
                String message = "Impossibile creare la directory"
                        + "per il salvataggio temporaneo dei file .xml "
                        + "relativi agli eventi intercettati. Path: " + path;
                Exception e = new FileNotFoundException(message);

                KpeopleLogger
                        .getInstance()
                        .getService()
                        .logError(KpeopleLabel.getSystemId(),
                                ActionDataProcessorUtil.class + e.getMessage());
                throw e;

            }
        }
        if (!path.endsWith("//")) {
            correctedPath = path.concat("/");
        }
        return correctedPath;

    }
}
