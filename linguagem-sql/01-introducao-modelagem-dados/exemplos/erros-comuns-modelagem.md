# Erros Comuns na Modelagem de Dados
## Guia Prático com Soluções

### 🚫 Erro #1: Violação da Primeira Forma Normal

#### ❌ **Problema: Valores Múltiplos em uma Coluna**
```sql
-- ERRADO: Múltiplos telefones em uma string
CLIENTE (
    id_cliente INT,
    nome VARCHAR(100),
    telefones VARCHAR(200)  -- "11999887766,11988776655,21987654321"
);
```

#### ✅ **Solução: Tabela Separada**
```sql
-- CORRETO: Tabela normalizada
CLIENTE (
    id_cliente INT PRIMARY KEY,
    nome VARCHAR(100)
);

TELEFONE_CLIENTE (
    id_telefone INT PRIMARY KEY,
    id_cliente INT,
    tipo_telefone ENUM('RESIDENCIAL', 'COMERCIAL', 'CELULAR'),
    numero VARCHAR(15),
    FOREIGN KEY (id_cliente) REFERENCES CLIENTE(id_cliente)
);
```

---

### 🚫 Erro #2: Dependência Parcial (Violação da 2FN)

#### ❌ **Problema: Atributos que Dependem Apenas de Parte da Chave**
```sql
-- ERRADO: nome_produto depende apenas de id_produto
ITEM_PEDIDO (
    id_pedido INT,
    id_produto INT,
    nome_produto VARCHAR(100),  -- Depende só de id_produto
    preco_produto DECIMAL(10,2), -- Depende só de id_produto
    quantidade INT,
    PRIMARY KEY (id_pedido, id_produto)
);
```

#### ✅ **Solução: Separar as Entidades**
```sql
-- CORRETO: Informações do produto em tabela separada
PRODUTO (
    id_produto INT PRIMARY KEY,
    nome_produto VARCHAR(100),
    preco_atual DECIMAL(10,2)
);

ITEM_PEDIDO (
    id_pedido INT,
    id_produto INT,
    quantidade INT,
    preco_praticado DECIMAL(10,2), -- Preço no momento do pedido
    PRIMARY KEY (id_pedido, id_produto),
    FOREIGN KEY (id_produto) REFERENCES PRODUTO(id_produto)
);
```

---

### 🚫 Erro #3: Dependência Transitiva (Violação da 3FN)

#### ❌ **Problema: Atributo Depende de Outro Atributo Não-Chave**
```sql
-- ERRADO: nome_categoria depende de id_categoria, não de id_produto
PRODUTO (
    id_produto INT PRIMARY KEY,
    nome_produto VARCHAR(100),
    id_categoria INT,
    nome_categoria VARCHAR(50)  -- Dependência transitiva!
);
```

#### ✅ **Solução: Normalizar Completamente**
```sql
-- CORRETO: Categoria em tabela separada
CATEGORIA (
    id_categoria INT PRIMARY KEY,
    nome_categoria VARCHAR(50)
);

PRODUTO (
    id_produto INT PRIMARY KEY,
    nome_produto VARCHAR(100),
    id_categoria INT,
    FOREIGN KEY (id_categoria) REFERENCES CATEGORIA(id_categoria)
);
```

---

### 🚫 Erro #4: Uso Inadequado de Campos Genéricos

#### ❌ **Problema: Tabela "Pau para Toda Obra"**
```sql
-- ERRADO: Significado dos campos varia conforme o tipo
ENTIDADE_GENERICA (
    id INT PRIMARY KEY,
    tipo ENUM('CLIENTE', 'FORNECEDOR', 'FUNCIONARIO'),
    campo1 VARCHAR(100),  -- Nome? Razão Social? Cargo?
    campo2 VARCHAR(100),  -- CPF? CNPJ? Matrícula?
    campo3 DATE,          -- Nascimento? Fundação? Admissão?
    campo4 VARCHAR(200)   -- Endereço? Observações? Departamento?
);
```

#### ✅ **Solução: Tabelas Específicas**
```sql
-- CORRETO: Entidades específicas com significado claro
CLIENTE (
    id_cliente INT PRIMARY KEY,
    nome VARCHAR(100),
    cpf CHAR(11),
    data_nascimento DATE,
    endereco VARCHAR(200)
);

FORNECEDOR (
    id_fornecedor INT PRIMARY KEY,
    razao_social VARCHAR(100),
    cnpj CHAR(14),
    data_fundacao DATE,
    endereco_comercial VARCHAR(200)
);

FUNCIONARIO (
    id_funcionario INT PRIMARY KEY,
    nome VARCHAR(100),
    matricula VARCHAR(20),
    data_admissao DATE,
    departamento VARCHAR(100)
);
```

---

### 🚫 Erro #5: Relacionamentos Muitos-para-Muitos Sem Tabela Associativa

#### ❌ **Problema: Tentar Resolver N:M com String**
```sql
-- ERRADO: Lista de IDs em string
PROJETO (
    id_projeto INT PRIMARY KEY,
    nome_projeto VARCHAR(100),
    funcionarios_ids VARCHAR(500)  -- "1,3,7,15,22" - Muito ruim!
);
```

#### ✅ **Solução: Tabela Associativa Adequada**
```sql
-- CORRETO: Tabela associativa com informações adicionais
PROJETO (
    id_projeto INT PRIMARY KEY,
    nome_projeto VARCHAR(100)
);

FUNCIONARIO (
    id_funcionario INT PRIMARY KEY,
    nome VARCHAR(100)
);

PROJETO_FUNCIONARIO (
    id_projeto INT,
    id_funcionario INT,
    data_inicio DATE,
    data_fim DATE,
    papel VARCHAR(50), -- 'COORDENADOR', 'DESENVOLVEDOR', 'ANALISTA'
    horas_alocadas INT,
    PRIMARY KEY (id_projeto, id_funcionario),
    FOREIGN KEY (id_projeto) REFERENCES PROJETO(id_projeto),
    FOREIGN KEY (id_funcionario) REFERENCES FUNCIONARIO(id_funcionario)
);
```

---

### 🚫 Erro #6: Não Considerar Dados Históricos

#### ❌ **Problema: Perder Informações Históricas**
```sql
-- ERRADO: Ao alterar preço, perde-se histórico
PRODUTO (
    id_produto INT PRIMARY KEY,
    nome VARCHAR(100),
    preco DECIMAL(10,2)  -- E se o preço mudar? Pedidos antigos ficam incorretos!
);

ITEM_PEDIDO (
    id_pedido INT,
    id_produto INT,
    quantidade INT
    -- Sem campo preco_praticado!
);
```

#### ✅ **Solução: Capturar Dados no Momento da Transação**
```sql
-- CORRETO: Histórico preservado
PRODUTO (
    id_produto INT PRIMARY KEY,
    nome VARCHAR(100),
    preco_atual DECIMAL(10,2)
);

HISTORICO_PRECO (
    id_historico INT PRIMARY KEY,
    id_produto INT,
    preco DECIMAL(10,2),
    data_inicio DATE,
    data_fim DATE,
    FOREIGN KEY (id_produto) REFERENCES PRODUTO(id_produto)
);

ITEM_PEDIDO (
    id_pedido INT,
    id_produto INT,
    quantidade INT,
    preco_praticado DECIMAL(10,2), -- Preço no momento do pedido
    nome_produto_snapshot VARCHAR(100) -- Nome no momento do pedido
);
```

---

### 🚫 Erro #7: Chaves Primárias Inadequadas

#### ❌ **Problema: Usar Dados de Negócio como Chave Primária**
```sql
-- ERRADO: CPF como chave primária
CLIENTE (
    cpf CHAR(11) PRIMARY KEY,  -- E se o CPF estiver errado?
    nome VARCHAR(100),
    email VARCHAR(100)
);
```

#### ✅ **Solução: Chaves Surrogate + Chaves Naturais Únicas**
```sql
-- CORRETO: Chave surrogate + constraint única
CLIENTE (
    id_cliente INT AUTO_INCREMENT PRIMARY KEY, -- Chave surrogate
    cpf CHAR(11) UNIQUE,                       -- Chave natural única
    nome VARCHAR(100),
    email VARCHAR(100) UNIQUE
);
```

---

### 🚫 Erro #8: Não Definir Constraints Adequadas

#### ❌ **Problema: Permitir Dados Inconsistentes**
```sql
-- ERRADO: Sem validações
PEDIDO (
    id_pedido INT PRIMARY KEY,
    data_pedido DATE,
    valor_total DECIMAL(10,2),
    status VARCHAR(20)  -- Qualquer string aceita!
);
```

#### ✅ **Solução: Constraints e Validações Adequadas**
```sql
-- CORRETO: Com validações apropriadas
STATUS_PEDIDO (
    id_status INT PRIMARY KEY,
    nome_status VARCHAR(50) NOT NULL,
    descricao VARCHAR(200)
);

PEDIDO (
    id_pedido INT PRIMARY KEY,
    data_pedido DATE NOT NULL,
    valor_total DECIMAL(10,2) NOT NULL CHECK (valor_total >= 0),
    id_status INT NOT NULL,
    data_entrega_prevista DATE,
    
    FOREIGN KEY (id_status) REFERENCES STATUS_PEDIDO(id_status),
    CHECK (data_entrega_prevista >= data_pedido)
);
```

---

### 🚫 Erro #9: Desnormalização Prematura

#### ❌ **Problema: "Otimizar" Sem Necessidade**
```sql
-- ERRADO: Duplicar dados para "performance"
PEDIDO (
    id_pedido INT PRIMARY KEY,
    id_cliente INT,
    nome_cliente VARCHAR(100),    -- Duplicado!
    email_cliente VARCHAR(100),   -- Duplicado!
    telefone_cliente VARCHAR(15), -- Duplicado!
    endereco_cliente TEXT         -- Duplicado!
);
```

#### ✅ **Solução: Normalizar Primeiro, Desnormalizar Depois (Se Necessário)**
```sql
-- CORRETO: Normalizado corretamente
CLIENTE (
    id_cliente INT PRIMARY KEY,
    nome VARCHAR(100),
    email VARCHAR(100),
    telefone VARCHAR(15)
);

ENDERECO (
    id_endereco INT PRIMARY KEY,
    id_cliente INT,
    logradouro VARCHAR(200),
    cidade VARCHAR(100),
    FOREIGN KEY (id_cliente) REFERENCES CLIENTE(id_cliente)
);

PEDIDO (
    id_pedido INT PRIMARY KEY,
    id_cliente INT,
    id_endereco_entrega INT,
    data_pedido TIMESTAMP,
    FOREIGN KEY (id_cliente) REFERENCES CLIENTE(id_cliente),
    FOREIGN KEY (id_endereco_entrega) REFERENCES ENDERECO(id_endereco)
);

-- Se realmente precisar de performance, criar VIEW ou usar cache
CREATE VIEW pedido_detalhado AS
SELECT p.*, c.nome as nome_cliente, c.email as email_cliente
FROM PEDIDO p
INNER JOIN CLIENTE c ON p.id_cliente = c.id_cliente;
```

---

### 🚫 Erro #10: Ignorar Casos Extremos

#### ❌ **Problema: Não Considerar Situações Especiais**
```sql
-- ERRADO: Não considera casos especiais
FUNCIONARIO (
    id_funcionario INT PRIMARY KEY,
    nome VARCHAR(100),
    id_supervisor INT,  -- E se for o CEO? E se estiver de férias?
    FOREIGN KEY (id_supervisor) REFERENCES FUNCIONARIO(id_funcionario)
);
```

#### ✅ **Solução: Considerar Todos os Cenários**
```sql
-- CORRETO: Trata casos especiais
FUNCIONARIO (
    id_funcionario INT PRIMARY KEY,
    nome VARCHAR(100),
    id_supervisor INT NULL,  -- NULL permitido para CEO
    data_inicio DATE,
    data_fim DATE NULL,      -- NULL = ainda ativo
    status ENUM('ATIVO', 'FERIAS', 'LICENCA', 'DEMITIDO') DEFAULT 'ATIVO',
    FOREIGN KEY (id_supervisor) REFERENCES FUNCIONARIO(id_funcionario)
);

-- Tabela para supervisão temporária
SUPERVISAO_TEMPORARIA (
    id_supervisao INT PRIMARY KEY,
    id_funcionario INT,
    id_supervisor_substituto INT,
    data_inicio DATE,
    data_fim DATE,
    motivo VARCHAR(200),
    FOREIGN KEY (id_funcionario) REFERENCES FUNCIONARIO(id_funcionario),
    FOREIGN KEY (id_supervisor_substituto) REFERENCES FUNCIONARIO(id_funcionario)
);
```

---

## 🎯 Checklist Anti-Erros

### ✅ **Antes de Finalizar seu Modelo**

**Normalização**:
- [ ] Todos os campos contêm valores atômicos?
- [ ] Não há dependências parciais?
- [ ] Não há dependências transitivas?
- [ ] Cada fato está armazenado em apenas um lugar?

**Integridade**:
- [ ] Todas as chaves estrangeiras estão definidas?
- [ ] Constraints de domínio estão aplicadas?
- [ ] Valores únicos estão protegidos?
- [ ] Casos especiais foram considerados?

**Flexibilidade**:
- [ ] O modelo suporta crescimento?
- [ ] Mudanças futuras foram antecipadas?
- [ ] Dados históricos são preservados?
- [ ] Performance foi considerada adequadamente?

**Clareza**:
- [ ] Nomenclatura é consistente e clara?
- [ ] Documentação está adequada?
- [ ] Relacionamentos estão bem definidos?
- [ ] Regras de negócio estão representadas?

---

## 🏆 Resumo das Boas Práticas

1. **Normalize primeiro**, desnormalize depois (se necessário)
2. **Use chaves surrogate** para estabilidade
3. **Preserve dados históricos** quando relevante
4. **Defina constraints adequadas** para integridade
5. **Considere casos extremos** e situações especiais
6. **Mantenha nomenclatura consistente** e clara
7. **Documente decisões** de design importantes
8. **Teste com dados reais** antes de finalizar
9. **Revise com stakeholders** para validar requisitos
10. **Monitore performance** após implementação

**Lembre-se**: Um bom modelo de dados é a base de um sistema confiável e escalável!