import java.util.ArrayList;
import java.util.List;

/*
 * Playlist — AGREGA conteúdos (losango vazio ◇: "contém").
 * Guarda ConteudoDeAudio (o tipo abstrato) — por isso aceita Musica E Podcast.
 * Os conteúdos EXISTEM independentemente da playlist (agregação, não composição).
 */
public class Playlist {
    private final String nome;
    private final List<ConteudoDeAudio> itens = new ArrayList<>();

    public Playlist(String nome) { this.nome = nome; }

    public void adicionar(ConteudoDeAudio conteudo) { itens.add(conteudo); }

    public String getNome() { return nome; }
    public List<ConteudoDeAudio> getItens() { return List.copyOf(itens); }

    public int duracaoTotalSegundos() {
        int total = 0;
        for (ConteudoDeAudio c : itens) total += c.getDuracaoSegundos();
        return total;
    }
}
