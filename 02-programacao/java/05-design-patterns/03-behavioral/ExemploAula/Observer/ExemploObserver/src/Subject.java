// Enviar notificações para os observadores quando o estado mudar
public interface Subject {
    void registrarObserver(Observer o);
    void removerObserver(Observer o);
    void notificarObservadores();
}