import java.util.ArrayList;
import java.util.List;

/**
 * GestorConsulta — coordena o processo (abrir, avançar, cancelar).
 * SRP (SOLID): só cuida do CICLO DE VIDA de cada Consulta; as regras de cada objeto ficam nele mesmo.
 * DIP (SOLID): recebe um Notificador (interface) pelo construtor — não sabe qual canal é.
 */
public class GestorConsulta {
    private final List<Consulta> registros = new ArrayList<>();
    private final Notificador notificador;

    public GestorConsulta(Notificador notificador) {
        this.notificador = notificador;
    }

    public Consulta abrir(Paciente paciente, double valor) {
        Consulta nova = new Consulta(paciente, valor);   // se o valor for inválido, a Consulta recusa (exceção)
        registros.add(nova);
        notificador.notificar("Novo registro de Consulta na etapa: " + nova.getEstado());
        return nova;
    }

    public void avancar(Consulta consulta) {
        consulta.avancar();   // MENSAGEM: o próprio objeto valida se pode avançar
        notificador.notificar("Consulta agora está: " + consulta.getEstado());
    }

    public void cancelar(Consulta consulta) {
        consulta.cancelar();
        notificador.notificar("Consulta agora está: " + consulta.getEstado());
    }

    public List<Consulta> listar() { return List.copyOf(registros); }
}
