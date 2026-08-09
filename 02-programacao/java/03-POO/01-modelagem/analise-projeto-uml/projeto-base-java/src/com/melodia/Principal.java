package com.melodia;

import com.melodia.assinatura.StatusAssinatura;
import com.melodia.banco.ContaBancaria;
import com.melodia.banco.Transacao;
import com.melodia.catalogo.Album;
import com.melodia.catalogo.Musica;
import com.melodia.catalogo.Playlist;
import com.melodia.plataforma.PlataformaStreaming;
import com.melodia.usuario.Artista;
import com.melodia.usuario.Ouvinte;

import java.util.List;

/**
 * Cenário executável do domínio-base "Melodia" (streaming + banco).
 *
 * <p>Este é o MESMO sistema modelado nos diagramas UML da pasta-pai. Rode e compare:
 * cada passo abaixo aparece em algum diagrama (casos de uso, sequência, estados...).</p>
 *
 * <pre>
 *   cd projeto-base-java
 *   javac -d out $(find src -name "*.java")
 *   java -cp out com.melodia.Principal
 * </pre>
 */
public class Principal {

    public static void main(String[] args) {
        titulo("MELODIA — Sistema de Streaming de Música (domínio-base)");

        // 1) BANCO: contas dos envolvidos ------------------------------------
        ContaBancaria contaAna    = new ContaBancaria("0001-1", "Ana Souza", 50.00);
        ContaBancaria contaLucas  = new ContaBancaria("0002-2", "Lucas Dias", 5.00);
        ContaBancaria contaBanda  = new ContaBancaria("0003-3", "Banda Nébula", 0.00);
        ContaBancaria contaMelodia = new ContaBancaria("9999-9", "Melodia S.A.", 0.00);

        // 2) USUÁRIOS: herança em ação (Ouvinte e Artista são Usuarios) -------
        Ouvinte ana   = new Ouvinte("Ana Souza", "ana@email.com", contaAna);
        Ouvinte lucas = new Ouvinte("Lucas Dias", "lucas@email.com", contaLucas);
        Artista nebula = new Artista("N. Ébula", "nebula@email.com", contaBanda, "Banda Nébula");

        PlataformaStreaming melodia = new PlataformaStreaming("Melodia", contaMelodia);
        melodia.registrar(ana);
        melodia.registrar(lucas);

        // 3) CATÁLOGO: composição (Album -> faixas) --------------------------
        Album album = new Album("Órbita", "Banda Nébula", 2025);
        Musica m1 = album.adicionarFaixa("Gravidade Zero", 213);
        Musica m2 = album.adicionarFaixa("Cometa", 187);
        Musica m3 = album.adicionarFaixa("Silêncio Sideral", 245);
        melodia.publicarTodas(album.getFaixas());

        System.out.println("Catálogo publicado: " + melodia.getCatalogo().size() + " faixas");
        System.out.println("Perfis (polimorfismo): " + ana + " | " + nebula);

        // 4) ASSINATURA + PAGAMENTO: os dois domínios se encontram -----------
        titulo("Assinatura Premium (streaming + banco)");
        boolean anaOk   = melodia.assinarPremium(ana);
        boolean lucasOk = melodia.assinarPremium(lucas); // saldo insuficiente -> suspende

        System.out.printf("Ana assinou Premium?   %s | plano=%s status=%s saldo=R$ %.2f%n",
                anaOk, ana.getAssinatura().getPlano(),
                ana.getAssinatura().getStatus(), contaAna.getSaldo());
        System.out.printf("Lucas assinou Premium? %s | plano=%s status=%s saldo=R$ %.2f%n",
                lucasOk, lucas.getAssinatura().getPlano(),
                lucas.getAssinatura().getStatus(), contaLucas.getSaldo());

        // 5) MÁQUINA DE ESTADOS: reativar assinatura suspensa ----------------
        if (lucas.getAssinatura().getStatus() == StatusAssinatura.SUSPENSA) {
            contaLucas.depositar(40.00);                 // repôs saldo
            lucas.getAssinatura().reativar();            // SUSPENSA -> ATIVA
            melodia.assinarPremium(lucas);               // cobra de novo
            System.out.printf("Após repor saldo: Lucas status=%s saldo=R$ %.2f%n",
                    lucas.getAssinatura().getStatus(), contaLucas.getSaldo());
        }

        // 6) REPRODUÇÃO: agregação (Playlist) + royalties --------------------
        titulo("Reprodução e Playlists");
        Playlist favoritas = ana.criarPlaylist("Favoritas");
        favoritas.adicionar(m1);
        favoritas.adicionar(m3);

        List<Artista> artistas = List.of(nebula);
        System.out.println(melodia.reproduzir(ana, m1, artistas));   // Premium: sem anúncio
        System.out.println(melodia.reproduzir(lucas, m2, artistas)); // reproduz
        System.out.println(melodia.reproduzir(ana, m3, artistas));

        System.out.printf("Playlist \"%s\": %d músicas, %d s no total%n",
                favoritas.getNome(), favoritas.getMusicas().size(),
                favoritas.duracaoTotalSegundos());

        // 7) ROYALTIES: transferência entre contas ---------------------------
        titulo("Royalties do artista");
        double sacado = nebula.sacarRoyalties(contaMelodia);
        System.out.printf("%s sacou R$ %.4f em royalties -> saldo da banda R$ %.4f%n",
                nebula.getNomeArtistico(), sacado, contaBanda.getSaldo());

        // 8) EXTRATO: composição imutável da conta ---------------------------
        titulo("Extrato da conta de Ana");
        for (Transacao t : contaAna.getExtrato()) {
            System.out.println("  " + t);
        }
    }

    private static void titulo(String s) {
        System.out.println("\n=== " + s + " ===");
    }
}
