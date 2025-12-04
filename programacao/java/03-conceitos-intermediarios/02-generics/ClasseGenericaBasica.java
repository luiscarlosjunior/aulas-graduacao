/**
 * ClasseGenericaBasica.java
 * 
 * Demonstra os conceitos fundamentais de classes genéricas em Java.
 * 
 * Uma classe genérica é definida com um ou mais type parameters (parâmetros de tipo)
 * que são especificados entre < >. Estes parâmetros atuam como placeholders que serão
 * substituídos por tipos concretos quando a classe for instanciada.
 * 
 * Benefícios das Classes Genéricas:
 * - Type safety em tempo de compilação
 * - Eliminação de casting manual
 * - Código mais reutilizável
 * - Detecção precoce de erros
 * 
 * @author Aulas de Graduação
 */

/**
 * Caixa genérica que pode armazenar qualquer tipo de objeto.
 * 
 * O type parameter T representa o tipo de objeto que será armazenado.
 * T é uma convenção comum para "Type" (tipo genérico geral).
 * 
 * @param <T> o tipo de objeto armazenado na caixa
 */
class Caixa<T> {
    private T conteudo;
    
    /**
     * Construtor padrão que cria uma caixa vazia.
     */
    public Caixa() {
        this.conteudo = null;
    }
    
    /**
     * Construtor que cria uma caixa com conteúdo inicial.
     * 
     * @param conteudo o objeto a ser armazenado
     */
    public Caixa(T conteudo) {
        this.conteudo = conteudo;
    }
    
    /**
     * Guarda um item na caixa.
     * Note que o parâmetro é do tipo T - type safe!
     * 
     * @param item o item a ser guardado
     */
    public void guardar(T item) {
        this.conteudo = item;
    }
    
    /**
     * Recupera o item da caixa.
     * Note que o retorno é do tipo T - sem necessidade de casting!
     * 
     * @return o item armazenado
     */
    public T recuperar() {
        return this.conteudo;
    }
    
    /**
     * Verifica se a caixa está vazia.
     * 
     * @return true se vazia, false caso contrário
     */
    public boolean estaVazia() {
        return this.conteudo == null;
    }
    
    /**
     * Limpa o conteúdo da caixa.
     */
    public void limpar() {
        this.conteudo = null;
    }
    
    /**
     * Retorna uma representação em string da caixa.
     * 
     * @return descrição da caixa e seu conteúdo
     */
    @Override
    public String toString() {
        if (estaVazia()) {
            return "Caixa vazia";
        }
        return "Caixa contendo: " + conteudo.toString();
    }
}

/**
 * Par genérico que armazena dois valores de tipos potencialmente diferentes.
 * 
 * Esta classe demonstra o uso de múltiplos type parameters.
 * K representa "Key" (chave) e V representa "Value" (valor).
 * 
 * @param <K> tipo da chave (primeiro elemento)
 * @param <V> tipo do valor (segundo elemento)
 */
class Par<K, V> {
    private K chave;
    private V valor;
    
    /**
     * Construtor que cria um par com chave e valor.
     * 
     * @param chave o primeiro elemento do par
     * @param valor o segundo elemento do par
     */
    public Par(K chave, V valor) {
        this.chave = chave;
        this.valor = valor;
    }
    
    public K getChave() {
        return chave;
    }
    
    public void setChave(K chave) {
        this.chave = chave;
    }
    
    public V getValor() {
        return valor;
    }
    
    public void setValor(V valor) {
        this.valor = valor;
    }
    
    @Override
    public String toString() {
        return "Par[" + chave + " = " + valor + "]";
    }
}

/**
 * Classe principal demonstrando o uso de classes genéricas.
 */
public class ClasseGenericaBasica {
    
    public static void main(String[] args) {
        System.out.println("=== DEMONSTRAÇÃO DE CLASSES GENÉRICAS ===\n");
        
        // ============================================================
        // EXEMPLO 1: Caixa de String
        // ============================================================
        System.out.println("--- Exemplo 1: Caixa de String ---");
        
        Caixa<String> caixaTexto = new Caixa<>();
        caixaTexto.guardar("Olá, Generics!");
        
        // Note: sem necessidade de casting!
        String mensagem = caixaTexto.recuperar();
        System.out.println("Mensagem recuperada: " + mensagem);
        System.out.println("Caixa vazia? " + caixaTexto.estaVazia());
        System.out.println(caixaTexto);
        System.out.println();
        
        // ============================================================
        // EXEMPLO 2: Caixa de Integer
        // ============================================================
        System.out.println("--- Exemplo 2: Caixa de Integer ---");
        
        Caixa<Integer> caixaNumero = new Caixa<>(42);
        
        // Type safe: apenas Integer pode ser armazenado
        // caixaNumero.guardar("texto"); // ❌ Erro de compilação!
        caixaNumero.guardar(100);
        
        Integer numero = caixaNumero.recuperar();
        System.out.println("Número recuperado: " + numero);
        System.out.println(caixaNumero);
        System.out.println();
        
        // ============================================================
        // EXEMPLO 3: Caixa de Double
        // ============================================================
        System.out.println("--- Exemplo 3: Caixa de Double ---");
        
        Caixa<Double> caixaDecimal = new Caixa<>();
        caixaDecimal.guardar(3.14159);
        
        Double pi = caixaDecimal.recuperar();
        System.out.println("Valor de PI: " + pi);
        System.out.println();
        
        // ============================================================
        // EXEMPLO 4: Caixa de Objetos Customizados
        // ============================================================
        System.out.println("--- Exemplo 4: Caixa de Objetos Customizados ---");
        
        class Produto {
            String nome;
            double preco;
            
            Produto(String nome, double preco) {
                this.nome = nome;
                this.preco = preco;
            }
            
            @Override
            public String toString() {
                return nome + " (R$ " + String.format("%.2f", preco) + ")";
            }
        }
        
        Caixa<Produto> caixaProduto = new Caixa<>();
        Produto notebook = new Produto("Notebook Dell", 2999.90);
        caixaProduto.guardar(notebook);
        
        Produto produtoRecuperado = caixaProduto.recuperar();
        System.out.println("Produto: " + produtoRecuperado);
        System.out.println();
        
        // ============================================================
        // EXEMPLO 5: Caixas Aninhadas (Generics dentro de Generics)
        // ============================================================
        System.out.println("--- Exemplo 5: Caixas Aninhadas ---");
        
        Caixa<Caixa<String>> caixaDupla = new Caixa<>();
        Caixa<String> caixaInterna = new Caixa<>("Conteúdo secreto");
        caixaDupla.guardar(caixaInterna);
        
        String segredo = caixaDupla.recuperar().recuperar();
        System.out.println("Segredo revelado: " + segredo);
        System.out.println();
        
        // ============================================================
        // EXEMPLO 6: Par Genérico (Múltiplos Type Parameters)
        // ============================================================
        System.out.println("--- Exemplo 6: Par Genérico ---");
        
        // Par de String e Integer
        Par<String, Integer> parIdade = new Par<>("João", 25);
        System.out.println(parIdade);
        System.out.println("Nome: " + parIdade.getChave());
        System.out.println("Idade: " + parIdade.getValor());
        System.out.println();
        
        // Par de Integer e Double
        Par<Integer, Double> parNota = new Par<>(1, 9.5);
        System.out.println(parNota);
        System.out.println();
        
        // Par de String e String
        Par<String, String> parCapitalPais = new Par<>("Brasil", "Brasília");
        System.out.println(parCapitalPais);
        System.out.println();
        
        // ============================================================
        // EXEMPLO 7: Demonstração de Type Safety
        // ============================================================
        System.out.println("--- Exemplo 7: Type Safety em Ação ---");
        
        Caixa<String> caixaSoTexto = new Caixa<>();
        caixaSoTexto.guardar("Java");
        
        // ✅ Funciona: tipo correto
        String linguagem = caixaSoTexto.recuperar();
        System.out.println("Linguagem: " + linguagem);
        
        // ❌ Não compila: tentativa de guardar tipo incorreto
        // caixaSoTexto.guardar(123);
        // caixaSoTexto.guardar(3.14);
        
        System.out.println("✅ Type safety garantida em compile time!");
        System.out.println();
        
        // ============================================================
        // EXEMPLO 8: Comparação com Código Pré-Generics (Ilustração)
        // ============================================================
        System.out.println("--- Exemplo 8: Antes vs Depois dos Generics ---");
        
        // ANTES (Java 1.4 e anterior) - usando Object
        class CaixaVelha {
            private Object conteudo;
            
            public void guardar(Object obj) {
                this.conteudo = obj;
            }
            
            public Object recuperar() {
                return this.conteudo;
            }
        }
        
        CaixaVelha velha = new CaixaVelha();
        velha.guardar("Texto");
        velha.guardar(123); // Aceita qualquer coisa! Perigoso!
        
        // Necessita casting manual - pode gerar ClassCastException em runtime!
        try {
            String textoVelho = (String) velha.recuperar();
            System.out.println("❌ PERIGO: Tentando recuperar String mas é Integer!");
            System.out.println(textoVelho); // Nunca chega aqui
        } catch (ClassCastException e) {
            System.out.println("💥 ClassCastException: " + e.getMessage());
        }
        
        // DEPOIS (Java 5+) - usando Generics
        Caixa<String> nova = new Caixa<>();
        nova.guardar("Texto");
        // nova.guardar(123); // ✅ Erro de compilação - type safety!
        
        String textoNovo = nova.recuperar(); // ✅ Sem casting, type safe!
        System.out.println("✅ Com Generics: seguro e sem casting!");
        System.out.println();
        
        // ============================================================
        // RESUMO
        // ============================================================
        System.out.println("=== RESUMO ===");
        System.out.println("✅ Classes genéricas proporcionam type safety");
        System.out.println("✅ Eliminam necessidade de casting");
        System.out.println("✅ Erros detectados em compile time, não runtime");
        System.out.println("✅ Código mais limpo e reutilizável");
        System.out.println("✅ Podem ter múltiplos type parameters");
    }
}
