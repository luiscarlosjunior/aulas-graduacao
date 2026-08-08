/**
 * Exemplo prático de Polimorfismo - Sobrecarga de Métodos (Overloading)
 * 
 * Esta classe demonstra o polimorfismo de sobrecarga:
 * - Métodos com o mesmo nome mas parâmetros diferentes
 * - Compilador escolhe qual método usar baseado nos argumentos
 * - Diferentes tipos de dados e quantidades de parâmetros
 * - Flexibilidade para diferentes situações de uso
 * 
 * @author Curso POO Java
 */
public class Calculadora {
    
    // ===== SOBRECARGA COM DIFERENTES TIPOS =====
    // Mesmo nome "somar", tipos diferentes de parâmetros
    
    /**
     * Soma dois números inteiros
     * @param a Primeiro número
     * @param b Segundo número
     * @return Soma dos números
     */
    public int somar(int a, int b) {
        System.out.printf("➕ Somando inteiros: %d + %d = ", a, b);
        int resultado = a + b;
        System.out.println(resultado);
        return resultado;
    }
    
    /**
     * Soma dois números decimais (double)
     * @param a Primeiro número
     * @param b Segundo número
     * @return Soma dos números
     */
    public double somar(double a, double b) {
        System.out.printf("➕ Somando decimais: %.2f + %.2f = ", a, b);
        double resultado = a + b;
        System.out.printf("%.2f%n", resultado);
        return resultado;
    }
    
    /**
     * Soma dois números de ponto flutuante (float)
     * @param a Primeiro número
     * @param b Segundo número
     * @return Soma dos números
     */
    public float somar(float a, float b) {
        System.out.printf("➕ Somando floats: %.2f + %.2f = ", a, b);
        float resultado = a + b;
        System.out.printf("%.2f%n", resultado);
        return resultado;
    }
    
    // ===== SOBRECARGA COM DIFERENTES QUANTIDADES DE PARÂMETROS =====
    
    /**
     * Soma três números inteiros
     * @param a Primeiro número
     * @param b Segundo número
     * @param c Terceiro número
     * @return Soma dos números
     */
    public int somar(int a, int b, int c) {
        System.out.printf("➕ Somando três inteiros: %d + %d + %d = ", a, b, c);
        int resultado = a + b + c;
        System.out.println(resultado);
        return resultado;
    }
    
    /**
     * Soma array de números inteiros (número variável de parâmetros)
     * @param numeros Array de números a serem somados
     * @return Soma de todos os números
     */
    public int somar(int... numeros) {
        System.out.print("➕ Somando array: ");
        for (int i = 0; i < numeros.length; i++) {
            System.out.print(numeros[i]);
            if (i < numeros.length - 1) {
                System.out.print(" + ");
            }
        }
        System.out.print(" = ");
        
        int soma = 0;
        for (int numero : numeros) {
            soma += numero;
        }
        
        System.out.println(soma);
        return soma;
    }
    
    // ===== SOBRECARGA COM TIPOS MISTOS =====
    
    /**
     * Soma número inteiro com decimal
     * @param inteiro Número inteiro
     * @param decimal Número decimal
     * @return Soma dos números (como double)
     */
    public double somar(int inteiro, double decimal) {
        System.out.printf("➕ Somando int + double: %d + %.2f = ", inteiro, decimal);
        double resultado = inteiro + decimal;
        System.out.printf("%.2f%n", resultado);
        return resultado;
    }
    
    /**
     * Soma número decimal com inteiro (ordem diferente)
     * @param decimal Número decimal
     * @param inteiro Número inteiro
     * @return Soma dos números (como double)
     */
    public double somar(double decimal, int inteiro) {
        System.out.printf("➕ Somando double + int: %.2f + %d = ", decimal, inteiro);
        double resultado = decimal + inteiro;
        System.out.printf("%.2f%n", resultado);
        return resultado;
    }
    
    // ===== SOBRECARGA DO MÉTODO MULTIPLICAR =====
    
    /**
     * Multiplica dois inteiros
     */
    public int multiplicar(int a, int b) {
        System.out.printf("✖️ Multiplicando inteiros: %d × %d = ", a, b);
        int resultado = a * b;
        System.out.println(resultado);
        return resultado;
    }
    
    /**
     * Multiplica dois decimais
     */
    public double multiplicar(double a, double b) {
        System.out.printf("✖️ Multiplicando decimais: %.2f × %.2f = ", a, b);
        double resultado = a * b;
        System.out.printf("%.2f%n", resultado);
        return resultado;
    }
    
    /**
     * Multiplica três números
     */
    public int multiplicar(int a, int b, int c) {
        System.out.printf("✖️ Multiplicando três: %d × %d × %d = ", a, b, c);
        int resultado = a * b * c;
        System.out.println(resultado);
        return resultado;
    }
    
    // ===== SOBRECARGA COM OBJETOS =====
    
    /**
     * Calcula área total de formas geométricas
     * @param formas Array de formas
     * @return Área total
     */
    public double calcularAreaTotal(Forma... formas) {
        System.out.println("📐 Calculando área total de " + formas.length + " formas:");
        
        double areaTotal = 0;
        for (int i = 0; i < formas.length; i++) {
            double area = formas[i].calcularArea();
            System.out.printf("   %s: %.2f unidades²%n", formas[i].getNome(), area);
            areaTotal += area;
        }
        
        System.out.printf("📊 Área total: %.2f unidades²%n", areaTotal);
        return areaTotal;
    }
    
    /**
     * Calcula área de dois retângulos
     * @param ret1 Primeiro retângulo
     * @param ret2 Segundo retângulo
     * @return Área total
     */
    public double calcularAreaTotal(Retangulo ret1, Retangulo ret2) {
        System.out.println("📐 Calculando área de dois retângulos:");
        double area1 = ret1.calcularArea();
        double area2 = ret2.calcularArea();
        double total = area1 + area2;
        
        System.out.printf("   Retângulo 1: %.2f unidades²%n", area1);
        System.out.printf("   Retângulo 2: %.2f unidades²%n", area2);
        System.out.printf("📊 Total: %.2f unidades²%n", total);
        
        return total;
    }
    
    /**
     * Calcula área de dois círculos
     * @param circ1 Primeiro círculo
     * @param circ2 Segundo círculo
     * @return Área total
     */
    public double calcularAreaTotal(Circulo circ1, Circulo circ2) {
        System.out.println("📐 Calculando área de dois círculos:");
        double area1 = circ1.calcularArea();
        double area2 = circ2.calcularArea();
        double total = area1 + area2;
        
        System.out.printf("   Círculo 1: %.2f unidades²%n", area1);
        System.out.printf("   Círculo 2: %.2f unidades²%n", area2);
        System.out.printf("📊 Total: %.2f unidades²%n", total);
        
        return total;
    }
    
    // ===== SOBRECARGA COM PARÂMETROS OPCIONAIS (SIMULAÇÃO) =====
    
    /**
     * Calcula potência (base elevada a expoente)
     * Versão simples: expoente = 2 (quadrado)
     */
    public double calcularPotencia(double base) {
        return calcularPotencia(base, 2);  // Chama versão com dois parâmetros
    }
    
    /**
     * Calcula potência com expoente personalizado
     */
    public double calcularPotencia(double base, int expoente) {
        System.out.printf("⚡ Calculando %.2f^%d = ", base, expoente);
        double resultado = Math.pow(base, expoente);
        System.out.printf("%.2f%n", resultado);
        return resultado;
    }
    
    /**
     * Calcula potência com base e expoente decimais
     */
    public double calcularPotencia(double base, double expoente) {
        System.out.printf("⚡ Calculando %.2f^%.2f = ", base, expoente);
        double resultado = Math.pow(base, expoente);
        System.out.printf("%.2f%n", resultado);
        return resultado;
    }
    
    // ===== MÉTODOS AUXILIARES =====
    
    /**
     * Exibe informações sobre os métodos sobrecarregados disponíveis
     */
    public void exibirMetodosDisponiveis() {
        System.out.println("\n=== MÉTODOS SOBRECARREGADOS DISPONÍVEIS ===");
        System.out.println("📋 somar():");
        System.out.println("   - somar(int, int)");
        System.out.println("   - somar(double, double)");
        System.out.println("   - somar(float, float)");
        System.out.println("   - somar(int, int, int)");
        System.out.println("   - somar(int...)");
        System.out.println("   - somar(int, double)");
        System.out.println("   - somar(double, int)");
        
        System.out.println("\n📋 multiplicar():");
        System.out.println("   - multiplicar(int, int)");
        System.out.println("   - multiplicar(double, double)");
        System.out.println("   - multiplicar(int, int, int)");
        
        System.out.println("\n📋 calcularAreaTotal():");
        System.out.println("   - calcularAreaTotal(Forma...)");
        System.out.println("   - calcularAreaTotal(Retangulo, Retangulo)");
        System.out.println("   - calcularAreaTotal(Circulo, Circulo)");
        
        System.out.println("\n📋 calcularPotencia():");
        System.out.println("   - calcularPotencia(double)");
        System.out.println("   - calcularPotencia(double, int)");
        System.out.println("   - calcularPotencia(double, double)");
        System.out.println("==========================================\n");
    }
    
    /**
     * Demonstra como o Java escolhe qual método usar
     */
    public void demonstrarResolucaoMetodo() {
        System.out.println("🔍 DEMONSTRAÇÃO: Como o Java escolhe qual método usar\n");
        
        System.out.println("1️⃣ Argumentos exatos:");
        somar(5, 3);                    // Usa somar(int, int)
        somar(5.5, 3.2);               // Usa somar(double, double)
        
        System.out.println("\n2️⃣ Promoção automática:");
        somar((byte) 5, (short) 3);    // byte e short promovidos para int
        
        System.out.println("\n3️⃣ Diferentes quantidades:");
        somar(1, 2, 3);                // Usa somar(int, int, int)
        somar(1, 2, 3, 4, 5);         // Usa somar(int...)
        
        System.out.println("\n4️⃣ Tipos mistos:");
        somar(5, 3.14);                // Usa somar(int, double)
        somar(3.14, 5);                // Usa somar(double, int)
        
        System.out.println();
    }
}