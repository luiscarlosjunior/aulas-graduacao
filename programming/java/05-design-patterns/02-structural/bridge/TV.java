/**
 * Concrete Implementor - Implementação concreta para TV
 * 
 * Implementa a interface Dispositivo fornecendo funcionalidades
 * específicas de uma televisão.
 * 
 * Esta classe pode evoluir independentemente da hierarquia de abstrações
 * (ControleRemoto), demonstrando a flexibilidade do padrão Bridge.
 */
public class TV implements Dispositivo {
    private boolean ligado = false;
    private int volume = 30;
    private int canal = 1;
    private String nome;
    
    /**
     * Construtor da TV
     * @param nome Nome/modelo da TV
     */
    public TV(String nome) {
        this.nome = nome;
    }
    
    @Override
    public boolean estaLigado() {
        return ligado;
    }
    
    @Override
    public void ligar() {
        ligado = true;
        System.out.println("TV " + nome + ": Ligando...");
    }
    
    @Override
    public void desligar() {
        ligado = false;
        System.out.println("TV " + nome + ": Desligando...");
    }
    
    @Override
    public int obterVolume() {
        return volume;
    }
    
    @Override
    public void definirVolume(int porcentagem) {
        if (porcentagem < 0) {
            this.volume = 0;
        } else if (porcentagem > 100) {
            this.volume = 100;
        } else {
            this.volume = porcentagem;
        }
        System.out.println("TV " + nome + ": Volume ajustado para " + this.volume + "%");
    }
    
    @Override
    public int obterCanal() {
        return canal;
    }
    
    @Override
    public void definirCanal(int canal) {
        this.canal = canal;
        System.out.println("TV " + nome + ": Canal alterado para " + canal);
    }
    
    @Override
    public String getNome() {
        return "TV " + nome;
    }
}
