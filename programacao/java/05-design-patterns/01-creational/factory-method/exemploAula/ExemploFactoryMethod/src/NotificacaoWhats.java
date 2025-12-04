public class NotificacaoWhats implements Notificacao {

    @Override
    public void enviar(String mensagem) {
        System.out.println("Enviando WhatsApp para " + mensagem);
    }
    
}
