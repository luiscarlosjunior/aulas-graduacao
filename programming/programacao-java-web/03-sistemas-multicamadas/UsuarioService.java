import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.regex.Pattern;

/**
 * UsuarioService - Camada de Negócio
 * Contém toda a lógica de negócio relacionada aos usuários
 * Implementa validações, regras de negócio e coordena operações
 * 
 * @author Apresentação Java Web
 */
public class UsuarioService {
    
    private UsuarioDAO usuarioDAO;
    private static final Pattern EMAIL_PATTERN = 
        Pattern.compile("^[A-Za-z0-9+_.-]+@([A-Za-z0-9.-]+\\.[A-Za-z]{2,})$");
    private static final Pattern TELEFONE_PATTERN = 
        Pattern.compile("^\\(?\\d{2}\\)?[\\s-]?\\d{4,5}[\\s-]?\\d{4}$");
    
    public UsuarioService() {
        this.usuarioDAO = new UsuarioDAO();
    }
    
    public UsuarioService(UsuarioDAO usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
    }
    
    /**
     * Criar novo usuário com validações de negócio
     */
    public UsuarioEntity criarUsuario(String nome, String email, String senha, 
                                     String telefone, String perfil) throws Exception {
        
        // Validações de entrada
        validarDadosUsuario(nome, email, senha, telefone);
        
        // Verificar se email já existe
        if (usuarioDAO.emailExiste(email)) {
            throw new IllegalArgumentException("Email já está em uso: " + email);
        }
        
        // Validar perfil
        if (perfil != null && !isPerfilValido(perfil)) {
            throw new IllegalArgumentException("Perfil inválido: " + perfil);
        }
        
        // Criar usuário
        UsuarioEntity usuario = new UsuarioEntity(nome, email, criptografarSenha(senha));
        usuario.setTelefone(telefone);
        usuario.setPerfil(perfil != null ? perfil : "USER");
        
        try {
            return usuarioDAO.criar(usuario);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao criar usuário no banco de dados", e);
        }
    }
    
    /**
     * Autenticar usuário
     */
    public UsuarioEntity autenticar(String email, String senha) throws Exception {
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("Email é obrigatório");
        }
        
        if (senha == null || senha.trim().isEmpty()) {
            throw new IllegalArgumentException("Senha é obrigatória");
        }
        
        try {
            UsuarioEntity usuario = usuarioDAO.buscarPorEmail(email);
            
            if (usuario == null) {
                throw new SecurityException("Email ou senha inválidos");
            }
            
            if (!usuario.isAtivo()) {
                throw new SecurityException("Usuário está inativo");
            }
            
            String senhaHash = criptografarSenha(senha);
            if (!senhaHash.equals(usuario.getSenha())) {
                throw new SecurityException("Email ou senha inválidos");
            }
            
            // Atualizar último login
            usuarioDAO.atualizarUltimoLogin(usuario.getId());
            usuario.atualizarUltimoLogin();
            
            return usuario.toSecureUser(); // Retorna sem a senha
            
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao autenticar usuário", e);
        }
    }
    
    /**
     * Buscar usuário por ID
     */
    public UsuarioEntity buscarPorId(Long id) throws Exception {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID inválido");
        }
        
        try {
            UsuarioEntity usuario = usuarioDAO.buscarPorId(id);
            return usuario != null ? usuario.toSecureUser() : null;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar usuário", e);
        }
    }
    
    /**
     * Listar usuários com paginação
     */
    public List<UsuarioEntity> listarUsuarios(int pagina, int tamanho) throws Exception {
        if (pagina <= 0) {
            throw new IllegalArgumentException("Página deve ser maior que zero");
        }
        
        if (tamanho <= 0 || tamanho > 100) {
            throw new IllegalArgumentException("Tamanho deve ser entre 1 e 100");
        }
        
        try {
            List<UsuarioEntity> usuarios = usuarioDAO.listarComPaginacao(pagina, tamanho);
            
            // Remover senhas dos usuários retornados
            return usuarios.stream()
                          .map(UsuarioEntity::toSecureUser)
                          .toList();
                          
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar usuários", e);
        }
    }
    
    /**
     * Contar total de usuários
     */
    public int contarUsuarios() throws Exception {
        try {
            return usuarioDAO.contarUsuariosAtivos();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao contar usuários", e);
        }
    }
    
    /**
     * Atualizar dados do usuário
     */
    public boolean atualizarUsuario(Long id, String nome, String email, 
                                   String telefone, String perfil) throws Exception {
        
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID inválido");
        }
        
        // Buscar usuário existente
        UsuarioEntity usuarioExistente;
        try {
            usuarioExistente = usuarioDAO.buscarPorId(id);
            if (usuarioExistente == null) {
                throw new IllegalArgumentException("Usuário não encontrado");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar usuário", e);
        }
        
        // Validar dados
        validarDadosUsuario(nome, email, null, telefone);
        
        // Verificar se email já existe (se foi alterado)
        if (!email.equals(usuarioExistente.getEmail())) {
            try {
                if (usuarioDAO.emailExiste(email)) {
                    throw new IllegalArgumentException("Email já está em uso: " + email);
                }
            } catch (SQLException e) {
                throw new RuntimeException("Erro ao verificar email", e);
            }
        }
        
        // Validar perfil
        if (perfil != null && !isPerfilValido(perfil)) {
            throw new IllegalArgumentException("Perfil inválido: " + perfil);
        }
        
        // Atualizar dados
        usuarioExistente.setNome(nome);
        usuarioExistente.setEmail(email);
        usuarioExistente.setTelefone(telefone);
        if (perfil != null) {
            usuarioExistente.setPerfil(perfil);
        }
        
        try {
            return usuarioDAO.atualizar(usuarioExistente);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar usuário", e);
        }
    }
    
    /**
     * Alterar senha do usuário
     */
    public boolean alterarSenha(Long id, String senhaAtual, String novaSenha) throws Exception {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID inválido");
        }
        
        validarSenha(novaSenha);
        
        try {
            // Buscar usuário
            UsuarioEntity usuario = usuarioDAO.buscarPorId(id);
            if (usuario == null) {
                throw new IllegalArgumentException("Usuário não encontrado");
            }
            
            // Verificar senha atual
            String senhaAtualHash = criptografarSenha(senhaAtual);
            if (!senhaAtualHash.equals(usuario.getSenha())) {
                throw new SecurityException("Senha atual incorreta");
            }
            
            // Atualizar senha
            String novaSenhaHash = criptografarSenha(novaSenha);
            return usuarioDAO.atualizarSenha(id, novaSenhaHash);
            
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao alterar senha", e);
        }
    }
    
    /**
     * Inativar usuário
     */
    public boolean inativarUsuario(Long id) throws Exception {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID inválido");
        }
        
        try {
            return usuarioDAO.deletar(id);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inativar usuário", e);
        }
    }
    
    /**
     * Buscar usuários por perfil
     */
    public List<UsuarioEntity> buscarPorPerfil(String perfil) throws Exception {
        if (!isPerfilValido(perfil)) {
            throw new IllegalArgumentException("Perfil inválido: " + perfil);
        }
        
        try {
            List<UsuarioEntity> usuarios = usuarioDAO.buscarPorPerfil(perfil);
            return usuarios.stream()
                          .map(UsuarioEntity::toSecureUser)
                          .toList();
                          
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar usuários por perfil", e);
        }
    }
    
    /**
     * Verificar se usuário está ativo há muito tempo
     */
    public boolean verificarAtividadeRecente(Long id, int diasLimite) throws Exception {
        try {
            UsuarioEntity usuario = usuarioDAO.buscarPorId(id);
            if (usuario == null || usuario.getDataUltimoLogin() == null) {
                return false;
            }
            
            long diasInativo = ChronoUnit.DAYS.between(
                usuario.getDataUltimoLogin(), 
                LocalDateTime.now()
            );
            
            return diasInativo <= diasLimite;
            
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao verificar atividade", e);
        }
    }
    
    /**
     * Validar dados do usuário
     */
    private void validarDadosUsuario(String nome, String email, String senha, String telefone) {
        // Validar nome
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome é obrigatório");
        }
        
        if (nome.trim().length() < 2) {
            throw new IllegalArgumentException("Nome deve ter pelo menos 2 caracteres");
        }
        
        if (nome.trim().length() > 100) {
            throw new IllegalArgumentException("Nome deve ter no máximo 100 caracteres");
        }
        
        // Validar email
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("Email é obrigatório");
        }
        
        if (!EMAIL_PATTERN.matcher(email).matches()) {
            throw new IllegalArgumentException("Email inválido: " + email);
        }
        
        // Validar senha (se fornecida)
        if (senha != null) {
            validarSenha(senha);
        }
        
        // Validar telefone (se fornecido)
        if (telefone != null && !telefone.trim().isEmpty()) {
            if (!TELEFONE_PATTERN.matcher(telefone).matches()) {
                throw new IllegalArgumentException("Telefone inválido: " + telefone);
            }
        }
    }
    
    /**
     * Validar senha
     */
    private void validarSenha(String senha) {
        if (senha == null || senha.isEmpty()) {
            throw new IllegalArgumentException("Senha é obrigatória");
        }
        
        if (senha.length() < 6) {
            throw new IllegalArgumentException("Senha deve ter pelo menos 6 caracteres");
        }
        
        if (senha.length() > 50) {
            throw new IllegalArgumentException("Senha deve ter no máximo 50 caracteres");
        }
        
        // Verificar se tem pelo menos um número
        if (!senha.matches(".*\\d.*")) {
            throw new IllegalArgumentException("Senha deve conter pelo menos um número");
        }
        
        // Verificar se tem pelo menos uma letra
        if (!senha.matches(".*[a-zA-Z].*")) {
            throw new IllegalArgumentException("Senha deve conter pelo menos uma letra");
        }
    }
    
    /**
     * Verificar se perfil é válido
     */
    private boolean isPerfilValido(String perfil) {
        return perfil != null && 
               (perfil.equals("ADMIN") || perfil.equals("USER") || perfil.equals("GUEST"));
    }
    
    /**
     * Criptografar senha usando SHA-256
     */
    private String criptografarSenha(String senha) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = md.digest(senha.getBytes());
            
            StringBuilder sb = new StringBuilder();
            for (byte b : hashedBytes) {
                sb.append(String.format("%02x", b));
            }
            
            return sb.toString();
            
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Erro ao criptografar senha", e);
        }
    }
}