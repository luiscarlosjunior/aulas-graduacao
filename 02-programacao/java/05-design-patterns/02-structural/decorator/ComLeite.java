/**
 * ConcreteDecorator - Adiciona leite à bebida
 * Estende comportamento adicionando descrição e custo do leite
 */
public class ComLeite extends BebidaDecorator {
    
    public ComLeite(Bebida bebida) {
        super(bebida);
    }
    
    /**
     * Adiciona ", Leite" à descrição da bebida base
     */
    @Override
    public String getDescricao() {
        return bebida.getDescricao() + ", Leite";
    }
    
    /**
     * Adiciona custo do leite ao custo da bebida base
     */
    @Override
    public double getCusto() {
        return bebida.getCusto() + 1.50;
    }
}
