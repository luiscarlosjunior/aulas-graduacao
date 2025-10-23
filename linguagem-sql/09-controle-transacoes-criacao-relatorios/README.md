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

A cláusula WHERE é uma das ferramentas mais poderosas do SQL, permitindo **filtrar registros** baseado em condições específicas. É essencial para trabalhar com grandes volumes de dados, retornando apenas as informações relevantes.

**Por que WHERE é importante?**
- **Performance**: Filtra dados no banco (mais rápido que filtrar na aplicação)
- **Precisão**: Retorna exatamente os dados necessários
- **Economia**: Reduz tráfego de rede e uso de memória
- **Segurança**: Limita acesso a dados específicos

**Como funciona:**
- WHERE é executado **após FROM/JOIN** e **antes de GROUP BY**
- Cada linha é avaliada individualmente
- Apenas linhas onde a condição é TRUE são incluídas
- Pode combinar múltiplas condições com AND, OR, NOT

**Sintaxe:**
```sql
SELECT colunas
FROM tabela
WHERE condição;
```

#### 3.1 Operadores de Comparação

Os operadores de comparação permitem testar valores de colunas contra valores específicos ou outras colunas.

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

**Como funcionam:**
- `=` : Igualdade exata (case-sensitive para texto)
- `<>` ou `!=` : Diferente de (ambas sintaxes funcionam igual)
- `>` : Maior que (exclusivo - não inclui o valor)
- `<` : Menor que (exclusivo - não inclui o valor)
- `>=` : Maior ou igual (inclusivo - inclui o valor)
- `<=` : Menor ou igual (inclusivo - inclui o valor)

**Por que usar:**
- **Filtros Numéricos**: Encontrar valores acima/abaixo de limites (preços, idades, quantidades)
- **Filtros de Data**: Registros antes/depois de datas específicas
- **Filtros de Texto**: Comparação exata de strings (menos comum que LIKE)
- **Exclusão**: Remover valores específicos com `<>` ou `!=`

**Comportamento com NULL:**
```sql
-- INCORRETO: Comparação com NULL sempre retorna NULL (nem TRUE nem FALSE)
SELECT * FROM artista WHERE data_formacao = NULL;  -- Não retorna nada!

-- CORRETO: Use IS NULL ou IS NOT NULL
SELECT * FROM artista WHERE data_formacao IS NULL;  -- Funciona!
```

**Dicas de Performance:**
- Filtros em colunas indexadas são muito mais rápidos
- Evite aplicar funções na coluna do WHERE (quebra uso de índice)
```sql
-- Lento (não usa índice):
WHERE UPPER(nome) = 'JOÃO'

-- Rápido (usa índice):
WHERE nome = 'João'  -- ou WHERE nome = UPPER('joão') se índice for case-insensitive
```

#### 3.2 Operadores Lógicos (AND, OR, NOT)

Operadores lógicos permitem combinar múltiplas condições em um único filtro, criando critérios de seleção mais complexos e precisos.

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

**Como funcionam:**

**AND** - Lógica de Conjunção:
- Retorna TRUE apenas se TODAS as condições forem TRUE
- Uso: Filtros restritivos (quanto mais AND, menos resultados)
- Exemplo: "Artistas do Brasil AND formados depois de 2000"

**OR** - Lógica de Disjunção:
- Retorna TRUE se PELO MENOS UMA condição for TRUE
- Uso: Filtros expansivos (quanto mais OR, mais resultados)
- Exemplo: "Artistas do Brasil OR Portugal OR Argentina"

**NOT** - Negação:
- Inverte o resultado da condição
- `NOT (condição)` é o oposto de `condição`
- Exemplo: "NOT (pais = 'Brasil')" = "todos países exceto Brasil"

**Por que usar:**
- **Filtros Complexos**: Combinar múltiplos critérios
- **Cenários Reais**: Requisitos de negócio raramente são simples
- **Flexibilidade**: Criar filtros dinâmicos baseados em preferências do usuário
- **Eficiência**: Uma query com múltiplas condições é melhor que várias queries simples

**Precedência de Operadores:**
```sql
-- Sem parênteses: NOT > AND > OR
-- Com parênteses: força ordem de avaliação

-- AMBÍGUO (evitar):
WHERE pais = 'Brasil' OR pais = 'Portugal' AND ativo = 'S'
-- Interpretado como: pais = 'Brasil' OR (pais = 'Portugal' AND ativo = 'S')

-- CLARO (usar parênteses):
WHERE (pais = 'Brasil' OR pais = 'Portugal') AND ativo = 'S'
-- Retorna artistas brasileiros OU portugueses que estejam ativos
```

**Tabela Verdade:**
```
A = TRUE, B = TRUE:   A AND B = TRUE,  A OR B = TRUE
A = TRUE, B = FALSE:  A AND B = FALSE, A OR B = TRUE  
A = FALSE, B = TRUE:  A AND B = FALSE, A OR B = TRUE
A = FALSE, B = FALSE: A AND B = FALSE, A OR B = FALSE
```

**Exemplos práticos:**

**Busca de álbuns específicos:**
```sql
-- Álbuns dos anos 2000 com muitas ou poucas faixas
WHERE ano_lancamento BETWEEN 2000 AND 2010
  AND (numero_faixas > 15 OR numero_faixas < 8);
```

**Filtro de múltiplos países exceto um:**
```sql
-- Artistas latinos exceto Brasil
WHERE (pais_origem = 'Argentina' OR pais_origem = 'México')
  AND NOT (pais_origem = 'Brasil');
-- Ou simplesmente: WHERE pais_origem IN ('Argentina', 'México')
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

**Como funciona:**
- IN testa se um valor existe em uma lista de valores
- Equivale a múltiplos OR: `WHERE x IN (1,2,3)` = `WHERE x = 1 OR x = 2 OR x = 3`
- Mais legível e conciso que vários OR
- Pode conter valores literais ou resultado de subconsulta

**Por que usar:**
- **Legibilidade**: Mais claro que múltiplos OR
- **Manutenção**: Fácil adicionar/remover valores da lista
- **Performance**: Em muitos bancos, IN é otimizado melhor que OR
- **Listas Dinâmicas**: Pode usar subconsulta: `WHERE id IN (SELECT ...)`

**IN vs OR:**
```sql
-- Com OR (verboso):
WHERE pais = 'Brasil' OR pais = 'Portugal' OR pais = 'Argentina' OR pais = 'Chile'

-- Com IN (conciso):
WHERE pais IN ('Brasil', 'Portugal', 'Argentina', 'Chile')
```

**NOT IN - Cuidados:**
```sql
-- CUIDADO: NOT IN com NULL não funciona como esperado
-- Se a lista contém NULL, NOT IN pode retornar resultado vazio

-- Lista com NULL:
WHERE id NOT IN (1, 2, NULL)  -- Pode não retornar nada!

-- Solução: filtrar NULL ou usar NOT EXISTS
WHERE id NOT IN (SELECT id FROM outra_tabela WHERE id IS NOT NULL)
```

**IN com Subconsulta:**
```sql
-- Artistas que têm álbuns
SELECT nome_artista
FROM artista
WHERE id_artista IN (SELECT id_artista FROM album);

-- Usuários que criaram playlists
SELECT nome_usuario
FROM usuario
WHERE id_usuario IN (SELECT id_usuario FROM playlist);
```

**Performance:**
- IN é eficiente para listas pequenas (< 1000 valores)
- Para listas grandes, considere JOIN ou tabela temporária
- Com subconsultas, o otimizador decide entre IN e EXISTS

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

**Como funciona:**
- BETWEEN testa se valor está em um intervalo **inclusivo** (inclui os extremos)
- `WHERE x BETWEEN a AND b` é equivalente a `WHERE x >= a AND x <= b`
- Funciona com números, datas, e até strings (ordem alfabética)
- Sempre use valor menor primeiro, depois maior

**Por que usar:**
- **Legibilidade**: Mais claro que usar >= e <=
- **Conciso**: Uma palavra ao invés de duas comparações
- **Intenção Clara**: Deixa óbvio que você quer um intervalo
- **Comum**: Intervalos são muito comuns (faixas de preço, períodos, idades)

**BETWEEN é Inclusivo:**
```sql
-- BETWEEN inclui os valores dos extremos
WHERE ano BETWEEN 2000 AND 2010
-- Inclui álbuns de 2000 E de 2010

-- Equivalente a:
WHERE ano >= 2000 AND ano <= 2010
```

**BETWEEN com Datas (cuidado com horas):**
```sql
-- CUIDADO: Se data tem hora, BETWEEN pode não incluir último dia completo
WHERE data_cadastro BETWEEN TO_DATE('2023-01-01', 'YYYY-MM-DD')
                       AND TO_DATE('2023-12-31', 'YYYY-MM-DD')
-- Se data_cadastro tem hora (timestamp), registros de 31/12/2023 23:59:59 são incluídos,
-- mas isso funciona porque BETWEEN é inclusivo

-- Alternativa mais segura para o dia seguinte:
WHERE data_cadastro >= TO_DATE('2023-01-01', 'YYYY-MM-DD')
  AND data_cadastro < TO_DATE('2024-01-01', 'YYYY-MM-DD')
```

**BETWEEN com Texto:**
```sql
-- Artistas cujo nome começa com A, B ou C
SELECT nome_artista
FROM artista
WHERE nome_artista BETWEEN 'A' AND 'D'
ORDER BY nome_artista;
-- Retorna: 'Arctic Monkeys', 'Beatles', 'Coldplay' mas não 'David Bowie'
```

**NOT BETWEEN:**
```sql
-- Músicas muito curtas ou muito longas (exclui intervalo médio)
SELECT titulo, duracao
FROM musica
WHERE duracao NOT BETWEEN 120 AND 300;
-- Equivalente a: WHERE duracao < 120 OR duracao > 300
```

**Performance:**
- BETWEEN pode usar índices eficientemente
- Mais eficiente que OR em muitos casos
- Em colunas indexadas, performance é excelente

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

**Como funciona:**
- LIKE busca padrões em texto usando **curingas** (wildcards)
- `%` = zero ou mais caracteres de qualquer tipo
- `_` = exatamente um caractere de qualquer tipo
- Case-sensitive na maioria dos bancos (incluindo Oracle)

**Curingas:**
- **`%`** - Representa qualquer sequência de caracteres (0 ou mais)
  - `'The%'` = começa com "The": "The Beatles", "The Who", "The"
  - `'%Band'` = termina com "Band": "Blues Band", "Band"
  - `'%Rock%'` = contém "Rock": "Rock Band", "The Rockers", "Rock"
  
- **`_`** - Representa exatamente UM caractere
  - `'B_nd'` = "Band", "Bind", "Bond" (mas não "Bound" - 5 letras)
  - `'_____'` = exatamente 5 caracteres: "Blues", "Rock", etc.
  - `'A__'` = começa com A + 2 caracteres: "ABC", "All"

**Por que usar:**
- **Buscas Parciais**: Encontrar registros sem saber texto completo
- **Autocomplete**: Implementar sugestões enquanto usuário digita
- **Filtros Flexíveis**: "Mostre artistas com 'rock' no nome"
- **Validação**: Verificar padrões (ex: emails, telefones)

**Padrões Comuns:**

**Começa com:**
```sql
WHERE nome LIKE 'The%'  -- "The Beatles", "The Who"
```

**Termina com:**
```sql
WHERE nome LIKE '%Jr.'  -- "Robert Downey Jr.", "Jr."
```

**Contém:**
```sql
WHERE email LIKE '%@gmail.com'  -- Emails do Gmail
WHERE titulo LIKE '%love%'       -- Músicas com "love" no título
```

**Comprimento Específico:**
```sql
WHERE codigo LIKE '___'    -- Códigos com exatamente 3 caracteres
WHERE telefone LIKE '(__)_____-____'  -- Formato: (11)98765-4321
```

**Padrão com posição específica:**
```sql
WHERE placa LIKE '___-____'  -- ABC-1234 (3 letras, hífen, 4 números)
```

**Case-Insensitive:**
```sql
-- Oracle: usar UPPER() ou LOWER()
WHERE UPPER(nome) LIKE UPPER('%rock%')  -- Encontra "Rock", "ROCK", "rock"

-- Alguns bancos têm ILIKE:
WHERE nome ILIKE '%rock%'  -- PostgreSQL
```

**Performance - CUIDADO:**
```sql
-- LENTO: % no início não pode usar índice
WHERE nome LIKE '%Beatles'      -- Scans toda tabela
WHERE nome LIKE '%Beatles%'     -- Scans toda tabela

-- RÁPIDO: sem % no início pode usar índice
WHERE nome LIKE 'Beatles%'      -- Usa índice
WHERE nome LIKE 'The B%'        -- Usa índice

-- SOLUÇÃO para busca full-text: índices especializados
-- Oracle: índices CONTEXT, PostgreSQL: índices GIN, MySQL: FULLTEXT
```

**Escape de caracteres especiais:**
```sql
-- Se você quer buscar literal "%" ou "_"
WHERE descricao LIKE '%100\%%' ESCAPE '\'  -- Encontra "100%"
-- Neste exemplo, \% significa o caractere literal "%"
```

**LIKE vs =:**
```sql
-- = é exato e mais rápido
WHERE nome = 'The Beatles'  -- Exatamente "The Beatles"

-- LIKE é para padrões
WHERE nome LIKE 'The Beatles'  -- Também exatamente, mas mais lento que =
WHERE nome LIKE 'The%'  -- Padrão: começa com "The"
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

**Como funciona:**
- NULL representa **ausência de valor** (não é zero, não é string vazia)
- IS NULL testa se valor está ausente/indefinido
- IS NOT NULL testa se valor está presente/definido
- NULL não pode ser comparado com = ou <> (sempre retorna NULL, não TRUE/FALSE)

**Por que NULL é especial:**
```sql
-- INCORRETO: Não funciona!
WHERE data_formacao = NULL   -- Sempre retorna nenhuma linha
WHERE data_formacao <> NULL  -- Sempre retorna nenhuma linha

-- CORRETO: Use IS NULL / IS NOT NULL
WHERE data_formacao IS NULL
WHERE data_formacao IS NOT NULL
```

**Por que usar:**
- **Dados Incompletos**: Identificar registros com informações faltantes
- **Validação**: Encontrar campos obrigatórios não preenchidos
- **Limpeza de Dados**: Listar registros que precisam ser atualizados
- **Regras de Negócio**: Diferentes comportamentos para valores definidos vs. indefinidos
- **Reports**: Separar dados completos de incompletos

**NULL em diferentes contextos:**

**Encontrar dados faltantes:**
```sql
-- Artistas sem biografia
SELECT nome_artista FROM artista WHERE biografia IS NULL;

-- Usuários sem telefone
SELECT nome_usuario FROM usuario WHERE telefone IS NULL;
```

**Filtrar dados completos:**
```sql
-- Apenas álbuns com ano de lançamento conhecido
SELECT titulo, ano_lancamento
FROM album
WHERE ano_lancamento IS NOT NULL
ORDER BY ano_lancamento DESC;
```

**Combinar com outros filtros:**
```sql
-- Artistas brasileiros sem data de formação
SELECT nome_artista, pais_origem
FROM artista
WHERE pais_origem = 'Brasil' AND data_formacao IS NULL;
```

**Comportamento do NULL em operações:**
```sql
-- NULL em comparações retorna NULL (nem TRUE nem FALSE)
NULL = NULL    --> NULL (não TRUE!)
NULL <> NULL   --> NULL
NULL > 10      --> NULL
'ABC' = NULL   --> NULL

-- NULL em lógica booleana
TRUE AND NULL  --> NULL
FALSE AND NULL --> FALSE
TRUE OR NULL   --> TRUE
FALSE OR NULL  --> NULL
NOT NULL       --> NULL
```

**COALESCE - Substituir NULL:**
```sql
-- Mostrar valor padrão quando NULL
SELECT 
    nome_artista,
    COALESCE(pais_origem, 'Desconhecido') as pais,
    COALESCE(data_formacao, TO_DATE('1900-01-01', 'YYYY-MM-DD')) as data
FROM artista;
```

**NVL (Oracle específico) - Substituir NULL:**
```sql
SELECT 
    nome_artista,
    NVL(pais_origem, 'Não informado') as pais
FROM artista;
```

**Count e NULL:**
```sql
-- COUNT(*) conta todas as linhas
SELECT COUNT(*) FROM artista;  -- Conta tudo, incluindo NULL

-- COUNT(coluna) ignora NULL
SELECT COUNT(data_formacao) FROM artista;  -- Conta apenas não-NULL

-- COUNT(DISTINCT coluna) ignora NULL e duplicatas
SELECT COUNT(DISTINCT pais_origem) FROM artista;  -- Países únicos, ignora NULL
```

**Performance:**
- IS NULL pode usar índices parciais/específicos
- IS NOT NULL normalmente faz full table scan (a menos que coluna seja NOT NULL)
- Em tabelas grandes com muitos NULL, considere índices parciais

### 4. Consultas com JOINs (Múltiplas Tabelas)

JOINs são uma das funcionalidades mais poderosas do SQL, permitindo **combinar dados de duas ou mais tabelas** baseado em relacionamentos entre elas. Em bancos de dados relacionais, informações geralmente estão distribuídas em várias tabelas, e JOINs permitem reuni-las de forma significativa.

**Por que JOINs são fundamentais?**
- **Normalização**: Bancos relacionais dividem dados em tabelas para evitar redundância
- **Relacionamentos**: Dados do mundo real têm relacionamentos (artistas → álbuns → músicas)
- **Consultas Complexas**: Relatórios precisam de dados de múltiplas fontes
- **Integridade**: Evita duplicação e inconsistência de dados

**Como JOINs funcionam:**
- Combinam linhas de duas tabelas baseado em uma **condição de junção** (geralmente chaves)
- Diferentes tipos de JOIN controlam quais linhas aparecem no resultado
- São processados na fase FROM (antes de WHERE, GROUP BY, etc.)
- Podem ser encadeados: tabela1 JOIN tabela2 JOIN tabela3...

**Tipos principais de JOIN:**
1. **INNER JOIN**: Apenas registros com correspondência em ambas as tabelas
2. **LEFT JOIN**: Todos da esquerda + correspondências da direita
3. **RIGHT JOIN**: Todos da direita + correspondências da esquerda
4. **FULL OUTER JOIN**: Todos de ambas as tabelas
5. **SELF JOIN**: Tabela junta consigo mesma
6. **CROSS JOIN**: Produto cartesiano (raramente usado)

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

**Como funciona:**
- INNER JOIN retorna **apenas linhas com correspondência em AMBAS as tabelas**
- É o tipo mais comum de JOIN (mais de 80% dos casos)
- Se um artista não tem álbuns, ele não aparece no resultado
- Se um álbum não tem artista, ele não aparece no resultado

**Sintaxe:**
```sql
SELECT colunas
FROM tabela1 t1
INNER JOIN tabela2 t2 ON t1.chave = t2.chave;
```

**Por que usar:**
- **Dados Relacionados**: Quando você precisa de informações de tabelas relacionadas
- **Filtro Implícito**: Automaticamente exclui registros órfãos
- **Mais Comum**: Maioria dos relatórios usa INNER JOIN
- **Performance**: Geralmente mais rápido que outros tipos de JOIN

**Exemplo conceitual:**
```
Tabela artista:          Tabela album:
id | nome                id | titulo        | id_artista
1  | Beatles             101| Abbey Road    | 1
2  | Pink Floyd          102| The Wall      | 2
3  | Queen               103| A Night...    | 3
4  | Sem Álbum           (nenhum álbum para id_artista=4)

INNER JOIN resultado:
Beatles    | Abbey Road
Pink Floyd | The Wall
Queen      | A Night...
(Artista 4 "Sem Álbum" NÃO aparece - sem correspondência)
```

**Aliases de tabela:**
```sql
-- Sem alias (verboso):
SELECT artista.nome_artista, album.titulo
FROM artista
INNER JOIN album ON artista.id_artista = album.id_artista;

-- Com alias (recomendado):
SELECT a.nome_artista, al.titulo
FROM artista a
INNER JOIN album al ON a.id_artista = al.id_artista;
```

**Múltiplos JOINs:**
```sql
-- Encadeamento: música → álbum → artista
SELECT 
    ar.nome_artista,
    al.titulo,
    m.titulo AS musica
FROM musica m                            -- Tabela base
INNER JOIN album al                      -- 1º JOIN
    ON m.id_album = al.id_album
INNER JOIN artista ar                    -- 2º JOIN
    ON al.id_artista = ar.id_artista;
```

**Quando NÃO usar:**
- Quando você precisa de registros mesmo sem correspondência (use LEFT JOIN)
- Para listar todos os registros de uma tabela (use LEFT ou RIGHT JOIN)

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

**Como funciona:**
- LEFT JOIN (ou LEFT OUTER JOIN) retorna **TODAS as linhas da tabela da ESQUERDA**
- Para linhas sem correspondência à direita, colunas da direita ficam NULL
- Garante que nenhum registro da tabela esquerda seja perdido
- "Esquerda" = tabela antes do JOIN, "Direita" = tabela após o JOIN

**Sintaxe:**
```sql
SELECT colunas
FROM tabela_esquerda t1
LEFT JOIN tabela_direita t2 ON t1.chave = t2.chave;
```

**Por que usar:**
- **Garantir Completude**: Ver todos os registros da tabela principal
- **Encontrar Órfãos**: Identificar registros sem relacionamentos (WHERE direita IS NULL)
- **Contagens Totais**: Incluir zeros em agregações
- **Relatórios Completos**: Não perder dados mesmo sem correspondência

**Exemplo conceitual:**
```
Tabela artista:          Tabela album:
id | nome                id | titulo        | id_artista
1  | Beatles             101| Abbey Road    | 1
2  | Pink Floyd          102| The Wall      | 2
3  | Queen               (nenhum álbum para id=3)
4  | Sem Álbum           (nenhum álbum para id=4)

LEFT JOIN resultado:
Beatles    | Abbey Road
Pink Floyd | The Wall
Queen      | NULL          (aparece mesmo sem álbum)
Sem Álbum  | NULL          (aparece mesmo sem álbum)
```

**Encontrar registros órfãos:**
```sql
-- Artistas que NÃO têm álbuns
SELECT a.nome_artista
FROM artista a
LEFT JOIN album al ON a.id_artista = al.id_artista
WHERE al.id_album IS NULL;  -- Chave: testar se direita é NULL
```

**LEFT JOIN com agregação:**
```sql
-- Contar álbuns por artista (incluindo artistas com 0 álbuns)
SELECT 
    a.nome_artista,
    COUNT(al.id_album) AS total_albuns  -- COUNT(coluna) ignora NULL
FROM artista a
LEFT JOIN album al ON a.id_artista = al.id_artista
GROUP BY a.id_artista, a.nome_artista;

-- Artista sem álbum retorna: "Nome | 0"
-- Com INNER JOIN, artista sem álbum não apareceria
```

**Diferença crucial: COUNT(*) vs COUNT(coluna):**
```sql
-- COUNT(*) conta linhas (incluindo NULL)
SELECT a.nome_artista, COUNT(*) 
FROM artista a LEFT JOIN album al ON a.id_artista = al.id_artista
GROUP BY a.nome_artista;
-- Artista sem álbum: retorna 1 (conta a linha com NULL)

-- COUNT(coluna) ignora NULL - CORRETO para LEFT JOIN!
SELECT a.nome_artista, COUNT(al.id_album)
FROM artista a LEFT JOIN album al ON a.id_artista = al.id_artista
GROUP BY a.nome_artista;
-- Artista sem álbum: retorna 0 (não conta NULL)
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

**Como funciona:**
- RIGHT JOIN (ou RIGHT OUTER JOIN) retorna **TODAS as linhas da tabela da DIREITA**
- Para linhas sem correspondência à esquerda, colunas da esquerda ficam NULL
- É o oposto do LEFT JOIN
- Menos comum que LEFT JOIN (preferência: reorganizar query para usar LEFT JOIN)

**Por que usar:**
- **Todos da Direita**: Garantir que registros da segunda tabela não sejam perdidos
- **Menos Comum**: Maioria dos desenvolvedores prefere LEFT JOIN e inverte as tabelas
- **Simetria**: Útil quando a lógica natural coloca a tabela "completa" à direita

**LEFT vs RIGHT:**
```sql
-- Estas duas queries são equivalentes:

-- Com LEFT JOIN:
SELECT a.nome, al.titulo
FROM artista a
LEFT JOIN album al ON a.id_artista = al.id_artista;

-- Com RIGHT JOIN (menos intuitivo):
SELECT a.nome, al.titulo
FROM album al
RIGHT JOIN artista a ON a.id_artista = al.id_artista;
```

**Recomendação:** Prefira LEFT JOIN reorganizando tabelas. É mais intuitivo:
- Tabela principal (todos os registros) → esquerda
- Tabela secundária (correspondências) → direita
- Use LEFT JOIN

**Exemplo conceitual:**
```
Tabela artista:          Tabela album:
id | nome                id | titulo        | id_artista
1  | Beatles             101| Abbey Road    | 1
2  | Pink Floyd          102| The Wall      | 2
                           103| Órfão         | 999 (artista não existe)

RIGHT JOIN resultado (artista RIGHT JOIN album):
Beatles    | Abbey Road
Pink Floyd | The Wall
NULL       | Órfão          (álbum aparece mesmo sem artista)
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

**Como funciona:**
- FULL OUTER JOIN retorna **TODAS as linhas de AMBAS as tabelas**
- Combina LEFT JOIN + RIGHT JOIN
- Linhas sem correspondência têm NULL nas colunas da outra tabela
- Raramente usado (maioria dos casos usa LEFT ou INNER JOIN)

**Por que usar:**
- **Auditoria**: Encontrar todas as inconsistências (órfãos de ambos os lados)
- **Relatórios Completos**: Ver tudo, independente de correspondência
- **Análise de Integridade**: Identificar problemas de dados
- **Raro**: Menos de 5% dos JOINs em produção

**Exemplo conceitual:**
```
Tabela artista:          Tabela album:
id | nome                id | titulo        | id_artista
1  | Beatles             101| Abbey Road    | 1
2  | Sem Álbum           102| Órfão         | 999
3  | Pink Floyd          

FULL OUTER JOIN resultado:
Beatles    | Abbey Road    (correspondência)
Sem Álbum  | NULL          (artista sem álbum)
NULL       | Órfão         (álbum sem artista)
Pink Floyd | NULL          (artista sem álbum)
```

**Identificar problemas de integridade:**
```sql
-- Encontrar artistas sem álbuns E álbuns sem artistas
SELECT 
    a.nome_artista,
    al.titulo,
    CASE 
        WHEN a.id_artista IS NULL THEN 'Álbum órfão'
        WHEN al.id_album IS NULL THEN 'Artista sem álbum'
        ELSE 'OK'
    END AS status
FROM artista a
FULL OUTER JOIN album al ON a.id_artista = al.id_artista
WHERE a.id_artista IS NULL OR al.id_album IS NULL;
```

**Alternativa ao FULL OUTER JOIN:**
```sql
-- Maioria dos bancos suporta UNION:
SELECT a.nome, al.titulo FROM artista a LEFT JOIN album al ON ...
UNION
SELECT a.nome, al.titulo FROM artista a RIGHT JOIN album al ON ...;
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

**Como funciona:**
- SELF JOIN junta uma tabela **consigo mesma**
- Usa aliases DIFERENTES para a mesma tabela (a1, a2)
- Trata a mesma tabela como se fossem duas tabelas separadas
- Útil para relacionamentos hierárquicos ou comparações dentro da mesma tabela

**Por que usar:**
- **Hierarquias**: Funcionário → Gerente (mesma tabela)
- **Comparações**: Encontrar pares, duplicatas, relacionamentos
- **Árvores**: Categorias com subcategorias
- **Grafos**: Qualquer estrutura de relacionamento auto-referencial

**Exemplo: Hierarquia de funcionários**
```sql
-- Tabela: funcionario (id, nome, id_gerente)
SELECT 
    f.nome AS "Funcionário",
    g.nome AS "Gerente"
FROM funcionario f
LEFT JOIN funcionario g ON f.id_gerente = g.id;
-- Usa LEFT JOIN porque CEO não tem gerente (NULL)
```

**Exemplo: Encontrar duplicatas**
```sql
-- Artistas com nomes similares
SELECT 
    a1.nome_artista AS "Nome 1",
    a2.nome_artista AS "Nome 2"
FROM artista a1
INNER JOIN artista a2 ON UPPER(a1.nome_artista) = UPPER(a2.nome_artista)
WHERE a1.id_artista < a2.id_artista;  -- Evita pares duplicados
```

**Por que `a1.id < a2.id`?**
```sql
-- Sem filtro: pares duplicados
Artista 1  | Artista 2
Beatles    | Pink Floyd
Pink Floyd | Beatles    ← Duplicata!

-- Com a1.id < a2.id: sem duplicatas
Artista 1  | Artista 2
Beatles    | Pink Floyd   (apenas uma direção)
```

**Exemplo: Recomendações**
```sql
-- "Usuários que gostam de artistas similares"
-- Encontrar usuários que gostaram do mesmo artista
SELECT DISTINCT
    u1.nome_usuario AS "Usuário 1",
    u2.nome_usuario AS "Usuário 2",
    a.nome_artista AS "Artista em Comum"
FROM historico_reproducao h1
INNER JOIN historico_reproducao h2 
    ON h1.id_musica = h2.id_musica
INNER JOIN usuario u1 ON h1.id_usuario = u1.id_usuario
INNER JOIN usuario u2 ON h2.id_usuario = u2.id_usuario
INNER JOIN musica m ON h1.id_musica = m.id_musica
INNER JOIN album al ON m.id_album = al.id_album
INNER JOIN artista a ON al.id_artista = a.id_artista
WHERE u1.id_usuario < u2.id_usuario;
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

**Como estruturar JOINs complexos:**

**1. Identifique o caminho dos relacionamentos:**
```
musica → album → artista
musica → genero
musica → historico_reproducao → usuario
```

**2. Comece pela tabela central:**
```sql
FROM musica m  -- Tabela central
```

**3. Adicione JOINs sequencialmente:**
```sql
INNER JOIN album al ON m.id_album = al.id_album      -- 1º nível
INNER JOIN artista ar ON al.id_artista = ar.id_artista  -- 2º nível
INNER JOIN genero g ON m.id_genero = g.id_genero     -- 1º nível (paralelo)
```

**4. Escolha o tipo correto de JOIN:**
- INNER JOIN: para dados obrigatórios (música sempre tem álbum)
- LEFT JOIN: para dados opcionais (música pode não ter reproduções)

**5. Use aliases consistentes:**
```sql
artista → ar (não a, art, artista...)
album → al
musica → m
genero → g
```

**Performance em múltiplos JOINs:**
- **Ordem importa**: O otimizador pode reorganizar, mas ajude começando por tabelas menores
- **Índices**: TODAS as colunas de JOIN devem ser indexadas
- **Evite JOIN desnecessários**: Só junte tabelas que você realmente precisa
- **WHERE antes de JOIN**: Filtrar reduz dados para JOIN processar

**Exemplo de otimização:**
```sql
-- MENOS EFICIENTE: JOIN depois filtra
SELECT ar.nome, m.titulo
FROM musica m
INNER JOIN album al ON m.id_album = al.id_album
INNER JOIN artista ar ON al.id_artista = ar.id_artista
WHERE ar.pais_origem = 'Brasil';

-- MAIS EFICIENTE: Filtra artistas primeiro (se tabela grande)
SELECT ar.nome, m.titulo
FROM artista ar  -- Começa pela tabela filtrada
INNER JOIN album al ON ar.id_artista = al.id_artista
INNER JOIN musica m ON al.id_album = m.id_album
WHERE ar.pais_origem = 'Brasil';
-- Ou use subconsulta para filtrar antes do JOIN
```

**Debugar JOINs complexos:**
```sql
-- Construa incrementalmente:
-- Passo 1: Teste primeiro JOIN
SELECT * FROM musica m INNER JOIN album al ON m.id_album = al.id_album;

-- Passo 2: Adicione segundo JOIN
SELECT * FROM musica m 
INNER JOIN album al ON m.id_album = al.id_album
INNER JOIN artista ar ON al.id_artista = ar.id_artista;

-- Passo 3: Continue adicionando...
```

#### 4.7 Exemplos Práticos de JOINs em SQL Developer

Esta seção apresenta exemplos práticos de JOINs que você pode executar diretamente no Oracle SQL Developer, com foco em cenários reais e boas práticas de desenvolvimento.

**Por que esta seção é importante:**
- **Prática Guiada**: Exemplos prontos para executar no SQL Developer
- **Cenários Reais**: Situações comuns em sistemas de informação
- **Visualização**: Como interpretar resultados no SQL Developer
- **Troubleshooting**: Soluções para problemas comuns

##### 4.7.1 JOIN para Relatórios de Negócio

**Exemplo 1: Relatório de Catálogo Completo**
```sql
-- Listar todas as músicas com suas informações completas
-- Use este exemplo para entender a estrutura do banco de dados
SELECT 
    ar.nome_artista AS "Artista",
    ar.pais_origem AS "País",
    al.titulo AS "Álbum",
    al.ano_lancamento AS "Ano",
    m.titulo AS "Música",
    m.numero_faixa AS "Faixa",
    TRUNC(m.duracao / 60) || ':' || 
        LPAD(MOD(m.duracao, 60), 2, '0') AS "Duração",
    g.nome_genero AS "Gênero"
FROM musica m
INNER JOIN album al ON m.id_album = al.id_album
INNER JOIN artista ar ON al.id_artista = ar.id_artista
INNER JOIN genero g ON m.id_genero = g.id_genero
ORDER BY ar.nome_artista, al.ano_lancamento, m.numero_faixa;
```

**Como usar no SQL Developer:**
1. Copie e cole o código na worksheet
2. Clique em "Run Script" (F5) para ver os resultados formatados
3. Use "Export" para salvar como CSV ou Excel
4. Ajuste as colunas do resultado para melhor visualização

**Resultado esperado:**
```
Artista         | País   | Álbum      | Ano  | Música        | Faixa | Duração | Gênero
----------------|--------|------------|------|---------------|-------|---------|--------
The Beatles     | UK     | Abbey Road | 1969 | Come Together | 1     | 4:19    | Rock
The Beatles     | UK     | Abbey Road | 1969 | Something     | 2     | 3:02    | Rock
```

**Exemplo 2: Análise de Popularidade por Gênero**
```sql
-- Descobrir quais gêneros são mais populares
SELECT 
    g.nome_genero AS "Gênero",
    COUNT(DISTINCT ar.id_artista) AS "Artistas",
    COUNT(DISTINCT al.id_album) AS "Álbuns",
    COUNT(DISTINCT m.id_musica) AS "Músicas",
    ROUND(AVG(m.duracao) / 60, 2) AS "Duração Média (min)"
FROM genero g
LEFT JOIN musica m ON g.id_genero = m.id_genero
LEFT JOIN album al ON m.id_album = al.id_album
LEFT JOIN artista ar ON al.id_artista = ar.id_artista
GROUP BY g.id_genero, g.nome_genero
HAVING COUNT(DISTINCT m.id_musica) > 0
ORDER BY COUNT(DISTINCT m.id_musica) DESC;
```

**Por que usar LEFT JOIN aqui:**
- Garante que todos os gêneros apareçam, mesmo sem músicas cadastradas
- Gêneros sem músicas terão contagens zero
- Útil para identificar gêneros não utilizados no catálogo

**Dica SQL Developer:**
- Clique com botão direito no resultado → "Export" → escolha formato
- Use "Copy" para colar em apresentações
- "Pin" a query para acesso rápido depois

**Exemplo 3: Descobrir Artistas Prolíficos**
```sql
-- Artistas com mais álbuns e músicas (TOP 20)
SELECT 
    ar.nome_artista AS "Artista",
    ar.pais_origem AS "País",
    COUNT(DISTINCT al.id_album) AS "Álbuns",
    COUNT(m.id_musica) AS "Músicas",
    SUM(m.duracao) / 3600 AS "Horas Totais",
    ROUND(AVG(m.duracao), 0) AS "Duração Média (seg)"
FROM artista ar
LEFT JOIN album al ON ar.id_artista = al.id_artista
LEFT JOIN musica m ON al.id_album = m.id_album
GROUP BY ar.id_artista, ar.nome_artista, ar.pais_origem
HAVING COUNT(DISTINCT al.id_album) >= 1
ORDER BY COUNT(DISTINCT al.id_album) DESC, COUNT(m.id_musica) DESC
FETCH FIRST 20 ROWS ONLY;
```

**Análise do resultado:**
- Identifica artistas com maior produção
- Útil para destacar artistas principais do catálogo
- Pode ser usado em páginas de "Artistas em Destaque"

##### 4.7.2 JOINs com Agregações e Estatísticas

**Exemplo 4: Playlists Mais Populares**
```sql
-- Descobrir as playlists mais seguidas/escutadas
SELECT 
    p.nome_playlist AS "Playlist",
    u.nome_usuario AS "Criador",
    p.publica AS "Pública",
    COUNT(DISTINCT pm.id_musica) AS "Músicas",
    SUM(m.duracao) / 60 AS "Duração Total (min)"
FROM playlist p
INNER JOIN usuario u ON p.id_usuario = u.id_usuario
LEFT JOIN playlist_musica pm ON p.id_playlist = pm.id_playlist
LEFT JOIN musica m ON pm.id_musica = m.id_musica
GROUP BY p.id_playlist, p.nome_playlist, u.nome_usuario, p.publica
ORDER BY COUNT(DISTINCT pm.id_musica) DESC;
```

**Notas importantes:**
- `LEFT JOIN playlist_musica`: Inclui playlists vazias (recém-criadas)
- `COUNT(DISTINCT pm.id_musica)`: Evita contar duplicatas
- Útil para relatórios administrativos

**Exemplo 5: Músicas Mais Tocadas por Período**
```sql
-- Top 10 músicas mais reproduzidas no último mês
SELECT 
    ar.nome_artista AS "Artista",
    m.titulo AS "Música",
    al.titulo AS "Álbum",
    COUNT(hr.id_historico) AS "Reproduções",
    COUNT(DISTINCT hr.id_usuario) AS "Usuários Únicos",
    MIN(hr.data_reproducao) AS "Primeira Reprodução",
    MAX(hr.data_reproducao) AS "Última Reprodução"
FROM historico_reproducao hr
INNER JOIN musica m ON hr.id_musica = m.id_musica
INNER JOIN album al ON m.id_album = al.id_album
INNER JOIN artista ar ON al.id_artista = ar.id_artista
WHERE hr.data_reproducao >= ADD_MONTHS(SYSDATE, -1)
GROUP BY ar.nome_artista, m.titulo, al.titulo, m.id_musica
ORDER BY COUNT(hr.id_historico) DESC
FETCH FIRST 10 ROWS ONLY;
```

**Funcionalidades Oracle usadas:**
- `ADD_MONTHS(SYSDATE, -1)`: Data de 1 mês atrás
- `COUNT(DISTINCT hr.id_usuario)`: Usuários únicos que ouviram
- `FETCH FIRST 10 ROWS ONLY`: Sintaxe moderna Oracle (12c+)

**Dica SQL Developer:**
- Salve esta query como "Report" → Botão direito → "Save As Report"
- Configure parâmetros para tornar reutilizável
- Agende execução automática (Tools → Scheduler)

**Exemplo 6: Análise de Comportamento de Usuários**
```sql
-- Usuários mais ativos e seus gêneros favoritos
SELECT 
    u.nome_usuario AS "Usuário",
    u.email AS "Email",
    COUNT(DISTINCT hr.id_musica) AS "Músicas Diferentes",
    COUNT(hr.id_historico) AS "Total Reproduções",
    g.nome_genero AS "Gênero Mais Ouvido"
FROM usuario u
INNER JOIN historico_reproducao hr ON u.id_usuario = hr.id_usuario
INNER JOIN musica m ON hr.id_musica = m.id_musica
INNER JOIN genero g ON m.id_genero = g.id_genero
GROUP BY u.id_usuario, u.nome_usuario, u.email, g.id_genero, g.nome_genero
HAVING COUNT(hr.id_historico) > 100
ORDER BY COUNT(hr.id_historico) DESC;
```

**Observação:** 
Esta query pode retornar múltiplas linhas por usuário se ele ouvir múltiplos gêneros. Para obter apenas o gênero mais ouvido, use window functions (seção avançada).

##### 4.7.3 JOINs para Análise de Dados Faltantes

**Exemplo 7: Identificar Álbuns Sem Músicas**
```sql
-- Encontrar álbuns que não têm músicas cadastradas
SELECT 
    ar.nome_artista AS "Artista",
    al.titulo AS "Álbum Incompleto",
    al.ano_lancamento AS "Ano",
    al.numero_faixas AS "Faixas Esperadas"
FROM album al
INNER JOIN artista ar ON al.id_artista = ar.id_artista
LEFT JOIN musica m ON al.id_album = m.id_album
WHERE m.id_musica IS NULL
ORDER BY ar.nome_artista, al.ano_lancamento;
```

**Uso prático:**
- Qualidade de dados: identifica catálogo incompleto
- Administração: lista de álbuns para completar cadastro
- `WHERE m.id_musica IS NULL`: Chave para encontrar LEFT JOIN sem correspondência

**Como executar no SQL Developer:**
1. Execute a query
2. Se retornar resultados, há álbuns incompletos
3. Use os IDs para corrigir os dados
4. Re-execute para validar correção

**Exemplo 8: Artistas Sem Produção**
```sql
-- Artistas cadastrados mas sem álbuns ou músicas
SELECT 
    ar.nome_artista AS "Artista",
    ar.pais_origem AS "País",
    ar.data_formacao AS "Ano Formação",
    CASE 
        WHEN al.id_album IS NULL THEN 'Sem álbuns'
        WHEN m.id_musica IS NULL THEN 'Com álbuns mas sem músicas'
        ELSE 'OK'
    END AS "Status"
FROM artista ar
LEFT JOIN album al ON ar.id_artista = al.id_artista
LEFT JOIN musica m ON al.id_album = m.id_album
WHERE al.id_album IS NULL OR m.id_musica IS NULL
GROUP BY ar.id_artista, ar.nome_artista, ar.pais_origem, 
         ar.data_formacao, al.id_album, m.id_musica
ORDER BY ar.nome_artista;
```

**Categoria de problemas identificados:**
1. **Sem álbuns**: Artista cadastrado mas sem produção
2. **Com álbuns mas sem músicas**: Álbuns vazios
3. Útil para limpeza e manutenção do banco

**Exemplo 9: Gêneros Não Utilizados**
```sql
-- Gêneros cadastrados mas sem músicas associadas
SELECT 
    g.nome_genero AS "Gênero Não Utilizado",
    g.id_genero AS "ID"
FROM genero g
LEFT JOIN musica m ON g.id_genero = m.id_genero
WHERE m.id_musica IS NULL
ORDER BY g.nome_genero;
```

**Ação recomendada:**
- Se retornar resultados, considere remover gêneros não utilizados
- Ou planeje criar músicas desses gêneros
- Mantém banco de dados limpo e organizado

##### 4.7.4 JOINs Complexos com Subconsultas

**Exemplo 10: Artistas com Álbuns em Múltiplas Décadas**
```sql
-- Artistas com carreira duradoura (álbuns em diferentes décadas)
SELECT 
    ar.nome_artista AS "Artista",
    COUNT(DISTINCT TRUNC(al.ano_lancamento / 10) * 10) AS "Décadas Ativas",
    MIN(al.ano_lancamento) AS "Primeiro Álbum",
    MAX(al.ano_lancamento) AS "Último Álbum",
    MAX(al.ano_lancamento) - MIN(al.ano_lancamento) AS "Anos de Carreira"
FROM artista ar
INNER JOIN album al ON ar.id_artista = al.id_artista
WHERE al.ano_lancamento IS NOT NULL
GROUP BY ar.id_artista, ar.nome_artista
HAVING COUNT(DISTINCT TRUNC(al.ano_lancamento / 10) * 10) >= 3
ORDER BY COUNT(DISTINCT TRUNC(al.ano_lancamento / 10) * 10) DESC;
```

**Análise:**
- `TRUNC(al.ano_lancamento / 10) * 10`: Converte ano em década
- `HAVING ... >= 3`: Apenas artistas com 3+ décadas de carreira
- Identifica artistas longevos e consistentes

**Exemplo 11: Comparação de Duração Média entre Gêneros e Décadas**
```sql
-- Evolução da duração das músicas por gênero ao longo do tempo
SELECT 
    g.nome_genero AS "Gênero",
    TRUNC(al.ano_lancamento / 10) * 10 AS "Década",
    COUNT(m.id_musica) AS "Músicas",
    ROUND(AVG(m.duracao) / 60, 2) AS "Duração Média (min)",
    ROUND(MIN(m.duracao) / 60, 2) AS "Mais Curta (min)",
    ROUND(MAX(m.duracao) / 60, 2) AS "Mais Longa (min)"
FROM musica m
INNER JOIN genero g ON m.id_genero = g.id_genero
INNER JOIN album al ON m.id_album = al.id_album
WHERE al.ano_lancamento IS NOT NULL
GROUP BY g.nome_genero, TRUNC(al.ano_lancamento / 10) * 10
HAVING COUNT(m.id_musica) >= 10
ORDER BY g.nome_genero, TRUNC(al.ano_lancamento / 10) * 10;
```

**Insights possíveis:**
- Músicas de Rock ficaram mais longas nos anos 70?
- Pop tem músicas mais curtas que Jazz?
- Tendências musicais ao longo das décadas

**Exemplo 12: Músicas em Múltiplas Playlists (Popularidade)**
```sql
-- Músicas mais adicionadas em playlists (indica popularidade)
SELECT 
    m.titulo AS "Música",
    ar.nome_artista AS "Artista",
    al.titulo AS "Álbum",
    COUNT(DISTINCT pm.id_playlist) AS "Em Playlists",
    COUNT(DISTINCT hr.id_usuario) AS "Ouvintes Únicos"
FROM musica m
INNER JOIN album al ON m.id_album = al.id_album
INNER JOIN artista ar ON al.id_artista = ar.id_artista
INNER JOIN playlist_musica pm ON m.id_musica = pm.id_musica
LEFT JOIN historico_reproducao hr ON m.id_musica = hr.id_musica
GROUP BY m.id_musica, m.titulo, ar.nome_artista, al.titulo
HAVING COUNT(DISTINCT pm.id_playlist) >= 5
ORDER BY COUNT(DISTINCT pm.id_playlist) DESC, 
         COUNT(DISTINCT hr.id_usuario) DESC;
```

**Métrica de popularidade:**
- Quanto mais playlists contêm a música, mais popular ela é
- Combina popularidade (playlists) com alcance (ouvintes)
- Útil para criar playlists automáticas de "hits"

##### 4.7.5 Troubleshooting de JOINs no SQL Developer

**Problema 1: Query retorna menos resultados que esperado**

```sql
-- PROBLEMA: Usando INNER JOIN quando deveria usar LEFT JOIN
-- INCORRETO: Perde artistas sem álbuns
SELECT ar.nome_artista, COUNT(al.id_album)
FROM artista ar
INNER JOIN album al ON ar.id_artista = al.id_artista
GROUP BY ar.nome_artista;

-- CORRETO: Inclui todos os artistas
SELECT ar.nome_artista, COUNT(al.id_album)
FROM artista ar
LEFT JOIN album al ON ar.id_artista = al.id_artista
GROUP BY ar.nome_artista;
```

**Como verificar no SQL Developer:**
1. Execute primeiro `SELECT COUNT(*) FROM artista;`
2. Execute o JOIN e conte resultados
3. Se números não batem, revise tipo de JOIN

**Problema 2: Query retorna duplicatas inesperadas**

```sql
-- PROBLEMA: JOIN cartesiano acidental
-- INCORRETO: Duplica resultados
SELECT ar.nome_artista, m.titulo
FROM artista ar
INNER JOIN album al ON ar.id_artista = al.id_artista
INNER JOIN musica m ON ar.id_artista = m.id_artista; -- ERRADO!

-- CORRETO: JOIN pela relação correta
SELECT ar.nome_artista, m.titulo
FROM artista ar
INNER JOIN album al ON ar.id_artista = al.id_artista
INNER JOIN musica m ON al.id_album = m.id_album; -- CERTO!
```

**Dica de debug:**
- Adicione COUNT(*) para detectar duplicatas
- Use DISTINCT temporariamente para verificar
- Revise relacionamentos entre tabelas

**Problema 3: Performance lenta em JOINs**

```sql
-- PROBLEMA: JOIN sem índices ou com muitos dados
-- SOLUÇÃO 1: Adicionar índices (se DBA)
CREATE INDEX idx_album_artista ON album(id_artista);
CREATE INDEX idx_musica_album ON musica(id_album);

-- SOLUÇÃO 2: Filtrar antes de JOIN
-- Menos eficiente
SELECT ar.nome_artista, m.titulo
FROM artista ar
INNER JOIN album al ON ar.id_artista = al.id_artista
INNER JOIN musica m ON al.id_album = m.id_album
WHERE ar.pais_origem = 'Brasil';

-- Mais eficiente (para grandes volumes)
SELECT ar.nome_artista, m.titulo
FROM (SELECT * FROM artista WHERE pais_origem = 'Brasil') ar
INNER JOIN album al ON ar.id_artista = al.id_artista
INNER JOIN musica m ON al.id_album = m.id_album;
```

**Ferramentas SQL Developer para diagnóstico:**
- **F10 (Explain Plan)**: Ver plano de execução
- **Autotrace**: Analisar estatísticas de performance
- **SQL Tuning Advisor**: Sugestões automáticas (Oracle)

**Problema 4: Condição de JOIN incorreta**

```sql
-- PROBLEMA: Usar WHERE ao invés de ON
-- INCORRETO: Pode gerar resultados inesperados
SELECT ar.nome_artista, al.titulo
FROM artista ar
INNER JOIN album al
WHERE ar.id_artista = al.id_artista; -- Deveria ser ON!

-- CORRETO: Condição de JOIN no ON
SELECT ar.nome_artista, al.titulo
FROM artista ar
INNER JOIN album al ON ar.id_artista = al.id_artista;
```

**Diferença:**
- **ON**: Condição de junção das tabelas
- **WHERE**: Filtro aplicado após JOIN
- Ambos funcionam com INNER JOIN, mas semanticamente ON é correto

**Problema 5: NULL values em agregações**

```sql
-- PROBLEMA: COUNT(*) vs COUNT(coluna) com LEFT JOIN
SELECT 
    ar.nome_artista,
    COUNT(*) AS total_linhas,           -- Conta linhas (inclui NULL)
    COUNT(al.id_album) AS total_albums  -- Conta valores (ignora NULL)
FROM artista ar
LEFT JOIN album al ON ar.id_artista = al.id_artista
GROUP BY ar.nome_artista;

-- Artista sem álbum:
-- total_linhas = 1 (conta a linha com NULL)
-- total_albums = 0 (não conta NULL)
```

**Regra de ouro:**
- Com LEFT/RIGHT JOIN: Use `COUNT(coluna)` da tabela opcional
- Com INNER JOIN: `COUNT(*)` e `COUNT(coluna)` são equivalentes

##### 4.7.6 Boas Práticas de JOINs no SQL Developer

**1. Use aliases descritivos e consistentes:**
```sql
-- BOM
SELECT 
    ar.nome_artista,
    al.titulo,
    m.titulo
FROM artista ar
INNER JOIN album al ON ar.id_artista = al.id_artista
INNER JOIN musica m ON al.id_album = m.id_album;

-- RUIM (inconsistente)
SELECT 
    artista.nome_artista,
    a.titulo,
    music.titulo
FROM artista
INNER JOIN album a ON artista.id_artista = a.id_artista
INNER JOIN musica music ON a.id_album = music.id_album;
```

**2. Formate queries para legibilidade:**
```sql
-- BOM (fácil de ler e manter)
SELECT 
    ar.nome_artista AS "Artista",
    al.titulo AS "Álbum",
    COUNT(m.id_musica) AS "Músicas"
FROM artista ar
    INNER JOIN album al 
        ON ar.id_artista = al.id_artista
    INNER JOIN musica m 
        ON al.id_album = m.id_album
GROUP BY 
    ar.id_artista,
    ar.nome_artista,
    al.id_album,
    al.titulo
ORDER BY 
    COUNT(m.id_musica) DESC;

-- RUIM (difícil de ler)
SELECT ar.nome_artista, al.titulo, COUNT(m.id_musica) FROM artista ar INNER JOIN album al ON ar.id_artista = al.id_artista INNER JOIN musica m ON al.id_album = m.id_album GROUP BY ar.id_artista, ar.nome_artista, al.id_album, al.titulo ORDER BY COUNT(m.id_musica) DESC;
```

**3. Documente queries complexas:**
```sql
-- Relatório de produtividade de artistas
-- Mostra artistas com mais de 5 álbuns e pelo menos 50 músicas
-- Ordenado por total de músicas
-- Atualizado: 2025-10-23
SELECT 
    ar.nome_artista AS "Artista",
    COUNT(DISTINCT al.id_album) AS "Álbuns",
    COUNT(m.id_musica) AS "Músicas"
FROM artista ar
    INNER JOIN album al ON ar.id_artista = al.id_artista
    INNER JOIN musica m ON al.id_album = m.id_album
GROUP BY ar.id_artista, ar.nome_artista
HAVING 
    COUNT(DISTINCT al.id_album) >= 5
    AND COUNT(m.id_musica) >= 50
ORDER BY COUNT(m.id_musica) DESC;
```

**4. Teste incrementalmente:**
```sql
-- PASSO 1: Teste a tabela base
SELECT * FROM artista WHERE ROWNUM <= 10;

-- PASSO 2: Adicione primeiro JOIN
SELECT ar.nome_artista, al.titulo
FROM artista ar
INNER JOIN album al ON ar.id_artista = al.id_artista
WHERE ROWNUM <= 10;

-- PASSO 3: Complete a query
SELECT ar.nome_artista, al.titulo, COUNT(m.id_musica)
FROM artista ar
INNER JOIN album al ON ar.id_artista = al.id_artista
INNER JOIN musica m ON al.id_album = m.id_album
GROUP BY ar.nome_artista, al.titulo;
```

**5. Use SQL Developer Snippets:**
- Salve queries úteis como Snippets (View → Snippets)
- Organize por categoria (Reports, Joins, etc.)
- Compartilhe com equipe via export/import

**Recursos do SQL Developer para JOINs:**
- **Code Template**: CTRL+SPACE para autocompletar JOINs
- **Explain Plan (F10)**: Analisar performance
- **Query Builder**: Ferramenta visual para criar JOINs
- **SQL History**: Recuperar queries anteriores
- **Format (CTRL+F7)**: Auto-formatar SQL

### PARTE 2: TRANSAÇÕES E CONTROLE

Transações são um dos conceitos mais importantes em bancos de dados, garantindo que operações complexas sejam executadas de forma confiável e consistente. Esta seção explora como controlar transações em SQL.

**O que é uma Transação?**
Uma transação é uma **unidade lógica de trabalho** que agrupa uma ou mais operações SQL. Todas as operações dentro de uma transação são tratadas como uma única unidade: ou todas são executadas com sucesso, ou nenhuma é aplicada.

**Por que Transações são Fundamentais?**
- **Integridade de Dados**: Garante consistência mesmo em caso de falhas
- **Operações Atômicas**: Múltiplas alterações são tratadas como uma só
- **Recuperação**: Permite desfazer operações em caso de erro
- **Concorrência**: Múltiplos usuários podem trabalhar simultaneamente sem conflitos

### 5. Conceitos de Transações

#### 5.1 Propriedades ACID

ACID é um acrônimo que define as quatro propriedades essenciais que toda transação deve garantir para manter a integridade dos dados.

- **Atomicidade (Atomicity)**: Transação é indivisível (tudo ou nada)
  - **Como funciona**: Uma transação é tratada como uma única unidade
  - **Exemplo**: Transferência bancária deve debitar E creditar, ou não fazer nada
  - **Garantia**: Se qualquer parte falha, TUDO é desfeito (rollback)
  - **Analogia**: Como um átomo (indivisível) - não pode ser quebrada em partes
  
- **Consistência (Consistency)**: Dados ficam em estado válido
  - **Como funciona**: Transação move o banco de um estado válido para outro estado válido
  - **Exemplo**: Saldo total antes = saldo total depois (em transferência)
  - **Garantia**: Todas as regras de negócio e constraints são respeitadas
  - **Validação**: CHECK constraints, FOREIGN KEY, NOT NULL, etc., são verificados
  
- **Isolamento (Isolation)**: Transações não interferem entre si
  - **Como funciona**: Cada transação vê o banco como se fosse a única executando
  - **Exemplo**: Duas pessoas comprando o último item não devem ambas ter sucesso
  - **Níveis**: READ UNCOMMITTED, READ COMMITTED, REPEATABLE READ, SERIALIZABLE
  - **Trade-off**: Mais isolamento = mais consistência, mas menos concorrência
  
- **Durabilidade (Durability)**: Mudanças persistem após COMMIT
  - **Como funciona**: Dados confirmados sobrevivem a falhas (queda de energia, crash)
  - **Exemplo**: Após COMMIT, compra está garantida mesmo se servidor cair
  - **Implementação**: Logs de transação (redo log), checkpoints, backup
  - **Garantia**: Uma vez confirmada, transação NUNCA é perdida

**Por que ACID é crucial?**
Sem ACID, você poderia ter:
- Dinheiro sumindo em transferências (sem Atomicidade)
- Saldos negativos (sem Consistência)
- Duas vendas do mesmo item (sem Isolamento)
- Perda de dados após queda (sem Durabilidade)

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

**Estados de uma Transação:**

1. **Ativa (Active)**: Transação está executando
   - Primeiro comando DML (INSERT, UPDATE, DELETE) inicia transação
   - Mudanças estão em memória, não visíveis para outros usuários
   - Exemplo: `INSERT INTO ...` - transação está ativa

2. **Parcialmente Confirmada (Partially Committed)**: Todos comandos executados
   - Última operação completou com sucesso
   - Ainda não foi confirmada com COMMIT
   - Aguardando confirmação ou rollback

3. **Confirmada (Committed)**: Transação completada com sucesso
   - COMMIT foi executado
   - Mudanças são persistentes e visíveis para todos
   - Não pode ser desfeita (exceto com nova transação)

4. **Falhada (Failed)**: Erro ocorreu durante execução
   - Violação de constraint, erro de sintaxe, deadlock, etc.
   - Sistema automaticamente vai para estado de Abortada

5. **Abortada (Aborted)**: Transação foi desfeita
   - ROLLBACK foi executado (manual ou automático)
   - Banco de dados volta ao estado antes da transação
   - Como se a transação nunca tivesse ocorrido

**Fluxo típico:**
```
[Início] → Ativa → Parcialmente Confirmada → [COMMIT] → Confirmada
                ↓                             
             [Erro]                           
                ↓                             
            Falhada → [ROLLBACK] → Abortada
```

**Exemplo prático:**
```sql
-- Estado: Ativa
INSERT INTO artista (id, nome) VALUES (999, 'Novo Artista');

-- Estado: ainda Ativa
UPDATE artista SET nome = 'Artista Atualizado' WHERE id = 999;

-- Opcões:
COMMIT;    -- Estado: Confirmada (mudanças persistem)
-- OU
ROLLBACK;  -- Estado: Abortada (mudanças desfeitas)
```

### 6. Comandos de Controle de Transação

Os comandos de controle de transação (TCL - Transaction Control Language) permitem gerenciar o ciclo de vida das transações, confirmando ou desfazendo mudanças.

#### 6.1 COMMIT - Confirmar Mudanças

```sql
-- Inserir um novo artista
INSERT INTO artista (id_artista, nome_artista, pais_origem) 
VALUES (200, 'Novos Artistas', 'Brasil');

-- Confirmar a inserção
COMMIT;

-- Agora a mudança está persistente
```

**Como funciona:**
- COMMIT torna **permanentes** todas as mudanças da transação atual
- Mudanças são escritas do buffer de memória para o disco
- Outros usuários passam a ver as mudanças
- Libera locks (bloqueios) mantidos pela transação
- Cria um ponto de não-retorno (não pode desfazer depois)

**Por que usar:**
- **Persistência**: Garantir que dados importantes sejam salvos
- **Visibilidade**: Tornar mudanças visíveis para outros usuários
- **Liberação**: Liberar recursos (locks) para outras transações
- **Checkpoint**: Marcar conclusão bem-sucedida de operações

**Quando usar:**
- Após completar com sucesso um conjunto de operações relacionadas
- Quando você tem certeza que os dados estão corretos
- Em pontos estratégicos de longas transações (com SAVEPOINTs)
- Ao final de rotinas de importação/migração bem-sucedidas

**Exemplo - Cadastro completo:**
```sql
-- Inserir artista
INSERT INTO artista (id, nome, pais) VALUES (100, 'New Band', 'USA');

-- Inserir álbum do artista
INSERT INTO album (id, titulo, id_artista) VALUES (500, 'First Album', 100);

-- Inserir músicas do álbum
INSERT INTO musica (id, titulo, id_album) VALUES (1000, 'Song 1', 500);
INSERT INTO musica (id, titulo, id_album) VALUES (1001, 'Song 2', 500);

-- Tudo certo? Confirmar todas as operações de uma vez
COMMIT;

-- Agora artista, álbum e músicas estão permanentemente no banco
```

**Comportamento em diferentes cenários:**
```sql
-- Autocommit OFF (controle manual):
SET AUTOCOMMIT OFF;
INSERT INTO usuario VALUES (1, 'João');
-- Mudança ainda não é permanente
COMMIT;  -- Agora é permanente

-- Autocommit ON (commit automático):
SET AUTOCOMMIT ON;
INSERT INTO usuario VALUES (2, 'Maria');
-- Mudança JÁ é permanente automaticamente (commit implícito)
```

**COMMIT implícito:**
Alguns comandos fazem COMMIT automático:
- DDL: CREATE, ALTER, DROP, TRUNCATE
- DCL: GRANT, REVOKE
- Desconexão normal do banco
- EXIT ou fechamento normal da sessão

**Atenção:**
```sql
INSERT INTO artista VALUES (1, 'Test');
CREATE TABLE temp (id NUMBER);  -- DDL faz COMMIT implícito!
-- INSERT anterior foi confirmado automaticamente
ROLLBACK;  -- Não desfaz o INSERT porque já foi commitado pelo CREATE
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

**Como funciona:**
- ROLLBACK **desfaz** todas as mudanças não confirmadas da transação atual
- Restaura o banco ao estado antes da transação começar
- Libera locks mantidos pela transação
- Cancela a transação como se ela nunca tivesse acontecido

**Por que usar:**
- **Correção de Erros**: Desfazer operações executadas por engano
- **Validação Falhou**: Quando detecta que dados estão incorretos
- **Tratamento de Exceções**: Em caso de erro durante processamento
- **Testes**: Experimentar queries sem afetar dados reais

**Quando usar:**
- Quando detectar erro lógico nas operações
- Em caso de violação de regra de negócio
- Durante testes (fazer ROLLBACK ao invés de COMMIT)
- Quando usuário cancela operação
- Em blocos de tratamento de exceções (Exception Handler)

**Exemplo - Detecção de erro:**
```sql
-- Iniciar transferência entre playlists
DELETE FROM playlist_musica WHERE id_playlist = 10 AND id_musica = 123;

-- Tentar inserir na nova playlist
INSERT INTO playlist_musica VALUES (20, 123, 1);
-- Erro! Playlist 20 não existe

-- Desfazer tudo
ROLLBACK;
-- Música volta para playlist 10 (DELETE foi desfeito)
```

**ROLLBACK completo vs ROLLBACK TO SAVEPOINT:**
```sql
-- ROLLBACK completo: desfaz TUDO
INSERT INTO artista VALUES (1, 'Test 1');
INSERT INTO artista VALUES (2, 'Test 2');
INSERT INTO artista VALUES (3, 'Test 3');
ROLLBACK;  -- Desfaz os 3 INSERTs

-- ROLLBACK TO SAVEPOINT: desfaz parcialmente (ver próxima seção)
INSERT INTO artista VALUES (1, 'Test 1');
SAVEPOINT sp1;
INSERT INTO artista VALUES (2, 'Test 2');
ROLLBACK TO sp1;  -- Desfaz apenas Test 2, mantém Test 1
COMMIT;  -- Confirma Test 1
```

**Exemplo prático - Importação com validação:**
```sql
-- Importar dados
BEGIN
    INSERT INTO artista SELECT * FROM artista_temp;
    
    -- Validar: todos devem ter país
    IF EXISTS (SELECT 1 FROM artista WHERE pais_origem IS NULL) THEN
        ROLLBACK;
        RAISE_APPLICATION_ERROR(-20001, 'Artistas sem país encontrados');
    ELSE
        COMMIT;
    END IF;
END;
/
```

**Performance:**
- ROLLBACK é geralmente rápido (desfaz mudanças em memória)
- Pode ser lento se transação fez muitas mudanças
- ROLLBACK de transações longas pode demorar segundos ou minutos

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

**Como funciona:**
- SAVEPOINT cria um **ponto de restauração** dentro de uma transação
- Permite **ROLLBACK parcial** - desfazer apenas parte das mudanças
- Múltiplos SAVEPOINTs podem existir em uma transação
- Não confirma mudanças (use COMMIT para isso)
- Útil para transações complexas com várias etapas

**Por que usar:**
- **Transações Longas**: Desfazer apenas parte sem perder tudo
- **Processamento em Etapas**: Cada etapa tem seu SAVEPOINT
- **Tentativa e Erro**: Experimentar operações sem comprometer transação
- **Recuperação Granular**: Voltar a pontos específicos em caso de erro

**Sintaxe:**
```sql
SAVEPOINT nome_do_savepoint;           -- Criar savepoint
ROLLBACK TO SAVEPOINT nome_do_savepoint; -- Voltar ao savepoint
-- ou simplesmente:
ROLLBACK TO nome_do_savepoint;         -- Sintaxe alternativa
```

**Exemplo completo - Processo multi-etapas:**
```sql
-- Etapa 1: Criar usuário
INSERT INTO usuario (id, nome, email) VALUES (100, 'João', 'joao@email.com');
SAVEPOINT usuario_criado;

-- Etapa 2: Criar playlist
INSERT INTO playlist (id, nome, id_usuario) VALUES (200, 'Favoritas', 100);
SAVEPOINT playlist_criada;

-- Etapa 3: Adicionar músicas (pode falhar)
INSERT INTO playlist_musica VALUES (200, 999, 1); -- Música não existe!
-- Erro! Mas não queremos perder usuário e playlist

-- Voltar apenas à playlist (mantém usuário)
ROLLBACK TO playlist_criada;

-- Ou voltar até usuário (perde playlist também)
-- ROLLBACK TO usuario_criado;

-- Finalizar o que deu certo
COMMIT;  -- Confirma usuário e playlist (músicas foram descartadas)
```

**SAVEPOINTs aninhados:**
```sql
INSERT INTO tabela1 VALUES (1);
SAVEPOINT sp1;

    INSERT INTO tabela2 VALUES (2);
    SAVEPOINT sp2;
    
        INSERT INTO tabela3 VALUES (3);
        SAVEPOINT sp3;
        
        -- Voltar um nível
        ROLLBACK TO sp2;  -- Desfaz sp3, mantém sp1 e sp2
        
    -- Voltar dois níveis
    ROLLBACK TO sp1;  -- Desfaz sp2 e sp3, mantém sp1

-- Confirmar tudo que restou
COMMIT;
```

**Liberar SAVEPOINT (Oracle):**
```sql
SAVEPOINT sp1;
-- ... operações ...

-- Liberar savepoint (não é rollback!)
-- Em Oracle, SAVEPOINTs são automaticamente liberados no COMMIT
-- Em outros bancos: RELEASE SAVEPOINT sp1;
```

**Diferença: ROLLBACK vs ROLLBACK TO SAVEPOINT:**

| ROLLBACK | ROLLBACK TO SAVEPOINT |
|----------|----------------------|
| Desfaz TODA a transação | Desfaz até o SAVEPOINT |
| Termina a transação | Continua a transação |
| Libera todos os locks | Mantém locks |
| Precisa BEGIN para nova transação | Pode continuar mesma transação |

**Exemplo prático - Importação de arquivo:**
```sql
BEGIN
    -- Processar arquivo linha por linha
    FOR i IN 1..1000 LOOP
        -- A cada 100 linhas, criar savepoint
        IF MOD(i, 100) = 0 THEN
            SAVEPOINT lote_i;
        END IF;
        
        BEGIN
            -- Processar linha
            INSERT INTO dados VALUES (i, ...);
        EXCEPTION
            WHEN OTHERS THEN
                -- Erro na linha, voltar ao último lote
                ROLLBACK TO lote_i;
                LOG_ERROR(i, SQLERRM);
        END;
    END LOOP;
    
    COMMIT;  -- Confirma tudo que deu certo
END;
/
```

**Uso em procedures:**
```sql
CREATE OR REPLACE PROCEDURE processar_pedido(p_id NUMBER) AS
    v_erro VARCHAR2(1000);
BEGIN
    SAVEPOINT inicio;
    
    -- Etapa 1: Atualizar estoque
    UPDATE estoque SET quantidade = quantidade - 1 WHERE produto_id = p_id;
    
    -- Etapa 2: Criar pedido
    INSERT INTO pedido VALUES (...);
    
    -- Etapa 3: Processar pagamento (pode falhar)
    BEGIN
        processar_pagamento(p_id);
    EXCEPTION
        WHEN OTHERS THEN
            ROLLBACK TO inicio;  -- Desfaz tudo se pagamento falhar
            RAISE;
    END;
    
    COMMIT;  -- Sucesso
EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;  -- Desfaz tudo em caso de erro
        RAISE;
END;
/
```

**Performance:**
- SAVEPOINTs têm custo mínimo (apenas marcam posição)
- ROLLBACK TO SAVEPOINT pode ser lento se muitas mudanças após o savepoint
- Evite criar muitos SAVEPOINTs desnecessários (overhead)

### 7. Autocommit e Controle Manual

Autocommit controla se transações são confirmadas automaticamente ou requerem COMMIT explícito. Entender este mecanismo é crucial para gerenciar transações corretamente.

#### 7.1 Configuração de Autocommit

```sql
-- Verificar status atual
SHOW AUTOCOMMIT;

-- Desabilitar autocommit para controle manual
SET AUTOCOMMIT OFF;

-- Habilitar autocommit (cada comando faz commit automático)
SET AUTOCOMMIT ON;
```

**Como funciona:**

**AUTOCOMMIT ON (padrão em muitos clientes SQL):**
- Cada comando DML (INSERT, UPDATE, DELETE) é automaticamente confirmado
- Não precisa digitar COMMIT
- Cada statement é uma transação separada
- Não pode fazer ROLLBACK de comandos anteriores

**AUTOCOMMIT OFF (recomendado para trabalho interativo):**
- Você controla manualmente quando fazer COMMIT ou ROLLBACK
- Múltiplos comandos fazem parte da mesma transação
- Permite testar e desfazer se necessário
- Mais seguro para operações críticas

**Por que usar cada modo:**

**Use AUTOCOMMIT ON quando:**
- Executando comandos isolados e simples
- Em scripts de importação onde cada linha é independente
- Quando você TEM CERTEZA que cada operação deve ser permanente
- Em aplicações onde framework gerencia transações

**Use AUTOCOMMIT OFF quando:**
- Executando operações relacionadas que devem ser atômicas
- Trabalhando interativamente e quer revisar antes de confirmar
- Testando queries de UPDATE/DELETE (pode fazer ROLLBACK se errar)
- Aprendendo SQL (mais seguro!)

**Exemplos práticos:**

**Com AUTOCOMMIT ON:**
```sql
SET AUTOCOMMIT ON;

INSERT INTO artista VALUES (1, 'Beatles');  -- Confirmado automaticamente
INSERT INTO artista VALUES (2, 'Queen');    -- Confirmado automaticamente

-- ERRO! Não pode desfazer
ROLLBACK;  -- Não desfaz nada, já foi commitado
```

**Com AUTOCOMMIT OFF:**
```sql
SET AUTOCOMMIT OFF;

INSERT INTO artista VALUES (1, 'Beatles');  -- Não confirmado ainda
INSERT INTO artista VALUES (2, 'Queen');    -- Não confirmado ainda

-- Opção 1: Confirmar tudo
COMMIT;

-- Opção 2: Desfazer tudo
-- ROLLBACK;
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

**Diferenças entre SGBDs:**

**Oracle:**
```sql
-- Transação inicia implicitamente no primeiro DML
INSERT INTO tabela VALUES (...);  -- Transação já começou aqui
UPDATE tabela SET ...;            -- Mesma transação
COMMIT;  -- Confirma ambos
```

**MySQL/PostgreSQL:**
```sql
-- Precisa START TRANSACTION ou BEGIN
START TRANSACTION;  -- ou BEGIN;
INSERT INTO tabela VALUES (...);
UPDATE tabela SET ...;
COMMIT;
```

**SQL Server:**
```sql
-- Pode usar BEGIN TRANSACTION
BEGIN TRANSACTION;
INSERT INTO tabela VALUES (...);
UPDATE tabela SET ...;
COMMIT TRANSACTION;
-- ou ROLLBACK TRANSACTION;
```

**Por que transações explícitas:**
- **Clareza**: Deixa óbvio onde transação começa e termina
- **Portabilidade**: Funciona consistentemente entre SGBDs
- **Documentação**: Código auto-documenta escopo de transação
- **Procedimentos**: Em stored procedures, delimitação clara de transação

**Exemplo - Transferência bancária:**
```sql
BEGIN TRANSACTION;  -- Início explícito

-- Debitar conta origem
UPDATE conta 
SET saldo = saldo - 100 
WHERE id = 1;

-- Creditar conta destino
UPDATE conta 
SET saldo = saldo + 100 
WHERE id = 2;

-- Verificar se ambos sucederam
IF @@ROWCOUNT = 2 THEN
    COMMIT;  -- Sucesso
ELSE
    ROLLBACK;  -- Falha
END IF;
```

### 8. Níveis de Isolamento

Níveis de isolamento controlam **quanto uma transação é isolada de outras transações concorrentes**. É um trade-off entre consistência e performance/concorrência.

#### 8.1 Configuração de Isolamento

```sql
-- Verificar nível atual
SELECT * FROM V$TRANSACTION;

-- Definir nível de isolamento para transação
SET TRANSACTION ISOLATION LEVEL READ COMMITTED;
-- Ou outros níveis: READ UNCOMMITTED, REPEATABLE READ, SERIALIZABLE
```

**Os 4 Níveis de Isolamento (do menos ao mais restritivo):**

**1. READ UNCOMMITTED (Leitura Suja)**
- **Permite**: Ler dados não confirmados (dirty reads)
- **Uso**: Quase nunca (muito perigoso)
- **Performance**: Máxima (sem locks de leitura)
- **Problema**: Pode ler dados que serão desfeitos

```sql
SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED;
-- Sessão A: UPDATE artista SET nome = 'Temp' WHERE id = 1; (sem COMMIT)
-- Sessão B: SELECT nome FROM artista WHERE id = 1;
-- Resultado: Vê 'Temp' mesmo sem COMMIT (pode ser desfeito!)
```

**2. READ COMMITTED (padrão na maioria dos SGBDs)**
- **Garante**: Só lê dados confirmados
- **Uso**: Padrão, bom para maioria dos casos
- **Performance**: Boa (locks curtos)
- **Problema**: Non-repeatable reads (mesma query, resultados diferentes)

```sql
SET TRANSACTION ISOLATION LEVEL READ COMMITTED;
-- Sessão A: SELECT nome FROM artista WHERE id = 1;  → 'Beatles'
-- Sessão B: UPDATE artista SET nome = 'The Beatles' WHERE id = 1; COMMIT;
-- Sessão A: SELECT nome FROM artista WHERE id = 1;  → 'The Beatles' (mudou!)
```

**3. REPEATABLE READ**
- **Garante**: Releituras retornam mesmo resultado
- **Uso**: Quando precisa consistência dentro da transação
- **Performance**: Média (locks mais longos)
- **Problema**: Phantom reads (novas linhas aparecem)

```sql
SET TRANSACTION ISOLATION LEVEL REPEATABLE READ;
-- Sessão A: SELECT COUNT(*) FROM artista WHERE pais = 'Brasil';  → 10
-- Sessão B: INSERT INTO artista VALUES (999, 'Novo', 'Brasil'); COMMIT;
-- Sessão A: SELECT COUNT(*) FROM artista WHERE pais = 'Brasil';  → 10 (mesmo!)
-- Mas se usar: SELECT * FROM artista WHERE pais = 'Brasil';  → nova linha aparece (phantom)
```

**4. SERIALIZABLE (máximo isolamento)**
- **Garante**: Transações executam como se fossem sequenciais
- **Uso**: Operações críticas que precisam isolamento total
- **Performance**: Baixa (locks pesados, possíveis timeouts)
- **Problema**: Mais lento, mais deadlocks

```sql
SET TRANSACTION ISOLATION LEVEL SERIALIZABLE;
-- Nenhuma interferência entre transações
-- Como se só uma transação executasse por vez
-- Muito seguro, mas muito lento
```

**Tabela Comparativa:**

| Nível | Dirty Read | Non-Repeatable Read | Phantom Read | Performance |
|-------|------------|---------------------|--------------|-------------|
| READ UNCOMMITTED | ✓ Permite | ✓ Permite | ✓ Permite | ⚡⚡⚡⚡ |
| READ COMMITTED | ✗ Previne | ✓ Permite | ✓ Permite | ⚡⚡⚡ |
| REPEATABLE READ | ✗ Previne | ✗ Previne | ✓ Permite | ⚡⚡ |
| SERIALIZABLE | ✗ Previne | ✗ Previne | ✗ Previne | ⚡ |

**Quando usar cada nível:**

**READ UNCOMMITTED**: Quase nunca (estatísticas aproximadas)
**READ COMMITTED**: Maioria dos casos (padrão recomendado)
**REPEATABLE READ**: Relatórios que precisam consistência
**SERIALIZABLE**: Operações financeiras críticas

#### 8.2 Problemas de Concorrência

Diferentes níveis de isolamento previnem diferentes problemas de concorrência.

**Dirty Read** - Ler dados não commitados:
```sql
-- Sessão 1
UPDATE artista SET nome_artista = 'Nome Temporário' WHERE id_artista = 1;
-- (não faz commit)

-- Sessão 2 (com READ UNCOMMITTED)
SELECT nome_artista FROM artista WHERE id_artista = 1;
-- Pode ver "Nome Temporário" mesmo sem commit

-- Sessão 1
ROLLBACK;  -- Desfaz a mudança

-- Problema: Sessão 2 viu dado que nunca foi confirmado!
```

**Como funciona:**
- Transação A modifica dados mas não confirma
- Transação B lê os dados modificados
- Transação A faz ROLLBACK
- Transação B leu dados "fantasmas" que nunca existiram oficialmente

**Por que é problema:**
- Decisões baseadas em dados incorretos
- Relatórios com informações que serão desfeitas
- Cálculos errados baseados em valores temporários

**Solução:** Use READ COMMITTED ou superior

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

**Como funciona:**
- Transação A lê um registro
- Transação B modifica e confirma o mesmo registro
- Transação A relê o registro e vê valor diferente
- Mesmo dentro da mesma transação!

**Por que é problema:**
- Inconsistência dentro de uma transação
- Cálculos baseados em valores que mudaram
- Lógica de negócio pode quebrar (if valor_antes != valor_depois)

**Solução:** Use REPEATABLE READ ou SERIALIZABLE

**Phantom Read** - Novas linhas aparecem:
```sql
-- Sessão 1
SET TRANSACTION ISOLATION LEVEL REPEATABLE READ;
SELECT COUNT(*) FROM artista WHERE pais = 'Brasil';  -- 10 artistas

-- Sessão 2
INSERT INTO artista (id, nome, pais) VALUES (999, 'Novo', 'Brasil');
COMMIT;

-- Sessão 1
SELECT * FROM artista WHERE pais = 'Brasil';  -- 11 artistas! (Phantom)
-- COUNT ainda retorna 10, mas SELECT * mostra 11
```

**Como funciona:**
- Transação A conta/lista registros
- Transação B insere novos registros que atendem critério
- Transação A re-executa query e vê novos registros (phantoms)
- REPEATABLE READ garante mesmos valores, mas não mesmas linhas

**Por que é problema:**
- Agregações podem mudar (COUNT, SUM)
- Relatórios com quantidades inconsistentes
- Lógica de paginação pode quebrar

**Solução:** Use SERIALIZABLE

### 9. Locks e Concorrência

Locks (bloqueios) são mecanismos que controlam acesso concorrente aos dados, prevenindo conflitos e garantindo integridade.

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

**Tipos principais de locks:**

**1. Shared Lock (S-Lock) - Bloqueio Compartilhado:**
- **Uso**: Durante SELECT (em alguns níveis de isolamento)
- **Permite**: Múltiplas transações ler simultaneamente
- **Bloqueia**: Escritas (UPDATE/DELETE) até lock ser liberado
- **Sintaxe**: `SELECT ... FOR SHARE` ou `SELECT ... LOCK IN SHARE MODE`

```sql
-- Múltiplas sessões podem ter S-Lock simultaneamente
-- Sessão A:
SELECT * FROM artista WHERE id = 1 FOR SHARE;  -- S-Lock

-- Sessão B:
SELECT * FROM artista WHERE id = 1 FOR SHARE;  -- OK! Também S-Lock

-- Sessão C:
UPDATE artista SET nome = 'X' WHERE id = 1;  -- ESPERA! Não pode modificar
```

**2. Exclusive Lock (X-Lock) - Bloqueio Exclusivo:**
- **Uso**: Durante UPDATE, DELETE, INSERT
- **Permite**: Apenas uma transação modificar
- **Bloqueia**: Outras leituras e escritas
- **Sintaxe**: `SELECT ... FOR UPDATE` ou automático em DML

```sql
-- Apenas uma sessão pode ter X-Lock
-- Sessão A:
SELECT * FROM artista WHERE id = 1 FOR UPDATE;  -- X-Lock

-- Sessão B:
SELECT * FROM artista WHERE id = 1 FOR UPDATE;  -- ESPERA!
SELECT * FROM artista WHERE id = 1 FOR SHARE;   -- ESPERA!
SELECT * FROM artista WHERE id = 1;             -- Depende do isolamento
```

**3. FOR UPDATE - Lock Explícito:**
```sql
-- Bloquear para atualização posterior
SELECT * FROM conta WHERE id = 123 FOR UPDATE;
-- Outros users não podem modificar ou bloquear esta linha
-- até você fazer COMMIT ou ROLLBACK

UPDATE conta SET saldo = saldo - 100 WHERE id = 123;
COMMIT;  -- Libera o lock
```

**4. FOR UPDATE NOWAIT - Não Esperar:**
```sql
-- Tenta bloquear, mas não espera se já estiver bloqueado
SELECT * FROM artista WHERE id = 1 FOR UPDATE NOWAIT;
-- Se já bloqueado: retorna erro imediatamente
-- Útil para: interfaces que não podem "travar"
```

**5. FOR UPDATE WAIT n - Esperar com Timeout:**
```sql
-- Espera até 10 segundos pelo lock
SELECT * FROM artista WHERE id = 1 FOR UPDATE WAIT 10;
-- Se não conseguir em 10s: retorna erro
-- Útil para: evitar esperas infinitas
```

**Por que usar locks explícitos:**
- **Consistência de Leitura**: Garantir que dados não mudem entre SELECT e UPDATE
- **Operações Atômicas**: Ler, processar e atualizar atomicamente
- **Evitar Lost Updates**: Duas transações não sobrescrevem mudanças uma da outra

**Exemplo - Evitar Lost Update:**
```sql
-- PROBLEMA (sem lock):
-- Sessão A: SELECT saldo FROM conta WHERE id = 1;  → 1000
-- Sessão B: SELECT saldo FROM conta WHERE id = 1;  → 1000
-- Sessão A: UPDATE conta SET saldo = 1000 - 100;   → 900
-- Sessão B: UPDATE conta SET saldo = 1000 - 50;    → 950 (perdeu update de A!)

-- SOLUÇÃO (com lock):
-- Sessão A: SELECT saldo FROM conta WHERE id = 1 FOR UPDATE;  → 1000, bloqueado
-- Sessão B: SELECT saldo FROM conta WHERE id = 1 FOR UPDATE;  → ESPERA...
-- Sessão A: UPDATE conta SET saldo = 1000 - 100; COMMIT;       → 900, libera lock
-- Sessão B: (agora consegue lock) SELECT retorna → 900
-- Sessão B: UPDATE conta SET saldo = 900 - 50; COMMIT;         → 850 (correto!)
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

**O que é Deadlock:**
Deadlock ocorre quando duas ou mais transações esperam eternamente uma pela outra, criando um ciclo de espera sem solução.

**Exemplo clássico de deadlock:**
```sql
-- Sessão A:
UPDATE artista SET nome = 'X' WHERE id = 1;  -- Lock em artista.id=1
-- Agora quer:
UPDATE album SET titulo = 'Y' WHERE id = 1;  -- ESPERA lock em album.id=1

-- Sessão B (executando ao mesmo tempo):
UPDATE album SET titulo = 'Z' WHERE id = 1;  -- Lock em album.id=1
-- Agora quer:
UPDATE artista SET nome = 'W' WHERE id = 1;  -- ESPERA lock em artista.id=1

-- DEADLOCK! A espera B, B espera A → ciclo infinito
```

**Como funciona a detecção:**
- SGBD monitora grafos de espera de transações
- Periodicamente verifica se há ciclos
- Quando detecta deadlock, escolhe uma "vítima" para abortar
- Vítima recebe erro de deadlock e faz ROLLBACK automático
- Outra transação pode prosseguir

**Como prevenir deadlocks:**

**1. Ordem Consistente de Acesso:**
```sql
-- RUIM: Ordens diferentes
-- Transação A: UPDATE tabela1, depois tabela2
-- Transação B: UPDATE tabela2, depois tabela1
-- Pode causar deadlock!

-- BOM: Mesma ordem sempre
-- Todas transações: UPDATE tabela1 primeiro, depois tabela2
-- Nunca deadlock!
```

**2. Transações Curtas:**
```sql
-- RUIM: Transação longa
BEGIN;
UPDATE artista ...;
-- processamento demorado, cálculos...
-- SLEEP(60);  -- 60 segundos!
UPDATE album ...;
COMMIT;

-- BOM: Transação rápida
-- Fazer processamento FORA da transação
calcular_valores();  -- Fora
BEGIN;
UPDATE artista ...;
UPDATE album ...;
COMMIT;  -- Rápido!
```

**3. Lock Explícito com Ordem:**
```sql
-- Bloquear tudo que precisa logo no início
BEGIN;
SELECT * FROM artista WHERE id IN (1,2,3) FOR UPDATE;
SELECT * FROM album WHERE id IN (10,20) FOR UPDATE;
-- Agora pode UPDATE sem risco de deadlock
UPDATE artista...;
UPDATE album...;
COMMIT;
```

**4. Usar NOWAIT ou WAIT com timeout:**
```sql
BEGIN
    -- Tenta bloquear sem esperar
    SELECT * FROM artista WHERE id = 1 FOR UPDATE NOWAIT;
EXCEPTION
    WHEN resource_busy THEN
        -- Não conseguiu lock, tenta depois
        ROLLBACK;
        RAISE_APPLICATION_ERROR(-20001, 'Recurso ocupado, tente novamente');
END;
```

**Tratando deadlocks na aplicação:**
```sql
-- Pseudocódigo
max_tentativas = 3;
tentativa = 0;

WHILE tentativa < max_tentativas LOOP
    BEGIN
        -- Tenta executar transação
        BEGIN TRANSACTION;
        UPDATE ...;
        COMMIT;
        EXIT;  -- Sucesso, sai do loop
    EXCEPTION
        WHEN deadlock_detected THEN
            tentativa++;
            ROLLBACK;
            SLEEP(RANDOM(1,5));  -- Espera tempo aleatório
            -- Tenta novamente
    END;
END LOOP;
```

**Monitoramento de deadlocks (Oracle):**
```sql
-- Ver deadlocks recentes
SELECT * FROM V$DEADLOCK;

-- Alertas no arquivo trace
-- Oracle grava detalhes de deadlock em arquivos trace

-- Configurar detecção
ALTER SYSTEM SET ddl_lock_timeout = 30;  -- Timeout de 30s
```

### PARTE 3: CRIAÇÃO DE RELATÓRIOS AVANÇADOS

Relatórios são a principal forma de apresentar informações do banco de dados de maneira organizada e compreensível. Esta seção ensina como criar relatórios profissionais usando SQL.

**O que são Relatórios SQL:**
- Consultas SELECT formatadas e organizadas para apresentação
- Agregações, totalizações e resumos de dados
- Informações estruturadas para tomada de decisão
- Base para dashboards, gráficos e análises

**Por que aprender criação de relatórios:**
- **Comunicação**: Traduzir dados brutos em informações úteis
- **Análise**: Identificar padrões, tendências e insights
- **Monitoramento**: Acompanhar KPIs e métricas de negócio
- **Decisão**: Fornecer dados para decisões estratégicas

### 10. Criação de Relatórios Básicos

Relatórios básicos combinam SELECT, agregações (COUNT, SUM, AVG) e agrupamentos (GROUP BY) para resumir grandes volumes de dados em informações compreensíveis.

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

**Como funciona:**
- **GROUP BY pais_origem**: Agrupa todos artistas do mesmo país
- **COUNT(*)**: Conta total de artistas em cada grupo (país)
- **COUNT(CASE...)**: Conta condicionalmente (apenas ativos ou inativos)
- **ORDER BY COUNT(*) DESC**: Países com mais artistas aparecem primeiro
- **WHERE IS NOT NULL**: Exclui artistas sem país definido

**Por que usar este formato:**
- **Agregação**: Resumir muitos registros em poucos
- **Comparação**: Fácil ver quais países têm mais artistas
- **Categorização**: Dividir em ativos/inativos mostra status
- **Organização**: Ordem decrescente destaca principais países

**Funções de agregação usadas:**

**COUNT(*)** - Conta todas as linhas:
```sql
SELECT pais_origem, COUNT(*) as total
FROM artista
GROUP BY pais_origem;
-- Retorna quantidade de artistas por país
```

**COUNT(CASE WHEN ... THEN 1 END)** - Conta condicional:
```sql
COUNT(CASE WHEN ativo = 'S' THEN 1 END)
-- Conta apenas quando condição é verdadeira
-- Equivale a COUNT com filtro
```

**Exemplo de resultado:**
```
País        | Total Artistas | Ativos | Inativos
------------|----------------|--------|----------
Brasil      | 150            | 120    | 30
EUA         | 200            | 180    | 20
Inglaterra  | 100            | 85     | 15
```

**Variações úteis:**

**Com percentual:**
```sql
SELECT 
    pais_origem as "País",
    COUNT(*) as "Total",
    ROUND(COUNT(*) * 100.0 / SUM(COUNT(*)) OVER(), 2) as "Percentual(%)"
FROM artista
WHERE pais_origem IS NOT NULL
GROUP BY pais_origem
ORDER BY COUNT(*) DESC;
```

**Com filtro de mínimo:**
```sql
-- Apenas países com 10+ artistas
SELECT pais_origem, COUNT(*) as total
FROM artista
WHERE pais_origem IS NOT NULL
GROUP BY pais_origem
HAVING COUNT(*) >= 10
ORDER BY total DESC;
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

**Como funciona:**
- **CASE WHEN**: Transforma anos em décadas (categorizando valores numéricos)
- **GROUP BY CASE**: Agrupa por resultado do CASE (década)
- **COUNT(*)**: Conta álbuns em cada década
- **AVG(numero_faixas)**: Calcula média de faixas por álbum na década
- **ROUND(..., 1)**: Arredonda média para 1 casa decimal

**Por que usar CASE para agrupamento:**
- **Categorização**: Transforma valores contínuos em categorias
- **Flexibilidade**: Define categorias customizadas (décadas, faixas de preço, etc.)
- **Legibilidade**: Resultado mais compreensível que anos individuais
- **Análise**: Identifica tendências ao longo do tempo

**Técnica: Categorização com CASE:**
```sql
-- Categorizar em faixas
CASE
    WHEN valor < 10 THEN 'Baixo'
    WHEN valor BETWEEN 10 AND 50 THEN 'Médio'
    WHEN valor > 50 THEN 'Alto'
    ELSE 'Não definido'
END
```

**Exemplo de resultado:**
```
Década | Total Álbuns | Média Faixas
-------|--------------|-------------
1960s  | 120          | 12.3
1970s  | 250          | 10.8
1980s  | 340          | 11.5
1990s  | 450          | 13.2
2000s  | 520          | 14.1
2010s  | 480          | 12.9
2020s  | 150          | 11.7
```

**Variação: usando FLOOR para década:**
```sql
-- Alternativa mais simples (mas menos legível)
SELECT 
    FLOOR(ano_lancamento / 10) * 10 || 's' as "Década",
    COUNT(*) as "Total"
FROM album
WHERE ano_lancamento IS NOT NULL
GROUP BY FLOOR(ano_lancamento / 10)
ORDER BY FLOOR(ano_lancamento / 10);
-- 1960 → 196 → 1960
-- 1975 → 197 → 1970
```

**Outras agregações úteis:**
```sql
SELECT 
    ... as "Década",
    COUNT(*) as "Total",
    AVG(numero_faixas) as "Média Faixas",
    MIN(numero_faixas) as "Mínimo Faixas",
    MAX(numero_faixas) as "Máximo Faixas",
    SUM(numero_faixas) as "Total Faixas"
FROM album
GROUP BY ...
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

**Como funciona:**
- **Multiple JOINs**: Combina dados de 5 tabelas relacionadas
- **COUNT(hr.id_historico)**: Conta reproduções de cada música
- **RANK() OVER**: Função analítica que cria ranking dentro de cada gênero
- **PARTITION BY g.nome_genero**: Reinicia ranking para cada gênero
- **ORDER BY COUNT(...) DESC**: Ordem do ranking (mais reproduções = rank 1)
- **HAVING COUNT(...) > 0**: Apenas músicas com pelo menos 1 reprodução

**Por que usar RANK():**
- **Rankings**: Criar top N dentro de categorias
- **Comparação**: Ver posição relativa dentro do grupo
- **Análise**: Identificar melhores/piores de cada categoria
- **Flexibilidade**: Pode fazer top 10 de cada gênero em uma query

**Funções de janela (Window Functions):**

**RANK()** - Ranking com gaps:
```sql
RANK() OVER (ORDER BY reproducoes DESC)
-- 1, 2, 2, 4, 5 (empate em 2º, próximo é 4º)
```

**DENSE_RANK()** - Ranking sem gaps:
```sql
DENSE_RANK() OVER (ORDER BY reproducoes DESC)
-- 1, 2, 2, 3, 4 (empate em 2º, próximo é 3º)
```

**ROW_NUMBER()** - Número sequencial único:
```sql
ROW_NUMBER() OVER (ORDER BY reproducoes DESC)
-- 1, 2, 3, 4, 5 (sem empates, ordem arbitrária para empates)
```

**PARTITION BY** - Reinicia contagem por grupo:
```sql
RANK() OVER (PARTITION BY genero ORDER BY reproducoes DESC)
-- Rank 1, 2, 3... para cada gênero separadamente
```

**Exemplo de resultado:**
```
Gênero | Música        | Artista    | Reproduções | Rank
-------|---------------|------------|-------------|-----
Rock   | Imagine       | John Lennon| 1500        | 1
Rock   | Hey Jude      | Beatles    | 1400        | 2
Rock   | Bohemian Rhap | Queen      | 1300        | 3
Pop    | Thriller      | MJ         | 2000        | 1
Pop    | Billie Jean   | MJ         | 1800        | 2
```

**Filtrar apenas Top N:**
```sql
-- Usar subquery ou CTE para filtrar por rank
WITH RankedSongs AS (
    SELECT 
        g.nome_genero,
        m.titulo,
        COUNT(hr.id_historico) as reproducoes,
        RANK() OVER (PARTITION BY g.nome_genero 
                     ORDER BY COUNT(hr.id_historico) DESC) as rank
    FROM genero g
    JOIN musica m ON g.id_genero = m.id_genero
    LEFT JOIN historico_reproducao hr ON m.id_musica = hr.id_musica
    GROUP BY g.nome_genero, m.titulo, m.id_musica
)
SELECT *
FROM RankedSongs
WHERE rank <= 10;  -- Top 10 de cada gênero
```

### 11. Formatação de Relatórios

A formatação adequada torna relatórios mais legíveis e profissionais, especialmente quando executados em ferramentas de linha de comando como SQL*Plus.

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

**Comandos de formatação:**

**SET PAGESIZE n** - Linhas por página:
- Define quantas linhas antes de repetir cabeçalho
- `SET PAGESIZE 50` = cabeçalho a cada 50 linhas
- `SET PAGESIZE 0` = sem quebra de página
- **Por que usar**: Relatórios longos ficam mais legíveis

**SET LINESIZE n** - Largura da linha:
- Define largura máxima da linha (caracteres)
- `SET LINESIZE 120` = linhas com até 120 caracteres
- **Por que usar**: Evita quebra de linhas no meio

**SET FEEDBACK OFF/ON** - Mensagem "N rows selected":
- OFF: Esconde mensagem de linhas retornadas
- ON: Mostra mensagem (padrão)
- **Por que usar OFF**: Relatórios mais limpos

**SET HEADING OFF/ON** - Cabeçalhos de coluna:
- OFF: Esconde nomes de colunas
- ON: Mostra nomes (padrão)
- **Por que usar OFF**: Exportar apenas dados

**COLUMN** - Formatação de coluna específica:

**FORMAT A30** - String com 30 caracteres:
```sql
COLUMN nome FORMAT A30;  -- Nome ocupará 30 caracteres
```

**FORMAT 999,999** - Número com separador de milhar:
```sql
COLUMN salario FORMAT 999,999.99;  -- Ex: 12,345.67
```

**HEADING** - Título customizado:
```sql
COLUMN total HEADING 'Total de|Registros';  -- | cria quebra de linha no título
```

**Outros formatos numéricos:**
```sql
COLUMN valor FORMAT 999999999;     -- 9 dígitos, sem formatação
COLUMN preco FORMAT $999,999.99;   -- Com símbolo de moeda
COLUMN percent FORMAT 999.99;      -- Decimais
```

**Exemplo prático:**
```sql
-- Configuração completa de relatório
SET PAGESIZE 60;
SET LINESIZE 150;
SET FEEDBACK OFF;

COLUMN artista FORMAT A25 HEADING 'Artista';
COLUMN albums FORMAT 999 HEADING 'Qtd|Álbuns';
COLUMN musicas FORMAT 9,999 HEADING 'Total|Músicas';
COLUMN duracao FORMAT 9,999.9 HEADING 'Duração|Média';

SELECT 
    a.nome_artista as artista,
    COUNT(DISTINCT al.id_album) as albums,
    COUNT(m.id_musica) as musicas,
    AVG(m.duracao/60.0) as duracao
FROM artista a
LEFT JOIN album al ON a.id_artista = al.id_artista
LEFT JOIN musica m ON al.id_album = m.id_album
GROUP BY a.nome_artista
ORDER BY albums DESC;

-- Limpar formatação
CLEAR COLUMNS;
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

**Comandos avançados de formatação:**

**TTITLE** - Título no topo da página:
```sql
TTITLE CENTER 'RELATÓRIO DE VENDAS' SKIP 2;
-- CENTER: centralizado
-- LEFT/RIGHT: alinhamento
-- SKIP 2: pula 2 linhas após título
```

**BTITLE** - Título no rodapé da página:
```sql
BTITLE LEFT 'Confidencial' CENTER 'Página: SQL.PNO' RIGHT '&_DATE';
-- Pode combinar múltiplos elementos
-- SQL.PNO: número da página
-- &_DATE: data atual
```

**BREAK ON** - Quebras e subtotais:
```sql
BREAK ON pais_origem SKIP 1 ON REPORT;
-- SKIP 1: pula linha quando país muda
-- ON REPORT: adiciona linha de total no final
```

**COMPUTE** - Cálculos em quebras:
```sql
COMPUTE SUM OF salario ON pais_origem;
COMPUTE SUM OF salario ON REPORT;
-- Soma salários por país e total geral
```

**Exemplo com quebras e subtotais:**
```sql
SET PAGESIZE 60;
SET LINESIZE 100;

COLUMN pais FORMAT A15 HEADING 'País';
COLUMN artista FORMAT A25 HEADING 'Artista';
COLUMN albums FORMAT 999 HEADING 'Álbuns';

BREAK ON pais SKIP 1 ON REPORT;
COMPUTE SUM OF albums ON pais;
COMPUTE SUM OF albums ON REPORT;

SELECT 
    a.pais_origem as pais,
    a.nome_artista as artista,
    COUNT(al.id_album) as albums
FROM artista a
LEFT JOIN album al ON a.id_artista = al.id_artista
WHERE a.pais_origem IS NOT NULL
GROUP BY a.pais_origem, a.nome_artista
ORDER BY a.pais_origem, albums DESC;

CLEAR BREAKS;
CLEAR COMPUTES;
```

**Resultado esperado:**
```
País           Artista                   Álbuns
------------   ------------------------- ------
Brasil         Roberto Carlos                 45
Brasil         Caetano Veloso                 38
Brasil         Gilberto Gil                   32
               ******                        ---
               sum                           115

EUA            Michael Jackson                15
EUA            Madonna                        13
               ******                        ---
               sum                            28

                                          =====
                                   sum        143
```

**Formatação de datas:**
```sql
COLUMN data FORMAT A12;
SELECT TO_CHAR(data_cadastro, 'DD/MM/YYYY') as data FROM usuario;
```

**Formatação condicional (usando CASE):**
```sql
SELECT 
    nome,
    salario,
    CASE 
        WHEN salario < 3000 THEN 'BAIXO'
        WHEN salario BETWEEN 3000 AND 7000 THEN 'MÉDIO'
        ELSE 'ALTO'
    END as faixa_salarial
FROM funcionarios;
```

**Limpar formatação:**
```sql
CLEAR COLUMNS;   -- Limpa todas formatações de COLUMN
CLEAR BREAKS;    -- Limpa todas quebras
CLEAR COMPUTES;  -- Limpa todos cálculos
TTITLE OFF;      -- Desliga título superior
BTITLE OFF;      -- Desliga título inferior
```

### 12. Transações em Cenários Práticos

Esta seção demonstra como aplicar transações em situações do mundo real, combinando todos os conceitos aprendidos para resolver problemas comuns de sistemas de informação.

**Por que cenários práticos são importantes:**
- **Aplicação Real**: Mostra como usar conceitos em situações reais
- **Boas Práticas**: Demonstra padrões de código profissional
- **Tratamento de Erros**: Ensina como lidar com falhas adequadamente
- **Atomicidade**: Garante consistência em operações complexas

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

**Como funciona:**
1. **SAVEPOINT**: Marca ponto de restauração antes de iniciar operações
2. **DELETE**: Remove músicas da playlist origem
3. **SQL%ROWCOUNT**: Verifica quantas linhas foram afetadas
4. **Validação**: Se nenhuma linha deletada, algo está errado
5. **ROLLBACK TO**: Em caso de erro, volta ao savepoint
6. **INSERT**: Se DELETE ok, insere na playlist destino
7. **COMMIT**: Confirma ambas operações atomicamente

**Por que esta abordagem:**
- **Atomicidade**: Ou transfere tudo ou não transfere nada
- **Validação**: Verifica sucesso antes de prosseguir
- **Rollback Parcial**: SAVEPOINT permite desfazer apenas esta operação
- **Feedback**: Mensagens informam o que aconteceu

**Versão melhorada com tratamento de exceções:**
```sql
CREATE OR REPLACE PROCEDURE transferir_musicas(
    p_playlist_origem NUMBER,
    p_playlist_destino NUMBER,
    p_musicas VARCHAR2  -- IDs separados por vírgula: '1,2,3'
) AS
    v_count NUMBER;
    v_max_ordem NUMBER;
BEGIN
    SAVEPOINT inicio_transferencia;
    
    -- Validar playlists existem
    SELECT COUNT(*) INTO v_count 
    FROM playlist 
    WHERE id_playlist IN (p_playlist_origem, p_playlist_destino);
    
    IF v_count < 2 THEN
        RAISE_APPLICATION_ERROR(-20001, 'Playlist origem ou destino não existe');
    END IF;
    
    -- Obter próxima ordem da playlist destino
    SELECT NVL(MAX(ordem_reproducao), 0) INTO v_max_ordem
    FROM playlist_musica
    WHERE id_playlist = p_playlist_destino;
    
    -- Remover da origem
    DELETE FROM playlist_musica
    WHERE id_playlist = p_playlist_origem
    AND id_musica IN (SELECT REGEXP_SUBSTR(p_musicas, '[^,]+', 1, LEVEL)
                      FROM dual
                      CONNECT BY LEVEL <= REGEXP_COUNT(p_musicas, ',') + 1);
    
    v_count := SQL%ROWCOUNT;
    
    IF v_count = 0 THEN
        ROLLBACK TO inicio_transferencia;
        RAISE_APPLICATION_ERROR(-20002, 'Nenhuma música encontrada na playlist origem');
    END IF;
    
    -- Adicionar ao destino
    INSERT INTO playlist_musica (id_playlist, id_musica, ordem_reproducao)
    SELECT 
        p_playlist_destino,
        REGEXP_SUBSTR(p_musicas, '[^,]+', 1, LEVEL),
        v_max_ordem + LEVEL
    FROM dual
    CONNECT BY LEVEL <= REGEXP_COUNT(p_musicas, ',') + 1;
    
    -- Atualizar contadores
    UPDATE playlist 
    SET numero_musicas = (SELECT COUNT(*) FROM playlist_musica WHERE id_playlist = p_playlist_origem)
    WHERE id_playlist = p_playlist_origem;
    
    UPDATE playlist 
    SET numero_musicas = (SELECT COUNT(*) FROM playlist_musica WHERE id_playlist = p_playlist_destino)
    WHERE id_playlist = p_playlist_destino;
    
    COMMIT;
    DBMS_OUTPUT.PUT_LINE('Transferência de ' || v_count || ' música(s) concluída');
    
EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK TO inicio_transferencia;
        RAISE_APPLICATION_ERROR(-20003, 'Erro na transferência: ' || SQLERRM);
END;
/
```

**Uso:**
```sql
-- Transferir músicas 1, 2 e 3 da playlist 10 para playlist 20
EXEC transferir_musicas(10, 20, '1,2,3');
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

**Como funciona:**
1. **UPDATE com subconsulta**: Calcula valores agregados e atualiza em uma operação
2. **Múltiplos UPDATEs**: Atualiza diferentes tabelas relacionadas
3. **Atomicidade**: Todas estatísticas atualizam juntas ou nenhuma atualiza
4. **SQL%ROWCOUNT**: Verifica se operações foram bem-sucedidas
5. **COMMIT/ROLLBACK**: Confirma ou desfaz baseado em sucesso

**Por que esta abordagem:**
- **Consistência**: Todas estatísticas ficam sincronizadas
- **Performance**: UPDATE com subconsulta é mais rápido que loops
- **Transacional**: Garante que estado do banco fica consistente
- **Manutenibilidade**: Fácil adicionar novas estatísticas

**Versão com tratamento robusto:**
```sql
CREATE OR REPLACE PROCEDURE atualizar_estatisticas AS
    v_usuarios_atualizados NUMBER := 0;
    v_playlists_atualizadas NUMBER := 0;
    v_erro VARCHAR2(4000);
BEGIN
    -- Atualizar estatísticas de usuários
    BEGIN
        UPDATE usuario u
        SET total_reproducoes = (
            SELECT COUNT(*) 
            FROM historico_reproducao hr 
            WHERE hr.id_usuario = u.id_usuario
        ),
        ultima_reproducao = (
            SELECT MAX(data_reproducao) 
            FROM historico_reproducao hr 
            WHERE hr.id_usuario = u.id_usuario
        );
        
        v_usuarios_atualizados := SQL%ROWCOUNT;
        
    EXCEPTION
        WHEN OTHERS THEN
            v_erro := 'Erro ao atualizar usuários: ' || SQLERRM;
            RAISE;
    END;
    
    -- Atualizar estatísticas de playlists
    BEGIN
        UPDATE playlist p
        SET numero_musicas = (
            SELECT COUNT(*) 
            FROM playlist_musica pm 
            WHERE pm.id_playlist = p.id_playlist
        ),
        duracao_total = (
            SELECT NVL(SUM(m.duracao), 0)
            FROM playlist_musica pm
            JOIN musica m ON pm.id_musica = m.id_musica
            WHERE pm.id_playlist = p.id_playlist
        );
        
        v_playlists_atualizadas := SQL%ROWCOUNT;
        
    EXCEPTION
        WHEN OTHERS THEN
            v_erro := 'Erro ao atualizar playlists: ' || SQLERRM;
            RAISE;
    END;
    
    -- Atualizar estatísticas de artistas (popularidade)
    UPDATE artista a
    SET popularidade = (
        SELECT COUNT(DISTINCT hr.id_usuario)
        FROM album al
        JOIN musica m ON al.id_album = m.id_album
        JOIN historico_reproducao hr ON m.id_musica = hr.id_musica
        WHERE al.id_artista = a.id_artista
    );
    
    COMMIT;
    
    DBMS_OUTPUT.PUT_LINE('Estatísticas atualizadas:');
    DBMS_OUTPUT.PUT_LINE('- Usuários: ' || v_usuarios_atualizados);
    DBMS_OUTPUT.PUT_LINE('- Playlists: ' || v_playlists_atualizadas);
    
EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        RAISE_APPLICATION_ERROR(-20001, 
            'Falha na atualização de estatísticas: ' || NVL(v_erro, SQLERRM));
END;
/
```

**Uso em job agendado:**
```sql
-- Agendar atualização diária às 2h da manhã
BEGIN
    DBMS_SCHEDULER.CREATE_JOB(
        job_name        => 'ATUALIZAR_STATS_DIARIO',
        job_type        => 'PLSQL_BLOCK',
        job_action      => 'BEGIN atualizar_estatisticas; END;',
        start_date      => SYSTIMESTAMP,
        repeat_interval => 'FREQ=DAILY; BYHOUR=2',
        enabled         => TRUE
    );
END;
/
```

**Casos de uso adicionais:**

**Importação de dados com validação:**
```sql
CREATE OR REPLACE PROCEDURE importar_artistas_lote(
    p_lote_id NUMBER
) AS
    v_importados NUMBER := 0;
    v_erros NUMBER := 0;
BEGIN
    SAVEPOINT inicio_importacao;
    
    -- Importar artistas validados
    INSERT INTO artista (id_artista, nome_artista, pais_origem)
    SELECT id, nome, pais
    FROM artista_staging
    WHERE lote_id = p_lote_id
    AND nome IS NOT NULL
    AND pais_origem IS NOT NULL;
    
    v_importados := SQL%ROWCOUNT;
    
    -- Marcar registros inválidos
    UPDATE artista_staging
    SET status = 'ERRO',
        mensagem_erro = 'Dados incompletos'
    WHERE lote_id = p_lote_id
    AND (nome IS NULL OR pais_origem IS NULL);
    
    v_erros := SQL%ROWCOUNT;
    
    -- Só confirma se houver importados
    IF v_importados > 0 THEN
        COMMIT;
        DBMS_OUTPUT.PUT_LINE('Importados: ' || v_importados || ', Erros: ' || v_erros);
    ELSE
        ROLLBACK TO inicio_importacao;
        RAISE_APPLICATION_ERROR(-20001, 'Nenhum registro válido para importar');
    END IF;
    
EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        RAISE;
END;
/
```

**Reserva de recursos (padrão pessimista):**
```sql
CREATE OR REPLACE FUNCTION reservar_ingresso(
    p_evento_id NUMBER,
    p_usuario_id NUMBER,
    p_quantidade NUMBER
) RETURN NUMBER AS
    v_disponiveis NUMBER;
    v_reserva_id NUMBER;
BEGIN
    -- Bloquear linha para evitar venda simultânea
    SELECT ingressos_disponiveis 
    INTO v_disponiveis
    FROM evento
    WHERE id_evento = p_evento_id
    FOR UPDATE NOWAIT;  -- Não espera se já bloqueado
    
    -- Verificar disponibilidade
    IF v_disponiveis < p_quantidade THEN
        ROLLBACK;
        RETURN -1;  -- Sem ingressos suficientes
    END IF;
    
    -- Reduzir disponíveis
    UPDATE evento
    SET ingressos_disponiveis = ingressos_disponiveis - p_quantidade
    WHERE id_evento = p_evento_id;
    
    -- Criar reserva
    INSERT INTO reserva (id_usuario, id_evento, quantidade)
    VALUES (p_usuario_id, p_evento_id, p_quantidade)
    RETURNING id_reserva INTO v_reserva_id;
    
    COMMIT;
    RETURN v_reserva_id;
    
EXCEPTION
    WHEN resource_busy THEN
        -- Linha bloqueada por outro usuário
        RETURN -2;
    WHEN OTHERS THEN
        ROLLBACK;
        RETURN -3;
END;
/
```

**Boas práticas demonstradas:**

1. **SAVEPOINTs**: Permitem rollback parcial em procedures complexas
2. **Validação**: Sempre validar dados antes de modificar
3. **Tratamento de Exceções**: Capturar e tratar erros apropriadamente
4. **COMMIT/ROLLBACK**: Sempre finalizar transação explicitamente
5. **SQL%ROWCOUNT**: Verificar resultado de DMLs
6. **Mensagens**: Informar usuário sobre resultado das operações
7. **FOR UPDATE**: Bloquear registros quando necessário
8. **Atomicidade**: Operações relacionadas na mesma transação

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