import java.util.Stack;

public class CommandHistory {
    private Stack<Command> history = new Stack<>();
    
    public void execute(Command command) {
        command.execute();
        history.push(command);
    }
    
    public void undo() {
        if (!history.isEmpty()) {
            Command command = history.pop();
            command.undo();
            System.out.println("↶ Desfazer última operação");
        } else {
            System.out.println("⚠ Nada para desfazer");
        }
    }
}
