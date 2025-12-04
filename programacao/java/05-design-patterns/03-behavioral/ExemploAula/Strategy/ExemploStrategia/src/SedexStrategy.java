public class SedexStrategy implements FreteStrategy {
    @Override
    public double calcularFrete(double peso, double distancia) {
        // Cálculo do frete para Sedex: R$ 20,00 + R$ 5,00 por kg + R$ 0,10 por km
        return 20.0 + (5.0 * peso) + (0.10 * distancia);
    }
}