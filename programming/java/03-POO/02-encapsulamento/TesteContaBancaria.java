/**
 * Classe de Teste - Demonstração de Encapsulamento
 * 
 * Esta classe demonstra como o encapsulamento protege os dados e 
 * garante que as operações sejam realizadas de forma segura.
 * 
 * @author Curso POO Java
 */
public class TesteContaBancaria {
    
    public static void main(String[] args) {
        
        System.out.println("=== DEMONSTRAÇÃO: ENCAPSULAMENTO ===\n");
        
        // ===== CRIAÇÃO DE CONTAS =====
        System.out.println("1️⃣ CRIANDO CONTAS BANCÁRIAS\n");
        
        try {
            // Criando conta válida
            ContaBancaria conta1 = new ContaBancaria("João Silva", "123456", 1000.0);
            
            // Criando segunda conta
            ContaBancaria conta2 = new ContaBancaria("Maria Santos", "654321", 500.0);
            
            System.out.println();
            
        } catch (Exception e) {
            System.out.println("❌ Erro ao criar conta: " + e.getMessage());
        }
        
        // ===== DEMONSTRAÇÃO DE VALIDAÇÕES =====
        System.out.println("2️⃣ TESTANDO VALIDAÇÕES DO ENCAPSULAMENTO\n");
        
        try {
            // Tentativa de criar conta com dados inválidos
            System.out.println("Tentando criar conta com nome vazio...");
            ContaBancaria contaInvalida = new ContaBancaria("", "111111", 100.0);
            
        } catch (IllegalArgumentException e) {
            System.out.println("✓ Validação funcionou: " + e.getMessage());
        }
        
        try {
            // Tentativa de criar conta com número inválido
            System.out.println("Tentando criar conta com número inválido...");
            ContaBancaria contaInvalida = new ContaBancaria("Pedro", "12345", 100.0);
            
        } catch (IllegalArgumentException e) {
            System.out.println("✓ Validação funcionou: " + e.getMessage());
        }
        
        try {
            // Tentativa de criar conta com saldo negativo
            System.out.println("Tentando criar conta com saldo negativo...");
            ContaBancaria contaInvalida = new ContaBancaria("Ana", "111111", -50.0);
            
        } catch (IllegalArgumentException e) {
            System.out.println("✓ Validação funcionou: " + e.getMessage());
        }
        
        System.out.println();
        
        // ===== OPERAÇÕES BANCÁRIAS =====
        System.out.println("3️⃣ REALIZANDO OPERAÇÕES BANCÁRIAS\n");
        
        // Recriando contas para os testes
        ContaBancaria conta1 = new ContaBancaria("João Silva", "123456", 1000.0);
        ContaBancaria conta2 = new ContaBancaria("Maria Santos", "654321", 500.0);
        
        // Exibindo informações iniciais
        conta1.exibirInformacoes();
        conta2.exibirInformacoes();
        
        // ===== TESTANDO DEPÓSITOS =====
        System.out.println("💰 TESTANDO DEPÓSITOS");
        conta1.depositar(250.0);        // Depósito válido
        conta1.depositar(-50.0);        // Depósito inválido (valor negativo)
        conta1.depositar(0);            // Depósito inválido (valor zero)
        
        System.out.println();
        
        // ===== TESTANDO SAQUES =====
        System.out.println("💸 TESTANDO SAQUES");
        conta1.sacar(200.0);           // Saque válido
        conta1.sacar(2000.0);          // Saque inválido (saldo insuficiente)
        conta1.sacar(-100.0);          // Saque inválido (valor negativo)
        
        System.out.println();
        
        // ===== TESTANDO TRANSFERÊNCIAS =====
        System.out.println("🔄 TESTANDO TRANSFERÊNCIAS");
        conta1.transferir(conta2, 300.0);    // Transferência válida
        conta1.transferir(conta2, 1500.0);   // Transferência inválida (saldo insuficiente)
        conta1.transferir(null, 100.0);      // Transferência inválida (conta nula)
        
        System.out.println();
        
        // ===== DEMONSTRANDO PROTEÇÃO DOS DADOS =====
        System.out.println("4️⃣ DEMONSTRANDO PROTEÇÃO DOS DADOS\n");
        
        System.out.println("🔒 Os atributos são PRIVADOS e protegidos:");
        System.out.println("   - Não podemos acessar diretamente: conta1.saldo");
        System.out.println("   - Não podemos modificar diretamente: conta1.saldo = 9999");
        System.out.println("   - Só podemos acessar através dos métodos públicos");
        
        System.out.println("\n✅ Acesso SEGURO através de getters:");
        System.out.println("   - Titular: " + conta1.getTitular());
        System.out.println("   - Número: " + conta1.getNumeroConta());
        System.out.println("   - Saldo: R$ " + conta1.getSaldo());
        System.out.println("   - Ativa: " + conta1.isContaAtiva());
        
        // ===== TESTANDO MUDANÇA DE STATUS =====
        System.out.println("\n5️⃣ TESTANDO MUDANÇA DE STATUS\n");
        
        // Desativando conta
        conta1.setContaAtiva(false);
        
        // Tentando operar com conta inativa
        System.out.println("Tentando depositar em conta inativa:");
        conta1.depositar(100.0);
        
        System.out.println("Tentando sacar de conta inativa:");
        conta1.sacar(50.0);
        
        // Reativando conta
        conta1.setContaAtiva(true);
        System.out.println("Depositando em conta reativada:");
        conta1.depositar(100.0);
        
        // ===== ESTADO FINAL =====
        System.out.println("\n6️⃣ ESTADO FINAL DAS CONTAS\n");
        
        conta1.exibirInformacoes();
        conta2.exibirInformacoes();
        
        // ===== DEMONSTRAÇÃO DO toString() =====
        System.out.println("📄 Representação textual das contas:");
        System.out.println("Conta 1: " + conta1.toString());
        System.out.println("Conta 2: " + conta2.toString());
        
        System.out.println("\n✅ Demonstração de encapsulamento concluída!");
        System.out.println("   - Dados protegidos ✓");
        System.out.println("   - Validações funcionando ✓"); 
        System.out.println("   - Operações seguras ✓");
    }
}