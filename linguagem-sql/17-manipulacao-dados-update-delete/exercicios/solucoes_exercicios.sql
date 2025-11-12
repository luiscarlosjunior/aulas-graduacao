-- ========================================
-- SOLUÇÕES DOS EXERCÍCIOS: UPDATE e DELETE
-- Módulo 17 - Manipulação de Dados
-- ========================================

-- ========================================
-- PARTE 1: SOLUÇÕES DE UPDATE
-- ========================================

-- Exercício 1
UPDATE usuario
SET nome_usuario = 'Carlos Silva Atualizado'
WHERE id_usuario = 1;

SELECT * FROM usuario WHERE id_usuario = 1;
ROLLBACK;

-- Exercício 2
UPDATE usuario
SET email = 'ana.souza.novo@email.com',
    pais = 'Brasil'
WHERE id_usuario = 2;

SELECT * FROM usuario WHERE id_usuario = 2;
ROLLBACK;

-- Exercício 3
UPDATE usuario
SET ativo = 'N'
WHERE pais = 'Espanha';

SELECT * FROM usuario WHERE pais = 'Espanha';
ROLLBACK;

-- Exercício 4
UPDATE musica
SET total_reproducoes = total_reproducoes + 5
WHERE id_album = 1;

SELECT id_musica, titulo, total_reproducoes 
FROM musica 
WHERE id_album = 1;
ROLLBACK;

-- Exercício 5
UPDATE tipo_assinatura
SET preco_mensal = preco_mensal * 1.15
WHERE ativo = 'S'
  AND nome_plano != 'Free';

SELECT nome_plano, preco_mensal 
FROM tipo_assinatura 
WHERE ativo = 'S';
ROLLBACK;

-- Exercício 6
UPDATE musica
SET id_genero = (
    SELECT id_genero
    FROM album
    WHERE album.id_album = musica.id_album
)
WHERE id_genero IS NULL;

SELECT id_musica, titulo, id_genero 
FROM musica;
ROLLBACK;

-- Exercício 7
UPDATE playlist
SET total_musicas = (
    SELECT COUNT(*)
    FROM playlist_musica
    WHERE playlist_musica.id_playlist = playlist.id_playlist
);

SELECT id_playlist, nome_playlist, total_musicas 
FROM playlist;
ROLLBACK;

-- Exercício 8
UPDATE album
SET duracao_total = (
    SELECT COALESCE(SUM(duracao), 0)
    FROM musica
    WHERE musica.id_album = album.id_album
);

SELECT id_album, titulo, duracao_total 
FROM album;
ROLLBACK;

-- Exercício 9
UPDATE assinatura
SET status_assinatura = CASE
    WHEN data_fim IS NULL THEN 'ATIVA'
    WHEN data_fim < SYSDATE THEN 'EXPIRADA'
    ELSE 'ATIVA'
END;

SELECT id_assinatura, status_assinatura, data_inicio, data_fim 
FROM assinatura;
ROLLBACK;

-- Exercício 10
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

-- ========================================
-- PARTE 2: SOLUÇÕES DE DELETE
-- ========================================

-- Exercício 11
-- Primeiro verificar dependências
SELECT 'historico' as tabela, COUNT(*) as total
FROM historico_reproducao WHERE id_usuario = 10
UNION ALL
SELECT 'assinatura', COUNT(*)
FROM assinatura WHERE id_usuario = 10
UNION ALL
SELECT 'playlist', COUNT(*)
FROM playlist WHERE id_usuario = 10;

-- Deletar na ordem correta
DELETE FROM historico_reproducao WHERE id_usuario = 10;
DELETE FROM assinatura WHERE id_usuario = 10;
DELETE FROM playlist_musica 
WHERE id_playlist IN (SELECT id_playlist FROM playlist WHERE id_usuario = 10);
DELETE FROM playlist WHERE id_usuario = 10;
DELETE FROM usuario WHERE id_usuario = 10;

SELECT COUNT(*) FROM usuario WHERE id_usuario = 10;
ROLLBACK;

-- Exercício 12
DELETE FROM musica
WHERE total_reproducoes = 0;

SELECT COUNT(*) FROM musica WHERE total_reproducoes = 0;
ROLLBACK;

-- Exercício 13
-- Solução 1: Usando NOT IN
DELETE FROM genero
WHERE id_genero NOT IN (
    SELECT DISTINCT id_genero
    FROM musica
    WHERE id_genero IS NOT NULL
)
AND id_genero NOT IN (
    SELECT DISTINCT id_genero
    FROM album
    WHERE id_genero IS NOT NULL
);

-- Solução 2: Usando NOT EXISTS
DELETE FROM genero g
WHERE NOT EXISTS (
    SELECT 1
    FROM musica m
    WHERE m.id_genero = g.id_genero
)
AND NOT EXISTS (
    SELECT 1
    FROM album a
    WHERE a.id_genero = g.id_genero
);

SELECT * FROM genero;
ROLLBACK;

-- Exercício 14
DELETE FROM playlist
WHERE id_playlist NOT IN (
    SELECT DISTINCT id_playlist
    FROM playlist_musica
);

-- Ou com NOT EXISTS:
DELETE FROM playlist p
WHERE NOT EXISTS (
    SELECT 1
    FROM playlist_musica pm
    WHERE pm.id_playlist = p.id_playlist
);

SELECT COUNT(*) FROM playlist;
ROLLBACK;

-- Exercício 15
DELETE FROM historico_reproducao
WHERE data_reproducao < ADD_MONTHS(SYSDATE, -12);

SELECT COUNT(*) as historico_antigo
FROM historico_reproducao
WHERE data_reproducao < ADD_MONTHS(SYSDATE, -12);
ROLLBACK;

-- Exercício 16
DELETE FROM usuario
WHERE ativo = 'N'
  AND pais = 'Brasil'
  AND NOT EXISTS (
      SELECT 1
      FROM assinatura
      WHERE assinatura.id_usuario = usuario.id_usuario
        AND status_assinatura = 'ATIVA'
  );

SELECT * FROM usuario 
WHERE ativo = 'N' AND pais = 'Brasil';
ROLLBACK;

-- Exercício 17
DELETE FROM musica
WHERE id_album IN (
    SELECT a.id_album
    FROM album a
    JOIN artista ar ON a.id_artista = ar.id_artista
    WHERE ar.pais_origem = 'Alemanha'
);

SELECT COUNT(*) as musicas_alemanha
FROM musica m
JOIN album a ON m.id_album = a.id_album
JOIN artista ar ON a.id_artista = ar.id_artista
WHERE ar.pais_origem = 'Alemanha';
ROLLBACK;

-- Exercício 18
-- Ordem: dependente primeiro, depois pai
DELETE FROM playlist_musica
WHERE id_playlist = 5;

DELETE FROM playlist
WHERE id_playlist = 5;

-- Verificar
SELECT COUNT(*) FROM playlist WHERE id_playlist = 5;
SELECT COUNT(*) FROM playlist_musica WHERE id_playlist = 5;
ROLLBACK;

-- Exercício 19
-- Verificar dependências primeiro
SELECT 'historico' as tabela, COUNT(*) as total
FROM historico_reproducao WHERE id_usuario = 6
UNION ALL
SELECT 'assinatura', COUNT(*)
FROM assinatura WHERE id_usuario = 6
UNION ALL
SELECT 'playlist', COUNT(*)
FROM playlist WHERE id_usuario = 6
UNION ALL
SELECT 'playlist_musica', COUNT(*)
FROM playlist_musica 
WHERE id_playlist IN (SELECT id_playlist FROM playlist WHERE id_usuario = 6);

-- Deletar na ordem correta (mais dependente para menos)
DELETE FROM historico_reproducao WHERE id_usuario = 6;
DELETE FROM assinatura WHERE id_usuario = 6;
DELETE FROM playlist_musica 
WHERE id_playlist IN (SELECT id_playlist FROM playlist WHERE id_usuario = 6);
DELETE FROM playlist WHERE id_usuario = 6;
DELETE FROM usuario WHERE id_usuario = 6;

-- Verificar
SELECT COUNT(*) FROM usuario WHERE id_usuario = 6;
ROLLBACK;

-- Exercício 20
-- 1. Histórico com mais de 2 anos
DELETE FROM historico_reproducao
WHERE data_reproducao < ADD_MONTHS(SYSDATE, -24);

-- 2. Assinaturas canceladas há mais de 1 ano
DELETE FROM assinatura
WHERE status_assinatura = 'CANCELADA'
  AND data_fim < ADD_MONTHS(SYSDATE, -12);

-- 3. Playlists vazias e antigas
DELETE FROM playlist
WHERE id_playlist NOT IN (
    SELECT DISTINCT id_playlist FROM playlist_musica
)
AND data_atualizacao < ADD_MONTHS(SYSDATE, -12);

-- Verificar resultados
SELECT COUNT(*) as historico_restante FROM historico_reproducao;
SELECT COUNT(*) as assinaturas_restantes FROM assinatura;
SELECT COUNT(*) as playlists_restantes FROM playlist;

ROLLBACK;

-- ========================================
-- PARTE 3: SOLUÇÕES COMBINADAS
-- ========================================

-- Exercício 21
-- 1. UPDATE
UPDATE musica
SET explicita = 'N'
WHERE id_album = 2;

SELECT id_musica, titulo, explicita 
FROM musica 
WHERE id_album = 2;

-- 2. DELETE
DELETE FROM historico_reproducao
WHERE id_musica IN (
    SELECT id_musica
    FROM musica
    WHERE id_album = 2
);

-- 3. Verificar
SELECT COUNT(*) as historico_deletado
FROM historico_reproducao
WHERE id_musica IN (
    SELECT id_musica FROM musica WHERE id_album = 2
);

-- 4. ROLLBACK
ROLLBACK;

-- Exercício 22
-- Soft delete em vez de DELETE físico
UPDATE usuario
SET ativo = 'N',
    ultimo_acesso = NULL
WHERE id_usuario = 8;

SELECT id_usuario, nome_usuario, ativo, ultimo_acesso
FROM usuario
WHERE id_usuario = 8;

ROLLBACK;

-- Exercício 23
-- 1. Criar backup
CREATE TABLE usuario_backup AS
SELECT *
FROM usuario
WHERE ativo = 'N';

-- 2. Verificar backup
SELECT COUNT(*) as usuarios_backup FROM usuario_backup;

-- 3. DELETE
DELETE FROM usuario
WHERE ativo = 'N';

-- 4. Verificar quantos foram deletados
SELECT COUNT(*) as usuarios_inativos_restantes
FROM usuario
WHERE ativo = 'N';

-- 5. Se necessário, restaurar (exemplo de como seria)
-- INSERT INTO usuario SELECT * FROM usuario_backup;

-- 6. ROLLBACK (para restaurar neste exercício)
ROLLBACK;

-- 7. Limpar backup
DROP TABLE usuario_backup;

-- ========================================
-- PARTE 4: SOLUÇÕES DE SEGURANÇA
-- ========================================

-- Exercício 24
-- PASSO 1: Verificar dados atuais
SELECT id_usuario, nome_usuario, email, ativo
FROM usuario
WHERE id_usuario = 3;

-- PASSO 2: UPDATE
UPDATE usuario
SET nome_usuario = 'John Smith Atualizado',
    email = 'john.smith.new@email.com'
WHERE id_usuario = 3;

-- PASSO 3: Verificar mudanças
SELECT id_usuario, nome_usuario, email, ativo
FROM usuario
WHERE id_usuario = 3;

-- PASSO 4: Confirmar ou reverter
-- COMMIT; -- Se correto
ROLLBACK; -- Para este exercício

-- Exercício 25
-- PASSO 1: Verificar dados atuais
SELECT * FROM playlist WHERE id_playlist = 7;

-- PASSO 2: Contar dependências
SELECT COUNT(*) as musicas_na_playlist
FROM playlist_musica
WHERE id_playlist = 7;

-- PASSO 3: DELETE das dependências
DELETE FROM playlist_musica
WHERE id_playlist = 7;

-- Verificar
SELECT COUNT(*) FROM playlist_musica WHERE id_playlist = 7;

-- PASSO 4: DELETE da playlist
DELETE FROM playlist
WHERE id_playlist = 7;

-- PASSO 5: Verificar remoção
SELECT COUNT(*) as playlist_restante
FROM playlist
WHERE id_playlist = 7;
-- Deve retornar 0

-- PASSO 6: Confirmar ou reverter
-- COMMIT; -- Se correto
ROLLBACK; -- Para este exercício

-- ========================================
-- OBSERVAÇÕES IMPORTANTES
-- ========================================
--
-- 1. Estas soluções são exemplos educacionais
-- 2. Em produção, sempre faça backup antes de UPDATE/DELETE em massa
-- 3. Sempre teste em ambiente de desenvolvimento primeiro
-- 4. Use transações explícitas em operações críticas
-- 5. Documente operações importantes
-- 6. Considere soft delete (UPDATE ativo='N') em vez de DELETE físico
-- 7. Verifique constraints e FKs antes de DELETE
-- 8. Em caso de dúvida, pergunte antes de executar!
--
-- ========================================
