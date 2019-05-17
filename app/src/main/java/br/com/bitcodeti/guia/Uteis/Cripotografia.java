package br.com.bitcodeti.guia.Uteis;

import java.math.BigInteger;
import java.security.MessageDigest;

/**
 * Created by frederykantunnes on 29/06/17.
 */

public class Cripotografia{
    public static String criptografar(String senha)throws Exception{
    MessageDigest m = MessageDigest.getInstance("MD5");
       m.update(senha.getBytes(),0,senha.length());
        String  a = new BigInteger(1,m.digest()).toString(16);
        return a;
    }
}
