# Módulo 03 - Interface SQL Plus, Tabelas e Regras

## Objetivos de Aprendizagem

Ao final deste módulo, o aluno será capaz de:
- Utilizar interfaces SQL básicas (SQL*Plus, MySQL Workbench, pgAdmin)
- Executar comandos SQL fundamentais
- Criar tabelas com tipos de dados apropriados
- Implementar regras de integridade básicas
- Compreender a sintaxe fundamental do SQL

## Conteúdo Teórico

### 1. Ambientes SQL

#### 1.1 SQL*Plus (Oracle)
**Interface de linha de comando tradicional do Oracle**

Características:
- Interface text-based
- Comandos de formatação específicos
- Variáveis de substituição
- Scripts executáveis

**Comandos básicos do SQL*Plus**:
```sql
-- Conectar ao banco
CONNECT username/password@database

-- Mostrar estrutura de tabela
DESCRIBE nome_tabela;
DESC nome_tabela;

-- Formatação de colunas
COLUMN nome_artista FORMAT A30
COLUMN pais_origem FORMAT A20

-- Configurar ambiente
SET PAGESIZE 50
SET LINESIZE 100
```

#### 1.2 Outras Interfaces Comuns

**MySQL Workbench**:
- Interface gráfica
- Editor visual de queries
- Diagramas ER integrados
- Administração visual

**pgAdmin (PostgreSQL)**:
- Interface web-based
- Gerenciamento completo
- Query tool avançado

**SQL Server Management Studio (SSMS)**:
- Interface gráfica completa
- IntelliSense para SQL
- Planos de execução visuais

### 2. Sintaxe Fundamental do SQL

#### 2.1 Estrutura Básica dos Comandos
```sql
-- Padrão geral de uma consulta SQL
SELECT [DISTINCT] lista_colunas
FROM nome_tabela
[WHERE condição]
[GROUP BY colunas]
[HAVING condição]
[ORDER BY colunas];
```

#### 2.2 Convenções de Nomenclatura
**Boas práticas**:
- Nomes descritivos e claros
- Evitar espaços (usar underscore)
- Consistência na nomenclatura
- Prefixos para identificar tipos

**Exemplos do sistema MusiStream**:
```sql
-- Tabelas: singular, minúsculo com underscore
usuario
artista  
album
musica

-- Colunas: descritivas com prefixos quando necessário
id_usuario
nome_usuario
email_usuario
data_nascimento
```

### 3. Criação de Tabelas - DDL (Data Definition Language)

#### 3.1 Comando CREATE TABLE
**Sintaxe básica**:
```sql
CREATE TABLE nome_tabela (
    nome_coluna1 tipo_dados [constraints],
    nome_coluna2 tipo_dados [constraints],
    ...
    [constraint_tabela]
);
```

#### 3.2 Tipos de Dados Comuns

**Numéricos**:
```sql
-- Inteiros
INT, INTEGER           -- Números inteiros
SMALLINT              -- Inteiros pequenos  
BIGINT                -- Inteiros grandes
DECIMAL(p,s)          -- Decimais exatos
NUMERIC(p,s)          -- Sinônimo de DECIMAL
FLOAT                 -- Ponto flutuante
REAL                  -- Ponto flutuante simples
DOUBLE PRECISION      -- Ponto flutuante duplo
```

**Caracteres**:
```sql
-- Strings de tamanho fixo
CHAR(n)               -- Exatamente n caracteres

-- Strings de tamanho variável
VARCHAR(n)            -- Até n caracteres
VARCHAR2(n)           -- Oracle específico

-- Textos longos
TEXT                  -- MySQL/PostgreSQL
CLOB                  -- Oracle
NVARCHAR(MAX)         -- SQL Server
```

**Datas e Tempo**:
```sql
DATE                  -- Data (ano-mês-dia)
TIME                  -- Hora (hora:minuto:segundo)
TIMESTAMP             -- Data e hora completas
DATETIME              -- SQL Server/MySQL
```

**Booleanos**:
```sql
BOOLEAN               -- PostgreSQL
BOOL                  -- MySQL
BIT                   -- SQL Server (0 ou 1)
```

### 4. Implementando o Sistema MusiStream

#### 4.1 Tabela de Usuários
```sql
-- Criação da tabela USUARIO
CREATE TABLE usuario (
    id_usuario       INTEGER PRIMARY KEY,
    nome_usuario     VARCHAR(100) NOT NULL,
    email            VARCHAR(150) NOT NULL UNIQUE,
    data_nascimento  DATE,
    data_cadastro    TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    ativo            BOOLEAN DEFAULT TRUE
);
```

**Análise dos componentes**:
- `PRIMARY KEY`: Define chave primária
- `NOT NULL`: Campo obrigatório
- `UNIQUE`: Valor único na tabela
- `DEFAULT`: Valor padrão quando não especificado

#### 4.2 Tabela de Artistas
```sql
-- Criação da tabela ARTISTA
CREATE TABLE artista (
    id_artista       INTEGER PRIMARY KEY,
    nome_artista     VARCHAR(100) NOT NULL,
    biografia        TEXT,
    data_formacao    DATE,
    pais_origem      VARCHAR(50),
    ativo            BOOLEAN DEFAULT TRUE,
    
    -- Constraint de verificação
    CONSTRAINT ck_data_formacao 
    CHECK (data_formacao <= CURRENT_DATE)
);
```

#### 4.3 Tabela de Álbuns
```sql
-- Criação da tabela ALBUM
CREATE TABLE album (
    id_album         INTEGER PRIMARY KEY,
    titulo           VARCHAR(150) NOT NULL,
    data_lancamento  DATE,
    numero_faixas    INTEGER,
    duracao_total    INTEGER, -- em segundos
    id_artista       INTEGER NOT NULL,
    
    -- Chave estrangeira
    CONSTRAINT fk_album_artista 
    FOREIGN KEY (id_artista) REFERENCES artista(id_artista),
    
    -- Constraints de verificação
    CONSTRAINT ck_numero_faixas 
    CHECK (numero_faixas > 0),
    
    CONSTRAINT ck_duracao_total 
    CHECK (duracao_total > 0)
);
```

#### 4.4 Tabela de Músicas
```sql
-- Criação da tabela MUSICA
CREATE TABLE musica (
    id_musica        INTEGER PRIMARY KEY,
    titulo           VARCHAR(150) NOT NULL,
    duracao          INTEGER NOT NULL, -- em segundos
    numero_faixa     INTEGER,
    letra            TEXT,
    id_album         INTEGER NOT NULL,
    
    -- Chave estrangeira
    CONSTRAINT fk_musica_album 
    FOREIGN KEY (id_album) REFERENCES album(id_album),
    
    -- Constraints de verificação
    CONSTRAINT ck_duracao_musica 
    CHECK (duracao > 0 AND duracao <= 3600), -- máximo 1 hora
    
    CONSTRAINT ck_numero_faixa 
    CHECK (numero_faixa > 0),
    
    -- Constraint única composta
    CONSTRAINT uk_musica_album_faixa 
    UNIQUE (id_album, numero_faixa)
);
```

### 5. Constraints e Regras de Integridade

#### 5.1 Tipos de Constraints

**PRIMARY KEY**:
- Identifica unicamente cada linha
- Automaticamente NOT NULL e UNIQUE
- Apenas uma por tabela

**FOREIGN KEY**:
- Referencia chave primária de outra tabela
- Mantém integridade referencial
- Pode ser NULL (se permitido)

**UNIQUE**:
- Garante valores únicos
- Diferente de PRIMARY KEY, pode ser NULL
- Múltiplas constraints UNIQUE por tabela

**NOT NULL**:
- Campo obrigatório
- Não permite valores nulos

**CHECK**:
- Valida condições específicas
- Expressões booleanas
- Aplicado na inserção/atualização

#### 5.2 Exemplos Avançados de Constraints

**Constraint de domínio**:
```sql
-- Tabela para gêneros musicais
CREATE TABLE genero (
    id_genero        INTEGER PRIMARY KEY,
    nome_genero      VARCHAR(50) NOT NULL UNIQUE,
    descricao        TEXT,
    
    -- Constraint de valores específicos
    CONSTRAINT ck_nome_genero 
    CHECK (nome_genero IN ('Rock', 'Pop', 'Jazz', 'Blues', 
                          'Classical', 'Electronic', 'Hip-Hop',
                          'Country', 'Reggae', 'Folk'))
);
```

**Constraint de data**:
```sql
-- Tabela para histórico de reprodução
CREATE TABLE historico_reproducao (
    id_historico     INTEGER PRIMARY KEY,
    id_usuario       INTEGER NOT NULL,
    id_musica        INTEGER NOT NULL,
    data_reproducao  TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    duracao_ouvida   INTEGER, -- segundos ouvidos
    
    CONSTRAINT fk_hist_usuario 
    FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario),
    
    CONSTRAINT fk_hist_musica 
    FOREIGN KEY (id_musica) REFERENCES musica(id_musica),
    
    -- Data não pode ser no futuro
    CONSTRAINT ck_data_reproducao 
    CHECK (data_reproducao <= CURRENT_TIMESTAMP),
    
    -- Duração ouvida não pode ser negativa
    CONSTRAINT ck_duracao_ouvida 
    CHECK (duracao_ouvida >= 0)
);
```

### 6. Comandos de Consulta Básicos

#### 6.1 SELECT Simples
```sql
-- Selecionar todas as colunas
SELECT * FROM artista;

-- Selecionar colunas específicas
SELECT nome_artista, pais_origem FROM artista;

-- Usar aliases para colunas
SELECT nome_artista AS "Nome do Artista",
       pais_origem AS "País"
FROM artista;
```

#### 6.2 Filtros Básicos com WHERE
```sql
-- Filtro por igualdade
SELECT * FROM artista 
WHERE pais_origem = 'Brasil';

-- Filtros com operadores
SELECT titulo, duracao 
FROM musica 
WHERE duracao > 180; -- músicas com mais de 3 minutos

-- Múltiplas condições
SELECT nome_usuario, email 
FROM usuario 
WHERE ativo = TRUE 
  AND data_cadastro >= '2023-01-01';
```

#### 6.3 Ordenação com ORDER BY
```sql
-- Ordenação crescente (padrão)
SELECT nome_artista FROM artista 
ORDER BY nome_artista;

-- Ordenação decrescente
SELECT titulo, data_lancamento FROM album 
ORDER BY data_lancamento DESC;

-- Múltiplas colunas
SELECT nome_artista, data_formacao 
FROM artista 
ORDER BY pais_origem, nome_artista;
```

### 7. Comandos de Manipulação Básicos

#### 7.1 INSERT - Inserindo Dados
```sql
-- Insert com todas as colunas
INSERT INTO artista (id_artista, nome_artista, biografia, 
                    data_formacao, pais_origem, ativo)
VALUES (1, 'The Beatles', 'Banda britânica de rock...', 
        '1960-08-17', 'Reino Unido', TRUE);

-- Insert especificando apenas algumas colunas
INSERT INTO artista (id_artista, nome_artista, pais_origem)
VALUES (2, 'Caetano Veloso', 'Brasil');
```

#### 7.2 UPDATE - Atualizando Dados
```sql
-- Update com WHERE específico
UPDATE artista 
SET biografia = 'Cantor e compositor brasileiro...'
WHERE id_artista = 2;

-- Update múltiplas colunas
UPDATE usuario 
SET nome_usuario = 'João da Silva Santos',
    email = 'joao.santos@email.com'
WHERE id_usuario = 1;
```

#### 7.3 DELETE - Removendo Dados
```sql
-- Delete com condição específica
DELETE FROM usuario 
WHERE ativo = FALSE 
  AND data_cadastro < '2022-01-01';

-- Cuidado: Delete sem WHERE remove tudo!
-- DELETE FROM artista; -- PERIGOSO!
```

### 8. Boas Práticas

#### 8.1 Convenções de Nomenclatura
```sql
-- ✅ Boas práticas
CREATE TABLE usuario_perfil (
    id_perfil        INTEGER PRIMARY KEY,
    id_usuario       INTEGER NOT NULL,
    tipo_perfil      VARCHAR(20) DEFAULT 'basico',
    
    CONSTRAINT fk_perfil_usuario 
    FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario)
);

-- ❌ Evitar
CREATE TABLE "Usuário Perfil" (
    "ID do Perfil"   INTEGER PRIMARY KEY,
    "ID-Usuário"     INTEGER NOT NULL
);
```

#### 8.2 Documentação de Tabelas
```sql
-- Comentários nas tabelas
COMMENT ON TABLE artista IS 
'Tabela que armazena informações dos artistas musicais';

COMMENT ON COLUMN artista.biografia IS 
'Texto livre com informações biográficas do artista';

COMMENT ON COLUMN artista.data_formacao IS 
'Data de início da carreira artística ou formação da banda';
```

## Exercícios Práticos

Consulte a pasta `exercicios/` para atividades hands-on que reforçam os conceitos apresentados.

## Perguntas e Respostas

### 1. Quais são as principais diferenças entre as interfaces SQL mais utilizadas?

**Resposta**: As principais interfaces variam em funcionalidades:
- **SQL*Plus (Oracle)**: Interface texto, comandos de formatação específicos, ideal para scripts
- **MySQL Workbench**: Interface gráfica completa, editor visual de queries, diagramas ER
- **pgAdmin (PostgreSQL)**: Interface web-based, gerenciamento completo via browser
- **SSMS (SQL Server)**: Interface gráfica com IntelliSense, planos de execução visuais

Cada uma atende diferentes necessidades de produtividade e experiência do usuário.

### 2. Qual a importância de seguir convenções de nomenclatura em SQL?

**Resposta**: Convenções de nomenclatura são fundamentais para:
- **Legibilidade**: Facilitar compreensão do código por outros desenvolvedores
- **Manutenibilidade**: Simplificar futuras modificações e correções
- **Consistência**: Manter padrão em toda a base de código
- **Portabilidade**: Evitar problemas entre diferentes SGBDs
- **Produtividade**: Reduzir tempo de desenvolvimento

Exemplo: `id_usuario`, `nome_usuario` são mais claros que `id`, `nome`.

### 3. Como escolher o tipo de dado apropriado para cada coluna?

**Resposta**: A escolha deve considerar:
- **Natureza dos dados**: Números (INT, DECIMAL), textos (VARCHAR), datas (DATE)
- **Tamanho**: VARCHAR(50) vs TEXT para textos grandes
- **Precisão**: DECIMAL para valores monetários vs FLOAT para cálculos aproximados
- **Performance**: Tipos menores são mais eficientes
- **Restrições**: CHAR para códigos fixos, VARCHAR para textos variáveis

Exemplo no MusiStream: `email VARCHAR(150)` comporta emails longos, `duracao INTEGER` para segundos.

### 4. Qual a diferença entre PRIMARY KEY e UNIQUE?

**Resposta**:
- **PRIMARY KEY**: 
  - Apenas uma por tabela
  - Automaticamente NOT NULL
  - Identifica unicamente cada linha
  - Cria índice automaticamente
- **UNIQUE**:
  - Múltiplas por tabela
  - Pode aceitar NULL (apenas um NULL por coluna)
  - Garante unicidade mas não é identificador principal
  - Exemplo: `email UNIQUE` na tabela usuario

### 5. Como as FOREIGN KEYS garantem integridade referencial?

**Resposta**: Foreign Keys garantem integridade através de:
- **Validação na inserção**: Valor deve existir na tabela referenciada
- **Controle de exclusão**: Impede excluir registros que são referenciados
- **Manutenção automática**: Alguns SGBDs oferecem CASCADE para atualizações/exclusões
- **Relacionamentos válidos**: Assegura que `id_artista` em `album` sempre aponte para artista existente

Exemplo: `FOREIGN KEY (id_artista) REFERENCES artista(id_artista)`

### 6. Quando usar constraints CHECK e quais suas limitações?

**Resposta**: Use CHECK para:
- **Validar domínios**: `CHECK (idade >= 0 AND idade <= 120)`
- **Regras de negócio**: `CHECK (data_formacao <= CURRENT_DATE)`
- **Valores específicos**: `CHECK (status IN ('ativo', 'inativo', 'suspenso'))`

**Limitações**:
- Não pode referenciar outras tabelas
- Não pode usar subconsultas
- Performance impacto em grandes volumes
- Dificulta mudanças futuras se muito restritiva

### 7. Quais os cuidados essenciais ao executar comandos UPDATE e DELETE?

**Resposta**: Cuidados fundamentais:
- **Sempre usar WHERE**: DELETE/UPDATE sem WHERE afeta toda a tabela
- **Testar com SELECT**: Execute SELECT com mesma condição antes do UPDATE/DELETE
- **Backup**: Realizar backup antes de operações críticas
- **Transações**: Usar BEGIN/COMMIT para operações em lote
- **Validação**: Verificar número de linhas afetadas

```sql
-- ✅ Correto
UPDATE usuario SET ativo = FALSE WHERE id_usuario = 123;

-- ❌ Perigoso
UPDATE usuario SET ativo = FALSE; -- Afeta TODOS os usuários
```

## Referências Bibliográficas

1. **Oracle Corporation** (2021). *Oracle Database SQL Language Reference*. Oracle Documentation.

2. **Beaulieu, A.** (2020). *Learning SQL: Master SQL Fundamentals*. 3rd Edition. O'Reilly Media. Capítulos 2-4.

3. **Forta, B.** (2018). *SQL in 10 Minutes, Sams Teach Yourself*. 5th Edition. Sams Publishing. Lições 1-6.

4. **PostgreSQL Global Development Group** (2023). *PostgreSQL Documentation*. Seções sobre DDL e DML.

5. **Gulutzan, P. & Pelzer, T.** (1999). *SQL-99 Complete, Really*. CMP Books. Capítulos sobre DDL.

---

**Módulo Anterior**: [02 - Introdução à História SQL](../02-introducao-historia-sql/README.md)
**Próximo Módulo**: [04 - Trabalhando com a Estrutura de Tabelas](../04-trabalhando-estrutura-tabelas/README.md)

**Dica Prática**: Pratique executando os comandos em um ambiente SQL real. A experiência hands-on é fundamental para consolidar o aprendizado.