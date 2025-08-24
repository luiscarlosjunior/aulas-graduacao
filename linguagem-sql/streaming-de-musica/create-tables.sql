-- =====================================================
-- SISTEMA MUSISTREAM - SCRIPTS CREATE TABLE
-- =====================================================
-- Scripts de criação das tabelas principais
-- Versão simplificada focada na estrutura básica
-- Compatível com Oracle, PostgreSQL, MySQL e SQL Server
-- =====================================================

-- =====================================================
-- LIMPEZA INICIAL (OPCIONAL)
-- =====================================================

-- Remover tabelas se existirem (descomentar se necessário)
/*
-- Para Oracle/PostgreSQL/MySQL:
DROP TABLE historico_reproducao CASCADE;
DROP TABLE playlist_musica CASCADE;
DROP TABLE playlist CASCADE;
DROP TABLE assinatura CASCADE;
DROP TABLE musica CASCADE;
DROP TABLE album CASCADE;
DROP TABLE artista CASCADE;
DROP TABLE genero CASCADE;
DROP TABLE usuario CASCADE;
DROP TABLE tipo_assinatura CASCADE;

-- Para SQL Server:
-- DROP TABLE historico_reproducao;
-- DROP TABLE playlist_musica;
-- DROP TABLE playlist;
-- DROP TABLE assinatura;
-- DROP TABLE musica;
-- DROP TABLE album;
-- DROP TABLE artista;
-- DROP TABLE genero;
-- DROP TABLE usuario;
-- DROP TABLE tipo_assinatura;
*/

-- =====================================================
-- TABELAS PRINCIPAIS
-- =====================================================

-- Tabela de Gêneros Musicais
CREATE TABLE genero (
    id_genero           INTEGER PRIMARY KEY,
    nome_genero         VARCHAR(50) NOT NULL UNIQUE,
    descricao           VARCHAR(200),
    data_criacao        DATE DEFAULT CURRENT_DATE,
    
    -- Validações
    CONSTRAINT ck_genero_nome_min CHECK (CHAR_LENGTH(nome_genero) >= 2)
);

-- Tabela de Usuários
CREATE TABLE usuario (
    id_usuario          INTEGER PRIMARY KEY,
    nome_usuario        VARCHAR(50) NOT NULL,
    email               VARCHAR(100) NOT NULL UNIQUE,
    senha               VARCHAR(100) NOT NULL,
    data_nascimento     DATE,
    pais                VARCHAR(50),
    data_cadastro       DATE DEFAULT CURRENT_DATE,
    ultimo_acesso       TIMESTAMP,
    ativo               CHAR(1) DEFAULT 'S',
    
    -- Validações
    CONSTRAINT ck_usuario_email CHECK (email LIKE '%_@_%._%'),
    CONSTRAINT ck_usuario_ativo CHECK (ativo IN ('S', 'N'))
);

-- Tabela de Artistas
CREATE TABLE artista (
    id_artista          INTEGER PRIMARY KEY,
    nome_artista        VARCHAR(100) NOT NULL,
    nome_real           VARCHAR(100),
    data_nascimento     DATE,
    pais_origem         VARCHAR(50),
    biografia           TEXT,
    data_inicio_carreira DATE,
    ativo               CHAR(1) DEFAULT 'S',
    website             VARCHAR(200),
    
    -- Validações
    CONSTRAINT ck_artista_ativo CHECK (ativo IN ('S', 'N'))
);

-- Tabela de Álbuns
CREATE TABLE album (
    id_album            INTEGER PRIMARY KEY,
    titulo              VARCHAR(150) NOT NULL,
    data_lancamento     DATE,
    numero_faixas       INTEGER,
    duracao_total       INTEGER, -- em segundos
    capa_url            VARCHAR(500),
    tipo_album          VARCHAR(20) DEFAULT 'ALBUM',
    id_artista          INTEGER NOT NULL,
    
    -- Chave estrangeira
    CONSTRAINT fk_album_artista FOREIGN KEY (id_artista) 
        REFERENCES artista(id_artista),
        
    -- Validações
    CONSTRAINT ck_numero_faixas CHECK (numero_faixas > 0),
    CONSTRAINT ck_duracao_total CHECK (duracao_total > 0),
    CONSTRAINT ck_tipo_album CHECK (tipo_album IN ('ALBUM', 'EP', 'SINGLE', 'COMPILACAO'))
);

-- Tabela de Músicas
CREATE TABLE musica (
    id_musica           INTEGER PRIMARY KEY,
    titulo              VARCHAR(150) NOT NULL,
    duracao             INTEGER NOT NULL, -- em segundos
    numero_faixa        INTEGER,
    letra               TEXT,
    arquivo_url         VARCHAR(500),
    total_reproducoes   INTEGER DEFAULT 0,
    data_upload         DATE DEFAULT CURRENT_DATE,
    id_album            INTEGER NOT NULL,
    id_genero           INTEGER,
    
    -- Chaves estrangeiras
    CONSTRAINT fk_musica_album FOREIGN KEY (id_album) 
        REFERENCES album(id_album),
    CONSTRAINT fk_musica_genero FOREIGN KEY (id_genero) 
        REFERENCES genero(id_genero),
        
    -- Validações
    CONSTRAINT ck_duracao_musica CHECK (duracao > 0 AND duracao <= 3600),
    CONSTRAINT ck_numero_faixa CHECK (numero_faixa > 0),
    CONSTRAINT ck_total_reproducoes CHECK (total_reproducoes >= 0),
    
    -- Constraint única composta
    CONSTRAINT uk_album_faixa UNIQUE (id_album, numero_faixa)
);

-- Tabela de Playlists
CREATE TABLE playlist (
    id_playlist         INTEGER PRIMARY KEY,
    nome_playlist       VARCHAR(100) NOT NULL,
    descricao           VARCHAR(500),
    publica             CHAR(1) DEFAULT 'N',
    data_criacao        DATE DEFAULT CURRENT_DATE,
    data_atualizacao    TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    total_musicas       INTEGER DEFAULT 0,
    duracao_total       INTEGER DEFAULT 0, -- em segundos
    id_usuario          INTEGER NOT NULL,
    
    -- Chave estrangeira
    CONSTRAINT fk_playlist_usuario FOREIGN KEY (id_usuario) 
        REFERENCES usuario(id_usuario),
        
    -- Validações
    CONSTRAINT ck_playlist_publica CHECK (publica IN ('S', 'N')),
    CONSTRAINT ck_total_musicas CHECK (total_musicas >= 0),
    CONSTRAINT ck_duracao_playlist CHECK (duracao_total >= 0)
);

-- Tabela de Tipos de Assinatura
CREATE TABLE tipo_assinatura (
    id_tipo_assinatura  INTEGER PRIMARY KEY,
    nome_plano          VARCHAR(50) NOT NULL UNIQUE,
    preco_mensal        DECIMAL(8,2) NOT NULL,
    qualidade_audio     VARCHAR(20),
    downloads_offline   CHAR(1) DEFAULT 'N',
    pulos_ilimitados    CHAR(1) DEFAULT 'N',
    sem_anuncios        CHAR(1) DEFAULT 'N',
    descricao           VARCHAR(200),
    ativo               CHAR(1) DEFAULT 'S',
    
    -- Validações
    CONSTRAINT ck_preco_mensal CHECK (preco_mensal >= 0),
    CONSTRAINT ck_downloads_offline CHECK (downloads_offline IN ('S', 'N')),
    CONSTRAINT ck_pulos_ilimitados CHECK (pulos_ilimitados IN ('S', 'N')),
    CONSTRAINT ck_sem_anuncios CHECK (sem_anuncios IN ('S', 'N')),
    CONSTRAINT ck_tipo_ativo CHECK (ativo IN ('S', 'N'))
);

-- Tabela de Assinaturas dos Usuários
CREATE TABLE assinatura (
    id_assinatura       INTEGER PRIMARY KEY,
    data_inicio         DATE NOT NULL,
    data_fim            DATE,
    status_assinatura   VARCHAR(20) DEFAULT 'ATIVA',
    metodo_pagamento    VARCHAR(50),
    valor_pago          DECIMAL(8,2),
    data_ultimo_pagamento DATE,
    renovacao_automatica CHAR(1) DEFAULT 'S',
    id_usuario          INTEGER NOT NULL,
    id_tipo_assinatura  INTEGER NOT NULL,
    
    -- Chaves estrangeiras
    CONSTRAINT fk_assinatura_usuario FOREIGN KEY (id_usuario) 
        REFERENCES usuario(id_usuario),
    CONSTRAINT fk_assinatura_tipo FOREIGN KEY (id_tipo_assinatura) 
        REFERENCES tipo_assinatura(id_tipo_assinatura),
        
    -- Validações
    CONSTRAINT ck_status_assinatura CHECK (status_assinatura IN ('ATIVA', 'CANCELADA', 'SUSPENSA', 'EXPIRADA')),
    CONSTRAINT ck_data_fim CHECK (data_fim IS NULL OR data_fim > data_inicio),
    CONSTRAINT ck_valor_pago CHECK (valor_pago >= 0),
    CONSTRAINT ck_renovacao_auto CHECK (renovacao_automatica IN ('S', 'N'))
);

-- =====================================================
-- TABELAS DE RELACIONAMENTO
-- =====================================================

-- Tabela de Relacionamento Playlist-Música (N:M)
CREATE TABLE playlist_musica (
    id_playlist         INTEGER NOT NULL,
    id_musica           INTEGER NOT NULL,
    ordem_musica        INTEGER NOT NULL,
    data_adicao         DATE DEFAULT CURRENT_DATE,
    
    -- Chave primária composta
    CONSTRAINT pk_playlist_musica PRIMARY KEY (id_playlist, id_musica),
    
    -- Chaves estrangeiras
    CONSTRAINT fk_pm_playlist FOREIGN KEY (id_playlist) 
        REFERENCES playlist(id_playlist),
    CONSTRAINT fk_pm_musica FOREIGN KEY (id_musica) 
        REFERENCES musica(id_musica),
        
    -- Validações
    CONSTRAINT ck_ordem_musica CHECK (ordem_musica > 0),
    
    -- Constraint única para ordem dentro da playlist
    CONSTRAINT uk_playlist_ordem UNIQUE (id_playlist, ordem_musica)
);

-- Tabela de Histórico de Reprodução
CREATE TABLE historico_reproducao (
    id_historico        INTEGER PRIMARY KEY,
    data_reproducao     TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    duracao_ouvida      INTEGER, -- em segundos
    dispositivo         VARCHAR(50),
    localizacao         VARCHAR(100),
    qualidade_reproducao VARCHAR(20),
    origem_reproducao   VARCHAR(50), -- playlist, busca, recomendacao, etc
    id_usuario          INTEGER NOT NULL,
    id_musica           INTEGER NOT NULL,
    
    -- Chaves estrangeiras
    CONSTRAINT fk_hist_usuario FOREIGN KEY (id_usuario) 
        REFERENCES usuario(id_usuario),
    CONSTRAINT fk_hist_musica FOREIGN KEY (id_musica) 
        REFERENCES musica(id_musica),
        
    -- Validações
    CONSTRAINT ck_duracao_ouvida CHECK (duracao_ouvida >= 0)
);

-- =====================================================
-- COMENTÁRIOS NAS TABELAS
-- =====================================================

COMMENT ON TABLE usuario IS 'Usuários cadastrados na plataforma MusiStream';
COMMENT ON TABLE artista IS 'Artistas, bandas e intérpretes musicais';
COMMENT ON TABLE genero IS 'Gêneros musicais para categorização';
COMMENT ON TABLE album IS 'Álbuns, EPs, singles e compilações';
COMMENT ON TABLE musica IS 'Faixas musicais individuais';
COMMENT ON TABLE playlist IS 'Listas de reprodução criadas pelos usuários';
COMMENT ON TABLE tipo_assinatura IS 'Tipos de planos de assinatura disponíveis';
COMMENT ON TABLE assinatura IS 'Assinaturas ativas e históricas dos usuários';
COMMENT ON TABLE playlist_musica IS 'Relacionamento entre playlists e músicas (N:M)';
COMMENT ON TABLE historico_reproducao IS 'Log de reproduções de músicas pelos usuários';

-- =====================================================
-- VERIFICAÇÃO DAS TABELAS CRIADAS
-- =====================================================

-- Para verificar se as tabelas foram criadas corretamente
-- (descomentar as linhas conforme o SGBD utilizado)

-- PostgreSQL:
-- SELECT table_name FROM information_schema.tables 
-- WHERE table_schema = 'public' AND table_type = 'BASE TABLE'
-- ORDER BY table_name;

-- MySQL:
-- SHOW TABLES;

-- Oracle:
-- SELECT table_name FROM user_tables ORDER BY table_name;

-- SQL Server:
-- SELECT name FROM sys.tables ORDER BY name;

-- =====================================================
-- FIM DOS SCRIPTS CREATE TABLE
-- =====================================================