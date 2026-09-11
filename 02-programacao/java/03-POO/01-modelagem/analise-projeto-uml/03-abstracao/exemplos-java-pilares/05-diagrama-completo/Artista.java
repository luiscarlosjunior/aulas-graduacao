import java.util.ArrayList;
import java.util.List;

/*
 * Artista É-UM Usuario. PUBLICA músicas (associação "1" --> "*").
 */
public class Artista extends Usuario {
    private final String nomeArtistico;
    private final List<Musica> publicadas = new ArrayList<>();

    public Artista(String nome, String email, String nomeArtistico) {
        super(nome, email);
        this.nomeArtistico = nomeArtistico;
    }

    public String getNomeArtistico() { return nomeArtistico; }

    public Musica publicar(String titulo, int duracaoSegundos) {
        Musica m = new Musica(titulo, duracaoSegundos);
        publicadas.add(m);
        return m;
    }

    public List<Musica> getPublicadas() { return List.copyOf(publicadas); }

    @Override public String beneficios() { return "publicar álbuns e receber royalties"; }
}
