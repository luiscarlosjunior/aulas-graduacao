/**
 * Teste do padrão Factory Method com notificações
 * 
 * Demonstra como usar diferentes factories para criar
 * diferentes tipos de notificação sem acoplamento.
 * 
 * @author Aulas Graduação
 */
public class TesteFactoryMethod {
    
    public static void main(String[] args) {
        System.out.println("=== TESTE FACTORY METHOD - NOTIFICAÇÕES ===\n");
        
        testarTiposNotificacao();
        System.out.println();
        
        testarValidacoes();
        System.out.println();
        
        testarFactoryMethod();
        System.out.println();
        
        testarCenarioReal();
    }
    
    /**
     * Testa cada tipo de notificação individualmente
     */
    private static void testarTiposNotificacao() {
        System.out.println("--- TESTE DOS TIPOS DE NOTIFICAÇÃO ---");
        
        // Email
        NotificacaoFactory emailFactory = new EmailNotificacaoFactory();
        System.out.println("📧 " + emailFactory.getInformacoesTipo());
        emailFactory.enviarNotificacao(
            "usuario@exemplo.com",
            "Bem-vindo!",
            "Obrigado por se cadastrar em nossa plataforma!"
        );
        
        System.out.println();
        
        // SMS
        NotificacaoFactory smsFactory = new SMSNotificacaoFactory();
        System.out.println("📱 " + smsFactory.getInformacoesTipo());
        smsFactory.enviarNotificacao(
            "11987654321",
            "Código",
            "Seu código de verificação é: 123456"
        );
        
        System.out.println();
        
        // Push
        NotificacaoFactory pushFactory = new PushNotificacaoFactory();
        System.out.println("🔔 " + pushFactory.getInformacoesTipo());
        pushFactory.enviarNotificacao(
            "a1b2c3d4e5f6789012345678901234567890abcd",
            "Nova mensagem",
            "Você recebeu uma nova mensagem de João Silva"
        );
    }
    
    /**
     * Testa validações de cada tipo
     */
    private static void testarValidacoes() {
        System.out.println("--- TESTE DE VALIDAÇÕES ---");
        
        NotificacaoFactory[] factories = {
            new EmailNotificacaoFactory(),
            new SMSNotificacaoFactory(),
            new PushNotificacaoFactory()
        };
        
        // Destinatários inválidos para cada tipo
        String[] destinatariosInvalidos = {
            "email-invalido",      // Email sem @
            "123",                 // SMS muito curto
            "token-muito-curto"    // Push token inválido
        };
        
        for (int i = 0; i < factories.length; i++) {
            System.out.println("Testando " + factories[i].criarNotificacao().getTipo() + 
                             " com destinatário inválido:");
            factories[i].enviarNotificacao(
                destinatariosInvalidos[i],
                "Teste",
                "Mensagem de teste"
            );
            System.out.println();
        }
    }
    
    /**
     * Demonstra o padrão Factory Method com polimorfismo
     */
    private static void testarFactoryMethod() {
        System.out.println("--- TESTE FACTORY METHOD (POLIMORFISMO) ---");
        
        // Array de factories - polimorfismo
        NotificacaoFactory[] factories = {
            new EmailNotificacaoFactory(),
            new SMSNotificacaoFactory(),
            new PushNotificacaoFactory()
        };
        
        // Destinatários válidos
        String[] destinatarios = {
            "admin@sistema.com",
            "11999887766",
            "1a2b3c4d5e6f7890123456789012345678901234"
        };
        
        // Envia a mesma mensagem usando diferentes tipos
        String assunto = "Manutenção do Sistema";
        String mensagem = "O sistema ficará em manutenção das 02:00 às 04:00.";
        
        for (int i = 0; i < factories.length; i++) {
            System.out.println("Enviando via " + factories[i].criarNotificacao().getTipo() + ":");
            factories[i].enviarNotificacao(destinatarios[i], assunto, mensagem);
            System.out.println();
        }
    }
    
    /**
     * Simula um cenário real de uso
     */
    private static void testarCenarioReal() {
        System.out.println("--- CENÁRIO REAL: SISTEMA DE ALERTAS ---");
        
        // Simula configuração do usuário
        String[] preferenciasUsuario = {"email", "sms", "push"};
        
        // Informações do alerta
        String evento = "Transação Suspeita";
        String detalhes = "Detectamos uma transação de R$ 2.500,00 em local incomum. " +
                         "Se não foi você, bloqueie seu cartão imediatamente.";
        
        // Dados do usuário
        String email = "cliente@email.com";
        String telefone = "11988776655";
        String deviceToken = "abc123def456789012345678901234567890abcd";
        
        System.out.println("🚨 Evento detectado: " + evento);
        System.out.println("📋 Preferências do usuário: " + String.join(", ", preferenciasUsuario));
        System.out.println();
        
        // Envia para todos os canais preferidos
        for (String preferencia : preferenciasUsuario) {
            NotificacaoFactory factory = obterFactory(preferencia);
            String destinatario = obterDestinatario(preferencia, email, telefone, deviceToken);
            
            if (factory != null && destinatario != null) {
                // Testa disponibilidade antes de enviar
                if (factory.testarDisponibilidade()) {
                    factory.enviarNotificacao(destinatario, evento, detalhes);
                } else {
                    System.out.println("⚠️ Serviço " + preferencia + " indisponível, tentando com retry...");
                    factory.enviarComRetry(destinatario, evento, detalhes, 3);
                }
                System.out.println();
            }
        }
        
        System.out.println("✅ Processo de alerta concluído");
    }
    
    /**
     * Método auxiliar para obter a factory baseada na preferência
     */
    private static NotificacaoFactory obterFactory(String tipo) {
        switch (tipo.toLowerCase()) {
            case "email":
                return new EmailNotificacaoFactory();
            case "sms":
                return new SMSNotificacaoFactory();
            case "push":
                return new PushNotificacaoFactory();
            default:
                System.out.println("❌ Tipo de notificação desconhecido: " + tipo);
                return null;
        }
    }
    
    /**
     * Método auxiliar para obter o destinatário correto
     */
    private static String obterDestinatario(String tipo, String email, String telefone, String deviceToken) {
        switch (tipo.toLowerCase()) {
            case "email":
                return email;
            case "sms":
                return telefone;
            case "push":
                return deviceToken;
            default:
                return null;
        }
    }
}