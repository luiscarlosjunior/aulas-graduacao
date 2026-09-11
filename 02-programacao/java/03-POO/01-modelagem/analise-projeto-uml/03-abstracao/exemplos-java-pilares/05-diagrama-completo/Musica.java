/*
 * Musica — concreto de ConteudoDeAudio, com estado ENCAPSULADO (reproducoes).
 */
public class Musica extends ConteudoDeAudio {
    private int reproducoes;                 // - privado: só muda pela operação abaixo

    public Musica(String titulo, int duracaoSegundos) {
        super(titulo, duracaoSegundos);
        this.reproducoes = 0;                // nasce coerente (invariante: >= 0)
    }

    public void registrarReproducao() { reproducoes++; }   // única porta de escrita
    public int getReproducoes() { return reproducoes; }

    @Override public String descricaoCurta() { return "Música"; }
}
