/**
 * Exemplo de violação do DIP (Dependency Inversion Principle)
 * Classe de alto nível depende diretamente de classe de baixo nível
 * 
 * PROBLEMA: Alto acoplamento com implementação específica.
 * Impossível trocar implementação ou testar isoladamente.
 */

// ❌ Classe de baixo nível concreta
class MySQLDatabase {
    public void salvarDados(String dados) {
        System.out.println("Salvando no MySQL: " + dados);
        // Lógica específica do MySQL
    }
    
    public String buscarDados(String id) {
        return "Dados do MySQL com id: " + id;
    }
}

// ❌ Classe de alto nível depende diretamente de baixo nível
class ProcessadorPedidos {
    // ❌ Dependência direta de classe concreta
    private MySQLDatabase database = new MySQLDatabase();
    
    public void processar(String pedido) {
        System.out.println("Processando pedido: " + pedido);
        database.salvarDados(pedido);
        
        // ❌ Problemas:
        // 1. Impossível trocar para PostgreSQL sem modificar esta classe
        // 2. Impossível testar sem MySQL real
        // 3. Alto acoplamento com implementação específica
        // 4. ProcessadorPedidos (alto nível) depende de MySQLDatabase (baixo nível)
    }
    
    public String buscar(String id) {
        return database.buscarDados(id);
    }
}

public class ProcessadorViolaDIP {
    public static void main(String[] args) {
        System.out.println("=== VIOLAÇÃO DO DIP ===");
        
        ProcessadorPedidos processador = new ProcessadorPedidos();
        processador.processar("Pedido #123");
        String dados = processador.buscar("123");
        System.out.println("Recuperado: " + dados);
        
        System.out.println("\n=== PROBLEMAS ===");
        System.out.println("1. ProcessadorPedidos depende DIRETAMENTE de MySQLDatabase");
        System.out.println("2. Para trocar para PostgreSQL, preciso MODIFICAR ProcessadorPedidos");
        System.out.println("3. Impossível testar ProcessadorPedidos sem banco real");
        System.out.println("4. Alto acoplamento = código rígido e difícil de manter");
        System.out.println("5. Viola DIP: alto nível depende de baixo nível");
        
        System.out.println("\n=== O QUE FAZER? ===");
        System.out.println("ProcessadorPedidos deveria depender de ABSTRAÇÃO");
        System.out.println("MySQLDatabase deveria implementar essa ABSTRAÇÃO");
        System.out.println("Isso INVERTE a dependência - seguindo DIP!");
    }
}
