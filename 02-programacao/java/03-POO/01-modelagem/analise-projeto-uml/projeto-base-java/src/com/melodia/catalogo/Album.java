package com.melodia.catalogo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Um álbum é uma <b>composição</b> de faixas: as músicas do álbum são criadas como
 * parte dele e não fazem sentido "soltas" fora de um álbum/single. Contraste direto
 * com {@link Playlist}, que apenas <b>agrega</b> músicas já existentes.
 *
 * <p>Esse par (Album=composição, Playlist=agregação) sobre o <i>mesmo</i> tipo Musica
 * é o melhor exemplo didático da diferença entre os dois relacionamentos.</p>
 */
public class Album {

    private final String titulo;
    private final String artista;
    private final int ano;
    private final List<Musica> faixas = new ArrayList<>();

    public Album(String titulo, String artista, int ano) {
        this.titulo = titulo;
        this.artista = artista;
        this.ano = ano;
    }

    /** Cria a faixa dentro do álbum (o álbum é dono da parte). */
    public Musica adicionarFaixa(String tituloFaixa, int duracaoSegundos) {
        Musica m = new Musica(tituloFaixa, artista, duracaoSegundos);
        faixas.add(m);
        return m;
    }

    public String getTitulo()       { return titulo; }
    public String getArtista()      { return artista; }
    public int getAno()             { return ano; }
    public List<Musica> getFaixas() { return Collections.unmodifiableList(faixas); }
}
