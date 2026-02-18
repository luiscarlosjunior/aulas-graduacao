-- =====================================================
-- CRIAÇÃO DE VIEWS PARA O SISTEMA MUSISTREAM
-- Módulo 16: Criando VIEWS (Visões)
-- =====================================================

-- =====================================================
-- 1. CONFIGURAÇÃO DO AMBIENTE
-- =====================================================

SET ECHO ON;
SET TIMING ON;

-- Limpeza de views existentes (se necessário)
DROP VIEW IF EXISTS vw_auditoria_atividades;
DROP VIEW IF EXISTS vw_recomendacoes;
DROP VIEW IF EXISTS vw_dashboard_executivo;
DROP VIEW IF EXISTS vw_usuarios_publico;
DROP VIEW IF EXISTS vw_ranking_popularidade;
DROP VIEW IF EXISTS vw_perfil_usuarios;
DROP VIEW IF EXISTS vw_estatisticas_artistas;
DROP VIEW IF EXISTS vw_catalogo_musicas;

-- =====================================================
-- 2. VIEWS BÁSICAS - CATÁLOGO E INFORMAÇÕES GERAIS
-- =====================================================

-- VIEW 1: Catálogo completo de músicas
CREATE VIEW vw_catalogo_musicas AS
SELECT m.id_musica,
       m.titulo AS musica,
       ar.nome_artista AS artista,
       al.titulo AS album,
       CONCAT(FLOOR(m.duracao/60), ':', LPAD(m.duracao%60, 2, '0')) AS duracao_formatada,
       m.duracao AS duracao_segundos,
       m.numero_faixa,
       m.explicita,
       al.data_lancamento,
       ar.pais_origem,
       al.tipo_album
FROM musica m
JOIN album al ON m.id_album = al.id_album
JOIN artista ar ON al.id_artista = ar.id_artista
ORDER BY ar.nome_artista, al.data_lancamento, m.numero_faixa;

-- Teste da VIEW
SELECT 'TESTE: vw_catalogo_musicas' AS teste;
SELECT artista, COUNT(*) AS total_musicas
FROM vw_catalogo_musicas 
GROUP BY artista 
ORDER BY total_musicas DESC 
LIMIT 5;

-- =====================================================
-- 3. VIEWS DE ESTATÍSTICAS - ANÁLISE DE DADOS
-- =====================================================

-- VIEW 2: Estatísticas completas de artistas
CREATE VIEW vw_estatisticas_artistas AS
SELECT ar.id_artista,
       ar.nome_artista,
       ar.pais_origem,
       ar.numero_membros,
       ar.ativo,
       COUNT(DISTINCT al.id_album) AS total_albums,
       COUNT(DISTINCT m.id_musica) AS total_musicas,
       COALESCE(ROUND(AVG(m.duracao), 2), 0) AS duracao_media_musicas,
       COALESCE(SUM(m.duracao), 0) AS duracao_total_segundos,
       MIN(al.data_lancamento) AS primeiro_album,
       MAX(al.data_lancamento) AS ultimo_album,
       COUNT(DISTINCT h.id_usuario) AS usuarios_unicos_ouvintes,
       COUNT(h.id_historico) AS total_reproducoes,
       CASE 
           WHEN COUNT(h.id_historico) > 100 THEN 'Muito Popular'
           WHEN COUNT(h.id_historico) > 10 THEN 'Popular' 
           WHEN COUNT(h.id_historico) > 0 THEN 'Moderado'
           ELSE 'Sem Reproduções'
       END AS nivel_popularidade
FROM artista ar
LEFT JOIN album al ON ar.id_artista = al.id_artista
LEFT JOIN musica m ON al.id_album = m.id_album
LEFT JOIN historico_reproducao h ON m.id_musica = h.id_musica
GROUP BY ar.id_artista, ar.nome_artista, ar.pais_origem, ar.numero_membros, ar.ativo;

-- Teste da VIEW
SELECT 'TESTE: vw_estatisticas_artistas' AS teste;
SELECT nome_artista, total_musicas, total_reproducoes, nivel_popularidade
FROM vw_estatisticas_artistas 
WHERE total_musicas > 0
ORDER BY total_reproducoes DESC;

-- =====================================================
-- 4. VIEWS DE PERFIL DE USUÁRIO
-- =====================================================

-- VIEW 3: Perfil completo de usuários
CREATE VIEW vw_perfil_usuarios AS
SELECT u.id_usuario,
       u.nome_usuario,
       u.email,
       u.data_nascimento,
       CASE 
           WHEN u.data_nascimento IS NOT NULL 
           THEN YEAR(CURRENT_DATE) - YEAR(u.data_nascimento)
           ELSE NULL
       END AS idade,
       u.data_cadastro,
       DATEDIFF(CURRENT_DATE, u.data_cadastro) AS dias_desde_cadastro,
       u.ativo,
       COUNT(DISTINCT h.id_historico) AS total_reproducoes,
       COUNT(DISTINCT m.id_musica) AS musicas_diferentes_ouvidas,
       COUNT(DISTINCT ar.id_artista) AS artistas_diferentes_ouvidos,
       COUNT(DISTINCT ar.pais_origem) AS paises_diferentes,
       COALESCE(SUM(h.duracao_ouvida), 0) AS tempo_total_ouvindo_segundos,
       ROUND(COALESCE(SUM(h.duracao_ouvida), 0) / 3600, 2) AS tempo_total_ouvindo_horas,
       MAX(h.data_reproducao) AS ultima_atividade,
       CASE 
           WHEN MAX(h.data_reproducao) >= DATE_SUB(CURRENT_DATE, INTERVAL 7 DAY) THEN 'Ativo'
           WHEN MAX(h.data_reproducao) >= DATE_SUB(CURRENT_DATE, INTERVAL 30 DAY) THEN 'Moderado'
           WHEN MAX(h.data_reproducao) IS NOT NULL THEN 'Inativo'
           ELSE 'Nunca usou'
       END AS status_atividade,
       CASE 
           WHEN COUNT(h.id_historico) > 50 THEN 'Expert'
           WHEN COUNT(h.id_historico) > 20 THEN 'Avançado'
           WHEN COUNT(h.id_historico) > 5 THEN 'Intermediário'
           WHEN COUNT(h.id_historico) > 0 THEN 'Iniciante'
           ELSE 'Inativo'
       END AS nivel_usuario
FROM usuario u
LEFT JOIN historico_reproducao h ON u.id_usuario = h.id_usuario
LEFT JOIN musica m ON h.id_musica = m.id_musica
LEFT JOIN album al ON m.id_album = al.id_album
LEFT JOIN artista ar ON al.id_artista = ar.id_artista
GROUP BY u.id_usuario, u.nome_usuario, u.email, u.data_nascimento, u.data_cadastro, u.ativo;

-- Teste da VIEW
SELECT 'TESTE: vw_perfil_usuarios' AS teste;
SELECT nome_usuario, total_reproducoes, nivel_usuario, status_atividade
FROM vw_perfil_usuarios 
ORDER BY total_reproducoes DESC;

-- =====================================================
-- 5. VIEWS DE RANKING E POPULARIDADE
-- =====================================================

-- VIEW 4: Ranking de músicas mais populares
CREATE VIEW vw_ranking_popularidade AS
SELECT m.id_musica,
       m.titulo AS musica,
       ar.nome_artista AS artista,
       al.titulo AS album,
       COUNT(h.id_historico) AS total_reproducoes,
       COUNT(DISTINCT h.id_usuario) AS usuarios_unicos,
       ROUND(COALESCE(AVG(h.duracao_ouvida), 0), 2) AS media_tempo_ouvido,
       ROUND((COALESCE(AVG(h.duracao_ouvida), 0) / m.duracao) * 100, 2) AS percentual_medio_ouvido,
       MAX(h.data_reproducao) AS ultima_reproducao,
       RANK() OVER (ORDER BY COUNT(h.id_historico) DESC) AS ranking_reproducoes,
       RANK() OVER (ORDER BY COUNT(DISTINCT h.id_usuario) DESC) AS ranking_alcance,
       CASE 
           WHEN COUNT(h.id_historico) >= 5 THEN 'Hit'
           WHEN COUNT(h.id_historico) >= 3 THEN 'Popular'
           WHEN COUNT(h.id_historico) >= 1 THEN 'Conhecida'
           ELSE 'Nova'
       END AS categoria_popularidade
FROM musica m
JOIN album al ON m.id_album = al.id_album
JOIN artista ar ON al.id_artista = ar.id_artista
LEFT JOIN historico_reproducao h ON m.id_musica = h.id_musica
GROUP BY m.id_musica, m.titulo, ar.nome_artista, al.titulo, m.duracao
ORDER BY total_reproducoes DESC, usuarios_unicos DESC;

-- Teste da VIEW
SELECT 'TESTE: vw_ranking_popularidade' AS teste;
SELECT musica, artista, total_reproducoes, categoria_popularidade, ranking_reproducoes
FROM vw_ranking_popularidade 
WHERE total_reproducoes > 0
LIMIT 10;

-- =====================================================
-- 6. VIEWS DE SEGURANÇA - DADOS PÚBLICOS
-- =====================================================

-- VIEW 5: Informações públicas de usuários (sem dados sensíveis)
CREATE VIEW vw_usuarios_publico AS
SELECT u.id_usuario,
       u.nome_usuario,
       CASE 
           WHEN u.data_nascimento IS NOT NULL 
           THEN CONCAT(FLOOR(YEAR(CURRENT_DATE) - YEAR(u.data_nascimento) / 5) * 5, '-', 
                      FLOOR(YEAR(CURRENT_DATE) - YEAR(u.data_nascimento) / 5) * 5 + 4, ' anos')
           ELSE 'Não informado'
       END AS faixa_etaria,
       COUNT(h.id_historico) AS total_reproducoes,
       COUNT(DISTINCT ar.pais_origem) AS diversidade_paises,
       CASE 
           WHEN COUNT(h.id_historico) > 100 THEN 'Melômano'
           WHEN COUNT(h.id_historico) > 50 THEN 'Expert'
           WHEN COUNT(h.id_historico) > 20 THEN 'Avançado'
           WHEN COUNT(h.id_historico) > 5 THEN 'Intermediário'
           ELSE 'Iniciante'
       END AS nivel_usuario,
       CASE 
           WHEN MAX(h.data_reproducao) >= DATE_SUB(CURRENT_DATE, INTERVAL 7 DAY) THEN 'Online'
           WHEN MAX(h.data_reproducao) >= DATE_SUB(CURRENT_DATE, INTERVAL 30 DAY) THEN 'Ativo'
           ELSE 'Inativo'
       END AS status_atividade
FROM usuario u
LEFT JOIN historico_reproducao h ON u.id_usuario = h.id_usuario
LEFT JOIN musica m ON h.id_musica = m.id_musica
LEFT JOIN album al ON m.id_album = al.id_album
LEFT JOIN artista ar ON al.id_artista = ar.id_artista
WHERE u.ativo = TRUE
GROUP BY u.id_usuario, u.nome_usuario, u.data_nascimento;

-- Teste da VIEW
SELECT 'TESTE: vw_usuarios_publico' AS teste;
SELECT nivel_usuario, COUNT(*) AS quantidade
FROM vw_usuarios_publico 
GROUP BY nivel_usuario 
ORDER BY quantidade DESC;

-- =====================================================
-- 7. VIEWS DE DASHBOARD EXECUTIVO
-- =====================================================

-- VIEW 6: Dashboard executivo com métricas principais
CREATE VIEW vw_dashboard_executivo AS
SELECT 
    -- Métricas de Conteúdo
    (SELECT COUNT(*) FROM artista WHERE ativo = TRUE) AS artistas_ativos,
    (SELECT COUNT(*) FROM album) AS total_albums,
    (SELECT COUNT(*) FROM musica) AS total_musicas,
    
    -- Métricas de Usuários
    (SELECT COUNT(*) FROM usuario WHERE ativo = TRUE) AS usuarios_ativos,
    (SELECT COUNT(DISTINCT id_usuario) FROM historico_reproducao 
     WHERE data_reproducao >= DATE_SUB(CURRENT_DATE, INTERVAL 30 DAY)) AS usuarios_ativos_mes,
    (SELECT COUNT(DISTINCT id_usuario) FROM historico_reproducao 
     WHERE data_reproducao >= DATE_SUB(CURRENT_DATE, INTERVAL 7 DAY)) AS usuarios_ativos_semana,
    
    -- Métricas de Engajamento
    (SELECT COUNT(*) FROM historico_reproducao) AS total_reproducoes,
    (SELECT COUNT(*) FROM historico_reproducao 
     WHERE data_reproducao >= DATE_SUB(CURRENT_DATE, INTERVAL 30 DAY)) AS reproducoes_ultimo_mes,
    (SELECT COUNT(*) FROM historico_reproducao 
     WHERE data_reproducao >= DATE_SUB(CURRENT_DATE, INTERVAL 7 DAY)) AS reproducoes_ultima_semana,
    
    -- Métricas de Tempo
    (SELECT ROUND(SUM(duracao_ouvida) / 3600, 2) FROM historico_reproducao) AS horas_totais_ouvidas,
    (SELECT ROUND(AVG(duracao), 2) FROM musica) AS duracao_media_musicas,
    
    -- Top Insights
    (SELECT pais_origem FROM artista GROUP BY pais_origem ORDER BY COUNT(*) DESC LIMIT 1) AS pais_mais_artistas,
    (SELECT COUNT(*) FROM (SELECT pais_origem FROM artista GROUP BY pais_origem) AS paises) AS total_paises_representados,
    
    -- Métricas de Qualidade
    (SELECT ROUND(AVG(duracao_ouvida / 
        (SELECT duracao FROM musica WHERE musica.id_musica = historico_reproducao.id_musica)) * 100, 2) 
     FROM historico_reproducao) AS percentual_medio_musica_ouvida,
    
    -- Data e Hora do Relatório
    CURRENT_TIMESTAMP AS data_geracao_relatorio,
    DAYNAME(CURRENT_DATE) AS dia_semana,
    DATE_FORMAT(CURRENT_DATE, '%Y-%m') AS mes_ano;

-- Teste da VIEW
SELECT 'TESTE: vw_dashboard_executivo' AS teste;
SELECT * FROM vw_dashboard_executivo;

-- =====================================================
-- 8. VIEWS DE AUDITORIA E LOG
-- =====================================================

-- VIEW 7: Auditoria de atividades recentes
CREATE VIEW vw_auditoria_atividades AS
SELECT 
    'REPRODUCAO' AS tipo_evento,
    h.data_reproducao AS data_evento,
    u.nome_usuario AS usuario,
    CONCAT('Ouviu: "', m.titulo, '" de ', ar.nome_artista) AS descricao,
    CONCAT('Dispositivo: ', h.dispositivo, ' | Duração ouvida: ', 
           FLOOR(h.duracao_ouvida/60), ':', LPAD(h.duracao_ouvida%60, 2, '0')) AS detalhes_adicionais,
    h.id_historico AS id_evento
FROM historico_reproducao h
JOIN usuario u ON h.id_usuario = u.id_usuario
JOIN musica m ON h.id_musica = m.id_musica
JOIN album al ON m.id_album = al.id_album
JOIN artista ar ON al.id_artista = ar.id_artista
WHERE h.data_reproducao >= DATE_SUB(CURRENT_DATE, INTERVAL 30 DAY)

UNION ALL

SELECT 
    'CADASTRO_USUARIO' AS tipo_evento,
    u.data_cadastro AS data_evento,
    u.nome_usuario AS usuario,
    'Novo usuário cadastrado na plataforma' AS descricao,
    CONCAT('Email: ', u.email, ' | Status: ', IF(u.ativo, 'Ativo', 'Inativo')) AS detalhes_adicionais,
    u.id_usuario AS id_evento
FROM usuario u
WHERE u.data_cadastro >= DATE_SUB(CURRENT_DATE, INTERVAL 30 DAY)

ORDER BY data_evento DESC;

-- Teste da VIEW
SELECT 'TESTE: vw_auditoria_atividades' AS teste;
SELECT tipo_evento, COUNT(*) AS quantidade
FROM vw_auditoria_atividades 
GROUP BY tipo_evento 
ORDER BY quantidade DESC;

-- =====================================================
-- 9. VERIFICAÇÃO DE TODAS AS VIEWS CRIADAS
-- =====================================================

-- Listar todas as views criadas
SELECT 'VIEWS CRIADAS COM SUCESSO:' AS status;

-- Verificar views no sistema (sintaxe pode variar por SGBD)
SHOW TABLES LIKE 'vw_%';

-- Ou usando INFORMATION_SCHEMA
SELECT TABLE_NAME as view_name, 
       TABLE_COMMENT as comentario
FROM INFORMATION_SCHEMA.VIEWS 
WHERE TABLE_SCHEMA = DATABASE()
  AND TABLE_NAME LIKE 'vw_%'
ORDER BY TABLE_NAME;

-- =====================================================
-- 10. EXEMPLOS DE USO DAS VIEWS
-- =====================================================

SELECT 'EXEMPLOS DE USO DAS VIEWS:' AS demonstracao;

-- Exemplo 1: Top 5 artistas mais populares
SELECT nome_artista, total_reproducoes, nivel_popularidade
FROM vw_estatisticas_artistas 
WHERE total_reproducoes > 0
ORDER BY total_reproducoes DESC 
LIMIT 5;

-- Exemplo 2: Perfil de usuários ativos
SELECT nivel_usuario, status_atividade, COUNT(*) AS quantidade
FROM vw_perfil_usuarios 
GROUP BY nivel_usuario, status_atividade
ORDER BY quantidade DESC;

-- Exemplo 3: Músicas mais populares por país do artista
SELECT artista, musica, total_reproducoes,
       (SELECT pais_origem FROM vw_catalogo_musicas c2 
        WHERE c2.artista = vw_ranking_popularidade.artista LIMIT 1) AS pais
FROM vw_ranking_popularidade 
WHERE total_reproducoes > 0
ORDER BY total_reproducoes DESC 
LIMIT 10;

-- Exemplo 4: Dashboard resumido
SELECT CONCAT('Total de ', artistas_ativos, ' artistas ativos') AS metrica_1,
       CONCAT('Total de ', total_reproducoes, ' reproduções') AS metrica_2,
       CONCAT(usuarios_ativos_mes, ' usuários ativos no mês') AS metrica_3
FROM vw_dashboard_executivo;

-- =====================================================
-- 11. LIMPEZA E CONFIGURAÇÕES FINAIS
-- =====================================================

-- Criar índices para melhorar performance das views (opcional)
-- Nota: Índices são criados nas tabelas base, não nas views

-- Verificar performance das views mais complexas
EXPLAIN SELECT * FROM vw_ranking_popularidade LIMIT 10;
EXPLAIN SELECT * FROM vw_perfil_usuarios WHERE total_reproducoes > 5;

SELECT 'Sistema de VIEWS do MusiStream criado com sucesso!' AS status_final;
SELECT 'Total de views criadas: 7' AS resumo;

-- Resetar configurações
SET ECHO OFF;
SET TIMING OFF;