/**
 * Exemplo de violação do princípio YAGNI (You Aren't Gonna Need It)
 * Sistema de usuários com muita funcionalidade especulativa
 * 
 * PROBLEMA: "Podemos precisar no futuro" levou a complexidade desnecessária atual.
 * Implementa funcionalidades que ninguém pediu e pode nunca usar.
 */
import java.util.*;

public class UsuarioComFuncionalidadeEspeculativa {
    private Long id;
    private String nome;
    private String email;
    
    // ❌ YAGNI: Múltiplos números de telefone - ninguém pediu isso
    private List<String> telefones;
    
    // ❌ YAGNI: Sistema de endereços múltiplos - requisito não existe
    private List<String> enderecos;
    
    // ❌ YAGNI: Preferências complexas - ninguém sabe o que são
    private Map<String, Object> preferencias;
    
    // ❌ YAGNI: Sistema de permissões elaborado - requisito simples atual
    private Set<String> permissoes;
    private List<String> grupos;
    
    // ❌ YAGNI: Histórico de atividades - não há requisito para isso
    private List<String> historicoAtividades;
    
    // ❌ YAGNI: Suporte multi-idioma - aplicação é só português
    private String localePreferido;
    
    public UsuarioComFuncionalidadeEspeculativa(String nome, String email) {
        this.nome = nome;
        this.email = email;
        this.telefones = new ArrayList<>();
        this.enderecos = new ArrayList<>();
        this.preferencias = new HashMap<>();
        this.permissoes = new HashSet<>();
        this.grupos = new ArrayList<>();
        this.historicoAtividades = new ArrayList<>();
        this.localePreferido = "pt-BR";
    }
    
    // Métodos complexos para gerenciar tudo isso...
    public void adicionarTelefone(String tel) {
        telefones.add(tel);
        historicoAtividades.add("Telefone adicionado: " + tel);
    }
    
    public void removerTelefone(String tel) {
        telefones.remove(tel);
        historicoAtividades.add("Telefone removido: " + tel);
    }
    
    public void adicionarEndereco(String endereco) {
        enderecos.add(endereco);
        historicoAtividades.add("Endereço adicionado");
    }
    
    public void configurarPreferencia(String chave, Object valor) {
        preferencias.put(chave, valor);
        historicoAtividades.add("Preferência configurada: " + chave);
    }
    
    public void adicionarPermissao(String permissao) {
        permissoes.add(permissao);
    }
    
    public void adicionarGrupo(String grupo) {
        grupos.add(grupo);
    }
    
    public List<String> getHistorico() {
        return historicoAtividades;
    }
    
    public static void main(String[] args) {
        // Requisito real: "Sistema precisa armazenar nome e email do usuário"
        // Resultado: Centenas de linhas de código para requisito simples!
        
        UsuarioComFuncionalidadeEspeculativa usuario = 
            new UsuarioComFuncionalidadeEspeculativa("João Silva", "joao@example.com");
        
        System.out.println("=== PROBLEMAS DO CÓDIGO COM YAGNI ===");
        System.out.println("1. Implementa funcionalidades que ninguém pediu");
        System.out.println("2. Código complexo para requisito simples");
        System.out.println("3. Difícil de testar (muitos casos de teste desnecessários)");
        System.out.println("4. Custo de manutenção alto");
        System.out.println("5. Se requisitos mudarem, pode ser que nada disso seja usado");
        System.out.println("\nRequisito real era apenas: nome e email!");
    }
}
