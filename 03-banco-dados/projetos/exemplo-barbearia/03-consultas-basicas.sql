-- ================
-- CONSULTAS BÁSICAS - SISTEMA BARBEARIA
-- ================
-- Este script contém exemplos de consultas básicas no sistema de barbearia
-- Execute após os scripts 01-estrutura-completa.sql e 02-inserir-dados.sql
--
-- Autor: Exemplo educacional
-- Data: 2024
-- SGBD: Oracle Database
--

-- ================
-- 1. CONSULTAS SIMPLES
-- ================

-- Listar todos os clientes
SELECT id, nome, telefone, email, data_cadastro, ativo
FROM clientes
ORDER BY nome;

-- Listar todos os barbeiros
SELECT id, nome, telefone, especialidade, data_admissao, ativo
FROM barbeiros
ORDER BY nome;

-- Listar todos os serviços com preços
SELECT id, nome, descricao, duracao_min, preco, ativo
FROM servicos
ORDER BY preco;

-- ================
-- 2. AGENDA COM CLIENTE E BARBEIRO (Exemplo do issue)
-- ================

SELECT a.id, 
       TO_CHAR(a.inicio, 'DD/MM/YYYY HH24:MI') AS data_hora,
       a.status,
       c.nome AS cliente, 
       b.nome AS barbeiro,
       a.valor_total
FROM agendamentos a
JOIN clientes  c ON c.id = a.cliente_id
JOIN barbeiros b ON b.id = a.barbeiro_id
ORDER BY a.inicio;

-- ================
-- 3. SERVIÇOS DE CADA AGENDAMENTO (Exemplo do issue)
-- ================

SELECT a.id AS agendamento_id,
       c.nome AS cliente,
       LISTAGG(s.nome, ', ') WITHIN GROUP (ORDER BY s.nome) AS servicos,
       SUM(asv.preco_unitario * asv.quantidade) AS valor_total
FROM agendamentos a
JOIN clientes c ON c.id = a.cliente_id
JOIN agendamento_servicos asv ON asv.agendamento_id = a.id
JOIN servicos s ON s.id = asv.servico_id
GROUP BY a.id, c.nome
ORDER BY a.id;

-- ================
-- 4. CONSULTAS POR STATUS
-- ================

-- Agendamentos marcados (futuros)
SELECT a.id,
       c.nome AS cliente,
       b.nome AS barbeiro,
       TO_CHAR(a.inicio, 'DD/MM/YYYY HH24:MI') AS data_hora,
       a.valor_total
FROM agendamentos a
JOIN clientes c ON c.id = a.cliente_id
JOIN barbeiros b ON b.id = a.barbeiro_id
WHERE a.status = 'MARCADO'
ORDER BY a.inicio;

-- Agendamentos concluídos
SELECT a.id,
       c.nome AS cliente,
       b.nome AS barbeiro,
       TO_CHAR(a.inicio, 'DD/MM/YYYY HH24:MI') AS inicio,
       TO_CHAR(a.fim, 'DD/MM/YYYY HH24:MI') AS fim,
       a.valor_total
FROM agendamentos a
JOIN clientes c ON c.id = a.cliente_id
JOIN barbeiros b ON b.id = a.barbeiro_id
WHERE a.status = 'CONCLUIDO'
ORDER BY a.inicio DESC;

-- ================
-- 5. CONSULTAS POR PERÍODO
-- ================

-- Agendamentos de hoje
SELECT a.id,
       c.nome AS cliente,
       b.nome AS barbeiro,
       TO_CHAR(a.inicio, 'HH24:MI') AS horario,
       a.status
FROM agendamentos a
JOIN clientes c ON c.id = a.cliente_id
JOIN barbeiros b ON b.id = a.barbeiro_id
WHERE TRUNC(a.inicio) = TRUNC(SYSDATE)
ORDER BY a.inicio;

-- Agendamentos da semana atual
SELECT a.id,
       c.nome AS cliente,
       b.nome AS barbeiro,
       TO_CHAR(a.inicio, 'DD/MM/YYYY HH24:MI') AS data_hora,
       a.status,
       a.valor_total
FROM agendamentos a
JOIN clientes c ON c.id = a.cliente_id
JOIN barbeiros b ON b.id = a.barbeiro_id
WHERE a.inicio >= TRUNC(SYSDATE, 'IW') -- Início da semana
  AND a.inicio < TRUNC(SYSDATE, 'IW') + 7 -- Fim da semana
ORDER BY a.inicio;

-- ================
-- 6. CONSULTAS POR BARBEIRO
-- ================

-- Agenda do Pedro Barbeiro (id = 1)
SELECT a.id,
       c.nome AS cliente,
       TO_CHAR(a.inicio, 'DD/MM/YYYY HH24:MI') AS data_hora,
       a.status,
       LISTAGG(s.nome, ', ') WITHIN GROUP (ORDER BY s.nome) AS servicos
FROM agendamentos a
JOIN clientes c ON c.id = a.cliente_id
LEFT JOIN agendamento_servicos asv ON asv.agendamento_id = a.id
LEFT JOIN servicos s ON s.id = asv.servico_id
WHERE a.barbeiro_id = 1
GROUP BY a.id, c.nome, a.inicio, a.status
ORDER BY a.inicio;

-- Agenda resumida de todos os barbeiros
SELECT b.nome AS barbeiro,
       COUNT(CASE WHEN a.status = 'MARCADO' THEN 1 END) AS agendamentos_marcados,
       COUNT(CASE WHEN a.status = 'CONCLUIDO' THEN 1 END) AS agendamentos_concluidos,
       COUNT(CASE WHEN a.status = 'CANCELADO' THEN 1 END) AS agendamentos_cancelados,
       SUM(CASE WHEN a.status = 'CONCLUIDO' THEN a.valor_total ELSE 0 END) AS receita_concluida
FROM barbeiros b
LEFT JOIN agendamentos a ON a.barbeiro_id = b.id
GROUP BY b.id, b.nome
ORDER BY b.nome;

-- ================
-- 7. CONSULTAS POR CLIENTE
-- ================

-- Histórico de um cliente específico (João Silva - id = 1)
SELECT a.id,
       b.nome AS barbeiro,
       TO_CHAR(a.inicio, 'DD/MM/YYYY HH24:MI') AS data_hora,
       a.status,
       LISTAGG(s.nome, ', ') WITHIN GROUP (ORDER BY s.nome) AS servicos,
       a.valor_total
FROM agendamentos a
JOIN barbeiros b ON b.id = a.barbeiro_id
LEFT JOIN agendamento_servicos asv ON asv.agendamento_id = a.id
LEFT JOIN servicos s ON s.id = asv.servico_id
WHERE a.cliente_id = 1
GROUP BY a.id, b.nome, a.inicio, a.status, a.valor_total
ORDER BY a.inicio DESC;

-- Resumo de todos os clientes
SELECT c.nome AS cliente,
       c.email,
       COUNT(a.id) AS total_agendamentos,
       COUNT(CASE WHEN a.status = 'CONCLUIDO' THEN 1 END) AS servicos_concluidos,
       SUM(CASE WHEN a.status = 'CONCLUIDO' THEN a.valor_total ELSE 0 END) AS total_gasto,
       MAX(a.inicio) AS ultimo_agendamento
FROM clientes c
LEFT JOIN agendamentos a ON a.cliente_id = c.id
GROUP BY c.id, c.nome, c.email
ORDER BY total_gasto DESC NULLS LAST;

-- ================
-- 8. CONSULTAS DE SERVIÇOS
-- ================

-- Serviços mais solicitados
SELECT s.nome AS servico,
       s.preco AS preco_tabela,
       COUNT(asv.servico_id) AS vezes_solicitado,
       SUM(asv.quantidade) AS quantidade_total,
       AVG(asv.preco_unitario) AS preco_medio_praticado
FROM servicos s
LEFT JOIN agendamento_servicos asv ON asv.servico_id = s.id
GROUP BY s.id, s.nome, s.preco
ORDER BY vezes_solicitado DESC NULLS LAST;

-- Duração média dos serviços por barbeiro
SELECT b.nome AS barbeiro,
       AVG(s.duracao_min) AS duracao_media_servicos,
       COUNT(DISTINCT asv.servico_id) AS tipos_servicos_diferentes
FROM barbeiros b
JOIN agendamentos a ON a.barbeiro_id = b.id
JOIN agendamento_servicos asv ON asv.agendamento_id = a.id
JOIN servicos s ON s.id = asv.servico_id
GROUP BY b.id, b.nome
ORDER BY duracao_media_servicos;

-- ================
-- 9. CONSULTAS FINANCEIRAS
-- ================

-- Receita por mês (agendamentos concluídos)
SELECT TO_CHAR(a.inicio, 'MM/YYYY') AS mes_ano,
       COUNT(a.id) AS agendamentos_concluidos,
       SUM(a.valor_total) AS receita_total,
       AVG(a.valor_total) AS ticket_medio
FROM agendamentos a
WHERE a.status = 'CONCLUIDO'
GROUP BY TO_CHAR(a.inicio, 'MM/YYYY')
ORDER BY mes_ano;

-- Receita por barbeiro (concluídos)
SELECT b.nome AS barbeiro,
       COUNT(a.id) AS atendimentos_concluidos,
       SUM(a.valor_total) AS receita_gerada,
       AVG(a.valor_total) AS ticket_medio
FROM barbeiros b
JOIN agendamentos a ON a.barbeiro_id = b.id
WHERE a.status = 'CONCLUIDO'
GROUP BY b.id, b.nome
ORDER BY receita_gerada DESC NULLS LAST;

-- ================
-- 10. CONSULTAS DE DISPONIBILIDADE
-- ================

-- Barbeiros livres em determinado horário (exemplo: próximas 2 horas)
SELECT b.id,
       b.nome AS barbeiro,
       b.especialidade,
       'DISPONÍVEL' AS status
FROM barbeiros b
WHERE b.ativo = 'S'
  AND b.id NOT IN (
    SELECT DISTINCT a.barbeiro_id
    FROM agendamentos a
    WHERE a.status IN ('MARCADO', 'EM_ANDAMENTO')
      AND a.inicio BETWEEN SYSTIMESTAMP AND SYSTIMESTAMP + INTERVAL '2' HOUR
  );

-- Horários ocupados de um barbeiro específico (Pedro Barbeiro - id = 1)
SELECT TO_CHAR(a.inicio, 'DD/MM/YYYY HH24:MI') AS inicio,
       TO_CHAR(a.inicio + INTERVAL '30' MINUTE, 'HH24:MI') AS fim_estimado, -- Assumindo 30min médio
       c.nome AS cliente,
       a.status
FROM agendamentos a
JOIN clientes c ON c.id = a.cliente_id
WHERE a.barbeiro_id = 1
  AND a.status IN ('MARCADO', 'EM_ANDAMENTO')
  AND a.inicio >= SYSDATE
ORDER BY a.inicio;

-- ================
-- MENSAGEM FINAL
-- ================

SELECT 'Consultas básicas executadas com sucesso!' AS status FROM dual;
SELECT 'Próximo: Execute 04-consultas-avancadas.sql para consultas mais complexas' AS proximo_passo FROM dual;