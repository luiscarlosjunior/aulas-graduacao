import java.util.ArrayList;
import java.util.List;

/**
 * Turma — o GRUPO da agregação: junta alunos que existem sozinhos.
 */
public class Turma {
    private final String nome;

    public Turma(String nome) {
        if (nome == null || nome.isBlank()) throw new IllegalArgumentException("nome é obrigatório");
        this.nome = nome;
    }

    public String getNome() { return nome; }

    // AGREGAÇÃO (◇): só AGRUPA alunos que já existem por conta própria.
    private final List<Aluno> alunos = new ArrayList<>();

    /** Recebe um objeto PRONTO (não faz `new`) — isso é agregação. */
    public void adicionarAluno(Aluno item) {
        if (item == null) throw new IllegalArgumentException("Aluno é obrigatório");
        if (!alunos.contains(item)) alunos.add(item);
    }

    public List<Aluno> getAlunos() { return List.copyOf(alunos); }
}
