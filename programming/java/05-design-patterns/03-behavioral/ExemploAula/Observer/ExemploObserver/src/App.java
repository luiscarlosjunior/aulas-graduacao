public class App {
    public static void main(String[] args) throws Exception {
        Temperatura temperatura = new Temperatura();

        Observer exibir = new SistemaDeEmergencia();
        Observer exibirTemp = new PainelExibicao();

        temperatura.registrarObserver(exibirTemp);
        temperatura.setTemperatura(25);
        temperatura.setTemperatura(55);
        temperatura.setTemperatura(25);

        temperatura.registrarObserver(exibir);

        temperatura.setTemperatura(45);
        temperatura.setTemperatura(55);
        temperatura.setTemperatura(30);

    }
}
