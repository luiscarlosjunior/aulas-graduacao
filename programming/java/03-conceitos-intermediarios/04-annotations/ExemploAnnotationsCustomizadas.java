import java.lang.annotation.*;

/**
 * Demonstração completa de Annotations Customizadas
 * 
 * Este exemplo mostra como criar annotations personalizadas com diferentes
 * retention policies, targets, e elementos. Inclui exemplos práticos de
 * annotations para validação, configuração e documentação.
 * 
 * @author Aulas Graduação
 */
public class ExemploAnnotationsCustomizadas {
    
    public static void main(String[] args) {
        System.out.println("=== ANNOTATIONS CUSTOMIZADAS ===\n");
        
        demonstrarAnnotationsDeValidacao();
        System.out.println();
        
        demonstrarAnnotationsDeConfiguracao();
        System.out.println();
        
        demonstrarAnnotationsDeDocumentacao();
        System.out.println();
        
        demonstrarAnnotationsCompostas();
    }
    
    private static void demonstrarAnnotationsDeValidacao() {
        System.out.println("--- Annotations de Validação ---");
        
        // Criando objeto com valores válidos
        Usuario usuario1 = new Usuario();
        usuario1.setNome("João Silva");
        usuario1.setEmail("joao@example.com");
        usuario1.setIdade(25);
        usuario1.setCpf("123.456.789-00");
        
        System.out.println("Validando usuário 1...");
        if (validarObjeto(usuario1)) {
            System.out.println("✓ Usuário 1 válido");
        }
        
        // Criando objeto com valores inválidos
        Usuario usuario2 = new Usuario();
        usuario2.setNome("Jo"); // Muito curto
        usuario2.setEmail("email-invalido"); // Formato errado
        usuario2.setIdade(150); // Muito alto
        usuario2.setCpf("123"); // Formato errado
        
        System.out.println("\nValidando usuário 2...");
        if (!validarObjeto(usuario2)) {
            System.out.println("✗ Usuário 2 inválido");
        }
    }
    
    private static void demonstrarAnnotationsDeConfiguracao() {
        System.out.println("--- Annotations de Configuração ---");
        
        ServicoEmail servico = new ServicoEmail();
        System.out.println("Configurando serviço de email...");
        
        Class<?> classe = servico.getClass();
        if (classe.isAnnotationPresent(Configuracao.class)) {
            Configuracao config = classe.getAnnotation(Configuracao.class);
            System.out.println("Servidor: " + config.servidor());
            System.out.println("Porta: " + config.porta());
            System.out.println("SSL: " + config.ssl());
            System.out.println("Timeout: " + config.timeout() + "s");
        }
    }
    
    private static void demonstrarAnnotationsDeDocumentacao() {
        System.out.println("--- Annotations de Documentação ---");
        
        Class<?> classe = ServicoAutenticacao.class;
        
        if (classe.isAnnotationPresent(DocumentacaoAPI.class)) {
            DocumentacaoAPI doc = classe.getAnnotation(DocumentacaoAPI.class);
            System.out.println("API: " + doc.nome());
            System.out.println("Descrição: " + doc.descricao());
            System.out.println("Versão: " + doc.versao());
            System.out.println("Autor: " + doc.autor());
            System.out.println("Desde: " + doc.desde());
        }
    }
    
    private static void demonstrarAnnotationsCompostas() {
        System.out.println("--- Annotations Compostas (@Repeatable) ---");
        
        try {
            java.lang.reflect.Method metodo = TarefaAgendada.class.getMethod("executarBackup");
            
            if (metodo.isAnnotationPresent(Agendamentos.class)) {
                Agendamentos agendamentos = metodo.getAnnotation(Agendamentos.class);
                System.out.println("Método executarBackup possui múltiplos agendamentos:");
                
                for (Agendamento agenda : agendamentos.value()) {
                    System.out.println("  - Cron: " + agenda.cron());
                    System.out.println("    Zona: " + agenda.zona());
                    System.out.println("    Descrição: " + agenda.descricao());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Validador simples baseado em annotations
     */
    private static boolean validarObjeto(Object obj) {
        boolean valido = true;
        Class<?> classe = obj.getClass();
        
        try {
            for (java.lang.reflect.Field campo : classe.getDeclaredFields()) {
                campo.setAccessible(true);
                Object valor = campo.get(obj);
                
                // Validar @NaoPode Null
                if (campo.isAnnotationPresent(NaoPodeSerNull.class) && valor == null) {
                    NaoPodeSerNull ann = campo.getAnnotation(NaoPodeSerNull.class);
                    System.out.println("  ✗ " + campo.getName() + ": " + ann.mensagem());
                    valido = false;
                }
                
                // Validar @TamanhoTexto
                if (campo.isAnnotationPresent(TamanhoTexto.class) && valor instanceof String) {
                    TamanhoTexto ann = campo.getAnnotation(TamanhoTexto.class);
                    String texto = (String) valor;
                    
                    if (texto.length() < ann.minimo() || texto.length() > ann.maximo()) {
                        System.out.println("  ✗ " + campo.getName() + ": " + ann.mensagem());
                        valido = false;
                    }
                }
                
                // Validar @Faixa
                if (campo.isAnnotationPresent(Faixa.class) && valor instanceof Number) {
                    Faixa ann = campo.getAnnotation(Faixa.class);
                    int numero = ((Number) valor).intValue();
                    
                    if (numero < ann.minimo() || numero > ann.maximo()) {
                        System.out.println("  ✗ " + campo.getName() + ": " + ann.mensagem());
                        valido = false;
                    }
                }
                
                // Validar @Padrao
                if (campo.isAnnotationPresent(Padrao.class) && valor instanceof String) {
                    Padrao ann = campo.getAnnotation(Padrao.class);
                    String texto = (String) valor;
                    
                    if (!texto.matches(ann.regex())) {
                        System.out.println("  ✗ " + campo.getName() + ": " + ann.mensagem());
                        valido = false;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        
        return valido;
    }
}

// ===== ANNOTATIONS DE VALIDAÇÃO =====

/**
 * Annotation para validar que campo não é nulo
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@interface NaoPodeSerNull {
    String mensagem() default "Campo não pode ser nulo";
}

/**
 * Annotation para validar tamanho de texto
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@interface TamanhoTexto {
    int minimo() default 0;
    int maximo() default Integer.MAX_VALUE;
    String mensagem() default "Tamanho do texto inválido";
}

/**
 * Annotation para validar faixa numérica
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@interface Faixa {
    int minimo() default Integer.MIN_VALUE;
    int maximo() default Integer.MAX_VALUE;
    String mensagem() default "Valor fora da faixa permitida";
}

/**
 * Annotation para validar padrão regex
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@interface Padrao {
    String regex();
    String mensagem() default "Formato inválido";
}

/**
 * Classe de exemplo usando annotations de validação
 */
class Usuario {
    @NaoPodeSerNull(mensagem = "Nome é obrigatório")
    @TamanhoTexto(minimo = 3, maximo = 50, mensagem = "Nome deve ter entre 3 e 50 caracteres")
    private String nome;
    
    @NaoPodeSerNull(mensagem = "Email é obrigatório")
    @Padrao(regex = "^[A-Za-z0-9+_.-]+@(.+)$", mensagem = "Email inválido")
    private String email;
    
    @NaoPodeSerNull(mensagem = "Idade é obrigatória")
    @Faixa(minimo = 18, maximo = 100, mensagem = "Idade deve estar entre 18 e 100")
    private Integer idade;
    
    @Padrao(regex = "^\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}$", mensagem = "CPF inválido")
    private String cpf;
    
    // Getters e Setters
    public void setNome(String nome) { this.nome = nome; }
    public void setEmail(String email) { this.email = email; }
    public void setIdade(Integer idade) { this.idade = idade; }
    public void setCpf(String cpf) { this.cpf = cpf; }
    
    public String getNome() { return nome; }
    public String getEmail() { return email; }
    public Integer getIdade() { return idade; }
    public String getCpf() { return cpf; }
}

// ===== ANNOTATIONS DE CONFIGURAÇÃO =====

/**
 * Annotation para configuração de serviços
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@interface Configuracao {
    String servidor() default "localhost";
    int porta() default 8080;
    boolean ssl() default false;
    int timeout() default 30;
    String[] dominiosPermitidos() default {};
}

/**
 * Annotation para configuração de cache
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@interface Cacheavel {
    String nome() default "";
    int ttl() default 3600; // Time to live em segundos
    boolean compartilhado() default false;
}

/**
 * Classe de exemplo com configuração
 */
@Configuracao(
    servidor = "mail.example.com",
    porta = 587,
    ssl = true,
    timeout = 60,
    dominiosPermitidos = {"example.com", "example.org"}
)
class ServicoEmail {
    
    @Cacheavel(nome = "configuracoes-email", ttl = 1800)
    public String obterConfiguracao() {
        return "Configurações do serviço de email";
    }
}

// ===== ANNOTATIONS DE DOCUMENTAÇÃO =====

/**
 * Annotation para documentação de API
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@interface DocumentacaoAPI {
    String nome();
    String descricao();
    String versao() default "1.0";
    String autor() default "Desconhecido";
    String desde() default "";
    boolean experimental() default false;
}

/**
 * Annotation para marcar pontos de entrada da API
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@interface Endpoint {
    String path();
    String metodo() default "GET";
    String[] permissoes() default {};
}

/**
 * Classe de exemplo com documentação
 */
@DocumentacaoAPI(
    nome = "Serviço de Autenticação",
    descricao = "Gerencia autenticação e autorização de usuários",
    versao = "2.1",
    autor = "Equipe de Segurança",
    desde = "2020-01-15"
)
class ServicoAutenticacao {
    
    @DocumentacaoAPI(
        nome = "Login",
        descricao = "Autentica usuário e retorna token JWT",
        experimental = false
    )
    @Endpoint(path = "/api/auth/login", metodo = "POST")
    public String login(String usuario, String senha) {
        return "token-jwt";
    }
    
    @DocumentacaoAPI(
        nome = "Validar Token",
        descricao = "Valida token JWT",
        experimental = false
    )
    @Endpoint(path = "/api/auth/validate", metodo = "GET", permissoes = {"ADMIN", "USER"})
    public boolean validarToken(String token) {
        return true;
    }
}

// ===== ANNOTATIONS REPETÍVEIS (@Repeatable) =====

/**
 * Annotation repetível para agendamento de tarefas
 */
@Repeatable(Agendamentos.class)
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@interface Agendamento {
    String cron();
    String zona() default "UTC";
    String descricao() default "";
}

/**
 * Container para múltiplos agendamentos
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@interface Agendamentos {
    Agendamento[] value();
}

/**
 * Annotation repetível para permissões
 */
@Repeatable(RequerPermissoes.class)
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@interface RequerPermissao {
    String valor();
    String descricao() default "";
}

/**
 * Container para múltiplas permissões
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@interface RequerPermissoes {
    RequerPermissao[] value();
}

/**
 * Classe de exemplo com annotations repetíveis
 */
class TarefaAgendada {
    
    /**
     * Método com múltiplos agendamentos
     */
    @Agendamento(cron = "0 0 2 * * ?", zona = "America/Sao_Paulo", descricao = "Backup diário às 2h")
    @Agendamento(cron = "0 0 14 * * ?", zona = "America/Sao_Paulo", descricao = "Backup diário às 14h")
    @Agendamento(cron = "0 0 * * * ?", zona = "UTC", descricao = "Backup horário (UTC)")
    public void executarBackup() {
        System.out.println("Executando backup...");
    }
    
    /**
     * Método com múltiplas permissões
     */
    @RequerPermissao(valor = "ADMIN", descricao = "Acesso administrativo completo")
    @RequerPermissao(valor = "SYSTEM_MANAGER", descricao = "Gerenciamento de sistema")
    @RequerPermissao(valor = "SUPER_USER", descricao = "Super usuário")
    public void operacaoSensivel() {
        System.out.println("Executando operação sensível...");
    }
}

// ===== ANNOTATIONS COM @Inherited =====

/**
 * Annotation herdável por subclasses
 */
@Inherited
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@interface Auditavel {
    String responsavel();
    boolean registrarMudancas() default true;
}

/**
 * Classe base auditável
 */
@Auditavel(responsavel = "Admin", registrarMudancas = true)
class EntidadeBase {
    protected Long id;
}

/**
 * Subclasse herda @Auditavel automaticamente
 */
class Cliente extends EntidadeBase {
    private String nome;
    
    // Esta classe herda @Auditavel da classe pai
}

// ===== ANNOTATIONS DE MARCAÇÃO (Marker Annotations) =====

/**
 * Annotation de marcação sem elementos
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@interface Entidade {
    // Annotation vazia - apenas marca a classe
}

/**
 * Annotation de marcação para métodos transacionais
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@interface Transacional {
    // Annotation vazia
}

/**
 * Classe marcada como entidade
 */
@Entidade
class Produto {
    private Long id;
    private String nome;
    
    @Transacional
    public void salvar() {
        System.out.println("Salvando produto em transação...");
    }
}
