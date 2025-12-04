package recomendacao;

public class Avaliacao {
    private String comentario;
    private int nota; // Nota de 1 a 5

    public Avaliacao(String comentario, int nota) {
        this.comentario = comentario;
        this.nota = nota;
    }

    public void avaliar() {
        System.out.println("Avaliação: " + nota + " - " + comentario);
    }
}