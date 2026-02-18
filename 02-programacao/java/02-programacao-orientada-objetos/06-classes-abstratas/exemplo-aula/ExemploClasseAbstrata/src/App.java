import exercicio.*;

public class App {
    public static void main(String[] args) throws Exception {
        Cachorro cachorro = new Cachorro("Rex", 3, 10.5);
        cachorro.emitirSom();
        cachorro.comer();
        cachorro.dormir();
        /*if (cachorro instanceof Cachorro) {
            ((Cachorro) cachorro).correr();
        }*/
        //cachorro.correr();

    }

    static void exemplo1() {
        /*Animal cachorro = new Cachorro("Rex", 3, 10.5);
        Animal gato = new Gato("Mimi", 2, 4.3);

        //Animal animal = new Animal("Juca", 5, 45.8);

       // animal.emitirSom();
       // animal.comer(); 
       // animal.dormir();

        cachorro.emitirSom();
        cachorro.comer();
        cachorro.dormir();
        gato.emitirSom();
        gato.comer();
        gato.dormir();*/
    }
}
