/**
 * Classe JogoTabuleiro - Especialização de Jogo
 * 
 * Implementa um jogo de tabuleiro com dados e casas.
 * Exemplo: Banco Imobiliário, Ludo, War, etc.
 * 
 * @author Curso POO Java - Universidade Nove de Julho
 */
public class JogoTabuleiro extends Jogo {
    private String tipoDado;
    private int casas;
    
    /**
     * Construtor da classe JogoTabuleiro
     * @param nome Nome do jogo
     * @param numeroJogadores Número de jogadores
     * @param tipoDado Tipo de dado usado (ex: "D6", "D20")
     * @param casas Número de casas no tabuleiro
     */
    public JogoTabuleiro(String nome, int numeroJogadores, String tipoDado, int casas) {
        super(nome, numeroJogadores);
        this.tipoDado = tipoDado;
        this.casas = casas;
    }
    
    public String getTipoDado() {
        return tipoDado;
    }
    
    public int getCasas() {
        return casas;
    }
    
    /**
     * Simula o lançamento de dados
     * @return Valor do dado (1 a 6)
     */
    public int lancarDados() {
        return (int) (Math.random() * 6) + 1;
    }
    
    @Override
    public void iniciar() {
        System.out.println("\n🎲 Preparando tabuleiro...");
        System.out.println("📋 Tabuleiro com " + casas + " casas");
        System.out.println("🎲 Dados: " + tipoDado);
        System.out.println("✨ Posicionando " + numeroJogadores + " peças no início");
    }
    
    @Override
    public void jogar() {
        System.out.println("\n▶️  JOGANDO...");
        // Simula 5 turnos
        for (int turno = 1; turno <= 5; turno++) {
            System.out.println("   🔄 Turno " + turno + ":");
            for (int j = 1; j <= numeroJogadores; j++) {
                int dado = lancarDados();
                System.out.println("      🎲 Jogador " + j + " tirou: " + dado);
            }
            try {
                Thread.sleep(300); // Simula tempo de jogo
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
    
    @Override
    public void terminar() {
        System.out.println("\n🏆 Finalizando jogo de tabuleiro");
        System.out.println("📊 Estatísticas:");
        System.out.println("   - Turnos jogados: 5");
        System.out.println("   - Jogadores: " + numeroJogadores);
        System.out.println("   - Casas do tabuleiro: " + casas);
        System.out.println("🎉 Um jogador alcançou a linha de chegada!");
    }
}
