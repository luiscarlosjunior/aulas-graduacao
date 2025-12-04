-- =====================================================
-- DEMONSTRAÇÃO: USANDO BASE-TABLES.SQL COM ALTER TABLE
-- =====================================================
-- Este script demonstra como usar as tabelas criadas
-- pelo base-tables.sql para praticar comandos ALTER TABLE
-- Execute primeiro: @base-tables.sql
-- =====================================================

-- Verificar se as tabelas base existem
SELECT table_name 
FROM user_tables 
WHERE table_name IN ('USUARIO', 'ARTISTA', 'ALBUM', 'MUSICA')
ORDER BY table_name;

-- =====================================================
-- EXEMPLO 1: EVOLUINDO A TABELA DE USUÁRIOS
-- =====================================================

-- Adicionar campos de perfil social
ALTER TABLE usuario 
ADD (
    instagram VARCHAR2(50),
    twitter VARCHAR2(50),
    telefone VARCHAR2(20)
);

-- Verificar estrutura alterada
DESCRIBE usuario;

-- Adicionar campo com constraint
ALTER TABLE usuario 
ADD pontos_fidelidade INTEGER DEFAULT 0 
CONSTRAINT ck_pontos_usuario CHECK (pontos_fidelidade >= 0);

-- =====================================================
-- EXEMPLO 2: MELHORANDO A TABELA DE MÚSICAS
-- =====================================================

-- Adicionar metadados de qualidade
ALTER TABLE musica 
ADD (
    formato_audio VARCHAR2(10) DEFAULT 'MP3',
    qualidade_kbps INTEGER DEFAULT 320,
    explicito CHAR(1) DEFAULT 'N'
);

-- Adicionar constraint para formato
ALTER TABLE musica 
ADD CONSTRAINT ck_formato_audio 
CHECK (formato_audio IN ('MP3', 'FLAC', 'WAV', 'AAC'));

-- =====================================================
-- EXEMPLO 3: EXPANDINDO A TABELA DE ARTISTAS
-- =====================================================

-- Adicionar informações comerciais
ALTER TABLE artista 
ADD (
    numero_seguidores INTEGER DEFAULT 0,
    verificado CHAR(1) DEFAULT 'N',
    data_verificacao DATE,
    website VARCHAR2(200)
);

-- Modificar tamanho de campo existente
ALTER TABLE artista 
MODIFY nome_artista VARCHAR2(150);

-- =====================================================
-- EXEMPLO 4: RENOMEAÇÃO E MODIFICAÇÃO
-- =====================================================

-- Renomear coluna
ALTER TABLE playlist 
RENAME COLUMN nome_playlist TO titulo_playlist;

-- Modificar constraint
ALTER TABLE album 
ADD CONSTRAINT ck_ano_album CHECK (ano_lancamento BETWEEN 1900 AND 2024);

-- =====================================================
-- VERIFICAÇÃO FINAL
-- =====================================================

-- Ver estruturas modificadas
DESCRIBE usuario;
DESCRIBE musica;
DESCRIBE artista;
DESCRIBE playlist;

-- Verificar constraints criadas
SELECT constraint_name, constraint_type, table_name 
FROM user_constraints 
WHERE table_name IN ('USUARIO', 'MUSICA', 'ARTISTA', 'ALBUM', 'PLAYLIST')
AND constraint_name LIKE 'CK_%'
ORDER BY table_name, constraint_name;

-- Exemplo de consulta usando as tabelas modificadas
SELECT 
    u.nome_usuario,
    u.email,
    u.pontos_fidelidade,
    u.instagram,
    COUNT(p.id_playlist) as total_playlists
FROM usuario u
LEFT JOIN playlist p ON u.id_usuario = p.id_usuario
WHERE u.ativo = 'S'
GROUP BY u.id_usuario, u.nome_usuario, u.email, u.pontos_fidelidade, u.instagram
ORDER BY u.nome_usuario;

PROMPT =====================================================;
PROMPT DEMONSTRAÇÃO CONCLUÍDA!;
PROMPT =====================================================;
PROMPT Você praticou:;
PROMPT - ADD COLUMN com e sem constraints;
PROMPT - MODIFY COLUMN para alterar tipos;
PROMPT - RENAME COLUMN;
PROMPT - ADD CONSTRAINT;
PROMPT ====================================================;