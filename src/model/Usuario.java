/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Maxwell
 */
public class Usuario {
    private int id;
    private String nomeCompleto;
    private String email;
    private String senha;
    private String perfil;
    private int ativo;
    private String dataCriacao;
    
    public Usuario(){}//construtor vazio
    
    public Usuario(String nomeCompleto, String email, String senha, String perfil){
        this.nomeCompleto = nomeCompleto;
        this.email = email;
        this.senha = senha;
        this.perfil = perfil;
        this.ativo = 1;
    }//construtor

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    public int getAtivo() {
        return ativo;
    }

    public void setAtivo(int ativo) {
        this.ativo = ativo;
    }

    public String getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(String dataCriacao) {
        this.dataCriacao = dataCriacao;
    }
    
    @Override
    public String toString(){
       return "Usuario{id=" + id + ", email=" + email + ", perfil=" + perfil + "}"; 
    }
    
}//Usuario
