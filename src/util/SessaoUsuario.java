package util;

import model.Usuario;

public class SessaoUsuario {

    // instância única disponível em toda a aplicação
    private static Usuario usuarioLogado;

    public static void iniciar(Usuario u) {
        usuarioLogado = u;
    }

    public static void encerrar() {
        usuarioLogado = null;
    }

    public static Usuario getUsuario() {
        return usuarioLogado;
    }

    public static String getPerfil() {
        return usuarioLogado != null ? usuarioLogado.getPerfil() : "";
    }

    public static boolean isAdmin() {
        return "admin".equalsIgnoreCase(getPerfil());
    }

    public static boolean isGerente() {
        return "gerente".equalsIgnoreCase(getPerfil());
    }

    public static boolean isOperador() {
        return "operador".equalsIgnoreCase(getPerfil());
    }

    public static boolean estaLogado() {
        return usuarioLogado != null;
    }
}