import java.util.ArrayList;
import java.util.List;

/*
 * Categoria — AGREGA livros (◇). A categoria só AGRUPA livros que já existem no
 * acervo por conta própria; se a categoria for removida, os livros continuam.
 * Por isso ela RECEBE o livro pronto (não faz `new Livro`).
 */
public class Categoria {
    private final String nome;
    private final List<Livro> livros = new ArrayList<>();          // AGREGAÇÃO ◇

    public Categoria(String nome) {
        if (nome == null || nome.isBlank())
            throw new IllegalArgumentException("Nome da categoria é obrigatório.");
        this.nome = nome;
    }

    /** Recebe uma referência a um Livro que já existe (não cria nada). */
    public void adicionarLivro(Livro livro) {
        if (livro != null && !livros.contains(livro)) livros.add(livro);
    }

    public String getNome() { return nome; }
    public List<Livro> getLivros() { return List.copyOf(livros); }
}
