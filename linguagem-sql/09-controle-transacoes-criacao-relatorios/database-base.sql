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
INSERT INTO genero VALUES (11, 'Samba', 'Ritmos brasileiros com percussão e alegria');
INSERT INTO genero VALUES (12, 'Funk', 'Batidas dançantes e grooves marcantes');
INSERT INTO genero VALUES (13, 'Soul', 'Vocais emocionantes e melodias suaves');
INSERT INTO genero VALUES (14, 'Gospel', 'Músicas religiosas e inspiradoras');
INSERT INTO genero VALUES (15, 'Forró', 'Ritmos nordestinos com sanfona e zabumba');
INSERT INTO genero VALUES (16, 'MPB', 'Música Popular Brasileira com influências diversas');
INSERT INTO genero VALUES (17, 'Bossa Nova', 'Ritmos suaves e harmonias sofisticadas');
INSERT INTO genero VALUES (18, 'Rap', 'Rimas rápidas e mensagens sociais');
INSERT INTO genero VALUES (19, 'Punk', 'Músicas rápidas e com atitude rebelde');
INSERT INTO genero VALUES (20, 'Disco', 'Batidas dançantes e estilo retrô');
INSERT INTO genero VALUES (21, 'K-Pop', 'Música pop coreana com coreografias elaboradas');
INSERT INTO genero VALUES (22, 'Reggaeton', 'Batidas latinas e letras envolventes');
INSERT INTO genero VALUES (23, 'Trap', 'Subgênero do hip hop com batidas eletrônicas');
INSERT INTO genero VALUES (24, 'House', 'Música eletrônica com batidas repetitivas');
INSERT INTO genero VALUES (25, 'Techno', 'Música eletrônica com foco em sintetizadores');
INSERT INTO genero VALUES (26, 'Dubstep', 'Batidas eletrônicas com drops intensos');
INSERT INTO genero VALUES (27, 'Indie', 'Música independente com estilos variados');
INSERT INTO genero VALUES (28, 'Alternativo', 'Gênero que foge do mainstream');
INSERT INTO genero VALUES (29, 'Afrobeat', 'Ritmos africanos com influências modernas');
INSERT INTO genero VALUES (30, 'Sertanejo', 'Música rural brasileira com letras românticas');

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
INSERT INTO usuario VALUES (11, 'Gabriel Lima', 'gabriel@email.com', 'senha6', TO_DATE('1997-04-15','YYYY-MM-DD'), 'Brasil', SYSDATE, NULL, 'S');
INSERT INTO usuario VALUES (12, 'Laura Santos', 'laura@email.com', 'senha7', TO_DATE('1990-11-22','YYYY-MM-DD'), 'Portugal', SYSDATE, NULL, 'S');
INSERT INTO usuario VALUES (13, 'Michael Brown', 'michael@email.com', 'senha8', TO_DATE('1985-02-18','YYYY-MM-DD'), 'EUA', SYSDATE, NULL, 'S');
INSERT INTO usuario VALUES (14, 'Camila Rocha', 'camila@email.com', 'senha9', TO_DATE('1999-09-09','YYYY-MM-DD'), 'Brasil', SYSDATE, NULL, 'S');
INSERT INTO usuario VALUES (15, 'Ricardo Alves', 'ricardo@email.com', 'senha10', TO_DATE('1993-07-30','YYYY-MM-DD'), 'Espanha', SYSDATE, NULL, 'S');
INSERT INTO usuario VALUES (16, 'Isabela Costa', 'isabela@email.com', 'senha11', TO_DATE('1991-12-05','YYYY-MM-DD'), 'Brasil', SYSDATE, NULL, 'S');
INSERT INTO usuario VALUES (17, 'Daniel Oliveira', 'daniel@email.com', 'senha12', TO_DATE('1989-03-25','YYYY-MM-DD'), 'Portugal', SYSDATE, NULL, 'S');
INSERT INTO usuario VALUES (18, 'Sophia Johnson', 'sophia@email.com', 'senha13', TO_DATE('1994-10-10','YYYY-MM-DD'), 'EUA', SYSDATE, NULL, 'S');
INSERT INTO usuario VALUES (19, 'Lucas Ferreira', 'lucasf@email.com', 'senha14', TO_DATE('1996-06-18','YYYY-MM-DD'), 'Brasil', SYSDATE, NULL, 'S');
INSERT INTO usuario VALUES (20, 'Mariana Dias', 'mariana@email.com', 'senha15', TO_DATE('1992-08-08','YYYY-MM-DD'), 'Portugal', SYSDATE, NULL, 'S');
INSERT INTO usuario VALUES (21, 'Ethan Wilson', 'ethan@email.com', 'senha16', TO_DATE('1998-01-01','YYYY-MM-DD'), 'EUA', SYSDATE, NULL, 'S');
INSERT INTO usuario VALUES (22, 'Alice Martins', 'alice@email.com', 'senha17', TO_DATE('1990-04-14','YYYY-MM-DD'), 'Brasil', SYSDATE, NULL, 'S');
INSERT INTO usuario VALUES (23, 'Henry White', 'henry@email.com', 'senha18', TO_DATE('1987-07-07','YYYY-MM-DD'), 'EUA', SYSDATE, NULL, 'S');
INSERT INTO usuario VALUES (24, 'Victoria Cruz', 'victoria@email.com', 'senha19', TO_DATE('1995-05-25','YYYY-MM-DD'), 'Portugal', SYSDATE, NULL, 'S');
INSERT INTO usuario VALUES (25, 'Benjamin Torres', 'benjamin@email.com', 'senha20', TO_DATE('1993-09-12','YYYY-MM-DD'), 'Espanha', SYSDATE, NULL, 'S');
INSERT INTO usuario VALUES (26, 'Chloe Garcia', 'chloe@email.com', 'senha21', TO_DATE('1997-02-20','YYYY-MM-DD'), 'Brasil', SYSDATE, NULL, 'S');
INSERT INTO usuario VALUES (27, 'Oliver Martinez', 'oliver@email.com', 'senha22', TO_DATE('1991-11-11','YYYY-MM-DD'), 'EUA', SYSDATE, NULL, 'S');
INSERT INTO usuario VALUES (28, 'Emma Lopez', 'emma@email.com', 'senha23', TO_DATE('1999-03-03','YYYY-MM-DD'), 'Portugal', SYSDATE, NULL, 'S');
INSERT INTO usuario VALUES (29, 'Liam Gonzalez', 'liam@email.com', 'senha24', TO_DATE('1994-06-06','YYYY-MM-DD'), 'Espanha', SYSDATE, NULL, 'S');
INSERT INTO usuario VALUES (30, 'Mia Rodriguez', 'mia@email.com', 'senha25', TO_DATE('1992-12-12','YYYY-MM-DD'), 'Brasil', SYSDATE, NULL, 'S');

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
INSERT INTO artista VALUES (11, 'Pop Queens', 'Sophia Brown', TO_DATE('1990-03-15','YYYY-MM-DD'), 'Reino Unido', TO_DATE('2010-06-01','YYYY-MM-DD'), 3);
INSERT INTO artista VALUES (12, 'Latin Beats', NULL, NULL, 'México', TO_DATE('2008-01-01','YYYY-MM-DD'), 5);
INSERT INTO artista VALUES (13, 'Soulful Voices', 'Emily Davis', TO_DATE('1988-07-22','YYYY-MM-DD'), 'EUA', TO_DATE('2005-01-01','YYYY-MM-DD'), 1);
INSERT INTO artista VALUES (14, 'Indie Stars', NULL, NULL, 'Canadá', TO_DATE('2015-01-01','YYYY-MM-DD'), 4);
INSERT INTO artista VALUES (15, 'Electronic Masters', NULL, NULL, 'Suécia', TO_DATE('2012-01-01','YYYY-MM-DD'), 2);
INSERT INTO artista VALUES (16, 'Folk Legends', 'John Carter', TO_DATE('1970-05-10','YYYY-MM-DD'), 'Irlanda', TO_DATE('1990-01-01','YYYY-MM-DD'), 1);
INSERT INTO artista VALUES (17, 'Afrobeat Kings', NULL, NULL, 'Nigéria', TO_DATE('2000-01-01','YYYY-MM-DD'), 8);
INSERT INTO artista VALUES (18, 'Disco Fever', NULL, NULL, 'EUA', TO_DATE('1978-01-01','YYYY-MM-DD'), 6);
INSERT INTO artista VALUES (19, 'K-Pop Stars', NULL, NULL, 'Coreia do Sul', TO_DATE('2015-01-01','YYYY-MM-DD'), 7);
INSERT INTO artista VALUES (20, 'Trap Lords', NULL, NULL, 'EUA', TO_DATE('2018-01-01','YYYY-MM-DD'), 3);
INSERT INTO artista VALUES (21, 'House Beats', NULL, NULL, 'Holanda', TO_DATE('2010-01-01','YYYY-MM-DD'), 2);
INSERT INTO artista VALUES (22, 'Techno Masters', NULL, NULL, 'Alemanha', TO_DATE('2005-01-01','YYYY-MM-DD'), 1);
INSERT INTO artista VALUES (23, 'Dubstep Heroes', NULL, NULL, 'Reino Unido', TO_DATE('2012-01-01','YYYY-MM-DD'), 1);
INSERT INTO artista VALUES (24, 'Alternative Vibes', NULL, NULL, 'Austrália', TO_DATE('2016-01-01','YYYY-MM-DD'), 4);
INSERT INTO artista VALUES (25, 'Classical Ensemble', NULL, NULL, 'Itália', TO_DATE('1985-01-01','YYYY-MM-DD'), 15);
INSERT INTO artista VALUES (26, 'Jazz Legends', NULL, NULL, 'EUA', TO_DATE('1990-01-01','YYYY-MM-DD'), 5);
INSERT INTO artista VALUES (27, 'Rock Revival', NULL, NULL, 'Canadá', TO_DATE('2000-01-01','YYYY-MM-DD'), 4);
INSERT INTO artista VALUES (28, 'Blues Revival', NULL, NULL, 'EUA', TO_DATE('1995-01-01','YYYY-MM-DD'), 3);
INSERT INTO artista VALUES (29, 'Samba Kings', NULL, NULL, 'Brasil', TO_DATE('2010-01-01','YYYY-MM-DD'), 6);
INSERT INTO artista VALUES (30, 'Forró Stars', NULL, NULL, 'Brasil', TO_DATE('2015-01-01','YYYY-MM-DD'), 5);

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
INSERT INTO album VALUES (11, 'Pop Queens Vol.1', TO_DATE('2010-06-01','YYYY-MM-DD'), 2010, 12, 3600, 11, 2);
INSERT INTO album VALUES (12, 'Latin Beats', TO_DATE('2008-01-01','YYYY-MM-DD'), 2008, 10, 3000, 12, 7);
INSERT INTO album VALUES (13, 'Soulful Melodies', TO_DATE('2005-01-01','YYYY-MM-DD'), 2005, 8, 2400, 13, 13);
INSERT INTO album VALUES (14, 'Indie Dreams', TO_DATE('2015-01-01','YYYY-MM-DD'), 2015, 10, 3000, 14, 27);
INSERT INTO album VALUES (15, 'Electronic Masters', TO_DATE('2012-01-01','YYYY-MM-DD'), 2012, 15, 5400, 15, 24);
INSERT INTO album VALUES (16, 'Folk Legends', TO_DATE('1990-01-01','YYYY-MM-DD'), 1990, 12, 3600, 16, 9);
INSERT INTO album VALUES (17, 'Afrobeat Kings', TO_DATE('2000-01-01','YYYY-MM-DD'), 2000, 10, 3000, 17, 29);
INSERT INTO album VALUES (18, 'Disco Fever', TO_DATE('1978-01-01','YYYY-MM-DD'), 1978, 8, 2400, 18, 20);
INSERT INTO album VALUES (19, 'K-Pop Stars Vol.1', TO_DATE('2015-01-01','YYYY-MM-DD'), 2015, 12, 3600, 19, 21);
INSERT INTO album VALUES (20, 'Trap Lords', TO_DATE('2018-01-01','YYYY-MM-DD'), 2018, 10, 3000, 20, 23);
INSERT INTO album VALUES (21, 'House Beats', TO_DATE('2010-01-01','YYYY-MM-DD'), 2010, 15, 5400, 21, 24);
INSERT INTO album VALUES (22, 'Techno Masters', TO_DATE('2005-01-01','YYYY-MM-DD'), 2005, 12, 3600, 22, 25);
INSERT INTO album VALUES (23, 'Dubstep Heroes', TO_DATE('2012-01-01','YYYY-MM-DD'), 2012, 10, 3000, 23, 26);
INSERT INTO album VALUES (24, 'Alternative Vibes', TO_DATE('2016-01-01','YYYY-MM-DD'), 2016, 8, 2400, 24, 28);
INSERT INTO album VALUES (25, 'Classical Ensemble', TO_DATE('1985-01-01','YYYY-MM-DD'), 1985, 15, 5400, 25, 5);
INSERT INTO album VALUES (26, 'Jazz Legends', TO_DATE('1990-01-01','YYYY-MM-DD'), 1990, 12, 3600, 26, 3);
INSERT INTO album VALUES (27, 'Rock Revival', TO_DATE('2000-01-01','YYYY-MM-DD'), 2000, 10, 3000, 27, 1);
INSERT INTO album VALUES (28, 'Blues Revival', TO_DATE('1995-01-01','YYYY-MM-DD'), 1995, 8, 2400, 28, 10);
INSERT INTO album VALUES (29, 'Samba Kings', TO_DATE('2010-01-01','YYYY-MM-DD'), 2010, 12, 3600, 29, 11);
INSERT INTO album VALUES (30, 'Forró Stars', TO_DATE('2015-01-01','YYYY-MM-DD'), 2015, 10, 3000, 30, 15);
INSERT INTO album VALUES (31, 'Rock Legends', TO_DATE('2005-01-01','YYYY-MM-DD'), 2005, 12, 3600, 1, 1);
INSERT INTO album VALUES (32, 'Pop Classics', TO_DATE('2010-01-01','YYYY-MM-DD'), 2010, 10, 3000, 2, 2);
INSERT INTO album VALUES (33, 'Jazz Classics', TO_DATE('2000-01-01','YYYY-MM-DD'), 2000, 8, 2400, 3, 3);
INSERT INTO album VALUES (34, 'Electro Classics', TO_DATE('2015-01-01','YYYY-MM-DD'), 2015, 15, 5400, 4, 4);
INSERT INTO album VALUES (35, 'Classical Symphony', TO_DATE('1990-01-01','YYYY-MM-DD'), 1990, 5, 7200, 5, 5);
INSERT INTO album VALUES (36, 'HipHop Classics', TO_DATE('2018-01-01','YYYY-MM-DD'), 2018, 12, 4200, 6, 6);
INSERT INTO album VALUES (37, 'Reggae Classics', TO_DATE('2016-01-01','YYYY-MM-DD'), 2016, 10, 3600, 7, 7);
INSERT INTO album VALUES (38, 'Metal Classics', TO_DATE('2019-01-01','YYYY-MM-DD'), 2019, 11, 4500, 8, 8);
INSERT INTO album VALUES (39, 'Country Classics', TO_DATE('2014-01-01','YYYY-MM-DD'), 2014, 9, 3300, 9, 9);
INSERT INTO album VALUES (40, 'Blues Classics', TO_DATE('2005-01-01','YYYY-MM-DD'), 2005, 8, 3000, 10, 10);

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

-- Álbum 6: HipHop Kings Vol.1
INSERT INTO musica VALUES (36, 'HipHop Anthem', 300, 1, NULL, NULL, 0, SYSDATE, 'N', 6, 6);
INSERT INTO musica VALUES (37, 'Street Beats', 280, 2, NULL, NULL, 0, SYSDATE, 'N', 6, 6);
INSERT INTO musica VALUES (38, 'Rap Battle', 250, 3, NULL, NULL, 0, SYSDATE, 'N', 6, 6);
INSERT INTO musica VALUES (39, 'Urban Flow', 330, 4, NULL, NULL, 0, SYSDATE, 'N', 6, 6);
INSERT INTO musica VALUES (40, 'Final Verse', 340, 5, NULL, NULL, 0, SYSDATE, 'N', 6, 6);

-- Álbum 7: Roots of Peace
INSERT INTO musica VALUES (41, 'Peaceful Melody', 300, 1, NULL, NULL, 0, SYSDATE, 'N', 7, 7);
INSERT INTO musica VALUES (42, 'Roots Jam', 280, 2, NULL, NULL, 0, SYSDATE, 'N', 7, 7);
INSERT INTO musica VALUES (43, 'Harmony Vibes', 250, 3, NULL, NULL, 0, SYSDATE, 'N', 7, 7);
INSERT INTO musica VALUES (44, 'Island Groove', 330, 4, NULL, NULL, 0, SYSDATE, 'N', 7, 7);
INSERT INTO musica VALUES (45, 'Chill Reggae', 340, 5, NULL, NULL, 0, SYSDATE, 'N', 7, 7);

-- Álbum 8: Metal Rage
INSERT INTO musica VALUES (46, 'Metal Storm', 300, 1, NULL, NULL, 0, SYSDATE, 'N', 8, 8);
INSERT INTO musica VALUES (47, 'Heavy Riffs', 280, 2, NULL, NULL, 0, SYSDATE, 'N', 8, 8);
INSERT INTO musica VALUES (48, 'Screaming Souls', 250, 3, NULL, NULL, 0, SYSDATE, 'N', 8, 8);
INSERT INTO musica VALUES (49, 'Iron Fist', 330, 4, NULL, NULL, 0, SYSDATE, 'N', 8, 8);
INSERT INTO musica VALUES (50, 'Final Fury', 340, 5, NULL, NULL, 0, SYSDATE, 'N', 8, 8);

-- Álbum 9: Country Roads
INSERT INTO musica VALUES (51, 'Country Sunrise', 300, 1, NULL, NULL, 0, SYSDATE, 'N', 9, 9);
INSERT INTO musica VALUES (52, 'Rural Harmony', 280, 2, NULL, NULL, 0, SYSDATE, 'N', 9, 9);
INSERT INTO musica VALUES (53, 'Farm Life', 250, 3, NULL, NULL, 0, SYSDATE, 'N', 9, 9);
INSERT INTO musica VALUES (54, 'Open Fields', 330, 4, NULL, NULL, 0, SYSDATE, 'N', 9, 9);
INSERT INTO musica VALUES (55, 'Country Nights', 340, 5, NULL, NULL, 0, SYSDATE, 'N', 9, 9);

-- Álbum 10: Blues Soul
INSERT INTO musica VALUES (56, 'Soulful Blues', 300, 1, NULL, NULL, 0, SYSDATE, 'N', 10, 10);
INSERT INTO musica VALUES (57, 'Deep Emotions', 280, 2, NULL, NULL, 0, SYSDATE, 'N', 10, 10);
INSERT INTO musica VALUES (58, 'Blues Journey', 250, 3, NULL, NULL, 0, SYSDATE, 'N', 10, 10);
INSERT INTO musica VALUES (59, 'Heartfelt Notes', 330, 4, NULL, NULL, 0, SYSDATE, 'N', 10, 10);
INSERT INTO musica VALUES (60, 'Final Blues', 340, 5, NULL, NULL, 0, SYSDATE, 'N', 10, 10);

-- Álbum 11: Pop Queens Vol.1
INSERT INTO musica VALUES (61, 'Pop Diva', 240, 1, NULL, NULL, 0, SYSDATE, 'N', 11, 2);
INSERT INTO musica VALUES (62, 'Queen of Pop', 260, 2, NULL, NULL, 0, SYSDATE, 'N', 11, 2);
INSERT INTO musica VALUES (63, 'Pop Revolution', 250, 3, NULL, NULL, 0, SYSDATE, 'N', 11, 2);
INSERT INTO musica VALUES (64, 'Dance Floor', 270, 4, NULL, NULL, 0, SYSDATE, 'N', 11, 2);
INSERT INTO musica VALUES (65, 'Pop Ballad', 300, 5, NULL, NULL, 0, SYSDATE, 'N', 11, 2);

-- Álbum 12: Latin Beats
INSERT INTO musica VALUES (66, 'Salsa Groove', 280, 1, NULL, NULL, 0, SYSDATE, 'N', 12, 7);
INSERT INTO musica VALUES (67, 'Latin Fire', 300, 2, NULL, NULL, 0, SYSDATE, 'N', 12, 7);
INSERT INTO musica VALUES (68, 'Tropical Nights', 290, 3, NULL, NULL, 0, SYSDATE, 'N', 12, 7);
INSERT INTO musica VALUES (69, 'Fiesta Beats', 310, 4, NULL, NULL, 0, SYSDATE, 'N', 12, 7);
INSERT INTO musica VALUES (70, 'Caribbean Vibes', 320, 5, NULL, NULL, 0, SYSDATE, 'N', 12, 7);

-- Álbum 13: Soulful Melodies
INSERT INTO musica VALUES (71, 'Soulful Journey', 340, 1, NULL, NULL, 0, SYSDATE, 'N', 13, 13);
INSERT INTO musica VALUES (72, 'Heart and Soul', 360, 2, NULL, NULL, 0, SYSDATE, 'N', 13, 13);
INSERT INTO musica VALUES (73, 'Melodic Bliss', 350, 3, NULL, NULL, 0, SYSDATE, 'N', 13, 13);
INSERT INTO musica VALUES (74, 'Soulful Harmony', 370, 4, NULL, NULL, 0, SYSDATE, 'N', 13, 13);
INSERT INTO musica VALUES (75, 'Final Melody', 380, 5, NULL, NULL, 0, SYSDATE, 'N', 13, 13);

-- Álbum 14: Indie Dreams
INSERT INTO musica VALUES (76, 'Indie Spirit', 300, 1, NULL, NULL, 0, SYSDATE, 'N', 14, 27);
INSERT INTO musica VALUES (77, 'Dreamy Tunes', 320, 2, NULL, NULL, 0, SYSDATE, 'N', 14, 27);
INSERT INTO musica VALUES (78, 'Alternative Vibes', 310, 3, NULL, NULL, 0, SYSDATE, 'N', 14, 27);
INSERT INTO musica VALUES (79, 'Indie Groove', 330, 4, NULL, NULL, 0, SYSDATE, 'N', 14, 27);
INSERT INTO musica VALUES (80, 'Final Indie', 340, 5, NULL, NULL, 0, SYSDATE, 'N', 14, 27);

-- Álbum 15: Electronic Masters
INSERT INTO musica VALUES (81, 'Synth Master', 400, 1, NULL, NULL, 0, SYSDATE, 'N', 15, 24);
INSERT INTO musica VALUES (82, 'Electronic Pulse', 420, 2, NULL, NULL, 0, SYSDATE, 'N', 15, 24);
INSERT INTO musica VALUES (83, 'Digital Dreams', 410, 3, NULL, NULL, 0, SYSDATE, 'N', 15, 24);
INSERT INTO musica VALUES (84, 'Future Sounds', 430, 4, NULL, NULL, 0, SYSDATE, 'N', 15, 24);
INSERT INTO musica VALUES (85, 'Electronic Finale', 440, 5, NULL, NULL, 0, SYSDATE, 'N', 15, 24);

-- Álbum 16: Folk Legends
INSERT INTO musica VALUES (86, 'Folk Ballad', 300, 1, NULL, NULL, 0, SYSDATE, 'N', 16, 9);
INSERT INTO musica VALUES (87, 'Irish Melody', 320, 2, NULL, NULL, 0, SYSDATE, 'N', 16, 9);
INSERT INTO musica VALUES (88, 'Acoustic Dreams', 310, 3, NULL, NULL, 0, SYSDATE, 'N', 16, 9);
INSERT INTO musica VALUES (89, 'Nature Song', 330, 4, NULL, NULL, 0, SYSDATE, 'N', 16, 9);
INSERT INTO musica VALUES (90, 'Final Folk', 340, 5, NULL, NULL, 0, SYSDATE, 'N', 16, 9);

-- Álbum 17: Afrobeat Kings
INSERT INTO musica VALUES (91, 'Afro Groove', 300, 1, NULL, NULL, 0, SYSDATE, 'N', 17, 29);
INSERT INTO musica VALUES (92, 'Rhythm of Africa', 320, 2, NULL, NULL, 0, SYSDATE, 'N', 17, 29);
INSERT INTO musica VALUES (93, 'Tribal Beats', 310, 3, NULL, NULL, 0, SYSDATE, 'N', 17, 29);
INSERT INTO musica VALUES (94, 'Cultural Vibes', 330, 4, NULL, NULL, 0, SYSDATE, 'N', 17, 29);
INSERT INTO musica VALUES (95, 'Final Afrobeat', 340, 5, NULL, NULL, 0, SYSDATE, 'N', 17, 29);

-- Álbum 18: Disco Fever
INSERT INTO musica VALUES (96, 'Disco Lights', 300, 1, NULL, NULL, 0, SYSDATE, 'N', 18, 20);
INSERT INTO musica VALUES (97, 'Dance Floor Fever', 320, 2, NULL, NULL, 0, SYSDATE, 'N', 18, 20);
INSERT INTO musica VALUES (98, 'Retro Beats', 310, 3, NULL, NULL, 0, SYSDATE, 'N', 18, 20);
INSERT INTO musica VALUES (99, 'Groovy Nights', 330, 4, NULL, NULL, 0, SYSDATE, 'N', 18, 20);
INSERT INTO musica VALUES (100, 'Final Disco', 340, 5, NULL, NULL, 0, SYSDATE, 'N', 18, 20);

-- Álbum 19: K-Pop Stars Vol.1
INSERT INTO musica VALUES (101, 'K-Pop Anthem', 240, 1, NULL, NULL, 0, SYSDATE, 'N', 19, 21);
INSERT INTO musica VALUES (102, 'Idol Dreams', 260, 2, NULL, NULL, 0, SYSDATE, 'N', 19, 21);
INSERT INTO musica VALUES (103, 'Fan Chant', 250, 3, NULL, NULL, 0, SYSDATE, 'N', 19, 21);
INSERT INTO musica VALUES (104, 'Stage Lights', 270, 4, NULL, NULL, 0, SYSDATE, 'N', 19, 21);
INSERT INTO musica VALUES (105, 'Final K-Pop', 300, 5, NULL, NULL, 0, SYSDATE, 'N', 19, 21);

-- Álbum 20: Trap Lords
INSERT INTO musica VALUES (106, 'Trap Beats', 250, 1, NULL, NULL, 0, SYSDATE, 'N', 20, 23);
INSERT INTO musica VALUES (107, 'Street Vibes', 260, 2, NULL, NULL, 0, SYSDATE, 'N', 20, 23);
INSERT INTO musica VALUES (108, 'Urban Trap', 270, 3, NULL, NULL, 0, SYSDATE, 'N', 20, 23);
INSERT INTO musica VALUES (109, 'Bass Heavy', 280, 4, NULL, NULL, 0, SYSDATE, 'N', 20, 23);
INSERT INTO musica VALUES (110, 'Final Trap', 290, 5, NULL, NULL, 0, SYSDATE, 'N', 20, 23);

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
INSERT INTO playlist VALUES (9, 'Pop Hits', 'Músicas pop mais tocadas', 'S', SYSDATE, SYSTIMESTAMP, 0, 0, 9);
INSERT INTO playlist VALUES (10, 'Blues Classics', 'Clássicos do blues', 'N', SYSDATE, SYSTIMESTAMP, 0, 0, 10);
INSERT INTO playlist VALUES (11, 'Country Roads', 'Músicas country para viagens', 'S', SYSDATE, SYSTIMESTAMP, 0, 0, 11);
INSERT INTO playlist VALUES (12, 'Eletrônica Top', 'Batidas eletrônicas mais tocadas', 'N', SYSDATE, SYSTIMESTAMP, 0, 0, 12);
INSERT INTO playlist VALUES (13, 'Samba Alegria', 'Ritmos brasileiros animados', 'S', SYSDATE, SYSTIMESTAMP, 0, 0, 13);
INSERT INTO playlist VALUES (14, 'Forró Dançante', 'Músicas para dançar forró', 'N', SYSDATE, SYSTIMESTAMP, 0, 0, 14);
INSERT INTO playlist VALUES (15, 'Indie Dreams', 'Músicas indie para relaxar', 'S', SYSDATE, SYSTIMESTAMP, 0, 0, 15);
INSERT INTO playlist VALUES (16, 'Rap Nacional', 'Melhores raps brasileiros', 'N', SYSDATE, SYSTIMESTAMP, 0, 0, 16);
INSERT INTO playlist VALUES (17, 'Funk Hits', 'Batidas dançantes do funk', 'S', SYSDATE, SYSTIMESTAMP, 0, 0, 17);
INSERT INTO playlist VALUES (18, 'MPB Clássica', 'Música Popular Brasileira', 'N', SYSDATE, SYSTIMESTAMP, 0, 0, 18);
INSERT INTO playlist VALUES (19, 'Bossa Nova', 'Ritmos suaves e sofisticados', 'S', SYSDATE, SYSTIMESTAMP, 0, 0, 19);
INSERT INTO playlist VALUES (20, 'Gospel Louvor', 'Músicas religiosas inspiradoras', 'N', SYSDATE, SYSTIMESTAMP, 0, 0, 20);
INSERT INTO playlist VALUES (21, 'K-Pop Fever', 'Músicas pop coreanas', 'S', SYSDATE, SYSTIMESTAMP, 0, 0, 21);
INSERT INTO playlist VALUES (22, 'Trap Beats', 'Batidas eletrônicas do trap', 'N', SYSDATE, SYSTIMESTAMP, 0, 0, 22);
INSERT INTO playlist VALUES (23, 'Techno Party', 'Músicas techno para festas', 'S', SYSDATE, SYSTIMESTAMP, 0, 0, 23);
INSERT INTO playlist VALUES (24, 'Dubstep Drops', 'Batidas intensas do dubstep', 'N', SYSDATE, SYSTIMESTAMP, 0, 0, 24);
INSERT INTO playlist VALUES (25, 'Afrobeat Energy', 'Ritmos africanos animados', 'S', SYSDATE, SYSTIMESTAMP, 0, 0, 25);
INSERT INTO playlist VALUES (26, 'Disco Fever', 'Músicas dançantes retrô', 'N', SYSDATE, SYSTIMESTAMP, 0, 0, 26);
INSERT INTO playlist VALUES (27, 'Alternativo Cool', 'Músicas fora do mainstream', 'S', SYSDATE, SYSTIMESTAMP, 0, 0, 27);
INSERT INTO playlist VALUES (28, 'Jazz Classics', 'Clássicos do jazz', 'N', SYSDATE, SYSTIMESTAMP, 0, 0, 28);
INSERT INTO playlist VALUES (29, 'Rock Revival', 'Revival do rock clássico', 'S', SYSDATE, SYSTIMESTAMP, 0, 0, 29);
INSERT INTO playlist VALUES (30, 'Chill Vibes', 'Músicas para relaxar', 'N', SYSDATE, SYSTIMESTAMP, 0, 0, 30);

-------------------------
-- PLAYLIST_MUSICA
-------------------------
-- Adicionando 30 músicas na playlist com ID 1
INSERT INTO playlist_musica VALUES (1, 1, 1);
INSERT INTO playlist_musica VALUES (1, 2, 2);
INSERT INTO playlist_musica VALUES (1, 3, 3);
INSERT INTO playlist_musica VALUES (1, 4, 4);
INSERT INTO playlist_musica VALUES (1, 5, 5);
INSERT INTO playlist_musica VALUES (1, 6, 6);
INSERT INTO playlist_musica VALUES (1, 7, 7);
INSERT INTO playlist_musica VALUES (1, 8, 8);
INSERT INTO playlist_musica VALUES (1, 9, 9);
INSERT INTO playlist_musica VALUES (1, 10, 10);
INSERT INTO playlist_musica VALUES (1, 11, 11);
INSERT INTO playlist_musica VALUES (1, 12, 12);
INSERT INTO playlist_musica VALUES (1, 13, 13);
INSERT INTO playlist_musica VALUES (1, 14, 14);
INSERT INTO playlist_musica VALUES (1, 15, 15);
INSERT INTO playlist_musica VALUES (1, 16, 16);
INSERT INTO playlist_musica VALUES (1, 17, 17);
INSERT INTO playlist_musica VALUES (1, 18, 18);
INSERT INTO playlist_musica VALUES (1, 19, 19);
INSERT INTO playlist_musica VALUES (1, 20, 20);
INSERT INTO playlist_musica VALUES (1, 21, 21);
INSERT INTO playlist_musica VALUES (1, 22, 22);
INSERT INTO playlist_musica VALUES (1, 23, 23);
INSERT INTO playlist_musica VALUES (1, 24, 24);
INSERT INTO playlist_musica VALUES (1, 25, 25);
INSERT INTO playlist_musica VALUES (1, 26, 26);
INSERT INTO playlist_musica VALUES (1, 27, 27);
INSERT INTO playlist_musica VALUES (1, 28, 28);
INSERT INTO playlist_musica VALUES (1, 29, 29);
INSERT INTO playlist_musica VALUES (1, 30, 30);
-- Adicionando músicas nas outras playlists
INSERT INTO playlist_musica VALUES (2, 13, 3);
INSERT INTO playlist_musica VALUES (2, 14, 4);
INSERT INTO playlist_musica VALUES (2, 15, 5);
INSERT INTO playlist_musica VALUES (3, 8, 3);
INSERT INTO playlist_musica VALUES (3, 9, 4);
INSERT INTO playlist_musica VALUES (3, 10, 5);
INSERT INTO playlist_musica VALUES (4, 23, 3);
INSERT INTO playlist_musica VALUES (4, 24, 4);
INSERT INTO playlist_musica VALUES (4, 25, 5);
INSERT INTO playlist_musica VALUES (5, 17, 3);
INSERT INTO playlist_musica VALUES (5, 19, 4);
INSERT INTO playlist_musica VALUES (5, 20, 5);
INSERT INTO playlist_musica VALUES (6, 36, 1);
INSERT INTO playlist_musica VALUES (6, 37, 2);
INSERT INTO playlist_musica VALUES (6, 38, 3);
INSERT INTO playlist_musica VALUES (6, 39, 4);
INSERT INTO playlist_musica VALUES (6, 40, 5);
INSERT INTO playlist_musica VALUES (7, 41, 1);
INSERT INTO playlist_musica VALUES (7, 42, 2);
INSERT INTO playlist_musica VALUES (7, 43, 3);
INSERT INTO playlist_musica VALUES (7, 44, 4);
INSERT INTO playlist_musica VALUES (7, 45, 5);
INSERT INTO playlist_musica VALUES (8, 46, 1);
INSERT INTO playlist_musica VALUES (8, 47, 2);
INSERT INTO playlist_musica VALUES (8, 48, 3);
INSERT INTO playlist_musica VALUES (8, 49, 4);
INSERT INTO playlist_musica VALUES (8, 50, 5);
INSERT INTO playlist_musica VALUES (9, 61, 1);
INSERT INTO playlist_musica VALUES (9, 62, 2);
INSERT INTO playlist_musica VALUES (9, 63, 3);
INSERT INTO playlist_musica VALUES (9, 64, 4);
INSERT INTO playlist_musica VALUES (9, 65, 5);
INSERT INTO playlist_musica VALUES (10, 56, 1);
INSERT INTO playlist_musica VALUES (10, 57, 2);
INSERT INTO playlist_musica VALUES (10, 58, 3);
INSERT INTO playlist_musica VALUES (10, 59, 4);
INSERT INTO playlist_musica VALUES (10, 60, 5);
INSERT INTO playlist_musica VALUES (11, 51, 1);
INSERT INTO playlist_musica VALUES (11, 52, 2);
INSERT INTO playlist_musica VALUES (11, 53, 3);
INSERT INTO playlist_musica VALUES (11, 54, 4);
INSERT INTO playlist_musica VALUES (11, 55, 5);
INSERT INTO playlist_musica VALUES (12, 16, 1);
INSERT INTO playlist_musica VALUES (12, 17, 2);
INSERT INTO playlist_musica VALUES (12, 18, 3);
INSERT INTO playlist_musica VALUES (12, 19, 4);
INSERT INTO playlist_musica VALUES (12, 20, 5);
INSERT INTO playlist_musica VALUES (13, 29, 1);
INSERT INTO playlist_musica VALUES (13, 28, 2);
INSERT INTO playlist_musica VALUES (13, 27, 3);
INSERT INTO playlist_musica VALUES (13, 26, 4);
INSERT INTO playlist_musica VALUES (13, 25, 5);
INSERT INTO playlist_musica VALUES (14, 91, 1);
INSERT INTO playlist_musica VALUES (14, 92, 2);
INSERT INTO playlist_musica VALUES (14, 93, 3);
INSERT INTO playlist_musica VALUES (14, 94, 4);
INSERT INTO playlist_musica VALUES (14, 95, 5);

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
INSERT INTO tipo_assinatura VALUES (6, 'Essencial', 19.90, 'Média', 'N', 'S', 'N', 'Plano básico com anúncios limitados');
INSERT INTO tipo_assinatura VALUES (7, 'Kids', 9.90, 'Média', 'N', 'N', 'S', 'Plano para crianças com conteúdo filtrado');
INSERT INTO tipo_assinatura VALUES (8, 'Empresarial', 99.90, 'Alta', 'S', 'S', 'S', 'Plano para empresas com múltiplas contas');
INSERT INTO tipo_assinatura VALUES (9, 'Anual Premium', 299.90, 'Alta', 'S', 'S', 'S', 'Plano premium com pagamento anual');
INSERT INTO tipo_assinatura VALUES (10, 'Anual Família', 499.90, 'Alta', 'S', 'S', 'S', 'Plano familiar com pagamento anual');
INSERT INTO tipo_assinatura VALUES (11, 'Trial', 0, 'Média', 'N', 'N', 'N', 'Plano de teste gratuito por 30 dias');
INSERT INTO tipo_assinatura VALUES (12, 'Estudante Plus', 19.90, 'Alta', 'S', 'S', 'S', 'Plano para estudantes com mais benefícios');
INSERT INTO tipo_assinatura VALUES (13, 'HiFi Plus', 49.90, 'Lossless', 'S', 'S', 'S', 'Plano HiFi com mais recursos');
INSERT INTO tipo_assinatura VALUES (14, 'Clássico', 24.90, 'Alta', 'N', 'S', 'N', 'Plano para amantes de música clássica');
INSERT INTO tipo_assinatura VALUES (15, 'Podcast', 9.90, 'Média', 'N', 'N', 'N', 'Plano focado em podcasts');
INSERT INTO tipo_assinatura VALUES (16, 'Fitness', 19.90, 'Alta', 'N', 'S', 'N', 'Plano para treinos e exercícios');
INSERT INTO tipo_assinatura VALUES (17, 'Viagem', 14.90, 'Média', 'S', 'N', 'N', 'Plano para viagens com playlists offline');
INSERT INTO tipo_assinatura VALUES (18, 'Cinema', 29.90, 'Alta', 'S', 'N', 'S', 'Plano com trilhas sonoras de filmes');
INSERT INTO tipo_assinatura VALUES (19, 'Relax', 12.90, 'Média', 'N', 'N', 'N', 'Plano com músicas relaxantes');
INSERT INTO tipo_assinatura VALUES (20, 'Gaming', 19.90, 'Alta', 'N', 'S', 'N', 'Plano para gamers com trilhas sonoras');
INSERT INTO tipo_assinatura VALUES (21, 'Karaokê', 14.90, 'Alta', 'N', 'N', 'N', 'Plano com letras de músicas para karaokê');
INSERT INTO tipo_assinatura VALUES (22, 'Estúdio', 59.90, 'Lossless', 'S', 'S', 'S', 'Plano para produtores musicais');
INSERT INTO tipo_assinatura VALUES (23, 'VIP', 99.90, 'Lossless', 'S', 'S', 'S', 'Plano exclusivo com benefícios adicionais');
INSERT INTO tipo_assinatura VALUES (24, 'Eventos', 49.90, 'Alta', 'S', 'N', 'S', 'Plano para eventos e festas');
INSERT INTO tipo_assinatura VALUES (25, 'Audiobook', 19.90, 'Média', 'N', 'N', 'N', 'Plano focado em audiolivros');
INSERT INTO tipo_assinatura VALUES (26, 'Estudante Básico', 9.90, 'Média', 'N', 'N', 'N', 'Plano básico para estudantes');
INSERT INTO tipo_assinatura VALUES (27, 'HiFi Família', 59.90, 'Lossless', 'S', 'S', 'S', 'Plano HiFi para famílias');
INSERT INTO tipo_assinatura VALUES (28, 'Premium Duo', 39.90, 'Alta', 'S', 'S', 'S', 'Plano para duas pessoas');
INSERT INTO tipo_assinatura VALUES (29, 'Eco', 4.90, 'Baixa', 'N', 'N', 'N', 'Plano econômico com anúncios');
INSERT INTO tipo_assinatura VALUES (30, 'Platinum', 199.90, 'Lossless', 'S', 'S', 'S', 'Plano premium com benefícios exclusivos');

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

INSERT INTO assinatura (id_assinatura, data_inicio, metodo_pagamento, valor_pago, id_usuario, id_tipo_assinatura) 
VALUES (6, TO_DATE('2024-06-01','YYYY-MM-DD'), 'Boleto', 19.90, 6, 6);
INSERT INTO assinatura (id_assinatura, data_inicio, metodo_pagamento, valor_pago, id_usuario, id_tipo_assinatura) 
VALUES (7, TO_DATE('2024-07-10','YYYY-MM-DD'), 'Cartão', 9.90, 7, 7);
INSERT INTO assinatura (id_assinatura, data_inicio, metodo_pagamento, valor_pago, id_usuario, id_tipo_assinatura) 
VALUES (8, TO_DATE('2024-08-15','YYYY-MM-DD'), 'Pix', 99.90, 8, 8);
INSERT INTO assinatura (id_assinatura, data_inicio, metodo_pagamento, valor_pago, id_usuario, id_tipo_assinatura) 
VALUES (9, TO_DATE('2024-09-01','YYYY-MM-DD'), 'Cartão', 299.90, 9, 9);
INSERT INTO assinatura (id_assinatura, data_inicio, metodo_pagamento, valor_pago, id_usuario, id_tipo_assinatura) 
VALUES (10, TO_DATE('2024-10-01','YYYY-MM-DD'), 'Paypal', 49.90, 10, 10);

INSERT INTO assinatura (id_assinatura, data_inicio, metodo_pagamento, valor_pago, id_usuario, id_tipo_assinatura) 
VALUES (11, TO_DATE('2024-11-01','YYYY-MM-DD'), 'Cartão', 0, 11, 11);
INSERT INTO assinatura (id_assinatura, data_inicio, metodo_pagamento, valor_pago, id_usuario, id_tipo_assinatura) 
VALUES (12, TO_DATE('2024-12-01','YYYY-MM-DD'), 'Pix', 19.90, 12, 12);
INSERT INTO assinatura (id_assinatura, data_inicio, metodo_pagamento, valor_pago, id_usuario, id_tipo_assinatura) 
VALUES (13, TO_DATE('2024-01-15','YYYY-MM-DD'), 'Boleto', 49.90, 13, 13);
INSERT INTO assinatura (id_assinatura, data_inicio, metodo_pagamento, valor_pago, id_usuario, id_tipo_assinatura) 
VALUES (14, TO_DATE('2024-02-20','YYYY-MM-DD'), 'Cartão', 24.90, 14, 14);
INSERT INTO assinatura (id_assinatura, data_inicio, metodo_pagamento, valor_pago, id_usuario, id_tipo_assinatura) 
VALUES (15, TO_DATE('2024-03-10','YYYY-MM-DD'), 'Paypal', 9.90, 15, 15);

INSERT INTO assinatura (id_assinatura, data_inicio, metodo_pagamento, valor_pago, id_usuario, id_tipo_assinatura) 
VALUES (16, TO_DATE('2024-04-05','YYYY-MM-DD'), 'Cartão', 19.90, 16, 16);
INSERT INTO assinatura (id_assinatura, data_inicio, metodo_pagamento, valor_pago, id_usuario, id_tipo_assinatura) 
VALUES (17, TO_DATE('2024-05-15','YYYY-MM-DD'), 'Pix', 14.90, 17, 17);
INSERT INTO assinatura (id_assinatura, data_inicio, metodo_pagamento, valor_pago, id_usuario, id_tipo_assinatura) 
VALUES (18, TO_DATE('2024-06-20','YYYY-MM-DD'), 'Boleto', 29.90, 18, 18);
INSERT INTO assinatura (id_assinatura, data_inicio, metodo_pagamento, valor_pago, id_usuario, id_tipo_assinatura) 
VALUES (19, TO_DATE('2024-07-25','YYYY-MM-DD'), 'Cartão', 12.90, 19, 19);
INSERT INTO assinatura (id_assinatura, data_inicio, metodo_pagamento, valor_pago, id_usuario, id_tipo_assinatura) 
VALUES (20, TO_DATE('2024-08-30','YYYY-MM-DD'), 'Paypal', 19.90, 20, 20);

INSERT INTO assinatura (id_assinatura, data_inicio, metodo_pagamento, valor_pago, id_usuario, id_tipo_assinatura) 
VALUES (21, TO_DATE('2024-09-10','YYYY-MM-DD'), 'Cartão', 14.90, 21, 21);
INSERT INTO assinatura (id_assinatura, data_inicio, metodo_pagamento, valor_pago, id_usuario, id_tipo_assinatura) 
VALUES (22, TO_DATE('2024-10-15','YYYY-MM-DD'), 'Pix', 59.90, 22, 22);
INSERT INTO assinatura (id_assinatura, data_inicio, metodo_pagamento, valor_pago, id_usuario, id_tipo_assinatura) 
VALUES (23, TO_DATE('2024-11-20','YYYY-MM-DD'), 'Boleto', 99.90, 23, 23);
INSERT INTO assinatura (id_assinatura, data_inicio, metodo_pagamento, valor_pago, id_usuario, id_tipo_assinatura) 
VALUES (24, TO_DATE('2024-12-25','YYYY-MM-DD'), 'Cartão', 49.90, 24, 24);
INSERT INTO assinatura (id_assinatura, data_inicio, metodo_pagamento, valor_pago, id_usuario, id_tipo_assinatura) 
VALUES (25, TO_DATE('2025-01-01','YYYY-MM-DD'), 'Paypal', 19.90, 25, 25);

INSERT INTO assinatura (id_assinatura, data_inicio, metodo_pagamento, valor_pago, id_usuario, id_tipo_assinatura) 
VALUES (26, TO_DATE('2025-02-01','YYYY-MM-DD'), 'Cartão', 9.90, 26, 26);
INSERT INTO assinatura (id_assinatura, data_inicio, metodo_pagamento, valor_pago, id_usuario, id_tipo_assinatura) 
VALUES (27, TO_DATE('2025-03-01','YYYY-MM-DD'), 'Pix', 59.90, 27, 27);
INSERT INTO assinatura (id_assinatura, data_inicio, metodo_pagamento, valor_pago, id_usuario, id_tipo_assinatura) 
VALUES (28, TO_DATE('2025-04-01','YYYY-MM-DD'), 'Boleto', 39.90, 28, 28);
INSERT INTO assinatura (id_assinatura, data_inicio, metodo_pagamento, valor_pago, id_usuario, id_tipo_assinatura) 
VALUES (29, TO_DATE('2025-05-01','YYYY-MM-DD'), 'Cartão', 4.90, 29, 29);
INSERT INTO assinatura (id_assinatura, data_inicio, metodo_pagamento, valor_pago, id_usuario, id_tipo_assinatura) 
VALUES (30, TO_DATE('2025-06-01','YYYY-MM-DD'), 'Paypal', 199.90, 30, 30);

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