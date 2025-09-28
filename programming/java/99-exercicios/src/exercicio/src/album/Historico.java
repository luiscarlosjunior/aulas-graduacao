package album;
public class Historico {
    private String conteudo;

    public Historico(String conteudo) {
        this.conteudo = conteudo;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public void exibir() {
        System.out.println("Conteúdo do histórico: " + conteudo);
    }
}