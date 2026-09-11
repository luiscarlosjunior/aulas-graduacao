/*
 * Diagrama COMPLETO do Melodia — a hierarquia de conteúdo.
 * ConteudoDeAudio é a abstração (classe abstrata): o essencial de todo áudio.
 */
public abstract class ConteudoDeAudio {
    protected final String titulo;          // # protegido: subclasses enxergam
    protected final int duracaoSegundos;

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

    public String duracaoFormatada() {
        return String.format("%02d:%02d", duracaoSegundos / 60, duracaoSegundos % 60);
    }

    /** Contrato abstrato: cada tipo se descreve à sua maneira (polimorfismo). */
    public abstract String descricaoCurta();
}
