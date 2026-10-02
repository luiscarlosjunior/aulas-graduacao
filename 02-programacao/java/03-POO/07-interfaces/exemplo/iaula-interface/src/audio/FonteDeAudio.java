package audio;

/*
 * Contrato "PODE-FAZER": tudo que é reproduzível no Melodia expõe isto.
 * Musica e Podcast implementam — e a Playlist depende DESTE contrato.
 */
public interface FonteDeAudio {
    String getTitulo();
    int getDuracaoSegundos();
    void reproduzir();
}
