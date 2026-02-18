/**
 * Aplicação cliente que usa a Abstract Factory
 * 
 * Esta classe representa uma aplicação que precisa criar
 * interfaces de usuário consistentes, mas não sabe de antemão
 * qual tema será usado.
 * 
 * @author Aulas Graduação
 */
public class Aplicacao {
    
    private Botao botao;
    private CampoTexto campoTexto;
    private UIFactory factory;
    
    /**
     * Construtor que recebe a factory
     * 
     * @param factory factory para criar os componentes
     */
    public Aplicacao(UIFactory factory) {
        this.factory = factory;
    }
    
    /**
     * Inicializa a interface da aplicação
     */
    public void criarInterface() {
        System.out.println("\n🎨 Criando interface com: " + factory.getNomeTema());
        System.out.println("=".repeat(60));
        
        // Cria os componentes usando a factory
        this.botao = factory.criarBotao();
        this.campoTexto = factory.criarCampoTexto();
        
        System.out.println("\n✅ Interface criada com sucesso!");
    }
    
    /**
     * Renderiza todos os componentes da interface
     */
    public void renderizar() {
        System.out.println("\n🖼️ Renderizando interface...");
        System.out.println("-".repeat(60));
        
        if (botao != null) {
            botao.renderizar();
            System.out.println();
        }
        
        if (campoTexto != null) {
            campoTexto.renderizar();
        }
    }
    
    /**
     * Simula interação com os componentes
     */
    public void interagir() {
        System.out.println("\n🎮 Simulando interações...");
        System.out.println("-".repeat(60));
        
        if (campoTexto != null) {
            campoTexto.setPlaceholder("Nome de usuário");
        }
        
        System.out.println();
        
        if (botao != null) {
            botao.onClick();
        }
    }
    
    /**
     * Exibe informações sobre os estilos
     */
    public void exibirInformacoes() {
        System.out.println("\n📋 Informações dos componentes:");
        System.out.println("-".repeat(60));
        
        if (botao != null) {
            System.out.println("Botão: " + botao.getEstilo());
        }
        
        if (campoTexto != null) {
            System.out.println("Campo: " + campoTexto.getEstilo());
        }
    }
    
    /**
     * Altera o tema da aplicação
     * 
     * @param novaFactory nova factory com outro tema
     */
    public void alterarTema(UIFactory novaFactory) {
        System.out.println("\n🔄 Alterando tema da aplicação...");
        this.factory = novaFactory;
        criarInterface();
    }
}
