package usuario;

public class UsuarioPremium extends Usuario {
    private double valorMensal;

    public UsuarioPremium(int id, String nome, String email, double valorMensal) {
        super(id, nome, email); // Chama o construtor da classe base Usuario
        this.valorMensal = valorMensal;
    }

    @Override
    public void exibirInfo() {
        System.out.println("Nome: " + getNome());
        System.out.println("Email: " + getEmail());
        System.out.println("Valor Mensal: " + valorMensal);
    }

    public void baixar(String conteudo) {
        System.out.println("Baixando conteúdo: " + conteudo);
    }

    public double getValorMensal() {
        return valorMensal;
    }

    public void setValorMensal(double valorMensal) {
        this.valorMensal = valorMensal;
    }
}
