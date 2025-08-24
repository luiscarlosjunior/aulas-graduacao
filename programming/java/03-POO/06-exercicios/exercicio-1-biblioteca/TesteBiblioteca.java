/**
 * Exercício 1 - Sistema de Biblioteca
 * 
 * Classe de teste demonstrando uso completo do sistema:
 * - Criação de objetos
 * - Operações básicas
 * - Busca e filtros
 * - Empréstimos e devoluções
 * - Relatórios e estatísticas
 * 
 * @author Exercício POO Java
 */
import java.util.List;

public class TesteBiblioteca {
    
    public static void main(String[] args) {
        
        System.out.println("=== EXERCÍCIO 1: SISTEMA DE BIBLIOTECA ===\n");
        
        // ===== 1. CRIANDO A BIBLIOTECA =====
        System.out.println("1️⃣ CRIANDO BIBLIOTECA\n");
        
        Biblioteca biblioteca = new Biblioteca("Biblioteca Central", 
                                              "Rua das Letras, 123", 50);
        
        // ===== 2. CRIANDO E ADICIONANDO LIVROS =====
        System.out.println("\n2️⃣ ADICIONANDO LIVROS\n");
        
        // Livros usando construtor básico
        Livro livro1 = new Livro("Dom Casmurro", "Machado de Assis", "978-85-359-0277-5");
        livro1.setAnoPublicacao(1899);
        livro1.setNumeroPaginas(256);
        livro1.setCategoria("Literatura Clássica");
        livro1.setPreco(25.90);
        
        // Livros usando construtor completo
        Livro livro2 = new Livro("Clean Code", "Robert C. Martin", "978-0-13-235088-4",
                                2008, 464, "Programação", 89.90);
        
        Livro livro3 = new Livro("1984", "George Orwell", "978-0-452-28423-4",
                                1949, 328, "Ficção Científica", 32.50);
        
        Livro livro4 = new Livro("O Alquimista", "Paulo Coelho", "978-85-325-1158-9",
                                1988, 174, "Literatura Brasileira", 29.90);
        
        Livro livro5 = new Livro("Effective Java", "Joshua Bloch", "978-0-134-68599-1",
                                2017, 412, "Programação", 95.00);
        
        // Adicionando livros à biblioteca
        biblioteca.adicionarLivro(livro1);
        biblioteca.adicionarLivro(livro2);
        biblioteca.adicionarLivro(livro3);
        biblioteca.adicionarLivro(livro4);
        biblioteca.adicionarLivro(livro5);
        
        // Tentativa de adicionar livro duplicado
        Livro livroDuplicado = new Livro("Dom Casmurro Cópia", "Machado de Assis", 
                                        "978-85-359-0277-5"); // Mesmo ISBN
        biblioteca.adicionarLivro(livroDuplicado);
        
        // ===== 3. EXIBINDO INFORMAÇÕES =====
        System.out.println("\n3️⃣ INFORMAÇÕES DOS LIVROS\n");
        
        System.out.println("--- Informações detalhadas do primeiro livro ---");
        livro1.exibirInformacoes();
        
        System.out.println("--- Catálogo completo ---");
        biblioteca.listarTodosLivros();
        
        // ===== 4. TESTANDO BUSCAS =====
        System.out.println("4️⃣ TESTANDO BUSCAS\n");
        
        // Busca por ISBN
        System.out.println("--- Busca por ISBN ---");
        Livro encontrado = biblioteca.buscarPorIsbn("978-0-13-235088-4");
        if (encontrado != null) {
            System.out.println("✅ Encontrado: " + encontrado.toString());
        }
        
        // Busca por título
        System.out.println("\n--- Busca por título (parcial: 'clean') ---");
        List<Livro> livrosTitulo = biblioteca.buscarPorTitulo("clean");
        for (Livro livro : livrosTitulo) {
            System.out.println("📖 " + livro.toString());
        }
        
        // Busca por autor
        System.out.println("\n--- Busca por autor (Machado) ---");
        List<Livro> livrosAutor = biblioteca.buscarPorAutor("Machado");
        for (Livro livro : livrosAutor) {
            System.out.println("👤 " + livro.toString());
        }
        
        // Busca por categoria
        System.out.println("\n--- Busca por categoria (Programação) ---");
        List<Livro> livrosCategoria = biblioteca.buscarPorCategoria("Programação");
        for (Livro livro : livrosCategoria) {
            System.out.println("💻 " + livro.toString());
        }
        
        // ===== 5. OPERAÇÕES DE EMPRÉSTIMO =====
        System.out.println("\n5️⃣ OPERAÇÕES DE EMPRÉSTIMO\n");
        
        // Emprestando livros
        System.out.println("--- Emprestando livros ---");
        biblioteca.emprestarLivro("978-85-359-0277-5");  // Dom Casmurro
        biblioteca.emprestarLivro("978-0-13-235088-4");   // Clean Code
        biblioteca.emprestarLivro("978-0-452-28423-4");   // 1984
        
        // Tentativa de emprestar livro já emprestado
        biblioteca.emprestarLivro("978-85-359-0277-5");  // Dom Casmurro novamente
        
        // Listando livros disponíveis e emprestados
        System.out.println("\n--- Status atual dos livros ---");
        System.out.println("Livros disponíveis: " + biblioteca.listarLivrosDisponiveis().size());
        System.out.println("Livros emprestados: " + biblioteca.listarLivrosEmprestados().size());
        
        // ===== 6. DEVOLUÇÕES =====
        System.out.println("\n6️⃣ DEVOLUÇÕES\n");
        
        // Devolvendo alguns livros
        biblioteca.devolverLivro("978-85-359-0277-5");  // Dom Casmurro
        biblioteca.devolverLivro("978-0-452-28423-4");   // 1984
        
        // Tentativa de devolver livro já disponível
        biblioteca.devolverLivro("978-85-325-1158-9");  // O Alquimista (já disponível)
        
        // ===== 7. TESTANDO MÉTODOS DOS LIVROS =====
        System.out.println("\n7️⃣ TESTANDO MÉTODOS DOS LIVROS\n");
        
        System.out.println("--- Testando características dos livros ---");
        System.out.println("Dom Casmurro é antigo? " + livro1.isAntigo());
        System.out.println("Clean Code é livro grande? " + livro2.isLivroGrande());
        
        System.out.printf("Preço do Effective Java com 20%% desconto: R$ %.2f%n",
                         livro5.calcularPrecoComDesconto(20));
        
        // ===== 8. RELATÓRIOS E ESTATÍSTICAS =====
        System.out.println("\n8️⃣ RELATÓRIOS E ESTATÍSTICAS\n");
        
        biblioteca.exibirEstatisticas();
        biblioteca.relatoriorPorCategoria();
        
        // ===== 9. TESTANDO REMOÇÃO =====
        System.out.println("\n9️⃣ TESTANDO REMOÇÃO\n");
        
        // Tentativa de remover livro emprestado
        biblioteca.removerLivro("978-0-13-235088-4");  // Clean Code (emprestado)
        
        // Remoção de livro disponível
        biblioteca.removerLivro("978-85-325-1158-9");  // O Alquimista (disponível)
        
        // ===== 10. VALIDAÇÕES E CASOS ESPECIAIS =====
        System.out.println("\n🔟 VALIDAÇÕES E CASOS ESPECIAIS\n");
        
        // Testando validações na criação de livro
        Livro livroInvalido = new Livro("", "", "");
        livroInvalido.setAnoPublicacao(2030);  // Ano futuro
        livroInvalido.setNumeroPaginas(-100);  // Páginas negativas
        livroInvalido.setPreco(-50);           // Preço negativo
        
        System.out.println("Livro com dados inválidos:");
        livroInvalido.exibirInformacoes();
        
        // Testando busca com termos que não existem
        System.out.println("--- Buscas sem resultado ---");
        List<Livro> naoEncontrados = biblioteca.buscarPorAutor("Autor Inexistente");
        System.out.println("Livros do autor inexistente: " + naoEncontrados.size());
        
        // ===== 11. STATUS FINAL =====
        System.out.println("1️⃣1️⃣ STATUS FINAL\n");
        
        biblioteca.exibirEstatisticas();
        
        System.out.println("--- Catálogo final ---");
        biblioteca.listarTodosLivros();
        
        // ===== 12. DEMONSTRAÇÃO DE EQUALS =====
        System.out.println("1️⃣2️⃣ DEMONSTRAÇÃO DE EQUALS\n");
        
        Livro livroOriginal = new Livro("Test", "Test Author", "123-456-789");
        Livro livroIgual = new Livro("Test Different Title", "Different Author", "123-456-789");
        Livro livroDiferente = new Livro("Test", "Test Author", "987-654-321");
        
        System.out.println("Livro original equals livro com mesmo ISBN? " + 
                          livroOriginal.equals(livroIgual));
        System.out.println("Livro original equals livro com ISBN diferente? " + 
                          livroOriginal.equals(livroDiferente));
        
        // ===== RESUMO FINAL =====
        System.out.println("\n🎯 RESUMO DOS CONCEITOS DEMONSTRADOS\n");
        
        System.out.println("✅ Exercício 1 completado com sucesso:");
        System.out.println("   1. Classes e Objetos - Livro e Biblioteca");
        System.out.println("   2. Encapsulamento - atributos privados, getters/setters");
        System.out.println("   3. Construtores - básico e completo");
        System.out.println("   4. Validações - dados consistentes");
        System.out.println("   5. Composição - Biblioteca 'tem' Livros");
        System.out.println("   6. Collections - ArrayList para gerenciar livros");
        System.out.println("   7. Métodos de negócio - empréstimo, busca, relatórios");
        System.out.println("   8. Override - toString, equals, hashCode");
        System.out.println("   9. Tratamento de casos especiais");
        System.out.println("   10. Organização e boas práticas");
        
        System.out.println("\n✅ Sistema de biblioteca funcional e completo!");
    }
}