package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;

public class LicenseDAO {

    public static void save(String email, String hwid, String license, String type, String expires) {

        String sql = "INSERT INTO licenses(email, hwid, license, type, expires, created_at) "
                + "VALUES (?, ?, ?, ?, ?, datetime('now'))";

        try (Connection conn = DB.connect();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);
            stmt.setString(2, hwid);
            stmt.setString(3, license);
            stmt.setString(4, type);
            stmt.setString(5, expires);

            stmt.executeUpdate();

            System.out.println("Licença salva");
            JOptionPane.showMessageDialog(null, "LICENÇA SALVA.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
