/**
 * Programa Principal — Testando Todos os Operadores
 *
 * Este programa integra todos os tipos de operadores em
 * um exemplo prático de sistema de pedidos de e-commerce.
 *
 * Execute com:  javac *.java && java TesteOperadores
 *
 * @author Aulas Graduação
 * @version 2.0
 */
public class TesteOperadores {

    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════════════════════╗");
        System.out.println("║     TESTE COMPLETO DE OPERADORES — E-COMMERCE        ║");
        System.out.println("╚══════════════════════════════════════════════════════╝\n");

        System.out.println("Este programa integra TODOS os operadores em um caso real:\n");
        System.out.println("  • Operadores ARITMÉTICOS → calcular preços e impostos");
        System.out.println("  • Operadores LÓGICOS     → validar regras de negócio");
        System.out.println("  • Operadores BITWISE     → verificar permissões do usuário");
        System.out.println("  • Operadores ESPECIAIS   → código limpo com ternário\n");
        System.out.println("════════════════════════════════════════════════════════\n");

        // ============================================================
        // Dados do cenário
        // ============================================================
        String nomeCliente = "Ana Paula";
        boolean clienteVip = true;
        int permissoesUsuario = 0b0111;  // leitura + escrita + execução (não admin)
        int qtdItens = 3;
        double precoUnitario = 89.90;
        boolean cupomAplicado = true;
        double percentualCupom = 0.10;   // 10%
        double aliquotaImposto = 0.08;   // 8%

        // Permissões (reuso do exemplo de OperadoresBitwise)
        final int PERM_LEITURA  = 0b0001;
        final int PERM_ESCRITA  = 0b0010;
        final int PERM_EXECUCAO = 0b0100;
        final int PERM_ADMIN    = 0b1000;

        // ============================================================
        // ETAPA 1: Verificação de acesso (BITWISE + LÓGICO)
        // ============================================================
        System.out.println("── ETAPA 1: Verificação de Acesso ──────────────────────");
        boolean podeRealizarPedido = (permissoesUsuario & PERM_LEITURA) != 0
                                  && (permissoesUsuario & PERM_ESCRITA) != 0;
        boolean podeVerRelatorio   = (permissoesUsuario & PERM_ADMIN)   != 0;

        System.out.printf("  Cliente: %s%n", nomeCliente);
        System.out.printf("  Pode realizar pedido: %s%n",
            podeRealizarPedido ? "✅ Sim" : "❌ Não");
        System.out.printf("  Pode ver relatórios:  %s%n",
            podeVerRelatorio  ? "✅ Sim" : "❌ Não");
        System.out.println();

        if (!podeRealizarPedido) {
            System.out.println("❌ ACESSO NEGADO. Operação abortada.");
            return;
        }

        // ============================================================
        // ETAPA 2: Cálculo do pedido (ARITMÉTICO)
        // ============================================================
        System.out.println("── ETAPA 2: Cálculo do Pedido ──────────────────────────");

        double subtotal = qtdItens * precoUnitario;               // aritmético: *
        double desconto = cupomAplicado ? subtotal * percentualCupom : 0;  // ternário
        double subtotalComDesconto = subtotal - desconto;         // aritmético: -
        double imposto = subtotalComDesconto * aliquotaImposto;   // aritmético: *
        double total = subtotalComDesconto + imposto;             // aritmético: +

        System.out.printf("  Produto:              %d x R$%.2f = R$%.2f%n",
            qtdItens, precoUnitario, subtotal);
        System.out.printf("  Cupom (%.0f%%):        -R$%.2f%n",
            percentualCupom * 100, desconto);
        System.out.printf("  Subtotal c/desconto:  R$%.2f%n", subtotalComDesconto);
        System.out.printf("  Imposto (%.0f%%):       +R$%.2f%n",
            aliquotaImposto * 100, imposto);
        System.out.printf("  TOTAL DO PEDIDO:      R$%.2f%n", total);
        System.out.println();

        // ============================================================
        // ETAPA 3: Validação e decisão (LÓGICO + RELACIONAL)
        // ============================================================
        System.out.println("── ETAPA 3: Validação do Pedido ────────────────────────");

        boolean totalValido = total > 0 && total < 100000;        // lógico &&, relacional > <
        boolean clienteElegivelFrete = clienteVip || total >= 150; // lógico ||
        double frete = clienteElegivelFrete ? 0.0 : 14.90;         // ternário

        System.out.printf("  Total válido:         %b%n", totalValido);
        System.out.printf("  Frete grátis:         %s%n",
            clienteElegivelFrete ? "✅ Sim (VIP ou total ≥ R$150)" : "❌ Não");
        System.out.printf("  Frete cobrado:        R$%.2f%n", frete);

        double totalFinalComFrete = total + frete;                  // aritmético: +

        System.out.println();

        // ============================================================
        // ETAPA 4: Resumo Final
        // ============================================================
        System.out.println("── RESUMO FINAL ─────────────────────────────────────────");
        String statusPedido = totalValido ? "✅ APROVADO" : "❌ REJEITADO";  // ternário

        System.out.printf("  Status do pedido:     %s%n", statusPedido);
        System.out.printf("  Produtos:             R$%.2f%n", subtotal);
        System.out.printf("  Desconto cupom:       -R$%.2f%n", desconto);
        System.out.printf("  Impostos:             +R$%.2f%n", imposto);
        System.out.printf("  Frete:                R$%.2f%n", frete);
        System.out.println("  ──────────────────────────────────");
        System.out.printf("  💰 TOTAL A PAGAR:     R$%.2f%n", totalFinalComFrete);
        System.out.println();

        // Operador módulo: número do pedido baseado em contador
        int contadorPedidos = 10047;
        int numeroPedido = contadorPedidos % 10000;  // módulo: últimos 4 dígitos
        System.out.printf("  Número do pedido:     #%04d%n", numeroPedido);

        System.out.println("\n════════════════════════════════════════════════════════");
        System.out.println("  Execute os exemplos individuais para ver cada operador");
        System.out.println("  em detalhe:");
        System.out.println("    java OperadoresAritmeticos");
        System.out.println("    java OperadoresLogicos");
        System.out.println("    java OperadoresBitwise");
        System.out.println("════════════════════════════════════════════════════════");
    }
}
