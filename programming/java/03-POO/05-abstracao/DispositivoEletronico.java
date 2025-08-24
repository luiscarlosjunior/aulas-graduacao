/**
 * Exemplo prático de Abstração - Classe Abstrata
 * 
 * Esta classe abstrata demonstra:
 * - Implementação parcial de uma interface
 * - Métodos concretos compartilhados entre subclasses
 * - Métodos abstratos que devem ser implementados
 * - Atributos protegidos para uso das subclasses
 * - Construtor para inicialização comum
 * - Combinação de interface e classe abstrata
 * 
 * @author Curso POO Java
 */
public abstract class DispositivoEletronico implements Dispositivo {
    
    // ===== ATRIBUTOS PROTEGIDOS =====
    // Disponíveis para as classes filhas, mas não para acesso externo
    
    protected String modelo;              // Modelo do dispositivo
    protected String numeroSerie;         // Número de série único
    protected String versaoSoftware;      // Versão atual do software
    protected String status;              // Status atual do dispositivo
    protected int nivelBateria;          // Nível da bateria (0-100)
    protected boolean conectado;          // Status de conexão de rede
    protected String redeAtual;           // Nome da rede conectada
    protected double potenciaWatts;       // Consumo de energia em watts
    protected long tempoLigado;           // Tempo ligado em milissegundos
    protected int contadorReinicio;       // Número de reinicializações
    
    // ===== CONSTRUTOR =====
    
    /**
     * Construtor da classe abstrata
     * Inicializa atributos comuns a todos os dispositivos eletrônicos
     * 
     * @param modelo Modelo do dispositivo
     * @param numeroSerie Número de série único
     * @param versaoSoftware Versão inicial do software
     * @param potenciaWatts Consumo de energia em watts
     */
    public DispositivoEletronico(String modelo, String numeroSerie, 
                                String versaoSoftware, double potenciaWatts) {
        this.modelo = modelo;
        this.numeroSerie = numeroSerie;
        this.versaoSoftware = versaoSoftware;
        this.potenciaWatts = potenciaWatts;
        
        // Inicialização padrão
        this.status = STATUS_DESLIGADO;
        this.nivelBateria = 100;  // Dispositivo novo vem com bateria cheia
        this.conectado = false;
        this.redeAtual = null;
        this.tempoLigado = 0;
        this.contadorReinicio = 0;
        
        System.out.printf("🔌 Dispositivo %s (S/N: %s) inicializado%n", modelo, numeroSerie);
    }
    
    // ===== IMPLEMENTAÇÃO DE MÉTODOS DA INTERFACE =====
    // Alguns métodos da interface Dispositivo são implementados aqui
    
    /**
     * Implementação básica do método ligar
     * Pode ser sobrescrita pelas subclasses se necessário
     */
    @Override
    public void ligar() {
        if (nivelBateria <= 0) {
            System.out.println("❌ " + modelo + " não pode ligar - bateria esgotada!");
            status = STATUS_ERRO;
            return;
        }
        
        if (!status.equals(STATUS_LIGADO)) {
            System.out.println("🔋 Ligando " + modelo + "...");
            status = STATUS_LIGADO;
            tempoLigado = System.currentTimeMillis();
            
            // Gasta um pouco de bateria ao ligar
            consumirBateria(1);
            
            System.out.println("✅ " + modelo + " ligado com sucesso!");
        } else {
            System.out.println("ℹ️ " + modelo + " já está ligado.");
        }
    }
    
    /**
     * Implementação básica do método desligar
     */
    @Override
    public void desligar() {
        if (status.equals(STATUS_LIGADO) || status.equals(STATUS_STANDBY)) {
            System.out.println("🔌 Desligando " + modelo + "...");
            
            // Calcula tempo total ligado
            if (tempoLigado > 0) {
                long tempoUso = System.currentTimeMillis() - tempoLigado;
                System.out.printf("⏱️ Tempo de uso: %.1f segundos%n", tempoUso / 1000.0);
            }
            
            desconectarRede();  // Desconecta da rede ao desligar
            status = STATUS_DESLIGADO;
            tempoLigado = 0;
            
            System.out.println("✅ " + modelo + " desligado.");
        } else {
            System.out.println("ℹ️ " + modelo + " já está desligado.");
        }
    }
    
    /**
     * Implementação do método reiniciar
     */
    @Override
    public void reiniciar() {
        System.out.println("🔄 Reiniciando " + modelo + "...");
        
        boolean estavaLigado = status.equals(STATUS_LIGADO);
        
        desligar();
        
        // Simula tempo de reinicialização
        try {
            Thread.sleep(100); // 100ms para simular processo
        } catch (InterruptedException e) {
            // Ignora interrupção
        }
        
        contadorReinicio++;
        
        if (estavaLigado && nivelBateria > 0) {
            ligar();
        }
        
        System.out.println("✅ " + modelo + " reiniciado (reinício #" + contadorReinicio + ")");
    }
    
    /**
     * Implementação do método obterStatus
     */
    @Override
    public String obterStatus() {
        return status;
    }
    
    /**
     * Implementação do método obterNivelBateria
     */
    @Override
    public int obterNivelBateria() {
        return nivelBateria;
    }
    
    /**
     * Implementação básica do diagnóstico
     */
    @Override
    public boolean executarDiagnostico() {
        System.out.println("🔍 Executando diagnóstico de " + modelo + "...");
        
        boolean resultado = true;
        
        // Verifica bateria
        if (nivelBateria < 10) {
            System.out.println("⚠️ Aviso: Bateria baixa (" + nivelBateria + "%)");
            resultado = false;
        }
        
        // Verifica reinicializações excessivas
        if (contadorReinicio > 10) {
            System.out.println("⚠️ Aviso: Muitas reinicializações (" + contadorReinicio + ")");
            resultado = false;
        }
        
        // Verifica versão do software
        if (precisaAtualizacao(versaoSoftware)) {
            System.out.println("⚠️ Aviso: Software desatualizado (" + versaoSoftware + ")");
            resultado = false;
        }
        
        System.out.println(resultado ? "✅ Diagnóstico: OK" : "❌ Diagnóstico: Problemas encontrados");
        return resultado;
    }
    
    /**
     * Implementação do método conectarRede
     */
    @Override
    public boolean conectarRede(String nomeRede, String senha) {
        if (!status.equals(STATUS_LIGADO)) {
            System.out.println("❌ " + modelo + " deve estar ligado para conectar à rede");
            return false;
        }
        
        if (!Dispositivo.validarNomeRede(nomeRede)) {
            System.out.println("❌ Nome da rede inválido: " + nomeRede);
            return false;
        }
        
        if (senha != null && !Dispositivo.senhaSegura(senha)) {
            System.out.println("⚠️ Aviso: Senha da rede não é considerada segura");
        }
        
        System.out.printf("📶 Conectando %s à rede '%s'...%n", modelo, nomeRede);
        
        // Simula processo de conexão
        try {
            Thread.sleep(50); // 50ms para simular negociação
        } catch (InterruptedException e) {
            // Ignora interrupção
        }
        
        // Simula chance de falha na conexão
        if (Math.random() < 0.1) { // 10% de chance de falha
            System.out.println("❌ Falha na conexão com " + nomeRede);
            return false;
        }
        
        conectado = true;
        redeAtual = nomeRede;
        consumirBateria(2); // Conectar gasta bateria
        
        System.out.println("✅ Conectado à rede " + nomeRede);
        return true;
    }
    
    /**
     * Implementação do método desconectarRede
     */
    @Override
    public void desconectarRede() {
        if (conectado) {
            System.out.println("📶 Desconectando de " + redeAtual + "...");
            conectado = false;
            redeAtual = null;
            System.out.println("✅ Desconectado da rede");
        }
    }
    
    /**
     * Implementação do método estaConectado
     */
    @Override
    public boolean estaConectado() {
        return conectado;
    }
    
    /**
     * Implementação dos métodos de identificação
     */
    @Override
    public String obterModelo() {
        return modelo;
    }
    
    @Override
    public String obterNumeroSerie() {
        return numeroSerie;
    }
    
    @Override
    public String obterVersaoSoftware() {
        return versaoSoftware;
    }
    
    // ===== MÉTODOS CONCRETOS ESPECÍFICOS =====
    // Métodos implementados que podem ser usados pelas subclasses
    
    /**
     * Método para consumir bateria
     * @param percentual Percentual de bateria a ser consumido
     */
    protected void consumirBateria(int percentual) {
        nivelBateria = Math.max(0, nivelBateria - percentual);
        
        if (nivelBateria <= 0) {
            System.out.println("🔋 " + modelo + " desligando - bateria esgotada!");
            status = STATUS_DESLIGADO;
        } else if (nivelBateria <= 10) {
            System.out.println("⚠️ " + modelo + " - bateria baixa: " + nivelBateria + "%");
        }
    }
    
    /**
     * Método para carregar bateria (implementação específica)
     * @param percentual Percentual a ser carregado
     */
    protected void carregarBateriaInterna(int percentual) {
        int nivelAnterior = nivelBateria;
        nivelBateria = Math.min(100, nivelBateria + percentual);
        
        System.out.printf("🔋 %s: %d%% → %d%%%n", modelo, nivelAnterior, nivelBateria);
    }
    
    /**
     * Método para simular uso do dispositivo
     * @param minutos Minutos de uso
     */
    public void simularUso(int minutos) {
        if (!status.equals(STATUS_LIGADO)) {
            System.out.println("ℹ️ " + modelo + " deve estar ligado para simular uso");
            return;
        }
        
        System.out.printf("🔄 Simulando %d minutos de uso de %s...%n", minutos, modelo);
        
        // Consome bateria baseado no tempo de uso e potência
        int consumo = Math.max(1, (int) (minutos * potenciaWatts / 100));
        consumirBateria(consumo);
        
        System.out.printf("✅ Uso simulado concluído. Bateria: %d%%%n", nivelBateria);
    }
    
    /**
     * Método para obter estatísticas de uso
     */
    public void exibirEstatisticas() {
        System.out.println("\n=== Estatísticas de Uso ===");
        System.out.println("Modelo: " + modelo);
        System.out.println("Número de série: " + numeroSerie);
        System.out.println("Status atual: " + status);
        System.out.println("Nível de bateria: " + nivelBateria + "%");
        System.out.println("Reinicializações: " + contadorReinicio);
        System.out.println("Rede atual: " + (redeAtual != null ? redeAtual : "Nenhuma"));
        System.out.println("Potência: " + potenciaWatts + "W");
        System.out.printf("Consumo mensal estimado: %.2f kWh%n", 
                         Dispositivo.calcularConsumoMensal(potenciaWatts, 8));
        System.out.println("========================\n");
    }
    
    // ===== MÉTODOS ABSTRATOS =====
    // Estes métodos devem ser implementados pelas classes filhas
    
    /**
     * Método abstrato para atualizar software
     * Cada tipo de dispositivo tem seu próprio processo de atualização
     * 
     * @param versao Nova versão do software
     * @return true se a atualização foi bem-sucedida
     */
    @Override
    public abstract boolean atualizarSoftware(String versao);
    
    /**
     * Método abstrato para funcionalidade específica do dispositivo
     * Cada dispositivo tem uma função principal diferente
     */
    public abstract void executarFuncaoPrincipal();
    
    /**
     * Método abstrato para configurar dispositivo
     * Cada tipo pode ter configurações específicas
     */
    public abstract void configurarDispositivo();
    
    /**
     * Override do toString para informações básicas
     */
    @Override
    public String toString() {
        return String.format("%s{modelo='%s', status='%s', bateria=%d%%, conectado=%s}", 
                           getClass().getSimpleName(), modelo, status, nivelBateria, conectado);
    }
}