package usuario;

public class Apresentador extends Usuario {
    
    private String programa;

    public Apresentador(int id, String nome, String email, String programa) {
        super(id, nome, email);
        this.programa = programa;
    }

    public String getPrograma() {
        return programa;
    }

    public void setPrograma(String programa) {
        this.programa = programa;
    }

    @Override
    public void exibirInfo() {
        super.exibirInfo();
        System.out.println("Programa: " + programa);
    }

    public void apresentar() {
        System.out.println("Apresentador " + getNome() + " está apresentando o programa " + programa + ".");
    }

}
