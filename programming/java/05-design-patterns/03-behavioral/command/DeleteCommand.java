public class DeleteCommand implements Command {
    private TextEditor editor;
    private int count;
    private String deletedText;
    
    public DeleteCommand(TextEditor editor, int count) {
        this.editor = editor;
        this.count = count;
    }
    
    public void execute() {
        String content = editor.getContent();
        int start = Math.max(0, content.length() - count);
        deletedText = content.substring(start);
        editor.deleteLastChars(count);
    }
    
    public void undo() {
        if (deletedText != null) {
            editor.write(deletedText);
        }
    }
}
