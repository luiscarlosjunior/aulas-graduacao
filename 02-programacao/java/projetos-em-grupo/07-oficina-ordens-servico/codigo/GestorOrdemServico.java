import java.util.ArrayList;
import java.util.List;

/**
 * GestorOrdemServico — coordena o processo (abrir, avançar, listar).
 * SRP (SOLID): só cuida do CICLO DE VIDA de cada OrdemServico; as regras de cada objeto ficam nele mesmo.
 * DIP (SOLID): recebe um Notificador (interface) pelo construtor — não sabe qual canal é.
 */
public class GestorOrdemServico {
    private final List<OrdemServico> registros = new ArrayList<>();
    private final Notificador notificador;

    public GestorOrdemServico(Notificador notificador) {
        this.notificador = notificador;
    }

    public OrdemServico abrir(Cliente cliente, double valorTotal) {
        OrdemServico nova = new OrdemServico(cliente, valorTotal);   // se o valor for inválido, a OrdemServico recusa (exceção)
        registros.add(nova);
        notificador.notificar("Novo registro de OrdemServico na etapa: " + nova.getEstado());
        return nova;
    }

    public void avancar(OrdemServico ordemServico) {
        ordemServico.avancar();   // MENSAGEM: o próprio objeto valida se pode avançar
        notificador.notificar("OrdemServico agora está: " + ordemServico.getEstado());
    }

    public List<OrdemServico> listar() { return List.copyOf(registros); }
}
