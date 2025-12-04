public class App {
    public static void main(String[] args) throws Exception {
        TocarMusica tocarMusica = new TocarMusicaAdapter();
        tocarMusica.play("minha_musica_favorita.mp3");
    }
}
