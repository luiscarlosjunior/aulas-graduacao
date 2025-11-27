# 📚 Revisão Completa - Linguagem SQL

## 🎯 Objetivo

Este documento apresenta uma **revisão abrangente e aprofundada** de todos os conceitos de Linguagem SQL cobertos neste repositório. Serve como material de referência para consolidar o aprendizado e preparar para avaliações, projetos práticos e uso profissional de bancos de dados relacionais.

O conteúdo está organizado em seções temáticas, cada uma com explicações teóricas profundas, exemplos práticos e casos de uso do sistema MusiStream (streaming de música).

---

## 📋 Índice de Conteúdo

1. [Fundamentos de Banco de Dados e SQL](#1-fundamentos-de-banco-de-dados-e-sql)
2. [Linguagem de Definição de Dados (DDL)](#2-linguagem-de-definição-de-dados-ddl)
3. [Linguagem de Manipulação de Dados (DML)](#3-linguagem-de-manipulação-de-dados-dml)
4. [Consultas SELECT e Relatórios](#4-consultas-select-e-relatórios)
5. [Filtros e Operadores](#5-filtros-e-operadores)
6. [Operadores Aritméticos e Funções Matemáticas](#6-operadores-aritméticos-e-funções-matemáticas)
7. [Funções de Banco de Dados](#7-funções-de-banco-de-dados)
8. [Subqueries (Subconsultas)](#8-subqueries-subconsultas)
9. [JOINs e Múltiplas Tabelas](#9-joins-e-múltiplas-tabelas)
10. [Controle de Transações](#10-controle-de-transações)
11. [Otimização e Boas Práticas](#11-otimização-e-boas-práticas)

---

## 1. Fundamentos de Banco de Dados e SQL

### 1.1 O que é SQL?

**SQL (Structured Query Language)** é a linguagem padrão para gerenciamento de bancos de dados relacionais. Desenvolvida nos anos 1970 pela IBM e padronizada pela ANSI/ISO, é utilizada em praticamente todos os sistemas de gerenciamento de banco de dados (SGBDs) modernos.

**Características principais:**
- **Declarativa**: Você especifica O QUE quer, não COMO fazer
- **Baseada em conjuntos**: Opera sobre conjuntos de dados
- **Padronizada**: Sintaxe consistente entre diferentes SGBDs
- **Poderosa**: Permite desde consultas simples até análises complexas

### 1.2 Divisões da Linguagem SQL

A linguagem SQL é dividida em subcategorias conforme o tipo de operação:

| Categoria | Significado | Comandos Principais | Propósito |
|-----------|-------------|---------------------|-----------|
| **DDL** | Data Definition Language | CREATE, ALTER, DROP, TRUNCATE | Definir estrutura do banco |
| **DML** | Data Manipulation Language | INSERT, UPDATE, DELETE, SELECT | Manipular dados |
| **DCL** | Data Control Language | GRANT, REVOKE | Controlar acesso |
| **TCL** | Transaction Control Language | COMMIT, ROLLBACK, SAVEPOINT | Gerenciar transações |

### 1.3 Modelo Relacional

O modelo relacional organiza dados em **tabelas (relações)** compostas por:
- **Linhas (tuplas)**: Representam registros individuais
- **Colunas (atributos)**: Representam características dos dados
- **Chaves primárias**: Identificadores únicos de cada registro
- **Chaves estrangeiras**: Estabelecem relacionamentos entre tabelas

**Exemplo do Sistema MusiStream:**

```
ARTISTA (id_artista PK, nome_artista, pais_origem, data_formacao, ativo)
    ↓ 1:N
ALBUM (id_album PK, titulo, id_artista FK, ano_lancamento, numero_faixas)
    ↓ 1:N
MUSICA (id_musica PK, titulo, id_album FK, id_genero FK, duracao, numero_faixa)
```

---

## 2. Linguagem de Definição de Dados (DDL)

### 2.1 CREATE TABLE - Criando Tabelas

O comando **CREATE TABLE** define a estrutura de uma nova tabela, incluindo colunas, tipos de dados e constraints.

**Sintaxe Completa:**

```sql
CREATE TABLE nome_tabela (
    nome_coluna TIPO_DADO [CONSTRAINT] [...],
    nome_coluna TIPO_DADO [CONSTRAINT] [...],
    [CONSTRAINT nome_constraint TIPO_CONSTRAINT (colunas)]
);
```

**Exemplo Prático - Tabela de Artistas:**

```sql
CREATE TABLE artista (
    id_artista      INTEGER       PRIMARY KEY,
    nome_artista    VARCHAR2(200) NOT NULL,
    pais_origem     VARCHAR2(50),
    data_formacao   DATE,
    numero_membros  INTEGER       DEFAULT 1,
    biografia       CLOB,
    ativo           CHAR(1)       DEFAULT 'S' CHECK (ativo IN ('S', 'N')),
    data_cadastro   TIMESTAMP     DEFAULT SYSTIMESTAMP
);
```

**Tipos de Dados Principais (Oracle):**

| Tipo | Descrição | Exemplo |
|------|-----------|---------|
| VARCHAR2(n) | Texto variável até n caracteres | VARCHAR2(200) |
| CHAR(n) | Texto fixo de n caracteres | CHAR(1) |
| NUMBER(p,s) | Número com precisão p e escala s | NUMBER(10,2) |
| INTEGER | Número inteiro | INTEGER |
| DATE | Data e hora | DATE |
| TIMESTAMP | Data/hora com precisão de frações de segundo | TIMESTAMP |
| CLOB | Texto longo (até 4GB) | CLOB |
| BLOB | Dados binários | BLOB |

### 2.2 Constraints (Restrições)

As constraints garantem **integridade dos dados**:

**PRIMARY KEY - Chave Primária:**
```sql
-- Forma inline
id_artista INTEGER PRIMARY KEY

-- Forma nomeada
CONSTRAINT pk_artista PRIMARY KEY (id_artista)
```

**FOREIGN KEY - Chave Estrangeira:**
```sql
-- Referência a outra tabela
CREATE TABLE album (
    id_album INTEGER PRIMARY KEY,
    titulo VARCHAR2(200) NOT NULL,
    id_artista INTEGER,
    CONSTRAINT fk_album_artista 
        FOREIGN KEY (id_artista) 
        REFERENCES artista(id_artista)
        ON DELETE CASCADE  -- Opção: CASCADE, SET NULL, RESTRICT
);
```

**NOT NULL - Obrigatório:**
```sql
nome_artista VARCHAR2(200) NOT NULL
```

**UNIQUE - Valor Único:**
```sql
email VARCHAR2(150) UNIQUE
-- Ou
CONSTRAINT uq_usuario_email UNIQUE (email)
```

**CHECK - Validação:**
```sql
duracao INTEGER CHECK (duracao > 0),
avaliacao NUMBER(2,1) CHECK (avaliacao BETWEEN 0 AND 5),
tipo_assinatura VARCHAR2(20) CHECK (tipo_assinatura IN ('gratuito', 'basico', 'premium'))
```

**DEFAULT - Valor Padrão:**
```sql
ativo CHAR(1) DEFAULT 'S',
data_cadastro TIMESTAMP DEFAULT SYSTIMESTAMP,
numero_faixas INTEGER DEFAULT 0
```

### 2.3 ALTER TABLE - Modificando Estrutura

**Adicionar coluna:**
```sql
ALTER TABLE artista ADD website VARCHAR2(300);
```

**Modificar coluna:**
```sql
ALTER TABLE artista MODIFY nome_artista VARCHAR2(300);
```

**Remover coluna:**
```sql
ALTER TABLE artista DROP COLUMN website;
```

**Adicionar constraint:**
```sql
ALTER TABLE album ADD CONSTRAINT fk_album_artista 
    FOREIGN KEY (id_artista) REFERENCES artista(id_artista);
```

**Remover constraint:**
```sql
ALTER TABLE album DROP CONSTRAINT fk_album_artista;
```

### 2.4 DROP e TRUNCATE

**DROP TABLE - Remove tabela completamente:**
```sql
DROP TABLE album;                    -- Falha se houver FK referenciando
DROP TABLE album CASCADE CONSTRAINTS; -- Remove FKs automaticamente
```

**TRUNCATE - Remove todos os dados:**
```sql
TRUNCATE TABLE historico_reproducao;  -- Mais rápido que DELETE
-- Não pode ser desfeito (não gera log de transação)
```

### 2.5 Sequences - Geração Automática de IDs

```sql
-- Criar sequence
CREATE SEQUENCE seq_artista
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 999999999
    NOCACHE
    NOCYCLE;

-- Usar em INSERT
INSERT INTO artista (id_artista, nome_artista)
VALUES (seq_artista.NEXTVAL, 'The Beatles');

-- NEXTVAL: próximo valor
-- CURRVAL: valor atual (só após NEXTVAL na sessão)
```

---

## 3. Linguagem de Manipulação de Dados (DML)

### 3.1 INSERT - Inserindo Dados

**Inserção Simples:**
```sql
INSERT INTO artista (id_artista, nome_artista, pais_origem, data_formacao)
VALUES (1, 'The Beatles', 'Reino Unido', DATE '1960-08-17');
```

**Inserção Múltipla (Oracle INSERT ALL):**
```sql
INSERT ALL
    INTO genero (id_genero, nome_genero) VALUES (1, 'Rock')
    INTO genero (id_genero, nome_genero) VALUES (2, 'Pop')
    INTO genero (id_genero, nome_genero) VALUES (3, 'Jazz')
    INTO genero (id_genero, nome_genero) VALUES (4, 'Blues')
    INTO genero (id_genero, nome_genero) VALUES (5, 'Eletrônico')
SELECT * FROM dual;
```

**INSERT... SELECT - Inserção de Consulta:**
```sql
-- Copiar dados de outra tabela com transformação
INSERT INTO estatistica_artista (id_artista, nome_artista, total_reproducoes)
SELECT 
    ar.id_artista,
    ar.nome_artista,
    COUNT(hr.id_historico) as total
FROM artista ar
JOIN album al ON ar.id_artista = al.id_artista
JOIN musica m ON al.id_album = m.id_album
JOIN historico_reproducao hr ON m.id_musica = hr.id_musica
GROUP BY ar.id_artista, ar.nome_artista;
```

**Inserção Condicional (NOT EXISTS):**
```sql
-- Inserir apenas se não existir
INSERT INTO genero (id_genero, nome_genero, descricao)
SELECT 100, 'Progressive Rock', 'Rock progressivo dos anos 70'
FROM dual
WHERE NOT EXISTS (
    SELECT 1 FROM genero WHERE nome_genero = 'Progressive Rock'
);
```

### 3.2 MERGE - Inserir ou Atualizar (UPSERT)

O comando MERGE combina INSERT e UPDATE em uma única operação:

```sql
MERGE INTO estatistica_mensal est
USING (
    SELECT 
        m.id_musica,
        EXTRACT(MONTH FROM hr.data_reproducao) as mes,
        EXTRACT(YEAR FROM hr.data_reproducao) as ano,
        COUNT(*) as total_reproducoes
    FROM musica m
    JOIN historico_reproducao hr ON m.id_musica = hr.id_musica
    WHERE hr.data_reproducao >= TRUNC(SYSDATE, 'MM')
    GROUP BY m.id_musica, 
             EXTRACT(MONTH FROM hr.data_reproducao), 
             EXTRACT(YEAR FROM hr.data_reproducao)
) src ON (est.id_musica = src.id_musica 
          AND est.mes = src.mes 
          AND est.ano = src.ano)
WHEN MATCHED THEN
    UPDATE SET est.total_reproducoes = src.total_reproducoes
WHEN NOT MATCHED THEN
    INSERT (id_musica, mes, ano, total_reproducoes)
    VALUES (src.id_musica, src.mes, src.ano, src.total_reproducoes);
```

**Quando usar MERGE:**
- Sincronização de dados entre sistemas
- Cargas incrementais em data warehouses
- Atualização de tabelas de cache ou summary

### 3.3 UPDATE - Atualizando Dados

**UPDATE Simples:**
```sql
UPDATE artista 
SET pais_origem = 'Inglaterra',
    data_formacao = DATE '1960-01-01'
WHERE nome_artista = 'The Beatles';
```

**UPDATE com Subconsulta:**
```sql
-- Atualizar estatísticas de artistas
UPDATE artista a
SET popularidade = (
    SELECT COUNT(DISTINCT hr.id_usuario)
    FROM album al
    JOIN musica m ON al.id_album = m.id_album
    JOIN historico_reproducao hr ON m.id_musica = hr.id_musica
    WHERE al.id_artista = a.id_artista
);
```

**UPDATE com JOIN (Sintaxe Oracle):**
```sql
UPDATE (
    SELECT al.numero_faixas, 
           (SELECT COUNT(*) FROM musica WHERE id_album = al.id_album) as contagem
    FROM album al
) 
SET numero_faixas = contagem;
```

### 3.4 DELETE - Removendo Dados

**DELETE Simples:**
```sql
DELETE FROM historico_reproducao
WHERE data_reproducao < SYSDATE - 365;  -- Remover histórico > 1 ano
```

**DELETE com Subconsulta:**
```sql
-- Remover músicas de álbuns inativos
DELETE FROM musica
WHERE id_album IN (
    SELECT id_album FROM album WHERE ativo = 'N'
);
```

**Cuidados com DELETE:**
```sql
-- SEMPRE usar WHERE para evitar remoção total
DELETE FROM artista WHERE id_artista = 123;

-- Verificar antes de deletar
SELECT COUNT(*) FROM artista WHERE id_artista = 123;

-- Usar transação para segurança
SAVEPOINT antes_delete;
DELETE FROM artista WHERE id_artista = 123;
-- Se erro: ROLLBACK TO antes_delete;
-- Se ok: COMMIT;
```

---

## 4. Consultas SELECT e Relatórios

### 4.1 Ordem de Execução do SELECT

A ordem de **escrita** difere da ordem de **execução**:

| Ordem Escrita | Cláusula | Ordem Execução | Função |
|---------------|----------|----------------|--------|
| 1 | SELECT | 5 | Projeta colunas |
| 2 | FROM | 1 | Identifica tabelas |
| 3 | WHERE | 2 | Filtra linhas |
| 4 | GROUP BY | 3 | Agrupa resultados |
| 5 | HAVING | 4 | Filtra grupos |
| 6 | ORDER BY | 6 | Ordena resultado |
| 7 | LIMIT/FETCH | 7 | Limita quantidade |

**Implicações Práticas:**

```sql
-- Aliases do SELECT não funcionam no WHERE (execução anterior)
-- ❌ INCORRETO
SELECT nome_artista AS artista FROM artista WHERE artista LIKE 'The%';

-- ✅ CORRETO  
SELECT nome_artista AS artista FROM artista WHERE nome_artista LIKE 'The%';

-- Aliases funcionam no ORDER BY (execução posterior)
-- ✅ CORRETO
SELECT nome_artista AS artista FROM artista ORDER BY artista;
```

### 4.2 SELECT Básico

**Selecionar todas as colunas:**
```sql
SELECT * FROM artista;  -- Evitar em produção
```

**Selecionar colunas específicas:**
```sql
SELECT nome_artista, pais_origem, data_formacao
FROM artista
WHERE ativo = 'S';
```

**Aliases para colunas:**
```sql
SELECT 
    nome_artista AS "Nome do Artista",
    pais_origem AS "País",
    data_formacao AS "Fundação"
FROM artista;
```

**DISTINCT - Valores únicos:**
```sql
SELECT DISTINCT pais_origem FROM artista ORDER BY pais_origem;
```

### 4.3 Ordenação (ORDER BY)

```sql
-- Ordenação simples
SELECT nome_artista, pais_origem FROM artista ORDER BY nome_artista;

-- Ordenação descendente
SELECT titulo, ano_lancamento FROM album ORDER BY ano_lancamento DESC;

-- Múltiplas colunas (primeiro país, depois nome)
SELECT nome_artista, pais_origem 
FROM artista 
ORDER BY pais_origem ASC, nome_artista ASC;
```

### 4.4 Limitação de Resultados

**Oracle 12c+ (FETCH FIRST):**
```sql
-- Top 10 álbuns mais recentes
SELECT titulo, ano_lancamento
FROM album
ORDER BY ano_lancamento DESC
FETCH FIRST 10 ROWS ONLY;

-- Paginação (página 3, 20 registros por página)
SELECT titulo FROM album
ORDER BY titulo
OFFSET 40 ROWS FETCH NEXT 20 ROWS ONLY;
```

**Oracle (ROWNUM - sintaxe clássica):**
```sql
-- Requer subconsulta para ORDER BY funcionar
SELECT * FROM (
    SELECT titulo, ano_lancamento FROM album ORDER BY ano_lancamento DESC
) WHERE ROWNUM <= 10;
```

---

## 5. Filtros e Operadores

### 5.1 Operadores de Comparação

| Operador | Descrição | Exemplo |
|----------|-----------|---------|
| = | Igual a | WHERE pais = 'Brasil' |
| <> ou != | Diferente de | WHERE status <> 'Inativo' |
| > | Maior que | WHERE duracao > 300 |
| < | Menor que | WHERE ano < 2000 |
| >= | Maior ou igual | WHERE avaliacao >= 4 |
| <= | Menor ou igual | WHERE preco <= 100 |

```sql
-- Músicas com mais de 5 minutos (300 segundos)
SELECT titulo, duracao/60 AS minutos
FROM musica
WHERE duracao > 300
ORDER BY duracao DESC;

-- Álbuns lançados antes de 1980
SELECT titulo, ano_lancamento
FROM album
WHERE ano_lancamento < 1980
ORDER BY ano_lancamento;
```

### 5.2 Operadores Lógicos

**AND - Todas condições verdadeiras:**
```sql
-- Artistas brasileiros ativos formados após 2000
SELECT nome_artista, data_formacao
FROM artista
WHERE pais_origem = 'Brasil' 
  AND ativo = 'S'
  AND data_formacao > DATE '2000-01-01';
```

**OR - Pelo menos uma condição verdadeira:**
```sql
-- Artistas do Brasil OU Portugal
SELECT nome_artista, pais_origem
FROM artista
WHERE pais_origem = 'Brasil' OR pais_origem = 'Portugal';
```

**NOT - Negação:**
```sql
-- Artistas que não são do Brasil
SELECT nome_artista FROM artista WHERE NOT pais_origem = 'Brasil';
-- Ou equivalente:
SELECT nome_artista FROM artista WHERE pais_origem <> 'Brasil';
```

**Precedência e Parênteses:**
```sql
-- Precedência: NOT > AND > OR
-- ❌ AMBÍGUO (AND avaliado antes de OR)
SELECT * FROM artista
WHERE pais_origem = 'Brasil' OR pais_origem = 'Portugal' AND numero_membros > 3;
-- Resultado: Brasil (todos) + Portugal com >3 membros

-- ✅ CLARO (parênteses explícitos)
SELECT * FROM artista
WHERE (pais_origem = 'Brasil' OR pais_origem = 'Portugal') AND numero_membros > 3;
-- Resultado: Brasil ou Portugal, apenas com >3 membros
```

### 5.3 Operador IN

```sql
-- Artistas de países específicos
SELECT nome_artista, pais_origem
FROM artista
WHERE pais_origem IN ('Brasil', 'Argentina', 'Chile', 'Uruguai');

-- Equivalente a múltiplos OR
WHERE pais_origem = 'Brasil' 
   OR pais_origem = 'Argentina' 
   OR pais_origem = 'Chile' 
   OR pais_origem = 'Uruguai';

-- IN com subconsulta
SELECT nome_artista
FROM artista
WHERE id_artista IN (
    SELECT DISTINCT id_artista FROM album WHERE ano_lancamento >= 2020
);

-- NOT IN (cuidado com NULLs!)
SELECT nome_artista
FROM artista
WHERE pais_origem NOT IN ('Estados Unidos', 'Reino Unido');
```

### 5.4 Operador BETWEEN

```sql
-- Álbuns lançados na década de 70 (BETWEEN é INCLUSIVO)
SELECT titulo, ano_lancamento
FROM album
WHERE ano_lancamento BETWEEN 1970 AND 1979;

-- Equivalente a:
WHERE ano_lancamento >= 1970 AND ano_lancamento <= 1979;

-- Músicas com duração entre 3 e 5 minutos
SELECT titulo, duracao/60 AS minutos
FROM musica
WHERE duracao BETWEEN 180 AND 300;

-- Com datas (cuidado com timestamps!)
SELECT nome_usuario, data_cadastro
FROM usuario
WHERE data_cadastro BETWEEN DATE '2023-01-01' AND DATE '2023-12-31';
```

### 5.5 Operador LIKE

**Wildcards:**
- `%` = zero ou mais caracteres
- `_` = exatamente um caractere

```sql
-- Começa com "The"
SELECT nome_artista FROM artista WHERE nome_artista LIKE 'The%';
-- Resultado: The Beatles, The Rolling Stones, The Who

-- Termina com "Band"
SELECT nome_artista FROM artista WHERE nome_artista LIKE '%Band';
-- Resultado: Dave Matthews Band, Blues Band

-- Contém "Rock"
SELECT nome_artista FROM artista WHERE nome_artista LIKE '%Rock%';
-- Resultado: Rock Nation, The Rockers, Hard Rock Cafe

-- Exatamente 4 caracteres
SELECT nome_genero FROM genero WHERE nome_genero LIKE '____';
-- Resultado: Jazz, Rock, Soul

-- Case-insensitive (Oracle)
SELECT nome_artista FROM artista WHERE UPPER(nome_artista) LIKE '%ROCK%';
```

**Performance de LIKE:**
```sql
-- ✅ EFICIENTE (pode usar índice)
WHERE nome_artista LIKE 'Beatles%'

-- ❌ INEFICIENTE (não pode usar índice)
WHERE nome_artista LIKE '%Beatles'
WHERE nome_artista LIKE '%Beatles%'
```

### 5.6 IS NULL / IS NOT NULL

**NULL representa ausência de valor** - não é zero, não é string vazia.

```sql
-- ❌ INCORRETO (comparação com NULL sempre retorna NULL)
SELECT * FROM artista WHERE biografia = NULL;    -- Não funciona!
SELECT * FROM artista WHERE biografia <> NULL;   -- Não funciona!

-- ✅ CORRETO
SELECT * FROM artista WHERE biografia IS NULL;      -- Sem biografia
SELECT * FROM artista WHERE biografia IS NOT NULL;  -- Com biografia

-- Combinando com outros filtros
SELECT nome_artista, pais_origem
FROM artista
WHERE pais_origem = 'Brasil' OR pais_origem IS NULL;
-- Brasileiros + artistas sem país definido
```

**Tratamento de NULL:**
```sql
-- COALESCE: primeiro valor não-NULL
SELECT nome_artista, COALESCE(biografia, 'Biografia não disponível') AS bio
FROM artista;

-- NVL (Oracle): valor padrão para NULL
SELECT nome_artista, NVL(pais_origem, 'Não informado') AS pais
FROM artista;

-- NVL2 (Oracle): valor se não-NULL, outro se NULL
SELECT nome_artista, NVL2(biografia, 'Tem biografia', 'Sem biografia') AS status
FROM artista;
```

---

## 6. Operadores Aritméticos e Funções Matemáticas

### 6.1 Operadores Aritméticos

| Operador | Descrição | Exemplo |
|----------|-----------|---------|
| + | Adição | duracao + 60 |
| - | Subtração | ano_atual - ano_lancamento |
| * | Multiplicação | preco * 1.1 |
| / | Divisão | duracao / 60 |

```sql
-- Cálculos em consultas
SELECT 
    titulo,
    duracao AS segundos,
    duracao / 60 AS minutos_inteiro,
    duracao / 60.0 AS minutos_decimal,
    ROUND(duracao / 60.0, 2) AS minutos_arredondado,
    FLOOR(duracao / 60) || ':' || LPAD(MOD(duracao, 60), 2, '0') AS formato_mm_ss
FROM musica;
```

### 6.2 Funções de Arredondamento

```sql
SELECT 
    titulo,
    duracao / 60.0 AS original,
    ROUND(duracao / 60.0, 2) AS arredondado_2_casas,
    CEIL(duracao / 60.0) AS arredondado_cima,
    FLOOR(duracao / 60.0) AS arredondado_baixo,
    TRUNC(duracao / 60.0, 1) AS truncado_1_casa
FROM musica;

-- ROUND: arredondamento padrão
-- CEIL: sempre arredonda para cima
-- FLOOR: sempre arredonda para baixo
-- TRUNC: remove decimais sem arredondar
```

### 6.3 Funções Estatísticas

```sql
SELECT 
    nome_genero,
    COUNT(*) AS total_musicas,
    MIN(duracao) AS duracao_minima,
    MAX(duracao) AS duracao_maxima,
    ROUND(AVG(duracao), 2) AS duracao_media,
    SUM(duracao) AS duracao_total,
    ROUND(STDDEV(duracao), 2) AS desvio_padrao,
    ROUND(VARIANCE(duracao), 2) AS variancia
FROM musica m
JOIN genero g ON m.id_genero = g.id_genero
GROUP BY g.id_genero, nome_genero
ORDER BY total_musicas DESC;
```

### 6.4 Evitando Divisão por Zero

```sql
-- Usando CASE
SELECT 
    nome_artista,
    total_reproducoes,
    total_musicas,
    CASE 
        WHEN total_musicas = 0 THEN 0
        ELSE total_reproducoes / total_musicas
    END AS media_por_musica
FROM estatisticas_artista;

-- Usando NULLIF (converte zero para NULL)
SELECT 
    titulo_album,
    duracao_total / NULLIF(numero_faixas, 0) AS duracao_media_faixa
FROM album;
-- Se numero_faixas = 0, retorna NULL (não erro)

-- Com COALESCE para valor padrão
SELECT 
    COALESCE(receita / NULLIF(numero_vendas, 0), 0) AS preco_medio
FROM vendas_album;
```

---

## 7. Funções de Banco de Dados

### 7.1 Funções de String

```sql
SELECT 
    nome_artista AS original,
    UPPER(nome_artista) AS maiusculo,
    LOWER(nome_artista) AS minusculo,
    INITCAP(nome_artista) AS primeira_maiuscula,
    LENGTH(nome_artista) AS tamanho,
    TRIM(nome_artista) AS sem_espacos,
    SUBSTR(nome_artista, 1, 10) AS primeiros_10,
    REPLACE(nome_artista, ' ', '_') AS com_underscore,
    INSTR(nome_artista, 'The') AS posicao_the
FROM artista;
```

**Concatenação:**
```sql
-- Operador || (Oracle)
SELECT nome_artista || ' (' || pais_origem || ')' AS artista_info FROM artista;

-- Função CONCAT
SELECT CONCAT(nome_artista, CONCAT(' - ', pais_origem)) AS artista_info FROM artista;
```

### 7.2 Funções de Data e Tempo

```sql
-- Funções de extração
SELECT 
    titulo,
    data_lancamento,
    EXTRACT(YEAR FROM data_lancamento) AS ano,
    EXTRACT(MONTH FROM data_lancamento) AS mes,
    EXTRACT(DAY FROM data_lancamento) AS dia,
    TO_CHAR(data_lancamento, 'Day') AS dia_semana,
    TO_CHAR(data_lancamento, 'Month YYYY') AS mes_ano
FROM album;

-- Cálculos com datas
SELECT 
    titulo,
    data_lancamento,
    SYSDATE - data_lancamento AS dias_desde_lancamento,
    ADD_MONTHS(data_lancamento, 12) AS um_ano_depois,
    MONTHS_BETWEEN(SYSDATE, data_lancamento) AS meses_desde_lancamento,
    TRUNC(MONTHS_BETWEEN(SYSDATE, data_lancamento)/12) AS anos_desde_lancamento
FROM album;

-- Data atual
SELECT 
    SYSDATE AS data_hora_atual,
    CURRENT_DATE AS data_atual,
    SYSTIMESTAMP AS timestamp_atual
FROM dual;
```

### 7.3 Funções de Conversão

```sql
-- TO_CHAR: converter para string formatada
SELECT 
    nome_artista,
    TO_CHAR(data_formacao, 'DD/MM/YYYY') AS data_formatada,
    TO_CHAR(COUNT(*), '999,999') AS total_formatado,
    TO_CHAR(AVG(duracao), '999.99') AS media_formatada
FROM artista a
JOIN album al ON a.id_artista = al.id_artista
JOIN musica m ON al.id_album = m.id_album
GROUP BY a.id_artista, nome_artista, data_formacao;

-- TO_DATE: converter string para data
SELECT * FROM album 
WHERE data_lancamento = TO_DATE('26/06/1970', 'DD/MM/YYYY');

-- TO_NUMBER: converter string para número
SELECT TO_NUMBER('1234.56') AS numero FROM dual;
```

### 7.4 Funções Agregadas com GROUP BY

```sql
-- Estatísticas por gênero
SELECT 
    g.nome_genero,
    COUNT(*) AS total_musicas,
    COUNT(DISTINCT al.id_artista) AS artistas_unicos,
    ROUND(AVG(m.duracao), 2) AS duracao_media,
    MIN(m.duracao) AS mais_curta,
    MAX(m.duracao) AS mais_longa,
    SUM(m.duracao) / 3600.0 AS horas_totais
FROM genero g
LEFT JOIN musica m ON g.id_genero = m.id_genero
LEFT JOIN album al ON m.id_album = al.id_album
GROUP BY g.id_genero, g.nome_genero
ORDER BY total_musicas DESC;
```

**Diferença COUNT(*), COUNT(coluna), COUNT(DISTINCT):**
```sql
SELECT 
    COUNT(*) AS total_linhas,           -- Todas as linhas
    COUNT(biografia) AS com_biografia,  -- Apenas não-NULL
    COUNT(DISTINCT pais_origem) AS paises_diferentes  -- Valores únicos
FROM artista;
```

### 7.5 Window Functions (Funções Analíticas)

**Ranking:**
```sql
SELECT 
    nome_artista,
    pais_origem,
    total_reproducoes,
    ROW_NUMBER() OVER (ORDER BY total_reproducoes DESC) AS posicao,
    RANK() OVER (ORDER BY total_reproducoes DESC) AS rank,
    DENSE_RANK() OVER (ORDER BY total_reproducoes DESC) AS dense_rank,
    PERCENT_RANK() OVER (ORDER BY total_reproducoes DESC) AS percentil
FROM estatisticas_artista;

-- ROW_NUMBER: numeração única (1, 2, 3, 4...)
-- RANK: com gaps em empates (1, 2, 2, 4...)
-- DENSE_RANK: sem gaps em empates (1, 2, 2, 3...)
```

**Ranking por Partição:**
```sql
-- Top 3 músicas por gênero
SELECT *
FROM (
    SELECT 
        g.nome_genero,
        m.titulo,
        COUNT(hr.id_historico) AS reproducoes,
        RANK() OVER (PARTITION BY g.id_genero ORDER BY COUNT(hr.id_historico) DESC) AS rank
    FROM genero g
    JOIN musica m ON g.id_genero = m.id_genero
    JOIN historico_reproducao hr ON m.id_musica = hr.id_musica
    GROUP BY g.id_genero, g.nome_genero, m.id_musica, m.titulo
)
WHERE rank <= 3;
```

**Agregações com Janelas:**
```sql
SELECT 
    TRUNC(data_reproducao) AS data,
    COUNT(*) AS reproducoes_dia,
    SUM(COUNT(*)) OVER (ORDER BY TRUNC(data_reproducao) ROWS BETWEEN 6 PRECEDING AND CURRENT ROW) AS media_7_dias,
    LAG(COUNT(*), 1) OVER (ORDER BY TRUNC(data_reproducao)) AS dia_anterior,
    COUNT(*) - LAG(COUNT(*), 1) OVER (ORDER BY TRUNC(data_reproducao)) AS diferenca
FROM historico_reproducao
GROUP BY TRUNC(data_reproducao)
ORDER BY data;
```

---

## 8. Subqueries (Subconsultas)

### 8.1 Subquery Não-Correlacionada

Executa independentemente da query externa:

```sql
-- Músicas com duração acima da média geral
SELECT titulo, duracao
FROM musica
WHERE duracao > (SELECT AVG(duracao) FROM musica);
```

### 8.2 Subquery Correlacionada

Depende de valores da query externa:

```sql
-- Músicas com duração acima da média do seu álbum
SELECT m1.titulo, m1.duracao
FROM musica m1
WHERE m1.duracao > (
    SELECT AVG(m2.duracao) 
    FROM musica m2 
    WHERE m2.id_album = m1.id_album
);
```

### 8.3 Operadores com Subqueries

**IN:**
```sql
-- Artistas que têm músicas de Rock
SELECT nome_artista
FROM artista
WHERE id_artista IN (
    SELECT DISTINCT al.id_artista
    FROM album al
    JOIN musica m ON al.id_album = m.id_album
    JOIN genero g ON m.id_genero = g.id_genero
    WHERE g.nome_genero = 'Rock'
);
```

**EXISTS:**
```sql
-- Usuários que criaram playlists
SELECT nome_usuario
FROM usuario u
WHERE EXISTS (
    SELECT 1 FROM playlist p WHERE p.id_usuario = u.id_usuario
);

-- Artistas SEM álbuns
SELECT nome_artista
FROM artista a
WHERE NOT EXISTS (
    SELECT 1 FROM album al WHERE al.id_artista = a.id_artista
);
```

**ANY e ALL:**
```sql
-- Músicas mais longas que QUALQUER música de Rock (> MIN)
SELECT titulo, duracao
FROM musica
WHERE duracao > ANY (
    SELECT m2.duracao FROM musica m2
    JOIN genero g ON m2.id_genero = g.id_genero
    WHERE g.nome_genero = 'Rock'
);

-- Músicas mais longas que TODAS as músicas de Pop (> MAX)
SELECT titulo, duracao
FROM musica
WHERE duracao > ALL (
    SELECT m2.duracao FROM musica m2
    JOIN genero g ON m2.id_genero = g.id_genero
    WHERE g.nome_genero = 'Pop'
);
```

### 8.4 Subqueries em Diferentes Cláusulas

**No SELECT (Scalar Subquery):**
```sql
SELECT 
    a.nome_artista,
    (SELECT COUNT(*) FROM album WHERE id_artista = a.id_artista) AS total_albuns,
    (SELECT MAX(ano_lancamento) FROM album WHERE id_artista = a.id_artista) AS ultimo_album
FROM artista a;
```

**No FROM (Derived Table):**
```sql
SELECT ranking.nome_artista, ranking.total_reproducoes
FROM (
    SELECT a.nome_artista, COUNT(hr.id_historico) AS total_reproducoes
    FROM artista a
    JOIN album al ON a.id_artista = al.id_artista
    JOIN musica m ON al.id_album = m.id_album
    JOIN historico_reproducao hr ON m.id_musica = hr.id_musica
    GROUP BY a.id_artista, a.nome_artista
) ranking
ORDER BY total_reproducoes DESC;
```

### 8.5 Common Table Expressions (CTE) - WITH

```sql
WITH artistas_populares AS (
    SELECT 
        a.id_artista,
        a.nome_artista,
        COUNT(hr.id_historico) AS total_reproducoes
    FROM artista a
    JOIN album al ON a.id_artista = al.id_artista
    JOIN musica m ON al.id_album = m.id_album
    JOIN historico_reproducao hr ON m.id_musica = hr.id_musica
    GROUP BY a.id_artista, a.nome_artista
    HAVING COUNT(hr.id_historico) > 1000
),
generos_principais AS (
    SELECT 
        g.id_genero,
        g.nome_genero
    FROM genero g
    WHERE EXISTS (SELECT 1 FROM musica WHERE id_genero = g.id_genero)
)
SELECT 
    ap.nome_artista,
    gp.nome_genero,
    ap.total_reproducoes
FROM artistas_populares ap
JOIN album al ON ap.id_artista = al.id_artista
JOIN musica m ON al.id_album = m.id_album
JOIN generos_principais gp ON m.id_genero = gp.id_genero;
```

---

## 9. JOINs e Múltiplas Tabelas

### 9.1 Tipos de JOIN

| Tipo | Descrição | Resultado |
|------|-----------|-----------|
| INNER JOIN | Correspondência em ambas | Apenas matches |
| LEFT JOIN | Todos da esquerda | Esquerda + matches |
| RIGHT JOIN | Todos da direita | Direita + matches |
| FULL OUTER JOIN | Todos de ambos | União completa |
| CROSS JOIN | Produto cartesiano | Todas combinações |

### 9.2 INNER JOIN

```sql
-- Músicas com seus álbuns e artistas
SELECT 
    ar.nome_artista,
    al.titulo AS album,
    m.titulo AS musica,
    m.duracao
FROM musica m
INNER JOIN album al ON m.id_album = al.id_album
INNER JOIN artista ar ON al.id_artista = ar.id_artista
ORDER BY ar.nome_artista, al.titulo, m.numero_faixa;
```

### 9.3 LEFT JOIN

```sql
-- Todos os artistas, com ou sem álbuns
SELECT 
    ar.nome_artista,
    ar.pais_origem,
    COUNT(al.id_album) AS total_albuns
FROM artista ar
LEFT JOIN album al ON ar.id_artista = al.id_artista
GROUP BY ar.id_artista, ar.nome_artista, ar.pais_origem
ORDER BY total_albuns DESC;

-- Encontrar artistas SEM álbuns
SELECT ar.nome_artista
FROM artista ar
LEFT JOIN album al ON ar.id_artista = al.id_artista
WHERE al.id_album IS NULL;  -- Chave: onde a direita é NULL
```

### 9.4 RIGHT JOIN

```sql
-- Todas as músicas, mesmo sem reproduções
SELECT 
    m.titulo,
    ar.nome_artista,
    COUNT(hr.id_historico) AS vezes_tocada
FROM historico_reproducao hr
RIGHT JOIN musica m ON hr.id_musica = m.id_musica
LEFT JOIN album al ON m.id_album = al.id_album
LEFT JOIN artista ar ON al.id_artista = ar.id_artista
GROUP BY m.id_musica, m.titulo, ar.nome_artista
ORDER BY vezes_tocada DESC;
```

### 9.5 FULL OUTER JOIN

```sql
-- União completa de artistas e gêneros
SELECT 
    COALESCE(ar.nome_artista, 'Sem artista') AS artista,
    COALESCE(g.nome_genero, 'Sem gênero') AS genero
FROM artista ar
FULL OUTER JOIN (
    SELECT DISTINCT al.id_artista, m.id_genero
    FROM album al
    JOIN musica m ON al.id_album = m.id_album
) rel ON ar.id_artista = rel.id_artista
FULL OUTER JOIN genero g ON rel.id_genero = g.id_genero;
```

### 9.6 Self-JOIN

```sql
-- Músicas do mesmo álbum com duração similar
SELECT 
    m1.titulo AS musica1,
    m2.titulo AS musica2,
    m1.duracao AS duracao1,
    m2.duracao AS duracao2
FROM musica m1
JOIN musica m2 ON m1.id_album = m2.id_album
              AND m1.id_musica < m2.id_musica  -- Evita duplicatas
              AND ABS(m1.duracao - m2.duracao) < 30;  -- Diferença < 30s
```

### 9.7 JOINs Complexos

```sql
-- Relatório completo de performance de artistas
SELECT 
    ar.nome_artista,
    ar.pais_origem,
    COUNT(DISTINCT al.id_album) AS total_albuns,
    COUNT(DISTINCT m.id_musica) AS total_musicas,
    COUNT(hr.id_historico) AS total_reproducoes,
    ROUND(AVG(m.duracao), 2) AS duracao_media,
    COUNT(DISTINCT u.id_usuario) AS usuarios_unicos
FROM artista ar
LEFT JOIN album al ON ar.id_artista = al.id_artista
LEFT JOIN musica m ON al.id_album = m.id_album
LEFT JOIN historico_reproducao hr ON m.id_musica = hr.id_musica
LEFT JOIN usuario u ON hr.id_usuario = u.id_usuario
GROUP BY ar.id_artista, ar.nome_artista, ar.pais_origem
HAVING COUNT(DISTINCT al.id_album) > 0
ORDER BY total_reproducoes DESC;
```

---

## 10. Controle de Transações

### 10.1 Propriedades ACID

| Propriedade | Significado | Garantia |
|-------------|-------------|----------|
| **A**tomicidade | Tudo ou nada | Transação completa ou nenhuma mudança |
| **C**onsistência | Estado válido | Regras de negócio mantidas |
| **I**solamento | Independência | Transações não interferem |
| **D**urabilidade | Permanência | Mudanças persistem após commit |

### 10.2 COMMIT e ROLLBACK

```sql
-- Em Oracle, transações iniciam automaticamente com o primeiro DML
-- Exemplo de transação:
INSERT INTO artista (id_artista, nome_artista) VALUES (100, 'Novo Artista');
INSERT INTO album (id_album, titulo, id_artista) VALUES (200, 'Primeiro Album', 100);

-- Se tudo ok:
COMMIT;  -- Confirma mudanças permanentemente

-- Se houve erro:
ROLLBACK;  -- Desfaz todas as mudanças desde o último COMMIT
```

### 10.3 SAVEPOINT

```sql
-- Transação começa automaticamente no primeiro DML (Oracle)
INSERT INTO artista VALUES (100, 'Artista A');
SAVEPOINT sp1;

INSERT INTO artista VALUES (101, 'Artista B');
SAVEPOINT sp2;

INSERT INTO artista VALUES (102, 'Artista C');  -- Erro aqui!

ROLLBACK TO sp2;  -- Desfaz apenas Artista C

INSERT INTO artista VALUES (103, 'Artista D');  -- Alternativa

COMMIT;  -- Confirma A, B, D (C foi desfeito)
```

### 10.4 Níveis de Isolamento

| Nível | Dirty Read | Non-Repeatable Read | Phantom Read |
|-------|------------|---------------------|--------------|
| READ UNCOMMITTED | ✅ Permite | ✅ Permite | ✅ Permite |
| READ COMMITTED | ❌ Evita | ✅ Permite | ✅ Permite |
| REPEATABLE READ | ❌ Evita | ❌ Evita | ✅ Permite |
| SERIALIZABLE | ❌ Evita | ❌ Evita | ❌ Evita |

```sql
-- Definir nível de isolamento (Oracle)
SET TRANSACTION ISOLATION LEVEL READ COMMITTED;
SET TRANSACTION ISOLATION LEVEL SERIALIZABLE;
```

### 10.5 Locks

**SELECT FOR UPDATE - Bloqueio explícito:**
```sql
-- Bloquear registros para atualização
SELECT * FROM conta WHERE id = 123 FOR UPDATE;
-- Outros usuários não podem modificar até COMMIT/ROLLBACK

UPDATE conta SET saldo = saldo - 100 WHERE id = 123;
COMMIT;  -- Libera o lock

-- Com NOWAIT (não espera)
SELECT * FROM conta WHERE id = 123 FOR UPDATE NOWAIT;
-- Retorna erro imediatamente se já bloqueado

-- Com WAIT timeout
SELECT * FROM conta WHERE id = 123 FOR UPDATE WAIT 10;
-- Espera até 10 segundos antes de erro
```

### 10.6 Evitando Deadlocks

```sql
-- ❌ CAUSA DEADLOCK: Ordens diferentes
-- Sessão A: UPDATE tabela1, depois tabela2
-- Sessão B: UPDATE tabela2, depois tabela1

-- ✅ PREVINE DEADLOCK: Mesma ordem sempre
-- Ambas sessões: UPDATE tabela1 primeiro, depois tabela2

-- Estratégias:
-- 1. Sempre acessar tabelas na mesma ordem
-- 2. Manter transações curtas
-- 3. Usar timeouts apropriados
-- 4. Bloquear todos os recursos necessários no início
```

---

## 11. Otimização e Boas Práticas

### 11.1 Índices

```sql
-- Criar índices em colunas frequentemente filtradas
CREATE INDEX idx_artista_pais ON artista(pais_origem);
CREATE INDEX idx_album_artista ON album(id_artista);
CREATE INDEX idx_musica_album ON musica(id_album);
CREATE INDEX idx_historico_usuario ON historico_reproducao(id_usuario);
CREATE INDEX idx_historico_data ON historico_reproducao(data_reproducao);

-- Índice composto para consultas específicas
CREATE INDEX idx_album_artista_ano ON album(id_artista, ano_lancamento);

-- Índice funcional (para buscas case-insensitive)
CREATE INDEX idx_artista_nome_upper ON artista(UPPER(nome_artista));
```

### 11.2 Evitar Funções em WHERE

```sql
-- ❌ LENTO: Função impede uso de índice
WHERE EXTRACT(YEAR FROM data_lancamento) = 1970

-- ✅ RÁPIDO: Comparação direta usa índice
WHERE data_lancamento >= DATE '1970-01-01' 
  AND data_lancamento < DATE '1971-01-01'

-- ❌ LENTO
WHERE UPPER(nome_artista) = 'THE BEATLES'

-- ✅ RÁPIDO (com índice funcional)
CREATE INDEX idx_upper ON artista(UPPER(nome_artista));
WHERE UPPER(nome_artista) = 'THE BEATLES'  -- Agora usa índice
```

### 11.3 Seletividade de Filtros

```sql
-- ✅ MELHOR: Condição mais seletiva primeiro
SELECT * FROM musica 
WHERE id_album = 1              -- Alta seletividade (poucas músicas)
  AND duracao > 180;            -- Baixa seletividade (muitas músicas)

-- Reduz: 100.000 → 10 → 5 (rápido)

-- ❌ MENOS EFICIENTE
SELECT * FROM musica 
WHERE duracao > 180             -- Baixa seletividade
  AND id_album = 1;             -- Alta seletividade

-- Processa: 100.000 → 50.000 → 10 (mais dados intermediários)
```

### 11.4 EXPLAIN PLAN

```sql
-- Verificar plano de execução antes de otimizar
EXPLAIN PLAN FOR
SELECT ar.nome_artista, al.titulo
FROM artista ar
JOIN album al ON ar.id_artista = al.id_artista
WHERE ar.pais_origem = 'Brasil';

SELECT * FROM TABLE(DBMS_XPLAN.DISPLAY());

-- Procurar por:
-- ✅ INDEX RANGE SCAN / INDEX UNIQUE SCAN (bom)
-- ❌ TABLE FULL SCAN em tabelas grandes (ruim)
-- ❌ CARTESIAN JOIN (muito ruim - geralmente erro)
```

### 11.5 Boas Práticas Gerais

**Escrita de Consultas:**
```sql
-- ✅ Especificar colunas necessárias
SELECT nome_artista, pais_origem FROM artista;

-- ❌ Evitar SELECT * em produção
SELECT * FROM artista;

-- ✅ Usar aliases claros
SELECT ar.nome_artista, al.titulo
FROM artista ar
JOIN album al ON ar.id_artista = al.id_artista;

-- ✅ Usar parênteses em condições complexas
WHERE (pais = 'Brasil' OR pais = 'Portugal') AND ativo = 'S';

-- ✅ Comentar consultas complexas
-- Relatório de artistas ativos com pelo menos 3 álbuns
SELECT ar.nome_artista, COUNT(al.id_album) AS total_albuns
FROM artista ar
JOIN album al ON ar.id_artista = al.id_artista
WHERE ar.ativo = 'S'
GROUP BY ar.id_artista, ar.nome_artista
HAVING COUNT(al.id_album) >= 3;
```

**Transações:**
```sql
-- ✅ Manter transações curtas
-- ✅ Fazer processamento FORA da transação
-- ✅ Usar SAVEPOINTs em operações complexas
-- ✅ Sempre ter plano de ROLLBACK
-- ✅ Testar em ambiente de desenvolvimento primeiro
```

---

## 📝 Checklist de Revisão

Use este checklist para verificar seu conhecimento:

### DDL - Definição de Dados
- [ ] Criar tabelas com tipos de dados apropriados
- [ ] Definir constraints (PK, FK, NOT NULL, UNIQUE, CHECK, DEFAULT)
- [ ] Modificar estrutura com ALTER TABLE
- [ ] Usar sequences para geração de IDs

### DML - Manipulação de Dados
- [ ] INSERT simples e com SELECT
- [ ] UPDATE com condições e subconsultas
- [ ] DELETE seguro com WHERE
- [ ] MERGE para upsert

### SELECT e Filtros
- [ ] Projeção de colunas específicas
- [ ] Ordenação com ORDER BY
- [ ] Filtros com WHERE e operadores de comparação
- [ ] Operadores lógicos (AND, OR, NOT)
- [ ] Operadores especiais (IN, BETWEEN, LIKE, IS NULL)
- [ ] Limitação de resultados (FETCH FIRST)

### Funções e Agregações
- [ ] Funções de string (UPPER, LOWER, SUBSTR, etc.)
- [ ] Funções de data (EXTRACT, TO_CHAR, etc.)
- [ ] Funções agregadas (COUNT, SUM, AVG, MIN, MAX)
- [ ] GROUP BY e HAVING
- [ ] Window functions (RANK, ROW_NUMBER, LAG, LEAD)

### Subqueries
- [ ] Subqueries não-correlacionadas
- [ ] Subqueries correlacionadas
- [ ] Operadores IN, EXISTS, ANY, ALL
- [ ] CTEs (WITH clause)

### JOINs
- [ ] INNER JOIN
- [ ] LEFT/RIGHT JOIN
- [ ] FULL OUTER JOIN
- [ ] Self-JOIN
- [ ] JOINs múltiplos

### Transações
- [ ] COMMIT e ROLLBACK
- [ ] SAVEPOINT
- [ ] Níveis de isolamento
- [ ] Locks e concorrência

### Otimização
- [ ] Criar índices apropriados
- [ ] Evitar funções em WHERE
- [ ] Analisar planos de execução
- [ ] Escrever consultas eficientes

---

## 📚 Referências

### Documentação Oficial
- [Oracle Database SQL Language Reference](https://docs.oracle.com/en/database/oracle/oracle-database/19/sqlrf/)
- [Oracle SQL Developer User's Guide](https://docs.oracle.com/en/database/oracle/sql-developer/)

### Livros Recomendados
- **Beaulieu, A.** (2020). *Learning SQL: Master SQL Fundamentals*. 3rd Edition. O'Reilly Media.
- **Date, C.J.** (2012). *SQL and Relational Theory*. 2nd Edition. O'Reilly Media.
- **Kyte, T.** (2010). *Expert Oracle Database Architecture*. 2nd Edition. Apress.
- **Celko, J.** (2010). *Joe Celko's SQL for Smarties*. 4th Edition. Morgan Kaufmann.

### Recursos Online
- [Oracle Live SQL](https://livesql.oracle.com/) - Ambiente interativo
- [Ask TOM](https://asktom.oracle.com/) - Perguntas e respostas Oracle
- [SQL Tutorial - W3Schools](https://www.w3schools.com/sql/)

---

## 🎯 Conclusão

Este material de revisão cobriu os conceitos fundamentais e avançados de SQL necessários para trabalhar efetivamente com bancos de dados relacionais. Os tópicos abordados incluem:

1. **Fundamentos sólidos** em estrutura de dados e modelo relacional
2. **Domínio de DDL** para criação e modificação de estruturas
3. **Competência em DML** para manipulação completa de dados
4. **Habilidades em consultas** desde simples até complexas
5. **Conhecimento de funções** para transformação e análise
6. **Expertise em JOINs** para trabalhar com múltiplas tabelas
7. **Compreensão de transações** para garantir integridade
8. **Técnicas de otimização** para performance em produção

**Próximos passos recomendados:**
- Praticar com exercícios dos módulos
- Experimentar no Oracle SQL Developer ou Live SQL
- Trabalhar em projetos práticos com dados reais
- Estudar tópicos avançados como PL/SQL e administração

---

*Material desenvolvido para o curso de Banco de Dados - Linguagem SQL*  
*Compatível com Oracle Database 11g, 12c, 18c, 19c, 21c*  
*Última atualização: Novembro 2024*
