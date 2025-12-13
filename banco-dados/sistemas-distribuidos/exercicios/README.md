# 📝 Exercícios - Sistemas Distribuídos em Banco de Dados

## Instruções Gerais

- Leia atentamente cada exercício antes de começar
- Consulte o material teórico do [README.md](../README.md) quando necessário
- Alguns exercícios são teóricos (análise e projeto)
- Outros são práticos (implementação SQL)
- Trabalhe os exercícios em ordem crescente de dificuldade

---

## 🟢 Exercícios Básicos (Conceitos Fundamentais)

### Exercício 1: Identificando Arquiteturas

**Contexto**: Você foi contratado para analisar sistemas de empresas reais e identificar qual arquitetura distribuída elas utilizam.

**Para cada sistema abaixo, identifique:**
1. Qual arquitetura: Cliente-Servidor, P2P ou Federada?
2. Justifique sua resposta
3. Identifique se é CP, AP ou CA (Teorema CAP)

**Sistemas:**

a) **Sistema Bancário do Banco do Brasil**
- Clientes acessam via aplicativo
- Dados de contas em múltiplos datacenters
- Transferências entre contas precisam ser consistentes
- Se há problema de rede, transação é recusada

b) **BitTorrent** (download de arquivos)
- Cada usuário baixa pedaços de diferentes pessoas
- Não há servidor central de dados
- Se alguns usuários saírem, outros continuam compartilhando

c) **Sistema de Prescrição Médica Nacional**
- Hospitais mantêm seus próprios bancos de dados
- Farmácias precisam consultar prescrições de diferentes hospitais
- Cada hospital tem autonomia sobre seus dados

---

### Exercício 2: Fragmentação Horizontal

**Contexto**: Você está projetando o banco de dados de uma rede social brasileira com 50 milhões de usuários.

**Tarefa**:
1. Projete uma estratégia de fragmentação horizontal para a tabela `usuarios`
2. Defina critérios de fragmentação (por região, por idade, por data de cadastro?)
3. Calcule quantos fragmentos serão necessários
4. Analise vantagens e desvantagens da sua escolha

**Tabela Original**:
```sql
CREATE TABLE usuarios (
    usuario_id NUMBER PRIMARY KEY,
    nome VARCHAR2(100),
    email VARCHAR2(100),
    data_nascimento DATE,
    estado VARCHAR2(2),
    cidade VARCHAR2(100),
    data_cadastro DATE,
    ultima_atividade TIMESTAMP
);
```

**Requisitos**:
- 70% dos usuários acessam de SP, RJ e MG
- 20% acessam de outros estados do Brasil
- 10% acessam do exterior
- Consultas típicas: "mostrar amigos próximos" (mesma cidade/estado)

---

### Exercício 3: Teorema CAP na Prática

**Contexto**: Para cada cenário abaixo, decida se deve priorizar CP (Consistency + Partition Tolerance) ou AP (Availability + Partition Tolerance).

**Cenários**:

a) **Sistema de Votação Eletrônica** (eleições presidenciais)
- Cada voto deve ser contado exatamente uma vez
- Preferível que sistema fique indisponível do que aceitar voto duplicado

**Pergunta**: CP ou AP? Por quê?

b) **Twitter/X**
- Milhões de tweets por segundo
- Mais importante que sistema esteja sempre disponível
- Aceitável que tweet demore alguns segundos para aparecer para todos

**Pergunta**: CP ou AP? Por quê?

c) **Sistema de Controle de Estoque de E-commerce**
- Importante não vender mais do que tem em estoque
- Mas também importante que clientes consigam comprar (disponibilidade)
- Como balancear?

**Pergunta**: CP, AP ou híbrido? Proponha solução.

---

## 🟡 Exercícios Intermediários (Implementação)

### Exercício 4: Implementando Fragmentação Horizontal

**Contexto**: Sistema de streaming de música (similar ao Spotify).

**Tarefa**: Implemente fragmentação horizontal para a tabela `reproducoes`:

```sql
CREATE TABLE reproducoes (
    reproducao_id NUMBER PRIMARY KEY,
    usuario_id NUMBER NOT NULL,
    musica_id NUMBER NOT NULL,
    data_hora TIMESTAMP DEFAULT SYSTIMESTAMP,
    duracao_segundos NUMBER,
    completa VARCHAR2(1) DEFAULT 'N', -- 'S' ou 'N'
    dispositivo VARCHAR2(20) -- 'mobile', 'web', 'desktop'
);
```

**Requisitos**:
1. Fragmente por **data** (um fragmento por mês)
2. Crie tabelas: `reproducoes_2024_01`, `reproducoes_2024_02`, etc.
3. Implemente procedure `inserir_reproducao` que roteia para fragmento correto
4. Implemente function `buscar_reproducoes_usuario` que busca em múltiplos fragmentos
5. Crie view unificada `v_todas_reproducoes`

**Entregáveis**:
- Script SQL com CREATE TABLE de todos os fragmentos
- Procedure de inserção
- Function de busca
- View unificada
- 10 INSERTs de teste em diferentes meses

---

### Exercício 5: Replicação Master-Slave

**Contexto**: Sistema de e-commerce com 1 master e 3 slaves.

**Tarefa**: Implemente um sistema de replicação assíncrona simulado:

1. **Tabela Principal** (no master):
```sql
CREATE TABLE produtos (
    produto_id NUMBER PRIMARY KEY,
    nome VARCHAR2(200),
    preco NUMBER(10,2),
    estoque NUMBER,
    ultima_atualizacao TIMESTAMP DEFAULT SYSTIMESTAMP
);
```

2. **Tabela de Log de Replicação**:
```sql
CREATE TABLE replication_queue (
    queue_id NUMBER PRIMARY KEY,
    tabela VARCHAR2(50),
    operacao VARCHAR2(10),
    registro_id NUMBER,
    dados_json CLOB, -- Simulação dos dados
    timestamp_criacao TIMESTAMP DEFAULT SYSTIMESTAMP,
    replicado_slave1 VARCHAR2(1) DEFAULT 'N',
    replicado_slave2 VARCHAR2(1) DEFAULT 'N',
    replicado_slave3 VARCHAR2(1) DEFAULT 'N'
);
```

3. **Implemente**:
   - Trigger que registra mudanças em `replication_queue`
   - Procedure `simular_replicacao_slave` que marca como replicado
   - Procedure `verificar_lag_replicacao` que mostra quantos registros pendentes

4. **Teste**:
   - Insira 5 produtos
   - Atualize 2 produtos
   - Delete 1 produto
   - Verifique quantas operações na fila
   - Simule replicação para slaves
   - Confirme que fila foi processada

---

### Exercício 6: Problema de Consistência Read-After-Write

**Contexto**: Cliente faz pedido e imediatamente tenta visualizar. Pedido ainda não foi replicado para slave.

**Cenário**:
```
T0: Cliente insere pedido (master)
T1: Sistema retorna "Pedido criado com sucesso"
T2: Cliente clica em "Meus Pedidos" (lê de slave)
T3: Pedido NÃO aparece (ainda não replicou)
```

**Tarefa**: Implemente solução para garantir **Read-Your-Writes Consistency**:

1. Crie tabela para rastrear escritas recentes por sessão:
```sql
CREATE TABLE session_writes (
    session_id VARCHAR2(100),
    timestamp_escrita TIMESTAMP,
    tabela VARCHAR2(50),
    registro_id NUMBER
);
```

2. Implemente function que decide de onde ler:
```sql
CREATE OR REPLACE FUNCTION determinar_fonte_leitura(
    p_session_id VARCHAR2,
    p_tabela VARCHAR2,
    p_tempo_max_segundos NUMBER DEFAULT 30
) RETURN VARCHAR2; -- Retorna 'MASTER' ou 'SLAVE'
```

3. Regra: Se usuário escreveu nos últimos 30 segundos, lê do MASTER. Caso contrário, lê de SLAVE.

4. Teste:
   - Simule inserção de pedido (registra em session_writes)
   - Imediatamente tenta ler (deve ir para master)
   - Espera 35 segundos
   - Tenta ler novamente (deve ir para slave)

---

## 🔴 Exercícios Avançados (Desafios)

### Exercício 7: Implementando 2PC (Two-Phase Commit)

**Contexto**: Transferência bancária entre dois bancos diferentes (dois bancos de dados diferentes).

**Cenário**:
```
Transferir R$ 1000 de Conta A (Banco Itaú) para Conta B (Banco Bradesco)
```

**Tarefa**: Implemente um protocolo 2PC simplificado:

1. **Crie tabelas**:
```sql
-- Banco Itaú
CREATE TABLE contas_itau (
    conta_id VARCHAR2(20) PRIMARY KEY,
    saldo NUMBER(10,2),
    bloqueada VARCHAR2(1) DEFAULT 'N'
);

-- Banco Bradesco
CREATE TABLE contas_bradesco (
    conta_id VARCHAR2(20) PRIMARY KEY,
    saldo NUMBER(10,2),
    bloqueada VARCHAR2(1) DEFAULT 'N'
);

-- Coordenador (log de transações distribuídas)
CREATE TABLE transacoes_distribuidas (
    transacao_id VARCHAR2(50) PRIMARY KEY,
    status VARCHAR2(20), -- PREPARANDO, AGUARDANDO, COMMITADO, ABORTADO
    timestamp_inicio TIMESTAMP,
    banco_origem VARCHAR2(50),
    banco_destino VARCHAR2(50),
    valor NUMBER(10,2)
);
```

2. **Implemente Fase 1 (PREPARE)**:
```sql
CREATE OR REPLACE PROCEDURE fase1_prepare_transferencia(
    p_transacao_id VARCHAR2,
    p_conta_origem VARCHAR2, -- Conta no Itaú
    p_conta_destino VARCHAR2, -- Conta no Bradesco
    p_valor NUMBER,
    p_pode_commitar OUT VARCHAR2 -- 'S' ou 'N'
);
```

- Verifica se conta origem tem saldo suficiente
- Bloqueia contas envolvidas
- Registra status 'PREPARANDO'
- Retorna 'S' se ambos os bancos podem commitar

3. **Implemente Fase 2 (COMMIT ou ABORT)**:
```sql
CREATE OR REPLACE PROCEDURE fase2_commit_ou_abort(
    p_transacao_id VARCHAR2,
    p_decisao VARCHAR2 -- 'COMMIT' ou 'ABORT'
);
```

- Se COMMIT: Executa débito e crédito, desbloqueia contas
- Se ABORT: Apenas desbloqueia contas, sem alterar saldos

4. **Teste completo**:
   - Cenário 1: Transferência com sucesso (saldo suficiente)
   - Cenário 2: Transferência abortada (saldo insuficiente)
   - Cenário 3: Simule falha na Fase 1 (banco destino offline)

---

### Exercício 8: Detectando e Resolvendo Deadlock Distribuído

**Contexto**: Dois clientes tentam reservar voo + hotel simultaneamente, causando deadlock.

**Cenário**:
```
Cliente A: Bloqueia Voo 123 (Servidor SP), tenta bloquear Hotel 456 (Servidor RJ)
Cliente B: Bloqueia Hotel 456 (Servidor RJ), tenta bloquear Voo 123 (Servidor SP)
→ DEADLOCK!
```

**Tarefa**: Implemente sistema de detecção e resolução de deadlock:

1. **Crie tabelas**:
```sql
CREATE TABLE recursos (
    recurso_id VARCHAR2(50) PRIMARY KEY,
    tipo VARCHAR2(20), -- 'VOO' ou 'HOTEL'
    bloqueado_por_transacao VARCHAR2(50),
    timestamp_bloqueio TIMESTAMP
);

CREATE TABLE wait_for_graph (
    transacao_origem VARCHAR2(50),
    transacao_destino VARCHAR2(50),
    recurso_aguardado VARCHAR2(50),
    timestamp_inicio_espera TIMESTAMP
);
```

2. **Implemente função de detecção de ciclos**:
```sql
CREATE OR REPLACE FUNCTION detectar_deadlock(
    p_transacao_id VARCHAR2
) RETURN VARCHAR2; -- Retorna 'DEADLOCK' ou 'OK'
```

Use algoritmo de busca de ciclos no grafo wait-for.

3. **Implemente resolução**:
   - Quando deadlock detectado, aborta transação mais nova
   - Libera recursos bloqueados por ela
   - Permite que outra transação prossiga

4. **Teste**:
   - Simule duas transações causando deadlock
   - Verifique detecção
   - Confirme que uma foi abortada e outra completou

---

### Exercício 9: Sistema de Replicação Multi-Master

**Contexto**: Google Docs - múltiplos usuários editam documento simultaneamente.

**Cenário**:
```
T0: Documento tem texto: "Olá"
T1: Usuário A (São Paulo) adiciona: "Olá mundo"
T2: Usuário B (Rio de Janeiro) adiciona: "Olá pessoal" (ao mesmo tempo)
→ CONFLITO!
```

**Tarefa**: Implemente resolução de conflitos baseada em **Last-Write-Wins** com timestamps:

1. **Crie tabelas**:
```sql
CREATE TABLE documentos (
    documento_id VARCHAR2(50),
    versao NUMBER,
    conteudo CLOB,
    timestamp_modificacao TIMESTAMP,
    modificado_por VARCHAR2(100),
    servidor_origem VARCHAR2(50),
    PRIMARY KEY (documento_id, versao)
);

CREATE TABLE conflitos (
    conflito_id NUMBER PRIMARY KEY,
    documento_id VARCHAR2(50),
    versao_a NUMBER,
    versao_b NUMBER,
    timestamp_deteccao TIMESTAMP,
    resolucao VARCHAR2(20), -- 'MANUAL', 'LWW', 'MERGE'
    versao_final NUMBER
);
```

2. **Implemente detecção de conflito**:
```sql
CREATE OR REPLACE PROCEDURE detectar_e_resolver_conflito(
    p_documento_id VARCHAR2
);
```

3. **Regra de resolução**: 
   - Last-Write-Wins: Versão com maior timestamp vence
   - Registra conflito para auditoria

4. **Teste**:
   - Crie duas versões simultâneas do mesmo documento
   - Execute detecção de conflito
   - Verifique que versão mais recente foi mantida

---

### Exercício 10: Implementando Quorum para Evitar Split-Brain

**Contexto**: Cluster de banco de dados com 5 nós precisa prevenir split-brain.

**Cenário**:
```
Cluster: [Nó1, Nó2, Nó3, Nó4, Nó5]
Partição de rede divide em:
- Grupo A: [Nó1, Nó2]
- Grupo B: [Nó3, Nó4, Nó5]
```

**Tarefa**: Implemente sistema baseado em quorum:

1. **Crie tabelas**:
```sql
CREATE TABLE cluster_nodes (
    node_id VARCHAR2(50) PRIMARY KEY,
    status VARCHAR2(20), -- 'ONLINE', 'OFFLINE'
    papel VARCHAR2(10), -- 'MASTER', 'SLAVE'
    ultimo_heartbeat TIMESTAMP,
    particao_id NUMBER
);

CREATE TABLE cluster_config (
    config_key VARCHAR2(50) PRIMARY KEY,
    config_value VARCHAR2(100)
);

-- Configuração: Total de nós = 5, Quorum = 3
INSERT INTO cluster_config VALUES ('TOTAL_NODES', '5');
INSERT INTO cluster_config VALUES ('QUORUM', '3');
```

2. **Implemente verificação de quorum**:
```sql
CREATE OR REPLACE FUNCTION tem_quorum(
    p_particao_id NUMBER
) RETURN VARCHAR2; -- 'S' ou 'N'
```

3. **Implemente política**:
   - Apenas partição com quorum pode aceitar escritas
   - Partição sem quorum entra em modo read-only
   - Quando rede se reconecta, partições se reconciliam

4. **Teste**:
   - Simule partição de rede
   - Grupo A (2 nós): NÃO tem quorum → Read-only
   - Grupo B (3 nós): TEM quorum → Aceita escritas
   - Simule reconexão → Sincroniza mudanças

---

## 🏆 Exercício Desafio Final: Sistema Distribuído Completo

**Contexto**: Você foi contratado para projetar o banco de dados de um novo concorrente do Uber chamado "RideShare Brasil".

**Requisitos**:

1. **Escala**:
   - 10 milhões de usuários (passageiros + motoristas)
   - 100 milhões de corridas por mês
   - Operação em 50 cidades brasileiras

2. **Funcionalidades**:
   - Matching de passageiro-motorista em tempo real
   - Histórico de corridas
   - Avaliações
   - Pagamentos

3. **Requisitos Não-Funcionais**:
   - Latência de matching < 500ms
   - Disponibilidade de 99.9%
   - Corridas não podem ser perdidas (durabilidade)

**Tarefas**:

1. **Projetar arquitetura distribuída**:
   - Quantos datacenters? Onde?
   - Fragmentação: por cidade? por região?
   - Replicação: síncrona ou assíncrona?

2. **Modelar tabelas principais** com estratégia de distribuição:
   - `usuarios` (passageiros e motoristas)
   - `corridas`
   - `localizacoes` (GPS em tempo real)
   - `pagamentos`

3. **Definir estratégias**:
   - Como garantir matching rápido?
   - Como lidar com corrida entre cidades?
   - O que acontece se datacenter cair?
   - Como evitar cobrar duas vezes?

4. **Implementar** (pelo menos parcialmente):
   - Script de criação de tabelas fragmentadas
   - Procedure de inserção de corrida
   - Function de busca de motoristas próximos
   - Sistema de replicação

5. **Documentar**:
   - Diagrama de arquitetura
   - Justificativas técnicas (por que escolheu cada estratégia)
   - Trade-offs identificados
   - Análise de custo vs benefício

**Entregável**: Documento técnico completo + scripts SQL funcionais

---

## 📊 Critérios de Avaliação

### Para Exercícios Teóricos:
- ✅ Clareza na explicação
- ✅ Justificativas técnicas sólidas
- ✅ Análise de trade-offs
- ✅ Referências a conceitos do material

### Para Exercícios Práticos:
- ✅ Código SQL funcional
- ✅ Comentários explicando lógica
- ✅ Casos de teste adequados
- ✅ Tratamento de erros
- ✅ Performance considerada

---

## 🎯 Dicas de Estudo

1. **Para Iniciantes**: Comece pelos exercícios básicos (1-3)
2. **Para Intermediários**: Foque nos exercícios 4-6
3. **Para Avançados**: Tente os exercícios 7-10
4. **Para Especialistas**: Encare o desafio final

**Recursos Adicionais**:
- Consulte exemplos em `/exemplos/`
- Revise conceitos no `README.md`
- Pesquise casos reais de empresas (Netflix, Uber, etc.)

**Boa sorte! 🚀**
