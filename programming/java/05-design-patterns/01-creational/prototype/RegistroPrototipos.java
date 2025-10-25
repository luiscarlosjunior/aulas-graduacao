import java.util.HashMap;
import java.util.Map;

/**
 * Registro de protótipos (Prototype Registry)
 * 
 * Mantém um cache de protótipos pré-configurados que podem
 * ser clonados quando necessário.
 * 
 * @author Aulas Graduação
 */
public class RegistroPrototipos {
    
    private Map<String, Prototipo> prototipos = new HashMap<>();
    
    /**
     * Registra um protótipo com uma chave
     * 
     * @param chave identificador do protótipo
     * @param prototipo objeto a ser registrado
     */
    public void registrar(String chave, Prototipo prototipo) {
        prototipos.put(chave, prototipo);
        System.out.println("📝 Protótipo registrado: " + chave);
    }
    
    /**
     * Remove um protótipo do registro
     * 
     * @param chave identificador do protótipo
     */
    public void remover(String chave) {
        if (prototipos.containsKey(chave)) {
            prototipos.remove(chave);
            System.out.println("🗑️ Protótipo removido: " + chave);
        } else {
            System.out.println("⚠️ Protótipo não encontrado: " + chave);
        }
    }
    
    /**
     * Clona um protótipo registrado
     * 
     * @param chave identificador do protótipo
     * @return clone do protótipo ou null se não encontrado
     */
    public Prototipo clonar(String chave) {
        if (prototipos.containsKey(chave)) {
            System.out.println("🔄 Clonando protótipo: " + chave);
            return prototipos.get(chave).clonar();
        } else {
            System.out.println("❌ Protótipo não encontrado: " + chave);
            return null;
        }
    }
    
    /**
     * Verifica se um protótipo está registrado
     * 
     * @param chave identificador do protótipo
     * @return true se existe, false caso contrário
     */
    public boolean existe(String chave) {
        return prototipos.containsKey(chave);
    }
    
    /**
     * Lista todos os protótipos registrados
     */
    public void listar() {
        System.out.println("\n📋 PROTÓTIPOS REGISTRADOS:");
        System.out.println("-".repeat(40));
        
        if (prototipos.isEmpty()) {
            System.out.println("Nenhum protótipo registrado.");
        } else {
            for (String chave : prototipos.keySet()) {
                System.out.println("  - " + chave);
            }
        }
        System.out.println("-".repeat(40));
    }
    
    /**
     * Retorna o número de protótipos registrados
     * 
     * @return quantidade de protótipos
     */
    public int tamanho() {
        return prototipos.size();
    }
    
    /**
     * Limpa todos os protótipos
     */
    public void limpar() {
        prototipos.clear();
        System.out.println("🧹 Todos os protótipos foram removidos");
    }
}
