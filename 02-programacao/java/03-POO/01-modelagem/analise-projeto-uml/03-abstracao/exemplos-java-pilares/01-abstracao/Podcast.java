/*
 *  Podcast — outro CONCRETO de ConteudoDeAudio.
 *
 *  Repare no LIMITE da abstração: `numeroDoEpisodio` só existe em Podcast.
 *  Ele NÃO subiu para ConteudoDeAudio de propósito — não é essencial a "todo
 *  áudio" (uma Música não tem episódio). Saber o que DEIXAR DE FORA é tão
 *  importante quanto saber o que incluir (o "recorte" de Booch).
 */
public class Podcast extends ConteudoDeAudio {

    private final int numeroDoEpisodio;

    public Podcast(String titulo, int duracaoSegundos, int numeroDoEpisodio) {
        super(titulo, duracaoSegundos);
        this.numeroDoEpisodio = numeroDoEpisodio;
    }

    public int getNumeroDoEpisodio() {
        return numeroDoEpisodio;
    }

    @Override
    public String descricaoCurta() {
        return "Podcast · ep. " + numeroDoEpisodio;
    }
}
