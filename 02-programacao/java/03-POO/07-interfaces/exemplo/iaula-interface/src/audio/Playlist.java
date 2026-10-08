package audio;

import java.util.ArrayList;
import java.util.List;

/*
 * Playlist depende do CONTRATO FonteDeAudio: aceita Musica, Podcast e qualquer
 * tipo futuro (AudioLivro, AoVivo...) sem mudar nada aqui.
 */
public class Playlist {
    private final String nome;
    private final List<FonteDeAudio> itens = new ArrayList<>();

    public Playlist(String nome) { this.nome = nome; }

    public void adicionar(FonteDeAudio f) { itens.add(f); }  // aceita QUALQUER fonte

    public void tocarTudo() {
        System.out.println("▶ Tocando playlist '" + nome + "':");
        for (FonteDeAudio f : itens) f.reproduzir();         // polimorfismo
    }

    public int duracaoTotalSegundos() {
        int total = 0;
        for (FonteDeAudio f : itens) total += f.getDuracaoSegundos();
        return total;
    }
}
