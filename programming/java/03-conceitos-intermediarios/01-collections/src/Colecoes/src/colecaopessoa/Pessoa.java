package colecaopessoa;
import java.util.*;

public class Pessoa {
    private String nome;
    private int idade;
    private String email;
    
    public Pessoa(String nome, int idade, String email) {
        this.nome = nome;
        this.idade = idade;
        this.email = email;
    }
    
    // Métodos de acesso (Encapsulamento)
    public String getNome() { return nome; }
    public int getIdade() { return idade; }
    public String getEmail() { return email; }
    
    public void setNome(String nome) { this.nome = nome; }
    public void setIdade(int idade) { this.idade = idade; }
    public void setEmail(String email) { this.email = email; }
    
    // Métodos de comportamento
    public void fazerAniversario() {
        this.idade++;
        System.out.println("   🎂 " + nome + " fez aniversário! Agora tem " + idade + " anos.");
    }
    
    public boolean ehMaiorIdade() {
        return idade >= 18;
    }
    
    // Implementação correta de equals() e hashCode() para uso em Collections
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        
        Pessoa pessoa = (Pessoa) obj;
        return idade == pessoa.idade && 
               Objects.equals(nome, pessoa.nome);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(nome, idade);
    }
    
    @Override
    public String toString() {
        return nome + " (" + idade + " anos, " + email + ")";
    }
}

