import java.util.ArrayList;
import java.util.List;

/*
 * Livro — o título do acervo. COMPÕE seus Exemplares (◆): cada exemplar é uma
 * cópia que só existe dentro deste livro. Se o livro sai do acervo, os
 * exemplares somem junto — por isso o Livro é quem CRIA o Exemplar (o `new`).
 */
public class Livro {
    private final String titulo;
    private final String autor;
    private final String isbn;
    private final List<Exemplar> exemplares = new ArrayList<>();   // COMPOSIÇÃO ◆

    public Livro(String titulo, String autor, String isbn) {
        if (titulo == null || titulo.isBlank())
            throw new IllegalArgumentException("Título é obrigatório.");
        this.titulo = titulo;
        this.autor = autor;
        this.isbn = isbn;
    }

    /** A parte NASCE dentro do todo (composição). */
    public Exemplar adicionarExemplar(String codigo) {
        Exemplar e = new Exemplar(codigo);
        exemplares.add(e);
        return e;
    }

    /** Devolve o primeiro exemplar livre, ou null se não houver. */
    public Exemplar primeiroDisponivel() {
        for (Exemplar e : exemplares)
            if (e.isDisponivel()) return e;
        return null;
    }

    public String getTitulo() { return titulo; }
    public String getAutor()  { return autor; }
    public List<Exemplar> getExemplares() { return List.copyOf(exemplares); }
}
