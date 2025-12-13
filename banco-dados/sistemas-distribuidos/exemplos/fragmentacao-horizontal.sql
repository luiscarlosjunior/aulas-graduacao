-- ============================================================================
-- EXEMPLO: Fragmentação Horizontal em Sistema Distribuído
-- ============================================================================
-- Cenário: Sistema de E-commerce com clientes distribuídos por região
-- Objetivo: Melhorar performance aproximando dados dos usuários
-- ============================================================================

-- ----------------------------------------------------------------------------
-- PARTE 1: Banco de Dados Centralizado (Situação Original)
-- ----------------------------------------------------------------------------

-- Tabela centralizada com todos os clientes
CREATE TABLE clientes_centralizado (
    cliente_id NUMBER PRIMARY KEY,
    nome VARCHAR2(100) NOT NULL,
    email VARCHAR2(100) UNIQUE NOT NULL,
    telefone VARCHAR2(20),
    estado VARCHAR2(2) NOT NULL,
    cidade VARCHAR2(100),
    data_cadastro DATE DEFAULT SYSDATE,
    status VARCHAR2(20) DEFAULT 'ATIVO'
);

-- Problema: Consultas de clientes de SP precisam varrer toda a tabela
-- Impacto: Lentidão quando há milhões de clientes de todas as regiões

-- ----------------------------------------------------------------------------
-- PARTE 2: Fragmentação Horizontal por Região
-- ----------------------------------------------------------------------------

-- NÓ 1: Servidor São Paulo
-- Contém apenas clientes da região Sudeste
CREATE TABLE clientes_sudeste (
    cliente_id NUMBER PRIMARY KEY,
    nome VARCHAR2(100) NOT NULL,
    email VARCHAR2(100) UNIQUE NOT NULL,
    telefone VARCHAR2(20),
    estado VARCHAR2(2) NOT NULL CHECK (estado IN ('SP', 'RJ', 'MG', 'ES')),
    cidade VARCHAR2(100),
    data_cadastro DATE DEFAULT SYSDATE,
    status VARCHAR2(20) DEFAULT 'ATIVO'
);

-- NÓ 2: Servidor Rio Grande do Sul
-- Contém apenas clientes da região Sul
CREATE TABLE clientes_sul (
    cliente_id NUMBER PRIMARY KEY,
    nome VARCHAR2(100) NOT NULL,
    email VARCHAR2(100) UNIQUE NOT NULL,
    telefone VARCHAR2(20),
    estado VARCHAR2(2) NOT NULL CHECK (estado IN ('RS', 'SC', 'PR')),
    cidade VARCHAR2(100),
    data_cadastro DATE DEFAULT SYSDATE,
    status VARCHAR2(20) DEFAULT 'ATIVO'
);

-- NÓ 3: Servidor Brasília
-- Contém clientes das regiões Centro-Oeste, Norte e Nordeste
CREATE TABLE clientes_outras_regioes (
    cliente_id NUMBER PRIMARY KEY,
    nome VARCHAR2(100) NOT NULL,
    email VARCHAR2(100) UNIQUE NOT NULL,
    telefone VARCHAR2(20),
    estado VARCHAR2(2) NOT NULL,
    cidade VARCHAR2(100),
    data_cadastro DATE DEFAULT SYSDATE,
    status VARCHAR2(20) DEFAULT 'ATIVO'
);

-- ----------------------------------------------------------------------------
-- PARTE 3: Inserção de Dados (Roteamento por Região)
-- ----------------------------------------------------------------------------

-- Função para determinar em qual fragmento inserir
CREATE OR REPLACE FUNCTION obter_fragmento_cliente(p_estado VARCHAR2)
RETURN VARCHAR2
IS
BEGIN
    IF p_estado IN ('SP', 'RJ', 'MG', 'ES') THEN
        RETURN 'SUDESTE';
    ELSIF p_estado IN ('RS', 'SC', 'PR') THEN
        RETURN 'SUL';
    ELSE
        RETURN 'OUTRAS';
    END IF;
END;
/

-- Procedure para inserir cliente no fragmento correto
CREATE OR REPLACE PROCEDURE inserir_cliente_distribuido(
    p_nome VARCHAR2,
    p_email VARCHAR2,
    p_telefone VARCHAR2,
    p_estado VARCHAR2,
    p_cidade VARCHAR2
)
IS
    v_fragmento VARCHAR2(20);
    v_cliente_id NUMBER;
BEGIN
    -- Gera ID único (em produção, usar sequência global distribuída)
    SELECT MAX(cliente_id) + 1 INTO v_cliente_id
    FROM (
        SELECT cliente_id FROM clientes_sudeste
        UNION ALL
        SELECT cliente_id FROM clientes_sul
        UNION ALL
        SELECT cliente_id FROM clientes_outras_regioes
    );
    
    IF v_cliente_id IS NULL THEN
        v_cliente_id := 1;
    END IF;
    
    -- Determina fragmento
    v_fragmento := obter_fragmento_cliente(p_estado);
    
    -- Insere no fragmento apropriado
    IF v_fragmento = 'SUDESTE' THEN
        INSERT INTO clientes_sudeste (cliente_id, nome, email, telefone, estado, cidade)
        VALUES (v_cliente_id, p_nome, p_email, p_telefone, p_estado, p_cidade);
    ELSIF v_fragmento = 'SUL' THEN
        INSERT INTO clientes_sul (cliente_id, nome, email, telefone, estado, cidade)
        VALUES (v_cliente_id, p_nome, p_email, p_telefone, p_estado, p_cidade);
    ELSE
        INSERT INTO clientes_outras_regioes (cliente_id, nome, email, telefone, estado, cidade)
        VALUES (v_cliente_id, p_nome, p_email, p_telefone, p_estado, p_cidade);
    END IF;
    
    COMMIT;
    
    DBMS_OUTPUT.PUT_LINE('Cliente ' || p_nome || ' inserido no fragmento ' || v_fragmento);
END;
/

-- ----------------------------------------------------------------------------
-- PARTE 4: Exemplos de Inserção
-- ----------------------------------------------------------------------------

-- Clientes da região Sudeste (vão para servidor SP)
BEGIN
    inserir_cliente_distribuido('João Silva', 'joao@email.com', '11999999999', 'SP', 'São Paulo');
    inserir_cliente_distribuido('Maria Santos', 'maria@email.com', '21988888888', 'RJ', 'Rio de Janeiro');
    inserir_cliente_distribuido('Carlos Oliveira', 'carlos@email.com', '31977777777', 'MG', 'Belo Horizonte');
END;
/

-- Clientes da região Sul (vão para servidor RS)
BEGIN
    inserir_cliente_distribuido('Ana Costa', 'ana@email.com', '51966666666', 'RS', 'Porto Alegre');
    inserir_cliente_distribuido('Pedro Souza', 'pedro@email.com', '47955555555', 'SC', 'Florianópolis');
END;
/

-- Clientes de outras regiões (vão para servidor Brasília)
BEGIN
    inserir_cliente_distribuido('Lucia Ferreira', 'lucia@email.com', '61944444444', 'DF', 'Brasília');
    inserir_cliente_distribuido('Roberto Lima', 'roberto@email.com', '85933333333', 'CE', 'Fortaleza');
END;
/

-- ----------------------------------------------------------------------------
-- PARTE 5: Consultas em Sistema Distribuído
-- ----------------------------------------------------------------------------

-- CONSULTA LOCAL (rápida): Cliente de SP consultando clientes de SP
-- Executa apenas no servidor local (NÓ 1)
SELECT nome, email, cidade 
FROM clientes_sudeste 
WHERE estado = 'SP'
ORDER BY nome;

-- CONSULTA GLOBAL (mais lenta): Listar todos os clientes do sistema
-- Precisa acessar todos os três nós (JOIN distribuído)
SELECT nome, email, estado, cidade
FROM (
    SELECT nome, email, estado, cidade FROM clientes_sudeste
    UNION ALL
    SELECT nome, email, estado, cidade FROM clientes_sul
    UNION ALL
    SELECT nome, email, estado, cidade FROM clientes_outras_regioes
)
ORDER BY nome;

-- CONSULTA CROSS-REGION: Clientes de SP buscando clientes do RS
-- Precisa acessar servidor remoto (NÓ 2)
-- Em produção, isso seria feito via database link
SELECT nome, email, cidade
FROM clientes_sul  -- Acesso remoto via DB Link
WHERE estado = 'RS';

-- ----------------------------------------------------------------------------
-- PARTE 6: Vantagens e Desvantagens
-- ----------------------------------------------------------------------------

/*
VANTAGENS:
1. Performance: Consultas locais são muito mais rápidas
   - Usuário de SP consulta dados de SP → sem latência de rede
   
2. Escalabilidade: Cada região pode ter seu próprio servidor
   - Crescimento de clientes no Sul não afeta servidor do Sudeste
   
3. Disponibilidade Local: Se servidor do Sul cair, Sudeste continua operando
   - Degradação parcial, não total
   
4. Conformidade Legal: Dados podem ficar na região geográfica exigida
   - Lei Geral de Proteção de Dados (LGPD) pode exigir dados no Brasil
   - GDPR (Europa) exige dados na UE

DESVANTAGENS:
1. Consultas Globais: Precisam acessar múltiplos nós
   - Relatórios gerenciais (todos os clientes) ficam lentos
   
2. Transações Distribuídas: Mais complexas
   - Transferir cliente entre regiões requer 2PC
   
3. Manutenção: Schema changes precisam ser aplicados em todos os nós
   - ALTER TABLE precisa rodar em 3 servidores
   
4. Complexidade: Aplicação precisa saber rotear requisições
   - Lógica de negócio mais complexa

QUANDO USAR:
- Aplicação tem localidade geográfica clara
- Consultas são majoritariamente locais
- Conformidade legal exige dados em regiões específicas
- Sistema precisa escalar horizontalmente

QUANDO NÃO USAR:
- Consultas globais são frequentes
- Dados precisam ser frequentemente movidos entre fragmentos
- Sistema é pequeno (não justifica complexidade)
*/

-- ----------------------------------------------------------------------------
-- PARTE 7: Exemplo do Dia a Dia - Uber/99
-- ----------------------------------------------------------------------------

/*
CENÁRIO REAL: UBER

Fragmentação por cidade/região:

NÓ São Paulo: Corridas em São Paulo
NÓ Rio de Janeiro: Corridas no Rio de Janeiro
NÓ Belo Horizonte: Corridas em Belo Horizonte

BENEFÍCIO:
- Motorista em SP busca corridas próximas → consulta apenas nó SP (rápido)
- Passageiro em RJ vê motoristas próximos → consulta apenas nó RJ (rápido)

PROBLEMA:
- Você mora em SP, viajou para RJ, quer ver histórico de todas as corridas
- Sistema precisa consultar nó SP + nó RJ (mais lento)
- Solução: Cache local ou replicação de histórico do usuário

IMPLEMENTAÇÃO:
- Cada corrida armazenada no nó da cidade onde ocorreu
- Histórico do usuário replicado para todos os nós (read replica)
- Matching de motorista-passageiro sempre local (baixa latência)
*/

-- Simulação: Tabela de corridas fragmentada por cidade
CREATE TABLE corridas_sao_paulo (
    corrida_id NUMBER PRIMARY KEY,
    motorista_id NUMBER NOT NULL,
    passageiro_id NUMBER NOT NULL,
    origem_lat FLOAT NOT NULL,
    origem_lng FLOAT NOT NULL,
    destino_lat FLOAT NOT NULL,
    destino_lng FLOAT NOT NULL,
    data_hora TIMESTAMP DEFAULT SYSTIMESTAMP,
    valor FLOAT,
    status VARCHAR2(20),
    CONSTRAINT chk_sp_corrida CHECK (
        -- Garante que corridas sejam de São Paulo (bbox simplificado)
        origem_lat BETWEEN -24.0 AND -23.3 AND
        origem_lng BETWEEN -46.9 AND -46.3
    )
);

-- Consulta local (rápida): Motoristas buscam corridas próximas em SP
SELECT corrida_id, origem_lat, origem_lng, valor
FROM corridas_sao_paulo
WHERE status = 'AGUARDANDO'
  AND SQRT(POWER(origem_lat - (-23.5505), 2) + 
           POWER(origem_lng - (-46.6333), 2)) < 0.05  -- ~5km
ORDER BY data_hora;

-- ----------------------------------------------------------------------------
-- PARTE 8: Exercício Prático
-- ----------------------------------------------------------------------------

/*
EXERCÍCIO:
1. Crie fragmentação para tabela de PEDIDOS de e-commerce por ano
   - pedidos_2022, pedidos_2023, pedidos_2024
   
2. Implemente procedure para inserir pedido no fragmento correto

3. Crie view que unifica todos os fragmentos

4. Analise: Quais consultas serão rápidas? Quais serão lentas?

RESPOSTA ESPERADA:
- Consultas de pedidos do ano corrente: RÁPIDAS (fragmento único)
- Consulta de histórico completo de cliente: LENTA (múltiplos fragmentos)
- Solução: Manter cache de "últimos 90 dias" replicado para acesso rápido
*/
