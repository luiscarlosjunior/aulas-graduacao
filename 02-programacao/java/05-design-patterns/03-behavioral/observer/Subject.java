/**
 * Interface Subject
 * 
 * Define operações para gerenciar e notificar observers.
 * Qualquer objeto que queira ser observado deve implementar esta interface.
 */
public interface Subject {
    /**
     * Anexa um observer à lista de observadores
     * 
     * @param observer O observer a ser adicionado
     */
    void attach(Observer observer);
    
    /**
     * Remove um observer da lista de observadores
     * 
     * @param observer O observer a ser removido
     */
    void detach(Observer observer);
    
    /**
     * Notifica todos os observers sobre mudanças de estado
     */
    void notifyObservers();
}
