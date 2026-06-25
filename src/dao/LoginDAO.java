package dao;

import conexao.Conexao;
import model.Usuario;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class LoginDAO {

    // ========== CREATE ==========
    public boolean inserir(Usuario u) {
        String sql = "INSERT INTO login (nomeCompleto, email, senha, perfil, ativo) "
                + "VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = Conexao.conectar();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, u.getNomeCompleto());
            ps.setString(2, u.getEmail());
            ps.setString(3, u.getSenha());
            ps.setString(4, u.getPerfil());
            ps.setInt(5, u.getAtivo());
            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERRO AO TENTAR CADASTRAR USUÁRIO: " + e.getMessage());
            return false;
        }
    }

    // ========== READ ==========
    public List<Usuario> listarTodos() {
        List<Usuario> lista = new ArrayList<>();
        String sql = "SELECT * FROM login ORDER BY nomeCompleto";

        try (Connection conn = Conexao.conectar();
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Usuario u = new Usuario();
                u.setId(rs.getInt("id"));
                u.setNomeCompleto(rs.getString("nomeCompleto"));
                u.setEmail(rs.getString("email"));
                u.setSenha(rs.getString("senha"));
                u.setPerfil(rs.getString("perfil"));
                u.setAtivo(rs.getInt("ativo"));
                u.setDataCriacao(rs.getString("data_criacao"));
                lista.add(u);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERRO AO TENTAR LISTAR USUÁRIO: " + e.getMessage());
        }
        return lista;
    }

    public Usuario buscarPorId(int id) {
        String sql = "SELECT * FROM login WHERE id = ?";
        try (Connection conn = Conexao.conectar();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Usuario u = new Usuario();
                u.setId(rs.getInt("id"));
                u.setNomeCompleto(rs.getString("nomeCompleto"));
                u.setEmail(rs.getString("email"));
                u.setSenha(rs.getString("senha"));
                u.setPerfil(rs.getString("perfil"));
                u.setAtivo(rs.getInt("ativo"));
                u.setDataCriacao(rs.getString("data_criacao"));
                return u;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERRO AO TENTAR BUSCAR O USUÁRIO PELO ID: " + e.getMessage());
        }
        return null;
    }

    // ========== UPDATE ==========
    public boolean atualizar(Usuario u) {
        String sql = "UPDATE login SET nomeCompleto=?, email=?, perfil=?, ativo=? WHERE id=?";
        try (Connection conn = Conexao.conectar();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, u.getNomeCompleto());
            ps.setString(2, u.getEmail());
            ps.setString(3, u.getPerfil());
            ps.setInt(4, u.getAtivo());
            ps.setInt(5, u.getId());
            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERRO AO TENTAR ATUALIZAR OS DADOS DO USUÁRIO: " + e.getMessage());
            return false;
        }
    }

    public boolean alterarSenha(int id, String novaSenha) {
        String sql = "UPDATE login SET senha=? WHERE id=?";
        try (Connection conn = Conexao.conectar();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, novaSenha);
            ps.setInt(2, id);
            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERRO AO TENTAR ALTERAR A SENHA: " + e.getMessage());
            return false;
        }
    }

    // ========== DELETE ==========
    public boolean deletar(int id) {
        String sql = "DELETE FROM login WHERE id=?";
        try (Connection conn = Conexao.conectar();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERRO AO TENTAR DELETAR O USUÁRIO: " + e.getMessage());
            return false;
        }
    }

    // ========== LOGIN ==========
    public Usuario validarLogin(String email, String senha) {
        //temp
        System.out.println("Email recebido: " + email);
        System.out.println("Senha recebida: " + senha);

        String sql = "SELECT * FROM login WHERE email=? AND senha=? AND ativo=1";
        try (
                Connection conn = Conexao.conectar();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, email);
            ps.setString(2, senha);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Usuario u = new Usuario();
                u.setId(rs.getInt("id"));
                u.setNomeCompleto(rs.getString("nomeCompleto"));
                u.setEmail(rs.getString("email"));
                u.setPerfil(rs.getString("perfil"));
                return u;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERRO AO TENTAR VALIDAR O LOGIN DO USUÁRIO: " + e.getMessage());
        }
        return null;
    }
}
