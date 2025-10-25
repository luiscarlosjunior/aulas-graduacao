/**
 * Interface Observer
 * 
 * Define o contrato que todos os observadores devem seguir.
 * Implementadores desta interface serão notificados quando o Subject mudar.
 * 
 * Este é um exemplo do Push Model: o Subject envia os dados necessários
 * diretamente para o Observer.
 */
public interface Observer {
    /**
     * Método chamado quando o Subject notifica mudanças
     * 
     * @param productName Nome do produto que mudou
     * @param price Novo preço do produto
     * @param stock Quantidade em estoque
     */
    void update(String productName, double price, int stock);
}
