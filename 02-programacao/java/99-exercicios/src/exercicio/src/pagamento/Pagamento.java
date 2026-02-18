package pagamento;

public class Pagamento {
    private String metodo;
    private double valor;

    public Pagamento(String metodo, double valor) {
        this.metodo = metodo;
        this.valor = valor;
    }

    public String getMetodoPagamento() {
        return metodo;
    }

    public void setMetodoPagamento(String metodoPagamento) {
        this.metodo = metodoPagamento;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public void processar() {
        System.out.println("Processando pagamento de " + valor + " usando " + metodo);
    }
}