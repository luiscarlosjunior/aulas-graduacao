/**
 * Cliente — classe ABSTRATA (pilar: ABSTRAÇÃO).
 * É a base comum de ClienteAvulso e ClienteMensalista: guarda o que os dois têm em comum.
 * Não existe "um Cliente genérico" — por isso não se faz `new Cliente(...)`.
 */
public abstract class Cliente {
    // ENCAPSULAMENTO: atributos privados; só leitura por getters (não há setters).
    private final String nome;
    private final String placa;

    protected Cliente(String nome, String placa) {
        // PRÉ-CONDIÇÕES: nenhum objeto nasce com dado inválido
        if (nome == null || nome.isBlank()) throw new IllegalArgumentException("nome é obrigatório");
        if (placa == null || placa.isBlank()) throw new IllegalArgumentException("placa é obrigatório");
        this.nome = nome;
        this.placa = placa;
    }

    public String getNome() { return nome; }
    public String getPlaca() { return placa; }

    /**
     * POLIMORFISMO: a mesma pergunta, resposta diferente por tipo.
     * Significado: preço por hora em R$ (0 = mensalista).
     */
    public abstract double calcularValor();
}
