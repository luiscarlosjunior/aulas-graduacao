public class TesteCommand {
    public static void main(String[] args) {
        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║    PADRÃO COMMAND - Editor de Texto   ║");
        System.out.println("╚════════════════════════════════════════╝\n");
        
        TextEditor editor = new TextEditor();
        CommandHistory history = new CommandHistory();
        
        // Escrever texto
        System.out.println("1. Escrevendo texto...");
        history.execute(new WriteCommand(editor, "Olá "));
        editor.showContent();
        
        System.out.println("\n2. Escrevendo mais texto...");
        history.execute(new WriteCommand(editor, "Mundo!"));
        editor.showContent();
        
        // Desfazer
        System.out.println("\n3. Desfazendo...");
        history.undo();
        editor.showContent();
        
        System.out.println("\n4. Desfazendo novamente...");
        history.undo();
        editor.showContent();
        
        // Escrever novamente
        System.out.println("\n5. Escrevendo novo texto...");
        history.execute(new WriteCommand(editor, "Java é incrível!"));
        editor.showContent();
        
        System.out.println("\n✓ Padrão Command demonstrado com sucesso!");
    }
}
