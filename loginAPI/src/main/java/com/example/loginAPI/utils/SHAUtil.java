package com.example.loginAPI.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHAUtil {
    public static String salt = "BCLT*7"; // parte extra que agregamos para mayor seguridad, debe ser static para poder usarlo

    public static String createHash(String value) {
        String res = value + salt;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256"); // MessageDigest: secure one-way hash functions that take arbitrary-sized data and output a fixed-length hash value.
            //Hay SHA-1, SHA-256, SHA-384, SHA-512
            md.update(res.getBytes()); //updates the digest using the specified byte
            byte [] resultado = md.digest(); //.digest(): Completes the hash computation by performing final operations such as padding
            int max = resultado.length;
            String tmp;
            res = "";
            for (int i =0; i<max; i++) {
                tmp = Integer.toHexString(0xFF & resultado[i]); //debemos agregar el 0xFF
                res += (tmp.length()<2)? 0 + tmp: tmp; //Operador ternario
            }//for
        }//try
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }//catch
        return res;
    }//createHash()

    public static boolean verifyHash(String original, String hash) { //creamos función para validar nuestro hash
        String res = createHash(original);
        return res.equalsIgnoreCase(hash); //Compares this String to another String, ignoring case considerations
    }//verifyHash

    public static void main(String[] args) {
        System.out.println("El hash generado es: " + createHash("fernanda"));
        System.out.println(verifyHash("bernardo", "6cc5f7e4d0adbba150f8a169be2119bee7f5743f46a1798e492e13b672736291"));
        System.out.println(verifyHash("bernardo", "6cc5f7e4d0adbba150f8a169be2119bee7f5743f46a1798e492e13b67273629g"));
    }//psvm

}//Class SHAUtil
