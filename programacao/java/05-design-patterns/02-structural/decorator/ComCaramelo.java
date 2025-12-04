/**
 * ConcreteDecorator - Adiciona caramelo à bebida
 */
public class ComCaramelo extends BebidaDecorator {
    
    public ComCaramelo(Bebida bebida) {
        super(bebida);
    }
    
    @Override
    public String getDescricao() {
        return bebida.getDescricao() + ", Caramelo";
    }
    
    @Override
    public double getCusto() {
        return bebida.getCusto() + 2.00;
    }
}
