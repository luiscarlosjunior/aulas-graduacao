-- ============================================================================
-- SISTEMA DE E-COMMERCE TECHSTORE
-- Consultas Básicas - Exemplos Fundamentais
-- ============================================================================
-- Descrição: Consultas essenciais para operação do e-commerce
-- Versão: 1.0
-- Data: 2024
-- ============================================================================

USE ecommerce_techstore;

-- ============================================================================
-- SEÇÃO 1: CONSULTAS DE PRODUTOS
-- ============================================================================

-- 1.1 Listar todos os produtos ativos com informações básicas
SELECT 
    p.nome_produto,
    p.marca,
    p.preco_atual,
    c.nome_categoria,
    f.nome_fantasia AS fornecedor
FROM PRODUTO p
INNER JOIN CATEGORIA c ON p.id_categoria = c.id_categoria
INNER JOIN FORNECEDOR f ON p.id_fornecedor = f.id_fornecedor
WHERE p.ativo = TRUE
ORDER BY p.nome_produto;

-- 1.2 Buscar produtos por categoria específica
SELECT 
    p.nome_produto,
    p.descricao_curta,
    p.preco_atual,
    e.quantidade_disponivel
FROM PRODUTO p
INNER JOIN CATEGORIA c ON p.id_categoria = c.id_categoria
INNER JOIN ESTOQUE e ON p.id_produto = e.id_produto
WHERE c.nome_categoria = 'Android'
  AND p.ativo = TRUE
ORDER BY p.preco_atual;

-- 1.3 Produtos em promoção (preço abaixo da média da categoria)
SELECT 
    p.nome_produto,
    p.preco_atual,
    ROUND(AVG_cat.preco_medio, 2) AS preco_medio_categoria,
    ROUND(((p.preco_atual - AVG_cat.preco_medio) / AVG_cat.preco_medio) * 100, 2) AS desconto_percentual
FROM PRODUTO p
INNER JOIN CATEGORIA c ON p.id_categoria = c.id_categoria
INNER JOIN (
    SELECT 
        id_categoria, 
        AVG(preco_atual) AS preco_medio
    FROM PRODUTO 
    WHERE ativo = TRUE 
    GROUP BY id_categoria
) AVG_cat ON p.id_categoria = AVG_cat.id_categoria
WHERE p.preco_atual < AVG_cat.preco_medio
  AND p.ativo = TRUE
ORDER BY desconto_percentual;

-- 1.4 Produtos com estoque baixo (abaixo do mínimo)
SELECT 
    p.nome_produto,
    p.marca,
    e.quantidade_disponivel,
    e.estoque_minimo,
    (e.estoque_minimo - e.quantidade_disponivel) AS quantidade_repor
FROM PRODUTO p
INNER JOIN ESTOQUE e ON p.id_produto = e.id_produto
WHERE e.quantidade_disponivel < e.estoque_minimo
ORDER BY quantidade_repor DESC;

-- ============================================================================
-- SEÇÃO 2: CONSULTAS DE CLIENTES
-- ============================================================================

-- 2.1 Listar clientes ativos com informações de contato
SELECT 
    c.nome_completo,
    c.email,
    c.telefone,
    c.tipo_pessoa,
    c.data_cadastro
FROM CLIENTE c
WHERE c.ativo = TRUE
ORDER BY c.data_cadastro DESC;

-- 2.2 Clientes com endereços cadastrados
SELECT 
    c.nome_completo,
    c.email,
    e.tipo_endereco,
    CONCAT(e.logradouro, ', ', e.numero) AS endereco,
    e.bairro,
    e.cidade,
    e.estado,
    e.endereco_padrao
FROM CLIENTE c
INNER JOIN ENDERECO e ON c.id_cliente = e.id_cliente
WHERE c.ativo = TRUE
ORDER BY c.nome_completo, e.endereco_padrao DESC;

-- 2.3 Clientes por tipo (Pessoa Física vs Jurídica)
SELECT 
    c.tipo_pessoa,
    COUNT(*) AS total_clientes,
    ROUND(COUNT(*) * 100.0 / (SELECT COUNT(*) FROM CLIENTE WHERE ativo = TRUE), 2) AS percentual
FROM CLIENTE c
WHERE c.ativo = TRUE
GROUP BY c.tipo_pessoa
ORDER BY total_clientes DESC;

-- 2.4 Clientes com formas de pagamento cadastradas
SELECT 
    c.nome_completo,
    fp.tipo AS tipo_pagamento,
    fp.descricao,
    fp.ativo AS pagamento_ativo
FROM CLIENTE c
INNER JOIN FORMA_PAGAMENTO fp ON c.id_cliente = fp.id_cliente
WHERE c.ativo = TRUE AND fp.ativo = TRUE
ORDER BY c.nome_completo, fp.tipo;

-- ============================================================================
-- SEÇÃO 3: CONSULTAS DE CARRINHO
-- ============================================================================

-- 3.1 Itens no carrinho por cliente
SELECT 
    c.nome_completo,
    p.nome_produto,
    ic.quantidade,
    ic.preco_unitario,
    (ic.quantidade * ic.preco_unitario) AS subtotal,
    ic.data_adicao
FROM CLIENTE c
INNER JOIN CARRINHO car ON c.id_cliente = car.id_cliente
INNER JOIN ITEM_CARRINHO ic ON car.id_carrinho = ic.id_carrinho
INNER JOIN PRODUTO p ON ic.id_produto = p.id_produto
ORDER BY c.nome_completo, ic.data_adicao DESC;

-- 3.2 Resumo dos carrinhos ativos (com itens)
SELECT 
    c.nome_completo,
    COUNT(ic.id_item_carrinho) AS total_itens,
    SUM(ic.quantidade) AS quantidade_total,
    SUM(ic.quantidade * ic.preco_unitario) AS valor_total_carrinho
FROM CLIENTE c
INNER JOIN CARRINHO car ON c.id_cliente = car.id_cliente
INNER JOIN ITEM_CARRINHO ic ON car.id_carrinho = ic.id_carrinho
GROUP BY c.id_cliente, c.nome_completo
ORDER BY valor_total_carrinho DESC;

-- 3.3 Carrinhos abandonados (sem alteração há mais de 7 dias)
SELECT 
    c.nome_completo,
    c.email,
    car.data_ultima_alteracao,
    DATEDIFF(NOW(), car.data_ultima_alteracao) AS dias_sem_alteracao,
    COUNT(ic.id_item_carrinho) AS itens_no_carrinho
FROM CLIENTE c
INNER JOIN CARRINHO car ON c.id_cliente = car.id_cliente
LEFT JOIN ITEM_CARRINHO ic ON car.id_carrinho = ic.id_carrinho
WHERE DATEDIFF(NOW(), car.data_ultima_alteracao) > 7
  AND EXISTS (SELECT 1 FROM ITEM_CARRINHO WHERE id_carrinho = car.id_carrinho)
GROUP BY c.id_cliente
ORDER BY dias_sem_alteracao DESC;

-- ============================================================================
-- SEÇÃO 4: CONSULTAS DE PEDIDOS
-- ============================================================================

-- 4.1 Listar pedidos com informações básicas
SELECT 
    p.numero_pedido,
    c.nome_completo AS cliente,
    s.nome_status,
    p.data_pedido,
    p.valor_total,
    p.data_entrega_prevista
FROM PEDIDO p
INNER JOIN CLIENTE c ON p.id_cliente = c.id_cliente
INNER JOIN STATUS_PEDIDO s ON p.id_status = s.id_status
ORDER BY p.data_pedido DESC;

-- 4.2 Pedidos por status
SELECT 
    s.nome_status,
    COUNT(*) AS total_pedidos,
    SUM(p.valor_total) AS valor_total_status
FROM PEDIDO p
INNER JOIN STATUS_PEDIDO s ON p.id_status = s.id_status
GROUP BY s.id_status, s.nome_status
ORDER BY s.ordem_status;

-- 4.3 Itens de um pedido específico
SELECT 
    p.numero_pedido,
    prod.nome_produto,
    prod.marca,
    ip.quantidade,
    ip.preco_unitario,
    ip.preco_total
FROM PEDIDO p
INNER JOIN ITEM_PEDIDO ip ON p.id_pedido = ip.id_pedido
INNER JOIN PRODUTO prod ON ip.id_produto = prod.id_produto
WHERE p.numero_pedido = 'PED-2024-000001'
ORDER BY prod.nome_produto;

-- 4.4 Pedidos com entrega em atraso
SELECT 
    p.numero_pedido,
    c.nome_completo AS cliente,
    p.data_entrega_prevista,
    DATEDIFF(CURDATE(), p.data_entrega_prevista) AS dias_atraso,
    s.nome_status
FROM PEDIDO p
INNER JOIN CLIENTE c ON p.id_cliente = c.id_cliente
INNER JOIN STATUS_PEDIDO s ON p.id_status = s.id_status
WHERE p.data_entrega_prevista < CURDATE()
  AND s.id_status NOT IN (5, 6) -- Não entregue nem cancelado
ORDER BY dias_atraso DESC;

-- ============================================================================
-- SEÇÃO 5: CONSULTAS DE ESTOQUE
-- ============================================================================

-- 5.1 Status geral do estoque
SELECT 
    p.nome_produto,
    p.marca,
    e.quantidade_disponivel,
    e.quantidade_reservada,
    (e.quantidade_disponivel + e.quantidade_reservada) AS quantidade_total,
    e.estoque_minimo,
    CASE 
        WHEN e.quantidade_disponivel = 0 THEN 'ESGOTADO'
        WHEN e.quantidade_disponivel < e.estoque_minimo THEN 'BAIXO'
        ELSE 'NORMAL'
    END AS status_estoque
FROM PRODUTO p
INNER JOIN ESTOQUE e ON p.id_produto = e.id_produto
WHERE p.ativo = TRUE
ORDER BY e.quantidade_disponivel;

-- 5.2 Produtos mais reservados (no carrinho)
SELECT 
    p.nome_produto,
    p.marca,
    e.quantidade_reservada,
    e.quantidade_disponivel,
    ROUND((e.quantidade_reservada * 100.0) / 
          NULLIF((e.quantidade_disponivel + e.quantidade_reservada), 0), 2) AS percentual_reservado
FROM PRODUTO p
INNER JOIN ESTOQUE e ON p.id_produto = e.id_produto
WHERE e.quantidade_reservada > 0
ORDER BY e.quantidade_reservada DESC;

-- 5.3 Movimentação de estoque (última entrada e saída)
SELECT 
    p.nome_produto,
    e.data_ultima_entrada,
    e.data_ultima_saida,
    DATEDIFF(CURDATE(), e.data_ultima_entrada) AS dias_desde_entrada,
    DATEDIFF(CURDATE(), e.data_ultima_saida) AS dias_desde_saida
FROM PRODUTO p
INNER JOIN ESTOQUE e ON p.id_produto = e.id_produto
WHERE p.ativo = TRUE
  AND (e.data_ultima_entrada IS NOT NULL OR e.data_ultima_saida IS NOT NULL)
ORDER BY e.data_ultima_entrada DESC;

-- ============================================================================
-- SEÇÃO 6: CONSULTAS DE AVALIAÇÕES
-- ============================================================================

-- 6.1 Produtos com suas avaliações
SELECT 
    p.nome_produto,
    a.nota,
    a.titulo,
    a.comentario,
    c.nome_completo AS cliente,
    a.data_avaliacao,
    a.verificada
FROM PRODUTO p
INNER JOIN AVALIACAO a ON p.id_produto = a.id_produto
INNER JOIN CLIENTE c ON a.id_cliente = c.id_cliente
ORDER BY a.data_avaliacao DESC;

-- 6.2 Ranking de produtos por nota média
SELECT 
    p.nome_produto,
    p.marca,
    COUNT(a.id_avaliacao) AS total_avaliacoes,
    ROUND(AVG(a.nota), 2) AS nota_media,
    SUM(a.votos_uteis) AS total_votos_uteis
FROM PRODUTO p
LEFT JOIN AVALIACAO a ON p.id_produto = a.id_produto
WHERE p.ativo = TRUE
GROUP BY p.id_produto, p.nome_produto, p.marca
HAVING COUNT(a.id_avaliacao) > 0
ORDER BY nota_media DESC, total_avaliacoes DESC;

-- 6.3 Avaliações mais úteis
SELECT 
    p.nome_produto,
    a.titulo,
    a.comentario,
    a.nota,
    c.nome_completo AS avaliador,
    a.votos_uteis,
    a.votos_totais,
    ROUND((a.votos_uteis * 100.0) / NULLIF(a.votos_totais, 0), 2) AS percentual_util
FROM AVALIACAO a
INNER JOIN PRODUTO p ON a.id_produto = p.id_produto
INNER JOIN CLIENTE c ON a.id_cliente = c.id_cliente
WHERE a.votos_totais >= 5
ORDER BY percentual_util DESC, a.votos_uteis DESC
LIMIT 10;

-- ============================================================================
-- SEÇÃO 7: CONSULTAS DE CUPONS
-- ============================================================================

-- 7.1 Cupons ativos e disponíveis
SELECT 
    cd.codigo_cupom,
    cd.descricao,
    cd.tipo_desconto,
    cd.valor_desconto,
    cd.valor_minimo_pedido,
    cd.data_inicio,
    cd.data_fim,
    cd.limite_uso_total,
    cd.usado_total,
    CASE 
        WHEN cd.limite_uso_total IS NULL THEN 'ILIMITADO'
        ELSE CONCAT(cd.limite_uso_total - cd.usado_total, ' restantes')
    END AS disponibilidade
FROM CUPOM_DESCONTO cd
WHERE cd.ativo = TRUE
  AND CURDATE() BETWEEN cd.data_inicio AND cd.data_fim
  AND (cd.limite_uso_total IS NULL OR cd.usado_total < cd.limite_uso_total)
ORDER BY cd.data_fim;

-- 7.2 Histórico de uso de cupons
SELECT 
    cd.codigo_cupom,
    p.numero_pedido,
    c.nome_completo AS cliente,
    cu.valor_desconto_aplicado,
    cu.data_utilizacao
FROM CUPOM_UTILIZADO cu
INNER JOIN CUPOM_DESCONTO cd ON cu.id_cupom = cd.id_cupom
INNER JOIN PEDIDO p ON cu.id_pedido = p.id_pedido
INNER JOIN CLIENTE c ON cu.id_cliente = c.id_cliente
ORDER BY cu.data_utilizacao DESC;

-- 7.3 Cupons mais populares
SELECT 
    cd.codigo_cupom,
    cd.descricao,
    COUNT(cu.id_cupom_utilizado) AS total_usos,
    SUM(cu.valor_desconto_aplicado) AS desconto_total_concedido
FROM CUPOM_DESCONTO cd
LEFT JOIN CUPOM_UTILIZADO cu ON cd.id_cupom = cu.id_cupom
GROUP BY cd.id_cupom
HAVING COUNT(cu.id_cupom_utilizado) > 0
ORDER BY total_usos DESC, desconto_total_concedido DESC;

-- ============================================================================
-- SEÇÃO 8: CONSULTAS DE CATEGORIAS
-- ============================================================================

-- 8.1 Hierarquia de categorias
SELECT 
    c1.nome_categoria AS categoria_pai,
    c2.nome_categoria AS subcategoria,
    COUNT(p.id_produto) AS total_produtos
FROM CATEGORIA c1
INNER JOIN CATEGORIA c2 ON c1.id_categoria = c2.id_categoria_pai
LEFT JOIN PRODUTO p ON c2.id_categoria = p.id_categoria
WHERE c1.ativo = TRUE AND c2.ativo = TRUE
GROUP BY c1.id_categoria, c2.id_categoria
ORDER BY c1.ordem_exibicao, c2.ordem_exibicao;

-- 8.2 Categorias com mais produtos
SELECT 
    c.nome_categoria,
    COUNT(p.id_produto) AS total_produtos,
    AVG(p.preco_atual) AS preco_medio
FROM CATEGORIA c
LEFT JOIN PRODUTO p ON c.id_categoria = p.id_categoria AND p.ativo = TRUE
WHERE c.ativo = TRUE
GROUP BY c.id_categoria, c.nome_categoria
ORDER BY total_produtos DESC;

-- ============================================================================
-- SEÇÃO 9: CONSULTAS USANDO VIEWS
-- ============================================================================

-- 9.1 Usando a view de produto completo
SELECT 
    nome_produto,
    marca,
    preco_atual,
    nome_categoria,
    fornecedor,
    status_estoque
FROM vw_produto_completo
WHERE status_estoque = 'NORMAL'
ORDER BY preco_atual;

-- 9.2 Usando a view de carrinho resumo
SELECT 
    nome_completo,
    total_itens,
    quantidade_total,
    valor_total_carrinho,
    data_ultima_alteracao
FROM vw_carrinho_resumo
WHERE valor_total_carrinho > 1000
ORDER BY valor_total_carrinho DESC;

-- 9.3 Usando a view de pedido completo
SELECT 
    numero_pedido,
    cliente,
    nome_status,
    data_pedido,
    valor_total,
    total_itens
FROM vw_pedido_completo
WHERE nome_status = 'Entregue'
ORDER BY data_pedido DESC;

-- ============================================================================
-- SEÇÃO 10: CONSULTAS DE VALIDAÇÃO E INTEGRIDADE
-- ============================================================================

-- 10.1 Verificar integridade de dados
SELECT 'Produtos sem estoque' AS verificacao, COUNT(*) AS total
FROM PRODUTO p
LEFT JOIN ESTOQUE e ON p.id_produto = e.id_produto
WHERE e.id_produto IS NULL

UNION ALL

SELECT 'Clientes sem carrinho' AS verificacao, COUNT(*) AS total
FROM CLIENTE c
LEFT JOIN CARRINHO car ON c.id_cliente = car.id_cliente
WHERE car.id_cliente IS NULL

UNION ALL

SELECT 'Pedidos sem itens' AS verificacao, COUNT(*) AS total
FROM PEDIDO p
LEFT JOIN ITEM_PEDIDO ip ON p.id_pedido = ip.id_pedido
WHERE ip.id_pedido IS NULL;

-- 10.2 Estatísticas gerais do sistema
SELECT 
    'Total de Clientes Ativos' AS metrica,
    COUNT(*) AS valor
FROM CLIENTE WHERE ativo = TRUE

UNION ALL

SELECT 'Total de Produtos Ativos', COUNT(*)
FROM PRODUTO WHERE ativo = TRUE

UNION ALL

SELECT 'Total de Pedidos', COUNT(*)
FROM PEDIDO

UNION ALL

SELECT 'Valor Total em Pedidos', CONCAT('R$ ', FORMAT(SUM(valor_total), 2))
FROM PEDIDO

UNION ALL

SELECT 'Produtos com Avaliação', COUNT(DISTINCT id_produto)
FROM AVALIACAO

UNION ALL

SELECT 'Carrinho com Itens', COUNT(DISTINCT id_carrinho)
FROM ITEM_CARRINHO;

-- ============================================================================
-- MENSAGEM FINAL
-- ============================================================================

SELECT 'CONSULTAS BÁSICAS EXECUTADAS COM SUCESSO!' AS status;