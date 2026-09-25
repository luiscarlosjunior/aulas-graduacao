/*
 * AppBiblioteca — o fluxo completo do sistema "Leitura Livre" rodando.
 * Mostra: herança/polimorfismo, composição, agregação, associação, mensagens
 * entre objetos e o invariante sendo protegido.
 */
public class AppBiblioteca {
    public static void main(String[] args) {
        // 1) HERANÇA + POLIMORFISMO — mesmo contrato, respostas diferentes
        Membro ana  = new MembroComum("Ana Souza", "ana@bib.com", "1234");
        Membro prof = new MembroProfessor("Prof. Silva", "silva@bib.com", "abcd");
        System.out.println(ana.getNome()  + ": prazo " + ana.prazoDeEmprestimoDias()
                + " dias, limite " + ana.limiteDeLivros() + " livros");
        System.out.println(prof.getNome() + ": prazo " + prof.prazoDeEmprestimoDias()
                + " dias, limite " + prof.limiteDeLivros() + " livros");

        // 2) COMPOSIÇÃO (◆) — o Livro CRIA seus exemplares (nascem dentro dele)
        Livro dom = new Livro("Dom Casmurro", "Machado de Assis", "978-85-0001");
        dom.adicionarExemplar("EX-001");
        dom.adicionarExemplar("EX-002");

        // 3) AGREGAÇÃO (◇) — a Categoria agrupa livros que JÁ existem
        Categoria classicos = new Categoria("Clássicos");
        classicos.adicionarLivro(dom);
        System.out.println("\nCategoria '" + classicos.getNome() + "' tem "
                + classicos.getLivros().size() + " livro(s).");

        // 4) ASSOCIAÇÃO + MENSAGENS — o empréstimo coordena membro e exemplar
        Exemplar disponivel = dom.primeiroDisponivel();
        Emprestimo emp = new Emprestimo(ana, disponivel);
        System.out.println("\nEmprestado " + disponivel.getCodigo() + " para "
                + emp.getMembro().getNome() + " (devolver até " + emp.getDataDevolucaoPrevista() + ")");
        System.out.println(disponivel.getCodigo() + " disponível? " + disponivel.isDisponivel());

        // 5) INVARIANTE — emprestar o MESMO exemplar de novo deve ser recusado
        try {
            new Emprestimo(prof, disponivel);          // já está emprestado
        } catch (IllegalStateException e) {
            System.out.println("\nRecusado (invariante): " + e.getMessage());
        }

        // 6) DEVOLUÇÃO (mensagem) — o exemplar volta a ficar disponível
        emp.devolver();
        System.out.println("\nApós devolver, " + disponivel.getCodigo()
                + " disponível? " + disponivel.isDisponivel());
    }
}
