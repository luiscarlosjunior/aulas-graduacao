/**
 * ENTIDADE: Livro
 *
 * Modelagem: um substantivo importante do enunciado ("livro") virou classe.
 * O próprio livro é quem SABE se está disponível — essa é a responsabilidade dele.
 * Ninguém de fora mexe no campo 'disponivel' direto (ele é private).
 */
public class Livro {
    // ATRIBUTOS: o que o enunciado disse que o livro "tem"
    private String titulo;
    private String autor;
    private boolean disponivel;

    public Livro(String titulo, String autor) {
        this.titulo = titulo;
        this.autor = autor;
        this.disponivel = true; // todo livro nasce disponível no acervo
    }

    // COMPORTAMENTOS: o que o livro "faz". A regra mora junto do dado.
    public void emprestar() {
        if (disponivel) {
            disponivel = false;
        } else {
            System.out.println("  [!] '" + titulo + "' já está emprestado.");
        }
    }

    public void devolver() {
        disponivel = true;
    }

    // Getters: leitura controlada do estado
    public boolean isDisponivel() {
        return disponivel;
    }

    public String getTitulo() {
        return titulo;
    }

    @Override
    public String toString() {
        return "'" + titulo + "' (" + autor + ")";
    }
}
