/**
 * Factory abstrata para criação de notificações
 * 
 * Implementa o padrão Factory Method definindo o método abstrato
 * para criação de notificações e métodos comuns para todas as factories.
 * 
 * @author Aulas Graduação
 */
public abstract class NotificacaoFactory {
    
    /**
     * Factory Method - deve ser implementado pelas subclasses
     * 
     * @return instância de Notificacao
     */
    public abstract Notificacao criarNotificacao();
    
    /**
     * Método template que usa o factory method
     * 
     * @param destinatario para quem enviar
     * @param assunto assunto da notificação
     * @param mensagem conteúdo da mensagem
     * @return true se enviado com sucesso
     */
    public boolean enviarNotificacao(String destinatario, String assunto, String mensagem) {
        // Usa o factory method para criar a notificação
        Notificacao notificacao = criarNotificacao();
        
        // Validação comum
        if (destinatario == null || destinatario.trim().isEmpty()) {
            System.out.println("❌ Erro: Destinatário não pode ser vazio");
            return false;
        }
        
        if (mensagem == null || mensagem.trim().isEmpty()) {
            System.out.println("❌ Erro: Mensagem não pode ser vazia");
            return false;
        }
        
        // Validação específica do tipo
        if (!notificacao.validarDestinatario(destinatario)) {
            System.out.println("❌ Erro: Destinatário inválido para " + notificacao.getTipo());
            return false;
        }
        
        // Log antes do envio
        System.out.println("📤 Preparando " + notificacao.getTipo() + " para: " + destinatario);
        
        // Envia usando o tipo específico
        boolean sucesso = notificacao.enviar(destinatario, assunto, mensagem);
        
        // Log após o envio
        if (sucesso) {
            System.out.println("✅ " + notificacao.getTipo() + " enviada com sucesso!");
        } else {
            System.out.println("❌ Falha ao enviar " + notificacao.getTipo());
        }
        
        return sucesso;
    }
    
    /**
     * Retorna informações sobre o tipo de notificação que esta factory cria
     * 
     * @return informações da factory
     */
    public String getInformacoesTipo() {
        Notificacao notificacao = criarNotificacao();
        return notificacao.getInformacoes();
    }
    
    /**
     * Método utilitário para testar conectividade/disponibilidade
     * 
     * @return true se o serviço está disponível
     */
    public boolean testarDisponibilidade() {
        try {
            Notificacao notificacao = criarNotificacao();
            System.out.println("🔍 Testando disponibilidade de " + notificacao.getTipo() + "...");
            
            // Simula teste de conectividade
            Thread.sleep(500);
            
            // 90% de chance de estar disponível (simulação)
            boolean disponivel = Math.random() > 0.1;
            
            System.out.println(disponivel ? 
                "✅ Serviço disponível" : 
                "❌ Serviço indisponível");
            
            return disponivel;
            
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return false;
        }
    }
    
    /**
     * Envia notificação com retry em caso de falha
     * 
     * @param destinatario destinatário
     * @param assunto assunto
     * @param mensagem mensagem
     * @param maxTentativas máximo de tentativas
     * @return true se conseguiu enviar
     */
    public boolean enviarComRetry(String destinatario, String assunto, String mensagem, int maxTentativas) {
        for (int tentativa = 1; tentativa <= maxTentativas; tentativa++) {
            System.out.println("🔄 Tentativa " + tentativa + " de " + maxTentativas);
            
            if (enviarNotificacao(destinatario, assunto, mensagem)) {
                return true;
            }
            
            if (tentativa < maxTentativas) {
                System.out.println("⏳ Aguardando antes da próxima tentativa...");
                try {
                    Thread.sleep(1000 * tentativa); // Backoff progressivo
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        }
        
        System.out.println("❌ Falha após " + maxTentativas + " tentativas");
        return false;
    }
}