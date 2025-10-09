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