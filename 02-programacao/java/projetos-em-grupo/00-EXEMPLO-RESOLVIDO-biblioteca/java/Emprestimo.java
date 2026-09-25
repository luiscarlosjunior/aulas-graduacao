import java.time.LocalDate;

/*
 * Emprestimo — liga um Membro a um Exemplar (ASSOCIAÇÃO com os dois).
 * É aqui que as MENSAGENS acontecem: o empréstimo PEDE ao exemplar que se marque
 * como emprestado e PERGUNTA ao membro qual é o prazo dele (chamada polimórfica).
 */
public class Emprestimo {
    private final Membro membro;              // associação -->
    private final Exemplar exemplar;          // associação -->
    private final LocalDate dataRetirada;
    private final LocalDate dataDevolucaoPrevista;
    private boolean devolvido;

    public Emprestimo(Membro membro, Exemplar exemplar) {
        if (membro == null || exemplar == null)
            throw new IllegalArgumentException("Empréstimo precisa de membro e exemplar.");
        this.membro = membro;
        this.exemplar = exemplar;
        exemplar.emprestar();                 // MENSAGEM: valida a pré-condição/invariante lá dentro
        this.dataRetirada = LocalDate.now();
        // MENSAGEM polimórfica: cada tipo de membro devolve um prazo diferente
        this.dataDevolucaoPrevista = dataRetirada.plusDays(membro.prazoDeEmprestimoDias());
        this.devolvido = false;
    }

    /** Devolve: manda o exemplar ficar disponível de novo (mensagem). */
    public void devolver() {
        if (devolvido)
            throw new IllegalStateException("Empréstimo já foi devolvido.");
        exemplar.devolver();                  // MENSAGEM
        devolvido = true;
    }

    public boolean isDevolvido() { return devolvido; }
    public Membro getMembro() { return membro; }
    public Exemplar getExemplar() { return exemplar; }
    public LocalDate getDataDevolucaoPrevista() { return dataDevolucaoPrevista; }
}
