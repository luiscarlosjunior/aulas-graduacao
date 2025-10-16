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