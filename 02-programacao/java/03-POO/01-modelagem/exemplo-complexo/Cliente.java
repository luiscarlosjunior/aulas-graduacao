/**
 * ENTIDADE: Cliente (classe base)
 *
 * Modela quem faz o pedido. O atributo é 'protected' para que a subclasse
 * ClienteVip possa reutilizá-lo (herança). O método calcularDesconto() define
 * o comportamento PADRÃO (sem desconto) que a subclasse vai sobrescrever.
 */
public class Cliente {
    protected String nome;

    public Cliente(String nome) {
        this.nome = nome;
    }

    /**
     * Cliente comum não tem desconto: retorna o valor cheio.
     * ClienteVip vai SOBRESCREVER este método (polimorfismo).
     */
    public double calcularDesconto(double valor) {
        return valor;
    }

    public String getNome() {
        return nome;
    }
}
