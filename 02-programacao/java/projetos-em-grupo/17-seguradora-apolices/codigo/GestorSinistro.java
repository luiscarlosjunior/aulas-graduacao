import java.util.ArrayList;
import java.util.List;

/**
 * GestorSinistro — coordena o processo (abrir, avançar, marcarNegado).
 * SRP (SOLID): só cuida do CICLO DE VIDA de cada Sinistro; as regras de cada objeto ficam nele mesmo.
 * DIP (SOLID): recebe um Notificador (interface) pelo construtor — não sabe qual canal é.
 */
public class GestorSinistro {
    private final List<Sinistro> registros = new ArrayList<>();
    private final Notificador notificador;

    public GestorSinistro(Notificador notificador) {
        this.notificador = notificador;
    }

    public Sinistro abrir(Apolice apolice, double valorIndenizacao) {
        Sinistro nova = new Sinistro(apolice, valorIndenizacao);   // se o valor for inválido, a Sinistro recusa (exceção)
        registros.add(nova);
        notificador.notificar("Novo registro de Sinistro na etapa: " + nova.getEstado());
        return nova;
    }

    public void avancar(Sinistro sinistro) {
        sinistro.avancar();   // MENSAGEM: o próprio objeto valida se pode avançar
        notificador.notificar("Sinistro agora está: " + sinistro.getEstado());
    }

    public void marcarNegado(Sinistro sinistro) {
        sinistro.marcarNegado();
        notificador.notificar("Sinistro agora está: " + sinistro.getEstado());
    }

    public List<Sinistro> listar() { return List.copyOf(registros); }
}
