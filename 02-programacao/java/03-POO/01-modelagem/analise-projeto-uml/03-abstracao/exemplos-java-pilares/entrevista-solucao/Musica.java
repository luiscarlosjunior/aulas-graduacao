/*
 * Musica — um ConteudoDeAudio. Vem da fala: "a música guarda quantas
 * reproduções ela já teve... não pode ser adulterado na mão".
 */
public class Musica extends ConteudoDeAudio {
    private int reproducoes;              // - privado (protege ranking/pagamento)

    public Musica(String titulo, int duracaoSegundos) {
        super(titulo, duracaoSegundos);
        this.reproducoes = 0;
    }

    public int getReproducoes() { return reproducoes; }

    /** Única forma de aumentar: não existe setReproducoes(int). */
    public void registrarReproducao() { reproducoes++; }

    @Override public String descricaoCurta() { return "Música"; }
}
