package com.melodia.plataforma;

import com.melodia.assinatura.Plano;
import com.melodia.banco.ContaBancaria;
import com.melodia.catalogo.Musica;
import com.melodia.usuario.Artista;
import com.melodia.usuario.Ouvinte;

import java.util.ArrayList;
import java.util.List;

/**
 * Fachada do sistema: coordena catálogo, usuários, assinaturas e pagamentos. É o
 * ponto onde os dois domínios (streaming + banco) se encontram — assinar um plano
 * dispara uma cobrança na {@link ContaBancaria}.
 *
 * <p>No diagrama de componentes, esta classe corresponde ao "ServiçoStreaming"; no de
 * sequência, é o objeto central de "assinar Premium" e "reproduzir música".</p>
 */
public class PlataformaStreaming {

    /** Valor pago ao artista por reprodução (bem simplificado). */
    private static final double ROYALTY_POR_REPRODUCAO = 0.004;

    private final String nome;
    private final ContaBancaria contaDaPlataforma;
    private final List<Ouvinte> ouvintes = new ArrayList<>();
    private final List<Musica> catalogo = new ArrayList<>();

    public PlataformaStreaming(String nome, ContaBancaria contaDaPlataforma) {
        this.nome = nome;
        this.contaDaPlataforma = contaDaPlataforma;
    }

    public void registrar(Ouvinte ouvinte) {
        ouvintes.add(ouvinte);
    }

    public void publicar(Musica musica) {
        catalogo.add(musica);
    }

    public void publicarTodas(List<Musica> musicas) {
        catalogo.addAll(musicas);
    }

    /**
     * Fluxo de "assinar Premium": tenta cobrar na conta do ouvinte e, se der certo,
     * eleva o plano. Retorna false se não houve saldo (assinatura fica suspensa).
     */
    public boolean assinarPremium(Ouvinte ouvinte) {
        ouvinte.getAssinatura().mudarPlano(Plano.PREMIUM);
        boolean pago = ouvinte.getAssinatura().cobrar(ouvinte.getConta());
        if (pago) {
            // a mensalidade paga entra no caixa da plataforma
            ouvinte.getConta(); // débito já ocorreu na conta do ouvinte
            contaDaPlataforma.depositar(Plano.PREMIUM.getPrecoMensal());
        }
        return pago;
    }

    /**
     * Reproduz uma música para um ouvinte, respeitando a regra de negócio:
     * plano FREE não toca conteúdo marcado como exclusivo (aqui, simplificamos:
     * FREE ouve tudo, mas só PREMIUM não recebe "anúncio"). Credita royalty ao artista.
     *
     * @return uma linha de status para exibição.
     */
    public String reproduzir(Ouvinte ouvinte, Musica musica, List<Artista> artistas) {
        if (!catalogo.contains(musica)) {
            return "✗ Música fora do catálogo.";
        }
        boolean semAnuncios = ouvinte.getAssinatura().getPlano().isSemAnuncios()
                && ouvinte.getAssinatura().estaAtiva();

        musica.registrarReproducao();
        creditarRoyalty(musica, artistas);

        String prefixo = semAnuncios ? "▶ " : "▶ (após anúncio) ";
        return prefixo + ouvinte.getNome() + " ouvindo " + musica;
    }

    private void creditarRoyalty(Musica musica, List<Artista> artistas) {
        for (Artista a : artistas) {
            if (a.getNomeArtistico().equals(musica.getArtista())) {
                a.creditarRoyalty(ROYALTY_POR_REPRODUCAO);
            }
        }
    }

    public String getNome()               { return nome; }
    public List<Musica> getCatalogo()     { return catalogo; }
    public ContaBancaria getConta()       { return contaDaPlataforma; }
}
