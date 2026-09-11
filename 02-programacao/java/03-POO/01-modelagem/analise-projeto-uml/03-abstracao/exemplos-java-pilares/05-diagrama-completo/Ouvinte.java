import java.util.ArrayList;
import java.util.List;

/*
 * Ouvinte É-UM Usuario. CRIA playlists (associação "1" --> "*").
 */
public class Ouvinte extends Usuario {
    private final String assinatura;                 // "Free" ou "Premium"
    private final List<Playlist> playlists = new ArrayList<>();

    public Ouvinte(String nome, String email, String assinatura) {
        super(nome, email);
        this.assinatura = assinatura;
    }

    public String getAssinatura() { return assinatura; }

    public Playlist criarPlaylist(String nome) {
        Playlist p = new Playlist(nome);
        playlists.add(p);
        return p;
    }

    public List<Playlist> getPlaylists() { return List.copyOf(playlists); }

    @Override public String beneficios() { return "streaming ilimitado e playlists"; }
}
