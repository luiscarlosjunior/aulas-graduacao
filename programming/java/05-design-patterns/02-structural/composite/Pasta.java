import java.util.ArrayList;
import java.util.List;

/**
 * Composite - Representa uma pasta (elemento composto)
 * Pode conter arquivos e outras pastas (composição recursiva)
 */
public class Pasta implements ElementoSistemaArquivos {
    private String nome;
    private List<ElementoSistemaArquivos> elementos;
    
    public Pasta(String nome) {
        this.nome = nome;
        this.elementos = new ArrayList<>();
    }
    
    /**
     * Adiciona elemento (arquivo ou pasta) a esta pasta
     */
    public void adicionar(ElementoSistemaArquivos elemento) {
        elementos.add(elemento);
    }
    
    /**
     * Remove elemento desta pasta
     */
    public void remover(ElementoSistemaArquivos elemento) {
        elementos.remove(elemento);
    }
    
    /**
     * Retorna elementos filhos
     */
    public List<ElementoSistemaArquivos> getElementos() {
        return elementos;
    }
    
    @Override
    public String getNome() {
        return nome;
    }
    
    /**
     * Tamanho da pasta = soma dos tamanhos de todos os filhos
     * Demonstra recursão: pastas filhas calculam seus próprios tamanhos
     */
    @Override
    public int getTamanho() {
        int tamanhoTotal = 0;
        for (ElementoSistemaArquivos elemento : elementos) {
            tamanhoTotal += elemento.getTamanho();
        }
        return tamanhoTotal;
    }
    
    /**
     * Exibe pasta e todo seu conteúdo recursivamente
     */
    @Override
    public void exibir(String indentacao) {
        System.out.println(indentacao + "📁 " + nome + "/ (" + getTamanho() + " KB total)");
        // Recursão: cada filho exibe a si mesmo
        for (ElementoSistemaArquivos elemento : elementos) {
            elemento.exibir(indentacao + "  ");
        }
    }
}
