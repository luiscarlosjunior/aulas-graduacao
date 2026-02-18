import java.util.*;

/**
 * Exemplos completos de HashSet - Conjunto de elementos únicos
 * 
 * HashSet é a implementação de Set mais utilizada em Java.
 * Oferece operações O(1) e elimina automaticamente duplicatas.
 * 
 * Conceitos POO demonstrados:
 * - Encapsulamento: Implementação interna oculta
 * - Polimorfismo: Pode ser usado como Set ou Collection
 * - Herança: Implementa interfaces Set e Collection
 * - Abstração: Interface Set esconde complexidade do hash
 * 
 * @author Aulas Graduação
 */
public class ExemplosHashSet {
    
    public static void main(String[] args) {
        System.out.println("=== HASHSET - EXEMPLOS COMPLETOS ===\n");
        
        exemploBasico();
        System.out.println();
        
        exemploEliminacaoDuplicatas();
        System.out.println();
        
        exemploOperacoesConjunto();
        System.out.println();
        
        exemploComObjetos();
        System.out.println();
        
        exemploPerformance();
    }
    
    /**
     * Exemplo básico de criação e uso
     */
    private static void exemploBasico() {
        System.out.println("--- EXEMPLO BÁSICO ---");
        
        // Criação de HashSet
        HashSet<String> cores = new HashSet<>();
        
        // Adicionando elementos
        cores.add("Vermelho");
        cores.add("Azul");
        cores.add("Verde");
        cores.add("Amarelo");
        cores.add("Azul"); // Duplicata - será ignorada
        
        System.out.println("Conjunto de cores: " + cores);
        System.out.println("Tamanho: " + cores.size());
        System.out.println("Está vazio? " + cores.isEmpty());
        
        // Verificações
        System.out.println("Contém 'Verde'? " + cores.contains("Verde"));
        System.out.println("Contém 'Roxo'? " + cores.contains("Roxo"));
        
        // Iteração (ordem não garantida)
        System.out.println("Iterando pelo conjunto:");
        for (String cor : cores) {
            System.out.println("   • " + cor);
        }
        
        // Remoção
        boolean removeu = cores.remove("Amarelo");
        System.out.println("Removeu 'Amarelo'? " + removeu);
        System.out.println("Conjunto após remoção: " + cores);
    }
    
    /**
     * Demonstra eliminação automática de duplicatas
     */
    private static void exemploEliminacaoDuplicatas() {
        System.out.println("--- ELIMINAÇÃO DE DUPLICATAS ---");
        
        // Lista com duplicatas
        List<String> listaNomes = Arrays.asList(
            "João", "Maria", "Carlos", "Ana", "João", 
            "Maria", "Bruno", "Ana", "Carlos", "João"
        );
        
        System.out.println("Lista original (com duplicatas): " + listaNomes);
        System.out.println("Tamanho da lista: " + listaNomes.size());
        
        // Convertendo para HashSet elimina duplicatas
        Set<String> nomesUnicos = new HashSet<>(listaNomes);
        
        System.out.println("HashSet (sem duplicatas): " + nomesUnicos);
        System.out.println("Tamanho do conjunto: " + nomesUnicos.size());
        
        // Voltando para lista sem duplicatas
        List<String> listaLimpa = new ArrayList<>(nomesUnicos);
        Collections.sort(listaLimpa); // Ordenando para visualização
        
        System.out.println("Lista limpa e ordenada: " + listaLimpa);
        
        // Contando duplicatas eliminadas
        int duplicatasRemovidas = listaNomes.size() - nomesUnicos.size();
        System.out.println("Duplicatas removidas: " + duplicatasRemovidas);
    }
    
    /**
     * Operações matemáticas de conjuntos
     */
    private static void exemploOperacoesConjunto() {
        System.out.println("--- OPERAÇÕES DE CONJUNTO ---");
        
        Set<Integer> conjunto1 = new HashSet<>(Arrays.asList(1, 2, 3, 4, 5));
        Set<Integer> conjunto2 = new HashSet<>(Arrays.asList(4, 5, 6, 7, 8));
        
        System.out.println("Conjunto A: " + conjunto1);
        System.out.println("Conjunto B: " + conjunto2);
        
        // União (A ∪ B)
        Set<Integer> uniao = new HashSet<>(conjunto1);
        uniao.addAll(conjunto2);
        System.out.println("União (A ∪ B): " + uniao);
        
        // Interseção (A ∩ B)
        Set<Integer> intersecao = new HashSet<>(conjunto1);
        intersecao.retainAll(conjunto2);
        System.out.println("Interseção (A ∩ B): " + intersecao);
        
        // Diferença (A - B)
        Set<Integer> diferenca = new HashSet<>(conjunto1);
        diferenca.removeAll(conjunto2);
        System.out.println("Diferença (A - B): " + diferenca);
        
        // Diferença simétrica (A ∆ B) = (A - B) ∪ (B - A)
        Set<Integer> diferencaSimetrica = new HashSet<>(conjunto1);
        Set<Integer> conjunto2Copia = new HashSet<>(conjunto2);
        conjunto2Copia.removeAll(conjunto1); // B - A
        diferencaSimetrica.removeAll(conjunto2); // A - B
        diferencaSimetrica.addAll(conjunto2Copia); // (A - B) ∪ (B - A)
        
        System.out.println("Diferença simétrica (A ∆ B): " + diferencaSimetrica);
        
        // Verificações de subconjunto
        Set<Integer> subconjunto = new HashSet<>(Arrays.asList(2, 3));
        boolean ehSubconjunto = conjunto1.containsAll(subconjunto);
        System.out.println("É {2, 3} subconjunto de A? " + ehSubconjunto);
        
        // Conjuntos disjuntos (não têm elementos em comum)
        Set<Integer> conjunto3 = new HashSet<>(Arrays.asList(10, 11, 12));
        boolean saoDisjuntos = Collections.disjoint(conjunto1, conjunto3);
        System.out.println("A e {10, 11, 12} são disjuntos? " + saoDisjuntos);
    }
    
    /**
     * HashSet com objetos personalizados
     * Demonstra importância de equals() e hashCode()
     */
    private static void exemploComObjetos() {
        System.out.println("--- HASHSET COM OBJETOS PERSONALIZADOS ---");
        
        Set<Produto> estoque = new HashSet<>();
        
        // Adicionando produtos
        estoque.add(new Produto("001", "Notebook", 2500.00));
        estoque.add(new Produto("002", "Mouse", 50.00));
        estoque.add(new Produto("003", "Teclado", 150.00));
        estoque.add(new Produto("001", "Notebook", 2500.00)); // Duplicata!
        
        System.out.println("Produtos no estoque (duplicatas eliminadas):");
        for (Produto produto : estoque) {
            System.out.println("   • " + produto);
        }
        System.out.println("Total de produtos únicos: " + estoque.size());
        
        // Busca por produto específico
        Produto produtoBusca = new Produto("002", "Mouse", 50.00);
        boolean encontrado = estoque.contains(produtoBusca);
        System.out.println("Produto Mouse encontrado? " + encontrado);
        
        // Removendo produto
        boolean removeu = estoque.remove(produtoBusca);
        System.out.println("Produto removido? " + removeu);
        System.out.println("Estoque após remoção: " + estoque.size() + " produtos");
        
        // Demonstrando agrupamento por categoria
        Set<String> categorias = new HashSet<>();
        estoque.forEach(produto -> categorias.add(produto.getCategoria()));
        
        System.out.println("Categorias únicas: " + categorias);
        
        // Filtrando produtos por preço usando Stream
        Set<Produto> produtosCaros = estoque.stream()
            .filter(produto -> produto.getPreco() > 100)
            .collect(HashSet::new, HashSet::add, HashSet::addAll);
        
        System.out.println("Produtos caros (>R$100): " + produtosCaros.size());
    }
    
    /**
     * Demonstração de características de performance
     */
    private static void exemploPerformance() {
        System.out.println("--- PERFORMANCE E CARACTERÍSTICAS ---");
        
        // Comparando HashSet vs ArrayList para busca
        List<Integer> lista = new ArrayList<>();
        Set<Integer> conjunto = new HashSet<>();
        
        // Adicionando elementos
        for (int i = 0; i < 10000; i++) {
            lista.add(i);
            conjunto.add(i);
        }
        
        // Testando busca - ArrayList O(n)
        long inicioLista = System.nanoTime();
        boolean encontrouLista = lista.contains(9999);
        long fimLista = System.nanoTime();
        
        // Testando busca - HashSet O(1)
        long inicioSet = System.nanoTime();
        boolean encontrouSet = conjunto.contains(9999);
        long fimSet = System.nanoTime();
        
        System.out.println("Busca em ArrayList (10.000 elementos): " + 
                          (fimLista - inicioLista) + " nanosegundos");
        System.out.println("Busca em HashSet (10.000 elementos): " + 
                          (fimSet - inicioSet) + " nanosegundos");
        
        // Diferença de velocidade
        double diferenca = (double)(fimLista - inicioLista) / (fimSet - inicioSet);
        System.out.println("HashSet é ~" + String.format("%.1f", diferenca) + "x mais rápido para busca");
        
        // Características do HashSet
        System.out.println("\n📋 Características do HashSet:");
        System.out.println("   ✅ Elimina duplicatas automaticamente");
        System.out.println("   ✅ Busca O(1) em média");
        System.out.println("   ✅ Inserção O(1) em média");
        System.out.println("   ✅ Remoção O(1) em média");
        System.out.println("   ❌ Não mantém ordem de inserção");
        System.out.println("   ❌ Não permite acesso por índice");
        System.out.println("   ⚠️ Requer equals() e hashCode() bem implementados");
        
        // Quando usar HashSet
        System.out.println("\n🎯 Quando usar HashSet:");
        System.out.println("   • Eliminar duplicatas de uma coleção");
        System.out.println("   • Verificar se elemento existe rapidamente");
        System.out.println("   • Operações matemáticas de conjuntos");
        System.out.println("   • Quando ordem não importa");
        System.out.println("   • Performance é crítica para busca/inserção");
    }
}

/**
 * Classe Produto para demonstrar HashSet com objetos
 * Implementa equals() e hashCode() corretamente
 */
class Produto {
    private String codigo;
    private String nome;
    private double preco;
    
    public Produto(String codigo, String nome, double preco) {
        this.codigo = codigo;
        this.nome = nome;
        this.preco = preco;
    }
    
    public String getCodigo() { return codigo; }
    public String getNome() { return nome; }
    public double getPreco() { return preco; }
    
    public String getCategoria() {
        // Simplificado: categoriza por preço
        if (preco < 100) return "Acessório";
        else if (preco < 1000) return "Periférico";
        else return "Equipamento";
    }
    
    // Implementação correta para uso em HashSet
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        
        Produto produto = (Produto) obj;
        return Objects.equals(codigo, produto.codigo);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(codigo);
    }
    
    @Override
    public String toString() {
        return codigo + " - " + nome + " (R$ " + String.format("%.2f", preco) + ")";
    }
}