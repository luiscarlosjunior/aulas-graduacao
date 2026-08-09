package com.melodia.catalogo;

/**
 * Uma faixa do catálogo. Repare que {@code Musica} guarda apenas uma referência ao
 * <b>nome</b> do artista (String), e não ao objeto Artista, para manter o catálogo
 * simples neste exemplo didático. Num sistema real haveria uma associação
 * {@code Musica --> Artista} (veja o diagrama de classes).
 */
public class Musica {

    private final String titulo;
    private final String artista;
    private final int duracaoSegundos;
    private int reproducoes;

    public Musica(String titulo, String artista, int duracaoSegundos) {
        this.titulo = titulo;
        this.artista = artista;
        this.duracaoSegundos = duracaoSegundos;
    }

    /** Chamado a cada play; alimenta métricas e royalties. */
    public void registrarReproducao() {
        reproducoes++;
    }

    public String getTitulo()       { return titulo; }
    public String getArtista()      { return artista; }
    public int getDuracaoSegundos() { return duracaoSegundos; }
    public int getReproducoes()     { return reproducoes; }

    public String duracaoFormatada() {
        return String.format("%d:%02d", duracaoSegundos / 60, duracaoSegundos % 60);
    }

    @Override
    public String toString() {
        return "\"" + titulo + "\" — " + artista + " (" + duracaoFormatada() + ")";
    }
}
