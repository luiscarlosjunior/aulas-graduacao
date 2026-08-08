/**
 * Programa principal do exemplo complexo da pizzaria.
 *
 * Demonstra, num único fluxo, TODOS os relacionamentos modelados:
 *   - composição  (Pedido cria ItemPedidos)
 *   - agregação   (ItemPedido usa Pizzas do cardápio)
 *   - associação  (Pedido conhece o Cliente)
 *   - herança     (ClienteVip é um Cliente)
 *   - abstração + polimorfismo (Pagamento e suas formas)
 *
 * Compile e rode:
 *     javac *.java
 *     java Principal
 */
public class Principal {
    public static void main(String[] args) {
        // --- Cardápio (pizzas existem independentemente dos pedidos: agregação) ---
        Pizza margherita = new Pizza("Margherita", "Grande", 45.00);
        Pizza calabresa   = new Pizza("Calabresa", "Média", 38.00);
        Pizza portuguesa  = new Pizza("Portuguesa", "Grande", 52.00);

        // ============ Pedido 1: cliente comum ============
        Cliente joao = new Cliente("João");
        Pedido pedido1 = new Pedido(joao);
        pedido1.adicionar(margherita, 1);
        pedido1.adicionar(calabresa, 2);
        pedido1.imprimirNota();

        // Polimorfismo no pagamento: tratamos tudo como "Pagamento",
        // mas cada objeto sabe pagar do seu jeito.
        Pagamento pagamento1 = new Pix();
        pagamento1.pagar(pedido1.calcularTotal());

        System.out.println();

        // ============ Pedido 2: cliente VIP (ganha 10%) ============
        Cliente maria = new ClienteVip("Maria (VIP)");
        Pedido pedido2 = new Pedido(maria);
        pedido2.adicionar(portuguesa, 1);
        pedido2.adicionar(margherita, 1);
        pedido2.imprimirNota(); // total já vem com desconto de 10%

        Pagamento pagamento2 = new Cartao(3);
        pagamento2.pagar(pedido2.calcularTotal());

        System.out.println();

        // ============ Pedido 3: pagamento em dinheiro com troco ============
        Pedido pedido3 = new Pedido(new Cliente("Carla"));
        pedido3.adicionar(calabresa, 1);
        pedido3.imprimirNota();

        Pagamento pagamento3 = new Dinheiro(50.00);
        pagamento3.pagar(pedido3.calcularTotal());
    }
}
