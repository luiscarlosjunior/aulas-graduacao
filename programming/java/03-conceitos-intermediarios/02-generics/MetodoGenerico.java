/**
 * MetodoGenerico.java
 * 
 * Demonstra a criação e uso de métodos genéricos em Java.
 * 
 * Um método genérico é um método que declara seus próprios type parameters,
 * independentemente da classe que o contém. Métodos genéricos podem existir
 * tanto em classes genéricas quanto em classes não-genéricas.
 * 
 * Sintaxe: <T> antes do tipo de retorno indica um método genérico
 * Exemplo: public static <T> void metodo(T parametro)
 * 
 * Benefícios dos Métodos Genéricos:
 * - Reutilização de código
 * - Type safety
 * - Inferência automática de tipos
 * - Flexibilidade sem perder segurança
 * 
 * @author Aulas de Graduação
 */

import java.util.*;

/**
 * Classe utilitária demonstrando métodos genéricos.
 * Note que a classe em si não é genérica, mas contém métodos genéricos.
 */
class UtilitariosGenericos {
    
    /**
     * Imprime um elemento de qualquer tipo.
     * 
     * O type parameter <T> é declarado antes do tipo de retorno (void).
     * Este método pode ser chamado com argumentos de qualquer tipo.
     * 
     * @param <T> o tipo do elemento
     * @param elemento o elemento a ser impresso
     */
    public static <T> void imprimir(T elemento) {
        System.out.println("Elemento: " + elemento);
    }
    
    /**
     * Imprime um array de qualquer tipo.
     * 
     * @param <T> o tipo dos elementos do array
     * @param array o array a ser impresso
     */
    public static <T> void imprimirArray(T[] array) {
        System.out.print("[ ");
        for (T elemento : array) {
            System.out.print(elemento + " ");
        }
        System.out.println("]");
    }
    
    /**
     * Retorna o primeiro elemento de uma lista.
     * 
     * Demonstra método genérico com retorno do tipo genérico.
     * 
     * @param <T> o tipo dos elementos da lista
     * @param lista a lista
     * @return o primeiro elemento, ou null se lista vazia
     */
    public static <T> T obterPrimeiro(List<T> lista) {
        if (lista == null || lista.isEmpty()) {
            return null;
        }
        return lista.get(0);
    }
    
    /**
     * Retorna o último elemento de uma lista.
     * 
     * @param <T> o tipo dos elementos da lista
     * @param lista a lista
     * @return o último elemento, ou null se lista vazia
     */
    public static <T> T obterUltimo(List<T> lista) {
        if (lista == null || lista.isEmpty()) {
            return null;
        }
        return lista.get(lista.size() - 1);
    }
    
    /**
     * Conta quantas vezes um elemento aparece em um array.
     * 
     * Demonstra uso de equals() com tipos genéricos.
     * 
     * @param <T> o tipo dos elementos
     * @param array o array
     * @param elemento o elemento a ser contado
     * @return número de ocorrências
     */
    public static <T> int contar(T[] array, T elemento) {
        int contador = 0;
        for (T item : array) {
            if (item != null && item.equals(elemento)) {
                contador++;
            }
        }
        return contador;
    }
    
    /**
     * Verifica se um array contém um elemento.
     * 
     * @param <T> o tipo dos elementos
     * @param array o array
     * @param elemento o elemento a ser buscado
     * @return true se encontrado, false caso contrário
     */
    public static <T> boolean contem(T[] array, T elemento) {
        for (T item : array) {
            if (item != null && item.equals(elemento)) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Troca dois elementos em um array.
     * 
     * @param <T> o tipo dos elementos
     * @param array o array
     * @param i índice do primeiro elemento
     * @param j índice do segundo elemento
     */
    public static <T> void trocar(T[] array, int i, int j) {
        if (i < 0 || i >= array.length || j < 0 || j >= array.length) {
            throw new IndexOutOfBoundsException("Índices inválidos");
        }
        T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
    
    /**
     * Cria uma lista a partir de elementos variáveis.
     * 
     * Demonstra uso de varargs com generics.
     * 
     * @param <T> o tipo dos elementos
     * @param elementos os elementos
     * @return lista contendo os elementos
     */
    @SafeVarargs
    public static <T> List<T> criarLista(T... elementos) {
        List<T> lista = new ArrayList<>();
        for (T elemento : elementos) {
            lista.add(elemento);
        }
        return lista;
    }
    
    /**
     * Retorna uma representação em string de uma coleção.
     * 
     * @param <T> o tipo dos elementos
     * @param colecao a coleção
     * @return string representando a coleção
     */
    public static <T> String paraString(Collection<T> colecao) {
        if (colecao == null || colecao.isEmpty()) {
            return "[]";
        }
        
        StringBuilder sb = new StringBuilder("[");
        boolean primeiro = true;
        for (T elemento : colecao) {
            if (!primeiro) {
                sb.append(", ");
            }
            sb.append(elemento);
            primeiro = false;
        }
        sb.append("]");
        return sb.toString();
    }
}

/**
 * Classe demonstrando métodos genéricos com múltiplos type parameters.
 */
class UtilitariosPar {
    
    /**
     * Cria um par a partir de dois valores.
     * 
     * Demonstra método com dois type parameters diferentes.
     * 
     * @param <K> tipo da chave
     * @param <V> tipo do valor
     * @param chave a chave
     * @param valor o valor
     * @return um par contendo chave e valor
     */
    public static <K, V> Map.Entry<K, V> criarPar(K chave, V valor) {
        return new AbstractMap.SimpleEntry<>(chave, valor);
    }
    
    /**
     * Inverte um par (chave vira valor, valor vira chave).
     * 
     * @param <K> tipo da chave original
     * @param <V> tipo do valor original
     * @param par o par original
     * @return par invertido
     */
    public static <K, V> Map.Entry<V, K> inverterPar(Map.Entry<K, V> par) {
        return new AbstractMap.SimpleEntry<>(par.getValue(), par.getKey());
    }
}

/**
 * Classe demonstrando métodos genéricos com retornos complexos.
 */
class UtilitariosColecao {
    
    /**
     * Converte uma lista em um conjunto (remove duplicatas).
     * 
     * @param <T> o tipo dos elementos
     * @param lista a lista
     * @return conjunto contendo elementos únicos da lista
     */
    public static <T> Set<T> listaParaConjunto(List<T> lista) {
        return new HashSet<>(lista);
    }
    
    /**
     * Converte um conjunto em uma lista.
     * 
     * @param <T> o tipo dos elementos
     * @param conjunto o conjunto
     * @return lista contendo os elementos do conjunto
     */
    public static <T> List<T> conjuntoParaLista(Set<T> conjunto) {
        return new ArrayList<>(conjunto);
    }
    
    /**
     * Une duas listas em uma nova lista.
     * 
     * @param <T> o tipo dos elementos
     * @param lista1 primeira lista
     * @param lista2 segunda lista
     * @return nova lista contendo elementos de ambas
     */
    public static <T> List<T> unirListas(List<T> lista1, List<T> lista2) {
        List<T> resultado = new ArrayList<>(lista1);
        resultado.addAll(lista2);
        return resultado;
    }
    
    /**
     * Filtra elementos de uma lista usando um predicado.
     * 
     * Este é um exemplo simplificado do que a Stream API faz.
     * 
     * @param <T> o tipo dos elementos
     * @param lista a lista
     * @param predicado função que testa cada elemento
     * @return nova lista contendo apenas elementos que passaram no teste
     */
    public static <T> List<T> filtrar(List<T> lista, Predicado<T> predicado) {
        List<T> resultado = new ArrayList<>();
        for (T elemento : lista) {
            if (predicado.testar(elemento)) {
                resultado.add(elemento);
            }
        }
        return resultado;
    }
}

/**
 * Interface funcional simples para demonstração.
 * (Em código real, use java.util.function.Predicate)
 */
interface Predicado<T> {
    boolean testar(T elemento);
}

/**
 * Classe principal demonstrando o uso de métodos genéricos.
 */
public class MetodoGenerico {
    
    public static void main(String[] args) {
        System.out.println("=== DEMONSTRAÇÃO DE MÉTODOS GENÉRICOS ===\n");
        
        // ============================================================
        // EXEMPLO 1: Método genérico simples
        // ============================================================
        System.out.println("--- Exemplo 1: Imprimir Elementos ---");
        
        // Inferência automática de tipo - não precisa especificar <String>
        UtilitariosGenericos.imprimir("Hello, Generics!");
        UtilitariosGenericos.imprimir(42);
        UtilitariosGenericos.imprimir(3.14);
        UtilitariosGenericos.imprimir(true);
        System.out.println();
        
        // ============================================================
        // EXEMPLO 2: Método genérico com array
        // ============================================================
        System.out.println("--- Exemplo 2: Imprimir Arrays ---");
        
        String[] nomes = {"Ana", "Bruno", "Carlos", "Diana"};
        Integer[] numeros = {1, 2, 3, 4, 5};
        Double[] decimais = {1.1, 2.2, 3.3};
        
        UtilitariosGenericos.imprimirArray(nomes);
        UtilitariosGenericos.imprimirArray(numeros);
        UtilitariosGenericos.imprimirArray(decimais);
        System.out.println();
        
        // ============================================================
        // EXEMPLO 3: Método genérico com retorno
        // ============================================================
        System.out.println("--- Exemplo 3: Obter Primeiro/Último Elemento ---");
        
        List<String> frutas = Arrays.asList("Maçã", "Banana", "Laranja", "Uva");
        List<Integer> idades = Arrays.asList(25, 30, 18, 45, 22);
        
        String primeiraFruta = UtilitariosGenericos.obterPrimeiro(frutas);
        Integer primeiraIdade = UtilitariosGenericos.obterPrimeiro(idades);
        
        System.out.println("Primeira fruta: " + primeiraFruta);
        System.out.println("Primeira idade: " + primeiraIdade);
        
        String ultimaFruta = UtilitariosGenericos.obterUltimo(frutas);
        Integer ultimaIdade = UtilitariosGenericos.obterUltimo(idades);
        
        System.out.println("Última fruta: " + ultimaFruta);
        System.out.println("Última idade: " + ultimaIdade);
        System.out.println();
        
        // ============================================================
        // EXEMPLO 4: Contar e Verificar Elementos
        // ============================================================
        System.out.println("--- Exemplo 4: Contar e Verificar ---");
        
        String[] palavras = {"Java", "Python", "Java", "C++", "Java", "Ruby"};
        
        int qtdJava = UtilitariosGenericos.contar(palavras, "Java");
        System.out.println("Quantidade de 'Java': " + qtdJava);
        
        boolean temPython = UtilitariosGenericos.contem(palavras, "Python");
        boolean temGo = UtilitariosGenericos.contem(palavras, "Go");
        
        System.out.println("Contém Python? " + temPython);
        System.out.println("Contém Go? " + temGo);
        System.out.println();
        
        // ============================================================
        // EXEMPLO 5: Trocar Elementos
        // ============================================================
        System.out.println("--- Exemplo 5: Trocar Elementos ---");
        
        Integer[] valores = {10, 20, 30, 40, 50};
        System.out.println("Antes da troca:");
        UtilitariosGenericos.imprimirArray(valores);
        
        UtilitariosGenericos.trocar(valores, 1, 3);
        System.out.println("Depois de trocar índices 1 e 3:");
        UtilitariosGenericos.imprimirArray(valores);
        System.out.println();
        
        // ============================================================
        // EXEMPLO 6: Varargs Genérico
        // ============================================================
        System.out.println("--- Exemplo 6: Criar Lista com Varargs ---");
        
        List<String> cores = UtilitariosGenericos.criarLista(
            "Vermelho", "Verde", "Azul", "Amarelo"
        );
        System.out.println("Cores: " + cores);
        
        List<Integer> primos = UtilitariosGenericos.criarLista(2, 3, 5, 7, 11, 13);
        System.out.println("Primos: " + primos);
        System.out.println();
        
        // ============================================================
        // EXEMPLO 7: Múltiplos Type Parameters
        // ============================================================
        System.out.println("--- Exemplo 7: Múltiplos Type Parameters ---");
        
        Map.Entry<String, Integer> parNomeIdade = UtilitariosPar.criarPar("João", 25);
        System.out.println("Par original: " + parNomeIdade.getKey() + " = " + parNomeIdade.getValue());
        
        Map.Entry<Integer, String> parInvertido = UtilitariosPar.inverterPar(parNomeIdade);
        System.out.println("Par invertido: " + parInvertido.getKey() + " = " + parInvertido.getValue());
        System.out.println();
        
        // ============================================================
        // EXEMPLO 8: Conversões de Coleções
        // ============================================================
        System.out.println("--- Exemplo 8: Conversões de Coleções ---");
        
        List<String> listaComDuplicatas = Arrays.asList("A", "B", "A", "C", "B", "D");
        System.out.println("Lista original: " + listaComDuplicatas);
        
        Set<String> conjuntoUnico = UtilitariosColecao.listaParaConjunto(listaComDuplicatas);
        System.out.println("Conjunto (sem duplicatas): " + conjuntoUnico);
        
        List<String> listaDeVolta = UtilitariosColecao.conjuntoParaLista(conjuntoUnico);
        System.out.println("De volta para lista: " + listaDeVolta);
        System.out.println();
        
        // ============================================================
        // EXEMPLO 9: Unir Listas
        // ============================================================
        System.out.println("--- Exemplo 9: Unir Listas ---");
        
        List<Integer> lista1 = Arrays.asList(1, 2, 3);
        List<Integer> lista2 = Arrays.asList(4, 5, 6);
        
        List<Integer> listaUnida = UtilitariosColecao.unirListas(lista1, lista2);
        System.out.println("Lista 1: " + lista1);
        System.out.println("Lista 2: " + lista2);
        System.out.println("União: " + listaUnida);
        System.out.println();
        
        // ============================================================
        // EXEMPLO 10: Filtrar com Predicado
        // ============================================================
        System.out.println("--- Exemplo 10: Filtrar com Predicado ---");
        
        List<Integer> todosNumeros = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        
        // Filtrar números pares
        List<Integer> pares = UtilitariosColecao.filtrar(todosNumeros, new Predicado<Integer>() {
            @Override
            public boolean testar(Integer n) {
                return n % 2 == 0;
            }
        });
        
        System.out.println("Todos: " + todosNumeros);
        System.out.println("Pares: " + pares);
        
        // Filtrar números maiores que 5
        List<Integer> maioresQue5 = UtilitariosColecao.filtrar(todosNumeros, new Predicado<Integer>() {
            @Override
            public boolean testar(Integer n) {
                return n > 5;
            }
        });
        
        System.out.println("Maiores que 5: " + maioresQue5);
        System.out.println();
        
        // ============================================================
        // EXEMPLO 11: Type Witness Explícito
        // ============================================================
        System.out.println("--- Exemplo 11: Type Witness Explícito ---");
        
        // Às vezes é necessário especificar o tipo explicitamente
        List<String> listaVazia = UtilitariosGenericos.<String>criarLista();
        System.out.println("Lista vazia: " + listaVazia);
        
        // Na maioria dos casos, inferência automática funciona
        List<Double> decimaisLista = UtilitariosGenericos.criarLista(1.1, 2.2, 3.3);
        System.out.println("Lista de decimais: " + decimaisLista);
        System.out.println();
        
        // ============================================================
        // RESUMO
        // ============================================================
        System.out.println("=== RESUMO ===");
        System.out.println("✅ Métodos genéricos têm seus próprios type parameters");
        System.out.println("✅ Sintaxe: <T> antes do tipo de retorno");
        System.out.println("✅ Inferência automática de tipos na maioria dos casos");
        System.out.println("✅ Podem existir em classes genéricas ou não-genéricas");
        System.out.println("✅ Suportam múltiplos type parameters");
        System.out.println("✅ Funcionam com varargs (@SafeVarargs)");
    }
}
