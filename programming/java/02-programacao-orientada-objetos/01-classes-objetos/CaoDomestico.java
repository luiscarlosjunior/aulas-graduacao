/**
 * Classe CaoDomestico - Exemplo de classe representando um cão doméstico
 * 
 * Esta classe demonstra conceitos básicos de POO incluindo
 * atributos, construtores, métodos e comportamentos.
 * 
 * @author Aulas Graduação
 */
public class CaoDomestico {
    
    // ===== ATRIBUTOS =====
    private String nome;
    private String raca;
    private int idade;
    private double peso;
    private String corPelo;
    private boolean vacinado;
    
    // ===== CONSTRUTORES =====
    
    /**
     * Construtor padrão
     */
    public CaoDomestico() {
        this.nome = "Sem nome";
        this.raca = "SRD"; // Sem Raça Definida
        this.idade = 0;
        this.peso = 0.0;
        this.corPelo = "Desconhecida";
        this.vacinado = false;
    }
    
    /**
     * Construtor com nome
     * 
     * @param nome Nome do cão
     */
    public CaoDomestico(String nome) {
        this(); // Chama o construtor padrão
        this.nome = nome;
    }
    
    /**
     * Construtor completo
     * 
     * @param nome Nome do cão
     * @param raca Raça do cão
     * @param idade Idade em anos
     * @param peso Peso em quilos
     * @param corPelo Cor do pelo
     */
    public CaoDomestico(String nome, String raca, int idade, double peso, String corPelo) {
        this.nome = nome;
        this.raca = raca;
        this.idade = idade;
        this.peso = peso;
        this.corPelo = corPelo;
        this.vacinado = false;
    }
    
    // ===== MÉTODOS COMPORTAMENTAIS =====
    
    /**
     * Método para o cão latir
     * O som varia conforme o peso do cão
     */
    public void latir() {
        System.out.print(nome + " está latindo: ");
        
        if (peso > 30) {
            System.out.println("WOOF! WOOF! (latido grave e forte)");
        } else if (peso > 10) {
            System.out.println("Au au! (latido médio)");
        } else {
            System.out.println("Yip yip! (latido agudo)");
        }
    }
    
    /**
     * Método para o cão correr
     */
    public void correr() {
        if (idade > 10) {
            System.out.println(nome + " está correndo devagar (já está idoso).");
        } else if (peso > 40) {
            System.out.println(nome + " está correndo pesadamente.");
        } else {
            System.out.println(nome + " está correndo rapidamente!");
        }
    }
    
    /**
     * Método para o cão comer
     */
    public void comer() {
        System.out.println(nome + " está comendo... *nhac nhac nhac*");
        // Simula ganho de peso
        this.peso += 0.1;
    }
    
    /**
     * Método para o cão dormir
     */
    public void dormir() {
        System.out.println(nome + " está dormindo... ZZZ...");
    }
    
    /**
     * Método para brincar
     */
    public void brincar() {
        System.out.println(nome + " está brincando alegremente!");
        if (peso > 0.1) {
            this.peso -= 0.05; // Perde um pouco de peso brincando
        }
    }
    
    /**
     * Método para tomar banho
     */
    public void tomarBanho() {
        System.out.println(nome + " está tomando banho... *respingo*");
        System.out.println("Agora " + nome + " está limpinho!");
    }
    
    /**
     * Método para vacinar o cão
     */
    public void vacinar() {
        this.vacinado = true;
        System.out.println(nome + " foi vacinado e agora está protegido!");
    }
    
    /**
     * Método para verificar se precisa de exercício
     * 
     * @return true se precisa de exercício
     */
    public boolean precisaExercicio() {
        return peso > (getIdadeEmMeses() * 0.5) && idade < 12;
    }
    
    /**
     * Calcula a idade em meses
     * 
     * @return idade em meses
     */
    public int getIdadeEmMeses() {
        return idade * 12;
    }
    
    /**
     * Calcula a idade equivalente em anos humanos
     * 
     * @return idade em anos humanos
     */
    public int getIdadeHumana() {
        if (idade <= 2) {
            return idade * 10;
        } else {
            return 20 + (idade - 2) * 7;
        }
    }
    
    /**
     * Verifica se o cão está com peso ideal
     * 
     * @return classificação do peso
     */
    public String classificarPeso() {
        // Valores aproximados baseados no porte
        double pesoIdeal;
        
        if (raca.toLowerCase().contains("chihuahua") || peso < 5) {
            pesoIdeal = 3;
        } else if (raca.toLowerCase().contains("labrador") || raca.toLowerCase().contains("golden")) {
            pesoIdeal = 30;
        } else if (raca.toLowerCase().contains("pastor") || peso > 25) {
            pesoIdeal = 35;
        } else {
            pesoIdeal = 15; // Porte médio
        }
        
        double diferenca = Math.abs(peso - pesoIdeal) / pesoIdeal * 100;
        
        if (diferenca <= 10) {
            return "Peso ideal";
        } else if (peso > pesoIdeal) {
            return "Acima do peso";
        } else {
            return "Abaixo do peso";
        }
    }
    
    // ===== GETTERS E SETTERS =====
    
    public String getNome() {
        return nome;
    }
    
    public void setNome(String nome) {
        if (nome != null && !nome.trim().isEmpty()) {
            this.nome = nome;
        }
    }
    
    public String getRaca() {
        return raca;
    }
    
    public void setRaca(String raca) {
        this.raca = raca;
    }
    
    public int getIdade() {
        return idade;
    }
    
    public void setIdade(int idade) {
        if (idade >= 0 && idade <= 25) {
            this.idade = idade;
        }
    }
    
    public double getPeso() {
        return peso;
    }
    
    public void setPeso(double peso) {
        if (peso > 0 && peso <= 100) {
            this.peso = peso;
        }
    }
    
    public String getCorPelo() {
        return corPelo;
    }
    
    public void setCorPelo(String corPelo) {
        this.corPelo = corPelo;
    }
    
    public boolean isVacinado() {
        return vacinado;
    }
    
    public void setVacinado(boolean vacinado) {
        this.vacinado = vacinado;
    }
    
    // ===== MÉTODO TOSTRING =====
    
    @Override
    public String toString() {
        return "CaoDomestico{" +
                "nome='" + nome + '\'' +
                ", raca='" + raca + '\'' +
                ", idade=" + idade + " anos" +
                ", peso=" + String.format("%.1f", peso) + "kg" +
                ", corPelo='" + corPelo + '\'' +
                ", vacinado=" + (vacinado ? "Sim" : "Não") +
                ", classificacaoPeso='" + classificarPeso() + '\'' +
                '}';
    }
}