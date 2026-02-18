import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * ProdutoEntity - Entidade de exemplo para demonstrar sistema completo
 * Representa um produto no sistema de e-commerce
 * 
 * @author Apresentação Java Web
 */
public class ProdutoEntity {
    
    private Long id;
    private String nome;
    private String descricao;
    private BigDecimal preco;
    private Integer quantidadeEstoque;
    private String categoria;
    private String codigoBarra;
    private boolean ativo;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataAtualizacao;
    
    // Construtores
    public ProdutoEntity() {
        this.dataCriacao = LocalDateTime.now();
        this.dataAtualizacao = LocalDateTime.now();
        this.ativo = true;
        this.quantidadeEstoque = 0;
    }
    
    public ProdutoEntity(String nome, String descricao, BigDecimal preco, String categoria) {
        this();
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.categoria = categoria;
    }
    
    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    
    public BigDecimal getPreco() { return preco; }
    public void setPreco(BigDecimal preco) { this.preco = preco; }
    
    public Integer getQuantidadeEstoque() { return quantidadeEstoque; }
    public void setQuantidadeEstoque(Integer quantidadeEstoque) { this.quantidadeEstoque = quantidadeEstoque; }
    
    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }
    
    public String getCodigoBarra() { return codigoBarra; }
    public void setCodigoBarra(String codigoBarra) { this.codigoBarra = codigoBarra; }
    
    public boolean isAtivo() { return ativo; }
    public void setAtivo(boolean ativo) { this.ativo = ativo; }
    
    public LocalDateTime getDataCriacao() { return dataCriacao; }
    public void setDataCriacao(LocalDateTime dataCriacao) { this.dataCriacao = dataCriacao; }
    
    public LocalDateTime getDataAtualizacao() { return dataAtualizacao; }
    public void setDataAtualizacao(LocalDateTime dataAtualizacao) { this.dataAtualizacao = dataAtualizacao; }
    
    // Métodos de negócio
    public boolean temEstoque() {
        return quantidadeEstoque != null && quantidadeEstoque > 0;
    }
    
    public boolean temEstoqueSuficiente(int quantidade) {
        return quantidadeEstoque != null && quantidadeEstoque >= quantidade;
    }
    
    public void adicionarEstoque(int quantidade) {
        if (quantidade > 0) {
            this.quantidadeEstoque = (this.quantidadeEstoque != null ? this.quantidadeEstoque : 0) + quantidade;
            this.dataAtualizacao = LocalDateTime.now();
        }
    }
    
    public boolean removerEstoque(int quantidade) {
        if (quantidade > 0 && temEstoqueSuficiente(quantidade)) {
            this.quantidadeEstoque -= quantidade;
            this.dataAtualizacao = LocalDateTime.now();
            return true;
        }
        return false;
    }
    
    public BigDecimal calcularValorEstoque() {
        if (preco != null && quantidadeEstoque != null) {
            return preco.multiply(BigDecimal.valueOf(quantidadeEstoque));
        }
        return BigDecimal.ZERO;
    }
    
    public void atualizarPreco(BigDecimal novoPreco) {
        if (novoPreco != null && novoPreco.compareTo(BigDecimal.ZERO) > 0) {
            this.preco = novoPreco;
            this.dataAtualizacao = LocalDateTime.now();
        }
    }
    
    // Validações
    public boolean isValid() {
        return nome != null && !nome.trim().isEmpty() &&
               preco != null && preco.compareTo(BigDecimal.ZERO) > 0 &&
               categoria != null && !categoria.trim().isEmpty();
    }
    
    @Override
    public String toString() {
        return String.format(
            "ProdutoEntity{id=%d, nome='%s', preco=R$%.2f, estoque=%d, categoria='%s', ativo=%s}",
            id, nome, preco, quantidadeEstoque, categoria, ativo
        );
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        
        ProdutoEntity produto = (ProdutoEntity) obj;
        return id != null ? id.equals(produto.id) : produto.id == null;
    }
    
    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}