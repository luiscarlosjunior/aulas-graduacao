/**
 * Classe de Teste - Demonstração Completa de Abstração
 * 
 * Esta classe demonstra todos os aspectos da abstração em Java:
 * 1. Interfaces - contratos que classes devem seguir
 * 2. Classes abstratas - implementação parcial com métodos abstratos
 * 3. Classes concretas - implementação completa
 * 4. Polimorfismo através de abstração
 * 5. Métodos default e static em interfaces
 * 6. Múltiplas implementações do mesmo contrato
 * 
 * @author Curso POO Java
 */
public class TesteAbstracao {
    
    public static void main(String[] args) {
        
        System.out.println("=== DEMONSTRAÇÃO COMPLETA: ABSTRAÇÃO ===\n");
        
        // ===== 1. CRIANDO DISPOSITIVOS CONCRETOS =====
        System.out.println("1️⃣ CRIANDO DISPOSITIVOS ELETRÔNICOS\n");
        
        // Criando smartphone
        Smartphone smartphone = new Smartphone(
            "iPhone 15", "SN123456789", "iOS 17.0",
            "Vivo", "(11) 99999-8888", 6.1, 8, 256
        );
        
        System.out.println();
        
        // Criando tablet
        Tablet tablet = new Tablet(
            "iPad Pro", "SN987654321", "iPadOS 17.0",
            12.9, true, true
        );
        
        System.out.println();
        
        // ===== 2. DEMONSTRANDO MÉTODOS STATIC DA INTERFACE =====
        System.out.println("2️⃣ MÉTODOS UTILITÁRIOS STATIC DA INTERFACE\n");
        
        // Testando validações
        System.out.println("--- Validações de rede ---");
        System.out.println("'MinhaRede' é válida? " + Dispositivo.validarNomeRede("MinhaRede"));
        System.out.println("'' é válida? " + Dispositivo.validarNomeRede(""));
        System.out.println("'RedeComNomeMuitoLongoParaSerValida123456789' é válida? " + 
                          Dispositivo.validarNomeRede("RedeComNomeMuitoLongoParaSerValida123456789"));
        
        System.out.println("\n--- Validações de senha ---");
        System.out.println("'123456' é segura? " + Dispositivo.senhaSegura("123456"));
        System.out.println("'MinhaSenh@123' é segura? " + Dispositivo.senhaSegura("MinhaSenh@123"));
        
        System.out.println("\n--- Cálculos utilitários ---");
        System.out.printf("Consumo mensal (15W, 8h/dia): %.2f kWh%n", 
                         Dispositivo.calcularConsumoMensal(15, 8));
        
        System.out.println("ID gerado: " + Dispositivo.gerarIdDispositivo("Apple", "iPhone"));
        
        System.out.println("Comparar versões '2.1.3' vs '2.1.4': " + 
                          Dispositivo.compararVersoes("2.1.3", "2.1.4"));
        
        // ===== 3. POLIMORFISMO ATRAVÉS DE ABSTRAÇÃO =====
        System.out.println("\n3️⃣ POLIMORFISMO ATRAVÉS DE ABSTRAÇÃO\n");
        
        // Array polimórfico usando a interface
        Dispositivo[] dispositivos = {smartphone, tablet};
        
        System.out.println("--- Processando dispositivos via interface ---");
        for (Dispositivo dispositivo : dispositivos) {
            System.out.println("Dispositivo: " + dispositivo.obterModelo());
            dispositivo.ligar();
            dispositivo.executarDiagnostico();
            System.out.println();
        }
        
        // ===== 4. DEMONSTRANDO IMPLEMENTAÇÕES ESPECÍFICAS =====
        System.out.println("4️⃣ IMPLEMENTAÇÕES ESPECÍFICAS DOS MÉTODOS ABSTRATOS\n");
        
        System.out.println("--- Configurando dispositivos ---");
        smartphone.configurarDispositivo();
        System.out.println();
        tablet.configurarDispositivo();
        
        System.out.println("\n--- Executando funções principais ---");
        smartphone.executarFuncaoPrincipal();
        tablet.executarFuncaoPrincipal();
        
        // ===== 5. MÉTODOS DEFAULT DA INTERFACE =====
        System.out.println("\n5️⃣ MÉTODOS DEFAULT DA INTERFACE\n");
        
        System.out.println("--- Usando métodos default ---");
        smartphone.entrarStandby();
        tablet.carregarBateria(30);
        
        System.out.println("\n--- Resetando configurações (método default) ---");
        smartphone.resetarConfiguracoes();
        
        // ===== 6. CONECTIVIDADE E REDE =====
        System.out.println("\n6️⃣ CONECTIVIDADE E REDE\n");
        
        System.out.println("--- Conectando dispositivos à rede ---");
        boolean conexaoSmartphone = smartphone.conectarRede("WiFi_Casa", "MinhaSenh@123");
        boolean conexaoTablet = tablet.conectarRede("WiFi_Casa", "MinhaSenh@123");
        
        System.out.println("\nStatus de conexão:");
        System.out.println("Smartphone conectado: " + smartphone.estaConectado());
        System.out.println("Tablet conectado: " + tablet.estaConectado());
        
        // ===== 7. ATUALIZAÇÕES DE SOFTWARE =====
        System.out.println("\n7️⃣ ATUALIZAÇÕES DE SOFTWARE\n");
        
        System.out.println("--- Verificando necessidade de atualização ---");
        System.out.println("Smartphone precisa atualizar? " + 
                          smartphone.precisaAtualizacao(smartphone.obterVersaoSoftware()));
        
        System.out.println("\n--- Atualizando software ---");
        smartphone.atualizarSoftware("iOS 17.1");
        tablet.atualizarSoftware("iPadOS 17.1");
        
        // ===== 8. FUNCIONALIDADES ESPECÍFICAS =====
        System.out.println("\n8️⃣ FUNCIONALIDADES ESPECÍFICAS\n");
        
        System.out.println("--- Funcionalidades do Smartphone ---");
        smartphone.fazerLigacao("(11) 88888-7777");
        smartphone.enviarMensagem("(11) 88888-7777", "Olá! Como vai?");
        smartphone.tirarFoto();
        smartphone.adicionarContato("João Silva", "(11) 77777-6666");
        smartphone.ativarGPS();
        smartphone.navegar("Shopping Center");
        
        System.out.println("\n--- Funcionalidades do Tablet ---");
        tablet.instalarAplicativoEducativo("Calculadora Científica");
        tablet.rotacionarTela();
        tablet.ativarModoLeitura();
        tablet.desenharComCaneta();
        
        // ===== 9. SIMULAÇÃO DE USO =====
        System.out.println("\n9️⃣ SIMULAÇÃO DE USO E BATERIA\n");
        
        System.out.println("--- Simulando uso intensivo ---");
        smartphone.simularUso(60); // 1 hora de uso
        tablet.simularUso(90);     // 1.5 horas de uso
        
        System.out.println("\n--- Status da bateria ---");
        System.out.println("Bateria smartphone: " + smartphone.obterNivelBateria() + "%");
        System.out.println("Bateria tablet: " + tablet.obterNivelBateria() + "%");
        
        // ===== 10. POLIMORFISMO COM CLASSE ABSTRATA =====
        System.out.println("\n🔟 POLIMORFISMO COM CLASSE ABSTRATA\n");
        
        // Array usando classe abstrata
        DispositivoEletronico[] eletrônicos = {smartphone, tablet};
        
        System.out.println("--- Exibindo estatísticas via classe abstrata ---");
        for (DispositivoEletronico dispositivo : eletrônicos) {
            dispositivo.exibirEstatisticas();
        }
        
        // ===== 11. INFORMAÇÕES DETALHADAS =====
        System.out.println("1️⃣1️⃣ INFORMAÇÕES DETALHADAS\n");
        
        System.out.println("--- Informações completas dos dispositivos ---");
        smartphone.exibirInformacoes();
        tablet.exibirInformacoes();
        
        // ===== 12. TESTES DE CASTING E INSTANCEOF =====
        System.out.println("1️⃣2️⃣ CASTING E VERIFICAÇÃO DE TIPOS\n");
        
        System.out.println("--- Testando instanceof com abstração ---");
        
        for (Dispositivo dispositivo : dispositivos) {
            System.out.println("\nAnalisando: " + dispositivo.obterModelo());
            
            // Verificação por interface
            if (dispositivo instanceof Dispositivo) {
                System.out.println("✓ É um Dispositivo (interface)");
            }
            
            // Verificação por classe abstrata
            if (dispositivo instanceof DispositivoEletronico) {
                System.out.println("✓ É um DispositivoEletronico (classe abstrata)");
                
                DispositivoEletronico eletronico = (DispositivoEletronico) dispositivo;
                System.out.println("  Pode simular uso: Sim");
            }
            
            // Verificação por classe concreta
            if (dispositivo instanceof Smartphone) {
                System.out.println("✓ É um Smartphone (classe concreta)");
                Smartphone phone = (Smartphone) dispositivo;
                System.out.println("  Operadora: " + phone.getOperadora());
                
            } else if (dispositivo instanceof Tablet) {
                System.out.println("✓ É um Tablet (classe concreta)");
                Tablet tab = (Tablet) dispositivo;
                System.out.println("  Tela: " + tab.getTamanhoTela() + " polegadas");
            }
        }
        
        // ===== 13. MÉTODO POLIMÓRFICO GENÉRICO =====
        System.out.println("\n1️⃣3️⃣ MÉTODO POLIMÓRFICO GENÉRICO\n");
        
        System.out.println("--- Processamento genérico de dispositivos ---");
        processarDispositivo(smartphone);
        processarDispositivo(tablet);
        
        // ===== 14. COMPARAÇÕES E TESTES FINAIS =====
        System.out.println("1️⃣4️⃣ TESTES FINAIS\n");
        
        System.out.println("--- Desligando todos os dispositivos ---");
        for (Dispositivo dispositivo : dispositivos) {
            dispositivo.desligar();
        }
        
        System.out.println("\n--- Representação textual final ---");
        System.out.println("Smartphone: " + smartphone.toString());
        System.out.println("Tablet: " + tablet.toString());
        
        // ===== RESUMO FINAL =====
        System.out.println("\n🎯 RESUMO DOS CONCEITOS DEMONSTRADOS\n");
        
        System.out.println("✅ Abstração demonstrada com sucesso:");
        System.out.println("   1. Interface (Dispositivo) - contrato com métodos abstratos e default");
        System.out.println("   2. Classe abstrata (DispositivoEletronico) - implementação parcial");
        System.out.println("   3. Classes concretas (Smartphone, Tablet) - implementação completa");
        System.out.println("   4. Polimorfismo através de abstração");
        System.out.println("   5. Métodos static e default em interfaces");
        System.out.println("   6. Herança múltipla de comportamento (interface)");
        System.out.println("   7. Encapsulamento de complexidade");
        System.out.println("   8. Flexibilidade e extensibilidade");
        
        System.out.println("\n✅ Demonstração de abstração concluída!");
    }
    
    // ===== MÉTODO AUXILIAR PARA DEMONSTRAÇÃO =====
    
    /**
     * Método polimórfico que funciona com qualquer implementação de Dispositivo
     * Demonstra como a abstração permite código genérico e reutilizável
     */
    public static void processarDispositivo(Dispositivo dispositivo) {
        System.out.println("🔄 Processamento genérico de: " + dispositivo.obterModelo());
        
        // Métodos da interface funcionam com qualquer implementação
        String status = dispositivo.obterStatus();
        int bateria = dispositivo.obterNivelBateria();
        
        System.out.printf("   Status: %s, Bateria: %d%%%n", status, bateria);
        
        // Executa diagnóstico (implementação específica de cada classe)
        boolean diagnostico = dispositivo.executarDiagnostico();
        System.out.println("   Diagnóstico: " + (diagnostico ? "✅ OK" : "❌ Problemas"));
        
        // Usa método default da interface
        if (bateria < 20) {
            dispositivo.carregarBateria(15);
        }
        
        System.out.println("✓ Processamento concluído\n");
    }
}