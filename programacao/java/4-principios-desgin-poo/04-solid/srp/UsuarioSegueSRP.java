/**
 * Exemplo seguindo SRP (Single Responsibility Principle)
 * Responsabilidades separadas em classes diferentes
 * 
 * Cada classe tem UMA única responsabilidade:
 * - Usuario: Apenas representa dados
 * - ValidadorUsuario: Apenas valida
 * - RepositorioUsuario: Apenas persiste
 * - NotificadorEmail: Apenas envia emails
 * - GeradorRelatorio: Apenas gera relatórios
 */

// ✅ SRP: Classe de modelo - APENAS representa dados
class Usuario {
    private Long id;
    private String nome;
    private String email;
    private String senha;
    
    public Usuario(String nome, String email, String senha) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }
    
    // Apenas getters e setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNome() { return nome; }
    public String getEmail() { return email; }
    public String getSenha() { return senha; }
}

// ✅ SRP: Responsabilidade única - Validação
class ValidadorUsuario {
    public void validar(Usuario usuario) {
        if (usuario.getNome() == null || usuario.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("Nome não pode ser vazio");
        }
        if (usuario.getEmail() == null || !usuario.getEmail().contains("@")) {
            throw new IllegalArgumentException("Email inválido");
        }
        if (usuario.getSenha() == null || usuario.getSenha().length() < 8) {
            throw new IllegalArgumentException("Senha deve ter no mínimo 8 caracteres");
        }
    }
}

// ✅ SRP: Responsabilidade única - Persistência
class RepositorioUsuario {
    public void salvar(Usuario usuario) {
        // Simulação de salvamento no banco
        System.out.println("Salvando usuário no banco de dados...");
        System.out.println("Usuário '" + usuario.getNome() + "' salvo com sucesso!");
    }
}

// ✅ SRP: Responsabilidade única - Notificação
class NotificadorEmail {
    public void enviarBoasVindas(Usuario usuario) {
        System.out.println("Enviando email para: " + usuario.getEmail());
        System.out.println("Assunto: Bem-vindo!");
        System.out.println("Corpo: Olá " + usuario.getNome() + ", bem-vindo ao sistema!");
    }
}

// ✅ SRP: Responsabilidade única - Geração de Relatório
class GeradorRelatorio {
    public String gerar(Usuario usuario) {
        StringBuilder sb = new StringBuilder();
        sb.append("====== RELATÓRIO DE USUÁRIO ======\n");
        sb.append("Nome: ").append(usuario.getNome()).append("\n");
        sb.append("Email: ").append(usuario.getEmail()).append("\n");
        sb.append("==================================\n");
        return sb.toString();
    }
}

// Classe principal para demonstração
public class UsuarioSegueSRP {
    public static void main(String[] args) {
        System.out.println("=== SEGUINDO SRP ===");
        
        // Criando usuário
        Usuario usuario = new Usuario("João Silva", "joao@example.com", "senha123456");
        
        // Cada classe tem uma responsabilidade
        ValidadorUsuario validador = new ValidadorUsuario();
        RepositorioUsuario repositorio = new RepositorioUsuario();
        NotificadorEmail notificador = new NotificadorEmail();
        GeradorRelatorio relatorio = new GeradorRelatorio();
        
        try {
            // Validando
            validador.validar(usuario);
            System.out.println("✓ Usuário válido");
            
            // Salvando
            repositorio.salvar(usuario);
            
            // Notificando
            notificador.enviarBoasVindas(usuario);
            
            // Gerando relatório
            System.out.println("\n" + relatorio.gerar(usuario));
            
        } catch (IllegalArgumentException e) {
            System.out.println("Erro de validação: " + e.getMessage());
        }
        
        System.out.println("=== BENEFÍCIOS DO SRP ===");
        System.out.println("1. Cada classe tem UMA responsabilidade");
        System.out.println("2. Mudanças em validação não afetam outras classes");
        System.out.println("3. Fácil de testar cada classe isoladamente");
        System.out.println("4. Classes reutilizáveis em outros contextos");
        System.out.println("5. Código mais organizado e manutenível");
    }
}
