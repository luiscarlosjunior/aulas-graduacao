/**
 * Demonstração do Princípio da Inversão de Dependência (DIP)
 * Dependency Inversion Principle
 * 
 * Dependa de abstrações, não de implementações concretas
 * 
 * @author Sistema de Ensino - Princípios SOLID
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// ==========================================
// EXEMPLO RUIM - Violando DIP
// ==========================================

// Implementação concreta de baixo nível
class EmailServiceRuim {
    public void enviarEmail(String destinatario, String mensagem) {
        System.out.println("📧 Email para " + destinatario + ": " + mensagem);
        // Código real de envio de email
    }
}

// Módulo de alto nível DEPENDE DIRETAMENTE da implementação concreta
class NotificadorUsuarioRuim {
    private EmailServiceRuim emailService; // ACOPLAMENTO DIRETO!
    
    public NotificadorUsuarioRuim() {
        this.emailService = new EmailServiceRuim(); // INSTANCIAÇÃO DIRETA!
    }
    
    public void notificar(String usuario, String mensagem) {
        emailService.enviarEmail(usuario, mensagem);
        // E se quisermos usar SMS? Precisamos MODIFICAR esta classe!
    }
}

// ==========================================
// EXEMPLO BOM - Seguindo DIP
// ==========================================

// ABSTRAÇÃO (Interface de alto nível)
interface ServicoNotificacaoDIP {
    void enviar(String destinatario, String mensagem);
    String getTipo();
}

// Implementações concretas DEPENDEM da abstração
class EmailService implements ServicoNotificacaoDIP {
    @Override
    public void enviar(String destinatario, String mensagem) {
        System.out.println("📧 Email para " + destinatario + ": " + mensagem);
        // Lógica real de envio de email
    }
    
    @Override
    public String getTipo() {
        return "Email";
    }
}

class SMSService implements ServicoNotificacaoDIP {
    @Override
    public void enviar(String destinatario, String mensagem) {
        System.out.println("📱 SMS para " + destinatario + ": " + mensagem);
        // Lógica real de envio de SMS
    }
    
    @Override
    public String getTipo() {
        return "SMS";
    }
}

class PushNotificationService implements ServicoNotificacaoDIP {
    @Override
    public void enviar(String destinatario, String mensagem) {
        System.out.println("🔔 Push para " + destinatario + ": " + mensagem);
        // Lógica real de push notification
    }
    
    @Override
    public String getTipo() {
        return "Push Notification";
    }
}

class WhatsAppService implements ServicoNotificacaoDIP {
    @Override
    public void enviar(String destinatario, String mensagem) {
        System.out.println("💬 WhatsApp para " + destinatario + ": " + mensagem);
        // Lógica real de envio pelo WhatsApp
    }
    
    @Override
    public String getTipo() {
        return "WhatsApp";
    }
}

class TelegramService implements ServicoNotificacaoDIP {
    @Override
    public void enviar(String destinatario, String mensagem) {
        System.out.println("✈️  Telegram para " + destinatario + ": " + mensagem);
        // Lógica real de envio pelo Telegram
    }
    
    @Override
    public String getTipo() {
        return "Telegram";
    }
}

// Módulo de alto nível DEPENDE da ABSTRAÇÃO
class NotificadorUsuario {
    private ServicoNotificacaoDIP servicoNotificacao; // ABSTRAÇÃO!
    
    // Injeção de dependência via construtor
    public NotificadorUsuario(ServicoNotificacaoDIP servicoNotificacao) {
        this.servicoNotificacao = servicoNotificacao;
    }
    
    public void notificar(String usuario, String mensagem) {
        System.out.println("Enviando via " + servicoNotificacao.getTipo() + ":");
        servicoNotificacao.enviar(usuario, mensagem);
    }
    
    // Permite trocar serviço em runtime
    public void setServicoNotificacao(ServicoNotificacaoDIP servicoNotificacao) {
        this.servicoNotificacao = servicoNotificacao;
    }
}

// Notificador que usa múltiplos canais
class NotificadorMulticanal {
    private List<ServicoNotificacaoDIP> servicos;
    
    public NotificadorMulticanal(List<ServicoNotificacaoDIP> servicos) {
        this.servicos = servicos;
    }
    
    public void notificarTodos(String usuario, String mensagem) {
        System.out.println("Notificando em todos os canais:");
        for (ServicoNotificacaoDIP servico : servicos) {
            System.out.print("  ");
            servico.enviar(usuario, mensagem);
        }
    }
    
    public void adicionarServico(ServicoNotificacaoDIP servico) {
        servicos.add(servico);
    }
}

// ==========================================
// OUTRO EXEMPLO: Sistema de Pagamento
// ==========================================

// Abstração para processamento de pagamento
interface ProcessadorPagamento {
    boolean processar(double valor, String conta);
    String getNome();
}

// Implementações concretas
class PagamentoCartaoCredito implements ProcessadorPagamento {
    @Override
    public boolean processar(double valor, String conta) {
        System.out.println("💳 Processando cartão de crédito: R$ " + 
                         String.format("%.2f", valor));
        System.out.println("   Conta: " + conta);
        return true;
    }
    
    @Override
    public String getNome() {
        return "Cartão de Crédito";
    }
}

class PagamentoPix implements ProcessadorPagamento {
    @Override
    public boolean processar(double valor, String conta) {
        System.out.println("🔷 Processando PIX: R$ " + 
                         String.format("%.2f", valor));
        System.out.println("   Chave: " + conta);
        return true;
    }
    
    @Override
    public String getNome() {
        return "PIX";
    }
}

class PagamentoBoleto implements ProcessadorPagamento {
    @Override
    public boolean processar(double valor, String conta) {
        System.out.println("📄 Gerando boleto: R$ " + 
                         String.format("%.2f", valor));
        System.out.println("   Código de barras gerado");
        return true;
    }
    
    @Override
    public String getNome() {
        return "Boleto";
    }
}

class PagamentoPayPal implements ProcessadorPagamento {
    @Override
    public boolean processar(double valor, String conta) {
        System.out.println("🅿️  Processando PayPal: R$ " + 
                         String.format("%.2f", valor));
        System.out.println("   Conta: " + conta);
        return true;
    }
    
    @Override
    public String getNome() {
        return "PayPal";
    }
}

// Gerenciador de pagamentos que depende da abstração
class GerenciadorPagamentos {
    private ProcessadorPagamento processador;
    
    public GerenciadorPagamentos(ProcessadorPagamento processador) {
        this.processador = processador;
    }
    
    public void executarPagamento(double valor, String conta) {
        System.out.println("\n💰 Executando pagamento:");
        System.out.println("   Método: " + processador.getNome());
        boolean sucesso = processador.processar(valor, conta);
        if (sucesso) {
            System.out.println("   ✓ Pagamento realizado com sucesso!");
        } else {
            System.out.println("   ✗ Falha no pagamento");
        }
    }
    
    public void trocarProcessador(ProcessadorPagamento novoProcessador) {
        this.processador = novoProcessador;
        System.out.println("\n🔄 Processador alterado para: " + 
                         novoProcessador.getNome());
    }
}

// ==========================================
// OUTRO EXEMPLO: Logger
// ==========================================

interface Logger {
    void log(String mensagem);
    String getTipo();
}

class ConsoleLogger implements Logger {
    @Override
    public void log(String mensagem) {
        System.out.println("[CONSOLE] " + mensagem);
    }
    
    @Override
    public String getTipo() {
        return "Console";
    }
}

class FileLogger implements Logger {
    @Override
    public void log(String mensagem) {
        System.out.println("[FILE] Gravando no arquivo: " + mensagem);
    }
    
    @Override
    public String getTipo() {
        return "File";
    }
}

class DatabaseLogger implements Logger {
    @Override
    public void log(String mensagem) {
        System.out.println("[DATABASE] Inserindo no banco: " + mensagem);
    }
    
    @Override
    public String getTipo() {
        return "Database";
    }
}

// Aplicação que depende da abstração Logger
class AplicacaoComLog {
    private Logger logger;
    
    public AplicacaoComLog(Logger logger) {
        this.logger = logger;
    }
    
    public void executar() {
        logger.log("Aplicação iniciada");
        logger.log("Processando dados...");
        logger.log("Aplicação finalizada");
    }
}

// ==========================================
// DEMONSTRAÇÃO E TESTES
// ==========================================

public class ExemploDIP {
    
    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════════════════════════╗");
        System.out.println("║  PRINCÍPIO DA INVERSÃO DE DEPENDÊNCIA (DIP)             ║");
        System.out.println("║  Dependency Inversion Principle                         ║");
        System.out.println("╚══════════════════════════════════════════════════════════╝\n");
        
        // Exemplo RUIM
        System.out.println("❌ EXEMPLO RUIM - Acoplamento direto:");
        System.out.println("─────────────────────────────────────────────────────────\n");
        demonstrarExemploRuim();
        
        System.out.println("\n" + "=".repeat(60) + "\n");
        
        // Exemplo BOM - Notificações
        System.out.println("✅ EXEMPLO BOM - Inversão de dependência:");
        System.out.println("─────────────────────────────────────────────────────────\n");
        demonstrarExemploBom();
        
        System.out.println("\n" + "=".repeat(60) + "\n");
        
        // Exemplo BOM - Pagamentos
        System.out.println("✅ EXEMPLO BOM - Sistema de Pagamento:");
        System.out.println("─────────────────────────────────────────────────────────\n");
        demonstrarExemploPagamentos();
        
        System.out.println("\n" + "=".repeat(60) + "\n");
        
        // Exemplo BOM - Logger
        System.out.println("✅ EXEMPLO BOM - Sistema de Log:");
        System.out.println("─────────────────────────────────────────────────────────\n");
        demonstrarExemploLogger();
        
        System.out.println("\n" + "=".repeat(60) + "\n");
        
        // Análise
        imprimirAnalise();
    }
    
    private static void demonstrarExemploRuim() {
        NotificadorUsuarioRuim notificador = new NotificadorUsuarioRuim();
        notificador.notificar("usuario@email.com", "Bem-vindo!");
        
        System.out.println("\n⚠️  PROBLEMAS:");
        System.out.println("   • NotificadorUsuario está ACOPLADO a EmailService");
        System.out.println("   • Para usar SMS, precisa MODIFICAR NotificadorUsuario");
        System.out.println("   • Impossível testar sem enviar emails reais");
        System.out.println("   • Rígido: trocar implementação requer reescrever código");
        System.out.println("   • Viola também OCP (não está fechado para modificação)");
    }
    
    private static void demonstrarExemploBom() {
        String usuario = "usuario@email.com";
        String mensagem = "Seu pedido foi confirmado!";
        
        // Criar diferentes serviços
        ServicoNotificacaoDIP email = new EmailService();
        ServicoNotificacaoDIP sms = new SMSService();
        ServicoNotificacaoDIP push = new PushNotificationService();
        ServicoNotificacaoDIP whatsapp = new WhatsAppService();
        ServicoNotificacaoDIP telegram = new TelegramService();
        
        // Notificador pode usar QUALQUER serviço
        NotificadorUsuario notificador = new NotificadorUsuario(email);
        
        System.out.println("1. Notificando via Email:");
        notificador.notificar(usuario, mensagem);
        
        System.out.println("\n2. Trocando para SMS:");
        notificador.setServicoNotificacao(sms);
        notificador.notificar("+5511999999999", mensagem);
        
        System.out.println("\n3. Trocando para Push:");
        notificador.setServicoNotificacao(push);
        notificador.notificar("device_token_123", mensagem);
        
        // Notificador multicanal
        System.out.println("\n4. Notificação em múltiplos canais:");
        List<ServicoNotificacaoDIP> todosServicos = Arrays.asList(
            email, sms, whatsapp, telegram, push
        );
        NotificadorMulticanal multicanal = new NotificadorMulticanal(todosServicos);
        multicanal.notificarTodos(usuario, "Alerta importante!");
        
        System.out.println("\n✅ VANTAGENS:");
        System.out.println("   ✓ NotificadorUsuario NÃO conhece implementações específicas");
        System.out.println("   ✓ Fácil trocar de Email para SMS sem modificar código");
        System.out.println("   ✓ Fácil testar com mock objects");
        System.out.println("   ✓ Flexível: adicionar novos canais sem modificar existentes");
        System.out.println("   ✓ Baixo acoplamento e alta coesão");
    }
    
    private static void demonstrarExemploPagamentos() {
        double valor = 150.50;
        String conta = "usuario@email.com";
        
        // Criar processadores
        ProcessadorPagamento cartao = new PagamentoCartaoCredito();
        ProcessadorPagamento pix = new PagamentoPix();
        ProcessadorPagamento boleto = new PagamentoBoleto();
        ProcessadorPagamento paypal = new PagamentoPayPal();
        
        // Gerenciador que depende da abstração
        GerenciadorPagamentos gerenciador = new GerenciadorPagamentos(cartao);
        
        // Executar com cartão
        gerenciador.executarPagamento(valor, "4111-1111-1111-1111");
        
        // Trocar para PIX
        gerenciador.trocarProcessador(pix);
        gerenciador.executarPagamento(valor, conta);
        
        // Trocar para Boleto
        gerenciador.trocarProcessador(boleto);
        gerenciador.executarPagamento(valor, conta);
        
        // Trocar para PayPal
        gerenciador.trocarProcessador(paypal);
        gerenciador.executarPagamento(valor, conta);
        
        System.out.println("\n✅ VANTAGENS:");
        System.out.println("   ✓ Fácil adicionar novos métodos de pagamento");
        System.out.println("   ✓ Gerenciador não conhece detalhes de implementação");
        System.out.println("   ✓ Testável com mocks");
        System.out.println("   ✓ Flexível e extensível");
    }
    
    private static void demonstrarExemploLogger() {
        // Criar diferentes loggers
        Logger console = new ConsoleLogger();
        Logger file = new FileLogger();
        Logger database = new DatabaseLogger();
        
        System.out.println("1. Aplicação com Console Logger:");
        AplicacaoComLog app1 = new AplicacaoComLog(console);
        app1.executar();
        
        System.out.println("\n2. Aplicação com File Logger:");
        AplicacaoComLog app2 = new AplicacaoComLog(file);
        app2.executar();
        
        System.out.println("\n3. Aplicação com Database Logger:");
        AplicacaoComLog app3 = new AplicacaoComLog(database);
        app3.executar();
        
        System.out.println("\n✅ VANTAGENS:");
        System.out.println("   ✓ Aplicação não conhece implementação específica de log");
        System.out.println("   ✓ Fácil trocar destino do log sem modificar aplicação");
        System.out.println("   ✓ Testável com logger mock que não grava nada");
    }
    
    private static void imprimirAnalise() {
        System.out.println("📊 ANÁLISE COMPARATIVA\n");
        
        System.out.println("┌─────────────────────────┬──────────────┬──────────────┐");
        System.out.println("│ Métrica                 │ Sem DIP      │ Com DIP      │");
        System.out.println("├─────────────────────────┼──────────────┼──────────────┤");
        System.out.println("│ Acoplamento             │ Alto         │ Baixo        │");
        System.out.println("│ Testabilidade           │ Difícil      │ Fácil        │");
        System.out.println("│ Flexibilidade           │ Rígido       │ Flexível     │");
        System.out.println("│ Trocar implementação    │ Reescrever   │ Plug-and-play│");
        System.out.println("│ Dependências            │ Concretas    │ Abstratas    │");
        System.out.println("│ Manutenibilidade        │ Difícil      │ Fácil        │");
        System.out.println("└─────────────────────────┴──────────────┴──────────────┘");
        
        System.out.println("\n💡 PRINCÍPIOS CHAVE:");
        System.out.println("   1. Módulos de alto nível não devem depender de baixo nível");
        System.out.println("   2. Ambos devem depender de abstrações");
        System.out.println("   3. Abstrações não devem depender de detalhes");
        System.out.println("   4. Detalhes devem depender de abstrações");
        System.out.println("\n🎯 SINAIS DE VIOLAÇÃO:");
        System.out.println("   • new de classes concretas em construtores");
        System.out.println("   • Dependências diretas de frameworks externos");
        System.out.println("   • Impossível testar sem dependências reais");
        System.out.println("   • Código acoplado a implementações específicas");
        System.out.println("\n✅ SOLUÇÃO:");
        System.out.println("   • Use interfaces ou classes abstratas");
        System.out.println("   • Injete dependências via construtor ou setter");
        System.out.println("   • Programe para interfaces, não implementações");
        System.out.println("   • Use Dependency Injection frameworks quando apropriado");
        System.out.println("\n✅ BENEFÍCIO PRINCIPAL:");
        System.out.println("   Código desacoplado, testável e fácil de manter/estender");
    }
}
