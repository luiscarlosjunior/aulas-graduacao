-- ===============================================
-- Tipos de Dados Práticos - Sistema MusiStream
-- Módulo 04: Trabalhando com a Estrutura de Tabelas
-- ===============================================

-- ===============================================
-- 1. DEMONSTRAÇÃO DE TIPOS NUMÉRICOS
-- ===============================================

-- Tabela para demonstrar diferentes tipos numéricos
CREATE TABLE demo_tipos_numericos (
    id              SERIAL PRIMARY KEY,    -- Auto-incremento
    pequeno_int     SMALLINT,              -- -32,768 a 32,767
    inteiro_normal  INTEGER,               -- -2 bilhões a 2 bilhões
    inteiro_grande  BIGINT,                -- Números muito grandes
    decimal_preco   DECIMAL(10,2),         -- Preços com 2 casas decimais
    percentual      DECIMAL(5,4),          -- 0.0000 a 9.9999 (percentuais)
    rating          DECIMAL(3,2),          -- 0.00 a 9.99 (avaliações)
    ponto_flutuante REAL,                  -- Números com ponto flutuante
    cientifica      DOUBLE PRECISION       -- Notação científica
);

-- Exemplos de inserção
INSERT INTO demo_tipos_numericos (
    pequeno_int, inteiro_normal, inteiro_grande,
    decimal_preco, percentual, rating,
    ponto_flutuante, cientifica
) VALUES 
(100, 1500000, 9999999999999999,
 29.99, 0.1250, 8.75,
 3.14159, 6.02214076e23);

-- Consultar para ver os tipos em ação
SELECT * FROM demo_tipos_numericos;

-- ===============================================
-- 2. DEMONSTRAÇÃO DE TIPOS DE TEXTO
-- ===============================================

CREATE TABLE demo_tipos_texto (
    id              INTEGER PRIMARY KEY,
    codigo_fixo     CHAR(3),               -- Sempre 3 caracteres
    nome_variavel   VARCHAR(50),           -- Até 50 caracteres
    descricao_media VARCHAR(500),          -- Até 500 caracteres
    texto_longo     TEXT,                  -- Sem limite específico
    email_validado  VARCHAR(254),          -- Tamanho máximo de email
    url_completa    VARCHAR(2048),         -- URLs podem ser longas
    hash_senha      VARCHAR(255)           -- Hashes têm tamanho fixo
);

-- Exemplos práticos do MusiStream
INSERT INTO demo_tipos_texto VALUES
(1, 'BR',  'João Silva', 'Usuário premium da plataforma',
 'Este é um texto muito longo que pode conter a biografia completa do artista, incluindo sua história, influências, discografia detalhada e muito mais informações.',
 'joao.silva@musicstream.com',
 'https://musicstream.com/artist/joao-silva?ref=profile&source=search',
 '$2b$12$LQv3c1yqBwmnZ0xNOldGVu8YHJv.xQMQL2t5GH3lU0UZ4XG8UoKM6');

-- ===============================================
-- 3. DEMONSTRAÇÃO DE TIPOS DE DATA/HORA
-- ===============================================

CREATE TABLE demo_tipos_temporais (
    id                  INTEGER PRIMARY KEY,
    data_nascimento     DATE,                    -- Apenas data (YYYY-MM-DD)
    momento_cadastro    TIMESTAMP,               -- Data e hora completas
    hora_preferida      TIME,                    -- Apenas hora (HH:MM:SS)
    timestamp_utc       TIMESTAMP WITH TIME ZONE, -- Com fuso horário
    intervalo_duracao   INTERVAL                 -- Períodos de tempo
);

-- Exemplos com dados do MusiStream
INSERT INTO demo_tipos_temporais VALUES
(1, '1990-05-15',                           -- Data de nascimento
    '2023-08-15 14:30:25',                  -- Momento do cadastro
    '09:00:00',                             -- Hora preferida para notificações
    '2023-08-15 14:30:25-03:00',           -- Timestamp com timezone
    '3 hours 25 minutes');                  -- Duração da playlist

-- ===============================================
-- 4. TIPOS BOOLEANOS E ESPECIAIS
-- ===============================================

CREATE TABLE demo_tipos_especiais (
    id              INTEGER PRIMARY KEY,
    ativo           BOOLEAN,               -- TRUE/FALSE
    dados_json      JSON,                  -- Dados estruturados (PostgreSQL)
    configuracoes   JSONB,                 -- JSON binário otimizado (PostgreSQL)
    tags            TEXT[],                -- Array de texto (PostgreSQL)
    metadata        HSTORE                 -- Chave-valor (PostgreSQL com extensão)
);

-- Exemplos para diferentes SGBDs
INSERT INTO demo_tipos_especiais (id, ativo) VALUES (1, TRUE);

-- Para PostgreSQL com suporte a JSON:
-- INSERT INTO demo_tipos_especiais VALUES
-- (1, TRUE, 
--  '{"preferences": {"theme": "dark", "quality": "high"}}',
--  '{"stats": {"plays": 1500, "likes": 42}}',
--  ARRAY['rock', 'classic', 'british'],
--  'artist_id=>123, verified=>true');

-- ===============================================
-- 5. ESCOLHENDO TIPOS ADEQUADOS - EXEMPLOS PRÁTICOS
-- ===============================================

-- Tabela otimizada para estatísticas de músicas
CREATE TABLE estatisticas_musica (
    id_musica           INTEGER PRIMARY KEY,
    reproduções_total   BIGINT DEFAULT 0,        -- Pode ser muito grande
    reproduções_mes     INTEGER DEFAULT 0,       -- Menor, mas ainda grande
    likes               INTEGER DEFAULT 0,       -- Relativamente pequeno
    dislikes            INTEGER DEFAULT 0,       -- Relativamente pequeno
    skips               INTEGER DEFAULT 0,       -- Pulos da música
    rating_medio        DECIMAL(3,2),           -- 0.00 a 9.99
    duracao_media_ouvida DECIMAL(5,2),          -- % da música ouvida
    ultima_reproducao   TIMESTAMP,              -- Quando foi tocada por último
    trend_score         REAL,                   -- Score de trending (float)
    
    -- Validações práticas
    CHECK (reproduções_total >= 0),
    CHECK (likes >= 0),
    CHECK (dislikes >= 0),
    CHECK (rating_medio IS NULL OR (rating_medio >= 0 AND rating_medio <= 10)),
    CHECK (duracao_media_ouvida IS NULL OR (duracao_media_ouvida >= 0 AND duracao_media_ouvida <= 100))
);

-- ===============================================
-- 6. TABELA DE ASSINATURAS - TIPOS MONETÁRIOS
-- ===============================================

CREATE TABLE plano_assinatura (
    id_plano            INTEGER PRIMARY KEY,
    nome_plano          VARCHAR(50) NOT NULL,
    preco_mensal        DECIMAL(10,2) NOT NULL,   -- Preço em reais
    preco_anual         DECIMAL(10,2),            -- Desconto anual
    max_downloads       INTEGER,                  -- Limite de downloads
    qualidade_maxima    VARCHAR(20),              -- 'baixa', 'media', 'alta', 'lossless'
    sem_anuncios        BOOLEAN DEFAULT FALSE,
    offline_disponivel  BOOLEAN DEFAULT FALSE,
    skips_ilimitados    BOOLEAN DEFAULT FALSE,
    
    -- Validações de negócio
    CHECK (preco_mensal > 0),
    CHECK (preco_anual IS NULL OR preco_anual > 0),
    CHECK (max_downloads IS NULL OR max_downloads >= 0),
    CHECK (qualidade_maxima IN ('baixa', 'media', 'alta', 'lossless'))
);

-- Inserir planos típicos de streaming
INSERT INTO plano_assinatura VALUES
(1, 'Gratuito', 0.00, NULL, 0, 'baixa', FALSE, FALSE, FALSE),
(2, 'Premium Individual', 19.90, 199.00, 10000, 'alta', TRUE, TRUE, TRUE),
(3, 'Premium Família', 29.90, 299.00, 50000, 'alta', TRUE, TRUE, TRUE),
(4, 'Premium Estudante', 9.90, 99.00, 5000, 'alta', TRUE, TRUE, TRUE),
(5, 'HiFi', 39.90, 399.00, 20000, 'lossless', TRUE, TRUE, TRUE);

-- ===============================================
-- 7. HISTÓRICO DE REPRODUÇÃO - VOLUME ALTO
-- ===============================================

CREATE TABLE historico_reproducao (
    id_reproducao       BIGSERIAL PRIMARY KEY,    -- Auto-incremento para grande volume
    id_usuario          INTEGER NOT NULL,
    id_musica           INTEGER NOT NULL,
    timestamp_inicio    TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    timestamp_fim       TIMESTAMP,
    duracao_ouvida      SMALLINT,                 -- Segundos ouvidos
    fonte_reproducao    VARCHAR(20),              -- 'app', 'web', 'smart_tv', etc.
    dispositivo         VARCHAR(50),              -- Modelo do dispositivo
    ip_usuario          INET,                     -- IP para análise geográfica
    pais_reproducao     CHAR(2),                  -- Código do país
    
    -- Indexes implícitos serão criados posteriormente
    FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario),
    FOREIGN KEY (id_musica) REFERENCES musica(id_musica),
    
    -- Validações de negócio
    CHECK (duracao_ouvida >= 0),
    CHECK (timestamp_fim IS NULL OR timestamp_fim >= timestamp_inicio),
    CHECK (fonte_reproducao IN ('app', 'web', 'smart_tv', 'alexa', 'google_home'))
);

-- ===============================================
-- 8. ANÁLISE DE DESEMPENHO DE TIPOS
-- ===============================================

-- Demonstração de como diferentes tipos afetam performance
CREATE TABLE analise_performance (
    -- IDs com diferentes tipos
    id_int              INTEGER,
    id_bigint           BIGINT,
    id_uuid             UUID,                     -- PostgreSQL
    
    -- Textos com diferentes tamanhos
    codigo_pequeno      CHAR(3),
    nome_medio          VARCHAR(100),
    descricao_grande    TEXT,
    
    -- Números com diferentes precisões
    contador_pequeno    SMALLINT,
    contador_normal     INTEGER,
    valor_monetario     DECIMAL(10,2),
    valor_cientifico    DOUBLE PRECISION,
    
    -- Tempo com diferentes precisões
    data_simples        DATE,
    timestamp_simples   TIMESTAMP(0),             -- Sem microssegundos
    timestamp_preciso   TIMESTAMP(6)              -- Com microssegundos
);

-- ===============================================
-- 9. COMPARANDO ABORDAGENS - EXEMPLO PRÁTICO
-- ===============================================

-- Abordagem 1: Campos separados (normalizada)
CREATE TABLE endereco_usuario_normalizado (
    id_endereco     INTEGER PRIMARY KEY,
    id_usuario      INTEGER NOT NULL,
    rua             VARCHAR(200),
    numero          VARCHAR(20),
    complemento     VARCHAR(100),
    bairro          VARCHAR(100),
    cidade          VARCHAR(100),
    estado          VARCHAR(50),
    pais            VARCHAR(50),
    cep             VARCHAR(20),
    
    FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario)
);

-- Abordagem 2: Campo único (desnormalizada)
CREATE TABLE endereco_usuario_denormalizado (
    id_usuario          INTEGER PRIMARY KEY,
    endereco_completo   TEXT,                   -- Endereço em texto livre
    
    FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario)
);

-- Abordagem 3: JSON estruturado (NoSQL-like)
CREATE TABLE endereco_usuario_json (
    id_usuario      INTEGER PRIMARY KEY,
    endereco_dados  JSONB,                      -- PostgreSQL
    
    FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario)
);

-- ===============================================
-- 10. VERIFICAÇÃO E LIMPEZA
-- ===============================================

-- Consultar informações sobre os tipos criados
SELECT 
    table_name,
    column_name,
    data_type,
    character_maximum_length,
    is_nullable,
    column_default
FROM information_schema.columns 
WHERE table_schema = 'public' 
  AND table_name LIKE 'demo_%'
ORDER BY table_name, ordinal_position;

-- Script de limpeza (descomente para usar)
-- DROP TABLE IF EXISTS analise_performance;
-- DROP TABLE IF EXISTS endereco_usuario_json;
-- DROP TABLE IF EXISTS endereco_usuario_denormalizado;
-- DROP TABLE IF EXISTS endereco_usuario_normalizado;
-- DROP TABLE IF EXISTS historico_reproducao;
-- DROP TABLE IF EXISTS plano_assinatura;
-- DROP TABLE IF EXISTS estatisticas_musica;
-- DROP TABLE IF EXISTS demo_tipos_especiais;
-- DROP TABLE IF EXISTS demo_tipos_temporais;
-- DROP TABLE IF EXISTS demo_tipos_texto;
-- DROP TABLE IF EXISTS demo_tipos_numericos;