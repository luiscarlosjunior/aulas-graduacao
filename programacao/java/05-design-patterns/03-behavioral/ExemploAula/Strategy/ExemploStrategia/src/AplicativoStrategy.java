public class AplicativoStrategy implements FreteStrategy {
    @Override
    public double calcularFrete(double peso, double distancia) {
        // Cálculo do frete para Aplicativo: R$ 15,00 + R$ 4,00 por kg + R$ 0,15 por km
        return 15.0 + (4.0 * peso) + (0.15 * distancia);
    }
    
}