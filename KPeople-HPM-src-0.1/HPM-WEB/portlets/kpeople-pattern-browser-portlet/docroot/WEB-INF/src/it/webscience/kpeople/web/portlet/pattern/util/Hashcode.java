package it.webscience.kpeople.web.portlet.pattern.util;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.io.IOUtils;


/**
 * Genera l'hashcode a partire da un file.
 */
public class Hashcode {

    /** Algoritmo sha256. */
    public static final String SHA256 = "SHA-256";

    /** Algoritmo sha. */
    public static final String SHA = "SHA";

    /** Algoritmo sha-1. */
    public static final String SHA1 = "SHA-1";

    /** Algoritmo sha-384. */
    public static final String SHA384 = "SHA-384";

    /** Algoritmo sha-512. */
    public static final String SHA512 = "SHA-512";

    /** Algoritmo md2. */
    public static final String MD2 = "MD2";

    /** Algoritmo MD5. */
    public static final String MD5 = "MD5";

    /**
     * genera l'hashcode.
     * @param file file da calcolare
     * @param alg algoritmo da utilizzare
     * @return hashcode
     * @throws NoSuchAlgorithmException algoritmo non trovato
     * @throws IOException errore generico
     */
    public static String getHashcode(
            final InputStream file,
            final String alg) throws NoSuchAlgorithmException, IOException {

        byte[] msg = IOUtils.toByteArray(file);
        MessageDigest md = MessageDigest.getInstance(alg);
        md.update(msg);

        byte[] digest = md.digest();
        return new BigInteger(1, digest).toString(16);
    }
}
