package crypto;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class PublicKeyLoader {

    public static PublicKey load() throws Exception {

        byte[] fileBytes = Files.readAllBytes(
                Paths.get("config/public.key")
        );

        String key = new String(fileBytes)
                .replaceAll("\\s+", "");

        byte[] decoded = Base64.getDecoder().decode(key);

        X509EncodedKeySpec spec =
                new X509EncodedKeySpec(decoded);

        return KeyFactory.getInstance("RSA")
                .generatePublic(spec);
    }
}