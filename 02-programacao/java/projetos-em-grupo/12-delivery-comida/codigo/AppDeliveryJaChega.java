/**
 * AppDeliveryJaChega — o fluxo completo do sistema Delivery JáChega.
 * Rode e leia: cada bloco numerado mostra uma parte do sistema funcionando.
 */
public class AppDeliveryJaChega {
    public static void main(String[] args) {
        System.out.println("=== Delivery JáChega ===");

        // DIP: o Gestor recebe o contrato Notificador (poderia ser e-mail, SMS...)
        Notificador notificador = new NotificadorConsole();
        GestorPedido gestor = new GestorPedido(notificador);

        // 1) HERANÇA + POLIMORFISMO: dois tipos, a mesma pergunta, respostas diferentes
        System.out.println("\n1) Polimorfismo — taxaEntrega() (taxa de entrega em R$)");
        Cliente comum = new ClienteComum("Ana Souza", "Rua A, 10");
        Cliente especial = new ClienteAssinante("Bruno Lima", "Rua B, 20");
        System.out.println("   ClienteComum -> " + comum.taxaEntrega());
        System.out.println("   ClienteAssinante -> " + especial.taxaEntrega());

        // 2) ASSOCIAÇÃO + ENCAPSULAMENTO: abre a Pedido pelo Gestor
        System.out.println("\n2) Abrindo um registro de Pedido");
        Pedido pedido = gestor.abrir(comum, 150.00);

        // 3) COMPOSIÇÃO: as partes nascem dentro do todo
        System.out.println("\n3) Composição — Pedido ◆ ItemPedido");
        pedido.adicionarItemPedido("ItemPedido 1", 50.00);
        pedido.adicionarItemPedido("ItemPedido 2", 30.00);
        System.out.println("   Partes (ItemPedido) criadas dentro de Pedido: " + pedido.getItensPedido().size());

        // 4) AGREGAÇÃO: o grupo recebe algo que já existe
        System.out.println("\n4) Agregação — Restaurante ◇ Prato");
        Prato item = new Prato("Prato A");   // existe por conta própria
        Restaurante restaurante = new Restaurante("Restaurante principal");
        restaurante.adicionarPrato(item);   // recebe PRONTO (agregação)
        System.out.println("   Itens (Prato) agrupados em Restaurante: " + restaurante.getPratos().size());

        // 5) Avança etapa por etapa até o fim
        System.out.println("\n5) Avançando até a última etapa");
        while (!pedido.emEtapaFinal()) {
            gestor.avancar(pedido);
        }

        // 6) PRÉ-CONDIÇÕES / INVARIANTE sendo protegidos
        System.out.println("\n6) Tentativas inválidas");
        try {
            gestor.avancar(pedido);   // já está na última etapa
        } catch (IllegalStateException e) {
            System.out.println("   Recusado: " + e.getMessage());
        }
        try {
            gestor.abrir(especial, -10.00);   // valor negativo fere o invariante
        } catch (IllegalArgumentException e) {
            System.out.println("   Recusado: " + e.getMessage());
        }

        // 7) CAMINHO ALTERNATIVO ("cancelado")
        System.out.println("\n7) Caminho alternativo: cancelado");
        Pedido outra = gestor.abrir(especial, 99.90);
        gestor.cancelar(outra);
        try {
            gestor.avancar(outra);   // PRÉ-CONDIÇÃO falha: não se avança a partir de "cancelado"
        } catch (IllegalStateException e) {
            System.out.println("   Recusado: " + e.getMessage());
        }

        System.out.println("\nTotal de registros (Pedido): " + gestor.listar().size());
    }
}
