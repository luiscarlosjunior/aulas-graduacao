import java.util.*;

/**
 * Exemplos completos de HashMap - Mapeamento chave-valor
 * 
 * HashMap é a implementação de Map mais utilizada em Java.
 * Oferece operações O(1) para busca, inserção e remoção.
 * 
 * @author Aulas Graduação
 */
public class ExemplosHashMap {
    
    public static void main(String[] args) {
        System.out.println("=== HASHMAP - EXEMPLOS COMPLETOS ===\n");
        
        exemploBasico();
        System.out.println();
        
        exemploOperacoes();
        System.out.println();
        
        exemploIteracao();
        System.out.println();
        
        exemploPratico();
        System.out.println();
        
        exemploAvancado();
    }
    
    /**
     * Exemplo básico de criação e uso
     */
    private static void exemploBasico() {
        System.out.println("--- EXEMPLO BÁSICO ---");
        
        // Criação de HashMap
        HashMap<String, Integer> idades = new HashMap<>();
        
        // Adicionando pares chave-valor
        idades.put("João", 25);
        idades.put("Maria", 30);
        idades.put("Carlos", 28);
        idades.put("Ana", 22);
        
        System.out.println("Mapa de idades: " + idades);
        System.out.println("Tamanho: " + idades.size());
        System.out.println("Está vazio? " + idades.isEmpty());
        
        // Busca por chave
        Integer idadeJoao = idades.get("João");
        System.out.println("Idade do João: " + idadeJoao);
        
        // Busca com valor padrão
        Integer idadePedro = idades.getOrDefault("Pedro", 0);
        System.out.println("Idade do Pedro (padrão): " + idadePedro);
        
        // Verificações
        System.out.println("Contém chave 'Maria'? " + idades.containsKey("Maria"));
        System.out.println("Contém valor 25? " + idades.containsValue(25));
        
        // Conjunto de chaves e valores
        System.out.println("Chaves: " + idades.keySet());
        System.out.println("Valores: " + idades.values());
    }
    
    /**
     * Operações de modificação
     */
    private static void exemploOperacoes() {
        System.out.println("--- OPERAÇÕES DE MODIFICAÇÃO ---");
        
        HashMap<String, String> capitais = new HashMap<>();
        capitais.put("Brasil", "Brasília");
        capitais.put("França", "Paris");
        capitais.put("Japão", "Tóquio");
        
        System.out.println("Capitais iniciais: " + capitais);
        
        // Substituição
        String antigaCapital = capitais.put("Brasil", "Rio de Janeiro"); // Retorna valor anterior
        System.out.println("Capital anterior do Brasil: " + antigaCapital);
        System.out.println("Após substituição: " + capitais);
        
        // Substituição condicional (só se chave existir)
        String substituida = capitais.replace("Brasil", "Brasília");
        System.out.println("Substituiu Brasil: " + substituida);
        
        // Substituição com valor específico
        boolean substituiu = capitais.replace("França", "Paris", "Lyon");
        System.out.println("Substituiu França de Paris para Lyon? " + substituiu);
        
        // Inserção condicional (só se chave não existir)
        String inserido = capitais.putIfAbsent("Alemanha", "Berlim");
        System.out.println("Inseriu Alemanha? " + (inserido == null ? "Sim" : "Não, já existia: " + inserido));
        System.out.println("Após inserção condicional: " + capitais);
        
        // Remoção
        String removida = capitais.remove("Japão");
        System.out.println("Capital removida do Japão: " + removida);
        
        // Remoção condicional
        boolean removeu = capitais.remove("Brasil", "Rio de Janeiro");
        System.out.println("Removeu Brasil com valor 'Rio de Janeiro'? " + removeu);
        
        System.out.println("Estado final: " + capitais);
    }
    
    /**
     * Diferentes formas de iteração
     */
    private static void exemploIteracao() {
        System.out.println("--- FORMAS DE ITERAÇÃO ---");
        
        HashMap<String, Double> precos = new HashMap<>();
        precos.put("Notebook", 2500.00);
        precos.put("Mouse", 50.00);
        precos.put("Teclado", 150.00);
        precos.put("Monitor", 800.00);
        
        // 1. Iteração pelas chaves
        System.out.println("1. Iteração por chaves:");
        for (String produto : precos.keySet()) {
            System.out.println("   " + produto + ": R$ " + precos.get(produto));
        }
        
        // 2. Iteração pelos valores
        System.out.println("2. Iteração por valores:");
        double total = 0;
        for (Double preco : precos.values()) {
            total += preco;
            System.out.println("   Preço: R$ " + preco);
        }
        System.out.println("   Total: R$ " + total);
        
        // 3. Iteração pelos pares (entrySet) - MAIS EFICIENTE
        System.out.println("3. Iteração por Entry (pares):");
        for (Map.Entry<String, Double> entry : precos.entrySet()) {
            String produto = entry.getKey();
            Double preco = entry.getValue();
            System.out.println("   " + produto + " custa R$ " + preco);
        }
        
        // 4. Stream API (Java 8+)
        System.out.println("4. Stream API:");
        precos.entrySet().stream()
            .filter(entry -> entry.getValue() > 100)
            .forEach(entry -> System.out.println("   Produto caro: " + entry.getKey()));
        
        // 5. forEach com lambda (Java 8+)
        System.out.println("5. forEach com lambda:");
        precos.forEach((produto, preco) -> {
            if (preco < 100) {
                System.out.println("   Produto barato: " + produto);
            }
        });
    }
    
    /**
     * Exemplo prático: sistema de inventário
     */
    private static void exemploPratico() {
        System.out.println("--- EXEMPLO PRÁTICO: INVENTÁRIO ---");
        
        // Mapa para controle de estoque
        HashMap<String, Integer> estoque = new HashMap<>();
        
        // Carregando estoque inicial
        estoque.put("Arroz", 100);
        estoque.put("Feijão", 80);
        estoque.put("Açúcar", 50);
        estoque.put("Café", 25);
        
        System.out.println("Estoque inicial: " + estoque);
        
        // Simulando vendas
        venderProduto(estoque, "Arroz", 10);
        venderProduto(estoque, "Feijão", 15);
        venderProduto(estoque, "Sal", 5); // Produto não existe
        
        // Simulando reposição
        reporEstoque(estoque, "Café", 20);
        reporEstoque(estoque, "Óleo", 30); // Produto novo
        
        // Relatório de estoque baixo
        System.out.println("\n📊 RELATÓRIO DE ESTOQUE:");
        int limite = 30;
        System.out.println("Produtos com estoque baixo (< " + limite + "):");
        
        estoque.entrySet().stream()
            .filter(entry -> entry.getValue() < limite)
            .sorted(Map.Entry.comparingByValue()) // Ordena por quantidade
            .forEach(entry -> System.out.println("   ⚠️ " + entry.getKey() + ": " + entry.getValue() + " unidades"));
        
        // Valor total do estoque (simulando preços)
        HashMap<String, Double> precoUnitario = new HashMap<>();
        precoUnitario.put("Arroz", 5.50);
        precoUnitario.put("Feijão", 8.00);
        precoUnitario.put("Açúcar", 3.20);
        precoUnitario.put("Café", 12.00);
        precoUnitario.put("Óleo", 6.50);
        
        double valorTotal = estoque.entrySet().stream()
            .mapToDouble(entry -> {
                String produto = entry.getKey();
                int quantidade = entry.getValue();
                double preco = precoUnitario.getOrDefault(produto, 0.0);
                return quantidade * preco;
            })
            .sum();
        
        System.out.println("💰 Valor total do estoque: R$ " + String.format("%.2f", valorTotal));
    }
    
    /**
     * Método auxiliar para venda
     */
    private static void venderProduto(HashMap<String, Integer> estoque, String produto, int quantidade) {
        Integer estoqueAtual = estoque.get(produto);
        
        if (estoqueAtual == null) {
            System.out.println("❌ Produto '" + produto + "' não encontrado no estoque");
            return;
        }
        
        if (estoqueAtual < quantidade) {
            System.out.println("⚠️ Estoque insuficiente de '" + produto + "' (disponível: " + estoqueAtual + ")");
            return;
        }
        
        estoque.put(produto, estoqueAtual - quantidade);
        System.out.println("✅ Vendeu " + quantidade + " unidades de '" + produto + "' (restam: " + estoque.get(produto) + ")");
    }
    
    /**
     * Método auxiliar para reposição
     */
    private static void reporEstoque(HashMap<String, Integer> estoque, String produto, int quantidade) {
        Integer estoqueAtual = estoque.getOrDefault(produto, 0);
        estoque.put(produto, estoqueAtual + quantidade);
        System.out.println("📦 Repôs " + quantidade + " unidades de '" + produto + "' (total: " + estoque.get(produto) + ")");
    }
    
    /**
     * Exemplos avançados
     */
    private static void exemploAvancado() {
        System.out.println("--- EXEMPLOS AVANÇADOS ---");
        
        // Merge - combina valores para chaves duplicadas
        HashMap<String, Integer> mapa1 = new HashMap<>();
        mapa1.put("A", 1);
        mapa1.put("B", 2);
        
        HashMap<String, Integer> mapa2 = new HashMap<>();
        mapa2.put("B", 3);
        mapa2.put("C", 4);
        
        System.out.println("Mapa 1: " + mapa1);
        System.out.println("Mapa 2: " + mapa2);
        
        // Merge usando putAll (sobrescreve)
        HashMap<String, Integer> merged1 = new HashMap<>(mapa1);
        merged1.putAll(mapa2);
        System.out.println("Merge com putAll: " + merged1);
        
        // Merge usando merge() para somar valores
        HashMap<String, Integer> merged2 = new HashMap<>(mapa1);
        mapa2.forEach((key, value) -> merged2.merge(key, value, Integer::sum));
        System.out.println("Merge somando valores: " + merged2);
        
        // Compute - calcula valor baseado na chave
        HashMap<String, Integer> contador = new HashMap<>();
        String[] palavras = {"java", "python", "java", "javascript", "java", "python"};
        
        for (String palavra : palavras) {
            contador.compute(palavra, (key, val) -> (val == null) ? 1 : val + 1);
        }
        System.out.println("Contador de palavras: " + contador);
        
        // ComputeIfAbsent - calcula apenas se chave não existir
        HashMap<String, List<Integer>> grupos = new HashMap<>();
        grupos.computeIfAbsent("pares", k -> new ArrayList<>()).add(2);
        grupos.computeIfAbsent("ímpares", k -> new ArrayList<>()).add(1);
        grupos.computeIfAbsent("pares", k -> new ArrayList<>()).add(4);
        grupos.computeIfAbsent("ímpares", k -> new ArrayList<>()).add(3);
        
        System.out.println("Grupos: " + grupos);
        
        // Removendo entradas com base em condições
        HashMap<String, Integer> idades = new HashMap<>();
        idades.put("João", 17);
        idades.put("Maria", 25);
        idades.put("Pedro", 16);
        idades.put("Ana", 30);
        
        System.out.println("Idades antes: " + idades);
        idades.entrySet().removeIf(entry -> entry.getValue() < 18);
        System.out.println("Apenas maiores de idade: " + idades);
    }
}