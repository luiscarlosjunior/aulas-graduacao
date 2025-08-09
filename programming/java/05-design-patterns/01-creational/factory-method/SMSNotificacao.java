/**
 * Implementação de notificação por SMS
 * 
 * @author Aulas Graduação
 */
public class SMSNotificacao implements Notificacao {
    
    private static final int MAX_CARACTERES = 160;
    
    @Override
    public boolean enviar(String destinatario, String assunto, String mensagem) {
        // SMS não tem assunto, então incluimos na mensagem se fornecido
        String mensagemFinal = mensagem;
        if (assunto != null && !assunto.trim().isEmpty()) {
            mensagemFinal = assunto + ": " + mensagem;
        }
        
        // Trunca se necessário
        if (mensagemFinal.length() > MAX_CARACTERES) {
            mensagemFinal = mensagemFinal.substring(0, MAX_CARACTERES - 3) + "...";
        }
        
        // Simula envio de SMS
        System.out.println("📱 === SMS ===");
        System.out.println("   Para: " + destinatario);
        System.out.println("   Mensagem: " + mensagemFinal);
        System.out.println("   Caracteres: " + mensagemFinal.length() + "/" + MAX_CARACTERES);
        System.out.println("   ===========");
        
        // Simula processamento
        try {
            Thread.sleep(100); // SMS é mais rápido
            return Math.random() > 0.02; // 98% de sucesso
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return false;
        }
    }
    
    @Override
    public boolean validarDestinatario(String destinatario) {
        // Validação simples de telefone
        if (destinatario == null) return false;
        
        // Remove espaços, parênteses, hífens
        String telefone = destinatario.replaceAll("[\\s\\(\\)\\-]", "");
        
        // Deve ter apenas números e ter entre 10-15 dígitos
        return telefone.matches("\\d{10,15}");
    }
    
    @Override
    public String getTipo() {
        return "SMS";
    }
    
    @Override
    public String getInformacoes() {
        return "SMS - Máximo " + MAX_CARACTERES + " caracteres. Entrega instantânea. Custo por mensagem.";
    }
}

/**
 * Factory concreta para criação de notificações por SMS
 */
class SMSNotificacaoFactory extends NotificacaoFactory {
    
    @Override
    public Notificacao criarNotificacao() {
        return new SMSNotificacao();
    }
}