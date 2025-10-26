/**
 * Exemplo seguindo princípio YAGNI (You Aren't Gonna Need It)
 * Implementa apenas requisitos reais atuais
 * 
 * REQUISITO REAL: "Sistema precisa armazenar nome e email do usuário"
 * 
 * BENEFÍCIO: Código simples, focado no que é necessário agora.
 * Pode evoluir quando houver requisito real.
 */
public class Usuario {
    private Long id;
    private String nome;
    private String email;
    
    public Usuario(String nome, String email) {
        validarNome(nome);
        validarEmail(email);
        this.nome = nome;
        this.email = email;
    }
    
    // Apenas getters e setters necessários
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
        validarNome(nome);
        this.nome = nome;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        validarEmail(email);
        this.email = email;
    }
    
    // Validações simples necessárias
    private void validarNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome não pode ser vazio");
        }
    }
    
    private void validarEmail(String email) {
        if (email == null || !email.contains("@")) {
            throw new IllegalArgumentException("Email inválido");
        }
    }
    
    @Override
    public String toString() {
        return "Usuario{nome='" + nome + "', email='" + email + "'}";
    }
    
    public static void main(String[] args) {
        Usuario usuario = new Usuario("João Silva", "joao@example.com");
        
        System.out.println("=== USUÁRIO SEGUINDO YAGNI ===");
        System.out.println(usuario);
        System.out.println("\n=== BENEFÍCIOS DO YAGNI ===");
        System.out.println("1. Código simples - implementa apenas o necessário");
        System.out.println("2. Fácil de entender e manter");
        System.out.println("3. Menos código para testar");
        System.out.println("4. Pode evoluir quando houver requisito real");
        System.out.println("\nEvolução Gradual:");
        System.out.println("- Se surgir requisito para telefone -> adiciona campo telefone");
        System.out.println("- Se surgir requisito para múltiplos telefones -> refatora para List");
        System.out.println("- Complexidade adicionada APENAS quando justificada");
    }
}
