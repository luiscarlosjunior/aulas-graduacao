/**
 * Facade - Simplifica o uso do sistema de home theater
 * 
 * Encapsula a complexidade de ligar múltiplos dispositivos
 * e configurá-los adequadamente para assistir um filme
 */
public class HomeTheaterFacade {
    // Referências aos subsistemas
    private DVDPlayer dvd;
    private Amplificador amp;
    private Projetor projetor;
    private Luzes luzes;
    
    /**
     * Construtor inicializa todos os subsistemas
     */
    public HomeTheaterFacade(DVDPlayer dvd, Amplificador amp, 
                             Projetor projetor, Luzes luzes) {
        this.dvd = dvd;
        this.amp = amp;
        this.projetor = projetor;
        this.luzes = luzes;
    }
    
    /**
     * Método de alto nível: assistir filme
     * Encapsula toda a complexidade de configuração
     * 
     * SEM facade, cliente precisaria:
     * - Ligar 4 dispositivos
     * - Configurar entrada do projetor
     * - Ajustar modo widescreen
     * - Configurar surround
     * - Ajustar volume
     * - Dimmer nas luzes
     * - Inserir e dar play no DVD
     * 
     * COM facade: apenas um método!
     */
    public void assistirFilme(String filme) {
        System.out.println("\n=== Preparando para assistir '" + filme + "' ===\n");
        
        // Sequência automatizada de configuração
        luzes.dim(10);              // Luz ambiente
        projetor.ligar();
        projetor.modoWideScreen();
        projetor.setInput("DVD");
        
        amp.ligar();
        amp.setVolume(8);
        amp.setSurroundSound();
        
        dvd.ligar();
        dvd.inserirDVD(filme);
        dvd.play();
        
        System.out.println("\n=== Aproveite o filme! ===\n");
    }
    
    /**
     * Método de alto nível: encerrar filme
     * Desliga tudo na ordem correta
     */
    public void encerrarFilme() {
        System.out.println("\n=== Encerrando sessão de cinema ===\n");
        
        dvd.stop();
        dvd.ejetar();
        dvd.desligar();
        
        amp.desligar();
        projetor.desligar();
        luzes.acender();
        
        System.out.println("\n=== Sistema desligado ===\n");
    }
    
    /**
     * Outro método de conveniência: modo game
     */
    public void modoGame() {
        System.out.println("\n=== Configurando modo GAME ===\n");
        
        luzes.dim(30);
        projetor.ligar();
        projetor.setInput("HDMI");
        
        amp.ligar();
        amp.setVolume(6);
        
        System.out.println("\n=== Pronto para jogar! ===\n");
    }
}
