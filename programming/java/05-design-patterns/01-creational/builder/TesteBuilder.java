/**
 * Programa de teste para o padrão Builder
 * 
 * Demonstra diferentes formas de construir objetos complexos
 * usando o Builder pattern.
 * 
 * @author Aulas Graduação
 */
public class TesteBuilder {
    
    public static void main(String[] args) {
        System.out.println("🏗️ DEMONSTRAÇÃO DO PADRÃO BUILDER\n");
        
        // Exemplo 1: Computador básico
        System.out.println("📦 Exemplo 1: Computador Básico");
        System.out.println("-".repeat(50));
        
        Computador pcBasico = new Computador.Builder("Intel Core i3", 8)
                .build();
        
        System.out.println(pcBasico.getEspecificacoes());
        System.out.println("\n💰 Preço: R$ " + String.format("%.2f", pcBasico.calcularPreco()));
        
        // Exemplo 2: Computador para jogos
        System.out.println("\n\n🎮 Exemplo 2: PC Gamer Completo");
        System.out.println("-".repeat(50));
        
        Computador pcGamer = new Computador.Builder("Intel Core i9-13900K", 32)
                .comPlacaVideo("NVIDIA RTX 4090")
                .comArmazenamento(2000, "SSD")
                .comWifi()
                .comBluetooth()
                .comSistemaOperacional("Windows 11 Pro")
                .comGabinete("NZXT H510 Elite RGB")
                .build();
        
        System.out.println(pcGamer.getEspecificacoes());
        System.out.println("\n💰 Preço: R$ " + String.format("%.2f", pcGamer.calcularPreco()));
        
        // Exemplo 3: Workstation para desenvolvimento
        System.out.println("\n\n👨‍💻 Exemplo 3: Workstation para Desenvolvimento");
        System.out.println("-".repeat(50));
        
        Computador workstation = new Computador.Builder("AMD Ryzen 9 7950X", 64)
                .comPlacaVideo("NVIDIA RTX 4070")
                .comArmazenamento(1000, "SSD")
                .comWifi()
                .comSistemaOperacional("Ubuntu 22.04 LTS")
                .comGabinete("Fractal Design Meshify C")
                .build();
        
        System.out.println(workstation.getEspecificacoes());
        System.out.println("\n💰 Preço: R$ " + String.format("%.2f", workstation.calcularPreco()));
        
        // Exemplo 4: Servidor
        System.out.println("\n\n🖥️ Exemplo 4: Servidor Web");
        System.out.println("-".repeat(50));
        
        Computador servidor = new Computador.Builder("Intel Xeon Gold", 128)
                .comArmazenamento(4000, "SSD")
                .comSistemaOperacional("CentOS 8")
                .comGabinete("Rack 2U")
                .build();
        
        System.out.println(servidor.getEspecificacoes());
        System.out.println("\n💰 Preço: R$ " + String.format("%.2f", servidor.calcularPreco()));
        
        // Exemplo 5: Comparação - Demonstrando flexibilidade
        System.out.println("\n\n📊 Exemplo 5: Comparação de Preços");
        System.out.println("-".repeat(50));
        
        Computador[] computadores = {
            new Computador.Builder("Intel Core i3", 8).build(),
            new Computador.Builder("Intel Core i5", 16).comArmazenamento(500, "SSD").build(),
            new Computador.Builder("Intel Core i7", 32).comPlacaVideo("RTX 4070").build()
        };
        
        for (int i = 0; i < computadores.length; i++) {
            System.out.printf("Computador %d: R$ %.2f%n", 
                    i + 1, computadores[i].calcularPreco());
        }
        
        // Demonstração de validação
        System.out.println("\n\n⚠️ Demonstração de Validação");
        System.out.println("-".repeat(50));
        
        try {
            // Tentando criar sem processador (deve lançar exceção)
            Computador pcInvalido = new Computador.Builder(null, 8).build();
        } catch (IllegalArgumentException e) {
            System.out.println("❌ Erro capturado: " + e.getMessage());
        }
        
        try {
            // Tentando criar com RAM inválida (deve lançar exceção)
            Computador pcInvalido = new Computador.Builder("Intel i5", 0).build();
        } catch (IllegalArgumentException e) {
            System.out.println("❌ Erro capturado: " + e.getMessage());
        }
        
        // Conclusão
        System.out.println("\n\n✅ VANTAGENS DO BUILDER:");
        System.out.println("1. Construção passo a passo de objetos complexos");
        System.out.println("2. Código mais legível e fluente");
        System.out.println("3. Parâmetros opcionais sem construtores telescópicos");
        System.out.println("4. Imutabilidade do objeto final");
        System.out.println("5. Validação centralizada no Builder");
    }
}
