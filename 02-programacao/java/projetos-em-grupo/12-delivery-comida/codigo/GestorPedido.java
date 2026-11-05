import java.util.ArrayList;
import java.util.List;

/**
 * GestorPedido — coordena o processo (abrir, avançar, cancelar).
 * SRP (SOLID): só cuida do CICLO DE VIDA de cada Pedido; as regras de cada objeto ficam nele mesmo.
 * DIP (SOLID): recebe um Notificador (interface) pelo construtor — não sabe qual canal é.
 */
public class GestorPedido {
    private final List<Pedido> registros = new ArrayList<>();
    private final Notificador notificador;

    public GestorPedido(Notificador notificador) {
        this.notificador = notificador;
    }

    public Pedido abrir(Cliente cliente, double valorTotal) {
        Pedido nova = new Pedido(cliente, valorTotal);   // se o valor for inválido, a Pedido recusa (exceção)
        registros.add(nova);
        notificador.notificar("Novo registro de Pedido na etapa: " + nova.getEstado());
        return nova;
    }

    public void avancar(Pedido pedido) {
        pedido.avancar();   // MENSAGEM: o próprio objeto valida se pode avançar
        notificador.notificar("Pedido agora está: " + pedido.getEstado());
    }

    public void cancelar(Pedido pedido) {
        pedido.cancelar();
        notificador.notificar("Pedido agora está: " + pedido.getEstado());
    }

    public List<Pedido> listar() { return List.copyOf(registros); }
}
