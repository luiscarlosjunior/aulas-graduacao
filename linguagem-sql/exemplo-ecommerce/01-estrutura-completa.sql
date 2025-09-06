-- ============================================================================
-- SISTEMA DE E-COMMERCE TECHSTORE
-- Criação de Estrutura Completa - Modelagem Física
-- ============================================================================
-- Descrição: Script para criação de todas as tabelas, constraints, índices
--           e triggers do sistema de e-commerce
-- Autor: Sistema de E-commerce TechStore
-- Versão: 1.0
-- Data: 2024
-- ============================================================================

-- Configurações iniciais
SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';

-- Criar database se não existir
CREATE DATABASE IF NOT EXISTS ecommerce_techstore 
    CHARACTER SET utf8mb4 
    COLLATE utf8mb4_unicode_ci;

USE ecommerce_techstore;

-- ============================================================================
-- TABELAS PRINCIPAIS
-- ============================================================================

-- ----------------------------------------------------------------------------
-- Tabela: FORNECEDOR
-- Propósito: Empresas que fornecem produtos para a loja
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS FORNECEDOR (
    id_fornecedor       INT AUTO_INCREMENT,
    razao_social        VARCHAR(200) NOT NULL,
    nome_fantasia       VARCHAR(200),
    cnpj                CHAR(14) NOT NULL,
    email               VARCHAR(100),
    telefone            VARCHAR(15),
    endereco            TEXT,
    contato_responsavel VARCHAR(100),
    data_cadastro       TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    ativo               BOOLEAN DEFAULT TRUE,
    
    -- Constraints
    CONSTRAINT pk_fornecedor PRIMARY KEY (id_fornecedor),
    CONSTRAINT uk_fornecedor_cnpj UNIQUE (cnpj)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------------------------------------------------------
-- Tabela: CATEGORIA
-- Propósito: Organização hierárquica dos produtos
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS CATEGORIA (
    id_categoria        INT AUTO_INCREMENT,
    nome_categoria      VARCHAR(100) NOT NULL,
    descricao           TEXT,
    id_categoria_pai    INT,
    ordem_exibicao      INT DEFAULT 0,
    data_cadastro       TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    ativo               BOOLEAN DEFAULT TRUE,
    
    -- Constraints
    CONSTRAINT pk_categoria PRIMARY KEY (id_categoria),
    CONSTRAINT fk_categoria_pai FOREIGN KEY (id_categoria_pai) 
        REFERENCES CATEGORIA(id_categoria)
        ON UPDATE CASCADE ON DELETE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------------------------------------------------------
-- Tabela: PRODUTO
-- Propósito: Catálogo principal de produtos da loja
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS PRODUTO (
    id_produto          INT AUTO_INCREMENT,
    nome_produto        VARCHAR(200) NOT NULL,
    descricao_curta     VARCHAR(500),
    descricao_completa  TEXT,
    marca               VARCHAR(100),
    modelo              VARCHAR(100),
    codigo_barras       VARCHAR(13),
    preco_atual         DECIMAL(10,2) NOT NULL,
    peso_kg             DECIMAL(6,3),
    comprimento_cm      DECIMAL(6,2),
    largura_cm          DECIMAL(6,2),
    altura_cm           DECIMAL(6,2),
    garantia_meses      INT DEFAULT 0,
    id_categoria        INT NOT NULL,
    id_fornecedor       INT NOT NULL,
    data_cadastro       TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    data_atualizacao    TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    ativo               BOOLEAN DEFAULT TRUE,
    
    -- Constraints
    CONSTRAINT pk_produto PRIMARY KEY (id_produto),
    CONSTRAINT uk_produto_codigo_barras UNIQUE (codigo_barras),
    CONSTRAINT fk_produto_categoria FOREIGN KEY (id_categoria) 
        REFERENCES CATEGORIA(id_categoria)
        ON UPDATE CASCADE ON DELETE RESTRICT,
    CONSTRAINT fk_produto_fornecedor FOREIGN KEY (id_fornecedor) 
        REFERENCES FORNECEDOR(id_fornecedor)
        ON UPDATE CASCADE ON DELETE RESTRICT,
    CONSTRAINT chk_produto_preco CHECK (preco_atual > 0),
    CONSTRAINT chk_produto_peso CHECK (peso_kg IS NULL OR peso_kg > 0),
    CONSTRAINT chk_produto_garantia CHECK (garantia_meses >= 0)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------------------------------------------------------
-- Tabela: ESTOQUE
-- Propósito: Controle de disponibilidade e reserva de produtos
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS ESTOQUE (
    id_estoque           INT AUTO_INCREMENT,
    id_produto           INT NOT NULL,
    quantidade_disponivel INT DEFAULT 0,
    quantidade_reservada  INT DEFAULT 0,
    estoque_minimo       INT DEFAULT 5,
    data_ultima_entrada  DATE,
    data_ultima_saida    DATE,
    
    -- Constraints
    CONSTRAINT pk_estoque PRIMARY KEY (id_estoque),
    CONSTRAINT uk_estoque_produto UNIQUE (id_produto),
    CONSTRAINT fk_estoque_produto FOREIGN KEY (id_produto) 
        REFERENCES PRODUTO(id_produto)
        ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT chk_estoque_disponivel CHECK (quantidade_disponivel >= 0),
    CONSTRAINT chk_estoque_reservada CHECK (quantidade_reservada >= 0),
    CONSTRAINT chk_estoque_minimo CHECK (estoque_minimo >= 0)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------------------------------------------------------
-- Tabela: CLIENTE
-- Propósito: Cadastro de clientes pessoa física e jurídica
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS CLIENTE (
    id_cliente          INT AUTO_INCREMENT,
    nome_completo       VARCHAR(100) NOT NULL,
    email               VARCHAR(100) NOT NULL,
    cpf_cnpj            VARCHAR(14),
    telefone            VARCHAR(15),
    data_nascimento     DATE,
    tipo_pessoa         ENUM('F', 'J') NOT NULL DEFAULT 'F',
    data_cadastro       TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    data_ultimo_login   TIMESTAMP NULL,
    ativo               BOOLEAN DEFAULT TRUE,
    
    -- Constraints
    CONSTRAINT pk_cliente PRIMARY KEY (id_cliente),
    CONSTRAINT uk_cliente_email UNIQUE (email),
    CONSTRAINT uk_cliente_cpf_cnpj UNIQUE (cpf_cnpj),
    CONSTRAINT chk_cliente_cpf_cnpj CHECK (
        (tipo_pessoa = 'F' AND LENGTH(cpf_cnpj) = 11) OR 
        (tipo_pessoa = 'J' AND LENGTH(cpf_cnpj) = 14) OR 
        cpf_cnpj IS NULL
    )
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------------------------------------------------------
-- Tabela: ENDERECO
-- Propósito: Endereços de entrega dos clientes
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS ENDERECO (
    id_endereco         INT AUTO_INCREMENT,
    id_cliente          INT NOT NULL,
    tipo_endereco       ENUM('RESIDENCIAL', 'COMERCIAL', 'ENTREGA') NOT NULL,
    nome_endereco       VARCHAR(50),
    logradouro          VARCHAR(200) NOT NULL,
    numero              VARCHAR(10),
    complemento         VARCHAR(100),
    bairro              VARCHAR(100) NOT NULL,
    cidade              VARCHAR(100) NOT NULL,
    estado              CHAR(2) NOT NULL,
    cep                 CHAR(8) NOT NULL,
    pais                VARCHAR(50) DEFAULT 'Brasil',
    endereco_padrao     BOOLEAN DEFAULT FALSE,
    data_cadastro       TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    
    -- Constraints
    CONSTRAINT pk_endereco PRIMARY KEY (id_endereco),
    CONSTRAINT fk_endereco_cliente FOREIGN KEY (id_cliente) 
        REFERENCES CLIENTE(id_cliente)
        ON UPDATE CASCADE ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------------------------------------------------------
-- Tabela: FORMA_PAGAMENTO
-- Propósito: Métodos de pagamento cadastrados pelos clientes
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS FORMA_PAGAMENTO (
    id_forma_pagamento  INT AUTO_INCREMENT,
    id_cliente          INT NOT NULL,
    tipo                ENUM('CARTAO_CREDITO', 'CARTAO_DEBITO', 'PIX', 'BOLETO') NOT NULL,
    descricao           VARCHAR(100) NOT NULL,
    numero_final        VARCHAR(4),
    nome_portador       VARCHAR(100),
    mes_validade        INT,
    ano_validade        INT,
    data_cadastro       TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    ativo               BOOLEAN DEFAULT TRUE,
    
    -- Constraints
    CONSTRAINT pk_forma_pagamento PRIMARY KEY (id_forma_pagamento),
    CONSTRAINT fk_forma_pagamento_cliente FOREIGN KEY (id_cliente) 
        REFERENCES CLIENTE(id_cliente)
        ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT chk_forma_pagamento_cartao CHECK (
        (tipo IN ('CARTAO_CREDITO', 'CARTAO_DEBITO') AND 
         numero_final IS NOT NULL AND mes_validade IS NOT NULL AND ano_validade IS NOT NULL)
        OR 
        tipo IN ('PIX', 'BOLETO')
    )
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------------------------------------------------------
-- Tabela: CARRINHO
-- Propósito: Carrinho único por cliente
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS CARRINHO (
    id_carrinho         INT AUTO_INCREMENT,
    id_cliente          INT NOT NULL,
    data_criacao        TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    data_ultima_alteracao TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    
    -- Constraints
    CONSTRAINT pk_carrinho PRIMARY KEY (id_carrinho),
    CONSTRAINT uk_carrinho_cliente UNIQUE (id_cliente),
    CONSTRAINT fk_carrinho_cliente FOREIGN KEY (id_cliente) 
        REFERENCES CLIENTE(id_cliente)
        ON UPDATE CASCADE ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------------------------------------------------------
-- Tabela: ITEM_CARRINHO
-- Propósito: Produtos adicionados ao carrinho (Relacionamento N:M)
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS ITEM_CARRINHO (
    id_item_carrinho    INT AUTO_INCREMENT,
    id_carrinho         INT NOT NULL,
    id_produto          INT NOT NULL,
    quantidade          INT NOT NULL DEFAULT 1,
    preco_unitario      DECIMAL(10,2) NOT NULL,
    data_adicao         TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    
    -- Constraints
    CONSTRAINT pk_item_carrinho PRIMARY KEY (id_item_carrinho),
    CONSTRAINT uk_item_carrinho_produto UNIQUE (id_carrinho, id_produto),
    CONSTRAINT fk_item_carrinho_carrinho FOREIGN KEY (id_carrinho) 
        REFERENCES CARRINHO(id_carrinho)
        ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT fk_item_carrinho_produto FOREIGN KEY (id_produto) 
        REFERENCES PRODUTO(id_produto)
        ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT chk_item_carrinho_quantidade CHECK (quantidade > 0),
    CONSTRAINT chk_item_carrinho_preco CHECK (preco_unitario > 0)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------------------------------------------------------
-- Tabela: STATUS_PEDIDO
-- Propósito: Estados possíveis dos pedidos
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS STATUS_PEDIDO (
    id_status           INT,
    nome_status         VARCHAR(50) NOT NULL,
    descricao           VARCHAR(200),
    ordem_status        INT NOT NULL,
    permite_cancelamento BOOLEAN DEFAULT FALSE,
    cor_exibicao        VARCHAR(7),
    
    -- Constraints
    CONSTRAINT pk_status_pedido PRIMARY KEY (id_status),
    CONSTRAINT uk_status_ordem UNIQUE (ordem_status)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------------------------------------------------------
-- Tabela: PEDIDO
-- Propósito: Registro das compras finalizadas
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS PEDIDO (
    id_pedido           INT AUTO_INCREMENT,
    numero_pedido       VARCHAR(20) NOT NULL,
    id_cliente          INT NOT NULL,
    id_endereco_entrega INT NOT NULL,
    id_status           INT NOT NULL DEFAULT 1,
    data_pedido         TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    valor_produtos      DECIMAL(10,2) NOT NULL,
    valor_frete         DECIMAL(10,2) DEFAULT 0.00,
    valor_desconto      DECIMAL(10,2) DEFAULT 0.00,
    valor_total         DECIMAL(10,2) NOT NULL,
    observacoes         TEXT,
    data_entrega_prevista DATE,
    
    -- Constraints
    CONSTRAINT pk_pedido PRIMARY KEY (id_pedido),
    CONSTRAINT uk_pedido_numero UNIQUE (numero_pedido),
    CONSTRAINT fk_pedido_cliente FOREIGN KEY (id_cliente) 
        REFERENCES CLIENTE(id_cliente)
        ON UPDATE CASCADE ON DELETE RESTRICT,
    CONSTRAINT fk_pedido_endereco FOREIGN KEY (id_endereco_entrega) 
        REFERENCES ENDERECO(id_endereco)
        ON UPDATE CASCADE ON DELETE RESTRICT,
    CONSTRAINT fk_pedido_status FOREIGN KEY (id_status) 
        REFERENCES STATUS_PEDIDO(id_status)
        ON UPDATE CASCADE ON DELETE RESTRICT,
    CONSTRAINT chk_pedido_valor_produtos CHECK (valor_produtos > 0),
    CONSTRAINT chk_pedido_valor_frete CHECK (valor_frete >= 0),
    CONSTRAINT chk_pedido_valor_desconto CHECK (valor_desconto >= 0),
    CONSTRAINT chk_pedido_data_entrega CHECK (
        data_entrega_prevista IS NULL OR 
        data_entrega_prevista >= DATE(data_pedido)
    )
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------------------------------------------------------
-- Tabela: ITEM_PEDIDO
-- Propósito: Produtos de cada pedido (Relacionamento N:M)
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS ITEM_PEDIDO (
    id_item_pedido      INT AUTO_INCREMENT,
    id_pedido           INT NOT NULL,
    id_produto          INT NOT NULL,
    quantidade          INT NOT NULL,
    preco_unitario      DECIMAL(10,2) NOT NULL,
    preco_total         DECIMAL(10,2) NOT NULL,
    
    -- Constraints
    CONSTRAINT pk_item_pedido PRIMARY KEY (id_item_pedido),
    CONSTRAINT fk_item_pedido_pedido FOREIGN KEY (id_pedido) 
        REFERENCES PEDIDO(id_pedido)
        ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT fk_item_pedido_produto FOREIGN KEY (id_produto) 
        REFERENCES PRODUTO(id_produto)
        ON UPDATE CASCADE ON DELETE RESTRICT,
    CONSTRAINT chk_item_pedido_quantidade CHECK (quantidade > 0),
    CONSTRAINT chk_item_pedido_preco CHECK (preco_unitario > 0),
    CONSTRAINT chk_item_pedido_total CHECK (preco_total = quantidade * preco_unitario)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------------------------------------------------------
-- Tabela: AVALIACAO
-- Propósito: Avaliações dos clientes sobre produtos
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS AVALIACAO (
    id_avaliacao        INT AUTO_INCREMENT,
    id_produto          INT NOT NULL,
    id_cliente          INT NOT NULL,
    nota                INT NOT NULL,
    titulo              VARCHAR(100),
    comentario          TEXT,
    data_avaliacao      TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    verificada          BOOLEAN DEFAULT FALSE,
    votos_uteis         INT DEFAULT 0,
    votos_totais        INT DEFAULT 0,
    
    -- Constraints
    CONSTRAINT pk_avaliacao PRIMARY KEY (id_avaliacao),
    CONSTRAINT uk_avaliacao_cliente_produto UNIQUE (id_cliente, id_produto),
    CONSTRAINT fk_avaliacao_produto FOREIGN KEY (id_produto) 
        REFERENCES PRODUTO(id_produto)
        ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT fk_avaliacao_cliente FOREIGN KEY (id_cliente) 
        REFERENCES CLIENTE(id_cliente)
        ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT chk_avaliacao_nota CHECK (nota BETWEEN 1 AND 5),
    CONSTRAINT chk_avaliacao_votos CHECK (
        votos_uteis >= 0 AND 
        votos_uteis <= votos_totais AND 
        votos_totais >= 0
    )
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------------------------------------------------------
-- Tabela: CUPOM_DESCONTO
-- Propósito: Cupons promocionais e descontos
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS CUPOM_DESCONTO (
    id_cupom            INT AUTO_INCREMENT,
    codigo_cupom        VARCHAR(20) NOT NULL,
    descricao           VARCHAR(100) NOT NULL,
    tipo_desconto       ENUM('PERCENTUAL', 'VALOR_FIXO') NOT NULL,
    valor_desconto      DECIMAL(10,2) NOT NULL,
    valor_minimo_pedido DECIMAL(10,2) DEFAULT 0.00,
    data_inicio         DATE NOT NULL,
    data_fim            DATE NOT NULL,
    limite_uso_total    INT DEFAULT NULL,
    limite_uso_cliente  INT DEFAULT 1,
    usado_total         INT DEFAULT 0,
    data_cadastro       TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    ativo               BOOLEAN DEFAULT TRUE,
    
    -- Constraints
    CONSTRAINT pk_cupom PRIMARY KEY (id_cupom),
    CONSTRAINT uk_cupom_codigo UNIQUE (codigo_cupom),
    CONSTRAINT chk_cupom_valor_desconto CHECK (valor_desconto > 0),
    CONSTRAINT chk_cupom_valor_minimo CHECK (valor_minimo_pedido >= 0),
    CONSTRAINT chk_cupom_periodo CHECK (data_fim >= data_inicio),
    CONSTRAINT chk_cupom_limites CHECK (limite_uso_cliente > 0),
    CONSTRAINT chk_cupom_usado CHECK (
        usado_total >= 0 AND 
        (limite_uso_total IS NULL OR usado_total <= limite_uso_total)
    )
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------------------------------------------------------
-- Tabela: CUPOM_UTILIZADO
-- Propósito: Registro de cupons utilizados (Relacionamento N:M)
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS CUPOM_UTILIZADO (
    id_cupom_utilizado  INT AUTO_INCREMENT,
    id_cupom            INT NOT NULL,
    id_pedido           INT NOT NULL,
    id_cliente          INT NOT NULL,
    valor_desconto_aplicado DECIMAL(10,2) NOT NULL,
    data_utilizacao     TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    
    -- Constraints
    CONSTRAINT pk_cupom_utilizado PRIMARY KEY (id_cupom_utilizado),
    CONSTRAINT fk_cupom_utilizado_cupom FOREIGN KEY (id_cupom) 
        REFERENCES CUPOM_DESCONTO(id_cupom)
        ON UPDATE CASCADE ON DELETE RESTRICT,
    CONSTRAINT fk_cupom_utilizado_pedido FOREIGN KEY (id_pedido) 
        REFERENCES PEDIDO(id_pedido)
        ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT fk_cupom_utilizado_cliente FOREIGN KEY (id_cliente) 
        REFERENCES CLIENTE(id_cliente)
        ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT chk_cupom_utilizado_valor CHECK (valor_desconto_aplicado > 0)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ============================================================================
-- ÍNDICES PARA OTIMIZAÇÃO DE PERFORMANCE
-- ============================================================================

-- Índices para busca de produtos
CREATE INDEX idx_produto_categoria_ativo ON PRODUTO(id_categoria, ativo);
CREATE INDEX idx_produto_marca ON PRODUTO(marca);
CREATE INDEX idx_produto_preco ON PRODUTO(preco_atual);

-- Índice Full-Text para busca textual
CREATE FULLTEXT INDEX idx_produto_busca ON PRODUTO(nome_produto, descricao_curta);

-- Índices para pedidos e histórico
CREATE INDEX idx_pedido_cliente_data ON PEDIDO(id_cliente, data_pedido DESC);
CREATE INDEX idx_pedido_status_data ON PEDIDO(id_status, data_pedido);
CREATE INDEX idx_pedido_data_entrega ON PEDIDO(data_entrega_prevista);

-- Índices para avaliações
CREATE INDEX idx_avaliacao_produto_nota ON AVALIACAO(id_produto, nota, verificada);
CREATE INDEX idx_avaliacao_data ON AVALIACAO(data_avaliacao DESC);

-- Índices para controle de estoque
CREATE INDEX idx_estoque_baixo ON ESTOQUE(quantidade_disponivel, estoque_minimo);
CREATE INDEX idx_estoque_disponivel ON ESTOQUE(quantidade_disponivel);

-- Índices para carrinho
CREATE INDEX idx_item_carrinho_data ON ITEM_CARRINHO(id_carrinho, data_adicao);

-- Índices para cupons
CREATE INDEX idx_cupom_ativo_validade ON CUPOM_DESCONTO(ativo, data_inicio, data_fim);
CREATE INDEX idx_cupom_codigo_ativo ON CUPOM_DESCONTO(codigo_cupom, ativo);

-- Índices para endereços
CREATE INDEX idx_endereco_cliente_padrao ON ENDERECO(id_cliente, endereco_padrao);

-- ============================================================================
-- DADOS INICIAIS
-- ============================================================================

-- Inserir status de pedido
INSERT INTO STATUS_PEDIDO (id_status, nome_status, descricao, ordem_status, permite_cancelamento, cor_exibicao) VALUES 
(1, 'Aguardando Pagamento', 'Pedido criado, aguardando confirmação do pagamento', 1, TRUE, '#FFA500'),
(2, 'Pagamento Confirmado', 'Pagamento aprovado, pedido será preparado', 2, TRUE, '#00FF00'),
(3, 'Preparando Envio', 'Pedido sendo separado no estoque', 3, TRUE, '#0080FF'),
(4, 'Enviado', 'Pedido despachado para entrega', 4, FALSE, '#8000FF'),
(5, 'Entregue', 'Pedido entregue ao cliente', 5, FALSE, '#006400'),
(6, 'Cancelado', 'Pedido cancelado pelo cliente ou sistema', 6, FALSE, '#FF0000');

-- ============================================================================
-- TRIGGERS PARA REGRAS DE NEGÓCIO
-- ============================================================================

-- Trigger para criar carrinho automaticamente quando cliente é cadastrado
DELIMITER //
CREATE TRIGGER trg_criar_carrinho_cliente
AFTER INSERT ON CLIENTE
FOR EACH ROW
BEGIN
    INSERT INTO CARRINHO (id_cliente) VALUES (NEW.id_cliente);
END//
DELIMITER ;

-- Trigger para criar controle de estoque quando produto é cadastrado
DELIMITER //
CREATE TRIGGER trg_criar_estoque_produto
AFTER INSERT ON PRODUTO
FOR EACH ROW
BEGIN
    INSERT INTO ESTOQUE (id_produto) VALUES (NEW.id_produto);
END//
DELIMITER ;

-- Trigger para atualizar estoque quando item é adicionado ao carrinho
DELIMITER //
CREATE TRIGGER trg_reservar_estoque_carrinho
AFTER INSERT ON ITEM_CARRINHO
FOR EACH ROW
BEGIN
    UPDATE ESTOQUE 
    SET quantidade_reservada = quantidade_reservada + NEW.quantidade
    WHERE id_produto = NEW.id_produto;
END//
DELIMITER ;

-- Trigger para atualizar estoque quando item é removido do carrinho
DELIMITER //
CREATE TRIGGER trg_liberar_estoque_carrinho
AFTER DELETE ON ITEM_CARRINHO
FOR EACH ROW
BEGIN
    UPDATE ESTOQUE 
    SET quantidade_reservada = quantidade_reservada - OLD.quantidade
    WHERE id_produto = OLD.id_produto;
END//
DELIMITER ;

-- Trigger para atualizar estoque quando quantidade no carrinho é alterada
DELIMITER //
CREATE TRIGGER trg_atualizar_estoque_carrinho
AFTER UPDATE ON ITEM_CARRINHO
FOR EACH ROW
BEGIN
    UPDATE ESTOQUE 
    SET quantidade_reservada = quantidade_reservada + (NEW.quantidade - OLD.quantidade)
    WHERE id_produto = NEW.id_produto;
END//
DELIMITER ;

-- Trigger para garantir apenas um endereço padrão por cliente
DELIMITER //
CREATE TRIGGER trg_endereco_padrao_unico
BEFORE INSERT ON ENDERECO
FOR EACH ROW
BEGIN
    IF NEW.endereco_padrao = TRUE THEN
        UPDATE ENDERECO 
        SET endereco_padrao = FALSE 
        WHERE id_cliente = NEW.id_cliente;
    END IF;
END//
DELIMITER ;

-- Trigger para atualizar contador de cupom usado
DELIMITER //
CREATE TRIGGER trg_incrementar_uso_cupom
AFTER INSERT ON CUPOM_UTILIZADO
FOR EACH ROW
BEGIN
    UPDATE CUPOM_DESCONTO 
    SET usado_total = usado_total + 1 
    WHERE id_cupom = NEW.id_cupom;
END//
DELIMITER ;

-- ============================================================================
-- VIEWS PARA FACILITAR CONSULTAS COMUNS
-- ============================================================================

-- View para produtos com informações de estoque e categoria
CREATE VIEW vw_produto_completo AS
SELECT 
    p.id_produto,
    p.nome_produto,
    p.descricao_curta,
    p.marca,
    p.modelo,
    p.preco_atual,
    p.garantia_meses,
    c.nome_categoria,
    f.nome_fantasia as fornecedor,
    e.quantidade_disponivel,
    e.quantidade_reservada,
    e.estoque_minimo,
    p.ativo as produto_ativo,
    CASE 
        WHEN e.quantidade_disponivel > e.estoque_minimo THEN 'NORMAL'
        WHEN e.quantidade_disponivel > 0 THEN 'BAIXO'
        ELSE 'ESGOTADO'
    END as status_estoque
FROM PRODUTO p
INNER JOIN CATEGORIA c ON p.id_categoria = c.id_categoria
INNER JOIN FORNECEDOR f ON p.id_fornecedor = f.id_fornecedor
INNER JOIN ESTOQUE e ON p.id_produto = e.id_produto;

-- View para carrinho com totais
CREATE VIEW vw_carrinho_resumo AS
SELECT 
    c.id_cliente,
    c.id_carrinho,
    COUNT(ic.id_item_carrinho) as total_itens,
    SUM(ic.quantidade) as quantidade_total,
    SUM(ic.quantidade * ic.preco_unitario) as valor_total,
    c.data_ultima_alteracao
FROM CARRINHO c
LEFT JOIN ITEM_CARRINHO ic ON c.id_carrinho = ic.id_carrinho
GROUP BY c.id_cliente, c.id_carrinho, c.data_ultima_alteracao;

-- View para pedidos com informações completas
CREATE VIEW vw_pedido_completo AS
SELECT 
    p.id_pedido,
    p.numero_pedido,
    cli.nome_completo as cliente,
    cli.email,
    s.nome_status,
    p.data_pedido,
    p.valor_produtos,
    p.valor_frete,
    p.valor_desconto,
    p.valor_total,
    p.data_entrega_prevista,
    COUNT(ip.id_item_pedido) as total_itens
FROM PEDIDO p
INNER JOIN CLIENTE cli ON p.id_cliente = cli.id_cliente
INNER JOIN STATUS_PEDIDO s ON p.id_status = s.id_status
LEFT JOIN ITEM_PEDIDO ip ON p.id_pedido = ip.id_pedido
GROUP BY p.id_pedido;

-- ============================================================================
-- RESTAURAR CONFIGURAÇÕES
-- ============================================================================
SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- ============================================================================
-- VERIFICAÇÕES FINAIS
-- ============================================================================

-- Mostrar tabelas criadas
SELECT 'ESTRUTURA CRIADA COM SUCESSO!' as status;
SHOW TABLES;

-- Mostrar estatísticas das tabelas
SELECT 
    TABLE_NAME as tabela,
    TABLE_ROWS as linhas,
    ROUND(((DATA_LENGTH + INDEX_LENGTH) / 1024 / 1024), 2) as tamanho_mb
FROM information_schema.TABLES 
WHERE TABLE_SCHEMA = 'ecommerce_techstore' 
ORDER BY TABLE_NAME;