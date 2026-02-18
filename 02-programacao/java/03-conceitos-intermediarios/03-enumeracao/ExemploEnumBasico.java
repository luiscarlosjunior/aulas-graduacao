import java.util.*;

/**
 * Exemplo Básico de Enumerações (Enum)
 * 
 * Enumerações são tipos especiais de classe que representam
 * um grupo fixo de constantes (valores imutáveis).
 * 
 * @author Aulas Graduação
 */
public class ExemploEnumBasico {
    
    /**
     * Enum básico representando dias da semana
     */
    enum DiaSemana {
        SEGUNDA, TERCA, QUARTA, QUINTA, SEXTA, SABADO, DOMINGO
    }
    
    /**
     * Enum representando status de um pedido
     */
    enum StatusPedido {
        PENDENTE, PROCESSANDO, ENVIADO, ENTREGUE, CANCELADO
    }
    
    /**
     * Enum representando prioridades
     */
    enum Prioridade {
        BAIXA, MEDIA, ALTA, CRITICA
    }
    
    public static void main(String[] args) {
        System.out.println("=== ENUMERAÇÕES BÁSICAS ===\n");
        
        exemploBasico();
        System.out.println();
        
        exemploMetodosEstaticos();
        System.out.println();
        
        exemploComSwitch();
        System.out.println();
        
        exemploComparacao();
        System.out.println();
        
        exemploIteracao();
    }
    
    /**
     * Demonstra uso básico de enum
     */
    private static void exemploBasico() {
        System.out.println("--- EXEMPLO BÁSICO ---");
        
        // Declarando variáveis enum
        DiaSemana hoje = DiaSemana.QUARTA;
        StatusPedido status = StatusPedido.PROCESSANDO;
        
        System.out.println("Hoje é: " + hoje);
        System.out.println("Status do pedido: " + status);
        
        // Type safety - não é possível atribuir valores inválidos
        // hoje = "SEGUNDA"; // ERRO DE COMPILAÇÃO
        // status = 5; // ERRO DE COMPILAÇÃO
    }
    
    /**
     * Demonstra métodos estáticos de enum
     */
    private static void exemploMetodosEstaticos() {
        System.out.println("--- MÉTODOS ESTÁTICOS ---");
        
        // values() - retorna array com todos os valores
        DiaSemana[] dias = DiaSemana.values();
        System.out.println("Total de dias: " + dias.length);
        
        // valueOf() - converte String para enum
        DiaSemana dia = DiaSemana.valueOf("SEGUNDA");
        System.out.println("Dia convertido: " + dia);
        
        // ordinal() - retorna posição do enum (começando em 0)
        System.out.println("Posição de QUARTA: " + DiaSemana.QUARTA.ordinal());
        
        // name() - retorna nome do enum como String
        System.out.println("Nome: " + DiaSemana.SEXTA.name());
        
        // Tratamento de erro ao converter String inválida
        try {
            DiaSemana invalido = DiaSemana.valueOf("INEXISTENTE");
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: Valor enum inválido");
        }
    }
    
    /**
     * Demonstra uso de enum com switch
     */
    private static void exemploComSwitch() {
        System.out.println("--- ENUM COM SWITCH ---");
        
        DiaSemana dia = DiaSemana.SABADO;
        
        switch (dia) {
            case SEGUNDA:
            case TERCA:
            case QUARTA:
            case QUINTA:
            case SEXTA:
                System.out.println("É dia de semana - hora de trabalhar!");
                break;
            case SABADO:
            case DOMINGO:
                System.out.println("É fim de semana - hora de descansar!");
                break;
        }
        
        // Switch com status
        StatusPedido status = StatusPedido.ENTREGUE;
        String mensagem = switch (status) {
            case PENDENTE -> "Aguardando processamento";
            case PROCESSANDO -> "Pedido em processamento";
            case ENVIADO -> "Pedido enviado para entrega";
            case ENTREGUE -> "Pedido entregue com sucesso";
            case CANCELADO -> "Pedido foi cancelado";
        };
        
        System.out.println("Mensagem: " + mensagem);
    }
    
    /**
     * Demonstra comparação de enums
     */
    private static void exemploComparacao() {
        System.out.println("--- COMPARAÇÃO DE ENUMS ---");
        
        Prioridade p1 = Prioridade.ALTA;
        Prioridade p2 = Prioridade.ALTA;
        Prioridade p3 = Prioridade.BAIXA;
        
        // Comparação por identidade (==)
        System.out.println("p1 == p2: " + (p1 == p2)); // true
        System.out.println("p1 == p3: " + (p1 == p3)); // false
        
        // Comparação com equals()
        System.out.println("p1.equals(p2): " + p1.equals(p2)); // true
        
        // compareTo() - compara pela ordem de declaração
        System.out.println("p1.compareTo(p3): " + p1.compareTo(p3)); // positivo
        System.out.println("p3.compareTo(p1): " + p3.compareTo(p1)); // negativo
        
        // Verificando ordem
        if (Prioridade.CRITICA.ordinal() > Prioridade.MEDIA.ordinal()) {
            System.out.println("CRITICA tem ordinal maior que MEDIA");
        }
    }
    
    /**
     * Demonstra iteração sobre valores de enum
     */
    private static void exemploIteracao() {
        System.out.println("--- ITERAÇÃO SOBRE ENUMS ---");
        
        System.out.println("Todos os dias da semana:");
        for (DiaSemana dia : DiaSemana.values()) {
            System.out.println("  " + dia + " (ordinal: " + dia.ordinal() + ")");
        }
        
        System.out.println("\nTodos os status de pedido:");
        for (StatusPedido status : StatusPedido.values()) {
            System.out.println("  " + status);
        }
        
        // Usando stream com enum
        System.out.println("\nPrioridades em ordem reversa:");
        Arrays.stream(Prioridade.values())
              .sorted(Comparator.reverseOrder())
              .forEach(p -> System.out.println("  " + p));
    }
}
