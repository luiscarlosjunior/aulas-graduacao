/**
 * Interface para diferentes tipos de notificação
 * 
 * Define o contrato que todos os tipos de notificação devem seguir.
 * 
 * @author Aulas Graduação
 */
public interface Notificacao {
    
    /**
     * Envia a notificação
     * 
     * @param destinatario para quem enviar
     * @param assunto assunto da notificação
     * @param mensagem conteúdo da mensagem
     * @return true se enviado com sucesso, false caso contrário
     */
    boolean enviar(String destinatario, String assunto, String mensagem);
    
    /**
     * Valida se o destinatário é válido para este tipo de notificação
     * 
     * @param destinatario destinatário a ser validado
     * @return true se válido, false caso contrário
     */
    boolean validarDestinatario(String destinatario);
    
    /**
     * Retorna o tipo de notificação
     * 
     * @return string identificando o tipo
     */
    String getTipo();
    
    /**
     * Retorna informações sobre limites/restrições
     * 
     * @return informações sobre o tipo de notificação
     */
    String getInformacoes();
}