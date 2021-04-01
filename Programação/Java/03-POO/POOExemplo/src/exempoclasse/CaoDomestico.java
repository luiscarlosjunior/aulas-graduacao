
package exempoclasse;

/**
 * @author luiscaparroz
 * Esta classe representa de maneira simples um cão domestico
 */
public class CaoDomestico {
     
    public String nome;
    public int peso;
    public String corOlhos;
    public int quantPatas;
    
    public CaoDomestico()
    {
    }
     
    public CaoDomestico(String nome)
    {
        this.nome = nome;
    }
     
    public String getNome()
    {
        return "Nome do Curso retornado " + nome;
    }
    
    public void falar(){
        //TODO FALAR
    }
     
    public void andar(){
        //TODO ANDAR
    } 
     
    public void comer(){
        //TODO COMER
    }
     
    public void dormir(){
        //TODO DORMIR
    }
    
    void latir(){
        if(peso > 60)
            System.out.println("Wooof, Wooof!");
        else if(peso > 14)
            System.out.println("Ruff!, Ruff!");
        else
            System.out.println("Yip!, Yip!");
    }
}
