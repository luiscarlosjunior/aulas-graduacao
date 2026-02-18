/**
 * WebApp - Aplicação principal demonstrando arquitetura de 3 camadas
 * Exemplo prático de sistema multi-tier completo
 * 
 * @author Apresentação Java Web
 */
public class WebApp {
    
    public static void main(String[] args) {
        System.out.println("=== Sistema Multi-Tier de Usuários ===");
        System.out.println("Demonstração de arquitetura de 3 camadas");
        System.out.println();
        
        try {
            // 1. Configurar infraestrutura
            System.out.println("1. Configurando infraestrutura...");
            configurarInfraestrutura();
            
            // 2. Testar camada de dados
            System.out.println("\n2. Testando camada de dados...");
            testarCamadaDados();
            
            // 3. Testar camada de negócio
            System.out.println("\n3. Testando camada de negócio...");
            testarCamadaNegocio();
            
            // 4. Testar camada de apresentação
            System.out.println("\n4. Testando camada de apresentação...");
            testarCamadaApresentacao();
            
            // 5. Demonstrar fluxo completo
            System.out.println("\n5. Demonstrando fluxo completo...");
            demonstrarFluxoCompleto();
            
            System.out.println("\n=== Aplicação executada com sucesso! ===");
            
        } catch (Exception e) {
            System.err.println("Erro na aplicação: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    private static void configurarInfraestrutura() throws Exception {
        // Testar conexão com banco
        DatabaseConnection db = DatabaseConnection.getInstance();
        
        if (!db.testarConexao()) {
            throw new RuntimeException("Falha na conexão com banco de dados");
        }
        
        db.exibirInformacoesBanco();
        
        // Criar tabela de usuários
        UsuarioDAO dao = new UsuarioDAO(db);
        dao.criarTabela();
        
        System.out.println("✓ Banco de dados configurado");
        System.out.println("✓ Tabela de usuários criada");
    }
    
    private static void testarCamadaDados() throws Exception {
        UsuarioDAO dao = new UsuarioDAO();
        
        // Criar usuário de teste
        UsuarioEntity usuario = new UsuarioEntity("João Silva", "joao@teste.com", "senha123");
        usuario.setTelefone("(11) 99999-9999");
        usuario.setPerfil("USER");
        
        // Inserir no banco
        UsuarioEntity usuarioCriado = dao.criar(usuario);
        System.out.println("✓ Usuário criado: " + usuarioCriado);
        
        // Buscar por ID
        UsuarioEntity usuarioBuscado = dao.buscarPorId(usuarioCriado.getId());
        System.out.println("✓ Usuário encontrado por ID: " + usuarioBuscado.getNome());
        
        // Buscar por email
        UsuarioEntity usuarioEmail = dao.buscarPorEmail("joao@teste.com");
        System.out.println("✓ Usuário encontrado por email: " + usuarioEmail.getNome());
        
        // Contar usuários
        int total = dao.contarUsuariosAtivos();
        System.out.println("✓ Total de usuários ativos: " + total);
    }
    
    private static void testarCamadaNegocio() throws Exception {
        UsuarioService service = new UsuarioService();
        
        // Criar usuário via service (com validações)
        try {
            UsuarioEntity maria = service.criarUsuario(
                "Maria Santos", 
                "maria@teste.com", 
                "senha123", 
                "(11) 88888-8888",
                "ADMIN"
            );
            System.out.println("✓ Usuário criado via service: " + maria.getNome());
        } catch (Exception e) {
            System.out.println("⚠ Email já existe (esperado): " + e.getMessage());
        }
        
        // Criar usuário com dados únicos
        UsuarioEntity admin = service.criarUsuario(
            "Admin Sistema",
            "admin@teste.com",
            "admin123",
            "(11) 77777-7777",
            "ADMIN"
        );
        System.out.println("✓ Admin criado: " + admin.getNome());
        
        // Teste de autenticação
        try {
            UsuarioEntity usuarioLogado = service.autenticar("admin@teste.com", "admin123");
            System.out.println("✓ Autenticação bem-sucedida: " + usuarioLogado.getNome());
        } catch (SecurityException e) {
            System.out.println("✗ Falha na autenticação: " + e.getMessage());
        }
        
        // Teste de validação (senha inválida)
        try {
            service.criarUsuario("Teste", "teste@email.com", "123", null, "USER");
        } catch (IllegalArgumentException e) {
            System.out.println("✓ Validação funcionando: " + e.getMessage());
        }
        
        // Listar usuários
        var usuarios = service.listarUsuarios(1, 5);
        System.out.println("✓ Usuários listados: " + usuarios.size());
    }
    
    private static void testarCamadaApresentacao() throws Exception {
        UsuarioController controller = new UsuarioController();
        
        // Teste de criação via controller
        UsuarioController.CreateUsuarioRequest request = new UsuarioController.CreateUsuarioRequest();
        request.nome = "Pedro Oliveira";
        request.email = "pedro@teste.com";
        request.senha = "pedro123";
        request.telefone = "(11) 66666-6666";
        request.perfil = "USER";
        
        var response = controller.criarUsuario(request);
        System.out.println("✓ Response criação: Status " + response.getStatus());
        
        // Teste de login
        UsuarioController.LoginRequest loginReq = new UsuarioController.LoginRequest();
        loginReq.email = "pedro@teste.com";
        loginReq.senha = "pedro123";
        
        var loginResponse = controller.login(loginReq);
        System.out.println("✓ Response login: Status " + loginResponse.getStatus());
        
        // Teste de listagem
        var listResponse = controller.listarUsuarios(1, 10);
        System.out.println("✓ Response listagem: Status " + listResponse.getStatus());
        
        // Teste de busca por ID inválido
        var notFoundResponse = controller.buscarUsuario(999L);
        System.out.println("✓ Response not found: Status " + notFoundResponse.getStatus());
    }
    
    private static void demonstrarFluxoCompleto() throws Exception {
        System.out.println("\n--- Simulando fluxo de cadastro completo ---");
        
        // 1. Controller recebe requisição HTTP
        UsuarioController controller = new UsuarioController();
        
        UsuarioController.CreateUsuarioRequest request = new UsuarioController.CreateUsuarioRequest();
        request.nome = "Ana Costa";
        request.email = "ana@teste.com";
        request.senha = "ana123456";
        request.telefone = "(11) 55555-5555";
        request.perfil = "USER";
        
        System.out.println("1. Controller recebeu requisição para criar usuário: " + request.nome);
        
        // 2. Controller chama service
        var response = controller.criarUsuario(request);
        System.out.println("2. Service processou regras de negócio");
        System.out.println("3. DAO persistiu no banco de dados");
        System.out.println("4. Controller retornou response: Status " + response.getStatus());
        
        // Simular autenticação
        System.out.println("\n--- Simulando fluxo de login ---");
        
        UsuarioController.LoginRequest loginReq = new UsuarioController.LoginRequest();
        loginReq.email = "ana@teste.com";
        loginReq.senha = "ana123456";
        
        System.out.println("1. Controller recebeu requisição de login");
        
        var loginResponse = controller.login(loginReq);
        System.out.println("2. Service validou credenciais");
        System.out.println("3. DAO consultou banco de dados");
        System.out.println("4. Service atualizou último login");
        System.out.println("5. Controller retornou token: Status " + loginResponse.getStatus());
        
        // Mostrar estatísticas finais
        System.out.println("\n--- Estatísticas Finais ---");
        UsuarioService service = new UsuarioService();
        
        int totalUsuarios = service.contarUsuarios();
        System.out.println("Total de usuários no sistema: " + totalUsuarios);
        
        var admins = service.buscarPorPerfil("ADMIN");
        System.out.println("Administradores: " + admins.size());
        
        var users = service.buscarPorPerfil("USER");
        System.out.println("Usuários comuns: " + users.size());
    }
}