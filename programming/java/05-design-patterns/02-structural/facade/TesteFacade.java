/**
 * Teste do Padrão Facade
 * 
 * Demonstra como o Facade simplifica o uso de um subsistema complexo
 */
public class TesteFacade {
    
    public static void main(String[] args) {
        System.out.println("=== Padrão Facade - Sistema Home Theater ===");
        
        // Criando os subsistemas (normalmente feito uma vez)
        DVDPlayer dvd = new DVDPlayer();
        Amplificador amp = new Amplificador();
        Projetor projetor = new Projetor();
        Luzes luzes = new Luzes();
        
        // Criando a facade
        HomeTheaterFacade homeTheater = new HomeTheaterFacade(dvd, amp, projetor, luzes);
        
        System.out.println("\n--- Comparação: COM vs SEM Facade ---");
        System.out.println("\nSEM FACADE (código cliente complexo):");
        System.out.println("- Cliente precisa conhecer 4 subsistemas");
        System.out.println("- Cliente precisa saber ordem correta de inicialização");
        System.out.println("- Cliente precisa conhecer métodos de cada subsistema");
        System.out.println("- Código duplicado se múltiplos clientes");
        System.out.println("- Difícil manutenção");
        
        System.out.println("\nCOM FACADE (código cliente simples):");
        System.out.println("- Cliente conhece apenas a Facade");
        System.out.println("- Um método encapsula toda complexidade");
        System.out.println("- Fácil de usar");
        System.out.println("- Código limpo e manutenível");
        
        // Usando a facade - operação complexa simplificada!
        homeTheater.assistirFilme("Matrix");
        
        // Simulando que o filme acabou...
        System.out.println("... (2 horas depois) ...\n");
        
        // Encerrando - novamente, muito simples!
        homeTheater.encerrarFilme();
        
        // Outro caso de uso
        homeTheater.modoGame();
        
        System.out.println("\n=== Vantagens Demonstradas ===");
        System.out.println("1. Interface simplificada para operações complexas");
        System.out.println("2. Cliente não precisa conhecer detalhes dos subsistemas");
        System.out.println("3. Reduz dependências entre cliente e subsistemas");
        System.out.println("4. Facilita manutenção e evolução do sistema");
        System.out.println("5. Não impede acesso direto aos subsistemas quando necessário");
        
        System.out.println("\n=== Nota Importante ===");
        System.out.println("O Facade não esconde os subsistemas completamente.");
        System.out.println("Cliente ainda pode acessá-los diretamente se necessário:");
        System.out.println("\nExemplo de acesso direto:");
        dvd.ligar();
        dvd.inserirDVD("Extras");
        dvd.play();
        System.out.println("\nIsso é útil para casos especiais não cobertos pela facade.");
    }
}
