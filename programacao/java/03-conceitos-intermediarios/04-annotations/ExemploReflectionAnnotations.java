import java.lang.annotation.*;
import java.lang.reflect.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Demonstração completa de Processamento de Annotations via Reflection
 * 
 * Este exemplo mostra como usar a API de Reflection do Java para inspecionar
 * annotations em tempo de execução, implementando frameworks simplificados
 * para validação, injeção de dependências e serialização.
 * 
 * @author Aulas Graduação
 */
public class ExemploReflectionAnnotations {
    
    public static void main(String[] args) {
        System.out.println("=== PROCESSAMENTO DE ANNOTATIONS VIA REFLECTION ===\n");
        
        demonstrarInspecaoBasica();
        System.out.println();
        
        demonstrarFrameworkValidacao();
        System.out.println();
        
        demonstrarInjecaoDependencias();
        System.out.println();
        
        demonstrarSerializacao();
        System.out.println();
        
        demonstrarProcessamentoAvancado();
    }
    
    /**
     * Demonstra inspeção básica de annotations via reflection
     */
    private static void demonstrarInspecaoBasica() {
        System.out.println("--- Inspeção Básica de Annotations ---");
        
        Class<?> classe = ProdutoExemplo.class;
        
        // Verificar se classe tem annotation
        System.out.println("Classe tem @Entidade? " + 
            classe.isAnnotationPresent(Entidade.class));
        
        // Obter annotation da classe
        if (classe.isAnnotationPresent(Entidade.class)) {
            Entidade entidade = classe.getAnnotation(Entidade.class);
            System.out.println("Nome da tabela: " + entidade.tabela());
            System.out.println("Schema: " + entidade.schema());
        }
        
        // Listar todas annotations da classe
        System.out.println("\nAnnotations da classe:");
        for (Annotation ann : classe.getAnnotations()) {
            System.out.println("  - " + ann.annotationType().getSimpleName());
        }
        
        // Inspecionar campos
        System.out.println("\nCampos anotados:");
        for (Field campo : classe.getDeclaredFields()) {
            if (campo.isAnnotationPresent(Coluna.class)) {
                Coluna coluna = campo.getAnnotation(Coluna.class);
                System.out.println("  Campo: " + campo.getName());
                System.out.println("    Nome da coluna: " + coluna.nome());
                System.out.println("    Nullable: " + coluna.nullable());
                System.out.println("    Tamanho: " + coluna.tamanho());
            }
        }
        
        // Inspecionar métodos
        System.out.println("\nMétodos anotados:");
        for (Method metodo : classe.getDeclaredMethods()) {
            if (metodo.isAnnotationPresent(Transacional.class)) {
                Transacional trans = metodo.getAnnotation(Transacional.class);
                System.out.println("  Método: " + metodo.getName());
                System.out.println("    Readonly: " + trans.readonly());
                System.out.println("    Timeout: " + trans.timeout());
            }
        }
    }
    
    /**
     * Demonstra framework de validação usando annotations e reflection
     */
    private static void demonstrarFrameworkValidacao() {
        System.out.println("--- Framework de Validação ---");
        
        // Criar objeto válido
        Pessoa pessoa1 = new Pessoa();
        pessoa1.setNome("Maria Silva");
        pessoa1.setEmail("maria@example.com");
        pessoa1.setIdade(30);
        
        System.out.println("Validando pessoa 1:");
        List<String> erros1 = ValidadorReflection.validar(pessoa1);
        if (erros1.isEmpty()) {
            System.out.println("  ✓ Objeto válido!");
        } else {
            erros1.forEach(erro -> System.out.println("  ✗ " + erro));
        }
        
        // Criar objeto inválido
        Pessoa pessoa2 = new Pessoa();
        pessoa2.setNome(null); // Viola @NaoNulo
        pessoa2.setEmail("email-invalido"); // Viola @Email
        pessoa2.setIdade(150); // Viola @Min e @Max
        
        System.out.println("\nValidando pessoa 2:");
        List<String> erros2 = ValidadorReflection.validar(pessoa2);
        if (erros2.isEmpty()) {
            System.out.println("  ✓ Objeto válido!");
        } else {
            erros2.forEach(erro -> System.out.println("  ✗ " + erro));
        }
    }
    
    /**
     * Demonstra injeção de dependências simples via annotations
     */
    private static void demonstrarInjecaoDependencias() {
        System.out.println("--- Injeção de Dependências ---");
        
        try {
            // Container simples de DI
            ContainerDI container = new ContainerDI();
            
            // Registrar dependências
            container.registrar(RepositorioProduto.class, new RepositorioProdutoImpl());
            container.registrar(ServicoEmail.class, new ServicoEmailImpl());
            
            // Criar e injetar dependências
            ServicoProduto servico = container.criar(ServicoProduto.class);
            
            // Usar serviço
            servico.salvarProduto("Notebook");
            
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
    
    /**
     * Demonstra serialização customizada baseada em annotations
     */
    private static void demonstrarSerializacao() {
        System.out.println("--- Serialização com Annotations ---");
        
        Cliente cliente = new Cliente();
        cliente.setId(1L);
        cliente.setNome("João Silva");
        cliente.setEmail("joao@example.com");
        cliente.setSenha("senha123"); // Não será serializada
        cliente.setCpf("123.456.789-00");
        
        try {
            String json = SerializadorJSON.serializar(cliente);
            System.out.println("JSON serializado:");
            System.out.println(json);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Demonstra processamento avançado de annotations
     */
    private static void demonstrarProcessamentoAvancado() {
        System.out.println("--- Processamento Avançado ---");
        
        Class<?> classe = Controlador.class;
        
        // Processar annotations de métodos
        System.out.println("Endpoints REST encontrados:");
        for (Method metodo : classe.getDeclaredMethods()) {
            if (metodo.isAnnotationPresent(Get.class)) {
                Get get = metodo.getAnnotation(Get.class);
                System.out.println("  GET " + get.path());
                
                // Processar permissões
                if (metodo.isAnnotationPresent(RequerPermissao.class)) {
                    RequerPermissao perm = metodo.getAnnotation(RequerPermissao.class);
                    System.out.println("    Permissões: " + String.join(", ", perm.roles()));
                }
            }
            
            if (metodo.isAnnotationPresent(Post.class)) {
                Post post = metodo.getAnnotation(Post.class);
                System.out.println("  POST " + post.path());
            }
            
            // Processar annotations de parâmetros
            processarParametros(metodo);
        }
    }
    
    /**
     * Processa annotations de parâmetros de método
     */
    private static void processarParametros(Method metodo) {
        Parameter[] parametros = metodo.getParameters();
        Annotation[][] anotacoesParam = metodo.getParameterAnnotations();
        
        for (int i = 0; i < parametros.length; i++) {
            if (anotacoesParam[i].length > 0) {
                System.out.println("    Parâmetro " + parametros[i].getName() + ":");
                for (Annotation ann : anotacoesParam[i]) {
                    System.out.println("      @" + ann.annotationType().getSimpleName());
                }
            }
        }
    }
}

// ===== ANNOTATIONS PARA MAPEAMENTO ORM =====

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@interface Entidade {
    String tabela();
    String schema() default "public";
}

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@interface Id {
    boolean autoIncremento() default true;
}

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@interface Coluna {
    String nome();
    boolean nullable() default true;
    int tamanho() default 255;
}

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@interface Transacional {
    boolean readonly() default false;
    int timeout() default 30;
}

/**
 * Classe exemplo para demonstrar mapeamento ORM
 */
@Entidade(tabela = "produtos", schema = "vendas")
class ProdutoExemplo {
    
    @Id
    @Coluna(nome = "id", nullable = false)
    private Long id;
    
    @Coluna(nome = "nome", nullable = false, tamanho = 100)
    private String nome;
    
    @Coluna(nome = "preco", nullable = false)
    private Double preco;
    
    @Transacional(readonly = false, timeout = 60)
    public void salvar() {
        System.out.println("Salvando produto...");
    }
    
    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public Double getPreco() { return preco; }
    public void setPreco(Double preco) { this.preco = preco; }
}

// ===== ANNOTATIONS PARA VALIDAÇÃO =====

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@interface NaoNulo {
    String mensagem() default "Campo não pode ser nulo";
}

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@interface Tamanho {
    int min() default 0;
    int max() default Integer.MAX_VALUE;
    String mensagem() default "Tamanho inválido";
}

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@interface Email {
    String mensagem() default "Email inválido";
}

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@interface Min {
    int valor();
    String mensagem() default "Valor abaixo do mínimo";
}

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@interface Max {
    int valor();
    String mensagem() default "Valor acima do máximo";
}

/**
 * Classe exemplo para validação
 */
class Pessoa {
    @NaoNulo(mensagem = "Nome é obrigatório")
    @Tamanho(min = 3, max = 50, mensagem = "Nome deve ter entre 3 e 50 caracteres")
    private String nome;
    
    @NaoNulo(mensagem = "Email é obrigatório")
    @Email(mensagem = "Email inválido")
    private String email;
    
    @NaoNulo(mensagem = "Idade é obrigatória")
    @Min(valor = 0, mensagem = "Idade não pode ser negativa")
    @Max(valor = 120, mensagem = "Idade não pode exceder 120")
    private Integer idade;
    
    // Getters e Setters
    public void setNome(String nome) { this.nome = nome; }
    public void setEmail(String email) { this.email = email; }
    public void setIdade(Integer idade) { this.idade = idade; }
    public String getNome() { return nome; }
    public String getEmail() { return email; }
    public Integer getIdade() { return idade; }
}

/**
 * Framework de validação usando reflection
 */
class ValidadorReflection {
    
    public static <T> List<String> validar(T objeto) {
        List<String> erros = new ArrayList<>();
        Class<?> classe = objeto.getClass();
        
        for (Field campo : classe.getDeclaredFields()) {
            campo.setAccessible(true);
            
            try {
                Object valor = campo.get(objeto);
                
                // Validar @NaoNulo
                if (campo.isAnnotationPresent(NaoNulo.class) && valor == null) {
                    NaoNulo ann = campo.getAnnotation(NaoNulo.class);
                    erros.add(campo.getName() + ": " + ann.mensagem());
                }
                
                // Validar @Tamanho
                if (campo.isAnnotationPresent(Tamanho.class) && valor != null) {
                    Tamanho ann = campo.getAnnotation(Tamanho.class);
                    if (valor instanceof String) {
                        String texto = (String) valor;
                        if (texto.length() < ann.min() || texto.length() > ann.max()) {
                            erros.add(campo.getName() + ": " + ann.mensagem());
                        }
                    }
                }
                
                // Validar @Email
                if (campo.isAnnotationPresent(Email.class) && valor != null) {
                    Email ann = campo.getAnnotation(Email.class);
                    String email = valor.toString();
                    if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
                        erros.add(campo.getName() + ": " + ann.mensagem());
                    }
                }
                
                // Validar @Min
                if (campo.isAnnotationPresent(Min.class) && valor != null) {
                    Min ann = campo.getAnnotation(Min.class);
                    if (valor instanceof Number) {
                        int numero = ((Number) valor).intValue();
                        if (numero < ann.valor()) {
                            erros.add(campo.getName() + ": " + ann.mensagem());
                        }
                    }
                }
                
                // Validar @Max
                if (campo.isAnnotationPresent(Max.class) && valor != null) {
                    Max ann = campo.getAnnotation(Max.class);
                    if (valor instanceof Number) {
                        int numero = ((Number) valor).intValue();
                        if (numero > ann.valor()) {
                            erros.add(campo.getName() + ": " + ann.mensagem());
                        }
                    }
                }
                
            } catch (IllegalAccessException e) {
                erros.add("Erro ao acessar campo: " + campo.getName());
            }
        }
        
        return erros;
    }
}

// ===== INJEÇÃO DE DEPENDÊNCIAS =====

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@interface Injetar {
}

/**
 * Container simples de injeção de dependências
 */
class ContainerDI {
    private Map<Class<?>, Object> instancias = new HashMap<>();
    
    public void registrar(Class<?> tipo, Object instancia) {
        instancias.put(tipo, instancia);
    }
    
    @SuppressWarnings("unchecked")
    public <T> T criar(Class<T> classe) throws Exception {
        T instancia = classe.getDeclaredConstructor().newInstance();
        
        // Injetar dependências nos campos anotados
        for (Field campo : classe.getDeclaredFields()) {
            if (campo.isAnnotationPresent(Injetar.class)) {
                campo.setAccessible(true);
                Object dependencia = instancias.get(campo.getType());
                
                if (dependencia == null) {
                    throw new RuntimeException("Dependência não encontrada: " + campo.getType());
                }
                
                campo.set(instancia, dependencia);
                System.out.println("  Injetado " + campo.getType().getSimpleName() + 
                                 " em " + classe.getSimpleName());
            }
        }
        
        return instancia;
    }
}

// Interfaces e implementações para DI
interface RepositorioProduto {
    void salvar(String nome);
}

class RepositorioProdutoImpl implements RepositorioProduto {
    public void salvar(String nome) {
        System.out.println("  Salvando produto no banco: " + nome);
    }
}

interface ServicoEmail {
    void enviar(String mensagem);
}

class ServicoEmailImpl implements ServicoEmail {
    public void enviar(String mensagem) {
        System.out.println("  Enviando email: " + mensagem);
    }
}

class ServicoProduto {
    @Injetar
    private RepositorioProduto repositorio;
    
    @Injetar
    private ServicoEmail servicoEmail;
    
    public void salvarProduto(String nome) {
        System.out.println("\nExecutando salvamento de produto:");
        repositorio.salvar(nome);
        servicoEmail.enviar("Produto " + nome + " foi salvo");
    }
}

// ===== SERIALIZAÇÃO COM ANNOTATIONS =====

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@interface Ignorar {
}

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@interface NomeJSON {
    String valor();
}

class Cliente {
    private Long id;
    
    @NomeJSON(valor = "full_name")
    private String nome;
    
    private String email;
    
    @Ignorar
    private String senha;
    
    @NomeJSON(valor = "document")
    private String cpf;
    
    // Getters e Setters
    public void setId(Long id) { this.id = id; }
    public void setNome(String nome) { this.nome = nome; }
    public void setEmail(String email) { this.email = email; }
    public void setSenha(String senha) { this.senha = senha; }
    public void setCpf(String cpf) { this.cpf = cpf; }
}

/**
 * Serializador JSON simples usando annotations
 */
class SerializadorJSON {
    
    public static String serializar(Object objeto) throws IllegalAccessException {
        StringBuilder json = new StringBuilder("{");
        Class<?> classe = objeto.getClass();
        Field[] campos = classe.getDeclaredFields();
        boolean primeiro = true;
        
        for (Field campo : campos) {
            // Ignorar campos com @Ignorar
            if (campo.isAnnotationPresent(Ignorar.class)) {
                continue;
            }
            
            campo.setAccessible(true);
            Object valor = campo.get(objeto);
            
            if (valor == null) {
                continue;
            }
            
            if (!primeiro) {
                json.append(", ");
            }
            primeiro = false;
            
            // Usar nome customizado se tiver @NomeJSON
            String nomeCampo = campo.getName();
            if (campo.isAnnotationPresent(NomeJSON.class)) {
                NomeJSON ann = campo.getAnnotation(NomeJSON.class);
                nomeCampo = ann.valor();
            }
            
            json.append("\"").append(nomeCampo).append("\": ");
            
            if (valor instanceof String) {
                json.append("\"").append(valor).append("\"");
            } else {
                json.append(valor);
            }
        }
        
        json.append("}");
        return json.toString();
    }
}

// ===== ANNOTATIONS PARA API REST =====

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@interface Get {
    String path();
}

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@interface Post {
    String path();
}

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@interface RequerPermissao {
    String[] roles();
}

@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@interface PathParam {
    String valor();
}

@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@interface CorpoRequisicao {
}

/**
 * Controlador REST de exemplo
 */
class Controlador {
    
    @Get(path = "/api/produtos")
    public List<String> listarProdutos() {
        return Arrays.asList("Produto 1", "Produto 2");
    }
    
    @Get(path = "/api/produtos/{id}")
    @RequerPermissao(roles = {"USER", "ADMIN"})
    public String obterProduto(@PathParam(valor = "id") Long id) {
        return "Produto " + id;
    }
    
    @Post(path = "/api/produtos")
    @RequerPermissao(roles = {"ADMIN"})
    public String criarProduto(@CorpoRequisicao String produto) {
        return "Produto criado: " + produto;
    }
}
