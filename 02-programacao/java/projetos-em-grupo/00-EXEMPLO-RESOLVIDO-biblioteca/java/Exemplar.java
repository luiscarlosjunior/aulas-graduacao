/*
 * Exemplar — uma CÓPIA física de um livro. Encapsula o estado `disponivel`.
 * INVARIANTE: um exemplar emprestado nunca está disponível (e vice-versa).
 */
public class Exemplar {
    private final String codigo;
    private boolean disponivel;

    public Exemplar(String codigo) {
        if (codigo == null || codigo.isBlank())
            throw new IllegalArgumentException("Código do exemplar é obrigatório.");
        this.codigo = codigo;
        this.disponivel = true;          // nasce disponível (invariante inicial válido)
    }

    public String getCodigo() { return codigo; }
    public boolean isDisponivel() { return disponivel; }

    /**
     * PRÉ-CONDIÇÃO: o exemplar precisa estar disponível.
     * PÓS-CONDIÇÃO: fica indisponível.
     * (garante o INVARIANTE: nunca dois empréstimos ativos do mesmo exemplar)
     */
    public void emprestar() {
        if (!disponivel)
            throw new IllegalStateException("Exemplar já emprestado: " + codigo);
        disponivel = false;
    }

    /** PRÉ: precisa estar emprestado. PÓS: volta a ficar disponível. */
    public void devolver() {
        if (disponivel)
            throw new IllegalStateException("Exemplar não está emprestado: " + codigo);
        disponivel = true;
    }
}
