-- ================
-- CONSULTAS AVANÇADAS - SISTEMA BARBEARIA
-- ================
-- Este script contém consultas mais complexas e análises avançadas
-- Execute após os scripts básicos
--
-- Autor: Exemplo educacional
-- Data: 2024
-- SGBD: Oracle Database
--

-- ================
-- 1. ANÁLISES TEMPORAIS
-- ================

-- Receita por mês dos últimos 6 meses
SELECT TO_CHAR(a.inicio, 'YYYY-MM') AS mes_ano,
       TO_CHAR(a.inicio, 'Month', 'NLS_DATE_LANGUAGE=PORTUGUESE') AS mes_nome,
       COUNT(a.id) AS total_agendamentos,
       COUNT(CASE WHEN a.status = 'CONCLUIDO' THEN 1 END) AS concluidos,
       SUM(CASE WHEN a.status = 'CONCLUIDO' THEN a.valor_total ELSE 0 END) AS receita,
       ROUND(AVG(CASE WHEN a.status = 'CONCLUIDO' THEN a.valor_total END), 2) AS ticket_medio
FROM agendamentos a
WHERE a.inicio >= ADD_MONTHS(SYSDATE, -6)
GROUP BY TO_CHAR(a.inicio, 'YYYY-MM'), TO_CHAR(a.inicio, 'Month', 'NLS_DATE_LANGUAGE=PORTUGUESE')
ORDER BY mes_ano;

-- Análise por dia da semana
SELECT TO_CHAR(a.inicio, 'Day', 'NLS_DATE_LANGUAGE=PORTUGUESE') AS dia_semana,
       TO_NUMBER(TO_CHAR(a.inicio, 'D')) AS num_dia,
       COUNT(a.id) AS total_agendamentos,
       AVG(a.valor_total) AS valor_medio,
       MIN(TO_CHAR(a.inicio, 'HH24:MI')) AS primeiro_horario,
       MAX(TO_CHAR(a.inicio, 'HH24:MI')) AS ultimo_horario
FROM agendamentos a
WHERE a.status = 'CONCLUIDO'
GROUP BY TO_CHAR(a.inicio, 'Day', 'NLS_DATE_LANGUAGE=PORTUGUESE'), TO_NUMBER(TO_CHAR(a.inicio, 'D'))
ORDER BY num_dia;

-- ================
-- 2. RANKING E TOP LISTS
-- ================

-- Top 5 clientes que mais gastaram
SELECT ROWNUM AS posicao,
       nome_cliente,
       total_gasto,
       total_agendamentos,
       valor_medio
FROM (
    SELECT c.nome AS nome_cliente,
           SUM(CASE WHEN a.status = 'CONCLUIDO' THEN a.valor_total ELSE 0 END) AS total_gasto,
           COUNT(a.id) AS total_agendamentos,
           ROUND(AVG(CASE WHEN a.status = 'CONCLUIDO' THEN a.valor_total END), 2) AS valor_medio
    FROM clientes c
    LEFT JOIN agendamentos a ON a.cliente_id = c.id
    GROUP BY c.id, c.nome
    HAVING SUM(CASE WHEN a.status = 'CONCLUIDO' THEN a.valor_total ELSE 0 END) > 0
    ORDER BY total_gasto DESC
)
WHERE ROWNUM <= 5;

-- Serviços mais rentáveis
WITH servicos_stats AS (
    SELECT s.nome AS servico,
           s.preco AS preco_tabela,
           COUNT(asv.servico_id) AS vezes_vendido,
           SUM(asv.quantidade) AS quantidade_total,
           SUM(asv.quantidade * asv.preco_unitario) AS receita_total,
           AVG(asv.preco_unitario) AS preco_medio_praticado
    FROM servicos s
    LEFT JOIN agendamento_servicos asv ON asv.servico_id = s.id
    JOIN agendamentos a ON a.id = asv.agendamento_id AND a.status = 'CONCLUIDO'
    GROUP BY s.id, s.nome, s.preco
)
SELECT servico,
       preco_tabela,
       vezes_vendido,
       receita_total,
       ROUND(receita_total / NULLIF(vezes_vendido, 0), 2) AS receita_media_por_venda,
       ROUND((preco_medio_praticado / preco_tabela) * 100, 1) AS percentual_preco_tabela
FROM servicos_stats
WHERE receita_total > 0
ORDER BY receita_total DESC;

-- ================
-- 3. ANÁLISE DE BARBEIROS
-- ================

-- Performance detalhada por barbeiro
SELECT b.nome AS barbeiro,
       b.especialidade,
       COUNT(a.id) AS total_agendamentos,
       COUNT(CASE WHEN a.status = 'CONCLUIDO' THEN 1 END) AS concluidos,
       COUNT(CASE WHEN a.status = 'CANCELADO' THEN 1 END) AS cancelados,
       ROUND(
           COUNT(CASE WHEN a.status = 'CONCLUIDO' THEN 1 END) * 100.0 / 
           NULLIF(COUNT(a.id), 0), 1
       ) AS taxa_conclusao_pct,
       SUM(CASE WHEN a.status = 'CONCLUIDO' THEN a.valor_total ELSE 0 END) AS receita_gerada,
       ROUND(AVG(CASE WHEN a.status = 'CONCLUIDO' THEN a.valor_total END), 2) AS ticket_medio
FROM barbeiros b
LEFT JOIN agendamentos a ON a.barbeiro_id = b.id
GROUP BY b.id, b.nome, b.especialidade
ORDER BY receita_gerada DESC NULLS LAST;

-- Barbeiros mais versáteis (que fazem mais tipos de serviços diferentes)
SELECT b.nome AS barbeiro,
       COUNT(DISTINCT s.id) AS tipos_servicos_diferentes,
       LISTAGG(s.nome, ', ') WITHIN GROUP (ORDER BY COUNT(*) DESC, s.nome) AS servicos_realizados,
       COUNT(asv.servico_id) AS total_servicos_prestados
FROM barbeiros b
JOIN agendamentos a ON a.barbeiro_id = b.id AND a.status = 'CONCLUIDO'
JOIN agendamento_servicos asv ON asv.agendamento_id = a.id
JOIN servicos s ON s.id = asv.servico_id
GROUP BY b.id, b.nome
ORDER BY tipos_servicos_diferentes DESC, total_servicos_prestados DESC;

-- ================
-- 4. ANÁLISE DE OCUPAÇÃO
-- ================

-- Taxa de ocupação por horário (considerando horário comercial 8h às 18h)
WITH horarios_comerciais AS (
    SELECT LEVEL + 7 AS hora
    FROM dual
    CONNECT BY LEVEL <= 11  -- 8h às 18h = 11 horas
),
agendamentos_por_hora AS (
    SELECT TO_NUMBER(TO_CHAR(a.inicio, 'HH24')) AS hora_agendamento,
           COUNT(*) AS total_agendamentos
    FROM agendamentos a
    WHERE a.status IN ('CONCLUIDO', 'MARCADO', 'EM_ANDAMENTO')
      AND TO_NUMBER(TO_CHAR(a.inicio, 'HH24')) BETWEEN 8 AND 18
    GROUP BY TO_NUMBER(TO_CHAR(a.inicio, 'HH24'))
)
SELECT hc.hora AS horario,
       hc.hora || ':00 - ' || (hc.hora + 1) || ':00' AS faixa_horaria,
       NVL(aph.total_agendamentos, 0) AS agendamentos,
       CASE 
           WHEN hc.hora BETWEEN 12 AND 14 THEN 'Almoço'
           WHEN hc.hora BETWEEN 8 AND 11 THEN 'Manhã'
           WHEN hc.hora BETWEEN 14 AND 18 THEN 'Tarde'
       END AS periodo
FROM horarios_comerciais hc
LEFT JOIN agendamentos_por_hora aph ON aph.hora_agendamento = hc.hora
ORDER BY hc.hora;

-- ================
-- 5. ANÁLISE DE FIDELIZAÇÃO
-- ================

-- Clientes por frequência de visitas
WITH cliente_frequencia AS (
    SELECT c.id,
           c.nome,
           COUNT(a.id) AS total_agendamentos,
           COUNT(CASE WHEN a.status = 'CONCLUIDO' THEN 1 END) AS visitas_concluidas,
           MIN(a.inicio) AS primeira_visita,
           MAX(a.inicio) AS ultima_visita,
           CASE 
               WHEN COUNT(CASE WHEN a.status = 'CONCLUIDO' THEN 1 END) >= 10 THEN 'VIP'
               WHEN COUNT(CASE WHEN a.status = 'CONCLUIDO' THEN 1 END) >= 5 THEN 'Frequente'  
               WHEN COUNT(CASE WHEN a.status = 'CONCLUIDO' THEN 1 END) >= 2 THEN 'Regular'
               WHEN COUNT(CASE WHEN a.status = 'CONCLUIDO' THEN 1 END) = 1 THEN 'Novo'
               ELSE 'Sem visitas'
           END AS categoria_cliente
    FROM clientes c
    LEFT JOIN agendamentos a ON a.cliente_id = c.id
    GROUP BY c.id, c.nome
)
SELECT categoria_cliente,
       COUNT(*) AS quantidade_clientes,
       ROUND(COUNT(*) * 100.0 / SUM(COUNT(*)) OVER(), 1) AS percentual,
       AVG(visitas_concluidas) AS media_visitas
FROM cliente_frequencia
GROUP BY categoria_cliente
ORDER BY 
    CASE categoria_cliente
        WHEN 'VIP' THEN 1
        WHEN 'Frequente' THEN 2
        WHEN 'Regular' THEN 3
        WHEN 'Novo' THEN 4
        ELSE 5
    END;

-- Intervalo médio entre visitas dos clientes regulares
SELECT c.nome AS cliente,
       COUNT(a.id) AS total_visitas,
       MIN(a.inicio) AS primeira_visita,
       MAX(a.inicio) AS ultima_visita,
       ROUND((MAX(a.inicio) - MIN(a.inicio)) / NULLIF(COUNT(a.id) - 1, 0), 1) AS intervalo_medio_dias
FROM clientes c
JOIN agendamentos a ON a.cliente_id = c.id AND a.status = 'CONCLUIDO'
GROUP BY c.id, c.nome
HAVING COUNT(a.id) >= 3  -- Pelo menos 3 visitas para calcular intervalo
ORDER BY intervalo_medio_dias;

-- ================
-- 6. ANÁLISES DE COMBINAÇÃO DE SERVIÇOS
-- ================

-- Combinações de serviços mais populares (2 ou mais serviços por agendamento)
SELECT servicos_combinados,
       COUNT(*) AS vezes_solicitada,
       AVG(valor_total) AS valor_medio_combo,
       MAX(valor_total) AS valor_maximo_combo
FROM (
    SELECT a.id,
           a.valor_total,
           LISTAGG(s.nome, ' + ') WITHIN GROUP (ORDER BY s.nome) AS servicos_combinados,
           COUNT(s.id) AS qtd_servicos
    FROM agendamentos a
    JOIN agendamento_servicos asv ON asv.agendamento_id = a.id
    JOIN servicos s ON s.id = asv.servico_id
    WHERE a.status = 'CONCLUIDO'
    GROUP BY a.id, a.valor_total
    HAVING COUNT(s.id) >= 2  -- Apenas combinações com 2+ serviços
)
GROUP BY servicos_combinados
ORDER BY vezes_solicitada DESC;

-- ================
-- 7. ALERTAS E MONITORAMENTO
-- ================

-- Agendamentos próximos (próximas 24 horas)
SELECT 'PRÓXIMOS AGENDAMENTOS' AS tipo_alerta,
       TO_CHAR(a.inicio, 'DD/MM HH24:MI') AS data_hora,
       c.nome AS cliente,
       c.telefone,
       b.nome AS barbeiro,
       ROUND((a.inicio - SYSTIMESTAMP) * 24, 1) AS horas_restantes,
       LISTAGG(s.nome, ', ') WITHIN GROUP (ORDER BY s.nome) AS servicos
FROM agendamentos a
JOIN clientes c ON c.id = a.cliente_id
JOIN barbeiros b ON b.id = a.barbeiro_id
LEFT JOIN agendamento_servicos asv ON asv.agendamento_id = a.id
LEFT JOIN servicos s ON s.id = asv.servico_id
WHERE a.status = 'MARCADO'
  AND a.inicio BETWEEN SYSTIMESTAMP AND SYSTIMESTAMP + INTERVAL '24' HOUR
GROUP BY a.id, a.inicio, c.nome, c.telefone, b.nome
ORDER BY a.inicio;

-- Barbeiros com agenda livre hoje
SELECT 'BARBEIROS DISPONÍVEIS' AS info,
       b.nome AS barbeiro,
       b.especialidade,
       b.telefone,
       'Disponível hoje' AS status
FROM barbeiros b
WHERE b.ativo = 'S'
  AND b.id NOT IN (
    SELECT DISTINCT a.barbeiro_id
    FROM agendamentos a
    WHERE TRUNC(a.inicio) = TRUNC(SYSDATE)
      AND a.status IN ('MARCADO', 'EM_ANDAMENTO')
  );

-- ================
-- 8. CONSULTA UNIFICADA DE DASHBOARD
-- ================

-- Dashboard executivo (métricas principais)
SELECT 'RESUMO EXECUTIVO' AS secao, 
       NULL AS metrica, 
       NULL AS valor,
       NULL AS observacao
FROM dual

UNION ALL

SELECT 'AGENDAMENTOS', 'Total hoje', 
       TO_CHAR(COUNT(*)), 
       'Agendamentos para hoje'
FROM agendamentos 
WHERE TRUNC(inicio) = TRUNC(SYSDATE)

UNION ALL

SELECT 'AGENDAMENTOS', 'Concluídos hoje',
       TO_CHAR(COUNT(*)),
       'Já finalizados'
FROM agendamentos 
WHERE TRUNC(inicio) = TRUNC(SYSDATE) AND status = 'CONCLUIDO'

UNION ALL

SELECT 'FINANCEIRO', 'Receita hoje',
       'R$ ' || TO_CHAR(SUM(valor_total), '999,999.99'),
       'Agendamentos concluídos'
FROM agendamentos 
WHERE TRUNC(inicio) = TRUNC(SYSDATE) AND status = 'CONCLUIDO'

UNION ALL

SELECT 'CLIENTES', 'Clientes ativos',
       TO_CHAR(COUNT(*)),
       'Total de clientes ativos'
FROM clientes 
WHERE ativo = 'S'

UNION ALL

SELECT 'EQUIPE', 'Barbeiros ativos',
       TO_CHAR(COUNT(*)),
       'Equipe disponível'
FROM barbeiros 
WHERE ativo = 'S'

UNION ALL

SELECT 'SERVIÇOS', 'Serviços no catálogo',
       TO_CHAR(COUNT(*)),
       'Serviços disponíveis'
FROM servicos 
WHERE ativo = 'S';

-- ================
-- MENSAGEM FINAL
-- ================

SELECT 'Consultas avançadas executadas com sucesso!' AS status FROM dual;
SELECT 'Sistema pronto para análises detalhadas e relatórios gerenciais' AS info FROM dual;