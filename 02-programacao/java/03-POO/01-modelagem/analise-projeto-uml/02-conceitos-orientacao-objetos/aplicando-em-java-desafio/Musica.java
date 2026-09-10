import java.time.LocalDateTime;
import java.util.*;

public class Musica {
    private String titulo;
    private String artista;
    private int duracaoEmSegundos;

    public Musica(String titulo, String artista, int duracaoEmSegundos) {
        this.titulo = titulo;
        this.artista = artista;
        this.duracaoEmSegundos = duracaoEmSegundos;
    }

    public String getArtista() {
        return artista;
    }

    public String getTitulo() {
        return titulo;
    }
}
