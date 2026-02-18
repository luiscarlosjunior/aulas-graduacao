/**
 * Cliente do Flyweight - Representa um caractere individual no documento
 * 
 * Esta classe mantém:
 * - ESTADO EXTRÍNSECO: caractere e posição (únicos para cada instância)
 * - REFERÊNCIA ao Flyweight: estilo compartilhado
 * 
 * Note que NÃO duplica os dados de estilo (fonte, tamanho, cor).
 * Em vez disso, mantém apenas uma REFERÊNCIA ao objeto de estilo compartilhado.
 * 
 * Isso economiza memória significativamente quando há milhares de caracteres.
 */
public class Character {
    
    // ============================================================
    // ESTADO EXTRÍNSECO - Único para cada instância
    // ============================================================
    private final char caractere;  // O caractere em si (a, b, c, etc.)
    private final int posicao;      // Posição no documento
    
    // ============================================================
    // REFERÊNCIA AO FLYWEIGHT - Compartilhado entre múltiplos caracteres
    // ============================================================
    private final CharacterStyle estilo; // Referência ao estilo compartilhado
    
    /**
     * Construtor
     * 
     * @param caractere O caractere (estado extrínseco - único)
     * @param posicao Posição no documento (estado extrínseco - único)
     * @param estilo Estilo compartilhado (referência ao flyweight)
     */
    public Character(char caractere, int posicao, CharacterStyle estilo) {
        this.caractere = caractere;
        this.posicao = posicao;
        this.estilo = estilo;
    }
    
    /**
     * Renderiza este caractere
     * 
     * Delega para o flyweight, passando o estado extrínseco
     * Demonstra como flyweight usa estado intrínseco (dele) 
     * + estado extrínseco (passado como parâmetro)
     */
    public void render() {
        estilo.render(caractere, posicao);
    }
    
    /**
     * Getters para estado extrínseco
     */
    public char getCaractere() {
        return caractere;
    }
    
    public int getPosicao() {
        return posicao;
    }
    
    /**
     * Getter para o estilo (flyweight)
     */
    public CharacterStyle getEstilo() {
        return estilo;
    }
    
    /**
     * Calcula tamanho estimado em memória desta instância
     * 
     * IMPORTANTE: Note que NÃO conta o tamanho do estilo,
     * pois o estilo é COMPARTILHADO. Conta apenas:
     * - char (2 bytes)
     * - int posição (4 bytes)
     * - referência ao estilo (8 bytes em JVM 64-bit)
     * - overhead de objeto (~12 bytes)
     * 
     * @return Bytes usados por esta instância (sem contar estilo compartilhado)
     */
    public int getEstimatedMemorySize() {
        int charBytes = 2;           // char em Java
        int positionBytes = 4;       // int
        int styleRefBytes = 8;       // referência 64-bit
        int objectOverhead = 12;     // overhead padrão de objeto
        
        return charBytes + positionBytes + styleRefBytes + objectOverhead;
        // Total: ~26 bytes por caractere (vs 80-100 bytes sem flyweight!)
    }
    
    @Override
    public String toString() {
        return String.format("Char['%c' @ pos=%d, style=%s]", 
                           caractere, posicao, estilo.getStyleDescription());
    }
}
