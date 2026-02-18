public class FreteService {
    // Composição com a estratégia de frete
    private FreteStrategy freteStrategy;

    public void setFreteStrategy(FreteStrategy freteStrategy) {
        this.freteStrategy = freteStrategy;
    }

    public double calcularFrete(double peso, double distancia) {
        if(freteStrategy == null) {
            throw new IllegalStateException("Estratégia de frete não definida.");
        }

        return freteStrategy.calcularFrete(peso, distancia);
    }
}