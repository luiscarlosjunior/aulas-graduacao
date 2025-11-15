public class TocarMusicaAdapter implements TocarMusica {
    private TocarMusicaLegado tocarMusicaLegado;

    // Construtor
    public TocarMusicaAdapter() {
        this.tocarMusicaLegado = new TocarMusicaLegado();
    }

    public void play(String arquivo) {
        tocarMusicaLegado.executaSom(arquivo);
    }
}