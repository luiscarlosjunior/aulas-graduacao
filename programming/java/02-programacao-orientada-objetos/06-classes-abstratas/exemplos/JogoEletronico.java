/**
 * Classe JogoEletronico - Especialização de Jogo
 * 
 * Implementa um jogo eletrônico com plataforma, dificuldade e pontuação.
 * Exemplo: Jogos de console, PC, mobile, etc.
 * 
 * @author Curso POO Java - Universidade Nove de Julho
 */
public class JogoEletronico extends Jogo {
    private String plataforma;
    private String nivelDificuldade;
    private int pontuacao;
    
    /**
     * Construtor da classe JogoEletronico
     * @param nome Nome do jogo
     * @param numeroJogadores Número de jogadores
     * @param plataforma Plataforma do jogo (PC, Console, Mobile)
     */
    public JogoEletronico(String nome, int numeroJogadores, String plataforma) {
        super(nome, numeroJogadores);
        this.plataforma = plataforma;
        this.nivelDificuldade = "Normal";
        this.pontuacao = 0;
    }
    
    public String getPlataforma() {
        return plataforma;
    }
    
    public String getNivelDificuldade() {
        return nivelDificuldade;
    }
    
    public int getPontuacao() {
        return pontuacao;
    }
    
    /**
     * Aumenta a pontuação do jogador
     * @param pontos Pontos a serem adicionados
     */
    public void aumentarPontuacao(int pontos) {
        this.pontuacao += pontos;
    }
    
    @Override
    public void iniciar() {
        System.out.println("\n🎮 Carregando jogo eletrônico...");
        System.out.println("💻 Plataforma: " + plataforma);
        System.out.println("⚙️  Dificuldade: " + nivelDificuldade);
        System.out.println("✨ Criando sessão para " + numeroJogadores + " jogador(es)");
        System.out.println("🔊 Carregando áudio e gráficos...");
    }
    
    @Override
    public void jogar() {
        System.out.println("\n▶️  JOGANDO...");
        // Simula diferentes níveis do jogo
        String[] niveis = {"Tutorial", "Nível 1", "Nível 2", "Boss Final"};
        
        for (int i = 0; i < niveis.length; i++) {
            System.out.println("   🎯 " + niveis[i] + ":");
            
            // Simula ações no jogo
            if (i == 0) {
                System.out.println("      📚 Aprendendo os controles...");
                aumentarPontuacao(100);
            } else if (i < 3) {
                System.out.println("      ⚔️  Enfrentando inimigos...");
                System.out.println("      💎 Coletando itens...");
                aumentarPontuacao(250 * i);
            } else {
                System.out.println("      👹 Batalha épica contra o chefe!");
                System.out.println("      💥 Usando habilidades especiais...");
                aumentarPontuacao(1000);
            }
            
            System.out.println("      📊 Pontuação atual: " + pontuacao);
            
            try {
                Thread.sleep(400); // Simula tempo de jogo
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
    
    @Override
    public void terminar() {
        System.out.println("\n🏆 Finalizando jogo eletrônico");
        System.out.println("📊 Estatísticas Finais:");
        System.out.println("   - Pontuação Total: " + pontuacao + " pontos");
        System.out.println("   - Jogadores: " + numeroJogadores);
        System.out.println("   - Plataforma: " + plataforma);
        
        if (pontuacao >= 1500) {
            System.out.println("🌟 Classificação: S - Perfeito!");
        } else if (pontuacao >= 1000) {
            System.out.println("⭐ Classificação: A - Excelente!");
        } else {
            System.out.println("✨ Classificação: B - Bom trabalho!");
        }
        
        System.out.println("💾 Salvando progresso...");
    }
}
