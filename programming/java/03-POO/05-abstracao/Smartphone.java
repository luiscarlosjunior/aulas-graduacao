/**
 * Exemplo prático de Abstração - Classe Concreta
 * 
 * Esta classe demonstra:
 * - Implementação completa de uma classe abstrata
 * - Implementação dos métodos abstratos obrigatórios
 * - Sobrescrita de métodos para comportamento específico
 * - Adição de funcionalidades específicas de smartphone
 * - Múltiplas interfaces (se necessário)
 * 
 * @author Curso POO Java
 */
public class Smartphone extends DispositivoEletronico {
    
    // ===== ATRIBUTOS ESPECÍFICOS DO SMARTPHONE =====
    
    private String operadora;           // Operadora de telefonia
    private String numeroTelefone;      // Número de telefone
    private double tamanhoTela;         // Tamanho da tela em polegadas
    private int memoriaRAM;             // Memória RAM em GB
    private int armazenamento;          // Armazenamento interno em GB
    private boolean modoSilencioso;     // Se está em modo silencioso
    private boolean gpsAtivo;           // Se o GPS está ativo
    private boolean bluetoothAtivo;     // Se o Bluetooth está ativo
    private int contatosArmazenados;    // Número de contatos salvos
    private int aplicativosInstalados;  // Número de apps instalados
    
    // ===== CONSTRUTOR =====
    
    /**
     * Construtor do Smartphone
     * 
     * @param modelo Modelo do smartphone
     * @param numeroSerie Número de série
     * @param versaoSoftware Versão do sistema operacional
     * @param operadora Operadora de telefonia
     * @param numeroTelefone Número de telefone
     * @param tamanhoTela Tamanho da tela em polegadas
     * @param memoriaRAM Memória RAM em GB
     * @param armazenamento Armazenamento interno em GB
     */
    public Smartphone(String modelo, String numeroSerie, String versaoSoftware,
                     String operadora, String numeroTelefone, double tamanhoTela,
                     int memoriaRAM, int armazenamento) {
        
        // Chama construtor da classe pai
        super(modelo, numeroSerie, versaoSoftware, 15.0); // Smartphone consome ~15W
        
        this.operadora = operadora;
        this.numeroTelefone = numeroTelefone;
        this.tamanhoTela = tamanhoTela;
        this.memoriaRAM = memoriaRAM;
        this.armazenamento = armazenamento;
        
        // Inicialização padrão
        this.modoSilencioso = false;
        this.gpsAtivo = false;
        this.bluetoothAtivo = false;
        this.contatosArmazenados = 0;
        this.aplicativosInstalados = 10; // Apps básicos pré-instalados
        
        System.out.printf("📱 Smartphone %s configurado para %s (%s)%n", 
                         modelo, operadora, numeroTelefone);
    }
    
    // ===== IMPLEMENTAÇÃO DOS MÉTODOS ABSTRATOS =====
    
    /**
     * Implementação do método atualizarSoftware específico para smartphone
     */
    @Override
    public boolean atualizarSoftware(String versao) {
        System.out.printf("📱 Iniciando atualização do %s para versão %s...%n", modelo, versao);
        
        // Verifica se o dispositivo está ligado
        if (!status.equals(STATUS_LIGADO)) {
            System.out.println("❌ Smartphone deve estar ligado para atualizar");
            return false;
        }
        
        // Verifica se tem bateria suficiente
        if (nivelBateria < 50) {
            System.out.println("❌ Bateria insuficiente para atualização (mínimo 50%)");
            return false;
        }
        
        // Verifica se está conectado à rede
        if (!conectado) {
            System.out.println("❌ Conexão de rede necessária para atualização");
            return false;
        }
        
        // Simula processo de atualização
        System.out.println("📥 Baixando atualização...");
        consumirBateria(10); // Download consome bateria
        
        try {
            Thread.sleep(200); // Simula tempo de download
        } catch (InterruptedException e) {
            return false;
        }
        
        System.out.println("⚙️ Instalando atualização...");
        consumirBateria(15); // Instalação consome mais bateria
        
        try {
            Thread.sleep(300); // Simula tempo de instalação
        } catch (InterruptedException e) {
            return false;
        }
        
        // Atualiza versão do software
        this.versaoSoftware = versao;
        
        System.out.println("🔄 Reiniciando para completar atualização...");
        reiniciar();
        
        System.out.printf("✅ %s atualizado com sucesso para versão %s%n", modelo, versao);
        return true;
    }
    
    /**
     * Implementação da função principal específica do smartphone
     */
    @Override
    public void executarFuncaoPrincipal() {
        if (!status.equals(STATUS_LIGADO)) {
            System.out.println("ℹ️ " + modelo + " deve estar ligado para fazer chamadas");
            return;
        }
        
        System.out.println("📞 Executando função principal: Realizar chamada telefônica");
        
        // Simula processo de chamada
        System.out.printf("📱 Discando de %s (%s)...%n", numeroTelefone, operadora);
        consumirBateria(3); // Chamada consome bateria
        
        if (modoSilencioso) {
            System.out.println("🔇 Chamada em modo silencioso");
        } else {
            System.out.println("🔊 Chamada com som ativo");
        }
        
        System.out.println("✅ Chamada realizada com sucesso");
    }
    
    /**
     * Implementação da configuração específica do smartphone
     */
    @Override
    public void configurarDispositivo() {
        System.out.printf("⚙️ Configurando smartphone %s...%n", modelo);
        
        if (!status.equals(STATUS_LIGADO)) {
            System.out.println("❌ Smartphone deve estar ligado para configurar");
            return;
        }
        
        // Configurações básicas
        System.out.println("📋 Aplicando configurações padrão:");
        System.out.printf("   - Operadora: %s%n", operadora);
        System.out.printf("   - Tela: %.1f polegadas%n", tamanhoTela);
        System.out.printf("   - RAM: %d GB%n", memoriaRAM);
        System.out.printf("   - Armazenamento: %d GB%n", armazenamento);
        
        // Ativa funcionalidades padrão
        ativarGPS();
        ativarBluetooth();
        
        // Instala apps básicos
        instalarAplicativo("Contatos");
        instalarAplicativo("Mensagens");
        instalarAplicativo("Câmera");
        
        consumirBateria(5); // Configuração consome bateria
        
        System.out.println("✅ Smartphone configurado com sucesso");
    }
    
    // ===== SOBRESCRITA DE MÉTODOS DA CLASSE PAI =====
    
    /**
     * Sobrescrita do método ligar com funcionalidades específicas
     */
    @Override
    public void ligar() {
        super.ligar(); // Chama método da classe pai
        
        if (status.equals(STATUS_LIGADO)) {
            System.out.printf("📶 Conectando à rede %s...%n", operadora);
            System.out.println("📱 Smartphone pronto para uso!");
        }
    }
    
    /**
     * Sobrescrita do método entrarStandby com comportamento específico
     */
    @Override
    public void entrarStandby() {
        super.entrarStandby(); // Chama implementação default da interface
        
        if (status.equals(STATUS_LIGADO)) {
            status = STATUS_STANDBY;
            
            // Desativa funcionalidades para economizar bateria
            if (gpsAtivo) {
                System.out.println("📍 GPS desativado para economizar bateria");
                gpsAtivo = false;
            }
            
            System.out.println("🔒 Tela bloqueada - smartphone em standby");
        }
    }
    
    /**
     * Sobrescrita do exibirInformacoes com dados específicos
     */
    @Override
    public void exibirInformacoes() {
        super.exibirInformacoes(); // Chama método da interface
        
        System.out.println("=== Informações Específicas ===");
        System.out.println("Operadora: " + operadora);
        System.out.println("Telefone: " + numeroTelefone);
        System.out.printf("Tela: %.1f polegadas%n", tamanhoTela);
        System.out.printf("RAM: %d GB%n", memoriaRAM);
        System.out.printf("Armazenamento: %d GB%n", armazenamento);
        System.out.println("Modo silencioso: " + (modoSilencioso ? "Sim" : "Não"));
        System.out.println("GPS: " + (gpsAtivo ? "Ativo" : "Inativo"));
        System.out.println("Bluetooth: " + (bluetoothAtivo ? "Ativo" : "Inativo"));
        System.out.println("Contatos: " + contatosArmazenados);
        System.out.println("Apps instalados: " + aplicativosInstalados);
        System.out.println("=============================\n");
    }
    
    // ===== MÉTODOS ESPECÍFICOS DO SMARTPHONE =====
    
    /**
     * Realiza uma chamada telefônica
     * @param numero Número para chamar
     */
    public void fazerLigacao(String numero) {
        if (!status.equals(STATUS_LIGADO)) {
            System.out.println("❌ Smartphone deve estar ligado para fazer chamadas");
            return;
        }
        
        System.out.printf("📞 Ligando de %s para %s...%n", numeroTelefone, numero);
        
        if (modoSilencioso) {
            System.out.println("📳 Chamada silenciosa...");
        }
        
        consumirBateria(5); // Chamada consome bateria
        System.out.println("✅ Chamada realizada");
    }
    
    /**
     * Envia uma mensagem de texto
     * @param numero Número de destino
     * @param mensagem Texto da mensagem
     */
    public void enviarMensagem(String numero, String mensagem) {
        if (!status.equals(STATUS_LIGADO)) {
            System.out.println("❌ Smartphone deve estar ligado para enviar mensagens");
            return;
        }
        
        System.out.printf("💬 Enviando mensagem para %s: \"%s\"%n", numero, mensagem);
        consumirBateria(1);
        System.out.println("✅ Mensagem enviada");
    }
    
    /**
     * Tira uma foto
     */
    public void tirarFoto() {
        if (!status.equals(STATUS_LIGADO)) {
            System.out.println("❌ Smartphone deve estar ligado para tirar fotos");
            return;
        }
        
        System.out.println("📸 Tirando foto...");
        consumirBateria(2);
        System.out.println("✅ Foto salva na galeria");
    }
    
    /**
     * Instala um aplicativo
     * @param nomeApp Nome do aplicativo
     */
    public void instalarAplicativo(String nomeApp) {
        if (!status.equals(STATUS_LIGADO)) {
            System.out.println("❌ Smartphone deve estar ligado para instalar apps");
            return;
        }
        
        if (!conectado) {
            System.out.println("❌ Conexão necessária para instalar " + nomeApp);
            return;
        }
        
        System.out.printf("📱 Instalando aplicativo: %s...%n", nomeApp);
        consumirBateria(3);
        aplicativosInstalados++;
        System.out.printf("✅ %s instalado (total: %d apps)%n", nomeApp, aplicativosInstalados);
    }
    
    /**
     * Adiciona um contato
     * @param nome Nome do contato
     * @param telefone Telefone do contato
     */
    public void adicionarContato(String nome, String telefone) {
        if (!status.equals(STATUS_LIGADO)) {
            System.out.println("❌ Smartphone deve estar ligado para adicionar contatos");
            return;
        }
        
        System.out.printf("📇 Adicionando contato: %s (%s)%n", nome, telefone);
        contatosArmazenados++;
        System.out.printf("✅ Contato salvo (total: %d contatos)%n", contatosArmazenados);
    }
    
    /**
     * Ativa/desativa modo silencioso
     * @param silencioso true para ativar modo silencioso
     */
    public void configurarModoSilencioso(boolean silencioso) {
        this.modoSilencioso = silencioso;
        System.out.println(silencioso ? "🔇 Modo silencioso ativado" : "🔊 Modo silencioso desativado");
    }
    
    /**
     * Ativa GPS
     */
    public void ativarGPS() {
        if (!status.equals(STATUS_LIGADO)) {
            System.out.println("❌ Smartphone deve estar ligado para ativar GPS");
            return;
        }
        
        if (!gpsAtivo) {
            gpsAtivo = true;
            consumirBateria(2);
            System.out.println("📍 GPS ativado");
        } else {
            System.out.println("ℹ️ GPS já está ativo");
        }
    }
    
    /**
     * Desativa GPS
     */
    public void desativarGPS() {
        if (gpsAtivo) {
            gpsAtivo = false;
            System.out.println("📍 GPS desativado");
        } else {
            System.out.println("ℹ️ GPS já está inativo");
        }
    }
    
    /**
     * Ativa Bluetooth
     */
    public void ativarBluetooth() {
        if (!status.equals(STATUS_LIGADO)) {
            System.out.println("❌ Smartphone deve estar ligado para ativar Bluetooth");
            return;
        }
        
        if (!bluetoothAtivo) {
            bluetoothAtivo = true;
            consumirBateria(1);
            System.out.println("📡 Bluetooth ativado");
        } else {
            System.out.println("ℹ️ Bluetooth já está ativo");
        }
    }
    
    /**
     * Desativa Bluetooth
     */
    public void desativarBluetooth() {
        if (bluetoothAtivo) {
            bluetoothAtivo = false;
            System.out.println("📡 Bluetooth desativado");
        } else {
            System.out.println("ℹ️ Bluetooth já está inativo");
        }
    }
    
    /**
     * Navega usando GPS
     * @param destino Destino da navegação
     */
    public void navegar(String destino) {
        if (!status.equals(STATUS_LIGADO)) {
            System.out.println("❌ Smartphone deve estar ligado para navegar");
            return;
        }
        
        if (!gpsAtivo) {
            System.out.println("📍 Ativando GPS para navegação...");
            ativarGPS();
        }
        
        if (!conectado) {
            System.out.println("⚠️ Navegação offline - mapas podem estar desatualizados");
        }
        
        System.out.printf("🗺️ Navegando para: %s%n", destino);
        consumirBateria(8); // Navegação consome muita bateria
        System.out.println("✅ Rota calculada");
    }
    
    // ===== GETTERS E SETTERS =====
    
    public String getOperadora() { return operadora; }
    public String getNumeroTelefone() { return numeroTelefone; }
    public double getTamanhoTela() { return tamanhoTela; }
    public int getMemoriaRAM() { return memoriaRAM; }
    public int getArmazenamento() { return armazenamento; }
    public boolean isModoSilencioso() { return modoSilencioso; }
    public boolean isGpsAtivo() { return gpsAtivo; }
    public boolean isBluetoothAtivo() { return bluetoothAtivo; }
    public int getContatosArmazenados() { return contatosArmazenados; }
    public int getAplicativosInstalados() { return aplicativosInstalados; }
    
    /**
     * Override do toString específico para smartphone
     */
    @Override
    public String toString() {
        return String.format("Smartphone{modelo='%s', operadora='%s', telefone='%s', " +
                           "bateria=%d%%, apps=%d, contatos=%d}", 
                           modelo, operadora, numeroTelefone, nivelBateria, 
                           aplicativosInstalados, contatosArmazenados);
    }
}