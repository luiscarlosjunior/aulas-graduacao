/**
 * Exemplo prático de Herança em Java - Classe Filha
 * 
 * Esta classe herda de Animal e demonstra:
 * - Uso da palavra-chave 'extends'
 * - Chamada de construtores da classe pai com 'super()'
 * - Sobrescrita de métodos com '@Override'
 * - Adição de novos métodos específicos
 * - Acesso a atributos protegidos da classe pai
 * 
 * @author Curso POO Java
 */
public class Cachorro extends Animal {  // 'extends' estabelece a herança
    
    // ===== ATRIBUTOS ESPECÍFICOS DO CACHORRO =====
    // Além dos atributos herdados, podemos adicionar novos
    
    private String raca;                    // Raça do cachorro
    private boolean adestrado;              // Se o cachorro é adestrado
    private String brinquedoFavorito;       // Brinquedo preferido
    private int nivelEnergia;               // Nível de energia (1-10)
    
    // ===== CONSTRUTORES =====
    
    /**
     * Construtor padrão
     * Chama o construtor da classe pai usando super()
     */
    public Cachorro() {
        super();  // Chama construtor padrão da classe Animal
        this.especie = "Cachorro";  // Define espécie específica
        this.adestrado = false;
        this.nivelEnergia = 7;      // Cachorros geralmente têm energia alta
        System.out.println("🐕 Cachorro criado com construtor padrão");
    }
    
    /**
     * Construtor com nome
     * @param nome Nome do cachorro
     */
    public Cachorro(String nome) {
        super(nome, "Cachorro");  // Chama construtor da classe pai com parâmetros
        this.adestrado = false;
        this.nivelEnergia = 7;
        System.out.println("🐕 Cachorro " + nome + " criado");
    }
    
    /**
     * Construtor completo
     * @param nome Nome do cachorro
     * @param idade Idade do cachorro
     * @param peso Peso do cachorro
     * @param raca Raça do cachorro
     */
    public Cachorro(String nome, int idade, double peso, String raca) {
        super(nome, "Cachorro", idade, peso);  // Chama construtor completo da classe pai
        this.raca = raca;
        this.adestrado = false;
        this.nivelEnergia = 7;
        System.out.println("🐕 Cachorro " + nome + " da raça " + raca + " criado");
    }
    
    // ===== MÉTODOS GETTERS E SETTERS ESPECÍFICOS =====
    
    public String getRaca() {
        return raca;
    }
    
    public void setRaca(String raca) {
        this.raca = raca;
    }
    
    public boolean isAdestrado() {
        return adestrado;
    }
    
    public void setAdestrado(boolean adestrado) {
        this.adestrado = adestrado;
        System.out.println("🎓 " + nome + " agora está " + 
                          (adestrado ? "adestrado" : "não adestrado"));
    }
    
    public String getBrinquedoFavorito() {
        return brinquedoFavorito;
    }
    
    public void setBrinquedoFavorito(String brinquedoFavorito) {
        this.brinquedoFavorito = brinquedoFavorito;
        System.out.println("🎾 Brinquedo favorito de " + nome + " agora é: " + brinquedoFavorito);
    }
    
    public int getNivelEnergia() {
        return nivelEnergia;
    }
    
    public void setNivelEnergia(int nivelEnergia) {
        if (nivelEnergia >= 1 && nivelEnergia <= 10) {
            this.nivelEnergia = nivelEnergia;
        }
    }
    
    // ===== MÉTODOS SOBRESCRITOS (OVERRIDE) =====
    // Estes métodos substituem a implementação da classe pai
    
    /**
     * Sobrescrita do método emitirSom da classe Animal
     * Cachorro tem um som específico: latir
     */
    @Override
    public void emitirSom() {
        if (!dormindo) {  // Acessa atributo protegido da classe pai
            // Som varia conforme o peso (herdado de Animal)
            if (peso > 30) {
                System.out.println("🐕 " + nome + ": WOOF! WOOF! (latido grave)");
            } else if (peso > 15) {
                System.out.println("🐕 " + nome + ": Ruff! Ruff! (latido médio)");
            } else {
                System.out.println("🐕 " + nome + ": Yip! Yip! (latido agudo)");
            }
        } else {
            System.out.println("😴 " + nome + " está dormindo e não pode latir.");
        }
    }
    
    /**
     * Sobrescrita do método mover da classe Animal
     * Cachorro tem forma específica de se mover
     */
    @Override
    public void mover() {
        if (!dormindo) {
            if (nivelEnergia > 6) {
                System.out.println("🏃 " + nome + " está correndo animadamente!");
            } else if (nivelEnergia > 3) {
                System.out.println("🚶 " + nome + " está caminhando calmamente.");
            } else {
                System.out.println("🐌 " + nome + " está se movendo devagar, parece cansado.");
            }
            diminuirEnergia(1);  // Mover gasta energia
        } else {
            super.mover();  // Chama implementação da classe pai
        }
    }
    
    /**
     * Sobrescrita do método comer da classe Animal
     * Adiciona comportamento específico de cachorro
     */
    @Override
    public void comer() {
        if (!dormindo) {
            System.out.println("🍖 " + nome + " está comendo ração com muito apetite!");
            aumentarEnergia(2);  // Comer aumenta energia
        } else {
            super.comer();  // Chama implementação da classe pai
        }
    }
    
    /**
     * Sobrescrita do método brincar da classe Animal
     * Cachorro tem forma específica de brincar
     */
    @Override
    public void brincar() {
        if (!dormindo) {
            if (brinquedoFavorito != null) {
                System.out.println("🎾 " + nome + " está brincando com " + brinquedoFavorito + "!");
            } else {
                System.out.println("🎾 " + nome + " está brincando alegremente!");
            }
            diminuirEnergia(2);  // Brincar gasta mais energia
        } else {
            super.brincar();  // Chama implementação da classe pai (acorda primeiro)
        }
    }
    
    /**
     * Sobrescrita do método exibirInformacoes
     * Adiciona informações específicas de cachorro
     */
    @Override
    public void exibirInformacoes() {
        super.exibirInformacoes();  // Chama método da classe pai primeiro
        
        // Adiciona informações específicas de cachorro
        System.out.println("=== Informações Específicas ===");
        System.out.println("Raça: " + (raca != null ? raca : "Não definida"));
        System.out.println("Adestrado: " + (adestrado ? "Sim" : "Não"));
        System.out.println("Brinquedo favorito: " + (brinquedoFavorito != null ? brinquedoFavorito : "Nenhum"));
        System.out.println("Nível de energia: " + nivelEnergia + "/10");
        System.out.println("===============================\n");
    }
    
    // ===== MÉTODOS ESPECÍFICOS DO CACHORRO =====
    // Novos comportamentos que só cachorro possui
    
    /**
     * Método específico: latir (similar a emitirSom, mas mais específico)
     */
    public void latir() {
        emitirSom();  // Reutiliza a lógica do emitirSom sobrescrito
    }
    
    /**
     * Método específico: buscar objetos
     * @param objeto Nome do objeto a ser buscado
     */
    public void buscar(String objeto) {
        if (!dormindo) {
            if (adestrado) {
                System.out.println("🎯 " + nome + " correu e trouxe o " + objeto + " de volta!");
                diminuirEnergia(3);
            } else {
                System.out.println("🤔 " + nome + " olhou para o " + objeto + " mas não sabe o que fazer.");
            }
        } else {
            System.out.println("😴 " + nome + " está dormindo e não pode buscar nada.");
        }
    }
    
    /**
     * Método específico: fazer truques
     * @param truque Nome do truque a ser executado
     */
    public void fazerTruque(String truque) {
        if (!dormindo) {
            if (adestrado) {
                System.out.println("🎭 " + nome + " executou o truque: " + truque + "!");
                diminuirEnergia(1);
            } else {
                System.out.println("🤷 " + nome + " não sabe fazer truques ainda. Precisa ser adestrado!");
            }
        } else {
            System.out.println("😴 " + nome + " está dormindo e não pode fazer truques.");
        }
    }
    
    /**
     * Método específico: proteger/guardar
     */
    public void guardar() {
        if (!dormindo) {
            emitirSom();  // Late primeiro
            System.out.println("🛡️ " + nome + " está guardando o território!");
            diminuirEnergia(2);
        } else {
            acordar();  // Cachorro acorda para guardar
            guardar();  // Chama recursivamente após acordar
        }
    }
    
    /**
     * Método específico: dar pata
     */
    public void darPata() {
        if (!dormindo) {
            if (adestrado) {
                System.out.println("🐾 " + nome + " deu a patinha!");
            } else {
                System.out.println("🤔 " + nome + " não entende o comando 'pata'.");
            }
        } else {
            System.out.println("😴 " + nome + " está dormindo e não pode dar a pata.");
        }
    }
    
    /**
     * Método específico: sentar
     */
    public void sentar() {
        if (!dormindo) {
            System.out.println("🪑 " + nome + " sentou!");
            aumentarEnergia(1);  // Descansar um pouco
        } else {
            System.out.println("😴 " + nome + " já está deitado dormindo.");
        }
    }
    
    // ===== MÉTODOS AUXILIARES =====
    
    /**
     * Método para aumentar energia
     * @param quantidade Quantidade a aumentar
     */
    private void aumentarEnergia(int quantidade) {
        nivelEnergia = Math.min(10, nivelEnergia + quantidade);
    }
    
    /**
     * Método para diminuir energia  
     * @param quantidade Quantidade a diminuir
     */
    private void diminuirEnergia(int quantidade) {
        nivelEnergia = Math.max(1, nivelEnergia - quantidade);
        
        // Se energia ficou muito baixa, cachorro fica com sono
        if (nivelEnergia <= 2) {
            System.out.println("😴 " + nome + " está ficando cansado... (energia: " + nivelEnergia + "/10)");
        }
    }
    
    /**
     * Override do toString para incluir informações de cachorro
     */
    @Override
    public String toString() {
        return String.format("Cachorro{nome='%s', raca='%s', idade=%d, peso=%.1f, adestrado=%s, energia=%d/10}", 
                           nome, raca, idade, peso, adestrado, nivelEnergia);
    }
}