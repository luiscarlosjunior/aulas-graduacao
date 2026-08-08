/**
 * Programa principal: junta as três entidades modeladas e mostra o sistema
 * funcionando. Repare como o código "conversa" no vocabulário do problema:
 * biblioteca.cadastrar(...), membro.pegarEmprestado(...), etc.
 *
 * Compile e rode:
 *     javac *.java
 *     java Principal
 */
public class Principal {
    public static void main(String[] args) {
        // 1. Criamos a biblioteca (dona do acervo)
        Biblioteca biblioteca = new Biblioteca("Biblioteca Central");

        // 2. Criamos livros e cadastramos
        Livro dom = new Livro("Dom Casmurro", "Machado de Assis");
        Livro hobbit = new Livro("O Hobbit", "J.R.R. Tolkien");
        Livro clean = new Livro("Clean Code", "Robert C. Martin");

        biblioteca.cadastrar(dom);
        biblioteca.cadastrar(hobbit);
        biblioteca.cadastrar(clean);

        biblioteca.listarDisponiveis(); // 3 disponíveis

        // 3. Um membro pega livros emprestados
        Membro ana = new Membro("Ana");
        System.out.println();
        ana.pegarEmprestado(hobbit);
        ana.pegarEmprestado(clean);

        biblioteca.listarDisponiveis(); // agora só 1 disponível (Dom Casmurro)

        // 4. Tentativa de pegar um livro já emprestado
        System.out.println();
        Membro bruno = new Membro("Bruno");
        bruno.pegarEmprestado(hobbit); // indisponível!

        // 5. Ana devolve, e o livro volta a ficar disponível
        System.out.println();
        ana.devolver(hobbit);
        biblioteca.listarDisponiveis(); // 2 disponíveis de novo
    }
}
