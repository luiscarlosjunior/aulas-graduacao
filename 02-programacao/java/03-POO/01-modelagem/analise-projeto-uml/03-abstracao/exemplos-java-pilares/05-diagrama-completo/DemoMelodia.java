import java.util.List;

/*
 * DemoMelodia — o diagrama COMPLETO em ação: os 4 pilares + associações.
 */
public class DemoMelodia {
    public static void main(String[] args) {
        // Artista publica músicas (associação Artista --> Musica)
        Artista jobim = new Artista("Tom Jobim", "tom@melodia.com", "Tom Jobim");
        Musica m1 = jobim.publicar("Garota de Ipanema", 200);
        jobim.publicar("Wave", 180);

        Podcast p1 = new Podcast("Como funciona a Melodia", 1620, 12);

        // Ouvinte cria playlist e mistura Musica + Podcast (POLIMORFISMO via ConteudoDeAudio)
        Ouvinte ana = new Ouvinte("Ana Souza", "ana@melodia.com", "Premium");
        Playlist favoritas = ana.criarPlaylist("Favoritas");
        favoritas.adicionar(m1);   // Musica
        favoritas.adicionar(p1);   // Podcast

        // Encapsulamento: reproducoes só cresce pela operação
        m1.registrarReproducao();
        m1.registrarReproducao();

        System.out.println("Playlist '" + favoritas.getNome() + "' ("
                + favoritas.duracaoTotalSegundos() / 60 + " min):");
        for (ConteudoDeAudio c : favoritas.getItens())   // trata o tipo genérico
            System.out.println("  - [" + c.descricaoCurta() + "] " + c.getTitulo()
                    + " (" + c.duracaoFormatada() + ")");

        System.out.println("\nBenefícios por tipo de usuário (polimorfismo):");
        for (Usuario u : List.of(ana, jobim))            // mesma chamada, respostas diferentes
            System.out.println("  " + u.getNome() + ": " + u.beneficios());

        System.out.println("\n" + m1.getTitulo() + " -> " + m1.getReproducoes() + " reproduções");
    }
}
