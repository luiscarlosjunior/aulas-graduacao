/**
 * Classe abstrata ProcessadorDocumento - Sistema de Processamento de Documentos
 * 
 * Representa a base para processadores de diferentes tipos de documentos.
 * Define um fluxo padrão de processamento (Template Method) e delega
 * operações específicas para cada tipo de documento.
 * 
 * Demonstra:
 * - Template Method Pattern
 * - Fluxo de processamento padronizado
 * - Operações específicas por tipo de documento
 * 
 * @author Curso POO Java - Universidade Nove de Julho
 */
public abstract class ProcessadorDocumento {
    protected String nomeArquivo;
    protected String conteudo;
    protected long tamanhoBytes;
    protected boolean processado;
    
    /**
     * Construtor da classe ProcessadorDocumento
     * @param nomeArquivo Nome do arquivo a ser processado
     */
    public ProcessadorDocumento(String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
        this.conteudo = "";
        this.tamanhoBytes = 0;
        this.processado = false;
    }
    
    // Getters
    public String getNomeArquivo() {
        return nomeArquivo;
    }
    
    public String getConteudo() {
        return conteudo;
    }
    
    public long getTamanhoBytes() {
        return tamanhoBytes;
    }
    
    public boolean isProcessado() {
        return processado;
    }
    
    /**
     * TEMPLATE METHOD - Define o fluxo de processamento
     * Este método é final e não pode ser sobrescrito
     * Garante que todos os documentos sigam o mesmo fluxo
     */
    public final void processar() {
        System.out.println("\n╔═══════════════════════════════════════════════════╗");
        System.out.println("║  📄 PROCESSANDO: " + nomeArquivo);
        System.out.println("╚═══════════════════════════════════════════════════╝");
        
        // Passo 1: Validação
        System.out.println("\n[1/5] Validando documento...");
        if (!validar()) {
            System.out.println("✗ Validação falhou! Processamento abortado.");
            return;
        }
        System.out.println("✓ Documento válido!");
        
        // Passo 2: Abertura
        System.out.println("\n[2/5] Abrindo arquivo...");
        abrir();
        System.out.println("✓ Arquivo aberto com sucesso!");
        
        // Passo 3: Leitura
        System.out.println("\n[3/5] Lendo conteúdo...");
        ler();
        System.out.println("✓ Conteúdo lido: " + tamanhoBytes + " bytes");
        
        // Passo 4: Processamento específico
        System.out.println("\n[4/5] Processando conteúdo específico...");
        processarConteudo();
        System.out.println("✓ Processamento específico concluído!");
        
        // Passo 5: Fechamento
        System.out.println("\n[5/5] Finalizando...");
        fechar();
        
        processado = true;
        System.out.println("\n✅ Processamento de '" + nomeArquivo + "' concluído com sucesso!");
    }
    
    /**
     * Método concreto - validação comum para todos os documentos
     */
    protected boolean validar() {
        // Validações comuns
        if (nomeArquivo == null || nomeArquivo.trim().isEmpty()) {
            System.out.println("✗ Nome de arquivo inválido!");
            return false;
        }
        
        // Validação específica do tipo
        return validarFormato();
    }
    
    /**
     * Método concreto - fecha o documento
     */
    protected void fechar() {
        System.out.println("→ Liberando recursos...");
        System.out.println("→ Fechando arquivo: " + nomeArquivo);
    }
    
    /**
     * Método concreto - exibe relatório do processamento
     */
    public void exibirRelatorio() {
        System.out.println("\n╔═══════════════════════════════════════════════════╗");
        System.out.println("║           RELATÓRIO DE PROCESSAMENTO              ║");
        System.out.println("╠═══════════════════════════════════════════════════╣");
        System.out.println("║ Arquivo: " + nomeArquivo);
        System.out.println("║ Tipo: " + getTipoDocumento());
        System.out.println("║ Tamanho: " + tamanhoBytes + " bytes");
        System.out.println("║ Status: " + (processado ? "Processado ✓" : "Não processado ✗"));
        exibirInformacoesEspecificas();
        System.out.println("╚═══════════════════════════════════════════════════╝");
    }
    
    // ========================================
    // MÉTODOS ABSTRATOS - Implementados pelas subclasses
    // ========================================
    
    /**
     * Validação específica do formato do documento
     * @return true se o formato é válido
     */
    protected abstract boolean validarFormato();
    
    /**
     * Abre o arquivo de acordo com seu tipo
     */
    protected abstract void abrir();
    
    /**
     * Lê o conteúdo do arquivo
     */
    protected abstract void ler();
    
    /**
     * Processa o conteúdo de forma específica
     */
    protected abstract void processarConteudo();
    
    /**
     * Retorna o tipo do documento
     * @return String com o tipo
     */
    public abstract String getTipoDocumento();
    
    /**
     * Exibe informações específicas do tipo de documento
     */
    protected abstract void exibirInformacoesEspecificas();
    
    /**
     * Exporta o documento processado
     * @param formato Formato de exportação
     * @return true se exportou com sucesso
     */
    public abstract boolean exportar(String formato);
}
