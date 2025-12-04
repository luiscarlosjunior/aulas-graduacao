import java.util.*;
import java.util.function.Function;

/**
 * Exemplos completos de TreeMap - Mapeamento ordenado por chave
 * 
 * TreeMap é uma implementação de SortedMap baseada em árvore Red-Black.
 * Mantém as chaves automaticamente ordenadas e oferece operações O(log n).
 * 
 * Conceitos POO demonstrados:
 * - Encapsulamento: Estrutura de árvore balanceada oculta
 * - Polimorfismo: Implementa Map, SortedMap, NavigableMap
 * - Herança: Hierarquia especializada para mapas ordenados
 * - Abstração: Ordenação automática e operações de navegação
 * 
 * @author Aulas Graduação
 */
public class ExemplosTreeMap {
    
    public static void main(String[] args) {
        System.out.println("=== TREEMAP - EXEMPLOS COMPLETOS ===\n");
        
        exemploBasico();
        System.out.println();
        
        exemploOrdenacao();
        System.out.println();
        
        exemploNavegacao();
        System.out.println();
        
        exemploComparador();
        System.out.println();
        
        exemploPratico();
    }
    
    /**
     * Exemplo básico de TreeMap
     */
    private static void exemploBasico() {
        System.out.println("--- TREEMAP BÁSICO ---");
        
        // TreeMap mantém chaves ordenadas automaticamente
        TreeMap<String, Integer> estoque = new TreeMap<>();
        
        // Inserindo produtos em ordem aleatória
        estoque.put("Feijão", 50);
        estoque.put("Arroz", 100);
        estoque.put("Macarrão", 75);
        estoque.put("Açúcar", 30);
        estoque.put("Café", 25);
        
        System.out.println("Estoque (chaves automaticamente ordenadas):");
        estoque.forEach((produto, quantidade) -> 
            System.out.println("  " + produto + ": " + quantidade + " unidades")
        );
        
        // Operações básicas
        System.out.println("\n📊 Informações básicas:");
        System.out.println("Primeiro produto: " + estoque.firstKey());
        System.out.println("Último produto: " + estoque.lastKey());
        System.out.println("Quantidade de Arroz: " + estoque.get("Arroz"));
        System.out.println("Total de produtos: " + estoque.size());
        
        // Comparação com HashMap
        HashMap<String, Integer> estoqueHash = new HashMap<>();
        estoqueHash.put("Feijão", 50);
        estoqueHash.put("Arroz", 100);
        estoqueHash.put("Macarrão", 75);
        estoqueHash.put("Açúcar", 30);
        estoqueHash.put("Café", 25);
        
        System.out.println("\nHashMap (sem ordem garantida):");
        estoqueHash.forEach((produto, quantidade) -> 
            System.out.println("  " + produto + ": " + quantidade + " unidades")
        );
    }
    
    /**
     * Exemplo demonstrando diferentes tipos de ordenação
     */
    private static void exemploOrdenacao() {
        System.out.println("--- TIPOS DE ORDENAÇÃO ---");
        
        // Ordenação natural de números
        TreeMap<Integer, String> meses = new TreeMap<>();
        meses.put(12, "Dezembro");
        meses.put(1, "Janeiro");
        meses.put(6, "Junho");
        meses.put(3, "Março");
        meses.put(9, "Setembro");
        
        System.out.println("Meses ordenados por número:");
        meses.forEach((num, nome) -> 
            System.out.println("  " + num + ": " + nome)
        );
        
        // Ordenação natural de strings
        TreeMap<String, Double> notas = new TreeMap<>();
        notas.put("Maria", 8.5);
        notas.put("João", 7.2);
        notas.put("Ana", 9.1);
        notas.put("Carlos", 6.8);
        notas.put("Beatriz", 8.9);
        
        System.out.println("\nNotas ordenadas por nome (alfabética):");
        notas.forEach((nome, nota) -> 
            System.out.println("  " + nome + ": " + nota)
        );
        
        // Demonstrando que TreeMap requer chaves comparáveis
        System.out.println("\n💡 TreeMap requer chaves Comparable ou Comparator");
        System.out.println("   Strings: ordenação lexicográfica natural");
        System.out.println("   Números: ordenação numérica natural");
        System.out.println("   Objetos customizados: precisam implementar Comparable");
    }
    
    /**
     * Exemplo de operações de navegação específicas do TreeMap
     */
    private static void exemploNavegacao() {
        System.out.println("--- OPERAÇÕES DE NAVEGAÇÃO ---");
        
        TreeMap<Integer, String> classificacao = new TreeMap<>();
        classificacao.put(1, "Primeiro lugar");
        classificacao.put(3, "Terceiro lugar");
        classificacao.put(5, "Quinto lugar");
        classificacao.put(8, "Oitavo lugar");
        classificacao.put(10, "Décimo lugar");
        
        System.out.println("Classificação: " + classificacao);
        
        // Operações de busca por chave
        System.out.println("\n🎯 Operações de busca:");
        System.out.println("Posição ≥ 4: " + classificacao.ceilingEntry(4));
        System.out.println("Posição ≤ 4: " + classificacao.floorEntry(4));
        System.out.println("Posição > 4: " + classificacao.higherEntry(4));
        System.out.println("Posição < 4: " + classificacao.lowerEntry(4));
        
        // Submapas
        System.out.println("\n📊 Submapas:");
        System.out.println("Posições 2 a 7: " + classificacao.subMap(2, 8));
        System.out.println("Até posição 5: " + classificacao.headMap(6));
        System.out.println("A partir da posição 5: " + classificacao.tailMap(5));
        
        // Operações destrutivas
        System.out.println("\n🔄 Removendo primeira e última posições:");
        TreeMap<Integer, String> copia = new TreeMap<>(classificacao);
        System.out.println("Remove primeira: " + copia.pollFirstEntry());
        System.out.println("Remove última: " + copia.pollLastEntry());
        System.out.println("Restantes: " + copia);
        
        // Navegação reversa
        System.out.println("\n🔙 Ordem reversa:");
        NavigableMap<Integer, String> reverso = classificacao.descendingMap();
        System.out.println("Classificação decrescente: " + reverso);
    }
    
    /**
     * Exemplo usando Comparator personalizado
     */
    private static void exemploComparador() {
        System.out.println("--- COMPARADORES PERSONALIZADOS ---");
        
        // TreeMap ordenado por valor (não por chave)
        // Primeiro criamos um TreeMap normal e depois transformamos
        Map<String, Integer> vendas = new HashMap<>();
        vendas.put("Ana", 150);
        vendas.put("Bruno", 230);
        vendas.put("Carlos", 180);
        vendas.put("Diana", 270);
        vendas.put("Eduardo", 190);
        
        // Criando TreeMap ordenado por valor
        TreeMap<String, Integer> vendasOrdenadas = new TreeMap<>(
            Comparator.comparing((String key) -> vendas.get(key)).reversed()
                     .thenComparing(key -> key) // desempate por nome
        );
        vendasOrdenadas.putAll(vendas);
        
        System.out.println("🏆 Vendedores ordenados por vendas (decrescente):");
        vendasOrdenadas.forEach((vendedor, quantidade) -> 
            System.out.println("  " + vendedor + ": " + quantidade + " vendas")
        );
        
        // TreeMap ordenado por tamanho da chave
        TreeMap<String, String> porTamanho = new TreeMap<>(
            Comparator.comparing(String::length)
                     .thenComparing(String::compareTo)
        );
        
        porTamanho.put("casa", "dwelling");
        porTamanho.put("bicicleta", "bicycle");
        porTamanho.put("sol", "sun");
        porTamanho.put("programação", "programming");
        porTamanho.put("a", "to");
        
        System.out.println("\n📏 Palavras ordenadas por tamanho:");
        porTamanho.forEach((pt, en) -> 
            System.out.println("  '" + pt + "' (" + pt.length() + ") = " + en)
        );
        
        // TreeMap case-insensitive
        TreeMap<String, String> caseInsensitive = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
        caseInsensitive.put("Banana", "fruit");
        caseInsensitive.put("apple", "fruit");
        caseInsensitive.put("Cherry", "fruit");
        caseInsensitive.put("date", "fruit");
        
        System.out.println("\n🔤 Ordenação case-insensitive:");
        caseInsensitive.forEach((fruta, tipo) -> 
            System.out.println("  " + fruta + " -> " + tipo)
        );
    }
    
    /**
     * Exemplo prático: sistema de agenda telefônica
     */
    private static void exemploPratico() {
        System.out.println("--- EXEMPLO PRÁTICO: AGENDA TELEFÔNICA ---");
        
        // Agenda ordenada alfabeticamente
        TreeMap<String, Contato> agenda = new TreeMap<>();
        
        agenda.put("Ana Silva", new Contato("Ana Silva", "(11) 99999-1111", "ana@email.com"));
        agenda.put("Bruno Costa", new Contato("Bruno Costa", "(11) 99999-2222", "bruno@email.com"));
        agenda.put("Carlos Lima", new Contato("Carlos Lima", "(11) 99999-3333", "carlos@email.com"));
        agenda.put("Diana Santos", new Contato("Diana Santos", "(11) 99999-4444", "diana@email.com"));
        agenda.put("Eduardo Ferreira", new Contato("Eduardo Ferreira", "(11) 99999-5555", "eduardo@email.com"));
        
        System.out.println("📞 Agenda telefônica (ordem alfabética):");
        agenda.forEach((nome, contato) -> 
            System.out.println("  " + contato)
        );
        
        // Buscas específicas
        System.out.println("\n🔍 Buscas na agenda:");
        
        // Busca exata
        String nomeBusca = "Carlos Lima";
        Contato contato = agenda.get(nomeBusca);
        if (contato != null) {
            System.out.println("Contato encontrado: " + contato);
        }
        
        // Busca por prefixo (nomes que começam com 'C')
        System.out.println("\nContatos que começam com 'C':");
        SortedMap<String, Contato> contatosC = agenda.subMap("C", "D");
        contatosC.forEach((nome, c) -> 
            System.out.println("  " + c.getNome() + " - " + c.getTelefone())
        );
        
        // Busca por faixa alfabética
        System.out.println("\nContatos de 'B' até 'D' (exclusive):");
        agenda.subMap("B", "D").forEach((nome, c) -> 
            System.out.println("  " + c.getNome())
        );
        
        // Operações de navegação úteis
        System.out.println("\n🧭 Navegação na agenda:");
        System.out.println("Primeiro contato: " + agenda.firstEntry().getValue().getNome());
        System.out.println("Último contato: " + agenda.lastEntry().getValue().getNome());
        
        // Contato anterior e posterior a 'Carlos'
        String referencia = "Carlos Lima";
        Map.Entry<String, Contato> anterior = agenda.lowerEntry(referencia);
        Map.Entry<String, Contato> posterior = agenda.higherEntry(referencia);
        
        System.out.println("Anterior a '" + referencia + "': " + 
                          (anterior != null ? anterior.getValue().getNome() : "Nenhum"));
        System.out.println("Posterior a '" + referencia + "': " + 
                          (posterior != null ? posterior.getValue().getNome() : "Nenhum"));
        
        // Estatísticas da agenda
        System.out.println("\n📊 Estatísticas da agenda:");
        System.out.println("Total de contatos: " + agenda.size());
        
        long contatosComEmail = agenda.values().stream()
                                     .filter(c -> c.getEmail() != null && !c.getEmail().isEmpty())
                                     .count();
        System.out.println("Contatos com email: " + contatosComEmail);
        
        // Demonstrando eficiência de TreeMap para buscas ordenadas
        System.out.println("\n⚡ Vantagens do TreeMap para agenda:");
        System.out.println("✅ Contatos sempre em ordem alfabética");
        System.out.println("✅ Busca eficiente: O(log n)");
        System.out.println("✅ Navegação entre contatos");
        System.out.println("✅ Busca por faixa de nomes");
        System.out.println("✅ Operações de substring/prefixo");
    }
    
    /**
     * Classe auxiliar para representar um contato
     */
    static class Contato {
        private String nome;
        private String telefone;
        private String email;
        
        public Contato(String nome, String telefone, String email) {
            this.nome = nome;
            this.telefone = telefone;
            this.email = email;
        }
        
        public String getNome() { return nome; }
        public String getTelefone() { return telefone; }
        public String getEmail() { return email; }
        
        @Override
        public String toString() {
            return nome + " - " + telefone + " (" + email + ")";
        }
        
        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Contato contato = (Contato) obj;
            return Objects.equals(nome, contato.nome);
        }
        
        @Override
        public int hashCode() {
            return Objects.hash(nome);
        }
    }
}