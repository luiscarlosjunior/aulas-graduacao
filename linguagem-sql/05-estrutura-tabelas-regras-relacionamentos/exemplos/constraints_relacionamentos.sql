-- =====================================================
-- ESTRUTURA DE TABELAS, REGRAS E RELACIONAMENTOS
-- Módulo 05: Constraints e Relacionamentos - Sistema MusiStream
-- =====================================================

-- =====================================================
-- 1. CRIAÇÃO DE TABELAS COM CONSTRAINTS BÁSICAS
-- =====================================================

-- Tabela de Gêneros Musicais
CREATE TABLE genero (
    id_genero INTEGER CONSTRAINT pk_genero PRIMARY KEY,
    nome_genero VARCHAR2(50) CONSTRAINT nn_genero_nome NOT NULL 
                              CONSTRAINT uq_genero_nome UNIQUE,
    descricao VARCHAR2(200),
    data_criacao DATE DEFAULT SYSDATE
);

-- Inserindo gêneros musicais
INSERT INTO genero (id_genero, nome_genero, descricao) VALUES 
(1, 'Rock', 'Gênero musical caracterizado por guitarra elétrica, baixo e bateria'),
(2, 'Pop', 'Música popular contemporânea com foco em melodias cativantes'),
(3, 'Jazz', 'Gênero musical com ênfase em improvisação e harmonias complexas'),
(4, 'MPB', 'Música Popular Brasileira, fusão de diversos estilos nacionais'),
(5, 'Eletrônica', 'Música produzida principalmente com instrumentos eletrônicos'),
(6, 'Hip Hop', 'Gênero caracterizado por rap, beats e cultura urbana');

-- =====================================================
-- 2. TABELA DE ARTISTAS COM CONSTRAINTS AVANÇADAS
-- =====================================================

CREATE TABLE artista (
    id_artista INTEGER CONSTRAINT pk_artista PRIMARY KEY,
    nome_artista VARCHAR2(100) CONSTRAINT nn_artista_nome NOT NULL,
    nome_artistico VARCHAR2(100),
    biografia CLOB,
    data_formacao DATE,
    pais_origem VARCHAR2(50) DEFAULT 'Brasil',
    ativo CHAR(1) DEFAULT 'S' CONSTRAINT ck_artista_ativo CHECK (ativo IN ('S', 'N')),
    numero_membros INTEGER CONSTRAINT ck_numero_membros CHECK (numero_membros > 0),
    email VARCHAR2(150) CONSTRAINT uq_artista_email UNIQUE,
    website VARCHAR2(200),
    data_cadastro DATE DEFAULT SYSDATE
);

-- Inserindo artistas com diferentes constraints
INSERT INTO artista (id_artista, nome_artista, biografia, data_formacao, pais_origem, numero_membros, email) VALUES 
(1, 'The Beatles', 'Banda britânica que revolucionou a música popular mundial', 
 DATE '1960-08-17', 'Reino Unido', 4, 'contact@thebeatles.com');

INSERT INTO artista (id_artista, nome_artista, biografia, data_formacao, pais_origem, numero_membros) VALUES 
(2, 'Caetano Veloso', 'Cantor, compositor e escritor brasileiro, ícone da MPB', 
 DATE '1965-01-01', 'Brasil', 1);

-- =====================================================
-- 3. TABELA DE ÁLBUNS COM RELACIONAMENTOS
-- =====================================================

CREATE TABLE album (
    id_album INTEGER CONSTRAINT pk_album PRIMARY KEY,
    titulo VARCHAR2(200) CONSTRAINT nn_album_titulo NOT NULL,
    ano_lancamento INTEGER CONSTRAINT ck_ano_lancamento 
                           CHECK (ano_lancamento BETWEEN 1900 AND EXTRACT(YEAR FROM SYSDATE)),
    numero_faixas INTEGER CONSTRAINT ck_numero_faixas CHECK (numero_faixas > 0 AND numero_faixas <= 100),
    duracao_total INTEGER, -- em segundos
    capa_album VARCHAR2(300), -- URL da imagem
    preco NUMBER(8,2) CONSTRAINT ck_preco_album CHECK (preco >= 0),
    disponivel CHAR(1) DEFAULT 'S' CONSTRAINT ck_album_disponivel CHECK (disponivel IN ('S', 'N')),
    id_artista INTEGER CONSTRAINT nn_album_artista NOT NULL,
    id_genero INTEGER,
    data_cadastro DATE DEFAULT SYSDATE,
    
    -- Constraints de integridade referencial
    CONSTRAINT fk_album_artista FOREIGN KEY (id_artista) REFERENCES artista(id_artista),
    CONSTRAINT fk_album_genero FOREIGN KEY (id_genero) REFERENCES genero(id_genero)
);

-- Inserindo álbuns
INSERT INTO album (id_album, titulo, ano_lancamento, numero_faixas, duracao_total, id_artista, id_genero, preco) VALUES 
(1, 'Abbey Road', 1969, 17, 2547, 1, 1, 29.90),
(2, 'Tropicália', 1968, 12, 2100, 2, 4, 24.90);

-- =====================================================
-- 4. TABELA DE USUÁRIOS COM MÚLTIPLAS CONSTRAINTS
-- =====================================================

CREATE TABLE usuario (
    id_usuario INTEGER CONSTRAINT pk_usuario PRIMARY KEY,
    nome_usuario VARCHAR2(100) CONSTRAINT nn_usuario_nome NOT NULL,
    sobrenome VARCHAR2(100),
    email VARCHAR2(150) CONSTRAINT nn_usuario_email NOT NULL 
                        CONSTRAINT uq_usuario_email UNIQUE,
    senha VARCHAR2(128) CONSTRAINT nn_usuario_senha NOT NULL,
    data_nascimento DATE,
    data_cadastro DATE DEFAULT SYSDATE,
    ativo CHAR(1) DEFAULT 'S' CONSTRAINT ck_usuario_ativo CHECK (ativo IN ('S', 'N')),
    pais VARCHAR2(50) DEFAULT 'Brasil',
    cidade VARCHAR2(100),
    genero CHAR(1) CONSTRAINT ck_usuario_genero CHECK (genero IN ('M', 'F', 'O')), -- M=Masculino, F=Feminino, O=Outro
    telefone VARCHAR2(20),
    data_ultimo_acesso DATE,
    
    -- Constraint para validar idade mínima (13 anos)
    CONSTRAINT ck_idade_minima CHECK (data_nascimento <= SYSDATE - INTERVAL '13' YEAR)
);

-- =====================================================
-- 5. TABELA DE PLAYLISTS
-- =====================================================

CREATE TABLE playlist (
    id_playlist INTEGER CONSTRAINT pk_playlist PRIMARY KEY,
    nome_playlist VARCHAR2(100) CONSTRAINT nn_playlist_nome NOT NULL,
    descricao VARCHAR2(500),
    publica CHAR(1) DEFAULT 'N' CONSTRAINT ck_playlist_publica CHECK (publica IN ('S', 'N')),
    data_criacao DATE DEFAULT SYSDATE,
    data_modificacao DATE DEFAULT SYSDATE,
    numero_musicas INTEGER DEFAULT 0 CONSTRAINT ck_numero_musicas CHECK (numero_musicas >= 0),
    duracao_total INTEGER DEFAULT 0, -- em segundos
    id_usuario INTEGER CONSTRAINT nn_playlist_usuario NOT NULL,
    
    CONSTRAINT fk_playlist_usuario FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario)
);

-- =====================================================
-- 6. TABELA DE MÚSICAS
-- =====================================================

CREATE TABLE musica (
    id_musica INTEGER CONSTRAINT pk_musica PRIMARY KEY,
    titulo VARCHAR2(200) CONSTRAINT nn_musica_titulo NOT NULL,
    duracao INTEGER CONSTRAINT ck_duracao_musica CHECK (duracao > 0), -- em segundos
    numero_faixa INTEGER CONSTRAINT ck_numero_faixa CHECK (numero_faixa > 0),
    letra CLOB,
    arquivo_audio VARCHAR2(300), -- caminho do arquivo
    tamanho_arquivo INTEGER, -- em bytes
    bitrate INTEGER CONSTRAINT ck_bitrate CHECK (bitrate IN (128, 192, 256, 320)), -- kbps
    data_gravacao DATE,
    compositor VARCHAR2(200),
    produtor VARCHAR2(200),
    classificacao_etaria INTEGER DEFAULT 0 CONSTRAINT ck_classificacao 
                                CHECK (classificacao_etaria IN (0, 10, 12, 14, 16, 18)),
    id_album INTEGER,
    id_genero INTEGER,
    data_cadastro DATE DEFAULT SYSDATE,
    
    CONSTRAINT fk_musica_album FOREIGN KEY (id_album) REFERENCES album(id_album),
    CONSTRAINT fk_musica_genero FOREIGN KEY (id_genero) REFERENCES genero(id_genero)
);

-- =====================================================
-- 7. TABELA DE RELACIONAMENTO N:N (PLAYLIST-MÚSICA)
-- =====================================================

CREATE TABLE playlist_musica (
    id_playlist INTEGER,
    id_musica INTEGER,
    data_adicao DATE DEFAULT SYSDATE,
    ordem_reproducao INTEGER CONSTRAINT ck_ordem_reproducao CHECK (ordem_reproducao > 0),
    favorita CHAR(1) DEFAULT 'N' CONSTRAINT ck_favorita CHECK (favorita IN ('S', 'N')),
    numero_reproducoes INTEGER DEFAULT 0 CONSTRAINT ck_num_reproducoes CHECK (numero_reproducoes >= 0),
    
    -- Chave primária composta
    CONSTRAINT pk_playlist_musica PRIMARY KEY (id_playlist, id_musica),
    
    -- Chaves estrangeiras
    CONSTRAINT fk_pm_playlist FOREIGN KEY (id_playlist) REFERENCES playlist(id_playlist),
    CONSTRAINT fk_pm_musica FOREIGN KEY (id_musica) REFERENCES musica(id_musica)
);

-- =====================================================
-- 8. CRIAÇÃO DE ÍNDICES PARA PERFORMANCE
-- =====================================================

-- Índices para otimização de consultas frequentes
CREATE INDEX idx_artista_nome ON artista(nome_artista);
CREATE INDEX idx_usuario_email ON usuario(email);
CREATE INDEX idx_album_artista ON album(id_artista);
CREATE INDEX idx_album_ano ON album(ano_lancamento);
CREATE INDEX idx_musica_titulo ON musica(titulo);
CREATE INDEX idx_musica_album ON musica(id_album);
CREATE INDEX idx_playlist_usuario ON playlist(id_usuario);

-- Índice composto para consultas específicas
CREATE INDEX idx_album_artista_ano ON album(id_artista, ano_lancamento);
CREATE INDEX idx_musica_album_faixa ON musica(id_album, numero_faixa);

-- =====================================================
-- 9. EXEMPLOS DE INSERÇÃO RESPEITANDO CONSTRAINTS
-- =====================================================

-- Inserindo usuários
INSERT INTO usuario (id_usuario, nome_usuario, sobrenome, email, senha, data_nascimento, pais, genero) VALUES 
(1, 'João', 'Silva', 'joao.silva@email.com', 'senha123hash', DATE '1990-05-15', 'Brasil', 'M'),
(2, 'Maria', 'Santos', 'maria.santos@email.com', 'senha456hash', DATE '1985-12-20', 'Brasil', 'F'),
(3, 'Pedro', 'Oliveira', 'pedro.oliveira@email.com', 'senha789hash', DATE '1992-07-08', 'Portugal', 'M');

-- Inserindo playlists
INSERT INTO playlist (id_playlist, nome_playlist, descricao, publica, id_usuario) VALUES 
(1, 'Meus Clássicos', 'Música clássica de rock dos anos 60-70', 'S', 1),
(2, 'MPB Favoritas', 'Melhores músicas da MPB', 'N', 2),
(3, 'Workout Mix', 'Músicas para treinar', 'S', 3);

-- Inserindo músicas
INSERT INTO musica (id_musica, titulo, duracao, numero_faixa, id_album, id_genero, compositor) VALUES 
(1, 'Come Together', 259, 1, 1, 1, 'Lennon-McCartney'),
(2, 'Something', 182, 2, 1, 1, 'George Harrison'),
(3, 'Tropicália', 205, 1, 2, 4, 'Caetano Veloso');

-- Relacionando músicas com playlists
INSERT INTO playlist_musica (id_playlist, id_musica, ordem_reproducao) VALUES 
(1, 1, 1),
(1, 2, 2),
(2, 3, 1);

-- =====================================================
-- 10. VERIFICAÇÃO DE CONSTRAINTS
-- =====================================================

-- Exemplos que irão falhar devido às constraints:

-- Tentativa de inserir artista com número de membros inválido
/*
INSERT INTO artista (id_artista, nome_artista, numero_membros) 
VALUES (99, 'Artista Teste', -1); -- Falha: numero_membros deve ser > 0
*/

-- Tentativa de inserir álbum com ano inválido
/*
INSERT INTO album (id_album, titulo, ano_lancamento, id_artista) 
VALUES (99, 'Álbum Futuro', 2050, 1); -- Falha: ano deve ser <= ano atual
*/

-- Tentativa de inserir usuário menor de 13 anos
/*
INSERT INTO usuario (id_usuario, nome_usuario, email, senha, data_nascimento) 
VALUES (99, 'Criança', 'crianca@email.com', 'senha', SYSDATE); -- Falha: idade mínima
*/

-- =====================================================
-- 11. CONSULTAS PARA VERIFICAR INTEGRIDADE
-- =====================================================

-- Verificar todos os artistas e seus álbuns
SELECT a.nome_artista, al.titulo, al.ano_lancamento
FROM artista a
LEFT JOIN album al ON a.id_artista = al.id_artista
ORDER BY a.nome_artista, al.ano_lancamento;

-- Verificar usuários e suas playlists
SELECT u.nome_usuario, p.nome_playlist, p.numero_musicas
FROM usuario u
LEFT JOIN playlist p ON u.id_usuario = p.id_usuario
ORDER BY u.nome_usuario;

-- Verificar músicas por gênero
SELECT g.nome_genero, COUNT(m.id_musica) as total_musicas
FROM genero g
LEFT JOIN musica m ON g.id_genero = m.id_genero
GROUP BY g.nome_genero
ORDER BY total_musicas DESC;

COMMIT;