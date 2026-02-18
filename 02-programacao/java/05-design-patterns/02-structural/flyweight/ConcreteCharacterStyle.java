/**
 * Concrete Flyweight - Implementação concreta do Flyweight
 * 
 * Esta classe armazena ESTADO INTRÍNSECO (compartilhado entre objetos):
 * - Fonte (font)
 * - Tamanho (size)
 * - Cor (color)
 * - Negrito (bold)
 * - Itálico (italic)
 * 
 * IMPORTANTE: Estes dados são IMUTÁVEIS (todos final) para permitir
 * compartilhamento seguro entre múltiplos clientes, inclusive em
 * ambientes multi-thread.
 * 
 * Objetos desta classe podem ser compartilhados por centenas ou milhares
 * de caracteres, economizando significativamente memória.
 */
public class ConcreteCharacterStyle implements CharacterStyle {
    
    // ============================================================
    // ESTADO INTRÍNSECO - Compartilhado entre múltiplos caracteres
    // Todos os campos são FINAL (imutáveis)
    // ============================================================
    
    private final String font;
    private final int size;
    private final String color;
    private final boolean bold;
    private final boolean italic;
    
    /**
     * Construtor - Inicializa o estado intrínseco
     * 
     * Uma vez criado, este objeto NÃO PODE SER MODIFICADO.
     * Isso garante que pode ser compartilhado com segurança.
     * 
     * @param font Nome da fonte (ex: "Arial", "Times New Roman")
     * @param size Tamanho da fonte em pontos (ex: 12, 14, 16)
     * @param color Cor em hexadecimal (ex: "#000000", "#FF0000")
     * @param bold Se o texto é negrito
     * @param italic Se o texto é itálico
     */
    public ConcreteCharacterStyle(String font, int size, String color, boolean bold, boolean italic) {
        this.font = font;
        this.size = size;
        this.color = color;
        this.bold = bold;
        this.italic = italic;
    }
    
    /**
     * Renderiza caractere com este estilo
     * 
     * Demonstra como estado INTRÍNSECO (armazenado no objeto)
     * é combinado com estado EXTRÍNSECO (passado como parâmetro)
     * 
     * Em um editor real, isso enviaria dados para GPU ou
     * sistema de renderização gráfica.
     */
    @Override
    public void render(char caractere, int posicao) {
        System.out.printf("Renderizando '%c' em posição %d com estilo: %s%n", 
                         caractere, posicao, getStyleDescription());
    }
    
    @Override
    public String getFont() {
        return font;
    }
    
    @Override
    public int getSize() {
        return size;
    }
    
    @Override
    public String getColor() {
        return color;
    }
    
    @Override
    public boolean isBold() {
        return bold;
    }
    
    @Override
    public boolean isItalic() {
        return italic;
    }
    
    @Override
    public String getStyleDescription() {
        return String.format("[%s, %dpt, %s, negrito=%s, itálico=%s]", 
                           font, size, color, bold, italic);
    }
    
    /**
     * Override hashCode para uso em HashMap da Factory
     * 
     * Dois estilos com mesmos atributos devem ter mesmo hash
     * Isso permite que a Factory identifique estilos duplicados
     */
    @Override
    public int hashCode() {
        int result = font.hashCode();
        result = 31 * result + size;
        result = 31 * result + color.hashCode();
        result = 31 * result + (bold ? 1 : 0);
        result = 31 * result + (italic ? 1 : 0);
        return result;
    }
    
    /**
     * Override equals para comparação na Factory
     * 
     * Dois estilos são iguais se todos seus atributos forem iguais
     * Isso é essencial para o funcionamento correto do pool
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        
        ConcreteCharacterStyle other = (ConcreteCharacterStyle) obj;
        return size == other.size &&
               bold == other.bold &&
               italic == other.italic &&
               font.equals(other.font) &&
               color.equals(other.color);
    }
    
    /**
     * Retorna representação string do objeto
     * Útil para debugging
     */
    @Override
    public String toString() {
        return "Style" + getStyleDescription();
    }
    
    /**
     * Método para estimar tamanho em memória
     * Útil para demonstração de economia de memória
     * 
     * @return Estimativa de bytes usados por este objeto
     */
    public int getEstimatedMemorySize() {
        // String overhead: aproximadamente 38 bytes por String em Java
        // int: 4 bytes
        // boolean: 1 byte
        // Overhead de objeto: aproximadamente 12 bytes
        
        int fontBytes = 38 + (font.length() * 2); // UTF-16
        int colorBytes = 38 + (color.length() * 2);
        int sizeBytes = 4;
        int boldBytes = 1;
        int italicBytes = 1;
        int objectOverhead = 12;
        
        return fontBytes + colorBytes + sizeBytes + boldBytes + italicBytes + objectOverhead;
    }
}
