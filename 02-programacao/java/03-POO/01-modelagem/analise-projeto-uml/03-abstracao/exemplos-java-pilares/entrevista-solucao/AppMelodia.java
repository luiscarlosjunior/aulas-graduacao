import java.util.List;

/*
 * AppMelodia — a aplicação inteira rodando: monta o cenário da entrevista e
 * exercita os 4 pilares + os 3 relacionamentos.
 */
public class AppMelodia {
    public static void main(String[] args) {
        // --- Artista publica músicas (associação Artista --> Musica) ---
        Artista jobim = new Artista("Tom Jobim", "tom@melodia.com", "1234", "Tom Jobim");
        Musica m1 = jobim.publicar("Garota de Ipanema", 200);
        jobim.publicar("Wave", 180);
        Podcast p1 = new Podcast("Bastidores da Melodia", 1620, "Ana Host");

        // --- Ouvinte: segue artista, cria playlist, mistura música e podcast ---
        Ouvinte ana = new Ouvinte("Ana Souza", "ana@melodia.com", "abcd");
        ana.seguir(jobim);
        Playlist favoritas = ana.criarPlaylist("Favoritas");
        favoritas.adicionar(m1); // Musica
        favoritas.adicionar(p1); // Podcast (mesma lista: polimorfismo)

        // --- Encapsulamento: reproduções só sobem pela operação ---
        m1.registrarReproducao();
        m1.registrarReproducao();

        // --- Encapsulamento: senha protegida ---
        System.out.println("Autentica com senha errada? " + ana.autenticar("xxxx"));
        ana.alterarSenha("abcd", "novaSenha");
        System.out.println("Autentica com a nova senha?  " + ana.autenticar("novaSenha"));

        // --- Polimorfismo: mesma pergunta, resposta por tipo ---
        System.out.println("\nBenefícios por tipo de usuário:");
        for (Usuario u : List.of(ana, jobim))
            System.out.println("  " + u.getNome() + " -> " + u.beneficios());

        // --- Agregação: a playlist trata tudo pelo tipo base ---
        System.out.println("\nPlaylist '" + favoritas.getNome() + "' ("
                + favoritas.duracaoTotal() / 60 + " min):");
        for (ConteudoDeAudio c : favoritas.getItens())
            System.out.println("  - [" + c.descricaoCurta() + "] " + c.getTitulo());

        // --- Composição: a assinatura vive dentro do ouvinte ---
        System.out.println("\nAssinatura da Ana ativa? " + ana.getAssinatura().estaAtiva()
                + " (início: " + ana.getAssinatura().getDataInicio() + ")");
        System.out.println(m1.getTitulo() + " -> " + m1.getReproducoes() + " reproduções");
    }
}
