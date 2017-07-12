package br.com.system.controller.security;

import java.security.MessageDigest;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author crhobus
 */
public class SisMecSecurity {

    public byte[] generateHash(String str) throws Exception {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(str.getBytes());
        return md.digest();
    }

    public SecretKey getSecretKey(String key) throws Exception {
        byte k[] = generateHash(key);
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DESede");
        return keyFactory.generateSecret(new DESedeKeySpec(k));
    }

    public SecretKey getSecretKey(byte[] key) throws Exception {
        return new SecretKeySpec(key, 0, key.length, "DESede");
    }

    public byte[] encryptDataSymmetric(SecretKey secretKey, String data) throws Exception {
        Cipher desCipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
        desCipher.init(Cipher.ENCRYPT_MODE, secretKey);

        return desCipher.doFinal(data.getBytes());
    }

    public String decryptDataSymmetric(SecretKey secretKey, byte[] data) throws Exception {
        Cipher desCipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
        desCipher.init(Cipher.DECRYPT_MODE, secretKey);

        return new String(desCipher.doFinal(data));
    }
}
