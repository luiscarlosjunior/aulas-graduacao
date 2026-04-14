/**
 * Estruturas de Repetição em Java — com Exemplos da Indústria
 *
 * ============================================================
 * POR QUE LOOPS IMPORTAM NA INDÚSTRIA?
 * ============================================================
 * Loops estão em todo lugar em sistemas reais:
 *
 *  - Processar lotes de pagamentos (batch processing)
 *  - Ler linhas de um arquivo CSV/Excel com milhões de registros
 *  - Enviar e-mails para uma lista de contatos
 *  - Calcular relatórios mensais para todos os clientes
 *  - Tentar reconectar ao banco de dados com retry exponencial
 *  - Validar cada item de um carrinho de compras
 *
 * Escolher o loop errado pode custar performance e até derrubar sistemas.
 * O loop mais rápido em Java é o for clássico com acesso por índice,
 * porém for-each e streams são preferidos por legibilidade.
 * ============================================================
 *
 * @author Aulas Graduação
 * @version 2.0
 */
public class EstruturaRepeticao {

    public static void main(String[] args) {
        System.out.println("=== ESTRUTURAS DE REPETIÇÃO — EXEMPLOS DA INDÚSTRIA ===\n");

        loopForClassico();
        loopForEach();
        loopWhile();
        loopDoWhile();
        breakEContinue();
        loopsAninhados();
    }

    // ----------------------------------------------------------
    // 1. for clássico — quando precisa do índice
    // ----------------------------------------------------------
    static void loopForClassico() {
        System.out.println("--- 1. FOR CLÁSSICO: Processamento de Notas Fiscais ---");

        // Simula processamento de notas fiscais em lote (batch)
        double[] valoresNF = {1250.00, 890.50, 3400.00, 145.90, 2100.00};
        String[] fornecedores = {"Fornecedor A", "Fornecedor B", "Fornecedor C",
                                  "Fornecedor D", "Fornecedor E"};

        System.out.println("  Processando lote de notas fiscais:");
        double totalGeral = 0;
        int notasAprovadas = 0;
        final double LIMITE_APROVACAO_DIRETA = 2000.0; // Acima disso precisa de aprovação gerencial

        for (int i = 0; i < valoresNF.length; i++) {
            double valor = valoresNF[i];
            totalGeral += valor;

            String status;
            if (valor <= LIMITE_APROVACAO_DIRETA) {
                status = "✅ Aprovada automaticamente";
                notasAprovadas++;
            } else {
                status = "⏳ Aguarda aprovação gerencial";
            }

            System.out.printf("  NF %02d | %s | R$ %8.2f → %s%n",
                (i + 1), fornecedores[i], valor, status);
        }

        System.out.printf("%n  Total processado: R$ %.2f em %d NFs%n",
            totalGeral, valoresNF.length);
        System.out.printf("  Aprovadas: %d | Pendentes: %d%n%n",
            notasAprovadas, valoresNF.length - notasAprovadas);
    }

    // ----------------------------------------------------------
    // 2. for-each — quando não precisa do índice (mais legível)
    // ----------------------------------------------------------
    static void loopForEach() {
        System.out.println("--- 2. FOR-EACH: Validação de Carrinho de Compras ---");

        // Array de preços dos itens no carrinho
        double[] itensCarrinho = {29.90, 149.00, 89.90, 15.50, 299.00};
        String[] nomesItens = {"Livro Java", "Teclado Mecânico", "Mouse Gamer",
                               "Caneta", "Monitor HD"};

        System.out.println("  Itens no carrinho:");
        double subtotal = 0;
        int itemCount = 0;

        // for-each: mais limpo quando não precisa do índice
        for (double preco : itensCarrinho) {
            System.out.printf("  • %s: R$ %.2f%n", nomesItens[itemCount], preco);
            subtotal += preco;
            itemCount++;
        }

        System.out.printf("%n  Subtotal: R$ %.2f%n", subtotal);
        System.out.printf("  Frete:    R$ %.2f%n", subtotal >= 250 ? 0.0 : 14.90);
        System.out.println();
        System.out.println("  >> for-each é a forma recomendada quando você não precisa");
        System.out.println("     do índice. É menos verboso e não permite erros como");
        System.out.println("     off-by-one (esquecer o '<' vs '<=').");
        System.out.println();
    }

    // ----------------------------------------------------------
    // 3. while — quando não sabe quantas iterações
    // ----------------------------------------------------------
    static void loopWhile() {
        System.out.println("--- 3. WHILE: Retry com Backoff Exponencial ---");
        System.out.println("  (Padrão usado em microserviços e integração com APIs externas)");
        System.out.println();

        // Simula tentativa de conexão com serviço externo (ex: API do Banco)
        // Em produção real, você chamaria o serviço HTTP aqui
        int tentativa = 0;
        final int MAX_TENTATIVAS = 5;
        boolean conexaoEstabelecida = false;
        int esperaMsBase = 100; // 100ms na primeira tentativa

        // Simula falha nas primeiras 3 tentativas (como um serviço instável)
        int tentativaEmQueConecta = 4;

        while (!conexaoEstabelecida && tentativa < MAX_TENTATIVAS) {
            tentativa++;
            long esperaMs = (long)(esperaMsBase * Math.pow(2, tentativa - 1)); // backoff exponencial

            System.out.printf("  Tentativa %d/%d — esperando %dms antes de tentar...%n",
                tentativa, MAX_TENTATIVAS, esperaMs);

            // Simula a chamada ao serviço
            conexaoEstabelecida = tentativa >= tentativaEmQueConecta;

            if (!conexaoEstabelecida) {
                System.out.println("    ❌ Falhou. Serviço indisponível.");
            }
        }

        if (conexaoEstabelecida) {
            System.out.println("  ✅ Conexão estabelecida na tentativa " + tentativa + "!");
        } else {
            System.out.println("  ❌ ERRO CRÍTICO: Número máximo de tentativas atingido.");
            System.out.println("     Enviando alerta para o time de operações...");
        }

        System.out.println();
        System.out.println("  >> Retry com Backoff Exponencial é padrão em:");
        System.out.println("     • Comunicação entre microserviços");
        System.out.println("     • Chamadas a APIs externas (pagamento, CEP, NFe)");
        System.out.println("     • Reconexão com banco de dados");
        System.out.println("     Frameworks como Resilience4j, Spring Retry implementam isso.");
        System.out.println();
    }

    // ----------------------------------------------------------
    // 4. do-while — executa pelo menos uma vez
    // ----------------------------------------------------------
    static void loopDoWhile() {
        System.out.println("--- 4. DO-WHILE: Menu de Opções ---");
        System.out.println("  (do-while garante que o menu apareça pelo menos uma vez)");
        System.out.println();

        // Simula um menu com opções (em um sistema real, leria do Scanner)
        // Aqui simulamos a seleção de "3" (relatório) e depois "0" (sair)
        int[] simulacaoEntradas = {3, 0};
        int indiceSimulacao = 0;

        int opcao;
        do {
            System.out.println("  ┌─────────────────────────────┐");
            System.out.println("  │  SISTEMA DE VENDAS v2.0     │");
            System.out.println("  ├─────────────────────────────┤");
            System.out.println("  │  1. Novo Pedido             │");
            System.out.println("  │  2. Consultar Estoque       │");
            System.out.println("  │  3. Relatório do Dia        │");
            System.out.println("  │  0. Sair                    │");
            System.out.println("  └─────────────────────────────┘");

            opcao = simulacaoEntradas[indiceSimulacao++];
            System.out.println("  Opção selecionada (simulada): " + opcao);

            switch (opcao) {
                case 1: System.out.println("  → Abrindo formulário de pedido..."); break;
                case 2: System.out.println("  → Consultando estoque no banco de dados..."); break;
                case 3: System.out.println("  → Gerando relatório: 47 pedidos, R$ 15.420,00"); break;
                case 0: System.out.println("  → Encerrando sessão do usuário..."); break;
                default: System.out.println("  → ❌ Opção inválida!");
            }

        } while (opcao != 0 && indiceSimulacao < simulacaoEntradas.length);

        System.out.println("  Sistema encerrado.");
        System.out.println();
        System.out.println("  >> do-while é ideal para menus e prompts, pois garante");
        System.out.println("     que o bloco execute pelo menos uma vez antes de checar");
        System.out.println("     a condição de saída.");
        System.out.println();
    }

    // ----------------------------------------------------------
    // 5. break e continue — controle dentro do loop
    // ----------------------------------------------------------
    static void breakEContinue() {
        System.out.println("--- 5. BREAK E CONTINUE: Processamento Seletivo ---");

        // Simula busca em catálogo de produtos com filtros
        String[] categorias   = {"Eletrônico", "Roupa", "Eletrônico", "Alimento",
                                  "Eletrônico", "Roupa", "Alimento", "Eletrônico"};
        double[] precos       = {1200.0, 89.90, 350.0, 12.50,
                                  5500.0, 150.0, 8.90, 899.0};
        boolean[] emEstoque   = {true, true, false, true,
                                  true, false, true, true};
        String[] nomes        = {"Smart TV", "Camisa", "Fone BT", "Feijão 1kg",
                                  "MacBook", "Calça", "Arroz 5kg", "iPhone 15"};

        // Busca: eletrônicos em estoque com preço até R$2000
        System.out.println("  Busca: Eletrônicos em estoque até R$2.000");
        System.out.println();

        int encontrados = 0;
        for (int i = 0; i < nomes.length; i++) {
            // continue: pula categorias que não são eletrônica
            if (!categorias[i].equals("Eletrônico")) {
                continue;  // vai para o próximo item
            }

            // continue: pula itens sem estoque
            if (!emEstoque[i]) {
                System.out.printf("  ⚠️  %s: sem estoque, pulando...%n", nomes[i]);
                continue;  // vai para o próximo item
            }

            // continue: pula itens acima do orçamento
            if (precos[i] > 2000.0) {
                System.out.printf("  💸 %s: R$%.0f — acima do orçamento%n",
                    nomes[i], precos[i]);
                continue;
            }

            // Se chegou aqui, o produto atende todos os critérios
            System.out.printf("  ✅ %s: R$ %.2f%n", nomes[i], precos[i]);
            encontrados++;

            // break: interrompe após encontrar 2 resultados (paginação!)
            if (encontrados >= 2) {
                System.out.println("  (Mostrando apenas os primeiros 2 — página 1 de ?)");
                break;
            }
        }

        System.out.println();
        System.out.println("  >> break e continue são usados para:");
        System.out.println("     • Paginação: parar após N resultados");
        System.out.println("     • Filtros: pular registros que não atendem critério");
        System.out.println("     • Fail-fast: parar ao encontrar o primeiro erro");
        System.out.println();
    }

    // ----------------------------------------------------------
    // 6. Loops aninhados — relatório matricial
    // ----------------------------------------------------------
    static void loopsAninhados() {
        System.out.println("--- 6. LOOPS ANINHADOS: Relatório de Vendas por Região/Mês ---");

        String[] regioes = {"Sul", "Sudeste", "Nordeste"};
        String[] meses   = {"Jan", "Fev", "Mar"};

        // Dados simulados: vendas[região][mês]
        double[][] vendas = {
            {45000.0, 52000.0, 38000.0},   // Sul
            {120000.0, 135000.0, 98000.0}, // Sudeste
            {67000.0, 71000.0, 55000.0},   // Nordeste
        };

        // Cabeçalho
        System.out.print("  Região       ");
        for (String mes : meses) {
            System.out.printf("│  %-10s", mes);
        }
        System.out.println("│  TOTAL");
        System.out.println("  " + "─".repeat(60));

        // Linhas de dados
        double totalGeral = 0;
        for (int r = 0; r < regioes.length; r++) {
            double totalRegiao = 0;
            System.out.printf("  %-12s", regioes[r]);

            for (int m = 0; m < meses.length; m++) {
                System.out.printf("│ R$%8.0f", vendas[r][m]);
                totalRegiao += vendas[r][m];
            }

            System.out.printf("│ R$%8.0f%n", totalRegiao);
            totalGeral += totalRegiao;
        }

        System.out.println("  " + "─".repeat(60));
        System.out.printf("  %-12s│ R$%8.0f│ R$%8.0f│ R$%8.0f│ R$%8.0f%n",
            "TOTAL", vendas[0][0]+vendas[1][0]+vendas[2][0],
            vendas[0][1]+vendas[1][1]+vendas[2][1],
            vendas[0][2]+vendas[1][2]+vendas[2][2],
            totalGeral);

        System.out.println();
        System.out.println("  >> Loops aninhados são a base de relatórios matriciais,");
        System.out.println("     processamento de imagens (pixel por pixel), algoritmos");
        System.out.println("     de ordenação, e manipulação de planilhas/Excel via Java.");
        System.out.println("     ⚠️  Cuidado: O(n²) — para n grande, use streams paralelos.");
        System.out.println();
    }
}
