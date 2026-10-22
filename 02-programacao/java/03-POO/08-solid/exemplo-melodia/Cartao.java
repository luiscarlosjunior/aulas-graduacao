public class Cartao implements MeioDePagamento {
    private final String numero;
    public Cartao(String numero) { this.numero = numero; }
    public String nome() { return "Cartão"; }
    public boolean cobrar(double valor) { System.out.printf("  [Cartão ****%s] cobrando R$ %.2f%n", numero, valor); return true; }
}
