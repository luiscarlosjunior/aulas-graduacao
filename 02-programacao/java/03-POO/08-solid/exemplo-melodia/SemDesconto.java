public class SemDesconto implements Desconto {
    public double aplicar(double preco) { return preco; }
    public String descricao() { return "sem desconto"; }
}
