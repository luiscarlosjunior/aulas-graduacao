package conteudo;

import album.Album;
import usuario.Artista;

public class Musica extends Conteudo {

    private Artista artista;
    private Album album;

    public Musica(int id, String titulo, double duracao, Artista artista, Album album) {
        super(id, titulo, duracao);
        this.artista = artista;
        this.album = album;
    }

    public Artista getArtista() {
        return artista;
    }

    public void setArtista(Artista artista) {
        this.artista = artista;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public void tocar() {
        System.out.println("Tocando música do artista " + artista + " do álbum " + album);
    }
}