import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * DemoMultiTier - Demonstração simplificada de arquitetura multi-tier
 * Funciona sem dependências externas para fins educacionais
 * 
 * @author Apresentação Java Web
 */
public class DemoMultiTier {
    
    public static void main(String[] args) {
        System.out.println("=== Demo Sistema Multi-Tier ===");
        System.out.println("Demonstração de arquitetura de 3 camadas");
        System.out.println("Data/Hora: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));
        System.out.println();
        
        try {
            demonstrarCamadaDados();
            demonstrarCamadaNegocio();
            demonstrarCamadaApresentacao();
            demonstrarFluxoCompleto();
            
            System.out.println("\n=== Demo executada com sucesso! ===");
            
        } catch (Exception e) {
            System.err.println("Erro na demonstração: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    private static void demonstrarCamadaDados() {
        System.out.println("1. === CAMADA DE DADOS ===");
        
        // Demonstrar entidade Usuario
        UsuarioEntity usuario = new UsuarioEntity("João Silva", "joao@teste.com", "senha123");
        usuario.setId(1L);
        usuario.setTelefone("(11) 99999-9999");
        usuario.setPerfil("USER");
        
        System.out.println("✓ Entidade Usuario criada:");
        System.out.println("  " + usuario);
        System.out.println("  É válido: " + usuario.isValid());
        System.out.println("  É admin: " + usuario.isAdmin());
        
        // Demonstrar entidade Produto
        ProdutoEntity produto = new ProdutoEntity(
            "Notebook Dell", 
            "Notebook Dell Inspiron 15 com Intel i5", 
            new BigDecimal("2500.00"), 
            "Eletrônicos"
        );
        produto.setId(1L);
        produto.setQuantidadeEstoque(10);
        produto.setCodigoBarra("7891234567890");
        
        System.out.println("\n✓ Entidade Produto criada:");
        System.out.println("  " + produto);
        System.out.println("  Tem estoque: " + produto.temEstoque());
        System.out.println("  Valor total estoque: R$" + produto.calcularValorEstoque());
        
        // Operações de estoque
        produto.removerEstoque(3);
        System.out.println("  Após remoção de 3 unidades: " + produto.getQuantidadeEstoque());
        
        produto.adicionarEstoque(5);
        System.out.println("  Após adição de 5 unidades: " + produto.getQuantidadeEstoque());
    }
    
    private static void demonstrarCamadaNegocio() {
        System.out.println("\n2. === CAMADA DE NEGÓCIO ===");
        
        // Simular validações de negócio
        System.out.println("✓ Demonstrando validações de negócio:");
        
        // Validação de email
        String[] emails = {"joao@teste.com", "email_invalido", "maria@empresa.com.br"};
        for (String email : emails) {
            boolean valido = email.contains("@") && email.contains(".");
            System.out.println("  Email '" + email + "': " + (valido ? "VÁLIDO" : "INVÁLIDO"));
        }
        
        // Validação de senha
        String[] senhas = {"123", "senha123", "Senha123!"};
        for (String senha : senhas) {
            boolean valida = senha.length() >= 6 && senha.matches(".*\\d.*");
            System.out.println("  Senha '" + senha + "': " + (valida ? "VÁLIDA" : "INVÁLIDA"));
        }
        
        // Regras de negócio para produtos
        System.out.println("\n✓ Demonstrando regras de negócio para produtos:");
        
        ProdutoEntity produto = new ProdutoEntity("Mouse", "Mouse óptico", new BigDecimal("25.00"), "Informática");
        produto.setQuantidadeEstoque(5);
        
        // Teste de estoque
        int[] quantidades = {2, 3, 8};
        for (int qty : quantidades) {
            boolean disponivel = produto.temEstoqueSuficiente(qty);
            System.out.println("  Solicitar " + qty + " unidades: " + (disponivel ? "DISPONÍVEL" : "INDISPONÍVEL"));
        }
        
        // Cálculo de desconto (simulado)
        BigDecimal precoOriginal = produto.getPreco();
        BigDecimal desconto = precoOriginal.multiply(new BigDecimal("0.10")); // 10%
        BigDecimal precoComDesconto = precoOriginal.subtract(desconto);
        
        System.out.println("  Preço original: R$" + precoOriginal);
        System.out.println("  Desconto (10%): R$" + desconto);
        System.out.println("  Preço final: R$" + precoComDesconto);
    }
    
    private static void demonstrarCamadaApresentacao() {
        System.out.println("\n3. === CAMADA DE APRESENTAÇÃO ===");
        
        // Simular responses HTTP
        System.out.println("✓ Simulando responses HTTP:");
        
        // Response de sucesso
        ResponseSimulado response1 = new ResponseSimulado(200, "OK", "Usuário criado com sucesso");
        System.out.println("  " + response1);
        
        // Response de erro de validação
        ResponseSimulado response2 = new ResponseSimulado(400, "Bad Request", "Email já está em uso");
        System.out.println("  " + response2);
        
        // Response de não encontrado
        ResponseSimulado response3 = new ResponseSimulado(404, "Not Found", "Produto não encontrado");
        System.out.println("  " + response3);
        
        // Simular DTOs
        System.out.println("\n✓ Demonstrando DTOs (Data Transfer Objects):");
        
        UsuarioEntity usuario = new UsuarioEntity("Maria Santos", "maria@teste.com", "senha456");
        usuario.setId(2L);
        usuario.setPerfil("ADMIN");
        
        // DTO sem dados sensíveis
        UsuarioDTO dto = new UsuarioDTO();
        dto.id = usuario.getId();
        dto.nome = usuario.getNome();
        dto.email = usuario.getEmail();
        dto.perfil = usuario.getPerfil();
        // Senha não é incluída no DTO por segurança
        
        System.out.println("  DTO Usuario: " + dto);
    }
    
    private static void demonstrarFluxoCompleto() {
        System.out.println("\n4. === FLUXO COMPLETO ===");
        System.out.println("Simulando requisição completa através das 3 camadas:");
        
        // 1. Requisição chega na camada de apresentação
        System.out.println("\n1. APRESENTAÇÃO: Recebida requisição POST /api/produtos");
        
        ProdutoRequest request = new ProdutoRequest();
        request.nome = "Teclado Mecânico";
        request.descricao = "Teclado mecânico RGB";
        request.preco = new BigDecimal("150.00");
        request.categoria = "Periféricos";
        request.quantidadeEstoque = 20;
        
        System.out.println("   Dados recebidos: " + request);
        
        // 2. Camada de apresentação valida entrada básica
        System.out.println("\n2. APRESENTAÇÃO: Validando entrada...");
        if (request.nome == null || request.preco == null) {
            System.out.println("   ✗ Validação falhou: dados obrigatórios faltando");
            return;
        }
        System.out.println("   ✓ Validação de entrada passou");
        
        // 3. Chama camada de negócio
        System.out.println("\n3. NEGÓCIO: Aplicando regras de negócio...");
        
        // Validar regras de negócio
        if (request.preco.compareTo(BigDecimal.ZERO) <= 0) {
            System.out.println("   ✗ Regra de negócio falhou: preço deve ser positivo");
            return;
        }
        
        if (request.nome.length() < 3) {
            System.out.println("   ✗ Regra de negócio falhou: nome muito curto");
            return;
        }
        
        System.out.println("   ✓ Regras de negócio validadas");
        
        // 4. Chama camada de dados
        System.out.println("\n4. DADOS: Persistindo no banco...");
        
        ProdutoEntity produto = new ProdutoEntity(
            request.nome, 
            request.descricao, 
            request.preco, 
            request.categoria
        );
        produto.setId(generateId()); // Simular ID gerado pelo banco
        produto.setQuantidadeEstoque(request.quantidadeEstoque);
        
        System.out.println("   ✓ Produto salvo com ID: " + produto.getId());
        
        // 5. Retorna através das camadas
        System.out.println("\n5. Retornando resposta através das camadas:");
        System.out.println("   DADOS → NEGÓCIO: Produto persistido");
        System.out.println("   NEGÓCIO → APRESENTAÇÃO: Operação validada e concluída");
        
        ResponseSimulado response = new ResponseSimulado(201, "Created", "Produto criado com sucesso");
        response.data = produto.toString();
        
        System.out.println("   APRESENTAÇÃO → CLIENTE: " + response);
        
        System.out.println("\n✓ Fluxo completo executado com sucesso!");
    }
    
    private static long generateId() {
        return System.currentTimeMillis() % 10000; // Simular ID
    }
    
    // Classes auxiliares para demonstração
    static class ResponseSimulado {
        int status;
        String statusText;
        String message;
        String data;
        
        ResponseSimulado(int status, String statusText, String message) {
            this.status = status;
            this.statusText = statusText;
            this.message = message;
        }
        
        @Override
        public String toString() {
            return String.format("HTTP %d %s - %s%s", 
                status, statusText, message, 
                data != null ? " | Data: " + data : "");
        }
    }
    
    static class UsuarioDTO {
        Long id;
        String nome;
        String email;
        String perfil;
        
        @Override
        public String toString() {
            return String.format("{id: %d, nome: '%s', email: '%s', perfil: '%s'}", 
                id, nome, email, perfil);
        }
    }
    
    static class ProdutoRequest {
        String nome;
        String descricao;
        BigDecimal preco;
        String categoria;
        Integer quantidadeEstoque;
        
        @Override
        public String toString() {
            return String.format("{nome: '%s', preco: R$%s, categoria: '%s', estoque: %d}", 
                nome, preco, categoria, quantidadeEstoque);
        }
    }
}