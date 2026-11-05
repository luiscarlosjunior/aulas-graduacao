import java.util.ArrayList;
import java.util.List;

/**
 * GestorTicket — coordena o processo (abrir, avançar, listar).
 * SRP (SOLID): só cuida do CICLO DE VIDA de cada Ticket; as regras de cada objeto ficam nele mesmo.
 * DIP (SOLID): recebe um Notificador (interface) pelo construtor — não sabe qual canal é.
 */
public class GestorTicket {
    private final List<Ticket> registros = new ArrayList<>();
    private final Notificador notificador;

    public GestorTicket(Notificador notificador) {
        this.notificador = notificador;
    }

    public Ticket abrir(Cliente cliente, double valor) {
        Ticket nova = new Ticket(cliente, valor);   // se o valor for inválido, a Ticket recusa (exceção)
        registros.add(nova);
        notificador.notificar("Novo registro de Ticket na etapa: " + nova.getEstado());
        return nova;
    }

    public void avancar(Ticket ticket) {
        ticket.avancar();   // MENSAGEM: o próprio objeto valida se pode avançar
        notificador.notificar("Ticket agora está: " + ticket.getEstado());
    }

    public List<Ticket> listar() { return List.copyOf(registros); }
}
