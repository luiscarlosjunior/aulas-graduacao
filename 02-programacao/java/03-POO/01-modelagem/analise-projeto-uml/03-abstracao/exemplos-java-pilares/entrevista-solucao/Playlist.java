import java.util.ArrayList;
import java.util.List;

/*
 * Playlist — AGREGA conteúdos (losango vazio ◇). Vem da fala: "a playlist só
 * junta conteúdos que já existem... se apagar a playlist, as músicas continuam".
 * Por isso guarda ConteudoDeAudio (o tipo base) e NÃO cria os conteúdos.
 */
public class Playlist {
    private final String nome;
    private final List<ConteudoDeAudio> itens = new ArrayList<>();

    public Playlist(String nome) { this.nome = nome; }

    public String getNome() { return nome; }

    public void adicionar(ConteudoDeAudio conteudo) { itens.add(conteudo); }
    public void remover(ConteudoDeAudio conteudo)   { itens.remove(conteudo); }

    public List<ConteudoDeAudio> getItens() { return List.copyOf(itens); }

    public int duracaoTotal() {
        int total = 0;
        for (ConteudoDeAudio c : itens) total += c.getDuracaoSegundos();
        return total;
    }
}
