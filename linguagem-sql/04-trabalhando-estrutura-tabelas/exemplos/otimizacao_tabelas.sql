-- ===============================================
-- Otimização de Tabelas - Sistema MusiStream
-- Módulo 04: Trabalhando com a Estrutura de Tabelas
-- ===============================================

-- ===============================================
-- 1. ANÁLISE DE NECESSIDADES DE ARMAZENAMENTO
-- ===============================================

-- Tabela para demonstrar impacto de diferentes tipos
CREATE TABLE comparacao_tipos (
    id                  INTEGER,
    
    -- Diferentes tipos para IDs
    id_smallint         SMALLINT,      -- 2 bytes
    id_integer          INTEGER,       -- 4 bytes  
    id_bigint           BIGINT,        -- 8 bytes
    
    -- Diferentes tipos para texto
    codigo_char3        CHAR(3),       -- Sempre 3 bytes + overhead
    codigo_varchar10    VARCHAR(10),   -- Até 10 bytes + overhead
    nome_varchar50      VARCHAR(50),   -- Até 50 bytes + overhead
    nome_varchar255     VARCHAR(255),  -- Até 255 bytes + overhead
    descricao_text      TEXT,          -- Variável + overhead
    
    -- Diferentes tipos para números
    contador_smallint   SMALLINT,      -- 2 bytes
    contador_integer    INTEGER,       -- 4 bytes
    preco_decimal       DECIMAL(10,2), -- Dependente da precisão
    percentual_real     REAL,          -- 4 bytes
    percentual_double   DOUBLE PRECISION, -- 8 bytes
    
    -- Tipos booleanos
    ativo_boolean       BOOLEAN,       -- 1 byte
    ativo_char1         CHAR(1),       -- 1 byte + overhead
    ativo_smallint      SMALLINT       -- 2 bytes
);

-- ===============================================
-- 2. ESTRUTURAS OTIMIZADAS PARA ALTA PERFORMANCE
-- ===============================================

-- Tabela de usuários otimizada para performance
CREATE TABLE usuario_otimizado (
    id_usuario          INTEGER PRIMARY KEY,           -- 4 bytes
    email_hash          CHAR(64) NOT NULL UNIQUE,      -- Hash do email para busca rápida
    nome_usuario        VARCHAR(50) NOT NULL,          -- Limitado ao essencial
    data_cadastro       DATE NOT NULL,                 -- Apenas data, não timestamp
    ultimo_acesso       DATE,                          -- Apenas data, não timestamp
    pais_codigo         CHAR(2),                       -- ISO country code
    ativo               BOOLEAN DEFAULT TRUE,          -- 1 byte
    tipo_conta          SMALLINT DEFAULT 1,            -- 1=free, 2=premium, 3=family
    
    -- Índices implícitos para performance
    CHECK (LENGTH(nome_usuario) >= 2),
    CHECK (tipo_conta IN (1, 2, 3))
);

-- Tabela separada para dados menos acessados
CREATE TABLE usuario_perfil_extendido (
    id_usuario          INTEGER PRIMARY KEY,
    nome_completo       VARCHAR(200),
    biografia           TEXT,
    data_nascimento     DATE,
    genero              CHAR(1),
    telefone            VARCHAR(20),
    endereco_completo   TEXT,
    preferencias_json   JSONB,
    
    FOREIGN KEY (id_usuario) REFERENCES usuario_otimizado(id_usuario),
    CHECK (genero IN ('M', 'F', 'O', 'N'))
);

-- ===============================================
-- 3. OTIMIZAÇÃO PARA CONSULTAS FREQUENTES
-- ===============================================

-- Tabela de músicas otimizada para streaming
CREATE TABLE musica_otimizada (
    id_musica           INTEGER PRIMARY KEY,
    titulo_normalizado  VARCHAR(200) NOT NULL,  -- Sem acentos para busca
    duracao_segundos    SMALLINT NOT NULL,      -- Máximo ~9 horas
    id_album            INTEGER NOT NULL,
    numero_faixa        SMALLINT,
    popularidade_score  INTEGER DEFAULT 0,      -- Para ranking
    explicito           BOOLEAN DEFAULT FALSE,
    disponivel          BOOLEAN DEFAULT TRUE,
    
    -- Dados para busca rápida
    genero_principal    SMALLINT,               -- Referência a tabela de gêneros
    ano_lancamento      SMALLINT,               -- Apenas o ano
    artista_principal   INTEGER NOT NULL,       -- Referência direta ao artista
    
    FOREIGN KEY (id_album) REFERENCES album(id_album),
    FOREIGN KEY (artista_principal) REFERENCES artista(id_artista),
    
    CHECK (duracao_segundos > 0),
    CHECK (numero_faixa > 0),
    CHECK (popularidade_score >= 0)
);

-- ===============================================
-- 4. PARTICIONAMENTO DE DADOS POR VOLUME
-- ===============================================

-- Tabela base para histórico de reprodução
CREATE TABLE historico_base (
    id_reproducao       BIGSERIAL,
    id_usuario          INTEGER NOT NULL,
    id_musica           INTEGER NOT NULL,
    data_reproducao     DATE NOT NULL,
    timestamp_inicio    TIME NOT NULL,
    duracao_ouvida      SMALLINT,
    fonte               SMALLINT,  -- 1=app, 2=web, 3=smart_tv
    pais_codigo         CHAR(2)
);

-- Tabelas particionadas por mês (exemplo conceitual)
CREATE TABLE historico_2023_01 (
    CHECK (data_reproducao >= '2023-01-01' AND data_reproducao < '2023-02-01')
) INHERITS (historico_base);

CREATE TABLE historico_2023_02 (
    CHECK (data_reproducao >= '2023-02-01' AND data_reproducao < '2023-03-01')
) INHERITS (historico_base);

-- Função para inserção automática na partição correta
CREATE OR REPLACE FUNCTION inserir_historico()
RETURNS TRIGGER AS $$
BEGIN
    IF NEW.data_reproducao >= '2023-01-01' AND NEW.data_reproducao < '2023-02-01' THEN
        INSERT INTO historico_2023_01 VALUES (NEW.*);
    ELSIF NEW.data_reproducao >= '2023-02-01' AND NEW.data_reproducao < '2023-03-01' THEN
        INSERT INTO historico_2023_02 VALUES (NEW.*);
    ELSE
        RAISE EXCEPTION 'Data de reprodução fora do range das partições: %', NEW.data_reproducao;
    END IF;
    RETURN NULL;
END;
$$ LANGUAGE plpgsql;

-- ===============================================
-- 5. ESTRUTURAS PARA CACHE E PERFORMANCE
-- ===============================================

-- Tabela materializada para estatísticas rápidas
CREATE TABLE estatisticas_artista_cache (
    id_artista          INTEGER PRIMARY KEY,
    nome_artista        VARCHAR(100),
    total_albums        SMALLINT DEFAULT 0,
    total_musicas       INTEGER DEFAULT 0,
    total_reproducoes   BIGINT DEFAULT 0,
    media_rating        DECIMAL(3,2),
    ultima_atualizacao  TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    
    FOREIGN KEY (id_artista) REFERENCES artista(id_artista)
);

-- Tabela para rankings pré-calculados
CREATE TABLE ranking_musicas_semanal (
    ranking_semana      DATE NOT NULL,      -- Data da semana (segunda-feira)
    posicao             SMALLINT NOT NULL,
    id_musica           INTEGER NOT NULL,
    reproduções_semana  INTEGER NOT NULL,
    variacao_posicao    SMALLINT,           -- +/- em relação à semana anterior
    
    PRIMARY KEY (ranking_semana, posicao),
    FOREIGN KEY (id_musica) REFERENCES musica(id_musica),
    
    CHECK (posicao > 0 AND posicao <= 100),  -- Top 100
    CHECK (reproduções_semana >= 0)
);

-- ===============================================
-- 6. DESNORMALIZAÇÃO CONTROLADA
-- ===============================================

-- Tabela desnormalizada para busca rápida
CREATE TABLE busca_musica_desnormalizada (
    id_musica           INTEGER PRIMARY KEY,
    titulo_musica       VARCHAR(200) NOT NULL,
    nome_artista        VARCHAR(100) NOT NULL,
    titulo_album        VARCHAR(200) NOT NULL,
    genero_nome         VARCHAR(50),
    ano_lancamento      SMALLINT,
    duracao_segundos    SMALLINT,
    popularidade        INTEGER DEFAULT 0,
    
    -- Campos para busca textual
    texto_busca         TEXT,  -- Concatenação de todos os textos
    
    FOREIGN KEY (id_musica) REFERENCES musica(id_musica)
);

-- Trigger para manter dados sincronizados
CREATE OR REPLACE FUNCTION atualizar_busca_desnormalizada()
RETURNS TRIGGER AS $$
BEGIN
    UPDATE busca_musica_desnormalizada 
    SET texto_busca = LOWER(
        COALESCE(NEW.titulo_musica, '') || ' ' ||
        COALESCE(NEW.nome_artista, '') || ' ' ||
        COALESCE(NEW.titulo_album, '') || ' ' ||
        COALESCE(NEW.genero_nome, '')
    )
    WHERE id_musica = NEW.id_musica;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

-- ===============================================
-- 7. OTIMIZAÇÃO DE TIPOS PARA CASOS ESPECÍFICOS
-- ===============================================

-- Configurações de usuário com tipos específicos
CREATE TABLE configuracao_otimizada (
    id_usuario          INTEGER PRIMARY KEY,
    
    -- Usar SMALLINT para valores pequenos conhecidos
    volume_padrao       SMALLINT CHECK (volume_padrao >= 0 AND volume_padrao <= 100),
    qualidade_audio     SMALLINT CHECK (qualidade_audio IN (1, 2, 3, 4)), -- 1=baixa, 4=lossless
    tema_interface      SMALLINT CHECK (tema_interface IN (1, 2, 3)),      -- 1=claro, 2=escuro, 3=auto
    
    -- Usar BIT para flags múltiplas
    flags_preferencias  BIT(8),  -- 8 flags diferentes em 1 byte
    
    -- Usar CHAR para códigos fixos
    idioma_codigo       CHAR(5),     -- pt-BR, en-US
    pais_codigo         CHAR(2),     -- BR, US
    
    -- Timestamp apenas onde necessário
    ultima_alteracao    TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- ===============================================
-- 8. ESTRUTURAS PARA ANALYTICS
-- ===============================================

-- Tabela agregada para métricas diárias
CREATE TABLE metricas_diarias (
    data_metrica        DATE PRIMARY KEY,
    usuarios_ativos     INTEGER DEFAULT 0,
    novas_reproducoes   BIGINT DEFAULT 0,
    novos_usuarios      INTEGER DEFAULT 0,
    receita_estimada    DECIMAL(12,2) DEFAULT 0,
    top_genero_id       INTEGER,
    top_artista_id      INTEGER,
    
    CHECK (usuarios_ativos >= 0),
    CHECK (novas_reproducoes >= 0),
    CHECK (novos_usuarios >= 0),
    CHECK (receita_estimada >= 0)
);

-- Tabela de dimensões para análise
CREATE TABLE dim_tempo (
    data_completa       DATE PRIMARY KEY,
    ano                 SMALLINT,
    mes                 SMALLINT,
    dia                 SMALLINT,
    dia_semana          SMALLINT,  -- 1=domingo, 7=sábado
    semana_ano          SMALLINT,
    trimestre           SMALLINT,
    eh_feriado          BOOLEAN DEFAULT FALSE,
    eh_fim_semana       BOOLEAN DEFAULT FALSE
);

-- ===============================================
-- 9. ESTRATÉGIAS DE COMPRESSÃO E ARMAZENAMENTO
-- ===============================================

-- Tabela com configurações de armazenamento (PostgreSQL)
CREATE TABLE logs_sistema (
    id_log              BIGSERIAL PRIMARY KEY,
    timestamp_log       TIMESTAMP NOT NULL,
    nivel_log           SMALLINT NOT NULL,  -- 1=debug, 2=info, 3=warn, 4=error
    modulo              VARCHAR(50) NOT NULL,
    mensagem            TEXT NOT NULL,
    dados_contexto      JSONB,
    
    CHECK (nivel_log BETWEEN 1 AND 4)
);

-- Configurar compressão (PostgreSQL específico)
-- ALTER TABLE logs_sistema SET (fillfactor = 90);

-- ===============================================
-- 10. COMPARAÇÃO DE PERFORMANCE
-- ===============================================

-- Inserir dados de teste para comparação
INSERT INTO comparacao_tipos VALUES
(1, 100, 100, 100, 
 'BR', 'Brasil', 'Nome Teste', 'Nome muito longo para teste de varchar',
 'Descrição longa em formato text que pode ter qualquer tamanho',
 50, 50, 19.99, 0.15, 0.15,
 TRUE, 'S', 1);

-- Analisar tamanho das estruturas
SELECT 
    schemaname,
    tablename,
    attname,
    n_distinct,
    avg_width,
    null_frac
FROM pg_stats 
WHERE schemaname = 'public' 
  AND tablename = 'comparacao_tipos';

-- Query para verificar espaço ocupado por cada tabela
SELECT 
    schemaname,
    tablename,
    pg_size_pretty(pg_total_relation_size(schemaname||'.'||tablename)) as size
FROM pg_tables 
WHERE schemaname = 'public'
ORDER BY pg_total_relation_size(schemaname||'.'||tablename) DESC;

-- ===============================================
-- 11. RECOMENDAÇÕES DE OTIMIZAÇÃO
-- ===============================================

/*
RESUMO DE BOAS PRÁTICAS IMPLEMENTADAS:

1. TIPOS APROPRIADOS:
   - SMALLINT para valores pequenos (0-32767)
   - INTEGER para IDs normais
   - BIGINT apenas para volumes muito altos
   - CHAR(n) para códigos fixos
   - VARCHAR com tamanhos apropriados
   - BOOLEAN para flags sim/não

2. DESNORMALIZAÇÃO CONTROLADA:
   - Campos calculados em tabelas separadas
   - Cache de dados frequentemente acessados
   - Tabelas de busca otimizadas

3. PARTICIONAMENTO:
   - Separar dados por data/volume
   - Tabelas de histórico particionadas
   - Herança para dados similares

4. PERFORMANCE:
   - Índices implícitos via PRIMARY KEY
   - Constraints para validação rápida
   - Tipos compactos para economia de espaço

5. MANUTENIBILIDADE:
   - Triggers para manter consistência
   - Comentários explicativos
   - Validações de domínio claras
*/

-- Verificar implementação
SELECT 'Estruturas otimizadas criadas com sucesso!' as status;