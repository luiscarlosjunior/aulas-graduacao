import java.util.ArrayList;
import java.util.List;

/**
 * Classe que representa um documento que pode ser clonado
 * 
 * Demonstra clonagem profunda (deep copy) onde objetos internos
 * também são clonados.
 * 
 * @author Aulas Graduação
 */
public class Documento implements Prototipo {
    
    private String titulo;
    private String conteudo;
    private String autor;
    private List<String> tags;
    private ConfiguracaoDocumento config;
    
    /**
     * Construtor completo
     */
    public Documento(String titulo, String conteudo, String autor) {
        this.titulo = titulo;
        this.conteudo = conteudo;
        this.autor = autor;
        this.tags = new ArrayList<>();
        this.config = new ConfiguracaoDocumento();
        
        System.out.println("📄 Documento original criado: " + titulo);
    }
    
    /**
     * Construtor privado para clonagem
     */
    private Documento(Documento original) {
        this.titulo = original.titulo;
        this.conteudo = original.conteudo;
        this.autor = original.autor;
        
        // Deep copy da lista de tags
        this.tags = new ArrayList<>(original.tags);
        
        // Deep copy da configuração
        this.config = original.config.clonar();
        
        System.out.println("📋 Documento clonado: " + titulo);
    }
    
    @Override
    public Prototipo clonar() {
        return new Documento(this);
    }
    
    @Override
    public void exibirInfo() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("📄 DOCUMENTO");
        System.out.println("=".repeat(50));
        System.out.println("Título: " + titulo);
        System.out.println("Autor: " + autor);
        System.out.println("Conteúdo: " + conteudo.substring(0, Math.min(50, conteudo.length())) + "...");
        System.out.println("Tags: " + tags);
        System.out.println("\nConfigurações:");
        config.exibir();
        System.out.println("=".repeat(50));
    }
    
    // Métodos auxiliares
    public void adicionarTag(String tag) {
        tags.add(tag);
    }
    
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    
    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }
    
    public void setAutor(String autor) {
        this.autor = autor;
    }
    
    public ConfiguracaoDocumento getConfig() {
        return config;
    }
    
    public String getTitulo() {
        return titulo;
    }
    
    public List<String> getTags() {
        return tags;
    }
}

/**
 * Classe auxiliar para configurações do documento
 */
class ConfiguracaoDocumento {
    private String fonte;
    private int tamanhoFonte;
    private String corTexto;
    private boolean negrito;
    
    public ConfiguracaoDocumento() {
        this.fonte = "Arial";
        this.tamanhoFonte = 12;
        this.corTexto = "#000000";
        this.negrito = false;
    }
    
    public ConfiguracaoDocumento clonar() {
        ConfiguracaoDocumento clone = new ConfiguracaoDocumento();
        clone.fonte = this.fonte;
        clone.tamanhoFonte = this.tamanhoFonte;
        clone.corTexto = this.corTexto;
        clone.negrito = this.negrito;
        return clone;
    }
    
    public void exibir() {
        System.out.println("  - Fonte: " + fonte);
        System.out.println("  - Tamanho: " + tamanhoFonte + "pt");
        System.out.println("  - Cor: " + corTexto);
        System.out.println("  - Negrito: " + (negrito ? "Sim" : "Não"));
    }
    
    // Getters e Setters
    public void setFonte(String fonte) { this.fonte = fonte; }
    public void setTamanhoFonte(int tamanho) { this.tamanhoFonte = tamanho; }
    public void setCorTexto(String cor) { this.corTexto = cor; }
    public void setNegrito(boolean negrito) { this.negrito = negrito; }
}
