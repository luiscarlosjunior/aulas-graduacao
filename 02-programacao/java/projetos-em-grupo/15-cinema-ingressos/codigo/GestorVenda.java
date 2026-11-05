import java.util.ArrayList;
import java.util.List;

/**
 * GestorVenda — coordena o processo (abrir, avançar, cancelar).
 * SRP (SOLID): só cuida do CICLO DE VIDA de cada Venda; as regras de cada objeto ficam nele mesmo.
 * DIP (SOLID): recebe um Notificador (interface) pelo construtor — não sabe qual canal é.
 */
public class GestorVenda {
    private final List<Venda> registros = new ArrayList<>();
    private final Notificador notificador;

    public GestorVenda(Notificador notificador) {
        this.notificador = notificador;
    }

    public Venda abrir(double valorTotal) {
        Venda nova = new Venda(valorTotal);   // se o valor for inválido, a Venda recusa (exceção)
        registros.add(nova);
        notificador.notificar("Novo registro de Venda na etapa: " + nova.getEstado());
        return nova;
    }

    public void avancar(Venda venda) {
        venda.avancar();   // MENSAGEM: o próprio objeto valida se pode avançar
        notificador.notificar("Venda agora está: " + venda.getEstado());
    }

    public void cancelar(Venda venda) {
        venda.cancelar();
        notificador.notificar("Venda agora está: " + venda.getEstado());
    }

    public List<Venda> listar() { return List.copyOf(registros); }
}
