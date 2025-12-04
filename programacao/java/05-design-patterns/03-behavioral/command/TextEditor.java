public class TextEditor {
    private StringBuilder content = new StringBuilder();
    
    public void write(String text) {
        content.append(text);
        System.out.println("✓ Texto adicionado: \"" + text + "\"");
    }
    
    public void deleteLastChars(int count) {
        int start = Math.max(0, content.length() - count);
        content.delete(start, content.length());
        System.out.println("✓ Removidos " + count + " caracteres");
    }
    
    public String getContent() {
        return content.toString();
    }
    
    public void showContent() {
        System.out.println("📄 Conteúdo: \"" + getContent() + "\"");
    }
}
