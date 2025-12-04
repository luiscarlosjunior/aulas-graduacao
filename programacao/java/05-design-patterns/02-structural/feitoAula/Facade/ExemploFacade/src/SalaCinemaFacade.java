public class SalaCinemaFacade {
    private LuzAmbiente luzAmbiente;
    private TV tv;
    private Som som;

    public SalaCinemaFacade() {
        this.luzAmbiente = new LuzAmbiente();
        this.tv = new TV();
        this.som = new Som();
    }

    public void iniciarFilme() {
        System.out.println("Iniciando experiência de cinema em casa...");
        luzAmbiente.powerToggle();
        while (luzAmbiente.getBrightness() > 20) {
            luzAmbiente.decreaseBrightness();
        }
        tv.powerToggle();
        som.tocarMusica();
        System.out.println("Aproveite o filme!");
    }

    public void encerrarFilme() {
        System.out.println("Encerrando experiência de cinema em casa...");
        tv.powerToggle();
        while (luzAmbiente.getBrightness() < 50) {
            luzAmbiente.increaseBrightness();
        }
        luzAmbiente.powerToggle();
        System.out.println("Sessão de cinema encerrada. Até a próxima!");
    }

}
