package conteudo;

import usuario.Narrador;

public class Audiobook extends Conteudo {

    private Narrador autor;
    private int capitulos;

    public Audiobook(int id, String titulo, double duracao, Narrador autor, int capitulos) {
        super(id, titulo, duracao);
        this.autor = autor;
        this.capitulos = capitulos;
    }

    public Narrador getAutor() {
        return autor;
    }

    public void setAutor(Narrador autor) {
        this.autor = autor;
    }

    public int getCapitulos() {
        return capitulos;
    }

    public void setCapitulos(int capitulos) {
        this.capitulos = capitulos;
    }

    public void ouvir() {
        System.out.println("Ouvindo audiobook do autor " + autor + " com " + capitulos + " capítulos.");
    }

}
