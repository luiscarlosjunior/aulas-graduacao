/**
 * Demonstração do Princípio da Responsabilidade Única (SRP)
 * Single Responsibility Principle
 * 
 * Uma classe deve ter apenas UMA razão para mudar
 * 
 * @author Sistema de Ensino - Princípios SOLID
 */

// ==========================================
// EXEMPLO RUIM - Violando SRP
// ==========================================

class FuncionarioRuim {
    private String nome;
    private String cargo;
    private double salario;
    
    public FuncionarioRuim(String nome, String cargo, double salario) {
        this.nome = nome;
        this.cargo = cargo;
        this.salario = salario;
    }
    
    // Responsabilidade 1: Cálculo de pagamento
    public double calcularPagamento() {
        if (cargo.equals("Gerente")) {
            return salario * 1.5;
        } else if (cargo.equals("Desenvolvedor")) {
            return salario * 1.2;
        }
        return salario;
    }
    
    // Responsabilidade 2: Geração de relatório
    public String gerarRelatorio() {
        return "=== RELATÓRIO ===\n" +
               "Nome: " + nome + "\n" +
               "Cargo: " + cargo + "\n" +
               "Salário Base: R$ " + String.format("%.2f", salario) + "\n" +
               "Salário Final: R$ " + String.format("%.2f", calcularPagamento());
    }
    
    // Responsabilidade 3: Persistência
    public void salvarNoBanco() {
        System.out.println("💾 Salvando funcionário '" + nome + "' no banco de dados...");
    }
    
    // Responsabilidade 4: Notificação
    public void enviarEmail(String mensagem) {
        System.out.println("📧 Enviando email para " + nome + ": " + mensagem);
    }
}

// ==========================================
// EXEMPLO BOM - Seguindo SRP
// ==========================================

// Classe com ÚNICA responsabilidade: representar dados do funcionário
class Funcionario {
    private String nome;
    private String cargo;
    private double salario;
    
    public Funcionario(String nome, String cargo, double salario) {
        this.nome = nome;
        this.cargo = cargo;
        this.salario = salario;
    }
    
    public String getNome() { return nome; }
    public String getCargo() { return cargo; }
    public double getSalario() { return salario; }
}

// Responsabilidade única: calcular salários
class CalculadoraSalario {
    public double calcular(Funcionario funcionario) {
        switch (funcionario.getCargo()) {
            case "Gerente":
                return funcionario.getSalario() * 1.5;
            case "Desenvolvedor":
                return funcionario.getSalario() * 1.2;
            case "Estagiário":
                return funcionario.getSalario() * 1.0;
            default:
                return funcionario.getSalario();
        }
    }
}

// Responsabilidade única: gerar relatórios
class GeradorRelatorio {
    private CalculadoraSalario calculadora;
    
    public GeradorRelatorio(CalculadoraSalario calculadora) {
        this.calculadora = calculadora;
    }
    
    public String gerar(Funcionario funcionario) {
        double salarioFinal = calculadora.calcular(funcionario);
        return "=== RELATÓRIO ===\n" +
               "Nome: " + funcionario.getNome() + "\n" +
               "Cargo: " + funcionario.getCargo() + "\n" +
               "Salário Base: R$ " + String.format("%.2f", funcionario.getSalario()) + "\n" +
               "Salário Final: R$ " + String.format("%.2f", salarioFinal);
    }
}

// Responsabilidade única: persistir dados
class FuncionarioRepository {
    public void salvar(Funcionario funcionario) {
        System.out.println("💾 Salvando funcionário '" + 
                         funcionario.getNome() + "' no banco de dados...");
        // Aqui entraria a lógica real de persistência
    }
    
    public void atualizar(Funcionario funcionario) {
        System.out.println("🔄 Atualizando funcionário '" + 
                         funcionario.getNome() + "' no banco...");
    }
}

// Responsabilidade única: enviar notificações
class NotificadorEmail {
    public void enviar(Funcionario funcionario, String mensagem) {
        System.out.println("📧 Enviando email para " + 
                         funcionario.getNome() + ": " + mensagem);
        // Aqui entraria a lógica real de envio de email
    }
}

// ==========================================
// DEMONSTRAÇÃO E TESTES
// ==========================================

public class ExemploSRP {
    
    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════════════════════════╗");
        System.out.println("║  PRINCÍPIO DA RESPONSABILIDADE ÚNICA (SRP)              ║");
        System.out.println("║  Single Responsibility Principle                        ║");
        System.out.println("╚══════════════════════════════════════════════════════════╝\n");
        
        // Exemplo RUIM
        System.out.println("❌ EXEMPLO RUIM - Classe com múltiplas responsabilidades:");
        System.out.println("─────────────────────────────────────────────────────────\n");
        demonstrarExemploRuim();
        
        System.out.println("\n" + "=".repeat(60) + "\n");
        
        // Exemplo BOM
        System.out.println("✅ EXEMPLO BOM - Classes com responsabilidade única:");
        System.out.println("─────────────────────────────────────────────────────────\n");
        demonstrarExemploBom();
        
        System.out.println("\n" + "=".repeat(60) + "\n");
        
        // Análise
        imprimirAnalise();
    }
    
    private static void demonstrarExemploRuim() {
        FuncionarioRuim func = new FuncionarioRuim("João Silva", "Desenvolvedor", 5000.0);
        
        System.out.println("Funcionário criado: João Silva");
        System.out.println("Problema: Uma única classe faz TUDO!\n");
        
        System.out.println("1. Calculando pagamento...");
        System.out.println("   Salário: R$ " + String.format("%.2f", func.calcularPagamento()));
        
        System.out.println("\n2. Gerando relatório...");
        System.out.println(func.gerarRelatorio());
        
        System.out.println("\n3. Salvando no banco...");
        func.salvarNoBanco();
        
        System.out.println("\n4. Enviando notificação...");
        func.enviarEmail("Bem-vindo à empresa!");
        
        System.out.println("\n⚠️  PROBLEMAS:");
        System.out.println("   • Se mudar o cálculo, precisa mexer na classe");
        System.out.println("   • Se mudar o formato do relatório, precisa mexer na classe");
        System.out.println("   • Se mudar o banco de dados, precisa mexer na classe");
        System.out.println("   • Se mudar o sistema de email, precisa mexer na classe");
        System.out.println("   • Impossível testar cada funcionalidade isoladamente");
        System.out.println("   • Classe tem 4 razões diferentes para mudar!");
    }
    
    private static void demonstrarExemploBom() {
        // Criar funcionário (apenas dados)
        Funcionario func = new Funcionario("Maria Santos", "Gerente", 8000.0);
        System.out.println("✓ Funcionário criado: Maria Santos");
        System.out.println("  (Classe Funcionario: apenas armazena dados)\n");
        
        // Calcular salário (responsabilidade separada)
        CalculadoraSalario calculadora = new CalculadoraSalario();
        double salarioCalculado = calculadora.calcular(func);
        System.out.println("1. ✓ Calculando pagamento...");
        System.out.println("   (CalculadoraSalario: apenas calcula)");
        System.out.println("   Salário: R$ " + String.format("%.2f", salarioCalculado));
        
        // Gerar relatório (responsabilidade separada)
        GeradorRelatorio gerador = new GeradorRelatorio(calculadora);
        String relatorio = gerador.gerar(func);
        System.out.println("\n2. ✓ Gerando relatório...");
        System.out.println("   (GeradorRelatorio: apenas gera relatórios)");
        System.out.println(relatorio);
        
        // Salvar no banco (responsabilidade separada)
        FuncionarioRepository repository = new FuncionarioRepository();
        System.out.println("\n3. ✓ Salvando no banco...");
        System.out.println("   (FuncionarioRepository: apenas persiste dados)");
        repository.salvar(func);
        
        // Enviar email (responsabilidade separada)
        NotificadorEmail notificador = new NotificadorEmail();
        System.out.println("\n4. ✓ Enviando notificação...");
        System.out.println("   (NotificadorEmail: apenas envia emails)");
        notificador.enviar(func, "Parabéns pela promoção!");
        
        System.out.println("\n✅ VANTAGENS:");
        System.out.println("   ✓ Cada classe tem UMA responsabilidade");
        System.out.println("   ✓ Fácil entender o que cada classe faz");
        System.out.println("   ✓ Mudanças são localizadas");
        System.out.println("   ✓ Fácil testar cada classe separadamente");
        System.out.println("   ✓ Classes reutilizáveis em outros contextos");
        System.out.println("   ✓ Fácil adicionar novos tipos de cálculo, relatório, etc.");
    }
    
    private static void imprimirAnalise() {
        System.out.println("📊 ANÁLISE COMPARATIVA\n");
        
        System.out.println("┌─────────────────────┬──────────────┬──────────────┐");
        System.out.println("│ Métrica             │ Sem SRP      │ Com SRP      │");
        System.out.println("├─────────────────────┼──────────────┼──────────────┤");
        System.out.println("│ Classes             │ 1            │ 5            │");
        System.out.println("│ Razões para mudar   │ 4            │ 1 cada       │");
        System.out.println("│ Testabilidade       │ Difícil      │ Fácil        │");
        System.out.println("│ Manutenibilidade    │ Baixa        │ Alta         │");
        System.out.println("│ Reutilização        │ Impossível   │ Total        │");
        System.out.println("│ Acoplamento         │ Alto         │ Baixo        │");
        System.out.println("└─────────────────────┴──────────────┴──────────────┘");
        
        System.out.println("\n💡 PRINCÍPIO CHAVE:");
        System.out.println("   \"Uma classe deve ter apenas UMA razão para mudar\"");
        System.out.println("\n🎯 COMO IDENTIFICAR:");
        System.out.println("   • A classe tem nome vago (Manager, Handler, Util)?");
        System.out.println("   • Tem muitos imports não relacionados?");
        System.out.println("   • Métodos que não usam os mesmos atributos?");
        System.out.println("   • Você precisa de \"e\" para descrever o que ela faz?");
        System.out.println("\n✅ BENEFÍCIO PRINCIPAL:");
        System.out.println("   Código mais coeso, menos acoplado e mais manutenível");
    }
}
