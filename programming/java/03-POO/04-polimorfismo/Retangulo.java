/**
 * Exemplo prático de Polimorfismo - Classe Concreta
 * 
 * Esta classe concreta demonstra:
 * - Herança de classe abstrata (extends Forma)
 * - Implementação obrigatória de métodos abstratos
 * - Sobrescrita de métodos concretos para comportamento específico
 * - Adição de atributos e métodos específicos de retângulo
 * 
 * @author Curso POO Java
 */
public class Retangulo extends Forma {
    
    // ===== ATRIBUTOS ESPECÍFICOS =====
    
    private double largura;          // Largura do retângulo
    private double altura;           // Altura do retângulo
    
    // ===== CONSTRUTORES =====
    
    /**
     * Construtor básico
     * @param largura Largura do retângulo
     * @param altura Altura do retângulo
     */
    public Retangulo(double largura, double altura) {
        super("Retângulo", "Azul");  // Chama construtor da classe pai
        this.largura = largura;
        this.altura = altura;
        System.out.printf("📐 Retângulo %.1f x %.1f criado%n", largura, altura);
    }
    
    /**
     * Construtor com cor
     * @param largura Largura do retângulo
     * @param altura Altura do retângulo
     * @param cor Cor do retângulo
     */
    public Retangulo(double largura, double altura, String cor) {
        super("Retângulo", cor);
        this.largura = largura;
        this.altura = altura;
        System.out.printf("📐 Retângulo %s %.1f x %.1f criado%n", cor, largura, altura);
    }
    
    /**
     * Construtor completo com posição
     * @param largura Largura do retângulo
     * @param altura Altura do retângulo
     * @param cor Cor do retângulo
     * @param x Posição X
     * @param y Posição Y
     */
    public Retangulo(double largura, double altura, String cor, double x, double y) {
        super("Retângulo", cor, x, y);
        this.largura = largura;
        this.altura = altura;
        System.out.printf("📐 Retângulo %s %.1f x %.1f criado na posição (%.1f, %.1f)%n", 
                         cor, largura, altura, x, y);
    }
    
    // ===== MÉTODOS GETTERS E SETTERS =====
    
    public double getLargura() {
        return largura;
    }
    
    public void setLargura(double largura) {
        if (largura > 0) {
            this.largura = largura;
            System.out.printf("📏 Largura do retângulo alterada para %.1f%n", largura);
        } else {
            System.out.println("❌ Largura deve ser positiva!");
        }
    }
    
    public double getAltura() {
        return altura;
    }
    
    public void setAltura(double altura) {
        if (altura > 0) {
            this.altura = altura;
            System.out.printf("📏 Altura do retângulo alterada para %.1f%n", altura);
        } else {
            System.out.println("❌ Altura deve ser positiva!");
        }
    }
    
    /**
     * Método para redimensionar ambas as dimensões
     * @param novaLargura Nova largura
     * @param novaAltura Nova altura
     */
    public void redimensionar(double novaLargura, double novaAltura) {
        if (novaLargura > 0 && novaAltura > 0) {
            this.largura = novaLargura;
            this.altura = novaAltura;
            System.out.printf("📏 Retângulo redimensionado para %.1f x %.1f%n", 
                             novaLargura, novaAltura);
        } else {
            System.out.println("❌ Dimensões devem ser positivas!");
        }
    }
    
    // ===== IMPLEMENTAÇÃO DOS MÉTODOS ABSTRATOS =====
    // Estes métodos DEVEM ser implementados pois são abstratos na classe pai
    
    /**
     * Implementação do método abstrato calcularArea
     * Fórmula: área = largura × altura
     * @return Área do retângulo
     */
    @Override
    public double calcularArea() {
        return largura * altura;
    }
    
    /**
     * Implementação do método abstrato calcularPerimetro
     * Fórmula: perímetro = 2 × (largura + altura)
     * @return Perímetro do retângulo
     */
    @Override
    public double calcularPerimetro() {
        return 2 * (largura + altura);
    }
    
    /**
     * Implementação do método abstrato desenhar
     * Cria uma representação visual simples do retângulo
     */
    @Override
    public void desenhar() {
        System.out.println("🎨 Desenhando " + nome + " " + cor + ":");
        
        // Desenha uma representação simples usando caracteres
        int linhas = Math.min((int) altura, 8);  // Máximo 8 linhas para não poluir
        int colunas = Math.min((int) largura, 15); // Máximo 15 colunas
        
        for (int i = 0; i < linhas; i++) {
            System.out.print("   ");  // Indentação
            for (int j = 0; j < colunas; j++) {
                if (i == 0 || i == linhas - 1 || j == 0 || j == colunas - 1) {
                    System.out.print("█");  // Borda
                } else {
                    System.out.print(" ");  // Interior vazio
                }
            }
            System.out.println();
        }
        System.out.printf("   Dimensões: %.1f x %.1f%n", largura, altura);
    }
    
    // ===== SOBRESCRITA DE MÉTODOS CONCRETOS =====
    // Estes métodos já tinham implementação na classe pai, mas são especializados
    
    /**
     * Sobrescrita do método escalar para aplicar o fator às dimensões
     * @param fator Fator de escala
     */
    @Override
    public void escalar(double fator) {
        if (fator <= 0) {
            System.out.println("❌ Fator de escala deve ser positivo!");
            return;
        }
        
        super.escalar(fator);  // Chama método da classe pai primeiro
        
        // Aplica o fator às dimensões específicas do retângulo
        largura *= fator;
        altura *= fator;
        
        System.out.printf("📐 Novas dimensões: %.1f x %.1f%n", largura, altura);
    }
    
    /**
     * Sobrescrita do método contemPonto para verificação precisa
     * @param x Coordenada X do ponto
     * @param y Coordenada Y do ponto
     * @return true se o ponto estiver dentro do retângulo
     */
    @Override
    public boolean contemPonto(double x, double y) {
        // Calcula os limites do retângulo considerando a posição central
        double esquerda = posicaoX - largura / 2;
        double direita = posicaoX + largura / 2;
        double inferior = posicaoY - altura / 2;
        double superior = posicaoY + altura / 2;
        
        // Verifica se o ponto está dentro dos limites
        return x >= esquerda && x <= direita && y >= inferior && y <= superior;
    }
    
    /**
     * Sobrescrita do método exibirInformacoes para incluir dados específicos
     */
    @Override
    public void exibirInformacoes() {
        super.exibirInformacoes();  // Chama método da classe pai
        
        // Adiciona informações específicas do retângulo
        System.out.println("=== Detalhes do Retângulo ===");
        System.out.printf("Largura: %.1f unidades%n", largura);
        System.out.printf("Altura: %.1f unidades%n", altura);
        System.out.printf("Diagonal: %.2f unidades%n", calcularDiagonal());
        System.out.println("Tipo: " + (isQuadrado() ? "Quadrado" : "Retângulo"));
        System.out.println("===========================\n");
    }
    
    // ===== MÉTODOS ESPECÍFICOS DO RETÂNGULO =====
    // Métodos que só fazem sentido para retângulos
    
    /**
     * Calcula a diagonal do retângulo
     * Fórmula: diagonal = √(largura² + altura²)
     * @return Comprimento da diagonal
     */
    public double calcularDiagonal() {
        return Math.sqrt(largura * largura + altura * altura);
    }
    
    /**
     * Verifica se o retângulo é um quadrado
     * @return true se largura igual à altura
     */
    public boolean isQuadrado() {
        return Math.abs(largura - altura) < 0.001;  // Considera tolerância para ponto flutuante
    }
    
    /**
     * Calcula a razão de aspecto (largura/altura)
     * @return Razão de aspecto
     */
    public double calcularRazaoAspecto() {
        return altura != 0 ? largura / altura : Double.POSITIVE_INFINITY;
    }
    
    /**
     * Transforma o retângulo em um quadrado mantendo a área
     * @return Lado do quadrado equivalente
     */
    public double transformarEmQuadrado() {
        double areaAtual = calcularArea();
        double ladoQuadrado = Math.sqrt(areaAtual);
        
        System.out.printf("🔄 Transformando retângulo %.1f x %.1f em quadrado %.1f x %.1f%n",
                         largura, altura, ladoQuadrado, ladoQuadrado);
        
        largura = ladoQuadrado;
        altura = ladoQuadrado;
        
        return ladoQuadrado;
    }
    
    /**
     * Gira o retângulo 90 graus (troca largura e altura)
     */
    public void girar90Graus() {
        double temp = largura;
        largura = altura;
        altura = temp;
        
        System.out.printf("🔄 Retângulo girado 90°: agora %.1f x %.1f%n", largura, altura);
    }
    
    /**
     * Verifica se este retângulo pode conter outro retângulo
     * @param outroRetangulo Retângulo a ser verificado
     * @return true se pode conter o outro retângulo
     */
    public boolean podeConter(Retangulo outroRetangulo) {
        if (outroRetangulo == null) {
            return false;
        }
        
        return this.largura >= outroRetangulo.largura && 
               this.altura >= outroRetangulo.altura;
    }
    
    /**
     * Override do toString para incluir informações específicas do retângulo
     */
    @Override
    public String toString() {
        return String.format("Retangulo{largura=%.1f, altura=%.1f, cor='%s', posição=(%.1f, %.1f), área=%.2f}", 
                           largura, altura, cor, posicaoX, posicaoY, calcularArea());
    }
}