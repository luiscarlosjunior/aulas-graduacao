import java.util.*;

/**
 * Exemplos completos de ArrayList - Lista redimensionável
 * 
 * ArrayList é a implementação de List mais utilizada em Java.
 * Baseada em array interno que cresce dinamicamente.
 * 
 * @author Aulas Graduação
 */
public class ExemplosArrayList {
    
    public static void main(String[] args) {
        System.out.println("=== ARRAYLIST - EXEMPLOS COMPLETOS ===\n");
        
        exemploBasico();
        System.out.println();
        
        exemploOperacoes();
        System.out.println();
        
        exemploIteracao();
        System.out.println();
        
        exemploOrdenacao();
        System.out.println();
        
        exemploPerformance();
    }
    
    /**
     * Exemplo básico de criação e uso
     */
    private static void exemploBasico() {
        System.out.println("--- EXEMPLO BÁSICO ---");
        
        // Criação de ArrayList
        ArrayList<String> frutas = new ArrayList<>();
        
        // Adicionando elementos
        frutas.add("Maçã");
        frutas.add("Banana");
        frutas.add("Laranja");
        frutas.add("Uva");
        
        System.out.println("Lista de frutas: " + frutas);
        System.out.println("Tamanho: " + frutas.size());
        System.out.println("Está vazia? " + frutas.isEmpty());
        
        // Acesso por índice
        System.out.println("Primeira fruta: " + frutas.get(0));
        System.out.println("Última fruta: " + frutas.get(frutas.size() - 1));
        
        // Verificando se contém elemento
        System.out.println("Contém 'Banana'? " + frutas.contains("Banana"));
        System.out.println("Índice da 'Laranja': " + frutas.indexOf("Laranja"));
    }
    
    /**
     * Operações de modificação
     */
    private static void exemploOperacoes() {
        System.out.println("--- OPERAÇÕES DE MODIFICAÇÃO ---");
        
        ArrayList<Integer> numeros = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        System.out.println("Lista inicial: " + numeros);
        
        // Inserção em posição específica
        numeros.add(2, 99); // Insere 99 na posição 2
        System.out.println("Após inserir 99 na posição 2: " + numeros);
        
        // Substituição
        numeros.set(0, 100); // Substitui o primeiro elemento por 100
        System.out.println("Após substituir primeiro por 100: " + numeros);
        
        // Remoção por índice
        Integer removido = numeros.remove(1); // Remove elemento no índice 1
        System.out.println("Removido índice 1 (" + removido + "): " + numeros);
        
        // Remoção por objeto
        boolean removeuObj = numeros.remove(Integer.valueOf(99)); // Remove o objeto 99
        System.out.println("Removeu objeto 99? " + removeuObj + " - Lista: " + numeros);
        
        // Adicionar múltiplos elementos
        numeros.addAll(Arrays.asList(10, 20, 30));
        System.out.println("Após adicionar [10,20,30]: " + numeros);
        
        // Remover múltiplos elementos
        numeros.removeAll(Arrays.asList(10, 30));
        System.out.println("Após remover [10,30]: " + numeros);
        
        // Manter apenas elementos específicos
        numeros.retainAll(Arrays.asList(100, 3, 20));
        System.out.println("Após manter apenas [100,3,20]: " + numeros);
        
        // Limpar toda a lista
        ArrayList<Integer> copia = new ArrayList<>(numeros);
        copia.clear();
        System.out.println("Após clear: " + copia + " (tamanho: " + copia.size() + ")");
    }
    
    /**
     * Diferentes formas de iteração
     */
    private static void exemploIteracao() {
        System.out.println("--- FORMAS DE ITERAÇÃO ---");
        
        ArrayList<String> cores = new ArrayList<>(Arrays.asList("Vermelho", "Azul", "Verde", "Amarelo"));
        
        // 1. For tradicional
        System.out.println("1. For tradicional:");
        for (int i = 0; i < cores.size(); i++) {
            System.out.println("   Índice " + i + ": " + cores.get(i));
        }
        
        // 2. Enhanced for (for-each)
        System.out.println("2. Enhanced for:");
        for (String cor : cores) {
            System.out.println("   Cor: " + cor);
        }
        
        // 3. Iterator
        System.out.println("3. Iterator:");
        Iterator<String> iterator = cores.iterator();
        while (iterator.hasNext()) {
            String cor = iterator.next();
            System.out.println("   Cor: " + cor);
            
            // Remoção segura durante iteração
            if (cor.equals("Verde")) {
                iterator.remove();
                System.out.println("   Removeu 'Verde' com Iterator");
            }
        }
        System.out.println("   Lista após remoção: " + cores);
        
        // 4. Stream (Java 8+)
        System.out.println("4. Stream:");
        cores.stream()
            .filter(cor -> cor.length() > 4)
            .forEach(cor -> System.out.println("   Cor com >4 chars: " + cor));
        
        // 5. ListIterator (permite iteração bidirecional)
        System.out.println("5. ListIterator (reverso):");
        ListIterator<String> listIterator = cores.listIterator(cores.size());
        while (listIterator.hasPrevious()) {
            System.out.println("   " + listIterator.previous());
        }
    }
    
    /**
     * Exemplos de ordenação
     */
    private static void exemploOrdenacao() {
        System.out.println("--- ORDENAÇÃO ---");
        
        // Ordenação de strings
        ArrayList<String> nomes = new ArrayList<>(Arrays.asList("João", "Ana", "Carlos", "Maria"));
        System.out.println("Nomes original: " + nomes);
        
        Collections.sort(nomes);
        System.out.println("Nomes ordenados: " + nomes);
        
        Collections.reverse(nomes);
        System.out.println("Nomes reversos: " + nomes);
        
        // Ordenação de números
        ArrayList<Integer> nums = new ArrayList<>(Arrays.asList(5, 2, 8, 1, 9, 3));
        System.out.println("Números original: " + nums);
        
        Collections.sort(nums);
        System.out.println("Números ordenados: " + nums);
        
        // Ordenação customizada com Comparator
        ArrayList<String> palavras = new ArrayList<>(Arrays.asList("casa", "bicicleta", "sol", "programação"));
        System.out.println("Palavras original: " + palavras);
        
        // Ordenar por tamanho
        palavras.sort(Comparator.comparing(String::length));
        System.out.println("Por tamanho: " + palavras);
        
        // Ordenar por tamanho reverso
        palavras.sort(Comparator.comparing(String::length).reversed());
        System.out.println("Por tamanho (reverso): " + palavras);
        
        // Busca binária (requer lista ordenada)
        Collections.sort(nomes);
        int index = Collections.binarySearch(nomes, "Carlos");
        System.out.println("Índice de 'Carlos' (busca binária): " + index);
    }
    
    /**
     * Demonstração de características de performance
     */
    private static void exemploPerformance() {
        System.out.println("--- PERFORMANCE E CAPACIDADE ---");
        
        // ArrayList com capacidade inicial
        ArrayList<Integer> listaComCapacidade = new ArrayList<>(1000);
        System.out.println("Lista criada com capacidade inicial 1000");
        
        // Medindo tempo de inserção
        long inicio = System.nanoTime();
        for (int i = 0; i < 10000; i++) {
            listaComCapacidade.add(i);
        }
        long fim = System.nanoTime();
        System.out.println("Tempo para adicionar 10.000 elementos: " + 
                          (fim - inicio) / 1_000_000.0 + " ms");
        
        // Redimensionamento manual
        ArrayList<String> lista = new ArrayList<>();
        lista.ensureCapacity(5000); // Garante capacidade
        System.out.println("Capacidade garantida para 5000 elementos");
        
        // Compactação da lista
        lista.addAll(Arrays.asList("A", "B", "C"));
        lista.trimToSize(); // Remove capacidade não utilizada
        System.out.println("Lista compactada após trimToSize()");
        
        // Conversão para array
        String[] array = lista.toArray(new String[0]);
        System.out.println("Convertido para array: " + Arrays.toString(array));
        
        // Sublista (visão da lista original)
        ArrayList<Integer> grande = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
        List<Integer> sublista = grande.subList(2, 6); // Índices 2 a 5
        System.out.println("Lista original: " + grande);
        System.out.println("Sublista (2-5): " + sublista);
        
        // Modificar sublista afeta a original
        sublista.set(0, 999);
        System.out.println("Após modificar sublista: " + grande);
    }
}