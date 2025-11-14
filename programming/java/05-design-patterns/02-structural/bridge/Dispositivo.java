/**
 * Interface Implementor do padrão Bridge
 * 
 * Define a interface para a implementação concreta dos dispositivos.
 * Esta interface não precisa corresponder exatamente à interface da Abstração.
 * De fato, as duas interfaces podem ser completamente diferentes.
 * 
 * No padrão Bridge, esta interface permanece independente da hierarquia
 * de abstração, permitindo que ambas variem independentemente.
 */
public interface Dispositivo {
    
    /**
     * Verifica se o dispositivo está ligado
     * @return true se ligado, false caso contrário
     */
    boolean estaLigado();
    
    /**
     * Liga o dispositivo
     */
    void ligar();
    
    /**
     * Desliga o dispositivo
     */
    void desligar();
    
    /**
     * Obtém o volume atual do dispositivo
     * @return volume atual (0-100)
     */
    int obterVolume();
    
    /**
     * Define o volume do dispositivo
     * @param porcentagem novo volume (0-100)
     */
    void definirVolume(int porcentagem);
    
    /**
     * Obtém o canal atual do dispositivo
     * @return número do canal
     */
    int obterCanal();
    
    /**
     * Define o canal do dispositivo
     * @param canal número do canal
     */
    void definirCanal(int canal);
    
    /**
     * Obtém o nome do dispositivo
     * @return nome do dispositivo
     */
    String getNome();
}
