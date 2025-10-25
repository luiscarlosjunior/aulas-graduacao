/**
 * ConcreteDecorator - Adiciona chocolate à bebida
 * Demonstra como diferentes decoradores funcionam independentemente
 */
public class ComChocolate extends BebidaDecorator {
    
    public ComChocolate(Bebida bebida) {
        super(bebida);
    }
    
    @Override
    public String getDescricao() {
        return bebida.getDescricao() + ", Chocolate";
    }
    
    @Override
    public double getCusto() {
        return bebida.getCusto() + 2.00;
    }
}
