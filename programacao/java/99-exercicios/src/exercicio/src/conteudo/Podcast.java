package conteudo;

import usuario.Apresentador;

public class Podcast extends Conteudo {

    private Apresentador apresentador;
    private int episodios;

    public Podcast(int id, String titulo, double duracao, Apresentador apresentador, int episodios) {
        super(id, titulo, duracao);
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

    public void ouvir() {
        System.out.println("Ouvindo podcast apresentado por " + apresentador + " com " + episodios + " episódios.");
    }
}