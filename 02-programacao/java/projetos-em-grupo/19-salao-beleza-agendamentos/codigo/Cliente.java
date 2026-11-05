/**
 * Cliente — classe ABSTRATA (pilar: ABSTRAÇÃO).
 * É a base comum de ClienteComum e ClienteFidelidade: guarda o que os dois têm em comum.
 * Não existe "um Cliente genérico" — por isso não se faz `new Cliente(...)`.
 */
public abstract class Cliente {
    // ENCAPSULAMENTO: atributos privados; só leitura por getters (não há setters).
    private final String nome;
    private final String telefone;

    protected Cliente(String nome, String telefone) {
        // PRÉ-CONDIÇÕES: nenhum objeto nasce com dado inválido
        if (nome == null || nome.isBlank()) throw new IllegalArgumentException("nome é obrigatório");
        if (telefone == null || telefone.isBlank()) throw new IllegalArgumentException("telefone é obrigatório");
        this.nome = nome;
        this.telefone = telefone;
    }

    public String getNome() { return nome; }
    public String getTelefone() { return telefone; }

    /**
     * POLIMORFISMO: a mesma pergunta, resposta diferente por tipo.
     * Significado: desconto (0.10 = 10%).
     */
    public abstract double calcularDesconto();
}
