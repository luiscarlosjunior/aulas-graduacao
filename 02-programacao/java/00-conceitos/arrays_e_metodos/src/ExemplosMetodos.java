/**
 * Demonstração de Métodos em Java
 * 
 * Este programa demonstra como criar e usar métodos em Java.
 * Métodos são blocos de código que realizam uma tarefa específica
 * e podem ser reutilizados em diferentes partes do programa.
 * 
 * Conceitos abordados:
 * - Declaração e chamada de métodos
 * - Métodos com e sem parâmetros
 * - Métodos com e sem retorno
 * - Métodos estáticos vs não-estáticos
 * - Sobrecarga de métodos (overloading)
 * - Escopo de variáveis
 * - Passagem de parâmetros por valor
 * 
 * @author luiscaparroz
 * @version 1.0
 * @since JDK 1.8
 */
public class ExemplosMetodos {

    /**
     * Método principal - ponto de entrada do programa
     * @param args argumentos da linha de comando
     */
    public static void main(String[] args) {
        
        System.out.println("=== DEMONSTRAÇÃO DE MÉTODOS EM JAVA ===\n");
        
        exemploMetodosBasicos();
        System.out.println("\n" + "=".repeat(60) + "\n");
        
        exemploMetodosComParametros();
        System.out.println("\n" + "=".repeat(60) + "\n");
        
        exemploMetodosComRetorno();
        System.out.println("\n" + "=".repeat(60) + "\n");
        
        exemploSobrecargaMetodos();
        System.out.println("\n" + "=".repeat(60) + "\n");
        
        exemploMetodosComArrays();
        System.out.println("\n" + "=".repeat(60) + "\n");
        
        exemploEscopoVariaveis();
        System.out.println("\n" + "=".repeat(60) + "\n");
        
        exemplosPraticosMetodos();
    }
    
    /**
     * Demonstra métodos básicos sem parâmetros e sem retorno
     */
    public static void exemploMetodosBasicos() {
        System.out.println("EXEMPLO 1: Métodos Básicos");
        
        // Chamando métodos sem parâmetros
        exibirCabecalho();
        exibirSeparador();
        exibirRodape();
        
        System.out.println("\nVantagens dos métodos:");
        System.out.println("• Reutilização de código");
        System.out.println("• Organização e legibilidade");
        System.out.println("• Facilita manutenção");
        System.out.println("• Evita repetição de código");
    }
    
    /**
     * Demonstra métodos com diferentes tipos de parâmetros
     */
    public static void exemploMetodosComParametros() {
        System.out.println("EXEMPLO 2: Métodos com Parâmetros");
        
        // Métodos com um parâmetro
        saudarPessoa("Maria");
        saudarPessoa("João");
        
        // Métodos com múltiplos parâmetros
        exibirDadosPessoa("Carlos", 25, "Engenheiro");
        exibirDadosPessoa("Ana", 30, "Médica");
        
        // Método com parâmetros de diferentes tipos
        calcularIdade(1995, 2024);
        calcularIdade(1988, 2024);
        
        System.out.println("\nTipos de parâmetros:");
        System.out.println("• Um parâmetro: método(tipo parametro)");
        System.out.println("• Múltiplos parâmetros: método(tipo1 param1, tipo2 param2)");
        System.out.println("• Parâmetros são separados por vírgula");
    }
    
    /**
     * Demonstra métodos que retornam valores
     */
    public static void exemploMetodosComRetorno() {
        System.out.println("EXEMPLO 3: Métodos com Retorno");
        
        // Métodos que retornam valores simples
        int soma = somar(10, 20);
        System.out.println("Soma de 10 + 20 = " + soma);
        
        double media = calcularMedia(8.5, 9.0, 7.5);
        System.out.println("Média de 8.5, 9.0, 7.5 = " + String.format("%.2f", media));
        
        boolean ehPar = verificarSePar(42);
        System.out.println("O número 42 é par? " + ehPar);
        
        String mensagem = obterSaudacao("Bom dia");
        System.out.println(mensagem);
        
        // Usando retorno em expressões
        int resultado = somar(5, 3) * 2;
        System.out.println("(5 + 3) * 2 = " + resultado);
        
        // Usando retorno em condicionais
        if (verificarSePar(15)) {
            System.out.println("15 é par");
        } else {
            System.out.println("15 é ímpar");
        }
        
        System.out.println("\nTipos de retorno:");
        System.out.println("• void: não retorna nada");
        System.out.println("• int, double, boolean, String: retorna valor do tipo especificado");
        System.out.println("• Use 'return valor;' para retornar um valor");
    }
    
    /**
     * Demonstra sobrecarga de métodos (método com mesmo nome, parâmetros diferentes)
     */
    public static void exemploSobrecargaMetodos() {
        System.out.println("EXEMPLO 4: Sobrecarga de Métodos");
        
        // Mesmo nome de método, diferentes assinaturas
        System.out.println("Cálculos com diferentes tipos:");
        
        int resultadoInt = calcular(10, 5);
        System.out.println("Calcular(int, int): " + resultadoInt);
        
        double resultadoDouble = calcular(10.5, 5.2);
        System.out.println("Calcular(double, double): " + String.format("%.2f", resultadoDouble));
        
        int resultadoTres = calcular(10, 5, 3);
        System.out.println("Calcular(int, int, int): " + resultadoTres);
        
        String resultadoString = calcular("Olá", "Mundo");
        System.out.println("Calcular(String, String): " + resultadoString);
        
        System.out.println("\nSobrecarga permite:");
        System.out.println("• Mesmo nome de método com parâmetros diferentes");
        System.out.println("• Diferentes quantidades de parâmetros");
        System.out.println("• Diferentes tipos de parâmetros");
        System.out.println("• Compilador escolhe o método correto automaticamente");
    }
    
    /**
     * Demonstra métodos que trabalham com arrays
     */
    public static void exemploMetodosComArrays() {
        System.out.println("EXEMPLO 5: Métodos com Arrays");
        
        int[] numeros = {45, 23, 67, 12, 89, 34};
        
        System.out.println("Array original:");
        exibirArray(numeros);
        
        int maior = encontrarMaior(numeros);
        System.out.println("Maior número: " + maior);
        
        double mediaArray = calcularMediaArray(numeros);
        System.out.println("Média: " + String.format("%.2f", mediaArray));
        
        int[] numerosDobrados = dobrarElementos(numeros);
        System.out.println("Números dobrados:");
        exibirArray(numerosDobrados);
        
        // Verificar se um número existe no array
        boolean existe = existeNoArray(numeros, 67);
        System.out.println("O número 67 existe no array? " + existe);
        
        // Contar elementos pares
        int pares = contarPares(numeros);
        System.out.println("Quantidade de números pares: " + pares);
    }
    
    /**
     * Demonstra escopo de variáveis em métodos
     */
    public static void exemploEscopoVariaveis() {
        System.out.println("EXEMPLO 6: Escopo de Variáveis");
        
        // Variável local do método main
        int variavelLocal = 100;
        
        System.out.println("Variável local no main: " + variavelLocal);
        
        // Chamando método que tem sua própria variável local
        demonstrarEscopoLocal();
        
        // A variável do outro método não é acessível aqui
        System.out.println("Variável local no main ainda é: " + variavelLocal);
        
        // Demonstrando passagem por valor
        int numero = 10;
        System.out.println("Antes de chamar método: " + numero);
        
        tentarModificarNumero(numero);
        System.out.println("Depois de chamar método: " + numero);
        
        System.out.println("\nRegras de escopo:");
        System.out.println("• Variáveis declaradas em um método só existem nesse método");
        System.out.println("• Parâmetros são cópias dos valores passados");
        System.out.println("• Modificar parâmetro não afeta a variável original");
        System.out.println("• Java usa passagem por valor para tipos primitivos");
    }
    
    /**
     * Exemplos práticos de uso de métodos
     */
    public static void exemplosPraticosMetodos() {
        System.out.println("EXEMPLO 7: Aplicações Práticas");
        
        // 1. Sistema de validação
        System.out.println("1. Sistema de Validação:");
        validarDados("joao@email.com", "12345678", 25);
        validarDados("email_invalido", "123", 15);
        
        // 2. Conversor de temperatura
        System.out.println("\n2. Conversor de Temperatura:");
        double tempCelsius = 25.0;
        double tempFahrenheit = celsiusParaFahrenheit(tempCelsius);
        double tempKelvin = celsiusParaKelvin(tempCelsius);
        
        System.out.printf("%.1f°C = %.1f°F = %.1fK%n", 
                        tempCelsius, tempFahrenheit, tempKelvin);
        
        // 3. Gerador de relatório
        System.out.println("\n3. Relatório de Vendas:");
        double[] vendas = {1500.50, 2300.75, 1800.00, 2100.25, 1950.80};
        gerarRelatorioVendas(vendas);
        
        // 4. Sistema de notas
        System.out.println("\n4. Sistema de Avaliação:");
        avaliarAluno("Maria", 8.5, 9.0, 7.5, 8.8);
        avaliarAluno("João", 6.0, 5.5, 7.0, 6.2);
    }
    
    // =========================== MÉTODOS AUXILIARES ===========================
    
    /**
     * Exibe um cabeçalho decorativo
     */
    public static void exibirCabecalho() {
        System.out.println("╔════════════════════════════════════╗");
        System.out.println("║        SISTEMA DE EXEMPLO          ║");
        System.out.println("╚════════════════════════════════════╝");
    }
    
    /**
     * Exibe um separador visual
     */
    public static void exibirSeparador() {
        System.out.println("────────────────────────────────────");
    }
    
    /**
     * Exibe um rodapé
     */
    public static void exibirRodape() {
        System.out.println("        Obrigado por usar!           ");
    }
    
    /**
     * Saúda uma pessoa pelo nome
     * @param nome o nome da pessoa a ser saudada
     */
    public static void saudarPessoa(String nome) {
        System.out.println("Olá, " + nome + "! Seja bem-vindo(a)!");
    }
    
    /**
     * Exibe dados completos de uma pessoa
     * @param nome nome da pessoa
     * @param idade idade da pessoa
     * @param profissao profissão da pessoa
     */
    public static void exibirDadosPessoa(String nome, int idade, String profissao) {
        System.out.println("Nome: " + nome + " | Idade: " + idade + " | Profissão: " + profissao);
    }
    
    /**
     * Calcula e exibe a idade baseada no ano de nascimento
     * @param anoNascimento ano em que a pessoa nasceu
     * @param anoAtual ano atual
     */
    public static void calcularIdade(int anoNascimento, int anoAtual) {
        int idade = anoAtual - anoNascimento;
        System.out.println("Quem nasceu em " + anoNascimento + " tem " + idade + " anos em " + anoAtual);
    }
    
    /**
     * Soma dois números inteiros
     * @param a primeiro número
     * @param b segundo número
     * @return a soma dos dois números
     */
    public static int somar(int a, int b) {
        return a + b;
    }
    
    /**
     * Calcula a média de três números
     * @param a primeiro número
     * @param b segundo número
     * @param c terceiro número
     * @return a média dos três números
     */
    public static double calcularMedia(double a, double b, double c) {
        return (a + b + c) / 3.0;
    }
    
    /**
     * Verifica se um número é par
     * @param numero o número a ser verificado
     * @return true se o número for par, false caso contrário
     */
    public static boolean verificarSePar(int numero) {
        return numero % 2 == 0;
    }
    
    /**
     * Cria uma mensagem de saudação
     * @param periodo período do dia (ex: "Bom dia")
     * @return mensagem de saudação personalizada
     */
    public static String obterSaudacao(String periodo) {
        return periodo + "! Espero que tenha um excelente dia!";
    }
    
    // ===================== MÉTODOS SOBRECARREGADOS =====================
    
    /**
     * Multiplica dois números inteiros
     */
    public static int calcular(int a, int b) {
        return a * b;
    }
    
    /**
     * Multiplica dois números decimais
     */
    public static double calcular(double a, double b) {
        return a * b;
    }
    
    /**
     * Multiplica três números inteiros
     */
    public static int calcular(int a, int b, int c) {
        return a * b * c;
    }
    
    /**
     * Concatena duas strings
     */
    public static String calcular(String a, String b) {
        return a + " " + b;
    }
    
    // ===================== MÉTODOS PARA ARRAYS =====================
    
    /**
     * Exibe todos os elementos de um array
     * @param array o array a ser exibido
     */
    public static void exibirArray(int[] array) {
        System.out.print("[ ");
        for (int elemento : array) {
            System.out.print(elemento + " ");
        }
        System.out.println("]");
    }
    
    /**
     * Encontra o maior elemento em um array
     * @param array o array a ser analisado
     * @return o maior valor encontrado
     */
    public static int encontrarMaior(int[] array) {
        int maior = array[0];
        for (int elemento : array) {
            if (elemento > maior) {
                maior = elemento;
            }
        }
        return maior;
    }
    
    /**
     * Calcula a média dos elementos de um array
     * @param array o array a ser analisado
     * @return a média dos elementos
     */
    public static double calcularMediaArray(int[] array) {
        int soma = 0;
        for (int elemento : array) {
            soma += elemento;
        }
        return (double) soma / array.length;
    }
    
    /**
     * Cria um novo array com todos os elementos dobrados
     * @param array o array original
     * @return novo array com elementos dobrados
     */
    public static int[] dobrarElementos(int[] array) {
        int[] resultado = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            resultado[i] = array[i] * 2;
        }
        return resultado;
    }
    
    /**
     * Verifica se um valor existe no array
     * @param array o array a ser pesquisado
     * @param valor o valor a ser procurado
     * @return true se o valor existir, false caso contrário
     */
    public static boolean existeNoArray(int[] array, int valor) {
        for (int elemento : array) {
            if (elemento == valor) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Conta quantos números pares existem no array
     * @param array o array a ser analisado
     * @return quantidade de números pares
     */
    public static int contarPares(int[] array) {
        int contador = 0;
        for (int elemento : array) {
            if (elemento % 2 == 0) {
                contador++;
            }
        }
        return contador;
    }
    
    // ===================== MÉTODOS DE ESCOPO =====================
    
    /**
     * Demonstra variável local em método
     */
    public static void demonstrarEscopoLocal() {
        int variavelLocal = 200;  // Variável diferente da do main
        System.out.println("Variável local no método: " + variavelLocal);
    }
    
    /**
     * Tenta modificar um número (demonstra passagem por valor)
     * @param numero número a ser "modificado"
     */
    public static void tentarModificarNumero(int numero) {
        numero = 999;  // Modifica apenas a cópia local
        System.out.println("Dentro do método: " + numero);
    }
    
    // ===================== MÉTODOS PRÁTICOS =====================
    
    /**
     * Valida dados de entrada
     */
    public static void validarDados(String email, String senha, int idade) {
        System.out.printf("Validando: %s, senha: %s, idade: %d%n", email, senha, idade);
        
        boolean emailValido = email.contains("@") && email.contains(".");
        boolean senhaValida = senha.length() >= 6;
        boolean idadeValida = idade >= 18;
        
        System.out.println("Email válido: " + emailValido);
        System.out.println("Senha válida: " + senhaValida);
        System.out.println("Idade válida: " + idadeValida);
        
        boolean todosValidos = emailValido && senhaValida && idadeValida;
        System.out.println("Cadastro aprovado: " + todosValidos);
    }
    
    /**
     * Converte Celsius para Fahrenheit
     */
    public static double celsiusParaFahrenheit(double celsius) {
        return (celsius * 9.0 / 5.0) + 32.0;
    }
    
    /**
     * Converte Celsius para Kelvin
     */
    public static double celsiusParaKelvin(double celsius) {
        return celsius + 273.15;
    }
    
    /**
     * Gera relatório de vendas
     */
    public static void gerarRelatorioVendas(double[] vendas) {
        double total = 0;
        double maior = vendas[0];
        double menor = vendas[0];
        
        for (double venda : vendas) {
            total += venda;
            if (venda > maior) maior = venda;
            if (venda < menor) menor = venda;
        }
        
        double media = total / vendas.length;
        
        System.out.printf("Total de vendas: R$ %.2f%n", total);
        System.out.printf("Média de vendas: R$ %.2f%n", media);
        System.out.printf("Maior venda: R$ %.2f%n", maior);
        System.out.printf("Menor venda: R$ %.2f%n", menor);
    }
    
    /**
     * Avalia o desempenho de um aluno
     */
    public static void avaliarAluno(String nome, double nota1, double nota2, double nota3, double nota4) {
        double media = (nota1 + nota2 + nota3 + nota4) / 4.0;
        String conceito;
        
        if (media >= 9.0) {
            conceito = "EXCELENTE";
        } else if (media >= 8.0) {
            conceito = "MUITO BOM";
        } else if (media >= 7.0) {
            conceito = "BOM";
        } else if (media >= 6.0) {
            conceito = "REGULAR";
        } else {
            conceito = "INSUFICIENTE";
        }
        
        System.out.printf("Aluno: %s | Média: %.2f | Conceito: %s%n", nome, media, conceito);
        
        if (media >= 7.0) {
            System.out.println("✓ APROVADO");
        } else {
            System.out.println("✗ REPROVADO");
        }
    }
}