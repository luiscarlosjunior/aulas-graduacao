import java.util.List;

/*
 *  DemoAbstracao — o "main" que mostra a abstração trabalhando.
 *
 *  Ponto-chave: a `biblioteca` é uma List<ConteudoDeAudio>. O código abaixo
 *  trata músicas, podcasts e audiolivros PELO TIPO GENÉRICO (a abstração),
 *  sem saber — nem precisar saber — qual é o tipo concreto de cada item.
 *  Isso é abstração + polimorfismo trabalhando juntos.
 */
public class DemoAbstracao {
    public static void main(String[] args) {

        // Guardamos tudo como ConteudoDeAudio: a abstração é o "denominador comum".
        List<ConteudoDeAudio> biblioteca = List.of(
                new Musica("Garota de Ipanema", 200, "Tom Jobim"),
                new Podcast("Como funciona a Melodia", 1620, 12),
                new Audiolivro("Dom Casmurro", 34200, "Machado de Assis", 148)
        );

        System.out.println("=== Biblioteca da Melodia (via abstração) ===");
        for (ConteudoDeAudio item : biblioteca) {
            // Chamamos SÓ o que o contrato ConteudoDeAudio promete.
            // Não há nenhum "if tipo == ...": cada objeto se descreve sozinho.
            System.out.println("  " + item.fichaTecnica());
        }

        // Duração total: de novo, tratamos o essencial comum, ignorando o resto.
        int total = biblioteca.stream().mapToInt(ConteudoDeAudio::getDuracaoSegundos).sum();
        System.out.println("Duração total do acervo: " + (total / 60) + " min");
    }
}
