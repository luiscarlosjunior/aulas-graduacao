-- =====================================================
-- SISTEMA MUSISTREAM - OPERAÇÕES DE ATUALIZAÇÃO
-- =====================================================
-- Script demonstrando operações UPDATE (CRUD)
-- Atualizações e modificações no sistema de streaming
-- =====================================================

-- Configurações iniciais
SET ECHO ON
SET FEEDBACK ON
SET PAGESIZE 30
SET LINESIZE 120

PROMPT =====================================================
PROMPT OPERAÇÕES DE ATUALIZAÇÃO NO SISTEMA MUSISTREAM
PROMPT =====================================================

-- =====================================================
-- SEÇÃO 1: ATUALIZAÇÕES BÁSICAS
-- =====================================================

PROMPT ===== 1.1 - ATUALIZANDO INFORMAÇÕES DE USUÁRIO =====

-- Mostrar dados antes da atualização
SELECT nome_usuario, email, pais, ultimo_acesso 
FROM usuario WHERE id_usuario = 1;

-- Atualizar último acesso do usuário
UPDATE usuario 
SET ultimo_acesso = CURRENT_TIMESTAMP,
    pais = 'Brasil'
WHERE id_usuario = 1;

-- Mostrar dados após atualização
SELECT nome_usuario, email, pais, ultimo_acesso 
FROM usuario WHERE id_usuario = 1;

PROMPT ===== 1.2 - CORRIGINDO DADOS DE ARTISTA =====

-- Verificar dados antes
SELECT nome_artista, website, data_inicio_carreira 
FROM artista WHERE nome_artista = 'Daft Punk';

-- Atualizar informações do artista
UPDATE artista 
SET website = 'www.daftpunk.com',
    biografia = 'Dupla francesa de música eletrônica formada por Thomas Bangalter e Guy-Manuel de Homem-Christo.'
WHERE nome_artista = 'Daft Punk';

-- Verificar após atualização
SELECT nome_artista, website, biografia 
FROM artista WHERE nome_artista = 'Daft Punk';

PROMPT ===== 1.3 - ATUALIZANDO PREÇOS DE ASSINATURA =====

-- Mostrar preços atuais
SELECT nome_plano, preco_mensal 
FROM tipo_assinatura 
ORDER BY preco_mensal;

-- Aplicar reajuste de 10% nos planos pagos
UPDATE tipo_assinatura 
SET preco_mensal = preco_mensal * 1.10
WHERE preco_mensal > 0;

-- Mostrar preços após reajuste
SELECT nome_plano, preco_mensal 
FROM tipo_assinatura 
ORDER BY preco_mensal;

-- =====================================================
-- SEÇÃO 2: ATUALIZAÇÕES CONDICIONAIS
-- =====================================================

PROMPT ===== 2.1 - ATIVANDO/DESATIVANDO USUÁRIOS POR INATIVIDADE =====

-- Verificar usuários sem atividade recente
SELECT 
    u.nome_usuario,
    u.ativo,
    MAX(hr.data_reproducao) AS ultima_atividade,
    CASE 
        WHEN MAX(hr.data_reproducao) < SYSDATE - 30 OR MAX(hr.data_reproducao) IS NULL 
        THEN 'Inativo há mais de 30 dias'
        ELSE 'Ativo'
    END AS status_atividade
FROM usuario u
LEFT JOIN historico_reproducao hr ON u.id_usuario = hr.id_usuario
GROUP BY u.id_usuario, u.nome_usuario, u.ativo;

-- Marcar como inativo usuários sem atividade há mais de 30 dias
UPDATE usuario 
SET ativo = 'N'
WHERE id_usuario IN (
    SELECT u.id_usuario
    FROM usuario u
    LEFT JOIN historico_reproducao hr ON u.id_usuario = hr.id_usuario
    GROUP BY u.id_usuario
    HAVING MAX(hr.data_reproducao) < SYSDATE - 30 OR MAX(hr.data_reproducao) IS NULL
);

PROMPT ===== 2.2 - ATUALIZANDO STATUS DE ASSINATURAS EXPIRADAS =====

-- Verificar assinaturas que deveriam estar expiradas
SELECT 
    u.nome_usuario,
    a.status_assinatura,
    a.data_inicio,
    a.data_fim,
    CASE 
        WHEN a.data_fim < SYSDATE THEN 'Deveria estar EXPIRADA'
        ELSE 'OK'
    END AS analise
FROM assinatura a
JOIN usuario u ON a.id_usuario = u.id_usuario
WHERE a.data_fim IS NOT NULL;

-- Atualizar status das assinaturas expiradas
UPDATE assinatura 
SET status_assinatura = 'EXPIRADA'
WHERE data_fim < SYSDATE 
  AND status_assinatura = 'ATIVA';

-- =====================================================
-- SEÇÃO 3: ATUALIZAÇÕES COM JOINS
-- =====================================================

PROMPT ===== 3.1 - ATUALIZANDO GÊNERO DAS MÚSICAS BASEADO NO ARTISTA =====

-- Verificar músicas sem gênero de artistas brasileiros
SELECT 
    m.titulo,
    ar.nome_artista,
    ar.pais_origem,
    g.nome_genero
FROM musica m
JOIN album al ON m.id_album = al.id_album
JOIN artista ar ON al.id_artista = ar.id_artista
LEFT JOIN genero g ON m.id_genero = g.id_genero
WHERE ar.pais_origem = 'Brasil';

-- Atualizar músicas de artistas brasileiros para MPB (se não tiverem gênero)
UPDATE musica 
SET id_genero = (SELECT id_genero FROM genero WHERE nome_genero = 'MPB')
WHERE id_genero IS NULL 
  AND id_album IN (
      SELECT al.id_album
      FROM album al
      JOIN artista ar ON al.id_artista = ar.id_artista
      WHERE ar.pais_origem = 'Brasil'
  );

PROMPT ===== 3.2 - ATUALIZANDO BIOGRAFIA DE ARTISTAS SEM INFORMAÇÃO =====

-- Identificar artistas sem biografia
SELECT nome_artista, pais_origem, biografia
FROM artista 
WHERE biografia IS NULL;

-- Atualizar com biografia padrão baseada no país
UPDATE artista 
SET biografia = 'Artista de ' || NVL(pais_origem, 'origem não informada') || ' com contribuições significativas para a música.'
WHERE biografia IS NULL;

-- =====================================================
-- SEÇÃO 4: ATUALIZAÇÕES EM LOTE COM SUBQUERIES
-- =====================================================

PROMPT ===== 4.1 - ATUALIZANDO CONTADOR DE REPRODUÇÕES DAS MÚSICAS =====

-- Verificar contadores atuais vs histórico real
SELECT 
    m.titulo,
    m.total_reproducoes AS contador_atual,
    COUNT(hr.id_historico) AS reproducoes_reais
FROM musica m
LEFT JOIN historico_reproducao hr ON m.id_musica = hr.id_musica
GROUP BY m.id_musica, m.titulo, m.total_reproducoes
ORDER BY m.titulo;

-- Corrigir contadores baseado no histórico real
UPDATE musica 
SET total_reproducoes = (
    SELECT COUNT(*)
    FROM historico_reproducao hr
    WHERE hr.id_musica = musica.id_musica
);

-- Verificar após correção
SELECT 
    m.titulo,
    m.total_reproducoes AS contador_corrigido,
    COUNT(hr.id_historico) AS reproducoes_reais
FROM musica m
LEFT JOIN historico_reproducao hr ON m.id_musica = hr.id_musica
GROUP BY m.id_musica, m.titulo, m.total_reproducoes
ORDER BY m.total_reproducoes DESC;

PROMPT ===== 4.2 - RECALCULANDO ESTATÍSTICAS DAS PLAYLISTS =====

-- Verificar estatísticas atuais das playlists
SELECT 
    p.nome_playlist,
    p.total_musicas AS contador_atual,
    p.duracao_total AS duracao_atual,
    COUNT(pm.id_musica) AS musicas_reais,
    SUM(m.duracao) AS duracao_real
FROM playlist p
LEFT JOIN playlist_musica pm ON p.id_playlist = pm.id_playlist
LEFT JOIN musica m ON pm.id_musica = m.id_musica
GROUP BY p.id_playlist, p.nome_playlist, p.total_musicas, p.duracao_total;

-- Recalcular contadores das playlists
UPDATE playlist 
SET total_musicas = (
        SELECT COUNT(*)
        FROM playlist_musica pm
        WHERE pm.id_playlist = playlist.id_playlist
    ),
    duracao_total = (
        SELECT NVL(SUM(m.duracao), 0)
        FROM playlist_musica pm
        JOIN musica m ON pm.id_musica = m.id_musica
        WHERE pm.id_playlist = playlist.id_playlist
    ),
    data_atualizacao = CURRENT_TIMESTAMP;

-- =====================================================
-- SEÇÃO 5: ATUALIZAÇÕES DE DADOS TEMPORAIS
-- =====================================================

PROMPT ===== 5.1 - RENOVANDO ASSINATURAS PRÓXIMAS DO VENCIMENTO =====

-- Verificar assinaturas próximas do vencimento (próximos 7 dias)
SELECT 
    u.nome_usuario,
    t.nome_plano,
    a.data_fim,
    a.renovacao_automatica,
    CASE 
        WHEN a.renovacao_automatica = 'S' THEN 'Será renovada automaticamente'
        ELSE 'Precisa renovar manualmente'
    END AS status_renovacao
FROM assinatura a
JOIN usuario u ON a.id_usuario = u.id_usuario
JOIN tipo_assinatura t ON a.id_tipo_assinatura = t.id_tipo_assinatura
WHERE a.data_fim BETWEEN SYSDATE AND SYSDATE + 7
  AND a.status_assinatura = 'ATIVA';

-- Renovar automaticamente assinaturas com renovação automática ativa
UPDATE assinatura 
SET data_fim = data_fim + INTERVAL '1' MONTH,
    data_ultimo_pagamento = SYSDATE
WHERE data_fim BETWEEN SYSDATE AND SYSDATE + 7
  AND status_assinatura = 'ATIVA'
  AND renovacao_automatica = 'S';

PROMPT ===== 5.2 - ATUALIZANDO DATAS DE ÚLTIMO ACESSO =====

-- Atualizar último acesso baseado no histórico de reprodução
UPDATE usuario 
SET ultimo_acesso = (
    SELECT MAX(hr.data_reproducao)
    FROM historico_reproducao hr
    WHERE hr.id_usuario = usuario.id_usuario
)
WHERE EXISTS (
    SELECT 1
    FROM historico_reproducao hr
    WHERE hr.id_usuario = usuario.id_usuario
);

-- =====================================================
-- SEÇÃO 6: ATUALIZAÇÕES DE CLASSIFICAÇÃO E TAGS
-- =====================================================

PROMPT ===== 6.1 - MARCANDO PLAYLISTS COMO PÚBLICAS BASEADO NO TAMANHO =====

-- Verificar playlists que poderiam ser públicas
SELECT 
    nome_playlist,
    total_musicas,
    publica,
    CASE 
        WHEN total_musicas >= 10 THEN 'Candidata a pública'
        ELSE 'Muito pequena'
    END AS recomendacao
FROM playlist
ORDER BY total_musicas DESC;

-- Sugerir tornar públicas playlists com mais de 10 músicas (apenas exemplo)
-- Na prática, isso seria uma decisão do usuário
UPDATE playlist 
SET publica = 'S'
WHERE total_musicas >= 15
  AND publica = 'N'
  AND id_usuario IN (1, 2, 3); -- Apenas alguns usuários como exemplo

PROMPT ===== 6.2 - ATUALIZANDO QUALIDADE DE REPRODUÇÃO BASEADA NA ASSINATURA =====

-- Simular atualização de qualidade baseada no plano do usuário
-- (Isso seria feito normalmente pela aplicação, não diretamente no banco)

-- Mostrar histórico com qualidade atual
SELECT DISTINCT
    u.nome_usuario,
    t.nome_plano,
    hr.qualidade_reproducao
FROM historico_reproducao hr
JOIN usuario u ON hr.id_usuario = u.id_usuario
JOIN assinatura a ON u.id_usuario = a.id_usuario
JOIN tipo_assinatura t ON a.id_tipo_assinatura = t.id_tipo_assinatura
WHERE a.status_assinatura = 'ATIVA';

-- =====================================================
-- SEÇÃO 7: ATUALIZAÇÕES DE CORREÇÃO DE DADOS
-- =====================================================

PROMPT ===== 7.1 - PADRONIZANDO NOMES DE PAÍSES =====

-- Verificar variações nos nomes de países
SELECT DISTINCT pais FROM usuario ORDER BY pais;
SELECT DISTINCT pais_origem FROM artista ORDER BY pais_origem;

-- Padronizar nomes (exemplo)
UPDATE usuario SET pais = 'Estados Unidos' WHERE pais IN ('EUA', 'USA');
UPDATE artista SET pais_origem = 'Estados Unidos' WHERE pais_origem IN ('EUA', 'USA');

PROMPT ===== 7.2 - CORRIGINDO EMAILS INVÁLIDOS =====

-- Identificar emails potencialmente inválidos
SELECT nome_usuario, email
FROM usuario 
WHERE email NOT LIKE '%@%.%'
   OR email LIKE '%..%'
   OR email LIKE '@%'
   OR email LIKE '%@';

-- Marcar emails inválidos para revisão (adicionando sufixo)
UPDATE usuario 
SET email = email || '.INVALID'
WHERE email NOT LIKE '%@%.%'
   OR email LIKE '%..%'
   OR email LIKE '@%'
   OR email LIKE '%@';

-- =====================================================
-- SEÇÃO 8: ATUALIZAÇÕES COM TRANSAÇÕES
-- =====================================================

PROMPT ===== 8.1 - TRANSFERINDO PROPRIEDADE DE PLAYLIST =====

-- Simular transferência de playlist entre usuários
SAVEPOINT antes_transferencia;

-- Verificar playlist antes da transferência
SELECT 
    p.nome_playlist,
    u_atual.nome_usuario AS proprietario_atual,
    p.total_musicas
FROM playlist p
JOIN usuario u_atual ON p.id_usuario = u_atual.id_usuario
WHERE p.id_playlist = 1;

-- Transferir playlist (exemplo: usuário 1 para usuário 2)
UPDATE playlist 
SET id_usuario = 2,
    data_atualizacao = CURRENT_TIMESTAMP
WHERE id_playlist = 1;

-- Verificar após transferência
SELECT 
    p.nome_playlist,
    u_novo.nome_usuario AS novo_proprietario,
    p.total_musicas
FROM playlist p
JOIN usuario u_novo ON p.id_usuario = u_novo.id_usuario
WHERE p.id_playlist = 1;

-- Reverter transferência para exemplo
ROLLBACK TO antes_transferencia;

PROMPT ===== 8.2 - ATUALIZAÇÃO EM LOTE COM CONTROLE DE ERRO =====

-- Exemplo de atualização em lote com tratamento de erro
SAVEPOINT antes_atualizacao_lote;

BEGIN
    -- Tentar atualizar descrições de gêneros
    UPDATE genero 
    SET descricao = CASE nome_genero
        WHEN 'Rock' THEN 'Gênero musical caracterizado por guitarras elétricas, baixo e bateria'
        WHEN 'Pop' THEN 'Música popular com melodias acessíveis e amplo apelo comercial'
        WHEN 'Jazz' THEN 'Gênero musical com ênfase na improvisação e harmonias complexas'
        ELSE descricao
    END
    WHERE nome_genero IN ('Rock', 'Pop', 'Jazz');
    
    -- Confirmar se tudo correu bem
    COMMIT;
    DBMS_OUTPUT.PUT_LINE('Atualização em lote realizada com sucesso!');
    
EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK TO antes_atualizacao_lote;
        DBMS_OUTPUT.PUT_LINE('Erro na atualização: ' || SQLERRM);
END;
/

-- =====================================================
-- SEÇÃO 9: VERIFICAÇÃO DAS ATUALIZAÇÕES
-- =====================================================

PROMPT ===== 9.1 - RELATÓRIO DE ATUALIZAÇÕES REALIZADAS =====

-- Verificar logs de atualizações (simulado)
SELECT 
    'Usuários ativos' AS metrica,
    COUNT(*) AS valor
FROM usuario WHERE ativo = 'S'
UNION ALL
SELECT 'Assinaturas ativas', COUNT(*)
FROM assinatura WHERE status_assinatura = 'ATIVA'
UNION ALL
SELECT 'Playlists públicas', COUNT(*)
FROM playlist WHERE publica = 'S'
UNION ALL
SELECT 'Músicas com gênero', COUNT(*)
FROM musica WHERE id_genero IS NOT NULL;

PROMPT ===== 9.2 - INTEGRIDADE APÓS ATUALIZAÇÕES =====

-- Verificar se não há inconsistências após as atualizações
SELECT 
    'Playlists com contador incorreto' AS problema,
    COUNT(*) AS total
FROM playlist p
WHERE p.total_musicas != (
    SELECT COUNT(*) 
    FROM playlist_musica pm 
    WHERE pm.id_playlist = p.id_playlist
)
UNION ALL
SELECT 'Músicas com contador de reprodução incorreto',
       COUNT(*)
FROM musica m
WHERE m.total_reproducoes != (
    SELECT COUNT(*) 
    FROM historico_reproducao hr 
    WHERE hr.id_musica = m.id_musica
);

COMMIT;

PROMPT =====================================================
PROMPT OPERAÇÕES DE ATUALIZAÇÃO CONCLUÍDAS!
PROMPT Próximo passo: Execute 05-exclusoes.sql
PROMPT =====================================================