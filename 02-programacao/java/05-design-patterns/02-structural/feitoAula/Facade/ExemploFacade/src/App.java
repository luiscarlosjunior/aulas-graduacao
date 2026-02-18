public class App {
    public static void main(String[] args) throws Exception {
        SalaCinemaFacade salaCinema = new SalaCinemaFacade();
        salaCinema.iniciarFilme();
        // Simula o tempo do filme
        salaCinema.encerrarFilme();
        
    }
}
