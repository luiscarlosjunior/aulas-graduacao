-- ================
-- INSERÇÃO DE DADOS - SISTEMA BARBEARIA
-- ================
-- Este script insere dados de exemplo no sistema de barbearia
-- Execute após o script 01-estrutura-completa.sql
--
-- Autor: Exemplo educacional
-- Data: 2024
-- SGBD: Oracle Database
--

-- ================
-- INSERÇÃO DE CLIENTES
-- ================

INSERT INTO clientes (nome, telefone, email) VALUES ('João Silva', '11999990001', 'joao@exemplo.com');
INSERT INTO clientes (nome, telefone, email) VALUES ('Maria Souza', '11988880002', 'maria@exemplo.com');
INSERT INTO clientes (nome, telefone, email) VALUES ('Pedro Santos', '11977770003', 'pedro@exemplo.com');
INSERT INTO clientes (nome, telefone, email) VALUES ('Ana Costa', '11966660004', 'ana@exemplo.com');
INSERT INTO clientes (nome, telefone, email) VALUES ('Carlos Oliveira', '11955550005', 'carlos@exemplo.com');
INSERT INTO clientes (nome, telefone, email) VALUES ('Fernanda Lima', '11944440006', 'fernanda@exemplo.com');
INSERT INTO clientes (nome, telefone, email) VALUES ('Roberto Ferreira', '11933330007', 'roberto@exemplo.com');
INSERT INTO clientes (nome, telefone, email) VALUES ('Julia Martins', '11922220008', 'julia@exemplo.com');
INSERT INTO clientes (nome, telefone, email) VALUES ('Marcos Alves', '11911110009', 'marcos@exemplo.com');
INSERT INTO clientes (nome, telefone, email) VALUES ('Carla Ribeiro', '11900000010', 'carla@exemplo.com');

-- ================
-- INSERÇÃO DE BARBEIROS
-- ================

INSERT INTO barbeiros (nome, telefone, especialidade) VALUES ('Pedro Barbeiro', '11888881001', 'Cortes Clássicos');
INSERT INTO barbeiros (nome, telefone, especialidade) VALUES ('Lucas Barbeiro', '11888881002', 'Barba e Bigode');
INSERT INTO barbeiros (nome, telefone, especialidade) VALUES ('André Silva', '11888881003', 'Cortes Modernos');
INSERT INTO barbeiros (nome, telefone, especialidade) VALUES ('Rafael Costa', '11888881004', 'Tratamentos Especiais');
INSERT INTO barbeiros (nome, telefone, especialidade) VALUES ('Thiago Santos', '11888881005', 'Cortes Masculinos e Femininos');

-- ================
-- INSERÇÃO DE SERVIÇOS
-- ================

INSERT INTO servicos (nome, descricao, duracao_min, preco) VALUES 
    ('Corte Masculino', 'Corte de cabelo masculino tradicional', 30, 25.00);

INSERT INTO servicos (nome, descricao, duracao_min, preco) VALUES 
    ('Barba', 'Fazer e aparar barba', 20, 15.00);

INSERT INTO servicos (nome, descricao, duracao_min, preco) VALUES 
    ('Bigode', 'Aparar e modelar bigode', 15, 10.00);

INSERT INTO servicos (nome, descricao, duracao_min, preco) VALUES 
    ('Corte + Barba', 'Pacote completo de corte e barba', 45, 35.00);

INSERT INTO servicos (nome, descricao, duracao_min, preco) VALUES 
    ('Sobrancelha', 'Aparar sobrancelha masculina', 10, 8.00);

INSERT INTO servicos (nome, descricao, duracao_min, preco) VALUES 
    ('Lavagem', 'Lavagem de cabelo', 15, 5.00);

INSERT INTO servicos (nome, descricao, duracao_min, preco) VALUES 
    ('Hidratação', 'Tratamento hidratante para cabelo', 30, 20.00);

INSERT INTO servicos (nome, descricao, duracao_min, preco) VALUES 
    ('Corte Feminino', 'Corte de cabelo feminino', 45, 40.00);

INSERT INTO servicos (nome, descricao, duracao_min, preco) VALUES 
    ('Escova', 'Escova modeladora', 30, 18.00);

INSERT INTO servicos (nome, descricao, duracao_min, preco) VALUES 
    ('Reflexo', 'Aplicação de reflexo no cabelo', 60, 50.00);

-- ================
-- INSERÇÃO DE AGENDAMENTOS
-- ================

-- Agendamentos para hoje e próximos dias
INSERT INTO agendamentos (cliente_id, barbeiro_id, inicio, status, observacoes, valor_total)
VALUES (1, 1, SYSTIMESTAMP + INTERVAL '1' HOUR, 'MARCADO', 'Cliente prefere corte baixo', 25.00);

INSERT INTO agendamentos (cliente_id, barbeiro_id, inicio, status, observacoes, valor_total)
VALUES (2, 2, SYSTIMESTAMP + INTERVAL '2' HOUR, 'MARCADO', 'Primeira vez na barbearia', 35.00);

INSERT INTO agendamentos (cliente_id, barbeiro_id, inicio, status, observacoes, valor_total)
VALUES (3, 1, SYSTIMESTAMP + INTERVAL '1' DAY, 'MARCADO', 'Cliente regular', 25.00);

INSERT INTO agendamentos (cliente_id, barbeiro_id, inicio, status, observacoes, valor_total)
VALUES (4, 3, SYSTIMESTAMP + INTERVAL '1' DAY + INTERVAL '2' HOUR, 'MARCADO', 'Corte moderno', 40.00);

INSERT INTO agendamentos (cliente_id, barbeiro_id, inicio, status, observacoes, valor_total)
VALUES (5, 2, SYSTIMESTAMP + INTERVAL '2' DAY, 'MARCADO', 'Só barba hoje', 15.00);

-- Agendamentos já concluídos (histórico)
INSERT INTO agendamentos (cliente_id, barbeiro_id, inicio, fim, status, observacoes, valor_total)
VALUES (6, 1, SYSTIMESTAMP - INTERVAL '1' DAY, SYSTIMESTAMP - INTERVAL '23' HOUR, 'CONCLUIDO', 'Serviço realizado', 25.00);

INSERT INTO agendamentos (cliente_id, barbeiro_id, inicio, fim, status, observacoes, valor_total)
VALUES (7, 2, SYSTIMESTAMP - INTERVAL '2' DAY, SYSTIMESTAMP - INTERVAL '1' DAY - INTERVAL '23' HOUR, 'CONCLUIDO', 'Cliente satisfeito', 35.00);

INSERT INTO agendamentos (cliente_id, barbeiro_id, inicio, status, observacoes, valor_total)
VALUES (8, 4, SYSTIMESTAMP + INTERVAL '3' DAY, 'MARCADO', 'Tratamento especial', 70.00);

INSERT INTO agendamentos (cliente_id, barbeiro_id, inicio, status, observacoes, valor_total)
VALUES (9, 5, SYSTIMESTAMP + INTERVAL '4' DAY, 'MARCADO', 'Corte feminino', 40.00);

INSERT INTO agendamentos (cliente_id, barbeiro_id, inicio, status, observacoes, valor_total)
VALUES (10, 3, SYSTIMESTAMP + INTERVAL '5' DAY, 'MARCADO', 'Corte + escova', 58.00);

-- Um agendamento cancelado
INSERT INTO agendamentos (cliente_id, barbeiro_id, inicio, status, observacoes, valor_total)
VALUES (1, 2, SYSTIMESTAMP + INTERVAL '6' DAY, 'CANCELADO', 'Cliente cancelou por motivo pessoal', 0.00);

-- ================
-- INSERÇÃO DE SERVIÇOS POR AGENDAMENTO (Relacionamento N:M)
-- ================

-- Agendamento 1: João Silva - Apenas corte masculino
INSERT INTO agendamento_servicos (agendamento_id, servico_id, quantidade, preco_unitario) 
VALUES (1, 1, 1, 25.00);

-- Agendamento 2: Maria Souza - Corte + Barba (usando o pacote)
INSERT INTO agendamento_servicos (agendamento_id, servico_id, quantidade, preco_unitario) 
VALUES (2, 4, 1, 35.00);

-- Agendamento 3: Pedro Santos - Apenas corte masculino
INSERT INTO agendamento_servicos (agendamento_id, servico_id, quantidade, preco_unitario) 
VALUES (3, 1, 1, 25.00);

-- Agendamento 4: Ana Costa - Corte feminino
INSERT INTO agendamento_servicos (agendamento_id, servico_id, quantidade, preco_unitario) 
VALUES (4, 8, 1, 40.00);

-- Agendamento 5: Carlos Oliveira - Apenas barba
INSERT INTO agendamento_servicos (agendamento_id, servico_id, quantidade, preco_unitario) 
VALUES (5, 2, 1, 15.00);

-- Agendamento 6: Fernanda Lima - Corte masculino (já concluído)
INSERT INTO agendamento_servicos (agendamento_id, servico_id, quantidade, preco_unitario) 
VALUES (6, 1, 1, 25.00);

-- Agendamento 7: Roberto Ferreira - Corte + Barba (já concluído)
INSERT INTO agendamento_servicos (agendamento_id, servico_id, quantidade, preco_unitario) 
VALUES (7, 4, 1, 35.00);

-- Agendamento 8: Julia Martins - Múltiplos serviços
INSERT INTO agendamento_servicos (agendamento_id, servico_id, quantidade, preco_unitario) 
VALUES (8, 7, 1, 20.00); -- Hidratação
INSERT INTO agendamento_servicos (agendamento_id, servico_id, quantidade, preco_unitario) 
VALUES (8, 10, 1, 50.00); -- Reflexo

-- Agendamento 9: Marcos Alves - Corte feminino
INSERT INTO agendamento_servicos (agendamento_id, servico_id, quantidade, preco_unitario) 
VALUES (9, 8, 1, 40.00);

-- Agendamento 10: Carla Ribeiro - Corte + Escova
INSERT INTO agendamento_servicos (agendamento_id, servico_id, quantidade, preco_unitario) 
VALUES (10, 8, 1, 40.00); -- Corte feminino
INSERT INTO agendamento_servicos (agendamento_id, servico_id, quantidade, preco_unitario) 
VALUES (10, 9, 1, 18.00); -- Escova

-- ================
-- COMMITS DAS TRANSAÇÕES
-- ================

COMMIT;

-- ================
-- VERIFICAÇÕES E ESTATÍSTICAS
-- ================

-- Contar registros inseridos
SELECT 'CLIENTES' AS tabela, COUNT(*) AS total FROM clientes
UNION ALL
SELECT 'BARBEIROS' AS tabela, COUNT(*) AS total FROM barbeiros
UNION ALL
SELECT 'SERVIÇOS' AS tabela, COUNT(*) AS total FROM servicos
UNION ALL
SELECT 'AGENDAMENTOS' AS tabela, COUNT(*) AS total FROM agendamentos
UNION ALL
SELECT 'AGEND_SERVICOS' AS tabela, COUNT(*) AS total FROM agendamento_servicos
ORDER BY tabela;

-- Exibir mensagem de sucesso
SELECT 'Dados inseridos com sucesso no sistema BARBEARIA!' AS status FROM dual;
SELECT 'Execute agora o script 03-consultas-basicas.sql para testar as consultas' AS proximo_passo FROM dual;