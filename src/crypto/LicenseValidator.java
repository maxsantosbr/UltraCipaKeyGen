package crypto;

import java.security.PublicKey;
import java.security.Signature;
import java.util.Base64;

public class LicenseValidator {

    public static boolean verify(String license, PublicKey key) {
        try {

            String[] parts = license.split("\\.");

            byte[] payload = Base64.getDecoder()
                    .decode(parts[0]);

            byte[] signature = Base64.getDecoder()
                    .decode(parts[1]);

            Signature sig = Signature.getInstance("SHA256withRSA");
            sig.initVerify(key);
            sig.update(payload);

            return sig.verify(signature);

        } catch (Exception e) {
            return false;
        }
    }
}