/**
 * Singleton Logger usando Enum - forma mais segura
 * 
 * A implementação com enum é considerada a melhor prática
 * para Singleton pois é naturalmente thread-safe e protegida
 * contra serialização e reflection.
 * 
 * @author Aulas Graduação
 */
public enum Logger {
    
    INSTANCE; // Única instância do enum
    
    // Configurações do logger
    private boolean debug;
    private String arquivo;
    private int numeroLogs;
    
    // Construtor do enum (chamado apenas uma vez)
    Logger() {
        this.debug = true;
        this.arquivo = "application.log";
        this.numeroLogs = 0;
        System.out.println("📋 Logger inicializado - arquivo: " + arquivo);
    }
    
    /**
     * Log de informação
     * 
     * @param mensagem mensagem a ser logada
     */
    public void info(String mensagem) {
        log("INFO", mensagem);
    }
    
    /**
     * Log de aviso
     * 
     * @param mensagem mensagem a ser logada
     */
    public void warning(String mensagem) {
        log("WARNING", mensagem);
    }
    
    /**
     * Log de erro
     * 
     * @param mensagem mensagem a ser logada
     */
    public void error(String mensagem) {
        log("ERROR", mensagem);
    }
    
    /**
     * Log de debug (só aparece se debug estiver ativo)
     * 
     * @param mensagem mensagem a ser logada
     */
    public void debug(String mensagem) {
        if (debug) {
            log("DEBUG", mensagem);
        }
    }
    
    /**
     * Método interno para fazer o log
     * 
     * @param nivel nível do log (INFO, WARNING, ERROR, DEBUG)
     * @param mensagem mensagem a ser logada
     */
    private void log(String nivel, String mensagem) {
        numeroLogs++;
        String timestamp = java.time.LocalDateTime.now().toString();
        String logLine = String.format("[%s] %s - %s", timestamp, nivel, mensagem);
        
        // Simula escrita no arquivo e console
        System.out.println("📝 " + logLine);
        
        // Em uma implementação real, escreveria no arquivo também
        // Files.write(Paths.get(arquivo), (logLine + "\n").getBytes(), 
        //           StandardOpenOption.CREATE, StandardOpenOption.APPEND);
    }
    
    /**
     * Ativa ou desativa o modo debug
     * 
     * @param debug true para ativar, false para desativar
     */
    public void setDebug(boolean debug) {
        this.debug = debug;
        info("Modo debug " + (debug ? "ativado" : "desativado"));
    }
    
    /**
     * Altera o arquivo de log
     * 
     * @param arquivo caminho do novo arquivo
     */
    public void setArquivo(String arquivo) {
        String arquivoAnterior = this.arquivo;
        this.arquivo = arquivo;
        info("Arquivo de log alterado de '" + arquivoAnterior + "' para '" + arquivo + "'");
    }
    
    /**
     * Retorna estatísticas do logger
     * 
     * @return string com estatísticas
     */
    public String getEstatisticas() {
        return String.format("Logger Stats - Arquivo: %s | Debug: %s | Total de logs: %d",
                arquivo, debug ? "ON" : "OFF", numeroLogs);
    }
    
    /**
     * Limpa as estatísticas do logger
     */
    public void limparEstatisticas() {
        numeroLogs = 0;
        info("Estatísticas do logger zeradas");
    }
    
    // Getters
    public boolean isDebug() {
        return debug;
    }
    
    public String getArquivo() {
        return arquivo;
    }
    
    public int getNumeroLogs() {
        return numeroLogs;
    }
}