package audio;

public class Podcast implements FonteDeAudio {
    private final String titulo;
    private final int duracaoSegundos;
    private final String apresentador;

    public Podcast(String titulo, int duracaoSegundos, String apresentador) {
        this.titulo = titulo;
        this.duracaoSegundos = duracaoSegundos;
        this.apresentador = apresentador;
    }

    @Override public String getTitulo() { return titulo; }
    @Override public int getDuracaoSegundos() { return duracaoSegundos; }

    @Override public void reproduzir() {
        System.out.println("  🎙 Podcast: " + titulo + " com " + apresentador + " (" + duracaoSegundos + "s)");
    }
}
