# Módulo 02 - Introdução à História SQL

## Objetivos de Aprendizagem

Ao final deste módulo, o aluno será capaz de:
- Compreender a evolução histórica da linguagem SQL
- Conhecer os principais marcos no desenvolvimento de bancos de dados
- Identificar diferentes padrões e versões do SQL
- Entender a importância da padronização
- Reconhecer as variações entre diferentes SGBDs

## Conteúdo Teórico

### 1. Origens dos Bancos de Dados

#### 1.1 Primeiros Sistemas (Década de 1960)
- **Sistema de arquivos**: Dados armazenados em arquivos sequenciais
- **Problemas**: Redundância, inconsistência, dificuldade de acesso
- **Sistemas hierárquicos**: IMS (Information Management System) da IBM

#### 1.2 Modelo de Rede (Década de 1970)
- **CODASYL** (Conference on Data Systems Languages)
- Estrutura em grafo com ponteiros
- Complexidade de navegação e manutenção

### 2. O Modelo Relacional e a Origem do SQL

#### 2.1 Edgar F. Codd e o Artigo Revolucionário (1970)
**Dr. Edgar Frank "Ted" Codd** (1923-2003), matemático britânico trabalhando na IBM, publicou em junho de 1970 o artigo que mudaria para sempre o mundo dos bancos de dados:

**"A Relational Model of Data for Large Shared Data Banks"**

**Principais contribuições de Codd**:
- Introduziu o conceito de modelo relacional
- Definiu a álgebra relacional
- Estabeleceu os fundamentos teóricos para SQL
- Propôs as 12 regras para sistemas relacionais verdadeiros

#### 2.2 As 12 Regras de Codd (Resumo)
1. **Regra da Informação**: Toda informação deve estar em tabelas
2. **Regra do Acesso Garantido**: Cada dado acessível via combinação de nome da tabela, chave primária e nome da coluna
3. **Regra do Tratamento Sistemático de Valores Nulos**: Valores nulos devem ser tratados de forma consistente
4. **Regra do Catálogo Dinâmico Online**: Metadados acessíveis via linguagem de consulta
5. **Regra da Sublinguagem de Dados Abrangente**: Deve existir pelo menos uma linguagem que suporte DDL, DML e integridade

### 3. Evolução do SQL

#### 3.1 SEQUEL - As Primeiras Implementações
**1974-1979: Projeto System R na IBM**
- **SEQUEL** (Structured English Query Language)
- Primeira implementação do modelo relacional de Codd
- Desenvolvido por **Donald Chamberlin** e **Raymond Boyce**

**Características do SEQUEL original**:
```sql
-- Sintaxe inicial do SEQUEL (1974)
SELECT nome, salario
FROM empregados  
WHERE departamento = 'Vendas'
```

#### 3.2 Mudança de Nome: SEQUEL para SQL
**Problema de marca registrada (1982)**:
- SEQUEL era marca registrada da Hawker Siddeley Aircraft Company
- IBM mudou para **SQL** (Structured Query Language)
- Pronuncia-se tanto "ess-cue-ell" quanto "sequel"

### 4. Padronização do SQL

#### 4.1 SQL-86 (SQL1) - Primeiro Padrão
**ANSI X3.135-1986 / ISO 9075:1987**
- Primeiro padrão oficial SQL
- Funcionalidades básicas: SELECT, INSERT, UPDATE, DELETE
- Definição de esquemas e views
- Operações básicas de junção

**Exemplo SQL-86**:
```sql
CREATE TABLE ARTISTA (
    ID_ARTISTA INTEGER,
    NOME_ARTISTA CHAR(50),
    PAIS_ORIGEM CHAR(30)
);

SELECT NOME_ARTISTA 
FROM ARTISTA 
WHERE PAIS_ORIGEM = 'Brasil';
```

#### 4.2 SQL-89 (SQL1 Revisado)
**Pequenas correções e melhorias**:
- Integridade referencial
- Comandos ALTER TABLE
- Junções externas (OUTER JOIN)

#### 4.3 SQL-92 (SQL2) - Grande Expansão
**ISO/IEC 9075:1992**
- Novos tipos de dados (DATE, TIME, TIMESTAMP)
- Junções mais elaboradas
- Operações com strings
- Subconsultas correlacionadas

**Novidades do SQL-92**:
```sql
-- Junção externa
SELECT a.nome_artista, al.titulo
FROM artista a
LEFT OUTER JOIN album al ON a.id_artista = al.id_artista;

-- Operações com strings
SELECT nome_artista
FROM artista
WHERE nome_artista LIKE 'The%';

-- Case expressions
SELECT nome_artista,
       CASE 
           WHEN pais_origem = 'Brasil' THEN 'Nacional'
           ELSE 'Internacional'
       END AS categoria
FROM artista;
```

#### 4.4 SQL-99 (SQL3) - Orientação a Objetos
**ISO/IEC 9075:1999**
- Expressões regulares
- Arrays
- Tipos definidos pelo usuário
- Recursos orientados a objetos

#### 4.5 SQL:2003 - Recursos XML
- Tipos de dados XML
- Funções para manipulação XML
- Procedimentos armazenados
- Recursos window functions

#### 4.6 SQL:2006 - Importação e Definições
- Comandos IMPORT/EXPORT
- Melhorias em tipos definidos pelo usuário
- Novos tipos de dados

#### 4.7 SQL:2008 - MERGE e Triggers
- Comando MERGE
- Comando INSTEAD OF para triggers
- Comando TRUNCATE

#### 4.8 SQL:2011 - Dados Temporais
- Dados temporais
- Janelas de tempo
- Recursos analíticos aprimorados

#### 4.9 SQL:2016 - JSON
- Suporte nativo para JSON
- Funções para manipulação JSON
- Correspondência de padrões de linha

### 5. Principais SGBDs e Suas Características

#### 5.1 Oracle Database (1979)
- **Fundadores**: Larry Ellison, Bob Miner, Ed Oates
- **Empresa**: Relational Software Inc. (depois Oracle Corporation)
- **Características**: Enterprise, PL/SQL, particionamento

#### 5.2 IBM DB2 (1982)
- Evolução do System R
- Forte integração com mainframes
- Suporte multi-plataforma

#### 5.3 Microsoft SQL Server (1989)
- Originalmente baseado no Sybase
- Forte integração com Windows
- T-SQL (Transact-SQL)

#### 5.4 MySQL (1995)
- **Criadores**: Michael Widenius, David Axmark
- Open source
- Muito popular em aplicações web

#### 5.5 PostgreSQL (1996)
- Evolução do projeto POSTGRES (UC Berkeley)
- Open source
- Conformidade com padrões SQL
- Recursos avançados

#### 5.6 SQLite (2000)
- **Criador**: Dr. Richard Hipp
- Embedded database
- Serverless, self-contained

### 6. Dialetos e Variações do SQL

#### 6.1 Principais Diferenças Entre SGBDs

**Tipos de Dados**:
```sql
-- MySQL
CREATE TABLE exemplo (
    id INT AUTO_INCREMENT,
    texto TEXT,
    data DATETIME
);

-- PostgreSQL  
CREATE TABLE exemplo (
    id SERIAL,
    texto TEXT,
    data TIMESTAMP
);

-- Oracle
CREATE TABLE exemplo (
    id NUMBER GENERATED BY DEFAULT AS IDENTITY,
    texto CLOB,
    data DATE
);

-- SQL Server
CREATE TABLE exemplo (
    id INT IDENTITY(1,1),
    texto NVARCHAR(MAX),
    data DATETIME2
);
```

**Tipos de data e hora**

```sql
-- MySQL

CREATE TABLE exemplo_mysql (
    id INT AUTO_INCREMENT PRIMARY KEY,
    somente_data DATE,
    data_hora DATETIME,
    com_timezone TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- PostgreSQL

CREATE TABLE exemplo_postgres (
    id SERIAL PRIMARY KEY,
    somente_data DATE,
    somente_hora TIME,
    data_hora TIMESTAMP,
    data_hora_tz TIMESTAMPTZ -- inclui fuso horário
);

-- Oracle

CREATE TABLE exemplo_oracle (
    id NUMBER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    data_hora DATE,               -- já inclui data e hora
    data_hora_precisa TIMESTAMP,  -- com frações de segundos
    data_hora_tz TIMESTAMP WITH TIME ZONE
);

-- SQL Server

CREATE TABLE exemplo_sqlserver (
    id INT IDENTITY(1,1) PRIMARY KEY,
    somente_data DATE,
    somente_hora TIME,
    data_hora DATETIME,
    data_hora_precisa DATETIME2  -- maior precisão
);
```

* **MySQL:** `DATE`, `DATETIME`, `TIMESTAMP`.
* **PostgreSQL:** mais granular, com `DATE`, `TIME`, `TIMESTAMP`, `TIMESTAMPTZ`.
* **Oracle:** `DATE` já inclui hora; `TIMESTAMP` amplia precisão.
* **SQL Server:** oferece `DATE`, `TIME`, `DATETIME`, `DATETIME2`.


**Funções de String**:
```sql
-- MySQL
SELECT CONCAT(nome, ' - ', pais) FROM artista;

-- PostgreSQL/SQL Server
SELECT nome || ' - ' || pais FROM artista;

-- Oracle
SELECT nome || ' - ' || pais FROM artista;
```

### 7. Tendências Atuais e Futuro

#### 7.1 NewSQL
- Combina ACID com escalabilidade NoSQL
- Exemplos: Google Spanner, CockroachDB

#### 7.2 Cloud SQL
- Amazon RDS, Google Cloud SQL, Azure SQL
- SQL como serviço

#### 7.3 SQL e Big Data
- Apache Spark SQL
- Presto/Trino
- BigQuery SQL

#### 7.4 SQL e Machine Learning
- SQL Server ML Services
- Oracle Machine Learning
- Google BigQuery ML

## Cronologia Resumida

| Ano | Marco | Descrição |
|-----|-------|-----------|
| 1970 | Artigo de Codd | Modelo relacional proposto |
| 1974-1979 | System R/SEQUEL | Primeiro protótipo SQL |
| 1982 | SEQUEL → SQL | Mudança de nome |
| 1986 | SQL-86 | Primeiro padrão ANSI/ISO |
| 1989 | SQL-89 | Primeira revisão |
| 1992 | SQL-92 | Grande expansão |
| 1999 | SQL-99 | Recursos orientados a objetos |
| 2003 | SQL:2003 | Recursos XML |
| 2006-2016 | SQL:2006-2016 | Melhorias contínuas |

## Curiosidades Históricas

### O Nome "SEQUEL"
- Originalmente "SQUARE" (Specifying Queries as Relational Expressions)
- Mudou para "SEQUEL" para soar mais natural
- "Structured English Query Language"

### Primeira Consulta SQL Comercial
```sql
-- Primeira consulta executada no Oracle V2 (1979)
SELECT * FROM emp;
```

### Influência do SEQUEL
- Inspirou outras linguagens: QBE (Query by Example)
- Influenciou desenvolvimento de ferramentas visuais
- Base para ORMs (Object-Relational Mapping)

## Perguntas e Respostas

### 1. Quem foi Edgar F. Codd e qual sua contribuição fundamental para os bancos de dados?

**Resposta**: Edgar Frank "Ted" Codd (1923-2003) foi um matemático britânico da IBM que revolucionou os bancos de dados. Sua contribuição fundamental foi o artigo "A Relational Model of Data for Large Shared Data Banks" (1970), onde:
- Introduziu o **modelo relacional** baseado na teoria dos conjuntos
- Definiu a **álgebra relacional** como base matemática
- Estabeleceu os **fundamentos teóricos** que levaram ao desenvolvimento do SQL
- Propôs as **12 regras** para sistemas relacionais verdadeiros

### 2. Por que o SQL substituiu os modelos hierárquico e de rede?

**Resposta**: O SQL e o modelo relacional se tornaram dominantes porque:
- **Simplicidade**: Interface declarativa vs. navegação complexa por ponteiros
- **Flexibilidade**: Estrutura tabular permite consultas ad-hoc facilmente
- **Independência**: Separação entre estrutura lógica e implementação física
- **Padronização**: Base matemática sólida permitiu padronização internacional
- **Produtividade**: Redução significativa no tempo de desenvolvimento

### 3. Qual a diferença entre SEQUEL e SQL?

**Resposta**: 
- **SEQUEL** (1974-1982): "Structured English Query Language" - nome original desenvolvido na IBM para o projeto System R
- **SQL** (1982-presente): "Structured Query Language" - nome alterado por questões de marca registrada
- **Funcionalidade**: Essencialmente a mesma linguagem, apenas mudança de nome
- **Importância**: SEQUEL foi a primeira implementação prática dos conceitos de Codd

### 4. Quais foram os marcos mais importantes na evolução do SQL?

**Resposta**: Os principais marcos foram:
- **1970**: Artigo de Codd estabelecendo fundamentos teóricos
- **1974-1982**: Desenvolvimento do SEQUEL/SQL no projeto System R (IBM)
- **1986**: SQL-86 (SQL1) - Primeiro padrão internacional ISO
- **1992**: SQL-92 (SQL2) - Grande expansão com outer joins, operações de string
- **1999**: SQL-99 (SQL3) - Recursos orientados a objetos, expressões regulares
- **2003**: SQL:2003 - Recursos XML, window functions
- **2016**: SQL:2016 - Suporte nativo a JSON

### 5. Por que existem variações do SQL entre diferentes SGBDs?

**Resposta**: As variações existem devido a:
- **Competição comercial**: Vendors adicionam recursos proprietários para diferenciação
- **Evolução histórica**: Implementações anteriores aos padrões ISO
- **Necessidades específicas**: Otimizações para diferentes casos de uso
- **Extensões avançadas**: Recursos especializados não cobertos pelo padrão
- **Compatibilidade**: Manutenção de recursos legados

Exemplos: PL/SQL (Oracle), T-SQL (SQL Server), PL/pgSQL (PostgreSQL)

### 6. Qual a importância da padronização ISO para o SQL?

**Resposta**: A padronização ISO é fundamental porque:
- **Portabilidade**: Facilita migração entre diferentes SGBDs
- **Interoperabilidade**: Permite integração entre sistemas diversos
- **Qualidade**: Estabelece práticas e sintaxes consistentes
- **Educação**: Fornece base comum para ensino e aprendizado
- **Evolução**: Processo formal para incorporar novos recursos

### 7. Como o SQL se adapta às tendências modernas (Big Data, NoSQL, Cloud)?

**Resposta**: O SQL tem se adaptado através de:
- **Padrões recentes**: SQL:2016 com JSON, SQL:2023 com multi-dimensional arrays
- **Extensões de Big Data**: Integração com Hadoop, Spark (SQL-on-Hadoop)
- **Híbrido SQL/NoSQL**: Bancos que suportam tanto SQL quanto documentos JSON
- **Cloud SQL**: Serviços gerenciados que abstraem infraestrutura
- **Analytics**: Window functions, CTEs para análises complexas
- **Streaming**: Extensões para processamento de dados em tempo real

## Referências Bibliográficas

### Artigos Fundamentais
1. **Codd, E.F.** (1970). "A Relational Model of Data for Large Shared Data Banks". *Communications of the ACM*, 13(6), 377-387.

2. **Chamberlin, D.D. & Boyce, R.F.** (1974). "SEQUEL: A Structured English Query Language". *Proceedings of the 1974 ACM SIGFIDET Workshop*, 249-264.

3. **Astrahan, M.M. et al.** (1976). "System R: Relational Approach to Database Management". *ACM Transactions on Database Systems*, 1(2), 97-137.

### Livros Históricos
1. **Date, C.J.** (2019). *Database Design and Relational Theory: Normal Forms and All That Jazz*. 2nd Edition. Apress.

2. **Melton, J. & Simon, A.R.** (2001). *SQL:1999 - Understanding Relational Language Components*. Morgan Kaufmann.

3. **Gulutzan, P. & Pelzer, T.** (1999). *SQL-99 Complete, Really*. CMP Books.

### Recursos Online
- **SQL Standards**: ISO/IEC 9075 (partes 1-14)
- **Oracle SQL History**: Oracle Documentation Archive
- **IBM DB2 Timeline**: IBM Knowledge Center
- **MySQL History**: MySQL Documentation

---

**Módulo Anterior**: [01 - Introdução à Modelagem de Dados](../01-introducao-modelagem-dados/README.md)
**Próximo Módulo**: [03 - Interface SQL Plus, Tabelas e Regras](../03-interface-sql-plus-tabelas-regras/README.md)

**Nota Histórica**: A compreensão da evolução do SQL ajuda a entender por que certas sintaxes existem e como diferentes SGBDs implementam recursos de formas ligeiramente diferentes.
