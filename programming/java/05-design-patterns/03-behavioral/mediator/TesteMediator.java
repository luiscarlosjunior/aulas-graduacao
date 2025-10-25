public class TesteMediator {
    public static void main(String[] args) {
        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║     PADRÃO MEDIATOR - Chat Room       ║");
        System.out.println("╚════════════════════════════════════════╝\n");
        
        ChatMediator chatRoom = new ChatRoom();
        
        User alice = new User("Alice", chatRoom);
        User bob = new User("Bob", chatRoom);
        User charlie = new User("Charlie", chatRoom);
        
        chatRoom.addUser(alice);
        chatRoom.addUser(bob);
        chatRoom.addUser(charlie);
        
        System.out.println();
        alice.send("Olá pessoal!");
        
        System.out.println();
        bob.send("Oi Alice!");
        
        System.out.println("\n✓ Mediator demonstrado com sucesso!");
    }
}
