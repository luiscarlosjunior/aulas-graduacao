/**
 * Interface abstrata para campos de texto
 * 
 * Define o contrato que todos os campos de texto devem seguir,
 * independente do tema da UI.
 * 
 * @author Aulas Graduação
 */
public interface CampoTexto {
    
    /**
     * Renderiza o campo de texto na interface
     */
    void renderizar();
    
    /**
     * Define o placeholder do campo
     * 
     * @param texto texto do placeholder
     */
    void setPlaceholder(String texto);
    
    /**
     * Retorna o estilo do campo
     * 
     * @return descrição do estilo
     */
    String getEstilo();
}
