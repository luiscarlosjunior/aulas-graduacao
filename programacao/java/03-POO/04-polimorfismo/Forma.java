/**
 * Exemplo prático de Polimorfismo - Classe Abstrata
 * 
 * Esta classe abstrata demonstra conceitos fundamentais:
 * - Classe abstrata (não pode ser instanciada diretamente)
 * - Métodos abstratos (devem ser implementados pelas subclasses)
 * - Métodos concretos (podem ser herdados ou sobrescritos)
 * - Base para polimorfismo (referência Forma para diferentes formas)
 * 
 * @author Curso POO Java
 */
public abstract class Forma {
    
    // ===== ATRIBUTOS PROTEGIDOS =====
    // Disponíveis para as classes filhas
    
    protected String nome;           // Nome da forma geométrica
    protected String cor;            // Cor da forma
    protected double posicaoX;       // Coordenada X no plano
    protected double posicaoY;       // Coordenada Y no plano
    
    // ===== CONSTRUTOR =====
    
    /**
     * Construtor da classe abstrata
     * @param nome Nome da forma
     * @param cor Cor da forma
     */
    public Forma(String nome, String cor) {
        this.nome = nome;
        this.cor = cor;
        this.posicaoX = 0.0;
        this.posicaoY = 0.0;
        System.out.println("🔷 Forma " + nome + " (" + cor + ") criada");
    }
    
    /**
     * Construtor completo com posição
     * @param nome Nome da forma
     * @param cor Cor da forma
     * @param x Posição X
     * @param y Posição Y
     */
    public Forma(String nome, String cor, double x, double y) {
        this.nome = nome;
        this.cor = cor;
        this.posicaoX = x;
        this.posicaoY = y;
        System.out.printf("🔷 Forma %s (%s) criada na posição (%.1f, %.1f)%n", 
                         nome, cor, x, y);
    }
    
    // ===== MÉTODOS GETTERS E SETTERS =====
    
    public String getNome() {
        return nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public String getCor() {
        return cor;
    }
    
    public void setCor(String cor) {
        this.cor = cor;
        System.out.println("🎨 " + nome + " agora é " + cor);
    }
    
    public double getPosicaoX() {
        return posicaoX;
    }
    
    public double getPosicaoY() {
        return posicaoY;
    }
    
    public void setPosicao(double x, double y) {
        this.posicaoX = x;
        this.posicaoY = y;
        System.out.printf("📍 %s movido para posição (%.1f, %.1f)%n", nome, x, y);
    }
    
    // ===== MÉTODOS ABSTRATOS =====
    // DEVEM ser implementados pelas classes filhas
    // Cada forma tem sua própria maneira de calcular área e perímetro
    
    /**
     * Método abstrato para calcular área
     * Cada forma geométrica implementa sua própria fórmula
     * @return Área da forma
     */
    public abstract double calcularArea();
    
    /**
     * Método abstrato para calcular perímetro
     * Cada forma geométrica implementa sua própria fórmula
     * @return Perímetro da forma
     */
    public abstract double calcularPerimetro();
    
    /**
     * Método abstrato para desenhar a forma
     * Cada forma tem sua representação visual específica
     */
    public abstract void desenhar();
    
    // ===== MÉTODOS CONCRETOS =====
    // Podem ser usados por todas as subclasses (herdados ou sobrescritos)
    
    /**
     * Método concreto para mover a forma
     * @param deltaX Deslocamento no eixo X
     * @param deltaY Deslocamento no eixo Y
     */
    public void mover(double deltaX, double deltaY) {
        double novaX = posicaoX + deltaX;
        double novaY = posicaoY + deltaY;
        
        System.out.printf("🚀 Movendo %s de (%.1f, %.1f) para (%.1f, %.1f)%n",
                         nome, posicaoX, posicaoY, novaX, novaY);
        
        setPosicao(novaX, novaY);
    }
    
    /**
     * Método concreto para rotacionar a forma
     * @param angulo Ângulo de rotação em graus
     */
    public void rotacionar(double angulo) {
        System.out.printf("🔄 %s rotacionado %.1f graus%n", nome, angulo);
    }
    
    /**
     * Método concreto para redimensionar (escalar) a forma
     * @param fator Fator de escala (1.0 = tamanho original, 2.0 = dobro, 0.5 = metade)
     */
    public void escalar(double fator) {
        if (fator <= 0) {
            System.out.println("❌ Fator de escala deve ser positivo!");
            return;
        }
        
        System.out.printf("📏 %s redimensionado com fator %.2f%n", nome, fator);
        // Nota: As subclasses podem sobrescrever este método para aplicar o fator
    }
    
    /**
     * Método concreto para verificar se um ponto está dentro da forma
     * Implementação básica - pode ser sobrescrita pelas subclasses
     * @param x Coordenada X do ponto
     * @param y Coordenada Y do ponto
     * @return true se o ponto estiver dentro da forma
     */
    public boolean contemPonto(double x, double y) {
        // Implementação genérica - verifica proximidade do centro
        double distancia = Math.sqrt(Math.pow(x - posicaoX, 2) + Math.pow(y - posicaoY, 2));
        return distancia <= 10.0;  // Considera "dentro" se estiver a 10 unidades do centro
    }
    
    /**
     * Método concreto para calcular distância até outra forma
     * @param outraForma Outra forma para calcular distância
     * @return Distância entre os centros das formas
     */
    public double distanciaAte(Forma outraForma) {
        if (outraForma == null) {
            return Double.MAX_VALUE;
        }
        
        double deltaX = outraForma.getPosicaoX() - this.posicaoX;
        double deltaY = outraForma.getPosicaoY() - this.posicaoY;
        
        return Math.sqrt(deltaX * deltaX + deltaY * deltaY);
    }
    
    /**
     * Método concreto para verificar se duas formas se sobrepõem
     * Implementação básica - pode ser refinada pelas subclasses
     * @param outraForma Outra forma para verificar sobreposição
     * @return true se as formas se sobrepõem
     */
    public boolean sobrepoe(Forma outraForma) {
        if (outraForma == null) {
            return false;
        }
        
        // Verifica se a distância é menor que um limite básico
        double distancia = distanciaAte(outraForma);
        return distancia < 15.0;  // Considera sobreposição se distância < 15 unidades
    }
    
    /**
     * Método concreto para exibir informações básicas da forma
     * Pode ser sobrescrito pelas subclasses para adicionar informações específicas
     */
    public void exibirInformacoes() {
        System.out.println("\n=== Informações da Forma ===");
        System.out.println("Nome: " + nome);
        System.out.println("Cor: " + cor);
        System.out.printf("Posição: (%.1f, %.1f)%n", posicaoX, posicaoY);
        System.out.printf("Área: %.2f unidades²%n", calcularArea());
        System.out.printf("Perímetro: %.2f unidades%n", calcularPerimetro());
        System.out.println("==========================\n");
    }
    
    /**
     * Método concreto para comparar áreas de duas formas
     * @param outraForma Outra forma para comparar
     * @return valor negativo se esta forma for menor, 0 se igual, positivo se maior
     */
    public int compararArea(Forma outraForma) {
        if (outraForma == null) {
            return 1;  // Esta forma é "maior" que null
        }
        
        double minhaArea = this.calcularArea();
        double outraArea = outraForma.calcularArea();
        
        return Double.compare(minhaArea, outraArea);
    }
    
    /**
     * Override do método equals para comparar formas
     * @param obj Objeto a ser comparado
     * @return true se as formas forem equivalentes
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        
        Forma forma = (Forma) obj;
        
        return Double.compare(forma.posicaoX, posicaoX) == 0 &&
               Double.compare(forma.posicaoY, posicaoY) == 0 &&
               nome.equals(forma.nome) &&
               cor.equals(forma.cor);
    }
    
    /**
     * Override do método toString para representação textual
     * @return String com informações básicas da forma
     */
    @Override
    public String toString() {
        return String.format("%s{nome='%s', cor='%s', posição=(%.1f, %.1f), área=%.2f}", 
                           getClass().getSimpleName(), nome, cor, posicaoX, posicaoY, calcularArea());
    }
}