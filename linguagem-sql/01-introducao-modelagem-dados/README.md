# Módulo 01 - Introdução à Modelagem de Dados

## Objetivos de Aprendizagem

Ao final deste módulo, o aluno será capaz de:
- Compreender os conceitos fundamentais de banco de dados
- Identificar as diferenças entre dados, informação e conhecimento
- Entender o modelo relacional e seus componentes
- Reconhecer a importância da modelagem conceitual
- Aplicar conceitos básicos de normalização

## Conteúdo Teórico

### 1. Conceitos Fundamentais

#### 1.1 Dados vs. Informação vs. Conhecimento
- **Dados**: Fatos brutos sem contexto (ex: "The Beatles", "1965", "Help!")
- **Informação**: Dados processados com significado (ex: "The Beatles lançou o álbum 'Help!' em 1965")
- **Conhecimento**: Informação aplicada com experiência (ex: "The Beatles foi uma banda influente dos anos 60")

#### 1.2 Sistema de Gerenciamento de Banco de Dados (SGBD)
Um SGBD é um software que permite:
- Armazenar dados de forma organizada
- Controlar o acesso aos dados
- Garantir a integridade e segurança
- Fornecer mecanismos de backup e recuperação

### 2. Modelo Relacional

#### 2.1 Componentes Básicos
- **Tabela (Relação)**: Estrutura que armazena dados em linhas e colunas
- **Tupla (Linha)**: Conjunto de valores relacionados
- **Atributo (Coluna)**: Característica específica de uma entidade
- **Domínio**: Conjunto de valores válidos para um atributo

#### 2.2 Propriedades das Relações
1. Cada tabela tem um nome único
2. Cada coluna tem um nome único dentro da tabela
3. A ordem das linhas e colunas é irrelevante
4. Cada célula contém apenas um valor (atomicidade)
5. Não existem linhas duplicadas

### 3. Conceitos de Modelagem

#### 3.1 Entidades
Uma entidade representa algo do mundo real sobre o qual queremos armazenar informações:
- **Usuário**: Pessoa que usa o sistema MusiStream
- **Artista**: Músico ou banda que cria música
- **Música**: Faixa musical individual
- **Álbum**: Coleção de músicas

#### 3.2 Atributos
Características das entidades:
- **Simples**: Não podem ser subdivididos (ex: nome_usuario)
- **Compostos**: Podem ser subdivididos (ex: endereço = rua + cidade + cep)
- **Monovalorados**: Um valor por entidade (ex: data_nascimento)
- **Multivalorados**: Múltiplos valores possíveis (ex: telefones)

#### 3.3 Relacionamentos
Associações entre entidades:
- **1:1 (Um para Um)**: Uma entidade A relaciona-se com apenas uma entidade B
- **1:N (Um para Muitos)**: Uma entidade A relaciona-se com várias entidades B
- **N:M (Muitos para Muitos)**: Várias entidades A relacionam-se com várias entidades B

### 4. Chaves e Integridade

#### 4.1 Tipos de Chaves
- **Chave Primária**: Identifica unicamente cada tupla
- **Chave Estrangeira**: Referencia a chave primária de outra tabela
- **Chave Candidata**: Atributo(s) que podem ser chave primária
- **Chave Composta**: Chave formada por múltiplos atributos

#### 4.2 Integridade Referencial
Garante que as relações entre tabelas sejam mantidas:
- Toda chave estrangeira deve referenciar uma chave primária existente
- Operações de inserção, atualização e exclusão devem preservar a integridade

### 5. Níveis de Modelagem de Dados

A modelagem de dados é um processo estruturado que ocorre em três níveis distintos, cada um com objetivos específicos e graus de abstração diferentes.

#### 5.1 Modelagem Conceitual

**Objetivo**: Representar a realidade do negócio de forma independente de tecnologia.

**Características**:
- Foco nas **entidades**, **atributos** e **relacionamentos**
- Independente de SGBD específico
- Usa notação de alto nível (ex: Diagrama Entidade-Relacionamento)
- Voltado para usuários finais e analistas de negócio

**Exemplo Conceitual - Sistema Biblioteca**:
```
LIVRO ──── possui ──── AUTOR
│                        │
└── pertence ── CATEGORIA
```

**Elementos principais**:
- **Entidades**: LIVRO, AUTOR, CATEGORIA
- **Relacionamentos**: "possui", "pertence"
- **Cardinalidades**: 1:N, N:M, etc.

#### 5.2 Modelagem Lógica

**Objetivo**: Traduzir o modelo conceitual para estruturas que podem ser implementadas em um SGBD relacional.

**Características**:
- Introduz conceitos como **tabelas**, **chaves primárias** e **chaves estrangeiras**
- Define tipos de dados em nível abstrato
- Aplica regras de normalização
- Still independent of specific DBMS implementation

**Exemplo Lógico - Sistema Biblioteca**:
```sql
LIVRO (id_livro, titulo, isbn, ano_publicacao, id_categoria)
AUTOR (id_autor, nome, nacionalidade)
CATEGORIA (id_categoria, nome_categoria)
LIVRO_AUTOR (id_livro, id_autor)  -- Tabela associativa para N:M
```

#### 5.3 Modelagem Física

**Objetivo**: Implementar o modelo lógico em um SGBD específico, considerando performance e otimização.

**Características**:
- Define tipos de dados específicos do SGBD
- Inclui **índices**, **constraints** e **triggers**
- Considera aspectos de **performance** e **armazenamento**
- Específico para um SGBD (Oracle, MySQL, PostgreSQL, etc.)

**Exemplo Físico - Sistema Biblioteca (MySQL)**:
```sql
CREATE TABLE LIVRO (
    id_livro INT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(200) NOT NULL,
    isbn VARCHAR(13) UNIQUE,
    ano_publicacao YEAR,
    id_categoria INT,
    INDEX idx_titulo (titulo),
    INDEX idx_ano (ano_publicacao),
    FOREIGN KEY (id_categoria) REFERENCES CATEGORIA(id_categoria)
);
```

### 6. Processo de Normalização Detalhado

A normalização é um processo sistemático de organização dos dados para reduzir redundância e melhorar a integridade.

#### 6.1 Primeira Forma Normal (1FN)

**Regras**:
- Cada atributo contém apenas **valores atômicos** (indivisíveis)
- Não há **grupos repetitivos**
- Cada coluna tem um **nome único**
- A ordem das linhas é **irrelevante**

**Exemplo de violação da 1FN**:
```
CLIENTE_PEDIDO (Não normalizada)
---------------------------------
id_cliente | nome | telefones         | produtos
1          | João | 11999887766,      | Mouse, Teclado,
           |      | 11988776655       | Monitor
```

**Correção para 1FN**:
```
CLIENTE
-------
id_cliente | nome
1          | João

TELEFONE_CLIENTE
----------------
id_cliente | telefone
1          | 11999887766
1          | 11988776655

PRODUTO_PEDIDO
--------------
id_pedido | id_cliente | produto
1         | 1          | Mouse
1         | 1          | Teclado
1         | 1          | Monitor
```

#### 6.2 Segunda Forma Normal (2FN)

**Regras**:
- Deve estar na **1FN**
- Todos os atributos não-chave dependem **totalmente** da chave primária
- Elimina **dependências parciais**

**Exemplo de violação da 2FN**:
```
ITEM_PEDIDO (Chave composta: id_pedido + id_produto)
----------------------------------------------------
id_pedido | id_produto | quantidade | nome_produto | preco_unitario
1         | 101        | 2          | Mouse        | 25.00
1         | 102        | 1          | Teclado      | 80.00
```

**Problema**: `nome_produto` depende apenas de `id_produto`, não da chave completa.

**Correção para 2FN**:
```
PRODUTO
-------
id_produto | nome_produto | preco_unitario
101        | Mouse        | 25.00
102        | Teclado      | 80.00

ITEM_PEDIDO
-----------
id_pedido | id_produto | quantidade
1         | 101        | 2
1         | 102        | 1
```

#### 6.3 Terceira Forma Normal (3FN)

**Regras**:
- Deve estar na **2FN**
- Não há **dependências transitivas** entre atributos não-chave
- Atributos não-chave não dependem de outros atributos não-chave

**Exemplo de violação da 3FN**:
```
FUNCIONARIO
-----------
id_funcionario | nome | id_departamento | nome_departamento | orcamento_depto
1              | Ana  | 10             | Vendas           | 100000
2              | João | 10             | Vendas           | 100000
```

**Problema**: `nome_departamento` e `orcamento_depto` dependem de `id_departamento`, não da chave primária.

**Correção para 3FN**:
```
DEPARTAMENTO
------------
id_departamento | nome_departamento | orcamento_depto
10             | Vendas           | 100000

FUNCIONARIO
-----------
id_funcionario | nome | id_departamento
1              | Ana  | 10
2              | João | 10
```

### 7. Padrões de Design e Boas Práticas

#### 7.1 Convenções de Nomenclatura

**Tabelas**:
```sql
-- ✅ Boas práticas
USUARIO, PRODUTO, PEDIDO_ITEM

-- ❌ Evitar
usuarios, tbl_usuario, user_table
```

**Colunas**:
```sql
-- ✅ Boas práticas
id_usuario, nome_completo, data_nascimento

-- ❌ Evitar
userID, NomeCompleto, dt_nasc
```

**Chaves Estrangeiras**:
```sql
-- ✅ Padrão consistente
id_categoria (referencia CATEGORIA.id_categoria)
id_cliente (referencia CLIENTE.id_cliente)

-- ❌ Inconsistente
categoria_id, cliente_cod
```

#### 7.2 Estratégias de Chaveamento

**Chaves Surrogate (Recomendado)**:
```sql
CREATE TABLE PRODUTO (
    id_produto INT AUTO_INCREMENT PRIMARY KEY,  -- Chave surrogate
    codigo_barras VARCHAR(13) UNIQUE,           -- Chave natural
    nome VARCHAR(100) NOT NULL
);
```

**Vantagens das chaves surrogate**:
- **Estabilidade**: Nunca mudam
- **Performance**: Inteiros são mais eficientes
- **Simplicidade**: Relacionamentos mais simples

**Chaves Compostas (Uso específico)**:
```sql
CREATE TABLE ITEM_PEDIDO (
    id_pedido INT,
    id_produto INT,
    quantidade INT,
    PRIMARY KEY (id_pedido, id_produto),
    FOREIGN KEY (id_pedido) REFERENCES PEDIDO(id_pedido),
    FOREIGN KEY (id_produto) REFERENCES PRODUTO(id_produto)
);
```

#### 7.3 Padrões de Relacionamento

**Padrão 1:N (Um para Muitos)**:
```sql
-- Cliente possui muitos pedidos
CLIENTE (id_cliente, nome, email)
PEDIDO (id_pedido, id_cliente, data_pedido)
```

**Padrão N:M (Muitos para Muitos)**:
```sql
-- Produtos podem estar em muitos pedidos
-- Pedidos podem ter muitos produtos
PRODUTO (id_produto, nome, preco)
PEDIDO (id_pedido, data_pedido)
ITEM_PEDIDO (id_pedido, id_produto, quantidade)  -- Tabela associativa
```

**Padrão de Hierarquia (Self-Join)**:
```sql
-- Funcionário pode ter um supervisor
FUNCIONARIO (
    id_funcionario,
    nome,
    id_supervisor,  -- FK para FUNCIONARIO.id_funcionario
    FOREIGN KEY (id_supervisor) REFERENCES FUNCIONARIO(id_funcionario)
);
```

#### 7.4 Tratamento de Dados Históricos

**Estratégia 1: Soft Delete**:
```sql
PRODUTO (
    id_produto,
    nome,
    preco,
    ativo BOOLEAN DEFAULT TRUE,      -- Marca ativo/inativo
    data_inativacao TIMESTAMP NULL   -- Quando foi inativado
);
```

**Estratégia 2: Tabelas de Histórico**:
```sql
PRODUTO (id_produto, nome, preco, versao)
PRODUTO_HISTORICO (id_produto, nome, preco, versao, data_alteracao, usuario_alteracao)
```

#### 7.5 Padrões de Auditoria

**Campos de Auditoria Padrão**:
```sql
CREATE TABLE EXEMPLO (
    id_exemplo INT PRIMARY KEY,
    dados VARCHAR(100),
    
    -- Campos de auditoria
    data_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    usuario_criacao VARCHAR(50),
    data_alteracao TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    usuario_alteracao VARCHAR(50)
);
```

### 8. Erros Comuns na Modelagem

#### 8.1 Desnormalização Prematura

**❌ Erro comum**:
```sql
-- Colocar tudo em uma tabela "para ser mais simples"
PEDIDO_COMPLETO (
    id_pedido, data_pedido, nome_cliente, endereco_cliente,
    nome_produto, preco_produto, quantidade
);
```

**✅ Solução correta**:
```sql
CLIENTE (id_cliente, nome, endereco)
PRODUTO (id_produto, nome, preco)
PEDIDO (id_pedido, id_cliente, data_pedido)
ITEM_PEDIDO (id_pedido, id_produto, quantidade)
```

#### 8.2 Uso Inadequado de Campos Genéricos

**❌ Evitar**:
```sql
ENTIDADE_GENERICA (
    id,
    tipo,      -- 'CLIENTE', 'FORNECEDOR', 'FUNCIONARIO'
    campo1,    -- Significado varia conforme tipo
    campo2,    -- Significado varia conforme tipo
    campo3     -- Significado varia conforme tipo
);
```

**✅ Preferir**:
```sql
CLIENTE (id_cliente, nome, cpf, email)
FORNECEDOR (id_fornecedor, razao_social, cnpj, contato)
FUNCIONARIO (id_funcionario, nome, matricula, departamento)
```

#### 8.3 Relacionamentos Mal Definidos

**❌ Problema: Relacionamento Many-to-Many sem tabela associativa**:
```sql
PRODUTO (id_produto, nome, categorias)  -- String com múltiplas categorias
```

**✅ Solução correta**:
```sql
PRODUTO (id_produto, nome)
CATEGORIA (id_categoria, nome)
PRODUTO_CATEGORIA (id_produto, id_categoria)  -- Tabela associativa
```

### 9. Ferramentas e Metodologias

#### 9.1 Ferramentas de Modelagem

**Ferramentas Gratuitas**:
- **MySQL Workbench**: Modelagem visual e engenharia reversa
- **Draw.io**: Diagramas ER online
- **BrModelo**: Ferramenta brasileira para modelagem conceitual
- **DBeaver**: Visualização de esquemas existentes

**Ferramentas Profissionais**:
- **ERwin Data Modeler**: Ferramenta corporativa completa
- **IBM InfoSphere Data Architect**: Modelagem enterprise
- **Oracle SQL Developer Data Modeler**: Específica para Oracle

#### 9.2 Metodologia de Modelagem

**Processo Estruturado**:

1. **Análise de Requisitos**:
   - Entrevistas com stakeholders
   - Análise de documentos existentes
   - Identificação de processos de negócio

2. **Modelagem Conceitual**:
   - Identificação de entidades
   - Definição de relacionamentos
   - Validação com usuários finais

3. **Modelagem Lógica**:
   - Aplicação de regras de normalização
   - Definição de chaves e constraints
   - Review técnico

4. **Modelagem Física**:
   - Implementação no SGBD escolhido
   - Otimização de performance
   - Testes de carga

**Checklist de Validação**:
- [ ] Todos os requisitos funcionais são atendidos?
- [ ] O modelo está normalizado adequadamente?
- [ ] As nomenclaturas seguem padrões consistentes?
- [ ] Há documentação suficiente?
- [ ] Performance foi considerada?

## Exemplo Prático: Sistema MusiStream

## Exemplo Prático: Sistema MusiStream

### Walkthrough Completo do Processo de Modelagem

#### Etapa 1: Análise de Requisitos

**Requisitos funcionais identificados**:
1. Usuários podem se cadastrar e fazer login
2. Artistas podem ter múltiplos álbuns
3. Álbuns contêm múltiplas músicas
4. Usuários podem criar e compartilhar playlists
5. Sistema deve rastrear histórico de reprodução
6. Músicas são categorizadas por gêneros
7. Usuários podem ter diferentes tipos de assinatura

#### Etapa 2: Modelo Conceitual

**Entidades identificadas**:
- USUARIO, ARTISTA, ALBUM, MUSICA, PLAYLIST, GENERO, ASSINATURA

**Relacionamentos principais**:
```
USUARIO ──(1:N)── PLAYLIST ──(N:M)── MUSICA
ARTISTA ──(1:N)── ALBUM ──(1:N)── MUSICA
GENERO ──(1:N)── MUSICA
USUARIO ──(1:1)── ASSINATURA
USUARIO ──(1:N)── HISTORICO_REPRODUCAO ──(N:1)── MUSICA
```

#### Etapa 3: Modelo Lógico

**Aplicação de normalização**:

```sql
-- Entidades fortes
USUARIO (id_usuario, nome, email, senha, data_nascimento, pais, data_cadastro)
ARTISTA (id_artista, nome_artista, biografia, data_formacao, pais_origem)
GENERO (id_genero, nome_genero, descricao)
TIPO_ASSINATURA (id_tipo, nome_tipo, preco_mensal, limite_downloads)

-- Entidades dependentes
ALBUM (id_album, titulo, data_lancamento, id_artista)
MUSICA (id_musica, titulo, duracao, numero_faixa, id_album, id_genero)
PLAYLIST (id_playlist, nome, descricao, publica, data_criacao, id_usuario)
ASSINATURA (id_assinatura, data_inicio, data_fim, status, id_usuario, id_tipo)

-- Tabelas associativas
PLAYLIST_MUSICA (id_playlist, id_musica, ordem, data_adicao)
HISTORICO_REPRODUCAO (id_usuario, id_musica, data_reproducao, dispositivo)
```

### Modelo Conceitual Inicial

#### Entidades Principais:
1. **USUARIO**
   - id_usuario (PK)
   - nome_usuario
   - email
   - data_nascimento
   - data_cadastro

2. **ARTISTA**
   - id_artista (PK)
   - nome_artista
   - biografia
   - data_formacao
   - pais_origem

3. **ALBUM**
   - id_album (PK)
   - titulo
   - data_lancamento
   - id_artista (FK)

4. **MUSICA**
   - id_musica (PK)
   - titulo
   - duracao
   - id_album (FK)

#### Modelo Expandido com Novas Entidades:

5. **PLAYLIST**
   - id_playlist (PK)
   - nome_playlist
   - descricao
   - publica (S/N)
   - data_criacao
   - id_usuario (FK)

6. **GENERO**
   - id_genero (PK)
   - nome_genero
   - descricao

7. **ASSINATURA**
   - id_assinatura (PK)
   - tipo_assinatura
   - data_inicio
   - data_fim
   - status
   - id_usuario (FK)

8. **HISTORICO_REPRODUCAO**
   - id_historico (PK)
   - data_reproducao
   - dispositivo
   - id_usuario (FK)
   - id_musica (FK)

#### Relacionamentos Expandidos:
- ARTISTA → ALBUM (1:N): Um artista pode ter vários álbuns
- ALBUM → MUSICA (1:N): Um álbum pode ter várias músicas
- GENERO → MUSICA (1:N): Um gênero pode categorizar várias músicas
- USUARIO → PLAYLIST (1:N): Um usuário pode criar várias playlists
- PLAYLIST → MUSICA (N:M): Uma playlist pode ter várias músicas, uma música pode estar em várias playlists
- USUARIO → ASSINATURA (1:1): Um usuário tem uma assinatura ativa
- USUARIO → HISTORICO_REPRODUCAO (1:N): Um usuário tem múltiplos registros de reprodução
- MUSICA → HISTORICO_REPRODUCAO (1:N): Uma música pode ser reproduzida múltiplas vezes

#### Análise de Integridade Referencial:

**Cenários de exclusão**:
1. **Usuário deletado**: Manter playlists como "usuário anônimo" ou deletar em cascata
2. **Artista removido**: Manter álbuns e músicas para preservar histórico
3. **Álbum removido**: Definir política para músicas órfãs
4. **Música removida**: Remover de playlists e manter histórico

## Exercícios Práticos

Consulte a pasta `exercicios/` para atividades hands-on que reforçam os conceitos apresentados.

## Exemplos Práticos Adicionais

### 📁 Pasta `exemplos/`

**Arquivos disponíveis**:

1. **[modelo_conceitual_inicial.sql](exemplos/modelo_conceitual_inicial.sql)**
   - Estrutura conceitual básica do sistema MusiStream
   - Comentários explicativos sobre decisões de design
   - Análise de normalização passo a passo

2. **[processo-modelagem-completo.md](exemplos/processo-modelagem-completo.md)**
   - Walkthrough completo de um projeto de e-commerce
   - Da análise de requisitos à implementação física
   - Inclui todas as etapas do processo de modelagem

3. **[erros-comuns-modelagem.md](exemplos/erros-comuns-modelagem.md)**
   - 10 erros mais comuns na modelagem de dados
   - Exemplos práticos de problemas e soluções
   - Checklist para evitar armadilhas comuns

## Tópicos Avançados para Estudo Futuro

### 10.1 Modelagem Temporal
- **Dados históricos**: Como modelar mudanças ao longo do tempo
- **Versionamento**: Estratégias para manter versões de dados
- **Slowly Changing Dimensions**: Técnicas de Data Warehouse

### 10.2 Modelagem para Performance
- **Desnormalização controlada**: Quando e como fazer
- **Índices**: Estratégias de indexação para diferentes tipos de consulta
- **Particionamento**: Divisão de tabelas grandes

### 10.3 Padrões Especiais
- **Padrão de Herança**: Modelagem de hierarquias complexas
- **Padrão de Polimorfismo**: Relacionamentos com múltiplos tipos
- **Padrão de Agregação**: Dados sumarizados e métricas

### 10.4 Modelagem NoSQL
- **Quando usar**: Cenários apropriados para bancos não-relacionais
- **Tipos**: Documento, Chave-Valor, Grafo, Coluna
- **Desnormalização**: Estratégias diferentes do modelo relacional

## Checklist de Validação de Modelo

### ✅ Completude
- [ ] Todas as entidades necessárias foram identificadas?
- [ ] Todos os relacionamentos foram mapeados?
- [ ] Todos os atributos essenciais estão presentes?
- [ ] As regras de negócio estão representadas?

### ✅ Consistência
- [ ] Nomenclatura segue padrões definidos?
- [ ] Tipos de dados são apropriados?
- [ ] Chaves primárias e estrangeiras estão corretas?
- [ ] Não há redundância desnecessária?

### ✅ Normalização
- [ ] Modelo está na forma normal adequada?
- [ ] Dependências funcionais foram analisadas?
- [ ] Não há dependências transitivas problemáticas?
- [ ] Valores atômicos estão garantidos?

### ✅ Integridade
- [ ] Constraints de integridade estão definidas?
- [ ] Políticas de exclusão estão claras?
- [ ] Valores únicos estão protegidos?
- [ ] Campos obrigatórios estão identificados?

### ✅ Performance
- [ ] Índices necessários foram identificados?
- [ ] Consultas frequentes foram consideradas?
- [ ] Estratégias de otimização foram avaliadas?
- [ ] Volumes de dados foram estimados?

## Questões para Reflexão

1. **Evolução do Modelo**: Como o modelo pode crescer conforme novos requisitos aparecem?

2. **Trade-offs**: Quais são os compromissos entre normalização e performance em seu projeto?

3. **Manutenibilidade**: Como garantir que o modelo seja fácil de entender e manter?

4. **Escalabilidade**: O modelo suporta crescimento significativo de dados e usuários?

5. **Flexibilidade**: Quão fácil é adaptar o modelo para mudanças nos requisitos de negócio?

## Referências Bibliográficas

1. **Elmasri, R. & Navathe, S.** (2016). *Fundamentals of Database Systems*. 7th Edition. Pearson. Capítulos 1-3.

2. **Date, C.J.** (2012). *SQL and Relational Theory: How to Write Accurate SQL Code*. 2nd Edition. O'Reilly Media. Capítulos 1-2.

3. **Silberschatz, A., Galvin, P. B., & Gagne, G.** (2018). *Operating System Concepts*. 10th Edition. John Wiley & Sons. Capítulo sobre sistemas de arquivos e bancos de dados.

4. **Codd, E.F.** (1970). "A Relational Model of Data for Large Shared Data Banks". *Communications of the ACM*, 13(6), 377-387.

5. **Chen, P.P.** (1976). "The Entity-Relationship Model: Toward a Unified View of Data". *ACM Transactions on Database Systems*, 1(1), 9-36.

## Material Complementar

### Artigos Recomendados:
- "Database Design Basics" - Microsoft SQL Server Documentation
- "Database Normalization Explained" - Oracle Documentation
- "Introduction to Database Design" - IBM Knowledge Center

### Vídeos Educacionais:
- "Database Design Course" - freeCodeCamp
- "Database Design Tutorial" - Derek Banas
- "Relational Database Design" - MIT OpenCourseWare

### Ferramentas de Modelagem:
- **MySQL Workbench**: Ferramenta visual para design de banco
- **Draw.io**: Ferramenta online para diagramas ER
- **Lucidchart**: Plataforma de diagramação colaborativa

---

**Próximo Módulo**: [02 - Introdução à História SQL](../02-introducao-historia-sql/README.md)

**Dica de Estudo**: Pratique criando diagramas ER para sistemas que você conhece (biblioteca, loja online, rede social) antes de prosseguir para o próximo módulo.