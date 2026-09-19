import java.util.ArrayList;
import java.util.List;

/*
 * Ouvinte É-UM Usuario. Vem da fala: "o ouvinte, que é quem escuta".
 * - CRIA playlists (associação) e SEGUE artistas (associação).
 * - POSSUI uma Assinatura que nasce junto com ele (COMPOSIÇÃO).
 */
public class Ouvinte extends Usuario {
    private final Assinatura assinatura;              // composição: criada aqui dentro
    private final List<Playlist> playlists = new ArrayList<>();
    private final List<Artista> seguindo = new ArrayList<>();

    public Ouvinte(String nome, String email, String senha) {
        super(nome, email, senha);
        this.assinatura = new Assinatura();           // "ganha uma assinatura" ao se cadastrar
    }

    public Assinatura getAssinatura() { return assinatura; }

    public Playlist criarPlaylist(String nome) {
        Playlist p = new Playlist(nome);
        playlists.add(p);
        return p;
    }

    public void seguir(Artista artista) {
        if (artista != null && !seguindo.contains(artista)) seguindo.add(artista);
    }

    public List<Playlist> getPlaylists() { return List.copyOf(playlists); }
    public List<Artista> getSeguindo()   { return List.copyOf(seguindo); }

    @Override public String beneficios() {
        return "ouvir sem limite e montar playlists";
    }
}
