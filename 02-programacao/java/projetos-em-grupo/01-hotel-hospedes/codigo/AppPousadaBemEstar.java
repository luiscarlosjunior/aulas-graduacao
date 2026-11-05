/**
 * AppPousadaBemEstar — o fluxo completo do sistema Pousada Bem-Estar.
 * Rode e leia: cada bloco numerado mostra uma parte do sistema funcionando.
 */
public class AppPousadaBemEstar {
    public static void main(String[] args) {
        System.out.println("=== Pousada Bem-Estar ===");

        // DIP: o Gestor recebe o contrato Notificador (poderia ser e-mail, SMS...)
        Notificador notificador = new NotificadorConsole();
        GestorReserva gestor = new GestorReserva(notificador);

        // 1) HERANÇA + POLIMORFISMO: dois tipos, a mesma pergunta, respostas diferentes
        System.out.println("\n1) Polimorfismo — taxaDeServico() (taxa de serviço em R$ cobrada por diária)");
        Hospede comum = new HospedeComum("Ana Souza", "123.456.789-00");
        Hospede especial = new HospedeVIP("Bruno Lima", "987.654.321-00");
        System.out.println("   HospedeComum -> " + comum.taxaDeServico());
        System.out.println("   HospedeVIP -> " + especial.taxaDeServico());

        // 2) ASSOCIAÇÃO + ENCAPSULAMENTO: abre a Reserva pelo Gestor
        System.out.println("\n2) Abrindo um registro de Reserva");
        Reserva reserva = gestor.abrir(comum, 150.00);

        // 3) COMPOSIÇÃO: as partes nascem dentro do todo
        System.out.println("\n3) Composição — Reserva ◆ Diaria");
        reserva.adicionarDiaria("Diaria 1", 50.00);
        reserva.adicionarDiaria("Diaria 2", 30.00);
        System.out.println("   Partes (Diaria) criadas dentro de Reserva: " + reserva.getDiarias().size());

        // 4) AGREGAÇÃO: o grupo recebe algo que já existe
        System.out.println("\n4) Agregação — Reserva ◇ Hospede");
        Hospede item = new HospedeComum("Carla Dias", "111.222.333-44");   // existe por conta própria
        reserva.adicionarHospede(item);   // recebe PRONTO (agregação)
        System.out.println("   Itens (Hospede) agrupados em Reserva: " + reserva.getHospedes().size());

        // 5) Avança etapa por etapa até o fim
        System.out.println("\n5) Avançando até a última etapa");
        while (!reserva.emEtapaFinal()) {
            gestor.avancar(reserva);
        }

        // 6) PRÉ-CONDIÇÕES / INVARIANTE sendo protegidos
        System.out.println("\n6) Tentativas inválidas");
        try {
            gestor.avancar(reserva);   // já está na última etapa
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
        Reserva outra = gestor.abrir(especial, 99.90);
        gestor.cancelar(outra);
        try {
            gestor.avancar(outra);   // PRÉ-CONDIÇÃO falha: não se avança a partir de "cancelada"
        } catch (IllegalStateException e) {
            System.out.println("   Recusado: " + e.getMessage());
        }

        System.out.println("\nTotal de registros (Reserva): " + gestor.listar().size());
    }
}
