# Scripts SQL Oracle - Documentação Técnica Acadêmica

## Objetivos de Aprendizagem

Ao final do estudo deste módulo, o aluno será capaz de:
- Dominar os conceitos fundamentais de SQL no ambiente Oracle
- Implementar estruturas de dados relacionais complexas
- Aplicar técnicas avançadas de consulta e manipulação de dados
- Utilizar recursos específicos do Oracle Database para otimização
- Desenvolver soluções de monitoramento e administração de banco de dados
- Compreender e aplicar boas práticas de desenvolvimento SQL

## Estrutura do Módulo

Este módulo está organizado em quatro grandes áreas de conhecimento, seguindo uma progressão pedagógica do básico ao avançado:

### 📚 [01a-basicos](./01a-basicos) - Fundamentos SQL
### 📈 [01b-basicos](./01b-basicos) - Conceitos Intermediários  
### ⚡ [02-desempenho](./02-desempenho) - Monitoramento e Performance
### 🔧 [03-administracao](./03-administracao) - Administração de Banco de Dados

---

## 1. Fundamentos SQL (01a-basicos)

### 1.1 Conceitos Teóricos

Os **fundamentos SQL** constituem a base essencial para o desenvolvimento de aplicações de banco de dados. O SQL (Structured Query Language) é uma linguagem declarativa padronizada pela ISO/IEC 9075, especificamente projetada para gerenciar dados armazenados em sistemas de gerenciamento de banco de dados relacionais (RDBMS).

#### 1.1.1 Estruturas de Dados Fundamentais

**Tabelas (Tables)**: Estruturas bidimensionais compostas por linhas (registros) e colunas (atributos), representando entidades do mundo real no modelo relacional.

**Exemplo prático do repositório:**
```sql
CREATE TABLE TB_PRODUTO (
    PRODUTO VARCHAR2(10) NOT NULL,
    NOME VARCHAR2(50) NOT NULL,
    EMBALAGEM VARCHAR2(50),
    TAMANHO VARCHAR2(50),
    SABOR VARCHAR2(30),
    PRECO_LISTA NUMBER(5,2)
)
```

#### 1.1.2 Tipos de Dados Oracle

O Oracle Database oferece uma rica variedade de tipos de dados nativos:

- **VARCHAR2(n)**: Strings de tamanho variável até 4000 bytes
- **NUMBER(p,s)**: Números com precisão (p) e escala (s) definidas
- **DATE**: Armazenamento de data e hora
- **CLOB/BLOB**: Objetos grandes de caractere/binário

**Exemplo de estrutura complexa:**
```sql
CREATE TABLE TB_CLIENTES3 (
    CPF VARCHAR(11),
    NOME VARCHAR(100),
    ENDERECO1 VARCHAR(150),
    ENDERECO2 VARCHAR(150),
    BAIRRO VARCHAR(50),
    CIDADE VARCHAR(50),
    ESTADO VARCHAR(2),
    CEP VARCHAR(8),
    IDADE INT,
    SEXO VARCHAR(1),
    LIMITE_CREDITO FLOAT,
    VOLUME_COMPRA FLOAT,
    PRIMEIRA_COMPRA NUMBER(1)
)
```

### 1.2 Scripts Disponíveis

O diretório contém 18 scripts progressivos (SQL_01.sql a SQL_15.sql + SQL_2.4A.sql) que abordam:
- Criação de tabelas básicas
- Definição de constraints
- Inserção de dados
- Consultas fundamentais
- Validação de estruturas

### 1.3 Perguntas Acadêmicas de Aprofundamento

1. **Análise Conceitual**: Compare as vantagens e desvantagens dos tipos VARCHAR2 versus CHAR no Oracle. Em que cenários cada um seria mais apropriado?

2. **Modelagem de Dados**: Analise a estrutura da tabela TB_CLIENTES3. Quais melhorias você sugeriria considerando:
   - Normalização
   - Integridade referencial
   - Performance

3. **Otimização**: Considerando a tabela TB_PRODUTO, que índices você criaria para otimizar consultas por SABOR e PRECO_LISTA? Justifique sua resposta.

---

## 2. Conceitos Intermediários (01b-basicos)

### 2.1 Consultas Condicionais

#### 2.1.1 Fundamentos Teóricos

As **consultas condicionais** implementam a álgebra relacional através de operadores lógicos e relacionais, permitindo a seleção precisa de subconjuntos de dados baseados em critérios específicos.

#### 2.1.2 Operadores Lógicos

**Operador AND**: Conjunção lógica - verdadeiro apenas quando ambas condições são verdadeiras
**Operador OR**: Disjunção lógica - verdadeiro quando pelo menos uma condição é verdadeira  
**Operador NOT**: Negação lógica - inverte o valor lógico da condição

**Exemplos práticos do repositório:**
```sql
-- Conjunção (AND)
SELECT * FROM TABELA_DE_PRODUTOS 
WHERE SABOR = 'Manga' AND TAMANHO = '470 ml';

-- Disjunção (OR)
SELECT * FROM TABELA_DE_PRODUTOS 
WHERE SABOR = 'Manga' OR TAMANHO = '470 ml';

-- Negação com De Morgan
SELECT * FROM TABELA_DE_PRODUTOS 
WHERE NOT (SABOR = 'Manga' AND TAMANHO = '470 ml');
```

#### 2.1.3 Operador IN e Otimização

O operador IN permite comparação eficiente com múltiplos valores, sendo otimizado internamente pelo Oracle:

```sql
-- Equivalência funcional
SELECT * FROM TABELA_DE_PRODUTOS WHERE SABOR IN ('Laranja','Manga');
SELECT * FROM TABELA_DE_PRODUTOS WHERE SABOR = 'Laranja' OR SABOR = 'Manga';
```

#### 2.1.4 Operador LIKE e Pattern Matching

Implementa busca por padrões usando wildcards:
- `%`: Zero ou mais caracteres
- `_`: Exatamente um caractere

### 2.2 Manipulação e Agregação de Dados

#### 2.2.1 Conceitos de Agrupamento

**GROUP BY**: Implementa a operação de agrupamento da álgebra relacional, particionando o conjunto de dados em subgrupos baseados em valores comuns de atributos especificados.

**HAVING**: Aplica condições a grupos após a agregação (equivalente ao WHERE para grupos).

#### 2.2.2 Funções de Agregação

- **COUNT()**: Cardinalidade do conjunto
- **SUM()**: Somatório aritmético
- **AVG()**: Média aritmética
- **MIN()/MAX()**: Valores extremos

#### 2.2.3 Cláusula DISTINCT

Remove duplicatas do resultado, implementando a operação de projeção sem repetição da álgebra relacional.

### 2.3 Operações de Junção (JOIN)

#### 2.3.1 Fundamentos Teóricos

As operações de **junção** implementam o produto cartesiano condicionado da álgebra relacional, permitindo relacionar dados de múltiplas tabelas baseado em critérios de correspondência.

#### 2.3.2 Tipos de JOIN

**INNER JOIN**: Intersecção - retorna apenas registros com correspondência em ambas tabelas
```sql
SELECT A.MATRICULA, B.NOME, COUNT(*) 
FROM NOTAS_FISCAIS A 
INNER JOIN TABELA_DE_VENDEDORES B
ON A.MATRICULA = B.MATRICULA
GROUP BY A.MATRICULA, B.NOME
```

**LEFT JOIN**: União à esquerda - todos registros da tabela esquerda + correspondências da direita
**RIGHT JOIN**: União à direita - todos registros da tabela direita + correspondências da esquerda  
**FULL OUTER JOIN**: União completa - todos registros de ambas tabelas
**CROSS JOIN**: Produto cartesiano completo

#### 2.3.3 Subconsultas (Subqueries)

Consultas aninhadas que implementam composição de operações relacionais:
- **Subconsultas correlacionadas**: Dependem da consulta externa
- **Subconsultas não-correlacionadas**: Independentes da consulta externa

#### 2.3.4 Operador UNION

Implementa a união de conjuntos na álgebra relacional:
- **UNION**: Remove duplicatas
- **UNION ALL**: Preserva duplicatas (mais eficiente)

### 2.4 Funções do Sistema Oracle

#### 2.4.1 Funções de Conversão
- **TO_CHAR()**: Conversão para string
- **TO_NUMBER()**: Conversão para número
- **TO_DATE()**: Conversão para data

#### 2.4.2 Funções de Data
- **SYSDATE**: Data/hora atual do sistema
- **ADD_MONTHS()**: Adição de meses
- **MONTHS_BETWEEN()**: Diferença em meses

#### 2.4.3 Funções de String
- **UPPER()/LOWER()**: Conversão de caso
- **SUBSTR()**: Extração de substring
- **LENGTH()**: Comprimento da string

#### 2.4.4 Funções Matemáticas
- **ROUND()**: Arredondamento
- **TRUNC()**: Truncamento
- **MOD()**: Módulo da divisão

### 2.5 Geração de Relatórios

#### 2.5.1 Conceitos de Business Intelligence

A geração de relatórios implementa conceitos fundamentais de **Business Intelligence (BI)**, transformando dados operacionais em informações estratégicas para tomada de decisão.

#### 2.5.2 Técnicas de Agregação Avançada

- **Análise dimensional**: Agrupamento por múltiplas dimensões
- **Análise temporal**: Tendências e sazonalidades
- **Análise comparativa**: Benchmarking e variações

### 2.6 Perguntas Acadêmicas de Aprofundamento

1. **Álgebra Relacional**: Explique como a operação de junção natural se relaciona com o produto cartesiano e a seleção. Demonstre com um exemplo prático.

2. **Otimização de Consultas**: Compare a eficiência entre uma subconsulta correlacionada e um INNER JOIN para o mesmo resultado. Que fatores influenciam a escolha?

3. **Teoria dos Conjuntos**: Demonstre como as operações UNION, INTERSECT e MINUS se relacionam com os conceitos matemáticos de união, interseção e diferença de conjuntos.

4. **Funções de Agregação**: Analise o comportamento das funções de agregação com valores NULL. Como isso impacta a integridade dos relatórios?

5. **Normalização**: Considerando as tabelas NOTAS_FISCAIS e TABELA_DE_VENDEDORES, analise se a estrutura atende às formas normais. Que problemas de redundância podem ocorrer?

---

## 3. Monitoramento e Performance (02-desempenho)

### 3.1 Conceitos Teóricos de Performance

#### 3.1.1 Arquitetura Oracle e Métricas de Sistema

O **monitoramento de performance** no Oracle Database baseia-se na análise de métricas extraídas das **views dinâmicas de performance (V$ views)**, que fornecem acesso em tempo real às estatísticas internas do sistema de gerenciamento de banco de dados.

#### 3.1.2 Views de Sistema Fundamentais

**V$SYSMETRIC**: Contém métricas agregadas do sistema em intervalos temporais definidos
```sql
SELECT * FROM V$SYSMETRIC;

SELECT METRIC_NAME, VALUE FROM V$SYSMETRIC
WHERE METRIC_NAME IN ('Database CPU Time Ratio','Database Wait Time Ratio');
```

#### 3.1.3 Análise de Wait Events

**Wait Events** representam situações onde sessões do banco aguardam recursos específicos, sendo fundamentais para diagnóstico de performance:

```sql
SELECT * FROM V$SYSTEM_WAIT_CLASS WHERE WAIT_CLASS <> 'Idle';

SELECT SUM(TOTAL_WAITS) AS SUM_TOTAL_WAITS, SUM(TIME_WAITED) AS SUM_TIME_WAITED
FROM V$SYSTEM_WAIT_CLASS WHERE WAIT_CLASS <> 'Idle';
```

#### 3.1.4 Análise Percentual de Eventos

Implementação de análise estatística para identificação de gargalos:
```sql
SELECT A.WAIT_CLASS, 
       ROUND(100*(A.TOTAL_WAITS/B.SUM_TOTAL_WAITS),2) AS PCT_TOTAL_WAITS,
       ROUND(100*(A.TIME_WAITED/B.SUM_TIME_WAITED),2) AS PCT_TIME_WAITED 
FROM (SELECT * FROM V$SYSTEM_WAIT_CLASS WHERE WAIT_CLASS <> 'Idle') A,
     (SELECT SUM(TOTAL_WAITS) AS SUM_TOTAL_WAITS, SUM(TIME_WAITED) AS SUM_TIME_WAITED
      FROM V$SYSTEM_WAIT_CLASS WHERE WAIT_CLASS <> 'Idle') B;
```

### 3.2 Metodologia de Análise de Performance

#### 3.2.1 Abordagem Top-Down

1. **Nível de Sistema**: Métricas globais (CPU, I/O, Memória)
2. **Nível de Sessão**: Análise por usuário/aplicação
3. **Nível de SQL**: Statements específicos
4. **Nível de Objeto**: Tabelas e índices

#### 3.2.2 Indicadores Chave de Performance (KPIs)

- **Database CPU Time Ratio**: Percentual de tempo gasto em processamento vs. espera
- **Database Wait Time Ratio**: Percentual de tempo em eventos de espera
- **Buffer Hit Ratio**: Eficiência do cache de buffer
- **Library Hit Ratio**: Eficiência do cache de SQL

### 3.3 Perguntas Acadêmicas de Aprofundamento

1. **Análise Estatística**: Explique como a análise percentual de wait events contribui para a identificação de gargalos. Que metodologia estatística está sendo aplicada?

2. **Arquitetura de Sistema**: Descreva a relação entre as views V$ e a arquitetura interna do Oracle Database. Como essas métricas são coletadas?

3. **Diagnóstico de Performance**: Dado um cenário onde o "Database Wait Time Ratio" está em 40%, que estratégias de investigação você aplicaria?

4. **Otimização Preventiva**: Que métricas você monitoraria proativamente para evitar problemas de performance? Estabeleça thresholds recomendados.

---

## 4. Administração de Banco de Dados (03-administracao)

### 4.1 Conceitos Teóricos de Administração

#### 4.1.1 Fundamentos de DBA (Database Administrator)

A **administração de banco de dados** engloba um conjunto de atividades técnicas e estratégicas voltadas para garantir:
- **Disponibilidade**: Uptime e acessibilidade do sistema
- **Performance**: Otimização contínua dos recursos
- **Segurança**: Controle de acesso e proteção de dados
- **Integridade**: Consistência e confiabilidade das informações
- **Recuperação**: Estratégias de backup e disaster recovery

#### 4.1.2 Arquivos de Administração

O diretório contém três módulos arquivados de administração:
- **oracle-dba-administracao-arquivos-aula-1.zip**: Fundamentos básicos
- **oracle-dba-administracao-arquivos-aula-2.zip**: Configurações avançadas  
- **oracle-dba-administracao-arquivos-aula-3.zip**: Tópicos especializados

### 4.2 Áreas de Conhecimento DBA

#### 4.2.1 Gerenciamento de Instância
- Configuração de parâmetros de inicialização
- Monitoramento de processos de background
- Gerenciamento de memória (SGA/PGA)

#### 4.2.2 Gerenciamento de Storage
- Administração de tablespaces
- Gerenciamento de datafiles
- Políticas de crescimento automático

#### 4.2.3 Segurança e Controle de Acesso
- Gerenciamento de usuários e roles
- Implementação de políticas de segurança
- Auditoria de atividades

#### 4.2.4 Backup e Recovery
- Estratégias de backup (físico/lógico)
- Point-in-time recovery
- Disaster recovery planning

#### 4.2.5 Tuning e Otimização
- Análise de SQL statements
- Otimização de índices
- Particionamento de tabelas

### 4.3 Perguntas Acadêmicas de Aprofundamento

1. **Arquitetura de Sistema**: Explique a diferença entre instância e banco de dados no Oracle. Como essa separação impacta as estratégias de administração?

2. **Backup Strategy**: Compare as vantagens e desvantagens entre backup físico (RMAN) e backup lógico (Data Pump). Em que cenários cada um é mais apropriado?

3. **Segurança**: Descreva o modelo de segurança multicamadas do Oracle. Como implementar o princípio do menor privilégio?

4. **Performance Tuning**: Explique a metodologia wait-based tuning. Como ela se diferencia das abordagens tradicionais de tuning?

5. **Alta Disponibilidade**: Que tecnologias Oracle você utilizaria para implementar um ambiente de alta disponibilidade? Compare RAC, Data Guard e GoldenGate.

---

## Metodologia de Estudo Recomendada

### Fase 1: Fundamentos (Semanas 1-3)
1. Estudar todos os scripts da pasta **01a-basicos**
2. Praticar criação de tabelas e tipos de dados
3. Implementar exercícios de INSERT/UPDATE/DELETE básicos

### Fase 2: Conceitos Intermediários (Semanas 4-8)
1. **Semana 4**: Consultas condicionais e filtros
2. **Semana 5**: Funções de agregação e GROUP BY
3. **Semana 6**: Operações JOIN e relacionamentos
4. **Semana 7**: Funções built-in do Oracle
5. **Semana 8**: Desenvolvimento de relatórios

### Fase 3: Performance e Administração (Semanas 9-12)
1. **Semanas 9-10**: Análise de performance e métricas
2. **Semanas 11-12**: Conceitos de administração DBA

### Critérios de Avaliação

**Conhecimento Teórico (40%)**:
- Compreensão dos conceitos de álgebra relacional
- Domínio da arquitetura Oracle Database
- Conhecimento de boas práticas de desenvolvimento

**Aplicação Prática (40%)**:
- Capacidade de escrever SQL eficiente
- Resolução de problemas de performance
- Implementação de soluções de monitoramento

**Análise Crítica (20%)**:
- Avaliação de alternativas de solução
- Justificativa técnica das escolhas
- Capacidade de otimização

## Referências Bibliográficas Acadêmicas

### Livros Fundamentais
- **ELMASRI, R.; NAVATHE, S. B.** (2019). *Fundamentals of Database Systems*. 7th Edition. Pearson.
- **GARCIA-MOLINA, H.; ULLMAN, J. D.; WIDOM, J.** (2013). *Database Systems: The Complete Book*. 2nd Edition. Pearson.
- **SILBERSCHATZ, A.; GALVIN, P. B.; GAGNE, G.** (2018). *Operating System Concepts*. 10th Edition. Wiley.

### Documentação Oracle Oficial
- **Oracle Corporation** (2023). *Oracle Database Concepts*. 21c Documentation.
- **Oracle Corporation** (2023). *Oracle Database Administrator's Guide*. 21c Documentation.
- **Oracle Corporation** (2023). *Oracle Database Performance Tuning Guide*. 21c Documentation.

### Artigos Científicos de Referência
- **CODD, E. F.** (1970). "A Relational Model of Data for Large Shared Data Banks". *Communications of the ACM*, 13(6), 377-387.
- **GRAY, J.; REUTER, A.** (1992). *Transaction Processing: Concepts and Techniques*. Morgan Kaufmann.
- **BERNSTEIN, P. A.; NEWCOMER, E.** (2009). *Principles of Transaction Processing*. 2nd Edition. Morgan Kaufmann.

## Recursos Adicionais

### Ferramentas de Desenvolvimento
- **Oracle SQL Developer**: IDE oficial para desenvolvimento SQL
- **Oracle Live SQL**: Ambiente online para prática
- **TOAD**: Ferramenta alternativa de desenvolvimento

### Comunidades e Fóruns
- **Oracle Technology Network (OTN)**
- **Stack Overflow - Oracle Tag**
- **Oracle ACE Program**

### Certificações Relacionadas
- **Oracle Database SQL Certified Associate**
- **Oracle Database Administration Certified Professional** 
- **Oracle Database Performance Tuning Certified Expert**

---

## Próximos Passos

Após completar este módulo, o aluno estará preparado para:
1. Avançar para estudos de **PL/SQL** (linguagem procedural)
2. Explorar recursos avançados como **particionamento** e **paralelismo**
3. Estudar tecnologias de **Big Data** e **Analytics**
4. Investigar soluções de **Cloud Database** (Oracle Cloud Infrastructure)

**Tempo Estimado Total**: 12 semanas (60-80 horas de estudo)

---

*Este documento foi elaborado seguindo padrões acadêmicos para suporte ao ensino de Sistemas de Banco de Dados em cursos de graduação em Ciência da Computação e áreas correlatas.*