package com.melodia.banco;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Conta/carteira usada para pagar a assinatura do streaming.
 *
 * <p>Exemplo canônico de <b>encapsulamento</b>: o {@code saldo} é {@code private} e
 * <b>nunca</b> é alterado diretamente de fora. Toda mudança passa por uma operação
 * ({@link #depositar}, {@link #sacar}, {@link #transferirPara}) que <b>valida</b> a regra
 * de negócio e <b>registra</b> a {@link Transacao}. É assim que se protege um invariante
 * ("o saldo nunca fica negativo") — impossível de garantir com um campo público.</p>
 *
 * <p>A lista de transações é uma <b>composição</b>: as transações nascem e morrem com a
 * conta, e nunca são expostas de forma mutável (retornamos uma cópia read-only).</p>
 */
public class ContaBancaria {

    private final String numero;
    private final String titular;
    private double saldo;
    private final List<Transacao> extrato = new ArrayList<>();

    public ContaBancaria(String numero, String titular, double saldoInicial) {
        if (saldoInicial < 0) {
            throw new IllegalArgumentException("Saldo inicial não pode ser negativo.");
        }
        this.numero = numero;
        this.titular = titular;
        this.saldo = saldoInicial;
    }

    /** Adiciona fundos. Retorna o novo saldo. */
    public double depositar(double valor) {
        exigirValorPositivo(valor);
        saldo += valor;
        extrato.add(new Transacao(TipoTransacao.DEPOSITO, valor, "Depósito"));
        return saldo;
    }

    /** Retira fundos, respeitando o invariante de saldo não-negativo. */
    public double sacar(double valor) {
        exigirValorPositivo(valor);
        exigirSaldoSuficiente(valor);
        saldo -= valor;
        extrato.add(new Transacao(TipoTransacao.SAQUE, valor, "Saque"));
        return saldo;
    }

    /** Transfere para outra conta de forma atômica do ponto de vista das duas contas. */
    public void transferirPara(ContaBancaria destino, double valor) {
        exigirValorPositivo(valor);
        exigirSaldoSuficiente(valor);
        this.saldo -= valor;
        destino.saldo += valor;
        this.extrato.add(new Transacao(
                TipoTransacao.TRANSFERENCIA_ENVIADA, valor, "Para " + destino.numero));
        destino.extrato.add(new Transacao(
                TipoTransacao.TRANSFERENCIA_RECEBIDA, valor, "De " + this.numero));
    }

    /**
     * Débito específico da cobrança da assinatura. Separado de {@link #sacar} de propósito:
     * o <i>tipo</i> da transação conta a intenção de negócio (útil para relatórios/auditoria).
     */
    public boolean debitarAssinatura(double valor, String descricao) {
        exigirValorPositivo(valor);
        if (saldo < valor) {
            return false; // sem crédito: a plataforma decide suspender a assinatura
        }
        saldo -= valor;
        extrato.add(new Transacao(TipoTransacao.PAGAMENTO_ASSINATURA, valor, descricao));
        return true;
    }

    public double getSaldo()   { return saldo; }
    public String getNumero()  { return numero; }
    public String getTitular() { return titular; }

    /** Lista read-only: ninguém de fora consegue adulterar o histórico. */
    public List<Transacao> getExtrato() {
        return Collections.unmodifiableList(extrato);
    }

    // ---- validações privadas (uso interno) --------------------------------
    private void exigirValorPositivo(double valor) {
        if (valor <= 0) {
            throw new IllegalArgumentException("O valor deve ser positivo.");
        }
    }

    private void exigirSaldoSuficiente(double valor) {
        if (valor > saldo) {
            throw new IllegalStateException(
                    "Saldo insuficiente: saldo=" + saldo + ", solicitado=" + valor);
        }
    }
}
