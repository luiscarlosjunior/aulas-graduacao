package usuario;

public class Narrador extends Usuario{
    
    private String estiloNarracao;

    public Narrador(int id, String nome, String email, String estiloNarracao) {
        super(id, nome, email);
        this.estiloNarracao = estiloNarracao;
    }

    public String getEstiloNarracao() {
        return estiloNarracao;
    }

    public void setEstiloNarracao(String estiloNarracao) {
        this.estiloNarracao = estiloNarracao;
    }

    @Override
    public void exibirInfo() {
        super.exibirInfo();
        System.out.println("Estilo de Narração: " + estiloNarracao);
    }

    public void narrar() {
        System.out.println("Narrador " + getNome() + " está narrando com o estilo " + estiloNarracao + ".");
    }

}
