-- ============================================================================
-- SISTEMA DE E-COMMERCE TECHSTORE
-- Consultas Avançadas - Relatórios e Análises Complexas
-- ============================================================================
-- Descrição: Consultas avançadas para relatórios gerenciais e análise de dados
-- Versão: 1.0
-- Data: 2024
-- ============================================================================

USE ecommerce_techstore;

-- ============================================================================
-- SEÇÃO 1: ANÁLISES DE VENDAS E RECEITA
-- ============================================================================

-- 1.1 Relatório de vendas por categoria (últimos 30 dias)
SELECT 
    c.nome_categoria,
    COUNT(DISTINCT p.id_pedido) AS total_pedidos,
    COUNT(ip.id_item_pedido) AS total_itens_vendidos,
    SUM(ip.quantidade) AS quantidade_total_vendida,
    SUM(ip.preco_total) AS receita_categoria,
    AVG(ip.preco_unitario) AS preco_medio_vendido,
    RANK() OVER (ORDER BY SUM(ip.preco_total) DESC) AS ranking_receita
FROM CATEGORIA c
INNER JOIN PRODUTO prod ON c.id_categoria = prod.id_categoria
INNER JOIN ITEM_PEDIDO ip ON prod.id_produto = ip.id_produto
INNER JOIN PEDIDO p ON ip.id_pedido = p.id_pedido
WHERE p.data_pedido >= DATE_SUB(CURDATE(), INTERVAL 30 DAY)
  AND p.id_status NOT IN (6) -- Excluir cancelados
GROUP BY c.id_categoria, c.nome_categoria
ORDER BY receita_categoria DESC;

-- 1.2 Evolução de vendas mensais (últimos 6 meses)
SELECT 
    DATE_FORMAT(p.data_pedido, '%Y-%m') AS mes_ano,
    COUNT(DISTINCT p.id_pedido) AS total_pedidos,
    COUNT(ip.id_item_pedido) AS total_itens,
    SUM(p.valor_produtos) AS receita_produtos,
    SUM(p.valor_frete) AS receita_frete,
    SUM(p.valor_desconto) AS total_descontos,
    SUM(p.valor_total) AS receita_total,
    AVG(p.valor_total) AS ticket_medio,
    -- Crescimento em relação ao mês anterior
    LAG(SUM(p.valor_total)) OVER (ORDER BY DATE_FORMAT(p.data_pedido, '%Y-%m')) AS receita_mes_anterior,
    ROUND(
        ((SUM(p.valor_total) - LAG(SUM(p.valor_total)) OVER (ORDER BY DATE_FORMAT(p.data_pedido, '%Y-%m'))) /
         LAG(SUM(p.valor_total)) OVER (ORDER BY DATE_FORMAT(p.data_pedido, '%Y-%m'))) * 100, 2
    ) AS crescimento_percentual
FROM PEDIDO p
LEFT JOIN ITEM_PEDIDO ip ON p.id_pedido = ip.id_pedido
WHERE p.data_pedido >= DATE_SUB(CURDATE(), INTERVAL 6 MONTH)
  AND p.id_status NOT IN (6) -- Excluir cancelados
GROUP BY DATE_FORMAT(p.data_pedido, '%Y-%m')
ORDER BY mes_ano;

-- 1.3 Top 10 produtos mais vendidos com análise de margem
SELECT 
    prod.nome_produto,
    prod.marca,
    c.nome_categoria,
    COUNT(ip.id_item_pedido) AS total_vendas,
    SUM(ip.quantidade) AS quantidade_total,
    SUM(ip.preco_total) AS receita_total,
    AVG(ip.preco_unitario) AS preco_medio_venda,
    prod.preco_atual AS preco_atual,
    ROUND(AVG(ip.preco_unitario) - prod.preco_atual, 2) AS diferenca_preco,
    -- Análise de avaliações
    COUNT(a.id_avaliacao) AS total_avaliacoes,
    ROUND(AVG(a.nota), 2) AS nota_media,
    -- Ranking por receita
    ROW_NUMBER() OVER (ORDER BY SUM(ip.preco_total) DESC) AS ranking
FROM PRODUTO prod
INNER JOIN ITEM_PEDIDO ip ON prod.id_produto = ip.id_produto
INNER JOIN PEDIDO p ON ip.id_pedido = p.id_pedido
INNER JOIN CATEGORIA c ON prod.id_categoria = c.id_categoria
LEFT JOIN AVALIACAO a ON prod.id_produto = a.id_produto
WHERE p.id_status NOT IN (6) -- Excluir cancelados
GROUP BY prod.id_produto
ORDER BY receita_total DESC
LIMIT 10;

-- 1.4 Análise de sazonalidade por dia da semana
SELECT 
    CASE DAYOFWEEK(p.data_pedido)
        WHEN 1 THEN 'Domingo'
        WHEN 2 THEN 'Segunda'
        WHEN 3 THEN 'Terça'
        WHEN 4 THEN 'Quarta'
        WHEN 5 THEN 'Quinta'
        WHEN 6 THEN 'Sexta'
        WHEN 7 THEN 'Sábado'
    END AS dia_semana,
    DAYOFWEEK(p.data_pedido) AS ordem_dia,
    COUNT(*) AS total_pedidos,
    SUM(p.valor_total) AS receita_total,
    AVG(p.valor_total) AS ticket_medio,
    -- Percentual do total
    ROUND(COUNT(*) * 100.0 / (SELECT COUNT(*) FROM PEDIDO WHERE id_status NOT IN (6)), 2) AS percentual_pedidos
FROM PEDIDO p
WHERE p.id_status NOT IN (6) -- Excluir cancelados
GROUP BY DAYOFWEEK(p.data_pedido)
ORDER BY ordem_dia;

-- ============================================================================
-- SEÇÃO 2: ANÁLISE DE CLIENTES E SEGMENTAÇÃO
-- ============================================================================

-- 2.1 Segmentação RFV (Recência, Frequência, Valor)
WITH cliente_metricas AS (
    SELECT 
        c.id_cliente,
        c.nome_completo,
        c.email,
        -- Recência (dias desde último pedido)
        DATEDIFF(CURDATE(), MAX(p.data_pedido)) AS dias_ultimo_pedido,
        -- Frequência (número de pedidos)
        COUNT(DISTINCT p.id_pedido) AS total_pedidos,
        -- Valor (valor total gasto)
        SUM(p.valor_total) AS valor_total_gasto,
        AVG(p.valor_total) AS ticket_medio
    FROM CLIENTE c
    INNER JOIN PEDIDO p ON c.id_cliente = p.id_cliente
    WHERE p.id_status NOT IN (6) -- Excluir cancelados
      AND c.ativo = TRUE
    GROUP BY c.id_cliente, c.nome_completo, c.email
),
cliente_quartis AS (
    SELECT *,
        -- Quartis para segmentação
        NTILE(4) OVER (ORDER BY dias_ultimo_pedido) AS recencia_quartil,
        NTILE(4) OVER (ORDER BY total_pedidos DESC) AS frequencia_quartil,
        NTILE(4) OVER (ORDER BY valor_total_gasto DESC) AS valor_quartil
    FROM cliente_metricas
)
SELECT 
    nome_completo,
    email,
    dias_ultimo_pedido,
    total_pedidos,
    CONCAT('R$ ', FORMAT(valor_total_gasto, 2)) AS valor_total,
    CONCAT('R$ ', FORMAT(ticket_medio, 2)) AS ticket_medio,
    -- Classificação RFV
    CASE 
        WHEN recencia_quartil <= 2 AND frequencia_quartil >= 3 AND valor_quartil >= 3 THEN 'VIP'
        WHEN recencia_quartil <= 2 AND frequencia_quartil >= 2 AND valor_quartil >= 2 THEN 'Leal'
        WHEN recencia_quartil <= 2 AND frequencia_quartil = 1 THEN 'Novo'
        WHEN recencia_quartil >= 3 AND frequencia_quartil >= 3 AND valor_quartil >= 3 THEN 'Em Risco'
        WHEN recencia_quartil >= 3 AND frequencia_quartil <= 2 THEN 'Perdido'
        ELSE 'Regular'
    END AS segmento_cliente
FROM cliente_quartis
ORDER BY valor_total_gasto DESC;

-- 2.2 Clientes mais valiosos (top 20% por valor)
SELECT 
    c.nome_completo,
    c.email,
    c.tipo_pessoa,
    COUNT(DISTINCT p.id_pedido) AS total_pedidos,
    SUM(p.valor_total) AS valor_total_gasto,
    AVG(p.valor_total) AS ticket_medio,
    MAX(p.data_pedido) AS ultimo_pedido,
    -- Participação na receita total
    ROUND(SUM(p.valor_total) * 100.0 / (
        SELECT SUM(valor_total) FROM PEDIDO WHERE id_status NOT IN (6)
    ), 2) AS participacao_receita,
    -- Ranking
    ROW_NUMBER() OVER (ORDER BY SUM(p.valor_total) DESC) AS ranking
FROM CLIENTE c
INNER JOIN PEDIDO p ON c.id_cliente = p.id_cliente
WHERE p.id_status NOT IN (6) -- Excluir cancelados
  AND c.ativo = TRUE
GROUP BY c.id_cliente
HAVING SUM(p.valor_total) >= (
    SELECT PERCENTILE_CONT(0.8) WITHIN GROUP (ORDER BY total_valor) 
    FROM (
        SELECT SUM(valor_total) AS total_valor 
        FROM PEDIDO 
        WHERE id_status NOT IN (6) 
        GROUP BY id_cliente
    ) sub
)
ORDER BY valor_total_gasto DESC;

-- 2.3 Análise de churn (clientes inativos)
SELECT 
    CASE 
        WHEN DATEDIFF(CURDATE(), MAX(p.data_pedido)) <= 30 THEN '0-30 dias'
        WHEN DATEDIFF(CURDATE(), MAX(p.data_pedido)) <= 60 THEN '31-60 dias'
        WHEN DATEDIFF(CURDATE(), MAX(p.data_pedido)) <= 90 THEN '61-90 dias'
        WHEN DATEDIFF(CURDATE(), MAX(p.data_pedido)) <= 180 THEN '91-180 dias'
        ELSE 'Mais de 180 dias'
    END AS periodo_inatividade,
    COUNT(DISTINCT c.id_cliente) AS total_clientes,
    AVG(DATEDIFF(CURDATE(), MAX(p.data_pedido))) AS dias_medio_inativo,
    SUM(total_gasto.valor_total) AS valor_total_segmento
FROM CLIENTE c
INNER JOIN PEDIDO p ON c.id_cliente = p.id_cliente
INNER JOIN (
    SELECT id_cliente, SUM(valor_total) AS valor_total
    FROM PEDIDO 
    WHERE id_status NOT IN (6)
    GROUP BY id_cliente
) total_gasto ON c.id_cliente = total_gasto.id_cliente
WHERE c.ativo = TRUE
GROUP BY periodo_inatividade
ORDER BY AVG(DATEDIFF(CURDATE(), MAX(p.data_pedido)));

-- ============================================================================
-- SEÇÃO 3: ANÁLISE DE PRODUTOS E ESTOQUE
-- ============================================================================

-- 3.1 Análise ABC dos produtos (Pareto)
WITH vendas_produto AS (
    SELECT 
        prod.id_produto,
        prod.nome_produto,
        SUM(ip.preco_total) AS receita_total,
        SUM(ip.quantidade) AS quantidade_vendida
    FROM PRODUTO prod
    INNER JOIN ITEM_PEDIDO ip ON prod.id_produto = ip.id_produto
    INNER JOIN PEDIDO p ON ip.id_pedido = p.id_pedido
    WHERE p.id_status NOT IN (6)
    GROUP BY prod.id_produto, prod.nome_produto
),
produtos_acumulado AS (
    SELECT *,
        SUM(receita_total) OVER (ORDER BY receita_total DESC) AS receita_acumulada,
        (SELECT SUM(receita_total) FROM vendas_produto) AS receita_total_geral
    FROM vendas_produto
)
SELECT 
    nome_produto,
    CONCAT('R$ ', FORMAT(receita_total, 2)) AS receita,
    quantidade_vendida,
    ROUND(receita_total * 100.0 / receita_total_geral, 2) AS participacao_receita,
    ROUND(receita_acumulada * 100.0 / receita_total_geral, 2) AS participacao_acumulada,
    CASE 
        WHEN receita_acumulada * 100.0 / receita_total_geral <= 80 THEN 'A'
        WHEN receita_acumulada * 100.0 / receita_total_geral <= 95 THEN 'B'
        ELSE 'C'
    END AS classificacao_abc
FROM produtos_acumulado
ORDER BY receita_total DESC;

-- 3.2 Produtos com giro lento (baixa rotatividade)
SELECT 
    p.nome_produto,
    p.marca,
    c.nome_categoria,
    e.quantidade_disponivel,
    e.data_ultima_saida,
    DATEDIFF(CURDATE(), e.data_ultima_saida) AS dias_sem_venda,
    COALESCE(vendas.total_vendido, 0) AS total_vendido_historico,
    COALESCE(vendas.ultima_venda, 'Nunca vendido') AS data_ultima_venda,
    p.preco_atual * e.quantidade_disponivel AS valor_estoque_parado
FROM PRODUTO p
INNER JOIN ESTOQUE e ON p.id_produto = e.id_produto
INNER JOIN CATEGORIA c ON p.id_categoria = c.id_categoria
LEFT JOIN (
    SELECT 
        ip.id_produto,
        SUM(ip.quantidade) AS total_vendido,
        MAX(DATE(ped.data_pedido)) AS ultima_venda
    FROM ITEM_PEDIDO ip
    INNER JOIN PEDIDO ped ON ip.id_pedido = ped.id_pedido
    WHERE ped.id_status NOT IN (6)
    GROUP BY ip.id_produto
) vendas ON p.id_produto = vendas.id_produto
WHERE p.ativo = TRUE
  AND e.quantidade_disponivel > 0
  AND (e.data_ultima_saida IS NULL OR DATEDIFF(CURDATE(), e.data_ultima_saida) > 30)
ORDER BY dias_sem_venda DESC, valor_estoque_parado DESC;

-- 3.3 Cross-selling: Produtos frequentemente comprados juntos
SELECT 
    p1.nome_produto AS produto_1,
    p2.nome_produto AS produto_2,
    COUNT(*) AS vezes_comprados_juntos,
    COUNT(DISTINCT ped1.id_cliente) AS clientes_distintos,
    AVG(ped1.valor_total) AS ticket_medio_conjunto
FROM ITEM_PEDIDO ip1
INNER JOIN PRODUTO p1 ON ip1.id_produto = p1.id_produto
INNER JOIN PEDIDO ped1 ON ip1.id_pedido = ped1.id_pedido
INNER JOIN ITEM_PEDIDO ip2 ON ip1.id_pedido = ip2.id_pedido AND ip1.id_produto < ip2.id_produto
INNER JOIN PRODUTO p2 ON ip2.id_produto = p2.id_produto
WHERE ped1.id_status NOT IN (6)
GROUP BY p1.id_produto, p2.id_produto
HAVING COUNT(*) >= 2
ORDER BY vezes_comprados_juntos DESC, clientes_distintos DESC
LIMIT 15;

-- ============================================================================
-- SEÇÃO 4: ANÁLISE DE PERFORMANCE DE MARKETING
-- ============================================================================

-- 4.1 Efetividade dos cupons de desconto
SELECT 
    cd.codigo_cupom,
    cd.descricao,
    cd.tipo_desconto,
    cd.valor_desconto,
    COUNT(cu.id_cupom_utilizado) AS total_usos,
    COUNT(DISTINCT cu.id_cliente) AS clientes_unicos,
    SUM(cu.valor_desconto_aplicado) AS desconto_total_concedido,
    AVG(ped.valor_total) AS ticket_medio_com_cupom,
    -- ROI do cupom (receita gerada vs desconto concedido)
    ROUND(
        (SUM(ped.valor_produtos) - SUM(cu.valor_desconto_aplicado)) / 
        SUM(cu.valor_desconto_aplicado), 2
    ) AS roi_cupom
FROM CUPOM_DESCONTO cd
INNER JOIN CUPOM_UTILIZADO cu ON cd.id_cupom = cu.id_cupom
INNER JOIN PEDIDO ped ON cu.id_pedido = ped.id_pedido
WHERE ped.id_status NOT IN (6)
GROUP BY cd.id_cupom
ORDER BY roi_cupom DESC;

-- 4.2 Taxa de conversão do carrinho para pedido
SELECT 
    'Total de Carrinhos com Itens' AS metrica,
    COUNT(DISTINCT ic.id_carrinho) AS valor
FROM ITEM_CARRINHO ic

UNION ALL

SELECT 
    'Carrinhos Convertidos em Pedidos',
    COUNT(DISTINCT conversao.id_carrinho)
FROM (
    SELECT DISTINCT ic.id_carrinho
    FROM ITEM_CARRINHO ic
    INNER JOIN CARRINHO c ON ic.id_carrinho = c.id_carrinho
    INNER JOIN PEDIDO p ON c.id_cliente = p.id_cliente
    WHERE EXISTS (
        SELECT 1 FROM ITEM_PEDIDO ip 
        WHERE ip.id_pedido = p.id_pedido 
        AND ip.id_produto = ic.id_produto
    )
) conversao

UNION ALL

SELECT 
    'Taxa de Conversão (%)',
    CONCAT(
        ROUND(
            (SELECT COUNT(DISTINCT conversao.id_carrinho)
             FROM (
                 SELECT DISTINCT ic.id_carrinho
                 FROM ITEM_CARRINHO ic
                 INNER JOIN CARRINHO c ON ic.id_carrinho = c.id_carrinho
                 INNER JOIN PEDIDO p ON c.id_cliente = p.id_cliente
                 WHERE EXISTS (
                     SELECT 1 FROM ITEM_PEDIDO ip 
                     WHERE ip.id_pedido = p.id_pedido 
                     AND ip.id_produto = ic.id_produto
                 )
             ) conversao) * 100.0 / 
            (SELECT COUNT(DISTINCT ic.id_carrinho) FROM ITEM_CARRINHO ic), 2
        ), 
        '%'
    );

-- 4.3 Análise de abandono de carrinho por valor
SELECT 
    CASE 
        WHEN valor_carrinho < 500 THEN 'Até R$ 500'
        WHEN valor_carrinho < 1000 THEN 'R$ 500 - R$ 1.000'
        WHEN valor_carrinho < 2000 THEN 'R$ 1.000 - R$ 2.000'
        ELSE 'Acima de R$ 2.000'
    END AS faixa_valor,
    COUNT(*) AS carrinhos_abandonados,
    AVG(valor_carrinho) AS valor_medio,
    SUM(valor_carrinho) AS valor_total_abandonado,
    AVG(dias_abandonado) AS dias_medio_abandono
FROM (
    SELECT 
        c.id_carrinho,
        c.id_cliente,
        SUM(ic.quantidade * ic.preco_unitario) AS valor_carrinho,
        DATEDIFF(CURDATE(), c.data_ultima_alteracao) AS dias_abandonado
    FROM CARRINHO c
    INNER JOIN ITEM_CARRINHO ic ON c.id_carrinho = ic.id_carrinho
    WHERE DATEDIFF(CURDATE(), c.data_ultima_alteracao) > 7
      AND NOT EXISTS (
          SELECT 1 FROM PEDIDO p 
          WHERE p.id_cliente = c.id_cliente 
          AND p.data_pedido > c.data_ultima_alteracao
      )
    GROUP BY c.id_carrinho, c.id_cliente, c.data_ultima_alteracao
) carrinhos_abandonados
GROUP BY faixa_valor
ORDER BY AVG(valor_carrinho);

-- ============================================================================
-- SEÇÃO 5: ANÁLISE DE SATISFAÇÃO E QUALIDADE
-- ============================================================================

-- 5.1 Produtos com menor satisfação (nota < 3.5)
SELECT 
    p.nome_produto,
    p.marca,
    c.nome_categoria,
    COUNT(a.id_avaliacao) AS total_avaliacoes,
    ROUND(AVG(a.nota), 2) AS nota_media,
    COUNT(CASE WHEN a.nota <= 2 THEN 1 END) AS avaliacoes_ruins,
    ROUND(COUNT(CASE WHEN a.nota <= 2 THEN 1 END) * 100.0 / COUNT(a.id_avaliacao), 2) AS percentual_ruins,
    -- Últimas avaliações negativas
    (SELECT GROUP_CONCAT(aa.comentario SEPARATOR ' | ')
     FROM AVALIACAO aa 
     WHERE aa.id_produto = p.id_produto 
     AND aa.nota <= 2 
     AND aa.comentario IS NOT NULL
     ORDER BY aa.data_avaliacao DESC 
     LIMIT 3
    ) AS ultimos_comentarios_negativos
FROM PRODUTO p
INNER JOIN AVALIACAO a ON p.id_produto = a.id_produto
INNER JOIN CATEGORIA c ON p.id_categoria = c.id_categoria
WHERE p.ativo = TRUE
GROUP BY p.id_produto
HAVING AVG(a.nota) < 3.5 AND COUNT(a.id_avaliacao) >= 3
ORDER BY nota_media ASC, total_avaliacoes DESC;

-- 5.2 Evolução das avaliações ao longo do tempo
SELECT 
    DATE_FORMAT(a.data_avaliacao, '%Y-%m') AS mes_ano,
    COUNT(a.id_avaliacao) AS total_avaliacoes,
    ROUND(AVG(a.nota), 2) AS nota_media_mensal,
    COUNT(CASE WHEN a.nota >= 4 THEN 1 END) AS avaliacoes_positivas,
    COUNT(CASE WHEN a.nota <= 2 THEN 1 END) AS avaliacoes_negativas,
    ROUND(COUNT(CASE WHEN a.nota >= 4 THEN 1 END) * 100.0 / COUNT(a.id_avaliacao), 2) AS percentual_positivas,
    -- NPS simplificado (promotores - detratores)
    ROUND(
        (COUNT(CASE WHEN a.nota >= 4 THEN 1 END) - COUNT(CASE WHEN a.nota <= 2 THEN 1 END)) * 100.0 / 
        COUNT(a.id_avaliacao), 2
    ) AS nps_simplificado
FROM AVALIACAO a
WHERE a.data_avaliacao >= DATE_SUB(CURDATE(), INTERVAL 12 MONTH)
GROUP BY DATE_FORMAT(a.data_avaliacao, '%Y-%m')
ORDER BY mes_ano;

-- ============================================================================
-- SEÇÃO 6: RELATÓRIOS EXECUTIVOS
-- ============================================================================

-- 6.1 Dashboard executivo - KPIs principais
SELECT 
    'Receita Total (Últimos 30 dias)' AS kpi,
    CONCAT('R$ ', FORMAT(SUM(p.valor_total), 2)) AS valor
FROM PEDIDO p
WHERE p.data_pedido >= DATE_SUB(CURDATE(), INTERVAL 30 DAY)
  AND p.id_status NOT IN (6)

UNION ALL

SELECT 
    'Número de Pedidos (Últimos 30 dias)',
    COUNT(*)
FROM PEDIDO p
WHERE p.data_pedido >= DATE_SUB(CURDATE(), INTERVAL 30 DAY)
  AND p.id_status NOT IN (6)

UNION ALL

SELECT 
    'Ticket Médio (Últimos 30 dias)',
    CONCAT('R$ ', FORMAT(AVG(p.valor_total), 2))
FROM PEDIDO p
WHERE p.data_pedido >= DATE_SUB(CURDATE(), INTERVAL 30 DAY)
  AND p.id_status NOT IN (6)

UNION ALL

SELECT 
    'Clientes Ativos (Com pedido nos últimos 90 dias)',
    COUNT(DISTINCT p.id_cliente)
FROM PEDIDO p
WHERE p.data_pedido >= DATE_SUB(CURDATE(), INTERVAL 90 DAY)
  AND p.id_status NOT IN (6)

UNION ALL

SELECT 
    'Taxa de Conversão de Carrinho',
    CONCAT(
        ROUND(
            (SELECT COUNT(DISTINCT c.id_cliente)
             FROM PEDIDO p 
             INNER JOIN CARRINHO c ON p.id_cliente = c.id_cliente
             WHERE p.data_pedido >= DATE_SUB(CURDATE(), INTERVAL 30 DAY)) * 100.0 /
            (SELECT COUNT(DISTINCT id_carrinho) 
             FROM ITEM_CARRINHO), 2
        ), '%'
    )

UNION ALL

SELECT 
    'Nota Média dos Produtos',
    ROUND(AVG(a.nota), 2)
FROM AVALIACAO a

UNION ALL

SELECT 
    'Produtos com Estoque Baixo',
    COUNT(*)
FROM ESTOQUE e
WHERE e.quantidade_disponivel < e.estoque_minimo

UNION ALL

SELECT 
    'Valor Total em Estoque',
    CONCAT('R$ ', FORMAT(SUM(p.preco_atual * e.quantidade_disponivel), 2))
FROM PRODUTO p
INNER JOIN ESTOQUE e ON p.id_produto = e.id_produto
WHERE p.ativo = TRUE;

-- 6.2 Comparativo mensal (mês atual vs mês anterior)
WITH metricas_mensais AS (
    SELECT 
        CASE 
            WHEN MONTH(p.data_pedido) = MONTH(CURDATE()) AND YEAR(p.data_pedido) = YEAR(CURDATE()) THEN 'Atual'
            WHEN p.data_pedido >= DATE_SUB(DATE_SUB(CURDATE(), INTERVAL DAY(CURDATE())-1 DAY), INTERVAL 1 MONTH)
                 AND p.data_pedido < DATE_SUB(CURDATE(), INTERVAL DAY(CURDATE())-1 DAY) THEN 'Anterior'
        END AS periodo,
        COUNT(*) AS total_pedidos,
        SUM(p.valor_total) AS receita_total,
        AVG(p.valor_total) AS ticket_medio,
        COUNT(DISTINCT p.id_cliente) AS clientes_unicos
    FROM PEDIDO p
    WHERE p.id_status NOT IN (6)
      AND p.data_pedido >= DATE_SUB(DATE_SUB(CURDATE(), INTERVAL DAY(CURDATE())-1 DAY), INTERVAL 1 MONTH)
    GROUP BY periodo
)
SELECT 
    'Total de Pedidos' AS metrica,
    MAX(CASE WHEN periodo = 'Anterior' THEN total_pedidos END) AS mes_anterior,
    MAX(CASE WHEN periodo = 'Atual' THEN total_pedidos END) AS mes_atual,
    CONCAT(
        ROUND(
            ((MAX(CASE WHEN periodo = 'Atual' THEN total_pedidos END) - 
              MAX(CASE WHEN periodo = 'Anterior' THEN total_pedidos END)) * 100.0 / 
             MAX(CASE WHEN periodo = 'Anterior' THEN total_pedidos END)), 2
        ), '%'
    ) AS variacao
FROM metricas_mensais

UNION ALL

SELECT 
    'Receita Total',
    CONCAT('R$ ', FORMAT(MAX(CASE WHEN periodo = 'Anterior' THEN receita_total END), 2)),
    CONCAT('R$ ', FORMAT(MAX(CASE WHEN periodo = 'Atual' THEN receita_total END), 2)),
    CONCAT(
        ROUND(
            ((MAX(CASE WHEN periodo = 'Atual' THEN receita_total END) - 
              MAX(CASE WHEN periodo = 'Anterior' THEN receita_total END)) * 100.0 / 
             MAX(CASE WHEN periodo = 'Anterior' THEN receita_total END)), 2
        ), '%'
    )
FROM metricas_mensais

UNION ALL

SELECT 
    'Ticket Médio',
    CONCAT('R$ ', FORMAT(MAX(CASE WHEN periodo = 'Anterior' THEN ticket_medio END), 2)),
    CONCAT('R$ ', FORMAT(MAX(CASE WHEN periodo = 'Atual' THEN ticket_medio END), 2)),
    CONCAT(
        ROUND(
            ((MAX(CASE WHEN periodo = 'Atual' THEN ticket_medio END) - 
              MAX(CASE WHEN periodo = 'Anterior' THEN ticket_medio END)) * 100.0 / 
             MAX(CASE WHEN periodo = 'Anterior' THEN ticket_medio END)), 2
        ), '%'
    )
FROM metricas_mensais;

-- ============================================================================
-- SEÇÃO 7: STORED PROCEDURES PARA RELATÓRIOS
-- ============================================================================

-- 7.1 Procedure para relatório de vendas por período
DELIMITER //
CREATE PROCEDURE sp_relatorio_vendas_periodo(
    IN data_inicio DATE,
    IN data_fim DATE
)
BEGIN
    SELECT 
        DATE(p.data_pedido) AS data_pedido,
        COUNT(DISTINCT p.id_pedido) AS total_pedidos,
        COUNT(ip.id_item_pedido) AS total_itens,
        SUM(p.valor_total) AS receita_dia,
        AVG(p.valor_total) AS ticket_medio
    FROM PEDIDO p
    LEFT JOIN ITEM_PEDIDO ip ON p.id_pedido = ip.id_pedido
    WHERE DATE(p.data_pedido) BETWEEN data_inicio AND data_fim
      AND p.id_status NOT IN (6)
    GROUP BY DATE(p.data_pedido)
    ORDER BY data_pedido;
END//
DELIMITER ;

-- 7.2 Procedure para análise de cliente específico
DELIMITER //
CREATE PROCEDURE sp_analise_cliente(
    IN cliente_id INT
)
BEGIN
    -- Informações básicas do cliente
    SELECT 
        c.nome_completo,
        c.email,
        c.telefone,
        c.tipo_pessoa,
        c.data_cadastro,
        COUNT(DISTINCT p.id_pedido) AS total_pedidos,
        COALESCE(SUM(p.valor_total), 0) AS valor_total_gasto,
        COALESCE(AVG(p.valor_total), 0) AS ticket_medio,
        MAX(p.data_pedido) AS ultimo_pedido
    FROM CLIENTE c
    LEFT JOIN PEDIDO p ON c.id_cliente = p.id_cliente AND p.id_status NOT IN (6)
    WHERE c.id_cliente = cliente_id
    GROUP BY c.id_cliente;
    
    -- Histórico de pedidos
    SELECT 
        p.numero_pedido,
        p.data_pedido,
        s.nome_status,
        p.valor_total
    FROM PEDIDO p
    INNER JOIN STATUS_PEDIDO s ON p.id_status = s.id_status
    WHERE p.id_cliente = cliente_id
    ORDER BY p.data_pedido DESC;
    
    -- Produtos mais comprados
    SELECT 
        prod.nome_produto,
        SUM(ip.quantidade) AS quantidade_total,
        COUNT(ip.id_item_pedido) AS vezes_comprado,
        SUM(ip.preco_total) AS valor_total_produto
    FROM PEDIDO p
    INNER JOIN ITEM_PEDIDO ip ON p.id_pedido = ip.id_pedido
    INNER JOIN PRODUTO prod ON ip.id_produto = prod.id_produto
    WHERE p.id_cliente = cliente_id
      AND p.id_status NOT IN (6)
    GROUP BY prod.id_produto
    ORDER BY quantidade_total DESC
    LIMIT 10;
END//
DELIMITER ;

-- ============================================================================
-- EXEMPLO DE USO DAS PROCEDURES
-- ============================================================================

-- Relatório de vendas dos últimos 7 dias
-- CALL sp_relatorio_vendas_periodo(DATE_SUB(CURDATE(), INTERVAL 7 DAY), CURDATE());

-- Análise do cliente com ID 1
-- CALL sp_analise_cliente(1);

-- ============================================================================
-- MENSAGEM FINAL
-- ============================================================================

SELECT 'CONSULTAS AVANÇADAS CRIADAS COM SUCESSO!' AS status,
       'Execute as procedures com: CALL sp_relatorio_vendas_periodo() ou CALL sp_analise_cliente()' AS dica;