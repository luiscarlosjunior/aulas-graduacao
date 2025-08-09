# Exercícios Práticos - Módulo 04
## Trabalhando com a Estrutura de Tabelas

### Instruções Gerais
- Execute os exercícios em ordem sequencial
- Cada exercício tem objetivos específicos de aprendizagem
- Consulte o material teórico quando necessário
- Valide suas soluções com os scripts de teste fornecidos

---

## Exercício 1: Análise de Estruturas Existentes
**Objetivo**: Compreender como analisar estruturas de tabelas

### Tarefas:
1. Execute o script `estruturas_basicas.sql` para criar as tabelas base
2. Use comandos SQL para obter informações sobre:
   - Todas as tabelas criadas
   - Estrutura detalhada da tabela `usuario`
   - Constraints da tabela `musica`
   - Relacionamentos entre `album` e `artista`

### Comandos a utilizar:
```sql
-- Complete os comandos abaixo:
DESCRIBE ____;  -- ou \d no PostgreSQL
SELECT * FROM information_schema._____ WHERE _____;
```

---

## Exercício 2: Escolha de Tipos de Dados
**Objetivo**: Praticar a seleção adequada de tipos de dados

### Cenário:
Você precisa criar uma tabela para armazenar informações sobre **concertos** no sistema MusiStream.

### Especificações:
- ID único do concerto
- Nome do evento (até 200 caracteres)
- Data e horário do evento
- Local (cidade e país)
- Capacidade máxima de público (até 100.000 pessoas)
- Preço do ingresso (com centavos)
- Status do evento (agendado, realizado, cancelado, adiado)
- Se o evento é gratuito (sim/não)
- Duração esperada do evento (em minutos)
- Idade mínima para participar
- Observações gerais (texto livre)

### Sua tarefa:
Crie a tabela `concerto` com os tipos de dados mais adequados para cada campo.

```sql
CREATE TABLE concerto (
    -- Complete a estrutura aqui
);
```

---

## Exercício 3: Implementação de Constraints
**Objetivo**: Aplicar regras de integridade e validação

### Cenário:
Aprimore a tabela `concerto` do exercício anterior adicionando:

### Constraints necessárias:
1. **Chave primária** no ID
2. **NOT NULL** em campos obrigatórios
3. **CHECK** para validar:
   - Capacidade > 0
   - Preço >= 0 (quando não gratuito)
   - Status deve ser um dos valores válidos
   - Data do evento não pode ser no passado
   - Idade mínima entre 0 e 18 anos
   - Duração entre 30 e 480 minutos (8 horas)

### Sua tarefa:
```sql
-- Recrie a tabela com todas as constraints
DROP TABLE IF EXISTS concerto;
CREATE TABLE concerto (
    -- Adicione as constraints aqui
);
```

---

## Exercício 4: Relacionamentos Complexos
**Objetivo**: Criar relacionamentos entre tabelas

### Cenário:
Implemente um sistema de **ingressos** que se relaciona com:
- Concertos (um concerto tem muitos ingressos)
- Usuários (um usuário pode comprar muitos ingressos)
- Diferentes tipos de ingresso (VIP, Premium, Normal)

### Tabelas a criar:

1. **tipo_ingresso**:
   - ID, nome do tipo, descrição, multiplicador de preço

2. **ingresso**:
   - ID único, concerto, usuário, tipo de ingresso
   - Data da compra, preço pago, status (ativo, usado, cancelado)
   - Assento/setor (se aplicável)

### Sua tarefa:
```sql
-- Crie as tabelas com relacionamentos apropriados
```

---

## Exercício 5: Otimização de Performance
**Objetivo**: Criar estruturas otimizadas para consultas frequentes

### Cenário:
O sistema precisa responder rapidamente a consultas como:
- "Quais concertos estão disponíveis no Rio de Janeiro?"
- "Qual o histórico de compras de um usuário?"
- "Quantos ingressos restam para cada tipo em um concerto?"

### Sua tarefa:
1. Identifique quais campos serão mais consultados
2. Escolha tipos de dados que otimizem espaço e performance
3. Crie uma tabela `consulta_rapida_concertos` desnormalizada para buscas

```sql
-- Tabela otimizada para consultas de concertos por localização
CREATE TABLE consulta_rapida_concertos (
    -- Estrutura otimizada aqui
);
```

---

## Exercício 6: Validação Prática
**Objetivo**: Testar as estruturas criadas com dados reais

### Tarefas:
1. Insira dados válidos em todas as tabelas criadas
2. Teste inserções que devem falhar (constraints)
3. Execute consultas para verificar relacionamentos
4. Analise a consistência dos dados

### Dados de teste sugeridos:
```sql
-- Exemplo de concerto
INSERT INTO concerto VALUES (
    1, 'Rock in Rio 2024', '2024-09-15 20:00:00',
    'Rio de Janeiro', 'Brasil', 100000, 150.00,
    'agendado', FALSE, 480, 16, 'Festival de rock'
);

-- Continue com outros dados...
```

---

## Exercício 7: Modificação de Estruturas
**Objetivo**: Praticar alterações em tabelas existentes

### Cenário:
Após feedback dos usuários, você precisa:

1. **Adicionar** campos na tabela `concerto`:
   - Website oficial do evento
   - Telefone para informações
   - Se permite camping no local

2. **Modificar** campos existentes:
   - Aumentar limite de caracteres do nome do evento
   - Permitir valores NULL em campos opcionais

3. **Remover** campo desnecessário:
   - Campo de observações (será movido para outra tabela)

### Sua tarefa:
```sql
-- Use comandos ALTER TABLE para fazer as modificações
ALTER TABLE concerto ADD COLUMN ____;
ALTER TABLE concerto ALTER COLUMN ____;
ALTER TABLE concerto DROP COLUMN ____;
```

---

## Exercício 8: Análise Comparativa
**Objetivo**: Comparar diferentes abordagens de design

### Cenário:
Compare três abordagens para armazenar endereços de concertos:

**Abordagem 1**: Campos separados
```sql
CREATE TABLE concerto_endereco_v1 (
    id_concerto INTEGER,
    rua VARCHAR(200),
    numero VARCHAR(10),
    bairro VARCHAR(100),
    cidade VARCHAR(100),
    estado VARCHAR(50),
    pais VARCHAR(50),
    cep VARCHAR(20)
);
```

**Abordagem 2**: Campo único
```sql
CREATE TABLE concerto_endereco_v2 (
    id_concerto INTEGER,
    endereco_completo TEXT
);
```

**Abordagem 3**: JSON estruturado
```sql
CREATE TABLE concerto_endereco_v3 (
    id_concerto INTEGER,
    endereco_json JSONB
);
```

### Sua tarefa:
1. Implemente as três versões
2. Insira os mesmos dados em cada uma
3. Execute consultas típicas (buscar por cidade, por país)
4. Compare vantagens e desvantagens de cada abordagem

---

## Exercício 9: Projeto Integrado
**Objetivo**: Integrar todos os conceitos em um projeto completo

### Cenário:
Crie um sistema completo de **avaliações de concertos** que inclua:

### Funcionalidades:
- Usuários podem avaliar concertos que participaram
- Avaliações têm nota (0-10) e comentário opcional
- Sistema de curtidas/descurtidas em avaliações
- Ranking de melhores concertos por cidade/gênero
- Histórico de todas as avaliações de um usuário

### Requisitos técnicos:
1. Pelo menos 4 tabelas inter-relacionadas
2. Uso de todos os tipos de dados estudados
3. Constraints adequadas para cada regra de negócio
4. Estrutura otimizada para consultas de ranking
5. Validação de que usuário só avalia concertos que participou

### Sua tarefa:
Projete e implemente o sistema completo.

---

## Exercício 10: Troubleshooting
**Objetivo**: Diagnosticar e corrigir problemas em estruturas

### Cenário:
Você recebeu o seguinte código com problemas:

```sql
-- CÓDIGO COM PROBLEMAS - ENCONTRE E CORRIJA OS ERROS
CREATE TABLE evento_problema (
    id INTEGER,  -- Problema 1: falta constraint
    nome VARCHAR,  -- Problema 2: tamanho indefinido
    data_evento DATE DEFAULT '2020-01-01',  -- Problema 3: data no passado
    preco DECIMAL,  -- Problema 4: precisão indefinida
    status VARCHAR(10) DEFAULT 'ativo',  -- Problema 5: valores não validados
    capacidade INTEGER DEFAULT -1,  -- Problema 6: valor inválido
    organizador_email TEXT UNIQUE  -- Problema 7: tipo inadequado
);

INSERT INTO evento_problema VALUES 
(NULL, '', '2019-12-31', 'abc', 'cancelado', 0, 'email_sem_arroba');
```

### Sua tarefa:
1. Identifique todos os problemas
2. Corrija a estrutura da tabela
3. Modifique os dados de inserção para serem válidos
4. Documente cada correção feita

---

## Respostas e Soluções

As soluções para todos os exercícios estão disponíveis no arquivo `solucoes_exercicios.sql` neste diretório.

## Dicas para Sucesso

1. **Leia sempre as mensagens de erro** - elas indicam exatamente o que precisa ser corrigido
2. **Teste incrementalmente** - crie uma tabela simples primeiro, depois adicione complexidade
3. **Use DESCRIBE/\d** para verificar estruturas criadas
4. **Valide com dados reais** - insira dados de teste para verificar se tudo funciona
5. **Consulte a documentação** do seu SGBD para sintaxes específicas

## Próximos Passos

Após completar estes exercícios, você estará preparado para:
- Módulo 05: Estrutura das Tabelas, Regras e Relacionamentos
- Trabalhar com constraints mais avançadas
- Implementar índices para otimização
- Criar views e procedures