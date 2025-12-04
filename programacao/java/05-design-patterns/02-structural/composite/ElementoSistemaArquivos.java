/**
 * Interface Component - Define operações comuns para arquivos e pastas
 * Permite tratar arquivos e pastas uniformemente
 */
public interface ElementoSistemaArquivos {
    /**
     * Retorna o nome do elemento
     */
    String getNome();
    
    /**
     * Retorna o tamanho em KB
     * Para arquivos: tamanho real
     * Para pastas: soma dos tamanhos dos filhos
     */
    int getTamanho();
    
    /**
     * Exibe estrutura do elemento
     * @param indentacao Nível de indentação para hierarquia visual
     */
    void exibir(String indentacao);
}
