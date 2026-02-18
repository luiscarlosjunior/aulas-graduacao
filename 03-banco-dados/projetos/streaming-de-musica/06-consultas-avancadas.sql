-- =====================================================
-- SISTEMA MUSISTREAM - CONSULTAS AVANÇADAS
-- =====================================================
-- Script demonstrando conceitos avançados de SQL
-- Consolidando módulos 10-16 do curso
-- =====================================================

-- Configurações iniciais
SET ECHO ON
SET FEEDBACK ON
SET PAGESIZE 40
SET LINESIZE 150

PROMPT =====================================================
PROMPT CONSULTAS AVANÇADAS DO SISTEMA MUSISTREAM
PROMPT =====================================================

-- =====================================================
-- SEÇÃO 1: SUBCONSULTAS CORRELACIONADAS (Módulo 13)
-- =====================================================

PROMPT ===== 1.1 - USUÁRIOS COM REPRODUÇÕES ACIMA DA MÉDIA =====

-- Encontrar usuários que ouvem mais música que a média
SELECT 
    u.nome_usuario,
    u.pais,
    (SELECT COUNT(*) 
     FROM historico_reproducao hr 
     WHERE hr.id_usuario = u.id_usuario) AS total_reproducoes,
    (SELECT ROUND(AVG(total_por_usuario), 2)
     FROM (SELECT COUNT(*) AS total_por_usuario 
           FROM historico_reproducao 
           GROUP BY id_usuario)) AS media_geral
FROM usuario u
WHERE (SELECT COUNT(*) 
       FROM historico_reproducao hr 
       WHERE hr.id_usuario = u.id_usuario) > 
      (SELECT AVG(total_por_usuario)
       FROM (SELECT COUNT(*) AS total_por_usuario 
             FROM historico_reproducao 
             GROUP BY id_usuario))
ORDER BY total_reproducoes DESC;

PROMPT ===== 1.2 - ARTISTAS COM MÚSICAS MAIS LONGAS QUE SUA MÉDIA =====

-- Músicas de cada artista que são mais longas que a média do próprio artista
SELECT 
    ar.nome_artista,
    m.titulo,
    FLOOR(m.duracao / 60) || ':' || LPAD(MOD(m.duracao, 60), 2, '0') AS duracao,
    FLOOR(media_artista.duracao_media / 60) || ':' || 
    LPAD(MOD(ROUND(media_artista.duracao_media), 60), 2, '0') AS media_artista
FROM musica m
JOIN album al ON m.id_album = al.id_album
JOIN artista ar ON al.id_artista = ar.id_artista
JOIN (
    SELECT 
        ar2.id_artista,
        AVG(m2.duracao) AS duracao_media
    FROM artista ar2
    JOIN album al2 ON ar2.id_artista = al2.id_artista
    JOIN musica m2 ON al2.id_album = m2.id_album
    GROUP BY ar2.id_artista
) media_artista ON ar.id_artista = media_artista.id_artista
WHERE m.duracao > media_artista.duracao_media
ORDER BY ar.nome_artista, m.duracao DESC;

PROMPT ===== 1.3 - PLAYLISTS COM MAIS MÚSICAS QUE A MÉDIA DO USUÁRIO =====

-- Playlists que têm mais músicas que a média de playlists do mesmo usuário
SELECT 
    u.nome_usuario,
    p.nome_playlist,
    p.total_musicas,
    ROUND((SELECT AVG(p2.total_musicas) 
           FROM playlist p2 
           WHERE p2.id_usuario = p.id_usuario), 1) AS media_usuario
FROM playlist p
JOIN usuario u ON p.id_usuario = u.id_usuario
WHERE p.total_musicas > (
    SELECT AVG(p2.total_musicas) 
    FROM playlist p2 
    WHERE p2.id_usuario = p.id_usuario
)
ORDER BY u.nome_usuario, p.total_musicas DESC;

-- =====================================================
-- SEÇÃO 2: JOINS COMPLEXOS (Módulo 14)
-- =====================================================

PROMPT ===== 2.1 - ANÁLISE COMPLETA DE ATIVIDADE MUSICAL =====

-- Join complexo com múltiplas tabelas mostrando atividade completa
SELECT 
    u.nome_usuario,
    u.pais AS pais_usuario,
    COUNT(DISTINCT p.id_playlist) AS playlists_criadas,
    COUNT(DISTINCT hr.id_musica) AS musicas_diferentes_ouvidas,
    COUNT(hr.id_historico) AS total_reproducoes,
    COUNT(DISTINCT ar.id_artista) AS artistas_diferentes,
    COUNT(DISTINCT g.id_genero) AS generos_diferentes,
    ROUND(AVG(m.duracao), 0) AS duracao_media_musicas,
    MAX(hr.data_reproducao) AS ultima_atividade,
    t.nome_plano AS tipo_assinatura
FROM usuario u
LEFT JOIN playlist p ON u.id_usuario = p.id_usuario
LEFT JOIN historico_reproducao hr ON u.id_usuario = hr.id_usuario
LEFT JOIN musica m ON hr.id_musica = m.id_musica
LEFT JOIN album al ON m.id_album = al.id_album
LEFT JOIN artista ar ON al.id_artista = ar.id_artista
LEFT JOIN genero g ON m.id_genero = g.id_genero
LEFT JOIN assinatura a ON u.id_usuario = a.id_usuario AND a.status_assinatura = 'ATIVA'
LEFT JOIN tipo_assinatura t ON a.id_tipo_assinatura = t.id_tipo_assinatura
GROUP BY u.id_usuario, u.nome_usuario, u.pais, t.nome_plano
ORDER BY total_reproducoes DESC;

PROMPT ===== 2.2 - ANÁLISE DE POPULARIDADE POR GÊNERO E PAÍS =====

-- Análise complexa relacionando gêneros, países e popularidade
SELECT 
    g.nome_genero,
    ar.pais_origem AS pais_artista,
    u.pais AS pais_ouvinte,
    COUNT(hr.id_historico) AS total_plays,
    COUNT(DISTINCT u.id_usuario) AS usuarios_unicos,
    COUNT(DISTINCT m.id_musica) AS musicas_diferentes,
    ROUND(AVG(hr.duracao_ouvida), 1) AS duracao_media_ouvida
FROM historico_reproducao hr
JOIN usuario u ON hr.id_usuario = u.id_usuario
JOIN musica m ON hr.id_musica = m.id_musica
JOIN genero g ON m.id_genero = g.id_genero
JOIN album al ON m.id_album = al.id_album
JOIN artista ar ON al.id_artista = ar.id_artista
WHERE g.nome_genero IS NOT NULL 
  AND ar.pais_origem IS NOT NULL
GROUP BY g.nome_genero, ar.pais_origem, u.pais
HAVING COUNT(hr.id_historico) > 0
ORDER BY total_plays DESC;

PROMPT ===== 2.3 - RECOMENDAÇÕES BASEADAS EM SIMILARIDADE =====

-- Encontrar usuários com gostos musicais similares
SELECT DISTINCT
    u1.nome_usuario AS usuario1,
    u2.nome_usuario AS usuario2,
    COUNT(DISTINCT m.id_musica) AS musicas_em_comum,
    ROUND(
        COUNT(DISTINCT m.id_musica) * 100.0 / 
        GREATEST(
            (SELECT COUNT(DISTINCT hr1.id_musica) FROM historico_reproducao hr1 WHERE hr1.id_usuario = u1.id_usuario),
            (SELECT COUNT(DISTINCT hr2.id_musica) FROM historico_reproducao hr2 WHERE hr2.id_usuario = u2.id_usuario)
        ), 2
    ) AS percentual_similaridade
FROM usuario u1
JOIN historico_reproducao hr1 ON u1.id_usuario = hr1.id_usuario
JOIN musica m ON hr1.id_musica = m.id_musica
JOIN historico_reproducao hr2 ON m.id_musica = hr2.id_musica
JOIN usuario u2 ON hr2.id_usuario = u2.id_usuario
WHERE u1.id_usuario < u2.id_usuario  -- Evitar duplicatas
GROUP BY u1.id_usuario, u1.nome_usuario, u2.id_usuario, u2.nome_usuario
HAVING COUNT(DISTINCT m.id_musica) >= 2  -- Pelo menos 2 músicas em comum
ORDER BY percentual_similaridade DESC, musicas_em_comum DESC;

-- =====================================================
-- SEÇÃO 3: FUNÇÕES ANALÍTICAS E WINDOW FUNCTIONS
-- =====================================================

PROMPT ===== 3.1 - RANKING DE MÚSICAS COM WINDOW FUNCTIONS =====

-- Ranking de músicas por gênero usando window functions
SELECT 
    g.nome_genero,
    m.titulo,
    ar.nome_artista,
    m.total_reproducoes,
    RANK() OVER (PARTITION BY g.nome_genero ORDER BY m.total_reproducoes DESC) AS rank_genero,
    DENSE_RANK() OVER (ORDER BY m.total_reproducoes DESC) AS rank_geral,
    ROUND(
        m.total_reproducoes * 100.0 / 
        SUM(m.total_reproducoes) OVER (PARTITION BY g.nome_genero), 2
    ) AS percentual_genero
FROM musica m
JOIN genero g ON m.id_genero = g.id_genero
JOIN album al ON m.id_album = al.id_album
JOIN artista ar ON al.id_artista = ar.id_artista
WHERE m.total_reproducoes > 0
ORDER BY g.nome_genero, rank_genero;

PROMPT ===== 3.2 - ANÁLISE TEMPORAL COM LAG E LEAD =====

-- Análise de tendências temporais de reprodução
SELECT 
    TO_CHAR(data_reproducao, 'YYYY-MM-DD') AS data_repr,
    COUNT(*) AS reproducoes_dia,
    LAG(COUNT(*), 1) OVER (ORDER BY TO_CHAR(data_reproducao, 'YYYY-MM-DD')) AS reproducoes_dia_anterior,
    LEAD(COUNT(*), 1) OVER (ORDER BY TO_CHAR(data_reproducao, 'YYYY-MM-DD')) AS reproducoes_dia_seguinte,
    COUNT(*) - LAG(COUNT(*), 1) OVER (ORDER BY TO_CHAR(data_reproducao, 'YYYY-MM-DD')) AS diferenca_dia_anterior,
    ROUND(
        AVG(COUNT(*)) OVER (
            ORDER BY TO_CHAR(data_reproducao, 'YYYY-MM-DD') 
            ROWS BETWEEN 2 PRECEDING AND 2 FOLLOWING
        ), 1
    ) AS media_movel_5dias
FROM historico_reproducao
GROUP BY TO_CHAR(data_reproducao, 'YYYY-MM-DD')
ORDER BY data_repr;

PROMPT ===== 3.3 - PERCENTIS E DISTRIBUIÇÃO DE DURAÇÃO =====

-- Análise de distribuição de duração das músicas por gênero
SELECT 
    g.nome_genero,
    COUNT(m.id_musica) AS total_musicas,
    ROUND(MIN(m.duracao / 60.0), 2) AS duracao_min_min,
    ROUND(PERCENTILE_CONT(0.25) WITHIN GROUP (ORDER BY m.duracao) / 60.0, 2) AS percentil_25_min,
    ROUND(PERCENTILE_CONT(0.5) WITHIN GROUP (ORDER BY m.duracao) / 60.0, 2) AS mediana_min,
    ROUND(PERCENTILE_CONT(0.75) WITHIN GROUP (ORDER BY m.duracao) / 60.0, 2) AS percentil_75_min,
    ROUND(MAX(m.duracao / 60.0), 2) AS duracao_max_min,
    ROUND(AVG(m.duracao / 60.0), 2) AS duracao_media_min,
    ROUND(STDDEV(m.duracao / 60.0), 2) AS desvio_padrao_min
FROM musica m
JOIN genero g ON m.id_genero = g.id_genero
GROUP BY g.id_genero, g.nome_genero
ORDER BY total_musicas DESC;

-- =====================================================
-- SEÇÃO 4: OPERAÇÕES COM CONJUNTOS (Módulo 15)
-- =====================================================

PROMPT ===== 4.1 - USUÁRIOS QUE OUVEM TODOS OS GÊNEROS =====

-- Usuários que ouviram pelo menos uma música de cada gênero
SELECT u.nome_usuario, u.pais
FROM usuario u
WHERE (
    SELECT COUNT(DISTINCT g.id_genero)
    FROM historico_reproducao hr
    JOIN musica m ON hr.id_musica = m.id_musica
    JOIN genero g ON m.id_genero = g.id_genero
    WHERE hr.id_usuario = u.id_usuario
) = (SELECT COUNT(*) FROM genero)

INTERSECT

-- Usuários ativos (que fizeram pelo menos uma reprodução)
SELECT u.nome_usuario, u.pais
FROM usuario u
WHERE EXISTS (
    SELECT 1 FROM historico_reproducao hr 
    WHERE hr.id_usuario = u.id_usuario
);

PROMPT ===== 4.2 - MÚSICAS POPULARES VS POUCO CONHECIDAS =====

-- Top 20% das músicas mais populares
SELECT 'Top 20% - Mais Populares' AS categoria, titulo, total_reproducoes
FROM (
    SELECT 
        m.titulo,
        m.total_reproducoes,
        NTILE(5) OVER (ORDER BY m.total_reproducoes DESC) AS quintil
    FROM musica m
    WHERE m.total_reproducoes > 0
) musicas_top
WHERE quintil = 1

UNION ALL

-- Bottom 20% das músicas menos populares
SELECT 'Bottom 20% - Menos Populares' AS categoria, titulo, total_reproducoes
FROM (
    SELECT 
        m.titulo,
        m.total_reproducoes,
        NTILE(5) OVER (ORDER BY m.total_reproducoes DESC) AS quintil
    FROM musica m
    WHERE m.total_reproducoes > 0
) musicas_bottom
WHERE quintil = 5

ORDER BY categoria, total_reproducoes DESC;

PROMPT ===== 4.3 - ANÁLISE DE EXCLUSIVIDADE DE GÊNEROS =====

-- Gêneros exclusivos por país (que só são ouvidos em um país)
SELECT 
    'Gêneros exclusivos do ' || pais AS categoria,
    generos_exclusivos
FROM (
    SELECT 
        u.pais,
        LISTAGG(g.nome_genero, ', ') WITHIN GROUP (ORDER BY g.nome_genero) AS generos_exclusivos
    FROM (
        SELECT 
            g.nome_genero,
            COUNT(DISTINCT u.pais) AS paises_ouvintes
        FROM historico_reproducao hr
        JOIN usuario u ON hr.id_usuario = u.id_usuario
        JOIN musica m ON hr.id_musica = m.id_musica
        JOIN genero g ON m.id_genero = g.id_genero
        GROUP BY g.nome_genero
        HAVING COUNT(DISTINCT u.pais) = 1  -- Só ouvido em um país
    ) generos_unicos
    JOIN historico_reproducao hr ON 1=1
    JOIN usuario u ON hr.id_usuario = u.id_usuario
    JOIN musica m ON hr.id_musica = m.id_musica
    JOIN genero g ON m.id_genero = g.id_genero
    WHERE g.nome_genero = generos_unicos.nome_genero
    GROUP BY u.pais
) generos_pais
WHERE generos_exclusivos IS NOT NULL;

-- =====================================================
-- SEÇÃO 5: VIEWS COMPLEXAS (Módulo 16)
-- =====================================================

PROMPT ===== 5.1 - CRIANDO VIEW DE DASHBOARD EXECUTIVO =====

-- View para dashboard com métricas principais
CREATE OR REPLACE VIEW vw_dashboard_executivo AS
SELECT 
    -- Métricas de usuários
    (SELECT COUNT(*) FROM usuario WHERE ativo = 'S') AS usuarios_ativos,
    (SELECT COUNT(*) FROM usuario WHERE data_cadastro >= SYSDATE - 30) AS novos_usuarios_mes,
    
    -- Métricas de conteúdo
    (SELECT COUNT(*) FROM musica) AS total_musicas,
    (SELECT COUNT(*) FROM artista) AS total_artistas,
    (SELECT COUNT(*) FROM album) AS total_albums,
    
    -- Métricas de engajamento
    (SELECT COUNT(*) FROM historico_reproducao WHERE data_reproducao >= SYSDATE - 7) AS plays_ultima_semana,
    (SELECT COUNT(*) FROM playlist WHERE publica = 'S') AS playlists_publicas,
    
    -- Métricas financeiras
    (SELECT COUNT(*) FROM assinatura WHERE status_assinatura = 'ATIVA') AS assinantes_ativos,
    (SELECT SUM(t.preco_mensal) 
     FROM assinatura a 
     JOIN tipo_assinatura t ON a.id_tipo_assinatura = t.id_tipo_assinatura 
     WHERE a.status_assinatura = 'ATIVA') AS receita_mensal_estimada,
    
    -- Métricas de qualidade
    (SELECT ROUND(AVG(total_reproducoes), 0) FROM musica WHERE total_reproducoes > 0) AS media_plays_por_musica,
    (SELECT ROUND(AVG(duracao), 0) FROM musica) AS duracao_media_musicas
FROM DUAL;

-- Consultar a view criada
SELECT * FROM vw_dashboard_executivo;

PROMPT ===== 5.2 - VIEW DE ANÁLISE DE CHURN =====

-- View para análise de usuários em risco de cancelamento
CREATE OR REPLACE VIEW vw_analise_churn AS
SELECT 
    u.id_usuario,
    u.nome_usuario,
    u.pais,
    t.nome_plano,
    a.data_inicio AS inicio_assinatura,
    TRUNC(SYSDATE - a.data_inicio) AS dias_como_assinante,
    NVL(MAX(hr.data_reproducao), a.data_inicio) AS ultima_atividade,
    TRUNC(SYSDATE - NVL(MAX(hr.data_reproducao), a.data_inicio)) AS dias_sem_atividade,
    COUNT(hr.id_historico) AS total_reproducoes,
    COUNT(DISTINCT hr.id_musica) AS musicas_diferentes,
    COUNT(DISTINCT p.id_playlist) AS playlists_criadas,
    CASE 
        WHEN SYSDATE - NVL(MAX(hr.data_reproducao), a.data_inicio) > 30 THEN 'Alto Risco'
        WHEN SYSDATE - NVL(MAX(hr.data_reproducao), a.data_inicio) > 14 THEN 'Médio Risco'
        WHEN SYSDATE - NVL(MAX(hr.data_reproducao), a.data_inicio) > 7 THEN 'Baixo Risco'
        ELSE 'Ativo'
    END AS risco_churn
FROM usuario u
JOIN assinatura a ON u.id_usuario = a.id_usuario
JOIN tipo_assinatura t ON a.id_tipo_assinatura = t.id_tipo_assinatura
LEFT JOIN historico_reproducao hr ON u.id_usuario = hr.id_usuario
LEFT JOIN playlist p ON u.id_usuario = p.id_usuario
WHERE a.status_assinatura = 'ATIVA'
  AND t.preco_mensal > 0  -- Apenas assinantes pagantes
GROUP BY u.id_usuario, u.nome_usuario, u.pais, t.nome_plano, a.data_inicio;

-- Consultar análise de churn
SELECT 
    risco_churn,
    COUNT(*) AS total_usuarios,
    ROUND(COUNT(*) * 100.0 / SUM(COUNT(*)) OVER (), 2) AS percentual,
    ROUND(AVG(dias_como_assinante), 0) AS media_dias_assinante,
    ROUND(AVG(total_reproducoes), 0) AS media_reproducoes
FROM vw_analise_churn
GROUP BY risco_churn
ORDER BY CASE risco_churn 
    WHEN 'Alto Risco' THEN 1 
    WHEN 'Médio Risco' THEN 2 
    WHEN 'Baixo Risco' THEN 3 
    ELSE 4 END;

PROMPT ===== 5.3 - VIEW DE RECOMENDAÇÕES PERSONALIZADAS =====

-- View para sistema de recomendações
CREATE OR REPLACE VIEW vw_recomendacoes AS
SELECT 
    u.id_usuario,
    u.nome_usuario,
    m.id_musica,
    m.titulo AS musica_recomendada,
    ar.nome_artista,
    g.nome_genero,
    ROUND(score.pontuacao, 2) AS score_recomendacao,
    score.motivo_recomendacao
FROM usuario u
CROSS JOIN musica m
JOIN album al ON m.id_album = al.id_album
JOIN artista ar ON al.id_artista = ar.id_artista
LEFT JOIN genero g ON m.id_genero = g.id_genero
JOIN (
    -- Subconsulta que calcula score de recomendação
    SELECT 
        u2.id_usuario,
        m2.id_musica,
        -- Score baseado em gênero preferido
        (CASE WHEN m2.id_genero IN (
            SELECT g2.id_genero
            FROM historico_reproducao hr2
            JOIN musica m3 ON hr2.id_musica = m3.id_musica
            JOIN genero g2 ON m3.id_genero = g2.id_genero
            WHERE hr2.id_usuario = u2.id_usuario
            GROUP BY g2.id_genero
            ORDER BY COUNT(*) DESC
            FETCH FIRST 3 ROWS ONLY
        ) THEN 3 ELSE 0 END) +
        
        -- Score baseado em popularidade geral
        (CASE 
            WHEN m2.total_reproducoes > 10 THEN 2
            WHEN m2.total_reproducoes > 5 THEN 1
            ELSE 0
        END) +
        
        -- Score baseado em artistas similares
        (CASE WHEN al2.id_artista IN (
            SELECT al3.id_artista
            FROM historico_reproducao hr3
            JOIN musica m4 ON hr3.id_musica = m4.id_musica
            JOIN album al3 ON m4.id_album = al3.id_album
            WHERE hr3.id_usuario = u2.id_usuario
        ) THEN 2 ELSE 0 END) AS pontuacao,
        
        'Baseado em seu histórico de ' || 
        (SELECT g3.nome_genero 
         FROM historico_reproducao hr4
         JOIN musica m5 ON hr4.id_musica = m5.id_musica
         JOIN genero g3 ON m5.id_genero = g3.id_genero
         WHERE hr4.id_usuario = u2.id_usuario
         GROUP BY g3.nome_genero
         ORDER BY COUNT(*) DESC
         FETCH FIRST 1 ROWS ONLY) AS motivo_recomendacao
    
    FROM usuario u2
    CROSS JOIN musica m2
    JOIN album al2 ON m2.id_album = al2.id_album
    WHERE NOT EXISTS (
        -- Não recomendar músicas já ouvidas
        SELECT 1 FROM historico_reproducao hr5 
        WHERE hr5.id_usuario = u2.id_usuario 
        AND hr5.id_musica = m2.id_musica
    )
) score ON u.id_usuario = score.id_usuario AND m.id_musica = score.id_musica
WHERE score.pontuacao >= 4  -- Apenas recomendações com score alto
ORDER BY u.id_usuario, score.pontuacao DESC;

-- Consultar algumas recomendações
SELECT 
    nome_usuario,
    musica_recomendada,
    nome_artista,
    score_recomendacao,
    motivo_recomendacao
FROM vw_recomendacoes
WHERE ROWNUM <= 20
ORDER BY score_recomendacao DESC;

-- =====================================================
-- SEÇÃO 6: ANÁLISES ESTATÍSTICAS AVANÇADAS
-- =====================================================

PROMPT ===== 6.1 - CORRELAÇÃO ENTRE DURAÇÃO E POPULARIDADE =====

-- Análise de correlação entre duração da música e popularidade
WITH stats_musica AS (
    SELECT 
        duracao,
        total_reproducoes,
        -- Normalizar valores para análise
        (duracao - AVG(duracao) OVER ()) / STDDEV(duracao) OVER () AS duracao_norm,
        (total_reproducoes - AVG(total_reproducoes) OVER ()) / 
        NULLIF(STDDEV(total_reproducoes) OVER (), 0) AS reproducoes_norm
    FROM musica
    WHERE total_reproducoes > 0
)
SELECT 
    'Análise de Correlação Duração vs Popularidade' AS analise,
    COUNT(*) AS total_musicas,
    ROUND(AVG(duracao), 0) AS duracao_media_seg,
    ROUND(AVG(total_reproducoes), 1) AS reproducoes_media,
    ROUND(
        AVG(duracao_norm * reproducoes_norm), 4
    ) AS coeficiente_correlacao_aproximado
FROM stats_musica;

PROMPT ===== 6.2 - ANÁLISE DE PADRÕES TEMPORAIS =====

-- Padrões de uso por hora do dia e dia da semana
SELECT 
    EXTRACT(HOUR FROM data_reproducao) AS hora,
    TO_CHAR(data_reproducao, 'Day') AS dia_semana,
    COUNT(*) AS total_reproducoes,
    ROUND(COUNT(*) * 100.0 / SUM(COUNT(*)) OVER (), 2) AS percentual_total,
    RANK() OVER (ORDER BY COUNT(*) DESC) AS ranking_popularidade
FROM historico_reproducao
GROUP BY EXTRACT(HOUR FROM data_reproducao), TO_CHAR(data_reproducao, 'Day')
ORDER BY total_reproducoes DESC;

PROMPT ===== 6.3 - ANÁLISE DE RETENÇÃO DE USUÁRIOS =====

-- Análise de retenção baseada em coortes mensais
WITH coortes AS (
    SELECT 
        u.id_usuario,
        TO_CHAR(u.data_cadastro, 'YYYY-MM') AS coorte_cadastro,
        TO_CHAR(hr.data_reproducao, 'YYYY-MM') AS mes_atividade,
        MONTHS_BETWEEN(
            TO_DATE(TO_CHAR(hr.data_reproducao, 'YYYY-MM') || '-01', 'YYYY-MM-DD'),
            TO_DATE(TO_CHAR(u.data_cadastro, 'YYYY-MM') || '-01', 'YYYY-MM-DD')
        ) AS meses_desde_cadastro
    FROM usuario u
    LEFT JOIN historico_reproducao hr ON u.id_usuario = hr.id_usuario
    WHERE u.data_cadastro >= SYSDATE - 365  -- Últimos 12 meses
)
SELECT 
    coorte_cadastro,
    COUNT(DISTINCT CASE WHEN meses_desde_cadastro = 0 THEN id_usuario END) AS mes_0,
    COUNT(DISTINCT CASE WHEN meses_desde_cadastro = 1 THEN id_usuario END) AS mes_1,
    COUNT(DISTINCT CASE WHEN meses_desde_cadastro = 2 THEN id_usuario END) AS mes_2,
    COUNT(DISTINCT CASE WHEN meses_desde_cadastro = 3 THEN id_usuario END) AS mes_3,
    ROUND(
        COUNT(DISTINCT CASE WHEN meses_desde_cadastro = 1 THEN id_usuario END) * 100.0 /
        NULLIF(COUNT(DISTINCT CASE WHEN meses_desde_cadastro = 0 THEN id_usuario END), 0), 2
    ) AS retencao_mes_1_pct
FROM coortes
GROUP BY coorte_cadastro
ORDER BY coorte_cadastro;

-- =====================================================
-- SEÇÃO 7: QUERIES DE PERFORMANCE E OTIMIZAÇÃO
-- =====================================================

PROMPT ===== 7.1 - ANÁLISE DE PERFORMANCE DE CONSULTAS =====

-- Consulta otimizada vs não otimizada (exemplo educacional)
-- Versão otimizada usando EXISTS
SELECT 
    'Artistas com pelo menos uma música popular (EXISTS)' AS metodo,
    COUNT(*) AS total_artistas
FROM artista ar
WHERE EXISTS (
    SELECT 1 
    FROM album al 
    JOIN musica m ON al.id_album = m.id_album
    WHERE al.id_artista = ar.id_artista 
    AND m.total_reproducoes > 5
);

-- Versão menos eficiente usando IN
SELECT 
    'Artistas com pelo menos uma música popular (IN)' AS metodo,
    COUNT(*) AS total_artistas
FROM artista ar
WHERE ar.id_artista IN (
    SELECT DISTINCT al.id_artista
    FROM album al 
    JOIN musica m ON al.id_album = m.id_album
    WHERE m.total_reproducoes > 5
);

PROMPT ===== 7.2 - CONSULTA COM HINTS DE OTIMIZAÇÃO =====

-- Exemplo de uso de hints (específico para Oracle)
SELECT /*+ USE_INDEX(historico_reproducao idx_hist_data) */
    TO_CHAR(hr.data_reproducao, 'YYYY-MM') AS mes,
    COUNT(*) AS total_reproducoes,
    COUNT(DISTINCT hr.id_usuario) AS usuarios_ativos,
    COUNT(DISTINCT hr.id_musica) AS musicas_tocadas
FROM historico_reproducao hr
WHERE hr.data_reproducao >= SYSDATE - 90
GROUP BY TO_CHAR(hr.data_reproducao, 'YYYY-MM')
ORDER BY mes;

-- =====================================================
-- SEÇÃO 8: CONSULTAS DE LIMPEZA E MANUTENÇÃO
-- =====================================================

PROMPT ===== 8.1 - IDENTIFICAÇÃO DE DADOS PARA LIMPEZA =====

-- Identificar dados que podem precisar de limpeza
SELECT 
    'Usuários sem atividade' AS problema,
    COUNT(*) AS total
FROM usuario u
WHERE NOT EXISTS (
    SELECT 1 FROM historico_reproducao hr 
    WHERE hr.id_usuario = u.id_usuario
)
UNION ALL
SELECT 'Playlists vazias', COUNT(*)
FROM playlist p
WHERE p.total_musicas = 0
UNION ALL
SELECT 'Álbuns sem músicas', COUNT(*)
FROM album al
WHERE NOT EXISTS (
    SELECT 1 FROM musica m 
    WHERE m.id_album = al.id_album
)
UNION ALL
SELECT 'Artistas sem álbuns', COUNT(*)
FROM artista ar
WHERE NOT EXISTS (
    SELECT 1 FROM album al 
    WHERE al.id_artista = ar.id_artista
);

PROMPT ===== 8.2 - RELATÓRIO FINAL DE INTEGRIDADE =====

-- Verificação final de integridade dos dados
SELECT 
    'Verificações de Integridade' AS categoria,
    CASE 
        WHEN COUNT(*) = 0 THEN 'PASSOU'
        ELSE 'FALHOU - ' || COUNT(*) || ' problemas'
    END AS resultado
FROM (
    -- Verificar referências órfãs
    SELECT 1 FROM musica m 
    WHERE NOT EXISTS (SELECT 1 FROM album al WHERE al.id_album = m.id_album)
    UNION ALL
    SELECT 1 FROM album al 
    WHERE NOT EXISTS (SELECT 1 FROM artista ar WHERE ar.id_artista = al.id_artista)
    UNION ALL
    SELECT 1 FROM playlist_musica pm 
    WHERE NOT EXISTS (SELECT 1 FROM playlist p WHERE p.id_playlist = pm.id_playlist)
       OR NOT EXISTS (SELECT 1 FROM musica m WHERE m.id_musica = pm.id_musica)
    UNION ALL
    SELECT 1 FROM historico_reproducao hr 
    WHERE NOT EXISTS (SELECT 1 FROM usuario u WHERE u.id_usuario = hr.id_usuario)
       OR NOT EXISTS (SELECT 1 FROM musica m WHERE m.id_musica = hr.id_musica)
) verificacoes_integridade;

PROMPT =====================================================
PROMPT CONSULTAS AVANÇADAS CONCLUÍDAS!
PROMPT Próximo passo: Execute 07-roteiro-completo.sql
PROMPT =====================================================