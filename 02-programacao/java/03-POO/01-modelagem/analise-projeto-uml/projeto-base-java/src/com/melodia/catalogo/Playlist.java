package com.melodia.catalogo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Coleção de músicas montada por um ouvinte. É um exemplo de <b>agregação</b>:
 * a playlist "tem" músicas, mas essas músicas <b>continuam existindo</b> no catálogo
 * mesmo que a playlist seja apagada (o todo não é dono da parte). Contraste com a
 * composição de {@code ContaBancaria} e suas {@code Transacao}s.
 */
public class Playlist {

    private String nome;
    private final String dono;
    private final List<Musica> musicas = new ArrayList<>();

    public Playlist(String nome, String dono) {
        this.nome = nome;
        this.dono = dono;
    }

    public void adicionar(Musica m) {
        if (!musicas.contains(m)) {
            musicas.add(m);
        }
    }

    public void remover(Musica m) {
        musicas.remove(m);
    }

    public int duracaoTotalSegundos() {
        return musicas.stream().mapToInt(Musica::getDuracaoSegundos).sum();
    }

    public String getNome()          { return nome; }
    public void setNome(String nome)  { this.nome = nome; }
    public String getDono()          { return dono; }
    public List<Musica> getMusicas() { return Collections.unmodifiableList(musicas); }
}
