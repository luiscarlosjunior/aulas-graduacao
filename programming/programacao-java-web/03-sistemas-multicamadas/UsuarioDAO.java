import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * UsuarioDAO - Camada de Dados
 * Data Access Object para operações de banco de dados com usuários
 * Implementa padrão DAO para abstrair acesso aos dados
 * 
 * @author Apresentação Java Web
 */
public class UsuarioDAO {
    
    private DatabaseConnection dbConnection;
    
    public UsuarioDAO() {
        this.dbConnection = new DatabaseConnection();
    }
    
    public UsuarioDAO(DatabaseConnection dbConnection) {
        this.dbConnection = dbConnection;
    }
    
    /**
     * Criar um novo usuário no banco de dados
     */
    public UsuarioEntity criar(UsuarioEntity usuario) throws SQLException {
        String sql = """
            INSERT INTO usuarios (nome, email, senha, telefone, data_criacao, ativo, perfil)
            VALUES (?, ?, ?, ?, ?, ?, ?)
            """;
        
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());
            stmt.setString(3, usuario.getSenha());
            stmt.setString(4, usuario.getTelefone());
            stmt.setTimestamp(5, Timestamp.valueOf(usuario.getDataCriacao()));
            stmt.setBoolean(6, usuario.isAtivo());
            stmt.setString(7, usuario.getPerfil());
            
            int affectedRows = stmt.executeUpdate();
            
            if (affectedRows == 0) {
                throw new SQLException("Falha ao criar usuário, nenhuma linha afetada.");
            }
            
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    usuario.setId(generatedKeys.getLong(1));
                } else {
                    throw new SQLException("Falha ao criar usuário, ID não foi gerado.");
                }
            }
            
            return usuario;
        }
    }
    
    /**
     * Buscar usuário por ID
     */
    public UsuarioEntity buscarPorId(Long id) throws SQLException {
        String sql = """
            SELECT id, nome, email, senha, telefone, data_criacao, 
                   data_ultimo_login, ativo, perfil
            FROM usuarios WHERE id = ?
            """;
        
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setLong(1, id);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapearResultSet(rs);
                }
                return null;
            }
        }
    }
    
    /**
     * Buscar usuário por email
     */
    public UsuarioEntity buscarPorEmail(String email) throws SQLException {
        String sql = """
            SELECT id, nome, email, senha, telefone, data_criacao,
                   data_ultimo_login, ativo, perfil
            FROM usuarios WHERE email = ? AND ativo = true
            """;
        
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, email);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapearResultSet(rs);
                }
                return null;
            }
        }
    }
    
    /**
     * Listar todos os usuários ativos
     */
    public List<UsuarioEntity> listarAtivos() throws SQLException {
        String sql = """
            SELECT id, nome, email, senha, telefone, data_criacao,
                   data_ultimo_login, ativo, perfil
            FROM usuarios WHERE ativo = true
            ORDER BY nome
            """;
        
        List<UsuarioEntity> usuarios = new ArrayList<>();
        
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                usuarios.add(mapearResultSet(rs));
            }
        }
        
        return usuarios;
    }
    
    /**
     * Listar usuários com paginação
     */
    public List<UsuarioEntity> listarComPaginacao(int pagina, int tamanho) throws SQLException {
        String sql = """
            SELECT id, nome, email, senha, telefone, data_criacao,
                   data_ultimo_login, ativo, perfil
            FROM usuarios WHERE ativo = true
            ORDER BY nome
            LIMIT ? OFFSET ?
            """;
        
        List<UsuarioEntity> usuarios = new ArrayList<>();
        int offset = (pagina - 1) * tamanho;
        
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, tamanho);
            stmt.setInt(2, offset);
            
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    usuarios.add(mapearResultSet(rs));
                }
            }
        }
        
        return usuarios;
    }
    
    /**
     * Contar total de usuários ativos
     */
    public int contarUsuariosAtivos() throws SQLException {
        String sql = "SELECT COUNT(*) FROM usuarios WHERE ativo = true";
        
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            if (rs.next()) {
                return rs.getInt(1);
            }
            return 0;
        }
    }
    
    /**
     * Atualizar dados do usuário
     */
    public boolean atualizar(UsuarioEntity usuario) throws SQLException {
        String sql = """
            UPDATE usuarios SET 
                nome = ?, email = ?, telefone = ?, perfil = ?, ativo = ?
            WHERE id = ?
            """;
        
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());
            stmt.setString(3, usuario.getTelefone());
            stmt.setString(4, usuario.getPerfil());
            stmt.setBoolean(5, usuario.isAtivo());
            stmt.setLong(6, usuario.getId());
            
            return stmt.executeUpdate() > 0;
        }
    }
    
    /**
     * Atualizar senha do usuário
     */
    public boolean atualizarSenha(Long id, String novaSenha) throws SQLException {
        String sql = "UPDATE usuarios SET senha = ? WHERE id = ?";
        
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, novaSenha);
            stmt.setLong(2, id);
            
            return stmt.executeUpdate() > 0;
        }
    }
    
    /**
     * Atualizar último login
     */
    public boolean atualizarUltimoLogin(Long id) throws SQLException {
        String sql = "UPDATE usuarios SET data_ultimo_login = ? WHERE id = ?";
        
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setTimestamp(1, Timestamp.valueOf(LocalDateTime.now()));
            stmt.setLong(2, id);
            
            return stmt.executeUpdate() > 0;
        }
    }
    
    /**
     * Deletar usuário (soft delete - marca como inativo)
     */
    public boolean deletar(Long id) throws SQLException {
        String sql = "UPDATE usuarios SET ativo = false WHERE id = ?";
        
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setLong(1, id);
            return stmt.executeUpdate() > 0;
        }
    }
    
    /**
     * Deletar permanentemente
     */
    public boolean deletarPermanente(Long id) throws SQLException {
        String sql = "DELETE FROM usuarios WHERE id = ?";
        
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setLong(1, id);
            return stmt.executeUpdate() > 0;
        }
    }
    
    /**
     * Verificar se email já existe
     */
    public boolean emailExiste(String email) throws SQLException {
        String sql = "SELECT COUNT(*) FROM usuarios WHERE email = ? AND ativo = true";
        
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, email);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
                return false;
            }
        }
    }
    
    /**
     * Buscar usuários por perfil
     */
    public List<UsuarioEntity> buscarPorPerfil(String perfil) throws SQLException {
        String sql = """
            SELECT id, nome, email, senha, telefone, data_criacao,
                   data_ultimo_login, ativo, perfil
            FROM usuarios WHERE perfil = ? AND ativo = true
            ORDER BY nome
            """;
        
        List<UsuarioEntity> usuarios = new ArrayList<>();
        
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, perfil);
            
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    usuarios.add(mapearResultSet(rs));
                }
            }
        }
        
        return usuarios;
    }
    
    /**
     * Mapear ResultSet para UsuarioEntity
     */
    private UsuarioEntity mapearResultSet(ResultSet rs) throws SQLException {
        UsuarioEntity usuario = new UsuarioEntity();
        
        usuario.setId(rs.getLong("id"));
        usuario.setNome(rs.getString("nome"));
        usuario.setEmail(rs.getString("email"));
        usuario.setSenha(rs.getString("senha"));
        usuario.setTelefone(rs.getString("telefone"));
        usuario.setPerfil(rs.getString("perfil"));
        usuario.setAtivo(rs.getBoolean("ativo"));
        
        Timestamp dataCriacao = rs.getTimestamp("data_criacao");
        if (dataCriacao != null) {
            usuario.setDataCriacao(dataCriacao.toLocalDateTime());
        }
        
        Timestamp dataUltimoLogin = rs.getTimestamp("data_ultimo_login");
        if (dataUltimoLogin != null) {
            usuario.setDataUltimoLogin(dataUltimoLogin.toLocalDateTime());
        }
        
        return usuario;
    }
    
    /**
     * Criar tabela de usuários (para testes)
     */
    public void criarTabela() throws SQLException {
        String sql = """
            CREATE TABLE IF NOT EXISTS usuarios (
                id BIGINT AUTO_INCREMENT PRIMARY KEY,
                nome VARCHAR(100) NOT NULL,
                email VARCHAR(150) NOT NULL UNIQUE,
                senha VARCHAR(255) NOT NULL,
                telefone VARCHAR(20),
                data_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                data_ultimo_login TIMESTAMP NULL,
                ativo BOOLEAN DEFAULT true,
                perfil VARCHAR(20) DEFAULT 'USER'
            )
            """;
        
        try (Connection conn = dbConnection.getConnection();
             Statement stmt = conn.createStatement()) {
            
            stmt.execute(sql);
        }
    }
}