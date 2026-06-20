package crypto;

import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;

public class KeyLoader {

    // 🔐 COLE SUA PRIVATE KEY AQUI (Base64 gerado pelo RSAKeyGen)
    private static final String PRIVATE_KEY = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDiX1vdz0u/eTZNAwHXpLC1DmHr8oGv6kizFN8V2hyEmKMOtlf/9t8q0Xub5Th0s+JVhhfDxlnrzhXvckyhnuc++cizswIKF9kNRuDQektTfrNyNC7KFyALV+0CxMOJxb463HKU1EjVJmf2nYcdFoHxbNN65/LuUm7c4Grj+OxWMMSm7Z2b0zp5dmK1qPOaf3G0PRA2/df5ZAT31537+GcX+JfRXf8wy1KETRl7FRAHM48QAqTp+RfTM9EGqzAqivIkD/kjvV3/ZPJLAp2Mkkx1OLWkVM1BcqaI/7gbyMwM1729aymVbOQl5tL7CIM4IMeZQhvLAx1dWcVzVtZda8Y/AgMBAAECggEAO1vn+fzejwHY1E7ij8pWr9Nqp/U6Poe/sn3ZlaBQ9BAWSchUwQDaOh8eO53atPdqdSSpXKPJdoTeyZ/+Ki9B0girsTtSYJUqTEn2r5Qx5l4F7f9y+Xkg5PiUZLJ1182reUHPJemYg5QDlO5vvJfnUQhJWCQ0Vnv1KCQMw2TFjD/VDqOPpMOsvIXdazX4j+OZSLWJ4j7bY1dPiZG6utiu2ObXYuyxUEIPIPCAU/2r6QcE3mS8EB3TvnAobq/yQYcKh3lnklT6fqU+NWQ8xIhl1Wuja8HmdrA0nbQXfHQ99udGsA6CXdbNOlFe2aU3HGvKRCNbYIlCMFWoWwrkNb6k8QKBgQD+lCOHWfjqL5QvsU90uLuWGNrY1Ai9tRfBzXAmDo6nawNBpjmOKzf0D7OCtXySj3huLJecV+DSeP19no/cO01ITiqzofZl/fmSmL9Pe3+7XJPv3LBQ8UpFXCBpi3R1JwAje+xzeWXfb/1OVzhWFWi73PPNORt+Dl4+uOQDvVN7pwKBgQDjoufn7HH4N193c4w46gP0sD3f20/UPAFCXO4KeQEHh1V5xnB8VEEpxAy07iyf3mKg7zFB2ctYq2LHjwLl6k4+HBcCzarzXQXxu/gLXNWxf9N9tCNQGxXu1Fhxsm6cayoIO7oc91HE5CwmC6g96iwnzVLT7Ulg7VoM7/BeVQhTqQKBgQDBgqlfAS0MFg52EjlrZ+BvP4VavBHI4hak4hwVUYtuBxH6nPey8XcetbCl8n42iYfpd0vnM4VZd054ZkbECO+9vm0HDl3VOTC0FheVuzJkoQJymTYM0Ny7zEN29uIXCwY38sLRI74kEWRiYDoadTufUVyg6n/TWeiZlMD9drQ2kQKBgGrSLYVWHX1fbrs9S9YBau3OLpr37ZQovGaJ+WKMugDwbxoAFxUg7JtO1q+o/OPglePzmufN7nCjrGz9HV0LAMCAqgO0w74ELx/rF1EttVHgLDzW5aNXsokquH5SPznDyCuo64NgQ8zjThxkaAT1+NaMsYul1ENZ3mInzmJWANxRAoGAUO7rzQyRsEeTvXJ150dJV+4BJ7K/sgxI5Yp7oexPnrV7Gd7POxcFV+tP2vPVKll/aCM/BDS0BuVV3tXxHfYqfhTMtj4w+VZktZayLa6/Fk8clojeTsdI0WqamVgW8UizwoImo+ZeSiV6XNN2VoMIEdq185QEKxysfd4dHi6v000=";

    public static PrivateKey loadPrivateKey() throws Exception {

        // 🔓 decodifica Base64 → bytes
        byte[] keyBytes = Base64.getDecoder().decode(PRIVATE_KEY);

        // 🧩 transforma em especificação RSA padrão Java
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);

        // ⚙️ cria fábrica RSA
        KeyFactory factory = KeyFactory.getInstance("RSA");

        // 🔑 gera objeto PrivateKey
        return factory.generatePrivate(spec);
    }
}