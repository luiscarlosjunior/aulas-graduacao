/**
 * Factory concreta para criar componentes com tema claro
 * 
 * Implementa UIFactory para criar uma família de produtos
 * relacionados com estilo claro/light mode.
 * 
 * @author Aulas Graduação
 */
public class UIFactoryClaro implements UIFactory {
    
    @Override
    public Botao criarBotao() {
        System.out.println("🏭 Factory Claro: Criando botão...");
        return new BotaoClaro();
    }
    
    @Override
    public CampoTexto criarCampoTexto() {
        System.out.println("🏭 Factory Claro: Criando campo de texto...");
        return new CampoTextoClaro();
    }
    
    @Override
    public String getNomeTema() {
        return "Light Mode (Tema Claro)";
    }
}
