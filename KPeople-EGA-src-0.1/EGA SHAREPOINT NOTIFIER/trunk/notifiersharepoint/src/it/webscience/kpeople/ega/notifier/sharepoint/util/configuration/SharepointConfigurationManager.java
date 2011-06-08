package it.webscience.kpeople.ega.notifier.sharepoint.util.configuration;



import java.io.File;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import java.util.Properties;

import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;

/**
 * @author filieri
 */
public class SharepointConfigurationManager {

    public static String adapterConfigurationFile = null;
        //"\\EgaWork\\notifierSP\\configuration.properties";

    private static  Properties adapterproperties;
    
    public static void initConfiguration () {
        BundleContext context = FrameworkUtil.getBundle(
                SharepointConfigurationManager.class).getBundleContext();
        
        Properties prop = new Properties();
        InputStream fileInput;
        try {

            fileInput = context.getBundle()
                    .getResource("/configuration/configuration.properties").openStream();
            // adapterproperties.clear();
            prop.load(fileInput);
            adapterConfigurationFile = prop.getProperty("configurationFilesPath");
            
            fileInput.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
 
    }

    public  static Properties getProperties() {
       
        if (adapterConfigurationFile == null) {
            initConfiguration();
        } 
        
        if (adapterproperties == null) {
            adapterproperties = new Properties();
        }
        
        boolean pathExists = false;
        try {
            pathExists = checkConfigurationPath(adapterConfigurationFile);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        if (pathExists) { 
            File file = new File(adapterConfigurationFile);
            FileInputStream fileInput;
            try {
               
                fileInput = new FileInputStream(file);
                adapterproperties.load(fileInput);
                
                fileInput.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } 

        return adapterproperties;
    }

    
    public static Boolean checkConfigurationPath(final String path) throws Exception {

        File file = new File(path);
        if (!file.exists()) {
           
                String message = "Attenzione: verficare la presenza del file di configurazione in: "
                        + path;
                Exception e = new FileNotFoundException(message);

               
                throw e;

            
        } else {
           return true;
        }

    }
    
    
    
    /**
     * @throws IOException
     *             eccezione sollevata
     */
    public static  void commitConfiguration() throws IOException {

        File file = new File(adapterConfigurationFile);
        FileOutputStream fileOutput;
        try {
            fileOutput = new FileOutputStream(file);
            adapterproperties.store(fileOutput, "prop");
            fileOutput.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw e;
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
    }

}
