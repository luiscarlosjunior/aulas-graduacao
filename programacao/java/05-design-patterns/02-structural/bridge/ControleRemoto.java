/**
 * Abstraction - Abstração do padrão Bridge
 * 
 * Define a interface de alto nível baseada na interface do Implementor.
 * Mantém uma referência para um objeto do tipo Implementor e delega
 * o trabalho real para esse objeto.
 * 
 * Esta classe pode ser estendida para criar abstrações mais refinadas
 * sem afetar as implementações concretas (Dispositivo).
 */
public class ControleRemoto {
    protected Dispositivo dispositivo;
    
    /**
     * Construtor que estabelece a "ponte" com o dispositivo
     * @param dispositivo Dispositivo a ser controlado
     */
    public ControleRemoto(Dispositivo dispositivo) {
        this.dispositivo = dispositivo;
    }
    
    /**
     * Liga ou desliga o dispositivo
     */
    public void alternarLigado() {
        if (dispositivo.estaLigado()) {
            dispositivo.desligar();
        } else {
            dispositivo.ligar();
        }
    }
    
    /**
     * Aumenta o volume do dispositivo
     */
    public void aumentarVolume() {
        int novoVolume = dispositivo.obterVolume() + 10;
        dispositivo.definirVolume(novoVolume);
    }
    
    /**
     * Diminui o volume do dispositivo
     */
    public void diminuirVolume() {
        int novoVolume = dispositivo.obterVolume() - 10;
        dispositivo.definirVolume(novoVolume);
    }
    
    /**
     * Avança para o próximo canal
     */
    public void proximoCanal() {
        int novoCanal = dispositivo.obterCanal() + 1;
        dispositivo.definirCanal(novoCanal);
    }
    
    /**
     * Volta para o canal anterior
     */
    public void canalAnterior() {
        int novoCanal = dispositivo.obterCanal() - 1;
        dispositivo.definirCanal(novoCanal);
    }
    
    /**
     * Exibe informações do dispositivo
     */
    public void exibirInfo() {
        System.out.println("\n--------------------");
        System.out.println("Dispositivo: " + dispositivo.getNome());
        System.out.println("Status: " + (dispositivo.estaLigado() ? "Ligado" : "Desligado"));
        if (dispositivo.estaLigado()) {
            System.out.println("Volume: " + dispositivo.obterVolume() + "%");
            System.out.println("Canal: " + dispositivo.obterCanal());
        }
        System.out.println("--------------------");
    }
}
