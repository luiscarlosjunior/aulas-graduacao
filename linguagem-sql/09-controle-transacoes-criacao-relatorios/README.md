# Módulo 09 - Controle de Transações e Criação de Relatórios

## Objetivos de Aprendizagem

Ao final deste módulo, o aluno será capaz de:
- Compreender e construir consultas SELECT básicas e avançadas
- Aplicar filtros e condições em consultas
- Utilizar JOINs para combinar dados de múltiplas tabelas
- Compreender e aplicar conceitos de transações em bancos de dados
- Utilizar comandos COMMIT, ROLLBACK e SAVEPOINT
- Implementar controle de concorrência e isolamento
- Criar relatórios básicos usando SELECT
- Aplicar formatação e organização em relatórios
- Trabalhar com níveis de isolamento de transações
- Detectar e resolver problemas de concorrência

## Conteúdo Teórico

### PARTE 1: FUNDAMENTOS DE CONSULTAS SELECT

Antes de trabalhar com transações e relatórios complexos, é essencial dominar o comando SELECT, que é a base para recuperação de dados em SQL. Vamos progredir desde consultas simples até operações mais complexas.

### Ordem de Execução de Consultas SQL

Antes de começarmos com os exemplos práticos, é fundamental compreender **como o SQL processa uma consulta por debaixo dos panos** e qual a **ordem de execução** quando vários elementos são combinados em uma única consulta.

#### Como o SQL Processa uma Consulta SELECT

Quando você escreve uma consulta SQL, a **ordem lógica de escrita** é diferente da **ordem de execução**. O motor de banco de dados processa a consulta em uma sequência específica para otimizar o desempenho e garantir resultados corretos.

**Ordem de ESCRITA (como você digita):**
```sql
SELECT     -- 1. O que você quer ver
FROM       -- 2. De onde vem os dados
WHERE      -- 3. Quais linhas filtrar
GROUP BY   -- 4. Como agrupar
HAVING     -- 5. Filtrar grupos
ORDER BY   -- 6. Como ordenar
```

**Ordem de EXECUÇÃO (como o banco processa):**
```sql
1. FROM       -- Primeiro: identifica as tabelas e faz JOINs
2. WHERE      -- Segundo: filtra as linhas individuais
3. GROUP BY   -- Terceiro: agrupa as linhas filtradas
4. HAVING     -- Quarto: filtra os grupos criados
5. SELECT     -- Quinto: determina quais colunas retornar
6. ORDER BY   -- Sexto: ordena o resultado final
7. LIMIT/FETCH -- Sétimo: limita a quantidade de resultados
```

#### Por Que Esta Ordem é Importante?

**1. FROM e JOINs são processados primeiro**
- O banco de dados primeiro identifica quais tabelas usar
- Realiza os JOINs necessários para combinar dados
- Cria um conjunto de dados temporário com todas as linhas possíveis

**2. WHERE filtra linhas antes do agrupamento**
- Remove linhas indesejadas o mais cedo possível
- É mais eficiente que filtrar depois do GROUP BY
- Por isso você NÃO pode usar aliases do SELECT no WHERE

**3. GROUP BY agrupa os dados filtrados**
- Cria grupos baseado nas colunas especificadas
- Prepara os dados para funções de agregação (COUNT, SUM, AVG, etc.)

**4. HAVING filtra grupos (não linhas)**
- Aplica-se após o agrupamento
- Pode usar funções de agregação (WHERE não pode)
- Exemplo: `HAVING COUNT(*) > 10`

**5. SELECT determina as colunas finais**
- Aplica cálculos e funções
- Cria aliases de colunas
- Por isso aliases só funcionam em ORDER BY (que vem depois)

**6. ORDER BY ordena o resultado**
- É uma das últimas operações
- Pode usar aliases do SELECT
- É a operação mais "cara" em termos de performance

#### Exemplo Prático da Ordem de Execução

Vamos analisar uma consulta completa:

```sql
SELECT 
    a.pais_origem AS "País",                    -- 5. Seleciona e cria alias
    COUNT(*) AS "Total Artistas",               -- 5. Conta artistas por país
    AVG(EXTRACT(YEAR FROM SYSDATE) - 
        EXTRACT(YEAR FROM data_formacao)) AS "Idade Média"  -- 5. Calcula média
FROM artista a                                   -- 1. Identifica tabela origem
WHERE data_formacao IS NOT NULL                  -- 2. Filtra artistas com data
GROUP BY a.pais_origem                           -- 3. Agrupa por país
HAVING COUNT(*) >= 3                             -- 4. Mostra países com 3+ artistas
ORDER BY COUNT(*) DESC;                          -- 6. Ordena por quantidade
```

**Passo a passo da execução:**

1. **FROM artista a**: Carrega a tabela de artistas
2. **WHERE data_formacao IS NOT NULL**: Filtra apenas artistas com data de formação definida
3. **GROUP BY a.pais_origem**: Agrupa os artistas filtrados por país
4. **HAVING COUNT(*) >= 3**: Mantém apenas países com 3 ou mais artistas
5. **SELECT**: Calcula as colunas finais (país, total, idade média)
6. **ORDER BY COUNT(*) DESC**: Ordena do país com mais artistas para o com menos

#### Implicações Práticas

**1. Você não pode usar aliases do SELECT no WHERE:**
```sql
-- INCORRETO ❌
SELECT nome_artista AS artista
FROM artista
WHERE artista LIKE 'The%';  -- Erro! 'artista' não existe ainda

-- CORRETO ✓
SELECT nome_artista AS artista
FROM artista
WHERE nome_artista LIKE 'The%';  -- WHERE executa antes do SELECT
```

**2. Você pode usar aliases no ORDER BY:**
```sql
-- CORRETO ✓
SELECT nome_artista AS artista
FROM artista
ORDER BY artista;  -- ORDER BY executa depois do SELECT
```

**3. WHERE vs HAVING - escolha correta:**
```sql
-- Use WHERE para filtrar linhas (antes do agrupamento)
SELECT pais_origem, COUNT(*) as total
FROM artista
WHERE ativo = 'S'  -- Filtra artistas ativos ANTES de agrupar
GROUP BY pais_origem;

-- Use HAVING para filtrar grupos (depois do agrupamento)
SELECT pais_origem, COUNT(*) as total
FROM artista
GROUP BY pais_origem
HAVING COUNT(*) > 5;  -- Filtra países DEPOIS de contar
```

**4. Otimização de performance:**
```sql
-- MENOS EFICIENTE: filtra depois de agrupar
SELECT pais_origem, COUNT(*) 
FROM artista
GROUP BY pais_origem
HAVING pais_origem = 'Brasil';

-- MAIS EFICIENTE: filtra antes de agrupar
SELECT pais_origem, COUNT(*) 
FROM artista
WHERE pais_origem = 'Brasil'
GROUP BY pais_origem;
```

#### JOINs na Ordem de Execução

Quando há JOINs, eles são processados na fase FROM:

```sql
SELECT m.titulo, g.nome_genero          -- 4. Seleciona colunas
FROM musica m                           -- 1a. Primeira tabela
INNER JOIN genero g                     -- 1b. JOIN com segunda tabela
    ON m.id_genero = g.id_genero        -- 1c. Condição do JOIN
WHERE m.duracao > 180                   -- 2. Filtra músicas longas
ORDER BY m.titulo;                      -- 5. Ordena resultado
```

**Processamento:**
1. FROM + JOIN: Combina `musica` com `genero` baseado no `id_genero`
2. WHERE: Filtra músicas com duração > 180 segundos
3. SELECT: Seleciona título da música e nome do gênero
4. ORDER BY: Ordena por título

### 1. Introdução ao Comando SELECT

O comando SELECT é o coração da linguagem SQL e a ferramenta principal para **consultar e recuperar dados** de um banco de dados. É o comando mais utilizado em SQL, representando cerca de 80-90% das operações em sistemas de produção, e serve como base fundamental para criação de relatórios, análises e visualizações de dados.

**Por que o SELECT é importante?**
- **Leitura de Dados**: É a única forma de visualizar informações armazenadas no banco
- **Base para Análises**: Todo relatório, dashboard ou análise começa com um SELECT
- **Não Destrutivo**: Diferente de INSERT, UPDATE ou DELETE, o SELECT apenas lê dados sem modificá-los
- **Versatilidade**: Pode realizar desde consultas simples até análises complexas com múltiplas tabelas

#### 1.1 Sintaxe Básica do SELECT

```sql
SELECT coluna1, coluna2, ...
FROM tabela
[WHERE condições]
[GROUP BY colunas]
[HAVING condições]
[ORDER BY colunas];
```

**Componentes principais explicados:**

- **SELECT**: Define **quais colunas** serão retornadas no resultado
  - É como escolher quais informações você quer ver em um relatório
  - Pode incluir colunas, cálculos, funções e expressões
  - Exemplo: `SELECT nome, idade, salario * 1.1`

- **FROM**: Especifica de **qual(is) tabela(s)** os dados serão extraídos
  - Identifica a fonte dos dados
  - Pode incluir uma ou múltiplas tabelas (com JOINs)
  - Exemplo: `FROM funcionarios` ou `FROM funcionarios f JOIN departamentos d`

- **WHERE**: Filtra os **registros** (linhas) baseado em condições (opcional)
  - Funciona como um filtro que decide quais linhas incluir
  - Avaliado ANTES do agrupamento
  - Exemplo: `WHERE salario > 5000 AND departamento = 'TI'`

- **GROUP BY**: Agrupa registros para cálculos de **agregação** (opcional)
  - Usado quando você quer resumir dados (somar, contar, calcular médias)
  - Agrupa linhas que têm valores iguais nas colunas especificadas
  - Exemplo: `GROUP BY departamento` para ver totais por departamento

- **HAVING**: Filtra os **grupos** criados pelo GROUP BY (opcional)
  - Similar ao WHERE, mas aplicado após o agrupamento
  - Pode usar funções de agregação (COUNT, SUM, AVG, etc.)
  - Exemplo: `HAVING COUNT(*) > 10` para mostrar apenas grupos com mais de 10 itens

- **ORDER BY**: Define a **ordenação** dos resultados (opcional)
  - Organiza os resultados em ordem crescente (ASC) ou decrescente (DESC)
  - É a última operação executada
  - Exemplo: `ORDER BY salario DESC` para ordenar do maior para o menor salário

#### 1.2 Quando Usar Cada Cláusula

**Cenário 1: Consulta Simples** (apenas SELECT e FROM)
```sql
SELECT nome, email FROM usuario;
```
**Uso**: Ver dados básicos sem filtros ou ordenação

**Cenário 2: Consulta com Filtro** (SELECT, FROM, WHERE)
```sql
SELECT nome, email FROM usuario WHERE ativo = 'S';
```
**Uso**: Ver apenas registros que atendem certas condições

**Cenário 3: Consulta com Ordenação** (SELECT, FROM, ORDER BY)
```sql
SELECT nome, email FROM usuario ORDER BY nome;
```
**Uso**: Ver dados organizados alfabeticamente ou por valor

**Cenário 4: Consulta Agregada** (SELECT, FROM, GROUP BY)
```sql
SELECT pais_origem, COUNT(*) as total 
FROM artista 
GROUP BY pais_origem;
```
**Uso**: Ver totalizações e resumos (quantos artistas por país)

**Cenário 5: Consulta Agregada com Filtro de Grupo** (todas as cláusulas)
```sql
SELECT pais_origem, COUNT(*) as total 
FROM artista 
WHERE ativo = 'S'
GROUP BY pais_origem 
HAVING COUNT(*) > 5
ORDER BY total DESC;
```
**Uso**: Análises complexas com filtros em linhas e grupos

### 2. Consultas Simples (Uma Tabela)

As consultas simples envolvem apenas uma tabela e são a base para compreender o SELECT. Dominar estas técnicas é essencial antes de avançar para consultas mais complexas com múltiplas tabelas.

**Por que começar com consultas simples?**
- Mais fáceis de entender e debugar
- Performance geralmente melhor (uma só tabela)
- Base para construir consultas complexas
- Úteis para exploração inicial de dados

#### 2.1 Selecionando Todas as Colunas

```sql
-- Selecionar todos os artistas
SELECT * FROM artista;

-- Selecionar todos os gêneros musicais
SELECT * FROM genero;

-- Selecionar todos os usuários
SELECT * FROM usuario;
```

**Como funciona:**
- O asterisco (`*`) é um atalho que significa "todas as colunas"
- O banco retorna todas as colunas na ordem em que foram definidas na criação da tabela
- Equivale a escrever `SELECT coluna1, coluna2, coluna3, ...`

**Por que usar:**
- **Exploração**: Útil quando você ainda não conhece a estrutura da tabela
- **Desenvolvimento**: Rápido para ver todos os dados durante testes
- **Simplicidade**: Menos código para escrever inicialmente

**Quando evitar:**
- **Produção**: Em sistemas reais, sempre especifique colunas necessárias
- **Performance**: Retornar dados desnecessários desperdiça memória e rede
- **Segurança**: Pode expor dados sensíveis não intencionalmente
- **Manutenção**: Se a tabela mudar (novas colunas), a query retorna dados inesperados

**Observação**: O uso de `*` retorna todas as colunas. Em produção, é recomendado especificar apenas as colunas necessárias para melhor performance e segurança.

#### 2.2 Selecionando Colunas Específicas

```sql
-- Selecionar apenas nome e país dos artistas
SELECT nome_artista, pais_origem 
FROM artista;

-- Selecionar informações básicas de álbuns
SELECT titulo, ano_lancamento, numero_faixas
FROM album;

-- Selecionar dados de usuários (sem senha)
SELECT id_usuario, nome_usuario, email, data_cadastro
FROM usuario;
```

**Como funciona:**
- Você especifica exatamente quais colunas quer ver
- O banco retorna apenas essas colunas, na ordem que você definiu
- Reduz o volume de dados trafegados entre banco e aplicação

**Por que usar:**
- **Performance**: Menos dados = consulta mais rápida
- **Segurança**: Não expõe dados sensíveis como senhas ou informações privadas
- **Clareza**: Código mais legível - fica claro quais dados são necessários
- **Manutenção**: Mudanças na estrutura da tabela afetam menos suas queries
- **Boas Práticas**: É o padrão recomendado em sistemas profissionais

**Exemplo prático:**
Imagine uma tabela `usuario` com 20 colunas. Se você só precisa do nome e email:
```sql
-- Ruim: retorna 20 colunas desnecessárias
SELECT * FROM usuario;

-- Bom: retorna apenas 2 colunas necessárias
SELECT nome_usuario, email FROM usuario;
```
O segundo exemplo é até 10x mais rápido em tabelas grandes!

#### 2.3 Usando Aliases (Apelidos) para Colunas

```sql
-- Aliases tornam os resultados mais legíveis
SELECT 
    nome_artista AS "Nome do Artista",
    pais_origem AS "País",
    data_formacao AS "Formado em"
FROM artista;

-- Aliases sem AS (sintaxe alternativa)
SELECT 
    titulo "Título do Álbum",
    ano_lancamento "Ano",
    numero_faixas "Número de Faixas"
FROM album;
```

**Como funciona:**
- A palavra-chave `AS` cria um "apelido" (alias) para a coluna
- O alias substitui o nome original apenas no resultado da consulta
- Não afeta o nome real da coluna na tabela
- `AS` é opcional, mas recomendado para clareza

**Por que usar:**
- **Legibilidade**: Nomes mais descritivos e em português (se for o caso)
- **Formatação**: Melhora apresentação em relatórios
- **Cálculos**: Nomear expressões complexas (ex: `salario * 1.1 AS "Novo Salário"`)
- **Espaços**: Permite nomes com espaços usando aspas duplas
- **Case**: Força maiúsculas/minúsculas específicas

**Quando usar:**
- Em relatórios para usuários finais
- Quando o nome da coluna não é autoexplicativo
- Para expressões calculadas: `SELECT salario * 12 AS "Salário Anual"`
- Em queries com JOIN onde há colunas com mesmo nome

**Sintaxes válidas:**
```sql
-- Com AS (recomendado)
SELECT nome_artista AS artista FROM artista;

-- Sem AS (funciona mas menos claro)
SELECT nome_artista artista FROM artista;

-- Com aspas (para espaços ou case-sensitive)
SELECT nome_artista AS "Nome Completo" FROM artista;

-- Sem aspas (convertido para maiúsculas no Oracle)
SELECT nome_artista AS artista FROM artista;
```

#### 2.4 Ordenando Resultados (ORDER BY)

```sql
-- Ordenar artistas por nome (A-Z)
SELECT nome_artista, pais_origem
FROM artista
ORDER BY nome_artista;

-- Ordenar artistas por nome em ordem decrescente (Z-A)
SELECT nome_artista, pais_origem
FROM artista
ORDER BY nome_artista DESC;

-- Ordenar por múltiplas colunas
SELECT nome_artista, pais_origem, data_formacao
FROM artista
ORDER BY pais_origem, nome_artista;

-- Ordenar álbuns por ano de lançamento (mais recentes primeiro)
SELECT titulo, ano_lancamento
FROM album
ORDER BY ano_lancamento DESC;
```

**Como funciona:**
- `ORDER BY` ordena os resultados após todas as outras operações
- `ASC` = ascendente (A-Z, 0-9, menor para maior) - **padrão**
- `DESC` = descendente (Z-A, 9-0, maior para menor)
- Múltiplas colunas: ordena pela primeira, depois desempata pela segunda, etc.

**Por que usar:**
- **Apresentação**: Dados organizados são mais fáceis de ler
- **Análise**: Encontrar top/bottom valores (maiores salários, datas mais recentes)
- **Relatórios**: Agrupamento visual de dados similares
- **Padrões**: Identificar padrões ordenando por data, valor, nome, etc.

**Ordenação por múltiplas colunas:**
```sql
-- Primeiro ordena por país, depois por nome dentro de cada país
SELECT nome_artista, pais_origem
FROM artista
ORDER BY pais_origem ASC, nome_artista ASC;
```
Resultado: todos artistas do Brasil em ordem alfabética, depois Portugal em ordem alfabética, etc.

**Ordenação por posição:**
```sql
-- Pode usar número da coluna ao invés do nome
SELECT nome_artista, pais_origem
FROM artista
ORDER BY 2, 1;  -- ordena pela 2ª coluna (país), depois 1ª (nome)
```
**Atenção**: Não recomendado! Dificulta manutenção se a ordem das colunas mudar.

**Performance:**
- ORDER BY é uma das operações mais custosas
- Em tabelas grandes, pode ser lento sem índices adequados
- Use LIMIT/FETCH se só precisa dos primeiros resultados

#### 2.5 Eliminando Duplicatas (DISTINCT)

```sql
-- Listar países de origem únicos
SELECT DISTINCT pais_origem
FROM artista
ORDER BY pais_origem;

-- Listar anos de lançamento únicos
SELECT DISTINCT ano_lancamento
FROM album
WHERE ano_lancamento IS NOT NULL
ORDER BY ano_lancamento DESC;
```

**Como funciona:**
- `DISTINCT` remove linhas duplicadas do resultado
- Compara **todas as colunas** selecionadas para determinar duplicatas
- Internamente, o banco ordena ou cria hash table para encontrar duplicatas
- Retorna apenas uma ocorrência de cada combinação única de valores

**Por que usar:**
- **Valores Únicos**: Listar categorias, tags, ou valores distintos
- **Análise Exploratória**: Descobrir quantos valores diferentes existem
- **Evitar Contagem Dupla**: Em JOINs que podem gerar duplicatas
- **Listas**: Criar listas de opções (países, categorias, anos, etc.)

**Exemplo prático:**
```sql
-- Sem DISTINCT: pode retornar "Brasil" 50 vezes se houver 50 artistas brasileiros
SELECT pais_origem FROM artista;

-- Com DISTINCT: retorna "Brasil" apenas uma vez
SELECT DISTINCT pais_origem FROM artista;
```

**DISTINCT com múltiplas colunas:**
```sql
-- Considera COMBINAÇÃO de país E ano como única
SELECT DISTINCT pais_origem, EXTRACT(YEAR FROM data_formacao) as ano
FROM artista
WHERE data_formacao IS NOT NULL;
```
Retorna cada combinação país-ano apenas uma vez.

**Impactos de Performance:**
- DISTINCT requer processamento adicional (ordenação ou hashing)
- Pode ser lento em grandes volumes de dados
- Prefira GROUP BY quando precisar contar ou agregar
- Considere se o problema real não está em um JOIN mal feito

**DISTINCT vs GROUP BY:**
```sql
-- DISTINCT: apenas valores únicos
SELECT DISTINCT pais_origem FROM artista;

-- GROUP BY: valores únicos + permite agregações
SELECT pais_origem, COUNT(*) as total 
FROM artista 
GROUP BY pais_origem;
```

#### 2.6 Limitando Resultados (ROWNUM ou FETCH)

```sql
-- Primeiros 10 artistas (Oracle com ROWNUM)
SELECT nome_artista, pais_origem
FROM artista
WHERE ROWNUM <= 10;

-- Primeiros 5 álbuns mais recentes (Oracle 12c+ com FETCH)
SELECT titulo, ano_lancamento
FROM album
ORDER BY ano_lancamento DESC
FETCH FIRST 5 ROWS ONLY;
```

**Como funciona:**
- **ROWNUM** (Oracle clássico): Numera linhas conforme são retornadas
- **FETCH FIRST** (Oracle 12c+, padrão SQL): Sintaxe moderna e mais clara
- Limita a quantidade de linhas no resultado final
- Essencial para paginação e visualização de amostras

**Por que usar:**
- **Performance**: Retornar milhões de linhas é impraticável
- **Paginação**: Mostrar 10, 20, 50 resultados por página
- **Amostragem**: Ver exemplos dos dados sem carregar tudo
- **Top N**: Encontrar os N maiores/menores valores
- **Teste**: Verificar query em amostra antes de executar em tudo

**Diferença entre ROWNUM e FETCH:**

**ROWNUM** (sintaxe antiga):
```sql
-- Atenção: ROWNUM é atribuído ANTES do ORDER BY!
-- Isso NÃO retorna os 5 álbuns mais recentes:
SELECT titulo, ano_lancamento
FROM album
WHERE ROWNUM <= 5
ORDER BY ano_lancamento DESC;

-- Correto com ROWNUM (precisa de subconsulta):
SELECT * FROM (
    SELECT titulo, ano_lancamento
    FROM album
    ORDER BY ano_lancamento DESC
)
WHERE ROWNUM <= 5;
```

**FETCH FIRST** (sintaxe moderna, recomendada):
```sql
-- Mais simples e correto
SELECT titulo, ano_lancamento
FROM album
ORDER BY ano_lancamento DESC
FETCH FIRST 5 ROWS ONLY;

-- Com OFFSET para paginação
SELECT titulo, ano_lancamento
FROM album
ORDER BY ano_lancamento DESC
OFFSET 10 ROWS FETCH NEXT 5 ROWS ONLY;  -- linhas 11-15
```

**Casos de uso práticos:**

**Top 10 Artistas:**
```sql
SELECT nome_artista, pais_origem
FROM artista
ORDER BY nome_artista
FETCH FIRST 10 ROWS ONLY;
```

**Paginação (página 3, 20 itens por página):**
```sql
SELECT nome_artista, pais_origem
FROM artista
ORDER BY nome_artista
OFFSET 40 ROWS    -- Pula primeiras 2 páginas (2 × 20)
FETCH NEXT 20 ROWS ONLY;  -- Retorna página 3
```

**Amostra aleatória:**
```sql
SELECT nome_artista
FROM artista
ORDER BY DBMS_RANDOM.VALUE  -- Ordem aleatória
FETCH FIRST 5 ROWS ONLY;    -- Pega 5 aleatórios
```

**Performance:**
- Muito mais eficiente que retornar tudo e filtrar na aplicação
- Banco para de processar após atingir o limite
- Em tabelas indexadas, pode ser extremamente rápido

### 3. Consultas com Filtros (Cláusula WHERE)

A cláusula WHERE permite filtrar registros baseado em condições específicas.

#### 3.1 Operadores de Comparação

```sql
-- Igualdade (=)
SELECT nome_artista, pais_origem
FROM artista
WHERE pais_origem = 'Brasil';

-- Diferente (<> ou !=)
SELECT nome_artista, pais_origem
FROM artista
WHERE pais_origem <> 'Brasil';

-- Maior que (>)
SELECT titulo, ano_lancamento
FROM album
WHERE ano_lancamento > 2000;

-- Menor que (<)
SELECT titulo, ano_lancamento
FROM album
WHERE ano_lancamento < 1980;

-- Maior ou igual (>=)
SELECT titulo, numero_faixas
FROM album
WHERE numero_faixas >= 15;

-- Menor ou igual (<=)
SELECT titulo, duracao
FROM musica
WHERE duracao <= 180; -- músicas com até 3 minutos
```

#### 3.2 Operadores Lógicos (AND, OR, NOT)

```sql
-- AND: Todas as condições devem ser verdadeiras
SELECT nome_artista, pais_origem, data_formacao
FROM artista
WHERE pais_origem = 'Brasil' 
  AND data_formacao > TO_DATE('2000-01-01', 'YYYY-MM-DD');

-- OR: Pelo menos uma condição deve ser verdadeira
SELECT nome_artista, pais_origem
FROM artista
WHERE pais_origem = 'Brasil' OR pais_origem = 'Portugal';

-- NOT: Inverte a condição
SELECT nome_artista, pais_origem
FROM artista
WHERE NOT (pais_origem = 'Brasil');

-- Combinação de operadores lógicos
SELECT titulo, ano_lancamento, numero_faixas
FROM album
WHERE (ano_lancamento BETWEEN 2000 AND 2010)
  AND (numero_faixas > 10 OR numero_faixas < 5);
```

#### 3.3 Operador IN (Lista de Valores)

```sql
-- Selecionar artistas de países específicos
SELECT nome_artista, pais_origem
FROM artista
WHERE pais_origem IN ('Brasil', 'Portugal', 'Argentina');

-- Selecionar álbuns de anos específicos
SELECT titulo, ano_lancamento
FROM album
WHERE ano_lancamento IN (1990, 1995, 2000, 2005, 2010);

-- NOT IN: Excluir valores específicos
SELECT nome_artista, pais_origem
FROM artista
WHERE pais_origem NOT IN ('Brasil', 'Estados Unidos');
```

#### 3.4 Operador BETWEEN (Intervalo de Valores)

```sql
-- Álbuns lançados entre 2000 e 2010
SELECT titulo, ano_lancamento
FROM album
WHERE ano_lancamento BETWEEN 2000 AND 2010
ORDER BY ano_lancamento;

-- Músicas com duração entre 3 e 5 minutos
SELECT titulo, duracao
FROM musica
WHERE duracao BETWEEN 180 AND 300;

-- BETWEEN com datas
SELECT nome_usuario, data_cadastro
FROM usuario
WHERE data_cadastro BETWEEN TO_DATE('2023-01-01', 'YYYY-MM-DD') 
                       AND TO_DATE('2023-12-31', 'YYYY-MM-DD');
```

#### 3.5 Operador LIKE (Busca por Padrões)

```sql
-- Nomes que começam com 'The'
SELECT nome_artista
FROM artista
WHERE nome_artista LIKE 'The%';

-- Nomes que terminam com 'Band'
SELECT nome_artista
FROM artista
WHERE nome_artista LIKE '%Band';

-- Nomes que contêm 'Rock'
SELECT nome_artista
FROM artista
WHERE nome_artista LIKE '%Rock%';

-- Nomes com exatamente 5 caracteres
SELECT nome_genero
FROM genero
WHERE nome_genero LIKE '_____';

-- Busca case-insensitive (Oracle)
SELECT nome_artista
FROM artista
WHERE UPPER(nome_artista) LIKE '%ROCK%';
```

#### 3.6 Operador IS NULL / IS NOT NULL

```sql
-- Artistas sem data de formação definida
SELECT nome_artista, data_formacao
FROM artista
WHERE data_formacao IS NULL;

-- Artistas com data de formação definida
SELECT nome_artista, data_formacao
FROM artista
WHERE data_formacao IS NOT NULL
ORDER BY data_formacao;

-- Álbuns sem ano de lançamento
SELECT titulo
FROM album
WHERE ano_lancamento IS NULL;
```

### 4. Consultas com JOINs (Múltiplas Tabelas)

JOINs permitem combinar dados de duas ou mais tabelas baseado em relacionamentos entre elas.

#### 4.1 INNER JOIN (Correspondência em Ambas as Tabelas)

```sql
-- Listar álbuns com nome do artista
SELECT 
    a.nome_artista AS "Artista",
    al.titulo AS "Álbum",
    al.ano_lancamento AS "Ano"
FROM artista a
INNER JOIN album al ON a.id_artista = al.id_artista
ORDER BY a.nome_artista, al.ano_lancamento;

-- Listar músicas com álbum e artista
SELECT 
    ar.nome_artista AS "Artista",
    al.titulo AS "Álbum",
    m.titulo AS "Música",
    m.duracao AS "Duração (seg)"
FROM musica m
INNER JOIN album al ON m.id_album = al.id_album
INNER JOIN artista ar ON al.id_artista = ar.id_artista
ORDER BY ar.nome_artista, al.titulo, m.numero_faixa;

-- Músicas com gênero
SELECT 
    m.titulo AS "Música",
    g.nome_genero AS "Gênero",
    m.duracao AS "Duração"
FROM musica m
INNER JOIN genero g ON m.id_genero = g.id_genero
WHERE g.nome_genero = 'Rock'
ORDER BY m.titulo;
```

#### 4.2 LEFT JOIN (Todos os Registros da Tabela à Esquerda)

```sql
-- Listar todos os artistas, mesmo sem álbuns
SELECT 
    a.nome_artista AS "Artista",
    COUNT(al.id_album) AS "Total de Álbuns"
FROM artista a
LEFT JOIN album al ON a.id_artista = al.id_artista
GROUP BY a.id_artista, a.nome_artista
ORDER BY COUNT(al.id_album) DESC, a.nome_artista;

-- Artistas sem álbuns cadastrados
SELECT 
    a.nome_artista AS "Artista",
    a.pais_origem AS "País"
FROM artista a
LEFT JOIN album al ON a.id_artista = al.id_artista
WHERE al.id_album IS NULL;

-- Todos os usuários com total de playlists (incluindo sem playlists)
SELECT 
    u.nome_usuario AS "Usuário",
    COUNT(p.id_playlist) AS "Total Playlists"
FROM usuario u
LEFT JOIN playlist p ON u.id_usuario = p.id_usuario
GROUP BY u.id_usuario, u.nome_usuario
ORDER BY COUNT(p.id_playlist) DESC;
```

#### 4.3 RIGHT JOIN (Todos os Registros da Tabela à Direita)

```sql
-- Todos os álbuns com seus artistas (garante que todos os álbuns apareçam)
SELECT 
    al.titulo AS "Álbum",
    a.nome_artista AS "Artista"
FROM artista a
RIGHT JOIN album al ON a.id_artista = al.id_artista
ORDER BY al.titulo;

-- Todos os gêneros com contagem de músicas
SELECT 
    g.nome_genero AS "Gênero",
    COUNT(m.id_musica) AS "Total Músicas"
FROM musica m
RIGHT JOIN genero g ON m.id_genero = g.id_genero
GROUP BY g.id_genero, g.nome_genero
ORDER BY COUNT(m.id_musica) DESC;
```

#### 4.4 FULL OUTER JOIN (Todos os Registros de Ambas as Tabelas)

```sql
-- Todos os artistas e álbuns (incluindo artistas sem álbuns e álbuns sem artistas)
SELECT 
    a.nome_artista AS "Artista",
    al.titulo AS "Álbum"
FROM artista a
FULL OUTER JOIN album al ON a.id_artista = al.id_artista
ORDER BY a.nome_artista, al.titulo;
```

#### 4.5 SELF JOIN (Junção de uma Tabela com Ela Mesma)

```sql
-- Exemplo: Se houver uma coluna de artistas relacionados/similares
-- Encontrar pares de artistas do mesmo país
SELECT 
    a1.nome_artista AS "Artista 1",
    a2.nome_artista AS "Artista 2",
    a1.pais_origem AS "País"
FROM artista a1
INNER JOIN artista a2 ON a1.pais_origem = a2.pais_origem
WHERE a1.id_artista < a2.id_artista
ORDER BY a1.pais_origem, a1.nome_artista;
```

#### 4.6 Consultas Complexas com Múltiplos JOINs

```sql
-- Relatório completo: Músicas com artista, álbum, gênero e reproduções
SELECT 
    ar.nome_artista AS "Artista",
    al.titulo AS "Álbum",
    m.titulo AS "Música",
    g.nome_genero AS "Gênero",
    m.duracao AS "Duração (seg)",
    COUNT(hr.id_historico) AS "Reproduções"
FROM musica m
INNER JOIN album al ON m.id_album = al.id_album
INNER JOIN artista ar ON al.id_artista = ar.id_artista
INNER JOIN genero g ON m.id_genero = g.id_genero
LEFT JOIN historico_reproducao hr ON m.id_musica = hr.id_musica
GROUP BY ar.nome_artista, al.titulo, m.titulo, g.nome_genero, m.duracao
ORDER BY COUNT(hr.id_historico) DESC, ar.nome_artista;

-- Top 10 músicas mais tocadas com informações completas
SELECT 
    ar.nome_artista AS "Artista",
    m.titulo AS "Música",
    g.nome_genero AS "Gênero",
    COUNT(hr.id_historico) AS "Reproduções"
FROM musica m
INNER JOIN album al ON m.id_album = al.id_album
INNER JOIN artista ar ON al.id_artista = ar.id_artista
INNER JOIN genero g ON m.id_genero = g.id_genero
INNER JOIN historico_reproducao hr ON m.id_musica = hr.id_musica
GROUP BY ar.nome_artista, m.titulo, g.nome_genero, m.id_musica
ORDER BY COUNT(hr.id_historico) DESC
FETCH FIRST 10 ROWS ONLY;
```

### PARTE 2: TRANSAÇÕES E CONTROLE

### 5. Conceitos de Transações

#### 5.1 Propriedades ACID
- **Atomicidade**: Transação é indivisível (tudo ou nada)
- **Consistência**: Dados ficam em estado válido
- **Isolamento**: Transações não interferem entre si
- **Durabilidade**: Mudanças persistem após COMMIT

#### 5.2 Estados de uma Transação
```sql
-- Transação iniciada implicitamente com primeiro DML
INSERT INTO usuario (id_usuario, nome_usuario, email) 
VALUES (100, 'João', 'joao@email.com');

-- Transação em andamento
UPDATE usuario SET nome_usuario = 'João Silva' WHERE id_usuario = 100;

-- Finalizar com sucesso
COMMIT;
-- OU desfazer
-- ROLLBACK;
```

### 6. Comandos de Controle de Transação

#### 6.1 COMMIT - Confirmar Mudanças
```sql
-- Inserir um novo artista
INSERT INTO artista (id_artista, nome_artista, pais_origem) 
VALUES (200, 'Novos Artistas', 'Brasil');

-- Confirmar a inserção
COMMIT;

-- Agora a mudança está persistente
```

#### 6.2 ROLLBACK - Desfazer Mudanças
```sql
-- Iniciar uma transação
INSERT INTO album (id_album, titulo, id_artista) 
VALUES (300, 'Álbum Teste', 200);

UPDATE artista SET nome_artista = 'Nome Errado' WHERE id_artista = 200;

-- Desfazer todas as mudanças da transação
ROLLBACK;

-- Verificar que mudanças foram desfeitas
SELECT * FROM artista WHERE id_artista = 200;
SELECT * FROM album WHERE id_album = 300;
```

#### 6.3 SAVEPOINT - Pontos de Salvamento
```sql
-- Iniciar transação
INSERT INTO usuario (id_usuario, nome_usuario, email) 
VALUES (101, 'Maria', 'maria@email.com');

-- Criar ponto de salvamento
SAVEPOINT user_inserted;

-- Fazer mais alterações
INSERT INTO playlist (id_playlist, nome_playlist, id_usuario) 
VALUES (201, 'Playlist Test', 101);

-- Criar outro savepoint
SAVEPOINT playlist_inserted;

-- Fazer alteração problemática
UPDATE usuario SET email = NULL WHERE id_usuario = 101; -- Pode violar constraint

-- Voltar ao savepoint anterior
ROLLBACK TO playlist_inserted;

-- Confirmar até aqui
COMMIT;
```

### 7. Autocommit e Controle Manual

#### 7.1 Configuração de Autocommit
```sql
-- Verificar status atual
SHOW AUTOCOMMIT;

-- Desabilitar autocommit para controle manual
SET AUTOCOMMIT OFF;

-- Habilitar autocommit (cada comando faz commit automático)
SET AUTOCOMMIT ON;
```

#### 7.2 Transações Explícitas
```sql
-- Iniciar transação explicitamente (alguns SGBDs)
START TRANSACTION; -- ou BEGIN TRANSACTION;

INSERT INTO genero (id_genero, nome_genero) VALUES (150, 'Test Genre');
UPDATE genero SET descricao = 'Gênero de teste' WHERE id_genero = 150;

-- Finalizar transação
COMMIT;
-- ou ROLLBACK;
```

### 8. Níveis de Isolamento

#### 8.1 Configuração de Isolamento
```sql
-- Verificar nível atual
SELECT * FROM V$TRANSACTION;

-- Definir nível de isolamento para transação
SET TRANSACTION ISOLATION LEVEL READ COMMITTED;
-- Ou outros níveis: READ UNCOMMITTED, REPEATABLE READ, SERIALIZABLE
```

#### 8.2 Problemas de Concorrência

**Dirty Read** - Ler dados não commitados:
```sql
-- Sessão 1
UPDATE artista SET nome_artista = 'Nome Temporário' WHERE id_artista = 1;
-- (não faz commit)

-- Sessão 2 (com READ UNCOMMITTED)
SELECT nome_artista FROM artista WHERE id_artista = 1;
-- Pode ver "Nome Temporário" mesmo sem commit
```

**Non-Repeatable Read** - Leituras diferentes na mesma transação:
```sql
-- Sessão 1
SET TRANSACTION ISOLATION LEVEL READ COMMITTED;
SELECT nome_artista FROM artista WHERE id_artista = 1; -- "The Beatles"

-- Sessão 2
UPDATE artista SET nome_artista = 'Beatles' WHERE id_artista = 1;
COMMIT;

-- Sessão 1 (mesma transação)
SELECT nome_artista FROM artista WHERE id_artista = 1; -- "Beatles" (diferente!)
```

### 9. Locks e Concorrência

#### 9.1 Tipos de Locks
```sql
-- Lock exclusivo (para UPDATE/DELETE)
SELECT * FROM artista WHERE id_artista = 1 FOR UPDATE;

-- Lock compartilhado (para SELECT consistente)
SELECT * FROM artista WHERE id_artista = 1 FOR SHARE;

-- Lock com timeout
SELECT * FROM artista WHERE id_artista = 1 FOR UPDATE WAIT 10;

-- Lock sem espera
SELECT * FROM artista WHERE id_artista = 1 FOR UPDATE NOWAIT;
```

#### 9.2 Detecção de Deadlocks
```sql
-- Monitorar locks ativos
SELECT 
    s.sid,
    s.serial#,
    s.username,
    o.object_name,
    l.mode_held,
    l.mode_requested
FROM v$locked_object l
JOIN dba_objects o ON l.object_id = o.object_id
JOIN v$session s ON l.session_id = s.sid;
```

### PARTE 3: CRIAÇÃO DE RELATÓRIOS AVANÇADOS

### 10. Criação de Relatórios Básicos

#### 10.1 Relatório de Artistas por País
```sql
-- Relatório básico formatado
SELECT 
    pais_origem as "País",
    COUNT(*) as "Total Artistas",
    COUNT(CASE WHEN ativo = 'S' THEN 1 END) as "Ativos",
    COUNT(CASE WHEN ativo = 'N' THEN 1 END) as "Inativos"
FROM artista 
WHERE pais_origem IS NOT NULL
GROUP BY pais_origem
ORDER BY COUNT(*) DESC;
```

#### 10.2 Relatório de Albums por Década
```sql
-- Relatório com agrupamento por década
SELECT 
    CASE 
        WHEN ano_lancamento BETWEEN 1960 AND 1969 THEN '1960s'
        WHEN ano_lancamento BETWEEN 1970 AND 1979 THEN '1970s'
        WHEN ano_lancamento BETWEEN 1980 AND 1989 THEN '1980s'
        WHEN ano_lancamento BETWEEN 1990 AND 1999 THEN '1990s'
        WHEN ano_lancamento BETWEEN 2000 AND 2009 THEN '2000s'
        WHEN ano_lancamento BETWEEN 2010 AND 2019 THEN '2010s'
        WHEN ano_lancamento >= 2020 THEN '2020s'
        ELSE 'Outros'
    END as "Década",
    COUNT(*) as "Total Álbuns",
    ROUND(AVG(numero_faixas), 1) as "Média Faixas"
FROM album 
WHERE ano_lancamento IS NOT NULL
GROUP BY 
    CASE 
        WHEN ano_lancamento BETWEEN 1960 AND 1969 THEN '1960s'
        WHEN ano_lancamento BETWEEN 1970 AND 1979 THEN '1970s'
        WHEN ano_lancamento BETWEEN 1980 AND 1989 THEN '1980s'
        WHEN ano_lancamento BETWEEN 1990 AND 1999 THEN '1990s'
        WHEN ano_lancamento BETWEEN 2000 AND 2009 THEN '2000s'
        WHEN ano_lancamento BETWEEN 2010 AND 2019 THEN '2010s'
        WHEN ano_lancamento >= 2020 THEN '2020s'
        ELSE 'Outros'
    END
ORDER BY "Década";
```

#### 10.3 Relatório de Top Músicas por Gênero
```sql
-- Relatório com ranking
SELECT 
    g.nome_genero as "Gênero",
    m.titulo as "Música",
    a.nome_artista as "Artista",
    COUNT(hr.id_historico) as "Reproduções",
    RANK() OVER (PARTITION BY g.nome_genero ORDER BY COUNT(hr.id_historico) DESC) as "Rank"
FROM genero g
JOIN musica m ON g.id_genero = m.id_genero
JOIN album al ON m.id_album = al.id_album
JOIN artista a ON al.id_artista = a.id_artista
LEFT JOIN historico_reproducao hr ON m.id_musica = hr.id_musica
GROUP BY g.nome_genero, m.titulo, a.nome_artista, m.id_musica
HAVING COUNT(hr.id_historico) > 0
ORDER BY g.nome_genero, "Reproduções" DESC;
```

### 11. Formatação de Relatórios

#### 11.1 Configuração de Formato
```sql
-- Configurar formato da página
SET PAGESIZE 50;
SET LINESIZE 120;
SET FEEDBACK OFF;
SET HEADING ON;

-- Definir títulos de colunas
COLUMN nome_artista FORMAT A30 HEADING 'Nome do Artista';
COLUMN total_albums FORMAT 999,999 HEADING 'Total|Álbuns';
COLUMN media_duracao FORMAT 99.99 HEADING 'Duração|Média(min)';
```

#### 11.2 Relatório com Formatação Avançada
```sql
-- Relatório bem formatado
SET PAGESIZE 60;
SET LINESIZE 150;
COLUMN artista FORMAT A25;
COLUMN albums FORMAT 999;
COLUMN musicas FORMAT 999;
COLUMN tempo_total FORMAT A12;
COLUMN status FORMAT A8;

TTITLE CENTER 'RELATÓRIO DE ARTISTAS - MUSISTREAM' SKIP 2;
BTITLE CENTER 'Gerado em: &_DATE';

SELECT 
    a.nome_artista as "Artista",
    COUNT(DISTINCT al.id_album) as "Albums",
    COUNT(m.id_musica) as "Músicas",
    CASE 
        WHEN SUM(m.duracao) IS NULL THEN 'N/A'
        ELSE TO_CHAR(FLOOR(SUM(m.duracao)/3600), '990') || 'h ' ||
             TO_CHAR(MOD(FLOOR(SUM(m.duracao)/60), 60), '00') || 'm'
    END as "Tempo Total",
    CASE WHEN a.ativo = 'S' THEN 'Ativo' ELSE 'Inativo' END as "Status"
FROM artista a
LEFT JOIN album al ON a.id_artista = al.id_artista  
LEFT JOIN musica m ON al.id_album = m.id_album
GROUP BY a.id_artista, a.nome_artista, a.ativo
HAVING COUNT(DISTINCT al.id_album) > 0
ORDER BY COUNT(DISTINCT al.id_album) DESC, a.nome_artista;

CLEAR COLUMNS;
CLEAR BREAKS;
TTITLE OFF;
BTITLE OFF;
```

### 12. Transações em Cenários Práticos

#### 12.1 Transferência de Playlist
```sql
-- Transação para transferir músicas entre playlists
SAVEPOINT inicio_transferencia;

-- Remover músicas da playlist origem
DELETE FROM playlist_musica 
WHERE id_playlist = 10 AND id_musica IN (1, 2, 3);

-- Verificar se remoção foi bem sucedida
IF SQL%ROWCOUNT = 0 THEN
    ROLLBACK TO inicio_transferencia;
    DBMS_OUTPUT.PUT_LINE('Erro: Nenhuma música removida');
ELSE
    -- Adicionar músicas na playlist destino
    INSERT INTO playlist_musica (id_playlist, id_musica, ordem_reproducao)
    SELECT 20, id_musica, ROWNUM + (SELECT MAX(ordem_reproducao) FROM playlist_musica WHERE id_playlist = 20)
    FROM (SELECT 1 as id_musica FROM dual UNION SELECT 2 FROM dual UNION SELECT 3 FROM dual);
    
    -- Confirmar transação
    COMMIT;
    DBMS_OUTPUT.PUT_LINE('Transferência concluída com sucesso');
END IF;
```

#### 12.2 Atualização de Estatísticas Consistente
```sql
-- Atualizar estatísticas de forma atômica
BEGIN
    -- Recalcular total de reproduções por usuário
    UPDATE usuario u
    SET (total_reproducoes, ultima_reproducao) = (
        SELECT COUNT(*), MAX(data_reproducao)
        FROM historico_reproducao hr
        WHERE hr.id_usuario = u.id_usuario
    );
    
    -- Recalcular estatísticas de playlists
    UPDATE playlist p
    SET (numero_musicas, duracao_total) = (
        SELECT COUNT(*), SUM(m.duracao)
        FROM playlist_musica pm
        JOIN musica m ON pm.id_musica = m.id_musica
        WHERE pm.id_playlist = p.id_playlist
    );
    
    -- Verificar consistência
    IF SQL%ROWCOUNT > 0 THEN
        COMMIT;
        DBMS_OUTPUT.PUT_LINE('Estatísticas atualizadas com sucesso');
    ELSE
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Erro na atualização de estatísticas');
    END IF;
END;
/
```

## Exercícios Práticos

Consulte a pasta `exercicios` para atividades práticas que reforçam os conceitos apresentados.

## Perguntas e Respostas

### 1. Qual a diferença entre SELECT com INNER JOIN, LEFT JOIN e RIGHT JOIN?

**Resposta**: Cada tipo de JOIN tem comportamento diferente para combinação de dados:

**INNER JOIN** - Retorna apenas registros com correspondência em ambas as tabelas:
```sql
-- Retorna apenas artistas que têm álbuns
SELECT a.nome_artista, al.titulo
FROM artista a
INNER JOIN album al ON a.id_artista = al.id_artista;
```
- **Uso**: Quando você precisa apenas de registros que existem em ambas as tabelas
- **Exemplo**: Listar músicas com seus álbuns (música sem álbum não aparece)

**LEFT JOIN** - Retorna todos os registros da tabela à esquerda, mesmo sem correspondência:
```sql
-- Retorna todos os artistas, incluindo os sem álbuns
SELECT a.nome_artista, COUNT(al.id_album) as total_albums
FROM artista a
LEFT JOIN album al ON a.id_artista = al.id_artista
GROUP BY a.id_artista, a.nome_artista;
```
- **Uso**: Quando você quer garantir que todos os registros da tabela principal apareçam
- **Exemplo**: Listar todos os artistas e seus álbuns (artistas sem álbuns aparecem com COUNT = 0)

**RIGHT JOIN** - Retorna todos os registros da tabela à direita, mesmo sem correspondência:
```sql
-- Retorna todos os álbuns, incluindo os sem artista (raro)
SELECT a.nome_artista, al.titulo
FROM artista a
RIGHT JOIN album al ON a.id_artista = al.id_artista;
```
- **Uso**: Menos comum, similar ao LEFT JOIN mas inverte a direção
- **Dica**: Prefira LEFT JOIN e reordene as tabelas para melhor legibilidade

### 2. Como usar WHERE eficientemente para filtrar grandes volumes de dados?

**Resposta**: Boas práticas para filtros eficientes:

**Use índices nas colunas de filtro**:
```sql
-- Rápido se houver índice em pais_origem
SELECT nome_artista FROM artista WHERE pais_origem = 'Brasil';
```

**Evite funções em colunas indexadas**:
```sql
-- Lento (índice não é usado):
SELECT nome_artista FROM artista WHERE UPPER(nome_artista) = 'THE BEATLES';

-- Rápido (índice é usado):
SELECT nome_artista FROM artista WHERE nome_artista = 'The Beatles';
```

**Use operadores apropriados**:
```sql
-- IN é eficiente para listas pequenas
WHERE pais_origem IN ('Brasil', 'Portugal', 'Argentina');

-- BETWEEN é eficiente para intervalos
WHERE ano_lancamento BETWEEN 2000 AND 2010;

-- LIKE com % no início é lento (não usa índice)
WHERE nome_artista LIKE '%Beatles'; -- Evitar se possível

-- LIKE sem % no início é rápido (usa índice)
WHERE nome_artista LIKE 'Beatles%'; -- Preferível
```

### 3. Quando usar DISTINCT e quais os impactos de performance?

**Resposta**: DISTINCT remove duplicatas, mas tem custo computacional:

**Uso apropriado de DISTINCT**:
```sql
-- Bom uso: Listar países únicos
SELECT DISTINCT pais_origem FROM artista;

-- Bom uso: Contagem de valores únicos
SELECT COUNT(DISTINCT pais_origem) FROM artista;
```

**Evite DISTINCT desnecessário**:
```sql
-- Desnecessário se id_artista já é único
SELECT DISTINCT id_artista, nome_artista FROM artista;

-- Melhor: Use GROUP BY quando apropriado
SELECT pais_origem, COUNT(*) 
FROM artista 
GROUP BY pais_origem;
```

**Impactos de performance**:
- DISTINCT requer ordenação/hash dos resultados
- Pode ser lento em grandes volumes de dados
- Considere se o problema não está no JOIN que gera duplicatas

### 4. Quais são as propriedades ACID e por que são fundamentais?

**Resposta**: ACID garante confiabilidade das transações:

**Atomicidade (Atomicity)**:
- Transação é executada completamente ou não é executada
- "Tudo ou nada" - não há estados intermediários
- Exemplo: Transferência bancária deve debitar e creditar, ou não fazer nada

**Consistência (Consistency)**:
- Banco de dados passa de um estado consistente para outro
- Todas as regras e constraints são respeitadas
- Exemplo: Saldo bancário nunca fica negativo (se houver constraint)

**Isolamento (Isolation)**:
- Transações concorrentes não interferem entre si
- Cada transação vê o banco como se fosse a única executando
- Diferentes níveis: READ UNCOMMITTED, READ COMMITTED, REPEATABLE READ, SERIALIZABLE

**Durabilidade (Durability)**:
- Mudanças confirmadas persistem mesmo com falhas do sistema
- Garantida através de logs de transação e backup

### 5. Quando usar COMMIT vs. ROLLBACK vs. SAVEPOINT?

**Resposta**:
**COMMIT**: Para confirmar mudanças
```sql
BEGIN;
INSERT INTO usuario (nome) VALUES ('João');
UPDATE usuario SET email = 'joao@email.com' WHERE nome = 'João';
COMMIT; -- Confirma ambas as operações
```

**ROLLBACK**: Para cancelar mudanças
```sql
BEGIN;
DELETE FROM playlist WHERE id_usuario = 123;
-- Ops, foi erro! Cancelar
ROLLBACK; -- Nada foi realmente excluído
```

**SAVEPOINT**: Para rollback parcial
```sql
BEGIN;
INSERT INTO artista (nome) VALUES ('Banda A');
SAVEPOINT sp1;
INSERT INTO artista (nome) VALUES ('Banda B'); -- Erro!
ROLLBACK TO sp1; -- Cancela apenas 'Banda B'
COMMIT; -- Confirma 'Banda A'
```

### 6. Como diferentes níveis de isolamento afetam a concorrência?

**Resposta**: Trade-off entre consistência e performance:

**READ UNCOMMITTED** (menos isolamento):
- Pode ler dados não commitados (dirty read)
- Máxima concorrência, mínima consistência
- Raramente usado em produção

**READ COMMITTED** (padrão na maioria dos SGBDs):
- Só lê dados commitados
- Evita dirty reads
- Permite non-repeatable reads

**REPEATABLE READ**:
- Garante que releituras retornem mesmo resultado
- Evita dirty reads e non-repeatable reads
- Permite phantom reads

**SERIALIZABLE** (máximo isolamento):
- Transações executam como se fossem sequenciais
- Evita todos os problemas de concorrência
- Menor performance devido a locks

### 7. Como identificar e resolver deadlocks?

**Resposta**: Estratégias de prevenção e resolução:

**Identificação de deadlock**:
```sql
-- Exemplo de deadlock
-- Transação A:
UPDATE artista SET nome = 'The Beatles' WHERE id = 1;
UPDATE album SET titulo = 'Abbey Road' WHERE id = 1;

-- Transação B (concorrente):
UPDATE album SET titulo = 'Sgt Pepper' WHERE id = 2;
UPDATE artista SET nome = 'The Beatles Updated' WHERE id = 1; -- DEADLOCK!
```

**Prevenção**:
- Sempre acessar tabelas na mesma ordem
- Usar timeouts apropriados
- Manter transações curtas
- Usar locks apropriados

**Resolução automática**: SGBD detecta e mata uma das transações.

### 8. Qual a diferença entre bloqueio otimista e pessimista?

**Resposta**:
**Bloqueio Pessimista**: Assume que conflitos vão ocorrer
```sql
-- Lock explícito
SELECT * FROM conta WHERE id = 123 FOR UPDATE;
UPDATE conta SET saldo = saldo - 100 WHERE id = 123;
COMMIT;
```
- **Vantagem**: Evita conflitos
- **Desvantagem**: Reduz concorrência

**Bloqueio Otimista**: Assume que conflitos são raros
```sql
-- Usa campo version/timestamp para controle
SELECT saldo, version FROM conta WHERE id = 123;
-- Na aplicação, verifica se version não mudou antes do UPDATE
UPDATE conta SET saldo = saldo - 100, version = version + 1 
WHERE id = 123 AND version = @version_original;
```
- **Vantagem**: Maior concorrência
- **Desvantagem**: Necessita retry em caso de conflito

### 9. Como estruturar relatórios SQL eficientes?

**Resposta**: Boas práticas para relatórios:

**Estrutura clara**:
```sql
SELECT 
    a.nome_artista,
    COUNT(m.id_musica) as total_musicas,
    AVG(m.duracao) as duracao_media
FROM artista a
LEFT JOIN album al ON a.id_artista = al.id_artista
LEFT JOIN musica m ON al.id_album = m.id_album
WHERE a.ativo = TRUE
GROUP BY a.id_artista, a.nome_artista
HAVING COUNT(m.id_musica) > 10
ORDER BY total_musicas DESC;
```

**Otimizações**:
- Use índices em colunas de WHERE e JOIN
- Evite SELECT * em tabelas grandes
- Use LIMIT para relatórios paginados
- Considere views para relatórios complexos reutilizáveis

### 10. Quando usar transações explícitas vs. auto-commit?

**Resposta**:
**Auto-commit** (padrão): Cada comando é uma transação
```sql
INSERT INTO artista VALUES (1, 'Beatles'); -- AUTO-COMMIT
UPDATE artista SET nome = 'The Beatles' WHERE id = 1; -- AUTO-COMMIT
```
- **Uso**: Operações simples, independentes
- **Vantagem**: Simples, sem gerenciamento manual

**Transações explícitas**: Múltiplos comandos em uma transação
```sql
BEGIN;
INSERT INTO artista VALUES (1, 'Beatles');
INSERT INTO album VALUES (1, 'Abbey Road', 1);
INSERT INTO musica VALUES (1, 'Come Together', 1);
COMMIT;
```
- **Uso**: Operações relacionadas que devem ser atômicas
- **Vantagem**: Consistência, possibilidade de rollback
- **Cuidado**: Locks prolongados, possível deadlock

**Recomendação**: Use transações explícitas para operações relacionadas que precisam ser atômicas.

## Referências Bibliográficas

- **Garcia-Molina, H., Ullman, J. D., & Widom, J.** (2013). *Database Systems: The Complete Book*. 2nd Edition. Pearson. Capítulos sobre Transactions.
- **Gray, J. & Reuter, A.** (1992). *Transaction Processing: Concepts and Techniques*. Morgan Kaufmann.
- **Oracle Corporation** (2021). *Oracle Database Concepts*. Capítulo sobre Transaction Management.

## Próximos Passos

No próximo módulo (10), estudaremos **Relatórios com Filtros e Operadores**, explorando claúsulas WHERE avançadas e operadores relacionais e lógicos.