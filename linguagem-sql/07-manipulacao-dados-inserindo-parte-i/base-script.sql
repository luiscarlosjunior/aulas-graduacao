-- =====================================================
-- SCRIPT BASE PARA ALTERAÇÃO DE ESTRUTURA DE TABELAS
-- Módulo 07: Sistema MusiStream - Tabelas Básicas
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

COMMIT;
