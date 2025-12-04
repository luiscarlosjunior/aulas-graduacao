public class WhatsFactory extends NotificacaoFactory {

    @Override
    public Notificacao criarNotificacao() {
        return new NotificacaoWhats();
    }
    
}
