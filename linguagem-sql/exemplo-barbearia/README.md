# Sistema Barbearia - Exemplo Completo de Modelagem de Dados

## 📋 Visão Geral

O **Sistema Barbearia** é um exemplo educacional completo que demonstra os conceitos fundamentais de **modelagem de dados** através de um domínio simples e familiar. Este sistema gerencia clientes, barbeiros, serviços e agendamentos de uma barbearia tradicional.

## 🎯 Objetivos Pedagógicos

Este exemplo foi criado para:

1. **Demonstrar modelagem completa**: Do conceitual ao físico
2. **Ilustrar relacionamentos**: 1:N e N:M com exemplos práticos
3. **Aplicar constraints**: Validações de domínio e integridade
4. **Exercitar SQL Oracle**: Sintaxe específica e recursos avançados
5. **Consolidar conceitos**: Chaves, índices, triggers e views

## 🗂️ Estrutura dos Arquivos

### Scripts SQL
- **`01-estrutura-completa.sql`**: Schema completo do banco de dados
- **`02-inserir-dados.sql`**: Inserção de dados de exemplo
- **`03-consultas-basicas.sql`**: Consultas essenciais do sistema

### Documentação da Modelagem
- **`modelagem-conceitual.md`**: Análise conceitual detalhada com entidades e relacionamentos
- **`modelagem-logica.md`**: Especificação lógica com tabelas, chaves e constraints
- **`README.md`**: Este arquivo com visão geral e explicações

## 💼 Domínio do Problema

### Cenário de Negócio

A **Barbearia Silva** é um estabelecimento tradicional que oferece serviços de corte de cabelo, barba, tratamentos e outros serviços de estética masculina e feminina. O sistema deve gerenciar:

- **Cadastro de clientes** com histórico de preferências
- **Controle da equipe** de barbeiros e suas especialidades  
- **Catálogo de serviços** com preços e durações
- **Agendamento flexível** permitindo múltiplos serviços por visita
- **Controle financeiro** com histórico de receita

### Funcionalidades Implementadas

✅ **Gestão de Clientes**: Cadastro, edição e controle de status  
✅ **Gestão de Barbeiros**: Controle da equipe e especialidades  
✅ **Catálogo de Serviços**: Preços, durações e descrições  
✅ **Sistema de Agendamentos**: Flexível com múltiplos serviços  
✅ **Controle de Status**: Marcado → Em Andamento → Concluído/Cancelado  
✅ **Relatórios**: Receita, popularidade de serviços, performance dos barbeiros  

## 🏗️ Arquitetura do Banco de Dados

### Entidades Principais

```
┌─────────────┐    ┌─────────────────┐    ┌─────────────┐
│   CLIENTES  │    │  AGENDAMENTOS   │    │  BARBEIROS  │
├─────────────┤    ├─────────────────┤    ├─────────────┤
│ • id (PK)   │◄───┤ • cliente_id    │───►│ • id (PK)   │
│ • nome      │    │ • barbeiro_id   │    │ • nome      │
│ • email ◄─────────┤ • inicio        │    │ • especialidade
│ • telefone  │    │ • status        │    │ • ativo     │
└─────────────┘    │ • valor_total   │    └─────────────┘
                   └─────────┬───────┘
                             │ N:M
                   ┌─────────▼───────┐
                   │ AGEND_SERVICOS  │
                   ├─────────────────┤
                   │ • agendamento_id │
                   │ • servico_id    ├───┐
                   │ • quantidade    │   │
                   │ • preco_unitario│   │
                   └─────────────────┘   │
                                         │
                   ┌─────────────┐       │
                   │  SERVICOS   │◄──────┘
                   ├─────────────┤
                   │ • id (PK)   │
                   │ • nome      │
                   │ • duracao_min
                   │ • preco     │
                   └─────────────┘
```

### Relacionamentos

| Origem | Destino | Tipo | Cardinalidade | Justificativa |
|--------|---------|------|---------------|---------------|
| CLIENTES | AGENDAMENTOS | 1:N | Um cliente, muitos agendamentos | Clientes retornam |
| BARBEIROS | AGENDAMENTOS | 1:N | Um barbeiro, muitos agendamentos | Barbeiros atendem vários |
| AGENDAMENTOS | SERVICOS | N:M | Múltiplos serviços por agendamento | Flexibilidade comercial |

## 📖 Conceitos Demonstrados

### 1. Modelagem Conceitual
- **Identificação de entidades** através de substantivos do domínio
- **Análise de relacionamentos** baseada em regras de negócio
- **Definição de cardinalidades** com justificativas práticas
- **Atributos derivados** e regras de domínio

### 2. Modelagem Lógica  
- **Tradução para tabelas relacionais** com chaves primárias e estrangeiras
- **Normalização até 3FN** com análise de dependências
- **Definição de tipos de dados** apropriados para Oracle
- **Constraints de integridade** e validação

### 3. Implementação Física
- **Uso de recursos Oracle**: IDENTITY, TIMESTAMP, CHECK constraints
- **Índices estratégicos** para performance
- **Comentários de documentação** no próprio banco
- **Triggers de validação** para regras complexas

## 🔧 Como Usar Este Exemplo

### Pré-requisitos
- Oracle Database 11g ou superior
- SQL*Plus, SQL Developer, ou similar
- Conhecimentos básicos de SQL

### Sequência de Execução

1. **📚 Estude a Teoria**
   ```bash
   # Leia primeiro os arquivos de modelagem
   1. modelagem-conceitual.md
   2. modelagem-logica.md
   3. Este README.md
   ```

2. **🏗️ Crie a Estrutura**
   ```sql
   -- Execute no Oracle
   @01-estrutura-completa.sql
   ```

3. **📝 Insira os Dados**
   ```sql
   -- Execute após o script anterior
   @02-inserir-dados.sql
   ```

4. **🔍 Teste as Consultas**
   ```sql
   -- Explore as consultas de exemplo
   @03-consultas-basicas.sql
   ```

5. **🧪 Experimente**
   - Modifique as consultas
   - Adicione novos dados
   - Teste cenários diferentes

## 💡 Exemplos Práticos

### Cenário 1: Cliente João agenda Corte + Barba

```sql
-- 1. Cliente existe
SELECT * FROM clientes WHERE nome LIKE '%João%';

-- 2. Criar agendamento
INSERT INTO agendamentos (cliente_id, barbeiro_id, inicio, status) 
VALUES (1, 1, SYSTIMESTAMP + INTERVAL '1' DAY, 'MARCADO');

-- 3. Adicionar serviços
INSERT INTO agendamento_servicos (agendamento_id, servico_id, quantidade, preco_unitario)
VALUES (1, 1, 1, 25.00), -- Corte
       (1, 2, 1, 15.00); -- Barba

-- 4. Ver resultado
SELECT a.id, c.nome, b.nome AS barbeiro, 
       LISTAGG(s.nome, ', ') WITHIN GROUP (ORDER BY s.nome) AS servicos
FROM agendamentos a
JOIN clientes c ON c.id = a.cliente_id  
JOIN barbeiros b ON b.id = a.barbeiro_id
JOIN agendamento_servicos asv ON asv.agendamento_id = a.id
JOIN servicos s ON s.id = asv.servico_id
WHERE a.id = 1
GROUP BY a.id, c.nome, b.nome;
```

### Cenário 2: Relatório de Receita por Barbeiro

```sql
SELECT b.nome AS barbeiro,
       COUNT(a.id) AS atendimentos_concluidos,
       SUM(a.valor_total) AS receita_total,
       AVG(a.valor_total) AS ticket_medio
FROM barbeiros b
JOIN agendamentos a ON a.barbeiro_id = b.id
WHERE a.status = 'CONCLUIDO'
GROUP BY b.nome
ORDER BY receita_total DESC;
```

### Cenário 3: Agenda do Dia

```sql
SELECT TO_CHAR(a.inicio, 'HH24:MI') AS horario,
       c.nome AS cliente,
       b.nome AS barbeiro,
       LISTAGG(s.nome, ', ') WITHIN GROUP (ORDER BY s.nome) AS servicos
FROM agendamentos a
JOIN clientes c ON c.id = a.cliente_id
JOIN barbeiros b ON b.id = a.barbeiro_id  
JOIN agendamento_servicos asv ON asv.agendamento_id = a.id
JOIN servicos s ON s.id = asv.servico_id
WHERE TRUNC(a.inicio) = TRUNC(SYSDATE)
  AND a.status = 'MARCADO'
GROUP BY a.inicio, c.nome, b.nome
ORDER BY a.inicio;
```

## 🎓 Exercícios Propostos

### Básicos
1. **Adicione um novo cliente** com validação de email
2. **Crie um agendamento** com múltiplos serviços
3. **Liste todos os agendamentos** de um barbeiro específico
4. **Calcule a receita** de um período específico

### Intermediários  
5. **Implemente busca de clientes** por nome (insensível a maiúsculas/minúsculas)
6. **Crie relatório de serviços** mais e menos solicitados
7. **Identifique horários livres** de um barbeiro em uma data
8. **Calcule tempo médio** de atendimento por barbeiro

### Avançados
9. **Implemente sistema de fidelidade** (desconto após N atendimentos)
10. **Crie alertas de agendamentos** próximos (1 hora antes)
11. **Implemente cancelamento** com regras de tempo mínimo
12. **Desenvolva sistema de avaliação** dos serviços

## 🔍 Validação do Aprendizado

### Checklist de Conceitos

#### Modelagem Conceitual
- [ ] Identifiquei todas as entidades necessárias
- [ ] Determinei relacionamentos e cardinalidades  
- [ ] Defini atributos essenciais
- [ ] Estabeleci regras de negócio

#### Modelagem Lógica
- [ ] Traduzi entidades para tabelas
- [ ] Implementei chaves primárias e estrangeiras
- [ ] Apliquei constraints de validação
- [ ] Normalizei até 3FN

#### Implementação Física  
- [ ] Escolhi tipos de dados apropriados
- [ ] Criei índices para performance
- [ ] Implementei triggers de validação
- [ ] Documentei o schema

### Perguntas de Reflexão

1. **Por que usar relacionamento N:M entre agendamentos e serviços?**
   - Resposta: Permite flexibilidade para múltiplos serviços por visita

2. **Por que armazenar preço na tabela de relacionamento?**
   - Resposta: Preserva histórico de preços na data do agendamento

3. **Qual a vantagem das chaves surrogate (id auto-incremento)?**
   - Resposta: Imutabilidade, performance e independência dos dados de negócio

4. **Por que usar TIMESTAMP em vez de DATE para agendamentos?**
   - Resposta: Maior precisão temporal necessária para controle de horários

## 🚀 Extensões Possíveis

### Funcionalidades Adicionais
- **Sistema de avaliações** (tabela AVALIACOES)
- **Controle de estoque** de produtos (PRODUTOS, MOVIMENTACAO_ESTOQUE)
- **Sistema de fidelidade** (PONTOS_CLIENTE, PROMOCOES)
- **Agenda de funcionamento** (HORARIO_FUNCIONAMENTO)
- **Múltiplas unidades** (FILIAIS, TRANSFERENCIAS)

### Melhorias Técnicas
- **Auditoria completa** com tabelas de LOG
- **Soft delete** em vez de exclusão física
- **Versionamento** de preços de serviços
- **Cache materializado** para relatórios
- **APIs REST** para integração

## 📚 Referências e Aprofundamento

### Bibliografia Recomendada
- **Elmasri & Navathe**: "Sistemas de Banco de Dados" - Modelagem conceitual
- **Date, C.J.**: "Introdução a Sistemas de BD" - Teoria relacional
- **Oracle Documentation**: SQL Language Reference - Sintaxe específica

### Links Úteis
- [Oracle SQL Developer](https://www.oracle.com/database/sqldeveloper/)
- [Oracle Live SQL](https://livesql.oracle.com/) - Para testar online
- [Documentação Oracle Database](https://docs.oracle.com/database/)

## 🤝 Contribuições

Este exemplo foi desenvolvido para fins educacionais. Sugestões de melhorias são bem-vindas:

- Novos cenários de uso
- Consultas mais complexas  
- Melhorias na documentação
- Correções de bugs
- Exercícios adicionais

---

## 📞 Informações Finais

**Nível de Dificuldade**: Básico a Intermediário  
**Tempo Estimado**: 2-3 horas para conclusão completa  
**Pré-requisitos**: Conhecimentos básicos de SQL e bancos relacionais  
**SGBD**: Oracle Database 11g+

---

*Desenvolvido com 💖 para facilitar o aprendizado de modelagem de dados através de exemplos práticos e relevantes.*