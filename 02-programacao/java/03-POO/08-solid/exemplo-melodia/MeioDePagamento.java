/* DIP: a abstração de pagamento. O serviço depende disto, não de Pix/Cartao. */
public interface MeioDePagamento {
    boolean cobrar(double valor);
    String nome();
}
