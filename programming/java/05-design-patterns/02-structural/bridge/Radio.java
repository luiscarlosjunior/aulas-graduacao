/**
 * Concrete Implementor - Implementação concreta para Rádio
 * 
 * Implementa a interface Dispositivo fornecendo funcionalidades
 * específicas de um rádio.
 * 
 * Demonstra como diferentes implementações podem coexistir
 * e serem controladas pela mesma hierarquia de abstrações.
 */
public class Radio implements Dispositivo {
    private boolean ligado = false;
    private int volume = 20;
    private int canal = 100; // Frequência em FM
    private String nome;
    
    /**
     * Construtor do Rádio
     * @param nome Nome/modelo do rádio
     */
    public Radio(String nome) {
        this.nome = nome;
    }
    
    @Override
    public boolean estaLigado() {
        return ligado;
    }
    
    @Override
    public void ligar() {
        ligado = true;
        System.out.println("Rádio " + nome + ": Ligando...");
    }
    
    @Override
    public void desligar() {
        ligado = false;
        System.out.println("Rádio " + nome + ": Desligando...");
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
        System.out.println("Rádio " + nome + ": Volume ajustado para " + this.volume + "%");
    }
    
    @Override
    public int obterCanal() {
        return canal;
    }
    
    @Override
    public void definirCanal(int canal) {
        this.canal = canal;
        System.out.println("Rádio " + nome + ": Estação alterada para " + canal + " FM");
    }
    
    @Override
    public String getNome() {
        return "Rádio " + nome;
    }
}
