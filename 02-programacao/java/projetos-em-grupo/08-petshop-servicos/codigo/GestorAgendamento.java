import java.util.ArrayList;
import java.util.List;

/**
 * GestorAgendamento — coordena o processo (abrir, avançar, cancelar).
 * SRP (SOLID): só cuida do CICLO DE VIDA de cada Agendamento; as regras de cada objeto ficam nele mesmo.
 * DIP (SOLID): recebe um Notificador (interface) pelo construtor — não sabe qual canal é.
 */
public class GestorAgendamento {
    private final List<Agendamento> registros = new ArrayList<>();
    private final Notificador notificador;

    public GestorAgendamento(Notificador notificador) {
        this.notificador = notificador;
    }

    public Agendamento abrir(Cliente cliente, double valorTotal) {
        Agendamento nova = new Agendamento(cliente, valorTotal);   // se o valor for inválido, a Agendamento recusa (exceção)
        registros.add(nova);
        notificador.notificar("Novo registro de Agendamento na etapa: " + nova.getEstado());
        return nova;
    }

    public void avancar(Agendamento agendamento) {
        agendamento.avancar();   // MENSAGEM: o próprio objeto valida se pode avançar
        notificador.notificar("Agendamento agora está: " + agendamento.getEstado());
    }

    public void cancelar(Agendamento agendamento) {
        agendamento.cancelar();
        notificador.notificar("Agendamento agora está: " + agendamento.getEstado());
    }

    public List<Agendamento> listar() { return List.copyOf(registros); }
}
