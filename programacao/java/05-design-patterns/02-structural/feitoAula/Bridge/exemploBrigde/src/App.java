public class App {
    public static void main(String[] args) throws Exception {
        Dispositivo tv = new TV();
        ControleBridge controleTV = new ControleBridge(tv);
        controleTV.ligarDispositivo();
        controleTV.aumentarVolumeDispositivo(5);
        controleTV.desligarDispositivo();

        Dispositivo radio = new Radio();
        ControleBridge controleRadio = new ControleBridge(radio);
        controleRadio.ligarDispositivo();
        controleRadio.aumentarVolumeDispositivo(3);
        controleRadio.desligarDispositivo();
        
    }
}
