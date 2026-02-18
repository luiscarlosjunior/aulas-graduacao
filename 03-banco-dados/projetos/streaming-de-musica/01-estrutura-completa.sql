-- =====================================================
-- SISTEMA MUSISTREAM - ESTRUTURA COMPLETA DO BANCO
-- =====================================================
-- Exemplo prático de streaming de música (similar ao Spotify)
-- Consolidando conceitos dos módulos 01-16 do curso SQL
-- =====================================================

-- Configurações iniciais para Oracle/SQL*Plus
SET ECHO ON
SET FEEDBACK ON
SET HEADING ON
SET PAGESIZE 50
SET LINESIZE 120

-- =====================================================
-- SEÇÃO 1: LIMPEZA E PREPARAÇÃO
-- =====================================================

-- Remove tabelas existentes (caso já existam)
-- Ordem inversa dos relacionamentos para evitar conflitos de FK
DROP TABLE historico_reproducao CASCADE CONSTRAINTS;
DROP TABLE playlist_musica CASCADE CONSTRAINTS;
DROP TABLE playlist CASCADE CONSTRAINTS;
DROP TABLE assinatura CASCADE CONSTRAINTS;
DROP TABLE musica CASCADE CONSTRAINTS;
DROP TABLE album CASCADE CONSTRAINTS;
DROP TABLE artista CASCADE CONSTRAINTS;
DROP TABLE genero CASCADE CONSTRAINTS;
DROP TABLE usuario CASCADE CONSTRAINTS;
DROP TABLE tipo_assinatura CASCADE CONSTRAINTS;

-- Remove sequences
DROP SEQUENCE seq_usuario;
DROP SEQUENCE seq_artista;
DROP SEQUENCE seq_genero;
DROP SEQUENCE seq_album;
DROP SEQUENCE seq_musica;
DROP SEQUENCE seq_playlist;
DROP SEQUENCE seq_tipo_assinatura;
DROP SEQUENCE seq_assinatura;
DROP SEQUENCE seq_historico;

PROMPT =====================================================
PROMPT Estruturas anteriores removidas com sucesso!
PROMPT =====================================================

-- =====================================================
-- SEÇÃO 2: CRIAÇÃO DE SEQUENCES
-- =====================================================

CREATE SEQUENCE seq_usuario START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE seq_artista START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE seq_genero START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE seq_album START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE seq_musica START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE seq_playlist START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE seq_tipo_assinatura START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE seq_assinatura START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE seq_historico START WITH 1 INCREMENT BY 1;

PROMPT =====================================================
PROMPT Sequences criadas com sucesso!
PROMPT =====================================================

-- =====================================================
-- SEÇÃO 3: TABELAS PRINCIPAIS
-- =====================================================

-- Tabela de Gêneros Musicais
CREATE TABLE genero (
    id_genero           NUMBER PRIMARY KEY,
    nome_genero         VARCHAR2(50) NOT NULL UNIQUE,
    descricao           VARCHAR2(200),
    data_criacao        DATE DEFAULT SYSDATE,
    
    -- Constraints de validação
    CONSTRAINT ck_genero_nome CHECK (LENGTH(nome_genero) >= 2)
);

-- Tabela de Usuários
CREATE TABLE usuario (
    id_usuario          NUMBER PRIMARY KEY,
    nome_usuario        VARCHAR2(50) NOT NULL,
    email               VARCHAR2(100) NOT NULL UNIQUE,
    senha               VARCHAR2(100) NOT NULL,
    data_nascimento     DATE,
    pais                VARCHAR2(50),
    data_cadastro       DATE DEFAULT SYSDATE,
    ultimo_acesso       TIMESTAMP,
    ativo               CHAR(1) DEFAULT 'S',
    
    -- Constraints de validação
    CONSTRAINT ck_usuario_email CHECK (email LIKE '%_@_%._%'),
    CONSTRAINT ck_usuario_ativo CHECK (ativo IN ('S', 'N')),
    CONSTRAINT ck_usuario_idade CHECK (data_nascimento <= SYSDATE - INTERVAL '13' YEAR)
);

-- Tabela de Artistas
CREATE TABLE artista (
    id_artista          NUMBER PRIMARY KEY,
    nome_artista        VARCHAR2(100) NOT NULL,
    nome_real           VARCHAR2(100),
    data_nascimento     DATE,
    pais_origem         VARCHAR2(50),
    biografia           CLOB,
    data_inicio_carreira DATE,
    ativo               CHAR(1) DEFAULT 'S',
    website             VARCHAR2(200),
    
    -- Constraints de validação
    CONSTRAINT ck_artista_ativo CHECK (ativo IN ('S', 'N')),
    CONSTRAINT ck_artista_carreira CHECK (data_inicio_carreira >= data_nascimento OR data_nascimento IS NULL)
);

-- Tabela de Álbuns
CREATE TABLE album (
    id_album            NUMBER PRIMARY KEY,
    titulo              VARCHAR2(150) NOT NULL,
    data_lancamento     DATE,
    numero_faixas       NUMBER,
    duracao_total       NUMBER, -- em segundos
    capa_url            VARCHAR2(500),
    tipo_album          VARCHAR2(20) DEFAULT 'ALBUM',
    id_artista          NUMBER NOT NULL,
    
    -- Chave estrangeira
    CONSTRAINT fk_album_artista FOREIGN KEY (id_artista) 
        REFERENCES artista(id_artista) ON DELETE CASCADE,
        
    -- Constraints de validação
    CONSTRAINT ck_numero_faixas CHECK (numero_faixas > 0),
    CONSTRAINT ck_duracao_total CHECK (duracao_total > 0),
    CONSTRAINT ck_tipo_album CHECK (tipo_album IN ('ALBUM', 'EP', 'SINGLE', 'COMPILACAO'))
);

-- Tabela de Músicas
CREATE TABLE musica (
    id_musica           NUMBER PRIMARY KEY,
    titulo              VARCHAR2(150) NOT NULL,
    duracao             NUMBER NOT NULL, -- em segundos
    numero_faixa        NUMBER,
    letra               CLOB,
    arquivo_url         VARCHAR2(500),
    total_reproducoes   NUMBER DEFAULT 0,
    data_upload         DATE DEFAULT SYSDATE,
    id_album            NUMBER NOT NULL,
    id_genero           NUMBER,
    
    -- Chaves estrangeiras
    CONSTRAINT fk_musica_album FOREIGN KEY (id_album) 
        REFERENCES album(id_album) ON DELETE CASCADE,
    CONSTRAINT fk_musica_genero FOREIGN KEY (id_genero) 
        REFERENCES genero(id_genero),
        
    -- Constraints de validação
    CONSTRAINT ck_duracao_musica CHECK (duracao > 0 AND duracao <= 3600), -- máximo 1 hora
    CONSTRAINT ck_numero_faixa CHECK (numero_faixa > 0),
    CONSTRAINT ck_total_reproducoes CHECK (total_reproducoes >= 0),
    
    -- Constraint única composta
    CONSTRAINT uk_album_faixa UNIQUE (id_album, numero_faixa)
);

-- Tabela de Playlists
CREATE TABLE playlist (
    id_playlist         NUMBER PRIMARY KEY,
    nome_playlist       VARCHAR2(100) NOT NULL,
    descricao           VARCHAR2(500),
    publica             CHAR(1) DEFAULT 'N',
    data_criacao        DATE DEFAULT SYSDATE,
    data_atualizacao    TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    total_musicas       NUMBER DEFAULT 0,
    duracao_total       NUMBER DEFAULT 0, -- em segundos
    id_usuario          NUMBER NOT NULL,
    
    -- Chave estrangeira
    CONSTRAINT fk_playlist_usuario FOREIGN KEY (id_usuario) 
        REFERENCES usuario(id_usuario) ON DELETE CASCADE,
        
    -- Constraints de validação
    CONSTRAINT ck_playlist_publica CHECK (publica IN ('S', 'N')),
    CONSTRAINT ck_total_musicas CHECK (total_musicas >= 0),
    CONSTRAINT ck_duracao_playlist CHECK (duracao_total >= 0)
);

-- Tabela de Tipos de Assinatura
CREATE TABLE tipo_assinatura (
    id_tipo_assinatura  NUMBER PRIMARY KEY,
    nome_plano          VARCHAR2(50) NOT NULL UNIQUE,
    preco_mensal        NUMBER(8,2) NOT NULL,
    qualidade_audio     VARCHAR2(20),
    downloads_offline   CHAR(1) DEFAULT 'N',
    pulos_ilimitados    CHAR(1) DEFAULT 'N',
    sem_anuncios        CHAR(1) DEFAULT 'N',
    descricao           VARCHAR2(200),
    ativo               CHAR(1) DEFAULT 'S',
    
    -- Constraints de validação
    CONSTRAINT ck_preco_mensal CHECK (preco_mensal >= 0),
    CONSTRAINT ck_downloads_offline CHECK (downloads_offline IN ('S', 'N')),
    CONSTRAINT ck_pulos_ilimitados CHECK (pulos_ilimitados IN ('S', 'N')),
    CONSTRAINT ck_sem_anuncios CHECK (sem_anuncios IN ('S', 'N')),
    CONSTRAINT ck_tipo_ativo CHECK (ativo IN ('S', 'N'))
);

-- Tabela de Assinaturas dos Usuários
CREATE TABLE assinatura (
    id_assinatura       NUMBER PRIMARY KEY,
    data_inicio         DATE NOT NULL,
    data_fim            DATE,
    status_assinatura   VARCHAR2(20) DEFAULT 'ATIVA',
    metodo_pagamento    VARCHAR2(50),
    valor_pago          NUMBER(8,2),
    data_ultimo_pagamento DATE,
    renovacao_automatica CHAR(1) DEFAULT 'S',
    id_usuario          NUMBER NOT NULL,
    id_tipo_assinatura  NUMBER NOT NULL,
    
    -- Chaves estrangeiras
    CONSTRAINT fk_assinatura_usuario FOREIGN KEY (id_usuario) 
        REFERENCES usuario(id_usuario) ON DELETE CASCADE,
    CONSTRAINT fk_assinatura_tipo FOREIGN KEY (id_tipo_assinatura) 
        REFERENCES tipo_assinatura(id_tipo_assinatura),
        
    -- Constraints de validação
    CONSTRAINT ck_status_assinatura CHECK (status_assinatura IN ('ATIVA', 'CANCELADA', 'SUSPENSA', 'EXPIRADA')),
    CONSTRAINT ck_data_fim CHECK (data_fim IS NULL OR data_fim > data_inicio),
    CONSTRAINT ck_valor_pago CHECK (valor_pago >= 0),
    CONSTRAINT ck_renovacao_auto CHECK (renovacao_automatica IN ('S', 'N'))
);

-- =====================================================
-- SEÇÃO 4: TABELAS DE RELACIONAMENTO
-- =====================================================

-- Tabela de Relacionamento Playlist-Música (N:M)
CREATE TABLE playlist_musica (
    id_playlist         NUMBER NOT NULL,
    id_musica           NUMBER NOT NULL,
    ordem_musica        NUMBER NOT NULL,
    data_adicao         DATE DEFAULT SYSDATE,
    
    -- Chave primária composta
    CONSTRAINT pk_playlist_musica PRIMARY KEY (id_playlist, id_musica),
    
    -- Chaves estrangeiras
    CONSTRAINT fk_pm_playlist FOREIGN KEY (id_playlist) 
        REFERENCES playlist(id_playlist) ON DELETE CASCADE,
    CONSTRAINT fk_pm_musica FOREIGN KEY (id_musica) 
        REFERENCES musica(id_musica) ON DELETE CASCADE,
        
    -- Constraints de validação
    CONSTRAINT ck_ordem_musica CHECK (ordem_musica > 0),
    
    -- Constraint única para ordem dentro da playlist
    CONSTRAINT uk_playlist_ordem UNIQUE (id_playlist, ordem_musica)
);

-- Tabela de Histórico de Reprodução
CREATE TABLE historico_reproducao (
    id_historico        NUMBER PRIMARY KEY,
    data_reproducao     TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    duracao_ouvida      NUMBER, -- em segundos
    dispositivo         VARCHAR2(50),
    localizacao         VARCHAR2(100),
    qualidade_reproducao VARCHAR2(20),
    origem_reproducao   VARCHAR2(50), -- playlist, busca, recomendacao, etc
    id_usuario          NUMBER NOT NULL,
    id_musica           NUMBER NOT NULL,
    
    -- Chaves estrangeiras
    CONSTRAINT fk_hist_usuario FOREIGN KEY (id_usuario) 
        REFERENCES usuario(id_usuario) ON DELETE CASCADE,
    CONSTRAINT fk_hist_musica FOREIGN KEY (id_musica) 
        REFERENCES musica(id_musica) ON DELETE CASCADE,
        
    -- Constraints de validação
    CONSTRAINT ck_duracao_ouvida CHECK (duracao_ouvida >= 0)
);

-- =====================================================
-- SEÇÃO 5: ÍNDICES PARA PERFORMANCE
-- =====================================================

-- Índices para melhorar performance de consultas frequentes
CREATE INDEX idx_musica_genero ON musica(id_genero);
CREATE INDEX idx_musica_reproducoes ON musica(total_reproducoes DESC);
CREATE INDEX idx_album_artista ON album(id_artista);
CREATE INDEX idx_playlist_usuario ON playlist(id_usuario);
CREATE INDEX idx_hist_usuario ON historico_reproducao(id_usuario);
CREATE INDEX idx_hist_musica ON historico_reproducao(id_musica);
CREATE INDEX idx_hist_data ON historico_reproducao(data_reproducao);
CREATE INDEX idx_usuario_email ON usuario(email);
CREATE INDEX idx_usuario_pais ON usuario(pais);
CREATE INDEX idx_artista_pais ON artista(pais_origem);

-- =====================================================
-- SEÇÃO 6: VIEWS PARA RELATÓRIOS FREQUENTES
-- =====================================================

-- View: Informações completas de músicas
CREATE OR REPLACE VIEW vw_musicas_completas AS
SELECT 
    m.id_musica,
    m.titulo AS titulo_musica,
    m.duracao,
    m.total_reproducoes,
    al.titulo AS titulo_album,
    ar.nome_artista,
    g.nome_genero,
    al.data_lancamento
FROM musica m
JOIN album al ON m.id_album = al.id_album
JOIN artista ar ON al.id_artista = ar.id_artista
LEFT JOIN genero g ON m.id_genero = g.id_genero;

-- View: Top músicas por reproduções
CREATE OR REPLACE VIEW vw_top_musicas AS
SELECT 
    ROWNUM AS ranking,
    titulo_musica,
    nome_artista,
    total_reproducoes,
    nome_genero
FROM (
    SELECT * FROM vw_musicas_completas 
    ORDER BY total_reproducoes DESC
) musicas_ordenadas
WHERE ROWNUM <= 100;

-- View: Estatísticas de usuários
CREATE OR REPLACE VIEW vw_stats_usuarios AS
SELECT 
    u.id_usuario,
    u.nome_usuario,
    COUNT(DISTINCT p.id_playlist) AS total_playlists,
    COUNT(DISTINCT hr.id_musica) AS musicas_diferentes_ouvidas,
    COUNT(hr.id_historico) AS total_reproducoes,
    MAX(hr.data_reproducao) AS ultima_atividade
FROM usuario u
LEFT JOIN playlist p ON u.id_usuario = p.id_usuario
LEFT JOIN historico_reproducao hr ON u.id_usuario = hr.id_usuario
GROUP BY u.id_usuario, u.nome_usuario;

-- =====================================================
-- SEÇÃO 7: TRIGGERS PARA AUTOMAÇÃO
-- =====================================================

-- Trigger: Atualizar contador de reproduções na tabela música
CREATE OR REPLACE TRIGGER trg_update_reproducoes
AFTER INSERT ON historico_reproducao
FOR EACH ROW
BEGIN
    UPDATE musica 
    SET total_reproducoes = total_reproducoes + 1
    WHERE id_musica = :NEW.id_musica;
END;
/

-- Trigger: Atualizar contadores da playlist quando música é adicionada
CREATE OR REPLACE TRIGGER trg_update_playlist_stats
AFTER INSERT OR DELETE ON playlist_musica
FOR EACH ROW
DECLARE
    v_duracao_musica NUMBER;
BEGIN
    IF INSERTING THEN
        -- Buscar duração da música
        SELECT duracao INTO v_duracao_musica 
        FROM musica WHERE id_musica = :NEW.id_musica;
        
        -- Atualizar contadores da playlist
        UPDATE playlist 
        SET total_musicas = total_musicas + 1,
            duracao_total = duracao_total + v_duracao_musica,
            data_atualizacao = CURRENT_TIMESTAMP
        WHERE id_playlist = :NEW.id_playlist;
        
    ELSIF DELETING THEN
        -- Buscar duração da música
        SELECT duracao INTO v_duracao_musica 
        FROM musica WHERE id_musica = :OLD.id_musica;
        
        -- Atualizar contadores da playlist
        UPDATE playlist 
        SET total_musicas = total_musicas - 1,
            duracao_total = duracao_total - v_duracao_musica,
            data_atualizacao = CURRENT_TIMESTAMP
        WHERE id_playlist = :OLD.id_playlist;
    END IF;
END;
/

-- =====================================================
-- SEÇÃO 8: COMENTÁRIOS NAS TABELAS
-- =====================================================

-- Adicionar comentários para documentação
COMMENT ON TABLE usuario IS 'Tabela de usuários do sistema MusiStream';
COMMENT ON TABLE artista IS 'Tabela de artistas e bandas';
COMMENT ON TABLE genero IS 'Tabela de gêneros musicais';
COMMENT ON TABLE album IS 'Tabela de álbuns musicais';
COMMENT ON TABLE musica IS 'Tabela de músicas/faixas';
COMMENT ON TABLE playlist IS 'Tabela de playlists criadas pelos usuários';
COMMENT ON TABLE tipo_assinatura IS 'Tabela de tipos de planos de assinatura';
COMMENT ON TABLE assinatura IS 'Tabela de assinaturas ativas dos usuários';
COMMENT ON TABLE playlist_musica IS 'Tabela de relacionamento N:M entre playlists e músicas';
COMMENT ON TABLE historico_reproducao IS 'Tabela de log de reproduções de músicas';

-- =====================================================
-- SEÇÃO 9: VERIFICAÇÃO FINAL
-- =====================================================

PROMPT =====================================================
PROMPT ESTRUTURA DO BANCO MUSISTREAM CRIADA COM SUCESSO!
PROMPT =====================================================

-- Mostrar tabelas criadas
SELECT table_name, num_rows 
FROM user_tables 
WHERE table_name IN ('USUARIO', 'ARTISTA', 'GENERO', 'ALBUM', 'MUSICA', 
                     'PLAYLIST', 'TIPO_ASSINATURA', 'ASSINATURA', 
                     'PLAYLIST_MUSICA', 'HISTORICO_REPRODUCAO')
ORDER BY table_name;

PROMPT =====================================================
PROMPT Próximo passo: Execute 02-inserir-dados.sql
PROMPT =====================================================