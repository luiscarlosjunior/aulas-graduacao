/**
 * Demonstração de Estruturas de Repetição em Java
 * 
 * Este programa demonstra os diferentes tipos de loops (estruturas de repetição)
 * disponíveis em Java: for, while, do-while, for-each.
 * 
 * Estruturas de repetição permitem executar um bloco de código múltiplas vezes,
 * evitando repetição desnecessária de código e automatizando tarefas repetitivas.
 * 
 * @author Prof. Luis Caparroz Santos
 * @version 2.0
 * @since JDK 1.8
 */
public class EstruturaRepeticao {

    /**
     * Método principal que demonstra diferentes tipos de estruturas de repetição
     * @param args argumentos da linha de comando (não utilizados)
     */
    public static void main(String[] args) {
        
        System.out.println("=== DEMONSTRAÇÃO DE ESTRUTURAS DE REPETIÇÃO ===\n");
        
        // Para executar diferentes exemplos, descomente as linhas abaixo:
        exemploForBasico();
        System.out.println("\n" + "=".repeat(60) + "\n");
        
        exemploWhileBasico();
        System.out.println("\n" + "=".repeat(60) + "\n");
        
        exemploDoWhile();
        System.out.println("\n" + "=".repeat(60) + "\n");
        
        exemploForEach();
        System.out.println("\n" + "=".repeat(60) + "\n");
        
        exemploForContinue();
        System.out.println("\n" + "=".repeat(60) + "\n");
        
        exemploForBreak();
        System.out.println("\n" + "=".repeat(60) + "\n");
        
        exemploArrayMultiInt();
        System.out.println("\n" + "=".repeat(60) + "\n");
        
        exemplosPraticos();
    }

    /**
     * Demonstra o uso básico do loop FOR
     * O loop for é ideal quando sabemos quantas vezes queremos repetir algo
     */
    public static void exemploForBasico() {
        System.out.println("EXEMPLO 1: Loop FOR Básico");
        System.out.println("Contando de 1 até 5:");
        
        // Estrutura: for(inicialização; condição; incremento)
        for (int i = 1; i <= 5; i++) {
            System.out.println("Número: " + i);
        }
        
        System.out.println("\nExplicação do loop for:");
        System.out.println("• int i = 1     → inicializa variável com valor 1");
        System.out.println("• i <= 5        → condição para continuar o loop");
        System.out.println("• i++           → incrementa i em 1 a cada iteração");
    }
    
    /**
     * Demonstra o uso básico do loop WHILE
     * O loop while é ideal quando não sabemos exatamente quantas repetições teremos
     */
    public static void exemploWhileBasico() {
        System.out.println("EXEMPLO 2: Loop WHILE Básico");
        System.out.println("Contando de 1 até 5 com while:");
        
        int contador = 1;  // Inicialização da variável de controle
        
        // while continua enquanto a condição for verdadeira
        while (contador <= 5) {
            System.out.println("Contador: " + contador);
            contador++;  // IMPORTANTE: incrementar para evitar loop infinito!
        }
        
        System.out.println("\nExplicação do loop while:");
        System.out.println("• Verifica a condição ANTES de executar o código");
        System.out.println("• Se a condição for falsa desde o início, não executa nenhuma vez");
        System.out.println("• CUIDADO: sempre modifique a variável de controle para evitar loop infinito!");
    }
    
    /**
     * Demonstra o uso do loop DO-WHILE
     * Executa o código pelo menos uma vez, depois verifica a condição
     */
    public static void exemploDoWhile() {
        System.out.println("EXEMPLO 3: Loop DO-WHILE");
        System.out.println("Contando de 1 até 5 com do-while:");
        
        int numero = 1;
        
        // do-while executa o código primeiro, depois verifica a condição
        do {
            System.out.println("Número: " + numero);
            numero++;
        } while (numero <= 5);  // Note o ponto e vírgula aqui!
        
        System.out.println("\nDemonstração: do-while executa pelo menos uma vez");
        int valor = 10;  // Valor maior que 5
        do {
            System.out.println("Este código executa mesmo com valor = " + valor);
            valor++;
        } while (valor <= 5);  // Condição falsa, mas executou uma vez
        
        System.out.println("\nExplicação do do-while:");
        System.out.println("• Executa o código PRIMEIRO, depois verifica a condição");
        System.out.println("• Garante que o código execute pelo menos uma vez");
        System.out.println("• Útil para menus e validações de entrada");
    }
    
    /**
     * Demonstra o uso do loop FOR-EACH (Enhanced for)
     * Ideal para percorrer arrays e coleções
     */
    public static void exemploForEach() {
        System.out.println("EXEMPLO 4: Loop FOR-EACH (Enhanced For)");
        
        // Array de strings
        String[] carros = {"Volkswagen", "Ford", "Chevrolet", "Toyota", "Honda"};
        
        System.out.println("Lista de carros usando for-each:");
        // Sintaxe: for(tipo variavel : array/coleção)
        for (String carro : carros) {
            System.out.println("• " + carro);
        }
        
        System.out.println("\nComparação - mesmo resultado com for tradicional:");
        for (int i = 0; i < carros.length; i++) {
            System.out.println("• " + carros[i]);
        }
        
        // Array de números
        int[] numeros = {10, 20, 30, 40, 50};
        System.out.println("\nSoma de números usando for-each:");
        int soma = 0;
        for (int num : numeros) {
            soma += num;
            System.out.println("Somando " + num + " → Total atual: " + soma);
        }
        
        System.out.println("\nExplicação do for-each:");
        System.out.println("• Mais simples e legível para percorrer arrays/coleções");
        System.out.println("• Não precisa se preocupar com índices");
        System.out.println("• Não permite modificar os elementos do array");
        System.out.println("• Ideal quando só precisa LER os valores");
    }
    
    /**
     * Demonstra o uso do comando CONTINUE
     * Continue pula para a próxima iteração do loop
     */
    public static void exemploForContinue() {
        System.out.println("EXEMPLO 5: Comando CONTINUE");
        System.out.println("Imprimindo números de 1 a 10, pulando os pares:");
        
        for (int i = 1; i <= 10; i++) {
            // Se o número for par, pula para a próxima iteração
            if (i % 2 == 0) {
                continue;  // Pula o resto do código e vai para i++
            }
            System.out.println("Número ímpar: " + i);
        }
        
        System.out.println("\nExplicação do continue:");
        System.out.println("• Pula o resto do código na iteração atual");
        System.out.println("• Vai direto para a próxima iteração do loop");
        System.out.println("• Útil para filtrar valores indesejados");
    }
    
    /**
     * Demonstra o uso do comando BREAK
     * Break sai completamente do loop
     */
    public static void exemploForBreak() {
        System.out.println("EXEMPLO 6: Comando BREAK");
        System.out.println("Procurando o primeiro número maior que 15:");
        
        int[] numeros = {5, 10, 12, 18, 25, 30};
        
        for (int numero : numeros) {
            System.out.println("Verificando: " + numero);
            
            if (numero > 15) {
                System.out.println("✓ Encontrado! " + numero + " é maior que 15");
                break;  // Sai do loop completamente
            }
            
            System.out.println("  " + numero + " não é maior que 15, continuando...");
        }
        
        System.out.println("\nExplicação do break:");
        System.out.println("• Sai completamente do loop atual");
        System.out.println("• Útil para parar quando encontrar o que procura");
        System.out.println("• Pode ser usado em qualquer tipo de loop");
    }
    
    /**
     * Demonstra loops aninhados (loop dentro de loop) com arrays bidimensionais
     */
    public static void exemploArrayMultiInt() {
        System.out.println("EXEMPLO 7: Loops Aninhados - Array Bidimensional");
        
        // Array bidimensional (matriz) - array de arrays
        int[][] matriz = { 
            {1, 2, 3, 4}, 
            {5, 6, 7}, 
            {8, 9, 10, 11, 12}
        };
        
        System.out.println("Imprimindo matriz com loops aninhados:");
        
        // Loop externo: percorre as linhas (cada sub-array)
        for (int linha = 0; linha < matriz.length; linha++) {
            System.out.print("Linha " + linha + ": ");
            
            // Loop interno: percorre as colunas (elementos de cada linha)
            for (int coluna = 0; coluna < matriz[linha].length; coluna++) {
                System.out.print(matriz[linha][coluna] + " ");
            }
            System.out.println();  // Nova linha após cada linha da matriz
        }
        
        System.out.println("\nMesmo resultado usando for-each aninhado:");
        int numeroLinha = 0;
        for (int[] linha : matriz) {
            System.out.print("Linha " + numeroLinha + ": ");
            for (int elemento : linha) {
                System.out.print(elemento + " ");
            }
            System.out.println();
            numeroLinha++;
        }
        
        System.out.println("\nExplicação dos loops aninhados:");
        System.out.println("• Loop externo controla as linhas");
        System.out.println("• Loop interno controla as colunas");
        System.out.println("• Para cada linha, percorre todas as colunas");
        System.out.println("• Usado para matrizes, tabelas, grades, etc.");
    }
    
    /**
     * Exemplos práticos usando diferentes tipos de loops
     */
    public static void exemplosPraticos() {
        System.out.println("EXEMPLO 8: Aplicações Práticas dos Loops");
        
        // 1. Calculando fatorial usando for
        System.out.println("1. Calculando fatorial de 5:");
        int numero = 5;
        int fatorial = 1;
        
        for (int i = 1; i <= numero; i++) {
            fatorial *= i;
            System.out.println("  " + i + "! = " + fatorial);
        }
        
        // 2. Encontrando o maior número em um array
        System.out.println("\n2. Encontrando o maior número:");
        int[] valores = {23, 67, 12, 89, 45, 34};
        int maior = valores[0];  // Assume que o primeiro é o maior
        
        for (int valor : valores) {
            if (valor > maior) {
                maior = valor;
            }
        }
        System.out.println("  Maior número: " + maior);
        
        // 3. Contando caracteres específicos
        System.out.println("\n3. Contando letras 'a' em uma palavra:");
        String palavra = "programacao";
        int contadorA = 0;
        
        for (int i = 0; i < palavra.length(); i++) {
            if (palavra.charAt(i) == 'a') {
                contadorA++;
            }
        }
        System.out.println("  A palavra '" + palavra + "' tem " + contadorA + " letras 'a'");
        
        // 4. Gerando tabuada
        System.out.println("\n4. Tabuada do 7:");
        for (int i = 1; i <= 10; i++) {
            System.out.println("  7 x " + i + " = " + (7 * i));
        }
        
        System.out.println("\n=== RESUMO DOS TIPOS DE LOOP ===");
        System.out.println("• FOR: quando souber quantas repetições");
        System.out.println("• WHILE: quando a condição pode mudar durante a execução");
        System.out.println("• DO-WHILE: quando precisar executar pelo menos uma vez");
        System.out.println("• FOR-EACH: para percorrer arrays/coleções de forma simples");
        System.out.println("• CONTINUE: pula para a próxima iteração");
        System.out.println("• BREAK: sai do loop completamente");
    }
}
