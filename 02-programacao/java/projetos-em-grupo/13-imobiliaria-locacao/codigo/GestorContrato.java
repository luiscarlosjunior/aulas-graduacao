import java.util.ArrayList;
import java.util.List;

/**
 * GestorContrato — coordena o processo (abrir, avançar, cancelar).
 * SRP (SOLID): só cuida do CICLO DE VIDA de cada Contrato; as regras de cada objeto ficam nele mesmo.
 * DIP (SOLID): recebe um Notificador (interface) pelo construtor — não sabe qual canal é.
 */
public class GestorContrato {
    private final List<Contrato> registros = new ArrayList<>();
    private final Notificador notificador;

    public GestorContrato(Notificador notificador) {
        this.notificador = notificador;
    }

    public Contrato abrir(Imovel imovel, double valorAluguel) {
        Contrato nova = new Contrato(imovel, valorAluguel);   // se o valor for inválido, a Contrato recusa (exceção)
        registros.add(nova);
        notificador.notificar("Novo registro de Contrato na etapa: " + nova.getEstado());
        return nova;
    }

    public void avancar(Contrato contrato) {
        contrato.avancar();   // MENSAGEM: o próprio objeto valida se pode avançar
        notificador.notificar("Contrato agora está: " + contrato.getEstado());
    }

    public void cancelar(Contrato contrato) {
        contrato.cancelar();
        notificador.notificar("Contrato agora está: " + contrato.getEstado());
    }

    public List<Contrato> listar() { return List.copyOf(registros); }
}
