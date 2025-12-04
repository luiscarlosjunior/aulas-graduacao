public class EmailFactory extends NotificacaoFactory {

    @Override
    public Notificacao criarNotificacao() {
        return new NotificacaoEmail();
    }
    
}
