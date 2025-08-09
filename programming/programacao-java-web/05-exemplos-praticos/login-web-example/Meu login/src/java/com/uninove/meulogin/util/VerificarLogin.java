package com.uninove.meulogin.util;

public class VerificarLogin {
    // Atributos;
    private String usuario;
    private String senha;    
    
    // Encapsulamento
    private String getSenha() {
        return senha;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }        
    
    // Comportamentos (métodos);
    public boolean permissao() {
        
        if (!(getSenha().isEmpty() || getUsuario().isEmpty())) {
            return validarSenha();
        } else {
            return false;
        }  
    }
    
    private boolean validarSenha() {
        return getSenha().equals("123") && getUsuario().equals("admin");
    }
    
}
