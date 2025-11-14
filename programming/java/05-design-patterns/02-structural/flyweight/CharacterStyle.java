/**
 * Interface Flyweight - Define operações que podem receber estado extrínseco
 * 
 * Esta interface representa o padrão Flyweight para estilos de caracteres.
 * Objetos que implementam esta interface armazenam estado INTRÍNSECO
 * (compartilhado entre múltiplos caracteres) e podem operar usando
 * estado EXTRÍNSECO (único para cada caractere) passado como parâmetro.
 */
public interface CharacterStyle {
    
    /**
     * Renderiza um caractere com este estilo em uma posição específica
     * 
     * IMPORTANTE: Note que 'caractere' e 'posicao' são estado EXTRÍNSECO
     * (variam de instância para instância) e são passados como parâmetros.
     * 
     * O estilo em si (fonte, tamanho, cor) é estado INTRÍNSECO
     * (compartilhado) e está armazenado no objeto Flyweight.
     * 
     * @param caractere O caractere a ser renderizado (estado extrínseco)
     * @param posicao A posição do caractere no documento (estado extrínseco)
     */
    void render(char caractere, int posicao);
    
    /**
     * Retorna o nome da fonte deste estilo
     * @return Nome da fonte
     */
    String getFont();
    
    /**
     * Retorna o tamanho da fonte em pontos
     * @return Tamanho da fonte
     */
    int getSize();
    
    /**
     * Retorna a cor da fonte em formato hexadecimal
     * @return Cor da fonte (ex: "#000000" para preto)
     */
    String getColor();
    
    /**
     * Verifica se o texto está em negrito
     * @return true se negrito, false caso contrário
     */
    boolean isBold();
    
    /**
     * Verifica se o texto está em itálico
     * @return true se itálico, false caso contrário
     */
    boolean isItalic();
    
    /**
     * Retorna uma representação string do estilo
     * Útil para debugging e demonstração
     * @return String descrevendo o estilo
     */
    String getStyleDescription();
}
