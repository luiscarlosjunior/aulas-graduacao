/* Dados do plano de assinatura (SRP: só guarda dados do plano). */
public class Plano {
    private final String nome;
    private final double precoMensal;
    public Plano(String nome, double precoMensal) { this.nome = nome; this.precoMensal = precoMensal; }
    public String getNome() { return nome; }
    public double getPrecoMensal() { return precoMensal; }
}
