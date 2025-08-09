# Arquitetura Cliente-Servidor

## Conceitos Fundamentais

A **arquitetura cliente-servidor** é um modelo de computação distribuída onde as tarefas são divididas entre dois tipos de entidades:

- **Cliente**: Solicita serviços ou recursos
- **Servidor**: Fornece serviços ou recursos

Esta arquitetura é a base da maioria das aplicações web e sistemas distribuídos modernos.

## Características Principais

### 1. **Separação de Responsabilidades**
- **Cliente**: Interface do usuário, validações locais, apresentação
- **Servidor**: Lógica de negócio, acesso a dados, processamento

### 2. **Comunicação via Rede**
- Protocolo de comunicação (HTTP, HTTPS, TCP, UDP)
- Formato de dados (JSON, XML, HTML)
- Requisições e respostas estruturadas

### 3. **Escalabilidade**
- Múltiplos clientes podem conectar a um servidor
- Balanceamento de carga
- Distribuição geográfica

## Tipos de Arquitetura Cliente-Servidor

### 1. **Two-Tier (2 Camadas)**
```
Cliente ←→ Servidor
```
- Cliente se conecta diretamente ao servidor de banco de dados
- Lógica de negócio pode estar no cliente ou servidor
- Exemplo: Aplicações desktop com banco de dados

### 2. **Three-Tier (3 Camadas)**
```
Cliente ←→ Servidor de Aplicação ←→ Servidor de Banco de Dados
```
- Separação clara entre apresentação, lógica e dados
- Exemplo: Aplicações web tradicionais

### 3. **N-Tier (Múltiplas Camadas)**
```
Cliente ←→ Load Balancer ←→ Web Server ←→ App Server ←→ Database Server
```
- Arquitetura mais complexa com múltiplos serviços
- Microserviços, APIs, cache, etc.

## Protocolos de Comunicação

### HTTP/HTTPS
O protocolo mais utilizado para aplicações web:

```http
GET /usuarios/123 HTTP/1.1
Host: api.exemplo.com
Accept: application/json
Authorization: Bearer token123

HTTP/1.1 200 OK
Content-Type: application/json

{
    "id": 123,
    "nome": "João Silva",
    "email": "joao@exemplo.com"
}
```

### TCP/UDP
Para comunicação de baixo nível:
- **TCP**: Confiável, conexão estabelecida
- **UDP**: Rápido, sem garantia de entrega

## Vantagens da Arquitetura Cliente-Servidor

1. **Centralização**: Dados e lógica centralizados no servidor
2. **Segurança**: Controle de acesso centralizado
3. **Manutenibilidade**: Atualizações no servidor beneficiam todos os clientes
4. **Escalabilidade**: Possibilidade de aumentar recursos do servidor
5. **Compartilhamento**: Múltiplos clientes acessam os mesmos recursos

## Desvantagens

1. **Ponto único de falha**: Se o servidor falha, todos os clientes são afetados
2. **Dependência de rede**: Comunicação depende da conectividade
3. **Gargalo do servidor**: Servidor pode se tornar um bottleneck
4. **Complexidade**: Gerenciamento de estado e sincronização

## Implementação em Java

### Exemplo 1: Servidor HTTP Simples
Veja [ServidorHTTPSimples.java](ServidorHTTPSimples.java)

### Exemplo 2: Cliente HTTP
Veja [ClienteHTTP.java](ClienteHTTP.java)

### Exemplo 3: Servidor de Sockets
Veja [ServidorSocket.java](ServidorSocket.java)

### Exemplo 4: Cliente de Sockets
Veja [ClienteSocket.java](ClienteSocket.java)

## Padrões de Design Relacionados

### 1. **Request-Response**
- Cliente envia requisição
- Servidor processa e retorna resposta
- Comunicação síncrona

### 2. **Publish-Subscribe**
- Clientes se inscrevem em tópicos
- Servidor publica eventos
- Comunicação assíncrona

### 3. **MVC (Model-View-Controller)**
- **Model**: Dados e lógica de negócio (Servidor)
- **View**: Interface do usuário (Cliente)
- **Controller**: Coordena comunicação

## Tecnologias Java para Cliente-Servidor

### Servidor Web Java
- **Servlet API**: Processamento de requisições HTTP
- **JSP (JavaServer Pages)**: Páginas dinâmicas
- **Spring Boot**: Framework moderno
- **Apache Tomcat**: Servidor de aplicação

### Cliente Java
- **Swing/JavaFX**: Aplicações desktop
- **HTTP Client**: Requisições HTTP
- **WebSocket**: Comunicação em tempo real

## Estado e Sessão

### Stateless (Sem Estado)
- Servidor não mantém informações entre requisições
- Cada requisição é independente
- Mais escalável

### Stateful (Com Estado)
- Servidor mantém informações do cliente
- Sessões HTTP, cookies
- Mais complexo de escalar

## Segurança

### Autenticação
- Verificação de identidade
- Login/senha, tokens, certificados

### Autorização
- Controle de acesso a recursos
- Roles, permissões

### Criptografia
- HTTPS/TLS para comunicação segura
- Criptografia de dados sensíveis

## Exercícios Práticos

1. **Execute os exemplos Java fornecidos**
   - Compile e teste o servidor HTTP simples
   - Execute o cliente HTTP para fazer requisições
   - Teste a comunicação com sockets

2. **Modifique o servidor HTTP**
   - Adicione novos endpoints
   - Implemente autenticação básica
   - Adicione logs de requisições

3. **Crie um sistema de chat**
   - Use sockets para comunicação
   - Múltiplos clientes conectados
   - Servidor distribui mensagens

## Monitoramento e Diagnóstico

### Logs
- Registro de requisições e respostas
- Análise de performance
- Detecção de problemas

### Métricas
- Tempo de resposta
- Throughput (requisições por segundo)
- Uso de recursos (CPU, memória)

### Ferramentas
- JConsole, VisualVM (Java)
- Postman (teste de APIs)
- Wireshark (análise de rede)

## Próximos Passos

Este módulo cobriu os fundamentos da arquitetura cliente-servidor. No próximo módulo, exploraremos:

- **Sistemas de Múltiplas Camadas**
- Separação de responsabilidades
- Padrões arquiteturais
- Escalabilidade horizontal e vertical

## Referências

- [Java Servlet Specification](https://jakarta.ee/specifications/servlet/)
- [HTTP/1.1 RFC](https://tools.ietf.org/html/rfc7231)
- [REST Architectural Style](https://www.ics.uci.edu/~fielding/pubs/dissertation/rest_arch_style.htm)