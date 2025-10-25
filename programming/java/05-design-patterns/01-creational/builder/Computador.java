/**
 * Produto complexo que será construído pelo Builder
 * 
 * Representa um computador com várias configurações opcionais.
 * Usar construtor tradicional seria confuso com tantos parâmetros.
 * 
 * @author Aulas Graduação
 */
public class Computador {
    
    // Componentes obrigatórios
    private final String processador;
    private final int memoriaRAM; // em GB
    
    // Componentes opcionais
    private final String placaVideo;
    private final int armazenamento; // em GB
    private final String tipoArmazenamento; // SSD ou HDD
    private final boolean temWifi;
    private final boolean temBluetooth;
    private final String sistemaOperacional;
    private final String gabinete;
    
    /**
     * Construtor privado - apenas o Builder pode criar instâncias
     * 
     * @param builder Builder com as configurações
     */
    private Computador(Builder builder) {
        this.processador = builder.processador;
        this.memoriaRAM = builder.memoriaRAM;
        this.placaVideo = builder.placaVideo;
        this.armazenamento = builder.armazenamento;
        this.tipoArmazenamento = builder.tipoArmazenamento;
        this.temWifi = builder.temWifi;
        this.temBluetooth = builder.temBluetooth;
        this.sistemaOperacional = builder.sistemaOperacional;
        this.gabinete = builder.gabinete;
    }
    
    /**
     * Retorna descrição completa do computador
     * 
     * @return especificações do computador
     */
    public String getEspecificacoes() {
        StringBuilder specs = new StringBuilder();
        specs.append("💻 ESPECIFICAÇÕES DO COMPUTADOR\n");
        specs.append("================================\n");
        specs.append("Processador: ").append(processador).append("\n");
        specs.append("Memória RAM: ").append(memoriaRAM).append(" GB\n");
        
        if (placaVideo != null) {
            specs.append("Placa de Vídeo: ").append(placaVideo).append("\n");
        }
        
        if (armazenamento > 0) {
            specs.append("Armazenamento: ").append(armazenamento).append(" GB ");
            specs.append(tipoArmazenamento != null ? tipoArmazenamento : "").append("\n");
        }
        
        specs.append("Wi-Fi: ").append(temWifi ? "Sim" : "Não").append("\n");
        specs.append("Bluetooth: ").append(temBluetooth ? "Sim" : "Não").append("\n");
        
        if (sistemaOperacional != null) {
            specs.append("Sistema Operacional: ").append(sistemaOperacional).append("\n");
        }
        
        if (gabinete != null) {
            specs.append("Gabinete: ").append(gabinete).append("\n");
        }
        
        specs.append("================================");
        
        return specs.toString();
    }
    
    /**
     * Calcula o preço estimado baseado nas especificações
     * 
     * @return preço em reais
     */
    public double calcularPreco() {
        double preco = 500.0; // Preço base
        
        // Processador
        if (processador.contains("i9") || processador.contains("Ryzen 9")) {
            preco += 3000;
        } else if (processador.contains("i7") || processador.contains("Ryzen 7")) {
            preco += 2000;
        } else if (processador.contains("i5") || processador.contains("Ryzen 5")) {
            preco += 1000;
        } else {
            preco += 500;
        }
        
        // RAM
        preco += memoriaRAM * 200;
        
        // Placa de vídeo
        if (placaVideo != null) {
            if (placaVideo.contains("RTX 4090")) {
                preco += 10000;
            } else if (placaVideo.contains("RTX 4080")) {
                preco += 7000;
            } else if (placaVideo.contains("RTX 4070")) {
                preco += 4000;
            } else {
                preco += 1500;
            }
        }
        
        // Armazenamento
        if (armazenamento > 0) {
            double precoGB = "SSD".equals(tipoArmazenamento) ? 0.5 : 0.2;
            preco += armazenamento * precoGB;
        }
        
        // Extras
        if (temWifi) preco += 100;
        if (temBluetooth) preco += 50;
        
        return preco;
    }
    
    // Getters
    public String getProcessador() { return processador; }
    public int getMemoriaRAM() { return memoriaRAM; }
    public String getPlacaVideo() { return placaVideo; }
    public int getArmazenamento() { return armazenamento; }
    public String getTipoArmazenamento() { return tipoArmazenamento; }
    public boolean isTemWifi() { return temWifi; }
    public boolean isTemBluetooth() { return temBluetooth; }
    public String getSistemaOperacional() { return sistemaOperacional; }
    public String getGabinete() { return gabinete; }
    
    /**
     * Builder interno para construir um Computador
     * 
     * Permite construção fluente e evita construtores telescópicos.
     */
    public static class Builder {
        // Obrigatórios
        private final String processador;
        private final int memoriaRAM;
        
        // Opcionais - inicializados com valores padrão
        private String placaVideo = null;
        private int armazenamento = 0;
        private String tipoArmazenamento = null;
        private boolean temWifi = false;
        private boolean temBluetooth = false;
        private String sistemaOperacional = null;
        private String gabinete = "ATX Padrão";
        
        /**
         * Construtor do Builder com parâmetros obrigatórios
         * 
         * @param processador CPU do computador
         * @param memoriaRAM quantidade de RAM em GB
         */
        public Builder(String processador, int memoriaRAM) {
            if (processador == null || processador.trim().isEmpty()) {
                throw new IllegalArgumentException("Processador é obrigatório");
            }
            if (memoriaRAM <= 0) {
                throw new IllegalArgumentException("Memória RAM deve ser maior que zero");
            }
            
            this.processador = processador;
            this.memoriaRAM = memoriaRAM;
        }
        
        /**
         * Define a placa de vídeo
         */
        public Builder comPlacaVideo(String placaVideo) {
            this.placaVideo = placaVideo;
            return this;
        }
        
        /**
         * Define o armazenamento
         */
        public Builder comArmazenamento(int gb, String tipo) {
            this.armazenamento = gb;
            this.tipoArmazenamento = tipo;
            return this;
        }
        
        /**
         * Adiciona Wi-Fi
         */
        public Builder comWifi() {
            this.temWifi = true;
            return this;
        }
        
        /**
         * Adiciona Bluetooth
         */
        public Builder comBluetooth() {
            this.temBluetooth = true;
            return this;
        }
        
        /**
         * Define o sistema operacional
         */
        public Builder comSistemaOperacional(String so) {
            this.sistemaOperacional = so;
            return this;
        }
        
        /**
         * Define o gabinete
         */
        public Builder comGabinete(String gabinete) {
            this.gabinete = gabinete;
            return this;
        }
        
        /**
         * Constrói o computador com as configurações definidas
         * 
         * @return instância de Computador
         */
        public Computador build() {
            return new Computador(this);
        }
    }
}
