/**
 * Decorator abstrato - Base para todos os decoradores
 * 
 * Implementa a interface Bebida e contém referência para uma Bebida
 * Esta classe é abstrata pois serve apenas como base
 */
public abstract class BebidaDecorator implements Bebida {
    // Referência para o componente sendo decorado
    protected Bebida bebida;
    
    /**
     * Construtor que recebe bebida a ser decorada
     * @param bebida Bebida base ou já decorada
     */
    public BebidaDecorator(Bebida bebida) {
        this.bebida = bebida;
    }
    
    /**
     * Implementação padrão delega para componente decorado
     * Subclasses podem sobrescrever para adicionar comportamento
     */
    @Override
    public String getDescricao() {
        return bebida.getDescricao();
    }
    
    @Override
    public double getCusto() {
        return bebida.getCusto();
    }
}
