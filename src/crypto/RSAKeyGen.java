package crypto;

import java.security.*;
import java.util.Base64;

public class RSAKeyGen {

    public static void main(String[] args) throws Exception {

        KeyPairGenerator gen = KeyPairGenerator.getInstance("RSA");
        gen.initialize(2048);

        KeyPair pair = gen.generateKeyPair();

        System.out.println("PUBLIC KEY:");
        System.out.println(Base64.getEncoder().encodeToString(pair.getPublic().getEncoded()));

        System.out.println("\nPRIVATE KEY:");
        System.out.println(Base64.getEncoder().encodeToString(pair.getPrivate().getEncoded()));
    }
}