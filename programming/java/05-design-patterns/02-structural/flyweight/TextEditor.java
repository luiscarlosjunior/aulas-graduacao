import java.util.ArrayList;
import java.util.List;

/**
 * Text Editor - Contexto do cliente que usa Flyweights
 * 
 * Representa um editor de texto simplificado que gerencia
 * um documento usando o padrão Flyweight para economizar memória.
 * 
 * DEMONSTRAÇÃO:
 * - Mantém lista de caracteres
 * - Cada caractere referencia um estilo compartilhado (flyweight)
 * - Adiciona texto com diferentes estilos
 * - Calcula economia de memória
 */
public class TextEditor {
    
    private final List<Character> document;
    private final StyleFactory styleFactory;
    private String documentName;
    
    /**
     * Construtor
     * @param documentName Nome do documento
     */
    public TextEditor(String documentName) {
        this.documentName = documentName;
        this.document = new ArrayList<>();
        this.styleFactory = new StyleFactory();
    }
    
    /**
     * Adiciona texto ao documento com estilo específico
     * 
     * IMPORTANTE: Este método usa a StyleFactory para obter estilos.
     * Se o estilo já existe, reutiliza (FLYWEIGHT!).
     * Se não existe, a factory cria um novo.
     * 
     * @param text Texto a adicionar
     * @param font Fonte do texto
     * @param size Tamanho da fonte
     * @param color Cor do texto
     */
    public void addText(String text, String font, int size, String color) {
        addText(text, font, size, color, false, false);
    }
    
    /**
     * Adiciona texto ao documento com estilo completo
     * 
     * @param text Texto a adicionar
     * @param font Fonte do texto
     * @param size Tamanho da fonte
     * @param color Cor do texto
     * @param bold Se é negrito
     * @param italic Se é itálico
     */
    public void addText(String text, String font, int size, String color, boolean bold, boolean italic) {
        // Obtém estilo da factory (pode ser novo ou reutilizado)
        CharacterStyle style = styleFactory.getStyle(font, size, color, bold, italic);
        
        // Cria um Character para cada letra do texto
        int startPosition = document.size();
        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            Character character = new Character(ch, startPosition + i, style);
            document.add(character);
        }
    }
    
    /**
     * Renderiza todo o documento
     * Em um editor real, isso desenharia na tela
     */
    public void render() {
        System.out.println("\n--- Renderizando Documento: " + documentName + " ---");
        for (Character ch : document) {
            ch.render();
        }
    }
    
    /**
     * Renderiza apenas uma parte do documento
     * @param start Posição inicial
     * @param end Posição final (exclusiva)
     */
    public void renderRange(int start, int end) {
        System.out.println("\n--- Renderizando posições " + start + " a " + end + " ---");
        for (int i = start; i < Math.min(end, document.size()); i++) {
            document.get(i).render();
        }
    }
    
    /**
     * Retorna o texto do documento
     * @return String com todo o texto
     */
    public String getText() {
        StringBuilder sb = new StringBuilder();
        for (Character ch : document) {
            sb.append(ch.getCaractere());
        }
        return sb.toString();
    }
    
    /**
     * Retorna número total de caracteres
     * @return Tamanho do documento
     */
    public int getCharacterCount() {
        return document.size();
    }
    
    /**
     * Retorna número de estilos únicos usados
     * @return Quantidade de estilos diferentes
     */
    public int getUniqueStyleCount() {
        return styleFactory.getPoolSize();
    }
    
    /**
     * Calcula memória estimada SEM usar Flyweight
     * Assume que cada caractere teria seu próprio objeto de estilo
     * 
     * @return Bytes que seriam usados sem Flyweight
     */
    public long estimateMemoryWithoutFlyweight() {
        // Cada Character: ~26 bytes
        // Cada Style completo: ~100 bytes (estimativa)
        // Total por caractere: ~126 bytes
        
        long characterMemory = document.size() * 26L;
        long styleMemoryPerChar = document.size() * 100L; // Cada char teria seu próprio estilo
        return characterMemory + styleMemoryPerChar;
    }
    
    /**
     * Calcula memória estimada COM Flyweight
     * Estilos são compartilhados, então contamos apenas uma vez cada
     * 
     * @return Bytes usados com Flyweight
     */
    public long estimateMemoryWithFlyweight() {
        // Caracteres: cada um ~26 bytes (inclui referência ao style)
        long characterMemory = document.size() * 26L;
        
        // Estilos: apenas os únicos no pool
        long styleMemory = styleFactory.getEstimatedPoolMemory();
        
        return characterMemory + styleMemory;
    }
    
    /**
     * Calcula porcentagem de economia de memória
     * @return Porcentagem economizada (0-100)
     */
    public double calculateMemorySavings() {
        long without = estimateMemoryWithoutFlyweight();
        long with = estimateMemoryWithFlyweight();
        
        if (without == 0) return 0.0;
        
        long saved = without - with;
        return (saved * 100.0) / without;
    }
    
    /**
     * Imprime estatísticas detalhadas sobre uso de memória
     * Demonstra a economia proporcionada pelo Flyweight
     */
    public void printMemoryStatistics() {
        System.out.println("\n=== Estatísticas de Memória ===");
        System.out.println("Total de caracteres: " + getCharacterCount());
        System.out.println("Estilos únicos criados: " + getUniqueStyleCount());
        
        if (getUniqueStyleCount() > 0) {
            int avgCharsPerStyle = getCharacterCount() / getUniqueStyleCount();
            System.out.println("Taxa de compartilhamento: " + avgCharsPerStyle + " caracteres por estilo");
        }
        
        long memWithout = estimateMemoryWithoutFlyweight();
        long memWith = estimateMemoryWithFlyweight();
        double savings = calculateMemorySavings();
        
        System.out.println("\nSe cada caractere tivesse seu próprio estilo:");
        System.out.printf("  Memória estimada: %,d bytes (~%d KB)%n", 
                         memWithout, memWithout / 1024);
        
        System.out.println("\nCom Flyweight Pattern:");
        System.out.printf("  Memória de estilos: %,d bytes (~%d KB)%n", 
                         styleFactory.getEstimatedPoolMemory(),
                         styleFactory.getEstimatedPoolMemory() / 1024);
        System.out.printf("  Memória de caracteres: %,d bytes%n", 
                         getCharacterCount() * 26L);
        System.out.printf("  Total: %,d bytes (~%d KB)%n", 
                         memWith, memWith / 1024);
        System.out.printf("  Economia: %.1f%%%n", savings);
        
        // Imprime estatísticas da factory
        styleFactory.printStatistics();
    }
    
    /**
     * Limpa o documento
     */
    public void clear() {
        document.clear();
    }
    
    /**
     * Retorna a factory de estilos (para demonstração/testes)
     * @return StyleFactory usada por este editor
     */
    public StyleFactory getStyleFactory() {
        return styleFactory;
    }
}
