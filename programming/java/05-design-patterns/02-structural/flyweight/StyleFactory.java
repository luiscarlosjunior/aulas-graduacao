import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Flyweight Factory - Gerencia o pool de objetos Flyweight
 * 
 * RESPONSABILIDADES:
 * 1. Criar novos flyweights quando necessário
 * 2. Armazenar flyweights em um pool (cache)
 * 3. Retornar flyweights existentes quando possível
 * 4. Garantir que flyweights idênticos sejam compartilhados
 * 
 * Esta é a classe MAIS IMPORTANTE do padrão Flyweight!
 * Sem ela, não há compartilhamento e o padrão não funciona.
 * 
 * THREAD-SAFETY: Usa ConcurrentHashMap para ser thread-safe
 */
public class StyleFactory {
    
    // Pool de estilos compartilhados
    // Key: Identificador único baseado nos atributos
    // Value: Objeto CharacterStyle compartilhado
    private final Map<String, CharacterStyle> stylePool;
    
    // Contador para estatísticas
    private int totalStylesCreated = 0;
    private int totalStyleRequests = 0;
    
    /**
     * Construtor - Inicializa o pool vazio
     * Usa ConcurrentHashMap para thread-safety
     */
    public StyleFactory() {
        this.stylePool = new ConcurrentHashMap<>();
    }
    
    /**
     * Retorna um estilo com as características especificadas
     * 
     * LÓGICA PRINCIPAL DO FLYWEIGHT:
     * 1. Gera chave única baseada nos parâmetros
     * 2. Verifica se estilo com essa chave já existe no pool
     * 3. Se existe: retorna estilo existente (COMPARTILHAMENTO!)
     * 4. Se não existe: cria novo, adiciona ao pool, retorna
     * 
     * Esta é a essência do padrão: garantir que objetos idênticos
     * sejam a MESMA INSTÂNCIA em memória.
     * 
     * @param font Nome da fonte
     * @param size Tamanho em pontos
     * @param color Cor em hexadecimal
     * @param bold Se é negrito
     * @param italic Se é itálico
     * @return Estilo compartilhado (flyweight)
     */
    public CharacterStyle getStyle(String font, int size, String color, boolean bold, boolean italic) {
        totalStyleRequests++;
        
        // Gera chave única para este conjunto de atributos
        String key = generateKey(font, size, color, bold, italic);
        
        // computeIfAbsent é thread-safe e atômico
        // Só cria novo estilo se não existir
        return stylePool.computeIfAbsent(key, k -> {
            totalStylesCreated++;
            System.out.println("🆕 Criando novo estilo: " + key);
            return new ConcreteCharacterStyle(font, size, color, bold, italic);
        });
    }
    
    /**
     * Versão simplificada sem negrito/itálico
     * Usa valores padrão (false) para ambos
     */
    public CharacterStyle getStyle(String font, int size, String color) {
        return getStyle(font, size, color, false, false);
    }
    
    /**
     * Gera chave única para um conjunto de atributos
     * 
     * A chave é usada como identificador no HashMap
     * Estilos com mesmos atributos devem gerar mesma chave
     * 
     * @return String única representando a combinação de atributos
     */
    private String generateKey(String font, int size, String color, boolean bold, boolean italic) {
        return String.format("%s-%d-%s-%s-%s", font, size, color, bold, italic);
    }
    
    /**
     * Retorna número de estilos únicos no pool
     * Útil para estatísticas e demonstração
     * 
     * @return Quantidade de estilos diferentes criados
     */
    public int getPoolSize() {
        return stylePool.size();
    }
    
    /**
     * Retorna todos os estilos do pool
     * Útil para debugging e demonstração
     * 
     * @return Map com todos os estilos
     */
    public Map<String, CharacterStyle> getAllStyles() {
        return new HashMap<>(stylePool);
    }
    
    /**
     * Retorna total de estilos criados desde o início
     * @return Número de vezes que um novo estilo foi criado
     */
    public int getTotalStylesCreated() {
        return totalStylesCreated;
    }
    
    /**
     * Retorna total de requisições de estilo
     * @return Número de vezes que getStyle() foi chamado
     */
    public int getTotalStyleRequests() {
        return totalStyleRequests;
    }
    
    /**
     * Calcula taxa de reuso (hit rate do cache)
     * 
     * Taxa alta (próxima de 100%) indica que o Flyweight
     * está sendo efetivo em compartilhar objetos
     * 
     * @return Porcentagem de requisições que reutilizaram estilos existentes
     */
    public double getReuseRate() {
        if (totalStyleRequests == 0) return 0.0;
        int reuses = totalStyleRequests - totalStylesCreated;
        return (reuses * 100.0) / totalStyleRequests;
    }
    
    /**
     * Limpa o pool de estilos
     * Útil para testes ou quando deseja liberar memória
     */
    public void clear() {
        stylePool.clear();
        totalStylesCreated = 0;
        totalStyleRequests = 0;
    }
    
    /**
     * Imprime estatísticas do pool
     * Útil para debugging e demonstração da eficiência do Flyweight
     */
    public void printStatistics() {
        System.out.println("\n=== Estatísticas da StyleFactory ===");
        System.out.println("Estilos únicos criados: " + totalStylesCreated);
        System.out.println("Total de requisições: " + totalStyleRequests);
        System.out.println("Taxa de reuso: " + String.format("%.2f%%", getReuseRate()));
        System.out.println("Estilos no pool: " + stylePool.size());
        
        if (totalStyleRequests > 0) {
            int avgCharsPerStyle = totalStyleRequests / Math.max(1, totalStylesCreated);
            System.out.println("Média de caracteres por estilo: " + avgCharsPerStyle);
        }
    }
    
    /**
     * Calcula memória estimada usada pelo pool
     * 
     * @return Bytes aproximados usados pelos estilos no pool
     */
    public int getEstimatedPoolMemory() {
        int total = 0;
        for (CharacterStyle style : stylePool.values()) {
            if (style instanceof ConcreteCharacterStyle) {
                total += ((ConcreteCharacterStyle) style).getEstimatedMemorySize();
            }
        }
        return total;
    }
    
    /**
     * Imprime todos os estilos no pool
     * Útil para demonstração
     */
    public void printAllStyles() {
        System.out.println("\n=== Estilos no Pool ===");
        int index = 1;
        for (Map.Entry<String, CharacterStyle> entry : stylePool.entrySet()) {
            System.out.printf("Estilo #%d: %s%n", index++, entry.getValue().getStyleDescription());
        }
    }
}
