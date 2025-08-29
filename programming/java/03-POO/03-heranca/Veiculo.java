/**
 * Exemplo Clássico da Literatura: Hierarquia de Veículos
 * 
 * Esta é uma das hierarquias mais famosas e didáticas da POO,
 * aparecendo em praticamente todos os livros de programação orientada a objetos.
 * 
 * Classe base Veiculo - representa as características comuns a todos os veículos
 * 
 * @author Curso POO Java - Exemplo Clássico
 */
public abstract class Veiculo {
    
    // ===== ATRIBUTOS COMUNS A TODOS OS VEÍCULOS =====
    protected String marca;              // Fabricante (Toyota, Ford, Honda, etc.)
    protected String modelo;             // Modelo específico (Corolla, Fiesta, Civic)
    protected int ano;                   // Ano de fabricação
    protected String cor;                // Cor do veículo
    protected double preco;              // Preço em reais
    protected int numeroRodas;           // Quantidade de rodas
    protected double velocidadeAtual;    // Velocidade atual (km/h)
    protected double velocidadeMaxima;   // Velocidade máxima (km/h)
    protected boolean ligado;            // Status do motor
    protected double combustivel;        // Nível de combustível (%)
    protected double quilometragem;      // Quilometragem total
    
    // ===== CONSTRUTOR =====
    
    /**
     * Construtor base para todos os veículos
     * 
     * @param marca Marca do veículo
     * @param modelo Modelo do veículo
     * @param ano Ano de fabricação
     * @param cor Cor do veículo
     * @param preco Preço do veículo
     * @param numeroRodas Número de rodas
     * @param velocidadeMaxima Velocidade máxima em km/h
     */
    public Veiculo(String marca, String modelo, int ano, String cor, 
                   double preco, int numeroRodas, double velocidadeMaxima) {
        this.marca = marca;
        this.modelo = modelo;
        this.ano = ano;
        this.cor = cor;
        this.preco = preco;
        this.numeroRodas = numeroRodas;
        this.velocidadeMaxima = velocidadeMaxima;
        
        // Estado inicial padrão
        this.velocidadeAtual = 0.0;
        this.ligado = false;
        this.combustivel = 100.0;  // Tanque cheio
        this.quilometragem = 0.0;
        
        System.out.println("🚗 Veículo " + marca + " " + modelo + " (" + ano + ") criado");
    }
    
    // ===== MÉTODOS COMUNS A TODOS OS VEÍCULOS =====
    
    /**
     * Liga o veículo - comportamento padrão que pode ser sobrescrito
     */
    public void ligar() {
        if (!ligado) {
            if (combustivel > 0) {
                ligado = true;
                System.out.println("🔑 " + marca + " " + modelo + " ligado!");
            } else {
                System.out.println("⛽ Não é possível ligar - sem combustível!");
            }
        } else {
            System.out.println("ℹ️ " + marca + " " + modelo + " já está ligado.");
        }
    }
    
    /**
     * Desliga o veículo
     */
    public void desligar() {
        if (ligado) {
            velocidadeAtual = 0.0;
            ligado = false;
            System.out.println("🔑 " + marca + " " + modelo + " desligado.");
        } else {
            System.out.println("ℹ️ " + marca + " " + modelo + " já está desligado.");
        }
    }
    
    /**
     * Acelerar - comportamento comum que pode ser especializado
     * @param incremento Incremento de velocidade em km/h
     */
    public void acelerar(double incremento) {
        if (!ligado) {
            System.out.println("❌ Não é possível acelerar com o veículo desligado!");
            return;
        }
        
        if (combustivel <= 0) {
            System.out.println("⛽ Sem combustível para acelerar!");
            return;
        }
        
        double novaVelocidade = velocidadeAtual + incremento;
        
        if (novaVelocidade > velocidadeMaxima) {
            velocidadeAtual = velocidadeMaxima;
            System.out.println("🏁 Velocidade máxima atingida: " + velocidadeMaxima + " km/h");
        } else {
            velocidadeAtual = novaVelocidade;
            System.out.println("🚀 Acelerando... Velocidade atual: " + velocidadeAtual + " km/h");
        }
        
        // Consumir combustível proporcionalmente
        consumirCombustivel(incremento * 0.1);
    }
    
    /**
     * Frear o veículo
     * @param decremento Redução de velocidade em km/h
     */
    public void frear(double decremento) {
        if (velocidadeAtual > 0) {
            velocidadeAtual = Math.max(0, velocidadeAtual - decremento);
            System.out.println("🛑 Freando... Velocidade atual: " + velocidadeAtual + " km/h");
        } else {
            System.out.println("ℹ️ " + marca + " " + modelo + " já está parado.");
        }
    }
    
    /**
     * Abastecer o veículo
     * @param litros Quantidade de litros para abastecer
     */
    public void abastecer(double litros) {
        double incremento = litros * 2; // Simulação: 1 litro = 2% do tanque
        combustivel = Math.min(100.0, combustivel + incremento);
        System.out.printf("⛽ Abastecido %.1f litros. Combustível: %.1f%%\n", litros, combustivel);
    }
    
    /**
     * Consumir combustível
     * @param quantidade Quantidade a ser consumida
     */
    protected void consumirCombustivel(double quantidade) {
        combustivel = Math.max(0, combustivel - quantidade);
        quilometragem += quantidade * 10; // Simulação simples
    }
    
    /**
     * Método abstrato - cada tipo de veículo emite som diferente
     * Este método DEVE ser implementado pelas classes filhas
     */
    public abstract void buzinar();
    
    /**
     * Método abstrato - cada tipo de veículo tem tipo de combustível específico
     */
    public abstract String getTipoCombustivel();
    
    // ===== GETTERS E SETTERS =====
    
    public String getMarca() { return marca; }
    public String getModelo() { return modelo; }
    public int getAno() { return ano; }
    public String getCor() { return cor; }
    public double getPreco() { return preco; }
    public int getNumeroRodas() { return numeroRodas; }
    public double getVelocidadeAtual() { return velocidadeAtual; }
    public double getVelocidadeMaxima() { return velocidadeMaxima; }
    public boolean isLigado() { return ligado; }
    public double getCombustivel() { return combustivel; }
    public double getQuilometragem() { return quilometragem; }
    
    /**
     * Exibir informações completas do veículo
     */
    public void exibirInformacoes() {
        System.out.println("\n=== Informações do Veículo ===");
        System.out.println("Marca: " + marca);
        System.out.println("Modelo: " + modelo);
        System.out.println("Ano: " + ano);
        System.out.println("Cor: " + cor);
        System.out.printf("Preço: R$ %.2f\n", preco);
        System.out.println("Rodas: " + numeroRodas);
        System.out.printf("Velocidade: %.1f/%.1f km/h\n", velocidadeAtual, velocidadeMaxima);
        System.out.println("Status: " + (ligado ? "Ligado" : "Desligado"));
        System.out.printf("Combustível: %.1f%%\n", combustivel);
        System.out.printf("Quilometragem: %.1f km\n", quilometragem);
        System.out.println("Tipo combustível: " + getTipoCombustivel());
        System.out.println("============================\n");
    }
    
    /**
     * Representação textual do veículo
     */
    @Override
    public String toString() {
        return String.format("%s %s (%d) - %.1f km/h [%s]", 
                           marca, modelo, ano, velocidadeAtual, 
                           ligado ? "Ligado" : "Desligado");
    }
    
    /**
     * Método utilitário para verificar se pode realizar operação
     */
    protected boolean verificarCondicoesDirecao() {
        if (!ligado) {
            System.out.println("❌ Veículo deve estar ligado!");
            return false;
        }
        if (combustivel <= 0) {
            System.out.println("⛽ Sem combustível!");
            return false;
        }
        return true;
    }
}