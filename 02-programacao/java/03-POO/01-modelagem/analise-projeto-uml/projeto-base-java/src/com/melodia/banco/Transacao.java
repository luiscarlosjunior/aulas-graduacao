package com.melodia.banco;

import java.time.LocalDateTime;

/**
 * Um lançamento (extrato) imutável na conta. É um <b>objeto de valor</b>:
 * depois de criado, não muda. Por isso todos os campos são {@code final} e não
 * há setters — uma vez registrada, uma transação é história.
 *
 * <p>Imutabilidade é uma prática forte na indústria (facilita concorrência,
 * evita bugs de estado compartilhado). No diagrama de classes, esta classe é uma
 * <b>parte</b> da {@code ContaBancaria} (composição: a transação não existe sem a conta).</p>
 */
public final class Transacao {

    private final TipoTransacao tipo;
    private final double valor;
    private final LocalDateTime momento;
    private final String descricao;

    public Transacao(TipoTransacao tipo, double valor, String descricao) {
        this.tipo = tipo;
        this.valor = valor;
        this.descricao = descricao;
        this.momento = LocalDateTime.now();
    }

    public TipoTransacao getTipo()      { return tipo; }
    public double getValor()            { return valor; }
    public LocalDateTime getMomento()   { return momento; }
    public String getDescricao()        { return descricao; }

    @Override
    public String toString() {
        return String.format("[%s] R$ %8.2f  %-24s (%s)",
                momento.toLocalDate(), valor, tipo, descricao);
    }
}
