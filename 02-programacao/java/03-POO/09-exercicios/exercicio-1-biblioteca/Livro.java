/**
 * Exercício 1 - Sistema de Biblioteca
 * 
 * Classe Livro demonstrando conceitos básicos de POO:
 * - Encapsulamento (atributos privados)
 * - Construtores
 * - Getters e Setters com validação
 * - Métodos de negócio
 * 
 * @author Exercício POO Java
 */
public class Livro {
    
    // ===== ATRIBUTOS PRIVADOS (ENCAPSULAMENTO) =====
    
    private String titulo;
    private String autor;
    private String isbn;
    private int anoPublicacao;
    private int numeroPaginas;
    private boolean disponivel;
    private String categoria;
    private double preco;
    
    // ===== CONSTRUTORES =====
    
    /**
     * Construtor básico
     */
    public Livro(String titulo, String autor, String isbn) {
        this.titulo = titulo;
        this.autor = autor;
        this.isbn = isbn;
        this.disponivel = true; // Livro novo está disponível
        this.categoria = "Geral";
        this.preco = 0.0;
        this.anoPublicacao = 2023;
        this.numeroPaginas = 0;
    }
    
    /**
     * Construtor completo
     */
    public Livro(String titulo, String autor, String isbn, int anoPublicacao, 
                 int numeroPaginas, String categoria, double preco) {
        this(titulo, autor, isbn); // Chama construtor básico
        setAnoPublicacao(anoPublicacao);
        setNumeroPaginas(numeroPaginas);
        setCategoria(categoria);
        setPreco(preco);
    }
    
    // ===== GETTERS =====
    
    public String getTitulo() { return titulo; }
    public String getAutor() { return autor; }
    public String getIsbn() { return isbn; }
    public int getAnoPublicacao() { return anoPublicacao; }
    public int getNumeroPaginas() { return numeroPaginas; }
    public boolean isDisponivel() { return disponivel; }
    public String getCategoria() { return categoria; }
    public double getPreco() { return preco; }
    
    // ===== SETTERS COM VALIDAÇÃO =====
    
    public void setTitulo(String titulo) {
        if (titulo != null && !titulo.trim().isEmpty()) {
            this.titulo = titulo.trim();
        }
    }
    
    public void setAutor(String autor) {
        if (autor != null && !autor.trim().isEmpty()) {
            this.autor = autor.trim();
        }
    }
    
    public void setAnoPublicacao(int anoPublicacao) {
        int anoAtual = java.time.Year.now().getValue();
        if (anoPublicacao > 0 && anoPublicacao <= anoAtual) {
            this.anoPublicacao = anoPublicacao;
        }
    }
    
    public void setNumeroPaginas(int numeroPaginas) {
        if (numeroPaginas > 0) {
            this.numeroPaginas = numeroPaginas;
        }
    }
    
    public void setCategoria(String categoria) {
        if (categoria != null && !categoria.trim().isEmpty()) {
            this.categoria = categoria.trim();
        }
    }
    
    public void setPreco(double preco) {
        if (preco >= 0) {
            this.preco = preco;
        }
    }
    
    // ===== MÉTODOS DE NEGÓCIO =====
    
    /**
     * Empresta o livro se estiver disponível
     * @return true se empréstimo foi realizado
     */
    public boolean emprestar() {
        if (disponivel) {
            disponivel = false;
            System.out.printf("📚 Livro '%s' emprestado com sucesso%n", titulo);
            return true;
        } else {
            System.out.printf("❌ Livro '%s' não está disponível%n", titulo);
            return false;
        }
    }
    
    /**
     * Devolve o livro
     */
    public void devolver() {
        if (!disponivel) {
            disponivel = true;
            System.out.printf("📚 Livro '%s' devolvido com sucesso%n", titulo);
        } else {
            System.out.printf("ℹ️ Livro '%s' já está disponível%n", titulo);
        }
    }
    
    /**
     * Verifica se o livro é antigo (mais de 50 anos)
     */
    public boolean isAntigo() {
        int anoAtual = java.time.Year.now().getValue();
        return (anoAtual - anoPublicacao) > 50;
    }
    
    /**
     * Verifica se é um livro grande (mais de 500 páginas)
     */
    public boolean isLivroGrande() {
        return numeroPaginas > 500;
    }
    
    /**
     * Calcula preço com desconto
     */
    public double calcularPrecoComDesconto(double percentualDesconto) {
        if (percentualDesconto < 0 || percentualDesconto > 100) {
            return preco;
        }
        return preco * (1 - percentualDesconto / 100);
    }
    
    /**
     * Exibe informações completas do livro
     */
    public void exibirInformacoes() {
        System.out.println("\n=== Informações do Livro ===");
        System.out.println("Título: " + titulo);
        System.out.println("Autor: " + autor);
        System.out.println("ISBN: " + isbn);
        System.out.println("Ano: " + anoPublicacao);
        System.out.println("Páginas: " + numeroPaginas);
        System.out.println("Categoria: " + categoria);
        System.out.printf("Preço: R$ %.2f%n", preco);
        System.out.println("Status: " + (disponivel ? "Disponível" : "Emprestado"));
        System.out.println("Tipo: " + (isLivroGrande() ? "Livro Grande" : "Livro Normal"));
        System.out.println("Época: " + (isAntigo() ? "Antigo" : "Moderno"));
        System.out.println("=========================\n");
    }
    
    /**
     * Override do método toString para representação textual
     */
    @Override
    public String toString() {
        return String.format("Livro{título='%s', autor='%s', ano=%d, disponível=%s}", 
                           titulo, autor, anoPublicacao, disponivel);
    }
    
    /**
     * Override do método equals para comparação
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        
        Livro livro = (Livro) obj;
        return isbn.equals(livro.isbn); // ISBN é único
    }
    
    /**
     * Override do método hashCode
     */
    @Override
    public int hashCode() {
        return isbn.hashCode();
    }
}