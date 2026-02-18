package usuario;

public class UsuarioGratuito extends Usuario {

    private boolean anuncio;
    
    public UsuarioGratuito(int id, String nome, String email, boolean anuncio) {
        super(id, nome, email); // Call the appropriate constructor of Usuario
        this.anuncio = anuncio;
    }

    public boolean isAnuncio() {
        return anuncio;
    }

    public void setAnuncio(boolean anuncio) {
        this.anuncio = anuncio;
    }

    @Override
    public void exibirInfo() {
        System.out.println("Informações do Usuário Gratuito:");
        System.out.println("Anúncio ativo: " + anuncio);
    }

    public void ouvirAnuncio() {
        System.out.println("Ouvindo anúncio...");
    }
}
