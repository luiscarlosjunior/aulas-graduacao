/**
 * Operadores Lógicos em Java — com Exemplos da Indústria
 *
 * ============================================================
 * POR QUE ISSO IMPORTA NA INDÚSTRIA?
 * ============================================================
 * Operadores lógicos são a base de TODA tomada de decisão em software:
 *
 *  - Controle de Acesso: "O usuário pode ver esse relatório?"
 *    → isPerfil("admin") || isPerfil("gerente")
 *
 *  - Validação de Formulários: "Pode finalizar o cadastro?"
 *    → !nomeVazio && emailValido && senhaForte
 *
 *  - Regras de Negócio: "Aplica desconto VIP?"
 *    → clienteVip && totalCompra > 500 && !promocaoAtiva
 *
 *  - Feature Flags: "Ativa novo módulo?"
 *    → featureFlagAtiva && (usuarioBeta || ambienteHomologacao)
 *
 * Short-circuit evaluation (avaliação em curto-circuito) é especialmente
 * importante: evita NullPointerException e melhora performance.
 * ============================================================
 *
 * @author Aulas Graduação
 * @version 2.0
 */
public class OperadoresLogicos {

    public static void main(String[] args) {
        System.out.println("=== OPERADORES LÓGICOS — EXEMPLOS DA INDÚSTRIA ===\n");

        tabelaVerdade();
        sistemaControleAcesso();
        validacaoFormulario();
        shortCircuitEvaluation();
        regrasNegocioEcommerce();
    }

    // ----------------------------------------------------------
    // 1. Tabela Verdade — fundamento
    // ----------------------------------------------------------
    static void tabelaVerdade() {
        System.out.println("--- 1. TABELA VERDADE ---");

        boolean[] valores = {true, false};

        System.out.println("  A       B       A && B  A || B  !A");
        System.out.println("  ─────────────────────────────────────");
        for (boolean a : valores) {
            for (boolean b : valores) {
                System.out.printf("  %-7s %-7s %-7s %-7s %s%n",
                    a, b, (a && b), (a || b), !a);
            }
        }
        System.out.println();
    }

    // ----------------------------------------------------------
    // 2. Sistema de Controle de Acesso (IAM — Identity & Access Management)
    // ----------------------------------------------------------
    static void sistemaControleAcesso() {
        System.out.println("--- 2. CONTROLE DE ACESSO (como Google IAM, AWS, Azure AD) ---");

        // Simula atributos de um usuário
        boolean estaAutenticado = true;
        boolean isAdmin = false;
        boolean isGerente = true;
        boolean contaAtiva = true;
        boolean contratoValido = true;

        // Regra 1: Pode acessar o painel geral?
        boolean podeAcessarPainel = estaAutenticado && contaAtiva;
        System.out.println("  Acesso ao painel:        " + formatarAcesso(podeAcessarPainel));

        // Regra 2: Pode ver relatórios financeiros? (admin OU gerente)
        boolean podeVerRelatorio = podeAcessarPainel && (isAdmin || isGerente);
        System.out.println("  Relatórios financeiros:  " + formatarAcesso(podeVerRelatorio));

        // Regra 3: Pode deletar dados? (apenas admin)
        boolean podeDeletar = podeAcessarPainel && isAdmin;
        System.out.println("  Deletar dados:           " + formatarAcesso(podeDeletar));

        // Regra 4: Pode aprovar contratos? (gerente + contrato válido + não admin)
        boolean podeAprovarContrato = podeAcessarPainel && isGerente && contratoValido && !isAdmin;
        System.out.println("  Aprovar contratos:       " + formatarAcesso(podeAprovarContrato));

        System.out.println();
        System.out.println("  >> Em sistemas reais, essas regras são configuradas via");
        System.out.println("     banco de dados e aplicadas por um serviço de autorização");
        System.out.println("     (ex: Spring Security, Auth0, Keycloak). A lógica booleana");
        System.out.println("     é a mesma — só a fonte dos dados muda.");
        System.out.println();
    }

    // ----------------------------------------------------------
    // 3. Validação de Formulário de Cadastro
    // ----------------------------------------------------------
    static void validacaoFormulario() {
        System.out.println("--- 3. VALIDAÇÃO DE FORMULÁRIO (como qualquer app web) ---");

        // Simula dados submetidos no formulário
        String nome = "Maria Silva";
        String email = "maria@email.com";
        String senha = "Senha@123";
        String confirmacaoSenha = "Senha@123";
        boolean termoAceito = true;
        int idade = 17;

        // Validações individuais
        boolean nomeValido = nome != null && !nome.trim().isEmpty() && nome.length() >= 3;
        boolean emailValido = email != null && email.contains("@") && email.contains(".");
        boolean senhaForte = senha != null && senha.length() >= 8;
        boolean senhasIguais = senha != null && senha.equals(confirmacaoSenha);
        boolean maiorIdade = idade >= 18;

        System.out.println("  Validando cadastro:");
        System.out.printf("  ✔ Nome válido:       %b (nome='%s')%n", nomeValido, nome);
        System.out.printf("  ✔ Email válido:      %b (email='%s')%n", emailValido, email);
        System.out.printf("  ✔ Senha forte:       %b (min 8 chars)%n", senhaForte);
        System.out.printf("  ✔ Senhas coincidem:  %b%n", senhasIguais);
        System.out.printf("  ✔ Maior de idade:    %b (idade=%d)%n", maiorIdade, idade);
        System.out.printf("  ✔ Termo aceito:      %b%n", termoAceito);

        // Decisão final: TODOS devem ser verdadeiros (&&)
        boolean podeCadastrar = nomeValido && emailValido && senhaForte
                                && senhasIguais && maiorIdade && termoAceito;

        System.out.println();
        if (podeCadastrar) {
            System.out.println("  ✅ Cadastro aprovado! Usuário pode continuar.");
        } else {
            System.out.println("  ❌ Cadastro rejeitado. Corrija os campos marcados.");
            // Em produção, retornaria lista de erros específicos
        }

        System.out.println();
        System.out.println("  >> Note que o operador && exige que TODAS condições sejam");
        System.out.println("     verdadeiras. Se qualquer uma falhar, o cadastro é bloqueado.");
        System.out.println("     Isso é exatamente o que você vê em qualquer app de cadastro.");
        System.out.println();
    }

    // ----------------------------------------------------------
    // 4. Short-Circuit Evaluation — CONCEITO CRÍTICO
    // ----------------------------------------------------------
    static void shortCircuitEvaluation() {
        System.out.println("--- 4. SHORT-CIRCUIT EVALUATION (Avaliação em Curto-Circuito) ---");

        System.out.println("  Fundamento: Java para de avaliar quando o resultado já é conhecido.");
        System.out.println("  - false && (qualquer coisa) → sempre false, não avalia o segundo");
        System.out.println("  - true  || (qualquer coisa) → sempre true,  não avalia o segundo");
        System.out.println();

        // Exemplo 1: Previne NullPointerException (NPE) — erro muito comum em produção!
        String usuario = null;  // usuário não logado

        // PERIGOSO — pode lançar NullPointerException:
        // if (usuario.equals("admin")) { ... }

        // SEGURO — short-circuit previne o NPE:
        // Se usuario == null, a segunda parte NÃO é avaliada
        if (usuario != null && usuario.equals("admin")) {
            System.out.println("  Usuário é admin");
        } else {
            System.out.println("  ✅ Verificação segura: usuário nulo tratado sem NPE");
        }

        // Exemplo 2: Otimização de performance
        System.out.println();
        System.out.println("  Ordem importa para performance:");
        System.out.println("  - Coloque a condição mais BARATA (rápida) primeiro");
        System.out.println("  - Coloque a condição mais CARA (consulta ao banco) por último");
        System.out.println();

        boolean cacheAtivo = true;  // verificação instantânea
        // boolean dadoNoBanco = buscarNoBancoDeDados(); // consulta lenta — só executa se necessário
        System.out.println("  if (cacheAtivo || dadoNoBanco()) → se cacheAtivo=true,");
        System.out.println("  o banco de dados NÃO é consultado! Economiza tempo e recursos.");

        // Exemplo 3: Ordem para &&
        System.out.println();
        boolean usuarioLogado = false;
        // Com &&: se usuarioLogado=false, verificações caras não executam
        System.out.println("  if (usuarioLogado && verificarPermissaoNoBanco())");
        System.out.println("  → usuarioLogado=false → banco NÃO é consultado!");
        System.out.println();
    }

    // ----------------------------------------------------------
    // 5. Regras de Negócio Complexas — E-commerce
    // ----------------------------------------------------------
    static void regrasNegocioEcommerce() {
        System.out.println("--- 5. REGRAS DE NEGÓCIO COMPLEXAS (E-COMMERCE) ---");

        // Cenário: Calcular desconto para um pedido
        boolean clienteVip = true;
        boolean primeiraCompra = false;
        double valorPedido = 350.00;
        boolean promocaoAtiva = false;
        boolean produtoEmPromocao = true;
        int diasParaVencer = 5;  // produto perto do vencimento

        System.out.printf("  Cliente VIP: %b%n", clienteVip);
        System.out.printf("  Primeira compra: %b%n", primeiraCompra);
        System.out.printf("  Valor do pedido: R$ %.2f%n", valorPedido);
        System.out.printf("  Promoção ativa: %b%n", promocaoAtiva);
        System.out.printf("  Produto em promoção: %b%n", produtoEmPromocao);
        System.out.println();

        // Regra 1: Desconto VIP (cliente vip com compra acima de R$200)
        double descontoVip = 0;
        if (clienteVip && valorPedido > 200) {
            descontoVip = 0.10;  // 10% de desconto
        }

        // Regra 2: Desconto boas-vindas (primeira compra de qualquer valor)
        double descontoBoasVindas = primeiraCompra ? 0.15 : 0;  // 15% para primeira compra

        // Regra 3: Desconto de promoção (produto em promoção OU promoção geral ativa)
        double descontoPromocao = (produtoEmPromocao || promocaoAtiva) ? 0.05 : 0;

        // Regra 4: Desconto urgência (produto perto de vencer, independente de tudo)
        double descontoUrgencia = (diasParaVencer <= 7) ? 0.20 : 0;

        // Não acumula todos os descontos — pega o maior
        double maiorDesconto = Math.max(Math.max(descontoVip, descontoBoasVindas),
                                        Math.max(descontoPromocao, descontoUrgencia));

        double valorDesconto = valorPedido * maiorDesconto;
        double totalFinal = valorPedido - valorDesconto;

        System.out.println("  Descontos disponíveis:");
        System.out.printf("    VIP (%.0f%%):         %s%n", descontoVip * 100,
            descontoVip > 0 ? "✅ Aplicável" : "❌ Não aplicável");
        System.out.printf("    Boas-vindas (%.0f%%): %s%n", descontoBoasVindas * 100,
            descontoBoasVindas > 0 ? "✅ Aplicável" : "❌ Não aplicável");
        System.out.printf("    Promoção (%.0f%%):    %s%n", descontoPromocao * 100,
            descontoPromocao > 0 ? "✅ Aplicável" : "❌ Não aplicável");
        System.out.printf("    Urgência (%.0f%%):    %s%n", descontoUrgencia * 100,
            descontoUrgencia > 0 ? "✅ Aplicável" : "❌ Não aplicável");
        System.out.println();
        System.out.printf("  Melhor desconto aplicado: %.0f%%%n", maiorDesconto * 100);
        System.out.printf("  Desconto: -R$ %.2f%n", valorDesconto);
        System.out.printf("  TOTAL FINAL: R$ %.2f%n%n", totalFinal);
    }

    // Método auxiliar para formatar acesso
    static String formatarAcesso(boolean permitido) {
        return permitido ? "✅ PERMITIDO" : "❌ NEGADO";
    }
}
