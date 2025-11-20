public class App {
    public static void main(String[] args) throws Exception {
        FreteService freteService = new FreteService();
        double peso = 10.0; // em kg
        double distancia = 100.0; // em km

        freteService.setFreteStrategy(new AplicativoStrategy());
        System.out.println("Frete via Aplicativo: R$ " + 
        freteService.calcularFrete(peso, distancia));

        freteService.setFreteStrategy(new SedexStrategy());
        System.out.println("Frete via Sedex: R$ " + freteService.calcularFrete(peso, distancia));

        freteService.setFreteStrategy(new TransportadoraStrategy());
        System.out.println("Frete via Transportadora: R$ " + freteService.calcularFrete(peso, distancia));

    }
}
