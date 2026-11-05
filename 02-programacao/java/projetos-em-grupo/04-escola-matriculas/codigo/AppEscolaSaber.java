/**
 * AppEscolaSaber — o fluxo completo do sistema Escola Saber+.
 * Rode e leia: cada bloco numerado mostra uma parte do sistema funcionando.
 */
public class AppEscolaSaber {
    public static void main(String[] args) {
        System.out.println("=== Escola Saber+ ===");

        // DIP: o Gestor recebe o contrato Notificador (poderia ser e-mail, SMS...)
        Notificador notificador = new NotificadorConsole();
        GestorMatricula gestor = new GestorMatricula(notificador);

        // 1) HERANÇA + POLIMORFISMO: dois tipos, a mesma pergunta, respostas diferentes
        System.out.println("\n1) Polimorfismo — mensalidade() (mensalidade em R$)");
        Aluno comum = new AlunoRegular("Ana Souza", "M-001");
        Aluno especial = new AlunoBolsista("Bruno Lima", "M-002");
        System.out.println("   AlunoRegular -> " + comum.mensalidade());
        System.out.println("   AlunoBolsista -> " + especial.mensalidade());

        // 2) ASSOCIAÇÃO + ENCAPSULAMENTO: abre a Matricula pelo Gestor
        System.out.println("\n2) Abrindo um registro de Matricula");
        Matricula matricula = gestor.abrir(comum, 150.00);

        // 3) COMPOSIÇÃO: as partes nascem dentro do todo
        System.out.println("\n3) Composição — Matricula ◆ Nota");
        matricula.adicionarNota("Nota 1", 50.00);
        matricula.adicionarNota("Nota 2", 30.00);
        System.out.println("   Partes (Nota) criadas dentro de Matricula: " + matricula.getNotas().size());

        // 4) AGREGAÇÃO: o grupo recebe algo que já existe
        System.out.println("\n4) Agregação — Turma ◇ Aluno");
        Aluno item = new AlunoRegular("Carla Dias", "M-003");   // existe por conta própria
        Turma turma = new Turma("Turma principal");
        turma.adicionarAluno(item);   // recebe PRONTO (agregação)
        System.out.println("   Itens (Aluno) agrupados em Turma: " + turma.getAlunos().size());

        // 5) Avança etapa por etapa até o fim
        System.out.println("\n5) Avançando até a última etapa");
        while (!matricula.emEtapaFinal()) {
            gestor.avancar(matricula);
        }

        // 6) PRÉ-CONDIÇÕES / INVARIANTE sendo protegidos
        System.out.println("\n6) Tentativas inválidas");
        try {
            gestor.avancar(matricula);   // já está na última etapa
        } catch (IllegalStateException e) {
            System.out.println("   Recusado: " + e.getMessage());
        }
        try {
            gestor.abrir(especial, -10.00);   // valor negativo fere o invariante
        } catch (IllegalArgumentException e) {
            System.out.println("   Recusado: " + e.getMessage());
        }

        // 7) CAMINHO ALTERNATIVO ("cancelada")
        System.out.println("\n7) Caminho alternativo: cancelada");
        Matricula outra = gestor.abrir(especial, 99.90);
        gestor.cancelar(outra);
        try {
            gestor.avancar(outra);   // PRÉ-CONDIÇÃO falha: não se avança a partir de "cancelada"
        } catch (IllegalStateException e) {
            System.out.println("   Recusado: " + e.getMessage());
        }

        System.out.println("\nTotal de registros (Matricula): " + gestor.listar().size());
    }
}
