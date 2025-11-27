# 📚 Revisão Completa - Linguagem SQL

## 🎯 Objetivo

Este documento apresenta uma **revisão abrangente e aprofundada** de todos os conceitos de Linguagem SQL cobertos neste repositório. Serve como material de referência para consolidar o aprendizado e preparar para avaliações, projetos práticos e uso profissional de bancos de dados relacionais.

O conteúdo está organizado em seções temáticas, cada uma com explicações teóricas profundas, exemplos práticos e casos de uso do sistema MusiStream (streaming de música).

### 📖 Como Utilizar Este Material

Este documento de revisão foi estruturado para atender diferentes necessidades de estudo:

**Para estudo inicial:**
- Leia cada seção na ordem apresentada
- Execute os exemplos em seu ambiente SQL
- Preste atenção nas explicações de "Por que usar" e "Quando usar"

**Para consulta rápida:**
- Use o índice para navegar diretamente ao tópico desejado
- Cada seção é independente e pode ser consultada isoladamente

**Para preparação de avaliações:**
- Foque nas tabelas comparativas e resumos
- Revise o checklist ao final do documento
- Pratique os exemplos sem consultar o material

**Para aplicação profissional:**
- Consulte as seções de boas práticas e otimização
- Use os exemplos como templates para suas próprias consultas

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

Esta seção estabelece a base teórica necessária para compreender todos os demais conceitos de SQL. Dominar estes fundamentos é essencial, pois eles são a base sobre a qual todo o restante do conhecimento em banco de dados é construído.

### 1.1 O que é SQL?

**SQL (Structured Query Language)** é a linguagem padrão para gerenciamento de bancos de dados relacionais. Desenvolvida nos anos 1970 pela IBM e padronizada pela ANSI/ISO, é utilizada em praticamente todos os sistemas de gerenciamento de banco de dados (SGBDs) modernos.

**Por que SQL é importante?**

SQL é uma das habilidades mais demandadas no mercado de tecnologia porque:
- É utilizada em **praticamente todas as aplicações** que armazenam dados estruturados
- É a **linguagem universal** para comunicação com bancos de dados relacionais
- Permite desde consultas simples até **análises de dados complexas**
- É essencial para profissionais de desenvolvimento, análise de dados, ciência de dados e administração de sistemas

**Características principais:**
- **Declarativa**: Você especifica O QUE quer, não COMO fazer. Isso significa que você descreve o resultado desejado e o banco de dados determina a melhor forma de obtê-lo
- **Baseada em conjuntos**: Opera sobre conjuntos de dados inteiros de uma vez, não linha por linha. Isso torna operações em massa muito eficientes
- **Padronizada**: Sintaxe consistente entre diferentes SGBDs, embora cada um possa ter extensões proprietárias
- **Poderosa**: Permite desde consultas simples até análises complexas com agregações, subconsultas e funções analíticas

**Exemplo prático de natureza declarativa:**

Em uma linguagem procedural, você diria:
```
1. Abra a tabela de artistas
2. Percorra cada linha
3. Se o país for 'Brasil', adicione à lista de resultados
4. Retorne a lista
```

Em SQL, você simplesmente declara:
```sql
SELECT nome_artista FROM artista WHERE pais_origem = 'Brasil';
```
O banco de dados decide internamente a melhor forma de executar essa consulta.

### 1.2 Divisões da Linguagem SQL

A linguagem SQL é dividida em subcategorias conforme o tipo de operação. Entender essas divisões ajuda a compreender o propósito de cada comando e quando utilizá-lo.

| Categoria | Significado | Comandos Principais | Propósito |
|-----------|-------------|---------------------|-----------|
| **DDL** | Data Definition Language | CREATE, ALTER, DROP, TRUNCATE | Definir estrutura do banco |
| **DML** | Data Manipulation Language | INSERT, UPDATE, DELETE, SELECT | Manipular dados |
| **DCL** | Data Control Language | GRANT, REVOKE | Controlar acesso |
| **TCL** | Transaction Control Language | COMMIT, ROLLBACK, SAVEPOINT | Gerenciar transações |

**Explicação detalhada de cada categoria:**

**DDL (Data Definition Language) - Linguagem de Definição de Dados:**
- Usada para criar, modificar e excluir estruturas do banco de dados
- Os comandos DDL afetam o *esquema* do banco, não os dados em si
- Exemplos de uso: criar uma nova tabela, adicionar uma coluna, remover um índice
- **Quando usar**: Ao projetar o banco de dados ou fazer manutenção na estrutura

**DML (Data Manipulation Language) - Linguagem de Manipulação de Dados:**
- Usada para inserir, consultar, atualizar e excluir dados
- É a categoria mais usada no dia a dia (especialmente SELECT)
- Os comandos DML afetam os *dados* armazenados nas tabelas
- **Quando usar**: Em operações rotineiras de leitura e escrita de dados

**DCL (Data Control Language) - Linguagem de Controle de Dados:**
- Usada para gerenciar permissões e segurança
- Controla quem pode fazer o quê no banco de dados
- **Quando usar**: Ao configurar segurança e permissões de usuários

**TCL (Transaction Control Language) - Linguagem de Controle de Transações:**
- Usada para gerenciar transações (conjuntos de operações atômicas)
- Permite confirmar ou reverter mudanças
- **Quando usar**: Ao executar operações que devem ser tratadas como unidade indivisível

### 1.3 Modelo Relacional

O modelo relacional é o fundamento teórico dos bancos de dados SQL. Criado por Edgar F. Codd em 1970, organiza dados em **tabelas (relações)** que se conectam através de chaves.

**Por que o modelo relacional é importante?**

O modelo relacional revolucionou o armazenamento de dados porque:
- **Elimina redundância**: Dados são armazenados uma única vez e referenciados quando necessário
- **Garante integridade**: Regras (constraints) garantem que os dados permaneçam consistentes
- **Facilita consultas**: A estrutura tabular permite consultas poderosas e flexíveis
- **Separa lógica de física**: Você trabalha com dados abstratos, não com detalhes de armazenamento

**Componentes principais do modelo relacional:**

- **Linhas (tuplas)**: Representam registros individuais. Cada linha é uma instância de uma entidade (por exemplo, um artista específico)
- **Colunas (atributos)**: Representam características dos dados. Cada coluna armazena um tipo específico de informação (por exemplo, nome do artista)
- **Chaves primárias (PK)**: Identificadores únicos de cada registro. Garantem que não existam duplicatas
- **Chaves estrangeiras (FK)**: Estabelecem relacionamentos entre tabelas. Referenciam a chave primária de outra tabela

**Exemplo do Sistema MusiStream:**

O diagrama abaixo mostra como as tabelas se relacionam:

```
ARTISTA (id_artista PK, nome_artista, pais_origem, data_formacao, ativo)
    ↓ 1:N (Um artista pode ter muitos álbuns)
ALBUM (id_album PK, titulo, id_artista FK, ano_lancamento, numero_faixas)
    ↓ 1:N (Um álbum pode ter muitas músicas)
MUSICA (id_musica PK, titulo, id_album FK, id_genero FK, duracao, numero_faixa)
```

**Leitura do diagrama:**
- `PK` significa Primary Key (Chave Primária) - identifica unicamente cada registro
- `FK` significa Foreign Key (Chave Estrangeira) - referencia registro de outra tabela
- `1:N` significa relacionamento "um para muitos" - um registro pode se relacionar com muitos outros

**Exemplo prático:**
- O artista "The Beatles" (id_artista = 1) tem vários álbuns
- O álbum "Abbey Road" (id_album = 101) pertence ao artista com id_artista = 1
- A música "Come Together" (id_musica = 1001) pertence ao álbum com id_album = 101

---

## 2. Linguagem de Definição de Dados (DDL)

A DDL (Data Definition Language) é o conjunto de comandos SQL utilizados para definir, modificar e remover estruturas do banco de dados. Estes comandos são fundamentais pois estabelecem o "esqueleto" sobre o qual os dados serão armazenados.

**Por que aprender DDL?**

Mesmo que você não seja responsável por criar bancos de dados, entender DDL é essencial porque:
- Ajuda a compreender a estrutura das tabelas com as quais você trabalha
- Permite fazer alterações necessárias em projetos de desenvolvimento
- É pré-requisito para entender constraints e relacionamentos
- É frequentemente cobrado em entrevistas técnicas e certificações

**Comandos principais:**
- `CREATE`: Cria novas estruturas (tabelas, índices, views)
- `ALTER`: Modifica estruturas existentes
- `DROP`: Remove estruturas permanentemente
- `TRUNCATE`: Remove todos os dados de uma tabela (mantendo a estrutura)

### 2.1 CREATE TABLE - Criando Tabelas

O comando **CREATE TABLE** define a estrutura de uma nova tabela, incluindo colunas, tipos de dados e constraints. É o comando DDL mais utilizado e mais importante.

**Por que CREATE TABLE é fundamental?**

Antes de armazenar qualquer dado, você precisa criar a tabela que vai contê-lo. Uma tabela bem projetada:
- Garante integridade dos dados através de constraints
- Otimiza o desempenho através de tipos de dados adequados
- Facilita manutenção futura com nomes claros e estrutura lógica
- Documenta as regras de negócio através de validações

**Sintaxe Completa:**

```sql
CREATE TABLE nome_tabela (
    nome_coluna TIPO_DADO [CONSTRAINT] [...],
    nome_coluna TIPO_DADO [CONSTRAINT] [...],
    [CONSTRAINT nome_constraint TIPO_CONSTRAINT (colunas)]
);
```

**Explicação da sintaxe:**
- `nome_tabela`: Nome único que identifica a tabela no banco de dados
- `nome_coluna`: Nome da coluna (deve ser único dentro da tabela)
- `TIPO_DADO`: Define que tipo de informação a coluna armazena (texto, número, data, etc.)
- `CONSTRAINT`: Regra de validação opcional (chave primária, não nulo, único, etc.)

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

**Explicação linha por linha:**
- `id_artista INTEGER PRIMARY KEY`: Número inteiro que identifica unicamente cada artista
- `nome_artista VARCHAR2(200) NOT NULL`: Texto de até 200 caracteres, obrigatório
- `pais_origem VARCHAR2(50)`: Texto opcional de até 50 caracteres
- `data_formacao DATE`: Data opcional de formação da banda/artista
- `numero_membros INTEGER DEFAULT 1`: Número inteiro, assume 1 se não informado
- `biografia CLOB`: Texto longo para biografias extensas
- `ativo CHAR(1) DEFAULT 'S' CHECK (...)`: Um caractere (S ou N), padrão S, validado
- `data_cadastro TIMESTAMP DEFAULT SYSTIMESTAMP`: Data/hora de cadastro, automática

**Tipos de Dados Principais (Oracle):**

Escolher o tipo de dado correto é fundamental para garantir integridade e otimizar armazenamento. Cada tipo tem características específicas:

| Tipo | Descrição | Exemplo | Quando Usar |
|------|-----------|---------|-------------|
| VARCHAR2(n) | Texto variável até n caracteres | VARCHAR2(200) | Textos de tamanho variável (nomes, endereços) |
| CHAR(n) | Texto fixo de n caracteres | CHAR(1) | Códigos de tamanho fixo (S/N, UF) |
| NUMBER(p,s) | Número com precisão p e escala s | NUMBER(10,2) | Valores monetários, medidas precisas |
| INTEGER | Número inteiro | INTEGER | Contadores, IDs, quantidades |
| DATE | Data e hora | DATE | Datas de nascimento, vencimento |
| TIMESTAMP | Data/hora com precisão de frações de segundo | TIMESTAMP | Logs, auditoria, operações precisas |
| CLOB | Texto longo (até 4GB) | CLOB | Biografias, descrições extensas |
| BLOB | Dados binários | BLOB | Imagens, arquivos (evitar quando possível) |

**Dicas para escolha de tipos:**
- **VARCHAR2 vs CHAR**: Use VARCHAR2 para textos de tamanho variável (economiza espaço); CHAR para códigos fixos
- **NUMBER vs INTEGER**: Use NUMBER quando precisar de casas decimais; INTEGER para valores inteiros
- **DATE vs TIMESTAMP**: Use DATE para datas simples; TIMESTAMP quando precisar de milissegundos ou fuso horário

### 2.2 Constraints (Restrições)

As constraints são regras que garantem **integridade dos dados**. Elas são fundamentais para manter a qualidade dos dados e prevenir erros.

**Por que usar constraints?**

Constraints são essenciais porque:
- **Previnem dados inválidos**: O banco rejeita inserções/atualizações que violem as regras
- **Documentam regras de negócio**: As constraints explicitam o que é permitido
- **Simplificam a aplicação**: A validação é feita pelo banco, não apenas pela aplicação
- **Garantem consistência**: Mesmo acessos diretos ao banco respeitam as regras

**PRIMARY KEY - Chave Primária:**

A chave primária identifica unicamente cada registro. É a constraint mais importante de uma tabela.

```sql
-- Forma inline (na definição da coluna)
id_artista INTEGER PRIMARY KEY

-- Forma nomeada (no final da tabela) - recomendada para facilitar manutenção
CONSTRAINT pk_artista PRIMARY KEY (id_artista)
```

**Por que usar:** Todo registro precisa ser identificável de forma única. Sem chave primária, não há como referenciar registros específicos.

**FOREIGN KEY - Chave Estrangeira:**

A chave estrangeira estabelece relacionamentos entre tabelas, garantindo integridade referencial.

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

**Opções de ON DELETE:**
- `CASCADE`: Ao excluir o artista, exclui também seus álbuns
- `SET NULL`: Ao excluir o artista, os álbuns ficam com id_artista = NULL
- `RESTRICT` (padrão): Impede exclusão do artista se houver álbuns

**Por que usar:** Garante que não existam álbuns "órfãos" (sem artista válido).

**NOT NULL - Obrigatório:**

Garante que a coluna sempre terá um valor (não pode ser deixada vazia).

```sql
nome_artista VARCHAR2(200) NOT NULL
```

**Por que usar:** Para campos essenciais que não podem ficar vazios (nome, email principal, etc.).

**UNIQUE - Valor Único:**

Garante que não existam valores duplicados na coluna.

```sql
email VARCHAR2(150) UNIQUE
-- Ou
CONSTRAINT uq_usuario_email UNIQUE (email)
```

**Por que usar:** Para campos que devem ser únicos além da chave primária (email, CPF, código interno).

**CHECK - Validação:**

Permite definir regras customizadas de validação.

```sql
duracao INTEGER CHECK (duracao > 0),
avaliacao NUMBER(2,1) CHECK (avaliacao BETWEEN 0 AND 5),
tipo_assinatura VARCHAR2(20) CHECK (tipo_assinatura IN ('gratuito', 'basico', 'premium'))
```

**Por que usar:** Para garantir que os dados estejam dentro de faixas ou conjuntos válidos.

**DEFAULT - Valor Padrão:**

Define um valor automático quando nenhum é fornecido.

```sql
ativo CHAR(1) DEFAULT 'S',
data_cadastro TIMESTAMP DEFAULT SYSTIMESTAMP,
numero_faixas INTEGER DEFAULT 0
```

**Por que usar:** Para campos que geralmente têm um valor específico, simplificando inserções.

### 2.3 ALTER TABLE - Modificando Estrutura

O comando ALTER TABLE permite modificar a estrutura de uma tabela existente sem precisar recriá-la. É essencial para evolução do banco de dados.

**Por que usar ALTER TABLE?**

- Adicionar novas colunas conforme os requisitos evoluem
- Modificar tipos de dados para acomodar mudanças
- Adicionar ou remover constraints
- Renomear colunas ou a própria tabela

**Adicionar coluna:**

Adiciona uma nova coluna à tabela. Linhas existentes terão valor NULL na nova coluna (a menos que especificado um DEFAULT).

```sql
ALTER TABLE artista ADD website VARCHAR2(300);
```

**Quando usar:** Quando um novo campo se torna necessário (ex: adicionar campo de redes sociais).

**Modificar coluna:**

Altera características de uma coluna existente (tipo, tamanho, constraint).

```sql
ALTER TABLE artista MODIFY nome_artista VARCHAR2(300);
```

**Cuidado:** Modificações podem falhar se os dados existentes não forem compatíveis com a nova definição.

**Remover coluna:**

Remove permanentemente uma coluna e todos os seus dados.

```sql
ALTER TABLE artista DROP COLUMN website;
```

**Cuidado:** Esta operação é irreversível. Sempre faça backup antes.

**Adicionar constraint:**

Adiciona uma nova regra a uma tabela existente.

```sql
ALTER TABLE album ADD CONSTRAINT fk_album_artista 
    FOREIGN KEY (id_artista) REFERENCES artista(id_artista);
```

**Cuidado:** A constraint só será criada se todos os dados existentes a respeitarem.

**Remover constraint:**

Remove uma regra existente da tabela.

```sql
ALTER TABLE album DROP CONSTRAINT fk_album_artista;
```

**Quando usar:** Ao reestruturar relacionamentos ou remover validações desnecessárias.

### 2.4 DROP e TRUNCATE

Estes comandos removem dados ou estruturas. Devem ser usados com extremo cuidado.

**DROP TABLE - Remove tabela completamente:**

Remove a tabela inteira, incluindo estrutura, dados, índices e constraints.

```sql
DROP TABLE album;                    -- Falha se houver FK referenciando
DROP TABLE album CASCADE CONSTRAINTS; -- Remove FKs automaticamente
```

**Por que usar:** Para remover tabelas que não são mais necessárias.

**⚠️ ATENÇÃO:** DROP TABLE é irreversível. Todos os dados serão perdidos permanentemente.

**TRUNCATE - Remove todos os dados:**

Remove todos os registros da tabela, mas mantém a estrutura (colunas, constraints, índices).

```sql
TRUNCATE TABLE historico_reproducao;  -- Mais rápido que DELETE
-- Não pode ser desfeito (não gera log de transação)
```

**TRUNCATE vs DELETE:**
| Aspecto | TRUNCATE | DELETE |
|---------|----------|--------|
| Velocidade | Muito rápido | Mais lento |
| Rollback | Não permite | Permite |
| WHERE | Não aceita | Aceita (filtra) |
| Triggers | Não dispara | Dispara |
| Uso | Limpar tabela inteira | Remover registros específicos |

**Por que usar TRUNCATE:** Para limpar tabelas grandes rapidamente (ex: tabelas de log, dados temporários).

### 2.5 Sequences - Geração Automática de IDs

Sequences são objetos que geram números sequenciais automaticamente, muito úteis para criar IDs únicos.

**Por que usar Sequences?**

- Garantem números únicos mesmo com múltiplos usuários simultâneos
- Evitam conflitos de IDs duplicados
- São mais eficientes que calcular MAX(id) + 1

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

-- NEXTVAL: próximo valor (incrementa a sequence)
-- CURRVAL: valor atual (só funciona após NEXTVAL na mesma sessão)
```

**Parâmetros explicados:**
- `START WITH 1`: Inicia em 1
- `INCREMENT BY 1`: Incrementa de 1 em 1
- `MAXVALUE`: Valor máximo permitido
- `NOCACHE`: Não pré-aloca valores (mais seguro, menos eficiente)
- `NOCYCLE`: Não reinicia quando atinge o máximo (gera erro)
```

---

## 3. Linguagem de Manipulação de Dados (DML)

A DML (Data Manipulation Language) é o conjunto de comandos SQL utilizados para inserir, consultar, atualizar e excluir dados. São os comandos mais utilizados no dia a dia, especialmente o SELECT.

**Por que a DML é importante?**

- É o que você usará 90% do tempo trabalhando com bancos de dados
- Permite todas as operações CRUD (Create, Read, Update, Delete)
- SELECT é fundamental para relatórios, análises e extração de dados
- INSERT, UPDATE e DELETE são essenciais para manter os dados atualizados

**Comandos principais:**
- `INSERT`: Adiciona novos registros
- `SELECT`: Consulta dados (mais usado)
- `UPDATE`: Modifica registros existentes
- `DELETE`: Remove registros

### 3.1 INSERT - Inserindo Dados

O comando INSERT adiciona novos registros a uma tabela. É a forma de popular o banco com dados.

**Por que entender INSERT?**

- Todo dado no banco foi inserido via INSERT em algum momento
- É essencial para carregar dados iniciais e novos registros
- Entender INSERT ajuda a entender a estrutura das tabelas

**Inserção Simples:**

A forma mais básica de INSERT especifica colunas e valores correspondentes.

```sql
INSERT INTO artista (id_artista, nome_artista, pais_origem, data_formacao)
VALUES (1, 'The Beatles', 'Reino Unido', DATE '1960-08-17');
```

**Explicação:**
- `INSERT INTO artista`: Tabela onde inserir
- `(id_artista, nome_artista, ...)`: Colunas que receberão valores
- `VALUES (1, 'The Beatles', ...)`: Valores correspondentes às colunas

**Inserção Múltipla (Oracle INSERT ALL):**

Permite inserir vários registros em um único comando, mais eficiente que múltiplos INSERTs.

```sql
INSERT ALL
    INTO genero (id_genero, nome_genero) VALUES (1, 'Rock')
    INTO genero (id_genero, nome_genero) VALUES (2, 'Pop')
    INTO genero (id_genero, nome_genero) VALUES (3, 'Jazz')
    INTO genero (id_genero, nome_genero) VALUES (4, 'Blues')
    INTO genero (id_genero, nome_genero) VALUES (5, 'Eletrônico')
SELECT * FROM dual;
```

**Por que usar INSERT ALL:**
- Mais rápido que vários INSERTs separados
- Reduz tráfego de rede
- Todas as inserções ocorrem em uma única transação

**INSERT... SELECT - Inserção de Consulta:**

Insere dados baseados no resultado de uma consulta. Muito útil para copiar ou transformar dados.
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

Insere apenas se o registro não existir. Útil para evitar duplicatas.

```sql
-- Inserir apenas se não existir
INSERT INTO genero (id_genero, nome_genero, descricao)
SELECT 100, 'Progressive Rock', 'Rock progressivo dos anos 70'
FROM dual
WHERE NOT EXISTS (
    SELECT 1 FROM genero WHERE nome_genero = 'Progressive Rock'
);
```

**Por que usar:** Evita erros de duplicação quando você não tem certeza se o registro já existe.

### 3.2 MERGE - Inserir ou Atualizar (UPSERT)

O comando MERGE combina INSERT e UPDATE em uma única operação. Se o registro existe, atualiza; se não existe, insere.

**Por que usar MERGE?**

- Evita a lógica "primeiro SELECT, depois decide se INSERT ou UPDATE"
- Mais eficiente que fazer duas operações separadas
- Garante atomicidade (tudo acontece em uma única transação)

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

**Explicação do MERGE:**
- `MERGE INTO`: Tabela alvo onde inserir/atualizar
- `USING`: Fonte de dados (pode ser tabela, view ou subconsulta)
- `ON`: Condição que determina se é match (atualiza) ou não (insere)
- `WHEN MATCHED THEN UPDATE`: O que fazer se encontrar correspondência
- `WHEN NOT MATCHED THEN INSERT`: O que fazer se não encontrar

**Quando usar MERGE:**
- Sincronização de dados entre sistemas
- Cargas incrementais em data warehouses
- Atualização de tabelas de cache ou summary
- Consolidação de dados de múltiplas fontes

### 3.3 UPDATE - Atualizando Dados

O comando UPDATE modifica dados existentes em uma tabela.

**Por que entender UPDATE?**

- Dados mudam constantemente (endereços, status, preferências)
- Correção de erros em dados existentes
- Atualização de campos calculados ou estatísticas

**⚠️ CUIDADO:** UPDATE sem WHERE atualiza TODAS as linhas da tabela!

**UPDATE Simples:**

```sql
UPDATE artista 
SET pais_origem = 'Inglaterra',
    data_formacao = DATE '1960-01-01'
WHERE nome_artista = 'The Beatles';
```

**Explicação:**
- `UPDATE artista`: Tabela a modificar
- `SET coluna = valor`: Colunas e novos valores
- `WHERE condição`: **ESSENCIAL** - define quais linhas atualizar

**UPDATE com Subconsulta:**

Atualiza uma coluna com valor calculado de outra tabela.

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

**Por que usar:** Para atualizar campos com valores que dependem de outras tabelas.

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

O comando DELETE remove registros de uma tabela.

**Por que usar DELETE?**

- Remover dados obsoletos ou incorretos
- Limpar registros antigos (logs, histórico)
- Manter o banco limpo e com boa performance

**⚠️ CUIDADO:** DELETE sem WHERE remove TODOS os registros da tabela!

**DELETE Simples:**
```sql
DELETE FROM historico_reproducao
WHERE data_reproducao < SYSDATE - 365;  -- Remover histórico > 1 ano
```

**Explicação:**
- `DELETE FROM tabela`: Tabela de onde remover
- `WHERE condição`: **ESSENCIAL** - define quais linhas remover

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

O comando SELECT é o coração da linguagem SQL - é utilizado para consultar e recuperar dados do banco de dados. Estima-se que 80-90% das operações SQL em sistemas de produção sejam consultas SELECT.

**Por que SELECT é tão importante?**

- É a única forma de visualizar dados armazenados no banco
- Base para todos os relatórios, dashboards e análises
- É não-destrutivo (apenas lê, não modifica dados)
- Permite desde consultas simples até análises complexas

### 4.1 Ordem de Execução do SELECT

Entender a ordem de execução é fundamental para escrever consultas corretas e evitar erros comuns.

**Por que a ordem importa?**

- Explica por que alguns aliases funcionam e outros não
- Ajuda a entender onde colocar filtros (WHERE vs HAVING)
- Fundamental para otimização de performance

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

**Explicação da ordem de execução:**

1. **FROM**: Primeiro, o banco identifica de onde vêm os dados
2. **WHERE**: Depois, filtra as linhas individuais
3. **GROUP BY**: Agrupa as linhas filtradas
4. **HAVING**: Filtra os grupos (não as linhas)
5. **SELECT**: Escolhe quais colunas retornar
6. **ORDER BY**: Ordena o resultado final
7. **LIMIT/FETCH**: Limita a quantidade de linhas

**Implicações Práticas:**

```sql
-- Aliases do SELECT não funcionam no WHERE (execução anterior)
-- ❌ INCORRETO - 'artista' ainda não existe quando WHERE executa
SELECT nome_artista AS artista FROM artista WHERE artista LIKE 'The%';

-- ✅ CORRETO - use o nome original da coluna  
SELECT nome_artista AS artista FROM artista WHERE nome_artista LIKE 'The%';

-- Aliases funcionam no ORDER BY (execução posterior)
-- ✅ CORRETO - 'artista' já existe quando ORDER BY executa
SELECT nome_artista AS artista FROM artista ORDER BY artista;
```

### 4.2 SELECT Básico

A forma mais simples de consulta, usada para recuperar dados de uma única tabela.

**Selecionar todas as colunas:**
```sql
SELECT * FROM artista;  -- Evitar em produção
```

**Por que evitar SELECT * em produção?**
- Retorna dados desnecessários (desperdiça memória e rede)
- Se a tabela mudar (novas colunas), a query pode quebrar
- Dificulta identificar quais dados são realmente usados
- Pode expor dados sensíveis acidentalmente

**Selecionar colunas específicas (recomendado):**
```sql
SELECT nome_artista, pais_origem, data_formacao
FROM artista
WHERE ativo = 'S';
```

**Vantagens de especificar colunas:**
- Mais eficiente (menos dados trafegados)
- Código mais claro e documentado
- Mais seguro (não expõe dados sensíveis)

**Aliases para colunas:**

Aliases dão nomes mais descritivos às colunas no resultado.

```sql
SELECT 
    nome_artista AS "Nome do Artista",
    pais_origem AS "País",
    data_formacao AS "Fundação"
FROM artista;
```

**Quando usar aliases:**
- Para tornar relatórios mais legíveis
- Quando o nome da coluna não é autoexplicativo
- Em expressões calculadas: `salario * 12 AS "Salário Anual"`

**DISTINCT - Valores únicos:**

Remove duplicatas do resultado.

```sql
SELECT DISTINCT pais_origem FROM artista ORDER BY pais_origem;
```

**Quando usar DISTINCT:**
- Para listar valores únicos (categorias, países, tags)
- Para contar valores distintos: `COUNT(DISTINCT pais_origem)`

### 4.3 Ordenação (ORDER BY)

Organiza o resultado em uma ordem específica.

**Por que ordenar?**

- Facilita a leitura e compreensão dos dados
- Permite encontrar valores extremos (maiores, menores, mais recentes)
- Essencial para relatórios profissionais

```sql
-- Ordenação simples (ascendente por padrão)
SELECT nome_artista, pais_origem FROM artista ORDER BY nome_artista;

-- Ordenação descendente (Z-A, maior para menor)
SELECT titulo, ano_lancamento FROM album ORDER BY ano_lancamento DESC;

-- Múltiplas colunas (primeiro país, depois nome dentro de cada país)
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

A cláusula WHERE é uma das ferramentas mais poderosas do SQL, permitindo filtrar registros baseado em condições específicas. Dominar filtros é essencial para trabalhar com grandes volumes de dados.

**Por que filtros são importantes?**

- Retornam apenas dados relevantes (não tudo)
- Melhoram drasticamente a performance
- Essenciais para relatórios e análises específicas
- Economizam memória e tráfego de rede

### 5.1 Operadores de Comparação

Os operadores de comparação testam valores de colunas contra valores específicos ou outras colunas.

| Operador | Descrição | Exemplo | Quando Usar |
|----------|-----------|---------|-------------|
| = | Igual a | WHERE pais = 'Brasil' | Correspondência exata |
| <> ou != | Diferente de | WHERE status <> 'Inativo' | Excluir valores |
| > | Maior que | WHERE duracao > 300 | Valores acima de limite |
| < | Menor que | WHERE ano < 2000 | Valores abaixo de limite |
| >= | Maior ou igual | WHERE avaliacao >= 4 | Valor mínimo (inclusivo) |
| <= | Menor ou igual | WHERE preco <= 100 | Valor máximo (inclusivo) |

**Exemplos práticos:**

```sql
-- Músicas com mais de 5 minutos (300 segundos)
-- Por que: Encontrar músicas longas, talvez para uma playlist especial
SELECT titulo, duracao/60 AS minutos
FROM musica
WHERE duracao > 300
ORDER BY duracao DESC;

-- Álbuns lançados antes de 1980
-- Por que: Análise de catálogo vintage
SELECT titulo, ano_lancamento
FROM album
WHERE ano_lancamento < 1980
ORDER BY ano_lancamento;
```

**Dica importante sobre NULL:**
```sql
-- INCORRETO: Comparação com NULL não funciona!
SELECT * FROM artista WHERE data_formacao = NULL;  -- Não retorna nada

-- CORRETO: Use IS NULL
SELECT * FROM artista WHERE data_formacao IS NULL;
```

### 5.2 Operadores Lógicos

Operadores lógicos combinam múltiplas condições, permitindo filtros complexos.

**Por que usar operadores lógicos?**

- Combinar múltiplos critérios em uma consulta
- Criar filtros precisos e específicos
- Implementar regras de negócio complexas

**AND - Todas condições devem ser verdadeiras:**

Restringe o resultado - quanto mais AND, menos resultados.

```sql
-- Artistas brasileiros ativos formados após 2000
-- Todas as 3 condições devem ser verdadeiras
SELECT nome_artista, data_formacao
FROM artista
WHERE pais_origem = 'Brasil' 
  AND ativo = 'S'
  AND data_formacao > DATE '2000-01-01';
```

**OR - Pelo menos uma condição verdadeira:**
```sql
-- Artistas do Brasil OU Portugal
-- Pelo menos uma condição deve ser verdadeira
SELECT nome_artista, pais_origem
FROM artista
WHERE pais_origem = 'Brasil' OR pais_origem = 'Portugal';
```

**NOT - Negação:**

Inverte o resultado da condição.

```sql
-- Artistas que não são do Brasil
SELECT nome_artista FROM artista WHERE NOT pais_origem = 'Brasil';
-- Ou equivalente (mais comum):
SELECT nome_artista FROM artista WHERE pais_origem <> 'Brasil';
```

**Precedência e Parênteses - MUITO IMPORTANTE:**

Sem parênteses, a ordem é: NOT > AND > OR. Use parênteses para clareza!

```sql
-- Precedência: NOT > AND > OR
-- ❌ AMBÍGUO (AND avaliado antes de OR) - evite!
SELECT * FROM artista
WHERE pais_origem = 'Brasil' OR pais_origem = 'Portugal' AND numero_membros > 3;
-- Resultado: Brasil (todos) + Portugal com >3 membros
-- Não é o que provavelmente se queria!

-- ✅ CLARO (parênteses explícitos) - use sempre!
SELECT * FROM artista
WHERE (pais_origem = 'Brasil' OR pais_origem = 'Portugal') AND numero_membros > 3;
-- Resultado: Brasil ou Portugal, apenas com >3 membros
```

**Dica:** Sempre use parênteses ao combinar AND e OR para evitar ambiguidade.

### 5.3 Operador IN

O operador IN testa se um valor está em uma lista de valores. É uma forma mais legível de escrever múltiplos OR.

**Por que usar IN?**

- Mais legível que múltiplos OR
- Mais fácil de manter (adicionar/remover valores)
- Permite usar subconsultas

```sql
-- Artistas de países específicos
SELECT nome_artista, pais_origem
FROM artista
WHERE pais_origem IN ('Brasil', 'Argentina', 'Chile', 'Uruguai');

-- Equivalente a múltiplos OR (mas IN é mais legível):
WHERE pais_origem = 'Brasil' 
   OR pais_origem = 'Argentina' 
   OR pais_origem = 'Chile' 
   OR pais_origem = 'Uruguai';

-- IN com subconsulta - muito poderoso!
-- Artistas que lançaram álbuns a partir de 2020
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

**⚠️ Cuidado com NOT IN e NULL:** Se a lista contém NULL, NOT IN pode não retornar resultado esperado.

### 5.4 Operador BETWEEN

O operador BETWEEN testa se um valor está dentro de um intervalo. É **inclusivo** (inclui os extremos).

**Por que usar BETWEEN?**

- Mais legível que usar >= e <=
- Deixa clara a intenção de intervalo
- Comum para datas, idades, faixas de preço

```sql
-- Álbuns lançados na década de 70 (BETWEEN é INCLUSIVO)
-- Inclui 1970 E 1979
SELECT titulo, ano_lancamento
FROM album
WHERE ano_lancamento BETWEEN 1970 AND 1979;

-- Equivalente a (mas BETWEEN é mais legível):
WHERE ano_lancamento >= 1970 AND ano_lancamento <= 1979;

-- Músicas com duração entre 3 e 5 minutos (180 a 300 segundos)
SELECT titulo, duracao/60 AS minutos
FROM musica
WHERE duracao BETWEEN 180 AND 300;

-- Com datas (cuidado com timestamps!)
SELECT nome_usuario, data_cadastro
FROM usuario
WHERE data_cadastro BETWEEN DATE '2023-01-01' AND DATE '2023-12-31';
```

**Dica para datas:** Se a coluna tem hora (timestamp), considere usar >= e < ao invés de BETWEEN para evitar problemas.

### 5.5 Operador LIKE

O operador LIKE busca padrões em texto. Essencial para buscas flexíveis.

**Por que usar LIKE?**

- Buscas parciais (começa com, termina com, contém)
- Implementar autocomplete e filtros de busca
- Encontrar padrões em textos

**Wildcards (curingas):**
- `%` = zero ou mais caracteres de qualquer tipo
- `_` = exatamente um caractere

```sql
-- Começa com "The"
SELECT nome_artista FROM artista WHERE nome_artista LIKE 'The%';
-- Resultado: The Beatles, The Rolling Stones, The Who

-- Termina com "Band"
SELECT nome_artista FROM artista WHERE nome_artista LIKE '%Band';
-- Resultado: Dave Matthews Band, Blues Band

-- Contém "Rock" (em qualquer posição)
SELECT nome_artista FROM artista WHERE nome_artista LIKE '%Rock%';
-- Resultado: Rock Nation, The Rockers, Hard Rock Cafe

-- Exatamente 4 caracteres (cada _ é um caractere)
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
