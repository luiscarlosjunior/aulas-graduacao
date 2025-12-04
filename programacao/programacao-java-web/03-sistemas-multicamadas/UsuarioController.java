import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * UsuarioController - Camada de Apresentação
 * Controlador REST para gerenciar usuários
 * Implementa padrão MVC e coordena entre apresentação e negócio
 * 
 * @author Apresentação Java Web
 */
public class UsuarioController {
    
    private UsuarioService usuarioService;
    
    public UsuarioController() {
        this.usuarioService = new UsuarioService();
    }
    
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }
    
    /**
     * POST /api/usuarios - Criar novo usuário
     */
    public ResponseEntity<Object> criarUsuario(CreateUsuarioRequest request) {
        try {
            // Validar request
            if (request == null) {
                return ResponseEntity.badRequest("Request inválido");
            }
            
            // Criar usuário via service
            UsuarioEntity usuario = usuarioService.criarUsuario(
                request.nome,
                request.email,
                request.senha,
                request.telefone,
                request.perfil
            );
            
            // Converter para DTO de resposta
            UsuarioDTO usuarioDTO = new UsuarioDTO(usuario);
            
            return ResponseEntity.created(usuarioDTO);
            
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError("Erro interno do servidor");
        }
    }
    
    /**
     * POST /api/usuarios/login - Autenticar usuário
     */
    public ResponseEntity<Object> login(LoginRequest request) {
        try {
            if (request == null || request.email == null || request.senha == null) {
                return ResponseEntity.badRequest("Email e senha são obrigatórios");
            }
            
            UsuarioEntity usuario = usuarioService.autenticar(request.email, request.senha);
            
            // Criar resposta com token (simulado)
            Map<String, Object> response = new HashMap<>();
            response.put("usuario", new UsuarioDTO(usuario));
            response.put("token", "jwt_token_simulado_" + usuario.getId());
            response.put("tipo", "Bearer");
            response.put("expiracao", "3600"); // 1 hora
            
            return ResponseEntity.ok(response);
            
        } catch (SecurityException e) {
            return ResponseEntity.unauthorized(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError("Erro interno do servidor");
        }
    }
    
    /**
     * GET /api/usuarios/{id} - Buscar usuário por ID
     */
    public ResponseEntity<Object> buscarUsuario(Long id) {
        try {
            if (id == null || id <= 0) {
                return ResponseEntity.badRequest("ID inválido");
            }
            
            UsuarioEntity usuario = usuarioService.buscarPorId(id);
            
            if (usuario == null) {
                return ResponseEntity.notFound("Usuário não encontrado");
            }
            
            return ResponseEntity.ok(new UsuarioDTO(usuario));
            
        } catch (Exception e) {
            return ResponseEntity.internalServerError("Erro interno do servidor");
        }
    }
    
    /**
     * GET /api/usuarios - Listar usuários com paginação
     */
    public ResponseEntity<Object> listarUsuarios(Integer pagina, Integer tamanho) {
        try {
            // Valores padrão
            int paginaAtual = pagina != null ? pagina : 1;
            int tamanhoPagina = tamanho != null ? tamanho : 10;
            
            List<UsuarioEntity> usuarios = usuarioService.listarUsuarios(paginaAtual, tamanhoPagina);
            int totalUsuarios = usuarioService.contarUsuarios();
            
            // Converter para DTOs
            List<UsuarioDTO> usuariosDTO = usuarios.stream()
                                                  .map(UsuarioDTO::new)
                                                  .toList();
            
            // Criar resposta paginada
            Map<String, Object> response = new HashMap<>();
            response.put("usuarios", usuariosDTO);
            response.put("pagina_atual", paginaAtual);
            response.put("tamanho_pagina", tamanhoPagina);
            response.put("total_usuarios", totalUsuarios);
            response.put("total_paginas", (int) Math.ceil((double) totalUsuarios / tamanhoPagina));
            
            return ResponseEntity.ok(response);
            
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError("Erro interno do servidor");
        }
    }
    
    /**
     * PUT /api/usuarios/{id} - Atualizar usuário
     */
    public ResponseEntity<Object> atualizarUsuario(Long id, UpdateUsuarioRequest request) {
        try {
            if (id == null || id <= 0) {
                return ResponseEntity.badRequest("ID inválido");
            }
            
            if (request == null) {
                return ResponseEntity.badRequest("Request inválido");
            }
            
            boolean atualizado = usuarioService.atualizarUsuario(
                id,
                request.nome,
                request.email,
                request.telefone,
                request.perfil
            );
            
            if (!atualizado) {
                return ResponseEntity.notFound("Usuário não encontrado");
            }
            
            // Buscar usuário atualizado
            UsuarioEntity usuario = usuarioService.buscarPorId(id);
            return ResponseEntity.ok(new UsuarioDTO(usuario));
            
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError("Erro interno do servidor");
        }
    }
    
    /**
     * PUT /api/usuarios/{id}/senha - Alterar senha
     */
    public ResponseEntity<Object> alterarSenha(Long id, AlterarSenhaRequest request) {
        try {
            if (id == null || id <= 0) {
                return ResponseEntity.badRequest("ID inválido");
            }
            
            if (request == null || request.senhaAtual == null || request.novaSenha == null) {
                return ResponseEntity.badRequest("Senha atual e nova senha são obrigatórias");
            }
            
            boolean alterado = usuarioService.alterarSenha(id, request.senhaAtual, request.novaSenha);
            
            if (!alterado) {
                return ResponseEntity.notFound("Usuário não encontrado");
            }
            
            Map<String, String> response = new HashMap<>();
            response.put("mensagem", "Senha alterada com sucesso");
            
            return ResponseEntity.ok(response);
            
        } catch (SecurityException e) {
            return ResponseEntity.unauthorized(e.getMessage());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError("Erro interno do servidor");
        }
    }
    
    /**
     * DELETE /api/usuarios/{id} - Inativar usuário
     */
    public ResponseEntity<Object> deletarUsuario(Long id) {
        try {
            if (id == null || id <= 0) {
                return ResponseEntity.badRequest("ID inválido");
            }
            
            boolean deletado = usuarioService.inativarUsuario(id);
            
            if (!deletado) {
                return ResponseEntity.notFound("Usuário não encontrado");
            }
            
            Map<String, String> response = new HashMap<>();
            response.put("mensagem", "Usuário inativado com sucesso");
            
            return ResponseEntity.ok(response);
            
        } catch (Exception e) {
            return ResponseEntity.internalServerError("Erro interno do servidor");
        }
    }
    
    /**
     * GET /api/usuarios/perfil/{perfil} - Buscar usuários por perfil
     */
    public ResponseEntity<Object> buscarPorPerfil(String perfil) {
        try {
            if (perfil == null || perfil.trim().isEmpty()) {
                return ResponseEntity.badRequest("Perfil é obrigatório");
            }
            
            List<UsuarioEntity> usuarios = usuarioService.buscarPorPerfil(perfil.toUpperCase());
            
            List<UsuarioDTO> usuariosDTO = usuarios.stream()
                                                  .map(UsuarioDTO::new)
                                                  .toList();
            
            Map<String, Object> response = new HashMap<>();
            response.put("perfil", perfil.toUpperCase());
            response.put("usuarios", usuariosDTO);
            response.put("total", usuariosDTO.size());
            
            return ResponseEntity.ok(response);
            
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError("Erro interno do servidor");
        }
    }
    
    // Classes internas para Request/Response DTOs
    
    public static class CreateUsuarioRequest {
        public String nome;
        public String email;
        public String senha;
        public String telefone;
        public String perfil;
    }
    
    public static class UpdateUsuarioRequest {
        public String nome;
        public String email;
        public String telefone;
        public String perfil;
    }
    
    public static class LoginRequest {
        public String email;
        public String senha;
    }
    
    public static class AlterarSenhaRequest {
        public String senhaAtual;
        public String novaSenha;
    }
    
    public static class UsuarioDTO {
        public Long id;
        public String nome;
        public String email;
        public String telefone;
        public String perfil;
        public boolean ativo;
        public String dataCriacao;
        public String dataUltimoLogin;
        
        public UsuarioDTO(UsuarioEntity usuario) {
            this.id = usuario.getId();
            this.nome = usuario.getNome();
            this.email = usuario.getEmail();
            this.telefone = usuario.getTelefone();
            this.perfil = usuario.getPerfil();
            this.ativo = usuario.isAtivo();
            this.dataCriacao = usuario.getDataCriacao() != null ? 
                              usuario.getDataCriacao().toString() : null;
            this.dataUltimoLogin = usuario.getDataUltimoLogin() != null ? 
                                  usuario.getDataUltimoLogin().toString() : null;
        }
    }
    
    // Classe para simular ResponseEntity
    public static class ResponseEntity<T> {
        private int status;
        private T body;
        
        private ResponseEntity(int status, T body) {
            this.status = status;
            this.body = body;
        }
        
        public static <T> ResponseEntity<T> ok(T body) {
            return new ResponseEntity<>(200, body);
        }
        
        public static <T> ResponseEntity<T> created(T body) {
            return new ResponseEntity<>(201, body);
        }
        
        public static ResponseEntity<Object> badRequest(String message) {
            Map<String, String> error = new HashMap<>();
            error.put("erro", message);
            return new ResponseEntity<>(400, error);
        }
        
        public static ResponseEntity<Object> unauthorized(String message) {
            Map<String, String> error = new HashMap<>();
            error.put("erro", message);
            return new ResponseEntity<>(401, error);
        }
        
        public static ResponseEntity<Object> notFound(String message) {
            Map<String, String> error = new HashMap<>();
            error.put("erro", message);
            return new ResponseEntity<>(404, error);
        }
        
        public static ResponseEntity<Object> internalServerError(String message) {
            Map<String, String> error = new HashMap<>();
            error.put("erro", message);
            return new ResponseEntity<>(500, error);
        }
        
        public int getStatus() { return status; }
        public T getBody() { return body; }
        
        @Override
        public String toString() {
            return "ResponseEntity{status=" + status + ", body=" + body + "}";
        }
    }
}