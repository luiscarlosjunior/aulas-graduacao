# 📖 SQL Básico - Fundamentos de SQL

> Aprenda os fundamentos essenciais da linguagem SQL do zero

Este módulo contém os conceitos fundamentais de SQL necessários para trabalhar com bancos de dados relacionais. É o ponto de partida ideal para quem está começando sua jornada em banco de dados.

## 🎯 Sobre Este Módulo

**🎓 Nível:** Iniciante  
**⏱️ Duração:** 6-8 semanas  
**📋 Pré-requisitos:** Nenhum - este é um curso introdutório!

---

## 📚 Módulos de Aprendizado (01-09)

### 1️⃣ [Introdução à Modelagem de Dados](01-introducao-modelagem-dados/)
**⏱️ 1 semana**

Conceitos básicos de modelagem e design de banco de dados.

**📖 Conteúdo:**
- O que é um banco de dados
- Conceitos de modelagem
- Entidades, atributos e relacionamentos
- Importância do design de dados

**📁 Estrutura:**
- `exemplos/` - Exemplos práticos de modelos
- `exercicios/` - Exercícios para praticar

**🎯 Você vai aprender:**
- ✅ Entender o que é um banco de dados relacional
- ✅ Compreender conceitos básicos de modelagem
- ✅ Identificar entidades e relacionamentos

---

### 2️⃣ [Introdução à História SQL](02-introducao-historia-sql/)
**⏱️ Alguns dias**

Evolução e importância da linguagem SQL.

**📖 Conteúdo:**
- História do SQL
- Padrões SQL (ANSI)
- Principais SGBDs
- Por que SQL é importante

**🎯 Você vai aprender:**
- ✅ Contexto histórico do SQL
- ✅ Importância da linguagem
- ✅ Diferenças entre SGBDs

---

### 3️⃣ [Interface SQL Plus, Tabelas e Regras](03-interface-sql-plus-tabelas-regras/)
**⏱️ 1 semana**

Primeiros passos com SQL e ferramentas.

**📖 Conteúdo:**
- SQL*Plus e outras interfaces
- Estrutura de uma tabela
- Tipos de dados básicos
- Regras de nomenclatura

**📁 Estrutura:**
- `exemplos/` - Scripts de exemplo

**🎯 Você vai aprender:**
- ✅ Usar ferramentas SQL
- ✅ Entender estrutura de tabelas
- ✅ Conhecer tipos de dados

---

### 4️⃣ [Trabalhando com a Estrutura de Tabelas](04-trabalhando-estrutura-tabelas/)
**⏱️ 1 semana**

Criação e definição de tabelas.

**📖 Conteúdo:**
- Comando `CREATE TABLE`
- Tipos de dados detalhados
- Definição de colunas
- Boas práticas de criação

**📁 Estrutura:**
- `exemplos/` - Scripts de criação de tabelas
- `exercicios/` - Exercícios práticos

**🎯 Você vai aprender:**
- ✅ Criar tabelas com CREATE TABLE
- ✅ Escolher tipos de dados apropriados
- ✅ Definir estruturas de dados

**🔑 Comandos principais:**
```sql
CREATE TABLE nome_tabela (
    coluna1 tipo,
    coluna2 tipo,
    ...
);
```

---

### 5️⃣ [Estrutura das Tabelas, Regras e Relacionamentos](05-estrutura-tabelas-regras-relacionamentos/)
**⏱️ 1 semana**

Constraints e relacionamentos entre tabelas.

**📖 Conteúdo:**
- Chaves primárias (PRIMARY KEY)
- Chaves estrangeiras (FOREIGN KEY)
- Constraints: NOT NULL, UNIQUE, CHECK
- Integridade referencial
- Relacionamentos entre tabelas

**📁 Estrutura:**
- `exemplos/` - Exemplos de constraints
- `exercicios/` - Exercícios práticos

**🎯 Você vai aprender:**
- ✅ Definir chaves primárias e estrangeiras
- ✅ Aplicar constraints
- ✅ Criar relacionamentos entre tabelas
- ✅ Garantir integridade dos dados

**🔑 Comandos principais:**
```sql
PRIMARY KEY, FOREIGN KEY, NOT NULL, UNIQUE, CHECK
```

---

### 6️⃣ [Alteração de Estrutura de uma Tabela](06-alteracao-estrutura-tabela/)
**⏱️ 1 semana**

Comandos DDL para modificação de estruturas.

**📖 Conteúdo:**
- Comando `ALTER TABLE`
- Adicionar colunas (ADD)
- Modificar colunas (MODIFY)
- Remover colunas (DROP)
- Renomear tabelas e colunas
- Comando `DROP TABLE`

**📁 Estrutura:**
- `exemplos/` - Scripts de alteração
- `exercicios/` - Exercícios práticos

**🎯 Você vai aprender:**
- ✅ Modificar estruturas existentes
- ✅ Adicionar e remover colunas
- ✅ Alterar tipos de dados
- ✅ Gerenciar mudanças de estrutura

**🔑 Comandos principais:**
```sql
ALTER TABLE, ADD, MODIFY, DROP, RENAME
```

---

### 7️⃣ [Manipulação de Dados - Inserindo Dados (Parte I)](07-manipulacao-dados-inserindo-parte-i/)
**⏱️ 1 semana**

Comandos INSERT básicos.

**📖 Conteúdo:**
- Comando `INSERT INTO`
- Inserir valores específicos
- Inserir em colunas específicas
- Inserir múltiplos registros
- Boas práticas

**📁 Estrutura:**
- `exemplos/` - Scripts de inserção
- `exercicios/` - Exercícios práticos

**🎯 Você vai aprender:**
- ✅ Inserir dados em tabelas
- ✅ Inserir em colunas específicas
- ✅ Tratar valores NULL
- ✅ Inserir múltiplos registros

**🔑 Comandos principais:**
```sql
INSERT INTO tabela (col1, col2) VALUES (val1, val2);
```

---

### 8️⃣ [Manipulação de Dados - Inserindo Dados (Parte II)](08-manipulacao-dados-inserindo-parte-ii/)
**⏱️ 1 semana**

Comandos INSERT avançados.

**📖 Conteúdo:**
- `INSERT com SELECT`
- Copiar dados entre tabelas
- Inserção em massa
- Técnicas avançadas

**📁 Estrutura:**
- `exemplos/` - Scripts avançados
- `exercicios/` - Exercícios práticos

**🎯 Você vai aprender:**
- ✅ Inserir dados a partir de consultas
- ✅ Copiar estruturas e dados
- ✅ Técnicas de carga de dados
- ✅ Otimizar inserções

**🔑 Comandos principais:**
```sql
INSERT INTO tabela SELECT * FROM outra_tabela;
```

---

### 9️⃣ [Controle de Transações e Criação de Relatórios](09-controle-transacoes-criacao-relatorios/)
**⏱️ 1 semana**

COMMIT, ROLLBACK e relatórios básicos.

**📖 Conteúdo:**
- Conceito de transações
- Comando `COMMIT`
- Comando `ROLLBACK`
- Comando `SAVEPOINT`
- Consultas básicas com SELECT
- Relatórios simples

**📁 Estrutura:**
- `exemplos/` - Scripts de transações
- `exercicios/` - Exercícios práticos

**🎯 Você vai aprender:**
- ✅ Controlar transações
- ✅ Confirmar ou reverter mudanças
- ✅ Usar savepoints
- ✅ Criar relatórios básicos

**🔑 Comandos principais:**
```sql
COMMIT, ROLLBACK, SAVEPOINT, SELECT
```

---

## 🛤️ Trilha de Aprendizado

### Progressão Recomendada:
```
Módulo 01 → 02 → 03 → 04 → 05 → 06 → 07 → 08 → 09
(1 sem)  (3d)  (1s)  (1s)  (1s)  (1s)  (1s)  (1s)  (1s)
```

### Sequência por Tipo de Comando:

#### Semanas 1-3: Conceitos e Estrutura (DDL)
- **Módulos 01-03**: Fundamentos e conceitos
- **Módulos 04-06**: Criação e modificação de tabelas
- **Foco**: `CREATE TABLE`, `ALTER TABLE`, `DROP TABLE`

#### Semanas 4-6: Manipulação de Dados (DML)
- **Módulos 07-09**: Inserção e controle de dados
- **Foco**: `INSERT`, `COMMIT`, `ROLLBACK`, `SELECT` básico

---

## 📖 Como Usar Este Material

### Para Estudantes:
1. **Siga a Ordem**: Os módulos são progressivos
2. **Leia o README**: Cada módulo tem um README detalhado
3. **Execute os Exemplos**: Pratique com os scripts em `exemplos/`
4. **Faça os Exercícios**: Consolide o aprendizado em `exercicios/`
5. **Experimente**: Modifique os scripts para explorar

### Para Professores:
- Material pronto para 6-8 semanas de aula
- Um módulo por semana
- Exemplos e exercícios prontos
- Progressão pedagógica estruturada

### Para Profissionais:
- Use como referência rápida
- Revise conceitos fundamentais
- Consulte sintaxe específica
- Base para SQL Avançado

---

## 🎯 Objetivos de Aprendizado

Ao completar SQL Básico, você será capaz de:

### Modelagem e Design:
- ✅ Entender conceitos básicos de banco de dados
- ✅ Identificar entidades e relacionamentos
- ✅ Projetar estruturas simples

### Comandos DDL (Data Definition Language):
- ✅ Criar tabelas (`CREATE TABLE`)
- ✅ Modificar tabelas (`ALTER TABLE`)
- ✅ Remover tabelas (`DROP TABLE`)
- ✅ Definir tipos de dados apropriados

### Constraints e Integridade:
- ✅ Definir chaves primárias
- ✅ Criar chaves estrangeiras
- ✅ Aplicar constraints (NOT NULL, UNIQUE, CHECK)
- ✅ Garantir integridade referencial

### Comandos DML (Data Manipulation Language):
- ✅ Inserir dados (`INSERT`)
- ✅ Controlar transações (`COMMIT`, `ROLLBACK`)
- ✅ Fazer consultas básicas (`SELECT`)

---

## 🔗 Próximos Passos

### Após Completar SQL Básico:

1. **[SQL Avançado](../sql-avancado/)** - Consultas complexas e otimização
   - Módulos 10-17
   - WHERE, JOINs, Subqueries, Functions
   - UPDATE, DELETE avançados

2. **[Projetos Práticos](../projetos/)** - Aplicar conhecimento
   - Streaming de Música
   - E-commerce
   - Barbearia

3. **[Linguagem Procedure](../linguagem-procedure/)** - Programação no BD
   - PL/SQL
   - Procedures e Functions
   - Triggers

---

## 🛠️ Ferramentas Recomendadas

### Para Iniciantes:
- **[Oracle Live SQL](https://livesql.oracle.com/)** ⭐ Não requer instalação
- **[MySQL Workbench](https://www.mysql.com/products/workbench/)** - Gratuito
- **[DBeaver](https://dbeaver.io/)** - Multiplataforma

### Para Avançados:
- **Oracle SQL Developer** - Profissional
- **DataGrip** - IDE completa

---

## 📚 Referências Bibliográficas

- **Beaulieu, A.** (2020). *Learning SQL: Master SQL Fundamentals*. 3rd Edition. O'Reilly Media.
- **Forta, B.** (2018). *SQL in 10 Minutes, Sams Teach Yourself*. 5th Edition. Sams Publishing.
- **Date, C.J.** (2012). *SQL and Relational Theory*. 2nd Edition. O'Reilly Media.

---

<div align="center">

**📖 Domine os fundamentos de SQL!**

*A base sólida para sua carreira em banco de dados*

📚 **9 módulos progressivos** | 💻 **Exemplos práticos** | ✏️ **Exercícios resolvidos**

</div>

## 🎓 Sistema Exemplo

Durante o curso, utilizamos o **MusiStream** (sistema de streaming de música similar ao Spotify) como exemplo principal. Veja o [README principal](../README.md) para mais detalhes sobre o sistema exemplo.
