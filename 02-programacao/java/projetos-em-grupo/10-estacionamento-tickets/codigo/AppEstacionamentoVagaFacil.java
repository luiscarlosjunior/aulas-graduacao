/**
 * AppEstacionamentoVagaFacil — o fluxo completo do sistema Estacionamento VagaFácil.
 * Leia na ordem: é exatamente este passo a passo que vira o DIAGRAMA DE SEQUÊNCIA.
 */
public class AppEstacionamentoVagaFacil {
    public static void main(String[] args) {
        System.out.println("=== Estacionamento VagaFácil ===");

        // DIP: o Gestor recebe o contrato Notificador (poderia ser e-mail, SMS...)
        Notificador notificador = new NotificadorConsole();
        GestorTicket gestor = new GestorTicket(notificador);

        // 1) HERANÇA + POLIMORFISMO: dois tipos, a mesma pergunta, respostas diferentes
        System.out.println("\n1) Polimorfismo — calcularValor() (preço por hora em R$ (0 = mensalista))");
        Cliente comum = new ClienteAvulso("Ana Souza", "ABC1D23");
        Cliente especial = new ClienteMensalista("Bruno Lima", "XYZ9K87");
        System.out.println("   ClienteAvulso -> " + comum.calcularValor());
        System.out.println("   ClienteMensalista -> " + especial.calcularValor());

        // 2) ASSOCIAÇÃO + ENCAPSULAMENTO: abre a Ticket pelo Gestor
        System.out.println("\n2) Abrindo um registro de Ticket");
        Ticket ticket = gestor.abrir(comum, 150.00);

        // 3) COMPOSIÇÃO: as partes nascem dentro do todo
        System.out.println("\n3) Composição — Ticket ◆ Cobranca");
        ticket.adicionarCobranca("Cobranca 1", 50.00);
        ticket.adicionarCobranca("Cobranca 2", 30.00);
        System.out.println("   Partes (Cobranca) criadas dentro de Ticket: " + ticket.getCobrancas().size());

        // 4) AGREGAÇÃO: o grupo recebe algo que já existe
        System.out.println("\n4) Agregação — Setor ◇ Vaga");
        Vaga item = new Vaga("Vaga A");   // existe por conta própria
        Setor setor = new Setor("Setor principal");
        setor.adicionarVaga(item);   // recebe PRONTO (agregação)
        System.out.println("   Itens (Vaga) agrupados em Setor: " + setor.getVagas().size());

        // 5) MÁQUINA DE ESTADOS: avança etapa por etapa até o fim
        System.out.println("\n5) Estados — aberto → em uso → fechado → pago");
        while (!ticket.emEtapaFinal()) {
            gestor.avancar(ticket);
        }

        // 6) PRÉ-CONDIÇÕES / INVARIANTE sendo protegidos
        System.out.println("\n6) Tentativas inválidas");
        try {
            gestor.avancar(ticket);   // já está na última etapa
        } catch (IllegalStateException e) {
            System.out.println("   Recusado: " + e.getMessage());
        }
        try {
            gestor.abrir(especial, -10.00);   // valor negativo fere o invariante
        } catch (IllegalArgumentException e) {
            System.out.println("   Recusado: " + e.getMessage());
        }

        System.out.println("\nTotal de registros (Ticket): " + gestor.listar().size());
    }
}
