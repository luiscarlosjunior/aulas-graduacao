/**
 * Demonstração completa de Annotations Predefinidas (Built-in) do Java
 * 
 * Este exemplo ilustra o uso das principais annotations fornecidas pela
 * plataforma Java: @Override, @Deprecated, @SuppressWarnings, 
 * @FunctionalInterface e @SafeVarargs.
 * 
 * @author Aulas Graduação
 */
public class ExemploAnnotationsBuiltIn {
    
    public static void main(String[] args) {
        System.out.println("=== ANNOTATIONS BUILT-IN DO JAVA ===\n");
        
        demonstrarOverride();
        System.out.println();
        
        demonstrarDeprecated();
        System.out.println();
        
        demonstrarSuppressWarnings();
        System.out.println();
        
        demonstrarFunctionalInterface();
        System.out.println();
        
        demonstrarSafeVarargs();
    }
    
    /**
     * Demonstra o uso de @Override
     */
    private static void demonstrarOverride() {
        System.out.println("--- @Override ---");
        System.out.println("Garante que método está sobrescrevendo método da superclasse");
        
        Animal animal = new Cachorro();
        animal.emitirSom();
        
        Forma forma = new Circulo(5.0);
        System.out.println("Área do círculo: " + forma.calcularArea());
    }
    
    /**
     * Demonstra o uso de @Deprecated
     */
    private static void demonstrarDeprecated() {
        System.out.println("--- @Deprecated ---");
        System.out.println("Marca elementos obsoletos com aviso ao compilador");
        
        CalculadoraLegada calc = new CalculadoraLegada();
        
        // Método novo (recomendado)
        System.out.println("Método novo: " + calc.somarNovo(5, 3));
        
        // Método deprecated (gera warning na compilação)
        System.out.println("Método antigo (deprecated): " + calc.somarAntigo(5, 3));
        
        // Classe deprecated
        ClasseObsoleta obsoleta = new ClasseObsoleta();
        obsoleta.executar();
    }
    
    /**
     * Demonstra o uso de @SuppressWarnings
     */
    private static void demonstrarSuppressWarnings() {
        System.out.println("--- @SuppressWarnings ---");
        System.out.println("Suprime warnings específicos do compilador");
        
        ExemploSuppressWarnings exemplo = new ExemploSuppressWarnings();
        exemplo.exemploUnchecked();
        exemplo.exemploDeprecation();
        exemplo.exemploRawTypes();
    }
    
    /**
     * Demonstra o uso de @FunctionalInterface
     */
    private static void demonstrarFunctionalInterface() {
        System.out.println("--- @FunctionalInterface ---");
        System.out.println("Marca interfaces funcionais (um método abstrato)");
        
        // Implementação com lambda
        Calculavel soma = (a, b) -> a + b;
        System.out.println("Soma com lambda: " + soma.calcular(10, 5));
        
        Calculavel multiplicacao = (a, b) -> a * b;
        System.out.println("Multiplicação com lambda: " + multiplicacao.calcular(10, 5));
        
        // Implementação com method reference
        Processavel<String> print = System.out::println;
        print.processar("Texto processado via method reference");
        
        // Usando interface com métodos default
        Transformavel<String> upper = String::toUpperCase;
        String resultado = upper.transformar("hello world");
        System.out.println("Transformado: " + resultado);
        System.out.println("Com prefixo: " + upper.transformarComPrefixo("hello", ">>> "));
    }
    
    /**
     * Demonstra o uso de @SafeVarargs
     */
    private static void demonstrarSafeVarargs() {
        System.out.println("--- @SafeVarargs ---");
        System.out.println("Suprime warnings de varargs com generics");
        
        UtilVarargs util = new UtilVarargs();
        
        util.imprimirTodos("A", "B", "C", "D");
        util.imprimirTodos(1, 2, 3, 4, 5);
        
        java.util.List<String> lista = util.criarLista("X", "Y", "Z");
        System.out.println("Lista criada: " + lista);
    }
}

// ===== EXEMPLOS @Override =====

/**
 * Classe base para demonstrar @Override
 */
class Animal {
    public void emitirSom() {
        System.out.println("Som genérico de animal");
    }
    
    public void dormir() {
        System.out.println("Animal dormindo");
    }
}

/**
 * Subclasse que sobrescreve métodos
 */
class Cachorro extends Animal {
    
    /**
     * @Override garante que estamos sobrescrevendo método correto
     * Se houver typo no nome ou assinatura diferente, compilador gera erro
     */
    @Override
    public void emitirSom() {
        System.out.println("Au au! (Cachorro latindo)");
    }
    
    // Se descomentarmos abaixo, haverá ERRO pois método não existe na superclasse
    // @Override
    // public void emitirSon() {  // Typo no nome
    //     System.out.println("Erro!");
    // }
}

/**
 * Interface para demonstrar @Override com interfaces
 */
interface Forma {
    double calcularArea();
    double calcularPerimetro();
}

/**
 * Implementação de interface usando @Override
 */
class Circulo implements Forma {
    private final double raio;
    
    public Circulo(double raio) {
        this.raio = raio;
    }
    
    @Override
    public double calcularArea() {
        return Math.PI * raio * raio;
    }
    
    @Override
    public double calcularPerimetro() {
        return 2 * Math.PI * raio;
    }
}

// ===== EXEMPLOS @Deprecated =====

/**
 * Classe com métodos deprecated demonstrando evolução da API
 */
class CalculadoraLegada {
    
    /**
     * Método antigo - será removido
     * @deprecated Use {@link #somarNovo(int, int)} em vez disso.
     * Este método será removido na versão 3.0.
     */
    @Deprecated(since = "2.0", forRemoval = true)
    public int somarAntigo(int a, int b) {
        return a + b;
    }
    
    /**
     * Método novo e recomendado
     */
    public int somarNovo(int a, int b) {
        return Math.addExact(a, b); // Usa método que verifica overflow
    }
    
    /**
     * @deprecated Substituído por solução mais eficiente
     */
    @Deprecated(since = "1.5")
    public void metodoLento() {
        // Implementação antiga e lenta
    }
}

/**
 * Classe completamente obsoleta
 * @deprecated Esta classe será removida. Use {@link NovaClasse} em vez disso.
 */
@Deprecated(since = "2.0", forRemoval = true)
class ClasseObsoleta {
    public void executar() {
        System.out.println("Executando classe obsoleta (deprecated)");
    }
}

/**
 * Classe substituta
 */
class NovaClasse {
    public void executar() {
        System.out.println("Executando nova implementação");
    }
}

// ===== EXEMPLOS @SuppressWarnings =====

class ExemploSuppressWarnings {
    
    /**
     * Suprime warning de unchecked operations
     */
    @SuppressWarnings("unchecked")
    public void exemploUnchecked() {
        // Raw type sem generics
        java.util.List lista = new java.util.ArrayList();
        lista.add("String");
        lista.add(123);
        
        // Cast unchecked
        java.util.List<String> listaString = lista;
        System.out.println("Lista com tipos misturados (unchecked): " + listaString.size() + " elementos");
    }
    
    /**
     * Suprime warning de uso de elementos deprecated
     */
    @SuppressWarnings("deprecation")
    public void exemploDeprecation() {
        ClasseObsoleta obsoleta = new ClasseObsoleta();
        obsoleta.executar();
        System.out.println("Usando classe deprecated sem warnings");
    }
    
    /**
     * Suprime múltiplos tipos de warnings
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    public void exemploRawTypes() {
        java.util.List lista = new java.util.ArrayList(); // raw type
        lista.add("item");
        System.out.println("Usando raw types sem warnings");
    }
    
    /**
     * Suprime warning de variável não utilizada
     */
    @SuppressWarnings("unused")
    private void metodoComVariavelNaoUsada() {
        int variavelNaoUsada = 42; // Normalmente geraria warning
        System.out.println("Método com variável não usada");
    }
}

// ===== EXEMPLOS @FunctionalInterface =====

/**
 * Interface funcional para cálculos
 * Pode ser implementada com lambda expressions
 */
@FunctionalInterface
interface Calculavel {
    /**
     * Único método abstrato (SAM - Single Abstract Method)
     */
    int calcular(int a, int b);
    
    /**
     * Métodos default são permitidos
     */
    default int calcularTriplo(int a, int b) {
        return calcular(a, b) * 3;
    }
    
    /**
     * Métodos static também são permitidos
     */
    static Calculavel criarSomador() {
        return (a, b) -> a + b;
    }
}

/**
 * Interface funcional para processamento genérico
 */
@FunctionalInterface
interface Processavel<T> {
    void processar(T item);
    
    default void processarComLog(T item) {
        System.out.println("Processando: " + item);
        processar(item);
    }
}

/**
 * Interface funcional com método que retorna valor
 */
@FunctionalInterface
interface Transformavel<T> {
    T transformar(T input);
    
    default T transformarComPrefixo(T input, String prefixo) {
        return transformar((T)(prefixo + input));
    }
}

// Se descomentarmos abaixo, haverá ERRO pois há dois métodos abstratos
// @FunctionalInterface
// interface NaoFuncional {
//     void metodo1();
//     void metodo2(); // ERRO: segundo método abstrato
// }

// ===== EXEMPLOS @SafeVarargs =====

class UtilVarargs {
    
    /**
     * Método genérico com varargs
     * @SafeVarargs suprime warning sobre heap pollution
     */
    @SafeVarargs
    public final <T> void imprimirTodos(T... elementos) {
        System.out.print("Elementos: ");
        for (T elemento : elementos) {
            System.out.print(elemento + " ");
        }
        System.out.println();
    }
    
    /**
     * Método static também pode usar @SafeVarargs
     */
    @SafeVarargs
    public static <T> java.util.List<T> criarLista(T... elementos) {
        java.util.List<T> lista = new java.util.ArrayList<>();
        for (T elemento : elementos) {
            lista.add(elemento);
        }
        return lista;
    }
    
    /**
     * Construtor também pode usar @SafeVarargs
     */
    @SafeVarargs
    public UtilVarargs(String... args) {
        System.out.println("Construtor com " + args.length + " argumentos");
    }
    
    // IMPORTANTE: @SafeVarargs só pode ser usado em:
    // - Métodos final
    // - Métodos static
    // - Métodos private (Java 9+)
    // - Construtores
    
    // Exemplo UNSAFE (não faça isso!):
    @SuppressWarnings("unchecked")
    public static <T> T[] unsafeMethod(T... elements) {
        // Isto pode causar heap pollution e ClassCastException
        Object[] array = elements;
        array[0] = "String"; // Pode quebrar type safety!
        return elements;
    }
}
