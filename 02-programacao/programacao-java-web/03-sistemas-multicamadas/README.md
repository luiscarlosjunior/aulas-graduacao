# Sistemas de Múltiplas Camadas (Multi-Tier)

## Conceito Fundamental

Um **sistema de múltiplas camadas** (multi-tier) é uma arquitetura de software que separa uma aplicação em camadas lógicas distintas, cada uma com responsabilidades específicas. Esta separação promove:

- **Modularidade**: Cada camada tem uma função bem definida
- **Reutilização**: Camadas podem ser reutilizadas em diferentes contextos
- **Manutenibilidade**: Mudanças em uma camada não afetam outras
- **Escalabilidade**: Cada camada pode ser dimensionada independentemente
- **Testabilidade**: Cada camada pode ser testada de forma isolada

## Arquitetura Clássica de 3 Camadas

### 1. **Camada de Apresentação (Presentation Layer)**
- **Responsabilidade**: Interface com o usuário
- **Componentes**: HTML, CSS, JavaScript, JSP, servlets
- **Funções**:
  - Exibir dados para o usuário
  - Capturar entrada do usuário
  - Validação básica (formato, obrigatórios)
  - Navegação entre telas

### 2. **Camada de Negócio (Business Layer)**
- **Responsabilidade**: Lógica de negócio da aplicação
- **Componentes**: Classes de serviço, beans, controladores
- **Funções**:
  - Processar regras de negócio
  - Validações complexas
  - Cálculos e transformações
  - Coordenar operações entre camadas

### 3. **Camada de Dados (Data Layer)**
- **Responsabilidade**: Persistência e acesso a dados
- **Componentes**: DAOs, repositórios, entidades
- **Funções**:
  - Operações CRUD (Create, Read, Update, Delete)
  - Consultas ao banco de dados
  - Mapeamento objeto-relacional
  - Transações

## Fluxo de Dados na Arquitetura

```
[Usuário] ↔ [Apresentação] ↔ [Negócio] ↔ [Dados] ↔ [Banco de Dados]
```

### Exemplo de Fluxo:
1. **Usuário** faz login na aplicação
2. **Camada de Apresentação** captura credenciais
3. **Camada de Negócio** valida regras (formato email, senha forte)
4. **Camada de Dados** consulta usuário no banco
5. **Camada de Negócio** verifica autenticação
6. **Camada de Apresentação** exibe resultado (sucesso/erro)

## Vantagens da Arquitetura Multi-Tier

### 1. **Separação de Responsabilidades**
- Cada camada tem uma função específica
- Facilita manutenção e evolução
- Permite especialização da equipe

### 2. **Reutilização de Código**
- Camada de negócio pode ser usada por diferentes interfaces
- Camada de dados pode ser compartilhada entre aplicações

### 3. **Flexibilidade**
- Mudanças em uma camada não afetam outras
- Possibilidade de trocar implementações

### 4. **Escalabilidade**
- Cada camada pode rodar em servidores diferentes
- Balanceamento de carga por camada

### 5. **Testabilidade**
- Cada camada pode ser testada isoladamente
- Mocks e stubs para simular dependências

## Implementação em Java

### Estrutura de Pacotes Sugerida
```
com.empresa.projeto/
├── presentation/     # Camada de Apresentação
│   ├── controller/   # Controladores MVC
│   └── dto/          # Data Transfer Objects
├── business/         # Camada de Negócio
│   ├── service/      # Serviços de negócio
│   └── validation/   # Validações
├── data/            # Camada de Dados
│   ├── dao/         # Data Access Objects
│   ├── entity/      # Entidades/Models
│   └── repository/  # Repositórios
└── util/           # Utilitários
    ├── connection/  # Conexão com BD
    └── exception/   # Exceções customizadas
```

## Exemplos Práticos

### 1. Sistema de Usuários Completo
- [UsuarioEntity.java](UsuarioEntity.java) - Entidade de dados
- [UsuarioDAO.java](UsuarioDAO.java) - Acesso a dados
- [UsuarioService.java](UsuarioService.java) - Lógica de negócio
- [UsuarioController.java](UsuarioController.java) - Controlador

### 2. Sistema de Produtos
- [ProdutoEntity.java](ProdutoEntity.java) - Entidade de produto
- [ProdutoDAO.java](ProdutoDAO.java) - Persistência
- [ProdutoService.java](ProdutoService.java) - Regras de negócio
- [ProdutoController.java](ProdutoController.java) - API REST

### 3. Aplicação Web Completa
- [WebApp.java](WebApp.java) - Aplicação principal
- [DatabaseConnection.java](DatabaseConnection.java) - Conexão BD

## Padrões de Design Relacionados

### 1. **MVC (Model-View-Controller)**
- **Model**: Camada de dados + parte da camada de negócio
- **View**: Camada de apresentação
- **Controller**: Ponte entre View e Model

### 2. **DAO (Data Access Object)**
- Encapsula acesso aos dados
- Abstrai detalhes do banco de dados
- Facilita mudanças de tecnologia

### 3. **Service Layer**
- Concentra lógica de negócio
- Coordena operações entre DAOs
- Define transações

### 4. **DTO (Data Transfer Object)**
- Objetos para transferir dados entre camadas
- Evita exposição de entidades internas
- Otimiza comunicação de rede

## Tecnologias Java por Camada

### Camada de Apresentação
- **Web**: JSF, JSP, Servlets, Thymeleaf
- **Desktop**: Swing, JavaFX
- **Mobile**: Android
- **API**: JAX-RS, Spring MVC

### Camada de Negócio
- **Frameworks**: Spring, EJB
- **Validação**: Bean Validation (JSR-303)
- **Segurança**: Spring Security, JAAS

### Camada de Dados
- **ORM**: Hibernate, JPA, MyBatis
- **Banco**: JDBC, Connection Pooling
- **Cache**: Ehcache, Redis

## Configuração e Deployment

### 1. **Aplicação Monolítica**
- Todas as camadas no mesmo WAR/JAR
- Deploy conjunto
- Compartilhamento de recursos

### 2. **Aplicação Distribuída**
- Cada camada em servidores diferentes
- Comunicação via HTTP/RMI
- Escalabilidade independente

### 3. **Containers e Microserviços**
- Cada camada em containers
- Orquestração com Docker/Kubernetes
- Independência de tecnologia

## Considerações de Performance

### 1. **Latência entre Camadas**
- Minimizar chamadas desnecessárias
- Usar cache entre camadas
- Otimizar consultas ao banco

### 2. **Serialização de Dados**
- Usar DTOs apropriados
- Evitar lazy loading em transferências
- Considerar formatos binários

### 3. **Transações**
- Gerenciar transações na camada de negócio
- Usar pool de conexões
- Implementar retry em falhas

## Monitoramento e Logging

### 1. **Logs por Camada**
```java
// Apresentação
logger.info("Usuário {} acessou tela de produtos", userId);

// Negócio  
logger.debug("Aplicando desconto de {}% para cliente {}", desconto, clienteId);

// Dados
logger.trace("Executando query: {}", sql);
```

### 2. **Métricas de Performance**
- Tempo de resposta por camada
- Número de transações por minuto
- Taxa de erro por operação

## Exercícios Práticos

1. **Implemente o sistema de usuários completo**
   - Compile e teste todas as classes
   - Execute operações CRUD
   - Teste validações de negócio

2. **Crie um sistema de pedidos**
   - Entidade Pedido com itens
   - Validação de estoque
   - Cálculo de totais

3. **Adicione autenticação**
   - Login e logout
   - Controle de sessão
   - Diferentes níveis de acesso

## Próximos Passos

Este módulo cobriu sistemas de múltiplas camadas. No próximo módulo, exploraremos:

- **Arquitetura Orientada a Serviços (SOA)**
- **REST e APIs**
- **Microserviços**
- **Comunicação entre serviços**

## Referências

- [Java EE Design Patterns](https://www.oracle.com/java/technologies/java-ee-design-patterns.html)
- [Spring Framework Reference](https://spring.io/projects/spring-framework)
- [Enterprise Application Architecture Patterns](https://martinfowler.com/eaaCatalog/)