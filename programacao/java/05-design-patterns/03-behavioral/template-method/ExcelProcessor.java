public class ExcelProcessor extends DocumentProcessor {
    protected String getDocumentType() {
        return "Processando Excel";
    }
    
    protected void parseContent() {
        System.out.println("  → Parsing XLSX com Apache POI");
    }
    
    @Override
    protected void extractData() {
        System.out.println("  → Extraindo planilhas e fórmulas");
    }
}
