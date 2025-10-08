/**
 * Classe de Teste - Sistema de Processamento de Documentos
 * 
 * Demonstra o uso de classes abstratas com Template Method Pattern,
 * mostrando como diferentes tipos de documentos seguem o mesmo fluxo
 * de processamento, mas com implementações específicas.
 * 
 * @author Curso POO Java - Universidade Nove de Julho
 */
public class TesteProcessadorDocumentos {
    public static void main(String[] args) {
        System.out.println("╔════════════════════════════════════════════════════════════╗");
        System.out.println("║   SISTEMA DE PROCESSAMENTO DE DOCUMENTOS                   ║");
        System.out.println("║        Template Method Pattern com Classes Abstratas       ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝\n");
        
        // Criando array polimórfico de processadores
        ProcessadorDocumento[] processadores = new ProcessadorDocumento[3];
        
        processadores[0] = new ProcessadorPDF("relatorio-financeiro-2024.pdf");
        processadores[1] = new ProcessadorExcel("planilha-vendas.xlsx");
        processadores[2] = new ProcessadorImagem("foto-produto.jpg");
        
        System.out.println("═══════════════════════════════════════════════════════════");
        System.out.println("  PARTE 1: PROCESSAMENTO DE DOCUMENTOS");
        System.out.println("  (Observe o fluxo padronizado do Template Method)");
        System.out.println("═══════════════════════════════════════════════════════════");
        
        // Polimorfismo: todos seguem o mesmo fluxo (Template Method)
        for (ProcessadorDocumento proc : processadores) {
            proc.processar();
            System.out.println();
        }
        
        System.out.println("\n═══════════════════════════════════════════════════════════");
        System.out.println("  PARTE 2: EXIBINDO RELATÓRIOS");
        System.out.println("═══════════════════════════════════════════════════════════");
        
        for (ProcessadorDocumento proc : processadores) {
            proc.exibirRelatorio();
        }
        
        System.out.println("\n═══════════════════════════════════════════════════════════");
        System.out.println("  PARTE 3: OPERAÇÕES ESPECÍFICAS POR TIPO");
        System.out.println("═══════════════════════════════════════════════════════════");
        
        // Operações específicas do PDF
        System.out.println("\n--- Operações específicas: PDF ---");
        if (processadores[0] instanceof ProcessadorPDF) {
            ProcessadorPDF pdf = (ProcessadorPDF) processadores[0];
            pdf.extrairImagens();
        }
        
        // Operações específicas do Excel
        System.out.println("\n--- Operações específicas: Excel ---");
        if (processadores[1] instanceof ProcessadorExcel) {
            ProcessadorExcel excel = (ProcessadorExcel) processadores[1];
            excel.gerarEstatisticas();
        }
        
        // Operações específicas da Imagem
        System.out.println("\n--- Operações específicas: Imagem ---");
        if (processadores[2] instanceof ProcessadorImagem) {
            ProcessadorImagem img = (ProcessadorImagem) processadores[2];
            img.redimensionar(800, 600);
            img.aplicarFiltro("Sépia");
        }
        
        System.out.println("\n═══════════════════════════════════════════════════════════");
        System.out.println("  PARTE 4: EXPORTAÇÃO PARA OUTROS FORMATOS");
        System.out.println("═══════════════════════════════════════════════════════════");
        
        // PDF para TXT
        System.out.println("\n→ Exportando PDF:");
        processadores[0].exportar("txt");
        processadores[0].exportar("html");
        
        // Excel para CSV
        System.out.println("\n→ Exportando Excel:");
        processadores[1].exportar("csv");
        processadores[1].exportar("json");
        
        // Imagem para outros formatos
        System.out.println("\n→ Exportando Imagem:");
        processadores[2].exportar("png");
        processadores[2].exportar("thumbnail");
        
        System.out.println("\n═══════════════════════════════════════════════════════════");
        System.out.println("  PARTE 5: TESTANDO VALIDAÇÃO DE FORMATOS");
        System.out.println("═══════════════════════════════════════════════════════════");
        
        // Teste com arquivos inválidos
        System.out.println("\n→ Tentando processar arquivo com extensão incorreta:");
        ProcessadorDocumento docInvalido = new ProcessadorPDF("documento.txt");
        docInvalido.processar(); // Deve falhar na validação
        
        System.out.println("\n═══════════════════════════════════════════════════════════");
        System.out.println("  PARTE 6: PROCESSAMENTO EM LOTE");
        System.out.println("═══════════════════════════════════════════════════════════");
        
        String[] arquivos = {
            "contrato-seguro.pdf",
            "dados-cliente.xlsx",
            "logo-empresa.png"
        };
        
        System.out.println("\n📁 Processando lote de " + arquivos.length + " arquivos...\n");
        
        for (String arquivo : arquivos) {
            ProcessadorDocumento proc = criarProcessador(arquivo);
            if (proc != null) {
                proc.processar();
            }
        }
        
        System.out.println("\n═══════════════════════════════════════════════════════════");
        System.out.println("  RESUMO E CONCEITOS DEMONSTRADOS");
        System.out.println("═══════════════════════════════════════════════════════════");
        
        System.out.println("\n📊 Estatísticas do Processamento:");
        int totalProcessado = 0;
        long tamanhoTotal = 0;
        
        for (ProcessadorDocumento proc : processadores) {
            if (proc.isProcessado()) {
                totalProcessado++;
                tamanhoTotal += proc.getTamanhoBytes();
            }
        }
        
        System.out.println("   Documentos processados: " + totalProcessado);
        System.out.println("   Tamanho total: " + tamanhoTotal + " bytes (" + 
                         String.format("%.2f", tamanhoTotal/1024.0/1024.0) + " MB)");
        
        System.out.println("\n💡 Conceitos de POO Demonstrados:");
        System.out.println("   ✓ Classe Abstrata como base comum");
        System.out.println("   ✓ Template Method Pattern (método final processar())");
        System.out.println("   ✓ Fluxo de processamento padronizado");
        System.out.println("   ✓ Polimorfismo com arrays");
        System.out.println("   ✓ Métodos abstratos com implementações específicas");
        System.out.println("   ✓ Métodos concretos compartilhados");
        System.out.println("   ✓ Encapsulamento e reutilização de código");
        System.out.println("   ✓ Validação em múltiplos níveis");
        
        System.out.println("\n🎯 Vantagens Observadas:");
        System.out.println("   • Fluxo consistente para todos os documentos");
        System.out.println("   • Fácil adicionar novos tipos (extensibilidade)");
        System.out.println("   • Código comum centralizado (manutenibilidade)");
        System.out.println("   • Comportamentos específicos bem isolados");
        System.out.println("   • Garantia de que todos seguem o mesmo padrão");
        
        System.out.println("\n╔════════════════════════════════════════════════════════════╗");
        System.out.println("║              FIM DA DEMONSTRAÇÃO                           ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝");
    }
    
    /**
     * Método auxiliar - cria o processador adequado baseado na extensão
     */
    private static ProcessadorDocumento criarProcessador(String nomeArquivo) {
        String ext = nomeArquivo.substring(nomeArquivo.lastIndexOf('.')).toLowerCase();
        
        switch (ext) {
            case ".pdf":
                return new ProcessadorPDF(nomeArquivo);
            case ".xlsx":
            case ".xls":
                return new ProcessadorExcel(nomeArquivo);
            case ".jpg":
            case ".jpeg":
            case ".png":
            case ".gif":
                return new ProcessadorImagem(nomeArquivo);
            default:
                System.out.println("✗ Tipo de arquivo não suportado: " + nomeArquivo);
                return null;
        }
    }
}
