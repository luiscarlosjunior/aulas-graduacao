import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * COMPOSIÇÃO (◆): o Album é "feito de" faixas. As músicas são CRIADAS DENTRO do
 * álbum (adicionarFaixa) e não fazem sentido sem ele — se o álbum some, as faixas
 * somem. Repare que quem cria a Musica é o próprio Album.
 */
public class Album {

    private final String titulo;
    private final int ano;
    private final List<Musica> faixas = new ArrayList<>();   // a parte vive DENTRO do todo

    public Album(String titulo, int ano) {
        this.titulo = titulo;
        this.ano = ano;
    }

    /** O Album é o DONO das faixas: ele as cria. Isso caracteriza a composição. */
    public Musica adicionarFaixa(String tituloFaixa, String artista, int duracaoSegundos) {
        Musica m = new Musica(tituloFaixa, artista, duracaoSegundos);
        faixas.add(m);
        return m;
    }

    public String getTitulo() { return titulo; }
    public int getAno()       { return ano; }

    /** Devolve uma visão SOMENTE-LEITURA: não deixa vazar a lista interna mutável. */
    public List<Musica> getFaixas() {
        return Collections.unmodifiableList(faixas);
    }

    public int duracaoTotalSegundos() {
        int total = 0;
        for (Musica m : faixas) total += m.getDuracaoSegundos();
        return total;
    }
}
