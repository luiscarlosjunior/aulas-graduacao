public class TesteTemplateMethod {
    public static void main(String[] args) {
        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║  PADRÃO TEMPLATE METHOD - Documentos  ║");
        System.out.println("╚════════════════════════════════════════╝");
        
        DocumentProcessor pdf = new PDFProcessor();
        pdf.processDocument("relatorio.pdf");
        
        DocumentProcessor word = new WordProcessor();
        word.processDocument("documento.docx");
        
        DocumentProcessor excel = new ExcelProcessor();
        excel.processDocument("planilha.xlsx");
        
        System.out.println("✓ Template Method demonstrado com sucesso!");
    }
}
