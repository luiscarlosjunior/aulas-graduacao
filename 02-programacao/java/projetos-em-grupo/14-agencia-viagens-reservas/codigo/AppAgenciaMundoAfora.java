/**
 * AppAgenciaMundoAfora — o fluxo completo do sistema Agência MundoAfora.
 * Leia na ordem: é exatamente este passo a passo que vira o DIAGRAMA DE SEQUÊNCIA.
 */
public class AppAgenciaMundoAfora {
    public static void main(String[] args) {
        System.out.println("=== Agência MundoAfora ===");

        // DIP: o Gestor recebe o contrato Notificador (poderia ser e-mail, SMS...)
        Notificador notificador = new NotificadorConsole();
        GestorReserva gestor = new GestorReserva(notificador);

        // 1) HERANÇA + POLIMORFISMO: dois tipos, a mesma pergunta, respostas diferentes
        System.out.println("\n1) Polimorfismo — valorComExtras() (valor do pacote com extras em R$)");
        Cliente comum = new ClienteComum("Ana Souza", "123.456.789-00");
        Cliente especial = new ClientePremium("Bruno Lima", "987.654.321-00");
        System.out.println("   ClienteComum -> " + comum.valorComExtras());
        System.out.println("   ClientePremium -> " + especial.valorComExtras());

        // 2) ASSOCIAÇÃO + ENCAPSULAMENTO: abre a Reserva pelo Gestor
        System.out.println("\n2) Abrindo um registro de Reserva");
        Reserva reserva = gestor.abrir(comum, 150.00);

        // 3) COMPOSIÇÃO: as partes nascem dentro do todo
        System.out.println("\n3) Composição — Pacote ◆ ItemPacote");
        Pacote pacote = new Pacote("Pacote principal");
        pacote.adicionarItemPacote("ItemPacote 1", 50.00);
        pacote.adicionarItemPacote("ItemPacote 2", 30.00);
        System.out.println("   Partes (ItemPacote) criadas dentro de Pacote: " + pacote.getItensPacote().size());

        // 4) AGREGAÇÃO: o grupo recebe algo que já existe
        System.out.println("\n4) Agregação — Pacote ◇ Destino");
        Destino item = new Destino("Destino A");   // existe por conta própria
        pacote.adicionarDestino(item);   // recebe PRONTO (agregação)
        System.out.println("   Itens (Destino) agrupados em Pacote: " + pacote.getDestinos().size());

        // 5) MÁQUINA DE ESTADOS: avança etapa por etapa até o fim
        System.out.println("\n5) Estados — pendente → confirmada");
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

        // 7) CAMINHO ALTERNATIVO da máquina de estados ("cancelada")
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
