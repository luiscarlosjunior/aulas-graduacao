public class App {
    public static void main(String[] args) throws Exception {
        ConfiguracaoSistema config1 = ConfiguracaoSistema.getInstancia();
        ConfiguracaoSistema config2 = ConfiguracaoSistema.getInstancia();
        
        config1.setIdioma("EN-US");
        config1.setTema("Escuro");
        System.out.println("Configuração 1: Idioma = " + config1.getIdioma() + ", Tema = " + config1.getTema());

        config2.setIdioma("FR-FR");
        config2.setTema("Azul");        
        System.out.println("Configuração 2: Idioma = " + config2.getIdioma() + ", Tema = " + config2.getTema());


    }
}
