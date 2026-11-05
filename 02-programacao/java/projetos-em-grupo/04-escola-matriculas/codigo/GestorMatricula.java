import java.util.ArrayList;
import java.util.List;

/**
 * GestorMatricula — coordena o processo (abrir, avançar, cancelar).
 * SRP (SOLID): só cuida do CICLO DE VIDA de cada Matricula; as regras de cada objeto ficam nele mesmo.
 * DIP (SOLID): recebe um Notificador (interface) pelo construtor — não sabe qual canal é.
 */
public class GestorMatricula {
    private final List<Matricula> registros = new ArrayList<>();
    private final Notificador notificador;

    public GestorMatricula(Notificador notificador) {
        this.notificador = notificador;
    }

    public Matricula abrir(Aluno aluno, double valorMensalidade) {
        Matricula nova = new Matricula(aluno, valorMensalidade);   // se o valor for inválido, a Matricula recusa (exceção)
        registros.add(nova);
        notificador.notificar("Novo registro de Matricula na etapa: " + nova.getEstado());
        return nova;
    }

    public void avancar(Matricula matricula) {
        matricula.avancar();   // MENSAGEM: o próprio objeto valida se pode avançar
        notificador.notificar("Matricula agora está: " + matricula.getEstado());
    }

    public void cancelar(Matricula matricula) {
        matricula.cancelar();
        notificador.notificar("Matricula agora está: " + matricula.getEstado());
    }

    public List<Matricula> listar() { return List.copyOf(registros); }
}
