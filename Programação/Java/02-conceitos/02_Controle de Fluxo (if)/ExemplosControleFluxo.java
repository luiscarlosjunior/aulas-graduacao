/**
 *
 * @author luiscaparroz
 */

public class ExemplosControleFluxo {

  public static void main(String[] args) {  
    exemploIf();
    //exemploIfElse();
    //exemploIfElseIf();
    //exemploIfAninhado();
    //exemploSwitch();
  }    

  public static void exemploIf() {
    //definindo uma variável 'idade'  
    int idade = 20;  
    //checando a idade  
    if(idade > 18) {  
       System.out.println("Idade e maior que 18");       
    }  
  }
  
  public static void exemploIfElse() {
    //definindo uma variável 'idade'  
    int idade = 20;  
    //checando a idade  
    if(idade > 18) {  
      System.out.println("Idade e maior que 18");       
    } else {
      System.out.println("Idade e menor que 18");
    }
  }

  public static void exemploIfElseIf()
  {
    int numero = -13;  
    if(numero > 0) {  
      System.out.println("POSITIVO");    
    } else if(numero < 0 ) {  
      System.out.println("NEGATIVO");    
    } else {  
      System.out.println("ZERO");  
    }  
  }

  public static void exemploIfAninhado() {
    //Criando duas variáveis para idade e peso  
    int idade = 20;  
    int peso = 80;  
    //aplicando condição na idade e peso  
    if(idade >= 18) {  
        if(peso > 50) {  
          System.out.println("Você pode ser um doador de sangue");  
        }  
    }  
  }

  // Controle de fluxo usando o comando Switch
    public static void exemploSwitch(){
        int day = 4;
        switch (day) {
          case 1:
            System.out.println("Segunda-feira");
            break;
          case 2:
            System.out.println("Ter�a-feira");
            break;
          case 3:
            System.out.println("Quarta-feira");
            break;
          case 4:
            System.out.println("Quinta-feira");
            break;
          case 5:
            System.out.println("Sexta-feira");
            break;
          case 6:
            System.out.println("Sabado");
            break;
          case 7:
            System.out.println("Domingo");
            break;
        }
    }
}
