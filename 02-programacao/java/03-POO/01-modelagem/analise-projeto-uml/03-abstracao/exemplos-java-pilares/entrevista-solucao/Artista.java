import java.util.ArrayList;
import java.util.List;

/*
 * Artista É-UM Usuario. Vem da fala: "o artista, que é quem publica música".
 * PUBLICA músicas (associação Artista --> Musica).
 */
public class Artista extends Usuario {
    private final String nomeArtistico;
    private final List<Musica> musicas = new ArrayList<>();

    public Artista(String nome, String email, String senha, String nomeArtistico) {
        super(nome, email, senha);
        this.nomeArtistico = nomeArtistico;
    }

    public String getNomeArtistico() { return nomeArtistico; }

    /** "o artista publica as músicas dele". */
    public Musica publicar(String titulo, int duracaoSegundos) {
        Musica m = new Musica(titulo, duracaoSegundos);
        musicas.add(m);
        return m;
    }

    public List<Musica> getMusicas() { return List.copyOf(musicas); }

    @Override public String beneficios() {
        return "publicar músicas e receber pelos toques";
    }
}
