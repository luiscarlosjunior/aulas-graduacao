-- =====================================================
-- CRIAÇÃO DO ESQUEMA BÁSICO MUSISTREAM
-- Módulo 03: Interface SQL Plus, Tabelas e Regras
-- =====================================================

-- =====================================================
-- 1. CONFIGURAÇÃO DO AMBIENTE SQL*PLUS
-- =====================================================

-- Configurações básicas do SQL*Plus
SET ECHO ON                    -- Mostrar comandos executados
SET PAGESIZE 50               -- Número de linhas por página
SET LINESIZE 120              -- Largura da linha
SET TIMING ON                 -- Mostrar tempo de execução

-- Formatação de colunas para melhor visualização
COLUMN nome_artista FORMAT A30
COLUMN titulo FORMAT A25
COLUMN email FORMAT A35
COLUMN pais_origem FORMAT A15

-- =====================================================
-- 2. REMOÇÃO DE TABELAS EXISTENTES (SE NECESSÁRIO)
-- =====================================================

-- Remover tabelas na ordem correta (dependências)
-- Comentar estas linhas na primeira execução
/*
DROP TABLE IF EXISTS historico_reproducao;
DROP TABLE IF EXISTS musica;
DROP TABLE IF EXISTS album;
DROP TABLE IF EXISTS artista;
DROP TABLE IF EXISTS usuario;
*/

-- =====================================================
-- 3. CRIAÇÃO DAS TABELAS PRINCIPAIS
-- =====================================================

-- Tabela USUARIO
-- Armazena informações dos usuários da plataforma
CREATE TABLE usuario (
    id_usuario       INTEGER PRIMARY KEY,
    nome_usuario     VARCHAR(100) NOT NULL,
    email            VARCHAR(150) NOT NULL UNIQUE,
    data_nascimento  DATE,
    data_cadastro    TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    ativo            BOOLEAN DEFAULT TRUE,
    
    -- Constraint para validar email básico
    CONSTRAINT ck_email_formato 
    CHECK (email LIKE '%@%.%'),
    
    -- Constraint para validar idade mínima
    CONSTRAINT ck_idade_minima 
    CHECK (data_nascimento <= CURRENT_DATE - INTERVAL '13' YEAR)
);

-- Comentários da tabela USUARIO
COMMENT ON TABLE usuario IS 
'Tabela que armazena informações dos usuários da plataforma MusiStream';

COMMENT ON COLUMN usuario.id_usuario IS 
'Identificador único do usuário (chave primária)';

COMMENT ON COLUMN usuario.email IS 
'Endereço de email único para login e comunicação';

COMMENT ON COLUMN usuario.ativo IS 
'Indica se a conta do usuário está ativa (TRUE) ou inativa (FALSE)';

-- =====================================================

-- Tabela ARTISTA
-- Armazena informações dos artistas e bandas
CREATE TABLE artista (
    id_artista       INTEGER PRIMARY KEY,
    nome_artista     VARCHAR(100) NOT NULL,
    biografia        TEXT,
    data_formacao    DATE,
    pais_origem      VARCHAR(50),
    ativo            BOOLEAN DEFAULT TRUE,
    numero_membros   INTEGER DEFAULT 1,
    
    -- Validação de data de formação
    CONSTRAINT ck_data_formacao 
    CHECK (data_formacao <= CURRENT_DATE),
    
    -- Validação de número de membros
    CONSTRAINT ck_numero_membros 
    CHECK (numero_membros > 0 AND numero_membros <= 20),
    
    -- Nome do artista não pode ser vazio
    CONSTRAINT ck_nome_artista_nao_vazio 
    CHECK (LENGTH(TRIM(nome_artista)) > 0)
);

-- Comentários da tabela ARTISTA
COMMENT ON TABLE artista IS 
'Tabela que armazena informações dos artistas musicais e bandas';

COMMENT ON COLUMN artista.numero_membros IS 
'Número de integrantes da banda (1 para artistas solo)';

-- =====================================================

-- Tabela ALBUM
-- Armazena informações dos álbuns musicais
CREATE TABLE album (
    id_album         INTEGER PRIMARY KEY,
    titulo           VARCHAR(150) NOT NULL,
    data_lancamento  DATE,
    numero_faixas    INTEGER,
    duracao_total    INTEGER, -- em segundos
    tipo_album       VARCHAR(20) DEFAULT 'album',
    id_artista       INTEGER NOT NULL,
    
    -- Chave estrangeira para artista
    CONSTRAINT fk_album_artista 
    FOREIGN KEY (id_artista) REFERENCES artista(id_artista)
    ON DELETE CASCADE,
    
    -- Validações de integridade
    CONSTRAINT ck_numero_faixas 
    CHECK (numero_faixas > 0 AND numero_faixas <= 100),
    
    CONSTRAINT ck_duracao_total 
    CHECK (duracao_total > 0 AND duracao_total <= 86400), -- máximo 24 horas
    
    CONSTRAINT ck_tipo_album 
    CHECK (tipo_album IN ('album', 'single', 'ep', 'compilacao', 'ao_vivo')),
    
    -- Data de lançamento não pode ser muito no futuro
    CONSTRAINT ck_data_lancamento 
    CHECK (data_lancamento <= CURRENT_DATE + INTERVAL '1' YEAR)
);

-- Comentários da tabela ALBUM
COMMENT ON TABLE album IS 
'Tabela que armazena informações dos álbuns musicais';

COMMENT ON COLUMN album.tipo_album IS 
'Tipo do álbum: album, single, ep, compilacao, ao_vivo';

COMMENT ON COLUMN album.duracao_total IS 
'Duração total do álbum em segundos';

-- =====================================================

-- Tabela MUSICA
-- Armazena informações das músicas individuais
CREATE TABLE musica (
    id_musica        INTEGER PRIMARY KEY,
    titulo           VARCHAR(150) NOT NULL,
    duracao          INTEGER NOT NULL, -- em segundos
    numero_faixa     INTEGER,
    letra            TEXT,
    explicita        BOOLEAN DEFAULT FALSE,
    id_album         INTEGER NOT NULL,
    
    -- Chave estrangeira para álbum
    CONSTRAINT fk_musica_album 
    FOREIGN KEY (id_album) REFERENCES album(id_album)
    ON DELETE CASCADE,
    
    -- Validações específicas de música
    CONSTRAINT ck_duracao_musica 
    CHECK (duracao > 0 AND duracao <= 3600), -- máximo 1 hora
    
    CONSTRAINT ck_numero_faixa 
    CHECK (numero_faixa > 0),
    
    CONSTRAINT ck_titulo_nao_vazio 
    CHECK (LENGTH(TRIM(titulo)) > 0),
    
    -- Garantir que não existam duas músicas com mesmo número de faixa no álbum
    CONSTRAINT uk_musica_album_faixa 
    UNIQUE (id_album, numero_faixa)
);

-- Comentários da tabela MUSICA
COMMENT ON TABLE musica IS 
'Tabela que armazena informações das músicas individuais';

COMMENT ON COLUMN musica.explicita IS 
'Indica se a música possui conteúdo explícito';

COMMENT ON COLUMN musica.duracao IS 
'Duração da música em segundos';

-- =====================================================

-- Tabela HISTORICO_REPRODUCAO
-- Armazena o histórico de reprodução dos usuários
CREATE TABLE historico_reproducao (
    id_historico     INTEGER PRIMARY KEY,
    id_usuario       INTEGER NOT NULL,
    id_musica        INTEGER NOT NULL,
    data_reproducao  TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    duracao_ouvida   INTEGER, -- segundos efetivamente ouvidos
    dispositivo      VARCHAR(50),
    
    -- Chaves estrangeiras
    CONSTRAINT fk_hist_usuario 
    FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario)
    ON DELETE CASCADE,
    
    CONSTRAINT fk_hist_musica 
    FOREIGN KEY (id_musica) REFERENCES musica(id_musica)
    ON DELETE CASCADE,
    
    -- Validações temporais e de integridade
    CONSTRAINT ck_data_reproducao 
    CHECK (data_reproducao <= CURRENT_TIMESTAMP),
    
    CONSTRAINT ck_duracao_ouvida 
    CHECK (duracao_ouvida >= 0),
    
    CONSTRAINT ck_dispositivo_valido 
    CHECK (dispositivo IN ('web', 'mobile_android', 'mobile_ios', 
                          'desktop', 'smart_tv', 'alexa'))
);

-- Comentários da tabela HISTORICO_REPRODUCAO
COMMENT ON TABLE historico_reproducao IS 
'Tabela que armazena o histórico de reprodução de músicas pelos usuários';

COMMENT ON COLUMN historico_reproducao.duracao_ouvida IS 
'Duração efetivamente ouvida da música em segundos';

-- =====================================================
-- 4. CRIAÇÃO DE ÍNDICES PARA PERFORMANCE
-- =====================================================

-- Índices para otimizar consultas frequentes
CREATE INDEX idx_usuario_email ON usuario(email);
CREATE INDEX idx_artista_nome ON artista(nome_artista);
CREATE INDEX idx_album_artista ON album(id_artista);
CREATE INDEX idx_musica_album ON musica(id_album);
CREATE INDEX idx_historico_usuario ON historico_reproducao(id_usuario);
CREATE INDEX idx_historico_data ON historico_reproducao(data_reproducao);

-- =====================================================
-- 5. VERIFICAÇÃO DA ESTRUTURA CRIADA
-- =====================================================

-- Listar todas as tabelas criadas
SELECT table_name 
FROM user_tables 
ORDER BY table_name;

-- Verificar constraints criadas
SELECT constraint_name, table_name, constraint_type, status
FROM user_constraints 
WHERE table_name IN ('USUARIO', 'ARTISTA', 'ALBUM', 'MUSICA', 'HISTORICO_REPRODUCAO')
ORDER BY table_name, constraint_type;

-- =====================================================
-- 6. EXEMPLOS DE CONSULTAS BÁSICAS À ESTRUTURA
-- =====================================================

-- Describir estrutura das tabelas
DESCRIBE usuario;
DESCRIBE artista;
DESCRIBE album;
DESCRIBE musica;

-- Verificar se as tabelas estão vazias (devem retornar 0)
SELECT 'USUARIO' as tabela, COUNT(*) as registros FROM usuario
UNION ALL
SELECT 'ARTISTA' as tabela, COUNT(*) as registros FROM artista
UNION ALL
SELECT 'ALBUM' as tabela, COUNT(*) as registros FROM album
UNION ALL
SELECT 'MUSICA' as tabela, COUNT(*) as registros FROM musica
UNION ALL
SELECT 'HISTORICO_REPRODUCAO' as tabela, COUNT(*) as registros FROM historico_reproducao;

-- =====================================================
-- 7. COMANDOS DE LIMPEZA (OPCIONAL)
-- =====================================================

-- Para limpar tudo e recomeçar, execute na ordem:
/*
DROP INDEX IF EXISTS idx_historico_data;
DROP INDEX IF EXISTS idx_historico_usuario;
DROP INDEX IF EXISTS idx_musica_album;
DROP INDEX IF EXISTS idx_album_artista;
DROP INDEX IF EXISTS idx_artista_nome;
DROP INDEX IF EXISTS idx_usuario_email;

DROP TABLE IF EXISTS historico_reproducao;
DROP TABLE IF EXISTS musica;
DROP TABLE IF EXISTS album;
DROP TABLE IF EXISTS artista;
DROP TABLE IF EXISTS usuario;
*/

-- =====================================================
-- FIM DO SCRIPT DE CRIAÇÃO
-- =====================================================

-- Mensagem de sucesso
SELECT 'Esquema MusiStream criado com sucesso!' as status;

-- Resetar configurações do SQL*Plus
SET ECHO OFF
SET TIMING OFF