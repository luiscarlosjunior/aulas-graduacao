# Processo Completo de Modelagem de Dados
## Estudo de Caso: Sistema de E-commerce

### 📋 Cenário de Negócio

A empresa **TechStore** está desenvolvendo uma plataforma de e-commerce para venda de produtos eletrônicos. O sistema deve permitir:

- Cadastro de clientes e fornecedores
- Catálogo de produtos organizados por categorias
- Carrinho de compras e processamento de pedidos
- Sistema de avaliações de produtos
- Controle de estoque
- Histórico de compras
- Sistema de cupons de desconto

---

## 🎯 Etapa 1: Análise de Requisitos

### Requisitos Funcionais Detalhados

**RF01 - Gestão de Clientes**
- Cliente pode se cadastrar com dados pessoais
- Cliente pode ter múltiplos endereços
- Cliente pode ter múltiplas formas de pagamento
- Sistema deve validar CPF/CNPJ

**RF02 - Gestão de Produtos**
- Produtos organizados em categorias hierárquicas
- Produtos têm especificações técnicas
- Controle de estoque por produto
- Produtos podem ter múltiplas imagens
- Preços podem variar por período (promoções)

**RF03 - Processo de Compra**
- Cliente pode adicionar produtos ao carrinho
- Sistema calcula frete baseado no CEP
- Aplicação de cupons de desconto
- Múltiplas formas de pagamento por pedido
- Acompanhamento de status do pedido

**RF04 - Sistema de Avaliações**
- Cliente pode avaliar produtos comprados
- Avaliações têm nota (1-5) e comentário
- Clientes podem marcar avaliações como úteis

---

## 🏗️ Etapa 2: Modelagem Conceitual

### Identificação de Entidades

**Entidades Principais**:
```
CLIENTE, PRODUTO, CATEGORIA, PEDIDO, CARRINHO
FORNECEDOR, ESTOQUE, AVALIACAO, ENDERECO
FORMA_PAGAMENTO, CUPOM_DESCONTO, STATUS_PEDIDO
```

### Relacionamentos Conceituais

```
CLIENTE ──(1:N)── ENDERECO
CLIENTE ──(1:N)── FORMA_PAGAMENTO  
CLIENTE ──(1:N)── PEDIDO
CLIENTE ──(1:1)── CARRINHO
CLIENTE ──(1:N)── AVALIACAO

CATEGORIA ──(1:N)── PRODUTO
CATEGORIA ──(1:N)── CATEGORIA (auto-relacionamento hierárquico)
FORNECEDOR ──(1:N)── PRODUTO
PRODUTO ──(1:N)── ESTOQUE
PRODUTO ──(1:N)── AVALIACAO

PEDIDO ──(N:M)── PRODUTO (através de ITEM_PEDIDO)
CARRINHO ──(N:M)── PRODUTO (através de ITEM_CARRINHO)
CUPOM ──(N:M)── PEDIDO
```

---

## 📊 Etapa 3: Modelagem Lógica

### Estrutura das Tabelas

#### Entidades de Clientes
```sql
CLIENTE (
    id_cliente INT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    cpf_cnpj VARCHAR(14) UNIQUE,
    telefone VARCHAR(15),
    data_nascimento DATE,
    tipo_pessoa ENUM('F', 'J'), -- Física ou Jurídica
    data_cadastro TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    ativo BOOLEAN DEFAULT TRUE
);

ENDERECO (
    id_endereco INT PRIMARY KEY,
    id_cliente INT NOT NULL,
    tipo_endereco ENUM('RESIDENCIAL', 'COMERCIAL', 'ENTREGA'),
    logradouro VARCHAR(200) NOT NULL,
    numero VARCHAR(10),
    complemento VARCHAR(100),
    bairro VARCHAR(100),
    cidade VARCHAR(100),
    estado CHAR(2),
    cep CHAR(8),
    pais VARCHAR(50) DEFAULT 'Brasil',
    padrao BOOLEAN DEFAULT FALSE, -- Endereço padrão
    FOREIGN KEY (id_cliente) REFERENCES CLIENTE(id_cliente)
);

FORMA_PAGAMENTO (
    id_forma_pagamento INT PRIMARY KEY,
    id_cliente INT NOT NULL,
    tipo ENUM('CARTAO_CREDITO', 'CARTAO_DEBITO', 'PIX', 'BOLETO'),
    descricao VARCHAR(100),
    numero_cartao VARCHAR(20), -- Últimos 4 dígitos apenas
    nome_portador VARCHAR(100),
    validade_mes INT,
    validade_ano INT,
    ativo BOOLEAN DEFAULT TRUE,
    FOREIGN KEY (id_cliente) REFERENCES CLIENTE(id_cliente)
);
```

#### Entidades de Produtos
```sql
CATEGORIA (
    id_categoria INT PRIMARY KEY,
    nome_categoria VARCHAR(100) NOT NULL,
    descricao TEXT,
    id_categoria_pai INT, -- Para hierarquia
    ativo BOOLEAN DEFAULT TRUE,
    FOREIGN KEY (id_categoria_pai) REFERENCES CATEGORIA(id_categoria)
);

FORNECEDOR (
    id_fornecedor INT PRIMARY KEY,
    razao_social VARCHAR(200) NOT NULL,
    nome_fantasia VARCHAR(200),
    cnpj CHAR(14) UNIQUE NOT NULL,
    email VARCHAR(100),
    telefone VARCHAR(15),
    endereco TEXT,
    contato_responsavel VARCHAR(100),
    ativo BOOLEAN DEFAULT TRUE
);

PRODUTO (
    id_produto INT PRIMARY KEY,
    nome_produto VARCHAR(200) NOT NULL,
    descricao_curta VARCHAR(500),
    descricao_completa TEXT,
    marca VARCHAR(100),
    modelo VARCHAR(100),
    codigo_barras VARCHAR(13) UNIQUE,
    preco_atual DECIMAL(10,2) NOT NULL,
    peso_kg DECIMAL(6,3),
    dimensoes VARCHAR(50), -- LxAxP em cm
    garantia_meses INT,
    id_categoria INT NOT NULL,
    id_fornecedor INT NOT NULL,
    data_cadastro TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    ativo BOOLEAN DEFAULT TRUE,
    FOREIGN KEY (id_categoria) REFERENCES CATEGORIA(id_categoria),
    FOREIGN KEY (id_fornecedor) REFERENCES FORNECEDOR(id_fornecedor)
);

ESTOQUE (
    id_estoque INT PRIMARY KEY,
    id_produto INT NOT NULL,
    quantidade_disponivel INT DEFAULT 0,
    quantidade_reservada INT DEFAULT 0,
    estoque_minimo INT DEFAULT 5,
    data_ultima_entrada DATE,
    data_ultima_saida DATE,
    FOREIGN KEY (id_produto) REFERENCES PRODUTO(id_produto)
);
```

#### Entidades de Carrinho e Pedidos
```sql
CARRINHO (
    id_carrinho INT PRIMARY KEY,
    id_cliente INT UNIQUE NOT NULL,
    data_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    data_ultima_alteracao TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (id_cliente) REFERENCES CLIENTE(id_cliente)
);

ITEM_CARRINHO (
    id_item_carrinho INT PRIMARY KEY,
    id_carrinho INT NOT NULL,
    id_produto INT NOT NULL,
    quantidade INT NOT NULL DEFAULT 1,
    preco_unitario DECIMAL(10,2) NOT NULL,
    data_adicao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (id_carrinho) REFERENCES CARRINHO(id_carrinho),
    FOREIGN KEY (id_produto) REFERENCES PRODUTO(id_produto),
    UNIQUE KEY unique_produto_carrinho (id_carrinho, id_produto)
);

STATUS_PEDIDO (
    id_status INT PRIMARY KEY,
    nome_status VARCHAR(50) NOT NULL,
    descricao VARCHAR(200),
    ordem_status INT NOT NULL -- Para ordenação lógica
);

PEDIDO (
    id_pedido INT PRIMARY KEY,
    numero_pedido VARCHAR(20) UNIQUE NOT NULL,
    id_cliente INT NOT NULL,
    id_endereco_entrega INT NOT NULL,
    id_status INT NOT NULL,
    data_pedido TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    valor_produtos DECIMAL(10,2) NOT NULL,
    valor_frete DECIMAL(10,2) DEFAULT 0,
    valor_desconto DECIMAL(10,2) DEFAULT 0,
    valor_total DECIMAL(10,2) NOT NULL,
    observacoes TEXT,
    FOREIGN KEY (id_cliente) REFERENCES CLIENTE(id_cliente),
    FOREIGN KEY (id_endereco_entrega) REFERENCES ENDERECO(id_endereco),
    FOREIGN KEY (id_status) REFERENCES STATUS_PEDIDO(id_status)
);

ITEM_PEDIDO (
    id_item_pedido INT PRIMARY KEY,
    id_pedido INT NOT NULL,
    id_produto INT NOT NULL,
    quantidade INT NOT NULL,
    preco_unitario DECIMAL(10,2) NOT NULL,
    preco_total DECIMAL(10,2) NOT NULL,
    FOREIGN KEY (id_pedido) REFERENCES PEDIDO(id_pedido),
    FOREIGN KEY (id_produto) REFERENCES PRODUTO(id_produto)
);
```

#### Entidades de Avaliações e Cupons
```sql
AVALIACAO (
    id_avaliacao INT PRIMARY KEY,
    id_produto INT NOT NULL,
    id_cliente INT NOT NULL,
    nota INT NOT NULL CHECK (nota BETWEEN 1 AND 5),
    titulo VARCHAR(100),
    comentario TEXT,
    data_avaliacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    verificada BOOLEAN DEFAULT FALSE, -- Cliente realmente comprou
    votos_uteis INT DEFAULT 0,
    FOREIGN KEY (id_produto) REFERENCES PRODUTO(id_produto),
    FOREIGN KEY (id_cliente) REFERENCES CLIENTE(id_cliente),
    UNIQUE KEY unique_cliente_produto (id_cliente, id_produto)
);

CUPOM_DESCONTO (
    id_cupom INT PRIMARY KEY,
    codigo_cupom VARCHAR(20) UNIQUE NOT NULL,
    descricao VARCHAR(100),
    tipo_desconto ENUM('PERCENTUAL', 'VALOR_FIXO'),
    valor_desconto DECIMAL(10,2) NOT NULL,
    valor_minimo_pedido DECIMAL(10,2) DEFAULT 0,
    data_validade_inicio DATE,
    data_validade_fim DATE,
    limite_uso_total INT DEFAULT NULL, -- NULL = ilimitado
    limite_uso_cliente INT DEFAULT 1,
    usado_total INT DEFAULT 0,
    ativo BOOLEAN DEFAULT TRUE
);

CUPOM_UTILIZADO (
    id_cupom_utilizado INT PRIMARY KEY,
    id_cupom INT NOT NULL,
    id_pedido INT NOT NULL,
    id_cliente INT NOT NULL,
    valor_desconto_aplicado DECIMAL(10,2) NOT NULL,
    data_utilizacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (id_cupom) REFERENCES CUPOM_DESCONTO(id_cupom),
    FOREIGN KEY (id_pedido) REFERENCES PEDIDO(id_pedido),
    FOREIGN KEY (id_cliente) REFERENCES CLIENTE(id_cliente)
);
```

---

## 🔍 Etapa 4: Análise de Normalização

### Verificação da 1FN
✅ **Conforme**: Todos os atributos contêm valores atômicos
- Endereço dividido em campos específicos
- Dimensões armazenadas como string formatada (poderia ser melhorado)
- Sem grupos repetitivos

### Verificação da 2FN
✅ **Conforme**: Não há dependências parciais
- Todas as chaves são simples ou as dependências são totais
- Em `ITEM_PEDIDO`, `preco_unitario` depende de `id_produto` no momento do pedido
- Em `ITEM_CARRINHO`, `preco_unitario` captura o preço no momento da adição

### Verificação da 3FN
✅ **Conforme**: Não há dependências transitivas
- `nome_categoria` não está em `PRODUTO` (está em tabela separada)
- `nome_cliente` não está em `PEDIDO` (referenciado via FK)
- Dados de endereço não duplicados em `PEDIDO`

---

## 🎯 Etapa 5: Definição de Constraints

### Integridade Referencial
```sql
-- Políticas de exclusão/atualização
ALTER TABLE ENDERECO ADD CONSTRAINT fk_endereco_cliente
    FOREIGN KEY (id_cliente) REFERENCES CLIENTE(id_cliente)
    ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE ITEM_PEDIDO ADD CONSTRAINT fk_item_pedido_produto
    FOREIGN KEY (id_produto) REFERENCES PRODUTO(id_produto)
    ON DELETE RESTRICT ON UPDATE CASCADE;

ALTER TABLE AVALIACAO ADD CONSTRAINT fk_avaliacao_produto
    FOREIGN KEY (id_produto) REFERENCES PRODUTO(id_produto)
    ON DELETE CASCADE ON UPDATE CASCADE;
```

### Regras de Negócio via Constraints
```sql
-- Cliente deve ter pelo menos um endereço padrão
CREATE TRIGGER trg_endereco_padrao 
AFTER INSERT ON ENDERECO
FOR EACH ROW
BEGIN
    IF (SELECT COUNT(*) FROM ENDERECO WHERE id_cliente = NEW.id_cliente) = 1 THEN
        UPDATE ENDERECO SET padrao = TRUE WHERE id_endereco = NEW.id_endereco;
    END IF;
END;

-- Validação de CPF/CNPJ por tipo de pessoa
ALTER TABLE CLIENTE ADD CONSTRAINT chk_cpf_cnpj
    CHECK (
        (tipo_pessoa = 'F' AND LENGTH(cpf_cnpj) = 11) OR
        (tipo_pessoa = 'J' AND LENGTH(cpf_cnpj) = 14)
    );

-- Quantidade no carrinho deve ser positiva
ALTER TABLE ITEM_CARRINHO ADD CONSTRAINT chk_quantidade_positiva
    CHECK (quantidade > 0);
```

---

## 📈 Etapa 6: Otimização e Índices

### Índices para Performance
```sql
-- Busca de produtos por categoria
CREATE INDEX idx_produto_categoria ON PRODUTO(id_categoria, ativo);

-- Busca de produtos por nome/marca
CREATE FULLTEXT INDEX idx_produto_busca ON PRODUTO(nome_produto, descricao_curta, marca);

-- Histórico de pedidos por cliente
CREATE INDEX idx_pedido_cliente_data ON PEDIDO(id_cliente, data_pedido DESC);

-- Avaliações por produto
CREATE INDEX idx_avaliacao_produto ON AVALIACAO(id_produto, verificada, nota);

-- Controle de estoque
CREATE INDEX idx_estoque_baixo ON ESTOQUE(quantidade_disponivel, estoque_minimo);
```

### Consultas de Exemplo Otimizadas
```sql
-- Buscar produtos em promoção com estoque
SELECT p.nome_produto, p.preco_atual, e.quantidade_disponivel
FROM PRODUTO p
INNER JOIN ESTOQUE e ON p.id_produto = e.id_produto
WHERE p.ativo = TRUE 
  AND e.quantidade_disponivel > 0
  AND p.preco_atual < (SELECT AVG(preco_atual) FROM PRODUTO WHERE id_categoria = p.id_categoria)
ORDER BY p.preco_atual;

-- Relatório de vendas por categoria
SELECT c.nome_categoria, 
       COUNT(ip.id_item_pedido) as total_itens_vendidos,
       SUM(ip.preco_total) as receita_total
FROM CATEGORIA c
INNER JOIN PRODUTO p ON c.id_categoria = p.id_categoria
INNER JOIN ITEM_PEDIDO ip ON p.id_produto = ip.id_produto
INNER JOIN PEDIDO ped ON ip.id_pedido = ped.id_pedido
WHERE ped.data_pedido >= DATE_SUB(NOW(), INTERVAL 30 DAY)
GROUP BY c.id_categoria, c.nome_categoria
ORDER BY receita_total DESC;
```

---

## 🎓 Lições Aprendidas

### ✅ Boas Práticas Aplicadas
1. **Normalização adequada** sem excesso
2. **Constraints de integridade** bem definidas
3. **Nomenclatura consistente** e clara
4. **Índices estratégicos** para consultas frequentes
5. **Campos de auditoria** (data_criacao, data_alteracao)
6. **Flexibilidade** para crescimento futuro

### 🔄 Pontos de Melhoria Futura
1. **Particionamento** de tabelas grandes (PEDIDO, ITEM_PEDIDO)
2. **Arquivamento** de dados históricos antigos
3. **Cache** de consultas frequentes
4. **Monitoramento** de performance de consultas
5. **Versionamento** de preços de produtos

### 📊 Métricas de Validação
- **Tempo de resposta** de consultas < 200ms
- **Integridade** dos dados mantida
- **Escalabilidade** para milhões de produtos
- **Manutenibilidade** do código SQL
- **Facilidade** de adicionar novos recursos

---

## 🚀 Próximos Passos

1. **Implementação física** no SGBD escolhido
2. **Testes de carga** com dados realistas
3. **Monitoramento** de performance
4. **Ajustes** baseados no uso real
5. **Documentação** para a equipe de desenvolvimento

Este estudo de caso demonstra um processo completo de modelagem, desde a análise de requisitos até a implementação física, seguindo todas as boas práticas de modelagem de dados.