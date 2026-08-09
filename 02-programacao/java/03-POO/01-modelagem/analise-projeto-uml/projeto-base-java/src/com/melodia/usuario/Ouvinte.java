package com.melodia.usuario;

import com.melodia.assinatura.Assinatura;
import com.melodia.assinatura.Plano;
import com.melodia.banco.ContaBancaria;
import com.melodia.catalogo.Playlist;
import java.util.ArrayList;
import java.util.List;

/**
 * Quem escuta. Um Ouvinte "tem uma" {@link Assinatura} (composição — a assinatura
 * é criada e vive dentro do ouvinte) e "tem muitas" {@link Playlist}s.
 *
 * <p>Começa no plano FREE. A troca para PREMIUM é intermediada pela plataforma,
 * que dispara a cobrança na conta (veja o diagrama de sequência).</p>
 */
public class Ouvinte extends Usuario {

    private Assinatura assinatura;
    private final List<Playlist> playlists = new ArrayList<>();

    public Ouvinte(String nome, String email, ContaBancaria conta) {
        super(nome, email, conta);
        this.assinatura = new Assinatura(Plano.FREE);
    }

    @Override
    public String tipoDePerfil() {
        return "Ouvinte";
    }

    public Playlist criarPlaylist(String nome) {
        Playlist p = new Playlist(nome, this.nome);
        playlists.add(p);
        return p;
    }

    public Assinatura getAssinatura()   { return assinatura; }
    public List<Playlist> getPlaylists() { return playlists; }
}
