import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * DatabaseConnection - Utilitário para conexão com banco de dados
 * Implementa padrão Singleton para gerenciar conexões
 * 
 * @author Apresentação Java Web
 */
public class DatabaseConnection {
    
    // Configurações do banco (em produção, usar variáveis de ambiente)
    private static final String URL = "jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1";
    private static final String DRIVER = "org.h2.Driver";
    private static final String USERNAME = "sa";
    private static final String PASSWORD = "";
    
    // Instância singleton
    private static DatabaseConnection instance;
    private Connection connection;
    
    // Construtor privado para Singleton
    private DatabaseConnection() {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Driver do banco de dados não encontrado", e);
        }
    }
    
    /**
     * Obter instância singleton
     */
    public static synchronized DatabaseConnection getInstance() {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }
    
    /**
     * Obter conexão com o banco de dados
     */
    public Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            Properties props = new Properties();
            props.setProperty("user", USERNAME);
            props.setProperty("password", PASSWORD);
            
            connection = DriverManager.getConnection(URL, props);
            
            // Para H2, habilitar modo compatibilidade MySQL
            connection.createStatement().execute("SET MODE MySQL");
        }
        
        return connection;
    }
    
    /**
     * Fechar conexão
     */
    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                connection = null;
            }
        } catch (SQLException e) {
            System.err.println("Erro ao fechar conexão: " + e.getMessage());
        }
    }
    
    /**
     * Testar conexão com o banco
     */
    public boolean testarConexao() {
        try (Connection conn = getConnection()) {
            return conn != null && !conn.isClosed();
        } catch (SQLException e) {
            System.err.println("Erro ao testar conexão: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Obter informações do banco
     */
    public void exibirInformacoesBanco() {
        try (Connection conn = getConnection()) {
            var metaData = conn.getMetaData();
            
            System.out.println("=== Informações do Banco de Dados ===");
            System.out.println("Driver: " + metaData.getDriverName());
            System.out.println("Versão do Driver: " + metaData.getDriverVersion());
            System.out.println("URL: " + metaData.getURL());
            System.out.println("Usuário: " + metaData.getUserName());
            System.out.println("====================================");
            
        } catch (SQLException e) {
            System.err.println("Erro ao obter informações: " + e.getMessage());
        }
    }
    
    /**
     * Configuração alternativa para MySQL
     */
    public static class MySQLConfig {
        public static DatabaseConnection createMySQLConnection(String host, String database, 
                                                              String username, String password) {
            return new DatabaseConnection() {
                @Override
                public Connection getConnection() throws SQLException {
                    String url = String.format("jdbc:mysql://%s/%s?useSSL=false&serverTimezone=UTC", 
                                             host, database);
                    
                    Properties props = new Properties();
                    props.setProperty("user", username);
                    props.setProperty("password", password);
                    props.setProperty("useUnicode", "true");
                    props.setProperty("characterEncoding", "utf8");
                    
                    return DriverManager.getConnection(url, props);
                }
            };
        }
    }
    
    /**
     * Configuração alternativa para PostgreSQL
     */
    public static class PostgreSQLConfig {
        public static DatabaseConnection createPostgreSQLConnection(String host, String database,
                                                                   String username, String password) {
            return new DatabaseConnection() {
                @Override
                public Connection getConnection() throws SQLException {
                    String url = String.format("jdbc:postgresql://%s/%s", host, database);
                    
                    Properties props = new Properties();
                    props.setProperty("user", username);
                    props.setProperty("password", password);
                    props.setProperty("ssl", "false");
                    
                    return DriverManager.getConnection(url, props);
                }
            };
        }
    }
    
    /**
     * Pool de conexões simples (para demonstração)
     */
    public static class SimpleConnectionPool {
        private static final int POOL_SIZE = 10;
        private Connection[] connections = new Connection[POOL_SIZE];
        private boolean[] inUse = new boolean[POOL_SIZE];
        private DatabaseConnection dbConfig;
        
        public SimpleConnectionPool(DatabaseConnection dbConfig) {
            this.dbConfig = dbConfig;
            initializePool();
        }
        
        private void initializePool() {
            try {
                for (int i = 0; i < POOL_SIZE; i++) {
                    connections[i] = dbConfig.getConnection();
                    inUse[i] = false;
                }
            } catch (SQLException e) {
                throw new RuntimeException("Erro ao inicializar pool de conexões", e);
            }
        }
        
        public synchronized Connection getConnection() throws SQLException {
            for (int i = 0; i < POOL_SIZE; i++) {
                if (!inUse[i]) {
                    inUse[i] = true;
                    return connections[i];
                }
            }
            throw new SQLException("Pool de conexões esgotado");
        }
        
        public synchronized void releaseConnection(Connection conn) {
            for (int i = 0; i < POOL_SIZE; i++) {
                if (connections[i] == conn) {
                    inUse[i] = false;
                    break;
                }
            }
        }
        
        public void closePool() {
            for (Connection conn : connections) {
                try {
                    if (conn != null && !conn.isClosed()) {
                        conn.close();
                    }
                } catch (SQLException e) {
                    System.err.println("Erro ao fechar conexão do pool: " + e.getMessage());
                }
            }
        }
    }
}