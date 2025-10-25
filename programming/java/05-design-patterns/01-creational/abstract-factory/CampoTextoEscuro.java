/**
 * Implementação concreta de CampoTexto para tema escuro
 * 
 * @author Aulas Graduação
 */
public class CampoTextoEscuro implements CampoTexto {
    
    private String placeholder;
    
    public CampoTextoEscuro() {
        this.placeholder = "Digite aqui...";
    }
    
    @Override
    public void renderizar() {
        System.out.println("🌙 Renderizando campo de texto com tema escuro");
        System.out.println("   Cor de fundo: #2a2a2a (Cinza escuro)");
        System.out.println("   Cor do texto: #ffffff (Branco)");
        System.out.println("   Placeholder: " + placeholder + " (em #888888)");
        System.out.println("   Borda: 1px solid #404040");
        System.out.println("   Focus: Borda #4a9eff (azul)");
    }
    
    @Override
    public void setPlaceholder(String texto) {
        this.placeholder = texto;
        System.out.println("   Placeholder alterado para: " + texto);
    }
    
    @Override
    public String getEstilo() {
        return "Dark Theme Input - Suave para os olhos em ambientes escuros";
    }
    
    public String getPlaceholder() {
        return placeholder;
    }
}
