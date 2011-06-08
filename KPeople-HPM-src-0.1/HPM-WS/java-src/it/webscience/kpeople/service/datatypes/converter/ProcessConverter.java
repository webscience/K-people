package it.webscience.kpeople.service.datatypes.converter;

import it.webscience.kpeople.service.datatypes.Process;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe di conversione tra la classe Process BE e Process Service.
 */
public final class ProcessConverter {

    /**
     * Costruttore privato.
     */
    private ProcessConverter() {
    }

    /**
     * Converte da Service a BE.
     * @param in oggetto Process Service
     * @return oggetto Process BE
     */
    public static it.webscience.kpeople.be.Process toBE(final Process in) {

        it.webscience.kpeople.be.Process out =
            new it.webscience.kpeople.be.Process();

        out.setIdProcess(in.getIdProcess());
        out.setName(in.getName());
        out.setDescription(in.getDescription());
        out.setDateCreated(in.getDateCreated());
        out.setDateDue(in.getDateDue());
        out.setPrivate(in.isPrivate());
        out.setActive(in.isActive());
        out.setDeleted(in.isDeleted());
        out.setHpmProcessId(in.getHpmProcessId());
        out.setProcessState(ProcessStateConverter.toBE(in.getProcessState()));

        if (in.getVisibility() == Process.PUBLIC) {
            out.setVisibility(
                    it.webscience.kpeople.be.Process.ProcessVisibility.PUBLIC);
        } else if (in.getVisibility() == Process.OWNER) {
            out.setVisibility(
                    it.webscience.kpeople.be.Process.ProcessVisibility.OWNER);
        } else if (in.getVisibility() == Process.ENABLED) {
            out.setVisibility(
                    it.webscience.kpeople.be.Process.ProcessVisibility.ENABLED);
        } else if (in.getVisibility() == Process.PRIVATE) {
            out.setVisibility(
                    it.webscience.kpeople.be.Process.ProcessVisibility.PRIVATE);
        }

        if (in.getOwner() != null) {
            out.setOwner(UserConverter.toBE(in.getOwner()));
        }

        if (in.getProcessType() != null) {
            out.setProcessType(ProcessTypeConverter.toBE(in.getProcessType()));
        }

        if (in.getKeywords() != null) {
            out.setKeywords(KeywordConverter.toBE(in.getKeywords()));
        }

        DataTraceClassConverter.toBE(in, out);

        return out;
    }

    /**
     * Converte da array Service a List BE.
     * @param in array di oggetti Document Service
     * @return Lista BE
     */
    public static List<it.webscience.kpeople.be.Process>
        toBE(final Process[] in) {

        List<it.webscience.kpeople.be.Process> out =
            new ArrayList<it.webscience.kpeople.be.Process>();

        for (int i = 0; i < in.length; i++) {
            out.add(toBE(in[i]));
        }

        return out;
    }

    /**
     * Converte da List Process BE a array Process Service.
     * @param in List Process BE
     * @return array Process Service
     */
    public static Process[] toService(
            final List<it.webscience.kpeople.be.Process> in) {

        Process[] out = new Process[in.size()];

        for (int i = 0; i < in.size(); i++) {
            out[i] = toService(in.get(i));
        }
        return out;
    }

    /**
     * Converte oggetto Process BE in Service.
     * @param in oggetto Process BE
     * @return oggetto Process Service
     */
    public static Process toService(
            final it.webscience.kpeople.be.Process in) {
        Process out = new Process();

        out.setIdProcess(in.getIdProcess());
        out.setName(in.getName());
        out.setDescription(in.getDescription());
        out.setDateCreated(in.getDateCreated());
        out.setDateDue(in.getDateDue());
        out.setPrivate(in.isPrivate());
        out.setActive(in.isActive());
        out.setOwner(UserConverter.toService(in.getOwner()));
        out.setProcessType(ProcessTypeConverter.toService(in.getProcessType()));
        out.setHpmProcessId(in.getHpmProcessId());
        out.setDeleted(in.isDeleted());
        out.setKeywords(KeywordConverter.toService(in.getKeywords()));
        out.setProcessState(
                ProcessStateConverter.toService(in.getProcessState()));

        if (in.getVisibility()
                == it.webscience.kpeople.be.Process.ProcessVisibility.PUBLIC) {
            out.setVisibility(Process.PUBLIC);
        } else if (in.getVisibility()
                == it.webscience.kpeople.be.Process.ProcessVisibility.OWNER) {
            out.setVisibility(Process.OWNER);
        } else if (in.getVisibility()
                == it.webscience.kpeople.be.Process.ProcessVisibility.ENABLED) {
            out.setVisibility(Process.ENABLED);
        } else if (in.getVisibility()
                == it.webscience.kpeople.be.Process.ProcessVisibility.PRIVATE) {
            out.setVisibility(Process.PRIVATE);
        }

        return out;
    }
}
