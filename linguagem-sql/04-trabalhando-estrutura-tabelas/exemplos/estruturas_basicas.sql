-- ===============================================
-- Estruturas Básicas - Sistema MusiStream
-- Módulo 04: Trabalhando com a Estrutura de Tabelas
-- ===============================================

-- Limpar estruturas anteriores se existirem
DROP TABLE IF EXISTS playlist_musica CASCADE;
DROP TABLE IF EXISTS playlist CASCADE;
DROP TABLE IF EXISTS musica CASCADE;
DROP TABLE IF EXISTS album CASCADE;
DROP TABLE IF EXISTS artista CASCADE;
DROP TABLE IF EXISTS genero_musical CASCADE;
DROP TABLE IF EXISTS usuario CASCADE;
DROP TABLE IF EXISTS configuracao_usuario CASCADE;

-- ===============================================
-- 1. ESTRUTURA BÁSICA - USUÁRIOS
-- ===============================================

CREATE TABLE usuario (
    id_usuario       INTEGER PRIMARY KEY,
    nome_usuario     VARCHAR(100) NOT NULL,
    sobrenome        VARCHAR(100),
    nome_exibicao    VARCHAR(150),
    email            VARCHAR(254) NOT NULL UNIQUE,
    senha_hash       VARCHAR(255) NOT NULL,
    data_nascimento  DATE,
    genero           CHAR(1),
    pais             VARCHAR(50),
    idioma_pref      VARCHAR(10) DEFAULT 'pt-BR',
    data_cadastro    TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    ultima_atividade TIMESTAMP,
    ativo            BOOLEAN DEFAULT TRUE,
    email_verificado BOOLEAN DEFAULT FALSE,
    
    -- Validações de domínio
    CHECK (genero IN ('M', 'F', 'O', 'N')),
    CHECK (LENGTH(nome_usuario) >= 2),
    CHECK (email LIKE '%@%.%')
);

-- Comentários na tabela
COMMENT ON TABLE usuario IS 'Usuários registrados na plataforma MusiStream';
COMMENT ON COLUMN usuario.nome_exibicao IS 'Nome público mostrado para outros usuários';
COMMENT ON COLUMN usuario.genero IS 'M=Masculino, F=Feminino, O=Outro, N=Não informado';
COMMENT ON COLUMN usuario.senha_hash IS 'Hash da senha usando algoritmo seguro (bcrypt/scrypt)';

-- ===============================================
-- 2. CONFIGURAÇÕES DE USUÁRIO
-- ===============================================

CREATE TABLE configuracao_usuario (
    id_usuario        INTEGER PRIMARY KEY,
    tema              VARCHAR(20) DEFAULT 'escuro',
    qualidade_audio   VARCHAR(20) DEFAULT 'alta',
    volume_padrao     INTEGER DEFAULT 75,
    reproducao_auto   BOOLEAN DEFAULT TRUE,
    notificacoes      BOOLEAN DEFAULT TRUE,
    modo_offline      BOOLEAN DEFAULT FALSE,
    idioma_interface  VARCHAR(10) DEFAULT 'pt-BR',
    
    -- Relacionamento com usuário
    FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario) ON DELETE CASCADE,
    
    -- Validações
    CHECK (volume_padrao >= 0 AND volume_padrao <= 100),
    CHECK (tema IN ('claro', 'escuro', 'auto')),
    CHECK (qualidade_audio IN ('baixa', 'media', 'alta', 'lossless'))
);

-- ===============================================
-- 3. GÊNEROS MUSICAIS
-- ===============================================

CREATE TABLE genero_musical (
    id_genero       INTEGER PRIMARY KEY,
    nome_genero     VARCHAR(50) NOT NULL UNIQUE,
    descricao       TEXT,
    genero_pai      INTEGER,
    cor_tema        VARCHAR(7),
    icone           VARCHAR(100),
    ativo           BOOLEAN DEFAULT TRUE,
    
    -- Auto-relacionamento para subgêneros
    FOREIGN KEY (genero_pai) REFERENCES genero_musical(id_genero),
    
    -- Validações
    CHECK (LENGTH(nome_genero) >= 2),
    CHECK (cor_tema IS NULL OR cor_tema LIKE '#______')
);

COMMENT ON TABLE genero_musical IS 'Gêneros e subgêneros musicais disponíveis';
COMMENT ON COLUMN genero_musical.genero_pai IS 'Referência para criar hierarquia de gêneros';
COMMENT ON COLUMN genero_musical.cor_tema IS 'Cor em hexadecimal para interface (#FF0000)';

-- ===============================================
-- 4. ARTISTAS
-- ===============================================

CREATE TABLE artista (
    id_artista       INTEGER PRIMARY KEY,
    nome_artista     VARCHAR(100) NOT NULL,
    tipo_artista     VARCHAR(20) NOT NULL,
    biografia        TEXT,
    data_formacao    DATE,
    data_fim         DATE,
    pais_origem      VARCHAR(50),
    cidade_origem    VARCHAR(100),
    genero_principal INTEGER,
    website          VARCHAR(255),
    instagram        VARCHAR(100),
    twitter          VARCHAR(100),
    facebook         VARCHAR(255),
    spotify_id       VARCHAR(50),
    imagem_perfil    VARCHAR(255),
    imagem_banner    VARCHAR(255),
    seguidores       INTEGER DEFAULT 0,
    verificado       BOOLEAN DEFAULT FALSE,
    ativo            BOOLEAN DEFAULT TRUE,
    data_criacao     TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    
    -- Relacionamentos
    FOREIGN KEY (genero_principal) REFERENCES genero_musical(id_genero),
    
    -- Validações de domínio
    CHECK (tipo_artista IN ('individual', 'banda', 'grupo')),
    CHECK (data_fim IS NULL OR data_fim >= data_formacao),
    CHECK (seguidores >= 0),
    CHECK (LENGTH(nome_artista) >= 1)
);

COMMENT ON TABLE artista IS 'Artistas, bandas e grupos musicais';
COMMENT ON COLUMN artista.tipo_artista IS 'individual=pessoa, banda=grupo musical, grupo=coletivo';
COMMENT ON COLUMN artista.verificado IS 'Artista verificado pela plataforma (selo azul)';

-- ===============================================
-- 5. ÁLBUNS
-- ===============================================

CREATE TABLE album (
    id_album         INTEGER PRIMARY KEY,
    titulo           VARCHAR(200) NOT NULL,
    tipo_album       VARCHAR(20) NOT NULL,
    data_lancamento  DATE,
    duracao_total    INTEGER DEFAULT 0,
    numero_faixas    INTEGER DEFAULT 0,
    gravadora        VARCHAR(100),
    produtor         VARCHAR(200),
    id_artista       INTEGER NOT NULL,
    genero_album     INTEGER,
    capa_album       VARCHAR(255),
    preco            DECIMAL(10,2),
    disponivel       BOOLEAN DEFAULT TRUE,
    explicito        BOOLEAN DEFAULT FALSE,
    data_criacao     TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    
    -- Relacionamentos
    FOREIGN KEY (id_artista) REFERENCES artista(id_artista),
    FOREIGN KEY (genero_album) REFERENCES genero_musical(id_genero),
    
    -- Validações
    CHECK (tipo_album IN ('studio', 'live', 'compilation', 'ep', 'single')),
    CHECK (numero_faixas >= 0),
    CHECK (duracao_total >= 0),
    CHECK (preco IS NULL OR preco >= 0),
    CHECK (LENGTH(titulo) >= 1)
);

COMMENT ON TABLE album IS 'Álbuns, EPs, singles e compilações';
COMMENT ON COLUMN album.duracao_total IS 'Duração total em segundos';
COMMENT ON COLUMN album.explicito IS 'Contém conteúdo explícito (linguagem forte, etc)';

-- ===============================================
-- 6. MÚSICAS
-- ===============================================

CREATE TABLE musica (
    id_musica        INTEGER PRIMARY KEY,
    titulo           VARCHAR(200) NOT NULL,
    duracao          INTEGER NOT NULL,
    id_album         INTEGER NOT NULL,
    numero_faixa     SMALLINT,
    letra            TEXT,
    compositor       VARCHAR(200),
    produtor         VARCHAR(200),
    data_upload      TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    reproduções      BIGINT DEFAULT 0,
    explicito        BOOLEAN DEFAULT FALSE,
    disponivel       BOOLEAN DEFAULT TRUE,
    preview_url      VARCHAR(255),
    arquivo_audio    VARCHAR(255),
    
    -- Relacionamentos
    FOREIGN KEY (id_album) REFERENCES album(id_album),
    
    -- Validações
    CHECK (duracao > 0 AND duracao <= 7200), -- Máximo 2 horas
    CHECK (numero_faixa IS NULL OR numero_faixa > 0),
    CHECK (reproduções >= 0),
    
    -- Garantir que não há duas músicas com mesmo número de faixa no álbum
    UNIQUE (id_album, numero_faixa)
);

COMMENT ON TABLE musica IS 'Faixas musicais individuais';
COMMENT ON COLUMN musica.duracao IS 'Duração da música em segundos';
COMMENT ON COLUMN musica.preview_url IS 'URL para preview de 30 segundos';

-- ===============================================
-- 7. PLAYLISTS
-- ===============================================

CREATE TABLE playlist (
    id_playlist      INTEGER PRIMARY KEY,
    nome_playlist    VARCHAR(150) NOT NULL,
    descricao        VARCHAR(500),
    publica          BOOLEAN DEFAULT FALSE,
    colaborativa     BOOLEAN DEFAULT FALSE,
    data_criacao     TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    data_modificacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    id_usuario       INTEGER NOT NULL,
    numero_musicas   INTEGER DEFAULT 0,
    duracao_total    INTEGER DEFAULT 0,
    imagem_capa      VARCHAR(255),
    
    -- Relacionamentos
    FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario),
    
    -- Validações
    CHECK (numero_musicas >= 0),
    CHECK (duracao_total >= 0),
    CHECK (LENGTH(nome_playlist) >= 1)
);

COMMENT ON TABLE playlist IS 'Playlists criadas pelos usuários';
COMMENT ON COLUMN playlist.colaborativa IS 'Permite que outros usuários adicionem músicas';

-- ===============================================
-- 8. RELACIONAMENTO PLAYLIST-MÚSICA
-- ===============================================

CREATE TABLE playlist_musica (
    id_playlist_musica INTEGER PRIMARY KEY,
    id_playlist        INTEGER NOT NULL,
    id_musica          INTEGER NOT NULL,
    posicao            INTEGER NOT NULL,
    data_adicao        TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    adicionado_por     INTEGER,
    
    -- Relacionamentos
    FOREIGN KEY (id_playlist) REFERENCES playlist(id_playlist) ON DELETE CASCADE,
    FOREIGN KEY (id_musica) REFERENCES musica(id_musica),
    FOREIGN KEY (adicionado_por) REFERENCES usuario(id_usuario),
    
    -- Garantir posição única na playlist
    UNIQUE (id_playlist, posicao),
    
    -- Evitar música duplicada na mesma playlist
    UNIQUE (id_playlist, id_musica),
    
    -- Validações
    CHECK (posicao > 0)
);

COMMENT ON TABLE playlist_musica IS 'Relacionamento N:N entre playlists e músicas';
COMMENT ON COLUMN playlist_musica.posicao IS 'Ordem da música na playlist (1, 2, 3...)';

-- ===============================================
-- 9. VERIFICAR ESTRUTURAS CRIADAS
-- ===============================================

-- Mostrar todas as tabelas criadas
SELECT table_name 
FROM information_schema.tables 
WHERE table_schema = 'public' 
  AND table_type = 'BASE TABLE'
ORDER BY table_name;

-- Mostrar estrutura de uma tabela específica
-- DESC usuario; -- Oracle/MySQL
-- \d usuario     -- PostgreSQL

-- ===============================================
-- 10. DADOS DE EXEMPLO PARA TESTE
-- ===============================================

-- Inserir gêneros básicos
INSERT INTO genero_musical (id_genero, nome_genero, descricao, cor_tema) VALUES
(1, 'Rock', 'Gênero musical caracterizado por ritmo forte e guitarras', '#FF4444'),
(2, 'Pop', 'Música popular mainstream', '#44FF44'),
(3, 'Jazz', 'Gênero musical com improviso e harmonias complexas', '#4444FF'),
(4, 'Classical', 'Música clássica erudita', '#FFD700'),
(5, 'Electronic', 'Música eletrônica e dance', '#FF44FF');

-- Inserir um usuário de teste
INSERT INTO usuario (id_usuario, nome_usuario, sobrenome, email, genero, pais) 
VALUES (1, 'João', 'Silva', 'joao.silva@email.com', 'M', 'Brasil');

-- Inserir configuração do usuário
INSERT INTO configuracao_usuario (id_usuario) VALUES (1);

-- Inserir um artista de teste
INSERT INTO artista (id_artista, nome_artista, tipo_artista, pais_origem, genero_principal) 
VALUES (1, 'The Beatles', 'banda', 'Reino Unido', 1);

-- Inserir um álbum de teste
INSERT INTO album (id_album, titulo, tipo_album, data_lancamento, id_artista, genero_album) 
VALUES (1, 'Abbey Road', 'studio', '1969-09-26', 1, 1);

-- Inserir uma música de teste
INSERT INTO musica (id_musica, titulo, duracao, id_album, numero_faixa) 
VALUES (1, 'Come Together', 259, 1, 1);

-- Verificar dados inseridos
SELECT 'Dados de teste inseridos com sucesso!' as status;