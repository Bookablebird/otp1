package database;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

import java.security.Key;
import java.util.Properties;
//import sun.misc.BASE64Encoder;  //kommentoitu pois, hajottaa jenkins tällä hetkellä (EnDeCoder.java:[7,16] cannot find symbol)
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * Decryption class
 *
 * */
@Converter
public class EnDeCoder implements AttributeConverter<String, String> {

    private static final String ALGORITHM = "AES/ECB/PKCS5Padding";
    private static final byte[] KEY = "ItBrokeMySP1R1T!".getBytes();
    /**
     * ENCRYPT for User password
     *
     * */
    @Override
    public String convertToDatabaseColumn(String ccNumber) {
        Key key = new SecretKeySpec(KEY, "AES");
        try {
            Cipher c = Cipher.getInstance(ALGORITHM);
            c.init(Cipher.ENCRYPT_MODE, key);
            return Base64.encode(c.doFinal(ccNumber.getBytes()));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * DECRYPT for User password
     *
     * */
    @Override
    public String convertToEntityAttribute(String dbData) {
        Key key = new SecretKeySpec(KEY, "AES");
        try {
            Cipher c = Cipher.getInstance(ALGORITHM);
            c.init(Cipher.DECRYPT_MODE, key);
            return new String(c.doFinal(Base64.decode(dbData)));
        } catch (Exception e) {
            throw new RuntimeException(e);

        }
    }
}