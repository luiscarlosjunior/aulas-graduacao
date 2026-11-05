/**
 * Imovel — classe ABSTRATA (pilar: ABSTRAÇÃO).
 * É a base comum de ImovelResidencial e ImovelComercial: guarda o que os dois têm em comum.
 * Não existe "um Imovel genérico" — por isso não se faz `new Imovel(...)`.
 */
public abstract class Imovel {
    // ENCAPSULAMENTO: atributos privados; só leitura por getters (não há setters).
    private final String endereco;
    private final double valorBase;

    protected Imovel(String endereco, double valorBase) {
        // PRÉ-CONDIÇÕES: nenhum objeto nasce com dado inválido
        if (endereco == null || endereco.isBlank()) throw new IllegalArgumentException("endereco é obrigatório");
        if (valorBase < 0) throw new IllegalArgumentException("valorBase não pode ser negativo");
        this.endereco = endereco;
        this.valorBase = valorBase;
    }

    public String getEndereco() { return endereco; }
    public double getValorBase() { return valorBase; }

    /**
     * POLIMORFISMO: a mesma pergunta, resposta diferente por tipo.
     * Significado: reajuste anual (0.04 = 4%).
     */
    public abstract double calcularReajuste();
}
