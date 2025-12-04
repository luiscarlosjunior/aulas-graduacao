/**
 * Programa Principal - Demonstração de Annotations em Java
 * 
 * Este programa executa todos os exemplos de annotations demonstrando:
 * - Annotations predefinidas (built-in)
 * - Annotations customizadas
 * - Processamento via reflection
 * - Casos de uso práticos
 * 
 * @author Aulas Graduação
 */
public class ExemploAnnotations {
    
    public static void main(String[] args) {
        System.out.println("╔═══════════════════════════════════════════════════════════════╗");
        System.out.println("║           ANNOTATIONS EM JAVA - EXEMPLOS COMPLETOS            ║");
        System.out.println("╚═══════════════════════════════════════════════════════════════╝");
        System.out.println();
        
        exibirMenu();
        
        System.out.println("\n" + "═".repeat(65));
        System.out.println("Para ver exemplos específicos, execute:");
        System.out.println("  - ExemploAnnotationsBuiltIn");
        System.out.println("  - ExemploAnnotationsCustomizadas");
        System.out.println("  - ExemploReflectionAnnotations");
        System.out.println("  - ExemploCasosDeUsoPraticos");
        System.out.println("═".repeat(65));
    }
    
    private static void exibirMenu() {
        System.out.println("📚 O QUE SÃO ANNOTATIONS?");
        System.out.println("─".repeat(65));
        System.out.println("Annotations (anotações) são metadados que fornecem informações sobre");
        System.out.println("o programa, mas não fazem parte da lógica do programa em si.");
        System.out.println();
        
        System.out.println("🎯 PRINCIPAIS CARACTERÍSTICAS:");
        System.out.println("─".repeat(65));
        System.out.println("✓ Metadados declarativos no código-fonte");
        System.out.println("✓ Processamento em tempo de compilação ou execução");
        System.out.println("✓ Type-safe (verificadas pelo compilador)");
        System.out.println("✓ Base de frameworks modernos (Spring, Hibernate, JUnit)");
        System.out.println();
        
        System.out.println("📋 EXEMPLOS DISPONÍVEIS:");
        System.out.println("─".repeat(65));
        System.out.println();
        
        System.out.println("1️⃣  ANNOTATIONS BUILT-IN (Predefinidas)");
        System.out.println("   Demonstra annotations fornecidas pelo Java:");
        System.out.println("   • @Override - Garantir sobrescrita correta");
        System.out.println("   • @Deprecated - Marcar elementos obsoletos");
        System.out.println("   • @SuppressWarnings - Suprimir warnings específicos");
        System.out.println("   • @FunctionalInterface - Interfaces funcionais");
        System.out.println("   • @SafeVarargs - Varargs seguros com generics");
        System.out.println();
        
        System.out.println("2️⃣  ANNOTATIONS CUSTOMIZADAS");
        System.out.println("   Demonstra criação de annotations personalizadas:");
        System.out.println("   • Annotations de validação (@NaoPodeSerNull, @TamanhoTexto)");
        System.out.println("   • Annotations de configuração (@Configuracao, @Cacheavel)");
        System.out.println("   • Annotations de documentação (@DocumentacaoAPI)");
        System.out.println("   • Annotations repetíveis (@Repeatable)");
        System.out.println("   • Meta-annotations (@Target, @Retention, @Inherited)");
        System.out.println();
        
        System.out.println("3️⃣  REFLECTION E ANNOTATIONS");
        System.out.println("   Demonstra processamento via reflection:");
        System.out.println("   • Inspeção básica de annotations");
        System.out.println("   • Framework de validação");
        System.out.println("   • Injeção de dependências simples");
        System.out.println("   • Serialização customizada (JSON)");
        System.out.println("   • Processamento avançado de métodos e parâmetros");
        System.out.println();
        
        System.out.println("4️⃣  CASOS DE USO PRÁTICOS");
        System.out.println("   Demonstra aplicações reais:");
        System.out.println("   • Framework de testes (similar ao JUnit)");
        System.out.println("   • Sistema de cache");
        System.out.println("   • Sistema de auditoria");
        System.out.println("   • Agendamento de tarefas");
        System.out.println("   • Validação de beans");
        System.out.println();
        
        System.out.println("💡 CONCEITOS FUNDAMENTAIS:");
        System.out.println("─".repeat(65));
        System.out.println();
        
        System.out.println("📌 RETENTION POLICIES (Políticas de Retenção):");
        System.out.println("   • SOURCE - Descartada pelo compilador");
        System.out.println("   • CLASS - Mantida no bytecode, não em runtime");
        System.out.println("   • RUNTIME - Disponível via reflection em runtime");
        System.out.println();
        
        System.out.println("🎯 TARGETS (Alvos de Aplicação):");
        System.out.println("   • TYPE - Classes, interfaces, enums");
        System.out.println("   • FIELD - Campos de classe");
        System.out.println("   • METHOD - Métodos");
        System.out.println("   • PARAMETER - Parâmetros de métodos");
        System.out.println("   • CONSTRUCTOR - Construtores");
        System.out.println("   • LOCAL_VARIABLE - Variáveis locais");
        System.out.println("   • ANNOTATION_TYPE - Outras annotations");
        System.out.println("   • PACKAGE - Pacotes");
        System.out.println();
        
        System.out.println("🏭 ONDE ANNOTATIONS SÃO USADAS:");
        System.out.println("─".repeat(65));
        System.out.println("• Spring Framework - @Autowired, @Component, @RestController");
        System.out.println("• JPA/Hibernate - @Entity, @Table, @Column, @OneToMany");
        System.out.println("• Bean Validation - @NotNull, @Size, @Email, @Pattern");
        System.out.println("• JUnit 5 - @Test, @BeforeEach, @ParameterizedTest");
        System.out.println("• JAX-RS - @Path, @GET, @POST, @Produces, @Consumes");
        System.out.println();
        
        System.out.println("✨ BENEFÍCIOS DAS ANNOTATIONS:");
        System.out.println("─".repeat(65));
        System.out.println("✓ Reduzem código boilerplate");
        System.out.println("✓ Melhoram legibilidade do código");
        System.out.println("✓ Facilitam configuração declarativa");
        System.out.println("✓ Permitem validações em tempo de compilação");
        System.out.println("✓ Habilitam metaprogramação poderosa");
        System.out.println("✓ Suportam geração automática de código");
        System.out.println();
        
        System.out.println("📚 MELHORES PRÁTICAS:");
        System.out.println("─".repeat(65));
        System.out.println("1. Escolha a retention policy apropriada");
        System.out.println("2. Seja específico com @Target");
        System.out.println("3. Forneça valores padrão sensatos");
        System.out.println("4. Documente extensivamente suas annotations");
        System.out.println("5. Valide combinações de annotations");
        System.out.println("6. Considere composição de annotations");
        System.out.println("7. Cache resultados de reflection para performance");
        System.out.println();
    }
}
