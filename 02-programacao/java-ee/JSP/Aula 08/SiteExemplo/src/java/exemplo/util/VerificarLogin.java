package exemplo.util;

public class VerificarLogin {
    // Atributos
    private String usuario; 
    private String senha;
    
    // Encapsulamento
    public String getUsuario() {
        return usuario;
    }
    
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    private String getSenha() {
        return senha;
    }
    
    public void setSenha(String senha) {
        this.senha = senha;
    }    
    
    // Coisas que faço (Metodos)
    public boolean permissao() {
        if(!(getSenha().isEmpty() || getUsuario().isEmpty())) {
            return validarSenha();
        }
        else {
            return false;            
        }
    }
    
    private boolean validarSenha() {
        return getUsuario().equals("admin") && getSenha().equals("123");
    }
}
