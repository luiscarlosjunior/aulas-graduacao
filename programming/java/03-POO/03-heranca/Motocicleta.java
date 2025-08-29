/**
 * Exemplo Clássico: Motocicleta (classe filha de Veiculo)
 * 
 * Demonstra herança com características bem diferentes do carro:
 * - Apenas 2 rodas (vs 4 do carro)
 * - Comportamentos específicos (empinar, capacete)
 * - Diferentes validações de segurança
 * 
 * @author Curso POO Java - Exemplo Clássico
 */
public class Motocicleta extends Veiculo {
    
    // ===== ATRIBUTOS ESPECÍFICOS DA MOTOCICLETA =====
    private int cilindradas;            // Potência do motor (125cc, 250cc, etc.)
    private String tipoGuidao;          // Tipo do guidão (esportivo, comum, chopper)
    private boolean bau;                // Se possui baú
    private boolean capaceteObrigatorio; // Capacete é obrigatório
    private boolean partidaEletrica;    // Se tem partida elétrica
    private boolean freioABS;           // Se possui freio ABS
    private double alturaSela;          // Altura da sela em cm
    private boolean empinando;          // Status: está empinando?
    
    // ===== CONSTRUTOR =====
    
    /**
     * Construtor específico para motocicleta
     */
    public Motocicleta(String marca, String modelo, int ano, String cor, double preco,
                       double velocidadeMaxima, int cilindradas, String tipoGuidao,
                       boolean bau, double alturaSela) {
        
        // Chama construtor da classe pai (Veiculo)
        // Motocicletas sempre têm 2 rodas
        super(marca, modelo, ano, cor, preco, 2, velocidadeMaxima);
        
        // Inicializa atributos específicos da motocicleta
        this.cilindradas = cilindradas;
        this.tipoGuidao = tipoGuidao;
        this.bau = bau;
        this.alturaSela = alturaSela;
        
        // Configurações padrão baseadas no ano e cilindradas
        this.capaceteObrigatorio = true; // Sempre obrigatório no Brasil
        this.partidaEletrica = ano >= 2010; // Motos mais novas têm partida elétrica
        this.freioABS = cilindradas > 300; // Motos maiores geralmente têm ABS
        this.empinando = false;
        
        System.out.println("🏍️ Motocicleta " + cilindradas + "cc, guidão " + 
                          tipoGuidao.toLowerCase() + " criada");
    }
    
    // ===== SOBRESCRITA DE MÉTODOS DA CLASSE PAI =====
    
    /**
     * Sobrescreve o método ligar() para incluir verificações específicas da moto
     */
    @Override
    public void ligar() {
        if (!ligado) {
            if (!capaceteObrigatorio) {
                System.out.println("⚠️ ATENÇÃO: Capacete é obrigatório!");
                System.out.println("⛑️ Colocando capacete de segurança...");
            }
            
            System.out.println("🔑 Ligando motocicleta " + marca + " " + modelo + "...");
            
            if (partidaEletrica) {
                System.out.println("⚡ Usando partida elétrica...");
            } else {
                System.out.println("🦵 Usando partida a pedal...");
            }
            
            // Chama método da classe pai
            super.ligar();
            
            if (ligado) {
                System.out.println("📊 Painel da moto aceso");
                System.out.println("🌡️ Aquecendo motor...");
            }
        }
    }
    
    /**
     * Implementação obrigatória do método abstrato buzinar()
     */
    @Override
    public void buzinar() {
        if (ligado) {
            System.out.println("🏍️ " + marca + " " + modelo + ": BIP BIP!");
        } else {
            System.out.println("❌ Ligue a moto para buzinar!");
        }
    }
    
    /**
     * Implementação obrigatória do método abstrato getTipoCombustivel()
     */
    @Override
    public String getTipoCombustivel() {
        // Motos menores usam gasolina comum, maiores podem usar premium
        return cilindradas > 250 ? "Gasolina Premium" : "Gasolina Comum";
    }
    
    /**
     * Sobrescreve acelerar para incluir comportamentos específicos da moto
     */
    @Override
    public void acelerar(double incremento) {
        if (!verificarCondicoesDirecao()) return;
        
        if (empinando) {
            System.out.println("⚠️ Não é seguro acelerar empinando! Descendo a roda...");
            descerRoda();
        }
        
        // Motos aceleram mais rápido que carros
        double incrementoMoto = incremento * 1.3; // 30% mais rápido
        
        // Chama método da classe pai com incremento ajustado
        super.acelerar(incrementoMoto);
        
        // Feedback específico da moto
        if (velocidadeAtual > 100) {
            System.out.println("💨 Velocidade alta - cuidado com a estabilidade!");
        }
        
        // Som específico do motor da moto
        if (velocidadeAtual > 50) {
            System.out.println("🏍️ VRUMMMMM!");
        }
    }
    
    /**
     * Sobrescreve frear para incluir características da moto
     */
    @Override
    public void frear(double decremento) {
        if (empinando) {
            System.out.println("⚠️ Freando empinando é perigoso! Descendo a roda primeiro...");
            descerRoda();
        }
        
        if (freioABS && velocidadeAtual > 60) {
            System.out.println("🛑 ABS ativado - frenagem segura!");
        }
        
        // Chama método da classe pai
        super.frear(decremento);
    }
    
    // ===== MÉTODOS ESPECÍFICOS DA MOTOCICLETA =====
    
    /**
     * Empinar a motocicleta (wheeling)
     */
    public void empinar() {
        if (!verificarCondicoesDirecao()) return;
        
        if (velocidadeAtual < 20) {
            System.out.println("❌ Velocidade muito baixa para empinar!");
            return;
        }
        
        if (velocidadeAtual > 80) {
            System.out.println("❌ Velocidade muito alta - perigoso empinar!");
            return;
        }
        
        if (cilindradas < 150) {
            System.out.println("❌ Cilindrada insuficiente para empinar!");
            return;
        }
        
        empinando = true;
        System.out.println("🏍️ EMPINANDO! ⬆️ Roda dianteira no ar!");
        System.out.println("⚠️ CUIDADO: Manobra perigosa!");
        
        // Empinar consome mais combustível
        consumirCombustivel(5.0);
    }
    
    /**
     * Descer a roda (parar de empinar)
     */
    public void descerRoda() {
        if (empinando) {
            empinando = false;
            System.out.println("⬇️ Roda dianteira voltou ao chão - manobra segura!");
        } else {
            System.out.println("ℹ️ A moto já está com as duas rodas no chão.");
        }
    }
    
    /**
     * Fazer curva (comportamento específico de moto)
     */
    public void fazerCurva(String direcao) {
        if (!verificarCondicoesDirecao()) return;
        
        if (empinando) {
            System.out.println("❌ Impossível fazer curva empinando!");
            return;
        }
        
        if (velocidadeAtual > 60) {
            System.out.println("⚠️ Reduzindo velocidade para curva segura...");
            frear(20);
        }
        
        System.out.println("↪️ Fazendo curva para a " + direcao.toLowerCase());
        System.out.println("🏍️ Inclinando a moto na curva...");
        
        // Simula gasto extra de combustível na curva
        consumirCombustivel(1.0);
    }
    
    /**
     * Usar baú da moto (se disponível)
     */
    public void abrirBau() {
        if (!bau) {
            System.out.println("❌ Esta moto não possui baú!");
            return;
        }
        
        System.out.println("📦 Baú da moto aberto - capacidade limitada");
        System.out.println("💡 Dica: Distribua o peso uniformemente");
    }
    
    /**
     * Verificar equipamentos de segurança
     */
    public void verificarSeguranca() {
        System.out.println("\n🛡️ Verificando equipamentos de segurança:");
        System.out.println("   ⛑️ Capacete: " + (capaceteObrigatorio ? "✅ Obrigatório" : "❌ Não usado"));
        System.out.println("   🦺 Colete refletivo: ✅ Recomendado");
        System.out.println("   👖 Calça comprida: ✅ Recomendado");
        System.out.println("   👢 Calçado fechado: ✅ Obrigatório");
        System.out.println("   🧤 Luvas: ✅ Recomendado");
        System.out.println("   💡 Farol aceso: ✅ Obrigatório (dia e noite)");
        
        if (freioABS) {
            System.out.println("   🛑 Freio ABS: ✅ Disponível");
        }
        
        System.out.println("🛡️ Verificação de segurança concluída!\n");
    }
    
    /**
     * Pilotar em grupo (motociclismo)
     */
    public void pilotarEmGrupo(int numeroMotos) {
        if (!verificarCondicoesDirecao()) return;
        
        System.out.println("👥 Pilotando em grupo com " + numeroMotos + " motos");
        System.out.println("📏 Mantendo distância segura...");
        System.out.println("📶 Usando sinais de comunicação...");
        
        if (numeroMotos > 5) {
            System.out.println("⚠️ Grupo grande - atenção redobrada!");
        }
        
        buzinar(); // Sinaliza para o grupo
    }
    
    /**
     * Fazer manutenção específica de moto
     */
    public void fazerManutencao() {
        System.out.println("\n🔧 Realizando manutenção da motocicleta:");
        System.out.println("   🛞 Verificando pressão dos pneus...");
        System.out.println("   ⛓️ Lubrificando corrente...");
        System.out.println("   🔩 Ajustando tensão da corrente...");
        System.out.println("   🛢️ Verificando nível do óleo...");
        System.out.println("   🔌 Testando vela de ignição...");
        System.out.println("   ⚡ Verificando bateria...");
        
        if (partidaEletrica) {
            System.out.println("   🔌 Testando partida elétrica...");
        }
        
        System.out.println("✅ Manutenção concluída - moto pronta para rodar!\n");
    }
    
    // ===== GETTERS ESPECÍFICOS =====
    
    public int getCilindradas() { return cilindradas; }
    public String getTipoGuidao() { return tipoGuidao; }
    public boolean temBau() { return bau; }
    public boolean temPartidaEletrica() { return partidaEletrica; }
    public boolean temFreioABS() { return freioABS; }
    public double getAlturaSela() { return alturaSela; }
    public boolean isEmpinando() { return empinando; }
    
    /**
     * Sobrescreve exibirInformacoes para incluir dados específicos da moto
     */
    @Override
    public void exibirInformacoes() {
        // Chama método da classe pai
        super.exibirInformacoes();
        
        // Adiciona informações específicas da motocicleta
        System.out.println("=== Informações Específicas da Motocicleta ===");
        System.out.println("Cilindradas: " + cilindradas + "cc");
        System.out.println("Tipo de guidão: " + tipoGuidao);
        System.out.println("Baú: " + (bau ? "Sim" : "Não"));
        System.out.println("Partida elétrica: " + (partidaEletrica ? "Sim" : "Não"));
        System.out.println("Freio ABS: " + (freioABS ? "Sim" : "Não"));
        System.out.printf("Altura da sela: %.1f cm\n", alturaSela);
        System.out.println("Status: " + (empinando ? "Empinando ⬆️" : "Normal"));
        System.out.println("===============================================\n");
    }
    
    /**
     * Sobrescreve toString para formato específico da moto
     */
    @Override
    public String toString() {
        return String.format("Motocicleta{%s %s (%d), %dcc, %.1f km/h%s}", 
                           marca, modelo, ano, cilindradas, velocidadeAtual,
                           empinando ? " (empinando)" : "");
    }
    
    /**
     * Método para demonstrar agilidade da moto
     */
    public void demonstrarAgilidade() {
        System.out.println("\n🏍️ Demonstrando agilidade da motocicleta:");
        fazerCurva("direita");
        acelerar(20);
        fazerCurva("esquerda");
        frear(15);
        
        if (cilindradas > 200) {
            empinar();
            try { Thread.sleep(1000); } catch (InterruptedException e) {}
            descerRoda();
        }
        
        buzinar();
        System.out.println("🏆 Demonstração de agilidade concluída!\n");
    }
}