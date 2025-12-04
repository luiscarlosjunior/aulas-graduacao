package colecaopessoa;
import java.util.*;

/**
 * Classe Departamento demonstrando Composição com Collections
 * O departamento "possui" uma lista de funcionários
 */
public class Departamento {
    private String nome;
    private List<Pessoa> funcionarios; // Composição: Departamento "tem" funcionários
    
    public Departamento(String nome) {
        this.nome = nome;
        this.funcionarios = new ArrayList<>(); // Inicialização da composição
    }
    
    public void adicionarFuncionario(Pessoa funcionario) {
        funcionarios.add(funcionario);
    }
    
    public void removerFuncionario(Pessoa funcionario) {
        funcionarios.remove(funcionario);
    }
    
    public Pessoa buscarFuncionario(String nome) {
        return funcionarios.stream()
                          .filter(f -> f.getNome().equalsIgnoreCase(nome))
                          .findFirst()
                          .orElse(null);
    }
    
    public int getTotalFuncionarios() {
        return funcionarios.size();
    }
    
    public double getIdadeMedia() {
        return funcionarios.stream()
                          .mapToInt(Pessoa::getIdade)
                          .average()
                          .orElse(0.0);
    }
    
    public void exibirInformacoes() {
        System.out.println("   Departamento: " + nome);
        System.out.println("   Funcionários:");
        for (int i = 0; i < funcionarios.size(); i++) {
            Pessoa f = funcionarios.get(i);
            System.out.println("     " + (i + 1) + ". " + f.getNome() + " - " + f.getIdade() + " anos");
        }
    }
    
    // Getter defensivo - retorna cópia para manter encapsulamento
    public List<Pessoa> getFuncionarios() {
        return new ArrayList<>(funcionarios);
    }
}
