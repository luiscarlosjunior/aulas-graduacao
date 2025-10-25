/**
 * Interface para objetos que podem ser clonados
 * 
 * Define o contrato para o padrão Prototype.
 * Classes que implementam esta interface podem ser clonadas.
 * 
 * @author Aulas Graduação
 */
public interface Prototipo extends Cloneable {
    
    /**
     * Clona o objeto atual
     * 
     * @return cópia do objeto
     */
    Prototipo clonar();
    
    /**
     * Exibe informações do objeto
     */
    void exibirInfo();
}
