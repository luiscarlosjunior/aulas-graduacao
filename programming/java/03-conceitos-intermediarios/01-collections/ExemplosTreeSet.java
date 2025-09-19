import java.util.*;

/**
 * Exemplos completos de TreeSet - Conjunto ordenado
 * 
 * TreeSet é uma implementação de SortedSet baseada em árvore Red-Black.
 * Mantém elementos automaticamente ordenados e oferece operações O(log n).
 * 
 * Conceitos POO demonstrados:
 * - Encapsulamento: Estrutura de árvore oculta
 * - Polimorfismo: Implementa Set, SortedSet, NavigableSet
 * - Herança: Hierarquia de interfaces especializadas
 * - Abstração: Ordenação automática transparente ao usuário
 * 
 * @author Aulas Graduação
 */
public class ExemplosTreeSet {
    
    public static void main(String[] args) {
        System.out.println("=== TREESET - EXEMPLOS COMPLETOS ===\n");
        
        exemploBasico();
        System.out.println();
        
        exemploOrdenacao();
        System.out.println();
        
        exemploNavegacao();
        System.out.println();
        
        exemploComparador();
        System.out.println();
        
        exemploComObjetos();
    }
    
    /**
     * Exemplo básico de TreeSet
     */
    private static void exemploBasico() {
        System.out.println("--- TREESET BÁSICO ---");
        
        // TreeSet mantém elementos ordenados automaticamente
        TreeSet<Integer> numeros = new TreeSet<>();
        
        // Inserindo números em ordem aleatória
        numeros.add(50);
        numeros.add(20);
        numeros.add(80);
        numeros.add(10);
        numeros.add(60);
        numeros.add(30);
        
        System.out.println("Números inseridos em ordem aleatória:");
        System.out.println("TreeSet (automaticamente ordenado): " + numeros);
        
        // Operações básicas
        System.out.println("Menor elemento: " + numeros.first());
        System.out.println("Maior elemento: " + numeros.last());
        System.out.println("Tamanho: " + numeros.size());
        System.out.println("Contém 30? " + numeros.contains(30));
        
        // Tentativa de inserir duplicata (será ignorada)
        boolean adicionou = numeros.add(50);
        System.out.println("Tentou adicionar 50 novamente: " + adicionou);
        System.out.println("TreeSet após tentativa: " + numeros);
    }
    
    /**
     * Exemplo com Strings demonstrando ordenação natural
     */
    private static void exemploOrdenacao() {
        System.out.println("--- ORDENAÇÃO AUTOMÁTICA ---");
        
        // TreeSet de strings
        TreeSet<String> nomes = new TreeSet<>();
        nomes.add("Maria");
        nomes.add("João");
        nomes.add("Ana");
        nomes.add("Carlos");
        nomes.add("Bruno");
        
        System.out.println("Nomes em ordem alfabética: " + nomes);
        
        // Comparando com HashSet (sem ordem)
        HashSet<String> nomesHash = new HashSet<>();
        nomesHash.add("Maria");
        nomesHash.add("João");
        nomesHash.add("Ana");
        nomesHash.add("Carlos");
        nomesHash.add("Bruno");
        
        System.out.println("HashSet (sem ordem garantida): " + nomesHash);
        
        // Demonstrando case sensitivity
        TreeSet<String> caseSensitive = new TreeSet<>();
        caseSensitive.add("apple");
        caseSensitive.add("Banana");
        caseSensitive.add("cherry");
        caseSensitive.add("Date");
        
        System.out.println("Case sensitive (maiúsculas primeiro): " + caseSensitive);
    }
    
    /**
     * Exemplo de operações de navegação específicas do TreeSet
     */
    private static void exemploNavegacao() {
        System.out.println("--- OPERAÇÕES DE NAVEGAÇÃO ---");
        
        TreeSet<Integer> notas = new TreeSet<>();
        Collections.addAll(notas, 65, 72, 81, 89, 95, 77, 83, 91);
        
        System.out.println("Notas dos alunos: " + notas);
        
        // Operações de navegação do NavigableSet
        System.out.println("\n🎯 Operações de busca:");
        System.out.println("Menor nota ≥ 80: " + notas.ceiling(80));
        System.out.println("Maior nota ≤ 80: " + notas.floor(80));
        System.out.println("Menor nota > 80: " + notas.higher(80));
        System.out.println("Maior nota < 80: " + notas.lower(80));
        
        // Subconjuntos
        System.out.println("\n📊 Subconjuntos:");
        System.out.println("Notas de 75 a 85: " + notas.subSet(75, 85));
        System.out.println("Notas menores que 80: " + notas.headSet(80));
        System.out.println("Notas maiores ou iguais a 80: " + notas.tailSet(80));
        
        // Operações destrutivas de navegação
        System.out.println("\n🔄 Removendo elementos:");
        TreeSet<Integer> copia = new TreeSet<>(notas);
        System.out.println("Remove menor: " + copia.pollFirst());
        System.out.println("Remove maior: " + copia.pollLast());
        System.out.println("Restantes: " + copia);
        
        // Navegação reversa
        System.out.println("\n🔙 Ordem reversa:");
        NavigableSet<Integer> reverso = notas.descendingSet();
        System.out.println("Notas em ordem decrescente: " + reverso);
    }
    
    /**
     * Exemplo usando Comparator personalizado
     */
    private static void exemploComparador() {
        System.out.println("--- COMPARADOR PERSONALIZADO ---");
        
        // TreeSet com comparador de tamanho de string
        TreeSet<String> porTamanho = new TreeSet<>(
            Comparator.comparing(String::length)
                     .thenComparing(String::compareTo) // Desempate por ordem alfabética
        );
        
        porTamanho.add("casa");
        porTamanho.add("bicicleta");
        porTamanho.add("sol");
        porTamanho.add("programação");
        porTamanho.add("Java");
        porTamanho.add("a");
        
        System.out.println("Palavras ordenadas por tamanho:");
        porTamanho.forEach(palavra -> 
            System.out.println("  '" + palavra + "' (tamanho: " + palavra.length() + ")")
        );
        
        // TreeSet ordenação reversa
        TreeSet<Integer> numerosReverso = new TreeSet<>(Collections.reverseOrder());
        Collections.addAll(numerosReverso, 5, 2, 8, 1, 9, 3);
        
        System.out.println("\nNúmeros em ordem decrescente: " + numerosReverso);
        
        // Comparador mais complexo
        TreeSet<String> complexo = new TreeSet<>((s1, s2) -> {
            // Primeiro por número de vogais, depois alfabeticamente
            int vogais1 = contarVogais(s1);
            int vogais2 = contarVogais(s2);
            
            if (vogais1 != vogais2) {
                return Integer.compare(vogais1, vogais2);
            }
            return s1.compareTo(s2);
        });
        
        complexo.add("casa");
        complexo.add("teste");
        complexo.add("programação");
        complexo.add("Java");
        complexo.add("código");
        
        System.out.println("\nPalavras ordenadas por número de vogais:");
        complexo.forEach(palavra -> 
            System.out.println("  '" + palavra + "' (" + contarVogais(palavra) + " vogais)")
        );
    }
    
    /**
     * Conta o número de vogais em uma string
     */
    private static int contarVogais(String palavra) {
        return (int) palavra.toLowerCase()
                           .chars()
                           .filter(c -> "aeiou".indexOf(c) >= 0)
                           .count();
    }
    
    /**
     * Exemplo com objetos personalizados
     */
    private static void exemploComObjetos() {
        System.out.println("--- TREESET COM OBJETOS ---");
        
        // TreeSet de produtos ordenados por preço
        TreeSet<Produto> produtos = new TreeSet<>();
        
        produtos.add(new Produto("Notebook", 2500.00));
        produtos.add(new Produto("Mouse", 50.00));
        produtos.add(new Produto("Teclado", 150.00));
        produtos.add(new Produto("Monitor", 800.00));
        produtos.add(new Produto("Webcam", 200.00));
        
        System.out.println("🛒 Produtos ordenados por preço:");
        produtos.forEach(produto -> 
            System.out.println("  " + produto.getNome() + " - R$ " + 
                             String.format("%.2f", produto.getPreco()))
        );
        
        // Buscas específicas
        Produto referencia = new Produto("", 200.00);
        System.out.println("\n🔍 Buscas por preço:");
        System.out.println("Produto ≥ R$ 200: " + produtos.ceiling(referencia));
        System.out.println("Produto ≤ R$ 200: " + produtos.floor(referencia));
        
        // Faixas de preço
        Produto min = new Produto("", 100.00);
        Produto max = new Produto("", 500.00);
        System.out.println("\n💰 Produtos entre R$ 100 e R$ 500:");
        produtos.subSet(min, max).forEach(produto ->
            System.out.println("  " + produto.getNome() + " - R$ " + 
                             String.format("%.2f", produto.getPreco()))
        );
        
        // Demonstrando diferentes ordenações
        System.out.println("\n📊 Diferentes ordenações:");
        
        // Por nome
        TreeSet<Produto> porNome = new TreeSet<>(Comparator.comparing(Produto::getNome));
        porNome.addAll(produtos);
        System.out.println("Por nome: " + porNome);
        
        // Por preço decrescente
        TreeSet<Produto> porPrecoDesc = new TreeSet<>(
            Comparator.comparing(Produto::getPreco).reversed()
        );
        porPrecoDesc.addAll(produtos);
        System.out.println("Por preço (decrescente): " + porPrecoDesc);
    }
    
    /**
     * Classe Produto que implementa Comparable
     */
    static class Produto implements Comparable<Produto> {
        private String nome;
        private double preco;
        
        public Produto(String nome, double preco) {
            this.nome = nome;
            this.preco = preco;
        }
        
        public String getNome() {
            return nome;
        }
        
        public double getPreco() {
            return preco;
        }
        
        @Override
        public int compareTo(Produto outro) {
            // Ordenação natural por preço
            return Double.compare(this.preco, outro.preco);
        }
        
        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Produto produto = (Produto) obj;
            return Double.compare(produto.preco, preco) == 0 && 
                   Objects.equals(nome, produto.nome);
        }
        
        @Override
        public int hashCode() {
            return Objects.hash(nome, preco);
        }
        
        @Override
        public String toString() {
            return nome + "(R$" + String.format("%.2f", preco) + ")";
        }
    }
}