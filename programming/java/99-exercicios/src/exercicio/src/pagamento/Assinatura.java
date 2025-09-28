package pagamento;

public class Assinatura {
    private String tipo;
    private double preco;

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public void renovar() {
        // Add implementation here
    }

    public void exibir() {
        System.out.println("Tipo de Assinatura: " + tipo);
        System.out.println("Preço: R$ " + preco);
    }
}
