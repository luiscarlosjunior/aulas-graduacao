import java.util.ArrayList;
import java.util.List;

/**
 * GestorComanda — coordena o processo (abrir, avançar, listar).
 * SRP (SOLID): só cuida do CICLO DE VIDA de cada Comanda; as regras de cada objeto ficam nele mesmo.
 * DIP (SOLID): recebe um Notificador (interface) pelo construtor — não sabe qual canal é.
 */
public class GestorComanda {
    private final List<Comanda> registros = new ArrayList<>();
    private final Notificador notificador;

    public GestorComanda(Notificador notificador) {
        this.notificador = notificador;
    }

    public Comanda abrir(Cliente cliente, double valorTotal) {
        Comanda nova = new Comanda(cliente, valorTotal);   // se o valor for inválido, a Comanda recusa (exceção)
        registros.add(nova);
        notificador.notificar("Novo registro de Comanda na etapa: " + nova.getEstado());
        return nova;
    }

    public void avancar(Comanda comanda) {
        comanda.avancar();   // MENSAGEM: o próprio objeto valida se pode avançar
        notificador.notificar("Comanda agora está: " + comanda.getEstado());
    }

    public List<Comanda> listar() { return List.copyOf(registros); }
}
