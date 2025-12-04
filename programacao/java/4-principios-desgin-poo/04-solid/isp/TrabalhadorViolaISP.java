/**
 * Exemplo de violação do ISP (Interface Segregation Principle)
 * Interface "gorda" com muitas responsabilidades
 * 
 * PROBLEMA: Força implementadores a implementar métodos que não precisam,
 * violando ISP.
 */

// ❌ Violação de ISP: Interface com muitas responsabilidades
interface Trabalhador {
    void trabalhar();
    void comer();
    void dormirNoTrabalho();
    void receberSalario();
    void tirarFerias();
    void fazerHoraExtra();
}

// ❌ Robô é forçado a implementar métodos que não fazem sentido
class Robo implements Trabalhador {
    @Override
    public void trabalhar() {
        System.out.println("Robô trabalhando 24/7");
    }
    
    @Override
    public void comer() {
        // ❌ Robô não come! Implementação vazia ou exceção?
        throw new UnsupportedOperationException("Robô não come");
    }
    
    @Override
    public void dormirNoTrabalho() {
        // ❌ Robô não dorme!
        throw new UnsupportedOperationException("Robô não dorme");
    }
    
    @Override
    public void receberSalario() {
        // ❌ Robô não recebe salário!
        throw new UnsupportedOperationException("Robô não recebe salário");
    }
    
    @Override
    public void tirarFerias() {
        // ❌ Robô não tira férias!
        throw new UnsupportedOperationException("Robô não tira férias");
    }
    
    @Override
    public void fazerHoraExtra() {
        System.out.println("Robô fazendo hora extra sem reclamar");
    }
}

class TrabalhadorHumano implements Trabalhador {
    @Override
    public void trabalhar() {
        System.out.println("Humano trabalhando");
    }
    
    @Override
    public void comer() {
        System.out.println("Humano comendo");
    }
    
    @Override
    public void dormirNoTrabalho() {
        System.out.println("Humano cochilando (ops!)");
    }
    
    @Override
    public void receberSalario() {
        System.out.println("Humano recebendo salário");
    }
    
    @Override
    public void tirarFerias() {
        System.out.println("Humano tirando férias");
    }
    
    @Override
    public void fazerHoraExtra() {
        System.out.println("Humano fazendo hora extra");
    }
}

public class TrabalhadorViolaISP {
    public static void main(String[] args) {
        System.out.println("=== VIOLAÇÃO DO ISP ===");
        
        System.out.println("\nTrabalhador Humano:");
        Trabalhador humano = new TrabalhadorHumano();
        humano.trabalhar();
        humano.comer();
        humano.receberSalario();
        
        System.out.println("\nRobô:");
        Trabalhador robo = new Robo();
        robo.trabalhar();
        robo.fazerHoraExtra();
        
        System.out.println("\nTentando chamar métodos inválidos no robô:");
        try {
            robo.comer();
        } catch (UnsupportedOperationException e) {
            System.out.println("✗ Erro: " + e.getMessage());
        }
        
        try {
            robo.receberSalario();
        } catch (UnsupportedOperationException e) {
            System.out.println("✗ Erro: " + e.getMessage());
        }
        
        System.out.println("\n=== PROBLEMAS ===");
        System.out.println("1. Robô forçado a implementar métodos irrelevantes");
        System.out.println("2. Exceções em runtime indicam design incorreto");
        System.out.println("3. Cliente pode chamar métodos inválidos");
        System.out.println("4. Interface 'gorda' dificulta entendimento");
        System.out.println("5. Viola ISP: clientes dependem de métodos que não usam");
    }
}
