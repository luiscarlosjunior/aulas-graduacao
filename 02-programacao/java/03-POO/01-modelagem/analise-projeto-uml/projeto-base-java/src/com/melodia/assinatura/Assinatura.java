package com.melodia.assinatura;

import com.melodia.banco.ContaBancaria;
import java.time.LocalDate;

/**
 * Assinatura de um ouvinte. É o objeto que implementa a <b>máquina de estados</b>
 * do domínio: as transições ({@link #suspender()}, {@link #reativar()},
 * {@link #cancelar()}) só permitem mudanças válidas — tentar reativar uma assinatura
 * cancelada lança exceção. Codificar a máquina de estados em código (e não só no
 * diagrama) é o que impede estados impossíveis em produção.
 */
public class Assinatura {

    private Plano plano;
    private StatusAssinatura status;
    private final LocalDate inicio;
    private LocalDate proximaCobranca;

    public Assinatura(Plano plano) {
        this.plano = plano;
        this.status = StatusAssinatura.ATIVA;
        this.inicio = LocalDate.now();
        this.proximaCobranca = inicio.plusMonths(1);
    }

    /**
     * Tenta cobrar o valor do plano na conta. Se falhar (saldo insuficiente),
     * a assinatura é <b>suspensa</b> — a regra de negócio vive aqui, junto do estado.
     *
     * @return true se a cobrança foi bem-sucedida.
     */
    public boolean cobrar(ContaBancaria conta) {
        if (status == StatusAssinatura.CANCELADA) {
            throw new IllegalStateException("Assinatura cancelada não pode ser cobrada.");
        }
        if (!plano.isPago()) {
            return true; // plano FREE não gera cobrança
        }
        boolean pago = conta.debitarAssinatura(
                plano.getPrecoMensal(), "Assinatura " + plano);
        if (pago) {
            status = StatusAssinatura.ATIVA;
            proximaCobranca = proximaCobranca.plusMonths(1);
        } else {
            suspender();
        }
        return pago;
    }

    public void suspender() {
        if (status == StatusAssinatura.CANCELADA) {
            throw new IllegalStateException("Não é possível suspender uma assinatura cancelada.");
        }
        status = StatusAssinatura.SUSPENSA;
    }

    public void reativar() {
        if (status != StatusAssinatura.SUSPENSA) {
            throw new IllegalStateException("Só é possível reativar uma assinatura suspensa.");
        }
        status = StatusAssinatura.ATIVA;
    }

    public void cancelar() {
        status = StatusAssinatura.CANCELADA;
    }

    public void mudarPlano(Plano novo) {
        if (status == StatusAssinatura.CANCELADA) {
            throw new IllegalStateException("Reative/assine novamente para trocar de plano.");
        }
        this.plano = novo;
    }

    public boolean estaAtiva()          { return status == StatusAssinatura.ATIVA; }
    public Plano getPlano()             { return plano; }
    public StatusAssinatura getStatus() { return status; }
    public LocalDate getProximaCobranca() { return proximaCobranca; }
}
