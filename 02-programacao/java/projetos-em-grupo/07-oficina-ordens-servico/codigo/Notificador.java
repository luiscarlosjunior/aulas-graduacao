/**
 * Notificador — uma INTERFACE (contrato).
 * DIP (SOLID): o Gestor depende DESTE contrato, não de "console", "e-mail" ou "SMS".
 * Para trocar o canal, basta criar outra classe que implemente esta interface.
 */
public interface Notificador {
    void notificar(String mensagem);
}
