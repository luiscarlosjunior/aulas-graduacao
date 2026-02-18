/**
 * Demonstração de Arrays (Vetores e Matrizes) em Java
 * 
 * Este programa demonstra como criar, usar e manipular arrays em Java.
 * Arrays são estruturas de dados que permitem armazenar múltiplos valores
 * do mesmo tipo em uma única variável.
 * 
 * Conceitos abordados:
 * - Declaração e inicialização de arrays
 * - Arrays unidimensionais (vetores)
 * - Arrays bidimensionais (matrizes)
 * - Percorrer arrays com loops
 * - Métodos úteis para trabalhar com arrays
 * 
 * @author luiscaparroz
 * @version 1.0
 * @since JDK 1.8
 */
public class ExemplosArrays {

    /**
     * Método principal que demonstra diferentes usos de arrays
     * @param args argumentos da linha de comando
     */
    public static void main(String[] args) {
        
        System.out.println("=== DEMONSTRAÇÃO DE ARRAYS EM JAVA ===\n");
        
        exemploArraysBasicos();
        System.out.println("\n" + "=".repeat(60) + "\n");
        
        exemploArraysInicializacao();
        System.out.println("\n" + "=".repeat(60) + "\n");
        
        exemploOperacoesArrays();
        System.out.println("\n" + "=".repeat(60) + "\n");
        
        exemploArraysBidimensionais();
        System.out.println("\n" + "=".repeat(60) + "\n");
        
        exemploArraysEstrings();
        System.out.println("\n" + "=".repeat(60) + "\n");
        
        exemplosPraticosArrays();
    }
    
    /**
     * Demonstra a declaração e uso básico de arrays
     */
    public static void exemploArraysBasicos() {
        System.out.println("EXEMPLO 1: Arrays Básicos");
        
        // Declaração de array de inteiros com tamanho 5
        int[] numeros = new int[5];
        
        // Atribuindo valores individualmente
        numeros[0] = 10;    // Primeiro elemento (índice 0)
        numeros[1] = 20;    // Segundo elemento (índice 1)
        numeros[2] = 30;    // Terceiro elemento (índice 2)
        numeros[3] = 40;    // Quarto elemento (índice 3)
        numeros[4] = 50;    // Quinto elemento (índice 4)
        
        System.out.println("Array de inteiros:");
        System.out.println("Tamanho do array: " + numeros.length);
        
        // Percorrendo o array com loop tradicional
        for (int i = 0; i < numeros.length; i++) {
            System.out.println("Posição " + i + ": " + numeros[i]);
        }
        
        System.out.println("\nMesmo array usando for-each:");
        int posicao = 0;
        for (int numero : numeros) {
            System.out.println("Posição " + posicao + ": " + numero);
            posicao++;
        }
        
        System.out.println("\nCONCEITOS IMPORTANTES:");
        System.out.println("• Arrays em Java começam no índice 0");
        System.out.println("• O último índice é sempre (tamanho - 1)");
        System.out.println("• Tamanho é fixo após a criação");
        System.out.println("• Todos os elementos são do mesmo tipo");
    }
    
    /**
     * Demonstra diferentes formas de inicializar arrays
     */
    public static void exemploArraysInicializacao() {
        System.out.println("EXEMPLO 2: Formas de Inicializar Arrays");
        
        // Forma 1: Declaração com tamanho e atribuição posterior
        double[] notas1 = new double[4];
        notas1[0] = 8.5;
        notas1[1] = 9.0;
        notas1[2] = 7.5;
        notas1[3] = 8.8;
        
        // Forma 2: Inicialização direta com valores
        double[] notas2 = {8.5, 9.0, 7.5, 8.8};
        
        // Forma 3: Usando new com valores
        double[] notas3 = new double[]{8.5, 9.0, 7.5, 8.8};
        
        System.out.println("Três arrays com os mesmos valores:");
        
        System.out.print("Array 1: ");
        for (double nota : notas1) {
            System.out.print(nota + " ");
        }
        
        System.out.print("\nArray 2: ");
        for (double nota : notas2) {
            System.out.print(nota + " ");
        }
        
        System.out.print("\nArray 3: ");
        for (double nota : notas3) {
            System.out.print(nota + " ");
        }
        
        // Array de strings
        System.out.println("\n\nArray de strings:");
        String[] diasSemana = {
            "Segunda-feira", "Terça-feira", "Quarta-feira", 
            "Quinta-feira", "Sexta-feira", "Sábado", "Domingo"
        };
        
        for (int i = 0; i < diasSemana.length; i++) {
            System.out.println((i + 1) + ". " + diasSemana[i]);
        }
    }
    
    /**
     * Demonstra operações comuns com arrays
     */
    public static void exemploOperacoesArrays() {
        System.out.println("EXEMPLO 3: Operações com Arrays");
        
        int[] valores = {45, 23, 67, 12, 89, 34, 56};
        
        System.out.println("Array original:");
        for (int valor : valores) {
            System.out.print(valor + " ");
        }
        
        // Encontrar o maior valor
        int maior = valores[0];  // Assume que o primeiro é o maior
        for (int valor : valores) {
            if (valor > maior) {
                maior = valor;
            }
        }
        System.out.println("\nMaior valor: " + maior);
        
        // Encontrar o menor valor
        int menor = valores[0];  // Assume que o primeiro é o menor
        for (int valor : valores) {
            if (valor < menor) {
                menor = valor;
            }
        }
        System.out.println("Menor valor: " + menor);
        
        // Calcular a soma
        int soma = 0;
        for (int valor : valores) {
            soma += valor;
        }
        System.out.println("Soma de todos os valores: " + soma);
        
        // Calcular a média
        double media = (double) soma / valores.length;
        System.out.println("Média: " + String.format("%.2f", media));
        
        // Contar quantos números são maiores que a média
        int acimaDaMedia = 0;
        for (int valor : valores) {
            if (valor > media) {
                acimaDaMedia++;
            }
        }
        System.out.println("Números acima da média: " + acimaDaMedia);
        
        // Buscar um valor específico
        int valorProcurado = 67;
        boolean encontrado = false;
        int posicaoEncontrada = -1;
        
        for (int i = 0; i < valores.length; i++) {
            if (valores[i] == valorProcurado) {
                encontrado = true;
                posicaoEncontrada = i;
                break;  // Para na primeira ocorrência
            }
        }
        
        if (encontrado) {
            System.out.println("Valor " + valorProcurado + " encontrado na posição " + posicaoEncontrada);
        } else {
            System.out.println("Valor " + valorProcurado + " não encontrado");
        }
    }
    
    /**
     * Demonstra arrays bidimensionais (matrizes)
     */
    public static void exemploArraysBidimensionais() {
        System.out.println("EXEMPLO 4: Arrays Bidimensionais (Matrizes)");
        
        // Criando uma matriz 3x4 (3 linhas, 4 colunas)
        int[][] matriz = new int[3][4];
        
        // Preenchendo a matriz com valores
        int contador = 1;
        for (int linha = 0; linha < matriz.length; linha++) {
            for (int coluna = 0; coluna < matriz[linha].length; coluna++) {
                matriz[linha][coluna] = contador;
                contador++;
            }
        }
        
        System.out.println("Matriz 3x4:");
        for (int linha = 0; linha < matriz.length; linha++) {
            System.out.print("Linha " + linha + ": ");
            for (int coluna = 0; coluna < matriz[linha].length; coluna++) {
                System.out.printf("%3d ", matriz[linha][coluna]);
            }
            System.out.println();
        }
        
        // Matriz inicializada diretamente
        System.out.println("\nMatriz de notas (inicialização direta):");
        double[][] notas = {
            {8.5, 9.0, 7.5},      // Aluno 1
            {7.0, 8.5, 9.5},      // Aluno 2
            {9.5, 8.0, 8.5},      // Aluno 3
            {6.5, 7.5, 8.0}       // Aluno 4
        };
        
        String[] nomeAlunos = {"Alice", "Bruno", "Carlos", "Diana"};
        String[] materias = {"Matemática", "Português", "Ciências"};
        
        // Cabeçalho
        System.out.printf("%-10s", "Aluno");
        for (String materia : materias) {
            System.out.printf("%-12s", materia);
        }
        System.out.printf("%-8s%n", "Média");
        
        // Dados dos alunos
        for (int aluno = 0; aluno < notas.length; aluno++) {
            System.out.printf("%-10s", nomeAlunos[aluno]);
            
            double soma = 0;
            for (int materia = 0; materia < notas[aluno].length; materia++) {
                System.out.printf("%-12.1f", notas[aluno][materia]);
                soma += notas[aluno][materia];
            }
            
            double media = soma / notas[aluno].length;
            System.out.printf("%-8.1f%n", media);
        }
    }
    
    /**
     * Demonstra arrays de strings e manipulação de texto
     */
    public static void exemploArraysEstrings() {
        System.out.println("EXEMPLO 5: Arrays de Strings");
        
        String[] frutas = {"Maçã", "Banana", "Laranja", "Uva", "Manga"};
        
        System.out.println("Lista de frutas:");
        for (int i = 0; i < frutas.length; i++) {
            System.out.println((i + 1) + ". " + frutas[i]);
        }
        
        // Buscar fruta que comece com uma letra específica
        char letraProcurada = 'M';
        System.out.println("\nFrutas que começam com '" + letraProcurada + "':");
        
        for (String fruta : frutas) {
            if (fruta.charAt(0) == letraProcurada) {
                System.out.println("• " + fruta);
            }
        }
        
        // Ordenação simples (bubble sort básico)
        String[] frutasOrdenadas = frutas.clone();  // Cópia para não alterar o original
        
        for (int i = 0; i < frutasOrdenadas.length - 1; i++) {
            for (int j = 0; j < frutasOrdenadas.length - 1 - i; j++) {
                if (frutasOrdenadas[j].compareToIgnoreCase(frutasOrdenadas[j + 1]) > 0) {
                    // Troca os elementos
                    String temp = frutasOrdenadas[j];
                    frutasOrdenadas[j] = frutasOrdenadas[j + 1];
                    frutasOrdenadas[j + 1] = temp;
                }
            }
        }
        
        System.out.println("\nFrutas em ordem alfabética:");
        for (String fruta : frutasOrdenadas) {
            System.out.println("• " + fruta);
        }
    }
    
    /**
     * Exemplos práticos do uso de arrays
     */
    public static void exemplosPraticosArrays() {
        System.out.println("EXEMPLO 6: Aplicações Práticas");
        
        // 1. Sistema de votação simples
        System.out.println("1. Sistema de Votação:");
        String[] candidatos = {"Alice", "Bruno", "Carlos"};
        int[] votos = {45, 38, 52};  // Votos para cada candidato
        
        int totalVotos = 0;
        for (int voto : votos) {
            totalVotos += voto;
        }
        
        System.out.println("Resultado da eleição:");
        for (int i = 0; i < candidatos.length; i++) {
            double percentual = (double) votos[i] / totalVotos * 100;
            System.out.printf("%-8s: %d votos (%.1f%%)%n", 
                            candidatos[i], votos[i], percentual);
        }
        
        // 2. Controle de estoque
        System.out.println("\n2. Controle de Estoque:");
        String[] produtos = {"Notebook", "Mouse", "Teclado", "Monitor"};
        int[] quantidades = {15, 45, 23, 8};
        double[] precos = {2500.00, 89.90, 159.90, 899.99};
        
        System.out.printf("%-12s %-10s %-10s %-15s%n", 
                        "Produto", "Qtd", "Preço", "Valor Total");
        System.out.println("-".repeat(50));
        
        double valorTotalEstoque = 0;
        for (int i = 0; i < produtos.length; i++) {
            double valorProduto = quantidades[i] * precos[i];
            valorTotalEstoque += valorProduto;
            
            System.out.printf("%-12s %-10d R$%-8.2f R$%-12.2f%n", 
                            produtos[i], quantidades[i], precos[i], valorProduto);
        }
        System.out.println("-".repeat(50));
        System.out.printf("Valor total do estoque: R$ %.2f%n", valorTotalEstoque);
        
        // 3. Análise de temperaturas
        System.out.println("\n3. Análise de Temperaturas da Semana:");
        String[] dias = {"Seg", "Ter", "Qua", "Qui", "Sex", "Sáb", "Dom"};
        double[] temperaturas = {22.5, 25.0, 23.8, 21.2, 24.5, 26.8, 25.3};
        
        double somaTemp = 0;
        double maiorTemp = temperaturas[0];
        double menorTemp = temperaturas[0];
        String diaMaior = dias[0];
        String diaMenor = dias[0];
        
        for (int i = 0; i < temperaturas.length; i++) {
            somaTemp += temperaturas[i];
            
            if (temperaturas[i] > maiorTemp) {
                maiorTemp = temperaturas[i];
                diaMaior = dias[i];
            }
            
            if (temperaturas[i] < menorTemp) {
                menorTemp = temperaturas[i];
                diaMenor = dias[i];
            }
        }
        
        double mediaTemp = somaTemp / temperaturas.length;
        
        System.out.println("Temperaturas da semana:");
        for (int i = 0; i < dias.length; i++) {
            System.out.printf("%s: %.1f°C%n", dias[i], temperaturas[i]);
        }
        
        System.out.printf("\nResumo semanal:%n");
        System.out.printf("Temperatura média: %.1f°C%n", mediaTemp);
        System.out.printf("Maior temperatura: %.1f°C (%s)%n", maiorTemp, diaMaior);
        System.out.printf("Menor temperatura: %.1f°C (%s)%n", menorTemp, diaMenor);
        
        System.out.println("\n=== RESUMO SOBRE ARRAYS ===");
        System.out.println("• Arrays armazenam múltiplos valores do mesmo tipo");
        System.out.println("• Índices começam em 0 e vão até (tamanho - 1)");
        System.out.println("• Tamanho é fixo após a criação");
        System.out.println("• Use .length para obter o tamanho");
        System.out.println("• For-each é mais simples para percorrer arrays");
        System.out.println("• Arrays bidimensionais são úteis para tabelas/matrizes");
    }
}