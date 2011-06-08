package it.webscience.kpeople.ega.core.configurationService;

import it.webscience.kpeople.ega.core.KpeopleLabel;
import it.webscience.kpeople.ega.core.startupManager.StartupManager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;


/**
 * @author XPMUser
 */
public class ConfigurationManager {

    /**
     * Indica il nome del file di configurazione del file di configurazione.
     */
    private static String adapterConfigurationFile = KpeopleLabel
            .getAdapterConfigurationFileName();
    /**
     * crea un nuovo oggetto di tipo Properties.
     */
    private static Properties adapterproperties;

    /**
     * metodo per settare il valore di una proprietà.
     */
    public static void commitConfiguration() {
        File file = new File(StartupManager.getProperties().getProperty(
                "configurationFilesPath")
                + adapterConfigurationFile);
        FileOutputStream fileOutput;
        try {
            fileOutput = new FileOutputStream(file);

            adapterproperties.store(fileOutput, "prop");

            fileOutput.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * @return adapterproperties.
     */
    public static Properties getProperties() {
        if (adapterproperties == null) {
            adapterproperties = new Properties();
        }
        // InputStream file_url = null;
        File file = new File(StartupManager.getProperties().getProperty(
                "configurationFilesPath")
                + adapterConfigurationFile);

        FileInputStream fileInput;
        try {

            fileInput = new FileInputStream(file);
            adapterproperties.load(fileInput);

            fileInput.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return adapterproperties;
    }

}
