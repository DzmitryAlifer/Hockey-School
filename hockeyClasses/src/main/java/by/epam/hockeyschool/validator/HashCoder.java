package by.epam.hockeyschool.validator;

import org.apache.log4j.Logger;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Dmitry Olifer on 17.07.2015.
 *
 * The {@code HashCoder} class represents the way to calculate
 * hashed value from its starting string equivalent.
 */
public class HashCoder {

    final static Logger LOG = Logger.getLogger(HashCoder.class);

    /**
     * Calculates hexadecimal hashed value of a string via 'MD5'
     * and represents it as an instance of {@code String}.
     * @param   word is the string value which have to be hashed.
     * @return  an instance of {@code String} that represents
     *          hexadecimal hashed value of {@param word}.
     */
    public String createHashedValue(String word) {
        MessageDigest messageDigest = null;
        byte[] array = null;
        try {
            messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(word.getBytes("utf-8"));
            array = messageDigest.digest();
        } catch (NoSuchAlgorithmException e) {
            LOG.error("NoSuchAlgorithmException occurred during hashing");
        } catch (UnsupportedEncodingException e) {
            LOG.error("UnsupportedEncodingException occurred during hashing");
        }
        BigInteger bigInteger = new BigInteger(1, array);
        String hexResult = bigInteger.toString(16);
        return hexResult;
    }

}
