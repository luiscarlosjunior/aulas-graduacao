// Criando a classe cachorro
public class Cachorro {
    
    // Atributos
    public String raca;
    public int idade;
    public String corOlhos;
    public String corPelo;
    public String tipoPelo;

    // Comportamento
    public boolean Latir(String latido)
    {
        return true;
    }

    public void Correr()
    {
        System.out.println("Estou correndo 2 metros");
    }

    public void Dormir(int dormindoMinutos)
    {
        System.out.println("Estou dormindo por " + dormindoMinutos + " minuto(s)");
    }

    public void Comer()
    {
        System.out.println("Estou comendo...");
    }

    public void Acordar()
    {
        System.out.println("Estou acordando");
    }

}
