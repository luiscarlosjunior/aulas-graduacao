public class FabricaMacOs implements FactoryGui {

    @Override
    public Botao criarBotao() {
        return new BotaoMacOs();
    }

    @Override
    public Janela criarJanela() {
        return new JanelaMacOs();
    }
    
}
