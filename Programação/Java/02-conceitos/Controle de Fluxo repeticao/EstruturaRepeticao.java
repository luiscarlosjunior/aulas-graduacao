public class EstruturaRepeticao {

  /**
	 * @author Prof. Luis Caparroz Santos
	 * Exemplo de uso de: Estrutura de repetição	 * 
	 */
	
	// Para saber mais
	// https://www.caelum.com.br/apostila-java-orientacao-objetos/variaveis-primitivas-e-controle-de-fluxo
	// http://java.meritcampus.com/core-java-topics/java-data-types
	// https://www.w3schools.com/java/default.asp
  public static void main(String[] args) {
    /* Para pode usar chamar as fun��es, 
     * retire os coment�rios, salve e execute o
     * o programa. Todas as fun��es, est�o presentes
     * neste mesmo arquivo    	
    */
    
    exemploArrayMultiInt();
    //exemploForContinue();
    //exemploForLoop();
    //exemploForEachLoop();
    //exemploWhileLoop();
    //exemploDoWhile();
  }

	public static void exemploArrayMultiInt() {
        int[][] myNumbers = { {1, 2, 3, 4}, {5, 6, 7} };
        for (int i = 0; i < myNumbers.length; ++i) {
          for(int j = 0; j < myNumbers[i].length; ++j) {
            System.out.println(myNumbers[i][j]);
          }
        }
    }
    
    public static void exemploForContinue() {
        for (int i = 0; i < 10; i++) {
            if (i == 4) {
              continue;
            }
            System.out.println(i);
        }
    }
    
    public static void exemploForLoop() {
        for (int i = 0; i < 5; i++) {
            System.out.println(i);
        }     
    }
    
    public static void exemploForEachLoop() {
        String[] cars = {"Volvo", "BMW", "Ford", "Mazda"};
        for (String i : cars) {
          System.out.println(i);
        }
    }
    
    public static void exemploWhileLoop() {
        int i = 0;
        while (i < 5) {
          System.out.println(i);
          i++;
        }  
    }
    
    public static void exemploDoWhile() {
        int i = 0;
        do {
          System.out.println(i);
          i++;
        }
        while (i < 5);
    }
}
