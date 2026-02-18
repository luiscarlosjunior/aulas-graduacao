import java.time.LocalDate;
import java.util.*;

/**
 * Exemplo Prático: Sistema de Gerenciamento de Pedidos
 * 
 * Demonstra aplicação prática de enumerações em um sistema real,
 * combinando enum básicos, com construtores, métodos e EnumMap.
 * 
 * @author Aulas Graduação
 */
public class SistemaGerenciamentoPedidos {
    
    /**
     * Enum representando status do pedido com transições válidas
     */
    enum StatusPedido {
        CRIADO("Pedido criado, aguardando pagamento"),
        PAGAMENTO_PENDENTE("Aguardando confirmação de pagamento"),
        PAGO("Pagamento confirmado"),
        EM_PREPARACAO("Pedido em preparação"),
        ENVIADO("Pedido enviado para entrega"),
        EM_TRANSITO("Pedido em trânsito"),
        ENTREGUE("Pedido entregue ao cliente"),
        CANCELADO("Pedido cancelado"),
        DEVOLVIDO("Pedido devolvido");
        
        private final String descricao;
        
        StatusPedido(String descricao) {
            this.descricao = descricao;
        }
        
        public String getDescricao() {
            return descricao;
        }
        
        /**
         * Verifica se pode transitar para outro status
         */
        public boolean podeTransitarPara(StatusPedido novoStatus) {
            return getTransicoesValidas().contains(novoStatus);
        }
        
        /**
         * Retorna transições válidas para cada status
         */
        private EnumSet<StatusPedido> getTransicoesValidas() {
            switch (this) {
                case CRIADO:
                    return EnumSet.of(PAGAMENTO_PENDENTE, CANCELADO);
                case PAGAMENTO_PENDENTE:
                    return EnumSet.of(PAGO, CANCELADO);
                case PAGO:
                    return EnumSet.of(EM_PREPARACAO, CANCELADO);
                case EM_PREPARACAO:
                    return EnumSet.of(ENVIADO, CANCELADO);
                case ENVIADO:
                    return EnumSet.of(EM_TRANSITO);
                case EM_TRANSITO:
                    return EnumSet.of(ENTREGUE, DEVOLVIDO);
                case ENTREGUE:
                    return EnumSet.of(DEVOLVIDO);
                case CANCELADO:
                case DEVOLVIDO:
                    return EnumSet.noneOf(StatusPedido.class);
                default:
                    return EnumSet.noneOf(StatusPedido.class);
            }
        }
        
        /**
         * Verifica se é status final
         */
        public boolean isStatusFinal() {
            return this == ENTREGUE || this == CANCELADO || this == DEVOLVIDO;
        }
    }
    
    /**
     * Enum representando categoria de produto
     */
    enum CategoriaProduto {
        ELETRONICOS("Eletrônicos", 0.15),
        LIVROS("Livros", 0.05),
        ROUPAS("Roupas e Acessórios", 0.10),
        ALIMENTOS("Alimentos e Bebidas", 0.08),
        MOVEIS("Móveis e Decoração", 0.12),
        ESPORTES("Esportes e Lazer", 0.10);
        
        private final String nome;
        private final double margemLucro;
        
        CategoriaProduto(String nome, double margemLucro) {
            this.nome = nome;
            this.margemLucro = margemLucro;
        }
        
        public String getNome() {
            return nome;
        }
        
        public double getMargemLucro() {
            return margemLucro;
        }
        
        public double calcularPrecoVenda(double precoCusto) {
            return precoCusto * (1 + margemLucro);
        }
    }
    
    /**
     * Enum representando prioridade de entrega
     */
    enum PrioridadeEntrega {
        NORMAL(0, 7, 10.00),
        EXPRESSA(1, 3, 25.00),
        URGENTE(2, 1, 50.00);
        
        private final int nivel;
        private final int diasEstimados;
        private final double custoAdicional;
        
        PrioridadeEntrega(int nivel, int diasEstimados, double custoAdicional) {
            this.nivel = nivel;
            this.diasEstimados = diasEstimados;
            this.custoAdicional = custoAdicional;
        }
        
        public int getNivel() {
            return nivel;
        }
        
        public int getDiasEstimados() {
            return diasEstimados;
        }
        
        public double getCustoAdicional() {
            return custoAdicional;
        }
    }
    
    /**
     * Classe representando um produto
     */
    static class Produto {
        private String nome;
        private CategoriaProduto categoria;
        private double precoCusto;
        
        public Produto(String nome, CategoriaProduto categoria, double precoCusto) {
            this.nome = nome;
            this.categoria = categoria;
            this.precoCusto = precoCusto;
        }
        
        public String getNome() {
            return nome;
        }
        
        public CategoriaProduto getCategoria() {
            return categoria;
        }
        
        public double getPrecoVenda() {
            return categoria.calcularPrecoVenda(precoCusto);
        }
        
        @Override
        public String toString() {
            return String.format("%s (%s) - R$ %.2f", 
                               nome, categoria.getNome(), getPrecoVenda());
        }
    }
    
    /**
     * Classe representando um pedido
     */
    static class Pedido {
        private static int contadorId = 1;
        
        private int id;
        private List<Produto> produtos;
        private StatusPedido status;
        private PrioridadeEntrega prioridade;
        private LocalDate dataCriacao;
        private List<String> historico;
        
        public Pedido(PrioridadeEntrega prioridade) {
            this.id = contadorId++;
            this.produtos = new ArrayList<>();
            this.status = StatusPedido.CRIADO;
            this.prioridade = prioridade;
            this.dataCriacao = LocalDate.now();
            this.historico = new ArrayList<>();
            adicionarHistorico("Pedido criado");
        }
        
        public void adicionarProduto(Produto produto) {
            produtos.add(produto);
            adicionarHistorico("Produto adicionado: " + produto.getNome());
        }
        
        public double calcularTotal() {
            double subtotal = produtos.stream()
                                     .mapToDouble(Produto::getPrecoVenda)
                                     .sum();
            return subtotal + prioridade.getCustoAdicional();
        }
        
        public boolean alterarStatus(StatusPedido novoStatus) {
            if (status.podeTransitarPara(novoStatus)) {
                StatusPedido statusAnterior = status;
                status = novoStatus;
                adicionarHistorico(String.format("Status alterado de %s para %s", 
                                                statusAnterior, novoStatus));
                return true;
            }
            return false;
        }
        
        private void adicionarHistorico(String evento) {
            historico.add(String.format("[%s] %s", 
                                       LocalDate.now(), evento));
        }
        
        public void exibirDetalhes() {
            System.out.println("═══════════════════════════════════════");
            System.out.println("PEDIDO #" + id);
            System.out.println("═══════════════════════════════════════");
            System.out.println("Data: " + dataCriacao);
            System.out.println("Status: " + status + " - " + status.getDescricao());
            System.out.println("Prioridade: " + prioridade + 
                             " (" + prioridade.getDiasEstimados() + " dias)");
            
            System.out.println("\nProdutos:");
            for (Produto p : produtos) {
                System.out.println("  • " + p);
            }
            
            System.out.printf("\nSubtotal: R$ %.2f\n", 
                            produtos.stream().mapToDouble(Produto::getPrecoVenda).sum());
            System.out.printf("Taxa de entrega %s: R$ %.2f\n", 
                            prioridade, prioridade.getCustoAdicional());
            System.out.printf("TOTAL: R$ %.2f\n", calcularTotal());
            
            System.out.println("\nHistórico:");
            for (String evento : historico) {
                System.out.println("  " + evento);
            }
            System.out.println("═══════════════════════════════════════\n");
        }
        
        public StatusPedido getStatus() {
            return status;
        }
        
        public int getId() {
            return id;
        }
    }
    
    /**
     * Classe para estatísticas do sistema
     */
    static class EstatisticasSistema {
        private EnumMap<StatusPedido, Integer> contagemPorStatus;
        private EnumMap<CategoriaProduto, Double> vendasPorCategoria;
        
        public EstatisticasSistema() {
            contagemPorStatus = new EnumMap<>(StatusPedido.class);
            vendasPorCategoria = new EnumMap<>(CategoriaProduto.class);
            
            // Inicializar todos os status com 0
            for (StatusPedido status : StatusPedido.values()) {
                contagemPorStatus.put(status, 0);
            }
            
            // Inicializar todas as categorias com 0.0
            for (CategoriaProduto cat : CategoriaProduto.values()) {
                vendasPorCategoria.put(cat, 0.0);
            }
        }
        
        public void registrarPedido(Pedido pedido) {
            // Atualizar contagem de status
            StatusPedido status = pedido.getStatus();
            contagemPorStatus.put(status, contagemPorStatus.get(status) + 1);
            
            // Atualizar vendas por categoria
            for (Produto produto : pedido.produtos) {
                CategoriaProduto cat = produto.getCategoria();
                double vendaAtual = vendasPorCategoria.get(cat);
                vendasPorCategoria.put(cat, vendaAtual + produto.getPrecoVenda());
            }
        }
        
        public void exibirRelatorio() {
            System.out.println("═══════════════════════════════════════");
            System.out.println("RELATÓRIO DE ESTATÍSTICAS");
            System.out.println("═══════════════════════════════════════");
            
            System.out.println("\nPedidos por Status:");
            int totalPedidos = 0;
            for (Map.Entry<StatusPedido, Integer> entry : contagemPorStatus.entrySet()) {
                if (entry.getValue() > 0) {
                    System.out.printf("  %s: %d pedidos\n", 
                                    entry.getKey(), entry.getValue());
                    totalPedidos += entry.getValue();
                }
            }
            System.out.println("  TOTAL: " + totalPedidos + " pedidos");
            
            System.out.println("\nVendas por Categoria:");
            double totalVendas = 0;
            for (Map.Entry<CategoriaProduto, Double> entry : vendasPorCategoria.entrySet()) {
                if (entry.getValue() > 0) {
                    System.out.printf("  %s: R$ %.2f\n", 
                                    entry.getKey().getNome(), entry.getValue());
                    totalVendas += entry.getValue();
                }
            }
            System.out.printf("  TOTAL: R$ %.2f\n", totalVendas);
            
            System.out.println("═══════════════════════════════════════\n");
        }
    }
    
    public static void main(String[] args) {
        System.out.println("=== SISTEMA DE GERENCIAMENTO DE PEDIDOS ===\n");
        
        // Criar produtos
        Produto notebook = new Produto("Notebook Dell", CategoriaProduto.ELETRONICOS, 2500.00);
        Produto livro = new Produto("Java Effective", CategoriaProduto.LIVROS, 80.00);
        Produto camiseta = new Produto("Camiseta Polo", CategoriaProduto.ROUPAS, 50.00);
        Produto cadeira = new Produto("Cadeira Gamer", CategoriaProduto.MOVEIS, 800.00);
        
        // Criar pedidos
        Pedido pedido1 = new Pedido(PrioridadeEntrega.EXPRESSA);
        pedido1.adicionarProduto(notebook);
        pedido1.adicionarProduto(livro);
        
        Pedido pedido2 = new Pedido(PrioridadeEntrega.NORMAL);
        pedido2.adicionarProduto(camiseta);
        pedido2.adicionarProduto(cadeira);
        
        // Processar pedido 1
        System.out.println("=== PROCESSAMENTO DO PEDIDO 1 ===\n");
        pedido1.exibirDetalhes();
        
        // Simular fluxo de status
        System.out.println("Alterando status do pedido 1...");
        if (pedido1.alterarStatus(StatusPedido.PAGAMENTO_PENDENTE)) {
            System.out.println("✓ Status alterado para PAGAMENTO_PENDENTE");
        }
        
        if (pedido1.alterarStatus(StatusPedido.PAGO)) {
            System.out.println("✓ Status alterado para PAGO");
        }
        
        if (pedido1.alterarStatus(StatusPedido.EM_PREPARACAO)) {
            System.out.println("✓ Status alterado para EM_PREPARACAO");
        }
        
        if (pedido1.alterarStatus(StatusPedido.ENVIADO)) {
            System.out.println("✓ Status alterado para ENVIADO");
        }
        
        // Tentativa de transição inválida
        System.out.println("\nTentando alterar para CRIADO (transição inválida)...");
        if (!pedido1.alterarStatus(StatusPedido.CRIADO)) {
            System.out.println("✗ Transição não permitida!");
        }
        
        System.out.println("\n");
        pedido1.exibirDetalhes();
        
        // Processar pedido 2
        System.out.println("=== PROCESSAMENTO DO PEDIDO 2 ===\n");
        pedido2.exibirDetalhes();
        
        pedido2.alterarStatus(StatusPedido.PAGAMENTO_PENDENTE);
        pedido2.alterarStatus(StatusPedido.PAGO);
        pedido2.alterarStatus(StatusPedido.EM_PREPARACAO);
        
        // Gerar estatísticas
        EstatisticasSistema stats = new EstatisticasSistema();
        stats.registrarPedido(pedido1);
        stats.registrarPedido(pedido2);
        stats.exibirRelatorio();
        
        // Demonstrar uso de EnumSet para filtros
        System.out.println("=== FILTROS COM ENUMSET ===\n");
        
        EnumSet<StatusPedido> statusAtivos = EnumSet.range(
            StatusPedido.CRIADO, StatusPedido.EM_TRANSITO
        );
        System.out.println("Status ativos (não finalizados): " + statusAtivos);
        
        EnumSet<StatusPedido> statusFinais = EnumSet.of(
            StatusPedido.ENTREGUE, StatusPedido.CANCELADO, StatusPedido.DEVOLVIDO
        );
        System.out.println("Status finais: " + statusFinais);
        
        // Verificar categorias mais vendidas
        System.out.println("\n=== ANÁLISE DE CATEGORIAS ===\n");
        for (CategoriaProduto cat : CategoriaProduto.values()) {
            System.out.printf("%s - Margem de lucro: %.0f%%\n", 
                            cat.getNome(), cat.getMargemLucro() * 100);
        }
    }
}
