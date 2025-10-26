/**
 * Exemplo de Program to Interfaces, not Implementations
 * Código depende de abstrações (interfaces), não de classes concretas
 * 
 * BENEFÍCIO: Flexibilidade para trocar implementações sem modificar código cliente.
 * Facilita testes com mocks.
 */
import java.util.*;

// ✅ Interfaces definem contratos
interface GatewayPagamento {
    boolean processar(double valor);
    String getNome();
}

interface Logger {
    void log(String mensagem);
}

interface CalculadoraFrete {
    double calcular(double pesoKg, String destino);
}

// ✅ Implementações concretas de Gateway
class PayPalGateway implements GatewayPagamento {
    @Override
    public boolean processar(double valor) {
        System.out.println("  Processando via PayPal: R$ " + valor);
        return true;
    }
    
    @Override
    public String getNome() {
        return "PayPal";
    }
}

class StripeGateway implements GatewayPagamento {
    @Override
    public boolean processar(double valor) {
        System.out.println("  Processando via Stripe: R$ " + valor);
        return true;
    }
    
    @Override
    public String getNome() {
        return "Stripe";
    }
}

// ✅ Implementações concretas de Logger
class ConsoleLogger implements Logger {
    @Override
    public void log(String mensagem) {
        System.out.println("[LOG] " + mensagem);
    }
}

class FileLogger implements Logger {
    @Override
    public void log(String mensagem) {
        System.out.println("[FILE] Gravando em arquivo: " + mensagem);
    }
}

// ✅ Implementações de cálculo de frete
class FreteCorreios implements CalculadoraFrete {
    @Override
    public double calcular(double pesoKg, String destino) {
        return pesoKg * 5.0; // R$ 5 por kg
    }
}

class FreteExpresso implements CalculadoraFrete {
    @Override
    public double calcular(double pesoKg, String destino) {
        return pesoKg * 15.0; // R$ 15 por kg
    }
}

// ✅ Código depende de abstrações (interfaces)
class ProcessadorPagamento {
    private GatewayPagamento gateway;
    private Logger logger;
    private CalculadoraFrete calculadoraFrete;
    
    // ✅ Dependency Injection via construtor
    public ProcessadorPagamento(GatewayPagamento gateway, Logger logger, CalculadoraFrete frete) {
        this.gateway = gateway;
        this.logger = logger;
        this.calculadoraFrete = frete;
    }
    
    public void processar(double valorProduto, double pesoKg, String destino) {
        logger.log("Iniciando processamento de pagamento");
        
        double frete = calculadoraFrete.calcular(pesoKg, destino);
        double total = valorProduto + frete;
        
        logger.log("Valor do produto: R$ " + valorProduto);
        logger.log("Frete: R$ " + frete);
        logger.log("Total: R$ " + total);
        
        System.out.println("\nProcessando pagamento:");
        System.out.println("  Gateway: " + gateway.getNome());
        boolean sucesso = gateway.processar(total);
        
        if (sucesso) {
            logger.log("✓ Pagamento processado com sucesso");
        } else {
            logger.log("✗ Falha no pagamento");
        }
    }
}

public class ProgrameParaInterfaces {
    public static void main(String[] args) {
        System.out.println("=== PROGRAM TO INTERFACES, NOT IMPLEMENTATIONS ===");
        
        // ✅ Configuração 1: PayPal + Console + Correios
        System.out.println("\n--- CONFIGURAÇÃO 1 ---");
        ProcessadorPagamento proc1 = new ProcessadorPagamento(
            new PayPalGateway(),
            new ConsoleLogger(),
            new FreteCorreios()
        );
        proc1.processar(100.0, 2.0, "SP");
        
        // ✅ Configuração 2: Stripe + File + Expresso
        // Sem modificar ProcessadorPagamento!
        System.out.println("\n--- CONFIGURAÇÃO 2 ---");
        ProcessadorPagamento proc2 = new ProcessadorPagamento(
            new StripeGateway(),
            new FileLogger(),
            new FreteExpresso()
        );
        proc2.processar(100.0, 2.0, "RJ");
        
        // ✅ Configuração 3: Mix diferente
        System.out.println("\n--- CONFIGURAÇÃO 3 ---");
        ProcessadorPagamento proc3 = new ProcessadorPagamento(
            new PayPalGateway(),
            new FileLogger(),
            new FreteExpresso()
        );
        proc3.processar(200.0, 1.5, "MG");
        
        System.out.println("\n=== BENEFÍCIOS ===");
        System.out.println("1. ✓ Código depende de interfaces, não implementações");
        System.out.println("2. ✓ Fácil trocar implementações sem modificar ProcessadorPagamento");
        System.out.println("3. ✓ Testável com mocks/stubs");
        System.out.println("4. ✓ Baixo acoplamento entre módulos");
        System.out.println("5. ✓ Múltiplas configurações possíveis");
        System.out.println("6. ✓ Princípio DIP aplicado corretamente");
    }
}
