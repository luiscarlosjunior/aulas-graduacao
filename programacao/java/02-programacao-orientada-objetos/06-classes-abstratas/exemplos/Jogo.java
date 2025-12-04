/**
 * Classe abstrata Jogo - Sistema de Jogos
 * 
 * Define a estrutura base para qualquer tipo de jogo,
 * implementando o Template Method Pattern para garantir
 * que todos os jogos sigam o mesmo fluxo de execução.
 * 
 * @author Curso POO Java - Universidade Nove de Julho
 */
public abstract class Jogo {
    protected String nome;
    protected int numeroJogadores;
    protected boolean emAndamento;
    
    /**
     * Construtor da classe Jogo
     * @param nome Nome do jogo
     * @param numeroJogadores Número de jogadores
     */
    public Jogo(String nome, int numeroJogadores) {
        this.nome = nome;
        this.numeroJogadores = numeroJogadores;
        this.emAndamento = false;
    }
    
    // Getters
    public String getNome() {
        return nome;
    }
    
    public int getNumeroJogadores() {
        return numeroJogadores;
    }
    
    public boolean isEmAndamento() {
        return emAndamento;
    }
    
    /**
     * Métodos abstratos - cada tipo de jogo implementa sua lógica
     */
    public abstract void iniciar();
    public abstract void jogar();
    public abstract void terminar();
    
    /**
     * Template Method - define o fluxo de execução do jogo
     * Este método não pode ser sobrescrito (final)
     */
    public final void executarJogo() {
        System.out.println("\n╔════════════════════════════════════════╗");
        System.out.println("║  🎮 INICIANDO: " + nome);
        System.out.println("║  👥 Jogadores: " + numeroJogadores);
        System.out.println("╚════════════════════════════════════════╝");
        
        iniciar();
        emAndamento = true;
        
        jogar();
        
        emAndamento = false;
        terminar();
        
        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║  ✅ JOGO FINALIZADO!");
        System.out.println("╚════════════════════════════════════════╝\n");
    }
}
