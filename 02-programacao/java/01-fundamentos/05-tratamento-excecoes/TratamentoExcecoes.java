/**
 * Tratamento de Exceções em Java — com Exemplos da Indústria
 *
 * ============================================================
 * POR QUE TRATAMENTO DE EXCEÇÕES IMPORTA NA INDÚSTRIA?
 * ============================================================
 * Erros VÃO acontecer. Sempre. A diferença entre um sistema
 * amador e um sistema profissional está em COMO lidar com eles:
 *
 *  ❌ Sistema amador: trava, mostra stack trace para o usuário,
 *     corrompe dados, perde transações bancárias
 *
 *  ✅ Sistema profissional:
 *     - Captura o erro no momento certo
 *     - Registra no log com contexto (qual usuário, qual operação)
 *     - Desfaz transação incompleta (rollback)
 *     - Retorna mensagem amigável ao usuário
 *     - Envia alerta para o time de engenharia
 *     - O sistema CONTINUA funcionando para outros usuários
 *
 * Exemplo real: Em 2003, um NullPointerException não tratado no
 * sistema de negociação do Northeast Blackout of 2003 contribuiu
 * para o maior apagão da história norte-americana.
 *
 * HIERARQUIA DE EXCEÇÕES JAVA:
 * Throwable
 * ├── Error (graves, sistema, não pegue!)
 * │   ├── OutOfMemoryError
 * │   ├── StackOverflowError
 * │   └── ...
 * └── Exception
 *     ├── RuntimeException (unchecked — não obriga try-catch)
 *     │   ├── NullPointerException
 *     │   ├── ArrayIndexOutOfBoundsException
 *     │   ├── ClassCastException
 *     │   ├── NumberFormatException
 *     │   └── IllegalArgumentException
 *     └── IOException e outras (checked — obriga try-catch)
 *         ├── FileNotFoundException
 *         ├── SQLException
 *         └── ...
 * ============================================================
 *
 * @author Aulas Graduação
 * @version 2.0
 */
public class TratamentoExcecoes {

    public static void main(String[] args) {
        System.out.println("=== TRATAMENTO DE EXCEÇÕES — EXEMPLOS DA INDÚSTRIA ===\n");

        exemploTryCatch();
        multiplasExcecoes();
        finallyBlock();
        excecoesPersonalizadas();
        boasPraticas();
    }

    // ----------------------------------------------------------
    // 1. try-catch básico
    // ----------------------------------------------------------
    static void exemploTryCatch() {
        System.out.println("--- 1. TRY-CATCH: Convertendo Dados de API Externa ---");
        System.out.println("  Cenário: recebendo dados JSON de uma API de CEP");
        System.out.println();

        // Simula dados recebidos de uma API (alguns podem ser inválidos)
        String[] cepsRecebidos = {"01310-100", "INVALIDO", "04534-011", null, "02311999"};
        String[] populacoes    = {"12325000", "abc123", "950000", "840000", "1200000"};

        for (int i = 0; i < cepsRecebidos.length; i++) {
            String cep = cepsRecebidos[i];
            String populacaoStr = populacoes[i];

            try {
                // Pode lançar NullPointerException se cep for null
                String cepFormatado = cep.replace("-", "");

                // Pode lançar NumberFormatException se não for número
                int populacao = Integer.parseInt(populacaoStr);

                System.out.printf("  ✅ CEP %s: população %,d hab%n",
                    cep, populacao);

            } catch (NullPointerException e) {
                // CEP nulo — dado faltando na API
                System.out.printf("  ❌ CEP #%d: dado nulo recebido — usando valor padrão%n", i+1);

            } catch (NumberFormatException e) {
                // População não é um número — dado corrompido
                System.out.printf("  ❌ CEP %s: população '%s' inválida — ignorando%n",
                    cep, populacaoStr);
            }
            // O loop CONTINUA para o próximo item mesmo com erro!
        }

        System.out.println();
        System.out.println("  >> Sem try-catch, o primeiro erro quebraria o processamento");
        System.out.println("     de todos os outros CEPs. Com try-catch, o sistema trata");
        System.out.println("     cada erro individualmente e continua processando.");
        System.out.println();
    }

    // ----------------------------------------------------------
    // 2. Múltiplas exceções e a ordem importa
    // ----------------------------------------------------------
    static void multiplasExcecoes() {
        System.out.println("--- 2. MÚLTIPLAS EXCEÇÕES: Processamento de Pagamento ---");

        // Testa diferentes cenários de erro
        processarPagamento("João Silva", 500.0, "4111111111111111");   // válido
        processarPagamento(null, 500.0, "4111111111111111");            // sem nome
        processarPagamento("Maria", -100.0, "4111111111111111");        // valor negativo
        processarPagamento("Pedro", 10000.0, "invalido");               // cartão inválido

        System.out.println();
    }

    static void processarPagamento(String cliente, double valor, String cartao) {
        try {
            // Validações que podem lançar exceções
            validarCliente(cliente);
            validarValor(valor);
            validarCartao(cartao);

            // Se chegou aqui, tudo está válido
            System.out.printf("  ✅ Pagamento de R$%.2f aprovado para %s (cartão ...%s)%n",
                valor, cliente, cartao.substring(cartao.length() - 4));

        } catch (IllegalArgumentException e) {
            // Dados de negócio inválidos (nós criamos essa exceção)
            System.out.printf("  ❌ Dados inválidos: %s%n", e.getMessage());

        } catch (SecurityException e) {
            // Problema de segurança
            System.out.printf("  🔒 Falha de segurança: %s%n", e.getMessage());

        } catch (Exception e) {
            // Captura qualquer outra exceção inesperada — sempre por último!
            System.out.printf("  ⚠️  Erro inesperado: %s%n", e.getClass().getSimpleName());
        }
    }

    static void validarCliente(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome do cliente é obrigatório");
        }
    }

    static void validarValor(double valor) {
        if (valor <= 0) {
            throw new IllegalArgumentException("Valor deve ser maior que zero: " + valor);
        }
        if (valor > 5000) {
            throw new SecurityException("Transação acima do limite. Requer autenticação adicional.");
        }
    }

    static void validarCartao(String cartao) {
        if (cartao == null || cartao.length() != 16) {
            throw new IllegalArgumentException("Cartão inválido — deve ter 16 dígitos");
        }
        // Validação simplificada (em produção usaria algoritmo de Luhn)
        try {
            Long.parseLong(cartao);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Cartão contém caracteres inválidos");
        }
    }

    // ----------------------------------------------------------
    // 3. finally — garante execução (fechar conexões, liberar recursos)
    // ----------------------------------------------------------
    static void finallyBlock() {
        System.out.println("--- 3. FINALLY: Garantindo Fechamento de Recursos ---");
        System.out.println("  Cenário: lendo configurações do banco de dados");
        System.out.println();

        // Em código real, seria uma conexão com banco de dados ou arquivo
        simularLeituraArquivo("configuracoes.properties");
        simularLeituraArquivo("arquivo_que_nao_existe.txt");

        System.out.println();
        System.out.println("  >> finally é essencial para liberar recursos:");
        System.out.println("     • Fechar conexões com banco de dados");
        System.out.println("     • Fechar arquivos abertos (FileReader, FileWriter)");
        System.out.println("     • Liberar locks de sincronização");
        System.out.println("     >> Hoje em dia, use 'try-with-resources' (Java 7+) que");
        System.out.println("        faz isso automaticamente! Ver próximo exemplo.");
        System.out.println();
    }

    static void simularLeituraArquivo(String nomeArquivo) {
        boolean conexaoAberta = false;

        try {
            System.out.printf("  Abrindo arquivo: %s...%n", nomeArquivo);
            conexaoAberta = true;

            // Simula erro se arquivo não existe
            if (nomeArquivo.contains("nao_existe")) {
                throw new RuntimeException("Arquivo não encontrado: " + nomeArquivo);
            }

            System.out.println("  Lendo configurações... OK");
            System.out.println("  db.url=jdbc:mysql://localhost/meubd");

        } catch (RuntimeException e) {
            System.out.println("  ❌ Erro: " + e.getMessage());
            System.out.println("  Usando configurações padrão...");

        } finally {
            // SEMPRE executado — seja sucesso ou exceção!
            if (conexaoAberta) {
                System.out.println("  → Fechando arquivo (finally garantiu isso!)");
                conexaoAberta = false;
            }
        }
        System.out.println();
    }

    // ----------------------------------------------------------
    // 4. Exceções Customizadas
    // ----------------------------------------------------------
    static void excecoesPersonalizadas() {
        System.out.println("--- 4. EXCEÇÕES CUSTOMIZADAS: Sistema de Pedidos ---");
        System.out.println("  Por que criar suas próprias exceções?");
        System.out.println("  → Mensagens de erro específicas do seu domínio de negócio");
        System.out.println("  → Código mais expressivo e fácil de entender");
        System.out.println("  → Facilita logging e monitoramento em produção");
        System.out.println();

        // Testa diferentes erros de negócio
        testarPedido("P001", 5, "Notebook");       // OK
        testarPedido("P002", 0, "Mouse");           // sem estoque
        testarPedido("P003", 3, "Produto Proibido"); // produto bloqueado

        System.out.println();
    }

    static void testarPedido(String numeroPedido, int quantidade, String produto) {
        try {
            processarPedido(numeroPedido, quantidade, produto);
            System.out.printf("  ✅ Pedido %s aprovado: %d x %s%n",
                numeroPedido, quantidade, produto);

        } catch (EstoqueInsuficienteException e) {
            System.out.printf("  📦 Pedido %s: %s (disponível: %d)%n",
                numeroPedido, e.getMessage(), e.getQuantidadeDisponivel());

        } catch (ProdutoBloqueadoException e) {
            System.out.printf("  🚫 Pedido %s: Produto bloqueado — %s%n",
                numeroPedido, e.getMessage());
        }
    }

    static void processarPedido(String numeroPedido, int quantidade, String produto)
            throws EstoqueInsuficienteException, ProdutoBloqueadoException {

        // Simula verificações de estoque e regras de negócio
        if (produto.equals("Produto Proibido")) {
            throw new ProdutoBloqueadoException(
                "Produto requer aprovação especial para venda");
        }

        if (quantidade <= 0) {
            throw new EstoqueInsuficienteException(
                "Produto sem estoque disponível", 0);
        }
    }

    // ----------------------------------------------------------
    // 5. Boas práticas
    // ----------------------------------------------------------
    static void boasPraticas() {
        System.out.println("--- 5. BOAS PRÁTICAS DE TRATAMENTO DE EXCEÇÕES ---");

        System.out.println("  ✅ O que fazer:");
        System.out.println("     • Capture exceções específicas (não só Exception genérica)");
        System.out.println("     • Sempre registre no log com contexto (usuário, operação)");
        System.out.println("     • Crie exceções customizadas para erros de negócio");
        System.out.println("     • Use try-with-resources para fechar recursos automaticamente");
        System.out.println("     • Nunca deixe catch vazio — pelo menos faça um log");
        System.out.println();

        System.out.println("  ❌ O que NÃO fazer:");
        System.out.println("     • catch (Exception e) {} → ← catch vazio: bug silencioso!");
        System.out.println("     • Mostrar stack trace para o usuário final");
        System.out.println("     • Usar exceções para controle de fluxo normal");
        System.out.println("     • Ignorar exceções checked sem tratamento adequado");
        System.out.println("     • Pegar 'Throwable' ou 'Error' — deixe o JVM tratar");
        System.out.println();

        // Exemplo de catch vazio vs. bom catch
        System.out.println("  Comparação:");
        System.out.println("  RUIM:");
        System.out.println("    try {");
        System.out.println("        processarPedido();");
        System.out.println("    } catch (Exception e) {}  ← Nunca saberá que deu erro!");
        System.out.println();
        System.out.println("  BOM:");
        System.out.println("    try {");
        System.out.println("        processarPedido();");
        System.out.println("    } catch (PedidoException e) {");
        System.out.println("        logger.error(\"Erro ao processar pedido {}: {}\",");
        System.out.println("            pedidoId, e.getMessage(), e);");
        System.out.println("        throw new ServicoException(\"Falha no pedido\", e);");
        System.out.println("    }");
    }
}

// ============================================================
// EXCEÇÕES CUSTOMIZADAS — CLASSES DE DOMÍNIO
// Em projetos reais, ficam em arquivos separados (ex/excecao/ package)
// ============================================================

/**
 * Exceção lançada quando produto não tem quantidade suficiente no estoque.
 *
 * Boas práticas:
 * 1. Herda de RuntimeException (unchecked) — não obriga try-catch no chamador
 *    Use checked Exception (extends Exception) se o chamador DEVE tratar.
 * 2. Inclui dados extras relevantes (quantidadeDisponivel)
 * 3. Nome claro que descreve o problema de negócio
 */
class EstoqueInsuficienteException extends RuntimeException {
    private final int quantidadeDisponivel;

    public EstoqueInsuficienteException(String mensagem, int quantidadeDisponivel) {
        super(mensagem);
        this.quantidadeDisponivel = quantidadeDisponivel;
    }

    public int getQuantidadeDisponivel() {
        return quantidadeDisponivel;
    }
}

/**
 * Exceção lançada quando um produto está bloqueado para venda.
 */
class ProdutoBloqueadoException extends RuntimeException {
    public ProdutoBloqueadoException(String mensagem) {
        super(mensagem);
    }
}
