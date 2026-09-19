/*
 * ConteudoDeAudio — a base do que toca (ABSTRATA).
 * Vem da fala: "música e podcast... os dois têm título e duração".
 */
public abstract class ConteudoDeAudio {
    private final String titulo;
    private final int duracaoSegundos;

    protected ConteudoDeAudio(String titulo, int duracaoSegundos) {
        if (titulo == null || titulo.isBlank())
            throw new IllegalArgumentException("Título é obrigatório.");
        if (duracaoSegundos <= 0)
            throw new IllegalArgumentException("Duração deve ser positiva.");
        this.titulo = titulo;
        this.duracaoSegundos = duracaoSegundos;
    }

    public String getTitulo() { return titulo; }
    public int getDuracaoSegundos() { return duracaoSegundos; }

    /** "os dois sabem se descrever" — mas cada um do seu jeito (polimorfismo). */
    public abstract String descricaoCurta();
}
