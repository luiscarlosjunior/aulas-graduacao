/**
 * Programa de teste para a classe CaoDomestico
 * 
 * Demonstra o uso da classe CaoDomestico com diferentes
 * cenários e comportamentos.
 * 
 * @author Aulas Graduação
 */
public class TesteCaoDomestico {
    
    public static void main(String[] args) {
        System.out.println("=== TESTE DA CLASSE CÃO DOMÉSTICO ===\n");
        
        // ===== CRIANDO CÃES COM DIFERENTES CONSTRUTORES =====
        System.out.println("--- CRIANDO CÃES ---");
        
        // Construtor padrão
        CaoDomestico cao1 = new CaoDomestico();
        System.out.println("Cão 1 (padrão): " + cao1);
        
        // Construtor só com nome
        CaoDomestico cao2 = new CaoDomestico("Rex");
        System.out.println("Cão 2 (só nome): " + cao2);
        
        // Construtor completo
        CaoDomestico cao3 = new CaoDomestico("Buddy", "Labrador", 5, 28.5, "Dourado");
        CaoDomestico cao4 = new CaoDomestico("Luna", "Border Collie", 3, 18.2, "Preto e Branco");
        CaoDomestico cao5 = new CaoDomestico("Thor", "Pastor Alemão", 7, 35.8, "Marrom e Preto");
        
        System.out.println("Cão 3 (Buddy): " + cao3);
        System.out.println("Cão 4 (Luna): " + cao4);
        System.out.println("Cão 5 (Thor): " + cao5);
        
        System.out.println();
        
        // ===== DEMONSTRANDO COMPORTAMENTOS =====
        System.out.println("--- COMPORTAMENTOS DOS CÃES ---");
        
        // Configurando o primeiro cão
        cao1.setNome("Pequeno");
        cao1.setPeso(3.5);
        cao1.setIdade(2);
        
        // Testando latidos (varia com o peso)
        System.out.println("Testando latidos:");
        cao1.latir(); // Cão pequeno
        cao3.latir(); // Cão médio
        cao5.latir(); // Cão grande
        
        System.out.println();
        
        // Testando corrida
        System.out.println("Testando corrida:");
        cao3.correr(); // Cão adulto normal
        cao5.correr(); // Cão mais pesado
        
        // Criando um cão idoso para teste
        CaoDomestico caoIdoso = new CaoDomestico("Vovô", "SRD", 12, 20.0, "Branco");
        caoIdoso.correr(); // Cão idoso
        
        System.out.println();
        
        // ===== ATIVIDADES DIÁRIAS =====
        System.out.println("--- ATIVIDADES DIÁRIAS ---");
        
        System.out.println("=== Manhã do " + cao3.getNome() + " ===");
        cao3.comer();
        cao3.brincar();
        cao3.correr();
        
        System.out.println("\n=== Tarde da " + cao4.getNome() + " ===");
        cao4.tomarBanho();
        cao4.brincar();
        cao4.dormir();
        
        System.out.println();
        
        // ===== CUIDADOS VETERINÁRIOS =====
        System.out.println("--- CUIDADOS VETERINÁRIOS ---");
        
        System.out.println("Status de vacinação:");
        System.out.println(cao3.getNome() + " vacinado: " + cao3.isVacinado());
        cao3.vacinar();
        System.out.println(cao3.getNome() + " vacinado: " + cao3.isVacinado());
        
        System.out.println();
        
        // ===== INFORMAÇÕES E ESTATÍSTICAS =====
        System.out.println("--- INFORMAÇÕES E ESTATÍSTICAS ---");
        
        // Array de cães para demonstrar
        CaoDomestico[] caes = {cao1, cao3, cao4, cao5};
        
        System.out.println("Relatório dos cães:");
        for (int i = 0; i < caes.length; i++) {
            CaoDomestico cao = caes[i];
            System.out.println("\n" + (i + 1) + ". " + cao.getNome() + ":");
            System.out.println("   - Idade: " + cao.getIdade() + " anos (" + 
                             cao.getIdadeHumana() + " anos humanos)");
            System.out.println("   - Idade em meses: " + cao.getIdadeEmMeses());
            System.out.println("   - Peso: " + String.format("%.1f", cao.getPeso()) + "kg");
            System.out.println("   - Classificação do peso: " + cao.classificarPeso());
            System.out.println("   - Precisa exercício: " + 
                             (cao.precisaExercicio() ? "Sim" : "Não"));
            System.out.println("   - Vacinado: " + (cao.isVacinado() ? "Sim" : "Não"));
        }
        
        // ===== SIMULAÇÃO DE UM DIA =====
        System.out.println("\n--- SIMULAÇÃO DE UM DIA COMPLETO ---");
        
        CaoDomestico caoTeste = new CaoDomestico("Max", "Golden Retriever", 4, 25.0, "Dourado");
        
        System.out.println("Um dia na vida de " + caoTeste.getNome() + ":");
        System.out.println("Estado inicial: " + caoTeste);
        
        System.out.println("\n6h - Acordando:");
        caoTeste.latir();
        
        System.out.println("\n7h - Café da manhã:");
        caoTeste.comer();
        
        System.out.println("\n8h - Exercício matinal:");
        caoTeste.correr();
        caoTeste.brincar();
        
        System.out.println("\n12h - Almoço:");
        caoTeste.comer();
        
        System.out.println("\n14h - Soneca da tarde:");
        caoTeste.dormir();
        
        System.out.println("\n17h - Banho:");
        caoTeste.tomarBanho();
        
        System.out.println("\n18h - Brincadeira:");
        caoTeste.brincar();
        
        System.out.println("\n19h - Jantar:");
        caoTeste.comer();
        
        System.out.println("\n22h - Hora de dormir:");
        caoTeste.dormir();
        
        System.out.println("\nEstado final: " + caoTeste);
        
        System.out.println("\n=== FIM DO TESTE ===");
    }
}