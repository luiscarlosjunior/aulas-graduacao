/**
 * Interface abstrata para botões
 * 
 * Define o contrato que todos os botões devem seguir,
 * independente do tema da UI.
 * 
 * @author Aulas Graduação
 */
public interface Botao {
    
    /**
     * Renderiza o botão na interface
     */
    void renderizar();
    
    /**
     * Evento quando o botão é clicado
     */
    void onClick();
    
    /**
     * Retorna o estilo do botão
     * 
     * @return descrição do estilo
     */
    String getEstilo();
}
