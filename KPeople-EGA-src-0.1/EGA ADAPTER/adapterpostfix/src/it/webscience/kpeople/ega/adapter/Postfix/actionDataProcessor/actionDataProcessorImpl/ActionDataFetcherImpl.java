package it.webscience.kpeople.ega.adapter.Postfix.actionDataProcessor.actionDataProcessorImpl;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import jcifs.smb.NtlmPasswordAuthentication;
import jcifs.smb.SmbFile;
import jcifs.smb.SmbFileInputStream;

import it.webscience.kpeople.domain.model.KpeopleAction;
import it.webscience.kpeople.ega.adapter.Postfix.KpeopleLabel;
import it.webscience.kpeople.ega.adapter.Postfix.KpeopleLogger;
import it.webscience.kpeople.ega.adapter.Postfix.actionDataProcessor.actionDataInterface.ActionDataFetcherInterface;
import it.webscience.kpeople.ega.adapter.Postfix.actionDataProcessorManager.ActionDataProcessorManager;
import it.webscience.kpeople.ega.adapter.Postfix.util.ActionDataProcessorUtil;

/**
 * @author filieri
 */
public class ActionDataFetcherImpl implements ActionDataFetcherInterface {

    /**
     * Oggetto ti tipo java.Properties contenente le
     * informazioni relative ai parametri di configurazione dell'adapter.
     */
    private static Properties currentProperties = ActionDataProcessorManager
            .getAdapterproperties();

    /**
     * @see it.webscience.kpeople.ega.adapter.Postfix.actionDataProcessor
     * .actionDataInterface.ActionDataFetcherInterface#fetch(it.webscience
     * .kpeople.domain.model.KpeopleAction)
     * @param action azione che è stata intercettata
     * @return un'istanza della classe Object che rappresenta l'azione
     *         intercettata
     * @throws Exception eccezione generata
     */

    public String fetch(final KpeopleAction action) throws Exception {
        KpeopleLogger
                .getInstance()
                .getService()
                .logInfo(
                        KpeopleLabel.getSystemId(),
                        this.getClass() + ".fetch()"
                                + KpeopleLabel.getLogStart());


        InputStream mail = null;
        String path = null;

        String actionReference = action.getActionReference();

        String[] actionReferenceSplitted = actionReference.split(":");

        String protocol = actionReferenceSplitted[0];

        if (protocol.equals(KpeopleLabel.getSamba())) {
            try {
                jcifs.Config.registerSmbURLHandler();

                NtlmPasswordAuthentication auth =
                    new NtlmPasswordAuthentication(
                        "", "kpeople@postfix", "kpeople");

                SmbFile smbfile = new SmbFile(action.getActionReference());

                InputStream in = new SmbFileInputStream(smbfile);
                String tmpPath = (String) currentProperties.get(KpeopleLabel
                        .getEventTmpPath());
                String checkedPath = ActionDataProcessorUtil
                        .checkTmpPath(tmpPath);
                
                path = checkedPath + action.getIdAction() + ".txt";
                OutputStream out = new FileOutputStream(path);

                byte[] b = new byte[8192];
                int n;

                while ((n = in.read(b)) > 0) {
                    out.write(b, 0, n);
                }

                in.close();
                out.close();

                mail = new FileInputStream(path);
                
                mail.close();
            } catch (Exception e) {
                e.printStackTrace();
                throw e;
            }
        }

        KpeopleLogger
                .getInstance()
                .getService()
                .logInfo(
                        KpeopleLabel.getSystemId(),
                        this.getClass() + ".fetch()"
                                + KpeopleLabel.getLogStop());
        return path;
    }

}
