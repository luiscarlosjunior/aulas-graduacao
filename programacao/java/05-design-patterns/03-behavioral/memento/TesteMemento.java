public class TesteMemento {
    public static void main(String[] args) {
        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║    PADRÃO MEMENTO - Editor Texto      ║");
        System.out.println("╚════════════════════════════════════════╝\n");
        
        TextEditor editor = new TextEditor();
        History history = new History();
        
        editor.write("Olá ");
        System.out.println("Conteúdo: " + editor.getContent());
        history.save(editor.save());
        
        editor.write("Mundo");
        System.out.println("Conteúdo: " + editor.getContent());
        history.save(editor.save());
        
        editor.write("!");
        System.out.println("Conteúdo: " + editor.getContent());
        
        System.out.println("\n⤶ Desfazendo...");
        editor.restore(history.undo());
        System.out.println("Conteúdo: " + editor.getContent());
        
        System.out.println("\n⤶ Desfazendo...");
        editor.restore(history.undo());
        System.out.println("Conteúdo: " + editor.getContent());
        
        System.out.println("\n✓ Memento demonstrado com sucesso!");
    }
}
