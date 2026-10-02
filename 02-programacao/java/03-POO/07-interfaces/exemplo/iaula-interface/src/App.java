import audio.*;
import assinatura.*;
import contrato.pagamento.MeioDePagamento;
import forma.pagamento.*;

import java.util.List;

/*
 * Exemplo COMPLETO da aula de Interfaces — domínio: streaming de música "Melodia".
 * Mostra os dois usos de interface vistos no README:
 *   (1) FonteDeAudio  -> a Playlist aceita Musica E Podcast pelo mesmo contrato;
 *   (2) MeioDePagamento (+ Autenticavel/Reembolsavel) -> a Assinatura cobra por
 *       qualquer meio, sem conhecer as classes concretas.
 */
public class App {
    public static void main(String[] args) {
        System.out.println("=== 1) Interface FonteDeAudio: a Playlist mistura tipos ===");
        Playlist favoritas = new Playlist("Favoritas");
        favoritas.adicionar(new Musica("Garota de Ipanema", 200, "Tom Jobim"));
        favoritas.adicionar(new Podcast("Bastidores da Melodia", 1620, "Ana Host"));
        favoritas.tocarTudo();
        System.out.println("Duração total: " + favoritas.duracaoTotalSegundos() / 60 + " min");

        System.out.println("\n=== 2) Módulo de pagamento: a Assinatura depende do CONTRATO ===");
        Plano premium = new Plano("Premium", 19.90);
        // A MESMA Assinatura funciona com meios diferentes (polimorfismo + desacoplamento).
        List<MeioDePagamento> meios = List.of(
                new Cartao("1234567890123456"),   // cobra + autentica + reembolsa
                new Pix("ana@melodia.com"),        // cobra + reembolsa
                new Boleto()                       // só cobra
        );
        for (MeioDePagamento meio : meios) {
            System.out.println("\n-- Ativando via " + meio.nome() + " --");
            Assinatura a = new Assinatura(premium, meio);
            a.ativar("token-seguro-123");          // autentica só se o meio for Autenticavel
        }

        System.out.println("\n=== 3) Capacidade OPCIONAL: cancelar com (ou sem) reembolso ===");
        Assinatura comPix = new Assinatura(premium, new Pix("ana@melodia.com"));
        comPix.ativar("x");
        comPix.cancelar();     // Pix é Reembolsavel -> devolve o dinheiro

        Assinatura comBoleto = new Assinatura(premium, new Boleto());
        comBoleto.ativar("x");
        comBoleto.cancelar();  // Boleto NÃO é Reembolsavel -> apenas avisa

        System.out.println("\n>> Para adicionar PayPal ou Cripto: crie a classe e use. A Assinatura NÃO muda.");
    }
}
