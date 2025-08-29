/**
 * Exemplo Clássico: Carro (classe filha de Veiculo)
 * 
 * Demonstra herança especializada para automóveis com:
 * - Características específicas (número de portas, tipo de transmissão)
 * - Comportamentos específicos (ré, pisca-alerta, ar-condicionado)
 * - Sobrescrita de métodos da classe pai
 * 
 * @author Curso POO Java - Exemplo Clássico
 */
public class Carro extends Veiculo {
    
    // ===== ATRIBUTOS ESPECÍFICOS DO CARRO =====
    private int numeroPortas;           // Número de portas (2, 4, 5)
    private String tipoTransmissao;     // Manual ou Automática
    private boolean arCondicionado;     // Se possui ar condicionado
    private boolean airbag;             // Se possui airbag
    private boolean gps;                // Se possui GPS
    private double tamanhoPortaMalas;   // Capacidade do porta-malas (litros)
    private boolean pistasAlerta;       // Status do pisca-alerta
    private int marcha;                 // Marcha atual (-1=ré, 0=neutro, 1-5=marchas)
    
    // ===== CONSTRUTOR =====
    
    /**
     * Construtor específico para carro
     */
    public Carro(String marca, String modelo, int ano, String cor, double preco,
                 double velocidadeMaxima, int numeroPortas, String tipoTransmissao,
                 boolean arCondicionado, double tamanhoPortaMalas) {
        
        // Chama construtor da classe pai (Veiculo)
        // Carros sempre têm 4 rodas
        super(marca, modelo, ano, cor, preco, 4, velocidadeMaxima);
        
        // Inicializa atributos específicos do carro
        this.numeroPortas = numeroPortas;
        this.tipoTransmissao = tipoTransmissao;
        this.arCondicionado = arCondicionado;
        this.tamanhoPortaMalas = tamanhoPortaMalas;
        
        // Estado inicial
        this.airbag = true;  // Todos os carros modernos têm airbag
        this.gps = ano >= 2015;  // Carros de 2015+ têm GPS
        this.pistasAlerta = false;
        this.marcha = 0;  // Inicia em neutro
        
        System.out.println("🚗 Carro " + numeroPortas + " portas, " + 
                          tipoTransmissao.toLowerCase() + " criado");
    }
    
    // ===== SOBRESCRITA DE MÉTODOS DA CLASSE PAI =====
    
    /**
     * Sobrescreve o método ligar() para incluir verificações específicas do carro
     */
    @Override
    public void ligar() {
        if (!ligado) {
            System.out.println("🔑 Inserindo chave no carro " + marca + " " + modelo + "...");
            
            // Verificações específicas do carro
            if (marcha != 0) {
                System.out.println("⚠️ Colocando em neutro para ligar...");
                marcha = 0;
            }
            
            // Chama método da classe pai
            super.ligar();
            
            if (ligado) {
                System.out.println("📊 Painel do carro aceso - sistemas verificados");
                if (gps) {
                    System.out.println("🛰️ GPS inicializado");
                }
            }
        }
    }
    
    /**
     * Implementação obrigatória do método abstrato buzinar()
     */
    @Override
    public void buzinar() {
        if (ligado) {
            System.out.println("🚗 " + marca + " " + modelo + ": BEEP BEEP!");
        } else {
            System.out.println("❌ Ligue o carro para buzinar!");
        }
    }
    
    /**
     * Implementação obrigatória do método abstrato getTipoCombustivel()
     */
    @Override
    public String getTipoCombustivel() {
        // A maioria dos carros usa gasolina ou flex
        return "Gasolina/Etanol";
    }
    
    /**
     * Sobrescreve acelerar para incluir sistema de marchas
     */
    @Override
    public void acelerar(double incremento) {
        if (!verificarCondicoesDirecao()) return;
        
        // Se está em neutro ou ré, precisa engatar marcha
        if (marcha <= 0) {
            engatar(1);
        }
        
        // Verifica se precisa trocar marcha automaticamente
        if (tipoTransmissao.equals("Automática")) {
            trocarMarchaAutomatica();
        }
        
        // Chama método da classe pai
        super.acelerar(incremento);
        
        // Feedback específico do carro
        if (velocidadeAtual > 80) {
            System.out.println("⚠️ Alta velocidade - dirija com cuidado!");
        }
    }
    
    // ===== MÉTODOS ESPECÍFICOS DO CARRO =====
    
    /**
     * Engatar uma marcha específica
     */
    public void engatar(int novaMarcha) {
        if (!ligado) {
            System.out.println("❌ Ligue o carro para engatar marcha!");
            return;
        }
        
        if (novaMarcha < -1 || novaMarcha > 5) {
            System.out.println("❌ Marcha inválida! Use -1 (ré) a 5 (quinta)");
            return;
        }
        
        // Para engatar ré, deve estar parado
        if (novaMarcha == -1 && velocidadeAtual > 0) {
            System.out.println("❌ Pare o carro para engatar ré!");
            return;
        }
        
        marcha = novaMarcha;
        String nomeMarcha = switch(marcha) {
            case -1 -> "Ré";
            case 0 -> "Neutro";
            case 1 -> "Primeira";
            case 2 -> "Segunda";
            case 3 -> "Terceira";
            case 4 -> "Quarta";
            case 5 -> "Quinta";
            default -> "Desconhecida";
        };
        
        System.out.println("⚙️ Marcha engatada: " + nomeMarcha);
    }
    
    /**
     * Sistema automático de troca de marchas
     */
    private void trocarMarchaAutomatica() {
        int marchaIdeal = switch((int)(velocidadeAtual / 20)) {
            case 0 -> velocidadeAtual > 0 ? 1 : 0;
            case 1 -> 2;
            case 2 -> 3;
            case 3 -> 4;
            default -> 5;
        };
        
        if (marchaIdeal != marcha && marchaIdeal > 0) {
            engatar(marchaIdeal);
        }
    }
    
    /**
     * Fazer ré (movimento específico do carro)
     */
    public void fazerRe(double distancia) {
        if (!verificarCondicoesDirecao()) return;
        
        if (velocidadeAtual > 0) {
            System.out.println("🛑 Parando para fazer ré...");
            frear(velocidadeAtual);
        }
        
        engatar(-1);
        System.out.println("↩️ Fazendo ré por " + distancia + " metros...");
        System.out.println("📹 Câmera de ré ativada (se disponível)");
        
        // Simula movimento em ré
        quilometragem += distancia / 1000; // converte metros para km
        consumirCombustivel(distancia * 0.01);
    }
    
    /**
     * Ativar/desativar pisca-alerta
     */
    public void piscarAlerta() {
        pistasAlerta = !pistasAlerta;
        System.out.println("🚨 Pisca-alerta " + (pistasAlerta ? "LIGADO" : "desligado"));
    }
    
    /**
     * Usar ar condicionado
     */
    public void ligarArCondicionado() {
        if (!ligado) {
            System.out.println("❌ Ligue o carro para usar o ar condicionado!");
            return;
        }
        
        if (!arCondicionado) {
            System.out.println("❌ Este carro não possui ar condicionado!");
            return;
        }
        
        System.out.println("❄️ Ar condicionado ligado - temperatura agradável!");
        // Ar condicionado consome mais combustível
        consumirCombustivel(2.0);
    }
    
    /**
     * Estacionar o carro
     */
    public void estacionar() {
        if (velocidadeAtual > 0) {
            System.out.println("🛑 Parando o carro...");
            frear(velocidadeAtual);
        }
        
        engatar(0); // Neutro
        
        if (Math.random() > 0.5) { // 50% de chance de ser vaga apertada
            System.out.println("🅿️ Estacionando em vaga simples...");
        } else {
            System.out.println("🅿️ Vaga apertada - usando sensores de estacionamento...");
            fazerRe(2.0);
            acelerar(1.0);
            frear(1.0);
        }
        
        System.out.println("✅ Carro estacionado com sucesso!");
    }
    
    /**
     * Abrir porta-malas
     */
    public void abrirPortaMalas() {
        System.out.printf("📦 Porta-malas aberto - capacidade: %.0f litros\n", tamanhoPortaMalas);
    }
    
    // ===== GETTERS ESPECÍFICOS =====
    
    public int getNumeroPortas() { return numeroPortas; }
    public String getTipoTransmissao() { return tipoTransmissao; }
    public boolean temArCondicionado() { return arCondicionado; }
    public boolean temAirbag() { return airbag; }
    public boolean temGPS() { return gps; }
    public double getTamanhoPortaMalas() { return tamanhoPortaMalas; }
    public int getMarcha() { return marcha; }
    
    /**
     * Sobrescreve exibirInformacoes para incluir dados específicos do carro
     */
    @Override
    public void exibirInformacoes() {
        // Chama método da classe pai
        super.exibirInformacoes();
        
        // Adiciona informações específicas do carro
        System.out.println("=== Informações Específicas do Carro ===");
        System.out.println("Portas: " + numeroPortas);
        System.out.println("Transmissão: " + tipoTransmissao);
        System.out.println("Ar condicionado: " + (arCondicionado ? "Sim" : "Não"));
        System.out.println("Airbag: " + (airbag ? "Sim" : "Não"));
        System.out.println("GPS: " + (gps ? "Sim" : "Não"));
        System.out.printf("Porta-malas: %.0f litros\n", tamanhoPortaMalas);
        System.out.println("Marcha atual: " + marcha);
        System.out.println("=========================================\n");
    }
    
    /**
     * Sobrescreve toString para formato específico do carro
     */
    @Override
    public String toString() {
        return String.format("Carro{%s %s (%d), %dP, %s, %.1f km/h, marcha %d}", 
                           marca, modelo, ano, numeroPortas, tipoTransmissao, 
                           velocidadeAtual, marcha);
    }
    
    /**
     * Método para demonstrar segurança do carro
     */
    public void testarSeguranca() {
        System.out.println("\n🛡️ Testando sistemas de segurança:");
        System.out.println("   Airbag: " + (airbag ? "✅ Funcional" : "❌ Não disponível"));
        System.out.println("   ABS: ✅ Funcional");
        System.out.println("   Freios: ✅ Funcionais");
        System.out.println("   Cinto de segurança: ✅ Obrigatório");
        if (gps) {
            System.out.println("   Rastreamento: ✅ GPS ativo");
        }
        piscarAlerta();
        System.out.println("🛡️ Teste de segurança concluído!\n");
    }
}