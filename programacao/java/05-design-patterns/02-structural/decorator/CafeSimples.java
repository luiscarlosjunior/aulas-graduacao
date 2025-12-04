/**
 * ConcreteComponent - Componente base (café simples)
 * Implementação básica que pode ser decorada
 */
public class CafeSimples implements Bebida {
    
    @Override
    public String getDescricao() {
        return "Café Expresso";
    }
    
    @Override
    public double getCusto() {
        return 5.00;
    }
}
