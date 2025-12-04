/**
 * Factory concreta para criar componentes com tema escuro
 * 
 * Implementa UIFactory para criar uma família de produtos
 * relacionados com estilo escuro/dark mode.
 * 
 * @author Aulas Graduação
 */
public class UIFactoryEscuro implements UIFactory {
    
    @Override
    public Botao criarBotao() {
        System.out.println("🏭 Factory Escuro: Criando botão...");
        return new BotaoEscuro();
    }
    
    @Override
    public CampoTexto criarCampoTexto() {
        System.out.println("🏭 Factory Escuro: Criando campo de texto...");
        return new CampoTextoEscuro();
    }
    
    @Override
    public String getNomeTema() {
        return "Dark Mode (Tema Escuro)";
    }
}
