/**
 * Exemplo de violação do SRP (Single Responsibility Principle)
 * Classe com MÚLTIPLAS responsabilidades
 * 
 * Responsabilidades misturadas:
 * 1. Representar dados de usuário (modelo)
 * 2. Validar dados de usuário (validação)
 * 3. Salvar no banco de dados (persistência)
 * 4. Enviar email (notificação)
 * 5. Gerar relatório (apresentação)
 * 
 * PROBLEMA: Classe tem múltiplas razões para mudar, violando SRP.
 */
import java.sql.*;

public class UsuarioViolaSRP {
    private Long id;
    private String nome;
    private String email;
    private String senha;
    
    public UsuarioViolaSRP(String nome, String email, String senha) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }
    
    // ❌ Responsabilidade 2: Validação
    public boolean validar() {
        if (nome == null || nome.trim().isEmpty()) {
            System.out.println("Nome inválido");
            return false;
        }
        if (email == null || !email.contains("@")) {
            System.out.println("Email inválido");
            return false;
        }
        if (senha == null || senha.length() < 8) {
            System.out.println("Senha inválida");
            return false;
        }
        return true;
    }
    
    // ❌ Responsabilidade 3: Persistência no banco
    public void salvar() {
        // Código SQL direto na classe de modelo!
        String sql = "INSERT INTO usuarios (nome, email, senha) VALUES (?, ?, ?)";
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/db");
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, this.nome);
            stmt.setString(2, this.email);
            stmt.setString(3, this.senha);
            stmt.executeUpdate();
            conn.close();
            System.out.println("Usuário salvo no banco");
        } catch (SQLException e) {
            System.out.println("Erro ao salvar: " + e.getMessage());
        }
    }
    
    // ❌ Responsabilidade 4: Envio de email
    public void enviarEmailBoasVindas() {
        String assunto = "Bem-vindo!";
        String corpo = "Olá " + this.nome + ", bem-vindo ao sistema!";
        System.out.println("Enviando email para " + this.email);
        System.out.println("Assunto: " + assunto);
        System.out.println("Corpo: " + corpo);
    }
    
    // ❌ Responsabilidade 5: Geração de relatório
    public String gerarRelatorio() {
        StringBuilder sb = new StringBuilder();
        sb.append("====== RELATÓRIO DE USUÁRIO ======\n");
        sb.append("ID: ").append(id).append("\n");
        sb.append("Nome: ").append(nome).append("\n");
        sb.append("Email: ").append(email).append("\n");
        sb.append("==================================\n");
        return sb.toString();
    }
    
    // Getters
    public String getNome() { return nome; }
    public String getEmail() { return email; }
    
    public static void main(String[] args) {
        System.out.println("=== VIOLAÇÃO DO SRP ===");
        UsuarioViolaSRP usuario = new UsuarioViolaSRP("João", "joao@example.com", "senha123");
        
        if (usuario.validar()) {
            // usuario.salvar(); // Comentado pois requer banco
            usuario.enviarEmailBoasVindas();
            System.out.println(usuario.gerarRelatorio());
        }
        
        System.out.println("\n=== PROBLEMAS ===");
        System.out.println("1. Classe tem 5 responsabilidades diferentes");
        System.out.println("2. Mudança em validação afeta classe de modelo");
        System.out.println("3. Mudança em banco afeta classe de modelo");
        System.out.println("4. Difícil de testar (como testar validação sem banco?)");
        System.out.println("5. Impossível reutilizar lógica de email em outros contextos");
    }
}
