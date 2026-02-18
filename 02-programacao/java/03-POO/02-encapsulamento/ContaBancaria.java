/**
 * Exemplo prático de Encapsulamento em Java
 * 
 * Esta classe demonstra os conceitos fundamentais do encapsulamento:
 * - Atributos privados (proteção de dados)
 * - Métodos públicos getters e setters (acesso controlado)
 * - Validação de dados (garantia de consistência)
 * - Métodos de negócio (operações seguras)
 * 
 * @author Curso POO Java
 */
public class ContaBancaria {
    
    // ===== ATRIBUTOS PRIVADOS =====
    // Utilizamos 'private' para proteger os dados contra acesso direto
    // Isso garante que apenas a própria classe pode modificar estes valores
    
    private String titular;          // Nome do dono da conta
    private String numeroConta;      // Número identificador da conta
    private double saldo;            // Saldo atual (em reais)
    private boolean contaAtiva;      // Status da conta (ativa/inativa)
    
    // ===== CONSTRUTOR =====
    
    /**
     * Construtor para criar uma nova conta bancária
     * 
     * @param titular Nome completo do titular
     * @param numeroConta Número único da conta
     * @param saldoInicial Valor inicial para depositar na conta
     */
    public ContaBancaria(String titular, String numeroConta, double saldoInicial) {
        // Usamos os setters para aproveitar as validações
        setTitular(titular);
        setNumeroConta(numeroConta);
        setSaldo(saldoInicial);
        this.contaAtiva = true;  // Nova conta sempre começa ativa
        
        System.out.println("✓ Conta criada com sucesso para " + titular);
    }
    
    // ===== MÉTODOS GETTERS (PARA LER DADOS) =====
    // Os getters permitem acessar os dados privados de forma controlada
    
    /**
     * Getter para o nome do titular
     * @return Nome do titular da conta
     */
    public String getTitular() {
        return titular;
    }
    
    /**
     * Getter para o número da conta
     * @return Número da conta
     */
    public String getNumeroConta() {
        return numeroConta;
    }
    
    /**
     * Getter para o saldo atual
     * Retorna o saldo formatado com 2 casas decimais
     * @return Saldo atual da conta
     */
    public double getSaldo() {
        return Math.round(saldo * 100.0) / 100.0;  // Arredonda para 2 casas decimais
    }
    
    /**
     * Getter para o status da conta
     * @return true se a conta estiver ativa, false caso contrário
     */
    public boolean isContaAtiva() {
        return contaAtiva;
    }
    
    // ===== MÉTODOS SETTERS (PARA MODIFICAR DADOS) =====
    // Os setters permitem modificar dados com validação e controle
    
    /**
     * Setter para o titular com validação
     * @param titular Nome do novo titular (não pode ser vazio)
     */
    public void setTitular(String titular) {
        // Validação: nome não pode ser nulo ou vazio
        if (titular == null || titular.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome do titular não pode ser vazio!");
        }
        
        // Validação: nome deve ter pelo menos 2 caracteres
        if (titular.trim().length() < 2) {
            throw new IllegalArgumentException("Nome do titular deve ter pelo menos 2 caracteres!");
        }
        
        this.titular = titular.trim();  // Remove espaços extras
    }
    
    /**
     * Setter para número da conta com validação
     * @param numeroConta Número da conta (deve ter exatamente 6 dígitos)
     */
    public void setNumeroConta(String numeroConta) {
        // Validação: número não pode ser nulo
        if (numeroConta == null) {
            throw new IllegalArgumentException("Número da conta não pode ser nulo!");
        }
        
        // Validação: deve ter exatamente 6 dígitos
        if (!numeroConta.matches("\\d{6}")) {
            throw new IllegalArgumentException("Número da conta deve ter exatamente 6 dígitos!");
        }
        
        this.numeroConta = numeroConta;
    }
    
    /**
     * Setter privado para saldo (usado internamente)
     * O saldo só pode ser alterado através de operações específicas
     * @param saldo Novo valor do saldo
     */
    private void setSaldo(double saldo) {
        // Validação: saldo não pode ser negativo
        if (saldo < 0) {
            throw new IllegalArgumentException("Saldo não pode ser negativo!");
        }
        
        this.saldo = saldo;
    }
    
    /**
     * Método para ativar/desativar a conta
     * @param ativa true para ativar, false para desativar
     */
    public void setContaAtiva(boolean ativa) {
        this.contaAtiva = ativa;
        System.out.println("Conta " + (ativa ? "ativada" : "desativada") + " com sucesso.");
    }
    
    // ===== MÉTODOS DE NEGÓCIO =====
    // Estes métodos realizam operações específicas da conta bancária
    
    /**
     * Método para depositar dinheiro na conta
     * @param valor Valor a ser depositado (deve ser positivo)
     * @return true se o depósito foi realizado, false caso contrário
     */
    public boolean depositar(double valor) {
        // Validação: conta deve estar ativa
        if (!contaAtiva) {
            System.out.println("❌ Erro: Conta inativa. Não é possível depositar.");
            return false;
        }
        
        // Validação: valor deve ser positivo
        if (valor <= 0) {
            System.out.println("❌ Erro: Valor do depósito deve ser positivo.");
            return false;
        }
        
        // Realiza o depósito
        saldo += valor;
        System.out.printf("✓ Depósito de R$ %.2f realizado. Saldo atual: R$ %.2f%n", 
                         valor, getSaldo());
        return true;
    }
    
    /**
     * Método para sacar dinheiro da conta
     * @param valor Valor a ser sacado
     * @return true se o saque foi realizado, false caso contrário
     */
    public boolean sacar(double valor) {
        // Validação: conta deve estar ativa
        if (!contaAtiva) {
            System.out.println("❌ Erro: Conta inativa. Não é possível sacar.");
            return false;
        }
        
        // Validação: valor deve ser positivo
        if (valor <= 0) {
            System.out.println("❌ Erro: Valor do saque deve ser positivo.");
            return false;
        }
        
        // Validação: deve ter saldo suficiente
        if (valor > saldo) {
            System.out.printf("❌ Erro: Saldo insuficiente. Saldo atual: R$ %.2f%n", getSaldo());
            return false;
        }
        
        // Realiza o saque
        saldo -= valor;
        System.out.printf("✓ Saque de R$ %.2f realizado. Saldo atual: R$ %.2f%n", 
                         valor, getSaldo());
        return true;
    }
    
    /**
     * Método para transferir dinheiro para outra conta
     * @param contaDestino Conta que receberá o dinheiro
     * @param valor Valor a ser transferido
     * @return true se a transferência foi realizada, false caso contrário
     */
    public boolean transferir(ContaBancaria contaDestino, double valor) {
        // Validação: conta destino não pode ser nula
        if (contaDestino == null) {
            System.out.println("❌ Erro: Conta destino inválida.");
            return false;
        }
        
        // Validação: não pode transferir para a mesma conta
        if (this.numeroConta.equals(contaDestino.getNumeroConta())) {
            System.out.println("❌ Erro: Não é possível transferir para a mesma conta.");
            return false;
        }
        
        // Tenta realizar o saque da conta origem
        if (sacar(valor)) {
            // Se o saque foi bem-sucedido, deposita na conta destino
            if (contaDestino.depositar(valor)) {
                System.out.printf("✓ Transferência de R$ %.2f realizada para conta %s%n", 
                                 valor, contaDestino.getNumeroConta());
                return true;
            } else {
                // Se o depósito falhou, desfaz o saque
                depositar(valor);
                System.out.println("❌ Erro na transferência. Operação cancelada.");
                return false;
            }
        }
        
        return false;  // Saque falhou
    }
    
    /**
     * Método para exibir informações da conta de forma segura
     * Não expõe dados sensíveis diretamente
     */
    public void exibirInformacoes() {
        System.out.println("\n=== Informações da Conta ===");
        System.out.println("Titular: " + titular);
        System.out.println("Número: " + numeroConta);
        System.out.printf("Saldo: R$ %.2f%n", getSaldo());
        System.out.println("Status: " + (contaAtiva ? "Ativa" : "Inativa"));
        System.out.println("========================\n");
    }
    
    /**
     * Override do método toString para representação textual da conta
     * @return String com informações básicas da conta
     */
    @Override
    public String toString() {
        return String.format("ContaBancaria{titular='%s', numero='%s', saldo=%.2f, ativa=%s}", 
                           titular, numeroConta, getSaldo(), contaAtiva);
    }
}