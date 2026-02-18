-- =====================================================
-- OPERAÇÕES COM CONJUNTOS - SISTEMA MUSISTREAM
-- Módulo 15: UNION, INTERSECT, EXCEPT
-- =====================================================

-- =====================================================
-- 1. UNION - COMBINANDO RESULTADOS
-- =====================================================

-- Listar todos os nomes no sistema (artistas e usuários)
SELECT nome_artista as nome, 'Artista' as tipo, pais_origem as origem
FROM artista
WHERE nome_artista IS NOT NULL

UNION

SELECT nome_usuario as nome, 'Usuário' as tipo, pais as origem
FROM usuario
WHERE nome_usuario IS NOT NULL

ORDER BY nome;

-- =====================================================
-- 2. UNION ALL - MANTENDO DUPLICATAS
-- =====================================================

-- Histórico de atividades do sistema
SELECT 
    'Cadastro' as atividade,
    nome_usuario as entidade,
    data_cadastro as data_evento
FROM usuario
WHERE data_cadastro >= CURRENT_DATE - INTERVAL 30 DAY

UNION ALL

SELECT 
    'Reprodução',
    CAST(id_musica AS VARCHAR2(100)),
    data_reproducao
FROM historico_reproducao
WHERE data_reproducao >= CURRENT_DATE - INTERVAL 30 DAY

ORDER BY data_evento DESC;

-- =====================================================
-- 3. INTERSECT - ELEMENTOS COMUNS
-- =====================================================

-- Países que têm tanto artistas quanto usuários
SELECT pais_origem as pais FROM artista WHERE pais_origem IS NOT NULL
INTERSECT
SELECT pais FROM usuario WHERE pais IS NOT NULL;

-- =====================================================
-- 4. EXCEPT - DIFERENÇAS
-- =====================================================

-- Gêneros que existem mas não têm músicas
SELECT id_genero, nome_genero FROM genero
EXCEPT
SELECT DISTINCT m.id_genero, g.nome_genero
FROM musica m
JOIN genero g ON m.id_genero = g.id_genero
WHERE m.id_genero IS NOT NULL;

-- =====================================================
-- 5. ANÁLISE COMPARATIVA POR REGIÃO
-- =====================================================

-- Top músicas no Brasil vs Global
WITH brasil_ranking AS (
    SELECT 
        m.titulo,
        a.nome_artista,
        COUNT(*) as reproducoes_brasil
    FROM musica m
    JOIN album al ON m.id_album = al.id_album
    JOIN artista a ON al.id_artista = a.id_artista
    JOIN historico_reproducao hr ON m.id_musica = hr.id_musica
    JOIN usuario u ON hr.id_usuario = u.id_usuario
    WHERE u.pais = 'Brasil'
    GROUP BY m.id_musica, m.titulo, a.nome_artista
    ORDER BY COUNT(*) DESC
    FETCH FIRST 10 ROWS ONLY
),
global_ranking AS (
    SELECT 
        m.titulo,
        a.nome_artista,
        COUNT(*) as reproducoes_global
    FROM musica m
    JOIN album al ON m.id_album = al.id_album
    JOIN artista a ON al.id_artista = a.id_artista
    JOIN historico_reproducao hr ON m.id_musica = hr.id_musica
    GROUP BY m.id_musica, m.titulo, a.nome_artista
    ORDER BY COUNT(*) DESC
    FETCH FIRST 10 ROWS ONLY
)
SELECT titulo, nome_artista, reproducoes_brasil, 'Brasil' as ranking_tipo
FROM brasil_ranking
UNION ALL
SELECT titulo, nome_artista, reproducoes_global, 'Global'
FROM global_ranking
ORDER BY ranking_tipo, 
         CASE ranking_tipo 
           WHEN 'Brasil' THEN reproducoes_brasil 
           ELSE reproducoes_global 
         END DESC;