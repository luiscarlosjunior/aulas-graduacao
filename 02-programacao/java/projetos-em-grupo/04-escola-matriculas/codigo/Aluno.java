/**
 * Aluno — classe ABSTRATA (pilar: ABSTRAÇÃO).
 * É a base comum de AlunoRegular e AlunoBolsista: guarda o que os dois têm em comum.
 * Não existe "um Aluno genérico" — por isso não se faz `new Aluno(...)`.
 */
public abstract class Aluno {
    // ENCAPSULAMENTO: atributos privados; só leitura por getters (não há setters).
    private final String nome;
    private final String matriculaId;

    protected Aluno(String nome, String matriculaId) {
        // PRÉ-CONDIÇÕES: nenhum objeto nasce com dado inválido
        if (nome == null || nome.isBlank()) throw new IllegalArgumentException("nome é obrigatório");
        if (matriculaId == null || matriculaId.isBlank()) throw new IllegalArgumentException("matriculaId é obrigatório");
        this.nome = nome;
        this.matriculaId = matriculaId;
    }

    public String getNome() { return nome; }
    public String getMatriculaId() { return matriculaId; }

    /**
     * POLIMORFISMO: a mesma pergunta, resposta diferente por tipo.
     * Significado: mensalidade em R$.
     */
    public abstract double mensalidade();
}
