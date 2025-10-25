/**
 * Implementação concreta de Botao para tema claro
 * 
 * @author Aulas Graduação
 */
public class BotaoClaro implements Botao {
    
    private String texto;
    
    public BotaoClaro() {
        this.texto = "Botão";
    }
    
    @Override
    public void renderizar() {
        System.out.println("☀️ Renderizando botão com tema claro");
        System.out.println("   Cor de fundo: #ffffff (Branco)");
        System.out.println("   Cor do texto: #333333 (Cinza escuro)");
        System.out.println("   Borda: 1px solid #cccccc");
        System.out.println("   Sombra: 0 1px 3px rgba(0,0,0,0.2)");
    }
    
    @Override
    public void onClick() {
        System.out.println("🖱️ Botão claro clicado!");
        System.out.println("   Animação: Feedback visual com leve escurecimento");
    }
    
    @Override
    public String getEstilo() {
        return "Light Theme Button - Limpo e profissional para uso diurno";
    }
    
    public void setTexto(String texto) {
        this.texto = texto;
    }
    
    public String getTexto() {
        return texto;
    }
}
