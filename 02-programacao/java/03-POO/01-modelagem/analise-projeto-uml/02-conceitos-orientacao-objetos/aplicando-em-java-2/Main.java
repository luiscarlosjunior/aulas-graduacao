public class Main {

    public static void main(String[] args) {
        Usuario usuario = new Usuario("Luis", "luis@teste.com");

        Musica musica1 = new Musica("Imagine", "John Lennon", 183);
        Musica musica2 = new Musica("Billie Jean", "Michael Jackson", 294);

        Playlist playlist = usuario.criarPlaylist("Curtindo");

        playlist.adicionarMusica(musica1);
        playlist.adicionarMusica(musica2);

        usuario.listarPlaylists();

        System.out.println();

        playlist.listaMusicas();
    }

}
