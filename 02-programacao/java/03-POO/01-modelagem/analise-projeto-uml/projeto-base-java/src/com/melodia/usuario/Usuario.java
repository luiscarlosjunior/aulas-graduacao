package com.melodia.usuario;

import com.melodia.banco.ContaBancaria;

/**
 * Superclasse abstrata de todos os usuários da plataforma. Exemplo de <b>abstração
 * como generalização</b>: reunimos o que Ouvinte e Artista têm em comum (nome, e-mail,
 * conta) e deixamos o específico para as subclasses.
 *
 * <p>É {@code abstract} porque "usuário" genérico não existe na prática — todo usuário
 * é um Ouvinte ou um Artista. O método {@link #tipoDePerfil()} é abstrato: cada
 * subclasse dá sua própria resposta (<b>polimorfismo</b>).</p>
 */
public abstract class Usuario {

    protected final String nome;
    protected final String email;
    protected final ContaBancaria conta;

    protected Usuario(String nome, String email, ContaBancaria conta) {
        this.nome = nome;
        this.email = email;
        this.conta = conta;
    }

    /** Cada subclasse descreve seu papel. Ligação dinâmica em ação. */
    public abstract String tipoDePerfil();

    public String getNome()        { return nome; }
    public String getEmail()       { return email; }
    public ContaBancaria getConta() { return conta; }

    @Override
    public String toString() {
        return tipoDePerfil() + " " + nome + " <" + email + ">";
    }
}
