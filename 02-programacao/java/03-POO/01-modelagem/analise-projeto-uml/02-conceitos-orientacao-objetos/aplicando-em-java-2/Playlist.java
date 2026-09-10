import java.util.ArrayList;
import java.util.List;

public class Playlist {
    private String nome;
    private List<ItemPlaylist> itens;

    public Playlist(String nome) {
        this.nome = nome;
        this.itens = new ArrayList<>();
    }

    public void adicionarMusica(Musica musica) {
        int proximaPosicao = itens.size() + 1;
        ItemPlaylist item = new ItemPlaylist(proximaPosicao, musica);
        itens.add(item);
    }

    public void listaMusicas() {
        System.out.println("Playlist: " + nome);

        for (ItemPlaylist item : itens) {
            Musica musica = item.getMusica();

            System.out.println(
                    item.getPosicao() + ". " +
                            musica.getTitulo() + " - " +
                            musica.getArtista());
        }
    }

    public String getNome() {
        return nome;
    }
}
