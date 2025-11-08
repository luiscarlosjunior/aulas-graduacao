public class FabricaWindows implements FactoryGui {

    @Override
    public Botao criarBotao() {
        return new BotaoWindows();
    }

    @Override
    public Janela criarJanela() {
        return new JanelaWindows();
    }
    
}
