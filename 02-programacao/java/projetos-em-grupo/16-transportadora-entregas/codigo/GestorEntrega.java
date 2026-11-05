import java.util.ArrayList;
import java.util.List;

/**
 * GestorEntrega — coordena o processo (abrir, avançar, marcarDevolvida).
 * SRP (SOLID): só cuida do CICLO DE VIDA de cada Entrega; as regras de cada objeto ficam nele mesmo.
 * DIP (SOLID): recebe um Notificador (interface) pelo construtor — não sabe qual canal é.
 */
public class GestorEntrega {
    private final List<Entrega> registros = new ArrayList<>();
    private final Notificador notificador;

    public GestorEntrega(Notificador notificador) {
        this.notificador = notificador;
    }

    public Entrega abrir(Encomenda encomenda, double valorFrete) {
        Entrega nova = new Entrega(encomenda, valorFrete);   // se o valor for inválido, a Entrega recusa (exceção)
        registros.add(nova);
        notificador.notificar("Novo registro de Entrega na etapa: " + nova.getEstado());
        return nova;
    }

    public void avancar(Entrega entrega) {
        entrega.avancar();   // MENSAGEM: o próprio objeto valida se pode avançar
        notificador.notificar("Entrega agora está: " + entrega.getEstado());
    }

    public void marcarDevolvida(Entrega entrega) {
        entrega.marcarDevolvida();
        notificador.notificar("Entrega agora está: " + entrega.getEstado());
    }

    public List<Entrega> listar() { return List.copyOf(registros); }
}
