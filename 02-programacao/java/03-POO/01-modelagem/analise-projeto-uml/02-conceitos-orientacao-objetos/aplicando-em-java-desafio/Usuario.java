public class Usuario {
    private String nome;
    private String email;
    private HistoricoReproducao historico;

    public Usuario(String nome, String email) {
        this.nome = nome;
        this.email = email;
        this.historico = new HistoricoReproducao();
    }

    public void ouvirMusica(Musica musica, String dispositivo, boolean tocouAteFinal) {
        historico.adicionarReproducao(musica, dispositivo, tocouAteFinal);
    }

    public void exibirHistorico() {
        System.out.println("Historico de " + nome + " : ");
        historico.listarHistorico();
    }
}
