/**
 * Uma faixa do catálogo. Mostra os fundamentos de uma classe em Java:
 * atributos PRIVADOS (estado protegido), construtor que valida, e operações
 * (comportamento) que mexem no estado de forma controlada.
 */
public class Musica {

    private final String titulo;      // final: o título não muda depois de criado
    private final String artista;
    private final int duracaoSegundos;
    private int reproducoes;           // começa em 0; só aumenta por operação

    public Musica(String titulo, String artista, int duracaoSegundos) {
        if (titulo == null || titulo.isBlank())
            throw new IllegalArgumentException("título é obrigatório");
        if (duracaoSegundos <= 0)
            throw new IllegalArgumentException("duração deve ser positiva");
        this.titulo = titulo;
        this.artista = artista;
        this.duracaoSegundos = duracaoSegundos;
    }

    /** Comportamento: única forma de mudar 'reproducoes' (protege o invariante). */
    public void registrarReproducao() {
        reproducoes++;
    }

    public String getTitulo()       { return titulo; }
    public String getArtista()      { return artista; }
    public int getDuracaoSegundos() { return duracaoSegundos; }
    public int getReproducoes()     { return reproducoes; }

    /** Atributo DERIVADO: calculado a partir de 'duracaoSegundos', não armazenado. */
    public String duracaoFormatada() {
        return String.format("%d:%02d", duracaoSegundos / 60, duracaoSegundos % 60);
    }

    @Override
    public String toString() {
        return "\"" + titulo + "\" — " + artista + " (" + duracaoFormatada() + ", "
                + reproducoes + " reproduções)";
    }
}
