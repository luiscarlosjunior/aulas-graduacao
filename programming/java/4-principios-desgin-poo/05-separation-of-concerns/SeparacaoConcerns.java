/**
 * Exemplo seguindo Separation of Concerns
 * Separação clara entre camadas: Modelo, Validação, Persistência, Serviço
 * 
 * BENEFÍCIO: Mudanças em uma camada não afetam outras.
 * Código organizado e manutenível.
 */

// ✅ SoC: Camada de Modelo - Apenas dados
class Usuario {
    private String nome;
    private String email;
    
    public Usuario(String nome, String email) {
        this.nome = nome;
        this.email = email;
    }
    
    public String getNome() { return nome; }
    public String getEmail() { return email; }
}

// ✅ SoC: Camada de Validação - Apenas valida
class ValidadorUsuario {
    public void validar(Usuario usuario) {
        if (usuario.getNome() == null || usuario.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("Nome obrigatório");
        }
        if (usuario.getEmail() == null || !usuario.getEmail().contains("@")) {
            throw new IllegalArgumentException("Email inválido");
        }
    }
}

// ✅ SoC: Camada de Persistência - Apenas acessa dados
class RepositorioUsuario {
    public void salvar(Usuario usuario) {
        // Simulação de salvamento
        System.out.println("Salvando usuário no banco de dados...");
        System.out.println("Nome: " + usuario.getNome());
        System.out.println("Email: " + usuario.getEmail());
    }
}

// ✅ SoC: Camada de Notificação - Apenas notifica
class NotificadorEmail {
    public void enviarBoasVindas(Usuario usuario) {
        System.out.println("\nEnviando email de boas-vindas...");
        System.out.println("Para: " + usuario.getEmail());
        System.out.println("Olá " + usuario.getNome() + ", bem-vindo!");
    }
}

// ✅ SoC: Camada de Serviço/Lógica de Negócio - Orquestra operações
class ServicoCadastroUsuario {
    private ValidadorUsuario validador;
    private RepositorioUsuario repositorio;
    private NotificadorEmail notificador;
    
    public ServicoCadastroUsuario() {
        this.validador = new ValidadorUsuario();
        this.repositorio = new RepositorioUsuario();
        this.notificador = new NotificadorEmail();
    }
    
    public void cadastrar(Usuario usuario) {
        System.out.println("=== INICIANDO CADASTRO ===");
        
        // Orquestra as operações
        validador.validar(usuario);
        System.out.println("✓ Validação concluída");
        
        repositorio.salvar(usuario);
        System.out.println("✓ Salvamento concluído");
        
        notificador.enviarBoasVindas(usuario);
        System.out.println("✓ Notificação enviada");
        
        System.out.println("\n=== CADASTRO CONCLUÍDO ===");
    }
}

public class SeparacaoConcerns {
    public static void main(String[] args) {
        System.out.println("=== SEPARATION OF CONCERNS ===\n");
        
        Usuario usuario = new Usuario("Maria Silva", "maria@example.com");
        ServicoCadastroUsuario servico = new ServicoCadastroUsuario();
        
        try {
            servico.cadastrar(usuario);
            
            System.out.println("\n=== BENEFÍCIOS DA SEPARAÇÃO ===");
            System.out.println("1. ✓ Cada camada tem responsabilidade clara");
            System.out.println("2. ✓ Validação testável sem banco ou UI");
            System.out.println("3. ✓ Pode trocar banco sem afetar validação");
            System.out.println("4. ✓ Pode trocar notificação sem afetar persistência");
            System.out.println("5. ✓ Mudanças localizadas em camadas específicas");
            System.out.println("6. ✓ Código mais organizado e manutenível");
            
        } catch (IllegalArgumentException e) {
            System.out.println("Erro de validação: " + e.getMessage());
        }
        
        System.out.println("\n=== ESTRUTURA DAS CAMADAS ===");
        System.out.println("Modelo:        Apenas dados");
        System.out.println("Validação:     Apenas valida regras de negócio");
        System.out.println("Persistência:  Apenas salva/busca dados");
        System.out.println("Notificação:   Apenas envia comunicações");
        System.out.println("Serviço:       Orquestra as operações");
    }
}
