import java.util.ArrayList;
import java.util.List;

/**
 * Convenio — o GRUPO da agregação: junta pacientes que existem sozinhos.
 */
public class Convenio {
    private final String nome;

    public Convenio(String nome) {
        if (nome == null || nome.isBlank()) throw new IllegalArgumentException("nome é obrigatório");
        this.nome = nome;
    }

    public String getNome() { return nome; }

    // AGREGAÇÃO (◇): só AGRUPA pacientes que já existem por conta própria.
    private final List<Paciente> pacientes = new ArrayList<>();

    /** Recebe um objeto PRONTO (não faz `new`) — isso é agregação. */
    public void adicionarPaciente(Paciente item) {
        if (item == null) throw new IllegalArgumentException("Paciente é obrigatório");
        if (!pacientes.contains(item)) pacientes.add(item);
    }

    public List<Paciente> getPacientes() { return List.copyOf(pacientes); }
}
