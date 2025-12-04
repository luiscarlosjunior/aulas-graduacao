public class Radio implements Dispositivo {
    private boolean ligada;
    private int volume;

    public Radio() {
        this.ligada = false;
        this.volume = 10; // volume inicial
    }

    @Override
    public void ligar() {
        ligada = true;
        System.out.println("Rádio ligado.");
    }

    @Override
    public void desligar() {
        ligada = false;
        System.out.println("Rádio desligado.");
    }

    @Override
    public void aumentarVolume(int quantidade) {
        if (ligada) {
            volume += quantidade;
            System.out.println("Volume aumentado para: " + volume);
        } else {
            System.out.println("O rádio está desligado. Não é possível aumentar o volume.");
        }
    }
}
