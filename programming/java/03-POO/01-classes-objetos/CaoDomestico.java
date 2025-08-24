
/**
 * Exemplo prático de Classes e Objetos em Java
 * 
 * Esta classe representa um cão doméstico e demonstra os conceitos fundamentais:
 * - Atributos (características do cão)
 * - Construtores (como criar um cão)
 * - Métodos (comportamentos do cão)
 * 
 * @author luiscaparroz
 */
public class CaoDomestico {
    
    // ===== ATRIBUTOS (CARACTERÍSTICAS) =====
    // Atributos são as características que todo cão doméstico possui
    
    public String nome;        // Nome do cão (ex: "Rex", "Bella")
    public int peso;           // Peso em quilos (ex: 25)
    public String corOlhos;    // Cor dos olhos (ex: "Marrom", "Azul")
    public int quantPatas;     // Quantidade de patas (normalmente 4)
    
    // ===== CONSTRUTORES =====
    // Construtores são métodos especiais que criam objetos da classe
    
    /**
     * Construtor padrão - cria um cão sem definir características iniciais
     * Permite criar: new CaoDomestico()
     */
    public CaoDomestico() {
        // Este construtor está vazio intencionalmente
        // Os atributos receberão valores padrão (null para String, 0 para int)
    }
    
    /**
     * Construtor com nome - cria um cão já com nome definido
     * Permite criar: new CaoDomestico("Rex")
     * 
     * @param nome O nome que será dado ao cão
     */
    public CaoDomestico(String nome) {
        this.nome = nome;  // 'this' se refere ao objeto atual
    }
    
    // ===== MÉTODOS (COMPORTAMENTOS) =====
    // Métodos são as ações que um cão pode realizar
    
    /**
     * Método getter personalizado para o nome
     * Retorna uma mensagem formatada com o nome do cão
     * 
     * @return String formatada com o nome
     */
    public String getNome() {
        return "Nome do cão: " + nome;
    }
    
    /**
     * Método que simula o cão falando
     * TODO: Implementar comportamento específico
     */
    public void falar() {
        // Exercício: implemente este método
        // Sugestão: System.out.println(nome + " está fazendo sons de cão!");
        System.out.println(nome + " está fazendo sons de cão!");
    }
    
    /**
     * Método que simula o cão andando
     * TODO: Implementar comportamento específico
     */
    public void andar() {
        // Exercício: implemente este método
        // Sugestão: usar quantPatas na mensagem
        System.out.println(nome + " está caminhando com " + quantPatas + " patas.");
    }
    
    /**
     * Método que simula o cão comendo
     * TODO: Implementar comportamento específico
     */
    public void comer() {
        // Exercício: implemente este método
        System.out.println(nome + " está comendo sua ração favorita!");
    }
    
    /**
     * Método que simula o cão dormindo
     * TODO: Implementar comportamento específico
     */
    public void dormir() {
        // Exercício: implemente este método
        System.out.println(nome + " está dormindo... Zzz...");
    }
    
    /**
     * Método completo que demonstra lógica condicional
     * O tipo de latido varia conforme o peso do cão:
     * - Cães grandes (> 60kg): latido grave
     * - Cães médios (14-60kg): latido normal  
     * - Cães pequenos (< 14kg): latido agudo
     */
    void latir() {
        if (peso > 60) {
            // Cães grandes fazem latidos graves
            System.out.println(nome + ": Wooof, Wooof!");
        } else if (peso > 14) {
            // Cães médios fazem latidos normais
            System.out.println(nome + ": Ruff!, Ruff!");
        } else {
            // Cães pequenos fazem latidos agudos
            System.out.println(nome + ": Yip!, Yip!");
        }
    }
}
