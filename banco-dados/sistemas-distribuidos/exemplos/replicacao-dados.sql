-- ============================================================================
-- EXEMPLO: Replicação de Dados em Sistema Distribuído
-- ============================================================================
-- Cenário: Sistema de E-commerce com replicação Master-Slave
-- Objetivo: Alta disponibilidade e distribuição de carga de leitura
-- ============================================================================

-- ----------------------------------------------------------------------------
-- PARTE 1: Configuração da Arquitetura
-- ----------------------------------------------------------------------------

/*
ARQUITETURA:

                [MASTER]
                (Servidor 1)
            Escritas: INSERT, UPDATE, DELETE
                    |
        +-----------+-----------+
        |           |           |
        v           v           v
    [SLAVE 1]   [SLAVE 2]   [SLAVE 3]
    (Server 2)  (Server 3)  (Server 4)
    Leituras    Leituras    Leituras
    
FLUXO:
1. Aplicação escreve no MASTER
2. MASTER replica para SLAVES (assíncrono ou síncrono)
3. Aplicação lê de qualquer SLAVE (distribui carga)
*/

-- ----------------------------------------------------------------------------
-- PARTE 2: Tabelas no Master (Servidor de Escrita)
-- ----------------------------------------------------------------------------

-- Tabela de produtos (MASTER)
CREATE TABLE produtos (
    produto_id NUMBER PRIMARY KEY,
    nome VARCHAR2(200) NOT NULL,
    descricao CLOB,
    preco NUMBER(10,2) NOT NULL,
    estoque NUMBER DEFAULT 0,
    categoria VARCHAR2(50),
    data_cadastro TIMESTAMP DEFAULT SYSTIMESTAMP,
    ultima_atualizacao TIMESTAMP DEFAULT SYSTIMESTAMP
);

-- Tabela de pedidos (MASTER)
CREATE TABLE pedidos (
    pedido_id NUMBER PRIMARY KEY,
    cliente_id NUMBER NOT NULL,
    data_pedido TIMESTAMP DEFAULT SYSTIMESTAMP,
    status VARCHAR2(20) DEFAULT 'PENDENTE',
    valor_total NUMBER(10,2),
    observacoes VARCHAR2(500)
);

-- Tabela de itens do pedido (MASTER)
CREATE TABLE itens_pedido (
    item_id NUMBER PRIMARY KEY,
    pedido_id NUMBER NOT NULL,
    produto_id NUMBER NOT NULL,
    quantidade NUMBER NOT NULL,
    preco_unitario NUMBER(10,2) NOT NULL,
    CONSTRAINT fk_pedido FOREIGN KEY (pedido_id) REFERENCES pedidos(pedido_id),
    CONSTRAINT fk_produto FOREIGN KEY (produto_id) REFERENCES produtos(produto_id)
);

-- Sequências para IDs (MASTER)
CREATE SEQUENCE seq_produto START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE seq_pedido START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE seq_item START WITH 1 INCREMENT BY 1;

-- ----------------------------------------------------------------------------
-- PARTE 3: Trigger para Rastrear Alterações (Replicação)
-- ----------------------------------------------------------------------------

-- Log de replicação (controle de mudanças)
CREATE TABLE replication_log (
    log_id NUMBER PRIMARY KEY,
    tabela VARCHAR2(50),
    operacao VARCHAR2(10), -- INSERT, UPDATE, DELETE
    registro_id NUMBER,
    timestamp_operacao TIMESTAMP DEFAULT SYSTIMESTAMP,
    replicado VARCHAR2(1) DEFAULT 'N'
);

CREATE SEQUENCE seq_replication_log START WITH 1;

-- Trigger para produtos (registra mudanças)
CREATE OR REPLACE TRIGGER trg_produto_replication
AFTER INSERT OR UPDATE OR DELETE ON produtos
FOR EACH ROW
DECLARE
    v_operacao VARCHAR2(10);
    v_produto_id NUMBER;
BEGIN
    IF INSERTING THEN
        v_operacao := 'INSERT';
        v_produto_id := :NEW.produto_id;
    ELSIF UPDATING THEN
        v_operacao := 'UPDATE';
        v_produto_id := :NEW.produto_id;
    ELSIF DELETING THEN
        v_operacao := 'DELETE';
        v_produto_id := :OLD.produto_id;
    END IF;
    
    INSERT INTO replication_log (log_id, tabela, operacao, registro_id)
    VALUES (seq_replication_log.NEXTVAL, 'PRODUTOS', v_operacao, v_produto_id);
END;
/

-- ----------------------------------------------------------------------------
-- PARTE 4: Simulação de Operações de Escrita (Master)
-- ----------------------------------------------------------------------------

-- Inserir produtos (só no MASTER)
INSERT INTO produtos (produto_id, nome, descricao, preco, estoque, categoria)
VALUES (seq_produto.NEXTVAL, 'Notebook Dell Inspiron', 'Core i5, 8GB RAM, 256GB SSD', 3500.00, 50, 'Eletrônicos');

INSERT INTO produtos (produto_id, nome, descricao, preco, estoque, categoria)
VALUES (seq_produto.NEXTVAL, 'Mouse Logitech MX Master', 'Mouse sem fio ergonômico', 350.00, 200, 'Periféricos');

INSERT INTO produtos (produto_id, nome, descricao, preco, estoque, categoria)
VALUES (seq_produto.NEXTVAL, 'Teclado Mecânico Keychron', 'Switch Blue, RGB', 450.00, 80, 'Periféricos');

INSERT INTO produtos (produto_id, nome, descricao, preco, estoque, categoria)
VALUES (seq_produto.NEXTVAL, 'Monitor LG UltraWide 29"', 'IPS, 2560x1080, 75Hz', 1200.00, 30, 'Monitores');

COMMIT;

-- Atualizar estoque (escrita no MASTER)
UPDATE produtos 
SET estoque = estoque - 1,
    ultima_atualizacao = SYSTIMESTAMP
WHERE produto_id = 1;

COMMIT;

-- Verificar log de replicação
SELECT * FROM replication_log ORDER BY timestamp_operacao DESC;

-- ----------------------------------------------------------------------------
-- PARTE 5: Replicação Síncrona vs Assíncrona
-- ----------------------------------------------------------------------------

/*
REPLICAÇÃO SÍNCRONA:
--------------------
Master aguarda confirmação de pelo menos N slaves antes de confirmar ao cliente

VANTAGENS:
+ Garante que dados estão em múltiplos lugares
+ Zero perda de dados se master cair
+ Leituras sempre consistentes

DESVANTAGENS:
- Maior latência (espera rede)
- Se slave estiver lento, escrita fica lenta
- Menor disponibilidade (se slaves caírem, escritas falham)

QUANDO USAR:
- Sistemas financeiros
- Transações críticas
- Dados que não podem ser perdidos
*/

-- Simulação de replicação síncrona (pseudo-código)
CREATE OR REPLACE PROCEDURE inserir_produto_sync(
    p_nome VARCHAR2,
    p_preco NUMBER,
    p_estoque NUMBER
)
IS
    v_produto_id NUMBER;
    v_replicado_slave1 BOOLEAN := FALSE;
    v_replicado_slave2 BOOLEAN := FALSE;
BEGIN
    -- 1. Inserir no MASTER
    INSERT INTO produtos (produto_id, nome, preco, estoque)
    VALUES (seq_produto.NEXTVAL, p_nome, p_preco, p_estoque)
    RETURNING produto_id INTO v_produto_id;
    
    -- 2. Aguardar replicação para SLAVES (simulado)
    -- Na prática, isso seria feito pelo SGBD ou middleware
    
    -- Tenta replicar para SLAVE 1
    BEGIN
        -- replicar_para_slave1(v_produto_id); -- Chamada remota
        v_replicado_slave1 := TRUE;
    EXCEPTION
        WHEN OTHERS THEN
            v_replicado_slave1 := FALSE;
    END;
    
    -- Tenta replicar para SLAVE 2
    BEGIN
        -- replicar_para_slave2(v_produto_id); -- Chamada remota
        v_replicado_slave2 := TRUE;
    EXCEPTION
        WHEN OTHERS THEN
            v_replicado_slave2 := FALSE;
    END;
    
    -- 3. Verifica se pelo menos 1 slave confirmou
    IF NOT v_replicado_slave1 AND NOT v_replicado_slave2 THEN
        ROLLBACK;
        RAISE_APPLICATION_ERROR(-20001, 'Falha na replicação síncrona');
    END IF;
    
    -- 4. Confirma transação
    COMMIT;
    DBMS_OUTPUT.PUT_LINE('Produto ' || v_produto_id || ' inserido e replicado');
END;
/

/*
REPLICAÇÃO ASSÍNCRONA:
---------------------
Master confirma ao cliente imediatamente, replica depois

VANTAGENS:
+ Baixa latência
+ Alta disponibilidade (slaves podem estar offline)
+ Não bloqueia escritas

DESVANTAGENS:
- Possível perda de dados (se master cair antes de replicar)
- Leituras podem ser desatualizadas (lag de replicação)
- Inconsistência temporária

QUANDO USAR:
- Redes sociais
- E-commerce (não crítico)
- Sistemas onde disponibilidade > consistência
*/

-- Simulação de replicação assíncrona
CREATE OR REPLACE PROCEDURE inserir_produto_async(
    p_nome VARCHAR2,
    p_preco NUMBER,
    p_estoque NUMBER
)
IS
    v_produto_id NUMBER;
BEGIN
    -- 1. Inserir no MASTER
    INSERT INTO produtos (produto_id, nome, preco, estoque)
    VALUES (seq_produto.NEXTVAL, p_nome, p_preco, p_estoque)
    RETURNING produto_id INTO v_produto_id;
    
    -- 2. COMMIT imediato (não espera replicação)
    COMMIT;
    
    -- 3. Agenda replicação assíncrona (job em background)
    -- Em produção, seria feito por processo separado lendo replication_log
    
    DBMS_OUTPUT.PUT_LINE('Produto ' || v_produto_id || ' inserido. Replicação em andamento...');
END;
/

-- ----------------------------------------------------------------------------
-- PARTE 6: Problema do Lag de Replicação
-- ----------------------------------------------------------------------------

/*
CENÁRIO DO DIA A DIA:

T0: Cliente faz pedido (escreve no MASTER)
    └─> INSERT INTO pedidos... 
    └─> COMMIT → Cliente recebe "Pedido realizado!"

T1: Cliente clica em "Meus Pedidos" (lê de SLAVE 1)
    └─> SELECT * FROM pedidos WHERE cliente_id = 123
    └─> SLAVE 1 ainda não recebeu replicação
    └─> Cliente NÃO VÊ seu pedido! 😱

T2: 5 segundos depois, replicação completa
    └─> Agora SLAVE 1 tem o pedido

T3: Cliente recarrega página
    └─> Agora VÊ o pedido ✓

PROBLEMA: Read-after-write inconsistency
*/

-- SOLUÇÃO 1: Read-Your-Writes Consistency
-- Após escrever, lê do MASTER por X segundos

-- NOTA: Database links (@master_link, @slave_link) precisam ser criados previamente
-- Exemplo de criação de database link (feito por DBA):
-- CREATE DATABASE LINK master_link CONNECT TO usuario IDENTIFIED BY senha USING 'TNS_MASTER';
-- CREATE DATABASE LINK slave_link CONNECT TO usuario IDENTIFIED BY senha USING 'TNS_SLAVE';

CREATE OR REPLACE FUNCTION buscar_pedidos_cliente(
    p_cliente_id NUMBER,
    p_usuario_escreveu_recentemente BOOLEAN
)
RETURN SYS_REFCURSOR
IS
    v_cursor SYS_REFCURSOR;
BEGIN
    IF p_usuario_escreveu_recentemente THEN
        -- Lê do MASTER (garante ver suas próprias escritas)
        -- Em produção: SELECT * FROM pedidos@master_link
        -- Para teste local: SELECT * FROM pedidos
        OPEN v_cursor FOR
        SELECT * FROM pedidos  -- Simulação: em produção seria @master_link
        WHERE cliente_id = p_cliente_id
        ORDER BY data_pedido DESC;
    ELSE
        -- Lê de SLAVE (distribui carga)
        -- Em produção: SELECT * FROM pedidos@slave_link
        -- Para teste local: SELECT * FROM pedidos
        OPEN v_cursor FOR
        SELECT * FROM pedidos  -- Simulação: em produção seria @slave_link
        WHERE cliente_id = p_cliente_id
        ORDER BY data_pedido DESC;
    END IF;
    
    RETURN v_cursor;
END;
/

-- SOLUÇÃO 2: Session Consistency
-- Marca timestamp da última escrita, slave só responde se atualizado

CREATE TABLE session_metadata (
    session_id VARCHAR2(50) PRIMARY KEY,
    last_write_timestamp TIMESTAMP,
    preferred_replica VARCHAR2(20)
);

-- Procedure que verifica se slave está atualizado
CREATE OR REPLACE FUNCTION slave_esta_atualizado(
    p_slave_id VARCHAR2,
    p_timestamp_requerido TIMESTAMP
)
RETURN BOOLEAN
IS
    v_ultimo_timestamp TIMESTAMP;
BEGIN
    -- Consulta qual o último timestamp replicado no slave
    -- (Em produção, seria via query remota ou API)
    -- Nota: Para usar este exemplo, seria necessário ter uma coluna slave_id na tabela
    -- ou manter uma tabela separada de status de replicação por slave
    
    -- Simulação simplificada: assume que todas as operações foram replicadas
    SELECT MAX(timestamp_operacao) INTO v_ultimo_timestamp
    FROM replication_log
    WHERE replicado = 'S';
    
    RETURN v_ultimo_timestamp >= p_timestamp_requerido;
END;
/

-- ----------------------------------------------------------------------------
-- PARTE 7: Exemplo Prático - E-commerce Black Friday
-- ----------------------------------------------------------------------------

/*
CENÁRIO: BLACK FRIDAY COM 100.000 REQUISIÇÕES/SEGUNDO

ARQUITETURA:
- 1 Master (escritas)
- 10 Slaves (leituras)

DISTRIBUIÇÃO DE CARGA:
- 10% escritas → Master (10.000 req/s)
- 90% leituras → Slaves (90.000 req/s → 9.000 req/s por slave)

SEM REPLICAÇÃO:
- 1 servidor precisa aguentar 100.000 req/s → COLAPSA! ❌

COM REPLICAÇÃO:
- Master: 10.000 req/s → OK ✓
- Cada slave: 9.000 req/s → OK ✓
- SISTEMA AGUENTA! ✓
*/

-- View que simula balanceamento de carga entre slaves
CREATE OR REPLACE FUNCTION selecionar_slave_para_leitura
RETURN VARCHAR2
IS
    v_slaves DBMS_SQL.VARCHAR2_TABLE;
    v_indice NUMBER;
BEGIN
    -- Lista de slaves disponíveis
    v_slaves(1) := 'slave1.ecommerce.com';
    v_slaves(2) := 'slave2.ecommerce.com';
    v_slaves(3) := 'slave3.ecommerce.com';
    v_slaves(4) := 'slave4.ecommerce.com';
    v_slaves(5) := 'slave5.ecommerce.com';
    
    -- Seleciona slave aleatoriamente (round-robin em produção)
    v_indice := DBMS_RANDOM.VALUE(1, 5);
    
    RETURN v_slaves(v_indice);
END;
/

-- Procedure de busca de produtos (distribui entre slaves)
CREATE OR REPLACE PROCEDURE buscar_produtos_black_friday(
    p_categoria VARCHAR2,
    p_cursor OUT SYS_REFCURSOR
)
IS
    v_slave VARCHAR2(100);
BEGIN
    -- Seleciona slave com menor carga
    v_slave := selecionar_slave_para_leitura();
    
    DBMS_OUTPUT.PUT_LINE('Consultando slave: ' || v_slave);
    
    -- Em produção, seria via database link:
    -- OPEN p_cursor FOR SELECT * FROM produtos@v_slave WHERE...
    
    -- Simulação local:
    OPEN p_cursor FOR
    SELECT produto_id, nome, preco, estoque
    FROM produtos
    WHERE categoria = p_categoria
      AND estoque > 0
    ORDER BY preco;
END;
/

-- ----------------------------------------------------------------------------
-- PARTE 8: Failover Automático (Promoção de Slave a Master)
-- ----------------------------------------------------------------------------

/*
CENÁRIO: MASTER CAI DURANTE BLACK FRIDAY

Sem Failover:
- Master cai às 10:00
- Sistema fica fora por 2 horas (recuperação manual)
- Prejuízo: milhões de reais

Com Failover Automático:
- Master cai às 10:00:00
- Sistema detecta falha às 10:00:05 (heartbeat timeout)
- Slave mais atualizado é promovido às 10:00:10
- Sistema volta às 10:00:15 (downtime: 15 segundos)
*/

-- Tabela de status dos servidores
CREATE TABLE cluster_status (
    servidor_id VARCHAR2(50) PRIMARY KEY,
    papel VARCHAR2(10), -- MASTER, SLAVE
    status VARCHAR2(20), -- ONLINE, OFFLINE, PROMOVENDO
    ultimo_heartbeat TIMESTAMP,
    lag_replicacao_segundos NUMBER
);

-- Procedure de failover
CREATE OR REPLACE PROCEDURE promover_slave_a_master(
    p_slave_id VARCHAR2
)
IS
    v_master_atual VARCHAR2(50);
BEGIN
    -- 1. Identifica master atual
    SELECT servidor_id INTO v_master_atual
    FROM cluster_status
    WHERE papel = 'MASTER';
    
    DBMS_OUTPUT.PUT_LINE('Master atual: ' || v_master_atual || ' está offline');
    
    -- 2. Atualiza status do master antigo
    UPDATE cluster_status
    SET papel = 'SLAVE',
        status = 'OFFLINE'
    WHERE servidor_id = v_master_atual;
    
    -- 3. Promove slave escolhido
    UPDATE cluster_status
    SET papel = 'MASTER',
        status = 'ONLINE'
    WHERE servidor_id = p_slave_id;
    
    -- 4. Reconfigura outros slaves para replicar do novo master
    -- (Em produção, seria feito por orquestrador como Patroni, MHA, etc.)
    
    COMMIT;
    
    DBMS_OUTPUT.PUT_LINE('Slave ' || p_slave_id || ' promovido a MASTER!');
    DBMS_OUTPUT.PUT_LINE('Sistema restaurado!');
END;
/

-- Procedure que escolhe melhor slave para promover
CREATE OR REPLACE FUNCTION escolher_melhor_slave_para_promover
RETURN VARCHAR2
IS
    v_melhor_slave VARCHAR2(50);
BEGIN
    -- Escolhe slave com menor lag de replicação
    SELECT servidor_id INTO v_melhor_slave
    FROM cluster_status
    WHERE papel = 'SLAVE'
      AND status = 'ONLINE'
    ORDER BY lag_replicacao_segundos ASC
    FETCH FIRST 1 ROW ONLY;
    
    RETURN v_melhor_slave;
END;
/

-- Simulação de failover automático
BEGIN
    -- Insere status dos servidores
    INSERT INTO cluster_status VALUES ('master01', 'MASTER', 'ONLINE', SYSTIMESTAMP, 0);
    INSERT INTO cluster_status VALUES ('slave01', 'SLAVE', 'ONLINE', SYSTIMESTAMP, 2);
    INSERT INTO cluster_status VALUES ('slave02', 'SLAVE', 'ONLINE', SYSTIMESTAMP, 5);
    INSERT INTO cluster_status VALUES ('slave03', 'SLAVE', 'ONLINE', SYSTIMESTAMP, 1);
    COMMIT;
    
    -- Master cai
    UPDATE cluster_status SET status = 'OFFLINE' WHERE servidor_id = 'master01';
    COMMIT;
    
    -- Sistema detecta e promove melhor slave
    DECLARE
        v_novo_master VARCHAR2(50);
    BEGIN
        v_novo_master := escolher_melhor_slave_para_promover();
        DBMS_OUTPUT.PUT_LINE('Slave com menor lag: ' || v_novo_master);
        promover_slave_a_master(v_novo_master);
    END;
END;
/

-- ----------------------------------------------------------------------------
-- PARTE 9: Exercícios Práticos
-- ----------------------------------------------------------------------------

/*
EXERCÍCIO 1:
Implemente uma procedure que:
1. Verifica lag de replicação de todos os slaves
2. Se lag > 10 segundos, envia alerta
3. Se lag > 60 segundos, remove slave do balanceamento

EXERCÍCIO 2:
Crie sistema de cache para evitar consultas repetidas ao banco:
1. Primeira consulta: busca do banco, armazena em cache (Redis simulado)
2. Consultas seguintes: retorna do cache
3. Após escrita: invalida cache

EXERCÍCIO 3:
Simule cenário de split-brain:
1. Rede se divide: Master em uma partição, Slaves em outra
2. Ambas partições tentam processar escritas
3. Implemente resolução de conflitos quando rede voltar
*/

-- ----------------------------------------------------------------------------
-- PARTE 10: Monitoramento de Replicação
-- ----------------------------------------------------------------------------

-- View para monitorar saúde da replicação
CREATE OR REPLACE VIEW v_replication_health AS
SELECT 
    servidor_id,
    papel,
    status,
    ultimo_heartbeat,
    EXTRACT(SECOND FROM (SYSTIMESTAMP - ultimo_heartbeat)) AS segundos_desde_heartbeat,
    lag_replicacao_segundos,
    CASE 
        WHEN lag_replicacao_segundos < 5 THEN 'SAUDÁVEL'
        WHEN lag_replicacao_segundos < 30 THEN 'ATENÇÃO'
        ELSE 'CRÍTICO'
    END AS saude
FROM cluster_status;

-- Consultar saúde do cluster
SELECT * FROM v_replication_health;

-- Alertas automáticos
CREATE OR REPLACE PROCEDURE verificar_saude_cluster
IS
    v_count_criticos NUMBER;
BEGIN
    SELECT COUNT(*) INTO v_count_criticos
    FROM v_replication_health
    WHERE saude = 'CRÍTICO';
    
    IF v_count_criticos > 0 THEN
        DBMS_OUTPUT.PUT_LINE('⚠️  ALERTA: ' || v_count_criticos || ' servidor(es) em estado CRÍTICO!');
        -- Enviar email, SMS, Slack, PagerDuty, etc.
    END IF;
END;
/
