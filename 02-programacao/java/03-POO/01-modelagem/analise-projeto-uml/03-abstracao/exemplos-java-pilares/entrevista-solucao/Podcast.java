/*
 * Podcast — outro ConteudoDeAudio. Vem da fala: "o podcast tem um apresentador".
 */
public class Podcast extends ConteudoDeAudio {
    private final String apresentador;

    public Podcast(String titulo, int duracaoSegundos, String apresentador) {
        super(titulo, duracaoSegundos);
        this.apresentador = apresentador;
    }

    public String getApresentador() { return apresentador; }

    @Override public String descricaoCurta() { return "Podcast com " + apresentador; }
}
