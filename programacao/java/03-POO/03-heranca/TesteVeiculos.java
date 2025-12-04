/**
 * Teste Completo da Hierarquia de Veículos - Exemplo Clássico da Literatura POO
 * 
 * Este teste demonstra todos os conceitos de herança usando a hierarquia
 * Veiculo → Carro/Motocicleta, que é um dos exemplos mais famosos da POO.
 * 
 * Conceitos demonstrados:
 * 1. Herança de atributos e métodos
 * 2. Sobrescrita de métodos (@Override)
 * 3. Métodos abstratos e implementação obrigatória
 * 4. Polimorfismo básico (referência pai → objeto filho)
 * 5. Comportamentos específicos de cada classe
 * 6. Uso da palavra-chave super
 * 
 * @author Curso POO Java - Exemplo Clássico
 */
public class TesteVeiculos {
    
    public static void main(String[] args) {
        System.out.println("=== DEMONSTRAÇÃO: HIERARQUIA DE VEÍCULOS ===");
        System.out.println("📚 Exemplo clássico da literatura de POO\n");
        
        // ===== 1. CRIAÇÃO DE VEÍCULOS =====
        System.out.println("1️⃣ CRIANDO VEÍCULOS ESPECÍFICOS\n");
        
        // Criando um carro
        Carro carro = new Carro(
            "Toyota", "Corolla", 2022, "Prata",
            89000.0, 180.0, 4, "Automática", 
            true, 470.0
        );
        
        // Criando uma motocicleta
        Motocicleta moto = new Motocicleta(
            "Honda", "CB 600F", 2021, "Azul",
            35000.0, 220.0, 600, "Esportivo",
            true, 81.0
        );
        
        // ===== 2. DEMONSTRANDO HERANÇA DE MÉTODOS =====
        System.out.println("\n2️⃣ USANDO MÉTODOS HERDADOS DA CLASSE PAI\n");
        
        System.out.println("--- Ligando veículos ---");
        carro.ligar();
        moto.ligar();
        
        System.out.println("\n--- Abastecendo veículos ---");
        carro.abastecer(30.0);
        moto.abastecer(15.0);
        
        // ===== 3. DEMONSTRANDO MÉTODOS SOBRESCRITOS =====
        System.out.println("\n3️⃣ MÉTODOS SOBRESCRITOS (COMPORTAMENTOS ESPECÍFICOS)\n");
        
        System.out.println("--- Cada veículo buzina diferente ---");
        carro.buzinar();
        moto.buzinar();
        
        System.out.println("\n--- Tipos de combustível específicos ---");
        System.out.println("Carro usa: " + carro.getTipoCombustivel());
        System.out.println("Moto usa: " + moto.getTipoCombustivel());
        
        // ===== 4. DEMONSTRANDO ACELERAÇÃO E MOVIMENTO =====
        System.out.println("\n4️⃣ MOVIMENTO E ACELERAÇÃO\n");
        
        System.out.println("--- Acelerando veículos ---");
        carro.acelerar(40.0);
        carro.acelerar(30.0);
        
        moto.acelerar(50.0);
        moto.acelerar(40.0);
        
        System.out.println("\n--- Velocidades atuais ---");
        System.out.printf("Carro: %.1f km/h\n", carro.getVelocidadeAtual());
        System.out.printf("Moto: %.1f km/h\n", moto.getVelocidadeAtual());
        
        // ===== 5. COMPORTAMENTOS ESPECÍFICOS DE CADA CLASSE =====
        System.out.println("\n5️⃣ COMPORTAMENTOS ESPECÍFICOS\n");
        
        System.out.println("--- Funcionalidades específicas do CARRO ---");
        carro.estacionar();
        carro.abrirPortaMalas();
        carro.ligarArCondicionado();
        carro.testarSeguranca();
        
        System.out.println("--- Funcionalidades específicas da MOTO ---");
        moto.empinar();
        moto.descerRoda();
        moto.fazerCurva("direita");
        moto.abrirBau();
        moto.verificarSeguranca();
        
        // ===== 6. POLIMORFISMO COM ARRAY DE VEÍCULOS =====
        System.out.println("\n6️⃣ POLIMORFISMO - ARRAY DE VEÍCULOS\n");
        
        // Array polimórfico - referência pai, objetos filhos
        Veiculo[] veiculos = {
            new Carro("Ford", "Fiesta", 2020, "Vermelho", 65000.0, 170.0, 4, "Manual", false, 290.0),
            new Motocicleta("Yamaha", "MT-03", 2022, "Preto", 28000.0, 180.0, 321, "Naked", false, 78.0),
            carro,  // Reutilizando o carro criado antes
            moto    // Reutilizando a moto criada antes
        };
        
        System.out.println("--- Processando todos os veículos polimorficamente ---");
        for (int i = 0; i < veiculos.length; i++) {
            Veiculo v = veiculos[i];
            System.out.println("\nVeículo " + (i + 1) + ": " + v.getMarca() + " " + v.getModelo());
            
            // Métodos comuns (funcionam para todos)
            if (!v.isLigado()) {
                v.ligar();
            }
            v.buzinar();
            v.acelerar(20.0);
            
            // Polimorfismo - método abstrato implementado diferentemente
            System.out.println("Combustível: " + v.getTipoCombustivel());
            System.out.printf("Velocidade: %.1f km/h\n", v.getVelocidadeAtual());
        }
        
        // ===== 7. CASTING E VERIFICAÇÃO DE TIPOS =====
        System.out.println("\n7️⃣ CASTING E INSTANCEOF\n");
        
        for (Veiculo v : veiculos) {
            System.out.println("\nAnalisando: " + v.getMarca() + " " + v.getModelo());
            
            if (v instanceof Carro) {
                System.out.println("✓ É um Carro!");
                Carro c = (Carro) v;  // Cast seguro
                System.out.println("  Portas: " + c.getNumeroPortas());
                System.out.println("  Transmissão: " + c.getTipoTransmissao());
                
                // Método específico do carro
                c.fazerRe(3.0);
                
            } else if (v instanceof Motocicleta) {
                System.out.println("✓ É uma Motocicleta!");
                Motocicleta m = (Motocicleta) v;  // Cast seguro
                System.out.println("  Cilindradas: " + m.getCilindradas() + "cc");
                System.out.println("  Guidão: " + m.getTipoGuidao());
                
                // Método específico da moto
                if (m.getCilindradas() > 300 && m.getVelocidadeAtual() > 30) {
                    m.empinar();
                    try { Thread.sleep(500); } catch (InterruptedException e) {}
                    m.descerRoda();
                }
            }
        }
        
        // ===== 8. DEMONSTRAÇÃO DE HERANÇA MULTINÍVEL =====
        System.out.println("\n8️⃣ COMPARANDO COMPORTAMENTOS HERDADOS VS ESPECÍFICOS\n");
        
        System.out.println("--- Métodos comuns (herdados) ---");
        for (Veiculo v : veiculos) {
            System.out.printf("%-20s - Rodas: %d, Velocidade máx: %.0f km/h\n", 
                            v.getMarca() + " " + v.getModelo(), 
                            v.getNumeroRodas(), v.getVelocidadeMaxima());
        }
        
        System.out.println("\n--- Métodos específicos (sobrescritos) ---");
        for (Veiculo v : veiculos) {
            System.out.print(v.getMarca() + " " + v.getModelo() + ": ");
            v.buzinar();  // Cada um buzina diferente
        }
        
        // ===== 9. INFORMAÇÕES DETALHADAS =====
        System.out.println("\n9️⃣ INFORMAÇÕES DETALHADAS DOS VEÍCULOS\n");
        
        carro.exibirInformacoes();
        moto.exibirInformacoes();
        
        // ===== 10. SIMULAÇÃO DE USO REAL =====
        System.out.println("\n🔟 SIMULAÇÃO DE USO REAL\n");
        
        System.out.println("--- Viagem de carro (família) ---");
        carro.acelerar(20.0);
        carro.ligarArCondicionado();
        carro.acelerar(40.0);
        System.out.println("🚗 Chegando ao destino...");
        carro.frear(carro.getVelocidadeAtual());
        carro.estacionar();
        
        System.out.println("\n--- Passeio de moto (esportivo) ---");
        moto.acelerar(60.0);
        moto.demonstrarAgilidade();
        moto.pilotarEmGrupo(3);
        System.out.println("🏍️ Chegando ao encontro...");
        moto.frear(moto.getVelocidadeAtual());
        
        // ===== 11. MANUTENÇÃO E CUIDADOS =====
        System.out.println("\n1️⃣1️⃣ MANUTENÇÃO DOS VEÍCULOS\n");
        
        moto.fazerManutencao();
        
        System.out.println("🔧 Manutenção do carro (simulada):");
        System.out.println("   🛞 Calibrando pneus...");
        System.out.println("   🛢️ Trocando óleo...");
        System.out.println("   🔋 Testando bateria...");
        System.out.println("✅ Manutenção do carro concluída!\n");
        
        // ===== 12. FINALIZANDO =====
        System.out.println("1️⃣2️⃣ FINALIZANDO DEMONSTRAÇÃO\n");
        
        System.out.println("--- Desligando todos os veículos ---");
        for (Veiculo v : veiculos) {
            if (v.isLigado()) {
                v.desligar();
            }
        }
        
        // ===== RESUMO CONCEITUAL =====
        System.out.println("\n🎯 RESUMO DOS CONCEITOS DEMONSTRADOS\n");
        System.out.println("✅ Herança demonstrada com sucesso:");
        System.out.println("   1. Reutilização de código (classe Veiculo → Carro/Moto)");
        System.out.println("   2. Sobrescrita de métodos (@Override)");
        System.out.println("   3. Métodos abstratos obrigatórios");
        System.out.println("   4. Polimorfismo com arrays");
        System.out.println("   5. Casting seguro com instanceof");
        System.out.println("   6. Comportamentos específicos por tipo");
        System.out.println("   7. Hierarquia lógica e intuitiva");
        
        System.out.println("\n📚 Este é um dos exemplos mais clássicos da POO!");
        System.out.println("📖 Presente em praticamente todos os livros de programação OO");
        System.out.println("🏆 Demonstra perfeitamente o poder da herança");
        
        System.out.println("\n✅ Demonstração da hierarquia de veículos concluída!");
    }
}