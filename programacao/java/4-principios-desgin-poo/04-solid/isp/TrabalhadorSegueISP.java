/**
 * Exemplo seguindo ISP (Interface Segregation Principle)
 * Interfaces pequenas e focadas
 * 
 * BENEFÍCIO: Cada implementador implementa apenas interfaces relevantes.
 * Clientes dependem apenas do que precisam.
 */

// ✅ ISP: Interfaces pequenas e focadas
interface Trabalhavel {
    void trabalhar();
}

interface Alimentavel {
    void comer();
}

interface Descansavel {
    void dormirNoTrabalho();
}

interface Remuneravel {
    void receberSalario();
}

interface PodeTirarFerias {
    void tirarFerias();
}

interface PodeFazerHoraExtra {
    void fazerHoraExtra();
}

// ✅ Humano implementa interfaces que fazem sentido
class TrabalhadorHumano implements Trabalhavel, Alimentavel, 
                                  Descansavel, Remuneravel,
                                  PodeTirarFerias, PodeFazerHoraExtra {
    private String nome;
    
    public TrabalhadorHumano(String nome) {
        this.nome = nome;
    }
    
    @Override
    public void trabalhar() {
        System.out.println(nome + " trabalhando");
    }
    
    @Override
    public void comer() {
        System.out.println(nome + " comendo");
    }
    
    @Override
    public void dormirNoTrabalho() {
        System.out.println(nome + " cochilando (cochilar)");
    }
    
    @Override
    public void receberSalario() {
        System.out.println(nome + " recebendo salário");
    }
    
    @Override
    public void tirarFerias() {
        System.out.println(nome + " tirando férias");
    }
    
    @Override
    public void fazerHoraExtra() {
        System.out.println(nome + " fazendo hora extra");
    }
}

// ✅ Robô implementa APENAS interfaces relevantes
class Robo implements Trabalhavel, PodeFazerHoraExtra {
    private String modelo;
    
    public Robo(String modelo) {
        this.modelo = modelo;
    }
    
    @Override
    public void trabalhar() {
        System.out.println("Robô " + modelo + " trabalhando 24/7");
    }
    
    @Override
    public void fazerHoraExtra() {
        System.out.println("Robô " + modelo + " fazendo hora extra sem reclamar");
    }
    
    // ✅ Não precisa implementar comer, dormir, salário, férias!
}

// ✅ Clientes dependem apenas de interfaces necessárias
class GerenciadorTrabalho {
    // Depende apenas da interface necessária
    public void atribuirTarefa(Trabalhavel trabalhador) {
        System.out.println("\nAtribuindo tarefa...");
        trabalhador.trabalhar();
    }
}

class DepartamentoPessoal {
    // Depende apenas de interfaces de RH
    public void processarFolhaPagamento(Remuneravel funcionario) {
        System.out.println("\nProcessando pagamento...");
        funcionario.receberSalario();
    }
    
    public void gerenciarFerias(PodeTirarFerias funcionario) {
        System.out.println("\nGerenciando férias...");
        funcionario.tirarFerias();
    }
}

public class TrabalhadorSegueISP {
    public static void main(String[] args) {
        System.out.println("=== SEGUINDO ISP ===");
        
        TrabalhadorHumano humano = new TrabalhadorHumano("João");
        Robo robo = new Robo("R2-D2");
        
        GerenciadorTrabalho gerente = new GerenciadorTrabalho();
        DepartamentoPessoal rh = new DepartamentoPessoal();
        
        // ✅ Ambos podem trabalhar
        gerente.atribuirTarefa(humano);
        gerente.atribuirTarefa(robo);
        
        // ✅ Apenas humano pode receber salário
        rh.processarFolhaPagamento(humano);
        // rh.processarFolhaPagamento(robo); // Erro de compilação - correto!
        
        // ✅ Apenas humano pode tirar férias
        rh.gerenciarFerias(humano);
        // rh.gerenciarFerias(robo); // Erro de compilação - correto!
        
        // ✅ Ambos podem fazer hora extra
        System.out.println("\nHora extra:");
        if (humano instanceof PodeFazerHoraExtra) {
            ((PodeFazerHoraExtra)humano).fazerHoraExtra();
        }
        if (robo instanceof PodeFazerHoraExtra) {
            ((PodeFazerHoraExtra)robo).fazerHoraExtra();
        }
        
        System.out.println("\n=== BENEFÍCIOS DO ISP ===");
        System.out.println("1. ✓ Interfaces pequenas e focadas");
        System.out.println("2. ✓ Cada classe implementa apenas o que precisa");
        System.out.println("3. ✓ Sem métodos não suportados (sem exceções)");
        System.out.println("4. ✓ Clientes dependem apenas de interfaces necessárias");
        System.out.println("5. ✓ Erros detectados em tempo de compilação");
        System.out.println("6. ✓ Código mais seguro e fácil de usar");
    }
}
