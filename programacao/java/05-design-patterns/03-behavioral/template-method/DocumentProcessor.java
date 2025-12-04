public abstract class DocumentProcessor {
    // Template Method - define a estrutura do algoritmo
    public final void processDocument(String filePath) {
        System.out.println("\n" + getDocumentType() + ":");
        
        openDocument(filePath);
        parseContent();
        extractData();
        closeDocument();
        
        System.out.println("✓ Processamento concluído\n");
    }
    
    // Métodos abstratos - subclasses implementam
    protected abstract String getDocumentType();
    protected abstract void parseContent();
    
    // Métodos concretos - comportamento padrão
    protected void openDocument(String filePath) {
        System.out.println("  → Abrindo: " + filePath);
    }
    
    protected void extractData() {
        System.out.println("  → Extraindo dados");
    }
    
    protected void closeDocument() {
        System.out.println("  → Fechando documento");
    }
}
