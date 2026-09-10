import java.util.ArrayList;
import java.util.List;

public class HistoricoReproducao {
    private List<Reproducao> reproducoes;

    public HistoricoReproducao() {
        this.reproducoes = new ArrayList();
    }

    public void adicionarReproducao(Musica musica, String dispositivo, boolean tocouAteFinal) {
        Reproducao reproducao = new Reproducao(musica, dispositivo, tocouAteFinal);
        reproducoes.add(reproducao);
    }

    public void listarHistorico() {
        for (Reproducao reproducao : reproducoes) {
            reproducao.exibir();
        }
    }
}
