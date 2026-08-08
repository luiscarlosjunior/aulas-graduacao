/**
 * HERANÇA ("é um"): ClienteVip É UM Cliente, com uma regra diferente de desconto.
 *
 * Passou no teste "é um"? Sim: um cliente VIP é um tipo especializado de cliente.
 * Por isso usamos herança (extends), e não composição.
 */
public class ClienteVip extends Cliente {

    public ClienteVip(String nome) {
        super(nome); // reaproveita o construtor da classe base
    }

    /**
     * Polimorfismo: mesmo método, comportamento diferente.
     * O VIP ganha 10% de desconto.
     */
    @Override
    public double calcularDesconto(double valor) {
        return valor * 0.90;
    }
}
