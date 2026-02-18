/**
 * ConcreteDecorator - Adiciona chantilly à bebida
 */
public class ComChantilly extends BebidaDecorator {
    
    public ComChantilly(Bebida bebida) {
        super(bebida);
    }
    
    @Override
    public String getDescricao() {
        return bebida.getDescricao() + ", Chantilly";
    }
    
    @Override
    public double getCusto() {
        return bebida.getCusto() + 1.50;
    }
}
