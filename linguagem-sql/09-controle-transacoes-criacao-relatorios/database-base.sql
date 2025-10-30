DROP TABLE historico_reproducao CASCADE CONSTRAINTS;
DROP TABLE playlist_musica CASCADE CONSTRAINTS;
DROP TABLE playlist CASCADE CONSTRAINTS;
DROP TABLE assinatura CASCADE CONSTRAINTS;
DROP TABLE  musica CASCADE CONSTRAINTS;
DROP TABLE album CASCADE CONSTRAINTS;
DROP TABLE artista CASCADE CONSTRAINTS;
DROP TABLE genero CASCADE CONSTRAINTS;
DROP TABLE usuario CASCADE CONSTRAINTS;
DROP TABLE tipo_assinatura CASCADE CONSTRAINTS;

CREATE TABLE genero (
    id_genero           NUMBER PRIMARY KEY,
    nome_genero         VARCHAR2(50) NOT NULL UNIQUE,
    descricao           VARCHAR2(200),
    data_criacao        DATE DEFAULT SYSDATE,
    
    -- Constraints de validação
    CONSTRAINT ck_genero_nome CHECK (LENGTH(nome_genero) >= 2)
);

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
    CONSTRAINT ck_usuario_ativo CHECK (ativo IN ('S', 'N'))
);

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
    numero_membros      NUMBER,
    
    -- Constraints de validação
    CONSTRAINT ck_artista_ativo CHECK (ativo IN ('S', 'N')),
    CONSTRAINT ck_artista_carreira CHECK (data_inicio_carreira >= data_nascimento OR data_nascimento IS NULL),
    CONSTRAINT ck_numero_membros CHECK (numero_membros > 0 AND numero_membros <= 20)
);

CREATE TABLE album (
    id_album            NUMBER PRIMARY KEY,
    titulo              VARCHAR2(150) NOT NULL,
    data_lancamento     DATE,
    ano_lancamento      NUMBER,
    numero_faixas       NUMBER,
    duracao_total       NUMBER, -- em segundos
    capa_url            VARCHAR2(500),
    tipo_album          VARCHAR2(20) DEFAULT 'ALBUM',
    id_artista          NUMBER NOT NULL,
    id_genero           NUMBER,
    
    -- Relacionamentos
    CONSTRAINT fk_album_artista FOREIGN KEY (id_artista) 
        REFERENCES artista(id_artista) ON DELETE CASCADE,
    CONSTRAINT fk_album_genero FOREIGN KEY (id_genero) 
        REFERENCES genero(id_genero),
    
    -- Constraints de validação
    CONSTRAINT ck_numero_faixas CHECK (numero_faixas > 0),
    CONSTRAINT ck_duracao_total CHECK (duracao_total > 0),
    CONSTRAINT ck_tipo_album CHECK (tipo_album IN ('ALBUM', 'EP', 'SINGLE', 'COMPILACAO'))
);

CREATE TABLE musica (
    id_musica           NUMBER PRIMARY KEY,
    titulo              VARCHAR2(150) NOT NULL,
    duracao             NUMBER NOT NULL, -- em segundos
    numero_faixa        NUMBER,
    letra               CLOB,
    arquivo_url         VARCHAR2(500),
    total_reproducoes   NUMBER DEFAULT 0,
    data_upload         DATE DEFAULT SYSDATE,
    explicita           CHAR(1) DEFAULT 'N',
    id_album            NUMBER NOT NULL,
    id_genero           NUMBER,
    
    -- Relacionamentos
    CONSTRAINT fk_musica_album FOREIGN KEY (id_album) 
        REFERENCES album(id_album) ON DELETE CASCADE,
    CONSTRAINT fk_musica_genero FOREIGN KEY (id_genero) 
        REFERENCES genero(id_genero),
        
    -- Constraints de validação
    CONSTRAINT ck_duracao_musica CHECK (duracao > 0 AND duracao <= 3600), -- máximo 1 hora
    CONSTRAINT ck_numero_faixa CHECK (numero_faixa > 0),
    CONSTRAINT ck_total_reproducoes CHECK (total_reproducoes >= 0),
    CONSTRAINT ck_explicita CHECK (explicita IN ('S', 'N')),
    
    -- Constraint única composta
    CONSTRAINT uk_album_faixa UNIQUE (id_album, numero_faixa)
);

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
    
    -- Relacionamentos
    CONSTRAINT fk_playlist_usuario FOREIGN KEY (id_usuario) 
        REFERENCES usuario(id_usuario) ON DELETE CASCADE,
        
    -- Constraints de validação
    CONSTRAINT ck_playlist_publica CHECK (publica IN ('S', 'N')),
    CONSTRAINT ck_total_musicas CHECK (total_musicas >= 0),
    CONSTRAINT ck_duracao_playlist CHECK (duracao_total >= 0)
);

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
    
    -- Relacionamentos
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

-- Tabela de Relacionamento Playlist-Música (N:M)
CREATE TABLE playlist_musica (
    id_playlist         NUMBER NOT NULL,
    id_musica           NUMBER NOT NULL,
    ordem_musica        NUMBER NOT NULL,
    data_adicao         DATE DEFAULT SYSDATE,
    
    -- Chave primária composta
    CONSTRAINT pk_playlist_musica PRIMARY KEY (id_playlist, id_musica),
    
    -- Relacionamentos
    CONSTRAINT fk_pm_playlist FOREIGN KEY (id_playlist) 
        REFERENCES playlist(id_playlist) ON DELETE CASCADE,
    CONSTRAINT fk_pm_musica FOREIGN KEY (id_musica) 
        REFERENCES musica(id_musica) ON DELETE CASCADE,
        
    -- Constraints de validação
    CONSTRAINT ck_ordem_musica CHECK (ordem_musica > 0)
);

-- Tabela de Histórico de Reprodução
CREATE TABLE historico_reproducao (
    id_historico        NUMBER PRIMARY KEY,
    data_reproducao     TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    duracao_ouvida      NUMBER, -- em segundos
    dispositivo         VARCHAR2(50),
    localizacao         VARCHAR2(100), -- país, cidade
    qualidade_reproduzida VARCHAR2(20),
    id_usuario          NUMBER NOT NULL,
    id_musica           NUMBER NOT NULL,
    
    -- Relacionamentos
    CONSTRAINT fk_hist_usuario FOREIGN KEY (id_usuario) 
        REFERENCES usuario(id_usuario) ON DELETE CASCADE,
    CONSTRAINT fk_hist_musica FOREIGN KEY (id_musica) 
        REFERENCES musica(id_musica) ON DELETE CASCADE,
        
    -- Constraints de validação
    CONSTRAINT ck_duracao_ouvida CHECK (duracao_ouvida >= 0)
);

-------------------------
-- Insercoes 

-------------------------
-- TABELA GENERO
-------------------------
INSERT INTO genero (id_genero, nome_genero, descricao) VALUES (1, 'Rock', 'Guitarras elétricas e bateria intensa');
INSERT INTO genero (id_genero, nome_genero, descricao) VALUES (2, 'Pop', 'Músicas populares e cativantes');
INSERT INTO genero (id_genero, nome_genero, descricao) VALUES (3, 'Jazz', 'Improvisação e harmonias complexas');
INSERT INTO genero (id_genero, nome_genero, descricao) VALUES (4, 'Eletrônica', 'Músicas com sintetizadores e batidas eletrônicas');
INSERT INTO genero (id_genero, nome_genero, descricao) VALUES (5, 'Clássica', 'Composições orquestrais tradicionais');
INSERT INTO genero VALUES (6, 'Hip Hop', 'Beats marcantes e rimas');
INSERT INTO genero VALUES (7, 'Reggae', 'Batidas relaxantes e mensagens positivas');
INSERT INTO genero VALUES (8, 'Metal', 'Riffs pesados e vocais intensos');
INSERT INTO genero VALUES (9, 'Country', 'Músicas com influência rural e folk');
INSERT INTO genero VALUES (10, 'Blues', 'Guitarra emocionada e ritmos lentos');

-------------------------
-- TABELA USUARIO
-------------------------
INSERT INTO usuario (id_usuario, nome_usuario, email, senha, data_nascimento, pais) 
VALUES (1, 'Carlos Silva', 'carlos@email.com', '1234', TO_DATE('1990-05-12','YYYY-MM-DD'), 'Brasil');
INSERT INTO usuario (id_usuario, nome_usuario, email, senha, data_nascimento, pais) 
VALUES (2, 'Ana Souza', 'ana@email.com', 'abcd', TO_DATE('1988-09-21','YYYY-MM-DD'), 'Portugal');
INSERT INTO usuario (id_usuario, nome_usuario, email, senha, data_nascimento, pais) 
VALUES (3, 'John Smith', 'john@email.com', 'pass123', TO_DATE('1995-01-15','YYYY-MM-DD'), 'EUA');
INSERT INTO usuario (id_usuario, nome_usuario, email, senha, data_nascimento, pais) 
VALUES (4, 'Maria Oliveira', 'maria@email.com', 'senha@123', TO_DATE('2000-03-10','YYYY-MM-DD'), 'Brasil');
INSERT INTO usuario (id_usuario, nome_usuario, email, senha, data_nascimento, pais) 
VALUES (5, 'Pedro Costa', 'pedro@email.com', 'qwerty', TO_DATE('1992-07-08','YYYY-MM-DD'), 'Espanha');
INSERT INTO usuario VALUES (6, 'Lucas Mendes', 'lucas@email.com', 'senha1', TO_DATE('1998-08-20','YYYY-MM-DD'), 'Brasil', SYSDATE, NULL, 'S');
INSERT INTO usuario VALUES (7, 'Juliana Costa', 'juliana@email.com', 'senha2', TO_DATE('1987-03-14','YYYY-MM-DD'), 'Brasil', SYSDATE, NULL, 'S');
INSERT INTO usuario VALUES (8, 'Emily Clark', 'emily@email.com', 'senha3', TO_DATE('1993-12-11','YYYY-MM-DD'), 'EUA', SYSDATE, NULL, 'S');
INSERT INTO usuario VALUES (9, 'Miguel Torres', 'miguel@email.com', 'senha4', TO_DATE('1996-06-02','YYYY-MM-DD'), 'Espanha', SYSDATE, NULL, 'S');
INSERT INTO usuario VALUES (10, 'Sofia Martins', 'sofia@email.com', 'senha5', TO_DATE('2001-01-28','YYYY-MM-DD'), 'Portugal', SYSDATE, NULL, 'S');

-------------------------
-- TABELA ARTISTA
-------------------------
INSERT INTO artista (id_artista, nome_artista, nome_real, data_nascimento, pais_origem, data_inicio_carreira, numero_membros) 
VALUES (1, 'The Rockers', NULL, NULL, 'EUA', TO_DATE('2005-01-01','YYYY-MM-DD'), 4);
INSERT INTO artista (id_artista, nome_artista, nome_real, data_nascimento, pais_origem, data_inicio_carreira, numero_membros) 
VALUES (2, 'Ana Pop', 'Ana Pereira', TO_DATE('1994-06-22','YYYY-MM-DD'), 'Brasil', TO_DATE('2012-01-01','YYYY-MM-DD'), 1);
INSERT INTO artista (id_artista, nome_artista, nome_real, data_nascimento, pais_origem, data_inicio_carreira, numero_membros) 
VALUES (3, 'Jazz Band', NULL, NULL, 'França', TO_DATE('1999-01-01','YYYY-MM-DD'), 6);
INSERT INTO artista (id_artista, nome_artista, nome_real, data_nascimento, pais_origem, data_inicio_carreira, numero_membros) 
VALUES (4, 'DJ Electro', 'David Johnson', TO_DATE('1985-11-03','YYYY-MM-DD'), 'EUA', TO_DATE('2008-01-01','YYYY-MM-DD'), 1);
INSERT INTO artista (id_artista, nome_artista, nome_real, data_nascimento, pais_origem, data_inicio_carreira, numero_membros) 
VALUES (5, 'Orquestra Clássica', NULL, NULL, 'Alemanha', TO_DATE('1980-01-01','YYYY-MM-DD'), 20);
INSERT INTO artista VALUES (6, 'HipHop Kings', NULL, NULL, 'EUA', TO_DATE('2010-01-01','YYYY-MM-DD'), 5);
INSERT INTO artista VALUES (7, 'Reggae Roots', NULL, NULL, 'Jamaica', TO_DATE('2000-01-01','YYYY-MM-DD'), 6);
INSERT INTO artista VALUES (8, 'Metal Fury', NULL, NULL, 'Alemanha', TO_DATE('2012-01-01','YYYY-MM-DD'), 4);
INSERT INTO artista VALUES (9, 'Country Stars', NULL, NULL, 'EUA', TO_DATE('2005-01-01','YYYY-MM-DD'), 3);
INSERT INTO artista VALUES (10, 'Blues Legend', 'Robert Green', TO_DATE('1975-09-05','YYYY-MM-DD'), 'EUA', TO_DATE('1995-01-01','YYYY-MM-DD'), 1);

-------------------------
-- TABELA ALBUM
-------------------------
INSERT INTO album (id_album, titulo, data_lancamento, ano_lancamento, numero_faixas, duracao_total, id_artista, id_genero) 
VALUES (1, 'Rock Forever', TO_DATE('2010-05-10','YYYY-MM-DD'), 2010, 10, 3600, 1, 1);
INSERT INTO album (id_album, titulo, data_lancamento, ano_lancamento, numero_faixas, duracao_total, id_artista, id_genero) 
VALUES (2, 'Ana Hits', TO_DATE('2015-03-20','YYYY-MM-DD'), 2015, 8, 2400, 2, 2);
INSERT INTO album (id_album, titulo, data_lancamento, ano_lancamento, numero_faixas, duracao_total, id_artista, id_genero) 
VALUES (3, 'Jazz Nights', TO_DATE('2001-09-01','YYYY-MM-DD'), 2001, 12, 4800, 3, 3);
INSERT INTO album (id_album, titulo, data_lancamento, ano_lancamento, numero_faixas, duracao_total, id_artista, id_genero) 
VALUES (4, 'Electro Beats', TO_DATE('2018-06-15','YYYY-MM-DD'), 2018, 15, 5400, 4, 4);
INSERT INTO album (id_album, titulo, data_lancamento, ano_lancamento, numero_faixas, duracao_total, id_artista, id_genero) 
VALUES (5, 'Sinfonia nº1', TO_DATE('1990-01-01','YYYY-MM-DD'), 1990, 5, 7200, 5, 5);
INSERT INTO album VALUES (6, 'HipHop Kings Vol.1', TO_DATE('2018-02-12','YYYY-MM-DD'), 2018, 12, 4200, 6, 6);
INSERT INTO album VALUES (7, 'Roots of Peace', TO_DATE('2016-07-20','YYYY-MM-DD'), 2016, 10, 3600, 7, 7);
INSERT INTO album VALUES (8, 'Metal Rage', TO_DATE('2019-10-10','YYYY-MM-DD'), 2019, 11, 4500, 8, 8);
INSERT INTO album VALUES (9, 'Country Roads', TO_DATE('2014-04-18','YYYY-MM-DD'), 2014, 9, 3300, 9, 9);
INSERT INTO album VALUES (10, 'Blues Soul', TO_DATE('2005-03-01','YYYY-MM-DD'), 2005, 8, 3000, 10, 10);

-------------------------
-- TABELA MUSICA
-------------------------
-------------------------
-- MUSICA (5 músicas por álbum)
-------------------------
-- Álbum 1: Rock Forever
INSERT INTO musica VALUES (1, 'Rock Anthem', 300, 1, NULL, NULL, 0, SYSDATE, 'N', 1, 1);
INSERT INTO musica VALUES (2, 'Guitar Hero', 280, 2, NULL, NULL, 0, SYSDATE, 'N', 1, 1);
INSERT INTO musica VALUES (3, 'Drum Solo', 250, 3, NULL, NULL, 0, SYSDATE, 'N', 1, 1);
INSERT INTO musica VALUES (4, 'Wild Energy', 330, 4, NULL, NULL, 0, SYSDATE, 'N', 1, 1);
INSERT INTO musica VALUES (5, 'Final Rock', 340, 5, NULL, NULL, 0, SYSDATE, 'N', 1, 1);

-- Álbum 2: Ana Hits
INSERT INTO musica VALUES (6, 'Love Song', 240, 1, NULL, NULL, 0, SYSDATE, 'N', 2, 2);
INSERT INTO musica VALUES (7, 'Dancing Night', 260, 2, NULL, NULL, 0, SYSDATE, 'N', 2, 2);
INSERT INTO musica VALUES (8, 'Pop Forever', 230, 3, NULL, NULL, 0, SYSDATE, 'N', 2, 2);
INSERT INTO musica VALUES (9, 'Summer Vibes', 250, 4, NULL, NULL, 0, SYSDATE, 'N', 2, 2);
INSERT INTO musica VALUES (10, 'Dreams', 220, 5, NULL, NULL, 0, SYSDATE, 'N', 2, 2);

-- Álbum 3: Jazz Nights
INSERT INTO musica VALUES (11, 'Smooth Jazz', 360, 1, NULL, NULL, 0, SYSDATE, 'N', 3, 3);
INSERT INTO musica VALUES (12, 'Blue Notes', 340, 2, NULL, NULL, 0, SYSDATE, 'N', 3, 3);
INSERT INTO musica VALUES (13, 'Improvisation', 380, 3, NULL, NULL, 0, SYSDATE, 'N', 3, 3);
INSERT INTO musica VALUES (14, 'Jazz Mood', 360, 4, NULL, NULL, 0, SYSDATE, 'N', 3, 3);
INSERT INTO musica VALUES (15, 'Late Night Jazz', 360, 5, NULL, NULL, 0, SYSDATE, 'N', 3, 3);

-- Álbum 4: Electro Beats
INSERT INTO musica VALUES (16, 'Electronic Vibes', 420, 1, NULL, NULL, 0, SYSDATE, 'N', 4, 4);
INSERT INTO musica VALUES (17, 'Bass Drop', 400, 2, NULL, NULL, 0, SYSDATE, 'N', 4, 4);
INSERT INTO musica VALUES (18, 'Synth Wave', 380, 3, NULL, NULL, 0, SYSDATE, 'N', 4, 4);
INSERT INTO musica VALUES (19, 'Electro Night', 400, 4, NULL, NULL, 0, SYSDATE, 'N', 4, 4);
INSERT INTO musica VALUES (20, 'Future Beat', 400, 5, NULL, NULL, 0, SYSDATE, 'N', 4, 4);

-- Álbum 5: Sinfonia nº1
INSERT INTO musica VALUES (21, 'Sinfonia Abertura', 600, 1, NULL, NULL, 0, SYSDATE, 'N', 5, 5);
INSERT INTO musica VALUES (22, 'Movimento I', 720, 2, NULL, NULL, 0, SYSDATE, 'N', 5, 5);
INSERT INTO musica VALUES (23, 'Movimento II', 780, 3, NULL, NULL, 0, SYSDATE, 'N', 5, 5);
INSERT INTO musica VALUES (24, 'Movimento III', 720, 4, NULL, NULL, 0, SYSDATE, 'N', 5, 5);
INSERT INTO musica VALUES (25, 'Finale', 780, 5, NULL, NULL, 0, SYSDATE, 'N', 5, 5);

INSERT INTO musica VALUES (26, 'Rap Game Strong', 250, 1, NULL, NULL, 0, SYSDATE, 'N', 6, 6);
INSERT INTO musica VALUES (27, 'Flow Master', 260, 2, NULL, NULL, 0, SYSDATE, 'N', 6, 6);

INSERT INTO musica VALUES (28, 'Island Vibe', 300, 1, NULL, NULL, 0, SYSDATE, 'N', 7, 7);
INSERT INTO musica VALUES (29, 'Positive Waves', 290, 2, NULL, NULL, 0, SYSDATE, 'N', 7, 7);

INSERT INTO musica VALUES (30, 'Thunder Guitar', 320, 1, NULL, NULL, 0, SYSDATE, 'N', 8, 8);
INSERT INTO musica VALUES (31, 'Metal Roar', 340, 2, NULL, NULL, 0, SYSDATE, 'N', 8, 8);

INSERT INTO musica VALUES (32, 'Old Town Life', 280, 1, NULL, NULL, 0, SYSDATE, 'N', 9, 9);
INSERT INTO musica VALUES (33, 'Road Trip Song', 300, 2, NULL, NULL, 0, SYSDATE, 'N', 9, 9);

INSERT INTO musica VALUES (34, 'Blues Heart', 360, 1, NULL, NULL, 0, SYSDATE, 'N', 10, 10);
INSERT INTO musica VALUES (35, 'Night Blues', 380, 2, NULL, NULL, 0, SYSDATE, 'N', 10, 10);

-------------------------
-- PLAYLIST
-------------------------
INSERT INTO playlist VALUES (1, 'Favoritas Rock', 'Melhores músicas de rock', 'S', SYSDATE, SYSTIMESTAMP, 0, 0, 1);
INSERT INTO playlist VALUES (2, 'Relax Jazz', 'Jazz para relaxar', 'N', SYSDATE, SYSTIMESTAMP, 0, 0, 2);
INSERT INTO playlist VALUES (3, 'Treino Pesado', 'Músicas para academia', 'S', SYSDATE, SYSTIMESTAMP, 0, 0, 3);
INSERT INTO playlist VALUES (4, 'Clássicos Eternos', 'Obras-primas da música clássica', 'N', SYSDATE, SYSTIMESTAMP, 0, 0, 4);
INSERT INTO playlist VALUES (5, 'Party Mix', 'Batidas eletrônicas para festas', 'S', SYSDATE, SYSTIMESTAMP, 0, 0, 5);
INSERT INTO playlist VALUES (6, 'Hip Hop Vibes', 'Melhores rimas', 'S', SYSDATE, SYSTIMESTAMP, 0, 0, 6);
INSERT INTO playlist VALUES (7, 'Reggae Chill', 'Só vibrações positivas', 'N', SYSDATE, SYSTIMESTAMP, 0, 0, 7);
INSERT INTO playlist VALUES (8, 'Metal Power', 'Metal pesado', 'S', SYSDATE, SYSTIMESTAMP, 0, 0, 8);

-------------------------
-- PLAYLIST_MUSICA
-------------------------
INSERT INTO playlist_musica VALUES (1, 1, 1);
INSERT INTO playlist_musica VALUES (1, 2, 2);
INSERT INTO playlist_musica VALUES (2, 11, 1);
INSERT INTO playlist_musica VALUES (2, 12, 2);
INSERT INTO playlist_musica VALUES (3, 6, 1);
INSERT INTO playlist_musica VALUES (3, 7, 2);
INSERT INTO playlist_musica VALUES (4, 21, 1);
INSERT INTO playlist_musica VALUES (4, 22, 2);
INSERT INTO playlist_musica VALUES (5, 16, 1);
INSERT INTO playlist_musica VALUES (5, 18, 2);
INSERT INTO playlist_musica VALUES (3, 26, 1);
INSERT INTO playlist_musica VALUES (3, 27, 2);
INSERT INTO playlist_musica VALUES (4, 28, 1);
INSERT INTO playlist_musica VALUES (4, 29, 2);
INSERT INTO playlist_musica VALUES (5, 30, 1);
INSERT INTO playlist_musica VALUES (5, 31, 2);

-------------------------
-- TABELA TIPO_ASSINATURA
-------------------------
INSERT INTO tipo_assinatura (id_tipo_assinatura, nome_plano, preco_mensal, qualidade_audio, downloads_offline, pulos_ilimitados, sem_anuncios, descricao) 
VALUES (1, 'Free', 0, 'Média', 'N', 'N', 'N', 'Plano gratuito com anúncios');
INSERT INTO tipo_assinatura (id_tipo_assinatura, nome_plano, preco_mensal, qualidade_audio, downloads_offline, pulos_ilimitados, sem_anuncios, descricao) 
VALUES (2, 'Premium', 29.90, 'Alta', 'S', 'S', 'S', 'Plano premium sem anúncios');
INSERT INTO tipo_assinatura (id_tipo_assinatura, nome_plano, preco_mensal, qualidade_audio, downloads_offline, pulos_ilimitados, sem_anuncios, descricao) 
VALUES (3, 'Família', 49.90, 'Alta', 'S', 'S', 'S', 'Plano familiar para até 6 contas');
INSERT INTO tipo_assinatura (id_tipo_assinatura, nome_plano, preco_mensal, qualidade_audio, downloads_offline, pulos_ilimitados, sem_anuncios, descricao) 
VALUES (4, 'Estudante', 14.90, 'Alta', 'S', 'S', 'S', 'Plano com desconto para estudantes');
INSERT INTO tipo_assinatura (id_tipo_assinatura, nome_plano, preco_mensal, qualidade_audio, downloads_offline, pulos_ilimitados, sem_anuncios, descricao) 
VALUES (5, 'HiFi', 39.90, 'Lossless', 'S', 'S', 'S', 'Plano para audiófilos');

-------------------------
-- TABELA ASSINATURA
-------------------------
INSERT INTO assinatura (id_assinatura, data_inicio, metodo_pagamento, valor_pago, id_usuario, id_tipo_assinatura) 
VALUES (1, TO_DATE('2024-01-01','YYYY-MM-DD'), 'Cartão', 29.90, 1, 2);
INSERT INTO assinatura (id_assinatura, data_inicio, metodo_pagamento, valor_pago, id_usuario, id_tipo_assinatura) 
VALUES (2, TO_DATE('2024-02-10','YYYY-MM-DD'), 'Pix', 14.90, 2, 4);
INSERT INTO assinatura (id_assinatura, data_inicio, metodo_pagamento, valor_pago, id_usuario, id_tipo_assinatura) 
VALUES (3, TO_DATE('2024-03-05','YYYY-MM-DD'), 'Cartão', 0, 3, 1);
INSERT INTO assinatura (id_assinatura, data_inicio, metodo_pagamento, valor_pago, id_usuario, id_tipo_assinatura) 
VALUES (4, TO_DATE('2024-04-15','YYYY-MM-DD'), 'Paypal', 49.90, 4, 3);
INSERT INTO assinatura (id_assinatura, data_inicio, metodo_pagamento, valor_pago, id_usuario, id_tipo_assinatura) 
VALUES (5, TO_DATE('2024-05-01','YYYY-MM-DD'), 'Cartão', 39.90, 5, 5);

-------------------------
-- TABELA HISTORICO_REPRODUCAO
-------------------------
INSERT INTO historico_reproducao (id_historico, duracao_ouvida, dispositivo, localizacao, qualidade_reproduzida, id_usuario, id_musica) 
VALUES (1, 200, 'iPhone', 'São Paulo - Brasil', 'Alta', 1, 1);
INSERT INTO historico_reproducao (id_historico, duracao_ouvida, dispositivo, localizacao, qualidade_reproduzida, id_usuario, id_musica) 
VALUES (2, 150, 'Android', 'Lisboa - Portugal', 'Média', 2, 3);
INSERT INTO historico_reproducao (id_historico, duracao_ouvida, dispositivo, localizacao, qualidade_reproduzida, id_usuario, id_musica) 
VALUES (3, 240, 'PC', 'Nova York - EUA', 'Alta', 3, 2);
INSERT INTO historico_reproducao (id_historico, duracao_ouvida, dispositivo, localizacao, qualidade_reproduzida, id_usuario, id_musica) 
VALUES (4, 600, 'Tablet', 'Madrid - Espanha', 'Alta', 4, 5);
INSERT INTO historico_reproducao (id_historico, duracao_ouvida, dispositivo, localizacao, qualidade_reproduzida, id_usuario, id_musica) 
VALUES (5, 420, 'Smart TV', 'Rio de Janeiro - Brasil', 'Lossless', 5, 4);