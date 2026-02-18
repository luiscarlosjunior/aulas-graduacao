-- =========================================
-- CRIAÇÃO DAS TABELAS
-- =========================================

-- 1. Cliente
CREATE TABLE Cliente (
    id_cliente      NUMBER PRIMARY KEY,
    nome            VARCHAR2(100) NOT NULL,
    email           VARCHAR2(120) UNIQUE NOT NULL,
    telefone        VARCHAR2(20),
    data_cadastro   DATE DEFAULT SYSDATE
);

-- 2. Cartao
CREATE TABLE Cartao (
    id_cartao       NUMBER PRIMARY KEY,
    id_cliente      NUMBER NOT NULL,
    numero_cartao   VARCHAR2(16) UNIQUE NOT NULL,
    validade        DATE NOT NULL,
    bandeira        VARCHAR2(20) CHECK (bandeira IN ('Visa','Mastercard','Elo','Amex')),
    limite_credito  NUMBER(12,2),
    CONSTRAINT fk_cartao_cliente FOREIGN KEY (id_cliente)
        REFERENCES Cliente(id_cliente) ON DELETE CASCADE
);

-- 3. Metodo_Pagamento
CREATE TABLE Metodo_Pagamento (
    id_metodo       NUMBER PRIMARY KEY,
    descricao       VARCHAR2(50) UNIQUE NOT NULL
);

-- 4. Fatura
CREATE TABLE Fatura (
    id_fatura       NUMBER PRIMARY KEY,
    id_cliente      NUMBER NOT NULL,
    data_inicio     DATE NOT NULL,
    data_fim        DATE NOT NULL,
    valor_total     NUMBER(12,2) DEFAULT 0,
    status          VARCHAR2(20) CHECK (status IN ('Aberta','Paga','Atrasada')),
    CONSTRAINT fk_fatura_cliente FOREIGN KEY (id_cliente)
        REFERENCES Cliente(id_cliente) ON DELETE CASCADE
);

-- 5. Transacao
CREATE TABLE Transacao (
    id_transacao    NUMBER PRIMARY KEY,
    id_cliente      NUMBER NOT NULL,
    id_cartao       NUMBER,
    id_metodo       NUMBER NOT NULL,
    id_fatura       NUMBER,
    valor           NUMBER(12,2) NOT NULL CHECK (valor > 0),
    data_transacao  TIMESTAMP DEFAULT SYSTIMESTAMP,
    status          VARCHAR2(20) CHECK (status IN ('Pendente','Confirmada','Cancelada')),
    CONSTRAINT fk_transacao_cliente FOREIGN KEY (id_cliente)
        REFERENCES Cliente(id_cliente) ON DELETE CASCADE,
    CONSTRAINT fk_transacao_cartao FOREIGN KEY (id_cartao)
        REFERENCES Cartao(id_cartao),
    CONSTRAINT fk_transacao_metodo FOREIGN KEY (id_metodo)
        REFERENCES Metodo_Pagamento(id_metodo),
    CONSTRAINT fk_transacao_fatura FOREIGN KEY (id_fatura)
        REFERENCES Fatura(id_fatura)
);

-- =========================================
-- INSERTS DE EXEMPLO
-- =========================================

-- Clientes
INSERT INTO Cliente VALUES (1, 'Carlos Silva', 'carlos@email.com', '11999999999', SYSDATE);
INSERT INTO Cliente VALUES (2, 'Ana Souza', 'ana@email.com', '21988888888', SYSDATE);
INSERT INTO Cliente VALUES (3, 'João Pereira', 'joao@email.com', '31977777777', SYSDATE);

-- Cartoes
INSERT INTO Cartao VALUES (1, 1, '4111111111111111', TO_DATE('2026-12-31','YYYY-MM-DD'), 'Visa', 5000);
INSERT INTO Cartao VALUES (2, 1, '5500000000000004', TO_DATE('2025-11-30','YYYY-MM-DD'), 'Mastercard', 10000);
INSERT INTO Cartao VALUES (3, 2, '340000000000009', TO_DATE('2027-01-31','YYYY-MM-DD'), 'Amex', 15000);

-- Métodos de pagamento
INSERT INTO Metodo_Pagamento VALUES (1, 'Cartão Crédito');
INSERT INTO Metodo_Pagamento VALUES (2, 'Cartão Débito');
INSERT INTO Metodo_Pagamento VALUES (3, 'PIX');
INSERT INTO Metodo_Pagamento VALUES (4, 'Boleto');

-- Faturas
INSERT INTO Fatura VALUES (1, 1, TO_DATE('2025-10-01','YYYY-MM-DD'), TO_DATE('2025-10-31','YYYY-MM-DD'), 0, 'Aberta');
INSERT INTO Fatura VALUES (2, 2, TO_DATE('2025-10-01','YYYY-MM-DD'), TO_DATE('2025-10-31','YYYY-MM-DD'), 0, 'Aberta');
INSERT INTO Fatura VALUES (3, 1, TO_DATE('2025-09-01','YYYY-MM-DD'), TO_DATE('2025-09-30','YYYY-MM-DD'), 1200, 'Paga');

-- Transações
INSERT INTO Transacao VALUES (1, 1, 1, 1, 1, 250.00, SYSTIMESTAMP, 'Confirmada');
INSERT INTO Transacao VALUES (2, 1, 2, 2, 1, 300.00, SYSTIMESTAMP, 'Confirmada');
INSERT INTO Transacao VALUES (3, 2, 3, 1, 2, 150.00, SYSTIMESTAMP, 'Pendente');
INSERT INTO Transacao VALUES (4, 3, NULL, 3, NULL, 500.00, SYSTIMESTAMP, 'Confirmada');
INSERT INTO Transacao VALUES (5, 1, 1, 4, 3, 1200.00, SYSTIMESTAMP, 'Confirmada');
