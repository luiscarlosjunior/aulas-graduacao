public class App {
    public static void main(String[] args) throws Exception {
        boolean ehMacOs = false; // Altere para false para Windows

        FactoryGui fabrica;
        if(ehMacOs){
            // Fabrico MacOS
            fabrica = new FabricaMacOs();
        }
        else{
            // Fabrico Windows
            fabrica = new FabricaWindows();
        }

        // Criar componentes GUI
        Botao botao = fabrica.criarBotao();
        Janela janela = fabrica.criarJanela();
        botao.renderizar();
        janela.exibir();
    }
}
