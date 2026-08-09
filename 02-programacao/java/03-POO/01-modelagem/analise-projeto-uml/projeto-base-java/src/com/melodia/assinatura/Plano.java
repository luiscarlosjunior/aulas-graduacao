package com.melodia.assinatura;

/**
 * Planos oferecidos pela plataforma. Enum <b>com atributo</b> ({@code precoMensal}):
 * cada constante carrega seu próprio dado, um recurso poderoso de enums em Java que
 * evita espalhar {@code if (plano == PREMIUM) preco = 19.90} pelo código.
 */
public enum Plano {
    FREE(0.0, false),
    PREMIUM(19.90, true),
    FAMILIA(34.90, true);

    private final double precoMensal;
    private final boolean semAnuncios;

    Plano(double precoMensal, boolean semAnuncios) {
        this.precoMensal = precoMensal;
        this.semAnuncios = semAnuncios;
    }

    public double getPrecoMensal() { return precoMensal; }
    public boolean isSemAnuncios() { return semAnuncios; }
    public boolean isPago()        { return precoMensal > 0; }
}
