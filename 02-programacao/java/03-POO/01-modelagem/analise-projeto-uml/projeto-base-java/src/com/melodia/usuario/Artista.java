package com.melodia.usuario;

import com.melodia.banco.ContaBancaria;

/**
 * Quem publica músicas e recebe royalties. Outra especialização de {@link Usuario}.
 * Sobrescreve {@link #tipoDePerfil()} de forma diferente do Ouvinte — a mesma chamada
 * {@code usuario.tipoDePerfil()} responde "Artista" ou "Ouvinte" conforme o objeto real
 * (ligação dinâmica / polimorfismo de subtipo).
 */
public class Artista extends Usuario {

    private final String nomeArtistico;
    private double royaltiesAcumulados;

    public Artista(String nome, String email, ContaBancaria conta, String nomeArtistico) {
        super(nome, email, conta);
        this.nomeArtistico = nomeArtistico;
    }

    @Override
    public String tipoDePerfil() {
        return "Artista";
    }

    /** A cada reprodução, o artista acumula um valor a receber. */
    public void creditarRoyalty(double valorPorReproducao) {
        royaltiesAcumulados += valorPorReproducao;
    }

    /** Transfere os royalties acumulados para a conta do artista e zera o acumulado. */
    public double sacarRoyalties(ContaBancaria contaPlataforma) {
        double valor = royaltiesAcumulados;
        if (valor > 0) {
            contaPlataforma.transferirPara(this.conta, valor);
            royaltiesAcumulados = 0;
        }
        return valor;
    }

    public String getNomeArtistico()     { return nomeArtistico; }
    public double getRoyaltiesAcumulados() { return royaltiesAcumulados; }
}
