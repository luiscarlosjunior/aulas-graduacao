package audio;

public class Musica implements FonteDeAudio {
    private final String titulo;
    private final int duracaoSegundos;
    private final String artista;
    private int reproducoes = 0;

    public Musica(String titulo, int duracaoSegundos, String artista) {
        this.titulo = titulo;
        this.duracaoSegundos = duracaoSegundos;
        this.artista = artista;
    }

    @Override public String getTitulo() { return titulo; }
    @Override public int getDuracaoSegundos() { return duracaoSegundos; }

    @Override public void reproduzir() {
        reproducoes++;
        System.out.println("  ♪ Música: " + titulo + " — " + artista + " (" + duracaoSegundos + "s)");
    }
}
