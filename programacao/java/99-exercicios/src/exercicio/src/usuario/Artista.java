package usuario;

public class Artista extends Usuario {
    private String generoMusical;

    public Artista(int id, String nome, String email, String generoMusical) {
        super(id, nome, email);        
        this.generoMusical = generoMusical;
    }

    public String getGeneroMusical() {
        return generoMusical;
    }

    public void setGeneroMusical(String generoMusical) {
        this.generoMusical = generoMusical;
    }

    @Override
    public void exibirInfo() {
        super.exibirInfo();
        System.out.println("Nome Artístico: " + getNome());
        System.out.println("Gênero Musical: " + generoMusical);
    }

    public void publicarConteudo() {
        // Lógica para publicar conteúdo
        System.out.println("Artista " + getNome() + " publicou novo conteúdo.");
    }
}
