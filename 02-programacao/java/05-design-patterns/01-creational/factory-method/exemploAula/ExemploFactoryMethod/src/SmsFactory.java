public class SmsFactory extends NotificacaoFactory {

    @Override
    public Notificacao criarNotificacao() {
        return new NotificacaoSms();
    }
    
}
