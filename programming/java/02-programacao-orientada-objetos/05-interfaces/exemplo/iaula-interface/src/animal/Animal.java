package animal;

public class Animal {
    private String nome;
    private String especie;

    // Construtor
    public Animal(String nome, String especie) {
        this.nome = nome;
        this.especie = especie;
    }

    // Getters
    public String getNome() {
        return nome;
    }
    public String getEspecie() {
        return especie;
    }
    // Setters
    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public void emitirSom() {
        System.out.println("O animal está emitindo um som.");
    }
}