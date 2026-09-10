import java.util.ArrayList;
import java.util.List;

public class Musica {
    private String titulo;
    private String artista;
    private int duracaoEmSegundos;

    public Musica(String titulo, String artista, int duracaoEmSegundos) {
        this.titulo = titulo;
        this.artista = artista;
        this.duracaoEmSegundos = duracaoEmSegundos;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getArtista() {
        return artista;
    }

    public int getDuracaoEmSegundos() {
        return duracaoEmSegundos;
    }

}
