/**
 * Interface Target - Define o contrato esperado pelo sistema de pagamento
 * Esta é a interface que o cliente espera usar
 */
public interface ProcessadorPagamento {
    /**
     * Processa um pagamento com o valor especificado
     * @param valor Valor a ser processado em Reais (R$)
     * @return true se pagamento foi processado com sucesso, false caso contrário
     */
    boolean processar(double valor);
    
    /**
     * Retorna o nome do processador de pagamento
     * @return Nome do processador
     */
    String getNomeProcessador();
}
