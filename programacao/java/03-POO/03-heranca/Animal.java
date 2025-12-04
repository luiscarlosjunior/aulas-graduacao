/**
 * Exemplo prático de Herança em Java - Classe Pai
 * 
 * Esta classe representa a superclasse (classe pai) Animal que contém
 * características e comportamentos comuns a todos os animais.
 * Demonstra conceitos fundamentais:
 * - Atributos protegidos (protected)
 * - Métodos que podem ser herdados
 * - Métodos que podem ser sobrescritos
 * - Construtor da classe pai
 * 
 * @author Curso POO Java
 */
public class Animal {
    
    // ===== ATRIBUTOS PROTEGIDOS =====
    // Usamos 'protected' para que as classes filhas possam acessar diretamente
    // mas ainda mantemos proteção contra acesso externo descontrolado
    
    protected String nome;              // Nome do animal
    protected int idade;                // Idade em anos
    protected double peso;              // Peso em quilos
    protected String especie;           // Espécie do animal
    protected boolean dormindo;         // Estado atual: dormindo ou acordado
    
    // ===== CONSTRUTOR DA CLASSE PAI =====
    
    /**
     * Construtor padrão
     * Cria um animal sem características específicas
     */
    public Animal() {
        this.dormindo = false;  // Animal começa acordado
        System.out.println("🐾 Animal criado (construtor padrão)");
    }
    
    /**
     * Construtor com parâmetros básicos
     * @param nome Nome do animal
     * @param especie Espécie do animal
     */
    public Animal(String nome, String especie) {
        this.nome = nome;
        this.especie = especie;
        this.dormindo = false;
        System.out.println("🐾 Animal " + nome + " (" + especie + ") criado");
    }
    
    /**
     * Construtor completo
     * @param nome Nome do animal
     * @param especie Espécie do animal  
     * @param idade Idade do animal
     * @param peso Peso do animal
     */
    public Animal(String nome, String especie, int idade, double peso) {
        this.nome = nome;
        this.especie = especie;
        this.idade = idade;
        this.peso = peso;
        this.dormindo = false;
        System.out.printf("🐾 Animal %s (%s, %d anos, %.1fkg) criado%n", 
                         nome, especie, idade, peso);
    }
    
    // ===== MÉTODOS GETTERS E SETTERS =====
    // Métodos para acessar e modificar os atributos de forma controlada
    
    public String getNome() {
        return nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public int getIdade() {
        return idade;
    }
    
    public void setIdade(int idade) {
        if (idade >= 0) {
            this.idade = idade;
        }
    }
    
    public double getPeso() {
        return peso;
    }
    
    public void setPeso(double peso) {
        if (peso > 0) {
            this.peso = peso;
        }
    }
    
    public String getEspecie() {
        return especie;
    }
    
    public boolean isDormindo() {
        return dormindo;
    }
    
    // ===== MÉTODOS COMUNS A TODOS OS ANIMAIS =====
    // Estes métodos serão herdados por todas as classes filhas
    
    /**
     * Método para o animal dormir
     * Este método pode ser usado por qualquer animal
     */
    public void dormir() {
        if (!dormindo) {
            dormindo = true;
            System.out.println("😴 " + nome + " está dormindo... Zzz...");
        } else {
            System.out.println("💤 " + nome + " já está dormindo!");
        }
    }
    
    /**
     * Método para o animal acordar
     * Este método pode ser usado por qualquer animal
     */
    public void acordar() {
        if (dormindo) {
            dormindo = false;
            System.out.println("😊 " + nome + " acordou!");
        } else {
            System.out.println("👁️ " + nome + " já está acordado!");
        }
    }
    
    /**
     * Método para o animal comer
     * Este método pode ser sobrescrito pelas classes filhas para comportamento específico
     */
    public void comer() {
        if (!dormindo) {
            System.out.println("🍽️ " + nome + " está comendo sua refeição.");
        } else {
            System.out.println("😴 " + nome + " está dormindo e não pode comer agora.");
        }
    }
    
    /**
     * Método para o animal se mover
     * Este método será sobrescrito pelas classes filhas com comportamentos específicos
     */
    public void mover() {
        if (!dormindo) {
            System.out.println("🚶 " + nome + " está se movendo.");
        } else {
            System.out.println("😴 " + nome + " está dormindo e não pode se mover.");
        }
    }
    
    /**
     * Método para o animal fazer som
     * Este método DEVE ser sobrescrito pelas classes filhas
     * pois cada animal tem seu som característico
     */
    public void emitirSom() {
        if (!dormindo) {
            System.out.println("🔊 " + nome + " está fazendo algum som...");
        } else {
            System.out.println("😴 " + nome + " está dormindo silenciosamente.");
        }
    }
    
    /**
     * Método para brincar
     * Comportamento comum mas que pode ser especializado pelas classes filhas
     */
    public void brincar() {
        if (!dormindo) {
            System.out.println("🎾 " + nome + " está brincando!");
        } else {
            acordar();  // Acorda para brincar
            System.out.println("🎾 " + nome + " acordou para brincar!");
        }
    }
    
    /**
     * Método para envelhecer o animal
     * Incrementa a idade em 1 ano
     */
    public void envelhecer() {
        idade++;
        System.out.println("🎂 " + nome + " fez aniversário! Agora tem " + idade + " anos.");
    }
    
    /**
     * Método para exibir informações do animal
     * Este método pode ser sobrescrito para adicionar informações específicas
     */
    public void exibirInformacoes() {
        System.out.println("\n=== Informações do Animal ===");
        System.out.println("Nome: " + (nome != null ? nome : "Não definido"));
        System.out.println("Espécie: " + (especie != null ? especie : "Não definida"));
        System.out.println("Idade: " + idade + " anos");
        System.out.println("Peso: " + peso + " kg");
        System.out.println("Estado: " + (dormindo ? "Dormindo" : "Acordado"));
        System.out.println("============================\n");
    }
    
    /**
     * Override do método toString para representação textual
     * @return String com informações básicas do animal
     */
    @Override
    public String toString() {
        return String.format("Animal{nome='%s', especie='%s', idade=%d, peso=%.1f, dormindo=%s}", 
                           nome, especie, idade, peso, dormindo);
    }
    
    /**
     * Método para verificar se dois animais são da mesma espécie
     * @param outroAnimal Animal para comparar
     * @return true se forem da mesma espécie
     */
    public boolean mesmaEspecie(Animal outroAnimal) {
        if (outroAnimal == null || this.especie == null) {
            return false;
        }
        return this.especie.equals(outroAnimal.getEspecie());
    }
}