/**
 * Operadores Aritméticos em Java — com Exemplos da Indústria
 *
 * ============================================================
 * POR QUE ISSO IMPORTA NA INDÚSTRIA?
 * ============================================================
 * Operadores aritméticos são usados em TODOS os sistemas:
 *
 *  - E-commerce: calcular preço final com desconto e impostos
 *  - Financeiro: calcular juros compostos, parcelas de empréstimo
 *  - Logística: calcular distâncias, tempo de entrega, peso de frete
 *  - Games: física do jogo — velocidade, posição, colisão
 *  - Ciência de Dados: médias, desvios, normalização de dados
 *
 * Entender bem esses operadores — especialmente divisão inteira
 * e módulo — evita bugs silenciosos que só aparecem em produção.
 * ============================================================
 *
 * @author Aulas Graduação
 * @version 2.0
 */
public class OperadoresAritmeticos {

    public static void main(String[] args) {
        System.out.println("=== OPERADORES ARITMÉTICOS — EXEMPLOS DA INDÚSTRIA ===\n");

        operacoesBasicas();
        calculoEcommerce();
        divisaoInteiraVsDecimal();
        operadorModulo();
        incrementoDecremento();
        calculoJurosCompostos();
    }

    // ----------------------------------------------------------
    // 1. Operações Básicas
    // ----------------------------------------------------------
    static void operacoesBasicas() {
        System.out.println("--- 1. OPERAÇÕES BÁSICAS ---");

        int a = 10, b = 3;

        System.out.println("  a = " + a + ", b = " + b);
        System.out.println("  a + b = " + (a + b));   // 13 — adição
        System.out.println("  a - b = " + (a - b));   // 7  — subtração
        System.out.println("  a * b = " + (a * b));   // 30 — multiplicação
        System.out.println("  a / b = " + (a / b));   // 3  — DIVISÃO INTEIRA! (não 3.33)
        System.out.println("  a % b = " + (a % b));   // 1  — módulo (resto da divisão)

        System.out.println();
    }

    // ----------------------------------------------------------
    // 2. Cálculo de E-commerce (cenário real)
    // ----------------------------------------------------------
    static void calculoEcommerce() {
        System.out.println("--- 2. CÁLCULO DE PREÇO FINAL (E-COMMERCE) ---");

        // Dados do produto
        double precoOriginal = 299.90;
        int quantidade = 3;
        double percentualDesconto = 15.0;   // 15% de desconto na compra de 3+
        double aliquotaICMS = 0.12;         // 12% de ICMS

        // Cálculo passo a passo — como um sistema de checkout real faz
        double subtotal = precoOriginal * quantidade;
        double valorDesconto = subtotal * (percentualDesconto / 100);
        double subtotalComDesconto = subtotal - valorDesconto;
        double valorICMS = subtotalComDesconto * aliquotaICMS;
        double frete = calcularFrete(subtotalComDesconto);
        double totalFinal = subtotalComDesconto + frete;
        // Nota: em compras B2B, o ICMS é adicionado ao preço. Em B2C geralmente está incluso.

        System.out.printf("  Produto: Fone de Ouvido Bluetooth%n");
        System.out.printf("  Preço unitário:     R$ %7.2f%n", precoOriginal);
        System.out.printf("  Quantidade:         %8d%n", quantidade);
        System.out.printf("  Subtotal:           R$ %7.2f%n", subtotal);
        System.out.printf("  Desconto (%.0f%%):    -R$ %7.2f%n", percentualDesconto, valorDesconto);
        System.out.printf("  Subtotal c/desconto:R$ %7.2f%n", subtotalComDesconto);
        System.out.printf("  ICMS (%.0f%%, incluso):R$ %7.2f%n", aliquotaICMS * 100, valorICMS);
        System.out.printf("  Frete:              R$ %7.2f%n", frete);
        System.out.printf("  ═══════════════════════════════%n");
        System.out.printf("  TOTAL:              R$ %7.2f%n", totalFinal);

        System.out.println();
    }

    // ----------------------------------------------------------
    // 3. DIVISÃO INTEIRA vs DECIMAL — armadilha clássica!
    // ----------------------------------------------------------
    static void divisaoInteiraVsDecimal() {
        System.out.println("--- 3. DIVISÃO INTEIRA vs DECIMAL — ERRO CLÁSSICO ---");

        int totalVotos = 7;
        int totalCandidatos = 2;

        // BUG SILENCIOSO: divisão inteira perde o decimal!
        int resultadoBugado = totalVotos / totalCandidatos;  // 3, não 3.5!

        // CORRETO: converta para double antes da divisão
        double mediaCorreta = (double) totalVotos / totalCandidatos; // 3.5

        System.out.println("  Dividindo " + totalVotos + " por " + totalCandidatos + ":");
        System.out.println("  int / int = " + resultadoBugado + "  ← ⚠️  RESULTADO INCORRETO!");
        System.out.printf( "  (double) int / int = %.1f  ← ✅ CORRETO%n", mediaCorreta);

        System.out.println();
        System.out.println("  >> Caso real: Em 2003, um bug de divisão inteira no módulo");
        System.out.println("     de folha de pagamento de uma empresa pagou salários errados");
        System.out.println("     por 3 meses antes de ser detectado. Sempre verifique o tipo!");

        // Mais exemplos
        System.out.println();
        System.out.println("  Outros exemplos:");
        System.out.printf("  5 / 2 = %d (int)  mas  5.0 / 2 = %.1f (double)%n", 5/2, 5.0/2);
        System.out.printf("  1 / 3 = %d (int)  mas  1.0 / 3 = %.4f (double)%n", 1/3, 1.0/3);
        System.out.println();
    }

    // ----------------------------------------------------------
    // 4. OPERADOR MÓDULO (%) — uso prático
    // ----------------------------------------------------------
    static void operadorModulo() {
        System.out.println("--- 4. OPERADOR MÓDULO (%) — USOS PRÁTICOS ---");

        // Uso 1: Verificar se número é par ou ímpar
        for (int i = 1; i <= 6; i++) {
            String tipo = (i % 2 == 0) ? "par" : "ímpar";
            System.out.println("  " + i + " % 2 = " + (i % 2) + " → " + tipo);
        }

        System.out.println();

        // Uso 2: Distribuição em round-robin (servidores em load balancing)
        // Imagine 3 servidores de banco de dados — distribuir queries igualmente
        int totalServidores = 3;
        System.out.println("  Distribuição de 9 requisições entre " + totalServidores + " servidores (round-robin):");
        for (int req = 0; req < 9; req++) {
            int servidor = req % totalServidores;  // 0, 1, 2, 0, 1, 2, ...
            System.out.println("    Requisição " + req + " → Servidor " + servidor);
        }

        System.out.println();

        // Uso 3: Verificar se é hora de executar job agendado
        // "Execute a cada 5 minutos"
        System.out.println("  Job agendado a cada 5 minutos:");
        for (int minuto = 0; minuto <= 20; minuto += 1) {
            if (minuto % 5 == 0) {
                System.out.println("    Minuto " + minuto + ": ✅ Executar job de sincronização");
            }
        }

        System.out.println();
    }

    // ----------------------------------------------------------
    // 5. Incremento e Decremento
    // ----------------------------------------------------------
    static void incrementoDecremento() {
        System.out.println("--- 5. INCREMENTO/DECREMENTO — PRÉ vs PÓS ---");

        // Pré-incremento: incrementa ANTES de usar o valor
        int contador = 0;
        System.out.println("  Pré-incremento (++contador):");
        System.out.println("    contador inicial = " + contador);
        int valorPre = ++contador;  // primeiro incrementa, depois usa
        System.out.println("    ++contador retorna: " + valorPre + ", contador agora é: " + contador);

        // Pós-incremento: usa o valor ANTES de incrementar
        contador = 0;
        System.out.println("  Pós-incremento (contador++):");
        System.out.println("    contador inicial = " + contador);
        int valorPos = contador++;  // primeiro usa, depois incrementa
        System.out.println("    contador++ retorna: " + valorPos + ", contador agora é: " + contador);

        System.out.println();
        System.out.println("  >> Dica de indústria: Prefira sempre for(int i=0; i<n; i++)");
        System.out.println("     à forma i++. Evite usar ++i ou i++ em expressões complexas");
        System.out.println("     — cria código confuso e difícil de manter.");

        // Operadores de atribuição composta
        System.out.println();
        System.out.println("  Atribuição composta (forma curta e legível):");
        int saldo = 1000;
        System.out.println("    saldo = " + saldo);
        saldo += 500;  // equivale a: saldo = saldo + 500
        System.out.println("    saldo += 500 → " + saldo);
        saldo -= 200;  // equivale a: saldo = saldo - 200
        System.out.println("    saldo -= 200 → " + saldo);
        saldo *= 2;    // equivale a: saldo = saldo * 2
        System.out.println("    saldo *= 2   → " + saldo);
        saldo /= 4;    // equivale a: saldo = saldo / 4
        System.out.println("    saldo /= 4   → " + saldo);
        System.out.println();
    }

    // ----------------------------------------------------------
    // 6. Juros Compostos — fórmula financeira clássica
    // ----------------------------------------------------------
    static void calculoJurosCompostos() {
        System.out.println("--- 6. JUROS COMPOSTOS (SISTEMA FINANCEIRO) ---");
        System.out.println("  Fórmula: M = C * (1 + i)^n");
        System.out.println("  Onde: C=capital, i=taxa/mês, n=número de meses");
        System.out.println();

        double capital = 10000.00;          // R$ 10.000 investidos
        double taxaMensal = 0.01;           // 1% ao mês
        int prazoMeses = 12;                // 12 meses (1 ano)

        System.out.printf("  Investimento inicial: R$ %.2f%n", capital);
        System.out.printf("  Taxa mensal: %.1f%%%n", taxaMensal * 100);
        System.out.printf("  Prazo: %d meses%n%n", prazoMeses);

        double montante = capital;
        System.out.println("  Evolução mês a mês:");
        for (int mes = 1; mes <= prazoMeses; mes++) {
            montante = montante * (1 + taxaMensal);  // M = M * (1 + i)
            double jurosDoMes = montante - capital * Math.pow(1 + taxaMensal, mes - 1);
            System.out.printf("    Mês %2d: R$ %9.2f%n", mes, montante);
        }

        double jurosTotal = montante - capital;
        System.out.printf("%n  Capital inicial:  R$ %9.2f%n", capital);
        System.out.printf("  Juros acumulados: R$ %9.2f%n", jurosTotal);
        System.out.printf("  Montante final:   R$ %9.2f%n", montante);
        System.out.printf("  Rendimento:       %.1f%%%n%n", (jurosTotal / capital) * 100);
    }

    // Método auxiliar para calcular frete
    static double calcularFrete(double valorCompra) {
        if (valorCompra >= 250.00) return 0.00;      // Frete grátis acima de R$250
        if (valorCompra >= 150.00) return 9.90;       // Frete reduzido
        return 15.90;                                  // Frete padrão
    }
}
