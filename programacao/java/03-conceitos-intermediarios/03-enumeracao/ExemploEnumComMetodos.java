import java.util.*;

/**
 * Exemplo de Enumerações com Métodos
 * 
 * Demonstra como enums podem ter métodos concretos e abstratos,
 * permitindo encapsular comportamento relacionado às constantes.
 * 
 * @author Aulas Graduação
 */
public class ExemploEnumComMetodos {
    
    /**
     * Enum representando operações matemáticas
     * Cada constante implementa seu próprio comportamento
     */
    enum Operacao {
        SOMA("+") {
            @Override
            public double calcular(double x, double y) {
                return x + y;
            }
        },
        SUBTRACAO("-") {
            @Override
            public double calcular(double x, double y) {
                return x - y;
            }
        },
        MULTIPLICACAO("*") {
            @Override
            public double calcular(double x, double y) {
                return x * y;
            }
        },
        DIVISAO("/") {
            @Override
            public double calcular(double x, double y) {
                if (y == 0) {
                    throw new ArithmeticException("Divisão por zero");
                }
                return x / y;
            }
        },
        POTENCIA("^") {
            @Override
            public double calcular(double x, double y) {
                return Math.pow(x, y);
            }
        };
        
        private final String simbolo;
        
        Operacao(String simbolo) {
            this.simbolo = simbolo;
        }
        
        public String getSimbolo() {
            return simbolo;
        }
        
        // Método abstrato que cada constante deve implementar
        public abstract double calcular(double x, double y);
        
        @Override
        public String toString() {
            return simbolo;
        }
    }
    
    /**
     * Enum representando planetas com métodos de cálculo
     */
    enum Planeta {
        MERCURIO(3.303e+23, 2.4397e6),
        VENUS(4.869e+24, 6.0518e6),
        TERRA(5.976e+24, 6.37814e6),
        MARTE(6.421e+23, 3.3972e6),
        JUPITER(1.9e+27, 7.1492e7),
        SATURNO(5.688e+26, 6.0268e7),
        URANO(8.686e+25, 2.5559e7),
        NETUNO(1.024e+26, 2.4746e7);
        
        private final double massa; // em kg
        private final double raio;  // em metros
        private static final double G = 6.67300E-11; // constante gravitacional
        
        Planeta(double massa, double raio) {
            this.massa = massa;
            this.raio = raio;
        }
        
        public double getMassa() {
            return massa;
        }
        
        public double getRaio() {
            return raio;
        }
        
        /**
         * Calcula a gravidade superficial do planeta
         */
        public double gravidadeSuperficial() {
            return G * massa / (raio * raio);
        }
        
        /**
         * Calcula o peso de um objeto no planeta
         */
        public double calcularPeso(double massaNaTerra) {
            return massaNaTerra * gravidadeSuperficial();
        }
        
        /**
         * Retorna descrição formatada do planeta
         */
        public String getDescricao() {
            return String.format("%s - Massa: %.3e kg, Raio: %.3e m", 
                               name(), massa, raio);
        }
    }
    
    /**
     * Enum representando níveis de log com métodos de formatação
     */
    enum NivelLog {
        DEBUG("DEBUG", "\u001B[36m"),    // Ciano
        INFO("INFO", "\u001B[32m"),      // Verde
        WARN("WARN", "\u001B[33m"),      // Amarelo
        ERROR("ERROR", "\u001B[31m"),    // Vermelho
        FATAL("FATAL", "\u001B[35m");    // Magenta
        
        private static final String RESET = "\u001B[0m";
        private final String label;
        private final String cor;
        
        NivelLog(String label, String cor) {
            this.label = label;
            this.cor = cor;
        }
        
        /**
         * Formata mensagem de log com cor
         */
        public String formatarMensagem(String mensagem) {
            return cor + "[" + label + "] " + mensagem + RESET;
        }
        
        /**
         * Verifica se o nível é crítico
         */
        public boolean isCritico() {
            return this == ERROR || this == FATAL;
        }
        
        /**
         * Verifica se deve exibir baseado no nível mínimo
         */
        public boolean deveExibir(NivelLog nivelMinimo) {
            return this.ordinal() >= nivelMinimo.ordinal();
        }
    }
    
    public static void main(String[] args) {
        System.out.println("=== ENUMERAÇÕES COM MÉTODOS ===\n");
        
        exemploOperacoesMatematicas();
        System.out.println();
        
        exemploPlanetas();
        System.out.println();
        
        exemploNiveisLog();
        System.out.println();
        
        exemploCalculadora();
    }
    
    /**
     * Demonstra enum com métodos abstratos (Operação)
     */
    private static void exemploOperacoesMatematicas() {
        System.out.println("--- OPERAÇÕES MATEMÁTICAS ---");
        
        double x = 10.0;
        double y = 3.0;
        
        // Cada constante implementa seu próprio cálculo
        for (Operacao op : Operacao.values()) {
            try {
                double resultado = op.calcular(x, y);
                System.out.printf("%.1f %s %.1f = %.2f\n", 
                                x, op.getSimbolo(), y, resultado);
            } catch (ArithmeticException e) {
                System.out.printf("%.1f %s %.1f = Erro: %s\n", 
                                x, op.getSimbolo(), y, e.getMessage());
            }
        }
    }
    
    /**
     * Demonstra enum com métodos de cálculo (Planeta)
     */
    private static void exemploPlanetas() {
        System.out.println("--- PLANETAS DO SISTEMA SOLAR ---");
        
        double massaTerrestre = 75.0; // kg na Terra
        
        System.out.println("Peso de uma pessoa de " + massaTerrestre + " kg:\n");
        
        for (Planeta p : Planeta.values()) {
            double peso = p.calcularPeso(massaTerrestre);
            System.out.printf("%s: %.2f N (gravidade: %.2f m/s²)\n", 
                            p.name(), peso, p.gravidadeSuperficial());
        }
        
        // Informações detalhadas de um planeta específico
        System.out.println("\nDetalhes da Terra:");
        System.out.println(Planeta.TERRA.getDescricao());
    }
    
    /**
     * Demonstra enum com métodos de formatação (NivelLog)
     */
    private static void exemploNiveisLog() {
        System.out.println("--- NÍVEIS DE LOG ---");
        
        // Demonstra formatação de mensagens
        System.out.println(NivelLog.DEBUG.formatarMensagem("Iniciando aplicação"));
        System.out.println(NivelLog.INFO.formatarMensagem("Usuário autenticado"));
        System.out.println(NivelLog.WARN.formatarMensagem("Memória em 80%"));
        System.out.println(NivelLog.ERROR.formatarMensagem("Falha na conexão"));
        System.out.println(NivelLog.FATAL.formatarMensagem("Sistema crítico falhou"));
        
        // Verifica níveis críticos
        System.out.println("\nNíveis críticos:");
        for (NivelLog nivel : NivelLog.values()) {
            if (nivel.isCritico()) {
                System.out.println("  " + nivel + " é crítico");
            }
        }
        
        // Filtra por nível mínimo
        System.out.println("\nMensagens com nível >= WARN:");
        NivelLog nivelMinimo = NivelLog.WARN;
        for (NivelLog nivel : NivelLog.values()) {
            if (nivel.deveExibir(nivelMinimo)) {
                System.out.println("  " + nivel);
            }
        }
    }
    
    /**
     * Demonstra calculadora usando enum de operações
     */
    private static void exemploCalculadora() {
        System.out.println("--- CALCULADORA COM ENUM ---");
        
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Operações disponíveis:");
        for (Operacao op : Operacao.values()) {
            System.out.println("  " + op.name() + " (" + op.getSimbolo() + ")");
        }
        
        // Simula entrada de usuário
        double num1 = 15.0;
        double num2 = 4.0;
        String operacaoStr = "MULTIPLICACAO";
        
        System.out.println("\nCalculando: " + num1 + " " + operacaoStr + " " + num2);
        
        try {
            Operacao operacao = Operacao.valueOf(operacaoStr);
            double resultado = operacao.calcular(num1, num2);
            System.out.printf("Resultado: %.1f %s %.1f = %.2f\n", 
                            num1, operacao.getSimbolo(), num2, resultado);
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: Operação inválida");
        }
    }
}
