public class WordProcessor extends DocumentProcessor {
    protected String getDocumentType() {
        return "Processando Word";
    }
    
    protected void parseContent() {
        System.out.println("  → Parsing DOCX com Apache POI");
    }
}
