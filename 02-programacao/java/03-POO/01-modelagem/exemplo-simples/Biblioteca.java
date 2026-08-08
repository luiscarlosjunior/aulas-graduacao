import java.util.ArrayList;
import java.util.List;

/**
 * ENTIDADE: Biblioteca
 *
 * Relacionamento: a Biblioteca É DONA do acervo (composição).
 * Os livros são guardados numa lista interna e a biblioteca controla
 * o cadastro e a consulta. Ela é a responsável por "listar disponíveis".
 */
public class Biblioteca {
    private String nome;
    private List<Livro> acervo;

    public Biblioteca(String nome) {
        this.nome = nome;
        this.acervo = new ArrayList<>(); // a biblioteca cria e mantém seu próprio acervo
    }

    public void cadastrar(Livro livro) {
        acervo.add(livro);
        System.out.println("Cadastrado no acervo: " + livro);
    }

    public void listarDisponiveis() {
        System.out.println("\n=== Livros disponíveis em " + nome + " ===");
        for (Livro livro : acervo) {
            if (livro.isDisponivel()) {
                System.out.println("  - " + livro);
            }
        }
    }
}
