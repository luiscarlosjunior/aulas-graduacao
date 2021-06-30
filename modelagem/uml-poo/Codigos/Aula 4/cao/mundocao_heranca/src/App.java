import mundocao.heranca.categoria.Cachorro;
import mundocao.heranca.categoria.Pinscher;
import mundocao.heranca.categoria.Poddle;

// Criação de um exemplo simples de classes
public class App {
    public static void main(String[] args) throws Exception {
        
        // Criar um objeto atraves da instanciacao
        Cachorro pinscher = new Pinscher();
        pinscher.raca = "Pinscher";
        InstanciarMembros(pinscher);
        
        pinscher.raca = "Poddle";
        Cachorro poddle = new Poddle();
        InstanciarMembros(poddle);
        
        // Chamando caracteristicas de cada classe
        ((Pinscher)pinscher).Tremer();
        ((Poddle)poddle).Brincar();
        
    }
    
    private static void InstanciarMembros(Cachorro cachorro)
    {
        System.out.println("\n");

        // Atribuindo valores 
        cachorro.corOlhos = "Castanhos";
        cachorro.corPelo = "Pretos";
        cachorro.idade = 2;

        // Visualizar os atributos do cachorro
        System.out.println("Este cachorro tem os olhos " + cachorro.corOlhos);
        System.out.println("Este cachorro tem os cor dos pelos " + cachorro.corPelo);
        System.out.println("Este cachorro tem  " + cachorro.idade + " ano(s) de idade");
        System.out.println("Este cachorro eh da raca " + cachorro.raca);

        System.out.println("\n");

        // Definindo comportamentos
        cachorro.Dormir(20);
        cachorro.Acordar();
        cachorro.Correr();
        cachorro.Latir("Au! Au! Au!");
    }
    
}
