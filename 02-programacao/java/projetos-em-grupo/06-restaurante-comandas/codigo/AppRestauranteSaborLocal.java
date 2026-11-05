/**
 * AppRestauranteSaborLocal — o fluxo completo do sistema Restaurante SaborLocal.
 * Rode e leia: cada bloco numerado mostra uma parte do sistema funcionando.
 */
public class AppRestauranteSaborLocal {
    public static void main(String[] args) {
        System.out.println("=== Restaurante SaborLocal ===");

        // DIP: o Gestor recebe o contrato Notificador (poderia ser e-mail, SMS...)
        Notificador notificador = new NotificadorConsole();
        GestorComanda gestor = new GestorComanda(notificador);

        // 1) HERANÇA + POLIMORFISMO: dois tipos, a mesma pergunta, respostas diferentes
        System.out.println("\n1) Polimorfismo — calcularConta() (valor fixo em R$ (0 = paga a soma dos pedidos))");
        Cliente comum = new ClienteComum("Ana Souza", "123.456.789-00");
        Cliente especial = new ClienteRodizio("Bruno Lima", "987.654.321-00");
        System.out.println("   ClienteComum -> " + comum.calcularConta());
        System.out.println("   ClienteRodizio -> " + especial.calcularConta());

        // 2) ASSOCIAÇÃO + ENCAPSULAMENTO: abre a Comanda pelo Gestor
        System.out.println("\n2) Abrindo um registro de Comanda");
        Comanda comanda = gestor.abrir(comum, 150.00);

        // 3) COMPOSIÇÃO: as partes nascem dentro do todo
        System.out.println("\n3) Composição — Comanda ◆ Pedido");
        comanda.adicionarPedido("Pedido 1", 50.00);
        comanda.adicionarPedido("Pedido 2", 30.00);
        System.out.println("   Partes (Pedido) criadas dentro de Comanda: " + comanda.getPedidos().size());

        // 4) AGREGAÇÃO: o grupo recebe algo que já existe
        System.out.println("\n4) Agregação — Cardapio ◇ Prato");
        Prato item = new Prato("Prato A");   // existe por conta própria
        Cardapio cardapio = new Cardapio("Cardapio principal");
        cardapio.adicionarPrato(item);   // recebe PRONTO (agregação)
        System.out.println("   Itens (Prato) agrupados em Cardapio: " + cardapio.getPratos().size());

        // 5) Avança etapa por etapa até o fim
        System.out.println("\n5) Avançando até a última etapa");
        while (!comanda.emEtapaFinal()) {
            gestor.avancar(comanda);
        }

        // 6) PRÉ-CONDIÇÕES / INVARIANTE sendo protegidos
        System.out.println("\n6) Tentativas inválidas");
        try {
            gestor.avancar(comanda);   // já está na última etapa
        } catch (IllegalStateException e) {
            System.out.println("   Recusado: " + e.getMessage());
        }
        try {
            gestor.abrir(especial, -10.00);   // valor negativo fere o invariante
        } catch (IllegalArgumentException e) {
            System.out.println("   Recusado: " + e.getMessage());
        }

        System.out.println("\nTotal de registros (Comanda): " + gestor.listar().size());
    }
}
