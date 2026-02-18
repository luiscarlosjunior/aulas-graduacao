import java.util.*;

/**
 * Exemplos completos de LinkedList - Lista duplamente ligada
 * 
 * LinkedList é uma implementação de List baseada em nós ligados.
 * Oferece operações O(1) para inserção/remoção no início e fim,
 * mas O(n) para acesso por índice.
 * 
 * Conceitos POO demonstrados:
 * - Encapsulamento: Estrutura interna de nós oculta
 * - Polimorfismo: Pode ser usado como List, Queue ou Deque
 * - Herança: Implementa múltiplas interfaces
 * - Abstração: Interface simples para estrutura complexa
 * 
 * @author Aulas Graduação
 */
public class ExemplosLinkedList {
    
    public static void main(String[] args) {
        System.out.println("=== LINKEDLIST - EXEMPLOS COMPLETOS ===\n");
        
        exemploBasico();
        System.out.println();
        
        exemploQueue();
        System.out.println();
        
        exemploDeque();
        System.out.println();
        
        comparacaoPerformance();
        System.out.println();
        
        exemploPolimorfismo();
    }
    
    /**
     * Exemplo básico de uso como List
     */
    private static void exemploBasico() {
        System.out.println("--- LINKEDLIST COMO LIST ---");
        
        // Criação de LinkedList
        LinkedList<String> tarefas = new LinkedList<>();
        
        // Operações básicas de lista
        tarefas.add("Estudar Java");
        tarefas.add("Fazer exercícios");
        tarefas.add("Revisar teoria");
        
        System.out.println("Tarefas: " + tarefas);
        System.out.println("Primeira tarefa: " + tarefas.getFirst());
        System.out.println("Última tarefa: " + tarefas.getLast());
        
        // Inserção no meio
        tarefas.add(1, "Ler documentação");
        System.out.println("Após inserção no índice 1: " + tarefas);
        
        // Acesso por índice (operação custosa em LinkedList)
        System.out.println("Tarefa no índice 2: " + tarefas.get(2));
        
        // Remoção
        tarefas.removeFirst();
        tarefas.removeLast();
        System.out.println("Após remover primeira e última: " + tarefas);
    }
    
    /**
     * Exemplo usando LinkedList como Queue (fila)
     */
    private static void exemploQueue() {
        System.out.println("--- LINKEDLIST COMO QUEUE (FILA) ---");
        
        // LinkedList implementa Queue - FIFO (First In, First Out)
        Queue<String> filaAtendimento = new LinkedList<>();
        
        // Adicionando à fila (offer = add para Queue)
        filaAtendimento.offer("Cliente 1");
        filaAtendimento.offer("Cliente 2");
        filaAtendimento.offer("Cliente 3");
        filaAtendimento.offer("Cliente 4");
        
        System.out.println("Fila de atendimento: " + filaAtendimento);
        System.out.println("Próximo cliente: " + filaAtendimento.peek()); // Não remove
        
        // Processando a fila
        System.out.println("Atendendo clientes:");
        while (!filaAtendimento.isEmpty()) {
            String cliente = filaAtendimento.poll(); // Remove e retorna
            System.out.println("  ✅ Atendido: " + cliente);
            System.out.println("     Restam na fila: " + filaAtendimento.size());
        }
        
        System.out.println("Fila vazia: " + filaAtendimento.isEmpty());
    }
    
    /**
     * Exemplo usando LinkedList como Deque (fila dupla)
     */
    private static void exemploDeque() {
        System.out.println("--- LINKEDLIST COMO DEQUE (FILA DUPLA) ---");
        
        // LinkedList implementa Deque - pode inserir/remover em ambas as pontas
        Deque<String> historico = new LinkedList<>();
        
        // Adicionando no início (como pilha)
        historico.addFirst("Página 1");
        historico.addFirst("Página 2");
        historico.addFirst("Página 3");
        
        System.out.println("Histórico após adicionar no início: " + historico);
        
        // Adicionando no fim (como fila)
        historico.addLast("Página 4");
        historico.addLast("Página 5");
        
        System.out.println("Histórico após adicionar no fim: " + historico);
        
        // Simulando navegação para trás (remove do início)
        System.out.println("🔙 Voltando páginas:");
        while (historico.size() > 2) {
            String pagina = historico.removeFirst();
            System.out.println("  Voltou de: " + pagina);
            System.out.println("  Histórico atual: " + historico);
        }
        
        // Usando como pilha (LIFO)
        System.out.println("\n--- USANDO COMO PILHA (LIFO) ---");
        Deque<Integer> pilha = new LinkedList<>();
        
        // Push elementos
        pilha.push(10);
        pilha.push(20);
        pilha.push(30);
        
        System.out.println("Pilha: " + pilha);
        System.out.println("Topo da pilha: " + pilha.peek());
        
        // Pop elementos
        System.out.println("Removendo da pilha:");
        while (!pilha.isEmpty()) {
            System.out.println("  Pop: " + pilha.pop());
        }
    }
    
    /**
     * Comparação de performance entre ArrayList e LinkedList
     */
    private static void comparacaoPerformance() {
        System.out.println("--- COMPARAÇÃO DE PERFORMANCE ---");
        
        final int TAMANHO = 50000;
        
        // Teste 1: Inserção no início
        System.out.println("🚀 Teste 1: Inserção no início (" + TAMANHO + " elementos)");
        
        ArrayList<Integer> arrayList = new ArrayList<>();
        LinkedList<Integer> linkedList = new LinkedList<>();
        
        // ArrayList - inserção no início
        long inicio = System.nanoTime();
        for (int i = 0; i < TAMANHO; i++) {
            arrayList.add(0, i); // Custoso: move todos os elementos
        }
        long tempoArrayList = System.nanoTime() - inicio;
        
        // LinkedList - inserção no início
        inicio = System.nanoTime();
        for (int i = 0; i < TAMANHO; i++) {
            linkedList.addFirst(i); // Rápido: apenas cria novo nó
        }
        long tempoLinkedList = System.nanoTime() - inicio;
        
        System.out.println("  ArrayList: " + String.format("%.2f", tempoArrayList / 1_000_000.0) + " ms");
        System.out.println("  LinkedList: " + String.format("%.2f", tempoLinkedList / 1_000_000.0) + " ms");
        System.out.println("  LinkedList é " + String.format("%.1fx", (double) tempoArrayList / tempoLinkedList) + " mais rápido");
        
        // Teste 2: Acesso por índice
        System.out.println("\n🎯 Teste 2: Acesso por índice (1000 acessos aleatórios)");
        
        Random random = new Random();
        
        // ArrayList - acesso por índice
        inicio = System.nanoTime();
        for (int i = 0; i < 1000; i++) {
            int index = random.nextInt(arrayList.size());
            arrayList.get(index); // Rápido: acesso direto ao array
        }
        long tempoAcessoArray = System.nanoTime() - inicio;
        
        // LinkedList - acesso por índice
        inicio = System.nanoTime();
        for (int i = 0; i < 1000; i++) {
            int index = random.nextInt(linkedList.size());
            linkedList.get(index); // Lento: precisa percorrer os nós
        }
        long tempoAcessoLinked = System.nanoTime() - inicio;
        
        System.out.println("  ArrayList: " + String.format("%.2f", tempoAcessoArray / 1_000_000.0) + " ms");
        System.out.println("  LinkedList: " + String.format("%.2f", tempoAcessoLinked / 1_000_000.0) + " ms");
        System.out.println("  ArrayList é " + String.format("%.1fx", (double) tempoAcessoLinked / tempoAcessoArray) + " mais rápido");
        
        // Resumo das características
        System.out.println("\n📊 RESUMO DE CARACTERÍSTICAS:");
        System.out.println("  ArrayList:");
        System.out.println("    ✅ Acesso por índice: O(1)");
        System.out.println("    ❌ Inserção/remoção no início: O(n)");
        System.out.println("    ✅ Inserção/remoção no fim: O(1)*");
        System.out.println("    ✅ Menor uso de memória");
        System.out.println();
        System.out.println("  LinkedList:");
        System.out.println("    ❌ Acesso por índice: O(n)");
        System.out.println("    ✅ Inserção/remoção no início/fim: O(1)");
        System.out.println("    ✅ Inserção/remoção no meio: O(1) se tiver referência");
        System.out.println("    ❌ Maior uso de memória (overhead dos nós)");
    }
    
    /**
     * Demonstra polimorfismo com LinkedList
     */
    private static void exemploPolimorfismo() {
        System.out.println("--- POLIMORFISMO COM LINKEDLIST ---");
        
        // Uma mesma LinkedList pode ser vista de diferentes formas
        LinkedList<String> dados = new LinkedList<>();
        dados.add("A");
        dados.add("B");
        dados.add("C");
        
        System.out.println("LinkedList original: " + dados);
        
        // Como List - acesso sequencial
        List<String> comoList = dados;
        System.out.println("Como List - tamanho: " + comoList.size());
        System.out.println("Como List - elemento 1: " + comoList.get(1));
        
        // Como Queue - processamento FIFO
        Queue<String> comoQueue = dados;
        System.out.println("Como Queue - próximo: " + comoQueue.peek());
        
        // Como Deque - acesso em ambas as pontas
        Deque<String> comoDeque = dados;
        System.out.println("Como Deque - primeiro: " + comoDeque.getFirst());
        System.out.println("Como Deque - último: " + comoDeque.getLast());
        
        // Demonstrando flexibilidade
        System.out.println("\n🎭 Flexibilidade do polimorfismo:");
        processarComoLista(dados);
        processarComoFila(dados);
        processarComoPilha(dados);
    }
    
    /**
     * Processa dados como lista
     */
    private static void processarComoLista(List<String> lista) {
        System.out.println("  📋 Processando como Lista:");
        for (int i = 0; i < lista.size(); i++) {
            System.out.println("    Índice " + i + ": " + lista.get(i));
        }
    }
    
    /**
     * Processa dados como fila
     */
    private static void processarComoFila(Queue<String> fila) {
        System.out.println("  🔄 Processando como Fila (não destrutivo):");
        Queue<String> copia = new LinkedList<>(fila);
        while (!copia.isEmpty()) {
            System.out.println("    Processou: " + copia.poll());
        }
    }
    
    /**
     * Processa dados como pilha
     */
    private static void processarComoPilha(Deque<String> pilha) {
        System.out.println("  📚 Processando como Pilha (não destrutivo):");
        Deque<String> copia = new LinkedList<>(pilha);
        while (!copia.isEmpty()) {
            System.out.println("    Pop: " + copia.pop());
        }
    }
}