/**
 * Controle de Fluxo Condicional em Java — com Exemplos da Indústria
 *
 * ============================================================
 * POR QUE CONTROLE DE FLUXO IMPORTA NA INDÚSTRIA?
 * ============================================================
 * Literalmente TODO sistema toma decisões:
 *
 *  - Sistemas bancários: "Aprovado ou reprovado o empréstimo?"
 *  - E-commerce: "Qual desconto aplicar? Tem estoque?"
 *  - Autenticação: "Usuário tem permissão? Sessão expirou?"
 *  - Logística: "Qual transportadora usar? Prazo estimado?"
 *  - Saúde: "Dose correta de medicamento para o peso do paciente"
 *
 * A habilidade de modelar decisões complexas de negócio em código
 * é uma das competências mais valorizadas em desenvolvedores.
 *
 * Um código com IFs mal estruturados é chamado de "Spaghetti Code"
 * — é difícil de manter e um dos maiores problemas em sistemas legados.
 * ============================================================
 *
 * @author Aulas Graduação
 * @version 2.0
 */
public class ControleFluxoCondicional {

    public static void main(String[] args) {
        System.out.println("=== CONTROLE DE FLUXO CONDICIONAL — EXEMPLOS DA INDÚSTRIA ===\n");

        exemploIfElse();
        exemploCreditScore();
        exemploSwitch();
        exemploEarlyReturn();
        exemploSwitchExpressao();
    }

    // ----------------------------------------------------------
    // 1. if-else básico com contexto real
    // ----------------------------------------------------------
    static void exemploIfElse() {
        System.out.println("--- 1. IF-ELSE: REGRAS DE FRETE ---");

        double pesoKg = 2.5;
        String destino = "SP";  // São Paulo (mesma cidade)
        boolean clienteVip = false;

        double frete;
        String prazoEntrega;

        // Regra de frete baseada em peso + destino + perfil cliente
        if (clienteVip) {
            // Clientes VIP sempre têm frete grátis e entrega expressa
            frete = 0.0;
            prazoEntrega = "1 dia útil";
        } else if (destino.equals("SP") && pesoKg <= 5.0) {
            // Entrega local, produto leve
            frete = 8.90;
            prazoEntrega = "2 dias úteis";
        } else if (destino.equals("SP") && pesoKg > 5.0) {
            // Entrega local, produto pesado
            frete = 15.90;
            prazoEntrega = "3 dias úteis";
        } else if (pesoKg <= 5.0) {
            // Fora de SP, produto leve
            frete = 24.90;
            prazoEntrega = "5 dias úteis";
        } else {
            // Fora de SP, produto pesado
            frete = 39.90;
            prazoEntrega = "7 dias úteis";
        }

        System.out.printf("  Peso: %.1f kg | Destino: %s | VIP: %b%n",
            pesoKg, destino, clienteVip);
        System.out.printf("  Frete: R$ %.2f | Prazo: %s%n%n", frete, prazoEntrega);
    }

    // ----------------------------------------------------------
    // 2. Análise de Crédito — lógica condicional complexa
    // ----------------------------------------------------------
    static void exemploCreditScore() {
        System.out.println("--- 2. ANÁLISE DE CRÉDITO (como bancos fazem) ---");

        // Dados do solicitante
        double rendaMensal = 4500.0;
        int scoreCredit = 720;          // Score de 0 a 1000 (SPC/Serasa)
        int mesesEmprego = 18;          // Tempo no emprego atual
        boolean temRestricao = false;   // SPC/Serasa negativado
        double valorSolicitado = 15000.0;

        System.out.printf("  Solicitante:%n");
        System.out.printf("    Renda mensal:    R$ %.2f%n", rendaMensal);
        System.out.printf("    Score de crédito: %d%n", scoreCredit);
        System.out.printf("    Meses no emprego: %d%n", mesesEmprego);
        System.out.printf("    Restrições:       %b%n", temRestricao);
        System.out.printf("    Valor solicitado: R$ %.2f%n%n", valorSolicitado);

        // Regra 1: Restrição bloqueia imediatamente (fail-fast)
        if (temRestricao) {
            System.out.println("  ❌ CRÉDITO NEGADO: Restrição no CPF.");
            return;
        }

        // Regra 2: Score mínimo exigido
        if (scoreCredit < 400) {
            System.out.println("  ❌ CRÉDITO NEGADO: Score abaixo do mínimo (400).");
            return;
        }

        // Regra 3: Comprometimento de renda (parcela não pode superar 30%)
        int numeroParcelas = 24;
        double parcelaSemJuros = valorSolicitado / numeroParcelas;
        double comprometimentoRenda = parcelaSemJuros / rendaMensal;

        if (comprometimentoRenda > 0.30) {
            System.out.printf("  ❌ CRÉDITO NEGADO: Parcela (R$ %.2f) comprometeria "
                + "%.0f%% da renda (máximo 30%%).%n", parcelaSemJuros,
                comprometimentoRenda * 100);
            return;
        }

        // Regra 4: Estabilidade no emprego
        if (mesesEmprego < 6) {
            System.out.println("  ❌ CRÉDITO NEGADO: Menos de 6 meses no emprego atual.");
            return;
        }

        // Se chegou aqui, passou todas as regras — determinar taxa de juros
        double taxaJuros;
        String classificacao;

        if (scoreCredit >= 900) {
            taxaJuros = 0.89;
            classificacao = "Premium";
        } else if (scoreCredit >= 750) {
            taxaJuros = 1.29;
            classificacao = "Ouro";
        } else if (scoreCredit >= 600) {
            taxaJuros = 1.79;
            classificacao = "Prata";
        } else {
            taxaJuros = 2.49;
            classificacao = "Bronze";
        }

        double totalComJuros = valorSolicitado * Math.pow(1 + taxaJuros/100, numeroParcelas);
        double parcelaComJuros = totalComJuros / numeroParcelas;

        System.out.println("  ✅ CRÉDITO APROVADO!");
        System.out.printf("  Classificação: %s | Taxa: %.2f%% a.m.%n",
            classificacao, taxaJuros);
        System.out.printf("  Parcela: R$ %.2f x %d meses%n",
            parcelaComJuros, numeroParcelas);
        System.out.printf("  Total: R$ %.2f (custo do crédito: R$ %.2f)%n%n",
            totalComJuros, totalComJuros - valorSolicitado);
    }

    // ----------------------------------------------------------
    // 3. switch-case: processar tipo de pagamento
    // ----------------------------------------------------------
    static void exemploSwitch() {
        System.out.println("--- 3. SWITCH-CASE: PROCESSAMENTO DE PAGAMENTO ---");

        String metodoPagamento = "PIX";

        System.out.printf("  Método de pagamento: %s%n", metodoPagamento);

        String gateway;
        double taxaProcessamento;
        int prazoCompensacaoHoras;

        switch (metodoPagamento.toUpperCase()) {
            case "PIX":
                gateway = "Banco Central (PIX)";
                taxaProcessamento = 0.0;    // PIX não tem taxa para o vendedor
                prazoCompensacaoHoras = 0;  // instantâneo!
                break;

            case "CREDITO":
            case "CARTAO_CREDITO":
                gateway = "Stone/Cielo/PagSeguro";
                taxaProcessamento = 2.49;   // taxa média das maquininhas
                prazoCompensacaoHoras = 30 * 24; // D+30
                break;

            case "DEBITO":
            case "CARTAO_DEBITO":
                gateway = "Stone/Cielo/PagSeguro";
                taxaProcessamento = 1.49;
                prazoCompensacaoHoras = 24; // D+1
                break;

            case "BOLETO":
                gateway = "Banco Inter/Bradesco";
                taxaProcessamento = 1.99;
                prazoCompensacaoHoras = 48; // 2 dias úteis
                break;

            default:
                System.out.println("  ❌ Método de pagamento não suportado: " + metodoPagamento);
                return;
        }

        System.out.printf("  Gateway: %s%n", gateway);
        System.out.printf("  Taxa: %.2f%%%n", taxaProcessamento);
        System.out.printf("  Compensação: %s%n",
            prazoCompensacaoHoras == 0 ? "Instantânea"
            : prazoCompensacaoHoras < 24 ? prazoCompensacaoHoras + "h"
            : (prazoCompensacaoHoras / 24) + " dias");

        System.out.println();
        System.out.println("  >> Cada e-commerce tem uma lógica de switch para ");
        System.out.println("     roteamento de pagamento. Em produção, isso seria");
        System.out.println("     uma enum + Strategy pattern, mas a lógica é idêntica.");
        System.out.println();
    }

    // ----------------------------------------------------------
    // 4. Early Return — código limpo e sem aninhamentos
    // ----------------------------------------------------------
    static void exemploEarlyReturn() {
        System.out.println("--- 4. EARLY RETURN — CÓDIGO LIMPO (Clean Code) ---");

        System.out.println("  Validando cadastro de produto:");
        System.out.println();

        // Simulando validação de produto para cadastro no catálogo
        String nome = "Smart TV 4K 55\"";
        double preco = 2999.90;
        int estoque = -5;  // Problema: estoque negativo!
        String categoria = "Eletrônicos";

        String erro = validarProduto(nome, preco, estoque, categoria);

        if (erro != null) {
            System.out.println("  ❌ Produto inválido: " + erro);
        } else {
            System.out.printf("  ✅ Produto '%s' cadastrado com sucesso!%n", nome);
        }

        System.out.println();
        System.out.println("  >> Early Return é uma prática de Clean Code:");
        System.out.println("     - Valide e retorne cedo se houver erro");
        System.out.println("     - Evita aninhamentos profundos (código piramidal)");
        System.out.println("     - Código principal fica no 'caminho feliz' (happy path)");
        System.out.println();
    }

    // Método com Early Return — evita ifs aninhados
    static String validarProduto(String nome, double preco, int estoque, String categoria) {
        // Cada 'return' sai do método imediatamente — sem else necessário
        if (nome == null || nome.trim().isEmpty()) {
            return "Nome do produto é obrigatório";
        }
        if (nome.length() > 200) {
            return "Nome do produto muito longo (máx 200 caracteres)";
        }
        if (preco <= 0) {
            return "Preço deve ser maior que zero";
        }
        if (preco > 1000000) {
            return "Preço acima do limite permitido (R$1.000.000)";
        }
        if (estoque < 0) {
            return "Estoque não pode ser negativo";
        }
        if (categoria == null || categoria.trim().isEmpty()) {
            return "Categoria é obrigatória";
        }

        return null;  // null = sem erros = produto válido
    }

    // ----------------------------------------------------------
    // 5. Switch Expression (Java 14+) — forma moderna
    // ----------------------------------------------------------
    static void exemploSwitchExpressao() {
        System.out.println("--- 5. SWITCH EXPRESSION (Java 14+) — FORMA MODERNA ---");

        System.out.println("  Comparação: switch clássico vs. switch expression:");

        // Forma clássica (verbosa)
        int diaSemana = 3; // Quarta-feira
        String nomeDiaClassico;
        switch (diaSemana) {
            case 1: nomeDiaClassico = "Segunda-feira"; break;
            case 2: nomeDiaClassico = "Terça-feira"; break;
            case 3: nomeDiaClassico = "Quarta-feira"; break;
            case 4: nomeDiaClassico = "Quinta-feira"; break;
            case 5: nomeDiaClassico = "Sexta-feira"; break;
            case 6: nomeDiaClassico = "Sábado"; break;
            case 7: nomeDiaClassico = "Domingo"; break;
            default: nomeDiaClassico = "Inválido";
        }

        // Forma moderna — switch expression (Java 14+)
        // Elimina 'break', mais conciso, menos propenso a erros de fall-through
        String nomeDiaModerno = switch (diaSemana) {
            case 1 -> "Segunda-feira";
            case 2 -> "Terça-feira";
            case 3 -> "Quarta-feira";
            case 4 -> "Quinta-feira";
            case 5 -> "Sexta-feira";
            case 6 -> "Sábado";
            case 7 -> "Domingo";
            default -> "Inválido";
        };

        System.out.println("  Dia " + diaSemana + " → " + nomeDiaModerno);
        System.out.println("  Clássico e moderno produzem o mesmo resultado: "
            + nomeDiaClassico.equals(nomeDiaModerno));
        System.out.println();
        System.out.println("  >> Switch Expression é o padrão moderno em projetos Java 14+.");
        System.out.println("     Em times profissionais, prefira a forma moderna.");
        System.out.println("     Spring Boot, Quarkus e outros frameworks já assumem Java 17+.");
    }
}
