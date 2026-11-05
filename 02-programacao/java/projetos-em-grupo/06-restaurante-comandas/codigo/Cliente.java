/**
 * Cliente — classe ABSTRATA (pilar: ABSTRAÇÃO).
 * É a base comum de ClienteComum e ClienteRodizio: guarda o que os dois têm em comum.
 * Não existe "um Cliente genérico" — por isso não se faz `new Cliente(...)`.
 */
public abstract class Cliente {
    // ENCAPSULAMENTO: atributos privados; só leitura por getters (não há setters).
    private final String nome;
    private final String documento;

    protected Cliente(String nome, String documento) {
        // PRÉ-CONDIÇÕES: nenhum objeto nasce com dado inválido
        if (nome == null || nome.isBlank()) throw new IllegalArgumentException("nome é obrigatório");
        if (documento == null || documento.isBlank()) throw new IllegalArgumentException("documento é obrigatório");
        this.nome = nome;
        this.documento = documento;
    }

    public String getNome() { return nome; }
    public String getDocumento() { return documento; }

    /**
     * POLIMORFISMO: a mesma pergunta, resposta diferente por tipo.
     * Significado: valor fixo em R$ (0 = paga a soma dos pedidos).
     */
    public abstract double calcularConta();
}
