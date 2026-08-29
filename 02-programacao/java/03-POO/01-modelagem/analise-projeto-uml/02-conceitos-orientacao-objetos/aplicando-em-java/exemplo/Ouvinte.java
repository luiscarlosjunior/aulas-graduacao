import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Um ouvinte. Demonstra dois relacionamentos:
 *  - AGREGAÇÃO com Playlist (o ouvinte MONTA playlists — cria e guarda).
 *  - ASSOCIAÇÃO com Artista (o ouvinte SEGUE artistas — só conhece/aponta).
 *
 * Nesta aula, Ouvinte e Artista são classes SEPARADAS (sem herança). A ideia de
 * generalizar em "Usuario" fica para a aula de herança.
 */
public class Ouvinte {

    private final String nome;
    private final String email;
    private final List<Playlist> playlists = new ArrayList<>();
    private final List<Artista> seguindo = new ArrayList<>();   // ASSOCIAÇÃO

    public Ouvinte(String nome, String email) {
        this.nome = nome;
        this.email = email;
    }

    /** O ouvinte cria e guarda a playlist (agregação). Retorna a playlist criada. */
    public Playlist criarPlaylist(String nome) {
        Playlist p = new Playlist(nome, this.nome);
        playlists.add(p);
        return p;
    }

    /** ASSOCIAÇÃO: passa a "conhecer" um artista já existente. */
    public void seguir(Artista a) {
        if (a != null && !seguindo.contains(a)) seguindo.add(a);
    }

    public String getNome()  { return nome; }
    public String getEmail() { return email; }
    public List<Playlist> getPlaylists() { return Collections.unmodifiableList(playlists); }
    public List<Artista> getSeguindo()   { return Collections.unmodifiableList(seguindo); }
}
