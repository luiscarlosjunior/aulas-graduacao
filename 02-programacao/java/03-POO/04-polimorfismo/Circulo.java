/**
 * Exemplo prático de Polimorfismo - Segunda Classe Concreta
 * 
 * Esta classe demonstra como diferentes formas podem implementar
 * os mesmos métodos abstratos de maneiras completamente diferentes.
 * Mostra:
 * - Implementação específica dos métodos abstratos de Forma
 * - Sobrescrita com cálculos específicos para círculo
 * - Métodos únicos que só fazem sentido para círculos
 * - Polimorfismo em ação (mesma interface, implementação diferente)
 * 
 * @author Curso POO Java
 */
public class Circulo extends Forma {
    
    // ===== ATRIBUTOS ESPECÍFICOS =====
    
    private double raio;             // Raio do círculo
    
    // Constante matemática PI (também disponível como Math.PI)
    private static final double PI = Math.PI;
    
    // ===== CONSTRUTORES =====
    
    /**
     * Construtor básico
     * @param raio Raio do círculo
     */
    public Circulo(double raio) {
        super("Círculo", "Vermelho");  // Chama construtor da classe pai
        this.raio = raio;
        System.out.printf("⭕ Círculo com raio %.1f criado%n", raio);
    }
    
    /**
     * Construtor com cor
     * @param raio Raio do círculo
     * @param cor Cor do círculo
     */
    public Circulo(double raio, String cor) {
        super("Círculo", cor);
        this.raio = raio;
        System.out.printf("⭕ Círculo %s com raio %.1f criado%n", cor, raio);
    }
    
    /**
     * Construtor completo com posição
     * @param raio Raio do círculo
     * @param cor Cor do círculo
     * @param x Posição X do centro
     * @param y Posição Y do centro
     */
    public Circulo(double raio, String cor, double x, double y) {
        super("Círculo", cor, x, y);
        this.raio = raio;
        System.out.printf("⭕ Círculo %s com raio %.1f criado na posição (%.1f, %.1f)%n", 
                         cor, raio, x, y);
    }
    
    // ===== MÉTODOS GETTERS E SETTERS =====
    
    public double getRaio() {
        return raio;
    }
    
    public void setRaio(double raio) {
        if (raio > 0) {
            this.raio = raio;
            System.out.printf("📏 Raio do círculo alterado para %.1f%n", raio);
        } else {
            System.out.println("❌ Raio deve ser positivo!");
        }
    }
    
    /**
     * Getter para o diâmetro (calculado a partir do raio)
     * @return Diâmetro do círculo
     */
    public double getDiametro() {
        return 2 * raio;
    }
    
    /**
     * Setter para o diâmetro (ajusta o raio automaticamente)
     * @param diametro Novo diâmetro
     */
    public void setDiametro(double diametro) {
        if (diametro > 0) {
            this.raio = diametro / 2;
            System.out.printf("📏 Diâmetro alterado para %.1f (raio = %.1f)%n", diametro, raio);
        } else {
            System.out.println("❌ Diâmetro deve ser positivo!");
        }
    }
    
    // ===== IMPLEMENTAÇÃO DOS MÉTODOS ABSTRATOS =====
    // Implementações específicas para círculo
    
    /**
     * Implementação do método abstrato calcularArea
     * Fórmula: área = π × raio²
     * @return Área do círculo
     */
    @Override
    public double calcularArea() {
        return PI * raio * raio;
    }
    
    /**
     * Implementação do método abstrato calcularPerimetro
     * Fórmula: perímetro = 2 × π × raio (também chamado de circunferência)
     * @return Perímetro (circunferência) do círculo
     */
    @Override
    public double calcularPerimetro() {
        return 2 * PI * raio;
    }
    
    /**
     * Implementação do método abstrato desenhar
     * Cria uma representação visual aproximada do círculo
     */
    @Override
    public void desenhar() {
        System.out.println("🎨 Desenhando " + nome + " " + cor + ":");
        
        // Desenha um círculo aproximado usando caracteres
        int tamanho = Math.min((int) (raio * 2), 15);  // Limita tamanho para não poluir
        int centro = tamanho / 2;
        
        for (int y = 0; y <= tamanho; y++) {
            System.out.print("   ");  // Indentação
            for (int x = 0; x <= tamanho; x++) {
                // Calcula distância do ponto ao centro
                double distancia = Math.sqrt(Math.pow(x - centro, 2) + Math.pow(y - centro, 2));
                
                // Desenha borda se próximo do raio
                if (Math.abs(distancia - (tamanho / 2.0)) <= 0.8) {
                    System.out.print("●");
                } else if (distancia < (tamanho / 2.0)) {
                    System.out.print(" ");  // Interior vazio
                } else {
                    System.out.print(" ");  // Exterior
                }
            }
            System.out.println();
        }
        System.out.printf("   Raio: %.1f, Diâmetro: %.1f%n", raio, getDiametro());
    }
    
    // ===== SOBRESCRITA DE MÉTODOS CONCRETOS =====
    
    /**
     * Sobrescrita do método escalar para aplicar o fator ao raio
     * @param fator Fator de escala
     */
    @Override
    public void escalar(double fator) {
        if (fator <= 0) {
            System.out.println("❌ Fator de escala deve ser positivo!");
            return;
        }
        
        super.escalar(fator);  // Chama método da classe pai
        
        // Aplica o fator ao raio específico do círculo
        raio *= fator;
        
        System.out.printf("⭕ Novo raio: %.1f (diâmetro: %.1f)%n", raio, getDiametro());
    }
    
    /**
     * Sobrescrita do método contemPonto para verificação precisa
     * @param x Coordenada X do ponto
     * @param y Coordenada Y do ponto
     * @return true se o ponto estiver dentro do círculo
     */
    @Override
    public boolean contemPonto(double x, double y) {
        // Calcula a distância do ponto ao centro do círculo
        double distancia = Math.sqrt(Math.pow(x - posicaoX, 2) + Math.pow(y - posicaoY, 2));
        
        // Ponto está dentro se a distância for menor ou igual ao raio
        return distancia <= raio;
    }
    
    /**
     * Sobrescrita do método sobrepoe para verificação específica entre círculos
     * @param outraForma Outra forma para verificar sobreposição
     * @return true se as formas se sobrepõem
     */
    @Override
    public boolean sobrepoe(Forma outraForma) {
        if (outraForma == null) {
            return false;
        }
        
        // Se a outra forma também for um círculo, usa cálculo preciso
        if (outraForma instanceof Circulo) {
            Circulo outroCirculo = (Circulo) outraForma;
            double distanciaCentros = distanciaAte(outroCirculo);
            double somaRaios = this.raio + outroCirculo.raio;
            
            return distanciaCentros <= somaRaios;
        }
        
        // Para outras formas, usa método da classe pai
        return super.sobrepoe(outraForma);
    }
    
    /**
     * Sobrescrita do método exibirInformacoes para incluir dados específicos
     */
    @Override
    public void exibirInformacoes() {
        super.exibirInformacoes();  // Chama método da classe pai
        
        // Adiciona informações específicas do círculo
        System.out.println("=== Detalhes do Círculo ===");
        System.out.printf("Raio: %.1f unidades%n", raio);
        System.out.printf("Diâmetro: %.1f unidades%n", getDiametro());
        System.out.printf("Circunferência: %.2f unidades%n", calcularPerimetro());
        System.out.printf("Área do setor (90°): %.2f unidades²%n", calcularAreaSetor(90));
        System.out.println("=========================\n");
    }
    
    // ===== MÉTODOS ESPECÍFICOS DO CÍRCULO =====
    // Métodos que só fazem sentido para círculos
    
    /**
     * Calcula a circunferência (mesmo que perímetro, mas nome mais específico)
     * @return Circunferência do círculo
     */
    public double calcularCircunferencia() {
        return calcularPerimetro();  // Reutiliza o método já implementado
    }
    
    /**
     * Calcula a área de um setor circular
     * @param anguloGraus Ângulo do setor em graus
     * @return Área do setor
     */
    public double calcularAreaSetor(double anguloGraus) {
        if (anguloGraus < 0 || anguloGraus > 360) {
            System.out.println("❌ Ângulo deve estar entre 0 e 360 graus!");
            return 0;
        }
        
        return (anguloGraus / 360.0) * calcularArea();
    }
    
    /**
     * Calcula o comprimento de um arco
     * @param anguloGraus Ângulo do arco em graus
     * @return Comprimento do arco
     */
    public double calcularComprimentoArco(double anguloGraus) {
        if (anguloGraus < 0 || anguloGraus > 360) {
            System.out.println("❌ Ângulo deve estar entre 0 e 360 graus!");
            return 0;
        }
        
        return (anguloGraus / 360.0) * calcularCircunferencia();
    }
    
    /**
     * Verifica se este círculo está completamente dentro de outro círculo
     * @param outroCirculo Círculo contenedor
     * @return true se este círculo está dentro do outro
     */
    public boolean estaDentro(Circulo outroCirculo) {
        if (outroCirculo == null) {
            return false;
        }
        
        double distanciaCentros = distanciaAte(outroCirculo);
        return (distanciaCentros + this.raio) <= outroCirculo.raio;
    }
    
    /**
     * Verifica se este círculo contém completamente outro círculo
     * @param outroCirculo Círculo a ser verificado
     * @return true se este círculo contém o outro
     */
    public boolean contem(Circulo outroCirculo) {
        if (outroCirculo == null) {
            return false;
        }
        
        return outroCirculo.estaDentro(this);
    }
    
    /**
     * Calcula a área de interseção com outro círculo
     * @param outroCirculo Outro círculo
     * @return Área de interseção (aproximada)
     */
    public double calcularAreaIntersecao(Circulo outroCirculo) {
        if (outroCirculo == null || !this.sobrepoe(outroCirculo)) {
            return 0.0;
        }
        
        // Cálculo simplificado - implementação completa seria mais complexa
        double distancia = distanciaAte(outroCirculo);
        
        if (distancia == 0) {
            // Círculos concêntricos - interseção é o menor círculo
            return Math.PI * Math.pow(Math.min(this.raio, outroCirculo.raio), 2);
        }
        
        // Para interseção parcial, retorna estimativa baseada na sobreposição
        double somaRaios = this.raio + outroCirculo.raio;
        double proporcaoSobreposicao = Math.max(0, (somaRaios - distancia) / somaRaios);
        
        return proporcaoSobreposicao * Math.min(this.calcularArea(), outroCirculo.calcularArea());
    }
    
    /**
     * Transforma o círculo em um quadrado de mesma área
     * @return Lado do quadrado equivalente
     */
    public double transformarEmQuadradoEquivalente() {
        double areaAtual = calcularArea();
        double ladoQuadrado = Math.sqrt(areaAtual);
        
        System.out.printf("🔄 Círculo de raio %.1f equivale a quadrado de lado %.2f%n",
                         raio, ladoQuadrado);
        
        return ladoQuadrado;
    }
    
    /**
     * Calcula quantos círculos deste tamanho cabem em outro círculo
     * @param circuloMaior Círculo contenedor
     * @return Número aproximado de círculos que cabem
     */
    public int quantosCabemEm(Circulo circuloMaior) {
        if (circuloMaior == null || circuloMaior.raio <= this.raio) {
            return 0;
        }
        
        // Cálculo baseado na razão das áreas
        double razaoAreas = circuloMaior.calcularArea() / this.calcularArea();
        
        return (int) Math.floor(razaoAreas * 0.9);  // Fator de correção para empacotamento
    }
    
    /**
     * Override do toString para incluir informações específicas do círculo
     */
    @Override
    public String toString() {
        return String.format("Circulo{raio=%.1f, diâmetro=%.1f, cor='%s', posição=(%.1f, %.1f), área=%.2f}", 
                           raio, getDiametro(), cor, posicaoX, posicaoY, calcularArea());
    }
}