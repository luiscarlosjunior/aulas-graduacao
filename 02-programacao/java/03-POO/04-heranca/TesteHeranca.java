/**
 * Classe de Teste - Demonstração de Herança
 * 
 * Esta classe demonstra os conceitos de herança em Java:
 * - Como classes filhas herdam comportamentos da classe pai
 * - Como métodos podem ser sobrescritos para comportamentos específicos
 * - Como usar super() para chamar métodos da classe pai
 * - Polimorfismo básico (referência pai apontando para objeto filho)
 * 
 * @author Curso POO Java
 */
public class TesteHeranca {
    
    public static void main(String[] args) {
        
        System.out.println("=== DEMONSTRAÇÃO: HERANÇA ===\n");
        
        // ===== CRIANDO OBJETOS DAS CLASSES FILHAS =====
        System.out.println("1️⃣ CRIANDO ANIMAIS (CLASSES FILHAS)\n");
        
        // Criando um cachorro
        Cachorro dog = new Cachorro("Rex", 3, 25.5, "Labrador");
        dog.setAdestrado(true);
        dog.setBrinquedoFavorito("bolinha");
        
        System.out.println();
        
        // Criando um gato
        Gato cat = new Gato("Mimi", 2, 4.2, "Cinza");
        cat.setIndependente(false);  // Gato carinhoso
        cat.setLugaritoFavorito("sofá");
        
        System.out.println();
        
        // ===== DEMONSTRANDO MÉTODOS HERDADOS =====
        System.out.println("2️⃣ USANDO MÉTODOS HERDADOS DA CLASSE PAI\n");
        
        System.out.println("--- Métodos comuns (herdados de Animal) ---");
        
        // Ambos podem usar métodos de Animal
        dog.exibirInformacoes();
        cat.exibirInformacoes();
        
        // Métodos herdados funcionam igual para ambos
        System.out.println("💤 Fazendo os animais dormirem:");
        dog.dormir();  // Usa método herdado
        cat.dormir();  // Usa método sobrescrito
        
        System.out.println("\n😊 Acordando os animais:");
        dog.acordar();
        cat.acordar();
        
        System.out.println();
        
        // ===== DEMONSTRANDO MÉTODOS SOBRESCRITOS =====
        System.out.println("3️⃣ MÉTODOS SOBRESCRITOS (COMPORTAMENTOS ESPECÍFICOS)\n");
        
        System.out.println("--- Cada animal emite som diferente ---");
        dog.emitirSom();  // Cachorro: late
        cat.emitirSom();  // Gato: mia
        
        System.out.println("\n--- Cada animal se move diferente ---");
        dog.mover();      // Cachorro: corre energicamente
        cat.mover();      // Gato: move-se elegantemente
        
        System.out.println("\n--- Cada animal come diferente ---");
        dog.comer();      // Cachorro: come com apetite
        cat.comer();      // Gato: come delicadamente
        
        System.out.println("\n--- Cada animal brinca diferente ---");
        dog.brincar();    // Cachorro: brinca com brinquedo
        cat.brincar();    // Gato: brinca com agilidade
        
        System.out.println();
        
        // ===== DEMONSTRANDO MÉTODOS ESPECÍFICOS =====
        System.out.println("4️⃣ MÉTODOS ESPECÍFICOS DE CADA CLASSE\n");
        
        System.out.println("--- Comportamentos específicos do Cachorro ---");
        dog.latir();
        dog.buscar("graveto");
        dog.fazerTruque("dar a pata");
        dog.sentar();
        dog.guardar();
        
        System.out.println("\n--- Comportamentos específicos do Gato ---");
        cat.miar();
        cat.ronronar();
        cat.afiarUnhas();
        cat.subir("estante");
        cat.cacar();
        cat.pedirCarinho();
        
        System.out.println();
        
        // ===== POLIMORFISMO BÁSICO =====
        System.out.println("5️⃣ POLIMORFISMO BÁSICO\n");
        
        System.out.println("--- Referências da classe pai apontando para objetos filhos ---");
        
        // Polimorfismo: referência Animal pode apontar para qualquer subclasse
        Animal animal1 = new Cachorro("Bobby");  // Animal aponta para Cachorro
        Animal animal2 = new Gato("Whiskers");   // Animal aponta para Gato
        
        System.out.println("\nTestando métodos polimórficos:");
        
        // Mesmo método, comportamentos diferentes (sobrescrita)
        System.out.print("Animal 1 (Cachorro): ");
        animal1.emitirSom();  // Chamará o método sobrescrito de Cachorro
        
        System.out.print("Animal 2 (Gato): ");
        animal2.emitirSom();  // Chamará o método sobrescrito de Gato
        
        // Array de animais (polimorfismo em coleções)
        Animal[] animais = {dog, cat, animal1, animal2};
        
        System.out.println("\n--- Fazendo todos os animais emitirem som ---");
        for (int i = 0; i < animais.length; i++) {
            System.out.print("Animal " + (i+1) + ": ");
            animais[i].emitirSom();  // Cada um usa sua própria implementação
        }
        
        System.out.println();
        
        // ===== DEMONSTRANDO LIMITAÇÕES DO POLIMORFISMO =====
        System.out.println("6️⃣ LIMITAÇÕES DO POLIMORFISMO\n");
        
        System.out.println("--- Referência Animal não pode acessar métodos específicos ---");
        System.out.println("✅ animal1.emitirSom() funciona (método de Animal)");
        System.out.println("❌ animal1.latir() NÃO funciona (método específico de Cachorro)");
        System.out.println("❌ animal2.ronronar() NÃO funciona (método específico de Gato)");
        
        System.out.println("\n--- Para acessar métodos específicos, precisa fazer cast ---");
        
        // Cast (conversão) necessário para acessar métodos específicos
        if (animal1 instanceof Cachorro) {  // Verifica se é realmente um Cachorro
            Cachorro dogRef = (Cachorro) animal1;  // Faz o cast
            dogRef.latir();  // Agora pode chamar método específico
        }
        
        if (animal2 instanceof Gato) {  // Verifica se é realmente um Gato
            Gato catRef = (Gato) animal2;  // Faz o cast
            catRef.ronronar();  // Agora pode chamar método específico
        }
        
        System.out.println();
        
        // ===== DEMONSTRAÇÃO AVANÇADA =====
        System.out.println("7️⃣ EXEMPLO PRÁTICO: CUIDANDO DOS ANIMAIS\n");
        
        cuidarDoAnimal(dog);   // Passa Cachorro como Animal
        cuidarDoAnimal(cat);   // Passa Gato como Animal
        
        System.out.println();
        
        // ===== COMPARANDO ANIMAIS =====
        System.out.println("8️⃣ COMPARANDO ANIMAIS (MÉTODO HERDADO)\n");
        
        Animal outroCachorro = new Cachorro("Buddy");
        
        System.out.println("Rex e Mimi são da mesma espécie? " + 
                          dog.mesmaEspecie(cat));
        System.out.println("Rex e Buddy são da mesma espécie? " + 
                          dog.mesmaEspecie(outroCachorro));
        
        // ===== ESTADO FINAL =====
        System.out.println("\n9️⃣ ESTADO FINAL DOS ANIMAIS\n");
        
        dog.exibirInformacoes();
        cat.exibirInformacoes();
        
        System.out.println("📄 Representação textual:");
        System.out.println("Cachorro: " + dog.toString());
        System.out.println("Gato: " + cat.toString());
        
        System.out.println("\n✅ Demonstração de herança concluída!");
        System.out.println("   - Herança de código ✓");
        System.out.println("   - Sobrescrita de métodos ✓");
        System.out.println("   - Métodos específicos ✓");
        System.out.println("   - Polimorfismo básico ✓");
    }
    
    /**
     * Método que demonstra polimorfismo
     * Recebe qualquer Animal (classe pai) mas funciona com qualquer subclasse
     * 
     * @param animal Qualquer animal (Cachorro, Gato, etc.)
     */
    public static void cuidarDoAnimal(Animal animal) {
        System.out.println("--- Cuidando de " + animal.getNome() + " ---");
        
        // Métodos da classe pai funcionam com qualquer subclasse
        if (animal.isDormindo()) {
            animal.acordar();
        }
        
        animal.comer();        // Cada animal come de forma diferente
        animal.brincar();      // Cada animal brinca de forma diferente
        animal.emitirSom();    // Cada animal faz som diferente
        animal.dormir();       // Volta a dormir
        
        System.out.println("✓ " + animal.getNome() + " foi bem cuidado!\n");
    }
}