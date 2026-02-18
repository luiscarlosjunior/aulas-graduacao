public class TV implements Dispositivo {
    private boolean ligada;
    private int volume;

    public TV() {
        this.ligada = false;
        this.volume = 10; // volume inicial
    }

    @Override
    public void ligar() {
        ligada = true;
        System.out.println("TV ligada.");
    }

    @Override
    public void desligar() {
        ligada = false;
        System.out.println("TV desligada.");
    }

    @Override
    public void aumentarVolume(int quantidade) {
        if (ligada) {
            volume += quantidade;
            System.out.println("Volume aumentado para: " + volume);
        } else {
            System.out.println("A TV está desligada. Não é possível aumentar o volume.");
        }
    }
    
}
