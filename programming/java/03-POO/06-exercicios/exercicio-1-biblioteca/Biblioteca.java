/**
 * Exercício 1 - Sistema de Biblioteca
 * 
 * Classe Biblioteca demonstrando:
 * - Composição (biblioteca "tem" livros)
 * - Coleções (ArrayList)
 * - Métodos de busca e operações
 * - Validações e tratamento de casos especiais
 * 
 * @author Exercício POO Java
 */
import java.util.ArrayList;
import java.util.List;

public class Biblioteca {
    
    // ===== ATRIBUTOS =====
    
    private String nome;
    private String endereco;
    private List<Livro> livros;
    private int capacidadeMaxima;
    private int totalEmprestimos;
    
    // ===== CONSTRUTOR =====
    
    public Biblioteca(String nome, String endereco, int capacidadeMaxima) {
        this.nome = nome;
        this.endereco = endereco;
        this.capacidadeMaxima = capacidadeMaxima;
        this.livros = new ArrayList<>();
        this.totalEmprestimos = 0;
        
        System.out.printf("🏛️ Biblioteca '%s' criada (capacidade: %d livros)%n", 
                         nome, capacidadeMaxima);
    }
    
    // ===== GETTERS =====
    
    public String getNome() { return nome; }
    public String getEndereco() { return endereco; }
    public int getCapacidadeMaxima() { return capacidadeMaxima; }
    public int getTotalLivros() { return livros.size(); }
    public int getTotalEmprestimos() { return totalEmprestimos; }
    
    // ===== MÉTODOS DE GERENCIAMENTO =====
    
    /**
     * Adiciona um livro à biblioteca
     */
    public boolean adicionarLivro(Livro livro) {
        if (livro == null) {
            System.out.println("❌ Livro não pode ser nulo");
            return false;
        }
        
        if (livros.size() >= capacidadeMaxima) {
            System.out.println("❌ Biblioteca lotada! Capacidade máxima atingida");
            return false;
        }
        
        if (livros.contains(livro)) {
            System.out.printf("❌ Livro '%s' já existe na biblioteca%n", livro.getTitulo());
            return false;
        }
        
        livros.add(livro);
        System.out.printf("✅ Livro '%s' adicionado à biblioteca%n", livro.getTitulo());
        return true;
    }
    
    /**
     * Remove um livro da biblioteca
     */
    public boolean removerLivro(String isbn) {
        Livro livro = buscarPorIsbn(isbn);
        
        if (livro == null) {
            System.out.printf("❌ Livro com ISBN '%s' não encontrado%n", isbn);
            return false;
        }
        
        if (!livro.isDisponivel()) {
            System.out.printf("❌ Não é possível remover '%s' - livro está emprestado%n", 
                             livro.getTitulo());
            return false;
        }
        
        livros.remove(livro);
        System.out.printf("✅ Livro '%s' removido da biblioteca%n", livro.getTitulo());
        return true;
    }
    
    // ===== MÉTODOS DE BUSCA =====
    
    /**
     * Busca livro por ISBN
     */
    public Livro buscarPorIsbn(String isbn) {
        for (Livro livro : livros) {
            if (livro.getIsbn().equals(isbn)) {
                return livro;
            }
        }
        return null;
    }
    
    /**
     * Busca livros por título (busca parcial)
     */
    public List<Livro> buscarPorTitulo(String titulo) {
        List<Livro> resultado = new ArrayList<>();
        String tituloLower = titulo.toLowerCase();
        
        for (Livro livro : livros) {
            if (livro.getTitulo().toLowerCase().contains(tituloLower)) {
                resultado.add(livro);
            }
        }
        
        return resultado;
    }
    
    /**
     * Busca livros por autor
     */
    public List<Livro> buscarPorAutor(String autor) {
        List<Livro> resultado = new ArrayList<>();
        String autorLower = autor.toLowerCase();
        
        for (Livro livro : livros) {
            if (livro.getAutor().toLowerCase().contains(autorLower)) {
                resultado.add(livro);
            }
        }
        
        return resultado;
    }
    
    /**
     * Busca livros por categoria
     */
    public List<Livro> buscarPorCategoria(String categoria) {
        List<Livro> resultado = new ArrayList<>();
        
        for (Livro livro : livros) {
            if (livro.getCategoria().equalsIgnoreCase(categoria)) {
                resultado.add(livro);
            }
        }
        
        return resultado;
    }
    
    /**
     * Lista todos os livros disponíveis
     */
    public List<Livro> listarLivrosDisponiveis() {
        List<Livro> disponiveis = new ArrayList<>();
        
        for (Livro livro : livros) {
            if (livro.isDisponivel()) {
                disponiveis.add(livro);
            }
        }
        
        return disponiveis;
    }
    
    /**
     * Lista todos os livros emprestados
     */
    public List<Livro> listarLivrosEmprestados() {
        List<Livro> emprestados = new ArrayList<>();
        
        for (Livro livro : livros) {
            if (!livro.isDisponivel()) {
                emprestados.add(livro);
            }
        }
        
        return emprestados;
    }
    
    // ===== OPERAÇÕES DE EMPRÉSTIMO =====
    
    /**
     * Empresta um livro pelo ISBN
     */
    public boolean emprestarLivro(String isbn) {
        Livro livro = buscarPorIsbn(isbn);
        
        if (livro == null) {
            System.out.printf("❌ Livro com ISBN '%s' não encontrado%n", isbn);
            return false;
        }
        
        if (livro.emprestar()) {
            totalEmprestimos++;
            return true;
        }
        
        return false;
    }
    
    /**
     * Devolve um livro pelo ISBN
     */
    public boolean devolverLivro(String isbn) {
        Livro livro = buscarPorIsbn(isbn);
        
        if (livro == null) {
            System.out.printf("❌ Livro com ISBN '%s' não encontrado%n", isbn);
            return false;
        }
        
        if (!livro.isDisponivel()) {
            livro.devolver();
            return true;
        } else {
            System.out.printf("ℹ️ Livro '%s' já está disponível%n", livro.getTitulo());
            return false;
        }
    }
    
    // ===== RELATÓRIOS E ESTATÍSTICAS =====
    
    /**
     * Exibe estatísticas da biblioteca
     */
    public void exibirEstatisticas() {
        System.out.println("\n=== Estatísticas da Biblioteca ===");
        System.out.println("Nome: " + nome);
        System.out.println("Endereço: " + endereco);
        System.out.println("Total de livros: " + livros.size());
        System.out.println("Capacidade máxima: " + capacidadeMaxima);
        System.out.println("Livros disponíveis: " + listarLivrosDisponiveis().size());
        System.out.println("Livros emprestados: " + listarLivrosEmprestados().size());
        System.out.println("Total de empréstimos realizados: " + totalEmprestimos);
        System.out.printf("Taxa de ocupação: %.1f%%%n", 
                         (livros.size() * 100.0) / capacidadeMaxima);
        System.out.println("===============================\n");
    }
    
    /**
     * Lista todos os livros com suas informações
     */
    public void listarTodosLivros() {
        if (livros.isEmpty()) {
            System.out.println("📚 Biblioteca vazia - nenhum livro cadastrado");
            return;
        }
        
        System.out.println("\n=== Catálogo da Biblioteca ===");
        for (int i = 0; i < livros.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, livros.get(i).toString());
        }
        System.out.println("============================\n");
    }
    
    /**
     * Relatório por categoria
     */
    public void relatoriorPorCategoria() {
        System.out.println("\n=== Relatório por Categoria ===");
        
        // Conta livros por categoria
        java.util.Map<String, Integer> categorias = new java.util.HashMap<>();
        
        for (Livro livro : livros) {
            String categoria = livro.getCategoria();
            categorias.put(categoria, categorias.getOrDefault(categoria, 0) + 1);
        }
        
        for (java.util.Map.Entry<String, Integer> entry : categorias.entrySet()) {
            System.out.printf("📖 %s: %d livros%n", entry.getKey(), entry.getValue());
        }
        System.out.println("=============================\n");
    }
    
    /**
     * Override do toString
     */
    @Override
    public String toString() {
        return String.format("Biblioteca{nome='%s', livros=%d/%d}", 
                           nome, livros.size(), capacidadeMaxima);
    }
}