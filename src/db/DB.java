package db;

import java.sql.Connection;
import java.sql.DriverManager;

public class DB {

    private static final String URL = "jdbc:sqlite:licencas.db";

    public static Connection connect() throws Exception {
        return DriverManager.getConnection(URL);
    }
    
}//Classe banco de dados