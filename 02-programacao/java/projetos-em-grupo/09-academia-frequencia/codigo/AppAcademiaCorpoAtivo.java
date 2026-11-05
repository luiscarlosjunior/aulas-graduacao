/**
 * AppAcademiaCorpoAtivo — o fluxo completo do sistema Academia CorpoAtivo.
 * Leia na ordem: é exatamente este passo a passo que vira o DIAGRAMA DE SEQUÊNCIA.
 */
public class AppAcademiaCorpoAtivo {
    public static void main(String[] args) {
        System.out.println("=== Academia CorpoAtivo ===");

        // DIP: o Gestor recebe o contrato Notificador (poderia ser e-mail, SMS...)
        Notificador notificador = new NotificadorConsole();
        GestorPlano gestor = new GestorPlano(notificador);

        // 1) HERANÇA + POLIMORFISMO: dois tipos, a mesma pergunta, respostas diferentes
        System.out.println("\n1) Polimorfismo — valorMensalidade() (mensalidade em R$ por pessoa)");
        Aluno comum = new AlunoComum("Ana Souza", "123.456.789-00");
        Aluno especial = new AlunoPlanoFamilia("Bruno Lima", "987.654.321-00");
        System.out.println("   AlunoComum -> " + comum.valorMensalidade());
        System.out.println("   AlunoPlanoFamilia -> " + especial.valorMensalidade());

        // 2) ASSOCIAÇÃO + ENCAPSULAMENTO: abre a Plano pelo Gestor
        System.out.println("\n2) Abrindo um registro de Plano");
        Plano plano = gestor.abrir(comum, 150.00);

        // 3) COMPOSIÇÃO: as partes nascem dentro do todo
        System.out.println("\n3) Composição — Aluno ◆ CheckIn");
        comum.adicionarCheckIn("CheckIn 1", 50.00);
        comum.adicionarCheckIn("CheckIn 2", 30.00);
        System.out.println("   Partes (CheckIn) criadas dentro de Aluno: " + comum.getCheckIns().size());

        // 4) AGREGAÇÃO: o grupo recebe algo que já existe
        System.out.println("\n4) Agregação — Turma ◇ Aluno");
        Aluno item = new AlunoComum("Carla Dias", "111.222.333-44");   // existe por conta própria
        Turma turma = new Turma("Turma principal");
        turma.adicionarAluno(item);   // recebe PRONTO (agregação)
        System.out.println("   Itens (Aluno) agrupados em Turma: " + turma.getAlunos().size());

        // 5) MÁQUINA DE ESTADOS: avança etapa por etapa até o fim
        System.out.println("\n5) Estados — ativo → vencido → cancelado");
        while (!plano.emEtapaFinal()) {
            gestor.avancar(plano);
        }

        // 6) PRÉ-CONDIÇÕES / INVARIANTE sendo protegidos
        System.out.println("\n6) Tentativas inválidas");
        try {
            gestor.avancar(plano);   // já está na última etapa
        } catch (IllegalStateException e) {
            System.out.println("   Recusado: " + e.getMessage());
        }
        try {
            gestor.abrir(especial, -10.00);   // valor negativo fere o invariante
        } catch (IllegalArgumentException e) {
            System.out.println("   Recusado: " + e.getMessage());
        }

        System.out.println("\nTotal de registros (Plano): " + gestor.listar().size());
    }
}
