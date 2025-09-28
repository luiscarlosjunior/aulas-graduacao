package album;

public class Album {
    private String titulo;
    private int ano;

    public Album(String titulo, int ano) {
        this.titulo = titulo;
        this.ano = ano;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public void addMusica(String musica) {
        System.out.println("Música " + musica + " adicionada ao álbum " + titulo);
    }

    public void exibirInfo() {
        System.out.println("Álbum: " + titulo + " (" + ano + ")");
    }
}