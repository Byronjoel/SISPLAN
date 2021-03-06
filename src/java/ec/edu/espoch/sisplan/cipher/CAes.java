/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espoch.sisplan.cipher;

import java.security.Key;
import java.security.MessageDigest;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

/**
 *
 * @author
 */
public class CAes {

    private static final String ALGO = "AES";
    //generate 128bit key
    private static final String keyStr = "LW5Jnx8QeiMe39+xPq+Wy0bzsB3WRY1yxhsOLKYu4NLl2pDsnp6CfOnAlvO3Nq6zin53KxdOGC6ZuL2+ta15rH5NvsQwJS+Mr9yeSVxLrppqe/5R5CkIEtKZi7xQYSVRoJKrKf9Cb4aW6rn8uoF9xw==";

    private static Key generateKey() throws Exception {
        byte[] keyValue = keyStr.getBytes("UTF-8");
        MessageDigest sha = MessageDigest.getInstance("SHA-1");
        keyValue = sha.digest(keyValue);
        keyValue = Arrays.copyOf(keyValue, 16); // use only first 128 bit       
        Key key = new SecretKeySpec(keyValue, ALGO);
        return key;
    }

    public static String encrypt(String Data) throws Exception {
        Key key = generateKey();
        Cipher c = Cipher.getInstance(ALGO);
        c.init(Cipher.ENCRYPT_MODE, key);
        byte[] encVal = c.doFinal(Data.getBytes());
        String encryptedValue = DatatypeConverter.printBase64Binary(encVal);
        return encryptedValue;
    }

    public static String decrypt(String encryptedData) throws Exception {
        Key key = generateKey();
        Cipher c = Cipher.getInstance(ALGO);
        c.init(Cipher.DECRYPT_MODE, key);
        byte[] decordedValue = DatatypeConverter.parseBase64Binary(encryptedData);
        byte[] decValue = c.doFinal(decordedValue);
        String decryptedValue = new String(decValue);
        return decryptedValue;
    }

    /*public static void main(String[] args) throws Exception {
     CAes a=new CAes();
     String message="hola!";
     System.out.println("code: " + a.encrypt(message));
     System.out.println("code: " + a.decrypt(a.encrypt(message)));
     }*/
}
