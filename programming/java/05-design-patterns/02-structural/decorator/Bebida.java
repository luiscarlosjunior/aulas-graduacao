/**
 * Interface Component - Define contrato para bebidas
 * Tanto componente concreto quanto decoradores implementam esta interface
 */
public interface Bebida {
    /**
     * Retorna descrição da bebida
     * @return Descrição completa com todos os ingredientes
     */
    String getDescricao();
    
    /**
     * Retorna custo total da bebida
     * @return Custo em Reais (R$)
     */
    double getCusto();
}
