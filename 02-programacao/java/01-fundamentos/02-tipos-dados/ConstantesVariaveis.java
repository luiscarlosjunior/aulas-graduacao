/**
 * Constantes e Boas Práticas de Variáveis em Java
 *
 * ============================================================
 * POR QUE ISSO IMPORTA NA INDÚSTRIA?
 * ============================================================
 * Em sistemas reais — como plataformas de e-commerce, sistemas bancários,
 * ERPs — usar constantes corretamente é fundamental para:
 *
 *  1. MANUTENIBILIDADE: Se o valor do IPI muda de 10% para 12%, você
 *     altera em UM lugar, não em 200 arquivos espalhados pelo projeto.
 *
 *  2. LEGIBILIDADE: `if (status == PEDIDO_CANCELADO)` é muito mais claro
 *     do que `if (status == 3)` — ninguém sabe o que significa "3"!
 *
 *  3. PREVENÇÃO DE BUGS: "Magic numbers" (números mágicos sem contexto)
 *     são uma das causas mais comuns de bugs em sistemas legados.
 *
 * Exemplo real: Em 2012, um bug em um sistema de negociação da Knight
 * Capital causou prejuízo de US$ 440 milhões em 45 minutos por conta
 * de valores "hard-coded" (embutidos diretamente no código) que foram
 * atualizados incorretamente.
 * ============================================================
 *
 * @author Aulas Graduação
 * @version 2.0
 */
public class ConstantesVariaveis {

    // ============================================================
    // CONSTANTES DE CLASSE (static final)
    // Usadas em toda a aplicação — defina-as no nível da classe
    // ============================================================

    // Regras de negócio: taxas fiscais
    // Em produção, essas constantes seriam lidas de um arquivo de configuração
    // ou banco de dados, mas o conceito de nomear corretamente é o mesmo.
    public static final double ALIQUOTA_IPI = 0.10;          // 10% de Imposto sobre Produtos Industrializados
    public static final double ALIQUOTA_ICMS_SP = 0.18;      // 18% de ICMS no Estado de São Paulo
    public static final double ALIQUOTA_ISS = 0.05;          // 5% de ISS (serviços)

    // Limites do sistema
    public static final int MAX_TENTATIVAS_LOGIN = 3;        // Segurança: bloquear após 3 tentativas
    public static final int TIMEOUT_SESSAO_MINUTOS = 30;     // Sessão expira em 30 minutos
    public static final int MAX_ITENS_CARRINHO = 50;         // Limite de itens no carrinho

    // Status de pedidos (em vez de números mágicos)
    // Sem constantes: `if (status == 1)` — o que é 1?
    // Com constantes: `if (status == STATUS_AGUARDANDO_PAGAMENTO)` — claro!
    public static final int STATUS_AGUARDANDO_PAGAMENTO = 1;
    public static final int STATUS_PAGAMENTO_CONFIRMADO = 2;
    public static final int STATUS_EM_SEPARACAO = 3;
    public static final int STATUS_ENVIADO = 4;
    public static final int STATUS_ENTREGUE = 5;
    public static final int STATUS_CANCELADO = 6;

    // Mensagens padronizadas (evita erros de digitação e facilita tradução)
    public static final String MSG_ERRO_SALDO_INSUFICIENTE = "Saldo insuficiente para realizar a operação.";
    public static final String MSG_SUCESSO_PAGAMENTO = "Pagamento realizado com sucesso!";
    public static final String MSG_CONTA_BLOQUEADA = "Conta bloqueada por excesso de tentativas.";

    // Versão da API
    public static final String VERSAO_API = "2.1.0";

    public static void main(String[] args) {
        System.out.println("=== CONSTANTES E BOAS PRÁTICAS DE VARIÁVEIS ===\n");

        demonstrarConstantesFinanceiras();
        demonstrarStatusPedido();
        demonstrarBoasPraticas();
        demonstrarNomeacaoVariaveis();
    }

    // ----------------------------------------------------------
    // DEMONSTRAÇÃO 1: Constantes Financeiras
    // ----------------------------------------------------------
    static void demonstrarConstantesFinanceiras() {
        System.out.println("--- 1. CÁLCULOS FINANCEIROS COM CONSTANTES ---");

        double precoBase = 1000.00; // Notebook, por exemplo

        // Cálculo de impostos usando constantes nomeadas
        double valorIPI = precoBase * ALIQUOTA_IPI;
        double valorICMS = precoBase * ALIQUOTA_ICMS_SP;
        double precoFinal = precoBase + valorIPI + valorICMS;

        System.out.printf("  Produto: Notebook%n");
        System.out.printf("  Preço base:  R$ %8.2f%n", precoBase);
        System.out.printf("  IPI (%.0f%%):  R$ %8.2f%n", ALIQUOTA_IPI * 100, valorIPI);
        System.out.printf("  ICMS (%.0f%%): R$ %8.2f%n", ALIQUOTA_ICMS_SP * 100, valorICMS);
        System.out.printf("  Preço final: R$ %8.2f%n", precoFinal);

        System.out.println();
        System.out.println("  >> Quando a lei mudar a alíquota do IPI para 12%, você altera");
        System.out.println("     APENAS a constante ALIQUOTA_IPI. Sem essa constante, você");
        System.out.println("     precisaria buscar todos os '0.10' no projeto — arriscado!");
        System.out.println();
    }

    // ----------------------------------------------------------
    // DEMONSTRAÇÃO 2: Status de Pedido
    // ----------------------------------------------------------
    static void demonstrarStatusPedido() {
        System.out.println("--- 2. STATUS DE PEDIDO (SEM NÚMEROS MÁGICOS) ---");

        // Simula alguns pedidos com diferentes status
        int[] statusPedidos = {
            STATUS_AGUARDANDO_PAGAMENTO,
            STATUS_PAGAMENTO_CONFIRMADO,
            STATUS_ENVIADO,
            STATUS_CANCELADO
        };

        int[] numeroPedidos = { 1001, 1002, 1003, 1004 };

        System.out.println("  Pedidos em processamento:");
        for (int i = 0; i < statusPedidos.length; i++) {
            String descricao = obterDescricaoStatus(statusPedidos[i]);
            System.out.printf("  Pedido #%d → %s%n", numeroPedidos[i], descricao);
        }

        System.out.println();
        System.out.println("  >> Compare:");
        System.out.println("     RUIM:  if (status == 4) → O que é 4?!");
        System.out.println("     BOM:   if (status == STATUS_ENVIADO) → Claro e explícito!");
        System.out.println();
    }

    // ----------------------------------------------------------
    // DEMONSTRAÇÃO 3: Boas Práticas de Nomeação de Variáveis
    // ----------------------------------------------------------
    static void demonstrarBoasPraticas() {
        System.out.println("--- 3. BOAS PRÁTICAS DE NOMEAÇÃO ---");

        // RUIM: nomes sem significado (evite!)
        // int x = 5;           // O que é x?
        // double d = 19.90;    // O que é d?
        // boolean b = true;    // O que significa true aqui?

        // BOM: nomes descritivos
        int quantidadeItensCarrinho = 5;
        double precoUnitarioProduto = 19.90;
        boolean usuarioEstaAutenticado = true;
        boolean estoqueDisponivel = true;

        System.out.printf("  Itens no carrinho: %d%n", quantidadeItensCarrinho);
        System.out.printf("  Preço por item: R$ %.2f%n", precoUnitarioProduto);
        System.out.printf("  Usuário logado: %b%n", usuarioEstaAutenticado);
        System.out.printf("  Estoque disponível: %b%n", estoqueDisponivel);

        // Constante local (final em variável local)
        final double FRETE_GRATIS_ACIMA_DE = 150.00;
        double totalCompra = quantidadeItensCarrinho * precoUnitarioProduto;
        double frete = totalCompra >= FRETE_GRATIS_ACIMA_DE ? 0.00 : 15.90;

        System.out.printf("%n  Total da compra: R$ %.2f%n", totalCompra);
        System.out.printf("  Frete: R$ %.2f%s%n", frete,
            frete == 0 ? " 🎉 (frete grátis!)" : "");

        System.out.println();
    }

    // ----------------------------------------------------------
    // DEMONSTRAÇÃO 4: Convenções de Nomeação em Java
    // ----------------------------------------------------------
    static void demonstrarNomeacaoVariaveis() {
        System.out.println("--- 4. CONVENÇÕES DE NOMEAÇÃO EM JAVA ---");

        System.out.println("  Variáveis e métodos → camelCase:");
        System.out.println("    ✅ nomeCompleto, calcularImposto, totalVendas");
        System.out.println("    ❌ NomeCompleto, calcular_imposto, total_vendas");

        System.out.println();
        System.out.println("  Classes → PascalCase (UpperCamelCase):");
        System.out.println("    ✅ ContaBancaria, ProcessadorPagamento, ListaDeProdutos");
        System.out.println("    ❌ contaBancaria, processador_pagamento, listadeprodutos");

        System.out.println();
        System.out.println("  Constantes → UPPER_SNAKE_CASE:");
        System.out.println("    ✅ MAX_TENTATIVAS_LOGIN, ALIQUOTA_IPI, STATUS_CANCELADO");
        System.out.println("    ❌ maxTentativasLogin, aliquotaIPI, statusCancelado");

        System.out.println();
        System.out.println("  Pacotes → letras minúsculas:");
        System.out.println("    ✅ br.com.empresa.servico, com.loja.modelo");
        System.out.println("    ❌ br.com.Empresa.Servico, com.Loja.Modelo");

        System.out.println();
        System.out.println("  >> Seguir convenções é obrigatório em times profissionais.");
        System.out.println("     Code reviews rejeitem código que não segue o padrão.");
    }

    // Método auxiliar para converter código de status em descrição legível
    static String obterDescricaoStatus(int status) {
        if (status == STATUS_AGUARDANDO_PAGAMENTO) return "⏳ Aguardando Pagamento";
        if (status == STATUS_PAGAMENTO_CONFIRMADO) return "✅ Pagamento Confirmado";
        if (status == STATUS_EM_SEPARACAO)         return "📦 Em Separação";
        if (status == STATUS_ENVIADO)              return "🚚 Enviado";
        if (status == STATUS_ENTREGUE)             return "✔️  Entregue";
        if (status == STATUS_CANCELADO)            return "❌ Cancelado";
        return "❓ Status Desconhecido";
    }
}
