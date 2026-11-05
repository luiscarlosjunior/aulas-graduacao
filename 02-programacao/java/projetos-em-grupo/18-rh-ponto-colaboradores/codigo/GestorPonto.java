import java.util.ArrayList;
import java.util.List;

/**
 * GestorPonto — coordena o processo (abrir, avançar, marcarAjustado).
 * SRP (SOLID): só cuida do CICLO DE VIDA de cada Ponto; as regras de cada objeto ficam nele mesmo.
 * DIP (SOLID): recebe um Notificador (interface) pelo construtor — não sabe qual canal é.
 */
public class GestorPonto {
    private final List<Ponto> registros = new ArrayList<>();
    private final Notificador notificador;

    public GestorPonto(Notificador notificador) {
        this.notificador = notificador;
    }

    public Ponto abrir(Colaborador colaborador, double horasTrabalhadas) {
        Ponto nova = new Ponto(colaborador, horasTrabalhadas);   // se o valor for inválido, a Ponto recusa (exceção)
        registros.add(nova);
        notificador.notificar("Novo registro de Ponto na etapa: " + nova.getEstado());
        return nova;
    }

    public void avancar(Ponto ponto) {
        ponto.avancar();   // MENSAGEM: o próprio objeto valida se pode avançar
        notificador.notificar("Ponto agora está: " + ponto.getEstado());
    }

    public void marcarAjustado(Ponto ponto) {
        ponto.marcarAjustado();
        notificador.notificar("Ponto agora está: " + ponto.getEstado());
    }

    public List<Ponto> listar() { return List.copyOf(registros); }
}
