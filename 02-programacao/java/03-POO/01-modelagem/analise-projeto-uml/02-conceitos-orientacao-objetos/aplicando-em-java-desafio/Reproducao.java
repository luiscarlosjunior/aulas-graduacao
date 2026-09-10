import java.time.LocalDateTime;

public class Reproducao {
    private LocalDateTime dataHora;
    private String dispositivo;
    private boolean tocouAteFinal;
    private Musica musica;

    public Reproducao(Musica musica, String dispositivo, boolean tocouAteFinal) {
        this.dispositivo = dispositivo;
        this.musica = musica;
        this.tocouAteFinal = tocouAteFinal;
        this.dataHora = LocalDateTime.now();
    }

    public void exibir() {
        System.out.println(dataHora + " - " + musica.getTitulo() + " - " + musica.getArtista() + " - " + dispositivo);
    }
}
