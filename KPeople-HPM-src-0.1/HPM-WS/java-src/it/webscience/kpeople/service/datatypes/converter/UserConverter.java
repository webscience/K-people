package it.webscience.kpeople.service.datatypes.converter;

import it.webscience.kpeople.service.datatypes.Process;
import it.webscience.kpeople.service.datatypes.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe di conversione tra la classe User BE e User Service.
 */
public final class UserConverter {

    /**
     * Costruttore privato.
     */
    private UserConverter() {

    }

    /**
     * Converte da Service a BE.
     * @param in oggetto User Service
     * @return oggetto User BE
     */
    public static it.webscience.kpeople.be.User toBE(final User in) {
        it.webscience.kpeople.be.User out = new it.webscience.kpeople.be.User();

        out.setIdUser(in.getIdUser());
        out.setUsername(in.getUsername());
        out.setAccount(in.getAccount());
        out.setHpmUserId(in.getHpmUserId());
        out.setFirstName(in.getFirstName());
        out.setLastName(in.getLastName());
        out.setScreenName(in.getScreenName());
        out.setEmail(in.getEmail());

        return out;
    }

    /**
     * Converte da array Service a List BE.
     * @param in array di oggetti User Service
     * @return Lista BE
     */
    public static List<it.webscience.kpeople.be.User>
        toBE(final User[] in) {

        List<it.webscience.kpeople.be.User> out =
            new ArrayList<it.webscience.kpeople.be.User>();

        for (int i = 0; i < in.length; i++) {
            out.add(toBE(in[i]));
        }

        return out;
    }

    /**
     * Converte da BE a Service.
     * @param in oggetto User BE
     * @return oggetto User Service
     */
    public static User toService(final it.webscience.kpeople.be.User in) {
        User out = new User();
        if (in == null) {
            return null;
        }

        out.setIdUser(in.getIdUser());
        out.setUsername(in.getUsername());
        out.setAccount(in.getAccount());
        out.setHpmUserId(in.getHpmUserId());
        out.setFirstName(in.getFirstName());
        out.setLastName(in.getLastName());
        out.setScreenName(in.getScreenName());
        out.setEmail(in.getEmail());

        return out;
    }

    /**
     * Converte da List User BE a array User Service.
     * @param in List Process BE
     * @return array Process Service
     */
    public static User[] toService(
            final List<it.webscience.kpeople.be.User> in) {

        User[] out = new User[in.size()];

        for (int i = 0; i < in.size(); i++) {
            out[i] = toService(in.get(i));
        }
        return out;
    }
}
