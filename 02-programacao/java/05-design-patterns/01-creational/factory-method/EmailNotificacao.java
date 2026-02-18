/**
 * Implementação de notificação por email
 * 
 * @author Aulas Graduação
 */
public class EmailNotificacao implements Notificacao {
    
    @Override
    public boolean enviar(String destinatario, String assunto, String mensagem) {
        // Simula envio de email
        System.out.println("📧 === EMAIL ===");
        System.out.println("   Para: " + destinatario);
        System.out.println("   Assunto: " + assunto);
        System.out.println("   Mensagem: " + mensagem);
        System.out.println("   ===============");
        
        // Simula processamento
        try {
            Thread.sleep(200); // Simula tempo de envio
            return Math.random() > 0.05; // 95% de sucesso
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return false;
        }
    }
    
    @Override
    public boolean validarDestinatario(String destinatario) {
        // Validação simples de email
        return destinatario != null && 
               destinatario.contains("@") && 
               destinatario.contains(".") &&
               destinatario.length() > 5;
    }
    
    @Override
    public String getTipo() {
        return "Email";
    }
    
    @Override
    public String getInformacoes() {
        return "Email - Suporta texto rico, anexos, CC/BCC. Entrega pode demorar alguns minutos.";
    }
}

/**
 * Factory concreta para criação de notificações por email
 */
class EmailNotificacaoFactory extends NotificacaoFactory {
    
    @Override
    public Notificacao criarNotificacao() {
        return new EmailNotificacao();
    }
}