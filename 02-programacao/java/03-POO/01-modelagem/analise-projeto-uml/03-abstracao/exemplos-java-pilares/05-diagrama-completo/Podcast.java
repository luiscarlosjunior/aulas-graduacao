/*
 * Podcast — outro concreto de ConteudoDeAudio.
 * numeroDoEpisodio é específico do podcast e NÃO sobe para a base.
 */
public class Podcast extends ConteudoDeAudio {
    private final int numeroDoEpisodio;

    public Podcast(String titulo, int duracaoSegundos, int numeroDoEpisodio) {
        super(titulo, duracaoSegundos);
        this.numeroDoEpisodio = numeroDoEpisodio;
    }

    public int getNumeroDoEpisodio() { return numeroDoEpisodio; }

    @Override public String descricaoCurta() { return "Podcast · ep. " + numeroDoEpisodio; }
}
