import java.util.ArrayList;
import java.util.List;

public class Usuario {
    private String nome;
    private String email;
    private List<Playlist> playlists;

    public Usuario(String nome, String email) {
        this.nome = nome;
        this.email = email;
        this.playlists = new ArrayList<>();
    }

    public Playlist criarPlaylist(String nome) {
        Playlist playlist = new Playlist(nome);
        playlists.add(playlist);
        return playlist;
    }

    public void listarPlaylists() {
        System.out.println("Playlists de " + nome + ":");

        for (Playlist playlist : playlists) {
            System.out.println("- " + playlist.getNome());
        }
    }
}
