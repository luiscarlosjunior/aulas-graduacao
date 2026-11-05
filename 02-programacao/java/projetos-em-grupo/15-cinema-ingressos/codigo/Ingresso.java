/**
 * Ingresso — classe ABSTRATA (pilar: ABSTRAÇÃO).
 * É a base comum de IngressoInteira e IngressoMeia: guarda o que os dois têm em comum.
 * Não existe "um Ingresso genérico" — por isso não se faz `new Ingresso(...)`.
 */
public abstract class Ingresso {
    // ENCAPSULAMENTO: atributos privados; só leitura por getters (não há setters).
    private final String assento;
    private final double preco;

    protected Ingresso(String assento, double preco) {
        // PRÉ-CONDIÇÕES: nenhum objeto nasce com dado inválido
        if (assento == null || assento.isBlank()) throw new IllegalArgumentException("assento é obrigatório");
        if (preco < 0) throw new IllegalArgumentException("preco não pode ser negativo");
        this.assento = assento;
        this.preco = preco;
    }

    public String getAssento() { return assento; }
    public double getPreco() { return preco; }

    /**
     * POLIMORFISMO: a mesma pergunta, resposta diferente por tipo.
     * Significado: preço do ingresso em R$.
     */
    public abstract double calcularPreco();
}
