/**
 * Exemplo prático de Abstração - Segunda Classe Concreta
 * 
 * Esta classe demonstra como diferentes dispositivos podem implementar
 * os mesmos métodos abstratos de maneiras diferentes, mas seguindo
 * o mesmo contrato definido pela classe abstrata e interface.
 * 
 * @author Curso POO Java
 */
public class Tablet extends DispositivoEletronico {
    
    // ===== ATRIBUTOS ESPECÍFICOS DO TABLET =====
    
    private double tamanhoTela;         // Tamanho da tela em polegadas
    private boolean temCaneta;          // Se suporta caneta stylus
    private boolean temTeclado;         // Se tem teclado acoplado
    private String orientacao;          // "RETRATO" ou "PAISAGEM"
    private int aplicativosEducativos;  // Número de apps educativos
    private boolean modoLeitura;        // Modo otimizado para leitura
    
    // ===== CONSTRUTOR =====
    
    public Tablet(String modelo, String numeroSerie, String versaoSoftware,
                  double tamanhoTela, boolean temCaneta, boolean temTeclado) {
        
        // Tablets geralmente consomem mais energia que smartphones
        super(modelo, numeroSerie, versaoSoftware, 20.0);
        
        this.tamanhoTela = tamanhoTela;
        this.temCaneta = temCaneta;
        this.temTeclado = temTeclado;
        this.orientacao = "PAISAGEM";
        this.aplicativosEducativos = 0;
        this.modoLeitura = false;
        
        System.out.printf("📱 Tablet %s (%.1f\") configurado%n", modelo, tamanhoTela);
    }
    
    // ===== IMPLEMENTAÇÃO DOS MÉTODOS ABSTRATOS =====
    
    @Override
    public boolean atualizarSoftware(String versao) {
        System.out.printf("📱 Atualizando tablet %s para versão %s...%n", modelo, versao);
        
        if (!status.equals(STATUS_LIGADO)) {
            System.out.println("❌ Tablet deve estar ligado para atualizar");
            return false;
        }
        
        if (nivelBateria < 30) {
            System.out.println("❌ Bateria insuficiente para atualização (mínimo 30%)");
            return false;
        }
        
        System.out.println("📥 Baixando atualização do sistema...");
        consumirBateria(15);
        
        this.versaoSoftware = versao;
        reiniciar();
        
        System.out.printf("✅ Tablet atualizado para versão %s%n", versao);
        return true;
    }
    
    @Override
    public void executarFuncaoPrincipal() {
        if (!status.equals(STATUS_LIGADO)) {
            System.out.println("ℹ️ " + modelo + " deve estar ligado para executar função principal");
            return;
        }
        
        System.out.println("📚 Executando função principal: Navegação e produtividade");
        
        if (modoLeitura) {
            System.out.println("📖 Otimizando tela para leitura...");
        }
        
        if (temCaneta) {
            System.out.println("✏️ Modo de escrita com stylus disponível");
        }
        
        consumirBateria(4);
        System.out.println("✅ Pronto para produtividade");
    }
    
    @Override
    public void configurarDispositivo() {
        System.out.printf("⚙️ Configurando tablet %s...%n", modelo);
        
        if (!status.equals(STATUS_LIGADO)) {
            System.out.println("❌ Tablet deve estar ligado para configurar");
            return;
        }
        
        System.out.println("📋 Aplicando configurações de tablet:");
        System.out.printf("   - Tela: %.1f polegadas%n", tamanhoTela);
        System.out.printf("   - Stylus: %s%n", temCaneta ? "Sim" : "Não");
        System.out.printf("   - Teclado: %s%n", temTeclado ? "Sim" : "Não");
        
        // Instala apps básicos de tablet
        instalarAplicativoEducativo("Leitor PDF");
        instalarAplicativoEducativo("Editor de Texto");
        if (temCaneta) {
            instalarAplicativoEducativo("App de Desenho");
        }
        
        ativarModoLeitura();
        
        consumirBateria(8);
        System.out.println("✅ Tablet configurado para produtividade");
    }
    
    // ===== MÉTODOS ESPECÍFICOS DO TABLET =====
    
    public void instalarAplicativoEducativo(String nomeApp) {
        if (!status.equals(STATUS_LIGADO)) {
            System.out.println("❌ Tablet deve estar ligado para instalar apps");
            return;
        }
        
        System.out.printf("📚 Instalando app educativo: %s%n", nomeApp);
        aplicativosEducativos++;
        consumirBateria(2);
        System.out.printf("✅ %s instalado (apps educativos: %d)%n", nomeApp, aplicativosEducativos);
    }
    
    public void rotacionarTela() {
        orientacao = orientacao.equals("RETRATO") ? "PAISAGEM" : "RETRATO";
        System.out.printf("🔄 Tela rotacionada para modo %s%n", orientacao);
    }
    
    public void ativarModoLeitura() {
        modoLeitura = true;
        System.out.println("📖 Modo leitura ativado - tela otimizada");
    }
    
    public void desativarModoLeitura() {
        modoLeitura = false;
        System.out.println("📖 Modo leitura desativado");
    }
    
    public void desenharComCaneta() {
        if (!status.equals(STATUS_LIGADO)) {
            System.out.println("❌ Tablet deve estar ligado para desenhar");
            return;
        }
        
        if (!temCaneta) {
            System.out.println("❌ Este tablet não suporta caneta stylus");
            return;
        }
        
        System.out.println("✏️ Iniciando modo de desenho com stylus...");
        consumirBateria(5);
        System.out.println("🎨 Desenho criado e salvo");
    }
    
    @Override
    public void exibirInformacoes() {
        super.exibirInformacoes();
        
        System.out.println("=== Informações do Tablet ===");
        System.out.printf("Tela: %.1f polegadas%n", tamanhoTela);
        System.out.println("Suporte a stylus: " + (temCaneta ? "Sim" : "Não"));
        System.out.println("Teclado acoplado: " + (temTeclado ? "Sim" : "Não"));
        System.out.println("Orientação: " + orientacao);
        System.out.println("Apps educativos: " + aplicativosEducativos);
        System.out.println("Modo leitura: " + (modoLeitura ? "Ativo" : "Inativo"));
        System.out.println("===========================\n");
    }
    
    // ===== GETTERS =====
    
    public double getTamanhoTela() { return tamanhoTela; }
    public boolean isTemCaneta() { return temCaneta; }
    public boolean isTemTeclado() { return temTeclado; }
    public String getOrientacao() { return orientacao; }
    public int getAplicativosEducativos() { return aplicativosEducativos; }
    public boolean isModoLeitura() { return modoLeitura; }
    
    @Override
    public String toString() {
        return String.format("Tablet{modelo='%s', tela=%.1f\", stylus=%s, bateria=%d%%}", 
                           modelo, tamanhoTela, temCaneta, nivelBateria);
    }
}