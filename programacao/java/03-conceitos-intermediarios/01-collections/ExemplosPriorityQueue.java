import java.util.*;

/**
 * Exemplos completos de PriorityQueue - Fila com prioridade
 * 
 * PriorityQueue é uma implementação de Queue baseada em heap binário.
 * Elementos são automaticamente ordenados por prioridade, permitindo
 * acesso eficiente ao elemento de maior prioridade em O(1) e
 * inserção/remoção em O(log n).
 * 
 * Conceitos POO demonstrados:
 * - Encapsulamento: Estrutura de heap oculta do usuário
 * - Polimorfismo: Implementa Queue interface
 * - Herança: Especialização da interface Collection
 * - Abstração: Operações de prioridade transparentes
 * 
 * @author Aulas Graduação
 */
public class ExemplosPriorityQueue {
    
    public static void main(String[] args) {
        System.out.println("=== PRIORITY QUEUE - EXEMPLOS COMPLETOS ===\n");
        
        exemploBasico();
        System.out.println();
        
        exemploComparador();
        System.out.println();
        
        exemploTarefas();
        System.out.println();
        
        exemploHospital();
        System.out.println();
        
        exemploAlgoritmos();
    }
    
    /**
     * Exemplo básico de PriorityQueue com números
     */
    private static void exemploBasico() {
        System.out.println("--- PRIORITY QUEUE BÁSICO ---");
        
        // PriorityQueue mantém o menor elemento no topo (min-heap por padrão)
        PriorityQueue<Integer> numeros = new PriorityQueue<>();
        
        // Adicionando números em ordem aleatória
        numeros.offer(30);
        numeros.offer(10);
        numeros.offer(50);
        numeros.offer(20);
        numeros.offer(40);
        
        System.out.println("Números adicionados: 30, 10, 50, 20, 40");
        System.out.println("PriorityQueue: " + numeros);
        System.out.println("Menor elemento (peek): " + numeros.peek());
        
        // Removendo elementos (sempre o de menor valor)
        System.out.println("\n🔄 Removendo elementos por prioridade:");
        while (!numeros.isEmpty()) {
            int removido = numeros.poll();
            System.out.println("  Removido: " + removido + " | Restante: " + numeros);
        }
        
        // Demonstrando que a ordem interna pode parecer confusa
        PriorityQueue<String> frutas = new PriorityQueue<>();
        frutas.offer("Banana");
        frutas.offer("Maçã");
        frutas.offer("Uva");
        frutas.offer("Abacaxi");
        
        System.out.println("\n🍎 Frutas na PriorityQueue: " + frutas);
        System.out.println("Primeira alfabeticamente: " + frutas.peek());
        System.out.println("⚠️ Nota: A ordem interna não é totalmente ordenada!");
        System.out.println("    Apenas garante que o topo tem a maior prioridade");
    }
    
    /**
     * Exemplo usando Comparator personalizado
     */
    private static void exemploComparador() {
        System.out.println("--- COMPARADORES PERSONALIZADOS ---");
        
        // Max-heap (maior elemento primeiro)
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        maxHeap.addAll(Arrays.asList(10, 30, 20, 50, 40));
        
        System.out.println("Max-heap: " + maxHeap);
        System.out.println("Maior elemento: " + maxHeap.peek());
        
        // Ordenação por tamanho de string (menor primeiro)
        PriorityQueue<String> porTamanho = new PriorityQueue<>(
            Comparator.comparing(String::length)
                     .thenComparing(String::compareTo) // Desempate alfabético
        );
        
        porTamanho.offer("programação");
        porTamanho.offer("Java");
        porTamanho.offer("a");
        porTamanho.offer("casa");
        porTamanho.offer("código");
        
        System.out.println("\n📏 Palavras por tamanho (menor primeiro):");
        while (!porTamanho.isEmpty()) {
            String palavra = porTamanho.poll();
            System.out.println("  '" + palavra + "' (tamanho: " + palavra.length() + ")");
        }
        
        // Comparador mais complexo: prioridade por urgência e data
        PriorityQueue<Evento> eventos = new PriorityQueue<>((e1, e2) -> {
            // Primeiro por urgência (alta = 1, baixa = 3)
            int urgencia = Integer.compare(e1.urgencia, e2.urgencia);
            if (urgencia != 0) return urgencia;
            
            // Depois por data (mais cedo primeiro)
            return e1.data.compareTo(e2.data);
        });
        
        eventos.offer(new Evento("Reunião cliente", 2, "2024-01-15"));
        eventos.offer(new Evento("Bug crítico", 1, "2024-01-14"));
        eventos.offer(new Evento("Documentação", 3, "2024-01-13"));
        eventos.offer(new Evento("Deploy urgente", 1, "2024-01-16"));
        
        System.out.println("\n📅 Eventos por prioridade:");
        while (!eventos.isEmpty()) {
            Evento evento = eventos.poll();
            System.out.println("  " + evento);
        }
    }
    
    /**
     * Exemplo prático: sistema de tarefas com prioridade
     */
    private static void exemploTarefas() {
        System.out.println("--- SISTEMA DE TAREFAS COM PRIORIDADE ---");
        
        PriorityQueue<Tarefa> filaTrabalho = new PriorityQueue<>();
        
        // Adicionando tarefas com diferentes prioridades
        filaTrabalho.offer(new Tarefa("Revisar código", Prioridade.MEDIA, 60));
        filaTrabalho.offer(new Tarefa("Corrigir bug crítico", Prioridade.ALTA, 30));
        filaTrabalho.offer(new Tarefa("Atualizar documentação", Prioridade.BAIXA, 90));
        filaTrabalho.offer(new Tarefa("Implementar feature", Prioridade.MEDIA, 120));
        filaTrabalho.offer(new Tarefa("Hotfix produção", Prioridade.CRITICA, 15));
        filaTrabalho.offer(new Tarefa("Reunião equipe", Prioridade.BAIXA, 45));
        
        System.out.println("📋 Fila de tarefas (6 tarefas adicionadas):");
        System.out.println("Próxima tarefa: " + filaTrabalho.peek());
        
        System.out.println("\n⚡ Processando tarefas por prioridade:");
        int tempoTotal = 0;
        while (!filaTrabalho.isEmpty()) {
            Tarefa tarefa = filaTrabalho.poll();
            tempoTotal += tarefa.duracaoMinutos;
            System.out.println("  🔧 " + tarefa.nome + 
                             " (" + tarefa.prioridade + ", " + tarefa.duracaoMinutos + " min)");
        }
        
        System.out.println("\n⏱️ Tempo total estimado: " + tempoTotal + " minutos");
        System.out.println("🎯 Tarefas críticas foram processadas primeiro!");
    }
    
    /**
     * Exemplo prático: sistema de triagem hospitalar
     */
    private static void exemploHospital() {
        System.out.println("--- SISTEMA DE TRIAGEM HOSPITALAR ---");
        
        PriorityQueue<Paciente> filaAtendimento = new PriorityQueue<>();
        
        // Pacientes chegando ao hospital
        filaAtendimento.offer(new Paciente("João Silva", TriagemCor.VERDE, "09:00"));
        filaAtendimento.offer(new Paciente("Maria Santos", TriagemCor.AMARELO, "09:05"));
        filaAtendimento.offer(new Paciente("Carlos Lima", TriagemCor.VERMELHO, "09:10"));
        filaAtendimento.offer(new Paciente("Ana Costa", TriagemCor.VERDE, "09:15"));
        filaAtendimento.offer(new Paciente("Bruno Ferreira", TriagemCor.VERMELHO, "09:20"));
        filaAtendimento.offer(new Paciente("Diana Oliveira", TriagemCor.AMARELO, "09:25"));
        
        System.out.println("🏥 Pacientes na fila de triagem:");
        System.out.println("Total de pacientes: " + filaAtendimento.size());
        System.out.println("Próximo atendimento: " + filaAtendimento.peek());
        
        System.out.println("\n🩺 Ordem de atendimento por prioridade:");
        int contador = 1;
        while (!filaAtendimento.isEmpty()) {
            Paciente paciente = filaAtendimento.poll();
            System.out.println("  " + contador + "º " + paciente);
            contador++;
        }
        
        System.out.println("\n💡 Sistema de triagem:");
        System.out.println("  🔴 VERMELHO: Emergência (prioridade 1)");
        System.out.println("  🟡 AMARELO: Urgência (prioridade 2)");
        System.out.println("  🟢 VERDE: Pouco urgente (prioridade 3)");
        System.out.println("  ⏰ Dentro da mesma cor: ordem de chegada");
    }
    
    /**
     * Exemplo demonstrando uso em algoritmos
     */
    private static void exemploAlgoritmos() {
        System.out.println("--- USO EM ALGORITMOS ---");
        
        // Algoritmo de Dijkstra simplificado
        System.out.println("🗺️ Simulação do algoritmo de Dijkstra:");
        
        PriorityQueue<NoGrafo> fronteira = new PriorityQueue<>();
        
        // Adicionando nós com suas distâncias
        fronteira.offer(new NoGrafo("A", 0));    // Origem
        fronteira.offer(new NoGrafo("B", 5));
        fronteira.offer(new NoGrafo("C", 3));
        fronteira.offer(new NoGrafo("D", 8));
        fronteira.offer(new NoGrafo("E", 1));
        
        System.out.println("Processando nós por menor distância:");
        while (!fronteira.isEmpty()) {
            NoGrafo no = fronteira.poll();
            System.out.println("  Visitando nó " + no.nome + 
                             " (distância: " + no.distancia + ")");
        }
        
        // Encontrando os K maiores elementos
        System.out.println("\n🏆 Encontrando os 3 maiores números:");
        
        int[] numeros = {64, 34, 25, 12, 22, 11, 90, 5, 77, 30};
        PriorityQueue<Integer> topK = new PriorityQueue<>(3); // Min-heap de tamanho 3
        
        for (int num : numeros) {
            if (topK.size() < 3) {
                topK.offer(num);
            } else if (num > topK.peek()) {
                topK.poll(); // Remove o menor
                topK.offer(num); // Adiciona o novo
            }
        }
        
        System.out.println("Array original: " + Arrays.toString(numeros));
        List<Integer> resultado = new ArrayList<>(topK);
        Collections.sort(resultado, Collections.reverseOrder());
        System.out.println("Os 3 maiores: " + resultado);
        
        // Merge K sorted arrays (conceito)
        System.out.println("\n🔀 Conceito: Merge de arrays ordenados:");
        System.out.println("PriorityQueue é fundamental para:");
        System.out.println("  • Algoritmo de Dijkstra (menor caminho)");
        System.out.println("  • Algoritmo de Prim (árvore geradora mínima)");
        System.out.println("  • Top-K elementos");
        System.out.println("  • Merge de múltiplos arrays ordenados");
        System.out.println("  • Agendamento de tarefas por prioridade");
        System.out.println("  • Simulações de eventos discretos");
    }
    
    // Classes auxiliares
    
    enum Prioridade {
        CRITICA(1), ALTA(2), MEDIA(3), BAIXA(4);
        
        private final int valor;
        
        Prioridade(int valor) {
            this.valor = valor;
        }
        
        public int getValor() {
            return valor;
        }
    }
    
    static class Tarefa implements Comparable<Tarefa> {
        String nome;
        Prioridade prioridade;
        int duracaoMinutos;
        
        Tarefa(String nome, Prioridade prioridade, int duracaoMinutos) {
            this.nome = nome;
            this.prioridade = prioridade;
            this.duracaoMinutos = duracaoMinutos;
        }
        
        @Override
        public int compareTo(Tarefa outra) {
            // Primeira prioridade por urgência (menor valor = maior prioridade)
            int resultado = Integer.compare(this.prioridade.getValor(), outra.prioridade.getValor());
            if (resultado != 0) return resultado;
            
            // Desempate por duração (menor duração primeiro)
            return Integer.compare(this.duracaoMinutos, outra.duracaoMinutos);
        }
        
        @Override
        public String toString() {
            return nome + " (" + prioridade + ", " + duracaoMinutos + " min)";
        }
    }
    
    enum TriagemCor {
        VERMELHO(1), AMARELO(2), VERDE(3);
        
        private final int prioridade;
        
        TriagemCor(int prioridade) {
            this.prioridade = prioridade;
        }
        
        public int getPrioridade() {
            return prioridade;
        }
    }
    
    static class Paciente implements Comparable<Paciente> {
        String nome;
        TriagemCor cor;
        String horarioChegada;
        
        Paciente(String nome, TriagemCor cor, String horarioChegada) {
            this.nome = nome;
            this.cor = cor;
            this.horarioChegada = horarioChegada;
        }
        
        @Override
        public int compareTo(Paciente outro) {
            // Primeira prioridade por cor da triagem
            int resultado = Integer.compare(this.cor.getPrioridade(), outro.cor.getPrioridade());
            if (resultado != 0) return resultado;
            
            // Desempate por horário de chegada
            return this.horarioChegada.compareTo(outro.horarioChegada);
        }
        
        @Override
        public String toString() {
            String emoji = cor == TriagemCor.VERMELHO ? "🔴" : 
                          cor == TriagemCor.AMARELO ? "🟡" : "🟢";
            return emoji + " " + nome + " (" + horarioChegada + ")";
        }
    }
    
    static class Evento {
        String nome;
        int urgencia; // 1 = alta, 2 = média, 3 = baixa
        String data;
        
        Evento(String nome, int urgencia, String data) {
            this.nome = nome;
            this.urgencia = urgencia;
            this.data = data;
        }
        
        @Override
        public String toString() {
            String prioridade = urgencia == 1 ? "ALTA" : 
                              urgencia == 2 ? "MÉDIA" : "BAIXA";
            return nome + " (" + prioridade + ", " + data + ")";
        }
    }
    
    static class NoGrafo implements Comparable<NoGrafo> {
        String nome;
        int distancia;
        
        NoGrafo(String nome, int distancia) {
            this.nome = nome;
            this.distancia = distancia;
        }
        
        @Override
        public int compareTo(NoGrafo outro) {
            return Integer.compare(this.distancia, outro.distancia);
        }
    }
}