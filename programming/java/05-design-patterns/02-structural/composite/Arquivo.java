/**
 * Leaf - Representa um arquivo (elemento simples)
 * Não pode conter outros elementos
 */
public class Arquivo implements ElementoSistemaArquivos {
    private String nome;
    private int tamanho; // em KB
    
    public Arquivo(String nome, int tamanho) {
        this.nome = nome;
        this.tamanho = tamanho;
    }
    
    @Override
    public String getNome() {
        return nome;
    }
    
    @Override
    public int getTamanho() {
        return tamanho;
    }
    
    @Override
    public void exibir(String indentacao) {
        System.out.println(indentacao + "📄 " + nome + " (" + tamanho + " KB)");
    }
}
