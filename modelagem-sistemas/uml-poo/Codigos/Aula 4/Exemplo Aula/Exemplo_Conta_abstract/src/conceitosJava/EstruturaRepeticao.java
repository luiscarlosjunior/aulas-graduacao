package conceitosJava;

public class EstruturaRepeticao {
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
