import java.util.List;

/*
 *  DemoPolimorfismo — a mesma linha, respostas diferentes.
 *
 *  Contraste pedagógico:
 *   - relatorio()  -> jeito POLIMÓRFICO (bom): cada objeto responde por si.
 *   - relatorioRuim() -> jeito com IF DE TIPO (ruim): mostrado de propósito
 *     para o aluno enxergar a dor que o polimorfismo elimina.
 */
public class DemoPolimorfismo {
    public static void main(String[] args) {

        List<Usuario> usuarios = List.of(
                new Ouvinte("Ana"),
                new Artista("João"),
                new Podcaster("Marina")   // o tipo NOVO entra sem quebrar nada
        );

        System.out.println("=== Jeito polimórfico (recomendado) ===");
        relatorio(usuarios);

        System.out.println("\n=== Jeito com 'if de tipo' (anti-exemplo) ===");
        relatorioRuim(usuarios);
    }

    /**
     * BOM: nenhum "if tipo". Chamamos beneficios() no tipo genérico e a
     * ligação dinâmica escolhe a implementação certa. Um tipo novo NÃO obriga
     * a mudar este método — ele é "fechado para modificação".
     */
    static void relatorio(List<Usuario> usuarios) {
        for (Usuario u : usuarios) {
            System.out.println("  " + u.getNome() + " tem direito a: " + u.beneficios());
        }
    }

    /**
     * RUIM (só para comparação): a cada tipo novo, alguém precisa VOLTAR aqui e
     * acrescentar um `else if`. Fácil esquecer, fácil errar. É a "cascata de
     * ifs" que o polimorfismo veio substituir. (Aqui o Podcaster cai no else e
     * fica sem benefício — exatamente o tipo de bug que essa abordagem gera.)
     */
    static void relatorioRuim(List<Usuario> usuarios) {
        for (Usuario u : usuarios) {
            String b;
            if (u instanceof Ouvinte) {
                b = "streaming ilimitado e playlists";
            } else if (u instanceof Artista) {
                b = "publicar álbuns e receber royalties";
            } else {
                b = "??? (esqueceram de tratar este tipo)";
            }
            System.out.println("  " + u.getNome() + " tem direito a: " + b);
        }
    }
}
