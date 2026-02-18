import java.time.LocalDateTime;

/**
 * Entidade Usuario - Camada de Dados
 * Representa a estrutura de dados de um usuário no sistema
 * 
 * @author Apresentação Java Web
 */
public class UsuarioEntity {
    
    private Long id;
    private String nome;
    private String email;
    private String senha;
    private String telefone;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataUltimoLogin;
    private boolean ativo;
    private String perfil; // ADMIN, USER, GUEST
    
    // Construtores
    public UsuarioEntity() {
        this.dataCriacao = LocalDateTime.now();
        this.ativo = true;
        this.perfil = "USER";
    }
    
    public UsuarioEntity(String nome, String email, String senha) {
        this();
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }
    
    public UsuarioEntity(Long id, String nome, String email, String senha, 
                        String telefone, String perfil) {
        this(nome, email, senha);
        this.id = id;
        this.telefone = telefone;
        this.perfil = perfil;
    }
    
    // Getters e Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getNome() {
        return nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
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
    
    public String getTelefone() {
        return telefone;
    }
    
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    
    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }
    
    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }
    
    public LocalDateTime getDataUltimoLogin() {
        return dataUltimoLogin;
    }
    
    public void setDataUltimoLogin(LocalDateTime dataUltimoLogin) {
        this.dataUltimoLogin = dataUltimoLogin;
    }
    
    public boolean isAtivo() {
        return ativo;
    }
    
    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
    
    public String getPerfil() {
        return perfil;
    }
    
    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }
    
    // Métodos utilitários
    public boolean isAdmin() {
        return "ADMIN".equals(perfil);
    }
    
    public boolean isUser() {
        return "USER".equals(perfil);
    }
    
    public boolean isGuest() {
        return "GUEST".equals(perfil);
    }
    
    public void atualizarUltimoLogin() {
        this.dataUltimoLogin = LocalDateTime.now();
    }
    
    // Validações básicas da entidade
    public boolean isValid() {
        return nome != null && !nome.trim().isEmpty() &&
               email != null && email.contains("@") &&
               senha != null && senha.length() >= 6;
    }
    
    @Override
    public String toString() {
        return String.format(
            "UsuarioEntity{id=%d, nome='%s', email='%s', telefone='%s', " +
            "perfil='%s', ativo=%s, dataCriacao=%s}",
            id, nome, email, telefone, perfil, ativo, 
            dataCriacao != null ? dataCriacao.toString() : "null"
        );
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        
        UsuarioEntity usuario = (UsuarioEntity) obj;
        return id != null ? id.equals(usuario.id) : usuario.id == null;
    }
    
    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
    
    // Método para criar uma cópia sem dados sensíveis (para DTOs)
    public UsuarioEntity toSecureUser() {
        UsuarioEntity secureUser = new UsuarioEntity();
        secureUser.setId(this.id);
        secureUser.setNome(this.nome);
        secureUser.setEmail(this.email);
        secureUser.setTelefone(this.telefone);
        secureUser.setPerfil(this.perfil);
        secureUser.setAtivo(this.ativo);
        secureUser.setDataCriacao(this.dataCriacao);
        secureUser.setDataUltimoLogin(this.dataUltimoLogin);
        // Não copia a senha por segurança
        return secureUser;
    }
}