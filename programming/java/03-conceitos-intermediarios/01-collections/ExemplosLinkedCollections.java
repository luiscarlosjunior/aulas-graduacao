import java.util.*;

/**
 * Exemplos completos de LinkedHashSet e LinkedHashMap
 * Collections que preservam ordem de inserção
 * 
 * LinkedHashSet: Combina velocidade do HashSet com ordem do insertion
 * LinkedHashMap: Combina velocidade do HashMap com ordem de inserção
 * 
 * Conceitos POO demonstrados:
 * - Encapsulamento: Lista duplamente ligada interna oculta
 * - Herança: Especialização de HashSet e HashMap
 * - Polimorfismo: Mantém compatibilidade com interfaces pai
 * - Abstração: Ordem preservada transparentemente
 * 
 * @author Aulas Graduação
 */
public class ExemplosLinkedCollections {
    
    public static void main(String[] args) {
        System.out.println("=== LINKED COLLECTIONS - ORDEM DE INSERÇÃO ===\n");
        
        exemploLinkedHashSet();
        System.out.println();
        
        exemploLinkedHashMap();
        System.out.println();
        
        comparacaoPerformance();
        System.out.println();
        
        exemploPratico();
    }
    
    /**
     * Exemplos completos de LinkedHashSet
     */
    private static void exemploLinkedHashSet() {
        System.out.println("--- LINKEDHASHSET: ORDEM + VELOCIDADE ---");
        
        // Comparação entre HashSet, LinkedHashSet e TreeSet
        System.out.println("🔄 Comparando diferentes implementações de Set:");
        
        // HashSet - sem ordem garantida
        Set<String> hashSet = new HashSet<>();
        hashSet.add("terceiro");
        hashSet.add("primeiro");
        hashSet.add("segundo");
        hashSet.add("quarto");
        
        // LinkedHashSet - mantém ordem de inserção
        Set<String> linkedHashSet = new LinkedHashSet<>();
        linkedHashSet.add("terceiro");
        linkedHashSet.add("primeiro");
        linkedHashSet.add("segundo");
        linkedHashSet.add("quarto");
        
        // TreeSet - ordem natural (alfabética)
        Set<String> treeSet = new TreeSet<>();
        treeSet.add("terceiro");
        treeSet.add("primeiro");
        treeSet.add("segundo");
        treeSet.add("quarto");
        
        System.out.println("HashSet (sem ordem): " + hashSet);
        System.out.println("LinkedHashSet (inserção): " + linkedHashSet);
        System.out.println("TreeSet (alfabética): " + treeSet);
        
        // Demonstrando eliminação de duplicatas com ordem preservada
        System.out.println("\n📝 Eliminação de duplicatas preservando ordem:");
        
        List<String> listComDuplicatas = Arrays.asList(
            "Java", "Python", "JavaScript", "Java", "C++", "Python", "Go", "JavaScript"
        );
        
        System.out.println("Lista original: " + listComDuplicatas);
        
        // Usando LinkedHashSet para remover duplicatas mantendo ordem
        Set<String> semDuplicatas = new LinkedHashSet<>(listComDuplicatas);
        System.out.println("Sem duplicatas (ordem preservada): " + semDuplicatas);
        
        // Convertendo de volta para lista
        List<String> listaLimpa = new ArrayList<>(semDuplicatas);
        System.out.println("Lista final: " + listaLimpa);
        
        // Operações específicas do LinkedHashSet
        System.out.println("\n🎯 Operações do LinkedHashSet:");
        
        LinkedHashSet<Integer> numeros = new LinkedHashSet<>();
        numeros.add(10);
        numeros.add(5);
        numeros.add(15);
        numeros.add(3);
        numeros.add(12);
        
        System.out.println("Números na ordem de inserção: " + numeros);
        
        // Iteração mantém a ordem
        System.out.println("Iteração:");
        Iterator<Integer> iterator = numeros.iterator();
        while (iterator.hasNext()) {
            System.out.print("  " + iterator.next());
        }
        System.out.println();
        
        // Remoção não afeta ordem dos elementos restantes
        numeros.remove(5);
        System.out.println("Após remover 5: " + numeros);
        
        // Reinserção vai para o final
        numeros.add(20);
        System.out.println("Após adicionar 20: " + numeros);
    }
    
    /**
     * Exemplos completos de LinkedHashMap
     */
    private static void exemploLinkedHashMap() {
        System.out.println("--- LINKEDHASHMAP: MAPEAMENTO COM ORDEM ---");
        
        // Comparação entre HashMap, LinkedHashMap e TreeMap
        System.out.println("🗺️ Comparando diferentes implementações de Map:");
        
        // HashMap - sem ordem garantida
        Map<String, Integer> hashMap = new HashMap<>();
        hashMap.put("Banana", 3);
        hashMap.put("Maçã", 5);
        hashMap.put("Laranja", 2);
        hashMap.put("Uva", 8);
        
        // LinkedHashMap - mantém ordem de inserção
        Map<String, Integer> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put("Banana", 3);
        linkedHashMap.put("Maçã", 5);
        linkedHashMap.put("Laranja", 2);
        linkedHashMap.put("Uva", 8);
        
        // TreeMap - ordem natural das chaves
        Map<String, Integer> treeMap = new TreeMap<>();
        treeMap.put("Banana", 3);
        treeMap.put("Maçã", 5);
        treeMap.put("Laranja", 2);
        treeMap.put("Uva", 8);
        
        System.out.println("HashMap: " + hashMap);
        System.out.println("LinkedHashMap: " + linkedHashMap);
        System.out.println("TreeMap: " + treeMap);
        
        // LinkedHashMap com access-order (LRU behavior)
        System.out.println("\n🔄 LinkedHashMap com access-order (LRU):");
        
        // Capacidade inicial 16, load factor 0.75, access-order true
        LinkedHashMap<String, String> lruCache = new LinkedHashMap<>(16, 0.75f, true);
        
        lruCache.put("arquivo1.txt", "conteúdo 1");
        lruCache.put("arquivo2.txt", "conteúdo 2");
        lruCache.put("arquivo3.txt", "conteúdo 3");
        lruCache.put("arquivo4.txt", "conteúdo 4");
        
        System.out.println("Cache inicial: " + lruCache.keySet());
        
        // Acessando elementos - eles vão para o final
        lruCache.get("arquivo2.txt");
        System.out.println("Após acessar arquivo2: " + lruCache.keySet());
        
        lruCache.get("arquivo1.txt");
        System.out.println("Após acessar arquivo1: " + lruCache.keySet());
        
        // Implementando cache LRU com tamanho limitado
        System.out.println("\n💾 Cache LRU com tamanho limitado:");
        
        LRUCache<String, String> cache = new LRUCache<>(3);
        cache.put("A", "valor A");
        cache.put("B", "valor B");
        cache.put("C", "valor C");
        
        System.out.println("Cache (3/3): " + cache.keySet());
        
        // Adicionando quarto elemento - o mais antigo é removido
        cache.put("D", "valor D");
        System.out.println("Após adicionar D: " + cache.keySet());
        
        // Acessando B - ele fica mais recente
        cache.get("B");
        System.out.println("Após acessar B: " + cache.keySet());
        
        // Adicionando E - C é removido (mais antigo não acessado)
        cache.put("E", "valor E");
        System.out.println("Após adicionar E: " + cache.keySet());
    }
    
    /**
     * Comparação de performance entre implementações
     */
    private static void comparacaoPerformance() {
        System.out.println("--- COMPARAÇÃO DE PERFORMANCE ---");
        
        final int ELEMENTOS = 100000;
        
        System.out.println("📊 Teste com " + ELEMENTOS + " elementos:");
        
        // Teste de inserção
        System.out.println("\n⚡ Inserção:");
        
        long inicio = System.nanoTime();
        Set<Integer> hashSet = new HashSet<>();
        for (int i = 0; i < ELEMENTOS; i++) {
            hashSet.add(i);
        }
        long tempoHashSet = System.nanoTime() - inicio;
        
        inicio = System.nanoTime();
        Set<Integer> linkedHashSet = new LinkedHashSet<>();
        for (int i = 0; i < ELEMENTOS; i++) {
            linkedHashSet.add(i);
        }
        long tempoLinkedHashSet = System.nanoTime() - inicio;
        
        inicio = System.nanoTime();
        Set<Integer> treeSet = new TreeSet<>();
        for (int i = 0; i < ELEMENTOS; i++) {
            treeSet.add(i);
        }
        long tempoTreeSet = System.nanoTime() - inicio;
        
        System.out.println("  HashSet: " + String.format("%.2f", tempoHashSet / 1_000_000.0) + " ms");
        System.out.println("  LinkedHashSet: " + String.format("%.2f", tempoLinkedHashSet / 1_000_000.0) + " ms");
        System.out.println("  TreeSet: " + String.format("%.2f", tempoTreeSet / 1_000_000.0) + " ms");
        
        // Teste de busca
        System.out.println("\n🔍 Busca (10.000 elementos aleatórios):");
        
        Random random = new Random();
        
        inicio = System.nanoTime();
        for (int i = 0; i < 10000; i++) {
            hashSet.contains(random.nextInt(ELEMENTOS));
        }
        long buscaHashSet = System.nanoTime() - inicio;
        
        inicio = System.nanoTime();
        for (int i = 0; i < 10000; i++) {
            linkedHashSet.contains(random.nextInt(ELEMENTOS));
        }
        long buscaLinkedHashSet = System.nanoTime() - inicio;
        
        inicio = System.nanoTime();
        for (int i = 0; i < 10000; i++) {
            treeSet.contains(random.nextInt(ELEMENTOS));
        }
        long buscaTreeSet = System.nanoTime() - inicio;
        
        System.out.println("  HashSet: " + String.format("%.2f", buscaHashSet / 1_000_000.0) + " ms");
        System.out.println("  LinkedHashSet: " + String.format("%.2f", buscaLinkedHashSet / 1_000_000.0) + " ms");
        System.out.println("  TreeSet: " + String.format("%.2f", buscaTreeSet / 1_000_000.0) + " ms");
        
        // Resumo das características
        System.out.println("\n📋 RESUMO DAS CARACTERÍSTICAS:");
        System.out.println();
        System.out.println("🔹 HashSet:");
        System.out.println("  ✅ Melhor performance geral: O(1)");
        System.out.println("  ❌ Sem ordem garantida");
        System.out.println("  ✅ Menor uso de memória");
        System.out.println();
        System.out.println("🔗 LinkedHashSet:");
        System.out.println("  ✅ Performance quase igual ao HashSet: O(1)");
        System.out.println("  ✅ Mantém ordem de inserção");
        System.out.println("  ❌ Uso adicional de memória (~20-30%)");
        System.out.println();
        System.out.println("🌳 TreeSet:");
        System.out.println("  ❌ Performance menor: O(log n)");
        System.out.println("  ✅ Ordem natural/comparador");
        System.out.println("  ✅ Operações de navegação");
    }
    
    /**
     * Exemplo prático: histórico de navegação
     */
    private static void exemploPratico() {
        System.out.println("--- EXEMPLO PRÁTICO: HISTÓRICO DE NAVEGAÇÃO ---");
        
        // Histórico que mantém ordem e remove duplicatas
        LinkedHashSet<String> historicoUnico = new LinkedHashSet<>();
        
        // Simulando navegação
        String[] navegacao = {
            "google.com",
            "github.com", 
            "stackoverflow.com",
            "google.com",        // duplicata - será ignorada
            "oracle.com",
            "github.com",        // duplicata - será movida para o final? Não!
            "youtube.com",
            "stackoverflow.com"  // duplicata - será ignorada
        };
        
        System.out.println("🌐 Simulação de navegação:");
        for (String site : navegacao) {
            boolean novo = historicoUnico.add(site);
            System.out.println("  Visitou: " + site + (novo ? " (novo)" : " (já visitado)"));
        }
        
        System.out.println("\n📋 Histórico final (sem duplicatas, ordem preservada):");
        historicoUnico.forEach(site -> System.out.println("  • " + site));
        
        // Histórico com acesso recente (LRU)
        System.out.println("\n🕒 Histórico com páginas recentes (LRU):");
        
        LRUCache<String, VisitaInfo> historicoRecente = new LRUCache<>(5);
        
        historicoRecente.put("google.com", new VisitaInfo("Google", "09:00"));
        historicoRecente.put("github.com", new VisitaInfo("GitHub", "09:15"));
        historicoRecente.put("stackoverflow.com", new VisitaInfo("Stack Overflow", "09:30"));
        historicoRecente.put("oracle.com", new VisitaInfo("Oracle", "09:45"));
        historicoRecente.put("youtube.com", new VisitaInfo("YouTube", "10:00"));
        
        System.out.println("Cache cheio (5/5): " + historicoRecente.keySet());
        
        // Acessando páginas antigas
        historicoRecente.get("github.com");  // github fica recente
        historicoRecente.get("google.com");  // google fica recente
        
        System.out.println("Após acessar github e google: " + historicoRecente.keySet());
        
        // Adicionando nova página - remove a menos recente
        historicoRecente.put("wikipedia.org", new VisitaInfo("Wikipedia", "10:15"));
        System.out.println("Após visitar wikipedia: " + historicoRecente.keySet());
        
        System.out.println("\n💡 Casos de uso para Linked Collections:");
        System.out.println("  🔗 LinkedHashSet:");
        System.out.println("    • Remover duplicatas preservando ordem");
        System.out.println("    • Históricos únicos");
        System.out.println("    • Listas de favoritos");
        System.out.println();
        System.out.println("  🗺️ LinkedHashMap:");
        System.out.println("    • Caches LRU (Least Recently Used)");
        System.out.println("    • Configurações com ordem");
        System.out.println("    • Histórico de acessos");
        System.out.println("    • Serialização com ordem preservada");
    }
    
    /**
     * Implementação simples de cache LRU usando LinkedHashMap
     */
    static class LRUCache<K, V> extends LinkedHashMap<K, V> {
        private final int capacidade;
        
        public LRUCache(int capacidade) {
            // Capacidade inicial, load factor, access-order = true
            super(capacidade + 1, 1.0f, true);
            this.capacidade = capacidade;
        }
        
        @Override
        protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
            // Remove o elemento mais antigo quando excede a capacidade
            return size() > capacidade;
        }
        
        // Métodos adicionais para demonstração
        public V getQuietly(K key) {
            // Busca sem afetar a ordem de acesso
            return super.getOrDefault(key, null);
        }
        
        public void printCache() {
            System.out.println("Cache (mais recente -> mais antigo): " + 
                             new ArrayList<>(keySet()));
        }
    }
    
    /**
     * Classe auxiliar para informações de visita
     */
    static class VisitaInfo {
        private String titulo;
        private String horario;
        
        public VisitaInfo(String titulo, String horario) {
            this.titulo = titulo;
            this.horario = horario;
        }
        
        public String getTitulo() { return titulo; }
        public String getHorario() { return horario; }
        
        @Override
        public String toString() {
            return titulo + " (" + horario + ")";
        }
    }
}