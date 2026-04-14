/**
 * Encapsulamento em Java — Exemplo Industrial: Sistema de E-commerce
 *
 * ============================================================
 * POR QUE ENCAPSULAMENTO IMPORTA NA INDÚSTRIA?
 * ============================================================
 * Encapsulamento é o fundamento da SEGURANÇA e CONFIABILIDADE
 * de sistemas empresariais:
 *
 * 1. INTEGRIDADE DOS DADOS: Sem encapsulamento, qualquer código
 *    pode corromper dados críticos.
 *    Ex: conta.saldo = -99999999  ← isso não deve ser possível!
 *
 * 2. REGRAS DE NEGÓCIO PROTEGIDAS: As validações ficam dentro
 *    da classe, não espalhadas pelo sistema.
 *
 * 3. REFATORAÇÃO SEGURA: Você pode mudar a implementação interna
 *    sem quebrar o código que usa sua classe.
 *    Ex: mudar de `double saldo` para `BigDecimal saldo` internamente
 *    sem que nenhum chamador precise ser alterado.
 *
 * 4. API DESIGN: Você decide O QUE é público (a interface) e
 *    O QUE é privado (a implementação). Isso é design de software!
 *
 * Todos os frameworks populares (Spring, Hibernate, Jackson) dependem
 * fortemente de getters/setters para funcionar corretamente.
 * ============================================================
 *
 * @author Aulas Graduação
 * @version 2.0
 */
public class ProdutoEcommerce {

    // ============================================================
    // ATRIBUTOS PRIVADOS — ninguém pode alterá-los diretamente!
    // Isso força o uso dos setters que têm validação
    // ============================================================
    private String sku;                  // Código único do produto (Stock Keeping Unit)
    private String nome;
    private String descricao;
    private double preco;                // Preço em R$
    private int quantidadeEstoque;
    private boolean ativo;              // Produto ativo para venda?
    private double pesoKg;              // Para cálculo de frete
    private int totalVendas;            // Contador de vendas (somente leitura externa)

    // ============================================================
    // CONSTRUTOR — garante estado inicial válido
    // ============================================================
    public ProdutoEcommerce(String sku, String nome, double preco, int estoque) {
        setSku(sku);
        setNome(nome);
        setPreco(preco);
        setQuantidadeEstoque(estoque);
        this.ativo = true;
        this.totalVendas = 0;
        this.pesoKg = 0.5;  // peso padrão
    }

    // ============================================================
    // GETTERS — acesso de LEITURA controlado
    // ============================================================

    public String getSku() { return sku; }
    public String getNome() { return nome; }
    public String getDescricao() { return descricao; }
    public double getPreco() { return preco; }
    public int getQuantidadeEstoque() { return quantidadeEstoque; }
    public boolean isAtivo() { return ativo; }
    public double getPesoKg() { return pesoKg; }

    // Getter com processamento: retorna apenas se tem estoque disponível
    public boolean isDisponivel() {
        return ativo && quantidadeEstoque > 0;
    }

    // Getter calculado: não existe como atributo, é calculado dinamicamente
    public double getPrecoComDesconto(double percentualDesconto) {
        if (percentualDesconto < 0 || percentualDesconto > 100) {
            throw new IllegalArgumentException("Desconto deve ser entre 0 e 100%");
        }
        return preco * (1 - percentualDesconto / 100);
    }

    // totalVendas: somente leitura — não tem setter público!
    public int getTotalVendas() { return totalVendas; }

    // ============================================================
    // SETTERS — modificação COM VALIDAÇÃO
    // ============================================================

    public void setSku(String sku) {
        if (sku == null || !sku.matches("[A-Z0-9-]{5,20}")) {
            throw new IllegalArgumentException(
                "SKU inválido. Deve ter 5-20 caracteres alfanuméricos maiúsculos.");
        }
        this.sku = sku;
    }

    public void setNome(String nome) {
        if (nome == null || nome.trim().length() < 3 || nome.length() > 200) {
            throw new IllegalArgumentException("Nome deve ter entre 3 e 200 caracteres.");
        }
        this.nome = nome.trim();
    }

    public void setDescricao(String descricao) {
        // Descrição pode ser null (produto sem descrição ainda)
        this.descricao = descricao != null ? descricao.trim() : null;
    }

    public void setPreco(double preco) {
        if (preco < 0.01) {
            throw new IllegalArgumentException("Preço mínimo é R$ 0,01.");
        }
        if (preco > 1_000_000) {
            throw new IllegalArgumentException("Preço acima do limite máximo permitido.");
        }
        this.preco = preco;
    }

    public void setQuantidadeEstoque(int quantidade) {
        if (quantidade < 0) {
            throw new IllegalArgumentException("Estoque não pode ser negativo.");
        }
        this.quantidadeEstoque = quantidade;
    }

    public void setPesoKg(double pesoKg) {
        if (pesoKg <= 0) {
            throw new IllegalArgumentException("Peso deve ser positivo.");
        }
        this.pesoKg = pesoKg;
    }

    // ============================================================
    // MÉTODOS DE NEGÓCIO — operações que mudam o estado interno
    // ============================================================

    /**
     * Registra uma venda do produto.
     * O método controla internamente o estoque e contador de vendas.
     * @param quantidade quantidade vendida
     * @return true se a venda foi registrada, false se sem estoque
     */
    public boolean registrarVenda(int quantidade) {
        if (!isDisponivel()) {
            System.out.printf("  ❌ Produto '%s' não disponível para venda%n", nome);
            return false;
        }
        if (quantidade > quantidadeEstoque) {
            System.out.printf("  ❌ Estoque insuficiente. Disponível: %d, Solicitado: %d%n",
                quantidadeEstoque, quantidade);
            return false;
        }

        quantidadeEstoque -= quantidade;   // Reduz estoque
        totalVendas += quantidade;          // Incrementa contador (privado, não tem setter!)

        System.out.printf("  ✅ Venda registrada: %d x '%s' | Estoque restante: %d%n",
            quantidade, nome, quantidadeEstoque);

        if (quantidadeEstoque < 5) {
            System.out.printf("  ⚠️  ALERTA: Estoque baixo (%d unidades) — reposição necessária!%n",
                quantidadeEstoque);
        }

        return true;
    }

    /**
     * Ativa ou desativa o produto para venda.
     * Em e-commerce, produtos podem ser desativados temporariamente.
     */
    public void definirAtivo(boolean ativo) {
        this.ativo = ativo;
        System.out.printf("  Produto '%s' %s para vendas%n",
            nome, ativo ? "habilitado" : "desabilitado");
    }

    @Override
    public String toString() {
        return String.format("[%s] %s | R$%.2f | Estoque:%d | %s",
            sku, nome, preco, quantidadeEstoque,
            isDisponivel() ? "🟢 Disponível" : "🔴 Indisponível");
    }

    // ============================================================
    // PROGRAMA PRINCIPAL — demonstração
    // ============================================================
    public static void main(String[] args) {
        System.out.println("=== ENCAPSULAMENTO: SISTEMA DE PRODUTO E-COMMERCE ===\n");

        // Cria produto com validações no construtor
        ProdutoEcommerce produto = new ProdutoEcommerce(
            "FONE-BT-001",        // SKU
            "Fone Bluetooth Pro", // Nome
            249.90,               // Preço
            10                    // Estoque
        );
        produto.setDescricao("Fone de ouvido sem fio com cancelamento de ruído");
        produto.setPesoKg(0.3);

        System.out.println("--- Produto Criado ---");
        System.out.println(produto);

        System.out.println("\n--- Verificações via Getters ---");
        System.out.printf("  Disponível para compra: %b%n", produto.isDisponivel());
        System.out.printf("  Preço com 15%% desconto: R$%.2f%n",
            produto.getPrecoComDesconto(15));

        System.out.println("\n--- Simulando Vendas ---");
        produto.registrarVenda(3);  // OK
        produto.registrarVenda(5);  // OK — vai acionar alerta de estoque baixo
        produto.registrarVenda(5);  // Vai falhar: sem estoque

        System.out.println("\n--- Tentativa de Preço Inválido ---");
        try {
            produto.setPreco(-50.0);  // Deve lançar exceção
        } catch (IllegalArgumentException e) {
            System.out.println("  ❌ Validação funcionou: " + e.getMessage());
        }

        System.out.println("\n--- Estado Final ---");
        System.out.println(produto);
        System.out.printf("  Total de vendas registradas: %d unidades%n",
            produto.getTotalVendas());

        System.out.println("\n--- Demonstração da Proteção ---");
        System.out.println("  Sem encapsulamento: produto.totalVendas = 9999; ← IMPOSSÍVEL!");
        System.out.println("  Com encapsulamento: totalVendas só é alterado por registrarVenda()");
        System.out.println("  Isso GARANTE que os dados são sempre consistentes.");
    }
}
