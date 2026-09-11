public class Ouvinte extends Usuario {
    public Ouvinte(String nome) {
        super(nome);
    }

    // Mesma mensagem (beneficios), resposta própria do ouvinte.
    @Override
    public String beneficios() {
        return "streaming ilimitado e playlists";
    }
}
