import java.util.ArrayList;
import java.util.List;

/**
 * GestorLocacao — coordena o processo (abrir, avançar, cancelar).
 * SRP (SOLID): só cuida do CICLO DE VIDA de cada Locacao; as regras de cada objeto ficam nele mesmo.
 * DIP (SOLID): recebe um Notificador (interface) pelo construtor — não sabe qual canal é.
 */
public class GestorLocacao {
    private final List<Locacao> registros = new ArrayList<>();
    private final Notificador notificador;

    public GestorLocacao(Notificador notificador) {
        this.notificador = notificador;
    }

    public Locacao abrir(Cliente cliente, double valorDiaria) {
        Locacao nova = new Locacao(cliente, valorDiaria);   // se o valor for inválido, a Locacao recusa (exceção)
        registros.add(nova);
        notificador.notificar("Novo registro de Locacao na etapa: " + nova.getEstado());
        return nova;
    }

    public void avancar(Locacao locacao) {
        locacao.avancar();   // MENSAGEM: o próprio objeto valida se pode avançar
        notificador.notificar("Locacao agora está: " + locacao.getEstado());
    }

    public void cancelar(Locacao locacao) {
        locacao.cancelar();
        notificador.notificar("Locacao agora está: " + locacao.getEstado());
    }

    public List<Locacao> listar() { return List.copyOf(registros); }
}
