/**
 * Implementação concreta de CampoTexto para tema claro
 * 
 * @author Aulas Graduação
 */
public class CampoTextoClaro implements CampoTexto {
    
    private String placeholder;
    
    public CampoTextoClaro() {
        this.placeholder = "Digite aqui...";
    }
    
    @Override
    public void renderizar() {
        System.out.println("☀️ Renderizando campo de texto com tema claro");
        System.out.println("   Cor de fundo: #ffffff (Branco)");
        System.out.println("   Cor do texto: #333333 (Cinza escuro)");
        System.out.println("   Placeholder: " + placeholder + " (em #999999)");
        System.out.println("   Borda: 1px solid #dddddd");
        System.out.println("   Focus: Borda #0066cc (azul)");
    }
    
    @Override
    public void setPlaceholder(String texto) {
        this.placeholder = texto;
        System.out.println("   Placeholder alterado para: " + texto);
    }
    
    @Override
    public String getEstilo() {
        return "Light Theme Input - Claro e profissional para uso corporativo";
    }
    
    public String getPlaceholder() {
        return placeholder;
    }
}
