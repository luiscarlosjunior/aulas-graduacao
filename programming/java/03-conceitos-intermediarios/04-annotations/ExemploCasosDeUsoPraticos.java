import java.lang.annotation.*;
import java.lang.reflect.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Demonstração de Casos de Uso Práticos com Annotations
 * 
 * Este exemplo mostra aplicações reais de annotations em frameworks
 * e bibliotecas, incluindo testes, cache, auditoria e configuração.
 * 
 * @author Aulas Graduação
 */
public class ExemploCasosDeUsoPraticos {
    
    public static void main(String[] args) {
        System.out.println("=== CASOS DE USO PRÁTICOS COM ANNOTATIONS ===\n");
        
        demonstrarFrameworkTestes();
        System.out.println();
        
        demonstrarSistemaCache();
        System.out.println();
        
        demonstrarAuditoria();
        System.out.println();
        
        demonstrarAgendamentoTarefas();
        System.out.println();
        
        demonstrarValidacaoBeans();
    }
    
    /**
     * Demonstra mini-framework de testes similar ao JUnit
     */
    private static void demonstrarFrameworkTestes() {
        System.out.println("--- Framework de Testes ---");
        
        ExecutorTestes executor = new ExecutorTestes();
        executor.executarTestes(CalculadoraTeste.class);
    }
    
    /**
     * Demonstra sistema de cache com annotations
     */
    private static void demonstrarSistemaCache() {
        System.out.println("--- Sistema de Cache ---");
        
        ServicoComCache servico = new ServicoComCache();
        ProxyCache proxy = new ProxyCache(servico);
        
        // Primeira chamada - executa método
        System.out.println("1ª chamada: " + proxy.buscarDados(1));
        
        // Segunda chamada - retorna do cache
        System.out.println("2ª chamada: " + proxy.buscarDados(1));
        
        // Chamada com parâmetro diferente - executa método
        System.out.println("3ª chamada: " + proxy.buscarDados(2));
        
        // Limpar cache
        proxy.limparCache();
        System.out.println("Cache limpo!");
        
        // Nova chamada - executa método novamente
        System.out.println("4ª chamada: " + proxy.buscarDados(1));
    }
    
    /**
     * Demonstra sistema de auditoria
     */
    private static void demonstrarAuditoria() {
        System.out.println("--- Sistema de Auditoria ---");
        
        ServicoAuditado servico = new ServicoAuditado();
        ProxyAuditoria proxy = new ProxyAuditoria(servico);
        
        proxy.operacaoCritica("Dados sensíveis");
        proxy.operacaoNormal("Dados normais");
    }
    
    /**
     * Demonstra agendamento de tarefas
     */
    private static void demonstrarAgendamentoTarefas() {
        System.out.println("--- Agendamento de Tarefas ---");
        
        AgendadorTarefas agendador = new AgendadorTarefas();
        agendador.registrarTarefas(TarefasAgendadas.class);
        agendador.listarTarefas();
    }
    
    /**
     * Demonstra validação de beans
     */
    private static void demonstrarValidacaoBeans() {
        System.out.println("--- Validação de Beans ---");
        
        // Formulário válido
        FormularioRegistro form1 = new FormularioRegistro();
        form1.setNome("João Silva");
        form1.setEmail("joao@example.com");
        form1.setIdade(25);
        form1.setSenha("SenhaForte123!");
        form1.setConfirmacaoSenha("SenhaForte123!");
        form1.setAceitaTermos(true);
        
        System.out.println("Validando formulário 1:");
        ValidadorBeans.validar(form1).forEach(System.out::println);
        
        // Formulário inválido
        FormularioRegistro form2 = new FormularioRegistro();
        form2.setNome("Jo");
        form2.setEmail("email-invalido");
        form2.setIdade(15);
        form2.setSenha("123");
        form2.setConfirmacaoSenha("456");
        form2.setAceitaTermos(false);
        
        System.out.println("\nValidando formulário 2:");
        ValidadorBeans.validar(form2).forEach(System.out::println);
    }
}

// ===== FRAMEWORK DE TESTES =====

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@interface Teste {
    String descricao() default "";
}

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@interface AntesDecada {
}

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@interface DepoisDecada {
}

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@interface AntesDeTodos {
}

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@interface DepoisDeTodos {
}

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@interface Ignorar {
    String motivo() default "";
}

/**
 * Classe de testes de exemplo
 */
class CalculadoraTeste {
    private int contador;
    
    @AntesDeTodos
    public static void configurarTodos() {
        System.out.println("  [Setup Global] Configurando todos os testes");
    }
    
    @AntesDeCA
    public void configurar() {
        contador = 0;
        System.out.println("  [Setup] Antes de cada teste");
    }
    
    @Teste(descricao = "Deve somar dois números positivos")
    public void testeSomaPositivos() {
        int resultado = 2 + 3;
        if (resultado != 5) {
            throw new AssertionError("Esperado 5, mas obteve " + resultado);
        }
        System.out.println("  ✓ Teste de soma positivos passou");
    }
    
    @Teste(descricao = "Deve subtrair corretamente")
    public void testeSubtracao() {
        int resultado = 10 - 3;
        if (resultado != 7) {
            throw new AssertionError("Esperado 7, mas obteve " + resultado);
        }
        System.out.println("  ✓ Teste de subtração passou");
    }
    
    @Teste(descricao = "Deve falhar propositalmente")
    public void testeFalha() {
        throw new AssertionError("Este teste sempre falha");
    }
    
    @Ignorar(motivo = "Teste ainda não implementado")
    @Teste(descricao = "Teste ignorado")
    public void testeIgnorado() {
        System.out.println("Este teste não deve ser executado");
    }
    
    @DepoisDeCA
    public void limpar() {
        System.out.println("  [Teardown] Depois de cada teste");
    }
    
    @DepoisDeTodos
    public static void limparTodos() {
        System.out.println("  [Teardown Global] Limpando todos os testes");
    }
}

/**
 * Executor de testes
 */
class ExecutorTestes {
    
    public void executarTestes(Class<?> classeTexto) {
        try {
            Object instancia = classeTexto.getDeclaredConstructor().newInstance();
            
            // Executar @AntesDeTodos
            executarMetodosEstaticos(classeTexto, AntesDeTodos.class);
            
            // Encontrar todos os métodos de teste
            List<Method> metodosTeste = new ArrayList<>();
            for (Method metodo : classeTexto.getDeclaredMethods()) {
                if (metodo.isAnnotationPresent(Teste.class)) {
                    metodosTeste.add(metodo);
                }
            }
            
            int passou = 0, falhou = 0, ignorado = 0;
            
            // Executar cada teste
            for (Method metodoTeste : metodosTeste) {
                Teste teste = metodoTeste.getAnnotation(Teste.class);
                String descricao = teste.descricao().isEmpty() ? 
                                  metodoTeste.getName() : teste.descricao();
                
                // Verificar se deve ser ignorado
                if (metodoTeste.isAnnotationPresent(Ignorar.class)) {
                    Ignorar ign = metodoTeste.getAnnotation(Ignorar.class);
                    System.out.println("⊘ IGNORADO: " + descricao);
                    System.out.println("  Motivo: " + ign.motivo());
                    ignorado++;
                    continue;
                }
                
                System.out.println("\nExecutando: " + descricao);
                
                try {
                    // Executar @AntesDeCA
                    executarMetodos(instancia, AntesDeCA.class);
                    
                    // Executar teste
                    metodoTeste.invoke(instancia);
                    passou++;
                    
                } catch (Exception e) {
                    System.out.println("  ✗ FALHOU: " + e.getCause().getMessage());
                    falhou++;
                    
                } finally {
                    // Executar @DepoisDeCA
                    executarMetodos(instancia, DepoisDeCA.class);
                }
            }
            
            // Executar @DepoisDeTodos
            executarMetodosEstaticos(classeTexto, DepoisDeTodos.class);
            
            // Resumo
            System.out.println("\n--- RESUMO ---");
            System.out.println("Total: " + metodosTeste.size());
            System.out.println("Passou: " + passou);
            System.out.println("Falhou: " + falhou);
            System.out.println("Ignorado: " + ignorado);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void executarMetodos(Object instancia, Class<? extends Annotation> annotationType) 
            throws Exception {
        for (Method metodo : instancia.getClass().getDeclaredMethods()) {
            if (metodo.isAnnotationPresent(annotationType)) {
                metodo.invoke(instancia);
            }
        }
    }
    
    private void executarMetodosEstaticos(Class<?> classe, Class<? extends Annotation> annotationType) 
            throws Exception {
        for (Method metodo : classe.getDeclaredMethods()) {
            if (metodo.isAnnotationPresent(annotationType) && 
                Modifier.isStatic(metodo.getModifiers())) {
                metodo.invoke(null);
            }
        }
    }
}

// ===== SISTEMA DE CACHE =====

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@interface Cacheavel {
    int ttl() default 3600; // Time to live em segundos
    String chave() default "";
}

class ServicoComCache {
    
    @Cacheavel(ttl = 300, chave = "dados")
    public String buscarDados(int id) {
        System.out.println("  → Executando método buscarDados (não cacheado)");
        return "Dados do ID: " + id;
    }
    
    public String buscarSemCache(int id) {
        return "Dados sem cache: " + id;
    }
}

/**
 * Proxy que implementa cache baseado em annotations
 */
class ProxyCache {
    private final Object alvo;
    private final Map<String, CacheEntry> cache = new ConcurrentHashMap<>();
    
    public ProxyCache(Object alvo) {
        this.alvo = alvo;
    }
    
    public String buscarDados(int id) {
        try {
            Method metodo = alvo.getClass().getMethod("buscarDados", int.class);
            
            if (metodo.isAnnotationPresent(Cacheavel.class)) {
                String chaveCache = "buscarDados-" + id;
                
                // Verificar cache
                CacheEntry entrada = cache.get(chaveCache);
                if (entrada != null && !entrada.expirou()) {
                    System.out.println("  ← Retornando do cache");
                    return (String) entrada.valor;
                }
                
                // Executar método e cachear resultado
                Object resultado = metodo.invoke(alvo, id);
                
                Cacheavel ann = metodo.getAnnotation(Cacheavel.class);
                cache.put(chaveCache, new CacheEntry(resultado, ann.ttl()));
                
                return (String) resultado;
            }
            
            return (String) metodo.invoke(alvo, id);
            
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    public void limparCache() {
        cache.clear();
    }
    
    private static class CacheEntry {
        final Object valor;
        final long expiracao;
        
        CacheEntry(Object valor, int ttlSegundos) {
            this.valor = valor;
            this.expiracao = System.currentTimeMillis() + (ttlSegundos * 1000L);
        }
        
        boolean expirou() {
            return System.currentTimeMillis() > expiracao;
        }
    }
}

// ===== SISTEMA DE AUDITORIA =====

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@interface Auditado {
    String nivel() default "INFO";
    boolean registrarParametros() default false;
}

class ServicoAuditado {
    
    @Auditado(nivel = "CRITICAL", registrarParametros = true)
    public void operacaoCritica(String dados) {
        System.out.println("  Executando operação crítica com: " + dados);
    }
    
    @Auditado(nivel = "INFO")
    public void operacaoNormal(String dados) {
        System.out.println("  Executando operação normal com: " + dados);
    }
}

class ProxyAuditoria {
    private final Object alvo;
    
    public ProxyAuditoria(Object alvo) {
        this.alvo = alvo;
    }
    
    public void operacaoCritica(String dados) {
        executarComAuditoria("operacaoCritica", new Object[]{dados});
    }
    
    public void operacaoNormal(String dados) {
        executarComAuditoria("operacaoNormal", new Object[]{dados});
    }
    
    private void executarComAuditoria(String nomeMetodo, Object[] args) {
        try {
            Method metodo = alvo.getClass().getMethod(nomeMetodo, String.class);
            
            if (metodo.isAnnotationPresent(Auditado.class)) {
                Auditado audit = metodo.getAnnotation(Auditado.class);
                
                System.out.println("[AUDIT-" + audit.nivel() + "] Chamada: " + nomeMetodo);
                
                if (audit.registrarParametros()) {
                    System.out.println("[AUDIT] Parâmetros: " + Arrays.toString(args));
                }
                
                long inicio = System.currentTimeMillis();
                metodo.invoke(alvo, args);
                long duracao = System.currentTimeMillis() - inicio;
                
                System.out.println("[AUDIT] Duração: " + duracao + "ms");
            }
            
        } catch (Exception e) {
            System.out.println("[AUDIT-ERROR] Erro: " + e.getMessage());
        }
    }
}

// ===== AGENDAMENTO DE TAREFAS =====

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@interface Agendado {
    String cron();
    String descricao() default "";
    boolean ativo() default true;
}

class TarefasAgendadas {
    
    @Agendado(cron = "0 0 2 * * ?", descricao = "Backup diário às 2h da manhã")
    public void backupDiario() {
        System.out.println("Executando backup...");
    }
    
    @Agendado(cron = "0 */15 * * * ?", descricao = "Sincronização a cada 15 minutos")
    public void sincronizacaoPeriodica() {
        System.out.println("Sincronizando dados...");
    }
    
    @Agendado(cron = "0 0 0 1 * ?", descricao = "Limpeza mensal", ativo = false)
    public void limpezaMensal() {
        System.out.println("Limpando dados antigos...");
    }
}

class AgendadorTarefas {
    private Map<String, TarefaInfo> tarefas = new HashMap<>();
    
    public void registrarTarefas(Class<?> classe) {
        for (Method metodo : classe.getDeclaredMethods()) {
            if (metodo.isAnnotationPresent(Agendado.class)) {
                Agendado agendado = metodo.getAnnotation(Agendado.class);
                
                TarefaInfo info = new TarefaInfo(
                    metodo.getName(),
                    agendado.cron(),
                    agendado.descricao(),
                    agendado.ativo()
                );
                
                tarefas.put(metodo.getName(), info);
                System.out.println("Tarefa registrada: " + metodo.getName());
            }
        }
    }
    
    public void listarTarefas() {
        System.out.println("\nTarefas agendadas:");
        tarefas.values().forEach(tarefa -> {
            String status = tarefa.ativo ? "ATIVO" : "INATIVO";
            System.out.println("  [" + status + "] " + tarefa.nome);
            System.out.println("    Cron: " + tarefa.cron);
            System.out.println("    Descrição: " + tarefa.descricao);
        });
    }
    
    private static class TarefaInfo {
        final String nome;
        final String cron;
        final String descricao;
        final boolean ativo;
        
        TarefaInfo(String nome, String cron, String descricao, boolean ativo) {
            this.nome = nome;
            this.cron = cron;
            this.descricao = descricao;
            this.ativo = ativo;
        }
    }
}

// ===== VALIDAÇÃO DE BEANS =====

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@interface NaoVazio {
    String mensagem() default "Campo não pode estar vazio";
}

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@interface TamanhoMinimo {
    int valor();
    String mensagem() default "Tamanho mínimo não atingido";
}

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@interface EmailValido {
    String mensagem() default "Email inválido";
}

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@interface IdadeMinima {
    int valor();
    String mensagem() default "Idade mínima não atingida";
}

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@interface Verdadeiro {
    String mensagem() default "Campo deve ser verdadeiro";
}

class FormularioRegistro {
    @NaoVazio(mensagem = "Nome é obrigatório")
    @TamanhoMinimo(valor = 3, mensagem = "Nome deve ter no mínimo 3 caracteres")
    private String nome;
    
    @NaoVazio(mensagem = "Email é obrigatório")
    @EmailValido(mensagem = "Formato de email inválido")
    private String email;
    
    @IdadeMinima(valor = 18, mensagem = "Idade mínima é 18 anos")
    private Integer idade;
    
    @NaoVazio(mensagem = "Senha é obrigatória")
    @TamanhoMinimo(valor = 8, mensagem = "Senha deve ter no mínimo 8 caracteres")
    private String senha;
    
    @NaoVazio(mensagem = "Confirmação de senha é obrigatória")
    private String confirmacaoSenha;
    
    @Verdadeiro(mensagem = "Você deve aceitar os termos de uso")
    private Boolean aceitaTermos;
    
    // Getters e Setters
    public void setNome(String nome) { this.nome = nome; }
    public void setEmail(String email) { this.email = email; }
    public void setIdade(Integer idade) { this.idade = idade; }
    public void setSenha(String senha) { this.senha = senha; }
    public void setConfirmacaoSenha(String confirmacaoSenha) { this.confirmacaoSenha = confirmacaoSenha; }
    public void setAceitaTermos(Boolean aceitaTermos) { this.aceitaTermos = aceitaTermos; }
    public String getSenha() { return senha; }
    public String getConfirmacaoSenha() { return confirmacaoSenha; }
}

class ValidadorBeans {
    
    public static List<String> validar(Object objeto) {
        List<String> erros = new ArrayList<>();
        
        try {
            for (Field campo : objeto.getClass().getDeclaredFields()) {
                campo.setAccessible(true);
                Object valor = campo.get(objeto);
                
                // @NaoVazio
                if (campo.isAnnotationPresent(NaoVazio.class)) {
                    if (valor == null || valor.toString().trim().isEmpty()) {
                        erros.add(campo.getAnnotation(NaoVazio.class).mensagem());
                    }
                }
                
                // @TamanhoMinimo
                if (campo.isAnnotationPresent(TamanhoMinimo.class) && valor != null) {
                    TamanhoMinimo ann = campo.getAnnotation(TamanhoMinimo.class);
                    if (valor.toString().length() < ann.valor()) {
                        erros.add(ann.mensagem());
                    }
                }
                
                // @EmailValido
                if (campo.isAnnotationPresent(EmailValido.class) && valor != null) {
                    if (!valor.toString().matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
                        erros.add(campo.getAnnotation(EmailValido.class).mensagem());
                    }
                }
                
                // @IdadeMinima
                if (campo.isAnnotationPresent(IdadeMinima.class) && valor != null) {
                    IdadeMinima ann = campo.getAnnotation(IdadeMinima.class);
                    if (((Number) valor).intValue() < ann.valor()) {
                        erros.add(ann.mensagem());
                    }
                }
                
                // @Verdadeiro
                if (campo.isAnnotationPresent(Verdadeiro.class)) {
                    if (valor == null || !((Boolean) valor)) {
                        erros.add(campo.getAnnotation(Verdadeiro.class).mensagem());
                    }
                }
            }
            
            // Validação customizada: senhas devem ser iguais
            if (objeto instanceof FormularioRegistro) {
                FormularioRegistro form = (FormularioRegistro) objeto;
                if (form.getSenha() != null && form.getConfirmacaoSenha() != null) {
                    if (!form.getSenha().equals(form.getConfirmacaoSenha())) {
                        erros.add("Senha e confirmação de senha não coincidem");
                    }
                }
            }
            
        } catch (Exception e) {
            erros.add("Erro ao validar: " + e.getMessage());
        }
        
        if (erros.isEmpty()) {
            erros.add("✓ Validação bem-sucedida!");
        }
        
        return erros;
    }
}

// Correção do nome da annotation
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@interface AntesDeCA {
}

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@interface DepoisDeCA {
}
