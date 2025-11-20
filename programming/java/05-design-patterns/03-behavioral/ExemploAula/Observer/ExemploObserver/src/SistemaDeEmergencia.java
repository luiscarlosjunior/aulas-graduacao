public class SistemaDeEmergencia implements Observer {
    @Override
    public void atualizar(int temperatura) {
        if (temperatura > 50) {
            System.out.println("Sistema de Emergência: Alerta! Temperatura crítica: " + temperatura + "°C");
        }
    }
}