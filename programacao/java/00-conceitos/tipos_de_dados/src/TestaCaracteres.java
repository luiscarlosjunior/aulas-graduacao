/**
 * @author luiscaparroz
 */
public class TestaCaracteres{

    public static void main(String[] args){

        char letra = 'a';
        System.out.println(letra);

        char valor = 65;                    // Compila!
        System.out.println(valor);

        //valor = (char) (valor + 1);         // Não compila!
        //System.out.println(valor);

        String palavra = "alura cursos online de tecnologia";
        System.out.println(palavra);

        palavra = palavra + 2020;
        System.out.println(palavra);
    }
}