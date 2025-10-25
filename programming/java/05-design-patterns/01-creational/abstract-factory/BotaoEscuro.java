/**
 * Implementação concreta de Botao para tema escuro
 * 
 * @author Aulas Graduação
 */
public class BotaoEscuro implements Botao {
    
    private String texto;
    
    public BotaoEscuro() {
        this.texto = "Botão";
    }
    
    @Override
    public void renderizar() {
        System.out.println("🌙 Renderizando botão com tema escuro");
        System.out.println("   Cor de fundo: #1a1a1a (Preto)");
        System.out.println("   Cor do texto: #ffffff (Branco)");
        System.out.println("   Borda: 1px solid #404040");
        System.out.println("   Sombra: 0 2px 4px rgba(0,0,0,0.8)");
    }
    
    @Override
    public void onClick() {
        System.out.println("🖱️ Botão escuro clicado!");
        System.out.println("   Animação: Ripple effect em cinza claro");
    }
    
    @Override
    public String getEstilo() {
        return "Dark Theme Button - Elegante e moderno para ambientes noturnos";
    }
    
    public void setTexto(String texto) {
        this.texto = texto;
    }
    
    public String getTexto() {
        return texto;
    }
}
