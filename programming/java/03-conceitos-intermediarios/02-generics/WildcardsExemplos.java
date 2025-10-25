/**
 * WildcardsExemplos.java
 * 
 * Demonstra o uso completo de Wildcards (curingas) em Java Generics.
 * 
 * Wildcards representam tipos desconhecidos e proporcionam flexibilidade
 * adicional ao trabalhar com Generics. O símbolo ? representa um wildcard.
 * 
 * Tipos de Wildcards:
 * 1. Unbounded Wildcard: <?>
 * 2. Upper Bounded Wildcard: <? extends T> (covariância)
 * 3. Lower Bounded Wildcard: <? super T> (contravariância)
 * 
 * PECS - Producer Extends, Consumer Super:
 * - Se um tipo PRODUZ (retorna) T, use <? extends T>
 * - Se um tipo CONSOME (recebe) T, use <? super T>
 * 
 * @author Aulas de Graduação
 */

import java.util.*;

/**
 * Classe demonstrando Unbounded Wildcards <?>.
 */
class UnboundedWildcardExemplos {
    
    /**
     * Imprime elementos de uma lista de qualquer tipo.
     * 
     * Usa <?> porque não importa o tipo específico - apenas imprimimos.
     * Podemos apenas LER como Object, não podemos ESCREVER (exceto null).
     */
    public static void imprimirLista(List<?> lista) {
        for (Object elemento : lista) {
            System.out.println(elemento);
        }
    }
    
    /**
     * Retorna o tamanho de uma lista de qualquer tipo.
     */
    public static int tamanho(List<?> lista) {
        return lista.size();
    }
    
    /**
     * Verifica se duas listas têm o mesmo tamanho.
     */
    public static boolean mesmoTamanho(List<?> lista1, List<?> lista2) {
        return lista1.size() == lista2.size();
    }
    
    /**
     * Limpa uma lista de qualquer tipo.
     */
    public static void limpar(List<?> lista) {
        lista.clear(); // Podemos chamar métodos que não dependem do tipo
    }
}

/**
 * Classe demonstrando Upper Bounded Wildcards <? extends T>.
 * 
 * Producer Extends: usa quando você quer LER/PRODUZIR elementos do tipo T.
 */
class UpperBoundedWildcardExemplos {
    
    /**
     * Soma números de uma lista.
     * 
     * Aceita List<Integer>, List<Double>, List<Number>, etc.
     * Podemos LER como Number, mas NÃO podemos ADICIONAR elementos.
     */
    public static double somar(List<? extends Number> numeros) {
        double soma = 0;
        for (Number numero : numeros) {
            soma += numero.doubleValue(); // ✅ Leitura OK
        }
        // numeros.add(5); // ❌ Erro: não pode adicionar
        // numeros.add(5.0); // ❌ Erro: não pode adicionar
        // numeros.add(new Integer(5)); // ❌ Erro: não pode adicionar
        return soma;
    }
    
    /**
     * Retorna o maior número de uma lista.
     */
    public static double maiorNumero(List<? extends Number> numeros) {
        if (numeros.isEmpty()) {
            throw new IllegalArgumentException("Lista vazia");
        }
        
        double maior = numeros.get(0).doubleValue();
        for (Number numero : numeros) {
            double valor = numero.doubleValue();
            if (valor > maior) {
                maior = valor;
            }
        }
        return maior;
    }
    
    /**
     * Copia elementos de uma lista origem para uma lista destino.
     * 
     * origem é producer (produz elementos) - usa extends
     * destino é consumer (consome elementos) - usa super
     */
    public static <T> void copiar(List<? extends T> origem, 
                                   List<? super T> destino) {
        for (T elemento : origem) {
            destino.add(elemento);
        }
    }
    
    /**
     * Encontra o maior elemento em uma coleção de comparáveis.
     */
    public static <T extends Comparable<? super T>> T max(Collection<? extends T> colecao) {
        if (colecao.isEmpty()) {
            throw new NoSuchElementException("Coleção vazia");
        }
        
        Iterator<? extends T> it = colecao.iterator();
        T max = it.next();
        
        while (it.hasNext()) {
            T elemento = it.next();
            if (elemento.compareTo(max) > 0) {
                max = elemento;
            }
        }
        return max;
    }
}

/**
 * Classe demonstrando Lower Bounded Wildcards <? super T>.
 * 
 * Consumer Super: usa quando você quer ESCREVER/CONSUMIR elementos do tipo T.
 */
class LowerBoundedWildcardExemplos {
    
    /**
     * Adiciona inteiros a uma lista.
     * 
     * Aceita List<Integer>, List<Number>, List<Object>, etc.
     * Podemos ADICIONAR Integer, mas só podemos LER como Object.
     */
    public static void adicionarInteiros(List<? super Integer> lista) {
        lista.add(1); // ✅ Adicionar OK
        lista.add(2);
        lista.add(3);
        
        // Leitura retorna Object
        Object obj = lista.get(0); // ✅ OK como Object
        // Integer i = lista.get(0); // ❌ Erro de compilação
    }
    
    /**
     * Adiciona números de uma origem a um destino.
     * 
     * origem: produz números (extends)
     * destino: consome números (super)
     */
    public static void adicionarNumeros(List<? extends Number> origem,
                                        List<? super Number> destino) {
        for (Number numero : origem) {
            destino.add(numero); // ✅ Pode adicionar Number
        }
    }
    
    /**
     * Preenche uma lista com um valor.
     */
    public static <T> void preencher(List<? super T> lista, T valor, int quantidade) {
        for (int i = 0; i < quantidade; i++) {
            lista.add(valor);
        }
    }
}

/**
 * Hierarquia de classes para demonstração.
 */
class Animal {
    private String nome;
    
    public Animal(String nome) {
        this.nome = nome;
    }
    
    public String getNome() {
        return nome;
    }
    
    @Override
    public String toString() {
        return getClass().getSimpleName() + "[" + nome + "]";
    }
}

class Mamifero extends Animal {
    public Mamifero(String nome) {
        super(nome);
    }
}

class Cachorro extends Mamifero {
    public Cachorro(String nome) {
        super(nome);
    }
}

class Gato extends Mamifero {
    public Gato(String nome) {
        super(nome);
    }
}

/**
 * Demonstração prática do princípio PECS.
 */
class PECSExemplo {
    
    /**
     * Producer Extends: produz animais para serem lidos.
     * Podemos LER como Mamifero (ou Animal).
     */
    public static void processarAnimais(List<? extends Mamifero> animais) {
        System.out.println("Processando animais:");
        for (Mamifero animal : animais) {
            System.out.println("  - " + animal);
        }
        // animais.add(new Cachorro("Rex")); // ❌ Não pode adicionar
    }
    
    /**
     * Consumer Super: consome mamíferos para serem adicionados.
     * Podemos ADICIONAR Mamifero (ou subtipos).
     */
    public static void adicionarMamiferos(List<? super Mamifero> lista) {
        lista.add(new Mamifero("Genérico"));
        lista.add(new Cachorro("Rex"));
        lista.add(new Gato("Mimi"));
        
        // Leitura retorna Object
        // Mamifero m = lista.get(0); // ❌ Erro
        Object obj = lista.get(0); // ✅ OK
    }
}

/**
 * Classe principal demonstrando wildcards.
 */
public class WildcardsExemplos {
    
    public static void main(String[] args) {
        System.out.println("=== WILDCARDS EM JAVA GENERICS ===\n");
        
        // ============================================================
        // EXEMPLO 1: Unbounded Wildcard <?>
        // ============================================================
        System.out.println("--- Exemplo 1: Unbounded Wildcard <?> ---");
        
        List<String> listaString = Arrays.asList("Java", "Python", "C++");
        List<Integer> listaInteger = Arrays.asList(1, 2, 3, 4, 5);
        List<Double> listaDouble = Arrays.asList(1.1, 2.2, 3.3);
        
        System.out.println("Tamanho da lista de strings: " + 
                          UnboundedWildcardExemplos.tamanho(listaString));
        System.out.println("Tamanho da lista de inteiros: " + 
                          UnboundedWildcardExemplos.tamanho(listaInteger));
        
        System.out.println("\nMesmo tamanho? " + 
                          UnboundedWildcardExemplos.mesmoTamanho(listaString, listaInteger));
        
        System.out.println("\nImprimindo lista de strings:");
        UnboundedWildcardExemplos.imprimirLista(listaString);
        System.out.println();
        
        // ============================================================
        // EXEMPLO 2: Upper Bounded Wildcard <? extends T>
        // ============================================================
        System.out.println("--- Exemplo 2: Upper Bounded <? extends Number> ---");
        
        List<Integer> inteiros = Arrays.asList(1, 2, 3, 4, 5);
        List<Double> decimais = Arrays.asList(1.5, 2.5, 3.5, 4.5);
        List<Number> numeros = Arrays.asList(1, 2.5, 3, 4.5);
        
        System.out.println("Soma de inteiros: " + 
                          UpperBoundedWildcardExemplos.somar(inteiros));
        System.out.println("Soma de doubles: " + 
                          UpperBoundedWildcardExemplos.somar(decimais));
        System.out.println("Soma de numbers: " + 
                          UpperBoundedWildcardExemplos.somar(numeros));
        
        System.out.println("\nMaior inteiro: " + 
                          UpperBoundedWildcardExemplos.maiorNumero(inteiros));
        System.out.println("Maior decimal: " + 
                          UpperBoundedWildcardExemplos.maiorNumero(decimais));
        System.out.println();
        
        // ============================================================
        // EXEMPLO 3: Lower Bounded Wildcard <? super T>
        // ============================================================
        System.out.println("--- Exemplo 3: Lower Bounded <? super Integer> ---");
        
        List<Integer> listaInt = new ArrayList<>();
        List<Number> listaNum = new ArrayList<>();
        List<Object> listaObj = new ArrayList<>();
        
        // Todas aceitam adição de Integer
        LowerBoundedWildcardExemplos.adicionarInteiros(listaInt);
        LowerBoundedWildcardExemplos.adicionarInteiros(listaNum);
        LowerBoundedWildcardExemplos.adicionarInteiros(listaObj);
        
        System.out.println("Lista Integer: " + listaInt);
        System.out.println("Lista Number: " + listaNum);
        System.out.println("Lista Object: " + listaObj);
        System.out.println();
        
        // ============================================================
        // EXEMPLO 4: PECS - Producer Extends, Consumer Super
        // ============================================================
        System.out.println("--- Exemplo 4: PECS em Ação ---");
        
        // Producer (origem): extends
        List<Integer> origem = Arrays.asList(10, 20, 30);
        
        // Consumer (destino): super
        List<Number> destino = new ArrayList<>();
        
        UpperBoundedWildcardExemplos.copiar(origem, destino);
        System.out.println("Origem (Integer): " + origem);
        System.out.println("Destino (Number): " + destino);
        
        // Também funciona com outros tipos
        List<Object> destinoObj = new ArrayList<>();
        UpperBoundedWildcardExemplos.copiar(origem, destinoObj);
        System.out.println("Destino (Object): " + destinoObj);
        System.out.println();
        
        // ============================================================
        // EXEMPLO 5: Hierarquia de Classes com Wildcards
        // ============================================================
        System.out.println("--- Exemplo 5: Hierarquia de Classes ---");
        
        List<Cachorro> cachorros = Arrays.asList(
            new Cachorro("Rex"),
            new Cachorro("Bolt"),
            new Cachorro("Max")
        );
        
        List<Gato> gatos = Arrays.asList(
            new Gato("Mimi"),
            new Gato("Felix")
        );
        
        // Producer Extends: aceita List<Cachorro> como List<? extends Mamifero>
        PECSExemplo.processarAnimais(cachorros);
        System.out.println();
        PECSExemplo.processarAnimais(gatos);
        System.out.println();
        
        // Consumer Super: adiciona Mamiferos a List<Animal>
        List<Animal> todosAnimais = new ArrayList<>();
        PECSExemplo.adicionarMamiferos(todosAnimais);
        System.out.println("Todos os animais: " + todosAnimais);
        System.out.println();
        
        // ============================================================
        // EXEMPLO 6: Preencher Lista
        // ============================================================
        System.out.println("--- Exemplo 6: Preencher Lista ---");
        
        List<Integer> numerosInt = new ArrayList<>();
        LowerBoundedWildcardExemplos.preencher(numerosInt, 42, 5);
        System.out.println("Lista preenchida com Integer: " + numerosInt);
        
        List<Number> numerosNum = new ArrayList<>();
        LowerBoundedWildcardExemplos.preencher(numerosNum, 3.14, 3);
        System.out.println("Lista preenchida com Double: " + numerosNum);
        System.out.println();
        
        // ============================================================
        // EXEMPLO 7: Método max com Wildcards Complexos
        // ============================================================
        System.out.println("--- Exemplo 7: Método max com Wildcards ---");
        
        List<String> palavras = Arrays.asList("zebra", "abelha", "macaco", "elefante");
        String maiorPalavra = UpperBoundedWildcardExemplos.max(palavras);
        System.out.println("Palavras: " + palavras);
        System.out.println("Maior (alfabeticamente): " + maiorPalavra);
        
        List<Integer> valores = Arrays.asList(5, 2, 9, 1, 7);
        Integer maiorValor = UpperBoundedWildcardExemplos.max(valores);
        System.out.println("\nValores: " + valores);
        System.out.println("Maior valor: " + maiorValor);
        System.out.println();
        
        // ============================================================
        // EXEMPLO 8: Restrições de Leitura/Escrita
        // ============================================================
        System.out.println("--- Exemplo 8: Restrições de Leitura/Escrita ---");
        
        System.out.println("Upper Bounded <? extends T>:");
        System.out.println("  ✅ Pode LER como T");
        System.out.println("  ❌ NÃO pode ADICIONAR (exceto null)");
        System.out.println("  📝 Uso: quando quer LER/PRODUZIR dados");
        
        System.out.println("\nLower Bounded <? super T>:");
        System.out.println("  ✅ Pode ADICIONAR T");
        System.out.println("  ❌ Leitura retorna apenas Object");
        System.out.println("  📝 Uso: quando quer ESCREVER/CONSUMIR dados");
        
        System.out.println("\nUnbounded <?>:");
        System.out.println("  ✅ Pode LER como Object");
        System.out.println("  ❌ NÃO pode ADICIONAR (exceto null)");
        System.out.println("  📝 Uso: quando tipo não importa");
        System.out.println();
        
        // ============================================================
        // EXEMPLO 9: Casos de Uso Práticos
        // ============================================================
        System.out.println("--- Exemplo 9: Quando Usar Cada Wildcard ---");
        
        System.out.println("Use <? extends T> quando:");
        System.out.println("  • Ler de uma estrutura (get, iterator, stream)");
        System.out.println("  • Processar elementos existentes");
        System.out.println("  • Retornar valores de um producer");
        System.out.println("  • Exemplo: List<? extends Number> para somar");
        
        System.out.println("\nUse <? super T> quando:");
        System.out.println("  • Adicionar a uma estrutura (add, put)");
        System.out.println("  • Consumir elementos");
        System.out.println("  • Passar callback/comparator");
        System.out.println("  • Exemplo: List<? super Integer> para adicionar inteiros");
        
        System.out.println("\nUse <?> quando:");
        System.out.println("  • Apenas verificar tamanho, isEmpty");
        System.out.println("  • Limpar estrutura");
        System.out.println("  • Tipo específico irrelevante");
        System.out.println("  • Exemplo: List<?> para contar elementos");
        System.out.println();
        
        // ============================================================
        // RESUMO
        // ============================================================
        System.out.println("=== RESUMO ===");
        System.out.println("✅ <?> = tipo desconhecido qualquer");
        System.out.println("✅ <? extends T> = T ou subtipo (covariância)");
        System.out.println("✅ <? super T> = T ou supertipo (contravariância)");
        System.out.println("✅ PECS: Producer Extends, Consumer Super");
        System.out.println("✅ Extends = LER (get)");
        System.out.println("✅ Super = ESCREVER (add)");
        System.out.println("✅ Wildcards aumentam flexibilidade mantendo type safety");
    }
}
