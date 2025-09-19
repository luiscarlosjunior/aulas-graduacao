/**
 * Teste Collections - Programa Principal
 * 
 * Este programa demonstra todos os exemplos de Collections Framework
 * integrados com Programação Orientada a Objetos.
 * 
 * Execute este arquivo para ver uma demonstração completa de:
 * - ArrayList com objetos básicos
 * - LinkedList com filas, pilhas e listas
 * - HashMap com mapeamentos chave-valor
 * - TreeMap com mapeamentos ordenados
 * - HashSet com eliminação de duplicatas
 * - TreeSet com conjuntos ordenados
 * - LinkedHashSet/LinkedHashMap com ordem de inserção
 * - PriorityQueue com filas de prioridade
 * - Collections com objetos personalizados (POO)
 * - Sistema escolar completo (POO avançado)
 * 
 * @author Aulas Graduação
 */
public class TesteCollections {
    
    public static void main(String[] args) {
        System.out.println("🎯 TESTE COMPLETO: COLLECTIONS FRAMEWORK + POO");
        System.out.println("=" .repeat(60));
        
        executarExemplo("📋 ArrayList Básico", () -> {
            System.out.println("Executando ExemplosArrayList...");
            ExemplosArrayList.main(new String[]{});
        });
        
        executarExemplo("🔗 LinkedList (Listas, Filas, Pilhas)", () -> {
            System.out.println("Executando ExemplosLinkedList...");
            ExemplosLinkedList.main(new String[]{});
        });
        
        executarExemplo("🗺️ HashMap Básico", () -> {
            System.out.println("Executando ExemplosHashMap...");
            ExemplosHashMap.main(new String[]{});
        });
        
        executarExemplo("🌳 TreeMap (Mapeamento Ordenado)", () -> {
            System.out.println("Executando ExemplosTreeMap...");
            ExemplosTreeMap.main(new String[]{});
        });
        
        executarExemplo("🔄 HashSet Básico", () -> {
            System.out.println("Executando ExemplosHashSet...");
            ExemplosHashSet.main(new String[]{});
        });
        
        executarExemplo("🌲 TreeSet (Conjunto Ordenado)", () -> {
            System.out.println("Executando ExemplosTreeSet...");
            ExemplosTreeSet.main(new String[]{});
        });
        
        executarExemplo("🔗 LinkedCollections (Ordem de Inserção)", () -> {
            System.out.println("Executando ExemplosLinkedCollections...");
            ExemplosLinkedCollections.main(new String[]{});
        });
        
        executarExemplo("⚡ PriorityQueue (Fila de Prioridade)", () -> {
            System.out.println("Executando ExemplosPriorityQueue...");
            ExemplosPriorityQueue.main(new String[]{});
        });
        
        executarExemplo("🧑‍🤝‍🧑 Collections com POO", () -> {
            System.out.println("Executando ColecoesPessoas...");
            ColecoesPessoas.main(new String[]{});
        });
        
        executarExemplo("🏫 Sistema Escolar Completo", () -> {
            System.out.println("Executando SistemaEscolar...");
            SistemaEscolar.main(new String[]{});
        });
        
        System.out.println("\n🎉 DEMONSTRAÇÃO COMPLETA FINALIZADA!");
        System.out.println("=" .repeat(60));
        System.out.println("✅ Conceitos demonstrados:");
        System.out.println("   • Todas as principais Collections: List, Set, Map, Queue");
        System.out.println("   • ArrayList, LinkedList, Vector");
        System.out.println("   • HashMap, TreeMap, LinkedHashMap");
        System.out.println("   • HashSet, TreeSet, LinkedHashSet");
        System.out.println("   • PriorityQueue, ArrayDeque");
        System.out.println("   • Encapsulamento, Herança, Polimorfismo");
        System.out.println("   • Composição e Agregação");
        System.out.println("   • Design Patterns (Strategy, Observer, Repository)");
        System.out.println("   • Stream API com objetos");
        System.out.println("   • Relacionamentos entre objetos");
        System.out.println("   • Comparadores e ordenação");
        System.out.println("   • Performance e otimização");
        System.out.println("\n📚 Continue estudando: Generics, Enums, Annotations...");
    }
    
    private static void executarExemplo(String titulo, Runnable exemplo) {
        System.out.println("\n" + titulo);
        System.out.println("-".repeat(50));
        
        try {
            exemplo.run();
        } catch (Exception e) {
            System.err.println("❌ Erro ao executar " + titulo + ": " + e.getMessage());
        }
        
        System.out.println("\n✅ " + titulo + " concluído!");
        System.out.println("-".repeat(50));
        
        // Pausa para visualização
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}