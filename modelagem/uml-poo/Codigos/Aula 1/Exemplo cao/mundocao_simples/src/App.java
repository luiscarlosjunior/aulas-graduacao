// Criação de um exemplo simples de classe
public class App {
    public static void main(String[] args) throws Exception {
        
        // Criar um objeto através da instânciação
        Cachorro cachorro = new Cachorro();

        System.out.println("\n");

        // Atribuindo valores 
        cachorro.corOlhos = "Castanhos";
        cachorro.corPelo = "Pretos";
        cachorro.idade = 2;
        cachorro.raca = "Poodle";

        // Visualizar os atributos do cachorro
        System.out.println("Este cachorro tem os olhos " + cachorro.corOlhos);
        System.out.println("Este cachorro tem os cor dos pelos " + cachorro.corPelo);
        System.out.println("Este cachorro tem  " + cachorro.idade + " ano(s) de idade");
        System.out.println("Este cachorro tem é da raça " + cachorro.raca);

        System.out.println("\n");

        // Definindo comportamentos
        cachorro.Dormir(20);
        cachorro.Acordar();
        cachorro.Correr();
        cachorro.Latir("Au! Au! Au!");



    }
}
