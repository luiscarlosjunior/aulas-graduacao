/**
 * Singleton para gerenciar conexão com banco de dados
 * 
 * Implementação thread-safe usando double-checked locking
 * para garantir performance e segurança em ambientes concorrentes.
 * 
 * @author Aulas Graduação
 */
public class DatabaseConnection {
    
    // Instância única da classe (volatile para thread-safety)
    private static volatile DatabaseConnection instance;
    
    // Simulação de dados de conexão
    private String url;
    private String usuario;
    private boolean conectado;
    private int numeroConexoes;
    
    /**
     * Construtor privado para impedir instanciação externa
     */
    private DatabaseConnection() {
        // Simula configuração inicial da conexão
        this.url = "jdbc:postgresql://localhost:5432/app_db";
        this.usuario = "app_user";
        this.conectado = false;
        this.numeroConexoes = 0;
        
        System.out.println("🔌 Conexão com banco de dados criada");
        System.out.println("   URL: " + url);
        System.out.println("   Usuário: " + usuario);
    }
    
    /**
     * Método para obter a única instância da classe
     * 
     * Usa double-checked locking para otimizar performance
     * em cenários multi-thread.
     * 
     * @return única instância de DatabaseConnection
     */
    public static DatabaseConnection getInstance() {
        // Primeira verificação (sem sincronização para performance)
        if (instance == null) {
            // Sincroniza apenas quando necessário
            synchronized (DatabaseConnection.class) {
                // Segunda verificação (dentro do bloco sincronizado)
                if (instance == null) {
                    instance = new DatabaseConnection();
                }
            }
        }
        return instance;
    }
    
    /**
     * Conecta ao banco de dados
     */
    public void conectar() {
        if (!conectado) {
            // Simula processo de conexão
            try {
                System.out.println("🔄 Conectando ao banco de dados...");
                Thread.sleep(1000); // Simula tempo de conexão
                conectado = true;
                numeroConexoes++;
                System.out.println("✅ Conectado com sucesso! Conexão #" + numeroConexoes);
            } catch (InterruptedException e) {
                System.err.println("❌ Erro ao conectar: " + e.getMessage());
            }
        } else {
            System.out.println("ℹ️ Já está conectado ao banco de dados");
        }
    }
    
    /**
     * Desconecta do banco de dados
     */
    public void desconectar() {
        if (conectado) {
            conectado = false;
            System.out.println("🔌 Desconectado do banco de dados");
        } else {
            System.out.println("ℹ️ Não há conexão ativa para desconectar");
        }
    }
    
    /**
     * Executa uma query SQL (simulada)
     * 
     * @param sql comando SQL a ser executado
     */
    public void executarQuery(String sql) {
        if (!conectado) {
            System.out.println("⚠️ Conectando automaticamente...");
            conectar();
        }
        
        System.out.println("🔍 Executando SQL: " + sql);
        
        // Simula execução
        try {
            Thread.sleep(100); // Simula tempo de execução
            System.out.println("✅ Query executada com sucesso");
        } catch (InterruptedException e) {
            System.err.println("❌ Erro na execução: " + e.getMessage());
        }
    }
    
    /**
     * Retorna informações sobre a conexão
     * 
     * @return status da conexão
     */
    public String getStatusConexao() {
        return String.format("Status: %s | URL: %s | Usuário: %s | Conexões realizadas: %d",
                conectado ? "Conectado" : "Desconectado",
                url, usuario, numeroConexoes);
    }
    
    /**
     * Verifica se está conectado
     * 
     * @return true se conectado, false caso contrário
     */
    public boolean isConectado() {
        return conectado;
    }
    
    /**
     * Fecha todas as conexões e limpa recursos
     */
    public void fecharRecursos() {
        if (conectado) {
            desconectar();
        }
        System.out.println("🧹 Recursos de conexão liberados");
    }
    
    // Getters para informações da conexão
    public String getUrl() {
        return url;
    }
    
    public String getUsuario() {
        return usuario;
    }
    
    public int getNumeroConexoes() {
        return numeroConexoes;
    }
}