package it.webscience.kpeople.ega.channel.egaChannelManager;

import it.webscience.kpeople.domain.model.KpeopleAction;
import it.webscience.kpeople.domain.model.KpeopleEvent;
import it.webscience.kpeople.ega.channel.EgaChannelLabel;
import it.webscience.kpeople.ega.channel.KpeopleLogger;
import it.webscience.kpeople.ega.channel.egaChannelSender.EgaChannelSender;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import java.io.InputStreamReader;
import java.util.concurrent.Callable;

import org.apache.axis2.AxisFault;

/**
 * @author filieri
 */
public class EgaChannel implements Callable<KpeopleEvent> {

    /**
     * Evento che deve essere inviato.
     */
    private KpeopleEvent eventToSent;

    /**
     * Percorso all'interno del filesystem all'interno
     * del quale sono memorizzati i file xml relativi agli eventi.
     */
    private String xmlPath;

    /**
     * Identificativo del tipo di azione che è stata intercettata.
     */
    private String actionType;

    /**
     * Oggetto di tipo KpeopleAction che rappresenta l'azione intercettata.
     */
    private KpeopleAction action;

    /**
     * @param eventToSent evento che deve essere inviato.
     */
    public EgaChannel(final KpeopleEvent eventToSent) {
        this.eventToSent = eventToSent;
        this.xmlPath = eventToSent.getXMLevent();
        this.actionType = eventToSent.getFKEventAction().getActionType();
        this.action = eventToSent.getFKEventAction();

    }

    /**
     * Implementazione del metodo call
     * definito nella interfaccia Callable.
     * @return KpeopleEvent - un oggetto di tipo KpeopleEvent
     * che rappresenta l'oggetto da inviare al modulo EPE
     */
    public KpeopleEvent call() {
        // TODO Auto-generated method stub

        File file = new File(xmlPath);
        EgaChannelSender sender = null;
        FileInputStream fileInput = null;
        try {
            sender = new EgaChannelSender();
            fileInput = new FileInputStream(file);
        } catch (AxisFault e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        if (fileInput != null) {
            StringBuilder sb = new StringBuilder();
            String line;

            try {
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(fileInput, "UTF-8"));
                while ((line = reader.readLine()) != null) {
                    sb.append(line).append("\n");
                }

            } catch (UnsupportedEncodingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } finally {
                try {
                    fileInput.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

            boolean isSent = false;
            try {
                isSent = sender.sendEvent(sb.toString(), "/kpeople/event/"
                        + actionType);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            if (isSent) {
                EgaChannelManager.updateDB(action);
                KpeopleLogger
                .getInstance()
                .getService()
                .logInfo("evento inviato",
                        this.getClass() + "call");
            }
        }

        return eventToSent;
    }

}
