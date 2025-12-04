import java.util.ArrayList;
import java.util.List;

public class Temperatura implements Subject {
    private List<Observer> observers;
    private int temperatura;

    public Temperatura() {
        this.observers = new ArrayList<>();
    }

    @Override
    public void registrarObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void removerObserver(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notificarObservadores() {
        for (Observer o : observers) {
            o.atualizar(temperatura);
        }
    }

    public void setTemperatura(int novaTemperatura) {
        this.temperatura = novaTemperatura;
        notificarObservadores();
    }
    
}