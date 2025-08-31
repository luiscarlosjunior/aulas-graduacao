# Módulo 04 - Trabalhando com a Estrutura de Tabelas

## Objetivos de Aprendizagem

Ao final deste módulo, o aluno será capaz de:
- Analisar e compreender estruturas de tabelas existentes
- Trabalhar com diferentes tipos de dados na prática
- Definir estruturas de tabelas otimizadas
- Aplicar boas práticas na criação de tabelas
- Preparar estruturas para implementação de relacionamentos
- Compreender aspectos de armazenamento e performance

## Conteúdo Teórico

### 1. Análise de Estruturas de Tabelas

#### 1.1 Compreendendo Estruturas Existentes

**Comando DESCRIBE/DESC**:
```sql
-- Oracle/MySQL
DESCRIBE nome_tabela;
DESC nome_tabela;

-- PostgreSQL
\d nome_tabela

-- SQL Server
sp_help nome_tabela;
```

**Exemplo prático - Sistema MusiStream**:
```sql
-- Analisando a estrutura da tabela USUARIO
DESC usuario;

-- Resultado esperado:
-- Nome           Nulo?    Tipo
-- -------------- -------- ----------------
-- ID_USUARIO     NOT NULL NUMBER(10)
-- NOME_USUARIO   NOT NULL VARCHAR2(100)
-- EMAIL          NOT NULL VARCHAR2(150)
-- DATA_NASC              DATE
-- DATA_CADASTRO          TIMESTAMP(6)
-- ATIVO                  NUMBER(1)
```

#### 1.2 Consultas de Metadados

**Consultando informações do sistema**:
```sql
-- Oracle - Informações sobre colunas
SELECT column_name, data_type, data_length, nullable, data_default
FROM user_tab_columns 
WHERE table_name = 'USUARIO'
ORDER BY column_id;

-- MySQL - Schema Information
SELECT column_name, data_type, is_nullable, column_default
FROM information_schema.columns 
WHERE table_name = 'usuario' 
  AND table_schema = DATABASE();

-- PostgreSQL - Informações detalhadas
SELECT column_name, data_type, is_nullable, column_default
FROM information_schema.columns 
WHERE table_name = 'usuario';
```

### 2. Tipos de Dados - Aplicação Prática

#### 2.1 Escolhendo Tipos Adequados

**Para o Sistema MusiStream**:

**Identificadores únicos**:
```sql
-- Sempre use tipos numéricos para IDs
id_usuario    INTEGER          -- ou NUMBER(10) no Oracle
id_artista    BIGINT          -- para volumes maiores
id_musica     SERIAL          -- PostgreSQL auto-increment
```

**Textos e strings**:
```sql
-- Nomes e títulos
nome_usuario     VARCHAR(100)    -- Nome de pessoas
titulo_musica    VARCHAR(200)    -- Títulos podem ser longos
email           VARCHAR(254)    -- Tamanho máximo do email RFC

-- Textos longos
biografia       TEXT            -- Biografias de artistas
letra_musica    TEXT            -- Letras completas
descricao       VARCHAR(500)    -- Descrições limitadas
```

**Dados numéricos específicos**:
```sql
-- Durações em segundos
duracao_musica   INTEGER         -- Segundos (máx: ~596 horas)
duracao_album    INTEGER         -- Soma das durações

-- Avaliações e rankings
avaliacao       DECIMAL(3,2)    -- 0.00 a 9.99
popularidade    INTEGER         -- Ranking de 1 a 100
numero_faixa    SMALLINT        -- 1 a 32767
```

**Datas e timestamps**:
```sql
-- Datas específicas
data_nascimento  DATE           -- Só a data
data_lancamento  DATE           -- Data de lançamento do álbum

-- Timestamps completos
data_cadastro    TIMESTAMP      -- Momento exato do cadastro
ultima_atividade TIMESTAMP      -- Última interação do usuário
```

**Valores booleanos**:
```sql
-- Estados sim/não
ativo           BOOLEAN         -- PostgreSQL
ativo           TINYINT(1)      -- MySQL (0 ou 1)
ativo           BIT             -- SQL Server
ativo           NUMBER(1)       -- Oracle (0 ou 1)
```

#### 2.2 Exemplo Completo - Tabela de Playlists

```sql
-- Estrutura otimizada para playlist
CREATE TABLE playlist (
    id_playlist      INTEGER PRIMARY KEY,
    nome_playlist    VARCHAR(150) NOT NULL,
    descricao        VARCHAR(500),
    publica          BOOLEAN DEFAULT FALSE,
    data_criacao     TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    data_modificacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    id_usuario       INTEGER NOT NULL,
    numero_musicas   INTEGER DEFAULT 0,
    duracao_total    INTEGER DEFAULT 0, -- em segundos
    imagem_capa      VARCHAR(255),      -- URL da imagem
    
    -- Verificações básicas
    CHECK (numero_musicas >= 0),
    CHECK (duracao_total >= 0),
    CHECK (LENGTH(nome_playlist) >= 1)
);
```

### 3. Estratégias de Criação de Tabelas

#### 3.1 Abordagem Incremental

**Passo 1 - Estrutura Básica**:
```sql
-- Começar com o essencial
CREATE TABLE artista_temp (
    id_artista   INTEGER PRIMARY KEY,
    nome_artista VARCHAR(100) NOT NULL
);
```

**Passo 2 - Adicionar Campos Complementares**:
```sql
-- Expandir gradualmente
ALTER TABLE artista_temp 
ADD COLUMN biografia TEXT;

ALTER TABLE artista_temp 
ADD COLUMN data_formacao DATE;

ALTER TABLE artista_temp 
ADD COLUMN pais_origem VARCHAR(50);
```

**Passo 3 - Versão Final Consolidada**:
```sql
-- Criar versão final otimizada
CREATE TABLE artista (
    id_artista      INTEGER PRIMARY KEY,
    nome_artista    VARCHAR(100) NOT NULL,
    nome_artistico  VARCHAR(100),           -- Nome profissional
    biografia       TEXT,
    data_formacao   DATE,
    data_fim        DATE,                   -- Para bandas extintas
    pais_origem     VARCHAR(50),
    cidade_origem   VARCHAR(100),
    genero_principal VARCHAR(50),
    website         VARCHAR(255),
    ativo           BOOLEAN DEFAULT TRUE,
    verificado      BOOLEAN DEFAULT FALSE,   -- Artista verificado
    
    -- Validações
    CHECK (data_fim IS NULL OR data_fim >= data_formacao),
    CHECK (LENGTH(nome_artista) >= 1)
);
```

#### 3.2 Considerações de Design

**Normalização vs Performance**:
```sql
-- Opção 1: Normalizada (múltiplas tabelas)
CREATE TABLE endereco_artista (
    id_endereco  INTEGER PRIMARY KEY,
    id_artista   INTEGER NOT NULL,
    pais         VARCHAR(50),
    estado       VARCHAR(50),
    cidade       VARCHAR(100),
    FOREIGN KEY (id_artista) REFERENCES artista(id_artista)
);

-- Opção 2: Desnormalizada (campos diretos)
CREATE TABLE artista_completo (
    id_artista    INTEGER PRIMARY KEY,
    nome_artista  VARCHAR(100) NOT NULL,
    pais_origem   VARCHAR(50),
    estado_origem VARCHAR(50),
    cidade_origem VARCHAR(100)
    -- Mais rápido para consultas, mais espaço usado
);
```

### 4. Trabalhando com Estruturas do MusiStream

#### 4.1 Estrutura Principal do Sistema

**Hierarquia de Entidades**:
```
USUARIO (usuários da plataforma)
    ↓
PLAYLIST (listas criadas pelos usuários)
    ↓
PLAYLIST_MUSICA (relação N:N entre playlist e música)
    ↓
MUSICA (faixas individuais)
    ↓
ALBUM (coleções de músicas)
    ↓
ARTISTA (intérpretes e compositores)
```

#### 4.2 Implementação das Tabelas Principais

**Tabela de Usuários Completa**:
```sql
CREATE TABLE usuario (
    id_usuario       INTEGER PRIMARY KEY,
    nome_usuario     VARCHAR(100) NOT NULL,
    sobrenome        VARCHAR(100),
    nome_exibicao    VARCHAR(150),          -- Nome mostrado publicamente
    email            VARCHAR(254) NOT NULL UNIQUE,
    senha_hash       VARCHAR(255) NOT NULL, -- Hash da senha
    data_nascimento  DATE,
    genero           CHAR(1),               -- M/F/O/N (Outro/Não informado)
    pais             VARCHAR(50),
    idioma_pref      VARCHAR(10) DEFAULT 'pt-BR',
    data_cadastro    TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    ultima_atividade TIMESTAMP,
    ativo            BOOLEAN DEFAULT TRUE,
    email_verificado BOOLEAN DEFAULT FALSE,
    
    -- Validações
    CHECK (genero IN ('M', 'F', 'O', 'N')),
    CHECK (data_nascimento <= CURRENT_DATE - INTERVAL '13 years'), -- Mínimo 13 anos
    CHECK (LENGTH(nome_usuario) >= 2),
    CHECK (email LIKE '%@%.%')
);
```

**Tabela de Gêneros Musicais**:
```sql
CREATE TABLE genero_musical (
    id_genero       INTEGER PRIMARY KEY,
    nome_genero     VARCHAR(50) NOT NULL UNIQUE,
    descricao       TEXT,
    genero_pai      INTEGER,                -- Para subgêneros
    cor_tema        VARCHAR(7),             -- Código hexadecimal da cor
    icone           VARCHAR(100),           -- Nome do ícone
    ativo           BOOLEAN DEFAULT TRUE,
    
    FOREIGN KEY (genero_pai) REFERENCES genero_musical(id_genero),
    CHECK (LENGTH(nome_genero) >= 2),
    CHECK (cor_tema IS NULL OR cor_tema LIKE '#______')
);
```

**Tabela de Artistas Expandida**:
```sql
CREATE TABLE artista (
    id_artista       INTEGER PRIMARY KEY,
    nome_artista     VARCHAR(100) NOT NULL,
    tipo_artista     VARCHAR(20) NOT NULL,  -- 'individual', 'banda', 'grupo'
    biografia        TEXT,
    data_formacao    DATE,
    data_fim         DATE,
    pais_origem      VARCHAR(50),
    cidade_origem    VARCHAR(100),
    genero_principal INTEGER,
    website          VARCHAR(255),
    instagram        VARCHAR(100),           -- @username
    twitter          VARCHAR(100),           -- @username
    facebook         VARCHAR(255),           -- URL completa
    spotify_id       VARCHAR(50),            -- ID no Spotify real
    imagem_perfil    VARCHAR(255),           -- URL da imagem
    imagem_banner    VARCHAR(255),           -- URL do banner
    seguidores       INTEGER DEFAULT 0,
    verificado       BOOLEAN DEFAULT FALSE,
    ativo            BOOLEAN DEFAULT TRUE,
    
    FOREIGN KEY (genero_principal) REFERENCES genero_musical(id_genero),
    
    CHECK (tipo_artista IN ('individual', 'banda', 'grupo')),
    CHECK (data_fim IS NULL OR data_fim >= data_formacao),
    CHECK (seguidores >= 0),
    CHECK (LENGTH(nome_artista) >= 1)
);
```

**Tabela de Álbuns Detalhada**:
```sql
CREATE TABLE album (
    id_album         INTEGER PRIMARY KEY,
    titulo           VARCHAR(200) NOT NULL,
    tipo_album       VARCHAR(20) NOT NULL,  -- 'studio', 'live', 'compilation', 'ep', 'single'
    data_lancamento  DATE,
    duracao_total    INTEGER DEFAULT 0,     -- segundos
    numero_faixas    INTEGER DEFAULT 0,
    gravadora        VARCHAR(100),
    produtor         VARCHAR(200),
    id_artista       INTEGER NOT NULL,
    genero_album     INTEGER,
    capa_album       VARCHAR(255),          -- URL da capa
    preco            DECIMAL(10,2),         -- Preço de venda
    disponivel       BOOLEAN DEFAULT TRUE,
    explicito        BOOLEAN DEFAULT FALSE, -- Conteúdo explícito
    
    FOREIGN KEY (id_artista) REFERENCES artista(id_artista),
    FOREIGN KEY (genero_album) REFERENCES genero_musical(id_genero),
    
    CHECK (tipo_album IN ('studio', 'live', 'compilation', 'ep', 'single')),
    CHECK (numero_faixas >= 0),
    CHECK (duracao_total >= 0),
    CHECK (preco IS NULL OR preco >= 0),
    CHECK (LENGTH(titulo) >= 1)
);
```

### 5. Otimização e Performance

#### 5.1 Considerações de Armazenamento

**Tamanhos de Campos Adequados**:
```sql
-- ❌ Evitar desperdício
nome_usuario VARCHAR(1000)  -- Muito grande para nomes

-- ✅ Tamanhos adequados
nome_usuario VARCHAR(100)   -- Suficiente para nomes
codigo_pais  CHAR(2)        -- ISO padrão (BR, US, etc)
ativo        BOOLEAN        -- Não usar VARCHAR para sim/não
```

**Campos com Valores Padrão Inteligentes**:
```sql
CREATE TABLE configuracao_usuario (
    id_usuario        INTEGER PRIMARY KEY,
    tema              VARCHAR(20) DEFAULT 'escuro',
    qualidade_audio   VARCHAR(20) DEFAULT 'alta',
    volume_padrao     INTEGER DEFAULT 75,
    reproducao_auto   BOOLEAN DEFAULT TRUE,
    notificacoes      BOOLEAN DEFAULT TRUE,
    modo_offline      BOOLEAN DEFAULT FALSE,
    
    CHECK (volume_padrao >= 0 AND volume_padrao <= 100),
    CHECK (tema IN ('claro', 'escuro', 'auto')),
    CHECK (qualidade_audio IN ('baixa', 'media', 'alta', 'lossless'))
);
```

#### 5.2 Preparação para Índices

**Campos que Precisarão de Índices**:
```sql
-- Estes campos serão muito consultados, prepare-os adequadamente
CREATE TABLE musica (
    id_musica        INTEGER PRIMARY KEY,
    titulo           VARCHAR(200) NOT NULL,  -- Será indexado para buscas
    duracao          INTEGER NOT NULL,
    id_album         INTEGER NOT NULL,       -- FK, será indexada
    numero_faixa     SMALLINT,
    data_upload      TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- Ordenação temporal
    reproduções      BIGINT DEFAULT 0,       -- Ranking de popularidade
    explicito        BOOLEAN DEFAULT FALSE,
    
    -- Campos para busca textual
    titulo_search    VARCHAR(200),           -- Versão normalizada para busca
    
    CHECK (duracao > 0 AND duracao <= 7200), -- Máximo 2 horas
    CHECK (numero_faixa > 0),
    CHECK (reproduções >= 0)
);
```

### 6. Validação e Testes de Estrutura

#### 6.1 Testando Tipos de Dados

**Script de Teste para Validação**:
```sql
-- Teste de inserção com dados válidos
INSERT INTO usuario (id_usuario, nome_usuario, email, data_nascimento, genero)
VALUES (1, 'João Silva', 'joao@email.com', '1990-05-15', 'M');

-- Teste de inserção com dados inválidos (deve falhar)
-- INSERT INTO usuario (id_usuario, nome_usuario, email, data_nascimento, genero)
-- VALUES (2, 'X', 'email_invalido', '2020-01-01', 'X'); -- Deve dar erro

-- Verificar se a inserção funcionou
SELECT * FROM usuario WHERE id_usuario = 1;
```

#### 6.2 Verificando Limites

**Testando Constraints**:
```sql
-- Teste de limite de caracteres
INSERT INTO artista (id_artista, nome_artista, tipo_artista)
VALUES (1, 'Nome Artista Teste', 'individual');

-- Teste de data futura (deve falhar se tiver CHECK apropriado)
-- INSERT INTO album (id_album, titulo, data_lancamento, id_artista)
-- VALUES (1, 'Álbum Teste', '2030-01-01', 1);

-- Verificar estrutura criada
DESC artista;
DESC album;
```

## Exercícios Práticos

Consulte a pasta `exercicios/` para atividades hands-on que reforçam os conceitos apresentados.

## Arquivos de Exemplo

Na pasta `exemplos/` você encontrará:
- `estruturas_basicas.sql`: Criação das tabelas fundamentais
- `tipos_dados_praticos.sql`: Exemplos de diferentes tipos de dados
- `validacao_estruturas.sql`: Scripts para testar e validar estruturas
- `otimizacao_tabelas.sql`: Exemplos de estruturas otimizadas

## Perguntas e Respostas

### 1. Como interpretar corretamente a saída do comando DESCRIBE/DESC?

**Resposta**: A saída do DESCRIBE mostra informações essenciais:
- **Nome**: Nome da coluna
- **Nulo?**: Se aceita valores NULL (NOT NULL = obrigatório)
- **Tipo**: Tipo de dados e tamanho (ex: VARCHAR2(100), NUMBER(10))
- **Default**: Valor padrão quando não especificado

Exemplo de interpretação:
```
ID_USUARIO     NOT NULL NUMBER(10)     -- Chave primária, obrigatória
EMAIL          NOT NULL VARCHAR2(150)  -- Campo obrigatório, único
DATA_NASC               DATE           -- Campo opcional
```

### 2. Quais critérios usar para escolher entre VARCHAR e CHAR?

**Resposta**: A escolha depende da natureza dos dados:
- **CHAR(n)**: Para dados de tamanho fixo
  - Códigos de país: `CHAR(2)` para 'BR', 'US'
  - CEP: `CHAR(8)` para códigos brasileiros
  - Performance ligeiramente melhor para tamanhos fixos
- **VARCHAR(n)**: Para dados de tamanho variável
  - Nomes: tamanhos variam muito
  - Emails: comprimentos diferentes
  - Economiza espaço de armazenamento

Regra geral: Use CHAR apenas quando TODOS os valores têm exatamente o mesmo tamanho.

### 3. Como dimensionar adequadamente os tipos numéricos?

**Resposta**: Considere range e precisão necessários:

**Para contadores/IDs**:
- SMALLINT: até 32.767 (tabelas pequenas)
- INTEGER: até ~2 bilhões (uso geral)
- BIGINT: para grandes volumes ou IDs globais

**Para valores monetários**:
- DECIMAL(10,2): até 99.999.999,99
- DECIMAL(15,2): para valores maiores

**Para medidas**:
- FLOAT/REAL: quando precisão exata não é crítica
- DECIMAL: quando precisão é fundamental

### 4. Qual a importância de definir constraints adequadas?

**Resposta**: Constraints são fundamentais para:
- **Integridade dos dados**: Previnem dados inválidos
- **Performance**: Índices implícitos em PKs e UKs
- **Documentação**: Explicam regras de negócio no próprio esquema
- **Manutenção**: Detectam problemas automaticamente
- **Confiabilidade**: Garantem consistência mesmo com múltiplos usuários

Exemplo: `CHECK (duracao > 0)` impede músicas com duração inválida.

### 5. Como otimizar estruturas de tabelas para performance?

**Resposta**: Estratégias de otimização:

**Tipos de dados**:
- Use tipos menores quando possível: INT vs BIGINT
- VARCHAR dimensionado adequadamente (não excessivo)

**Organização de colunas**:
- Colunas mais usadas no início
- Colunas NULL ao final
- Agrupe colunas relacionadas

**Índices implícitos**:
- PRIMARY KEY cria índice automaticamente
- UNIQUE constraints também criam índices

**Normalização equilibrada**:
- Normalize para eliminar redundância
- Considere desnormalização seletiva para performance crítica

### 6. Quando usar diferentes tipos de dados para datas?

**Resposta**: Escolha baseada na necessidade:

- **DATE**: Apenas data (ano-mês-dia)
  - Data de nascimento: `data_nascimento DATE`
  - Data de lançamento de álbum

- **TIMESTAMP**: Data e hora completas
  - Log de ações: `data_acao TIMESTAMP`
  - Histórico de reprodução

- **TIME**: Apenas hora
  - Horário de funcionamento
  - Duração em formato tempo

Para o MusiStream: use DATE para datas de nascimento e TIMESTAMP para logs de atividade.

### 7. Como projetar tabelas pensando em evolução futura?

**Resposta**: Práticas para flexibilidade:

**Nomenclatura consistente**:
- Prefixos claros: `id_`, `nome_`, `data_`
- Padrões mantidos em todas as tabelas

**Tipos com margem**:
- VARCHAR com espaço extra: VARCHAR(150) vs VARCHAR(50)
- Campos de extensão: `configuracoes_json TEXT`

**Estrutura extensível**:
- Tabelas de configuração para valores dinâmicos
- Campos de metadados quando apropriado
- Versionamento de esquema documentado

**Constraints flexíveis**:
- Evite CHECK muito restritivos
- Use constraints que podem ser facilmente alteradas

## Referências Bibliográficas

1. **Date, C.J.** (2012). *SQL and Relational Theory: How to Write Accurate SQL Code*. 2nd Edition. O'Reilly Media. Capítulos 5-7.

2. **Garcia-Molina, H., Ullman, J.D. & Widom, J.** (2014). *Database Systems: The Complete Book*. 2nd Edition. Pearson. Seções sobre design físico.

3. **Kline, K.E., Kline, D. & Hunt, B.** (2009). *SQL in a Nutshell*. 3rd Edition. O'Reilly Media. Capítulos sobre DDL.

4. **Mullins, C.S.** (2012). *Database Administration: The Complete Guide to DBA Practices and Procedures*. 2nd Edition. Addison-Wesley. Capítulos sobre design de tabelas.

5. **Oracle Corporation** (2021). *Oracle Database Concepts*. Oracle Documentation. Seções sobre estruturas de armazenamento.

6. **PostgreSQL Global Development Group** (2023). *PostgreSQL Documentation*. Capítulos sobre tipos de dados e criação de tabelas.

---

**Módulo Anterior**: [03 - Interface SQL Plus, Tabelas e Regras](../03-interface-sql-plus-tabelas-regras/README.md)  
**Próximo Módulo**: [05 - Estrutura das Tabelas, Regras e Relacionamentos](../05-estrutura-tabelas-regras-relacionamentos/README.md)

**Dica de Estudo**: Este módulo é prático e hands-on. Execute todos os exemplos em seu ambiente SQL para consolidar o aprendizado sobre estruturas de tabelas.