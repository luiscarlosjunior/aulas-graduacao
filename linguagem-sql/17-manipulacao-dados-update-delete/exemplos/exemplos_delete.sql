-- ========================================
-- EXEMPLOS PRÁTICOS: DELETE
-- Módulo 17 - Manipulação de Dados
-- ========================================
-- 
-- Este arquivo contém exemplos práticos do comando DELETE
-- usando o banco de dados MusiStream
--
-- ⚠️ AVISO CRÍTICO: DELETE é uma operação DESTRUTIVA!
-- Execute estes comandos APENAS em ambiente de teste!
-- SEMPRE use ROLLBACK para restaurar dados após testes.
--

-- ========================================
-- SEÇÃO 1: DELETE BÁSICO
-- ========================================

-- Exemplo 1: Deletar um registro específico
DELETE FROM usuario
WHERE id_usuario = 10;

SELECT * FROM usuario WHERE id_usuario = 10;
-- Deve retornar 0 linhas
ROLLBACK; -- Restaurar dados

-- Exemplo 2: Deletar por email
DELETE FROM usuario
WHERE email = 'sofia@email.com';

SELECT COUNT(*) FROM usuario WHERE email = 'sofia@email.com';
ROLLBACK;

-- Exemplo 3: Deletar playlist
DELETE FROM playlist
WHERE id_playlist = 8;

SELECT * FROM playlist WHERE id_playlist = 8;
ROLLBACK;

-- ========================================
-- SEÇÃO 2: DELETE COM CONDIÇÕES MÚLTIPLAS
-- ========================================

-- Exemplo 4: DELETE com AND
DELETE FROM usuario
WHERE ativo = 'N'
  AND pais = 'Brasil'
  AND data_cadastro < TO_DATE('2024-01-01', 'YYYY-MM-DD');

SELECT * FROM usuario 
WHERE ativo = 'N' AND pais = 'Brasil';
ROLLBACK;

-- Exemplo 5: DELETE com IN
DELETE FROM musica
WHERE id_musica IN (26, 27, 28, 29);

SELECT * FROM musica WHERE id_musica IN (26, 27, 28, 29);
ROLLBACK;

-- Exemplo 6: DELETE com LIKE
DELETE FROM playlist
WHERE nome_playlist LIKE '%teste%'
   OR nome_playlist LIKE '%test%';

SELECT * FROM playlist WHERE nome_playlist LIKE '%test%';
ROLLBACK;

-- Exemplo 7: DELETE com BETWEEN
DELETE FROM musica
WHERE duracao BETWEEN 100 AND 200
  AND total_reproducoes = 0;

SELECT * FROM musica WHERE duracao BETWEEN 100 AND 200;
ROLLBACK;

-- ========================================
-- SEÇÃO 3: DELETE COM SUBCONSULTAS
-- ========================================

-- Exemplo 8: DELETE com subconsulta simples
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

-- Exemplo 9: DELETE com NOT IN
DELETE FROM genero
WHERE id_genero NOT IN (
    SELECT DISTINCT id_genero
    FROM musica
    WHERE id_genero IS NOT NULL
);

SELECT * FROM genero 
WHERE id_genero NOT IN (SELECT DISTINCT id_genero FROM musica WHERE id_genero IS NOT NULL);
ROLLBACK;

-- Exemplo 10: DELETE com EXISTS
DELETE FROM usuario
WHERE NOT EXISTS (
    SELECT 1
    FROM assinatura
    WHERE assinatura.id_usuario = usuario.id_usuario
);

SELECT u.id_usuario, u.nome_usuario
FROM usuario u
WHERE NOT EXISTS (
    SELECT 1 FROM assinatura WHERE id_usuario = u.id_usuario
);
ROLLBACK;

-- Exemplo 11: DELETE com agregação
DELETE FROM playlist
WHERE id_playlist NOT IN (
    SELECT DISTINCT id_playlist
    FROM playlist_musica
);

SELECT p.id_playlist, p.nome_playlist
FROM playlist p
WHERE NOT EXISTS (
    SELECT 1 FROM playlist_musica pm WHERE pm.id_playlist = p.id_playlist
);
ROLLBACK;

-- ========================================
-- SEÇÃO 4: DELETE EM MASSA
-- ========================================

-- Exemplo 12: Limpeza de dados antigos
DELETE FROM historico_reproducao
WHERE data_reproducao < ADD_MONTHS(SYSDATE, -12);

SELECT COUNT(*) as historico_antigo
FROM historico_reproducao
WHERE data_reproducao < ADD_MONTHS(SYSDATE, -12);
ROLLBACK;

-- Exemplo 13: Remover músicas sem dados
DELETE FROM musica
WHERE arquivo_url IS NULL
   OR duracao <= 0
   OR titulo IS NULL;

SELECT COUNT(*) as musicas_invalidas
FROM musica
WHERE arquivo_url IS NULL OR duracao <= 0 OR titulo IS NULL;
ROLLBACK;

-- Exemplo 14: Deletar usuários inativos antigos
DELETE FROM usuario
WHERE ativo = 'N'
  AND data_cadastro < ADD_MONTHS(SYSDATE, -24);

SELECT COUNT(*) as usuarios_inativos_antigos
FROM usuario
WHERE ativo = 'N' AND data_cadastro < ADD_MONTHS(SYSDATE, -24);
ROLLBACK;

-- ========================================
-- SEÇÃO 5: DELETE COM INTEGRIDADE REFERENCIAL
-- ========================================

-- Exemplo 15: Deletar registros de tabela de relacionamento N:M
DELETE FROM playlist_musica
WHERE id_playlist = 1
  AND id_musica IN (1, 2);

SELECT * FROM playlist_musica 
WHERE id_playlist = 1 AND id_musica IN (1, 2);
ROLLBACK;

-- Exemplo 16: DELETE respeitando ordem de dependências
-- Primeiro verificar dependências
SELECT 
    'Histórico' as tabela,
    COUNT(*) as registros
FROM historico_reproducao
WHERE id_musica = 20
UNION ALL
SELECT 
    'Playlist_Musica',
    COUNT(*)
FROM playlist_musica
WHERE id_musica = 20;

-- Depois deletar na ordem correta
DELETE FROM historico_reproducao WHERE id_musica = 20;
DELETE FROM playlist_musica WHERE id_musica = 20;
DELETE FROM musica WHERE id_musica = 20;

ROLLBACK;

-- Exemplo 17: DELETE de usuário e relacionamentos
-- Ordem: mais dependente para menos dependente
DELETE FROM historico_reproducao WHERE id_usuario = 9;
DELETE FROM assinatura WHERE id_usuario = 9;
DELETE FROM playlist_musica 
WHERE id_playlist IN (SELECT id_playlist FROM playlist WHERE id_usuario = 9);
DELETE FROM playlist WHERE id_usuario = 9;
DELETE FROM usuario WHERE id_usuario = 9;

ROLLBACK;

-- ========================================
-- SEÇÃO 6: PADRÃO DE DELETE SEGURO
-- ========================================

-- SEMPRE siga este padrão completo:

-- PASSO 1: Visualizar o que será deletado
SELECT *
FROM usuario
WHERE id_usuario = 8;

-- PASSO 2: Contar registros
SELECT COUNT(*)
FROM usuario
WHERE id_usuario = 8;

-- PASSO 3: Verificar dependências
SELECT 'playlists' as tabela, COUNT(*) as total
FROM playlist WHERE id_usuario = 8
UNION ALL
SELECT 'assinaturas', COUNT(*)
FROM assinatura WHERE id_usuario = 8
UNION ALL
SELECT 'historico', COUNT(*)
FROM historico_reproducao WHERE id_usuario = 8;

-- PASSO 4: Executar DELETE
DELETE FROM usuario
WHERE id_usuario = 8;

-- PASSO 5: Verificar resultado
SELECT COUNT(*) as registros_restantes
FROM usuario
WHERE id_usuario = 8;
-- Deve retornar 0

-- PASSO 6: Confirmar ou reverter
COMMIT; -- Se correto
-- ROLLBACK; -- Se errado

-- ========================================
-- SEÇÃO 7: DELETE COM BACKUP
-- ========================================

-- Exemplo 18: Criar backup antes de DELETE em massa
CREATE TABLE musica_backup AS
SELECT *
FROM musica
WHERE id_album IN (1, 2);

-- Executar DELETE
DELETE FROM musica
WHERE id_album IN (1, 2);

-- Verificar
SELECT COUNT(*) FROM musica WHERE id_album IN (1, 2);

-- Se correto: COMMIT e depois dropar backup
-- COMMIT;
-- DROP TABLE musica_backup;

-- Se errado: ROLLBACK (dados voltam)
ROLLBACK;

-- ========================================
-- SEÇÃO 8: CASOS DE USO PRÁTICOS
-- ========================================

-- Exemplo 19: Implementar direito ao esquecimento (LGPD)
-- Remover todos os dados de um usuário

-- Primeiro: criar registro de auditoria (se tabela existisse)
-- INSERT INTO auditoria_lgpd VALUES (7, SYSDATE, SYSDATE);

-- Remover dados em cascata
DELETE FROM historico_reproducao WHERE id_usuario = 7;
DELETE FROM assinatura WHERE id_usuario = 7;
DELETE FROM playlist_musica 
WHERE id_playlist IN (SELECT id_playlist FROM playlist WHERE id_usuario = 7);
DELETE FROM playlist WHERE id_usuario = 7;
DELETE FROM usuario WHERE id_usuario = 7;

-- Verificar se removeu tudo
SELECT COUNT(*) FROM usuario WHERE id_usuario = 7;

ROLLBACK;

-- Exemplo 20: Limpeza de dados duplicados
-- Manter apenas o registro mais recente por email
DELETE FROM usuario
WHERE id_usuario IN (
    SELECT u1.id_usuario
    FROM usuario u1
    WHERE EXISTS (
        SELECT 1
        FROM usuario u2
        WHERE u1.email = u2.email 
          AND u1.id_usuario < u2.id_usuario
    )
);

ROLLBACK;

-- Exemplo 21: Remover dados órfãos
-- Músicas sem álbum válido
DELETE FROM musica
WHERE id_album NOT IN (SELECT id_album FROM album);

-- Playlists sem usuário (se FK permitisse NULL)
DELETE FROM playlist
WHERE id_usuario NOT IN (SELECT id_usuario FROM usuario);

ROLLBACK;

-- Exemplo 22: Política de retenção de dados
-- Manter histórico apenas dos últimos 2 anos
DELETE FROM historico_reproducao
WHERE data_reproducao < ADD_MONTHS(SYSDATE, -24);

-- Remover assinaturas canceladas há mais de 1 ano
DELETE FROM assinatura
WHERE status_assinatura = 'CANCELADA'
  AND data_fim < ADD_MONTHS(SYSDATE, -12);

ROLLBACK;

-- ========================================
-- SEÇÃO 9: DELETE INCREMENTAL (GRANDES VOLUMES)
-- ========================================

-- Exemplo 23: Deletar em lotes para evitar locks longos
-- (Este exemplo usa ROWNUM, específico do Oracle)

-- Primeira execução
DELETE FROM historico_reproducao
WHERE data_reproducao < TO_DATE('2022-01-01', 'YYYY-MM-DD')
  AND ROWNUM <= 1000;

SELECT SQL%ROWCOUNT as linhas_deletadas FROM DUAL;

COMMIT;

-- Repetir até não haver mais linhas
SELECT COUNT(*) as linhas_restantes
FROM historico_reproducao
WHERE data_reproducao < TO_DATE('2022-01-01', 'YYYY-MM-DD');

ROLLBACK;

-- ========================================
-- COMPARAÇÃO: DELETE vs TRUNCATE
-- ========================================

-- DELETE - Seletivo, transacional, pode fazer ROLLBACK
DELETE FROM historico_reproducao
WHERE data_reproducao < TO_DATE('2023-01-01', 'YYYY-MM-DD');
ROLLBACK; -- Funciona!

-- TRUNCATE - Remove tudo, rápido, NÃO pode fazer ROLLBACK (em muitos DBs)
-- TRUNCATE TABLE historico_reproducao;
-- ROLLBACK; -- NÃO funciona em muitos bancos!

-- ========================================
-- IMPORTANTE - REGRAS DE SEGURANÇA
-- ========================================
-- 
-- 1. SEMPRE use WHERE no DELETE (exceto se realmente quer tudo)
-- 2. SEMPRE teste com SELECT COUNT primeiro
-- 3. SEMPRE use transações (implícitas ou explícitas)
-- 4. SEMPRE verifique dependências antes de deletar
-- 5. SEMPRE faça backup em operações críticas
-- 6. NUNCA execute DELETE sem revisar WHERE cuidadosamente
-- 7. Prefira soft delete (UPDATE ativo='N') quando possível
-- 8. DELETE sem WHERE deleta TODOS os registros!
-- 
-- Lembre-se: DELETE é PERMANENTE após COMMIT!
-- ========================================
