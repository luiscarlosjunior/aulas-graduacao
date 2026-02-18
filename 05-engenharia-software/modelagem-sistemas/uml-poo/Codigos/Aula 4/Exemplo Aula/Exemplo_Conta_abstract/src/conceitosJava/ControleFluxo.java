package conceitosJava;

public class ControleFluxo {

	// Controle de fluxo usando o comando Switch
    public static void exemploSwitch(){
        int day = 4;
        switch (day) {
          case 1:
            System.out.println("Segunda-feira");
            break;
          case 2:
            System.out.println("Terça-feira");
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
