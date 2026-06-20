package db;

import java.sql.Connection;
import java.sql.Statement;

public class DatabaseInitializer {

    public static void initialize() {

        String sql
                = "CREATE TABLE IF NOT EXISTS licenses ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "email TEXT NOT NULL,"
                + "hwid TEXT NOT NULL,"
                + "license TEXT NOT NULL,"
                + "type TEXT NOT NULL,"
                + "expires TEXT NOT NULL,"
                + "created_at TEXT NOT NULL"
                + ");";

        try (
                Connection conn = DB.connect();
                Statement stmt = conn.createStatement()) {

            stmt.execute(sql);

            System.out.println("Tabela licenses criada.");

        } catch (Exception e) {

            e.printStackTrace();

        }
    }
}
