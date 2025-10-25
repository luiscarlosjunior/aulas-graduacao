/**
 * SistemaRepositorio.java
 * 
 * Demonstração completa de um sistema de repositório genérico aplicando
 * padrões de design profissionais com Generics.
 * 
 * Este exemplo integra:
 * - Classes genéricas
 * - Interfaces genéricas  
 * - Bounded type parameters
 * - Wildcards
 * - Repository Pattern
 * - Factory Pattern
 * 
 * Representa um caso de uso real de como Generics são utilizados em
 * frameworks como Spring Data JPA, Hibernate, etc.
 * 
 * @author Aulas de Graduação
 */

import java.util.*;
import java.util.stream.Collectors;

/**
 * Interface base para entidades com identificador.
 * 
 * @param <ID> tipo do identificador (Long, Integer, String, etc.)
 */
interface Entity<ID> {
    ID getId();
    void setId(ID id);
}

/**
 * Interface genérica para repositório CRUD.
 * 
 * Esta é a base de muitos frameworks de persistência.
 * Observe como usa bounded type parameter: T deve ser Entity<ID>.
 * 
 * @param <T> tipo da entidade
 * @param <ID> tipo do identificador
 */
interface Repository<T extends Entity<ID>, ID> {
    
    /**
     * Salva uma entidade.
     */
    T save(T entity);
    
    /**
     * Busca entidade por ID.
     */
    Optional<T> findById(ID id);
    
    /**
     * Retorna todas as entidades.
     */
    List<T> findAll();
    
    /**
     * Verifica se existe entidade com o ID.
     */
    boolean existsById(ID id);
    
    /**
     * Deleta uma entidade.
     */
    void delete(T entity);
    
    /**
     * Deleta por ID.
     */
    void deleteById(ID id);
    
    /**
     * Conta total de entidades.
     */
    long count();
}

/**
 * Implementação base abstrata de Repository.
 * 
 * Fornece implementação comum que pode ser reutilizada por repositórios específicos.
 * Armazena dados em memória (em aplicação real, seria banco de dados).
 * 
 * @param <T> tipo da entidade
 * @param <ID> tipo do identificador
 */
abstract class AbstractRepository<T extends Entity<ID>, ID> implements Repository<T, ID> {
    
    protected Map<ID, T> storage = new HashMap<>();
    protected ID nextId;
    
    @Override
    public T save(T entity) {
        if (entity.getId() == null) {
            entity.setId(generateId());
        }
        storage.put(entity.getId(), entity);
        return entity;
    }
    
    @Override
    public Optional<T> findById(ID id) {
        return Optional.ofNullable(storage.get(id));
    }
    
    @Override
    public List<T> findAll() {
        return new ArrayList<>(storage.values());
    }
    
    @Override
    public boolean existsById(ID id) {
        return storage.containsKey(id);
    }
    
    @Override
    public void delete(T entity) {
        storage.remove(entity.getId());
    }
    
    @Override
    public void deleteById(ID id) {
        storage.remove(id);
    }
    
    @Override
    public long count() {
        return storage.size();
    }
    
    /**
     * Método abstrato para gerar ID - cada repositório implementa conforme seu tipo.
     */
    protected abstract ID generateId();
}

/**
 * Classe de domínio: Usuario.
 */
class Usuario implements Entity<Long> {
    private Long id;
    private String nome;
    private String email;
    private int idade;
    
    public Usuario(String nome, String email, int idade) {
        this.nome = nome;
        this.email = email;
        this.idade = idade;
    }
    
    @Override
    public Long getId() {
        return id;
    }
    
    @Override
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
    
    public int getIdade() {
        return idade;
    }
    
    public void setIdade(int idade) {
        this.idade = idade;
    }
    
    @Override
    public String toString() {
        return String.format("Usuario[id=%d, nome=%s, email=%s, idade=%d]",
                           id, nome, email, idade);
    }
}

/**
 * Repositório específico para Usuario.
 */
class UsuarioRepository extends AbstractRepository<Usuario, Long> {
    private long currentId = 1L;
    
    @Override
    protected Long generateId() {
        return currentId++;
    }
    
    /**
     * Método customizado: buscar por email.
     */
    public Optional<Usuario> findByEmail(String email) {
        return storage.values().stream()
                .filter(u -> u.getEmail().equals(email))
                .findFirst();
    }
    
    /**
     * Método customizado: buscar usuários maiores que idade.
     */
    public List<Usuario> findByIdadeGreaterThan(int idade) {
        return storage.values().stream()
                .filter(u -> u.getIdade() > idade)
                .collect(Collectors.toList());
    }
}

/**
 * Classe de domínio: Produto.
 */
class Produto implements Entity<String> {
    private String id; // SKU como ID
    private String nome;
    private double preco;
    private int quantidade;
    
    public Produto(String id, String nome, double preco, int quantidade) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
    }
    
    @Override
    public String getId() {
        return id;
    }
    
    @Override
    public void setId(String id) {
        this.id = id;
    }
    
    public String getNome() {
        return nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public double getPreco() {
        return preco;
    }
    
    public void setPreco(double preco) {
        this.preco = preco;
    }
    
    public int getQuantidade() {
        return quantidade;
    }
    
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
    
    @Override
    public String toString() {
        return String.format("Produto[id=%s, nome=%s, preco=%.2f, qtd=%d]",
                           id, nome, preco, quantidade);
    }
}

/**
 * Repositório específico para Produto.
 */
class ProdutoRepository extends AbstractRepository<Produto, String> {
    
    @Override
    protected String generateId() {
        // Para Produto, ID é fornecido manualmente (SKU)
        throw new UnsupportedOperationException("Produto deve ter ID definido manualmente");
    }
    
    @Override
    public Produto save(Produto entity) {
        if (entity.getId() == null || entity.getId().isEmpty()) {
            throw new IllegalArgumentException("Produto deve ter ID (SKU) definido");
        }
        storage.put(entity.getId(), entity);
        return entity;
    }
    
    /**
     * Método customizado: buscar produtos com estoque baixo.
     */
    public List<Produto> findByQuantidadeLessThan(int quantidade) {
        return storage.values().stream()
                .filter(p -> p.getQuantidade() < quantidade)
                .collect(Collectors.toList());
    }
    
    /**
     * Método customizado: buscar produtos por faixa de preço.
     */
    public List<Produto> findByPrecoEntre(double min, double max) {
        return storage.values().stream()
                .filter(p -> p.getPreco() >= min && p.getPreco() <= max)
                .collect(Collectors.toList());
    }
}

/**
 * Serviço genérico base.
 * 
 * Demonstra como criar camada de serviço sobre repositório genérico.
 * 
 * @param <T> tipo da entidade
 * @param <ID> tipo do identificador
 */
abstract class BaseService<T extends Entity<ID>, ID> {
    protected Repository<T, ID> repository;
    
    public BaseService(Repository<T, ID> repository) {
        this.repository = repository;
    }
    
    public T criar(T entity) {
        System.out.println("  [Service] Validando entidade...");
        validar(entity);
        System.out.println("  [Service] Salvando entidade...");
        return repository.save(entity);
    }
    
    public Optional<T> buscarPorId(ID id) {
        System.out.println("  [Service] Buscando por ID: " + id);
        return repository.findById(id);
    }
    
    public List<T> listarTodos() {
        System.out.println("  [Service] Listando todas as entidades");
        return repository.findAll();
    }
    
    public void remover(ID id) {
        System.out.println("  [Service] Removendo entidade ID: " + id);
        repository.deleteById(id);
    }
    
    /**
     * Método abstrato para validação específica de cada entidade.
     */
    protected abstract void validar(T entity);
}

/**
 * Serviço específico para Usuario.
 */
class UsuarioService extends BaseService<Usuario, Long> {
    
    public UsuarioService(UsuarioRepository repository) {
        super(repository);
    }
    
    @Override
    protected void validar(Usuario usuario) {
        if (usuario.getNome() == null || usuario.getNome().isEmpty()) {
            throw new IllegalArgumentException("Nome é obrigatório");
        }
        if (usuario.getEmail() == null || !usuario.getEmail().contains("@")) {
            throw new IllegalArgumentException("Email inválido");
        }
        if (usuario.getIdade() < 0) {
            throw new IllegalArgumentException("Idade inválida");
        }
    }
    
    public List<Usuario> buscarMaioresDeIdade() {
        return ((UsuarioRepository) repository).findByIdadeGreaterThan(18);
    }
}

/**
 * Utilitários genéricos para trabalhar com repositórios.
 */
class RepositoryUtils {
    
    /**
     * Transfere entidades de um repositório para outro.
     * 
     * Usa wildcards para máxima flexibilidade.
     */
    public static <T extends Entity<ID>, ID> void transferir(
            Repository<? extends T, ID> origem,
            Repository<? super T, ID> destino) {
        
        List<? extends T> entidades = origem.findAll();
        for (T entidade : entidades) {
            destino.save(entidade);
        }
    }
    
    /**
     * Imprime estatísticas de um repositório.
     */
    public static <T extends Entity<ID>, ID> void printEstatisticas(
            Repository<T, ID> repository, String nome) {
        
        System.out.println("Estatísticas de " + nome + ":");
        System.out.println("  Total de registros: " + repository.count());
        System.out.println("  Entidades: " + repository.findAll());
    }
}

/**
 * Classe principal demonstrando o sistema de repositório genérico.
 */
public class SistemaRepositorio {
    
    public static void main(String[] args) {
        System.out.println("=== SISTEMA DE REPOSITÓRIO GENÉRICO ===\n");
        
        // ============================================================
        // EXEMPLO 1: Repositório de Usuários
        // ============================================================
        System.out.println("--- Exemplo 1: Repositório de Usuários ---");
        
        UsuarioRepository usuarioRepo = new UsuarioRepository();
        UsuarioService usuarioService = new UsuarioService(usuarioRepo);
        
        // Criar usuários
        Usuario user1 = usuarioService.criar(new Usuario("Ana Silva", "ana@email.com", 25));
        Usuario user2 = usuarioService.criar(new Usuario("Bruno Costa", "bruno@email.com", 30));
        Usuario user3 = usuarioService.criar(new Usuario("Carlos Lima", "carlos@email.com", 17));
        
        System.out.println("\nUsuários criados:");
        System.out.println("  " + user1);
        System.out.println("  " + user2);
        System.out.println("  " + user3);
        
        // Buscar por ID
        System.out.println("\nBuscando usuário ID 2:");
        Optional<Usuario> encontrado = usuarioService.buscarPorId(2L);
        encontrado.ifPresent(u -> System.out.println("  Encontrado: " + u));
        
        // Listar todos
        System.out.println("\nTodos os usuários:");
        List<Usuario> todosUsuarios = usuarioService.listarTodos();
        todosUsuarios.forEach(u -> System.out.println("  " + u));
        
        // Busca customizada
        System.out.println("\nUsuários maiores de 18 anos:");
        List<Usuario> maiores = usuarioService.buscarMaioresDeIdade();
        maiores.forEach(u -> System.out.println("  " + u));
        
        System.out.println();
        
        // ============================================================
        // EXEMPLO 2: Repositório de Produtos
        // ============================================================
        System.out.println("--- Exemplo 2: Repositório de Produtos ---");
        
        ProdutoRepository produtoRepo = new ProdutoRepository();
        
        // Criar produtos
        Produto p1 = produtoRepo.save(new Produto("PROD-001", "Notebook", 2999.90, 10));
        Produto p2 = produtoRepo.save(new Produto("PROD-002", "Mouse", 49.90, 50));
        Produto p3 = produtoRepo.save(new Produto("PROD-003", "Teclado", 149.90, 3));
        
        System.out.println("Produtos criados:");
        System.out.println("  " + p1);
        System.out.println("  " + p2);
        System.out.println("  " + p3);
        
        // Buscar por ID (String neste caso)
        System.out.println("\nBuscando produto PROD-002:");
        produtoRepo.findById("PROD-002").ifPresent(p -> 
            System.out.println("  Encontrado: " + p));
        
        // Busca customizada: produtos com estoque baixo
        System.out.println("\nProdutos com estoque < 10:");
        List<Produto> estoqueBaixo = produtoRepo.findByQuantidadeLessThan(10);
        estoqueBaixo.forEach(p -> System.out.println("  " + p));
        
        // Busca por faixa de preço
        System.out.println("\nProdutos entre R$ 40 e R$ 200:");
        List<Produto> faixaPreco = produtoRepo.findByPrecoEntre(40.0, 200.0);
        faixaPreco.forEach(p -> System.out.println("  " + p));
        
        System.out.println();
        
        // ============================================================
        // EXEMPLO 3: Operações Genéricas
        // ============================================================
        System.out.println("--- Exemplo 3: Operações Genéricas ---");
        
        // Contar entidades
        System.out.println("Total de usuários: " + usuarioRepo.count());
        System.out.println("Total de produtos: " + produtoRepo.count());
        
        // Verificar existência
        System.out.println("\nExiste usuário ID 1? " + usuarioRepo.existsById(1L));
        System.out.println("Existe produto PROD-999? " + produtoRepo.existsById("PROD-999"));
        
        // Remover entidade
        System.out.println("\nRemovendo usuário ID 3...");
        usuarioRepo.deleteById(3L);
        System.out.println("Total de usuários após remoção: " + usuarioRepo.count());
        
        System.out.println();
        
        // ============================================================
        // EXEMPLO 4: Utilitários Genéricos
        // ============================================================
        System.out.println("--- Exemplo 4: Utilitários Genéricos ---");
        
        RepositoryUtils.printEstatisticas(usuarioRepo, "Usuários");
        System.out.println();
        RepositoryUtils.printEstatisticas(produtoRepo, "Produtos");
        
        System.out.println();
        
        // ============================================================
        // EXEMPLO 5: Polimorfismo com Generics
        // ============================================================
        System.out.println("--- Exemplo 5: Polimorfismo com Generics ---");
        
        // Referência polimórfica - Repository é interface genérica
        Repository<Usuario, Long> repo1 = usuarioRepo;
        Repository<Produto, String> repo2 = produtoRepo;
        
        System.out.println("Trabalhando com referência polimórfica:");
        System.out.println("  Repositório 1 tem " + repo1.count() + " entidades");
        System.out.println("  Repositório 2 tem " + repo2.count() + " entidades");
        
        System.out.println();
        
        // ============================================================
        // RESUMO
        // ============================================================
        System.out.println("=== RESUMO DO PADRÃO REPOSITORY GENÉRICO ===");
        System.out.println("✅ Entity<ID>: interface base para entidades");
        System.out.println("✅ Repository<T, ID>: interface genérica CRUD");
        System.out.println("✅ AbstractRepository: implementação base reutilizável");
        System.out.println("✅ Repositórios específicos: customizações por entidade");
        System.out.println("✅ BaseService: camada de serviço genérica");
        System.out.println("✅ Serviços específicos: lógica de negócio");
        System.out.println("✅ Type safety completa em toda a arquitetura");
        System.out.println("✅ Reutilização máxima de código");
        System.out.println("✅ Padrão usado em Spring Data, Hibernate, etc.");
    }
}
