/**
 * Implementação de notificação push
 * 
 * @author Aulas Graduação
 */
public class PushNotificacao implements Notificacao {
    
    private static final int MAX_TITULO = 50;
    private static final int MAX_CORPO = 200;
    
    @Override
    public boolean enviar(String destinatario, String assunto, String mensagem) {
        // Para push, assunto vira o título
        String titulo = (assunto != null && !assunto.trim().isEmpty()) ? 
                       assunto : "Notificação";
        
        // Trunca título se necessário
        if (titulo.length() > MAX_TITULO) {
            titulo = titulo.substring(0, MAX_TITULO - 3) + "...";
        }
        
        // Trunca mensagem se necessário
        String corpo = mensagem;
        if (corpo.length() > MAX_CORPO) {
            corpo = corpo.substring(0, MAX_CORPO - 3) + "...";
        }
        
        // Simula envio de push
        System.out.println("🔔 === PUSH NOTIFICATION ===");
        System.out.println("   Device Token: " + destinatario);
        System.out.println("   Título: " + titulo + " (" + titulo.length() + "/" + MAX_TITULO + ")");
        System.out.println("   Corpo: " + corpo + " (" + corpo.length() + "/" + MAX_CORPO + ")");
        System.out.println("   Prioridade: Alta");
        System.out.println("   ============================");
        
        // Simula processamento
        try {
            Thread.sleep(50); // Push é muito rápido
            return Math.random() > 0.08; // 92% de sucesso (dispositivo pode estar offline)
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return false;
        }
    }
    
    @Override
    public boolean validarDestinatario(String destinatario) {
        // Device token é uma string alfanumérica longa
        if (destinatario == null) return false;
        
        // Simula validação de device token
        return destinatario.length() >= 32 && 
               destinatario.matches("[a-fA-F0-9]+");
    }
    
    @Override
    public String getTipo() {
        return "Push Notification";
    }
    
    @Override
    public String getInformacoes() {
        return "Push - Título máx " + MAX_TITULO + " chars, corpo máx " + MAX_CORPO + 
               " chars. Entrega instantânea se dispositivo online.";
    }
}

/**
 * Factory concreta para criação de notificações push
 */
class PushNotificacaoFactory extends NotificacaoFactory {
    
    @Override
    public Notificacao criarNotificacao() {
        return new PushNotificacao();
    }
}