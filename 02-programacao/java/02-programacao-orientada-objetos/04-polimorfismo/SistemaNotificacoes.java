/**
 * Polimorfismo em Java — Exemplo Industrial: Sistema de Notificações
 *
 * ============================================================
 * POR QUE POLIMORFISMO IMPORTA NA INDÚSTRIA?
 * ============================================================
 * Polimorfismo é o que torna sistemas EXTENSÍVEIS sem precisar
 * modificar código existente:
 *
 * Imagine um sistema que envia notificações. Hoje envia por email.
 * Amanhã precisam adicionar SMS. Depois, WhatsApp, Slack, Push...
 *
 * SEM polimorfismo: você modifica o código existente toda vez
 * → viola Open/Closed Principle (SOLID)
 * → risco de quebrar o que já funciona
 * → código cresce com if/else para cada tipo
 *
 * COM polimorfismo:
 * → você cria uma nova implementação
 * → o código existente não precisa mudar NADA
 * → é testado independentemente
 *
 * Exemplos reais de polimorfismo em frameworks:
 * - JpaRepository: findById(), save(), delete() — mesma interface,
 *   múltiplos bancos de dados (PostgreSQL, MySQL, MongoDB)
 * - Logger (SLF4J): log.info() — pode ser Log4j, Logback, JUL
 * - PaymentGateway: processPayment() — Stripe, PayPal, Cielo
 * ============================================================
 *
 * @author Aulas Graduação
 * @version 2.0
 */
import java.util.ArrayList;
import java.util.List;

/**
 * Interface: Define o CONTRATO de qualquer serviço de notificação.
 * Qualquer classe que implemente esta interface pode ser usada
 * pelo sistema sem que o código chamador precise saber qual é.
 */
interface ServicoNotificacao {
    boolean enviar(String destinatario, String assunto, String mensagem);
    String getTipo();  // Retorna o tipo: "EMAIL", "SMS", etc.
}

/**
 * Implementação 1: Notificação por Email
 */
class NotificacaoEmail implements ServicoNotificacao {

    private String servidorSMTP;
    private int porta;

    public NotificacaoEmail(String servidorSMTP, int porta) {
        this.servidorSMTP = servidorSMTP;
        this.porta = porta;
    }

    @Override
    public boolean enviar(String destinatario, String assunto, String mensagem) {
        // Em produção: conectaria ao SMTP e enviaria o email real
        System.out.printf("  📧 EMAIL enviado para %s%n", destinatario);
        System.out.printf("     Assunto: %s%n", assunto);
        System.out.printf("     Servidor: %s:%d%n", servidorSMTP, porta);
        return true;  // sucesso simulado
    }

    @Override
    public String getTipo() { return "EMAIL"; }
}

/**
 * Implementação 2: Notificação por SMS
 */
class NotificacaoSMS implements ServicoNotificacao {

    private String operadora;

    public NotificacaoSMS(String operadora) {
        this.operadora = operadora;
    }

    @Override
    public boolean enviar(String destinatario, String assunto, String mensagem) {
        // SMS tem limite de 160 caracteres
        String smsTexto = mensagem.length() > 160
            ? mensagem.substring(0, 157) + "..."
            : mensagem;
        System.out.printf("  📱 SMS enviado para %s via %s%n", destinatario, operadora);
        System.out.printf("     Texto: %s%n", smsTexto);
        return true;
    }

    @Override
    public String getTipo() { return "SMS"; }
}

/**
 * Implementação 3: Notificação por Push (App Mobile)
 */
class NotificacaoPush implements ServicoNotificacao {

    private String appId;

    public NotificacaoPush(String appId) {
        this.appId = appId;
    }

    @Override
    public boolean enviar(String destinatario, String assunto, String mensagem) {
        System.out.printf("  🔔 PUSH enviado para dispositivo de %s (App: %s)%n",
            destinatario, appId);
        System.out.printf("     Título: %s%n", assunto);
        // Push tem limite de caracteres menor ainda
        System.out.printf("     Corpo: %s%n",
            mensagem.length() > 50 ? mensagem.substring(0, 47) + "..." : mensagem);
        return true;
    }

    @Override
    public String getTipo() { return "PUSH"; }
}

/**
 * Implementação 4: Notificação por Slack (Webhook)
 * Adicionamos DEPOIS sem mudar NADA no código existente!
 */
class NotificacaoSlack implements ServicoNotificacao {

    private String canal;

    public NotificacaoSlack(String canal) {
        this.canal = canal;
    }

    @Override
    public boolean enviar(String destinatario, String assunto, String mensagem) {
        System.out.printf("  💬 SLACK postado no canal #%s%n", canal);
        System.out.printf("     *%s*: %s%n", assunto, mensagem);
        return true;
    }

    @Override
    public String getTipo() { return "SLACK"; }
}

/**
 * Serviço de Notificações — usa polimorfismo para enviar por múltiplos canais.
 * Este código NUNCA PRECISA MUDAR quando adicionamos novos tipos de notificação!
 */
class GerenciadorNotificacoes {

    // Lista de ServicoNotificacao — aceita QUALQUER implementação!
    private List<ServicoNotificacao> servicos = new ArrayList<>();

    public void adicionarServico(ServicoNotificacao servico) {
        servicos.add(servico);
        System.out.printf("  ➕ Canal '%s' adicionado ao gerenciador%n", servico.getTipo());
    }

    /**
     * Envia notificação por todos os canais configurados.
     * Polimorfismo: chama enviar() sem saber qual classe está por baixo.
     */
    public void notificarTodos(String destinatario, String assunto, String mensagem) {
        System.out.printf("%n  🚀 Enviando notificação para: %s%n", destinatario);
        System.out.println("  " + "─".repeat(50));

        int sucessos = 0;
        for (ServicoNotificacao servico : servicos) {
            // POLIMORFISMO: chama o mesmo método, comportamento diferente!
            boolean ok = servico.enviar(destinatario, assunto, mensagem);
            if (ok) sucessos++;
        }

        System.out.printf("  Resultado: %d/%d canais com sucesso%n",
            sucessos, servicos.size());
    }
}

/**
 * Classe principal — demonstra o poder do polimorfismo
 */
public class SistemaNotificacoes {

    public static void main(String[] args) {
        System.out.println("=== POLIMORFISMO: SISTEMA DE NOTIFICAÇÕES ===\n");

        // Configurando os canais de notificação
        System.out.println("--- Configurando Canais ---");
        GerenciadorNotificacoes gerenciador = new GerenciadorNotificacoes();

        // Adiciona canais — cada um é uma implementação diferente da interface
        gerenciador.adicionarServico(new NotificacaoEmail("smtp.empresa.com.br", 587));
        gerenciador.adicionarServico(new NotificacaoSMS("Twilio"));
        gerenciador.adicionarServico(new NotificacaoPush("com.empresa.app"));
        gerenciador.adicionarServico(new NotificacaoSlack("alertas-pagamentos"));

        // Envia notificação de pagamento confirmado
        gerenciador.notificarTodos(
            "joao.silva@cliente.com",
            "✅ Pagamento Confirmado - Pedido #10472",
            "Seu pagamento de R$299,90 foi aprovado! Seu pedido será enviado em até 2 dias úteis."
        );

        // Envia alerta de estoque baixo (para a equipe interna)
        gerenciador.notificarTodos(
            "time-operacoes@empresa.com",
            "⚠️ Estoque Crítico — Produto FONE-BT-001",
            "Apenas 3 unidades restantes. Reposição necessária."
        );

        System.out.println("\n--- Demonstração do Poder do Polimorfismo ---");
        System.out.println("  O GerenciadorNotificacoes usa ServicoNotificacao (interface).");
        System.out.println("  Ele NÃO sabe se está enviando email, SMS, push ou Slack.");
        System.out.println("  Apenas chama servico.enviar() e cada implementação faz o resto.");
        System.out.println();
        System.out.println("  Para adicionar WhatsApp amanhã:");
        System.out.println("    1. Crie class NotificacaoWhatsApp implements ServicoNotificacao");
        System.out.println("    2. Implemente o método enviar()");
        System.out.println("    3. gerenciador.adicionarServico(new NotificacaoWhatsApp(...))");
        System.out.println("    ✅ ZERO mudança no código existente!");
        System.out.println();
        System.out.println("  Isso é o princípio Open/Closed (SOLID):");
        System.out.println("  Aberto para extensão, fechado para modificação.");

        System.out.println("\n--- Sobrecarga (Overloading) — outro tipo de polimorfismo ---");
        demonstrarSobrecarga();
    }

    // Sobrecarga: mesmo nome de método, parâmetros diferentes
    static void demonstrarSobrecarga() {
        System.out.println("  Calculadora com sobrecarga de métodos:");
        System.out.println("  calcular(10, 5)        = " + calcular(10, 5));
        System.out.println("  calcular(10.5, 3.2)    = " + calcular(10.5, 3.2));
        System.out.println("  calcular(\"Olá\", 3)   = " + calcular("Olá", 3));
        System.out.println();
        System.out.println("  Java escolhe qual versão chamar baseado nos argumentos.");
        System.out.println("  Isso é polimorfismo em tempo de compilação (static dispatch).");
    }

    // Mesmos nomes — Java decide em compilação qual chamar
    static int calcular(int a, int b) {
        return a + b;
    }

    static double calcular(double a, double b) {
        return Math.round((a + b) * 100.0) / 100.0;
    }

    static String calcular(String texto, int vezes) {
        return texto.repeat(vezes);
    }
}
