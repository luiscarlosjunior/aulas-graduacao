public class Main {
    public static void main(String[] args) {
        Usuario usuario = new Usuario("Luis", "luis@email.com");
        Musica musica = new Musica("Imagine", "John Lennon", 183);

        usuario.ouvirMusica(musica, "celular", false);
        usuario.ouvirMusica(musica, "computador", true);

        usuario.exibirHistorico();
    }
}