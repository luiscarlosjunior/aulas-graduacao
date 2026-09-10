public class ItemPlaylist {
    private int posicao;
    private Musica musica;

    public ItemPlaylist(int posicao, Musica musica) {
        this.posicao = posicao;
        this.musica = musica;
    }

    public Musica getMusica() {
        return musica;
    }

    public int getPosicao() {
        return posicao;
    }
}
