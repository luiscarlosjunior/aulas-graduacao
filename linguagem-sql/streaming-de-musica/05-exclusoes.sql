-- =====================================================
-- SISTEMA MUSISTREAM - OPERAÇÕES DE EXCLUSÃO
-- =====================================================
-- Script demonstrando operações DELETE (CRUD)
-- Exclusões e limpezas no sistema de streaming
-- =====================================================

-- Configurações iniciais
SET ECHO ON
SET FEEDBACK ON
SET PAGESIZE 30
SET LINESIZE 120

PROMPT =====================================================
PROMPT OPERAÇÕES DE EXCLUSÃO NO SISTEMA MUSISTREAM
PROMPT =====================================================

-- =====================================================
-- SEÇÃO 1: EXCLUSÕES BÁSICAS E SEGURAS
-- =====================================================

PROMPT ===== 1.1 - LIMPEZA DE HISTÓRICO ANTIGO =====

-- Verificar histórico mais antigo
SELECT 
    MIN(data_reproducao) AS mais_antigo,
    MAX(data_reproducao) AS mais_recente,
    COUNT(*) AS total_registros
FROM historico_reproducao;

-- Criar backup dos dados antes da exclusão (simulado)
CREATE TABLE historico_backup AS
SELECT * FROM historico_reproducao 
WHERE data_reproducao < SYSDATE - 365;

-- Verificar quantos registros serão excluídos
SELECT COUNT(*) AS registros_para_exclusao
FROM historico_reproducao 
WHERE data_reproducao < SYSDATE - 365;

-- Excluir histórico mais antigo que 1 ano (se existir)
DELETE FROM historico_reproducao 
WHERE data_reproducao < SYSDATE - 365;

PROMPT ===== 1.2 - REMOVENDO USUÁRIOS INATIVOS =====

-- Identificar usuários inativos há mais de 6 meses
SELECT 
    u.id_usuario,
    u.nome_usuario,
    u.ativo,
    u.ultimo_acesso,
    MAX(hr.data_reproducao) AS ultima_atividade
FROM usuario u
LEFT JOIN historico_reproducao hr ON u.id_usuario = hr.id_usuario
WHERE u.ativo = 'N'
GROUP BY u.id_usuario, u.nome_usuario, u.ativo, u.ultimo_acesso
HAVING MAX(hr.data_reproducao) < SYSDATE - 180 OR MAX(hr.data_reproducao) IS NULL;

-- Criar um usuário inativo para demonstração
INSERT INTO usuario (
    id_usuario, nome_usuario, email, senha, data_nascimento, 
    pais, data_cadastro, ativo
) VALUES (
    seq_usuario.NEXTVAL, 'Usuario Inativo', 'inativo@test.com', 'senha123',
    DATE '1980-01-01', 'Brasil', SYSDATE - 200, 'N'
);

-- Excluir usuários inativos sem atividade (CASCADE irá remover dependências)
DELETE FROM usuario 
WHERE ativo = 'N' 
  AND id_usuario NOT IN (
      SELECT DISTINCT id_usuario 
      FROM historico_reproducao 
      WHERE data_reproducao > SYSDATE - 180
  )
  AND nome_usuario = 'Usuario Inativo'; -- Só o exemplo criado

PROMPT ===== 1.3 - REMOVENDO GÊNEROS NÃO UTILIZADOS =====

-- Identificar gêneros sem músicas associadas
SELECT 
    g.id_genero,
    g.nome_genero,
    COUNT(m.id_musica) AS musicas_associadas
FROM genero g
LEFT JOIN musica m ON g.id_genero = m.id_genero
GROUP BY g.id_genero, g.nome_genero
HAVING COUNT(m.id_musica) = 0;

-- Adicionar um gênero não utilizado para demonstração
INSERT INTO genero (id_genero, nome_genero, descricao) 
VALUES (seq_genero.NEXTVAL, 'Gênero Teste', 'Gênero para teste de exclusão');

-- Remover gêneros não utilizados
DELETE FROM genero 
WHERE id_genero NOT IN (
    SELECT DISTINCT id_genero 
    FROM musica 
    WHERE id_genero IS NOT NULL
)
AND nome_genero = 'Gênero Teste'; -- Só o exemplo criado

-- =====================================================
-- SEÇÃO 2: EXCLUSÕES CONDICIONAIS
-- =====================================================

PROMPT ===== 2.1 - REMOVENDO REPRODUÇÕES DE TESTE/SPAM =====

-- Identificar reproduções potencialmente suspeitas
SELECT 
    hr.id_usuario,
    u.nome_usuario,
    COUNT(*) AS reproducoes_por_dia,
    TRUNC(hr.data_reproducao) AS data_reproducao
FROM historico_reproducao hr
JOIN usuario u ON hr.id_usuario = u.id_usuario
WHERE hr.data_reproducao >= SYSDATE - 1
GROUP BY hr.id_usuario, u.nome_usuario, TRUNC(hr.data_reproducao)
HAVING COUNT(*) > 100; -- Mais de 100 reproduções por dia

-- Remover reproduções muito rápidas (duração ouvida muito baixa)
DELETE FROM historico_reproducao 
WHERE duracao_ouvida < 10 
  AND duracao_ouvida IS NOT NULL;

PROMPT ===== 2.2 - LIMPEZA DE PLAYLISTS VAZIAS =====

-- Identificar playlists vazias ou quase vazias
SELECT 
    p.id_playlist,
    p.nome_playlist,
    u.nome_usuario,
    p.total_musicas,
    COUNT(pm.id_musica) AS musicas_reais
FROM playlist p
JOIN usuario u ON p.id_usuario = u.id_usuario
LEFT JOIN playlist_musica pm ON p.id_playlist = pm.id_playlist
GROUP BY p.id_playlist, p.nome_playlist, u.nome_usuario, p.total_musicas
HAVING COUNT(pm.id_musica) = 0;

-- Criar uma playlist vazia para demonstração
INSERT INTO playlist (
    id_playlist, nome_playlist, descricao, id_usuario
) VALUES (
    seq_playlist.NEXTVAL, 'Playlist Vazia Teste', 'Para teste de exclusão', 1
);

-- Remover playlists vazias criadas há mais de 30 dias
DELETE FROM playlist 
WHERE total_musicas = 0 
  AND data_criacao < SYSDATE - 30
  AND nome_playlist = 'Playlist Vazia Teste'; -- Só o exemplo criado

-- =====================================================
-- SEÇÃO 3: EXCLUSÕES COM JOINS
-- =====================================================

PROMPT ===== 3.1 - REMOVENDO MÚSICAS DE ÁLBUNS ESPECÍFICOS =====

-- Criar um álbum de teste para demonstração
INSERT INTO album (
    id_album, titulo, data_lancamento, numero_faixas, 
    duracao_total, tipo_album, id_artista
) VALUES (
    seq_album.NEXTVAL, 'Album Teste Exclusao', SYSDATE, 1, 180, 'SINGLE', 1
);

-- Inserir uma música no álbum de teste
INSERT INTO musica (
    id_musica, titulo, duracao, numero_faixa, id_album, id_genero
) VALUES (
    seq_musica.NEXTVAL, 'Musica Teste Exclusao', 180, 1, 
    (SELECT MAX(id_album) FROM album), 1
);

-- Verificar músicas do álbum de teste
SELECT 
    m.titulo,
    al.titulo AS album,
    ar.nome_artista
FROM musica m
JOIN album al ON m.id_album = al.id_album
JOIN artista ar ON al.id_artista = ar.id_artista
WHERE al.titulo = 'Album Teste Exclusao';

-- Remover músicas de álbuns de teste
DELETE FROM musica 
WHERE id_album IN (
    SELECT id_album 
    FROM album 
    WHERE titulo = 'Album Teste Exclusao'
);

-- Remover o álbum de teste
DELETE FROM album 
WHERE titulo = 'Album Teste Exclusao';

PROMPT ===== 3.2 - REMOVENDO ASSINATURAS CANCELADAS ANTIGAS =====

-- Verificar assinaturas canceladas há mais de 1 ano
SELECT 
    a.id_assinatura,
    u.nome_usuario,
    a.status_assinatura,
    a.data_fim,
    t.nome_plano
FROM assinatura a
JOIN usuario u ON a.id_usuario = u.id_usuario
JOIN tipo_assinatura t ON a.id_tipo_assinatura = t.id_tipo_assinatura
WHERE a.status_assinatura = 'CANCELADA'
  AND a.data_fim < SYSDATE - 365;

-- Criar uma assinatura cancelada antiga para demonstração
INSERT INTO assinatura (
    id_assinatura, data_inicio, data_fim, status_assinatura,
    id_usuario, id_tipo_assinatura
) VALUES (
    seq_assinatura.NEXTVAL, SYSDATE - 400, SYSDATE - 365, 'CANCELADA', 1, 1
);

-- Remover assinaturas canceladas há mais de 1 ano
DELETE FROM assinatura 
WHERE status_assinatura = 'CANCELADA' 
  AND data_fim < SYSDATE - 365;

-- =====================================================
-- SEÇÃO 4: EXCLUSÕES EM CASCATA CONTROLADAS
-- =====================================================

PROMPT ===== 4.1 - REMOVENDO ARTISTA E SUAS DEPENDÊNCIAS =====

-- Criar um artista de teste com dependências
INSERT INTO artista (
    id_artista, nome_artista, pais_origem, data_inicio_carreira
) VALUES (
    seq_artista.NEXTVAL, 'Artista Teste Exclusao', 'Brasil', SYSDATE
);

-- Criar álbum para o artista de teste
INSERT INTO album (
    id_album, titulo, data_lancamento, numero_faixas, 
    duracao_total, tipo_album, id_artista
) VALUES (
    seq_album.NEXTVAL, 'Album do Artista Teste', SYSDATE, 1, 200, 'SINGLE',
    (SELECT MAX(id_artista) FROM artista WHERE nome_artista = 'Artista Teste Exclusao')
);

-- Verificar estrutura criada
SELECT 
    ar.nome_artista,
    al.titulo AS album,
    COUNT(m.id_musica) AS total_musicas
FROM artista ar
LEFT JOIN album al ON ar.id_artista = al.id_artista
LEFT JOIN musica m ON al.id_album = m.id_album
WHERE ar.nome_artista = 'Artista Teste Exclusao'
GROUP BY ar.id_artista, ar.nome_artista, al.titulo;

-- Remover o artista (CASCADE remove álbuns e músicas automaticamente)
DELETE FROM artista 
WHERE nome_artista = 'Artista Teste Exclusao';

PROMPT ===== 4.2 - LIMPEZA DE PLAYLIST COM VERIFICAÇÃO =====

-- Verificar playlists antes da exclusão
SELECT 
    p.nome_playlist,
    u.nome_usuario,
    p.total_musicas,
    COUNT(pm.id_musica) AS musicas_vinculadas
FROM playlist p
JOIN usuario u ON p.id_usuario = u.id_usuario
LEFT JOIN playlist_musica pm ON p.id_playlist = pm.id_playlist
GROUP BY p.id_playlist, p.nome_playlist, u.nome_usuario, p.total_musicas;

-- Remover vinculações de músicas nas playlists primeiro (exemplo específico)
DELETE FROM playlist_musica 
WHERE id_playlist IN (
    SELECT id_playlist 
    FROM playlist 
    WHERE nome_playlist LIKE '%Teste%'
);

-- Agora remover as playlists de teste
DELETE FROM playlist 
WHERE nome_playlist LIKE '%Teste%';

-- =====================================================
-- SEÇÃO 5: EXCLUSÕES COM SAVEPOINTS
-- =====================================================

PROMPT ===== 5.1 - EXCLUSÃO COM CONTROLE DE TRANSAÇÃO =====

-- Iniciar transação controlada
SAVEPOINT antes_exclusao_controlada;

-- Contar registros antes
SELECT COUNT(*) AS total_antes FROM historico_reproducao;

-- Simular exclusão problemática
BEGIN
    -- Tentar remover histórico de um usuário específico
    DELETE FROM historico_reproducao 
    WHERE id_usuario = 999; -- Usuário que não existe
    
    -- Verificar se a exclusão afetou algum registro
    IF SQL%ROWCOUNT = 0 THEN
        DBMS_OUTPUT.PUT_LINE('Nenhum registro foi excluído - operação segura');
    ELSE
        DBMS_OUTPUT.PUT_LINE('Registros excluídos: ' || SQL%ROWCOUNT);
    END IF;
    
EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK TO antes_exclusao_controlada;
        DBMS_OUTPUT.PUT_LINE('Erro na exclusão: ' || SQLERRM);
END;
/

-- Contar registros depois
SELECT COUNT(*) AS total_depois FROM historico_reproducao;

PROMPT ===== 5.2 - EXCLUSÃO EM LOTE COM CHECKPOINT =====

-- Exclusão em lote de registros antigos com controle
DECLARE
    v_batch_size NUMBER := 100;
    v_total_deleted NUMBER := 0;
    v_rows_deleted NUMBER;
BEGIN
    LOOP
        SAVEPOINT batch_checkpoint;
        
        -- Excluir um lote de registros antigos
        DELETE FROM historico_reproducao 
        WHERE data_reproducao < SYSDATE - 730 -- 2 anos
          AND ROWNUM <= v_batch_size;
        
        v_rows_deleted := SQL%ROWCOUNT;
        v_total_deleted := v_total_deleted + v_rows_deleted;
        
        -- Se não há mais registros para excluir, sair do loop
        EXIT WHEN v_rows_deleted = 0;
        
        -- Commit do lote
        COMMIT;
        
        -- Log do progresso
        DBMS_OUTPUT.PUT_LINE('Lote processado: ' || v_rows_deleted || ' registros');
        
    END LOOP;
    
    DBMS_OUTPUT.PUT_LINE('Total excluído: ' || v_total_deleted || ' registros');
    
EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK TO batch_checkpoint;
        DBMS_OUTPUT.PUT_LINE('Erro no processamento em lote: ' || SQLERRM);
END;
/

-- =====================================================
-- SEÇÃO 6: EXCLUSÕES DE MANUTENÇÃO
-- =====================================================

PROMPT ===== 6.1 - LIMPEZA DE DADOS DUPLICADOS =====

-- Identificar possíveis duplicatas no histórico
SELECT 
    id_usuario,
    id_musica,
    TRUNC(data_reproducao) AS data_repr,
    COUNT(*) AS total_reproducoes
FROM historico_reproducao
GROUP BY id_usuario, id_musica, TRUNC(data_reproducao)
HAVING COUNT(*) > 10; -- Mais de 10 reproduções da mesma música no mesmo dia

-- Criar duplicatas para demonstração
INSERT INTO historico_reproducao (
    id_historico, id_usuario, id_musica, data_reproducao, duracao_ouvida
) VALUES (
    seq_historico.NEXTVAL, 1, 1, SYSDATE, 30
);

INSERT INTO historico_reproducao (
    id_historico, id_usuario, id_musica, data_reproducao, duracao_ouvida
) VALUES (
    seq_historico.NEXTVAL, 1, 1, SYSDATE, 30
);

-- Remover duplicatas mantendo apenas a mais recente de cada grupo
DELETE FROM historico_reproducao hr1
WHERE EXISTS (
    SELECT 1 FROM historico_reproducao hr2
    WHERE hr2.id_usuario = hr1.id_usuario
      AND hr2.id_musica = hr1.id_musica
      AND TRUNC(hr2.data_reproducao) = TRUNC(hr1.data_reproducao)
      AND hr2.id_historico > hr1.id_historico
      AND hr2.duracao_ouvida = hr1.duracao_ouvida
);

PROMPT ===== 6.2 - LIMPEZA DE DADOS ÓRFÃOS =====

-- Verificar se há dados órfãos (não deveria haver devido às FKs)
SELECT 'Músicas órfãs' AS tipo, COUNT(*) AS total
FROM musica m
WHERE NOT EXISTS (SELECT 1 FROM album al WHERE al.id_album = m.id_album)
UNION ALL
SELECT 'Playlists órfãs', COUNT(*)
FROM playlist p
WHERE NOT EXISTS (SELECT 1 FROM usuario u WHERE u.id_usuario = p.id_usuario)
UNION ALL
SELECT 'Histórico órfão', COUNT(*)
FROM historico_reproducao hr
WHERE NOT EXISTS (SELECT 1 FROM usuario u WHERE u.id_usuario = hr.id_usuario)
   OR NOT EXISTS (SELECT 1 FROM musica m WHERE m.id_musica = hr.id_musica);

-- =====================================================
-- SEÇÃO 7: EXCLUSÕES DE SEGURANÇA
-- =====================================================

PROMPT ===== 7.1 - REMOVENDO DADOS SENSÍVEIS =====

-- Simular remoção de dados pessoais sensíveis (LGPD/GDPR)
-- Em um cenário real, isso seria feito com muito cuidado

-- Criar usuário para teste de exclusão
INSERT INTO usuario (
    id_usuario, nome_usuario, email, senha, data_nascimento, pais
) VALUES (
    seq_usuario.NEXTVAL, 'Usuario Para Exclusao', 'exclusao@test.com', 
    'senha123', DATE '1990-01-01', 'Brasil'
);

-- Anonimizar dados do usuário em vez de excluir completamente
UPDATE usuario 
SET nome_usuario = 'Usuário Removido',
    email = 'removido@' || id_usuario || '.sistema',
    senha = 'REMOVIDO',
    data_nascimento = NULL
WHERE email = 'exclusao@test.com';

-- Verificar anonimização
SELECT nome_usuario, email, data_nascimento 
FROM usuario 
WHERE email LIKE 'removido@%sistema';

PROMPT ===== 7.2 - LIMPEZA DE LOGS ANTIGOS =====

-- Simular exclusão de logs de auditoria antigos
-- (Não temos tabela de logs, mas seria similar ao histórico)

-- Manter apenas logs dos últimos 90 dias para auditoria
DELETE FROM historico_reproducao 
WHERE data_reproducao < SYSDATE - 90
  AND id_usuario = (SELECT id_usuario FROM usuario WHERE email LIKE 'removido@%sistema' AND ROWNUM = 1);

-- =====================================================
-- SEÇÃO 8: VERIFICAÇÃO FINAL
-- =====================================================

PROMPT ===== 8.1 - RELATÓRIO DE LIMPEZA =====

-- Verificar integridade após exclusões
SELECT 
    'Total Usuários' AS metrica,
    COUNT(*) AS valor
FROM usuario
UNION ALL
SELECT 'Total Artistas', COUNT(*) FROM artista
UNION ALL
SELECT 'Total Álbuns', COUNT(*) FROM album
UNION ALL
SELECT 'Total Músicas', COUNT(*) FROM musica
UNION ALL
SELECT 'Total Playlists', COUNT(*) FROM playlist
UNION ALL
SELECT 'Total Reproduções', COUNT(*) FROM historico_reproducao
UNION ALL
SELECT 'Total Assinaturas', COUNT(*) FROM assinatura;

PROMPT ===== 8.2 - VERIFICAÇÃO DE INTEGRIDADE REFERENCIAL =====

-- Verificar se não há registros órfãos após as exclusões
SELECT 
    'Problemas de integridade encontrados' AS status,
    COUNT(*) AS total
FROM (
    -- Verificações de integridade que não deveriam retornar registros
    SELECT 1 FROM musica m 
    WHERE NOT EXISTS (SELECT 1 FROM album al WHERE al.id_album = m.id_album)
    UNION ALL
    SELECT 1 FROM album al 
    WHERE NOT EXISTS (SELECT 1 FROM artista ar WHERE ar.id_artista = al.id_artista)
    UNION ALL
    SELECT 1 FROM playlist p 
    WHERE NOT EXISTS (SELECT 1 FROM usuario u WHERE u.id_usuario = p.id_usuario)
    UNION ALL
    SELECT 1 FROM historico_reproducao hr 
    WHERE NOT EXISTS (SELECT 1 FROM usuario u WHERE u.id_usuario = hr.id_usuario)
       OR NOT EXISTS (SELECT 1 FROM musica m WHERE m.id_musica = hr.id_musica)
) verificacoes;

-- Cleanup final - remover dados de teste criados
DELETE FROM usuario WHERE email LIKE 'removido@%sistema';

COMMIT;

-- Limpar tabela de backup se criada
DROP TABLE historico_backup;

PROMPT =====================================================
PROMPT OPERAÇÕES DE EXCLUSÃO CONCLUÍDAS!
PROMPT Próximo passo: Execute 06-consultas-avancadas.sql
PROMPT =====================================================