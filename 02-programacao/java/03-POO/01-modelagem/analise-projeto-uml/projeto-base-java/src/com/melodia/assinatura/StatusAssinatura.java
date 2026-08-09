package com.melodia.assinatura;

/**
 * Estados possíveis de uma {@link Assinatura}. Corresponde <b>1 para 1</b> ao
 * diagrama de máquina de estados (ver {@code ../../14-diagrama-de-maquina-de-estados}).
 *
 * <pre>
 *   ATIVA  --(falha no pagamento)-->  SUSPENSA
 *   SUSPENSA  --(pagamento ok)-->     ATIVA
 *   ATIVA/SUSPENSA  --(cancelar)-->   CANCELADA  (estado final)
 * </pre>
 */
public enum StatusAssinatura {
    ATIVA,
    SUSPENSA,
    CANCELADA
}
