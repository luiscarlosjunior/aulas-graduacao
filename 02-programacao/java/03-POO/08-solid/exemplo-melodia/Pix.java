public class Pix implements MeioDePagamento {
    private final String chave;
    public Pix(String chave) { this.chave = chave; }
    public String nome() { return "Pix"; }
    public boolean cobrar(double valor) { System.out.printf("  [Pix %s] cobrando R$ %.2f%n", chave, valor); return true; }
}
