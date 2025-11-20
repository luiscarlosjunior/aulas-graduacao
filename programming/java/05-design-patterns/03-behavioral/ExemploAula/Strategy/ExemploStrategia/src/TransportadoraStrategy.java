public class TransportadoraStrategy implements FreteStrategy {
    @Override
    public double calcularFrete(double peso, double distancia) {
        // Cálculo do frete para Transportadora: R$ 10,00 + R$ 3,00 por kg + R$ 0,20 por km
        return 10.0 + (3.0 * peso) + (0.20 * distancia);
    }
    
}