package com.melodia.banco;

/**
 * Tipos possíveis de movimentação em uma {@link ContaBancaria}.
 *
 * <p>Modelado como enum porque o conjunto de valores é <b>fechado e conhecido</b>:
 * não faz sentido um "tipo de transação" inventado em tempo de execução. Isso dá
 * segurança de tipo (o compilador barra valores inválidos) — muito melhor que usar
 * uma {@code String} "solta", um erro clássico em código de produção.</p>
 */
public enum TipoTransacao {
    DEPOSITO,
    SAQUE,
    TRANSFERENCIA_ENVIADA,
    TRANSFERENCIA_RECEBIDA,
    PAGAMENTO_ASSINATURA
}
