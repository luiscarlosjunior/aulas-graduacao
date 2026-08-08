/**
 * Exemplo prático de Herança em Java - Segunda Classe Filha
 * 
 * Esta classe também herda de Animal, demonstrando como diferentes
 * classes filhas podem especializar a mesma classe pai de formas distintas.
 * Mostra:
 * - Herança de uma mesma classe pai (Animal)
 * - Sobrescrita com comportamentos específicos de gato
 * - Novos atributos e métodos específicos
 * - Diferentes formas de usar super()
 * 
 * @author Curso POO Java
 */
public class Gato extends Animal {  // Também herda de Animal (como Cachorro)
    
    // ===== ATRIBUTOS ESPECÍFICOS DO GATO =====
    
    private String corPelo;             // Cor do pelo
    private boolean castrado;           // Se o gato é castrado
    private int vidasRestantes;         // Número de vidas restantes (mito dos 9 vidas)
    private boolean independente;       // Nível de independência
    private String lugaritoFavorito;    // Local preferido para ficar
    
    // ===== CONSTRUTORES =====
    
    /**
     * Construtor padrão
     */
    public Gato() {
        super();  // Chama construtor da classe Animal
        this.especie = "Gato";
        this.vidasRestantes = 9;        // Gatos começam com 9 vidas
        this.independente = true;       // Gatos são naturalmente independentes
        System.out.println("🐱 Gato criado com construtor padrão");
    }
    
    /**
     * Construtor com nome
     * @param nome Nome do gato
     */
    public Gato(String nome) {
        super(nome, "Gato");  // Chama construtor da classe pai
        this.vidasRestantes = 9;
        this.independente = true;
        System.out.println("🐱 Gato " + nome + " criado");
    }
    
    /**
     * Construtor completo
     * @param nome Nome do gato
     * @param idade Idade do gato
     * @param peso Peso do gato
     * @param corPelo Cor do pelo
     */
    public Gato(String nome, int idade, double peso, String corPelo) {
        super(nome, "Gato", idade, peso);  // Chama construtor completo da classe pai
        this.corPelo = corPelo;
        this.vidasRestantes = 9;
        this.independente = true;
        this.castrado = false;
        System.out.println("🐱 Gato " + nome + " de pelo " + corPelo + " criado");
    }
    
    // ===== MÉTODOS GETTERS E SETTERS ESPECÍFICOS =====
    
    public String getCorPelo() {
        return corPelo;
    }
    
    public void setCorPelo(String corPelo) {
        this.corPelo = corPelo;
    }
    
    public boolean isCastrado() {
        return castrado;
    }
    
    public void setCastrado(boolean castrado) {
        this.castrado = castrado;
        System.out.println("🏥 " + nome + " agora está " + 
                          (castrado ? "castrado" : "não castrado"));
    }
    
    public int getVidasRestantes() {
        return vidasRestantes;
    }
    
    public boolean isIndependente() {
        return independente;
    }
    
    public void setIndependente(boolean independente) {
        this.independente = independente;
        System.out.println("🎭 " + nome + " agora é " + 
                          (independente ? "independente" : "dependente"));
    }
    
    public String getLugaritoFavorito() {
        return lugaritoFavorito;
    }
    
    public void setLugaritoFavorito(String lugarito) {
        this.lugaritoFavorito = lugarito;
        System.out.println("🏠 Lugar favorito de " + nome + " agora é: " + lugarito);
    }
    
    // ===== MÉTODOS SOBRESCRITOS (OVERRIDE) =====
    
    /**
     * Sobrescrita do método emitirSom da classe Animal
     * Gato tem som específico: miar
     */
    @Override
    public void emitirSom() {
        if (!dormindo) {
            // Tipo de miado varia conforme a situação
            if (independente) {
                System.out.println("🐱 " + nome + ": Meow... (miado calmo e independente)");
            } else {
                System.out.println("🐱 " + nome + ": Miau! Miau! (miado pedindo atenção)");
            }
        } else {
            System.out.println("😴 " + nome + " está dormindo silenciosamente.");
        }
    }
    
    /**
     * Sobrescrita do método mover da classe Animal
     * Gatos se movem de forma mais elegante e silenciosa
     */
    @Override
    public void mover() {
        if (!dormindo) {
            if (independente) {
                System.out.println("🐾 " + nome + " está caminhando silenciosamente e com elegância.");
            } else {
                System.out.println("🐾 " + nome + " está se movendo e buscando atenção.");
            }
        } else {
            super.mover();  // Chama implementação da classe pai
        }
    }
    
    /**
     * Sobrescrita do método comer da classe Animal
     * Gatos são mais seletivos com comida
     */
    @Override
    public void comer() {
        if (!dormindo) {
            if (independente) {
                System.out.println("🐟 " + nome + " está comendo delicadamente sua refeição.");
            } else {
                System.out.println("🐟 " + nome + " está comendo e ronronando de satisfação.");
            }
        } else {
            super.comer();  // Chama implementação da classe pai
        }
    }
    
    /**
     * Sobrescrita do método brincar da classe Animal
     * Gatos brincam de forma diferente
     */
    @Override
    public void brincar() {
        if (!dormindo) {
            System.out.println("🧶 " + nome + " está brincando com agilidade felina!");
            if (!independente) {
                System.out.println("❤️ " + nome + " quer que você brinque junto!");
            }
        } else {
            super.brincar();  // Chama implementação da classe pai
        }
    }
    
    /**
     * Sobrescrita do método dormir da classe Animal
     * Gatos dormem muito mais que outros animais
     */
    @Override
    public void dormir() {
        if (!dormindo) {
            dormindo = true;
            if (lugaritoFavorito != null) {
                System.out.println("😴 " + nome + " está dormindo em " + lugaritoFavorito + "... Zzz...");
            } else {
                System.out.println("😴 " + nome + " encontrou um cantinho confortável e está dormindo... Zzz...");
            }
        } else {
            System.out.println("💤 " + nome + " continua dormindo profundamente.");
        }
    }
    
    /**
     * Sobrescrita do método exibirInformacoes
     * Adiciona informações específicas de gato
     */
    @Override
    public void exibirInformacoes() {
        super.exibirInformacoes();  // Chama método da classe pai
        
        // Adiciona informações específicas de gato
        System.out.println("=== Informações Específicas ===");
        System.out.println("Cor do pelo: " + (corPelo != null ? corPelo : "Não definida"));
        System.out.println("Castrado: " + (castrado ? "Sim" : "Não"));
        System.out.println("Vidas restantes: " + vidasRestantes + "/9");
        System.out.println("Independente: " + (independente ? "Sim" : "Não"));
        System.out.println("Lugar favorito: " + (lugaritoFavorito != null ? lugaritoFavorito : "Qualquer cantinho"));
        System.out.println("===============================\n");
    }
    
    // ===== MÉTODOS ESPECÍFICOS DO GATO =====
    
    /**
     * Método específico: miar (similar a emitirSom, mas mais específico)
     */
    public void miar() {
        emitirSom();  // Reutiliza a lógica do emitirSom sobrescrito
    }
    
    /**
     * Método específico: ronronar
     */
    public void ronronar() {
        if (!dormindo) {
            System.out.println("😸 " + nome + " está ronronando de satisfação... purr purr...");
        } else {
            System.out.println("😴 " + nome + " está ronronando suavemente enquanto dorme.");
        }
    }
    
    /**
     * Método específico: afiar unhas
     */
    public void afiarUnhas() {
        if (!dormindo) {
            System.out.println("💅 " + nome + " está afiando as unhas!");
        } else {
            System.out.println("😴 " + nome + " está dormindo e não pode afiar as unhas agora.");
        }
    }
    
    /**
     * Método específico: subir em lugares altos
     */
    public void subir(String lugar) {
        if (!dormindo) {
            System.out.println("🧗 " + nome + " subiu agilmente em " + lugar + "!");
            if (lugaritoFavorito == null) {
                setLugaritoFavorito(lugar);  // Define como lugar favorito se não tiver um
            }
        } else {
            System.out.println("😴 " + nome + " está dormindo e não pode subir agora.");
        }
    }
    
    /**
     * Método específico: caçar (comportamento instintivo)
     */
    public void cacar() {
        if (!dormindo) {
            System.out.println("🎯 " + nome + " ativou o instinto de caça! Está observando atentamente...");
            if (vidasRestantes < 9) {
                System.out.println("🦊 A experiência de " + nome + " em caça está aumentando!");
            }
        } else {
            System.out.println("😴 " + nome + " está sonhando que está caçando...");
        }
    }
    
    /**
     * Método específico: ignorar humano (comportamento típico felino)
     */
    public void ignorarHumano() {
        if (independente) {
            System.out.println("😒 " + nome + " está te ignorando completamente...");
        } else {
            System.out.println("❤️ " + nome + " não consegue te ignorar e vem buscar carinho!");
        }
    }
    
    /**
     * Método específico: pedir carinho
     */
    public void pedirCarinho() {
        if (!dormindo) {
            if (!independente) {
                miar();  // Mia primeiro
                System.out.println("❤️ " + nome + " está esfregando em você pedindo carinho!");
                ronronar();  // Ronrona de satisfação
            } else {
                System.out.println("😤 " + nome + " é muito independente para pedir carinho.");
            }
        } else {
            System.out.println("😴 " + nome + " está dormindo e não quer carinho agora.");
        }
    }
    
    /**
     * Método específico: usar uma das nove vidas
     */
    public void usarVida() {
        if (vidasRestantes > 1) {
            vidasRestantes--;
            System.out.println("💀 " + nome + " usou uma vida! Restam " + vidasRestantes + " vidas.");
        } else {
            System.out.println("😱 " + nome + " está na última vida! Cuidado!");
        }
    }
    
    /**
     * Método específico: se esconder
     */
    public void esconder() {
        if (!dormindo) {
            System.out.println("📦 " + nome + " se escondeu em um lugar secreto!");
        } else {
            System.out.println("😴 " + nome + " já está 'escondido' dormindo.");
        }
    }
    
    /**
     * Override do toString para incluir informações de gato
     */
    @Override
    public String toString() {
        return String.format("Gato{nome='%s', corPelo='%s', idade=%d, peso=%.1f, vidas=%d/9, independente=%s}", 
                           nome, corPelo, idade, peso, vidasRestantes, independente);
    }
}