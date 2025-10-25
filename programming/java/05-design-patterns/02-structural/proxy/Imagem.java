/**
 * Interface Subject - Define contrato para imagens
 * Tanto ImagemReal quanto ImagemProxy implementam esta interface
 */
public interface Imagem {
    /**
     * Exibe a imagem
     */
    void exibir();
    
    /**
     * Retorna informações sobre a imagem
     */
    String getInfo();
}
