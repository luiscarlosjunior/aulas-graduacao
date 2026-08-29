/**
 * Cenário que junta CLASSES, OBJETOS e os três RELACIONAMENTOS (composição,
 * agregação e associação) — sem herança. Rode e leia a saída acompanhando os
 * comentários.
 *
 *   javac *.java
 *   java Principal
 */
public class Principal {

    public static void main(String[] args) {
        System.out.println("=== MELODIA — classes, objetos e relacionamentos ===\n");

        // ARTISTA (objeto simples) ------------------------------------------
        Artista nebula = new Artista("Banda Nébula");

        // COMPOSIÇÃO: o Album cria as próprias faixas -----------------------
        Album orbita = new Album("Órbita", 2025);
        Musica m1 = orbita.adicionarFaixa("Gravidade Zero", "Banda Nébula", 213);
        Musica m2 = orbita.adicionarFaixa("Cometa", "Banda Nébula", 187);
        Musica m3 = orbita.adicionarFaixa("Silêncio Sideral", "Banda Nébula", 245);

        System.out.println("Álbum: " + orbita.getTitulo() + " (" + orbita.getAno() + ")"
                + " — " + orbita.getFaixas().size() + " faixas, "
                + orbita.duracaoTotalSegundos() + "s no total");
        for (Musica m : orbita.getFaixas()) System.out.println("   • " + m);

        // OUVINTE + AGREGAÇÃO (playlist) + ASSOCIAÇÃO (seguir) ---------------
        Ouvinte ana = new Ouvinte("Ana Souza", "ana@email.com");
        ana.seguir(nebula);                       // ASSOCIAÇÃO: ana -> nebula

        Playlist favoritas = ana.criarPlaylist("Favoritas");
        favoritas.adicionar(m1);                  // AGREGAÇÃO: aponta p/ músicas existentes
        favoritas.adicionar(m3);
        favoritas.adicionar(m3);                  // duplicata ignorada

        // Comportamento: registrar reproduções ------------------------------
        m1.registrarReproducao();
        m1.registrarReproducao();
        m3.registrarReproducao();

        System.out.println("\nOuvinte: " + ana.getNome());
        System.out.println("   segue: " + ana.getSeguindo());
        System.out.println("   playlist \"" + favoritas.getNome() + "\": "
                + favoritas.getMusicas().size() + " músicas, "
                + favoritas.duracaoTotalSegundos() + "s");
        for (Musica m : favoritas.getMusicas()) System.out.println("      • " + m);

        // Prova da diferença composição x agregação -------------------------
        System.out.println("\nA mesma música pode estar no álbum E na playlist:");
        System.out.println("   m1 no álbum?    " + orbita.getFaixas().contains(m1));
        System.out.println("   m1 na playlist? " + favoritas.getMusicas().contains(m1));
        System.out.println("   (a playlist só APONTA; o álbum é DONO das faixas)");
    }
}
