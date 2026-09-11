public class Artista extends Usuario {
    public Artista(String nome) {
        super(nome);
    }

    @Override
    public String beneficios() {
        return "publicar álbuns e receber royalties";
    }
}
