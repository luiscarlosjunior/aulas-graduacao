/**
 * Interface da Abstract Factory para criar componentes de UI
 * 
 * Define o contrato para criar famílias de objetos relacionados
 * (botões e campos de texto) sem especificar suas classes concretas.
 * 
 * @author Aulas Graduação
 */
public interface UIFactory {
    
    /**
     * Cria um botão do tema específico
     * 
     * @return instância de Botao
     */
    Botao criarBotao();
    
    /**
     * Cria um campo de texto do tema específico
     * 
     * @return instância de CampoTexto
     */
    CampoTexto criarCampoTexto();
    
    /**
     * Retorna o nome do tema
     * 
     * @return nome do tema
     */
    String getNomeTema();
}
