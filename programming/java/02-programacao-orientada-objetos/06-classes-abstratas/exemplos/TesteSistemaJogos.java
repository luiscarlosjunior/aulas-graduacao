/**
 * Classe de Teste - Sistema de Jogos
 * 
 * Demonstra o uso de classes abstratas e Template Method Pattern
 * no contexto de um sistema de gerenciamento de jogos diversos.
 * 
 * @author Curso POO Java - Universidade Nove de Julho
 */
public class TesteSistemaJogos {
    
    public static void main(String[] args) {
        System.out.println("\n");
        System.out.println("╔════════════════════════════════════════════════════════════╗");
        System.out.println("║          🎮 PLATAFORMA DE JOGOS - GAME CENTER 🎮          ║");
        System.out.println("║                  Sistema de Gerenciamento                  ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝");
        
        // Criando diferentes tipos de jogos
        JogoCartas poker = new JogoCartas(
            "Poker Texas Hold'em", 
            4, 
            "Baralho Francês 52 cartas"
        );
        
        JogoTabuleiro bancoImobiliario = new JogoTabuleiro(
            "Banco Imobiliário", 
            3, 
            "D6", 
            40
        );
        
        JogoEletronico aventura = new JogoEletronico(
            "The Quest for Glory", 
            1, 
            "PC/Steam"
        );
        
        JogoEletronico multiplayer = new JogoEletronico(
            "Battle Royale Champions", 
            100, 
            "PlayStation 5"
        );
        
        // Array polimórfico - todos são tratados como Jogo
        Jogo[] jogos = {poker, bancoImobiliario, aventura, multiplayer};
        
        // 1. Listando jogos disponíveis
        System.out.println("\n╔════════════════════════════════════════════════════════════╗");
        System.out.println("║                   JOGOS DISPONÍVEIS                        ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝");
        
        for (int i = 0; i < jogos.length; i++) {
            System.out.println((i + 1) + ". 🎮 " + jogos[i].getNome() + 
                             " (" + jogos[i].getNumeroJogadores() + " jogadores)");
        }
        
        // 2. Executando cada jogo (Template Method em ação!)
        System.out.println("\n╔════════════════════════════════════════════════════════════╗");
        System.out.println("║              EXECUTANDO SESSÕES DE JOGOS                   ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝");
        
        for (Jogo jogo : jogos) {
            jogo.executarJogo(); // Template Method - mesmo fluxo para todos!
            
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        
        // 3. Demonstrando recursos específicos
        System.out.println("╔════════════════════════════════════════════════════════════╗");
        System.out.println("║            DEMONSTRAÇÃO DE RECURSOS ESPECÍFICOS            ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝\n");
        
        // Recursos específicos de JogoCartas
        System.out.println("🃏 Recursos do Jogo de Cartas:");
        System.out.println("   - Baralho: " + poker.getBaralho());
        System.out.println("   - Rodadas jogadas: " + poker.getRodadas());
        poker.embaralhar();
        
        System.out.println();
        
        // Recursos específicos de JogoTabuleiro
        System.out.println("🎲 Recursos do Jogo de Tabuleiro:");
        System.out.println("   - Tipo de dado: " + bancoImobiliario.getTipoDado());
        System.out.println("   - Casas no tabuleiro: " + bancoImobiliario.getCasas());
        System.out.println("   - Lançando dado: " + bancoImobiliario.lancarDados());
        
        System.out.println();
        
        // Recursos específicos de JogoEletronico
        System.out.println("💻 Recursos do Jogo Eletrônico:");
        System.out.println("   - Plataforma: " + aventura.getPlataforma());
        System.out.println("   - Pontuação final: " + aventura.getPontuacao());
        System.out.println("   - Dificuldade: " + aventura.getNivelDificuldade());
        
        System.out.println();
        
        // 4. Análise de uso do Template Method Pattern
        System.out.println("╔════════════════════════════════════════════════════════════╗");
        System.out.println("║              ANÁLISE DO TEMPLATE METHOD PATTERN            ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝\n");
        
        System.out.println("📚 O que aconteceu:");
        System.out.println("   ✓ Todos os jogos seguiram o MESMO fluxo:");
        System.out.println("     1. iniciar()");
        System.out.println("     2. jogar()");
        System.out.println("     3. terminar()");
        System.out.println();
        System.out.println("   ✓ Mas cada tipo implementou de forma DIFERENTE!");
        System.out.println("     - Cartas: embaralhar, rodadas");
        System.out.println("     - Tabuleiro: dados, casas, turnos");
        System.out.println("     - Eletrônico: níveis, pontuação, gráficos");
        System.out.println();
        System.out.println("   ✓ O método executarJogo() (final) garantiu:");
        System.out.println("     - Estrutura consistente");
        System.out.println("     - Impossibilidade de alterar o fluxo");
        System.out.println("     - Flexibilidade nas implementações");
        
        System.out.println();
        
        // 5. Estatísticas finais
        System.out.println("╔════════════════════════════════════════════════════════════╗");
        System.out.println("║                  ESTATÍSTICAS DO SISTEMA                   ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝\n");
        
        int totalJogadores = 0;
        for (Jogo jogo : jogos) {
            totalJogadores += jogo.getNumeroJogadores();
        }
        
        System.out.println("📊 Resumo:");
        System.out.println("   - Total de jogos: " + jogos.length);
        System.out.println("   - Total de jogadores: " + totalJogadores);
        System.out.println("   - Tipos de jogos: 3 (Cartas, Tabuleiro, Eletrônico)");
        
        System.out.println("\n✅ Sistema executado com sucesso!");
        System.out.println("🎮 Demonstração de Classes Abstratas e Template Method concluída!\n");
    }
}
