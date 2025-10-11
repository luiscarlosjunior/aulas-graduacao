/**
 * Exemplo Completo - Todos os Princípios SOLID Integrados
 * 
 * Sistema de Gerenciamento de Pedidos de E-commerce
 * Demonstra como todos os 5 princípios SOLID trabalham juntos
 * 
 * @author Sistema de Ensino - Princípios SOLID
 */

import java.util.ArrayList;
import java.util.List;

// ==========================================
// INTERFACES (DIP + ISP)
// ==========================================

// ISP: Interfaces pequenas e focadas
interface Calculavel {
    double calcular();
}

interface Notificavel {
    void notificar(String mensagem);
}

interface Persistivel {
    void salvar();
}

interface Validavel {
    boolean validar();
}

// DIP: Abstrações para serviços
interface ServicoNotificacao {
    void enviar(String destinatario, String mensagem);
}

interface ServicoDesconto {
    double aplicarDesconto(double valor);
    String getDescricao();
}

interface RepositorioPedido {
    void salvar(Pedido pedido);
    Pedido buscar(int id);
}

// ==========================================
// ENTIDADES DE DOMÍNIO (SRP)
// ==========================================

// SRP: Classe com responsabilidade única - representar um produto
class Produto {
    private String nome;
    private double preco;
    private String categoria;
    
    public Produto(String nome, double preco, String categoria) {
        this.nome = nome;
        this.preco = preco;
        this.categoria = categoria;
    }
    
    public String getNome() { return nome; }
    public double getPreco() { return preco; }
    public String getCategoria() { return categoria; }
    
    @Override
    public String toString() {
        return String.format("%s (R$ %.2f)", nome, preco);
    }
}

// SRP: Classe com responsabilidade única - representar item do pedido
class ItemPedido {
    private Produto produto;
    private int quantidade;
    
    public ItemPedido(Produto produto, int quantidade) {
        this.produto = produto;
        this.quantidade = quantidade;
    }
    
    public double getSubtotal() {
        return produto.getPreco() * quantidade;
    }
    
    public Produto getProduto() { return produto; }
    public int getQuantidade() { return quantidade; }
}

// SRP: Classe com responsabilidade única - representar um pedido
class Pedido implements Calculavel, Validavel {
    private int id;
    private String cliente;
    private List<ItemPedido> itens;
    private String status;
    
    public Pedido(int id, String cliente) {
        this.id = id;
        this.cliente = cliente;
        this.itens = new ArrayList<>();
        this.status = "NOVO";
    }
    
    public void adicionarItem(ItemPedido item) {
        itens.add(item);
    }
    
    @Override
    public double calcular() {
        double total = 0;
        for (ItemPedido item : itens) {
            total += item.getSubtotal();
        }
        return total;
    }
    
    @Override
    public boolean validar() {
        return !itens.isEmpty() && cliente != null && !cliente.trim().isEmpty();
    }
    
    public int getId() { return id; }
    public String getCliente() { return cliente; }
    public List<ItemPedido> getItens() { return itens; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}

// ==========================================
// SERVIÇOS DE DESCONTO (OCP + LSP)
// ==========================================

// OCP: Extensível sem modificação
// LSP: Todas as implementações são substituíveis
class DescontoNenhum implements ServicoDesconto {
    @Override
    public double aplicarDesconto(double valor) {
        return valor;
    }
    
    @Override
    public String getDescricao() {
        return "Sem desconto";
    }
}

class DescontoPercentual implements ServicoDesconto {
    private double percentual;
    
    public DescontoPercentual(double percentual) {
        this.percentual = percentual;
    }
    
    @Override
    public double aplicarDesconto(double valor) {
        return valor * (1 - percentual / 100);
    }
    
    @Override
    public String getDescricao() {
        return String.format("%.0f%% de desconto", percentual);
    }
}

class DescontoValorFixo implements ServicoDesconto {
    private double valorDesconto;
    
    public DescontoValorFixo(double valorDesconto) {
        this.valorDesconto = valorDesconto;
    }
    
    @Override
    public double aplicarDesconto(double valor) {
        return Math.max(0, valor - valorDesconto);
    }
    
    @Override
    public String getDescricao() {
        return String.format("R$ %.2f de desconto", valorDesconto);
    }
}

class DescontoPrimeiraCompra implements ServicoDesconto {
    @Override
    public double aplicarDesconto(double valor) {
        return valor * 0.85; // 15% desconto
    }
    
    @Override
    public String getDescricao() {
        return "15% desconto - Primeira compra";
    }
}

// ==========================================
// SERVIÇOS DE NOTIFICAÇÃO (OCP + LSP + DIP)
// ==========================================

class EmailNotificacao implements ServicoNotificacao {
    @Override
    public void enviar(String destinatario, String mensagem) {
        System.out.println("📧 Email para " + destinatario);
        System.out.println("   " + mensagem);
    }
}

class SMSNotificacao implements ServicoNotificacao {
    @Override
    public void enviar(String destinatario, String mensagem) {
        System.out.println("📱 SMS para " + destinatario);
        System.out.println("   " + mensagem);
    }
}

class WhatsAppNotificacao implements ServicoNotificacao {
    @Override
    public void enviar(String destinatario, String mensagem) {
        System.out.println("💬 WhatsApp para " + destinatario);
        System.out.println("   " + mensagem);
    }
}

// ==========================================
// REPOSITÓRIO (SRP + DIP)
// ==========================================

// SRP: Responsabilidade única - persistir pedidos
class RepositorioPedidoMemoria implements RepositorioPedido {
    private List<Pedido> pedidos = new ArrayList<>();
    
    @Override
    public void salvar(Pedido pedido) {
        pedidos.add(pedido);
        System.out.println("💾 Pedido #" + pedido.getId() + " salvo na memória");
    }
    
    @Override
    public Pedido buscar(int id) {
        for (Pedido p : pedidos) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }
}

// ==========================================
// SERVIÇO DE PROCESSAMENTO (SRP + DIP)
// ==========================================

// SRP: Responsabilidade única - processar pedidos
class ProcessadorPedido {
    private RepositorioPedido repositorio;
    private ServicoNotificacao notificador;
    private ServicoDesconto servicoDesconto;
    
    // DIP: Injeção de dependências via construtor
    public ProcessadorPedido(RepositorioPedido repositorio,
                            ServicoNotificacao notificador,
                            ServicoDesconto servicoDesconto) {
        this.repositorio = repositorio;
        this.notificador = notificador;
        this.servicoDesconto = servicoDesconto;
    }
    
    public void processar(Pedido pedido) {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("🛒 PROCESSANDO PEDIDO #" + pedido.getId());
        System.out.println("=".repeat(60));
        
        // Validar pedido
        if (!pedido.validar()) {
            System.out.println("❌ Pedido inválido!");
            return;
        }
        
        // Exibir itens
        System.out.println("\n📦 Itens do pedido:");
        for (ItemPedido item : pedido.getItens()) {
            System.out.printf("   %dx %s = R$ %.2f\n",
                item.getQuantidade(),
                item.getProduto(),
                item.getSubtotal());
        }
        
        // Calcular totais
        double subtotal = pedido.calcular();
        double total = servicoDesconto.aplicarDesconto(subtotal);
        double economia = subtotal - total;
        
        System.out.println("\n💰 Valores:");
        System.out.printf("   Subtotal: R$ %.2f\n", subtotal);
        System.out.println("   Desconto: " + servicoDesconto.getDescricao());
        if (economia > 0) {
            System.out.printf("   Economia: R$ %.2f\n", economia);
        }
        System.out.printf("   TOTAL: R$ %.2f\n", total);
        
        // Salvar pedido
        pedido.setStatus("PROCESSADO");
        repositorio.salvar(pedido);
        
        // Notificar cliente
        String mensagem = String.format(
            "Pedido #%d confirmado! Total: R$ %.2f",
            pedido.getId(), total
        );
        notificador.enviar(pedido.getCliente(), mensagem);
        
        System.out.println("\n✅ Pedido processado com sucesso!");
    }
}

// ==========================================
// GERADOR DE RELATÓRIOS (SRP)
// ==========================================

// SRP: Responsabilidade única - gerar relatórios
class GeradorRelatorioCompleto {
    public void gerarResumoPedido(Pedido pedido, ServicoDesconto desconto) {
        System.out.println("\n" + "━".repeat(60));
        System.out.println("📊 RELATÓRIO DO PEDIDO #" + pedido.getId());
        System.out.println("━".repeat(60));
        System.out.println("Cliente: " + pedido.getCliente());
        System.out.println("Status: " + pedido.getStatus());
        System.out.println("\nItens:");
        
        for (ItemPedido item : pedido.getItens()) {
            System.out.printf("  • %dx %s\n",
                item.getQuantidade(),
                item.getProduto());
        }
        
        double subtotal = pedido.calcular();
        double total = desconto.aplicarDesconto(subtotal);
        
        System.out.printf("\nSubtotal: R$ %.2f\n", subtotal);
        System.out.println("Desconto: " + desconto.getDescricao());
        System.out.printf("Total: R$ %.2f\n", total);
        System.out.println("━".repeat(60));
    }
}

// ==========================================
// DEMONSTRAÇÃO COMPLETA
// ==========================================

public class ExemploCompleto {
    
    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════════════════════════╗");
        System.out.println("║  EXEMPLO COMPLETO - TODOS OS PRINCÍPIOS SOLID           ║");
        System.out.println("║  Sistema de Gerenciamento de Pedidos E-commerce        ║");
        System.out.println("╚══════════════════════════════════════════════════════════╝");
        
        System.out.println("\n🎯 Este exemplo demonstra:");
        System.out.println("   [S] Single Responsibility - Classes com única responsabilidade");
        System.out.println("   [O] Open/Closed - Extensível sem modificação");
        System.out.println("   [L] Liskov Substitution - Subtipos substituíveis");
        System.out.println("   [I] Interface Segregation - Interfaces específicas");
        System.out.println("   [D] Dependency Inversion - Dependência de abstrações");
        
        // Criar produtos
        Produto notebook = new Produto("Notebook Dell", 3500.00, "Eletrônicos");
        Produto mouse = new Produto("Mouse Logitech", 80.00, "Periféricos");
        Produto teclado = new Produto("Teclado Mecânico", 450.00, "Periféricos");
        Produto monitor = new Produto("Monitor LG 27\"", 1200.00, "Eletrônicos");
        
        // Processar Pedido 1 - Cliente Regular
        System.out.println("\n" + "█".repeat(60));
        System.out.println("CENÁRIO 1: Cliente Regular com Desconto Percentual");
        System.out.println("█".repeat(60));
        
        Pedido pedido1 = new Pedido(1001, "joao@email.com");
        pedido1.adicionarItem(new ItemPedido(notebook, 1));
        pedido1.adicionarItem(new ItemPedido(mouse, 2));
        
        ProcessadorPedido processador1 = new ProcessadorPedido(
            new RepositorioPedidoMemoria(),
            new EmailNotificacao(),
            new DescontoPercentual(10) // 10% desconto
        );
        
        processador1.processar(pedido1);
        
        // Processar Pedido 2 - Primeira Compra
        System.out.println("\n" + "█".repeat(60));
        System.out.println("CENÁRIO 2: Primeira Compra com Notificação SMS");
        System.out.println("█".repeat(60));
        
        Pedido pedido2 = new Pedido(1002, "+5511999999999");
        pedido2.adicionarItem(new ItemPedido(teclado, 1));
        pedido2.adicionarItem(new ItemPedido(mouse, 1));
        
        ProcessadorPedido processador2 = new ProcessadorPedido(
            new RepositorioPedidoMemoria(),
            new SMSNotificacao(),
            new DescontoPrimeiraCompra() // 15% desconto primeira compra
        );
        
        processador2.processar(pedido2);
        
        // Processar Pedido 3 - Desconto Fixo
        System.out.println("\n" + "█".repeat(60));
        System.out.println("CENÁRIO 3: Compra Grande com Desconto Fixo e WhatsApp");
        System.out.println("█".repeat(60));
        
        Pedido pedido3 = new Pedido(1003, "maria@email.com");
        pedido3.adicionarItem(new ItemPedido(notebook, 1));
        pedido3.adicionarItem(new ItemPedido(monitor, 2));
        pedido3.adicionarItem(new ItemPedido(teclado, 1));
        pedido3.adicionarItem(new ItemPedido(mouse, 2));
        
        ProcessadorPedido processador3 = new ProcessadorPedido(
            new RepositorioPedidoMemoria(),
            new WhatsAppNotificacao(),
            new DescontoValorFixo(500.00) // R$ 500 de desconto
        );
        
        processador3.processar(pedido3);
        
        // Gerar relatórios
        System.out.println("\n" + "█".repeat(60));
        System.out.println("RELATÓRIOS");
        System.out.println("█".repeat(60));
        
        GeradorRelatorioCompleto gerador = new GeradorRelatorioCompleto();
        gerador.gerarResumoPedido(pedido1, new DescontoPercentual(10));
        gerador.gerarResumoPedido(pedido2, new DescontoPrimeiraCompra());
        gerador.gerarResumoPedido(pedido3, new DescontoValorFixo(500.00));
        
        // Análise dos princípios aplicados
        imprimirAnalise();
    }
    
    private static void imprimirAnalise() {
        System.out.println("\n" + "═".repeat(60));
        System.out.println("📚 ANÁLISE DOS PRINCÍPIOS SOLID APLICADOS");
        System.out.println("═".repeat(60));
        
        System.out.println("\n[S] SINGLE RESPONSIBILITY PRINCIPLE");
        System.out.println("─".repeat(60));
        System.out.println("✓ Pedido: apenas representa dados do pedido");
        System.out.println("✓ ProcessadorPedido: apenas processa pedidos");
        System.out.println("✓ GeradorRelatorio: apenas gera relatórios");
        System.out.println("✓ RepositorioPedido: apenas persiste dados");
        System.out.println("✓ Cada classe tem UMA razão para mudar");
        
        System.out.println("\n[O] OPEN/CLOSED PRINCIPLE");
        System.out.println("─".repeat(60));
        System.out.println("✓ Novos descontos: adicionar classe, não modificar código");
        System.out.println("✓ Novos canais de notificação: extensão, não modificação");
        System.out.println("✓ Novos repositórios: plug-and-play");
        System.out.println("✓ Sistema ABERTO para extensão, FECHADO para modificação");
        
        System.out.println("\n[L] LISKOV SUBSTITUTION PRINCIPLE");
        System.out.println("─".repeat(60));
        System.out.println("✓ Qualquer ServicoDesconto pode substituir outro");
        System.out.println("✓ Qualquer ServicoNotificacao pode substituir outro");
        System.out.println("✓ Qualquer RepositorioPedido pode substituir outro");
        System.out.println("✓ Substituição sem quebrar funcionalidade");
        
        System.out.println("\n[I] INTERFACE SEGREGATION PRINCIPLE");
        System.out.println("─".repeat(60));
        System.out.println("✓ Interfaces pequenas: Calculavel, Validavel, Notificavel");
        System.out.println("✓ Classes implementam apenas o que precisam");
        System.out.println("✓ Sem métodos não utilizados");
        System.out.println("✓ Interfaces focadas e coesas");
        
        System.out.println("\n[D] DEPENDENCY INVERSION PRINCIPLE");
        System.out.println("─".repeat(60));
        System.out.println("✓ ProcessadorPedido depende de ABSTRAÇÕES");
        System.out.println("✓ Não depende de implementações concretas");
        System.out.println("✓ Injeção de dependências via construtor");
        System.out.println("✓ Fácil testar com mocks");
        System.out.println("✓ Baixo acoplamento, alta coesão");
        
        System.out.println("\n" + "═".repeat(60));
        System.out.println("✅ RESULTADO: Sistema flexível, testável e manutenível!");
        System.out.println("═".repeat(60));
    }
}
