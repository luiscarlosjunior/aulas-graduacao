# Arquitetura Orientada a Serviços com REST

## Conceitos Fundamentais

**REST** (Representational State Transfer) é um estilo arquitetural para sistemas distribuídos, especialmente para aplicações web. Foi definido por Roy Fielding em sua tese de doutorado em 2000 e se tornou o padrão de facto para APIs web.

### Princípios REST

1. **Stateless (Sem Estado)**: Cada requisição é independente
2. **Client-Server**: Separação clara entre cliente e servidor
3. **Cacheable**: Respostas podem ser cacheadas
4. **Uniform Interface**: Interface uniforme entre componentes
5. **Layered System**: Arquitetura em camadas
6. **Code on Demand**: Código executável pode ser transferido (opcional)

## Características de APIs REST

### 1. **Recursos (Resources)**
- Tudo é representado como recurso
- Identificados por URIs únicos
- Exemplo: `/api/usuarios/123`

### 2. **Métodos HTTP**
- **GET**: Buscar dados
- **POST**: Criar novos recursos
- **PUT**: Atualizar recursos completos
- **PATCH**: Atualizar recursos parcialmente
- **DELETE**: Remover recursos

### 3. **Códigos de Status HTTP**
- **2xx**: Sucesso (200 OK, 201 Created, 204 No Content)
- **4xx**: Erro do cliente (400 Bad Request, 404 Not Found, 401 Unauthorized)
- **5xx**: Erro do servidor (500 Internal Server Error, 503 Service Unavailable)

### 4. **Representações**
- Dados em formato JSON, XML, HTML
- Content-Type headers indicam o formato
- Negociação de conteúdo com Accept headers

## Design de APIs REST

### Estrutura de URLs
```
Recurso Único:
GET    /api/usuarios/123        # Buscar usuário específico
PUT    /api/usuarios/123        # Atualizar usuário
DELETE /api/usuarios/123        # Deletar usuário

Coleção de Recursos:
GET    /api/usuarios            # Listar usuários
POST   /api/usuarios            # Criar novo usuário

Recursos Aninhados:
GET    /api/usuarios/123/pedidos    # Pedidos do usuário
POST   /api/usuarios/123/pedidos    # Criar pedido para usuário

Filtros e Paginação:
GET    /api/usuarios?page=1&size=10&ativo=true
GET    /api/produtos?categoria=eletrônicos&preco_min=100
```

### Headers Importantes
```http
Content-Type: application/json
Accept: application/json
Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...
Cache-Control: no-cache
If-Modified-Since: Wed, 09 Aug 2023 14:30:00 GMT
```

## Implementação em Java

### Estrutura de um Controlador REST
```java
@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
    
    @GetMapping
    public ResponseEntity<List<Usuario>> listarUsuarios() { }
    
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscarUsuario(@PathVariable Long id) { }
    
    @PostMapping
    public ResponseEntity<Usuario> criarUsuario(@RequestBody Usuario usuario) { }
    
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> atualizarUsuario(@PathVariable Long id, @RequestBody Usuario usuario) { }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarUsuario(@PathVariable Long id) { }
}
```

## Exemplos Práticos

### 1. API de Usuários Completa
Veja [UsuarioRestAPI.java](UsuarioRestAPI.java) - API REST completa para gerenciar usuários

### 2. API de Produtos
Veja [ProdutoRestAPI.java](ProdutoRestAPI.java) - API REST para sistema de produtos

### 3. Cliente REST Java
Veja [RestClient.java](RestClient.java) - Cliente para consumir APIs REST

### 4. Servidor REST Simples
Veja [RestServer.java](RestServer.java) - Servidor HTTP com endpoints REST

## Boas Práticas REST

### 1. **Nomenclatura**
```
✓ Correto:
GET /api/usuarios
POST /api/usuarios
GET /api/usuarios/123

✗ Incorreto:
GET /api/getUsuarios
POST /api/createUsuario
GET /api/usuario/buscar/123
```

### 2. **Versionamento**
```
Via URL: /api/v1/usuarios
Via Header: Accept: application/vnd.api+json;version=1
Via Query: /api/usuarios?version=1
```

### 3. **Paginação**
```json
{
  "data": [...],
  "pagination": {
    "current_page": 1,
    "total_pages": 10,
    "total_items": 95,
    "items_per_page": 10
  }
}
```

### 4. **Filtros e Busca**
```
/api/produtos?categoria=eletrônicos
/api/usuarios?ativo=true&perfil=admin
/api/pedidos?data_inicio=2023-01-01&data_fim=2023-12-31
```

### 5. **Tratamento de Erros**
```json
{
  "error": {
    "code": "VALIDATION_ERROR",
    "message": "Dados inválidos",
    "details": [
      {
        "field": "email",
        "message": "Email já está em uso"
      }
    ]
  }
}
```

## Segurança em APIs REST

### 1. **Autenticação**
```http
# Basic Authentication
Authorization: Basic dXN1YXJpbzpzZW5oYQ==

# Bearer Token (JWT)
Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...

# API Key
X-API-Key: abc123def456ghi789
```

### 2. **CORS (Cross-Origin Resource Sharing)**
```java
@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class UsuarioController {
    // ...
}
```

### 3. **Rate Limiting**
```
HTTP/1.1 429 Too Many Requests
X-RateLimit-Limit: 100
X-RateLimit-Remaining: 0
X-RateLimit-Reset: 1609459200
```

### 4. **HTTPS**
- Sempre usar HTTPS em produção
- Criptografar dados sensíveis
- Validar certificados SSL

## Ferramentas e Testing

### 1. **Postman**
- Testes manuais de APIs
- Coleções de requisições
- Automação de testes

### 2. **curl**
```bash
# GET
curl -X GET http://localhost:8080/api/usuarios

# POST
curl -X POST http://localhost:8080/api/usuarios \
  -H "Content-Type: application/json" \
  -d '{"nome": "João", "email": "joao@teste.com"}'

# PUT
curl -X PUT http://localhost:8080/api/usuarios/123 \
  -H "Content-Type: application/json" \
  -d '{"nome": "João Silva", "email": "joao.silva@teste.com"}'

# DELETE
curl -X DELETE http://localhost:8080/api/usuarios/123
```

### 3. **Testes Automatizados**
```java
@Test
public void deveRetornarUsuarioQuandoBuscarPorId() {
    // Arrange
    Long id = 1L;
    
    // Act
    ResponseEntity<Usuario> response = restTemplate.getForEntity(
        "/api/usuarios/" + id, Usuario.class);
    
    // Assert
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertNotNull(response.getBody());
}
```

## Padrões Avançados

### 1. **HATEOAS** (Hypermedia as the Engine of Application State)
```json
{
  "id": 123,
  "nome": "João Silva",
  "email": "joao@teste.com",
  "_links": {
    "self": {"href": "/api/usuarios/123"},
    "pedidos": {"href": "/api/usuarios/123/pedidos"},
    "edit": {"href": "/api/usuarios/123"},
    "delete": {"href": "/api/usuarios/123"}
  }
}
```

### 2. **Richardson Maturity Model**
- **Nível 0**: HTTP como transporte
- **Nível 1**: Recursos individuais
- **Nível 2**: Verbos HTTP
- **Nível 3**: Controles hipermídia (HATEOAS)

### 3. **GraphQL como Alternativa**
```graphql
query {
  usuario(id: 123) {
    nome
    email
    pedidos {
      id
      total
      data
    }
  }
}
```

## Microserviços com REST

### 1. **Decomposição de Serviços**
```
Monolito: /api/usuarios, /api/produtos, /api/pedidos

Microserviços:
- Usuario Service: usuario-service.com/api/usuarios
- Produto Service: produto-service.com/api/produtos  
- Pedido Service: pedido-service.com/api/pedidos
```

### 2. **Comunicação entre Serviços**
```java
// Chamada síncrona
Usuario usuario = restTemplate.getForObject(
    "http://usuario-service/api/usuarios/" + id, Usuario.class);

// Chamada assíncrona
CompletableFuture<Usuario> futureUsuario = 
    asyncRestTemplate.getForEntity(url, Usuario.class);
```

### 3. **Service Discovery**
```java
@LoadBalanced
@Bean
public RestTemplate restTemplate() {
    return new RestTemplate();
}

// Eureka automaticamente resolve 'usuario-service' para IP:PORT
String url = "http://usuario-service/api/usuarios/" + id;
```

## Monitoramento e Observabilidade

### 1. **Logs Estruturados**
```java
@RestController
public class UsuarioController {
    
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscarUsuario(@PathVariable Long id) {
        logger.info("Buscando usuário", 
            kv("usuario_id", id),
            kv("endpoint", "GET /api/usuarios/{id}"));
        
        // ... lógica ...
        
        logger.info("Usuário encontrado",
            kv("usuario_id", id),
            kv("response_time_ms", responseTime));
    }
}
```

### 2. **Health Checks**
```java
@RestController
public class HealthController {
    
    @GetMapping("/health")
    public ResponseEntity<Map<String, String>> health() {
        Map<String, String> status = new HashMap<>();
        status.put("status", "UP");
        status.put("timestamp", Instant.now().toString());
        return ResponseEntity.ok(status);
    }
    
    @GetMapping("/health/ready")
    public ResponseEntity<Void> readiness() {
        // Verificar dependências (BD, cache, etc.)
        return ResponseEntity.ok().build();
    }
}
```

### 3. **Métricas**
```java
@Timed(name = "usuario.buscar", description = "Tempo para buscar usuário")
@GetMapping("/{id}")
public ResponseEntity<Usuario> buscarUsuario(@PathVariable Long id) {
    // ... implementação ...
}
```

## Exercícios Práticos

1. **Implemente a API de usuários completa**
   - Compile e execute o RestServer
   - Teste todos os endpoints com curl
   - Implemente validações e tratamento de erros

2. **Crie uma API de produtos**
   - CRUD completo
   - Filtros por categoria e preço
   - Paginação

3. **Integre multiple APIs**
   - API de usuários
   - API de produtos
   - API de pedidos (relaciona usuários e produtos)

4. **Teste com diferentes clientes**
   - Postman
   - curl
   - Cliente Java (RestClient.java)

## Próximos Passos

Este módulo completou a base da arquitetura Java Web:

1. ✅ **Introdução ao Java**
2. ✅ **Arquitetura Cliente-Servidor**  
3. ✅ **Sistemas Multi-Tier**
4. ✅ **REST e SOA**

### Tópicos Avançados (para estudo futuro):
- **Spring Boot e Spring Framework**
- **Microserviços com Spring Cloud**
- **Containerização com Docker**
- **Orquestração com Kubernetes**
- **Message Queues (RabbitMQ, Apache Kafka)**
- **Event-Driven Architecture**

## Referências

- [REST API Design Best Practices](https://restfulapi.net/)
- [HTTP Status Codes](https://httpstatuses.com/)
- [JSON API Specification](https://jsonapi.org/)
- [OpenAPI Specification](https://swagger.io/specification/)
- [Spring REST Documentation](https://spring.io/guides/tutorials/rest/)