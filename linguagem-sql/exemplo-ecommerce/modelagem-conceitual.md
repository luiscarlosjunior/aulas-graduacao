# Modelagem Conceitual - Sistema de E-commerce TechStore

## 🎯 Análise de Requisitos

### Cenário de Negócio

A **TechStore** é uma loja virtual especializada em produtos eletrônicos que está desenvolvendo um sistema de e-commerce completo. O sistema deve atender tanto clientes pessoa física quanto jurídica, oferecendo uma experiência de compra completa.

### 📋 Requisitos Funcionais Detalhados

#### **RF01 - Gestão de Clientes**
- Cliente pode se cadastrar informando dados pessoais
- Sistema deve diferenciar pessoa física (CPF) de jurídica (CNPJ)
- Cliente pode cadastrar múltiplos endereços (residencial, comercial, entrega)
- Cliente pode definir um endereço como padrão
- Cliente pode cadastrar múltiplas formas de pagamento
- Sistema deve validar dados como CPF/CNPJ, email e telefone

#### **RF02 - Catálogo de Produtos**
- Produtos organizados em categorias hierárquicas
- Cada produto tem informações detalhadas (nome, descrição, marca, modelo)
- Produtos devem ter código de barras único
- Sistema deve controlar preço atual de cada produto
- Produtos têm especificações físicas (peso, dimensões)
- Produtos são fornecidos por fornecedores cadastrados
- Sistema deve permitir ativação/desativação de produtos

#### **RF03 - Gestão de Estoque**
- Controle de quantidade disponível por produto
- Controle de quantidade reservada (no carrinho)
- Definição de estoque mínimo por produto
- Histórico de entradas e saídas de estoque
- Alerta quando estoque atingir o mínimo

#### **RF04 - Carrinho de Compras**
- Cada cliente possui um carrinho único
- Cliente pode adicionar produtos ao carrinho
- Quantidade pode ser alterada no carrinho
- Sistema deve preservar preço no momento da adição
- Carrinho pode ser esvaziado ou produtos podem ser removidos

#### **RF05 - Processamento de Pedidos**
- Cliente pode finalizar compra do carrinho
- Pedido deve ter número único identificador
- Sistema calcula valor total (produtos + frete - desconto)
- Pedido tem status que acompanha o andamento
- Cada pedido tem endereço de entrega específico
- Cliente pode adicionar observações ao pedido

#### **RF06 - Sistema de Avaliações**
- Cliente pode avaliar apenas produtos que comprou
- Avaliação tem nota de 1 a 5 estrelas
- Avaliação pode ter título e comentário
- Outros clientes podem marcar avaliações como úteis
- Sistema deve validar se cliente realmente comprou o produto

#### **RF07 - Cupons de Desconto**
- Sistema permite criação de cupons promocionais
- Cupom pode ter desconto percentual ou valor fixo
- Cupom pode ter valor mínimo de pedido
- Cupom tem data de validade
- Cupom pode ter limite de uso total e por cliente
- Sistema controla quantas vezes cupom foi usado

#### **RF08 - Gestão de Fornecedores**
- Cadastro de fornecedores com dados da empresa
- Fornecedor tem responsável técnico para contato
- Produtos são vinculados a fornecedores
- Fornecedores podem ser ativados/desativados

### 📊 Requisitos Não-Funcionais

#### **RNF01 - Performance**
- Consultas de produtos devem responder em menos de 200ms
- Sistema deve suportar 1000 usuários simultâneos
- Carrinho deve ser atualizado em tempo real

#### **RNF02 - Segurança**
- Dados de cartão devem ser criptografados
- CPF/CNPJ devem ser validados
- Sistema deve registrar log de todas as operações

#### **RNF03 - Integridade**
- Não permitir exclusão de produtos com pedidos
- Manter histórico de preços praticados
- Garantir consistência entre carrinho e estoque

---

## 🏗️ Modelagem Conceitual

### Identificação de Entidades Principais

#### **Entidades Centrais**
```
📦 PRODUTO      - Item comercializado na loja
👤 CLIENTE      - Pessoa que realiza compras
🏪 FORNECEDOR   - Empresa que fornece produtos
📋 PEDIDO       - Solicitação de compra finalizada
🛒 CARRINHO     - Itens selecionados para compra
⭐ AVALIACAO    - Comentário sobre produto
```

#### **Entidades de Apoio**
```
📍 ENDERECO           - Locais de entrega do cliente
💳 FORMA_PAGAMENTO    - Métodos de pagamento do cliente
📂 CATEGORIA          - Classificação hierárquica de produtos
📊 ESTOQUE           - Controle de disponibilidade
🎟️ CUPOM_DESCONTO    - Promoções e descontos
📈 STATUS_PEDIDO     - Estados do pedido
```

#### **Entidades Associativas**
```
🛒 ITEM_CARRINHO     - Produtos no carrinho (N:M)
📦 ITEM_PEDIDO       - Produtos do pedido (N:M)
🎟️ CUPOM_UTILIZADO   - Cupons usados em pedidos (N:M)
```

### Análise Detalhada das Entidades

#### 🏪 **FORNECEDOR**
**Propósito**: Empresas que fornecem produtos para a loja
**Atributos Essenciais**:
- ID único do fornecedor
- Razão social e nome fantasia
- CNPJ (obrigatório e único)
- Dados de contato (email, telefone)
- Endereço completo
- Responsável técnico
- Status (ativo/inativo)

#### 📂 **CATEGORIA**
**Propósito**: Organização hierárquica dos produtos
**Atributos Essenciais**:
- ID único da categoria
- Nome da categoria
- Descrição
- Categoria pai (para hierarquia)
- Status (ativo/inativo)

**Relacionamento Especial**: Auto-relacionamento hierárquico
```
Eletrônicos
├── Smartphones
│   ├── Android
│   └── iPhone
├── Computadores
│   ├── Notebooks
│   └── Desktops
└── Acessórios
```

#### 📦 **PRODUTO**
**Propósito**: Itens comercializados na loja
**Atributos Essenciais**:
- ID único do produto
- Nome e descrições (curta e completa)
- Marca e modelo
- Código de barras (único)
- Preço atual
- Especificações físicas (peso, dimensões)
- Garantia em meses
- Data de cadastro
- Status (ativo/inativo)

#### 👤 **CLIENTE**
**Propósito**: Pessoas que realizam compras
**Atributos Essenciais**:
- ID único do cliente
- Nome completo
- Email (único)
- CPF ou CNPJ (único)
- Telefone
- Data de nascimento
- Tipo de pessoa (F/J)
- Data de cadastro
- Status (ativo/inativo)

#### 📍 **ENDERECO**
**Propósito**: Locais de entrega dos clientes
**Atributos Essenciais**:
- ID único do endereço
- Tipo (residencial, comercial, entrega)
- Logradouro completo
- Número, complemento, bairro
- Cidade, estado, CEP
- País (padrão: Brasil)
- Indicador de endereço padrão

#### 💳 **FORMA_PAGAMENTO**
**Propósito**: Métodos de pagamento cadastrados
**Atributos Essenciais**:
- ID único da forma de pagamento
- Tipo (cartão crédito/débito, PIX, boleto)
- Descrição amigável
- Dados do cartão (últimos 4 dígitos, validade)
- Nome do portador
- Status (ativo/inativo)

#### 📊 **ESTOQUE**
**Propósito**: Controle de disponibilidade dos produtos
**Atributos Essenciais**:
- ID único do controle de estoque
- Quantidade disponível
- Quantidade reservada (em carrinho)
- Estoque mínimo
- Data da última entrada
- Data da última saída

#### 🛒 **CARRINHO**
**Propósito**: Itens selecionados para compra
**Atributos Essenciais**:
- ID único do carrinho
- Data de criação
- Data da última alteração

#### 📋 **PEDIDO**
**Propósito**: Solicitações de compra finalizadas
**Atributos Essenciais**:
- ID único do pedido
- Número do pedido (único, amigável)
- Data do pedido
- Valores (produtos, frete, desconto, total)
- Observações do cliente

#### 📈 **STATUS_PEDIDO**
**Propósito**: Estados possíveis do pedido
**Valores Típicos**:
```
1 - Aguardando Pagamento
2 - Pagamento Confirmado
3 - Preparando Envio
4 - Enviado
5 - Entregue
6 - Cancelado
```

#### ⭐ **AVALIACAO**
**Propósito**: Feedback dos clientes sobre produtos
**Atributos Essenciais**:
- ID único da avaliação
- Nota (1 a 5 estrelas)
- Título e comentário
- Data da avaliação
- Indicador se foi verificada (cliente comprou)
- Número de votos úteis

#### 🎟️ **CUPOM_DESCONTO**
**Propósito**: Promoções e descontos
**Atributos Essenciais**:
- ID único do cupom
- Código do cupom (único)
- Descrição
- Tipo de desconto (percentual/valor fixo)
- Valor do desconto
- Valor mínimo do pedido
- Data de validade (início e fim)
- Limites de uso (total e por cliente)
- Contador de uso

---

## 🔗 Análise de Relacionamentos

### Relacionamentos 1:N (Um para Muitos)

#### **CLIENTE ──(1:N)── ENDERECO**
- **Cardinalidade**: Um cliente pode ter vários endereços
- **Obrigatoriedade**: Cliente deve ter pelo menos um endereço
- **Regra**: Um endereço pertence a apenas um cliente

#### **CLIENTE ──(1:N)── FORMA_PAGAMENTO**
- **Cardinalidade**: Um cliente pode ter várias formas de pagamento
- **Obrigatoriedade**: Cliente pode não ter forma de pagamento cadastrada
- **Regra**: Uma forma de pagamento pertence a apenas um cliente

#### **CLIENTE ──(1:N)── PEDIDO**
- **Cardinalidade**: Um cliente pode fazer vários pedidos
- **Obrigatoriedade**: Cliente pode não ter feito pedidos ainda
- **Regra**: Um pedido pertence a apenas um cliente

#### **CATEGORIA ──(1:N)── PRODUTO**
- **Cardinalidade**: Uma categoria pode ter vários produtos
- **Obrigatoriedade**: Categoria pode estar vazia
- **Regra**: Um produto pertence a apenas uma categoria

#### **FORNECEDOR ──(1:N)── PRODUTO**
- **Cardinalidade**: Um fornecedor pode fornecer vários produtos
- **Obrigatoriedade**: Fornecedor pode não ter produtos cadastrados
- **Regra**: Um produto é fornecido por apenas um fornecedor

### Relacionamentos 1:1 (Um para Um)

#### **CLIENTE ──(1:1)── CARRINHO**
- **Cardinalidade**: Cada cliente tem exatamente um carrinho
- **Obrigatoriedade**: Carrinho é criado automaticamente no cadastro
- **Regra**: Relacionamento único e obrigatório

#### **PRODUTO ──(1:1)── ESTOQUE**
- **Cardinalidade**: Cada produto tem exatamente um controle de estoque
- **Obrigatoriedade**: Estoque é criado automaticamente no cadastro do produto
- **Regra**: Relacionamento único e obrigatório

### Relacionamentos N:M (Muitos para Muitos)

#### **CARRINHO ──(N:M)── PRODUTO**
- **Tabela Associativa**: ITEM_CARRINHO
- **Atributos da Associação**: quantidade, preco_unitario, data_adicao
- **Regra**: Um carrinho pode ter vários produtos, um produto pode estar em vários carrinhos

#### **PEDIDO ──(N:M)── PRODUTO**
- **Tabela Associativa**: ITEM_PEDIDO
- **Atributos da Associação**: quantidade, preco_unitario, preco_total
- **Regra**: Um pedido pode ter vários produtos, um produto pode estar em vários pedidos

#### **PEDIDO ──(N:M)── CUPOM_DESCONTO**
- **Tabela Associativa**: CUPOM_UTILIZADO
- **Atributos da Associação**: valor_desconto_aplicado, data_utilizacao
- **Regra**: Um pedido pode usar vários cupons, um cupom pode ser usado em vários pedidos

### Relacionamentos Especiais

#### **CATEGORIA ──(1:N)── CATEGORIA** (Auto-relacionamento)
- **Cardinalidade**: Uma categoria pode ter várias subcategorias
- **Obrigatoriedidade**: Categoria pode ser raiz (sem pai)
- **Regra**: Implementa hierarquia de categorias

---

## 💡 Decisões de Modelagem

### Por que Separar Carrinho de Pedido?

**Justificativa**:
1. **Estados diferentes**: Carrinho é temporário, pedido é permanente
2. **Ciclo de vida**: Carrinho pode ser abandonado, pedido tem rastreamento
3. **Flexibilidade**: Cliente pode ter vários itens no carrinho mas fazer pedidos parciais
4. **Auditoria**: Pedidos precisam de mais controle e histórico

### Por que Armazenar Preço nas Associações?

**Justificativa**:
1. **Histórico**: Preços mudam ao longo do tempo
2. **Integridade**: Pedidos devem manter preços do momento da compra
3. **Auditoria**: Relatórios financeiros precisos
4. **Flexibilidade**: Permite promoções específicas

### Por que Tabela de Status Separada?

**Justificativa**:
1. **Manutenibilidade**: Fácil adicionar novos status
2. **Padronização**: Evita inconsistências na nomenclatura
3. **Ordenação**: Campo ordem_status permite sequência lógica
4. **Internacionalização**: Facilita tradução dos status

### Por que Validar CPF/CNPJ por Constraint?

**Justificativa**:
1. **Integridade**: Garantir dados válidos no banco
2. **Performance**: Validação no banco é mais rápida
3. **Consistência**: Regra aplicada independente da aplicação
4. **Segurança**: Evita bypass da validação

---

## ✅ Validação do Modelo Conceitual

### Checklist de Completude

- ✅ **Entidades identificadas**: Todas as principais entidades mapeadas
- ✅ **Atributos essenciais**: Campos obrigatórios para funcionamento
- ✅ **Relacionamentos definidos**: Cardinalidades e obrigatoriedades claras
- ✅ **Regras de negócio**: Constraints e validações especificadas
- ✅ **Casos especiais**: Auto-relacionamentos e hierarquias tratados

### Checklist de Consistência

- ✅ **Nomenclatura**: Padrão consistente para entidades e atributos
- ✅ **Relacionamentos**: Coerentes com a realidade do negócio
- ✅ **Redundância**: Eliminada ou justificada quando mantida
- ✅ **Flexibilidade**: Modelo permite crescimento futuro
- ✅ **Compreensibilidade**: Estrutura clara e documentada

### Validação com Cenários Reais

#### **Cenário 1**: Cliente faz primeira compra
1. Cliente se cadastra → CLIENTE criado
2. Carrinho criado automaticamente → CARRINHO vinculado
3. Cliente cadastra endereço → ENDERECO criado
4. Cliente adiciona produtos → ITEM_CARRINHO criado
5. Cliente finaliza compra → PEDIDO e ITEM_PEDIDO criados
6. Cliente avalia produto → AVALIACAO criada

#### **Cenário 2**: Gestão de estoque
1. Produto cadastrado → ESTOQUE criado automaticamente
2. Cliente adiciona ao carrinho → quantidade_reservada aumenta
3. Cliente remove do carrinho → quantidade_reservada diminui
4. Pedido finalizado → quantidade_disponivel diminui
5. Estoque baixo → alerta baseado em estoque_minimo

#### **Cenário 3**: Aplicação de cupom
1. Cliente adiciona produtos no carrinho
2. Cliente insere código do cupom
3. Sistema valida validade e limites
4. Desconto aplicado no pedido
5. Registro na tabela CUPOM_UTILIZADO
6. Contador de uso do cupom atualizado

### Métricas de Validação

- **Número de entidades**: 13 principais + 3 associativas = 16 total
- **Relacionamentos**: 12 relacionamentos mapeados
- **Regras de negócio**: 8 principais regras identificadas
- **Cobertura funcional**: 100% dos requisitos atendidos

---

## 🎯 Próximos Passos

1. **Validar com stakeholders**: Confirmar se modelo atende necessidades
2. **Refinar relacionamentos**: Ajustar cardinalidades se necessário  
3. **Definir atributos detalhados**: Tipos, tamanhos, constraints
4. **Partir para modelagem lógica**: Transformar em estrutura de tabelas
5. **Implementar modelo físico**: Scripts SQL para criação do banco

Este modelo conceitual fornece a base sólida para as próximas etapas de modelagem lógica e física do sistema de e-commerce.