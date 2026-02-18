# 🚀 SQL Avançado - Consultas e Otimização

> Domine consultas complexas, otimização e comandos avançados de SQL

Este módulo contém conceitos avançados de SQL para criar consultas complexas, otimizar performance e dominar completamente a linguagem SQL.

## 🎯 Sobre Este Módulo

**🎓 Nível:** Intermediário a Avançado  
**⏱️ Duração:** 6-8 semanas  
**📋 Pré-requisitos:** SQL Básico (Módulos 01-09)

---

## 📚 Módulos de Aprendizado (10-17)

### 🔟 [Relatórios com Filtros e Operadores](10-relatorios-filtros-operadores/)
**⏱️ 1 semana**

WHERE, operadores relacionais e lógicos para filtrar dados.

**📖 Conteúdo:**
- Cláusula WHERE
- Operadores relacionais: `=`, `<>`, `<`, `>`, `<=`, `>=`
- Operadores lógicos: `AND`, `OR`, `NOT`
- Operador `BETWEEN`
- Operador `IN`
- Operador `LIKE` e wildcards
- Operador `IS NULL`

**🎯 Você vai aprender:**
- ✅ Filtrar dados com WHERE
- ✅ Combinar condições com AND/OR
- ✅ Usar operadores de comparação
- ✅ Buscar padrões com LIKE
- ✅ Trabalhar com valores NULL

**🔑 Comandos principais:**
```sql
SELECT * FROM tabela WHERE condicao;
WHERE coluna BETWEEN valor1 AND valor2;
WHERE coluna IN (valor1, valor2, ...);
WHERE coluna LIKE 'padrão%';
WHERE coluna IS NULL;
```

---

### 1️⃣1️⃣ [Relatórios com Operadores Aritméticos](11-relatorios-operadores-aritmeticos/)
**⏱️ 1 semana**

Cálculos e expressões matemáticas em SQL.

**📖 Conteúdo:**
- Operadores aritméticos: `+`, `-`, `*`, `/`, `%`
- Expressões calculadas
- Precedência de operadores
- Aliases para colunas calculadas
- Funções matemáticas básicas

**📁 Estrutura:**
- Diretório principal com exemplos
- `exercicios/` - Exercícios práticos

**🎯 Você vai aprender:**
- ✅ Realizar cálculos em consultas
- ✅ Criar colunas calculadas
- ✅ Usar operadores matemáticos
- ✅ Formatar resultados numéricos

**🔑 Comandos principais:**
```sql
SELECT preco * quantidade AS total FROM pedidos;
SELECT (preco * 1.10) AS preco_com_imposto;
```

---

### 1️⃣2️⃣ [Relatórios com Funções de Banco de Dados](12-relatorios-funcoes-banco-dados/)
**⏱️ 1 semana**

Funções agregadas, de string, de data e outras funções do SQL.

**📖 Conteúdo:**
- **Funções agregadas**: `COUNT()`, `SUM()`, `AVG()`, `MIN()`, `MAX()`
- **Funções de string**: `UPPER()`, `LOWER()`, `SUBSTRING()`, `CONCAT()`, `LENGTH()`
- **Funções de data**: `NOW()`, `DATE()`, `YEAR()`, `MONTH()`, `DAY()`
- **Funções de conversão**: `CAST()`, `CONVERT()`
- `GROUP BY` e agregações
- `HAVING` para filtrar grupos
- `ORDER BY` para ordenação

**📁 Estrutura:**
- Diretório principal com exemplos
- `exercicios/` - Exercícios práticos

**🎯 Você vai aprender:**
- ✅ Usar funções agregadas
- ✅ Manipular strings e datas
- ✅ Agrupar dados com GROUP BY
- ✅ Filtrar grupos com HAVING
- ✅ Ordenar resultados

**🔑 Comandos principais:**
```sql
SELECT COUNT(*), SUM(valor) FROM vendas;
SELECT categoria, AVG(preco) FROM produtos GROUP BY categoria;
SELECT categoria FROM produtos GROUP BY categoria HAVING COUNT(*) > 10;
```

---

### 1️⃣3️⃣ [Relatórios com Subqueries](13-relatorios-subqueries/)
**⏱️ 1 semana**

Subconsultas e consultas aninhadas.

**📖 Conteúdo:**
- Subqueries no WHERE
- Subqueries no FROM (derived tables)
- Subqueries no SELECT
- Subqueries correlacionadas
- Operadores `EXISTS`, `NOT EXISTS`
- Operadores `ANY`, `ALL`
- Comparação com subqueries

**📁 Estrutura:**
- Diretório principal com exemplos
- `exercicios/` - Exercícios práticos

**🎯 Você vai aprender:**
- ✅ Criar consultas aninhadas
- ✅ Usar subqueries em diferentes cláusulas
- ✅ Trabalhar com subqueries correlacionadas
- ✅ Otimizar subconsultas
- ✅ Quando usar subqueries vs JOINs

**🔑 Comandos principais:**
```sql
SELECT * FROM produtos WHERE preco > (SELECT AVG(preco) FROM produtos);
SELECT * FROM clientes WHERE EXISTS (SELECT 1 FROM pedidos WHERE pedidos.cliente_id = clientes.id);
```

---

### 1️⃣4️⃣ [Relatórios com Múltiplas Tabelas](14-relatorios-multiplas-tabelas/)
**⏱️ 1-2 semanas**

JOINs e relacionamentos entre tabelas - O tópico mais importante!

**📖 Conteúdo:**
- **INNER JOIN**: Registros que existem em ambas as tabelas
- **LEFT JOIN (LEFT OUTER JOIN)**: Todos da esquerda + correspondentes da direita
- **RIGHT JOIN (RIGHT OUTER JOIN)**: Todos da direita + correspondentes da esquerda
- **FULL JOIN (FULL OUTER JOIN)**: Todos os registros de ambas as tabelas
- **CROSS JOIN**: Produto cartesiano
- **SELF JOIN**: Junção da tabela com ela mesma
- JOINs múltiplos
- Aliases de tabelas
- Boas práticas com JOINs

**🎯 Você vai aprender:**
- ✅ Combinar dados de múltiplas tabelas
- ✅ Entender diferentes tipos de JOIN
- ✅ Usar INNER JOIN corretamente
- ✅ Trabalhar com LEFT/RIGHT JOIN
- ✅ Resolver problemas com múltiplas tabelas
- ✅ Otimizar JOINs

**🔑 Comandos principais:**
```sql
-- INNER JOIN
SELECT * FROM pedidos 
INNER JOIN clientes ON pedidos.cliente_id = clientes.id;

-- LEFT JOIN
SELECT * FROM clientes 
LEFT JOIN pedidos ON clientes.id = pedidos.cliente_id;

-- Múltiplos JOINs
SELECT * FROM pedidos p
INNER JOIN clientes c ON p.cliente_id = c.id
INNER JOIN produtos pr ON p.produto_id = pr.id;
```

---

### 1️⃣5️⃣ [Operações com Conjuntos](15-operacoes-conjuntos/)
**⏱️ 1 semana**

UNION, INTERSECT, EXCEPT para combinar resultados.

**📖 Conteúdo:**
- `UNION`: Combina resultados removendo duplicatas
- `UNION ALL`: Combina resultados mantendo duplicatas
- `INTERSECT`: Registros em comum
- `EXCEPT` (ou `MINUS`): Diferença entre conjuntos
- Regras de compatibilidade
- Performance e otimização

**📁 Estrutura:**
- `exemplos/` - Scripts de exemplo
- `exercicios/` - Exercícios práticos

**🎯 Você vai aprender:**
- ✅ Combinar resultados de múltiplas consultas
- ✅ Usar UNION vs UNION ALL
- ✅ Encontrar interseções
- ✅ Calcular diferenças entre conjuntos
- ✅ Otimizar operações de conjunto

**🔑 Comandos principais:**
```sql
SELECT nome FROM clientes_sp
UNION
SELECT nome FROM clientes_rj;

SELECT produto_id FROM vendas_2023
INTERSECT
SELECT produto_id FROM vendas_2024;
```

---

### 1️⃣6️⃣ [Criando VIEWS](16-criando-views/)
**⏱️ 1 semana**

Visões e consultas reutilizáveis.

**📖 Conteúdo:**
- O que são views
- Criar views: `CREATE VIEW`
- Modificar views: `CREATE OR REPLACE VIEW`
- Remover views: `DROP VIEW`
- Views vs Tabelas
- Views materializadas
- Vantagens e limitações
- Boas práticas com views

**📁 Estrutura:**
- `exemplos/` - Exemplos de views

**🎯 Você vai aprender:**
- ✅ Criar e gerenciar views
- ✅ Simplificar consultas complexas
- ✅ Encapsular lógica de negócio
- ✅ Melhorar segurança com views
- ✅ Quando usar views

**🔑 Comandos principais:**
```sql
CREATE VIEW vendas_resumo AS
SELECT produto_id, SUM(quantidade) AS total
FROM vendas
GROUP BY produto_id;

-- Usar a view
SELECT * FROM vendas_resumo WHERE total > 100;
```

---

### 1️⃣7️⃣ [Manipulação de Dados: UPDATE e DELETE](17-manipulacao-dados-update-delete/)
**⏱️ 1 semana**

Atualização e remoção de dados - Comandos DML avançados.

**📖 Conteúdo:**
- Comando `UPDATE`
- UPDATE com subqueries
- UPDATE múltiplas colunas
- Comando `DELETE`
- DELETE com WHERE
- DELETE com subqueries
- `TRUNCATE TABLE`
- Diferenças entre DELETE e TRUNCATE
- Boas práticas e segurança
- Transações e rollback

**📁 Estrutura:**
- `exemplos/` - Scripts seguros
- `exercicios/` - Exercícios práticos

**🎯 Você vai aprender:**
- ✅ Atualizar registros com UPDATE
- ✅ Remover registros com DELETE
- ✅ Usar WHERE para segurança
- ✅ Diferenciar DELETE de TRUNCATE
- ✅ Trabalhar com transações
- ✅ Prevenir erros catastróficos

**🔑 Comandos principais:**
```sql
-- UPDATE
UPDATE produtos SET preco = preco * 1.10 WHERE categoria = 'Eletrônicos';

-- DELETE
DELETE FROM pedidos WHERE data < '2020-01-01';

-- TRUNCATE (remove todos os registros rapidamente)
TRUNCATE TABLE temp_data;
```

⚠️ **ATENÇÃO**: Sempre use WHERE com UPDATE e DELETE! Sempre teste com SELECT primeiro!

---

## 🛤️ Trilha de Aprendizado

### Progressão Recomendada:
```
Módulo 10 → 11 → 12 → 13 → 14 → 15 → 16 → 17
(1 sem)  (1 sem)  (1 sem)  (1 sem) (1-2 sem) (1 sem)  (1 sem)  (1 sem)
```

### Sequência por Importância:

#### 🌟 Essenciais (Prioridade Alta):
- **Módulo 10**: Filtros - Base para tudo
- **Módulo 12**: Funções - Uso diário
- **Módulo 14**: JOINs - Mais importante!
- **Módulo 17**: UPDATE/DELETE - Crítico

#### 📊 Importantes (Prioridade Média):
- **Módulo 13**: Subqueries - Consultas complexas
- **Módulo 11**: Operadores Aritméticos - Cálculos
- **Módulo 16**: Views - Organização

#### 🎯 Complementares:
- **Módulo 15**: Operações de Conjunto - Casos específicos

---

## 📖 Como Usar Este Material

### Para Estudantes:
1. **Conclua SQL Básico Primeiro**: Pré-requisito obrigatório
2. **Foco nos Essenciais**: Módulos 10, 12, 14, 17
3. **Pratique Muito**: JOINs requerem muita prática
4. **Experimente**: Teste diferentes abordagens
5. **Projete Projetos**: Aplique em casos reais

### Para Professores:
- 6-8 semanas de conteúdo avançado
- Exercícios progressivos
- Exemplos do mundo real
- Foco especial em JOINs (Módulo 14)

### Para Profissionais:
- Referência rápida de sintaxe
- Exemplos de otimização
- Padrões de consultas comuns
- Boas práticas de performance

---

## 🎯 Objetivos de Aprendizado

Ao completar SQL Avançado, você será capaz de:

### Consultas Complexas:
- ✅ Filtrar dados com múltiplas condições
- ✅ Usar funções agregadas e de transformação
- ✅ Criar subconsultas (subqueries)
- ✅ Dominar todos os tipos de JOIN
- ✅ Combinar consultas com UNION/INTERSECT

### Otimização:
- ✅ Escrever consultas eficientes
- ✅ Usar índices apropriadamente
- ✅ Escolher entre subquery e JOIN
- ✅ Entender planos de execução

### Manipulação Avançada:
- ✅ Atualizar dados com UPDATE
- ✅ Remover dados com DELETE
- ✅ Trabalhar com transações
- ✅ Prevenir erros de dados

### Organização:
- ✅ Criar e gerenciar views
- ✅ Encapsular lógica complexa
- ✅ Melhorar reusabilidade de código

---

## 🔗 Próximos Passos

### Após Completar SQL Avançado:

1. **[Linguagem Procedure](../linguagem-procedure/)** - PL/SQL
   - Procedures e Functions
   - Triggers
   - Packages

2. **[Projetos Práticos](../projetos/)** - Aplicação Completa
   - Sistema de Streaming (MusiStream)
   - E-commerce
   - Barbearia

3. **[Sistemas Distribuídos](../sistemas-distribuidos/)** - Escalabilidade
   - Arquiteturas distribuídas
   - Fragmentação e replicação
   - Teorema CAP

---

## 💡 Dicas de Performance

### Otimização de Consultas:
1. **Use índices**: Em colunas usadas em WHERE e JOIN
2. **INNER JOIN vs LEFT JOIN**: em geral, `INNER JOIN` pode ser mais eficiente quando você não precisa de linhas sem correspondência — confirme sempre com `EXPLAIN/EXPLAIN PLAN`
3. **Filtre o mais cedo possível**: use `WHERE` (ou condições no `ON`) para reduzir o volume de dados que participa dos JOINs, lembrando que o otimizador pode reordenar operações
4. **Subquery vs JOIN**: teste ambas as abordagens e compare os planos de execução
5. **DISTINCT com cuidado**: Pode ser custoso
6. **Limite resultados**: Use LIMIT/TOP quando possível

### Boas Práticas:
- ✅ Sempre use WHERE em UPDATE/DELETE
- ✅ Teste com SELECT antes de UPDATE/DELETE
- ✅ Use EXPLAIN/EXPLAIN PLAN para análise
- ✅ Evite SELECT * em produção
- ✅ Use aliases para clareza
- ✅ Comente consultas complexas

---

## 🛠️ Ferramentas Recomendadas

### Para Performance:
- **EXPLAIN/EXPLAIN PLAN** - Análise de queries
- **SQL Tuning Advisor** (Oracle)
- **Query Analyzer** (SQL Server)
- **EXPLAIN ANALYZE** (PostgreSQL)

### Para Desenvolvimento:
- **DataGrip** - IDE profissional
- **DBeaver** - Gratuito, multiplataforma
- **SQL Developer** - Oracle

---

## 📚 Referências Bibliográficas

- **Beaulieu, A.** (2020). *Learning SQL: Master SQL Fundamentals*. 3rd Edition. O'Reilly Media.
- **Date, C.J.** (2012). *SQL and Relational Theory*. 2nd Edition. O'Reilly Media.
- **Molinaro, A.** (2005). *SQL Cookbook*. O'Reilly Media.
- **Kline, K., Kline, D., & Hunt, B.** (2008). *SQL in a Nutshell*. 3rd Edition. O'Reilly Media.

---

<div align="center">

**🚀 Domine SQL Avançado!**

*Torne-se um especialista em consultas complexas e otimização de banco de dados*

📚 **8 módulos avançados** | 💻 **Centenas de exemplos** | 🎯 **Foco em JOINs**

⚡ **Performance** | 🔍 **Otimização** | 📊 **Consultas Complexas**

</div>

## 🎓 Sistema Exemplo

Continuamos usando o **MusiStream** (sistema de streaming de música) para exemplos avançados. Consulte o [README principal](../README.md) para detalhes.
