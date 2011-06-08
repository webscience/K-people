package it.webscience.kpeople.ega.adapter.sharepoint.util;

import it.webscience.kpeople.ega.adapter.sharepoint.KpeopleLabel;
import it.webscience.kpeople.ega.adapter.sharepoint.KpeopleLogger;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ActionDataProcessorUtil {

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
            KpeopleLogger
                    .getInstance()
                    .getService()
                    .logError(KpeopleLabel.getSystemId(),
                            ActionDataProcessorUtil.class + "Deleted: " + file);
        }
        if (!success) {
            throw new IllegalArgumentException("Delete: deletion failed");
        }
    }

    public static String getDateString(final String dateString) {

        DateFormat formatter;
        Date date = null;
        formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        try {
            date = (Date) formatter.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        SimpleDateFormat df =
                new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.S'Z'");
        String s = df.format(date);

        return s;
    }

    public static String checkEventXMLPath(final String path) throws Exception {

        File dir = new File(path);
        String correctedPath = null;
        if (!dir.exists()) {
            boolean created = dir.mkdir();
            if (!created) {
                String message =
                        "Impossibile creare la directory per il salvataggio"
                                + "temporaneo dei file .xml relativi agli eventi intercettati. Path: "
                                + path;
                Exception e = new FileNotFoundException(message);

                KpeopleLogger
                        .getInstance()
                        .getService()
                        .logError(KpeopleLabel.getSystemId(),
                                ActionDataProcessorUtil.class + e.getMessage());
                throw e;

            }
        } else {
            if (!path.endsWith("//")) {
                correctedPath = path.concat("//");
            }
        }
        return correctedPath;

    }

}
