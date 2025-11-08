public class App {
    public static void main(String[] args) throws Exception {
        NotificacaoFactory email = new EmailFactory();
        email.enviarMensagem("Olá, tudo bem?"); 

        NotificacaoFactory sms = new SmsFactory();
        sms.enviarMensagem("Olá, tudo bem?");

        NotificacaoFactory what = new WhatsFactory();
        what.enviarMensagem("Olá");
    }
}
