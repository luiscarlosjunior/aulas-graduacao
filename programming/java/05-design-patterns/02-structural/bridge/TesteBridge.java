/**
 * Classe de Teste do Padrão Bridge
 * 
 * Demonstra como o padrão Bridge permite que abstrações (ControleRemoto)
 * e implementações (Dispositivo) variem independentemente.
 * 
 * O padrão Bridge desacopla uma abstração de sua implementação,
 * permitindo que ambas sejam estendidas independentemente.
 */
public class TesteBridge {
    
    /**
     * Testa operações básicas com um controle remoto
     */
    public static void testarControleBasico() {
        System.out.println("=== Teste com Controle Remoto Básico ===\n");
        
        // Criando dispositivos
        Dispositivo tv = new TV("Samsung 55\"");
        Dispositivo radio = new Radio("Sony XM-750");
        
        // Usando controle básico com TV
        System.out.println("--- Controlando TV com controle básico ---");
        ControleRemoto controleTV = new ControleRemoto(tv);
        controleTV.alternarLigado();
        controleTV.aumentarVolume();
        controleTV.aumentarVolume();
        controleTV.proximoCanal();
        controleTV.proximoCanal();
        controleTV.exibirInfo();
        
        // Mesmo controle pode trabalhar com rádio
        System.out.println("\n--- Controlando Rádio com controle básico ---");
        ControleRemoto controleRadio = new ControleRemoto(radio);
        controleRadio.alternarLigado();
        controleRadio.aumentarVolume();
        controleRadio.proximoCanal();
        controleRadio.exibirInfo();
    }
    
    /**
     * Testa operações avançadas com um controle remoto avançado
     */
    public static void testarControleAvancado() {
        System.out.println("\n\n=== Teste com Controle Remoto Avançado ===\n");
        
        // Criando dispositivos
        Dispositivo tv = new TV("LG OLED 65\"");
        Dispositivo radio = new Radio("JBL Tuner");
        
        // Usando controle avançado com TV
        System.out.println("--- Controlando TV com controle avançado ---");
        ControleRemotoAvancado controleAvancadoTV = new ControleRemotoAvancado(tv);
        controleAvancadoTV.alternarLigado();
        controleAvancadoTV.volumeMaximo();
        controleAvancadoTV.irParaCanal(25);
        controleAvancadoTV.mudo();
        controleAvancadoTV.exibirInfo();
        
        // Mesmo controle avançado pode trabalhar com rádio
        System.out.println("\n--- Controlando Rádio com controle avançado ---");
        ControleRemotoAvancado controleAvancadoRadio = new ControleRemotoAvancado(radio);
        controleAvancadoRadio.alternarLigado();
        controleAvancadoRadio.aumentarVolume();
        controleAvancadoRadio.scanCanais();
        controleAvancadoRadio.exibirInfo();
    }
    
    /**
     * Demonstra a flexibilidade do padrão Bridge
     */
    public static void demonstrarFlexibilidade() {
        System.out.println("\n\n=== Demonstração de Flexibilidade ===\n");
        
        // Criando array de dispositivos
        Dispositivo[] dispositivos = {
            new TV("Philips 42\""),
            new Radio("Motorola M-100"),
            new TV("Sony Bravia 50\"")
        };
        
        // Criando array de controles (polimorfismo)
        ControleRemoto[] controles = {
            new ControleRemoto(dispositivos[0]),
            new ControleRemotoAvancado(dispositivos[1]),
            new ControleRemotoAvancado(dispositivos[2])
        };
        
        System.out.println("Testando múltiplas combinações de controle-dispositivo:");
        
        for (int i = 0; i < controles.length; i++) {
            System.out.println("\n--- Teste " + (i + 1) + " ---");
            controles[i].alternarLigado();
            controles[i].aumentarVolume();
            controles[i].proximoCanal();
            
            // Funcionalidade específica do controle avançado
            if (controles[i] instanceof ControleRemotoAvancado) {
                ControleRemotoAvancado ca = (ControleRemotoAvancado) controles[i];
                ca.irParaCanal(50);
            }
            
            controles[i].exibirInfo();
        }
    }
    
    /**
     * Explica os benefícios do padrão Bridge
     */
    public static void exibirBeneficios() {
        System.out.println("\n\n=== Benefícios do Padrão Bridge ===");
        System.out.println("\n1. SEPARAÇÃO DE RESPONSABILIDADES");
        System.out.println("   - Abstrações (ControleRemoto) e Implementações (Dispositivo) separadas");
        System.out.println("   - Cada hierarquia pode evoluir independentemente");
        
        System.out.println("\n2. EXTENSIBILIDADE");
        System.out.println("   - Novos controles: Basta estender ControleRemoto");
        System.out.println("   - Novos dispositivos: Basta implementar Dispositivo");
        System.out.println("   - Sem modificar código existente (Open/Closed Principle)");
        
        System.out.println("\n3. REDUÇÃO DE ACOPLAMENTO");
        System.out.println("   - Abstração não conhece detalhes da implementação");
        System.out.println("   - Implementação não conhece detalhes da abstração");
        System.out.println("   - Comunicação apenas através de interfaces bem definidas");
        
        System.out.println("\n4. FLEXIBILIDADE EM RUNTIME");
        System.out.println("   - Pode trocar implementação em tempo de execução");
        System.out.println("   - Mesmo controle pode trabalhar com diferentes dispositivos");
        
        System.out.println("\n5. EVITA EXPLOSÃO DE CLASSES");
        System.out.println("   - Sem Bridge: N controles × M dispositivos = N×M classes");
        System.out.println("   - Com Bridge: N controles + M dispositivos = N+M classes");
        System.out.println("   - Exemplo: 3 controles × 3 dispositivos");
        System.out.println("     • Sem Bridge: 9 classes");
        System.out.println("     • Com Bridge: 6 classes");
    }
    
    public static void main(String[] args) {
        // Executa todos os testes
        testarControleBasico();
        testarControleAvancado();
        demonstrarFlexibilidade();
        exibirBeneficios();
        
        System.out.println("\n\n=== Fim da Demonstração ===");
        System.out.println("\nO padrão Bridge permite que você:");
        System.out.println("✓ Separe abstração de implementação");
        System.out.println("✓ Estenda ambas independentemente");
        System.out.println("✓ Reduza acoplamento entre camadas");
        System.out.println("✓ Melhore manutenibilidade e testabilidade");
    }
}
