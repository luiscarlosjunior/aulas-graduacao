/*
 *  Audiolivro — o terceiro CONCRETO.
 *
 *  Acrescentar um tipo novo NÃO exigiu tocar em ConteudoDeAudio, nem em
 *  Musica, nem em Podcast. Essa é a promessa da boa abstração: o novo entra
 *  "encaixando" no contrato existente. (Volta como pilar 4 — Aberto/Fechado.)
 */
public class Audiolivro extends ConteudoDeAudio {

    private final String autor;
    private final int capitulos;

    public Audiolivro(String titulo, int duracaoSegundos, String autor, int capitulos) {
        super(titulo, duracaoSegundos);
        this.autor = autor;
        this.capitulos = capitulos;
    }

    public String getAutor() {
        return autor;
    }

    public int getCapitulos() {
        return capitulos;
    }

    @Override
    public String descricaoCurta() {
        return "Audiolivro de " + autor + " (" + capitulos + " cap.)";
    }
}
