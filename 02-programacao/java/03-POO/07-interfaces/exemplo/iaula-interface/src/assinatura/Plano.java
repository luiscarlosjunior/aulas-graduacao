package assinatura;

/* Plano de assinatura do Melodia (ex.: Free, Premium, Universitário). */
public class Plano {
    private final String nome;
    private final double precoMensal;

    public Plano(String nome, double precoMensal) {
        this.nome = nome;
        this.precoMensal = precoMensal;
    }

    public String getNome() { return nome; }
    public double getPrecoMensal() { return precoMensal; }
}
