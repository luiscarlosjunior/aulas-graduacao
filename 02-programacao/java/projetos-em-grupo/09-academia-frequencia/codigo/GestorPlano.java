import java.util.ArrayList;
import java.util.List;

/**
 * GestorPlano — coordena o processo (abrir, avançar, listar).
 * SRP (SOLID): só cuida do CICLO DE VIDA de cada Plano; as regras de cada objeto ficam nele mesmo.
 * DIP (SOLID): recebe um Notificador (interface) pelo construtor — não sabe qual canal é.
 */
public class GestorPlano {
    private final List<Plano> registros = new ArrayList<>();
    private final Notificador notificador;

    public GestorPlano(Notificador notificador) {
        this.notificador = notificador;
    }

    public Plano abrir(Aluno aluno, double valorMensalidade) {
        Plano nova = new Plano(aluno, valorMensalidade);   // se o valor for inválido, a Plano recusa (exceção)
        registros.add(nova);
        notificador.notificar("Novo registro de Plano na etapa: " + nova.getEstado());
        return nova;
    }

    public void avancar(Plano plano) {
        plano.avancar();   // MENSAGEM: o próprio objeto valida se pode avançar
        notificador.notificar("Plano agora está: " + plano.getEstado());
    }

    public List<Plano> listar() { return List.copyOf(registros); }
}
