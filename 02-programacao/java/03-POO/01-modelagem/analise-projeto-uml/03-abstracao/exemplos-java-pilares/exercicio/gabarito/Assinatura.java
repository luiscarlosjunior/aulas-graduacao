/*
 * Assinatura — ENCAPSULAMENTO: o estado "ativa" só muda por operações.
 * Guarda uma referência a Plano (ASSOCIAÇÃO: o plano existe por si só).
 */
public class Assinatura {
    private final Plano plano;                 // associação --> Plano
    private boolean ativa;
    private final int diaDeVencimento;         // invariante: 1..28

    public Assinatura(Plano plano, int diaDeVencimento) {
        if (plano == null)
            throw new IllegalArgumentException("Assinatura precisa de um plano.");
        if (diaDeVencimento < 1 || diaDeVencimento > 28)
            throw new IllegalArgumentException("Dia de vencimento deve estar entre 1 e 28.");
        this.plano = plano;
        this.diaDeVencimento = diaDeVencimento;
        this.ativa = true;                     // nasce ativa
    }

    public void ativar()   { ativa = true; }
    public void cancelar() { ativa = false; }
    public boolean estaAtiva() { return ativa; }

    public Plano getPlano() { return plano; }
    public int getDiaDeVencimento() { return diaDeVencimento; }

    // POLIMORFISMO: usa o preço do plano concreto; 0 se cancelada.
    public double valorAPagar() { return ativa ? plano.precoMensal() : 0.0; }
}
