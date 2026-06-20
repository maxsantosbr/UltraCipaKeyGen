package crypto;

import java.security.PrivateKey;
import java.security.Signature;
import java.util.Base64;

public class LicenseService {

    public static String generate(String payload, PrivateKey privateKey) throws Exception {

        Signature rsa = Signature.getInstance("SHA256withRSA");
        rsa.initSign(privateKey);
        rsa.update(payload.getBytes("UTF-8"));

        byte[] signature = rsa.sign();

        String encodedPayload = Base64.getEncoder().encodeToString(payload.getBytes("UTF-8"));
        String encodedSig = Base64.getEncoder().encodeToString(signature);

        return encodedPayload + "." + encodedSig;
    }
}