# 💻 Modelo Físico - Implementação em SQL

> Transforme seus modelos lógicos em estruturas reais de banco de dados

Este módulo é a fase final da disciplina de Modelagem de Banco de Dados, onde você implementa na prática tudo que foi projetado nos modelos conceitual e lógico.

## 📚 Conteúdo

### 📄 Script Empresa.sql
Script completo demonstrando a implementação de um banco de dados de uma empresa.

**Contém:**
- Criação de tabelas
- Definição de constraints
- Inserção de dados de exemplo
- Consultas básicas

### 📂 [conceito-sql-oracle/](conceito-sql-oracle/)
**Introdução prática a SQL Oracle - Material de Aula**

Este diretório contém scripts SQL usados durante as aulas de Modelagem de Banco de Dados para introduzir os alunos à implementação física.

#### 📁 Estrutura:

**[01/](conceito-sql-oracle/01/)** - Fundamentos SQL
- `01 - Criar tabelas e inserir dados.sql` - CREATE TABLE e INSERT
- `02-drop-alter.sql` - DROP TABLE e ALTER TABLE
- `03-selects-where.sql` - Consultas básicas com filtros
- `04-join.sql` - Introdução a JOINs
- `05-resumo.sql` - Resumo dos conceitos

**[02/](conceito-sql-oracle/02/)** - SQL Intermediário
- `Funcoes SQL.sql` e `Funcoes SQL Server.sql` - Funções do banco de dados
- `02-Exemplos Joins.sql` - Exemplos de JOINs
- `03-Exemplos LEFT e RIGHT JOIN.sql` - JOINs externos
- **[Consultas/](conceito-sql-oracle/02/Consultas/)** - Consultas avançadas
  - DISTINCT, CASE, LIMIT, GROUP BY, HAVING, ORDER BY

**[03/](conceito-sql-oracle/03/)** - Bancos de Dados Completos
- `Banco de dados.sql` - Exemplo completo
- `Exemplo.sql` - Exemplos práticos
- `Banco de dados alunos.sql` - Sistema acadêmico

---

## 🎯 Objetivo deste Módulo

Este módulo ensina a implementar fisicamente os modelos que você criou:

```
Modelo Conceitual (MER) → Modelo Lógico (Normalizado) → Modelo Físico (SQL)
```

### O que você vai fazer:
1. **Traduzir** o modelo lógico para SQL
2. **Criar** tabelas com tipos de dados apropriados
3. **Definir** constraints (PK, FK, CHECK, etc.)
4. **Implementar** índices para performance
5. **Inserir** dados de teste
6. **Validar** com consultas

---

## 🔗 Relação com Outras Disciplinas

### Este módulo é uma INTRODUÇÃO a SQL

⚠️ **Importante**: O conteúdo aqui é uma introdução básica a SQL focada na implementação de modelos. Para aprender SQL completamente, consulte:

#### 📖 Para Aprendizado Completo de SQL:
1. **[SQL Básico](../../sql-basico/)** - Curso completo de SQL (Módulos 01-09)
   - 6-8 semanas de conteúdo estruturado
   - Exemplos e exercícios detalhados
   - Foco em fundamentos

2. **[SQL Avançado](../../sql-avancado/)** - Consultas complexas (Módulos 10-17)
   - JOINs avançados
   - Subqueries
   - Otimização
   - Functions e agregações

#### 📜 Para Prática por SGBD:
- **[Scripts SQL](../../scripts-sql/)** - Coleção organizada por SGBD
  - MySQL, Oracle, PostgreSQL, SQL Server
  - Scripts de administração
  - Exemplos específicos de cada banco

### Fluxo de Aprendizado Recomendado:

```
┌─────────────────────────────────────────────────────────────┐
│ Fase 1: MODELAGEM                                           │
│ 1. Modelo Conceitual (Aulas 01-04)                         │
│ 2. Modelo Lógico (Aulas 05-08)                             │
│ 3. Álgebra Relacional (Aulas 09-10)                        │
│ 4. Modelo Físico - INTRODUÇÃO (esta pasta)                 │
└─────────────────────────────────────────────────────────────┘
                            ↓
┌─────────────────────────────────────────────────────────────┐
│ Fase 2: SQL COMPLETO (Aprofundamento)                      │
│ 5. SQL Básico - Curso completo (Módulos 01-09)             │
│ 6. SQL Avançado - Consultas complexas (Módulos 10-17)      │
│ 7. Scripts SQL - Prática por SGBD                          │
└─────────────────────────────────────────────────────────────┘
                            ↓
┌─────────────────────────────────────────────────────────────┐
│ Fase 3: APLICAÇÃO PRÁTICA                                  │
│ 8. Projetos Práticos                                       │
│ 9. Linguagem Procedure (PL/SQL)                            │
│ 10. Sistemas Distribuídos                                  │
└─────────────────────────────────────────────────────────────┘
```

---

## 📖 Como Usar Este Material

### Durante a Disciplina de Modelagem:
1. **Foque na implementação**: Use os scripts como referência para implementar seus modelos
2. **Não se preocupe em decorar SQL**: O foco é entender a tradução modelo → SQL
3. **Use como ponte**: Este é o primeiro contato com SQL

### Após a Disciplina de Modelagem:
1. **Vá para SQL Básico**: Aprenda SQL de forma estruturada
2. **Pratique com Projetos**: Aplique em casos reais
3. **Avance para SQL Avançado**: Domine consultas complexas

---

## 🛠️ Ferramentas Recomendadas

### Para Este Módulo:
- **[Oracle Live SQL](https://livesql.oracle.com/)** ⭐ Recomendado
  - Não requer instalação
  - Ambiente web gratuito
  - Perfeito para aprender

- **[Oracle SQL Developer](https://www.oracle.com/database/sqldeveloper/)**
  - Gratuito
  - Profissional
  - Suporta múltiplos bancos

### Alternativas Multiplataforma:
- **[DBeaver](https://dbeaver.io/)** - Gratuito, suporta vários SGBDs
- **[MySQL Workbench](https://www.mysql.com/products/workbench/)** - Gratuito para MySQL

---

## 🎯 Objetivos de Aprendizado

Ao completar este módulo, você será capaz de:

### Implementação Básica:
- ✅ Criar tabelas a partir do modelo lógico
- ✅ Definir tipos de dados apropriados
- ✅ Implementar chaves primárias e estrangeiras
- ✅ Aplicar constraints básicos

### Validação:
- ✅ Inserir dados de teste
- ✅ Fazer consultas básicas (SELECT)
- ✅ Validar relacionamentos (JOINs simples)
- ✅ Verificar integridade referencial

### Próximos Passos:
- ✅ Estar pronto para o curso completo de SQL
- ✅ Entender a ponte entre teoria e prática
- ✅ Ter vocabulário básico de SQL

---

## 📚 Comandos SQL Abordados

Este módulo introduz:

### DDL (Data Definition Language):
```sql
CREATE TABLE, ALTER TABLE, DROP TABLE
```

### DML (Data Manipulation Language):
```sql
INSERT, SELECT (básico), UPDATE, DELETE
```

### Constraints:
```sql
PRIMARY KEY, FOREIGN KEY, NOT NULL, UNIQUE, CHECK
```

### Consultas Básicas:
```sql
SELECT, WHERE, JOIN (introdução)
```

---

## 💡 Dicas para Sucesso

### Durante a Modelagem:
1. **Faça o modelo antes**: Sempre faça MER → Lógico → Físico
2. **Não pule etapas**: Cada fase tem seu propósito
3. **Use os scripts como guia**: Veja como implementar seus modelos

### Transição para SQL:
1. **Não tenha pressa**: SQL tem muito conteúdo
2. **Pratique regularmente**: SQL requer prática
3. **Consulte documentação**: Cada SGBD tem particularidades

### Durante o Aprendizado:
1. **Execute os exemplos**: Não apenas leia, execute!
2. **Modifique e experimente**: Teste variações
3. **Compare SGBDs**: Veja diferenças entre Oracle, MySQL, etc.

---

## 📚 Referências

### Para Modelagem:
- **Elmasri, R. & Navathe, S.** (2016). *Fundamentals of Database Systems*. 7th Edition.
- **Heuser, C.A.** (2009). *Projeto de Banco de Dados*. 6ª Edição. Bookman.

### Para SQL:
- **Beaulieu, A.** (2020). *Learning SQL*. 3rd Edition. O'Reilly Media.
- **Date, C.J.** (2012). *SQL and Relational Theory*. 2nd Edition. O'Reilly Media.

### Documentação Oficial:
- [Oracle SQL Documentation](https://docs.oracle.com/en/database/)
- [MySQL Documentation](https://dev.mysql.com/doc/)
- [PostgreSQL Documentation](https://www.postgresql.org/docs/)

---

<div align="center">

**💻 Do modelo ao código: implemente seus bancos de dados!**

*A ponte entre o design teórico e a implementação prática*

🎨 **Modelos → SQL** | 💾 **Implementação Física** | 🚀 **Base para SQL Completo**

</div>

---

## 🔗 Links Úteis

- [← Voltar para Modelagem de Banco de Dados](../)
- [→ Ir para SQL Básico (Curso Completo)](../../sql-basico/)
- [→ Ir para SQL Avançado](../../sql-avancado/)
- [→ Ir para Scripts SQL (por SGBD)](../../scripts-sql/)
- [→ Ir para README Principal de Banco de Dados](../../)
