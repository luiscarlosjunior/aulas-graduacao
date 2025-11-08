public abstract class NotificacaoFactory {
    public abstract Notificacao criarNotificacao();

    public void enviarMensagem(String mensagem) {
        Notificacao notificacao = criarNotificacao();
        notificacao.enviar(mensagem);
    }
}
