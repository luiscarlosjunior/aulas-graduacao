package album;

import usuario.Apresentador;
public class Playlist {
    private Apresentador apresentador;
    private int episodios;

    public Playlist(Apresentador apresentador, int episodios) {
        this.apresentador = apresentador;
        this.episodios = episodios;
    }

    public Apresentador getApresentador() {
        return apresentador;
    }

    public void setApresentador(Apresentador apresentador) {
        this.apresentador = apresentador;
    }

    public int getEpisodios() {
        return episodios;
    }

    public void setEpisodios(int episodios) {
        this.episodios = episodios;
    }

    public void reproduzir() {
        System.out.println("Reproduzindo a playlist...");
    }
}