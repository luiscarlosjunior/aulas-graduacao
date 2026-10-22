/* OCP: contrato de desconto. Um desconto novo é uma CLASSE nova, não um if novo. */
public interface Desconto {
    double aplicar(double preco);
    String descricao();
}
