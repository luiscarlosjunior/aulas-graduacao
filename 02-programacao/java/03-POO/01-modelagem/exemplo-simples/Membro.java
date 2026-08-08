/**
 * ENTIDADE: Membro
 *
 * Relacionamento: o Membro USA livros (associação). Ele não é dono do acervo —
 * apenas pega emprestado e devolve. Por isso o Livro é passado como parâmetro
 * do método, e não guardado como "posse" permanente da biblioteca.
 */
public class Membro {
    private String nome;

    public Membro(String nome) {
        this.nome = nome;
    }

    public void pegarEmprestado(Livro livro) {
        if (livro.isDisponivel()) {
            livro.emprestar();
            System.out.println(nome + " pegou emprestado " + livro);
        } else {
            System.out.println(nome + " não pôde pegar " + livro + " (indisponível).");
        }
    }

    public void devolver(Livro livro) {
        livro.devolver();
        System.out.println(nome + " devolveu " + livro);
    }

    public String getNome() {
        return nome;
    }
}
