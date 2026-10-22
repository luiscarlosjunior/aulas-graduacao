public class DescontoAnual implements Desconto {
    public double aplicar(double preco) { return preco * 0.8; }   // 20%
    public String descricao() { return "anual -20%"; }
}
