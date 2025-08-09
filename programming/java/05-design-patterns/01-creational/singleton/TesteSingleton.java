/**
 * Teste dos padrões Singleton implementados
 * 
 * Demonstra o uso de diferentes implementações de Singleton
 * e testa características importantes como thread-safety.
 * 
 * @author Aulas Graduação
 */
public class TesteSingleton {
    
    public static void main(String[] args) {
        System.out.println("=== TESTE DOS PADRÕES SINGLETON ===\n");
        
        testarDatabaseConnection();
        System.out.println();
        
        testarLogger();
        System.out.println();
        
        testarInstanciaUnica();
        System.out.println();
        
        testarThreadSafety();
    }
    
    /**
     * Testa o Singleton DatabaseConnection
     */
    private static void testarDatabaseConnection() {
        System.out.println("--- TESTE DATABASE CONNECTION ---");
        
        // Primeira chamada - cria a instância
        DatabaseConnection db1 = DatabaseConnection.getInstance();
        System.out.println("Status: " + db1.getStatusConexao());
        
        // Segunda chamada - retorna a mesma instância
        DatabaseConnection db2 = DatabaseConnection.getInstance();
        
        // Verifica se são a mesma instância
        System.out.println("db1 == db2? " + (db1 == db2));
        
        // Usando a conexão
        db1.conectar();
        db1.executarQuery("SELECT * FROM usuarios");
        db1.executarQuery("SELECT COUNT(*) FROM produtos");
        
        // db2 é a mesma instância, então já está conectado
        System.out.println("db2 está conectado? " + db2.isConectado());
        
        db2.executarQuery("UPDATE configuracoes SET valor = 'novo'");
        db1.desconectar();
        
        System.out.println("Status final: " + db1.getStatusConexao());
    }
    
    /**
     * Testa o Singleton Logger
     */
    private static void testarLogger() {
        System.out.println("--- TESTE LOGGER ---");
        
        // Usando o Logger enum
        Logger logger = Logger.INSTANCE;
        
        // Testando diferentes níveis de log
        logger.info("Aplicação iniciada");
        logger.debug("Variável X = 10");
        logger.warning("Memoria em 85%");
        logger.error("Falha na conexão com API externa");
        
        // Alterando configurações
        logger.setDebug(false);
        logger.debug("Esta mensagem não aparecerá");
        
        logger.setDebug(true);
        logger.debug("Esta mensagem aparecerá");
        
        // Verificando estatísticas
        System.out.println(logger.getEstatisticas());
        
        // Testando instância única
        Logger logger2 = Logger.INSTANCE;
        System.out.println("logger == logger2? " + (logger == logger2));
        
        logger2.info("Mensagem do logger2 (mesma instância)");
        System.out.println("Total de logs: " + logger.getNumeroLogs());
    }
    
    /**
     * Testa se realmente há apenas uma instância
     */
    private static void testarInstanciaUnica() {
        System.out.println("--- TESTE INSTÂNCIA ÚNICA ---");
        
        // Criando múltiplas referências
        DatabaseConnection[] connections = new DatabaseConnection[5];
        
        for (int i = 0; i < connections.length; i++) {
            connections[i] = DatabaseConnection.getInstance();
            System.out.println("Conexão " + i + " criada");
        }
        
        // Verificando se todas são a mesma instância
        boolean todasIguais = true;
        for (int i = 1; i < connections.length; i++) {
            if (connections[0] != connections[i]) {
                todasIguais = false;
                break;
            }
        }
        
        System.out.println("Todas as conexões são a mesma instância? " + todasIguais);
        
        // Testando com Logger também
        Logger[] loggers = {
            Logger.INSTANCE,
            Logger.INSTANCE,
            Logger.INSTANCE
        };
        
        boolean loggersIguais = (loggers[0] == loggers[1]) && (loggers[1] == loggers[2]);
        System.out.println("Todos os loggers são a mesma instância? " + loggersIguais);
    }
    
    /**
     * Testa thread-safety dos Singletons
     */
    private static void testarThreadSafety() {
        System.out.println("--- TESTE THREAD-SAFETY ---");
        
        final int NUM_THREADS = 10;
        final DatabaseConnection[] results = new DatabaseConnection[NUM_THREADS];
        Thread[] threads = new Thread[NUM_THREADS];
        
        // Criando threads que tentam obter a instância simultaneamente
        for (int i = 0; i < NUM_THREADS; i++) {
            final int index = i;
            threads[i] = new Thread(() -> {
                // Simula algum processamento antes de obter a instância
                try {
                    Thread.sleep((int) (Math.random() * 100));
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                
                results[index] = DatabaseConnection.getInstance();
                System.out.println("Thread " + index + " obteve instância");
            });
        }
        
        // Iniciando todas as threads
        for (Thread thread : threads) {
            thread.start();
        }
        
        // Aguardando todas terminarem
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        
        // Verificando se todas obtiveram a mesma instância
        boolean threadSafe = true;
        for (int i = 1; i < results.length; i++) {
            if (results[0] != results[i]) {
                threadSafe = false;
                break;
            }
        }
        
        System.out.println("Singleton é thread-safe? " + threadSafe);
        
        // Teste final com Logger
        Logger.INSTANCE.info("Teste de thread-safety concluído");
        Logger.INSTANCE.info("=== TODOS OS TESTES FINALIZADOS ===");
    }
}