/**
 * Cliente — classe ABSTRATA (pilar: ABSTRAÇÃO).
 * É a base comum de ClienteComum e ClienteAssinante: guarda o que os dois têm em comum.
 * Não existe "um Cliente genérico" — por isso não se faz `new Cliente(...)`.
 */
public abstract class Cliente {
    // ENCAPSULAMENTO: atributos privados; só leitura por getters (não há setters).
    private final String nome;
    private final String endereco;

    protected Cliente(String nome, String endereco) {
        // PRÉ-CONDIÇÕES: nenhum objeto nasce com dado inválido
        if (nome == null || nome.isBlank()) throw new IllegalArgumentException("nome é obrigatório");
        if (endereco == null || endereco.isBlank()) throw new IllegalArgumentException("endereco é obrigatório");
        this.nome = nome;
        this.endereco = endereco;
    }

    public String getNome() { return nome; }
    public String getEndereco() { return endereco; }

    /**
     * POLIMORFISMO: a mesma pergunta, resposta diferente por tipo.
     * Significado: taxa de entrega em R$.
     */
    public abstract double taxaEntrega();
}
