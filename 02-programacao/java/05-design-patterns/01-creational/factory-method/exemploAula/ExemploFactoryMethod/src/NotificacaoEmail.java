public class NotificacaoEmail implements Notificacao {

    @Override
    public void enviar(String mensagem) {
        System.out.println("Enviando email para " + mensagem);
    }
    
}
