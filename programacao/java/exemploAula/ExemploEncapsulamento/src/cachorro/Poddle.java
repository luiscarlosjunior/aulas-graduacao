package cachorro;
public class Poddle extends Cachorro {

    // Construtor
    public Poddle(String raca, int idade, String nome, String corOlhos) {
        super(raca, idade, nome, corOlhos);
    }

    void brincar() {
        System.out.println("Brincando...");
    }

}
