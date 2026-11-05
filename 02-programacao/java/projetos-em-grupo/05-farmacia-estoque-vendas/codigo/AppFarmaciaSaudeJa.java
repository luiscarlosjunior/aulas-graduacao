/**
 * AppFarmaciaSaudeJa — o fluxo completo do sistema Farmácia SaúdeJá.
 * Rode e leia: cada bloco numerado mostra uma parte do sistema funcionando.
 */
public class AppFarmaciaSaudeJa {
    public static void main(String[] args) {
        System.out.println("=== Farmácia SaúdeJá ===");

        // DIP: o Gestor recebe o contrato Notificador (poderia ser e-mail, SMS...)
        Notificador notificador = new NotificadorConsole();
        GestorVenda gestor = new GestorVenda(notificador);

        // 1) HERANÇA + POLIMORFISMO: dois tipos, a mesma pergunta, respostas diferentes
        System.out.println("\n1) Polimorfismo — exigeReceita() (true se o produto exige receita)");
        Produto comum = new ProdutoComum("Dipirona 500mg", 12.90, 30);
        Produto especial = new MedicamentoControlado("Ritalina 10mg", 45.50, 10);
        System.out.println("   ProdutoComum -> " + comum.exigeReceita());
        System.out.println("   MedicamentoControlado -> " + especial.exigeReceita());

        // 2) ASSOCIAÇÃO + ENCAPSULAMENTO: abre a Venda pelo Gestor
        System.out.println("\n2) Abrindo um registro de Venda");
        Venda venda = gestor.abrir(comum, 150.00);

        // 3) COMPOSIÇÃO: as partes nascem dentro do todo
        System.out.println("\n3) Composição — Venda ◆ ItemVenda");
        venda.adicionarItemVenda("ItemVenda 1", 50.00);
        venda.adicionarItemVenda("ItemVenda 2", 30.00);
        System.out.println("   Partes (ItemVenda) criadas dentro de Venda: " + venda.getItensVenda().size());

        // 4) AGREGAÇÃO: o grupo recebe algo que já existe
        System.out.println("\n4) Agregação — Categoria ◇ Produto");
        Produto item = new ProdutoComum("Paracetamol 750mg", 8.90, 50);   // existe por conta própria
        Categoria categoria = new Categoria("Categoria principal");
        categoria.adicionarProduto(item);   // recebe PRONTO (agregação)
        System.out.println("   Itens (Produto) agrupados em Categoria: " + categoria.getProdutos().size());

        // 5) Avança etapa por etapa até o fim
        System.out.println("\n5) Avançando até a última etapa");
        while (!venda.emEtapaFinal()) {
            gestor.avancar(venda);
        }

        // 6) PRÉ-CONDIÇÕES / INVARIANTE sendo protegidos
        System.out.println("\n6) Tentativas inválidas");
        try {
            gestor.avancar(venda);   // já está na última etapa
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
        Venda outra = gestor.abrir(especial, 99.90);
        gestor.cancelar(outra);
        try {
            gestor.avancar(outra);   // PRÉ-CONDIÇÃO falha: não se avança a partir de "cancelada"
        } catch (IllegalStateException e) {
            System.out.println("   Recusado: " + e.getMessage());
        }

        System.out.println("\nTotal de registros (Venda): " + gestor.listar().size());
    }
}
