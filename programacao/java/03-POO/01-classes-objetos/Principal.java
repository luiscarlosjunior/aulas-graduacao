/**
 * Classe Principal - Demonstração de Classes e Objetos
 * 
 * Esta classe demonstra como:
 * 1. Criar objetos (instanciar classes)
 * 2. Definir características (atributos)
 * 3. Chamar comportamentos (métodos)
 * 
 * @author luisc
 */
public class Principal {
    
    /**
     * Método main - ponto de entrada da aplicação
     * Aqui demonstramos o uso prático da classe CaoDomestico
     */
    public static void main(String[] args) {
        
        System.out.println("=== Demonstração: Classes e Objetos ===\n");
        
        // ===== PASSO 1: CRIANDO UM OBJETO =====
        // Usamos a palavra 'new' para criar uma nova instância da classe
        CaoDomestico cd = new CaoDomestico();
        System.out.println("✓ Objeto CaoDomestico criado!");
        
        // ===== PASSO 2: DEFININDO CARACTERÍSTICAS =====
        // Atribuímos valores aos atributos do objeto
        cd.nome = "Pluto";           // Nome do nosso cão
        cd.corOlhos = "Azuis";       // Cor dos olhos
        cd.peso = 53;                // Peso em quilos (cão médio)
        cd.quantPatas = 4;           // Número de patas
        
        System.out.println("✓ Características definidas:");
        System.out.println("  - Nome: " + cd.nome);
        System.out.println("  - Cor dos olhos: " + cd.corOlhos);
        System.out.println("  - Peso: " + cd.peso + "kg");
        System.out.println("  - Quantidade de patas: " + cd.quantPatas);
        
        // ===== PASSO 3: CHAMANDO COMPORTAMENTOS =====
        // Executamos métodos do objeto para ver seus comportamentos
        System.out.println("\n=== Comportamentos do " + cd.nome + " ===");
        
        cd.latir();      // Chama o método latir()
        cd.falar();      // Chama o método falar()
        cd.andar();      // Chama o método andar()
        cd.comer();      // Chama o método comer()
        cd.dormir();     // Chama o método dormir()
        
        // ===== DEMONSTRAÇÃO ADICIONAL: MÚLTIPLOS OBJETOS =====
        System.out.println("\n=== Criando outro cão ===");
        
        // Criamos um segundo cão usando o construtor com parâmetro
        CaoDomestico cd2 = new CaoDomestico("Bella");
        cd2.peso = 8;  // Cão pequeno
        cd2.corOlhos = "Marrom";
        cd2.quantPatas = 4;
        
        System.out.println("✓ Segundo cão criado: " + cd2.nome);
        System.out.print("Latido da " + cd2.nome + ": ");
        cd2.latir();  // Note a diferença no latido por causa do peso
        
        // ===== COMPARAÇÃO DOS LATIDOS =====
        System.out.println("\n=== Comparação de Latidos ===");
        System.out.print(cd.nome + " (peso: " + cd.peso + "kg): ");
        cd.latir();
        
        System.out.print(cd2.nome + " (peso: " + cd2.peso + "kg): ");
        cd2.latir();
        
        System.out.println("\n✓ Demonstração concluída!");
    }
}
