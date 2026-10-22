public class DescontoEstudante implements Desconto {
    public double aplicar(double preco) { return preco * 0.5; }   // 50%
    public String descricao() { return "estudante -50%"; }
}
