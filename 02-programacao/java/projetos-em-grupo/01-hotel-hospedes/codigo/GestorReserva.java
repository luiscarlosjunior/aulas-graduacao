import java.util.ArrayList;
import java.util.List;

/**
 * GestorReserva — coordena o processo (abrir, avançar, cancelar).
 * SRP (SOLID): só cuida do CICLO DE VIDA de cada Reserva; as regras de cada objeto ficam nele mesmo.
 * DIP (SOLID): recebe um Notificador (interface) pelo construtor — não sabe qual canal é.
 */
public class GestorReserva {
    private final List<Reserva> registros = new ArrayList<>();
    private final Notificador notificador;

    public GestorReserva(Notificador notificador) {
        this.notificador = notificador;
    }

    public Reserva abrir(Hospede hospede, double valorDiaria) {
        Reserva nova = new Reserva(hospede, valorDiaria);   // se o valor for inválido, a Reserva recusa (exceção)
        registros.add(nova);
        notificador.notificar("Novo registro de Reserva na etapa: " + nova.getEstado());
        return nova;
    }

    public void avancar(Reserva reserva) {
        reserva.avancar();   // MENSAGEM: o próprio objeto valida se pode avançar
        notificador.notificar("Reserva agora está: " + reserva.getEstado());
    }

    public void cancelar(Reserva reserva) {
        reserva.cancelar();
        notificador.notificar("Reserva agora está: " + reserva.getEstado());
    }

    public List<Reserva> listar() { return List.copyOf(registros); }
}
