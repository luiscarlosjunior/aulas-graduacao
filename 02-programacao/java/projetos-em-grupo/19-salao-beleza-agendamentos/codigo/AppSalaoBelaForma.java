/**
 * AppSalaoBelaForma — o fluxo completo do sistema Salão BelaForma.
 * Leia na ordem: é exatamente este passo a passo que vira o DIAGRAMA DE SEQUÊNCIA.
 */
public class AppSalaoBelaForma {
    public static void main(String[] args) {
        System.out.println("=== Salão BelaForma ===");

        // DIP: o Gestor recebe o contrato Notificador (poderia ser e-mail, SMS...)
        Notificador notificador = new NotificadorConsole();
        GestorAgendamento gestor = new GestorAgendamento(notificador);

        // 1) HERANÇA + POLIMORFISMO: dois tipos, a mesma pergunta, respostas diferentes
        System.out.println("\n1) Polimorfismo — calcularDesconto() (desconto (0.10 = 10%))");
        Cliente comum = new ClienteComum("Ana Souza", "11 99999-0001");
        Cliente especial = new ClienteFidelidade("Bruno Lima", "11 99999-0002");
        System.out.println("   ClienteComum -> " + comum.calcularDesconto());
        System.out.println("   ClienteFidelidade -> " + especial.calcularDesconto());

        // 2) ASSOCIAÇÃO + ENCAPSULAMENTO: abre a Agendamento pelo Gestor
        System.out.println("\n2) Abrindo um registro de Agendamento");
        Agendamento agendamento = gestor.abrir(comum, 150.00);

        // 3) COMPOSIÇÃO: as partes nascem dentro do todo
        System.out.println("\n3) Composição — Agendamento ◆ ItemServico");
        agendamento.adicionarItemServico("ItemServico 1", 50.00);
        agendamento.adicionarItemServico("ItemServico 2", 30.00);
        System.out.println("   Partes (ItemServico) criadas dentro de Agendamento: " + agendamento.getItensServico().size());

        // 4) AGREGAÇÃO: o grupo recebe algo que já existe
        System.out.println("\n4) Agregação — Profissional ◇ Servico");
        Servico item = new Servico("Servico A");   // existe por conta própria
        Profissional profissional = new Profissional("Profissional principal");
        profissional.adicionarServico(item);   // recebe PRONTO (agregação)
        System.out.println("   Itens (Servico) agrupados em Profissional: " + profissional.getServicos().size());

        // 5) MÁQUINA DE ESTADOS: avança etapa por etapa até o fim
        System.out.println("\n5) Estados — marcado → atendido");
        while (!agendamento.emEtapaFinal()) {
            gestor.avancar(agendamento);
        }

        // 6) PRÉ-CONDIÇÕES / INVARIANTE sendo protegidos
        System.out.println("\n6) Tentativas inválidas");
        try {
            gestor.avancar(agendamento);   // já está na última etapa
        } catch (IllegalStateException e) {
            System.out.println("   Recusado: " + e.getMessage());
        }
        try {
            gestor.abrir(especial, -10.00);   // valor negativo fere o invariante
        } catch (IllegalArgumentException e) {
            System.out.println("   Recusado: " + e.getMessage());
        }

        // 7) CAMINHO ALTERNATIVO da máquina de estados ("cancelado")
        System.out.println("\n7) Caminho alternativo: cancelado");
        Agendamento outra = gestor.abrir(especial, 99.90);
        gestor.cancelar(outra);
        try {
            gestor.avancar(outra);   // PRÉ-CONDIÇÃO falha: não se avança a partir de "cancelado"
        } catch (IllegalStateException e) {
            System.out.println("   Recusado: " + e.getMessage());
        }

        System.out.println("\nTotal de registros (Agendamento): " + gestor.listar().size());
    }
}
