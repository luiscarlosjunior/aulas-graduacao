/**
 * Exemplo seguindo DIP (Dependency Inversion Principle)
 * Dependa de abstrações, não de concreções
 * 
 * BENEFÍCIO: Baixo acoplamento, fácil trocar implementações,
 * testável com mocks.
 */

// ✅ DIP: Abstração definida por módulo de alto nível
interface RepositorioDados {
    void salvar(String dados);
    String buscar(String id);
}

// ✅ Implementações concretas dependem da abstração
class MySQLDatabase implements RepositorioDados {
    @Override
    public void salvar(String dados) {
        System.out.println("Salvando no MySQL: " + dados);
    }
    
    @Override
    public String buscar(String id) {
        return "Dados do MySQL com id: " + id;
    }
}

class PostgreSQLDatabase implements RepositorioDados {
    @Override
    public void salvar(String dados) {
        System.out.println("Salvando no PostgreSQL: " + dados);
    }
    
    @Override
    public String buscar(String id) {
        return "Dados do PostgreSQL com id: " + id;
    }
}

class MongoDBDatabase implements RepositorioDados {
    @Override
    public void salvar(String dados) {
        System.out.println("Salvando no MongoDB: " + dados);
    }
    
    @Override
    public String buscar(String id) {
        return "Dados do MongoDB com id: " + id;
    }
}

// ✅ Mock para testes (não precisa de banco real!)
class RepositorioMockParaTestes implements RepositorioDados {
    @Override
    public void salvar(String dados) {
        System.out.println("Mock: Simulando salvamento de - " + dados);
    }
    
    @Override
    public String buscar(String id) {
        return "Mock: Dados de teste para id: " + id;
    }
}

// ✅ Classe de alto nível depende de abstração
class ProcessadorPedidos {
    private RepositorioDados repositorio;
    
    // ✅ Dependency Injection via construtor
    public ProcessadorPedidos(RepositorioDados repositorio) {
        this.repositorio = repositorio;
    }
    
    public void processar(String pedido) {
        System.out.println("Processando pedido: " + pedido);
        repositorio.salvar(pedido);
        
        // ✅ Benefícios:
        // 1. Pode usar qualquer implementação de RepositorioDados
        // 2. Fácil de testar com mock
        // 3. Baixo acoplamento
        // 4. ProcessadorPedidos depende de abstração, não concreção
    }
    
    public String buscar(String id) {
        return repositorio.buscar(id);
    }
}

public class ProcessadorSegueDIP {
    public static void main(String[] args) {
        System.out.println("=== SEGUINDO DIP ===");
        
        // ✅ Configuração externa - escolhe implementação
        System.out.println("\n1. Usando MySQL:");
        RepositorioDados repoMySQL = new MySQLDatabase();
        ProcessadorPedidos proc1 = new ProcessadorPedidos(repoMySQL);
        proc1.processar("Pedido #123");
        System.out.println("Buscando: " + proc1.buscar("123"));
        
        // ✅ Fácil trocar implementação
        System.out.println("\n2. Usando MongoDB:");
        RepositorioDados repoMongo = new MongoDBDatabase();
        ProcessadorPedidos proc2 = new ProcessadorPedidos(repoMongo);
        proc2.processar("Pedido #456");
        System.out.println("Buscando: " + proc2.buscar("456"));
        
        // ✅ Usando PostgreSQL
        System.out.println("\n3. Usando PostgreSQL:");
        RepositorioDados repoPostgres = new PostgreSQLDatabase();
        ProcessadorPedidos proc3 = new ProcessadorPedidos(repoPostgres);
        proc3.processar("Pedido #789");
        
        // ✅ Teste com mock (sem banco real!)
        System.out.println("\n4. Teste com Mock:");
        RepositorioDados mockRepo = new RepositorioMockParaTestes();
        ProcessadorPedidos procTeste = new ProcessadorPedidos(mockRepo);
        procTeste.processar("Pedido teste");
        System.out.println("Buscando: " + procTeste.buscar("test-id"));
        
        System.out.println("\n=== BENEFÍCIOS DO DIP ===");
        System.out.println("1. ✓ Baixo acoplamento - depende de abstração");
        System.out.println("2. ✓ Fácil trocar implementação (MySQL → MongoDB)");
        System.out.println("3. ✓ Testável com mocks (sem banco real)");
        System.out.println("4. ✓ ProcessadorPedidos não conhece implementação concreta");
        System.out.println("5. ✓ Inversão de dependência: ambos dependem de abstração");
        System.out.println("6. ✓ Código flexível e manutenível");
    }
}
