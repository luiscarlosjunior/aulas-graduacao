/** Implementação simples do contrato Notificador: escreve no console. */
public class NotificadorConsole implements Notificador {
    @Override
    public void notificar(String mensagem) {
        System.out.println("   [aviso] " + mensagem);
    }
}
