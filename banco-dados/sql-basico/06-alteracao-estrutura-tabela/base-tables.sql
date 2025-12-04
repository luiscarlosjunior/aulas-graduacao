-- =====================================================
-- SCRIPT BASE PARA ALTERAÇÃO DE ESTRUTURA DE TABELAS
-- Módulo 06: Sistema MusiStream - Tabelas Básicas
-- =====================================================
-- Este script cria as tabelas principais do sistema MusiStream
-- para serem usadas como base nos exemplos de ALTER TABLE
-- demonstrados no README.md e nos exercícios.
-- =====================================================

-- =====================================================
-- LIMPEZA INICIAL (OPCIONAL)
-- =====================================================
-- Descomentar as linhas abaixo se quiser limpar tabelas existentes

/*
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
*/

-- =====================================================
-- TABELAS PRINCIPAIS DO SISTEMA MUSISTREAM
-- =====================================================

-- Tabela de Gêneros Musicais
CREATE TABLE genero (
    id_genero           INTEGER PRIMARY KEY,
    nome_genero         VARCHAR2(50) NOT NULL UNIQUE,
    descricao           VARCHAR2(200)
);

-- Tabela de Usuários
CREATE TABLE usuario (
    id_usuario          INTEGER PRIMARY KEY,
    nome_usuario        VARCHAR2(100) NOT NULL,
    email               VARCHAR2(150) NOT NULL UNIQUE,
    data_nascimento     DATE,
    pais                VARCHAR2(50),
    data_cadastro       DATE DEFAULT SYSDATE,
    ativo               CHAR(1) DEFAULT 'S'
);

-- Tabela de Artistas
CREATE TABLE artista (
    id_artista          INTEGER PRIMARY KEY,
    nome_artista        VARCHAR2(100) NOT NULL,
    biografia           CLOB,
    data_formacao       DATE,
    pais_origem         VARCHAR2(50),
    ativo               CHAR(1) DEFAULT 'S'
);

-- Tabela de Álbuns
CREATE TABLE album (
    id_album            INTEGER PRIMARY KEY,
    titulo              VARCHAR2(200) NOT NULL,
    ano_lancamento      INTEGER,
    numero_faixas       INTEGER,
    duracao_total       INTEGER, -- em segundos
    id_artista          INTEGER NOT NULL,
    id_genero           INTEGER,
    
    -- Relacionamentos
    CONSTRAINT fk_album_artista FOREIGN KEY (id_artista) REFERENCES artista(id_artista),
    CONSTRAINT fk_album_genero FOREIGN KEY (id_genero) REFERENCES genero(id_genero)
);

-- Tabela de Músicas
CREATE TABLE musica (
    id_musica           INTEGER PRIMARY KEY,
    titulo              VARCHAR2(150) NOT NULL,
    duracao             INTEGER NOT NULL, -- em segundos
    numero_faixa        INTEGER,
    total_reproducoes   INTEGER DEFAULT 0,
    id_album            INTEGER NOT NULL,
    id_genero           INTEGER,
    
    -- Relacionamentos
    CONSTRAINT fk_musica_album FOREIGN KEY (id_album) REFERENCES album(id_album),
    CONSTRAINT fk_musica_genero FOREIGN KEY (id_genero) REFERENCES genero(id_genero)
);

-- Tabela de Playlists
CREATE TABLE playlist (
    id_playlist         INTEGER PRIMARY KEY,
    nome_playlist       VARCHAR2(100) NOT NULL,
    descricao           VARCHAR2(500),
    publica             CHAR(1) DEFAULT 'N',
    data_criacao        DATE DEFAULT SYSDATE,
    total_musicas       INTEGER DEFAULT 0,
    id_usuario          INTEGER NOT NULL,
    
    -- Relacionamentos
    CONSTRAINT fk_playlist_usuario FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario)
);

-- Tabela de Tipos de Assinatura
CREATE TABLE tipo_assinatura (
    id_tipo_assinatura  INTEGER PRIMARY KEY,
    nome_plano          VARCHAR2(50) NOT NULL UNIQUE,
    preco_mensal        NUMBER(8,2) NOT NULL,
    qualidade_audio     VARCHAR2(20),
    downloads_offline   CHAR(1) DEFAULT 'N',
    descricao           VARCHAR2(200)
);

-- Tabela de Assinaturas dos Usuários
CREATE TABLE assinatura (
    id_assinatura       INTEGER PRIMARY KEY,
    data_inicio         DATE NOT NULL,
    data_fim            DATE,
    status_assinatura   VARCHAR2(20) DEFAULT 'ATIVA',
    valor_pago          NUMBER(8,2),
    id_usuario          INTEGER NOT NULL,
    id_tipo_assinatura  INTEGER NOT NULL,
    
    -- Relacionamentos
    CONSTRAINT fk_assinatura_usuario FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario),
    CONSTRAINT fk_assinatura_tipo FOREIGN KEY (id_tipo_assinatura) REFERENCES tipo_assinatura(id_tipo_assinatura)
);

-- Tabela de Relacionamento Playlist-Música (N:M)
CREATE TABLE playlist_musica (
    id_playlist         INTEGER NOT NULL,
    id_musica           INTEGER NOT NULL,
    ordem_musica        INTEGER NOT NULL,
    data_adicao         DATE DEFAULT SYSDATE,
    
    -- Chave primária composta
    PRIMARY KEY (id_playlist, id_musica),
    
    -- Relacionamentos
    CONSTRAINT fk_pm_playlist FOREIGN KEY (id_playlist) REFERENCES playlist(id_playlist),
    CONSTRAINT fk_pm_musica FOREIGN KEY (id_musica) REFERENCES musica(id_musica)
);

-- Tabela de Histórico de Reprodução
CREATE TABLE historico_reproducao (
    id_historico        INTEGER PRIMARY KEY,
    data_reproducao     TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    duracao_ouvida      INTEGER, -- em segundos
    dispositivo         VARCHAR2(50),
    id_usuario          INTEGER NOT NULL,
    id_musica           INTEGER NOT NULL,
    
    -- Relacionamentos
    CONSTRAINT fk_hist_usuario FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario),
    CONSTRAINT fk_hist_musica FOREIGN KEY (id_musica) REFERENCES musica(id_musica)
);

-- =====================================================
-- DADOS DE EXEMPLO PARA TESTES
-- =====================================================

-- Inserir gêneros musicais
INSERT INTO genero (id_genero, nome_genero, descricao) VALUES (1, 'Rock', 'Música rock e suas variações');
INSERT INTO genero (id_genero, nome_genero, descricao) VALUES (2, 'Pop', 'Música popular contemporânea');
INSERT INTO genero (id_genero, nome_genero, descricao) VALUES (3, 'Jazz', 'Jazz clássico e moderno');
INSERT INTO genero (id_genero, nome_genero, descricao) VALUES (4, 'MPB', 'Música Popular Brasileira');
INSERT INTO genero (id_genero, nome_genero, descricao) VALUES (5, 'Eletrônica', 'Música eletrônica e dance');

-- Inserir usuários
INSERT INTO usuario (id_usuario, nome_usuario, email, data_nascimento, pais) 
VALUES (1, 'João Silva', 'joao.silva@email.com', DATE '1990-05-15', 'Brasil');

INSERT INTO usuario (id_usuario, nome_usuario, email, data_nascimento, pais) 
VALUES (2, 'Maria Santos', 'maria.santos@email.com', DATE '1985-03-22', 'Brasil');

INSERT INTO usuario (id_usuario, nome_usuario, email, data_nascimento, pais) 
VALUES (3, 'Carlos Oliveira', 'carlos@email.com', DATE '1992-08-10', 'Brasil');

-- Inserir artistas
INSERT INTO artista (id_artista, nome_artista, biografia, data_formacao, pais_origem) 
VALUES (1, 'The Beatles', 'Banda britânica de rock formada em Liverpool', DATE '1960-01-01', 'Reino Unido');

INSERT INTO artista (id_artista, nome_artista, biografia, data_formacao, pais_origem) 
VALUES (2, 'Caetano Veloso', 'Cantor, compositor e escritor brasileiro', DATE '1965-01-01', 'Brasil');

INSERT INTO artista (id_artista, nome_artista, biografia, data_formacao, pais_origem) 
VALUES (3, 'Miles Davis', 'Trompetista e compositor de jazz americano', DATE '1944-01-01', 'Estados Unidos');

INSERT INTO artista (id_artista, nome_artista, biografia, data_formacao, pais_origem) 
VALUES (4, 'Daft Punk', 'Dupla francesa de música eletrônica', DATE '1993-01-01', 'França');

-- Inserir álbuns
INSERT INTO album (id_album, titulo, ano_lancamento, numero_faixas, duracao_total, id_artista, id_genero) 
VALUES (1, 'Abbey Road', 1969, 17, 2862, 1, 1);

INSERT INTO album (id_album, titulo, ano_lancamento, numero_faixas, duracao_total, id_artista, id_genero) 
VALUES (2, 'Tropicália', 1968, 12, 2245, 2, 4);

INSERT INTO album (id_album, titulo, ano_lancamento, numero_faixas, duracao_total, id_artista, id_genero) 
VALUES (3, 'Kind of Blue', 1959, 5, 2760, 3, 3);

INSERT INTO album (id_album, titulo, ano_lancamento, numero_faixas, duracao_total, id_artista, id_genero) 
VALUES (4, 'Random Access Memories', 2013, 13, 4474, 4, 5);

-- Inserir músicas
INSERT INTO musica (id_musica, titulo, duracao, numero_faixa, total_reproducoes, id_album, id_genero) 
VALUES (1, 'Come Together', 259, 1, 15420000, 1, 1);

INSERT INTO musica (id_musica, titulo, duracao, numero_faixa, total_reproducoes, id_album, id_genero) 
VALUES (2, 'Something', 183, 2, 8750000, 1, 1);

INSERT INTO musica (id_musica, titulo, duracao, numero_faixa, total_reproducoes, id_album, id_genero) 
VALUES (3, 'Tropicália', 215, 1, 2100000, 2, 4);

INSERT INTO musica (id_musica, titulo, duracao, numero_faixa, total_reproducoes, id_album, id_genero) 
VALUES (4, 'So What', 563, 1, 5200000, 3, 3);

INSERT INTO musica (id_musica, titulo, duracao, numero_faixa, total_reproducoes, id_album, id_genero) 
VALUES (5, 'Get Lucky', 367, 8, 89500000, 4, 5);

-- Inserir tipos de assinatura
INSERT INTO tipo_assinatura (id_tipo_assinatura, nome_plano, preco_mensal, qualidade_audio, downloads_offline, descricao) 
VALUES (1, 'Free', 0.00, 'Normal', 'N', 'Plano gratuito com anúncios');

INSERT INTO tipo_assinatura (id_tipo_assinatura, nome_plano, preco_mensal, qualidade_audio, downloads_offline, descricao) 
VALUES (2, 'Premium', 16.90, 'Alta', 'S', 'Plano premium sem anúncios');

INSERT INTO tipo_assinatura (id_tipo_assinatura, nome_plano, preco_mensal, qualidade_audio, downloads_offline, descricao) 
VALUES (3, 'Family', 26.90, 'Alta', 'S', 'Plano família para até 6 usuários');

-- Inserir algumas assinaturas
INSERT INTO assinatura (id_assinatura, data_inicio, status_assinatura, valor_pago, id_usuario, id_tipo_assinatura) 
VALUES (1, DATE '2023-01-01', 'ATIVA', 16.90, 1, 2);

INSERT INTO assinatura (id_assinatura, data_inicio, status_assinatura, valor_pago, id_usuario, id_tipo_assinatura) 
VALUES (2, DATE '2023-06-15', 'ATIVA', 0.00, 2, 1);

INSERT INTO assinatura (id_assinatura, data_inicio, status_assinatura, valor_pago, id_usuario, id_tipo_assinatura) 
VALUES (3, DATE '2024-01-01', 'ATIVA', 26.90, 3, 3);

-- Inserir algumas playlists
INSERT INTO playlist (id_playlist, nome_playlist, descricao, publica, total_musicas, id_usuario) 
VALUES (1, 'Clássicos do Rock', 'Melhores músicas de rock de todos os tempos', 'S', 2, 1);

INSERT INTO playlist (id_playlist, nome_playlist, descricao, publica, total_musicas, id_usuario) 
VALUES (2, 'MPB Essencial', 'O melhor da Música Popular Brasileira', 'S', 1, 2);

INSERT INTO playlist (id_playlist, nome_playlist, descricao, publica, total_musicas, id_usuario) 
VALUES (3, 'Trabalho', 'Música para trabalhar concentrado', 'N', 2, 3);

-- Inserir músicas nas playlists
INSERT INTO playlist_musica (id_playlist, id_musica, ordem_musica) VALUES (1, 1, 1);
INSERT INTO playlist_musica (id_playlist, id_musica, ordem_musica) VALUES (1, 2, 2);
INSERT INTO playlist_musica (id_playlist, id_musica, ordem_musica) VALUES (2, 3, 1);
INSERT INTO playlist_musica (id_playlist, id_musica, ordem_musica) VALUES (3, 4, 1);
INSERT INTO playlist_musica (id_playlist, id_musica, ordem_musica) VALUES (3, 5, 2);

-- Inserir histórico de reprodução
INSERT INTO historico_reproducao (id_historico, data_reproducao, duracao_ouvida, dispositivo, id_usuario, id_musica) 
VALUES (1, TIMESTAMP '2024-01-15 14:30:00', 259, 'Smartphone', 1, 1);

INSERT INTO historico_reproducao (id_historico, data_reproducao, duracao_ouvida, dispositivo, id_usuario, id_musica) 
VALUES (2, TIMESTAMP '2024-01-15 14:35:00', 183, 'Smartphone', 1, 2);

INSERT INTO historico_reproducao (id_historico, data_reproducao, duracao_ouvida, dispositivo, id_usuario, id_musica) 
VALUES (3, TIMESTAMP '2024-01-16 09:15:00', 215, 'Desktop', 2, 3);

-- =====================================================
-- VERIFICAÇÃO DAS TABELAS CRIADAS
-- =====================================================

-- Confirmar criação das tabelas
SELECT table_name 
FROM user_tables 
WHERE table_name IN ('GENERO', 'USUARIO', 'ARTISTA', 'ALBUM', 'MUSICA', 'PLAYLIST', 'TIPO_ASSINATURA', 'ASSINATURA', 'PLAYLIST_MUSICA', 'HISTORICO_REPRODUCAO')
ORDER BY table_name;

-- Verificar dados inseridos
SELECT 'GENEROS' as tabela, COUNT(*) as registros FROM genero
UNION ALL
SELECT 'USUARIOS' as tabela, COUNT(*) as registros FROM usuario
UNION ALL
SELECT 'ARTISTAS' as tabela, COUNT(*) as registros FROM artista
UNION ALL
SELECT 'ALBUMS' as tabela, COUNT(*) as registros FROM album
UNION ALL
SELECT 'MUSICAS' as tabela, COUNT(*) as registros FROM musica
UNION ALL
SELECT 'PLAYLISTS' as tabela, COUNT(*) as registros FROM playlist
UNION ALL
SELECT 'TIPOS_ASSINATURA' as tabela, COUNT(*) as registros FROM tipo_assinatura
UNION ALL
SELECT 'ASSINATURAS' as tabela, COUNT(*) as registros FROM assinatura
UNION ALL
SELECT 'PLAYLIST_MUSICA' as tabela, COUNT(*) as registros FROM playlist_musica
UNION ALL
SELECT 'HISTORICO' as tabela, COUNT(*) as registros FROM historico_reproducao;

-- =====================================================
-- COMMIT DAS ALTERAÇÕES
-- =====================================================

COMMIT;

-- =====================================================
-- INSTRUÇÕES DE USO
-- =====================================================

PROMPT =====================================================;
PROMPT SCRIPT BASE-TABLES.SQL EXECUTADO COM SUCESSO!;
PROMPT =====================================================;
PROMPT;
PROMPT Este script criou as seguintes tabelas do sistema MusiStream:;
PROMPT - genero (gêneros musicais);
PROMPT - usuario (usuários da plataforma);
PROMPT - artista (artistas e bandas);
PROMPT - album (álbuns musicais);
PROMPT - musica (faixas musicais);
PROMPT - playlist (listas de reprodução);
PROMPT - tipo_assinatura (tipos de planos);
PROMPT - assinatura (assinaturas dos usuários);
PROMPT - playlist_musica (relacionamento N:M);
PROMPT - historico_reproducao (log de reproduções);
PROMPT;
PROMPT Agora você pode usar os exemplos de ALTER TABLE do README.md;
PROMPT e dos exercícios para praticar modificações estruturais.;
PROMPT;
PROMPT Para ver os exemplos práticos de ALTER TABLE, execute:;
PROMPT @alter_table_exemplos.sql;
PROMPT =====================================================;