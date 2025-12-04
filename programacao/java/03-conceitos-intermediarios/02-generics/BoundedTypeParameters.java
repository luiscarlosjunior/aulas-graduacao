/**
 * BoundedTypeParameters.java
 * 
 * Demonstra o uso de Bounded Type Parameters (parâmetros de tipo limitados) em Java.
 * 
 * Bounded type parameters permitem restringir os tipos que podem ser usados como
 * argumentos de tipo em classes e métodos genéricos. Isso adiciona uma camada extra
 * de type safety e permite chamar métodos específicos dos tipos limitados.
 * 
 * Tipos de Bounds:
 * - Upper Bound: <T extends Tipo> - T deve ser Tipo ou subtipo
 * - Multiple Bounds: <T extends Classe & Interface1 & Interface2>
 * 
 * Nota: Não existe lower bound para type parameters (apenas para wildcards)
 * 
 * @author Aulas de Graduação
 */

import java.util.*;

/**
 * Calculadora que trabalha apenas com tipos numéricos.
 * 
 * O bound <T extends Number> garante que T seja Number ou qualquer subtipo
 * (Integer, Double, Float, Long, etc). Isso permite chamar métodos de Number.
 * 
 * @param <T> tipo numérico (deve ser Number ou subtipo)
 */
class CalculadoraNumerica<T extends Number> {
    private T numero;
    
    public CalculadoraNumerica(T numero) {
        this.numero = numero;
    }
    
    /**
     * Retorna o valor como double.
     * Possível porque Number tem método doubleValue().
     */
    public double obterDouble() {
        return numero.doubleValue();
    }
    
    /**
     * Retorna o valor como int.
     */
    public int obterInt() {
        return numero.intValue();
    }
    
    /**
     * Soma este número com outro.
     */
    public double somar(T outroNumero) {
        return this.numero.doubleValue() + outroNumero.doubleValue();
    }
    
    /**
     * Verifica se é positivo.
     */
    public boolean ehPositivo() {
        return numero.doubleValue() > 0;
    }
    
    @Override
    public String toString() {
        return "Calculadora[" + numero + "]";
    }
}

/**
 * Classe que trabalha com tipos comparáveis.
 * 
 * O bound <T extends Comparable<T>> garante que T pode ser comparado com ele mesmo.
 * Isso permite usar compareTo() e criar métodos de ordenação.
 * 
 * @param <T> tipo comparável
 */
class ComparadorGenerico<T extends Comparable<T>> {
    
    /**
     * Retorna o maior entre dois elementos.
     */
    public T max(T a, T b) {
        return a.compareTo(b) > 0 ? a : b;
    }
    
    /**
     * Retorna o menor entre dois elementos.
     */
    public T min(T a, T b) {
        return a.compareTo(b) < 0 ? a : b;
    }
    
    /**
     * Retorna o maior elemento de uma lista.
     */
    public T maxDaLista(List<T> lista) {
        if (lista == null || lista.isEmpty()) {
            throw new IllegalArgumentException("Lista vazia ou nula");
        }
        
        T maior = lista.get(0);
        for (T elemento : lista) {
            if (elemento.compareTo(maior) > 0) {
                maior = elemento;
            }
        }
        return maior;
    }
    
    /**
     * Ordena uma lista in-place.
     */
    public void ordenar(List<T> lista) {
        Collections.sort(lista);
    }
}

/**
 * Classe demonstrando múltiplos bounds.
 * 
 * O type parameter deve satisfazer TODOS os bounds:
 * - Deve ser Number (ou subtipo)
 * - Deve implementar Comparable
 * 
 * Sintaxe: Classe sempre primeiro, interfaces depois, separados por &
 * 
 * @param <T> tipo que é Number E Comparable
 */
class ProcessadorNumeroComparavel<T extends Number & Comparable<T>> {
    
    /**
     * Retorna o maior número da lista.
     */
    public T maior(List<T> numeros) {
        if (numeros.isEmpty()) {
            return null;
        }
        
        T maior = numeros.get(0);
        for (T numero : numeros) {
            if (numero.compareTo(maior) > 0) {
                maior = numero;
            }
        }
        return maior;
    }
    
    /**
     * Calcula a soma total.
     */
    public double somaTotal(List<T> numeros) {
        double soma = 0;
        for (T numero : numeros) {
            soma += numero.doubleValue();
        }
        return soma;
    }
    
    /**
     * Calcula a média.
     */
    public double media(List<T> numeros) {
        if (numeros.isEmpty()) {
            return 0;
        }
        return somaTotal(numeros) / numeros.size();
    }
}

/**
 * Métodos estáticos genéricos com bounded type parameters.
 */
class UtilitariosBounded {
    
    /**
     * Retorna o maior elemento de um array.
     * Funciona com qualquer tipo Comparable.
     */
    public static <T extends Comparable<T>> T max(T[] array) {
        if (array == null || array.length == 0) {
            return null;
        }
        
        T max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i].compareTo(max) > 0) {
                max = array[i];
            }
        }
        return max;
    }
    
    /**
     * Retorna o menor elemento de um array.
     */
    public static <T extends Comparable<T>> T min(T[] array) {
        if (array == null || array.length == 0) {
            return null;
        }
        
        T min = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i].compareTo(min) < 0) {
                min = array[i];
            }
        }
        return min;
    }
    
    /**
     * Soma elementos numéricos de uma lista.
     * Funciona com Integer, Double, Float, etc.
     */
    public static <T extends Number> double somar(List<T> numeros) {
        double soma = 0;
        for (T numero : numeros) {
            soma += numero.doubleValue();
        }
        return soma;
    }
    
    /**
     * Conta quantos elementos são maiores que um threshold.
     */
    public static <T extends Comparable<T>> int contarMaioresQue(List<T> lista, T threshold) {
        int contador = 0;
        for (T elemento : lista) {
            if (elemento.compareTo(threshold) > 0) {
                contador++;
            }
        }
        return contador;
    }
    
    /**
     * Verifica se uma lista está ordenada.
     */
    public static <T extends Comparable<T>> boolean estaOrdenada(List<T> lista) {
        if (lista.size() <= 1) {
            return true;
        }
        
        for (int i = 0; i < lista.size() - 1; i++) {
            if (lista.get(i).compareTo(lista.get(i + 1)) > 0) {
                return false;
            }
        }
        return true;
    }
}

/**
 * Exemplo de classe que só aceita tipos específicos através de bounded types.
 */
class Pessoa implements Comparable<Pessoa> {
    private String nome;
    private int idade;
    
    public Pessoa(String nome, int idade) {
        this.nome = nome;
        this.idade = idade;
    }
    
    public String getNome() {
        return nome;
    }
    
    public int getIdade() {
        return idade;
    }
    
    @Override
    public int compareTo(Pessoa outra) {
        // Compara por idade primeiro, depois por nome
        int comp = Integer.compare(this.idade, outra.idade);
        if (comp != 0) {
            return comp;
        }
        return this.nome.compareTo(outra.nome);
    }
    
    @Override
    public String toString() {
        return nome + " (" + idade + " anos)";
    }
}

/**
 * Classe principal demonstrando bounded type parameters.
 */
public class BoundedTypeParameters {
    
    public static void main(String[] args) {
        System.out.println("=== BOUNDED TYPE PARAMETERS ===\n");
        
        // ============================================================
        // EXEMPLO 1: Bound com Number
        // ============================================================
        System.out.println("--- Exemplo 1: Bound com Number ---");
        
        CalculadoraNumerica<Integer> calcInt = new CalculadoraNumerica<>(42);
        System.out.println("Integer: " + calcInt);
        System.out.println("Como double: " + calcInt.obterDouble());
        System.out.println("É positivo? " + calcInt.ehPositivo());
        
        CalculadoraNumerica<Double> calcDouble = new CalculadoraNumerica<>(3.14159);
        System.out.println("\nDouble: " + calcDouble);
        System.out.println("Como int: " + calcDouble.obterInt());
        
        double soma = calcInt.somar(10);
        System.out.println("42 + 10 = " + soma);
        
        // ❌ Não compila: String não é Number
        // CalculadoraNumerica<String> calcString = new CalculadoraNumerica<>("teste");
        
        System.out.println();
        
        // ============================================================
        // EXEMPLO 2: Bound com Comparable
        // ============================================================
        System.out.println("--- Exemplo 2: Bound com Comparable ---");
        
        ComparadorGenerico<Integer> compInt = new ComparadorGenerico<>();
        System.out.println("Maior entre 10 e 20: " + compInt.max(10, 20));
        System.out.println("Menor entre 10 e 20: " + compInt.min(10, 20));
        
        ComparadorGenerico<String> compString = new ComparadorGenerico<>();
        System.out.println("Maior entre 'Ana' e 'Bruno': " + compString.max("Ana", "Bruno"));
        
        List<Integer> numeros = Arrays.asList(5, 2, 8, 1, 9, 3);
        System.out.println("Lista: " + numeros);
        System.out.println("Maior da lista: " + compInt.maxDaLista(numeros));
        
        compInt.ordenar(numeros);
        System.out.println("Lista ordenada: " + numeros);
        System.out.println();
        
        // ============================================================
        // EXEMPLO 3: Múltiplos Bounds
        // ============================================================
        System.out.println("--- Exemplo 3: Múltiplos Bounds (Number & Comparable) ---");
        
        ProcessadorNumeroComparavel<Integer> procInt = new ProcessadorNumeroComparavel<>();
        List<Integer> idades = Arrays.asList(25, 30, 18, 45, 22, 35);
        
        System.out.println("Idades: " + idades);
        System.out.println("Maior idade: " + procInt.maior(idades));
        System.out.println("Soma total: " + procInt.somaTotal(idades));
        System.out.println("Média: " + procInt.media(idades));
        
        ProcessadorNumeroComparavel<Double> procDouble = new ProcessadorNumeroComparavel<>();
        List<Double> notas = Arrays.asList(7.5, 8.0, 6.5, 9.0, 7.0);
        
        System.out.println("\nNotas: " + notas);
        System.out.println("Maior nota: " + procDouble.maior(notas));
        System.out.println("Média das notas: " + procDouble.media(notas));
        System.out.println();
        
        // ============================================================
        // EXEMPLO 4: Métodos Estáticos com Bounds
        // ============================================================
        System.out.println("--- Exemplo 4: Métodos Estáticos com Bounds ---");
        
        Integer[] arrayInt = {15, 3, 27, 9, 42, 8};
        System.out.println("Array: " + Arrays.toString(arrayInt));
        System.out.println("Máximo: " + UtilitariosBounded.max(arrayInt));
        System.out.println("Mínimo: " + UtilitariosBounded.min(arrayInt));
        
        String[] arrayString = {"Zebra", "Abelha", "Macaco", "Elefante"};
        System.out.println("\nArray: " + Arrays.toString(arrayString));
        System.out.println("Máximo (alfabético): " + UtilitariosBounded.max(arrayString));
        System.out.println("Mínimo (alfabético): " + UtilitariosBounded.min(arrayString));
        System.out.println();
        
        // ============================================================
        // EXEMPLO 5: Somar Números com Bound
        // ============================================================
        System.out.println("--- Exemplo 5: Somar Números ---");
        
        List<Integer> inteiros = Arrays.asList(1, 2, 3, 4, 5);
        List<Double> decimais = Arrays.asList(1.5, 2.5, 3.5);
        
        double somaInt = UtilitariosBounded.somar(inteiros);
        double somaDouble = UtilitariosBounded.somar(decimais);
        
        System.out.println("Soma de inteiros " + inteiros + ": " + somaInt);
        System.out.println("Soma de decimais " + decimais + ": " + somaDouble);
        System.out.println();
        
        // ============================================================
        // EXEMPLO 6: Contar Maiores Que Threshold
        // ============================================================
        System.out.println("--- Exemplo 6: Contar Maiores Que Threshold ---");
        
        List<Integer> valores = Arrays.asList(10, 25, 15, 30, 5, 35, 20);
        int qtdMaioresQue20 = UtilitariosBounded.contarMaioresQue(valores, 20);
        
        System.out.println("Valores: " + valores);
        System.out.println("Quantidade > 20: " + qtdMaioresQue20);
        
        List<String> palavras = Arrays.asList("casa", "apartamento", "sala", "quarto");
        int qtdMaioresQueQuarto = UtilitariosBounded.contarMaioresQue(palavras, "quarto");
        
        System.out.println("\nPalavras: " + palavras);
        System.out.println("Quantidade > 'quarto' (alfabeticamente): " + qtdMaioresQueQuarto);
        System.out.println();
        
        // ============================================================
        // EXEMPLO 7: Verificar se Lista Está Ordenada
        // ============================================================
        System.out.println("--- Exemplo 7: Verificar Ordenação ---");
        
        List<Integer> ordenada = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> desordenada = Arrays.asList(3, 1, 4, 2, 5);
        
        System.out.println("Lista " + ordenada + " está ordenada? " + 
                          UtilitariosBounded.estaOrdenada(ordenada));
        System.out.println("Lista " + desordenada + " está ordenada? " + 
                          UtilitariosBounded.estaOrdenada(desordenada));
        System.out.println();
        
        // ============================================================
        // EXEMPLO 8: Classe Customizada com Comparable
        // ============================================================
        System.out.println("--- Exemplo 8: Classe Customizada (Pessoa) ---");
        
        List<Pessoa> pessoas = Arrays.asList(
            new Pessoa("Ana", 25),
            new Pessoa("Bruno", 30),
            new Pessoa("Carlos", 25),
            new Pessoa("Diana", 22)
        );
        
        System.out.println("Pessoas:");
        for (Pessoa p : pessoas) {
            System.out.println("  " + p);
        }
        
        ComparadorGenerico<Pessoa> compPessoa = new ComparadorGenerico<>();
        Pessoa maisVelha = compPessoa.maxDaLista(pessoas);
        System.out.println("\nPessoa mais velha: " + maisVelha);
        
        compPessoa.ordenar(pessoas);
        System.out.println("\nPessoas ordenadas por idade:");
        for (Pessoa p : pessoas) {
            System.out.println("  " + p);
        }
        System.out.println();
        
        // ============================================================
        // EXEMPLO 9: Demonstração de Restrições
        // ============================================================
        System.out.println("--- Exemplo 9: Restrições de Bounded Types ---");
        
        // ✅ Tipos que funcionam com Number:
        System.out.println("Tipos válidos para <T extends Number>:");
        System.out.println("  - Integer, Double, Float, Long, Short, Byte");
        
        List<Integer> listaInt = Arrays.asList(1, 2, 3);
        List<Double> listaDouble = Arrays.asList(1.1, 2.2, 3.3);
        
        System.out.println("  Soma de Integer: " + UtilitariosBounded.somar(listaInt));
        System.out.println("  Soma de Double: " + UtilitariosBounded.somar(listaDouble));
        
        // ❌ Tipos que NÃO funcionam:
        System.out.println("\nTipos INVÁLIDOS para <T extends Number>:");
        System.out.println("  - String, Boolean, Object, etc.");
        // List<String> listaString = Arrays.asList("a", "b");
        // UtilitariosBounded.somar(listaString); // ❌ Erro de compilação!
        
        System.out.println();
        
        // ============================================================
        // RESUMO
        // ============================================================
        System.out.println("=== RESUMO ===");
        System.out.println("✅ Upper bound: <T extends Tipo>");
        System.out.println("✅ Permite chamar métodos do tipo bound");
        System.out.println("✅ Múltiplos bounds: <T extends Classe & Interface1 & Interface2>");
        System.out.println("✅ Classe sempre primeiro nos múltiplos bounds");
        System.out.println("✅ Adiciona type safety e permite operações específicas");
        System.out.println("✅ Number permite operações matemáticas");
        System.out.println("✅ Comparable permite comparações e ordenação");
    }
}
