public class PDFProcessor extends DocumentProcessor {
    protected String getDocumentType() {
        return "Processando PDF";
    }
    
    protected void parseContent() {
        System.out.println("  → Parsing PDF com PDFBox");
    }
}
