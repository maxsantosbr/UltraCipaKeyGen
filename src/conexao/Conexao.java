/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexao;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author Maxwell
 */
public class Conexao {

    private static final String URL;

    static {
        try {
            String appData = System.getenv("APPDATA");
            File bancoDir = new File(appData + "\\UltraCIPA\\UltraCipaKeyGenerator\\banco");
            File bancoDestino = new File(bancoDir, "acessoUCKG.db");

            if (!bancoDir.exists()) {
                bancoDir.mkdirs();
            }

            // ✅ MUDANÇA AQUI: lê o banco de dentro do .jar como recurso
            if (!bancoDestino.exists()) {
                try (java.io.InputStream in = Conexao.class
                        .getResourceAsStream("/banco/acessoUCKG.db")) {

                    if (in != null) {
                        java.nio.file.Files.copy(in, bancoDestino.toPath());
                    } else {
                        throw new RuntimeException("BANCO NÃO ENCONTRADO DENTRO DO JAR/CLASSPATH");
                    }
                }
            }

            URL = "jdbc:sqlite:" + bancoDestino.getAbsolutePath();
            System.out.println("BANCO: " + bancoDestino.getAbsolutePath());

        } catch (IOException e) {
            throw new RuntimeException("ERRO AO LOCALIZAR O BANCO DE DADOS: " + e.getMessage());
        }
    }

    public static Connection conectar() {
        try {
            Connection conn = DriverManager.getConnection(URL);
            criarTabelas(conn);
            return conn;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERRO AO TENTAR SE CONECTAR COM O BANCO DE DADOS acessoUCKG: " + e.getMessage());
            return null;
        }
    }//conectar

    private static void criarTabelas(Connection conn) {
        String sqlTabela = "CREATE TABLE IF NOT EXISTS login ("
                + "id                INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "nomeCompleto     TEXT    NOT NULL,"
                + "email             TEXT    NOT NULL UNIQUE,"
                + "senha             TEXT    NOT NULL,"
                + "perfil            TEXT    NOT NULL DEFAULT 'operador',"
                + "ativo             INTEGER NOT NULL DEFAULT 1,"
                + "data_criacao      TEXT    DEFAULT (datetime('now'))"
                + ");";

        try (Statement st = conn.createStatement()) {
            st.execute(sqlTabela);

            // ✅ Insere admin padrão se a tabela estiver vazia
            String sqlVerifica = "SELECT COUNT(*) FROM login";
            ResultSet rs = st.executeQuery(sqlVerifica);
            if (rs.next() && rs.getInt(1) == 0) {
                String sqlAdmin = "INSERT INTO login (nomeCompleto, email, senha, perfil, ativo) "
                        + "VALUES ('Administrador', 'mwllsantos@gmail.com', '369', 'admin', 1)";
                st.execute(sqlAdmin);
                System.out.println("Admin padrão criado.");
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERRO AO TENTAR CRIAR A TABELA LOGIN: " + e.getMessage());
        }
    }//criarTabelas

}//Conexao
