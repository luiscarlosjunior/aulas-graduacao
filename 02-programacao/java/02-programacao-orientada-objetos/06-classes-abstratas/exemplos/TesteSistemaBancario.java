/**
 * Classe de Teste - Sistema Bancário
 * 
 * Demonstra o uso de classes abstratas em um sistema bancário completo,
 * mostrando polimorfismo, reutilização de código e comportamentos específicos.
 * 
 * @author Curso POO Java - Universidade Nove de Julho
 */
public class TesteSistemaBancario {
    public static void main(String[] args) {
        System.out.println("╔════════════════════════════════════════════════════╗");
        System.out.println("║       SISTEMA BANCÁRIO - CLASSES ABSTRATAS         ║");
        System.out.println("║         Demonstração de POO com Java               ║");
        System.out.println("╚════════════════════════════════════════════════════╝\n");
        
        // Criando diferentes tipos de contas
        ContaBancaria[] contas = new ContaBancaria[3];
        
        contas[0] = new ContaCorrente("0001-CC", "João Silva", 1000.00, 500.00);
        contas[1] = new ContaPoupanca("0002-CP", "Maria Santos", 5000.00);
        contas[2] = new ContaInvestimento("0003-CI", "Pedro Oliveira", 10000.00);
        
        System.out.println("═══════════════════════════════════════════════════");
        System.out.println("  PARTE 1: EXIBINDO EXTRATOS INICIAIS");
        System.out.println("═══════════════════════════════════════════════════");
        
        // Polimorfismo: tratando todas as contas uniformemente
        for (ContaBancaria conta : contas) {
            conta.exibirExtrato();
        }
        
        System.out.println("\n═══════════════════════════════════════════════════");
        System.out.println("  PARTE 2: OPERAÇÕES BANCÁRIAS");
        System.out.println("═══════════════════════════════════════════════════");
        
        // Operações na Conta Corrente
        System.out.println("\n--- Operações: " + contas[0].getTipoConta() + " ---");
        contas[0].depositar(500.00);
        contas[0].sacar(200.00);
        
        // Operação específica de Conta Corrente
        if (contas[0] instanceof ContaCorrente) {
            ContaCorrente cc = (ContaCorrente) contas[0];
            cc.cobrarTaxaManutencao();
        }
        
        // Operações na Conta Poupança
        System.out.println("\n--- Operações: " + contas[1].getTipoConta() + " ---");
        contas[1].depositar(1000.00);
        contas[1].sacar(500.00);
        contas[1].sacar(300.00);
        contas[1].sacar(200.00); // 3º saque gratuito
        contas[1].sacar(100.00); // 4º saque - com taxa
        
        // Operação específica de Conta Poupança
        if (contas[1] instanceof ContaPoupanca) {
            ContaPoupanca cp = (ContaPoupanca) contas[1];
            cp.aniversarioPoupanca();
        }
        
        // Operações na Conta Investimento
        System.out.println("\n--- Operações: " + contas[2].getTipoConta() + " ---");
        contas[2].depositar(5000.00);
        contas[2].sacar(2000.00);
        
        // Operação específica de Conta Investimento
        if (contas[2] instanceof ContaInvestimento) {
            ContaInvestimento ci = (ContaInvestimento) contas[2];
            ci.processarMensalidade();
        }
        
        System.out.println("\n═══════════════════════════════════════════════════");
        System.out.println("  PARTE 3: TRANSFERÊNCIAS ENTRE CONTAS");
        System.out.println("═══════════════════════════════════════════════════");
        
        // Transferência da conta corrente para poupança
        contas[0].transferir(contas[1], 300.00);
        
        // Transferência da conta investimento para corrente
        contas[2].transferir(contas[0], 1000.00);
        
        System.out.println("\n═══════════════════════════════════════════════════");
        System.out.println("  PARTE 4: APLICANDO RENDIMENTOS");
        System.out.println("═══════════════════════════════════════════════════");
        
        for (ContaBancaria conta : contas) {
            System.out.println("\n→ Aplicando rendimento em: " + conta.getTipoConta());
            conta.aplicarRendimento();
        }
        
        System.out.println("\n═══════════════════════════════════════════════════");
        System.out.println("  PARTE 5: EXTRATOS FINAIS");
        System.out.println("═══════════════════════════════════════════════════");
        
        for (ContaBancaria conta : contas) {
            conta.exibirExtrato();
        }
        
        System.out.println("\n═══════════════════════════════════════════════════");
        System.out.println("  RESUMO GERAL DO SISTEMA");
        System.out.println("═══════════════════════════════════════════════════");
        
        double saldoTotal = 0;
        int operacoesTotal = 0;
        
        for (ContaBancaria conta : contas) {
            saldoTotal += conta.getSaldo();
            operacoesTotal += conta.getNumeroOperacoes();
        }
        
        System.out.println("\n📊 Estatísticas:");
        System.out.println("   Total de contas: " + contas.length);
        System.out.println("   Saldo total no banco: R$ " + String.format("%.2f", saldoTotal));
        System.out.println("   Total de operações: " + operacoesTotal);
        
        System.out.println("\n💡 Conceitos Demonstrados:");
        System.out.println("   ✓ Classe abstrata como base comum");
        System.out.println("   ✓ Polimorfismo com arrays");
        System.out.println("   ✓ Métodos abstratos implementados diferentemente");
        System.out.println("   ✓ Métodos concretos compartilhados");
        System.out.println("   ✓ Template Method Pattern");
        System.out.println("   ✓ Encapsulamento e reutilização de código");
        
        System.out.println("\n╔════════════════════════════════════════════════════╗");
        System.out.println("║            FIM DA DEMONSTRAÇÃO                     ║");
        System.out.println("╚════════════════════════════════════════════════════╝");
    }
}
