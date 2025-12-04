public class ControleBridge {
    private Dispositivo dispositivo;
    
    public ControleBridge(Dispositivo dispositivo) {
        this.dispositivo = dispositivo;
    }

    public void ligarDispositivo() {
        dispositivo.ligar();
    }

    public void desligarDispositivo() {
        dispositivo.desligar();
    }

    public void aumentarVolumeDispositivo(int quantidade) {
        dispositivo.aumentarVolume(quantidade);
    }

}
