/**
 * Refined Abstraction - Abstração refinada
 * 
 * Estende a interface definida pela Abstraction (ControleRemoto).
 * Adiciona funcionalidades extras sem modificar a hierarquia de implementação.
 * 
 * Esta é a chave do padrão Bridge: você pode estender a hierarquia de
 * abstrações independentemente da hierarquia de implementações.
 */
public class ControleRemotoAvancado extends ControleRemoto {
    
    /**
     * Construtor que passa o dispositivo para a classe base
     * @param dispositivo Dispositivo a ser controlado
     */
    public ControleRemotoAvancado(Dispositivo dispositivo) {
        super(dispositivo);
    }
    
    /**
     * Função avançada: Mudo (volume zero)
     */
    public void mudo() {
        System.out.println("\nControle Avançado: Ativando mudo");
        dispositivo.definirVolume(0);
    }
    
    /**
     * Função avançada: Volume máximo
     */
    public void volumeMaximo() {
        System.out.println("\nControle Avançado: Ativando volume máximo");
        dispositivo.definirVolume(100);
    }
    
    /**
     * Função avançada: Ir para canal específico diretamente
     * @param canal Número do canal desejado
     */
    public void irParaCanal(int canal) {
        System.out.println("\nControle Avançado: Mudando para canal " + canal);
        dispositivo.definirCanal(canal);
    }
    
    /**
     * Função avançada: Scan de canais (simula varredura)
     */
    public void scanCanais() {
        System.out.println("\nControle Avançado: Iniciando scan de canais...");
        int canalAtual = dispositivo.obterCanal();
        
        for (int i = 1; i <= 5; i++) {
            dispositivo.definirCanal(canalAtual + i);
            try {
                Thread.sleep(300); // Simula tempo de busca
            } catch (InterruptedException e) {
                // Ignora interrupção
            }
        }
        System.out.println("Scan completo!");
    }
}
