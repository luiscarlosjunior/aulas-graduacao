-- ========================================
-- EXEMPLOS PRÁTICOS: UPDATE
-- Módulo 17 - Manipulação de Dados
-- ========================================
-- 
-- Este arquivo contém exemplos práticos do comando UPDATE
-- usando o banco de dados MusiStream
--
-- IMPORTANTE: Execute estes comandos em ambiente de teste!
-- Sempre use transações e faça ROLLBACK para restaurar dados.
--

-- ========================================
-- SEÇÃO 1: UPDATE BÁSICO
-- ========================================

-- Exemplo 1: Atualizar nome de usuário
UPDATE usuario
SET nome_usuario = 'Carlos Silva Júnior'
WHERE id_usuario = 1;

SELECT * FROM usuario WHERE id_usuario = 1;
ROLLBACK; -- Desfazer mudança

-- Exemplo 2: Atualizar múltiplas colunas
UPDATE usuario
SET nome_usuario = 'João Pedro Silva',
    email = 'joao.pedro@email.com',
    pais = 'Brasil',
    ultimo_acesso = SYSTIMESTAMP
WHERE id_usuario = 3;

SELECT * FROM usuario WHERE id_usuario = 3;
ROLLBACK;

-- Exemplo 3: Atualizar com NULL
UPDATE artista
SET website = NULL,
    biografia = 'Biografia será atualizada em breve'
WHERE id_artista = 5;

SELECT * FROM artista WHERE id_artista = 5;
ROLLBACK;

-- ========================================
-- SEÇÃO 2: UPDATE COM EXPRESSÕES
-- ========================================

-- Exemplo 4: Incrementar contador
UPDATE musica
SET total_reproducoes = total_reproducoes + 1
WHERE id_musica = 1;

SELECT id_musica, titulo, total_reproducoes 
FROM musica 
WHERE id_musica = 1;
ROLLBACK;

-- Exemplo 5: Aplicar desconto percentual
UPDATE tipo_assinatura
SET preco_mensal = preco_mensal * 0.90
WHERE nome_plano = 'Premium';

SELECT id_tipo_assinatura, nome_plano, preco_mensal 
FROM tipo_assinatura 
WHERE nome_plano = 'Premium';
ROLLBACK;

-- Exemplo 6: Calcular com funções
UPDATE musica
SET duracao = duracao + 10
WHERE id_album = 1 AND duracao < 300;

SELECT id_musica, titulo, duracao 
FROM musica 
WHERE id_album = 1;
ROLLBACK;

-- ========================================
-- SEÇÃO 3: UPDATE COM SUBCONSULTAS
-- ========================================

-- Exemplo 7: Atualizar baseado em outra tabela
UPDATE musica
SET id_genero = (
    SELECT id_genero 
    FROM album 
    WHERE album.id_album = musica.id_album
)
WHERE id_genero IS NULL;

SELECT id_musica, titulo, id_genero 
FROM musica 
WHERE id_musica IN (1, 2, 3);
ROLLBACK;

-- Exemplo 8: UPDATE com agregação
UPDATE playlist
SET total_musicas = (
    SELECT COUNT(*)
    FROM playlist_musica
    WHERE playlist_musica.id_playlist = playlist.id_playlist
);

SELECT id_playlist, nome_playlist, total_musicas 
FROM playlist;
ROLLBACK;

-- Exemplo 9: UPDATE com EXISTS
UPDATE artista
SET ativo = 'S'
WHERE EXISTS (
    SELECT 1
    FROM album
    WHERE album.id_artista = artista.id_artista
      AND album.data_lancamento > TO_DATE('2010-01-01', 'YYYY-MM-DD')
);

SELECT id_artista, nome_artista, ativo, pais_origem 
FROM artista;
ROLLBACK;

-- Exemplo 10: UPDATE correlacionado
UPDATE album a
SET duracao_total = (
    SELECT SUM(duracao)
    FROM musica m
    WHERE m.id_album = a.id_album
)
WHERE EXISTS (
    SELECT 1 
    FROM musica m 
    WHERE m.id_album = a.id_album
);

SELECT id_album, titulo, duracao_total 
FROM album;
ROLLBACK;

-- ========================================
-- SEÇÃO 4: UPDATE COM CASE
-- ========================================

-- Exemplo 11: UPDATE condicional com CASE
UPDATE assinatura
SET status_assinatura = CASE
    WHEN data_fim IS NULL THEN 'ATIVA'
    WHEN data_fim < SYSDATE THEN 'EXPIRADA'
    WHEN data_fim >= SYSDATE THEN 'ATIVA'
    ELSE 'DESCONHECIDA'
END;

SELECT id_assinatura, status_assinatura, data_inicio, data_fim 
FROM assinatura;
ROLLBACK;

-- Exemplo 12: Aplicar diferentes ajustes por categoria
UPDATE tipo_assinatura
SET preco_mensal = CASE
    WHEN nome_plano = 'Premium' THEN preco_mensal * 0.90
    WHEN nome_plano = 'Família' THEN preco_mensal * 0.85
    WHEN nome_plano = 'Estudante' THEN preco_mensal * 0.95
    ELSE preco_mensal
END
WHERE ativo = 'S';

SELECT nome_plano, preco_mensal 
FROM tipo_assinatura 
WHERE ativo = 'S';
ROLLBACK;

-- Exemplo 13: Normalização com CASE
UPDATE usuario
SET pais = CASE
    WHEN pais IN ('USA', 'United States', 'US') THEN 'Estados Unidos'
    WHEN pais IN ('UK', 'England') THEN 'Reino Unido'
    WHEN pais = 'PT' THEN 'Portugal'
    WHEN pais = 'BR' THEN 'Brasil'
    ELSE pais
END;

SELECT id_usuario, nome_usuario, pais 
FROM usuario;
ROLLBACK;

-- ========================================
-- SEÇÃO 5: UPDATE EM MASSA
-- ========================================

-- Exemplo 14: Atualizar múltiplos registros
UPDATE usuario
SET ativo = 'S'
WHERE pais = 'Brasil'
  AND data_cadastro >= TO_DATE('2024-01-01', 'YYYY-MM-DD');

SELECT id_usuario, nome_usuario, ativo, pais 
FROM usuario 
WHERE pais = 'Brasil';
ROLLBACK;

-- Exemplo 15: Atualização global com filtro
UPDATE musica
SET explicita = 'N'
WHERE explicita = 'S' OR explicita IS NULL;

SELECT id_musica, titulo, explicita 
FROM musica;
ROLLBACK;

-- Exemplo 16: Resetar contadores
UPDATE musica
SET total_reproducoes = 0
WHERE data_upload < TO_DATE('2020-01-01', 'YYYY-MM-DD');

SELECT id_musica, titulo, total_reproducoes, data_upload 
FROM musica;
ROLLBACK;

-- ========================================
-- SEÇÃO 6: UPDATE AVANÇADO
-- ========================================

-- Exemplo 17: Sincronizar dados entre tabelas
UPDATE playlist p
SET total_musicas = (
    SELECT COUNT(*)
    FROM playlist_musica pm
    WHERE pm.id_playlist = p.id_playlist
),
duracao_total = (
    SELECT COALESCE(SUM(m.duracao), 0)
    FROM playlist_musica pm
    JOIN musica m ON pm.id_musica = m.id_musica
    WHERE pm.id_playlist = p.id_playlist
);

SELECT id_playlist, nome_playlist, total_musicas, duracao_total 
FROM playlist;
ROLLBACK;

-- Exemplo 18: UPDATE baseado em múltiplas condições
UPDATE artista
SET ativo = CASE
    WHEN numero_membros > 10 THEN 'S'
    WHEN numero_membros IS NULL THEN 'N'
    ELSE ativo
END,
pais_origem = CASE
    WHEN pais_origem IS NULL THEN 'Desconhecido'
    ELSE pais_origem
END;

SELECT id_artista, nome_artista, ativo, pais_origem, numero_membros 
FROM artista;
ROLLBACK;

-- ========================================
-- PADRÃO DE UPDATE SEGURO
-- ========================================

-- SEMPRE siga este padrão:

-- PASSO 1: Consultar dados atuais
SELECT id_usuario, nome_usuario, email, ativo
FROM usuario
WHERE id_usuario = 5;

-- PASSO 2: Executar UPDATE
UPDATE usuario
SET nome_usuario = 'Pedro Costa Silva',
    email = 'pedro.costa@email.com',
    ativo = 'S'
WHERE id_usuario = 5;

-- PASSO 3: Verificar mudanças
SELECT id_usuario, nome_usuario, email, ativo
FROM usuario
WHERE id_usuario = 5;

-- PASSO 4: Confirmar ou reverter
COMMIT; -- Se estiver correto
-- ROLLBACK; -- Se estiver errado

-- ========================================
-- IMPORTANTE
-- ========================================
-- 
-- 1. SEMPRE use WHERE no UPDATE
-- 2. SEMPRE teste com SELECT primeiro
-- 3. SEMPRE use transações
-- 4. SEMPRE faça backup em operações críticas
-- 5. NUNCA execute UPDATE sem revisar a cláusula WHERE
--
-- ========================================
