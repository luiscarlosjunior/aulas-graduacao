/**
 * Classe de Teste - Demonstração Completa de Polimorfismo
 * 
 * Esta classe demonstra todos os tipos de polimorfismo em Java:
 * 1. Sobrecarga (Overloading) - mesmo nome, parâmetros diferentes
 * 2. Sobrescrita (Overriding) - classes filhas redefinem métodos
 * 3. Polimorfismo de runtime - referência pai, objeto filho
 * 4. Arrays polimórficos - coleções de diferentes tipos
 * 5. Interface polimórfica - diferentes implementações
 * 
 * @author Curso POO Java
 */
public class TestePolimorfismo {
    
    public static void main(String[] args) {
        
        System.out.println("=== DEMONSTRAÇÃO COMPLETA: POLIMORFISMO ===\n");
        
        // ===== 1. SOBRECARGA DE MÉTODOS (OVERLOADING) =====
        System.out.println("1️⃣ SOBRECARGA DE MÉTODOS (OVERLOADING)\n");
        
        Calculadora calc = new Calculadora();
        
        // Exibir métodos disponíveis
        calc.exibirMetodosDisponiveis();
        
        // Demonstrar diferentes versões do mesmo método
        System.out.println("🔢 Testando diferentes versões do método 'somar':");
        calc.somar(5, 3);                    // int + int
        calc.somar(5.5, 3.2);               // double + double
        calc.somar(2.1f, 4.3f);             // float + float
        calc.somar(1, 2, 3);                // três inteiros
        calc.somar(1, 2, 3, 4, 5);         // array variável
        calc.somar(10, 3.14);               // int + double
        calc.somar(2.71, 8);                // double + int
        
        System.out.println("\n🔢 Testando sobrecarga com multiplicação:");
        calc.multiplicar(4, 5);
        calc.multiplicar(2.5, 4.0);
        calc.multiplicar(2, 3, 4);
        
        System.out.println("\n🔢 Testando sobrecarga com potências:");
        calc.calcularPotencia(3);           // Quadrado (expoente padrão = 2)
        calc.calcularPotencia(2, 8);        // Base e expoente inteiro
        calc.calcularPotencia(4.0, 0.5);    // Raiz quadrada
        
        // Demonstração técnica
        calc.demonstrarResolucaoMetodo();
        
        // ===== 2. POLIMORFISMO COM CLASSES ABSTRATAS =====
        System.out.println("2️⃣ POLIMORFISMO COM CLASSES ABSTRATAS\n");
        
        // Criando diferentes formas
        System.out.println("--- Criando formas geométricas ---");
        Retangulo retangulo = new Retangulo(10, 5, "Azul", 0, 0);
        Circulo circulo = new Circulo(7, "Vermelho", 10, 10);
        Retangulo quadrado = new Retangulo(6, 6, "Verde", 20, 0);  // Quadrado é um retângulo especial
        
        System.out.println();
        
        // ===== 3. ARRAYS POLIMÓRFICOS =====
        System.out.println("3️⃣ ARRAYS POLIMÓRFICOS\n");
        
        // Array de Forma pode conter qualquer subclasse
        Forma[] formas = {retangulo, circulo, quadrado};
        
        System.out.println("--- Processando array de formas (polimorfismo) ---");
        for (int i = 0; i < formas.length; i++) {
            System.out.println("Forma " + (i + 1) + ":");
            
            // Mesmo método, comportamento diferente para cada forma
            formas[i].desenhar();              // Chama método específico de cada classe
            
            System.out.printf("   Área: %.2f unidades²%n", formas[i].calcularArea());
            System.out.printf("   Perímetro: %.2f unidades%n", formas[i].calcularPerimetro());
            System.out.println();
        }
        
        // ===== 4. POLIMORFISMO EM AÇÃO - MÉTODO GENÉRICO =====
        System.out.println("4️⃣ MÉTODOS POLIMÓRFICOS\n");
        
        System.out.println("--- Processando formas individualmente ---");
        processarForma(retangulo);
        processarForma(circulo);
        processarForma(quadrado);
        
        // ===== 5. CASTING E INSTANCEOF =====
        System.out.println("5️⃣ CASTING E VERIFICAÇÃO DE TIPOS\n");
        
        System.out.println("--- Demonstrando casting e instanceof ---");
        
        // Array polimórfico para demonstração
        Forma[] minhasFormas = {
            new Retangulo(8, 4, "Azul"),
            new Circulo(5, "Amarelo"),
            new Retangulo(3, 3, "Rosa")  // Quadrado
        };
        
        for (Forma forma : minhasFormas) {
            System.out.println("\nAnalisando: " + forma.getNome());
            
            // Verificação de tipo com instanceof
            if (forma instanceof Retangulo) {
                System.out.println("✓ É um Retângulo!");
                
                // Cast seguro para acessar métodos específicos
                Retangulo ret = (Retangulo) forma;
                System.out.printf("  Dimensões: %.1f x %.1f%n", ret.getLargura(), ret.getAltura());
                System.out.printf("  Diagonal: %.2f%n", ret.calcularDiagonal());
                System.out.println("  É quadrado? " + (ret.isQuadrado() ? "Sim" : "Não"));
                
            } else if (forma instanceof Circulo) {
                System.out.println("✓ É um Círculo!");
                
                // Cast seguro para acessar métodos específicos
                Circulo circ = (Circulo) forma;
                System.out.printf("  Raio: %.1f%n", circ.getRaio());
                System.out.printf("  Diâmetro: %.1f%n", circ.getDiametro());
                System.out.printf("  Circunferência: %.2f%n", circ.calcularCircunferencia());
            }
        }
        
        // ===== 6. SOBRECARGA NA CALCULADORA COM FORMAS =====
        System.out.println("\n6️⃣ SOBRECARGA COM OBJETOS\n");
        
        System.out.println("--- Testando sobrecarga de calcularAreaTotal ---");
        
        // Diferentes versões do mesmo método com diferentes parâmetros
        calc.calcularAreaTotal(retangulo, quadrado);           // Dois retângulos
        calc.calcularAreaTotal(circulo, new Circulo(3, "Roxo")); // Dois círculos
        calc.calcularAreaTotal(retangulo, circulo, quadrado);   // Array de formas
        
        // ===== 7. COMPARAÇÃO DE COMPORTAMENTOS =====
        System.out.println("\n7️⃣ COMPARAÇÃO DE COMPORTAMENTOS\n");
        
        System.out.println("--- Mesmo método, comportamentos diferentes ---");
        
        // Todas são formas, mas cada uma escala diferente
        System.out.println("Escalando todas as formas com fator 1.5:");
        for (Forma forma : formas) {
            System.out.println("\nAntes: " + forma.toString());
            forma.escalar(1.5);  // Cada classe implementa diferente
            System.out.println("Depois: " + forma.toString());
        }
        
        // ===== 8. VERIFICAÇÃO DE PONTOS E SOBREPOSIÇÕES =====
        System.out.println("\n8️⃣ VERIFICAÇÕES POLIMÓRFICAS\n");
        
        double pontoX = 5, pontoY = 5;
        System.out.printf("--- Verificando se ponto (%.1f, %.1f) está dentro das formas ---%n", pontoX, pontoY);
        
        for (Forma forma : formas) {
            boolean contem = forma.contemPonto(pontoX, pontoY);
            System.out.printf("%s: %s%n", forma.getNome(), 
                            contem ? "✓ Contém o ponto" : "✗ Não contém o ponto");
        }
        
        System.out.println("\n--- Verificando sobreposições entre formas ---");
        for (int i = 0; i < formas.length; i++) {
            for (int j = i + 1; j < formas.length; j++) {
                boolean sobrepoe = formas[i].sobrepoe(formas[j]);
                System.out.printf("%s e %s: %s%n", 
                                formas[i].getNome(), formas[j].getNome(),
                                sobrepoe ? "✓ Se sobrepõem" : "✗ Não se sobrepõem");
            }
        }
        
        // ===== 9. DEMONSTRAÇÃO AVANÇADA =====
        System.out.println("\n9️⃣ DEMONSTRAÇÃO AVANÇADA\n");
        
        // Encontrar a maior forma
        Forma maiorForma = encontrarMaiorForma(formas);
        System.out.println("Maior forma (por área): " + maiorForma.toString());
        
        // Calcular área total usando polimorfismo
        double areaTotal = calcularAreaTotalArray(formas);
        System.out.printf("Área total de todas as formas: %.2f unidades²%n", areaTotal);
        
        // ===== 10. SITUAÇÕES ESPECIAIS =====
        System.out.println("\n🔟 SITUAÇÕES ESPECIAIS\n");
        
        // Polimorfismo com null
        System.out.println("--- Testando com referências null ---");
        Forma formaNula = null;
        try {
            // Isso causaria NullPointerException
            // formaNula.calcularArea();
            System.out.println("✓ Evitando NullPointerException com verificação");
        } catch (Exception e) {
            System.out.println("❌ Erro: " + e.getMessage());
        }
        
        // Cast incorreto (comentado para não quebrar o programa)
        System.out.println("--- Demonstrando necessidade de verificação antes do cast ---");
        Forma umaForma = new Circulo(5, "Branco");
        
        // CORRETO: verificar antes de fazer cast
        if (umaForma instanceof Retangulo) {
            Retangulo ret = (Retangulo) umaForma;
        } else {
            System.out.println("✓ Verificação evitou ClassCastException - forma não é Retângulo");
        }
        
        // ===== RESUMO FINAL =====
        System.out.println("\n🎯 RESUMO DOS CONCEITOS DEMONSTRADOS\n");
        
        System.out.println("✅ Polimorfismo demonstrado com sucesso:");
        System.out.println("   1. Sobrecarga (Overloading) - múltiplas versões do mesmo método");
        System.out.println("   2. Sobrescrita (Overriding) - comportamentos específicos por classe");
        System.out.println("   3. Arrays polimórficos - diferentes objetos, mesma interface");
        System.out.println("   4. Casting seguro com instanceof");
        System.out.println("   5. Dynamic binding - método correto escolhido em runtime");
        System.out.println("   6. Flexibilidade e extensibilidade do código");
        
        System.out.println("\n✅ Demonstração de polimorfismo concluída!");
    }
    
    // ===== MÉTODOS AUXILIARES PARA DEMONSTRAÇÃO =====
    
    /**
     * Método polimórfico que funciona com qualquer Forma
     * Demonstra como o mesmo código funciona com diferentes implementações
     */
    public static void processarForma(Forma forma) {
        System.out.println("🔄 Processando " + forma.getNome() + ":");
        
        // Métodos polimórficos - implementação varia conforme a classe real
        forma.exibirInformacoes();
        forma.mover(5, 5);
        
        // Comportamento específico sem precisar saber o tipo exato
        System.out.printf("   Área calculada: %.2f unidades²%n", forma.calcularArea());
        System.out.printf("   Perímetro calculado: %.2f unidades%n", forma.calcularPerimetro());
        
        System.out.println();
    }
    
    /**
     * Encontra a forma com maior área usando polimorfismo
     */
    public static Forma encontrarMaiorForma(Forma[] formas) {
        if (formas == null || formas.length == 0) {
            return null;
        }
        
        Forma maior = formas[0];
        double maiorArea = maior.calcularArea();
        
        for (int i = 1; i < formas.length; i++) {
            double areaAtual = formas[i].calcularArea();  // Polimorfismo em ação
            if (areaAtual > maiorArea) {
                maior = formas[i];
                maiorArea = areaAtual;
            }
        }
        
        return maior;
    }
    
    /**
     * Calcula área total usando polimorfismo
     */
    public static double calcularAreaTotalArray(Forma[] formas) {
        double total = 0;
        
        for (Forma forma : formas) {
            total += forma.calcularArea();  // Cada forma calcula sua área específica
        }
        
        return total;
    }
}