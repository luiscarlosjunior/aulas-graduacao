public class WriteCommand implements Command {
    private TextEditor editor;
    private String text;
    
    public WriteCommand(TextEditor editor, String text) {
        this.editor = editor;
        this.text = text;
    }
    
    public void execute() {
        editor.write(text);
    }
    
    public void undo() {
        editor.deleteLastChars(text.length());
    }
}
