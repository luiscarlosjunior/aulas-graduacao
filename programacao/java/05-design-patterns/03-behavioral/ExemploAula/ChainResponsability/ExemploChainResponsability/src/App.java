public class App {
    public static void main(String[] args) throws Exception {
        Handler supervisor = new Supervisor();
        Handler gerente = new Gerente();
        Handler diretor = new Diretor();

        supervisor.setNext(gerente).setNext(diretor);

        supervisor.handle(500);
        supervisor.handle(3000);
        supervisor.handle(15000);
        supervisor.handle(25000);

    }
}
