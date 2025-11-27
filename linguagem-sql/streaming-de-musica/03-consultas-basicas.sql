-- =====================================================
-- SISTEMA MUSISTREAM - CONSULTAS BÁSICAS
-- =====================================================
-- Script demonstrando operações READ (CRUD)
-- Consultas fundamentais do sistema de streaming
-- =====================================================

-- Configurações iniciais
SET ECHO ON
SET FEEDBACK ON
SET PAGESIZE 30
SET LINESIZE 120

PROMPT =====================================================
PROMPT CONSULTAS BÁSICAS DO SISTEMA MUSISTREAM
PROMPT =====================================================

-- =====================================================
-- SEÇÃO 1: CONSULTAS SIMPLES (SELECT BÁSICO)
-- =====================================================

PROMPT ===== 1.1 - LISTAGEM DE TODOS OS GÊNEROS =====
SELECT * FROM genero ORDER BY nome_genero;

PROMPT ===== 1.2 - LISTAGEM DE TODOS OS USUÁRIOS =====
SELECT 
    id_usuario,
    nome_usuario,
    email,
    pais,
    TO_CHAR(data_cadastro, 'DD/MM/YYYY') AS data_cadastro
FROM usuario 
ORDER BY nome_usuario;

PROMPT ===== 1.3 - LISTAGEM DE ARTISTAS BRASILEIROS =====
SELECT 
    nome_artista,
    nome_real,
    pais_origem,
    TO_CHAR(data_nascimento, 'DD/MM/YYYY') AS nascimento
FROM artista 
WHERE pais_origem = 'Brasil'
ORDER BY nome_artista;

-- =====================================================
-- SEÇÃO 2: CONSULTAS COM FILTROS E CONDIÇÕES
-- =====================================================

PROMPT ===== 2.1 - MÚSICAS COM DURAÇÃO MAIOR QUE 5 MINUTOS =====
SELECT 
    titulo,
    FLOOR(duracao / 60) || ':' || LPAD(MOD(duracao, 60), 2, '0') AS duracao_formatada,
    total_reproducoes
FROM musica 
WHERE duracao > 300
ORDER BY duracao DESC;

PROMPT ===== 2.2 - ÁLBUNS LANÇADOS APÓS 2000 =====
SELECT 
    titulo,
    TO_CHAR(data_lancamento, 'DD/MM/YYYY') AS lancamento,
    numero_faixas,
    tipo_album
FROM album 
WHERE data_lancamento > DATE '2000-01-01'
ORDER BY data_lancamento;

PROMPT ===== 2.3 - USUÁRIOS POR PAÍS =====
SELECT 
    pais,
    COUNT(*) AS total_usuarios
FROM usuario 
GROUP BY pais
ORDER BY total_usuarios DESC;

-- =====================================================
-- SEÇÃO 3: CONSULTAS COM JOINS BÁSICOS
-- =====================================================

PROMPT ===== 3.1 - MÚSICAS COM INFORMAÇÕES DO ARTISTA =====
SELECT 
    m.titulo AS musica,
    ar.nome_artista,
    al.titulo AS album,
    g.nome_genero,
    FLOOR(m.duracao / 60) || ':' || LPAD(MOD(m.duracao, 60), 2, '0') AS duracao
FROM musica m
JOIN album al ON m.id_album = al.id_album
JOIN artista ar ON al.id_artista = ar.id_artista
LEFT JOIN genero g ON m.id_genero = g.id_genero
ORDER BY ar.nome_artista, al.titulo, m.numero_faixa;

PROMPT ===== 3.2 - PLAYLISTS COM INFORMAÇÕES DO USUÁRIO =====
SELECT 
    p.nome_playlist,
    u.nome_usuario AS criador,
    p.total_musicas,
    CASE p.publica 
        WHEN 'S' THEN 'Pública'
        ELSE 'Privada'
    END AS visibilidade,
    TO_CHAR(p.data_criacao, 'DD/MM/YYYY') AS criada_em
FROM playlist p
JOIN usuario u ON p.id_usuario = u.id_usuario
ORDER BY p.total_musicas DESC;

PROMPT ===== 3.3 - ASSINATURAS ATIVAS COM DETALHES =====
SELECT 
    u.nome_usuario,
    u.email,
    t.nome_plano,
    t.preco_mensal,
    TO_CHAR(a.data_inicio, 'DD/MM/YYYY') AS inicio,
    a.status_assinatura
FROM assinatura a
JOIN usuario u ON a.id_usuario = u.id_usuario
JOIN tipo_assinatura t ON a.id_tipo_assinatura = t.id_tipo_assinatura
WHERE a.status_assinatura = 'ATIVA'
ORDER BY t.preco_mensal DESC;

-- =====================================================
-- SEÇÃO 4: CONSULTAS COM FUNÇÕES AGREGADAS
-- =====================================================

PROMPT ===== 4.1 - ESTATÍSTICAS GERAIS DO SISTEMA =====
SELECT 
    'Total de Usuários' AS metrica,
    COUNT(*) AS valor
FROM usuario
UNION ALL
SELECT 'Total de Artistas', COUNT(*) FROM artista
UNION ALL
SELECT 'Total de Álbuns', COUNT(*) FROM album
UNION ALL
SELECT 'Total de Músicas', COUNT(*) FROM musica
UNION ALL
SELECT 'Total de Playlists', COUNT(*) FROM playlist
UNION ALL
SELECT 'Total de Reproduções', COUNT(*) FROM historico_reproducao;

PROMPT ===== 4.2 - ARTISTAS MAIS PROLÍFICOS =====
SELECT 
    ar.nome_artista,
    COUNT(al.id_album) AS total_albums,
    COUNT(m.id_musica) AS total_musicas,
    ROUND(AVG(m.duracao), 0) AS duracao_media_segundos
FROM artista ar
LEFT JOIN album al ON ar.id_artista = al.id_artista
LEFT JOIN musica m ON al.id_album = m.id_album
GROUP BY ar.id_artista, ar.nome_artista
HAVING COUNT(al.id_album) > 0
ORDER BY total_albums DESC, total_musicas DESC;

PROMPT ===== 4.3 - GÊNEROS MAIS POPULARES =====
SELECT 
    g.nome_genero,
    COUNT(m.id_musica) AS total_musicas,
    SUM(m.total_reproducoes) AS total_plays
FROM genero g
LEFT JOIN musica m ON g.id_genero = m.id_genero
GROUP BY g.id_genero, g.nome_genero
ORDER BY total_plays DESC NULLS LAST;

-- =====================================================
-- SEÇÃO 5: CONSULTAS DE ANÁLISE COMPORTAMENTAL
-- =====================================================

PROMPT ===== 5.1 - TOP 10 MÚSICAS MAIS TOCADAS =====
SELECT 
    ROWNUM AS ranking,
    titulo,
    nome_artista,
    total_reproducoes
FROM (
    SELECT 
        m.titulo,
        ar.nome_artista,
        m.total_reproducoes
    FROM musica m
    JOIN album al ON m.id_album = al.id_album
    JOIN artista ar ON al.id_artista = ar.id_artista
    ORDER BY m.total_reproducoes DESC
)
WHERE ROWNUM <= 10;

PROMPT ===== 5.2 - USUÁRIOS MAIS ATIVOS =====
SELECT 
    u.nome_usuario,
    COUNT(hr.id_historico) AS total_reproducoes,
    COUNT(DISTINCT hr.id_musica) AS musicas_diferentes,
    MAX(hr.data_reproducao) AS ultima_atividade
FROM usuario u
LEFT JOIN historico_reproducao hr ON u.id_usuario = hr.id_usuario
GROUP BY u.id_usuario, u.nome_usuario
ORDER BY total_reproducoes DESC;

PROMPT ===== 5.3 - DISPOSITIVOS MAIS UTILIZADOS =====
SELECT 
    dispositivo,
    COUNT(*) AS total_uso,
    ROUND(COUNT(*) * 100.0 / (SELECT COUNT(*) FROM historico_reproducao), 2) AS percentual
FROM historico_reproducao
WHERE dispositivo IS NOT NULL
GROUP BY dispositivo
ORDER BY total_uso DESC;

-- =====================================================
-- SEÇÃO 6: CONSULTAS TEMPORAIS
-- =====================================================

PROMPT ===== 6.1 - ATIVIDADE POR DIA DA SEMANA =====
SELECT 
    TO_CHAR(data_reproducao, 'Day') AS dia_semana,
    COUNT(*) AS total_reproducoes
FROM historico_reproducao
GROUP BY TO_CHAR(data_reproducao, 'Day'), TO_CHAR(data_reproducao, 'D')
ORDER BY TO_CHAR(data_reproducao, 'D');

PROMPT ===== 6.2 - REPRODUÇÕES POR HORA DO DIA =====
SELECT 
    EXTRACT(HOUR FROM data_reproducao) AS hora,
    COUNT(*) AS total_reproducoes
FROM historico_reproducao
GROUP BY EXTRACT(HOUR FROM data_reproducao)
ORDER BY hora;

PROMPT ===== 6.3 - EVOLUÇÃO DE CADASTROS POR MÊS =====
SELECT 
    TO_CHAR(data_cadastro, 'YYYY-MM') AS mes,
    COUNT(*) AS novos_usuarios
FROM usuario
GROUP BY TO_CHAR(data_cadastro, 'YYYY-MM')
ORDER BY mes;

-- =====================================================
-- SEÇÃO 7: CONSULTAS DE RECEITA E ASSINATURAS
-- =====================================================

PROMPT ===== 7.1 - RECEITA POR TIPO DE PLANO =====
SELECT 
    t.nome_plano,
    COUNT(a.id_assinatura) AS assinantes_ativos,
    SUM(t.preco_mensal) AS receita_mensal_potencial
FROM tipo_assinatura t
LEFT JOIN assinatura a ON t.id_tipo_assinatura = a.id_tipo_assinatura 
    AND a.status_assinatura = 'ATIVA'
GROUP BY t.id_tipo_assinatura, t.nome_plano, t.preco_mensal
ORDER BY receita_mensal_potencial DESC;

PROMPT ===== 7.2 - DISTRIBUIÇÃO DE USUÁRIOS POR PLANO =====
SELECT 
    t.nome_plano,
    COUNT(a.id_assinatura) AS total_usuarios,
    ROUND(COUNT(a.id_assinatura) * 100.0 / (
        SELECT COUNT(*) FROM assinatura WHERE status_assinatura = 'ATIVA'
    ), 2) AS percentual
FROM tipo_assinatura t
LEFT JOIN assinatura a ON t.id_tipo_assinatura = a.id_tipo_assinatura 
    AND a.status_assinatura = 'ATIVA'
GROUP BY t.id_tipo_assinatura, t.nome_plano
ORDER BY total_usuarios DESC;

-- =====================================================
-- SEÇÃO 8: CONSULTAS GEOGRÁFICAS
-- =====================================================

PROMPT ===== 8.1 - DISTRIBUIÇÃO GEOGRÁFICA DE USUÁRIOS =====
SELECT 
    pais,
    COUNT(*) AS total_usuarios,
    ROUND(COUNT(*) * 100.0 / (SELECT COUNT(*) FROM usuario), 2) AS percentual
FROM usuario
GROUP BY pais
ORDER BY total_usuarios DESC;

PROMPT ===== 8.2 - ARTISTAS POR PAÍS DE ORIGEM =====
SELECT 
    pais_origem,
    COUNT(*) AS total_artistas,
    LISTAGG(nome_artista, ', ') WITHIN GROUP (ORDER BY nome_artista) AS artistas
FROM artista
WHERE pais_origem IS NOT NULL
GROUP BY pais_origem
ORDER BY total_artistas DESC;

-- =====================================================
-- SEÇÃO 9: CONSULTAS DE PLAYLISTS
-- =====================================================

PROMPT ===== 9.1 - DETALHES DAS PLAYLISTS PÚBLICAS =====
SELECT 
    p.nome_playlist,
    u.nome_usuario AS criador,
    p.total_musicas,
    FLOOR(p.duracao_total / 60) || ':' || LPAD(MOD(p.duracao_total, 60), 2, '0') AS duracao_total,
    TO_CHAR(p.data_criacao, 'DD/MM/YYYY') AS criada_em
FROM playlist p
JOIN usuario u ON p.id_usuario = u.id_usuario
WHERE p.publica = 'S'
ORDER BY p.total_musicas DESC;

PROMPT ===== 9.2 - MÚSICAS EM CADA PLAYLIST =====
SELECT 
    p.nome_playlist,
    m.titulo AS musica,
    ar.nome_artista,
    pm.ordem_musica,
    FLOOR(m.duracao / 60) || ':' || LPAD(MOD(m.duracao, 60), 2, '0') AS duracao
FROM playlist p
JOIN playlist_musica pm ON p.id_playlist = pm.id_playlist
JOIN musica m ON pm.id_musica = m.id_musica
JOIN album al ON m.id_album = al.id_album
JOIN artista ar ON al.id_artista = ar.id_artista
ORDER BY p.nome_playlist, pm.ordem_musica;

-- =====================================================
-- SEÇÃO 10: CONSULTAS DE VALIDAÇÃO
-- =====================================================

PROMPT ===== 10.1 - VERIFICAÇÃO DE INTEGRIDADE DOS DADOS =====

-- Músicas sem gênero
SELECT 'Músicas sem gênero' AS problema, COUNT(*) AS total
FROM musica WHERE id_genero IS NULL
UNION ALL
-- Álbuns sem músicas
SELECT 'Álbuns sem músicas', COUNT(*)
FROM album al
WHERE NOT EXISTS (SELECT 1 FROM musica m WHERE m.id_album = al.id_album)
UNION ALL
-- Usuários sem assinatura
SELECT 'Usuários sem assinatura', COUNT(*)
FROM usuario u
WHERE NOT EXISTS (SELECT 1 FROM assinatura a WHERE a.id_usuario = u.id_usuario);

PROMPT ===== 10.2 - CONSISTÊNCIA DE CONTADORES =====

-- Verificar se os contadores das playlists estão corretos
SELECT 
    p.nome_playlist,
    p.total_musicas AS contador_playlist,
    COUNT(pm.id_musica) AS musicas_reais,
    CASE 
        WHEN p.total_musicas = COUNT(pm.id_musica) THEN 'OK'
        ELSE 'INCONSISTENTE'
    END AS status
FROM playlist p
LEFT JOIN playlist_musica pm ON p.id_playlist = pm.id_playlist
GROUP BY p.id_playlist, p.nome_playlist, p.total_musicas
ORDER BY p.nome_playlist;

PROMPT =====================================================
PROMPT CONSULTAS BÁSICAS CONCLUÍDAS!
PROMPT Próximo passo: Execute 04-atualizacoes.sql
PROMPT =====================================================