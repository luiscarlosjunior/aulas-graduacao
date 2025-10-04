import cachorro.Poddle;
import personagem.Guerreiro;
import personagem.Mago;

public class App {
    public static void main(String[] args) throws Exception {
        // getMeuPoddle();

        Guerreiro conan = new Guerreiro("Conan", 100);
        System.out.println("Nome: " + conan.getNome());
        System.out.println("Vida: " + conan.getVida());
        conan.usarEspada();
        conan.setNome("Conan, o Bárbaro", "admin");
        conan.setVida(80);

        Mago gandalf = new Mago("Gandalf", 80);
        System.out.println("Nome: " + gandalf.getNome());
        System.out.println("Vida: " + gandalf.getVida());
        gandalf.usarMagia();
        gandalf.setNome("Gandalf, o Cinzento", "admin");
        gandalf.setVida(60);
    }

    private static void getMeuPoddle() {
        Poddle meuPoddle = 
        new Poddle("Poddle", 3, "Bob", "Castanho");
        
        // Pegar alguns atributos
        System.out.println("Nome: " + meuPoddle.getNome());
        System.out.println("Raça: " + meuPoddle.getRaca("admin", "admin123"));
        System.out.println("Idade: " + meuPoddle.getIdade() + " anos");
        System.out.println("Cor dos olhos: " + meuPoddle.getCorOlhos());

        meuPoddle.setIdade(4, "admin", "admin");
        meuPoddle.setNome("Rex");
        
        System.out.println("Nome: " + meuPoddle.getNome());
        System.out.println("Raça: " + meuPoddle.getRaca("admin", "admin123"));
        System.out.println("Idade: " + meuPoddle.getIdade() + " anos");
        System.out.println("Cor dos olhos: " + meuPoddle.getCorOlhos());

        meuPoddle.ladrar();
        meuPoddle.correr();
        meuPoddle.dormir();
        meuPoddle.comer();
        meuPoddle.acordar();
        //meuPoddle.brincar();
    }
}
