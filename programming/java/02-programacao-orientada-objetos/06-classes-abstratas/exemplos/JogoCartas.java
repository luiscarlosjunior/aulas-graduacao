/**
 * Classe JogoCartas - Especialização de Jogo
 * 
 * Implementa um jogo de cartas com baralho e rodadas.
 * Exemplo: Poker, Truco, Uno, etc.
 * 
 * @author Curso POO Java - Universidade Nove de Julho
 */
public class JogoCartas extends Jogo {
    private String baralho;
    private int rodadas;
    
    /**
     * Construtor da classe JogoCartas
     * @param nome Nome do jogo
     * @param numeroJogadores Número de jogadores
     * @param baralho Tipo de baralho usado
     */
    public JogoCartas(String nome, int numeroJogadores, String baralho) {
        super(nome, numeroJogadores);
        this.baralho = baralho;
        this.rodadas = 0;
    }
    
    public String getBaralho() {
        return baralho;
    }
    
    public int getRodadas() {
        return rodadas;
    }
    
    /**
     * Embaralha as cartas antes de iniciar
     */
    public void embaralhar() {
        System.out.println("🔀 Embaralhando cartas...");
    }
    
    @Override
    public void iniciar() {
        System.out.println("\n🃏 Preparando jogo de cartas...");
        System.out.println("📦 Baralho: " + baralho);
        embaralhar();
        System.out.println("✨ Distribuindo cartas para " + numeroJogadores + " jogadores");
    }
    
    @Override
    public void jogar() {
        System.out.println("\n▶️  JOGANDO...");
        // Simula 3 rodadas
        for (int i = 1; i <= 3; i++) {
            rodadas++;
            System.out.println("   🎯 Rodada " + i + ":");
            System.out.println("      - Cada jogador escolhe uma carta");
            System.out.println("      - Avaliando jogadas...");
            try {
                Thread.sleep(500); // Simula tempo de jogo
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println("   ✓ Total de rodadas: " + rodadas);
    }
    
    @Override
    public void terminar() {
        System.out.println("\n🏆 Finalizando jogo de cartas");
        System.out.println("📊 Estatísticas:");
        System.out.println("   - Rodadas jogadas: " + rodadas);
        System.out.println("   - Jogadores: " + numeroJogadores);
        System.out.println("🎉 Parabéns ao vencedor!");
    }
}
