# Módulo 10 - Relatórios com Filtros e Operadores

## 📋 Pré-requisitos

Para executar os exemplos deste módulo, você precisará de:
- **Oracle Database 11g ou superior** (11g, 12c, 18c, 19c, 21c)
- **Oracle SQL Developer** instalado e configurado
- Estrutura base do Sistema MusiStream já criada (consulte módulo 01)
- Conhecimento básico de comandos SELECT (Módulos anteriores)

## Objetivos de Aprendizagem

Ao final deste módulo, o aluno será capaz de:
- Utilizar a cláusula WHERE para filtrar dados
- Aplicar operadores de comparação (=, <>, <, >, <=, >=)
- Combinar condições com operadores lógicos (AND, OR, NOT)
- Usar operadores especiais (IN, BETWEEN, LIKE, IS NULL)
- Construir filtros complexos para relatórios específicos
- Otimizar consultas com filtros eficientes

## 🎓 Guia Prático de Execução no Oracle SQL Developer

### Passo a Passo Completo

Este módulo contém exemplos SQL que podem ser executados diretamente no Oracle SQL Developer. Siga este guia para máxima produtividade.

#### 1. Preparação Inicial

**Abrir SQL Developer e Configurar:**
```sql
-- Configure o ambiente antes de iniciar
SET SERVEROUTPUT ON SIZE UNLIMITED;
ALTER SESSION SET NLS_DATE_FORMAT = 'DD/MM/YYYY';
ALTER SESSION SET NLS_TIMESTAMP_FORMAT = 'DD/MM/YYYY HH24:MI:SS';
SET FEEDBACK ON;
```

**Verificar Conexão:**
```sql
-- Teste sua conexão
SELECT USER, SYSDATE FROM dual;
```

#### 2. Executando Consultas no SQL Developer

**Para consultas SELECT (recomendado para este módulo):**
1. Copie o exemplo que deseja executar
2. Cole na área de trabalho do SQL Developer
3. Selecione o comando completo (ou posicione o cursor nele)
4. Pressione **Ctrl+Enter** ou clique no botão "Run Statement" (ícone de play verde)
5. Visualize os resultados na grade inferior

**Exemplo prático:**
```sql
-- Selecione esta consulta completa e pressione Ctrl+Enter
SELECT nome_artista, pais_origem, data_formacao
FROM artista
WHERE pais_origem = 'Brasil'
ORDER BY data_formacao;
```

#### 3. Atalhos Úteis do SQL Developer

- **Ctrl+Enter**: Executar comando atual (mostra resultados em grade)
- **F5**: Executar como script (mostra saída em texto)
- **F9**: Executar e buscar dados (igual Ctrl+Enter)
- **Ctrl+/**: Comentar/descomentar linha
- **Ctrl+Shift+F**: Formatar código SQL automaticamente
- **Ctrl+Space**: Auto-completar (nomes de tabelas, colunas, palavras-chave)

#### 4. Dicas de Produtividade

**Executar Múltiplas Consultas:**
- Separe consultas com ponto-e-vírgula (;)
- Execute individualmente selecionando cada uma e pressionando Ctrl+Enter
- Ou execute todas com F5 (mostrará todos os resultados em sequência)

**Visualizar Plano de Execução (para otimização):**
1. Selecione a consulta
2. Pressione **F10** ou clique no botão "Explain Plan"
3. Analise o plano na aba "Explain Plan"

**Salvar Consultas Favoritas:**
- Use o menu "View" → "Snippets" para acessar snippets de código
- Arraste consultas úteis para a área de snippets para reutilização

#### 5. Tratamento de Erros Comuns

**Erro: ORA-00904: invalid identifier**
- **Causa**: Nome de coluna ou tabela incorreto
- **Solução**: Verifique a estrutura da tabela com: `DESCRIBE nome_tabela;`

**Erro: ORA-00942: table or view does not exist**
- **Causa**: Tabela não existe no schema atual
- **Solução**: Verifique se a estrutura MusiStream foi criada corretamente

**Erro: ORA-01722: invalid number**
- **Causa**: Tentativa de comparar texto com número
- **Solução**: Verifique os tipos de dados das colunas com DESCRIBE

**Consultas muito lentas:**
- Use **F10** para ver o plano de execução
- Verifique se há índices nas colunas filtradas
- Considere adicionar índices: `CREATE INDEX idx_nome ON tabela(coluna);`

## Conteúdo Teórico

### 1. Fundamentos da Cláusula WHERE

#### 1.1 O Que É e Por Que Existe

A cláusula **WHERE** é um componente fundamental da linguagem SQL que atua como um **mecanismo de filtragem de dados**. Ela foi introduzida no SQL desde suas primeiras versões (SQL-86) porque bancos de dados podem conter milhões ou bilhões de registros, e raramente precisamos de todos eles em uma única operação.

**Por que WHERE é essencial:**

1. **Eficiência de Processamento**: Sem filtros, o SGBD (Sistema Gerenciador de Banco de Dados) teria que processar e retornar todos os registros de uma tabela, consumindo memória, CPU e largura de banda de rede desnecessariamente.

2. **Precisão de Resultados**: Sistemas de informação requerem dados específicos para tomada de decisões. Filtros permitem extrair exatamente as informações necessárias.

3. **Performance de Aplicações**: Aplicações que não filtram dados adequadamente sofrem com lentidão, timeouts e sobrecarga de recursos.

4. **Segurança e Privacy**: Filtros podem restringir acesso a dados sensíveis, implementando políticas de segurança ao nível de consulta.

**O que aconteceria sem WHERE:**

Imagine um sistema de streaming de música com 10 milhões de usuários. Sem a cláusula WHERE:

```sql
-- ❌ SEM WHERE: Retorna TODOS os usuários (10 milhões de registros)
SELECT nome_usuario, email FROM usuario;
-- Resultado: Sobrecarga do servidor, aplicação trava, experiência ruim
```

```sql
-- ✅ COM WHERE: Retorna apenas usuários brasileiros (ex: 2 milhões)
SELECT nome_usuario, email FROM usuario WHERE pais = 'Brasil';
-- Resultado: Processamento rápido, uso eficiente de recursos
```

O impacto real:
- **Sem WHERE**: Consulta pode levar 30+ segundos, consumir 500MB+ de memória
- **Com WHERE**: Consulta leva menos de 1 segundo, consome 50MB de memória

#### 1.2 Sintaxe Básica e Estrutura

```sql
SELECT colunas
FROM tabela
WHERE condição;
```

A cláusula WHERE permite filtrar registros baseados em **predicados lógicos** - expressões que avaliam para verdadeiro (TRUE) ou falso (FALSE) para cada linha. Apenas linhas onde a condição é TRUE são retornadas.

**Exemplo Acadêmico - Teoria dos Conjuntos:**

Do ponto de vista matemático, WHERE implementa uma **operação de seleção (σ)** da álgebra relacional:

```
σ(pais='Brasil')(Usuario) → Subconjunto de Usuario onde pais='Brasil'
```

Em SQL:
```sql
SELECT * FROM usuario WHERE pais = 'Brasil';
```

#### 1.3 Posicionamento na Consulta e Ordem de Processamento

A ordem sintática (como escrevemos) difere da ordem lógica (como o SGBD processa):

```sql
-- Ordem SINTÁTICA (como escrevemos):
SELECT colunas          -- 1. O que mostrar
FROM tabela             -- 2. De onde vem
WHERE condição          -- 3. Filtrar linhas
GROUP BY colunas        -- 4. Agrupar resultados
HAVING condição         -- 5. Filtrar grupos
ORDER BY colunas        -- 6. Ordenar resultado
LIMIT número;           -- 7. Limitar quantidade

-- Ordem LÓGICA (como o SGBD processa internamente):
-- 1. FROM: Determina tabelas envolvidas
-- 2. WHERE: Filtra linhas ANTES de qualquer agregação
-- 3. GROUP BY: Agrupa linhas filtradas
-- 4. HAVING: Filtra grupos criados
-- 5. SELECT: Projeta colunas desejadas
-- 6. ORDER BY: Ordena resultado final
-- 7. LIMIT: Restringe quantidade de linhas retornadas
```

**Por que esta ordem importa:**

1. **WHERE vs HAVING**: WHERE filtra linhas individuais antes de agrupar (mais eficiente), HAVING filtra depois do agrupamento.

```sql
-- ✅ EFICIENTE: WHERE filtra ANTES de agrupar
SELECT pais, COUNT(*) as total
FROM usuario
WHERE data_cadastro >= '2023-01-01'  -- Filtra 1M de registros primeiro
GROUP BY pais;                        -- Agrupa apenas dados relevantes

-- ❌ INEFICIENTE: HAVING filtra DEPOIS de agrupar
SELECT pais, COUNT(*) as total
FROM usuario
GROUP BY pais                         -- Agrupa TODOS os 10M de registros
HAVING MAX(data_cadastro) >= '2023-01-01'; -- Filtra grupos depois
```

2. **Performance**: Filtrar cedo reduz o volume de dados processado nas etapas subsequentes.

#### 1.4 Impacto Acadêmico e Prático da Ausência de Filtros

**Cenário Real - Sistema MusiStream:**

Base de dados:
- 10.000.000 de usuários
- 50.000.000 de reproduções de música
- 1.000.000 de músicas

**Sem filtros adequados:**
```sql
-- Consulta sem WHERE: processa TODAS as 50 milhões de reproduções
SELECT u.nome_usuario, COUNT(*) as total_reproducoes
FROM usuario u
JOIN historico_reproducao h ON u.id_usuario = h.id_usuario
GROUP BY u.nome_usuario;

-- Tempo estimado: 2-5 minutos
-- Memória: 2-3 GB
-- CPU: 80-100% por minutos
-- Resultado: Timeout ou crash da aplicação
```

**Com filtros estratégicos:**
```sql
-- Consulta com WHERE: processa apenas últimos 30 dias (~4 milhões)
SELECT u.nome_usuario, COUNT(*) as total_reproducoes
FROM usuario u
JOIN historico_reproducao h ON u.id_usuario = h.id_usuario
WHERE h.data_reproducao >= SYSDATE - 30
  AND u.ativo = 'S'
GROUP BY u.nome_usuario;

-- Tempo estimado: 2-5 segundos
-- Memória: 200-300 MB
-- CPU: 20-30% por segundos
-- Resultado: Resposta rápida e eficiente
```

**Benefícios mensuráveis:**
- **92% redução no tempo de processamento**
- **85% redução no uso de memória**
- **90% redução na carga de CPU**
- **Melhor experiência do usuário**: Aplicação responsiva vs travada

### 2. Operadores de Comparação

#### 2.1 Fundamentos Teóricos

Os **operadores de comparação** são predicados binários que estabelecem relações entre dois valores, retornando um valor booleano (TRUE, FALSE ou NULL). Eles implementam conceitos fundamentais de lógica proposicional e teoria da ordem em matemática.

**Por que operadores de comparação existem:**

1. **Expressão de Requisitos de Negócio**: Sistemas reais têm regras baseadas em comparações - "clientes maiores de 18 anos", "produtos com preço acima de R$100", "pedidos após 01/01/2024".

2. **Implementação de Lógica Condicional**: Permitem que bancos de dados tomem "decisões" sobre quais dados incluir, análogo a estruturas if-then em programação.

3. **Suporte a Consultas Analíticas**: Análises de negócio frequentemente envolvem comparações - ranking, limites, faixas de valores.

4. **Otimização por Índices**: SGBDs podem usar índices B-tree ou Hash para acelerar comparações, tornando consultas milhares de vezes mais rápidas.

**O que aconteceria sem operadores de comparação:**

Teríamos que buscar TODOS os dados e filtrar na aplicação:

```sql
-- ❌ SEM OPERADORES: Impossível no SQL puro
-- Teríamos que fazer na aplicação:
SELECT * FROM usuario;  -- Busca TODOS os usuários (10M registros)
-- Na aplicação (Python, Java, etc.):
-- usuarios_adultos = [u for u in usuarios if calcular_idade(u.data_nascimento) >= 18]
-- Problema: 10M registros transferidos pela rede + processamento lento na aplicação
```

```sql
-- ✅ COM OPERADORES: Filtro eficiente no banco
SELECT * FROM usuario 
WHERE data_nascimento <= SYSDATE - INTERVAL '18' YEAR;
-- Resultado: Apenas ~6M adultos transferidos, processamento rápido no SGBD
```

**Impacto Real:**
- **Transferência de dados**: 10M registros vs 6M (40% de redução)
- **Tempo de rede**: 50MB vs 30MB transferidos
- **Processamento**: SGBD otimizado vs loop na aplicação

#### 2.2 Operadores Básicos e Suas Semânticas

**Igualdade (=) - Equivalência Exata**

Testa se dois valores são idênticos. É a operação mais comum e a que melhor aproveita índices.

```sql
-- Igualdade (=)
SELECT nome_artista, pais_origem
FROM artista
WHERE pais_origem = 'Brasil';
```

**Semântica formal**: `a = b ↔ a e b são indistinguíveis em valor`

**Por que existe**: Buscar correspondências exatas é a operação mais fundamental em bancos de dados relacionais - chaves primárias, chaves estrangeiras, lookups.

**Comportamento com NULL**: `NULL = NULL` retorna `NULL` (não TRUE!), pois NULL representa "valor desconhecido" e dois desconhecidos não são necessariamente iguais.

```sql
-- ❌ INCORRETO: Não encontra registros com biografia NULL
SELECT * FROM artista WHERE biografia = NULL;

-- ✅ CORRETO: Usa IS NULL
SELECT * FROM artista WHERE biografia IS NULL;
```

**Diferença (<> ou !=) - Não-Equivalência**

Testa se dois valores são diferentes.

```sql
-- Diferença (<> ou !=)
SELECT nome_artista, pais_origem
FROM artista
WHERE pais_origem <> 'Brasil';  -- Padrão SQL
-- OU
WHERE pais_origem != 'Brasil';  -- Sintaxe alternativa (Oracle aceita)
```

**Por que dois símbolos**: `<>` é o padrão ANSI SQL, `!=` é uma extensão comum suportada por muitos SGBDs por conveniência.

**Cuidado com NULL**: `valor <> NULL` também retorna NULL, não TRUE!

**Operadores de Magnitude - Ordem Total**

Implementam relações de **ordem** entre valores, baseados em:
- **Números**: ordem numérica (1 < 2 < 3...)
- **Strings**: ordem lexicográfica ('A' < 'B' < 'C'...)
- **Datas**: ordem cronológica (anterior < posterior)

```sql
-- Maior que (>)
SELECT titulo, duracao
FROM musica
WHERE duracao > 300; -- Músicas com mais de 5 minutos (300 segundos)

-- Menor que (<)
SELECT titulo, numero_faixas
FROM album
WHERE numero_faixas < 10; -- Álbuns compactos

-- Maior ou igual (>=)
SELECT nome_usuario, data_nascimento
FROM usuario
WHERE data_nascimento >= DATE '1990-01-01'; -- Nascidos em 1990 ou depois

-- Menor ou igual (<=)
SELECT titulo, data_lancamento
FROM album
WHERE data_lancamento <= DATE '2000-12-31'; -- Lançados até 2000
```

**Importância Acadêmica - Teoria da Ordem:**

Operadores de magnitude implementam uma **ordem total** (≤) com propriedades matemáticas:
1. **Reflexividade**: a ≤ a (todo elemento é ≤ a si mesmo)
2. **Antissimetria**: se a ≤ b e b ≤ a, então a = b
3. **Transitividade**: se a ≤ b e b ≤ c, então a ≤ c
4. **Totalidade**: para quaisquer a e b, ou a ≤ b ou b ≤ a

Essas propriedades garantem que:
- Dados podem ser ordenados consistentemente
- Índices B-tree funcionam corretamente
- Operações de range (intervalo) são eficientes

#### 2.3 Comparação de Tipos de Dados Diferentes

**Oracle SQL Developer - Conversões Implícitas:**

Oracle tenta converter tipos automaticamente, mas isso pode causar problemas de performance:

```sql
-- ⚠️ CONVERSÃO IMPLÍCITA: Oracle converte string para número
SELECT * FROM usuario WHERE id_usuario = '123';
-- Oracle faz: WHERE id_usuario = TO_NUMBER('123')
-- Funciona, mas pode ser mais lento

-- ✅ MELHOR: Tipo correto desde o início
SELECT * FROM usuario WHERE id_usuario = 123;
```

**Armadilha com Datas:**

```sql
-- ❌ PERIGOSO: Depende do formato de sessão NLS_DATE_FORMAT
SELECT * FROM album WHERE data_lancamento = '1970-06-26';
-- Pode falhar se NLS_DATE_FORMAT não for 'YYYY-MM-DD'

-- ✅ RECOMENDADO: Usa literais DATE ou TO_DATE explícito
SELECT * FROM album WHERE data_lancamento = DATE '1970-06-26';
-- OU
SELECT * FROM album WHERE data_lancamento = TO_DATE('26/06/1970', 'DD/MM/YYYY');
```

#### 2.4 Exemplos Práticos no Sistema MusiStream

**Filtrar artistas por país**:
```sql
-- Artistas brasileiros
-- Use este exemplo exato no SQL Developer (Ctrl+Enter)
SELECT nome_artista, data_formacao, numero_membros
FROM artista
WHERE pais_origem = 'Brasil'
ORDER BY data_formacao;
```

**Por que este filtro é útil**: Permite análise regional, planejamento de marketing localizado, relatórios por mercado geográfico.

**Músicas por duração**:
```sql
-- Músicas longas (mais de 4 minutos = 240 segundos)
-- Útil para criar playlists de músicas extensas
SELECT m.titulo, 
       FLOOR(m.duracao/60) || ':' || LPAD(MOD(m.duracao, 60), 2, '0') AS duracao_formatada,
       al.titulo AS album,
       ar.nome_artista
FROM musica m
JOIN album al ON m.id_album = al.id_album
JOIN artista ar ON al.id_artista = ar.id_artista
WHERE m.duracao > 240
ORDER BY m.duracao DESC;
```

**Técnica aplicada**: 
- `FLOOR(m.duracao/60)`: Converte segundos para minutos (parte inteira)
- `MOD(m.duracao, 60)`: Obtém segundos restantes
- `LPAD(..., 2, '0')`: Formata para sempre ter 2 dígitos (ex: 5:03 em vez de 5:3)
- `||`: Operador de concatenação do Oracle

**Álbuns por período**:
```sql
-- Álbuns dos anos 60 - Era de ouro do rock
-- Exemplo de filtro por intervalo usando dois operadores
SELECT ar.nome_artista,
       al.titulo,
       al.data_lancamento,
       al.numero_faixas
FROM album al
JOIN artista ar ON al.id_artista = ar.id_artista
WHERE al.data_lancamento >= DATE '1960-01-01' 
  AND al.data_lancamento <= DATE '1969-12-31'
ORDER BY al.data_lancamento;
```

**Análise de negócio**: Este tipo de consulta permite análises históricas, identificar tendências por década, criar coleções temáticas.

**Performance**: Com índice em `data_lancamento`, Oracle usa **Index Range Scan** - muito eficiente para intervalos.

### 3. Operadores Lógicos

#### 3.1 Fundamentos de Lógica Booleana

Os **operadores lógicos** implementam conceitos da **álgebra booleana** e **lógica proposicional**, permitindo combinar múltiplas condições em expressões complexas. Eles são essenciais para expressar requisitos de negócio sofisticados.

**Por que operadores lógicos existem:**

1. **Expressão de Condições Compostas**: Requisitos reais raramente são simples - "usuários ativos E com assinatura premium E que ouviram música nos últimos 7 dias".

2. **Redução de Consultas**: Sem operadores lógicos, precisaríamos de múltiplas consultas separadas e combinar resultados na aplicação (ineficiente).

3. **Otimização de Performance**: SGBD pode otimizar condições combinadas melhor que a aplicação processando resultados separados.

4. **Modelagem de Regras de Negócio**: Sistemas empresariais têm regras complexas que exigem lógica condicional sofisticada.

**O que aconteceria sem operadores lógicos:**

```sql
-- ❌ SEM AND: Precisaríamos de duas consultas e interseção na aplicação
-- Consulta 1:
SELECT id_artista FROM artista WHERE pais_origem = 'Brasil';
-- Consulta 2:
SELECT id_artista FROM artista WHERE numero_membros > 1;
-- Na aplicação: resultado = consulta1 ∩ consulta2 (interseção)
-- Problemas: 2 round-trips ao banco, processamento na aplicação, uso de memória

-- ✅ COM AND: Uma consulta eficiente
SELECT id_artista FROM artista 
WHERE pais_origem = 'Brasil' AND numero_membros > 1;
-- Benefícios: 1 round-trip, otimização do SGBD, uso de índices compostos
```

#### 3.2 Operador AND - Conjunção Lógica

**Definição**: AND retorna TRUE apenas se TODAS as condições forem TRUE.

**Tabela verdade:**
```
A       B       A AND B
TRUE    TRUE    TRUE
TRUE    FALSE   FALSE
FALSE   TRUE    FALSE
FALSE   FALSE   FALSE
NULL    TRUE    NULL
NULL    FALSE   FALSE
NULL    NULL    NULL
```

**Nota sobre NULL**: AND com NULL pode retornar NULL (desconhecido), o que é tratado como FALSE em filtros WHERE.

```sql
-- Artistas brasileiros com mais de 1 membro (bandas)
SELECT nome_artista, pais_origem, numero_membros
FROM artista
WHERE pais_origem = 'Brasil' 
  AND numero_membros > 1;
```

**Análise acadêmica**: Este é um **produto cartesiano filtrado** das condições. Matematicamente:

```
σ(pais='Brasil' ∧ membros>1)(Artista)
```

Onde ∧ representa a conjunção lógica.

**Aplicação prática no SQL Developer:**
```sql
-- Músicas específicas: não-explícitas, duração média, álbum específico
-- Execute esta consulta completa no SQL Developer
SELECT m.titulo, m.duracao, al.titulo AS album
FROM musica m
JOIN album al ON m.id_album = al.id_album
WHERE m.duracao >= 180          -- Pelo menos 3 minutos
  AND m.duracao <= 240          -- No máximo 4 minutos
  AND m.explicita = 0;          -- Não explícita (Oracle: 0=FALSE, 1=TRUE)
```

**Por que múltiplos ANDs são úteis**: Filtros progressivamente mais refinados. Cada condição AND reduz o conjunto de resultados.

**Estratégia de otimização**: Coloque condições mais seletivas (que eliminam mais linhas) primeiro:

```sql
-- ✅ OTIMIZADO: Condição mais seletiva primeiro
SELECT * FROM musica
WHERE id_album = 5              -- Muito seletivo (poucas músicas por álbum)
  AND duracao > 180             -- Menos seletivo
  AND titulo LIKE '%Love%';     -- Ainda menos seletivo

-- vs ❌ MENOS OTIMIZADO (mesma lógica, pior performance)
SELECT * FROM musica
WHERE titulo LIKE '%Love%'      -- Muitas músicas têm "Love"
  AND duracao > 180             
  AND id_album = 5;             -- Melhor condição no final
```

**Nota**: Otimizadores modernos podem reordenar condições, mas é boa prática organizar logicamente.

#### 3.3 Operador OR - Disjunção Lógica

**Definição**: OR retorna TRUE se PELO MENOS UMA condição for TRUE.

**Tabela verdade:**
```
A       B       A OR B
TRUE    TRUE    TRUE
TRUE    FALSE   TRUE
FALSE   TRUE    TRUE
FALSE   FALSE   FALSE
NULL    TRUE    TRUE
NULL    FALSE   NULL
NULL    NULL    NULL
```

```sql
-- Artistas do Reino Unido ou Estados Unidos
SELECT nome_artista, pais_origem, data_formacao
FROM artista
WHERE pais_origem = 'Reino Unido' 
   OR pais_origem = 'Estados Unidos'
ORDER BY pais_origem, nome_artista;
```

**Por que OR é importante**: Representa **união** de conjuntos, permitindo buscar múltiplas alternativas em uma consulta.

**Análise matemática**: OR implementa união (∪):

```
σ(pais='UK')(Artista) ∪ σ(pais='USA')(Artista)
```

**Aplicação prática - Categorização temporal:**
```sql
-- Álbuns clássicos (antes de 1980) ou modernos (depois de 2010)
SELECT titulo, 
       data_lancamento, 
       CASE 
           WHEN data_lancamento < DATE '1980-01-01' THEN 'Clássico'
           WHEN data_lancamento > DATE '2010-01-01' THEN 'Moderno'
           ELSE 'Intermediário'
       END AS categoria
FROM album
WHERE data_lancamento < DATE '1980-01-01' 
   OR data_lancamento > DATE '2010-01-01'
ORDER BY data_lancamento;
```

**Cuidado com performance**: OR pode impedir uso eficiente de índices. Em alguns casos, UNION ALL é mais eficiente:

```sql
-- ⚠️ OR pode ser ineficiente com índices diferentes
SELECT * FROM usuario 
WHERE email = 'user@example.com'  -- Índice em email
   OR cpf = '12345678900';        -- Índice em cpf
-- Oracle pode não usar ambos os índices eficientemente

-- ✅ ALTERNATIVA: UNION ALL pode ser mais rápido
SELECT * FROM usuario WHERE email = 'user@example.com'
UNION ALL
SELECT * FROM usuario WHERE cpf = '12345678900' AND email <> 'user@example.com';
-- Permite uso de ambos os índices separadamente
```

#### 3.4 Operador NOT - Negação Lógica

**Definição**: NOT inverte o valor lógico - TRUE vira FALSE e vice-versa.

**Tabela verdade:**
```
A       NOT A
TRUE    FALSE
FALSE   TRUE
NULL    NULL
```

```sql
-- Artistas que não são do Brasil
SELECT nome_artista, pais_origem
FROM artista
WHERE NOT pais_origem = 'Brasil';
-- Equivalente a: WHERE pais_origem <> 'Brasil'
```

**Por que usar NOT em vez de <>?**

NOT é mais expressivo com predicados complexos:

```sql
-- Usuários sem reproduções (subconsulta com NOT EXISTS)
SELECT u.nome_usuario, u.email
FROM usuario u
WHERE NOT EXISTS (
    SELECT 1 FROM historico_reproducao h 
    WHERE h.id_usuario = u.id_usuario
);
```

**Não é possível expressar isso com <> direto** - precisamos de NOT EXISTS.

**Aplicação no SQL Developer:**
```sql
-- Artistas ativos que NÃO têm álbuns lançados nos últimos 5 anos
SELECT ar.nome_artista, ar.pais_origem
FROM artista ar
WHERE ar.ativo = 1
  AND NOT EXISTS (
      SELECT 1 FROM album al
      WHERE al.id_artista = ar.id_artista
        AND al.data_lancamento >= ADD_MONTHS(SYSDATE, -60)  -- 5 anos = 60 meses
  )
ORDER BY ar.nome_artista;
```

**Por que esta consulta é valiosa**: Identifica artistas inativos que precisam de incentivo ou limpeza do catálogo.

**Performance de NOT**: 
- `NOT IN` pode ser lento com NULL
- `NOT EXISTS` geralmente é mais eficiente
- `<>` é mais rápido que `NOT =` para comparações simples

#### 3.5 Precedência e Uso de Parênteses

**Regras de precedência** (da maior para menor prioridade):
1. Parênteses `()`
2. NOT
3. AND
4. OR

**Por que precedência importa:**

```sql
-- ❌ AMBÍGUO: Sem parênteses - como será interpretado?
SELECT nome_artista
FROM artista
WHERE pais_origem = 'Brasil' OR pais_origem = 'Portugal' AND numero_membros = 1;

-- Oracle interpreta como (devido à precedência AND > OR):
-- pais_origem = 'Brasil' OR (pais_origem = 'Portugal' AND numero_membros = 1)
-- Resultado: TODOS os artistas brasileiros + artistas portugueses solo
```

```sql
-- ✅ CLARO: Com parênteses - intenção explícita
SELECT nome_artista
FROM artista
WHERE (pais_origem = 'Brasil' OR pais_origem = 'Portugal') 
  AND numero_membros = 1;
-- Resultado: Artistas SOLO brasileiros ou portugueses
```

**Impacto na query**: Sem parênteses, podemos obter conjuntos de dados completamente diferentes!

**Exemplo acadêmico - Leis de De Morgan:**

```sql
-- NOT (A AND B) ≡ (NOT A) OR (NOT B)
-- NOT (A OR B) ≡ (NOT A) AND (NOT B)

-- Exemplo prático:
-- "Não são brasileiros E não são portugueses"
SELECT * FROM artista 
WHERE NOT (pais_origem = 'Brasil' OR pais_origem = 'Portugal');

-- Equivalente a (De Morgan):
SELECT * FROM artista 
WHERE pais_origem <> 'Brasil' AND pais_origem <> 'Portugal';
```

**Best Practice no SQL Developer:**
- **SEMPRE use parênteses** para clareza, mesmo quando não obrigatório
- Indente condições complexas para legibilidade
- Teste expressões lógicas isoladamente antes de combinar

```sql
-- ✅ ESTILO RECOMENDADO: Legível e claro
SELECT m.titulo, ar.nome_artista
FROM musica m
JOIN album al ON m.id_album = al.id_album
JOIN artista ar ON al.id_artista = ar.id_artista
WHERE (
    -- Condição 1: Música longa E rock
    (m.duracao > 300 AND m.genero = 'Rock')
    OR
    -- Condição 2: Música curta E pop
    (m.duracao < 180 AND m.genero = 'Pop')
)
AND m.explicita = 0  -- Aplicado a ambos os casos
ORDER BY m.titulo;
```

### 4. Operadores Especiais

#### 4.1 Operador IN - Lista de Valores

**Definição**: IN verifica se um valor existe em uma lista especificada de valores. É um atalho para múltiplas comparações OR.

**Por que IN existe:**

1. **Legibilidade**: `pais IN ('Brasil', 'Argentina')` é mais claro que `pais = 'Brasil' OR pais = 'Argentina'`.

2. **Manutenibilidade**: Listas longas são mais fáceis de gerenciar com IN.

3. **Otimização**: SGBDs podem otimizar IN de maneiras especiais (ex: hash lookups, bitmap indexes).

4. **Subconsultas**: IN permite comparar com resultados de outra query: `WHERE id IN (SELECT ...)`.

**O que aconteceria sem IN:**

```sql
-- ❌ SEM IN: Verboso e propenso a erros
SELECT * FROM artista 
WHERE pais_origem = 'Brasil' 
   OR pais_origem = 'Argentina'
   OR pais_origem = 'Chile'
   OR pais_origem = 'Uruguai'
   OR pais_origem = 'Paraguai';
-- Difícil de ler, fácil esquecer parenteses em condições complexas

-- ✅ COM IN: Conciso e claro
SELECT * FROM artista 
WHERE pais_origem IN ('Brasil', 'Argentina', 'Chile', 'Uruguai', 'Paraguai');
```

**Sintaxe básica:**
```sql
-- Artistas de países específicos da América do Sul
SELECT nome_artista, pais_origem
FROM artista
WHERE pais_origem IN ('Brasil', 'Reino Unido', 'Estados Unidos')
ORDER BY pais_origem, nome_artista;
```

**Equivalência formal**: 
```
x IN (a, b, c) ≡ (x = a) OR (x = b) OR (x = c)
```

**Uso com subconsultas** (muito poderoso):
```sql
-- Músicas de álbuns específicos
SELECT m.titulo, al.titulo AS album, ar.nome_artista
FROM musica m
JOIN album al ON m.id_album = al.id_album
JOIN artista ar ON al.id_artista = ar.id_artista
WHERE al.id_album IN (1, 2, 4, 7)
ORDER BY ar.nome_artista, al.titulo;

-- Com subconsulta: Músicas de artistas brasileiros
SELECT titulo, duracao
FROM musica
WHERE id_album IN (
    SELECT id_album 
    FROM album 
    WHERE id_artista IN (
        SELECT id_artista 
        FROM artista 
        WHERE pais_origem = 'Brasil'
    )
);
```

**Comportamento com NULL:**
```sql
-- ⚠️ CUIDADO: IN com NULL pode ter comportamento inesperado
SELECT * FROM artista WHERE pais_origem IN ('Brasil', NULL);
-- NULL na lista IN é ignorado - funciona como IN ('Brasil')

-- ❌ NOT IN com NULL - armadilha comum!
SELECT * FROM artista WHERE pais_origem NOT IN ('Brasil', NULL);
-- Retorna VAZIO! NOT IN com NULL sempre retorna FALSE ou NULL
-- Porque: pais <> 'Brasil' AND pais <> NULL → NULL → tratado como FALSE
```

**Solução para NOT IN com possíveis NULLs:**
```sql
-- ✅ Use NOT EXISTS em vez de NOT IN quando NULLs são possíveis
SELECT * FROM artista a
WHERE NOT EXISTS (
    SELECT 1 FROM valores_lista v
    WHERE a.pais_origem = v.pais
);
```

**Performance IN vs OR:**

Para listas pequenas (< 5 itens): Performance similar
Para listas grandes (> 10 itens): IN geralmente mais rápido

```sql
-- Oracle pode usar diferentes estratégias:
-- 1. Hash semi-join (listas grandes)
-- 2. Nested loops (listas pequenas)
-- 3. Bitmap index (se disponível)
```

#### 4.2 Operador BETWEEN - Intervalo de Valores

**Definição**: BETWEEN testa se um valor está dentro de um intervalo **inclusivo** (inclui ambos os limites).

**Por que BETWEEN existe:**

1. **Legibilidade**: `duracao BETWEEN 180 AND 300` é mais intuitivo que `duracao >= 180 AND duracao <= 300`.

2. **Semântica de Intervalo**: Expressa claramente a intenção de buscar um range.

3. **Otimização**: Alguns SGBDs reconhecem BETWEEN e aplicam otimizações específicas (Index Range Scan).

4. **Convenção SQL**: Padrão ANSI para intervalos.

**O que aconteceria sem BETWEEN:**

```sql
-- ❌ SEM BETWEEN: Mais verboso
SELECT * FROM musica 
WHERE duracao >= 180 AND duracao <= 300;

-- ✅ COM BETWEEN: Mais expressivo
SELECT * FROM musica 
WHERE duracao BETWEEN 180 AND 300;
```

**Sintaxe e exemplos:**
```sql
-- Músicas com duração entre 3 e 5 minutos
SELECT titulo, 
       duracao,
       FLOOR(duracao/60) || ':' || LPAD(MOD(duracao, 60), 2, '0') AS duracao_formatada
FROM musica
WHERE duracao BETWEEN 180 AND 300
ORDER BY duracao;
```

**Importante**: BETWEEN é **inclusivo** - inclui ambos os limites:
```
duracao BETWEEN 180 AND 300  ≡  duracao >= 180 AND duracao <= 300
```

**Uso com datas** (muito comum):
```sql
-- Álbuns lançados na década de 70
SELECT ar.nome_artista, al.titulo, al.data_lancamento
FROM album al
JOIN artista ar ON al.id_artista = ar.id_artista
WHERE al.data_lancamento BETWEEN DATE '1970-01-01' AND DATE '1979-12-31'
ORDER BY al.data_lancamento;
```

**Cuidado com timestamps (datas com horas):**
```sql
-- ⚠️ ARMADILHA: BETWEEN com timestamps
-- Se data_reproducao incluir hora/minuto/segundo:
WHERE data_reproducao BETWEEN DATE '2024-01-01' AND DATE '2024-01-31'
-- Pode perder registros do dia 31 após 00:00:00!

-- ✅ MELHOR: Seja explícito com timestamps
WHERE data_reproducao >= TIMESTAMP '2024-01-01 00:00:00'
  AND data_reproducao < TIMESTAMP '2024-02-01 00:00:00'
-- Ou use TRUNC para ignorar parte de hora
WHERE TRUNC(data_reproducao) BETWEEN DATE '2024-01-01' AND DATE '2024-01-31'
```

**Exemplo acadêmico - Análise demográfica:**
```sql
-- Usuários por faixa etária (20 a 40 anos)
SELECT nome_usuario, 
       data_nascimento,
       FLOOR(MONTHS_BETWEEN(SYSDATE, data_nascimento)/12) AS idade
FROM usuario
WHERE data_nascimento BETWEEN 
    ADD_MONTHS(SYSDATE, -40*12) AND  -- 40 anos atrás
    ADD_MONTHS(SYSDATE, -20*12)      -- 20 anos atrás
ORDER BY data_nascimento DESC;
```

**Performance BETWEEN vs operadores separados:**

```sql
-- Ambos têm performance similar
-- Oracle usa Index Range Scan para ambos (se houver índice)

-- BETWEEN
WHERE data_lancamento BETWEEN DATE '1970-01-01' AND DATE '1979-12-31'

-- Operadores separados
WHERE data_lancamento >= DATE '1970-01-01' 
  AND data_lancamento <= DATE '1979-12-31'

-- Plano de execução típico (com índice):
-- INDEX RANGE SCAN on idx_album_data_lancamento
```

**Quando NÃO usar BETWEEN:**

1. **Intervalos exclusivos** (não incluir limites):
```sql
-- Melhor usar > e < direto
WHERE preco > 100 AND preco < 500  -- Exclui 100 e 500
-- Em vez de tentar adaptar BETWEEN
```

2. **Condições assimétricas**:
```sql
-- Melhor ser explícito
WHERE duracao >= 180 AND duracao < 300  -- Inclui 180, exclui 300
```

#### 4.3 Operador LIKE - Padrões de Texto

**Definição**: LIKE realiza **pattern matching** (correspondência de padrões) em strings usando wildcards.

**Por que LIKE existe:**

1. **Buscas Parciais**: Usuários raramente sabem o texto exato - precisam buscar "artistas que começam com 'The'" ou "músicas contendo 'Love'".

2. **Flexibilidade**: Permite buscas por prefixo, sufixo, ou substring.

3. **Interface com Usuário**: Sistemas precisam implementar caixas de busca - LIKE é essencial para isso.

4. **Validação de Padrões**: Verificar se dados seguem formatos específicos (ex: telefones, emails).

**O que aconteceria sem LIKE:**

```sql
-- ❌ SEM LIKE: Impossível buscar parcialmente
SELECT * FROM artista WHERE nome_artista = 'The';  
-- Só encontra artistas com nome exatamente "The", não "The Beatles"

-- ✅ COM LIKE: Busca flexível
SELECT * FROM artista WHERE nome_artista LIKE 'The%';
-- Encontra "The Beatles", "The Rolling Stones", "The Who", etc.
```

**Wildcards básicos:**

- **%**: Corresponde a **zero ou mais** caracteres
- **_**: Corresponde a **exatamente um** caractere

```sql
-- Artistas que começam com "The"
SELECT nome_artista, pais_origem
FROM artista
WHERE nome_artista LIKE 'The%'
ORDER BY nome_artista;

-- Exemplos de padrões:
-- 'The%'    → "The Beatles", "The Who", "Therion"
-- '%The%'   → "The Beatles", "Breathe", "Northern"
-- '%The'    → "Breathe", "Soothe"
-- 'The____' → "Theories" (The + exatamente 4 caracteres)
```

**Exemplos práticos no SQL Developer:**
```sql
-- Músicas que contêm "Love"
SELECT m.titulo, ar.nome_artista, al.titulo AS album
FROM musica m
JOIN album al ON m.id_album = al.id_album
JOIN artista ar ON al.id_artista = ar.id_artista
WHERE m.titulo LIKE '%Love%'
ORDER BY ar.nome_artista;

-- Emails com domínio específico
SELECT nome_usuario, email
FROM usuario
WHERE email LIKE '%@gmail.com'
ORDER BY nome_usuario;

-- Artistas com exatamente 4 caracteres no nome
SELECT nome_artista
FROM artista
WHERE nome_artista LIKE '____'  -- 4 underscores = 4 caracteres exatos
ORDER BY nome_artista;
-- Pode encontrar: "Rush", "Blur", "ABBA", etc.
```

**Case sensitivity (sensibilidade a maiúsculas/minúsculas):**

```sql
-- Oracle: LIKE é case-sensitive por padrão (depende do NLS_SORT)
SELECT * FROM artista WHERE nome_artista LIKE 'the%';
-- Pode não encontrar "The Beatles" (T maiúsculo)

-- ✅ Solução 1: UPPER ou LOWER
SELECT * FROM artista WHERE UPPER(nome_artista) LIKE UPPER('the%');
-- Encontra independente da capitalização

-- ✅ Solução 2 (Oracle 10g+): REGEXP_LIKE com flag 'i' (insensitive)
SELECT * FROM artista WHERE REGEXP_LIKE(nome_artista, '^the', 'i');
```

**Performance de LIKE - Crítico para otimização:**

```sql
-- ✅ EFICIENTE: Pode usar índice (prefixo)
WHERE nome_artista LIKE 'Beatles%'
-- Oracle pode usar Index Range Scan

-- ⚠️ MENOS EFICIENTE: Não pode usar índice bem (sufixo)
WHERE nome_artista LIKE '%Beatles'
-- Requer Index Full Scan ou Table Full Scan

-- ❌ INEFICIENTE: Nunca pode usar índice normal (substring)
WHERE nome_artista LIKE '%Beatles%'
-- Sempre requer Full Scan
```

**Por que essa diferença de performance?**

Índices B-tree (padrão) são organizados ordenadamente:
```
"ABBA"
"Beatles"
"Queen"
"The Beatles"
"The Rolling Stones"
```

- `LIKE 'The%'`: Oracle pode pular direto para "The..." (eficiente)
- `LIKE '%The%'`: Oracle precisa verificar CADA linha (ineficiente)

**Soluções para buscas de substring eficientes:**

1. **Full-Text Search** (Oracle Text):
```sql
-- Requer setup de Oracle Text
CREATE INDEX idx_artista_nome_text ON artista(nome_artista) 
INDEXTYPE IS CTXSYS.CONTEXT;

SELECT * FROM artista WHERE CONTAINS(nome_artista, 'Beatles') > 0;
-- Muito mais rápido para buscas de texto
```

2. **Índices de Trigrama** (não nativo no Oracle, mas PostgreSQL tem):
```sql
-- Específico para PostgreSQL (não Oracle):
CREATE INDEX idx_artista_nome_trgm ON artista USING gin(nome_artista gin_trgm_ops);
```

3. **Normalização - Campos de Busca**:
```sql
-- Criar coluna adicional para busca
ALTER TABLE artista ADD nome_busca VARCHAR2(200);
UPDATE artista SET nome_busca = UPPER(REGEXP_REPLACE(nome_artista, '[^A-Z0-9]', ''));
CREATE INDEX idx_artista_busca ON artista(nome_busca);

-- Busca eficiente
WHERE nome_busca LIKE UPPER('BEATLES%');
```

**Escape de caracteres especiais:**

Se precisar buscar literalmente `%` ou `_`:

```sql
-- Buscar músicas com "100%" no título
SELECT * FROM musica 
WHERE titulo LIKE '%100\%%' ESCAPE '\';
-- Padrão: %100\%%
-- % inicial = qualquer coisa antes
-- 100\% = literal "100%"
-- % final = qualquer coisa depois
```

**Padrões mais complexos - Expressões Regulares:**

Para padrões muito complexos, use REGEXP_LIKE (mais poderoso que LIKE):

```sql
-- Álbuns que terminam com número (usando regex)
SELECT titulo, data_lancamento
FROM album
WHERE REGEXP_LIKE(titulo, '[0-9]$')  -- $ significa fim da string
ORDER BY titulo;

-- Exemplos: "Abbey Road 1969", "Album Vol. 2", etc.
```

#### 4.4 Operador IS NULL / IS NOT NULL - Tratamento de Valores Nulos

**Definição**: IS NULL/IS NOT NULL são os **únicos** operadores corretos para testar valores nulos (NULL).

**Por que NULL existe e por que precisa de operador especial:**

1. **Representação de Ausência**: NULL representa "dado ausente", "desconhecido", ou "não aplicável" - conceito essencial em bancos de dados.

2. **Lógica Ternária**: SQL usa lógica de 3 valores (TRUE, FALSE, NULL) em vez de lógica booleana binária (TRUE, FALSE).

3. **Semântica Especial**: NULL não é igual a nada, nem mesmo a si mesmo (`NULL = NULL` é NULL, não TRUE!).

**Por que não usar = NULL:**

```sql
-- ❌ INCORRETO: Sempre retorna 0 resultados (nunca TRUE)
SELECT * FROM artista WHERE biografia = NULL;
-- Retorna VAZIO porque NULL = NULL → NULL → tratado como FALSE

-- ✅ CORRETO: Usa IS NULL
SELECT * FROM artista WHERE biografia IS NULL;
-- Funciona corretamente
```

**Explicação matemática**: NULL representa "desconhecido". Dois valores desconhecidos não são necessariamente iguais:
- Sua altura = desconhecido
- Minha altura = desconhecido
- Sua altura = Minha altura? → Desconhecido (NULL), não TRUE!

**Exemplos práticos:**
```sql
-- Artistas sem biografia cadastrada
SELECT nome_artista, pais_origem, data_formacao
FROM artista
WHERE biografia IS NULL
ORDER BY nome_artista;

-- Usuários com data de nascimento cadastrada (não NULL)
SELECT nome_usuario, email, data_nascimento
FROM usuario
WHERE data_nascimento IS NOT NULL
ORDER BY data_nascimento;

-- Músicas sem letra cadastrada (pode indicar instrumentais)
SELECT m.titulo, ar.nome_artista, al.titulo AS album
FROM musica m
JOIN album al ON m.id_album = al.id_album
JOIN artista ar ON al.id_artista = ar.id_artista
WHERE m.letra IS NULL
ORDER BY ar.nome_artista, m.titulo;
```

**NULL em operações lógicas:**

```sql
-- Tabela de verdade com NULL:
SELECT 
    NULL AND TRUE as "NULL AND TRUE",      -- NULL
    NULL AND FALSE as "NULL AND FALSE",    -- FALSE (!)
    NULL OR TRUE as "NULL OR TRUE",        -- TRUE (!)
    NULL OR FALSE as "NULL OR FALSE",      -- NULL
    NOT NULL as "NOT NULL"                 -- NULL
FROM dual;
```

**Importante**: 
- `NULL AND FALSE` → FALSE (porque FALSE força o resultado)
- `NULL OR TRUE` → TRUE (porque TRUE força o resultado)

**Funções para lidar com NULL:**

```sql
-- COALESCE: Retorna primeiro valor não-NULL
SELECT nome_artista, 
       COALESCE(biografia, 'Biografia não disponível') as biografia
FROM artista;

-- NVL (Oracle específico): Substitui NULL por valor padrão
SELECT nome_artista,
       NVL(biografia, 'Sem informação') as biografia
FROM artista;

-- NVL2 (Oracle): Valor se não-NULL, outro se NULL
SELECT nome_artista,
       NVL2(biografia, 'Tem bio', 'Sem bio') as status_bio
FROM artista;
```

**Filtros combinados com NULL:**

```sql
-- Incluir NULLs explicitamente quando necessário
SELECT * FROM artista 
WHERE pais_origem = 'Brasil' OR pais_origem IS NULL;
-- Pega brasileiros E artistas sem país definido

-- Excluir NULLs (geralmente não necessário, mas pode clarificar intenção)
SELECT * FROM artista 
WHERE pais_origem = 'Brasil' AND pais_origem IS NOT NULL;
-- Redundante aqui (= já exclui NULL), mas explícito
```

**Impacto de NULL em agregações:**

```sql
-- Agregações ignoram NULL automaticamente
SELECT 
    COUNT(*) as total_artistas,           -- Conta todas as linhas
    COUNT(biografia) as com_biografia,    -- Conta apenas não-NULL
    COUNT(*) - COUNT(biografia) as sem_biografia
FROM artista;
```

**Best practices com NULL:**

1. **Use IS NULL/IS NOT NULL** sempre para testar NULL
2. **Considere NULLs em lógica**: `WHERE status <> 'Inativo'` não pega NULLs!
3. **Documente semântica**: NULL significa "desconhecido" ou "não aplicável"?
4. **Use NOT NULL constraints** quando apropriado para prevenir NULLs indesejados

### 5. Filtros Complexos e Relatórios Específicos

#### 5.1 Fundamentos de Consultas Analíticas

Filtros complexos combinam múltiplos operadores e técnicas para extrair insights específicos de negócio. Eles são essenciais para **Business Intelligence**, **relatórios gerenciais** e **análise de dados**.

**Por que filtros complexos são necessários:**

1. **Requisitos de Negócio Sofisticados**: Perguntas reais não são simples - "Quais artistas brasileiros ativos, com mais de 3 músicas, que tiveram reproduções nos últimos 30 dias?"

2. **KPIs e Métricas**: Indicadores de performance requerem agregações filtradas.

3. **Segmentação de Clientes**: Marketing precisa de grupos específicos - "usuários premium inativos nos últimos 60 dias".

4. **Análise de Tendências**: Identificar padrões requer filtros temporais e estatísticos.

**O que aconteceria sem filtros complexos:**

```sql
-- ❌ SEM FILTROS COMPLEXOS: Teria que fazer múltiplas consultas
-- Consulta 1: Todos os artistas
SELECT * FROM artista;
-- Consulta 2: Todos os álbuns
SELECT * FROM album;
-- Consulta 3: Todas as músicas
SELECT * FROM musica;
-- Na aplicação: Combinar manualmente, calcular agregações, filtrar
-- Resultado: Lento, uso excessivo de memória, código complexo

-- ✅ COM FILTROS COMPLEXOS: Uma consulta otimizada
SELECT ar.nome_artista,
       COUNT(DISTINCT al.id_album) as total_albuns,
       COUNT(m.id_musica) as total_musicas,
       ROUND(AVG(m.duracao), 2) as duracao_media
FROM artista ar
JOIN album al ON ar.id_artista = al.id_artista
JOIN musica m ON al.id_album = m.id_album
WHERE ar.ativo = 1
  AND ar.pais_origem = 'Brasil'
  AND al.data_lancamento >= ADD_MONTHS(SYSDATE, -60)  -- Últimos 5 anos
GROUP BY ar.id_artista, ar.nome_artista
HAVING COUNT(m.id_musica) > 3
ORDER BY total_musicas DESC;
```

#### 5.2 Análise de Popularidade

**Caso de uso**: Identificar artistas produtivos e relevantes para promoção ou recomendações.

```sql
-- Artistas com mais de 3 músicas no catálogo (produtivos e ativos)
SELECT ar.nome_artista,
       ar.pais_origem,
       COUNT(m.id_musica) AS total_musicas,
       ROUND(AVG(m.duracao), 2) AS duracao_media,
       MIN(al.data_lancamento) AS primeiro_album,
       MAX(al.data_lancamento) AS ultimo_album
FROM artista ar
JOIN album al ON ar.id_artista = al.id_artista
JOIN musica m ON al.id_album = m.id_album
WHERE ar.ativo = 1  -- Apenas artistas ativos (Oracle: 1=TRUE, 0=FALSE)
GROUP BY ar.id_artista, ar.nome_artista, ar.pais_origem
HAVING COUNT(m.id_musica) > 3
ORDER BY total_musicas DESC;
```

**Análise acadêmica**: Esta consulta implementa:
1. **Seleção (σ)**: WHERE ar.ativo = 1
2. **Junção (⋈)**: JOIN entre tabelas
3. **Agrupamento (γ)**: GROUP BY artista
4. **Agregação**: COUNT, AVG, MIN, MAX
5. **Filtragem pós-agregação**: HAVING

**Insights de negócio desta consulta:**
- Artistas com mais músicas são mais valiosos para o catálogo
- Duração média indica perfil musical (músicas longas = rock progressivo?)
- Intervalo de datas mostra carreira ativa vs veteranos inativos

**Por que GROUP BY e HAVING juntos:**
- **GROUP BY**: Agrupa dados (uma linha por artista)
- **HAVING**: Filtra grupos (apenas artistas com > 3 músicas)

```sql
-- Sem HAVING: Mostra todos os artistas (mesmo com 1 música)
-- Com HAVING: Apenas artistas produtivos (> 3 músicas)
```

#### 5.3 Relatório de Engajamento de Usuários

**Caso de uso**: Identificar usuários ativos para programas de fidelidade ou detectar churning.

```sql
-- Usuários ativos (com reproduções nos últimos 30 dias)
SELECT u.nome_usuario,
       u.email,
       u.pais,
       COUNT(h.id_historico) AS reproducoes_recentes,
       MAX(h.data_reproducao) AS ultima_atividade,
       MIN(h.data_reproducao) AS primeira_atividade_periodo,
       ROUND(COUNT(h.id_historico) / 30.0, 2) AS media_dia
FROM usuario u
JOIN historico_reproducao h ON u.id_usuario = h.id_usuario
WHERE h.data_reproducao >= SYSDATE - 30  -- Últimos 30 dias
  AND u.ativo = 1
GROUP BY u.id_usuario, u.nome_usuario, u.email, u.pais
HAVING COUNT(h.id_historico) >= 5  -- Pelo menos 5 reproduções
ORDER BY reproducoes_recentes DESC;
```

**Métricas calculadas:**
- `reproducoes_recentes`: Volume de uso
- `ultima_atividade`: Detectar inatividade recente
- `media_dia`: Engajamento normalizado (comparável entre usuários)

**Aplicação de negócio:**
- **Segmentação**: Usuários com > 50 reproduções/mês = "power users"
- **Retenção**: Usuários com queda recente = risco de churn
- **Marketing**: Direcionar campanhas para usuários engajados

**Variação - Detectar usuários inativos** (risco de churn):
```sql
-- Usuários que foram ativos mas estão inativos nos últimos 30 dias
SELECT u.nome_usuario,
       u.email,
       MAX(h.data_reproducao) AS ultima_reproducao,
       TRUNC(SYSDATE - MAX(h.data_reproducao)) AS dias_inativo,
       COUNT(h.id_historico) AS total_reproducoes_historico
FROM usuario u
JOIN historico_reproducao h ON u.id_usuario = h.id_usuario
WHERE u.ativo = 1
GROUP BY u.id_usuario, u.nome_usuario, u.email
HAVING MAX(h.data_reproducao) < SYSDATE - 30  -- Última atividade > 30 dias atrás
   AND COUNT(h.id_historico) > 10             -- Eram usuários engajados
ORDER BY ultima_reproducao;
```

**Por que este relatório é crítico:**
- **Retenção é mais barata que aquisição**: Re-engajar usuários inativos custa menos que conseguir novos
- **Ação proativa**: Identificar antes de cancelarem
- **Personalização**: Oferecer incentivos baseados em histórico

#### 5.4 Catálogo por Características

**Caso de uso**: Curadoria de conteúdo, criação de playlists temáticas, análise de catálogo.

```sql
-- Álbuns compactos e recentes (bom para playlists rápidas)
SELECT ar.nome_artista,
       al.titulo,
       TO_CHAR(al.data_lancamento, 'DD/MM/YYYY') AS data_lancamento,
       al.numero_faixas,
       ROUND(al.duracao_total/60, 2) AS duracao_minutos,
       CASE 
           WHEN al.numero_faixas <= 10 THEN 'EP/Compacto'
           WHEN al.numero_faixas <= 15 THEN 'Álbum Médio'
           ELSE 'Álbum Longo'
       END AS tipo_album
FROM album al
JOIN artista ar ON al.id_artista = ar.id_artista
WHERE al.numero_faixas BETWEEN 8 AND 15           -- Compacto a médio
  AND al.data_lancamento >= DATE '2000-01-01'    -- Moderno
  AND al.duracao_total BETWEEN 1800 AND 4200     -- 30 a 70 minutos
ORDER BY al.data_lancamento DESC, ar.nome_artista;
```

**Critérios de filtro explicados:**
- `numero_faixas BETWEEN 8 AND 15`: Álbuns não muito longos (melhor para primeira audição)
- `data_lancamento >= 2000`: Modernos (produção e qualidade atuais)
- `duracao_total BETWEEN 30 AND 70 min`: Nem muito curto (EP) nem muito longo (consumir tempo)

**Classificação CASE**: Categoriza álbuns dinamicamente
- **EP/Compacto** (≤10): Lançamentos menores, singles estendidos
- **Álbum Médio** (11-15): Tamanho padrão
- **Álbum Longo** (>15): Álbuns duplos, obras extensas

**Uso prático:**
- **Playlist "Descobertas Rápidas"**: Álbuns compactos e recentes
- **Análise de catálogo**: Identificar gaps (ex: poucos álbuns recentes?)
- **Recomendações**: Sugerir para usuários com pouco tempo

#### 5.5 Análise Multi-dimensional Avançada

**Caso de uso**: Relatórios executivos, dashboards gerenciais.

```sql
-- Análise completa por país: artistas, álbuns, músicas, engajamento
SELECT 
    ar.pais_origem,
    COUNT(DISTINCT ar.id_artista) AS total_artistas,
    COUNT(DISTINCT al.id_album) AS total_albuns,
    COUNT(DISTINCT m.id_musica) AS total_musicas,
    COUNT(h.id_historico) AS total_reproducoes,
    ROUND(AVG(m.duracao), 2) AS duracao_media_musica,
    ROUND(COUNT(h.id_historico) * 1.0 / NULLIF(COUNT(DISTINCT m.id_musica), 0), 2) AS reproducoes_por_musica,
    CASE 
        WHEN COUNT(DISTINCT ar.id_artista) > 100 THEN 'Mercado Principal'
        WHEN COUNT(DISTINCT ar.id_artista) > 50 THEN 'Mercado Secundário'
        ELSE 'Mercado Emergente'
    END AS classificacao_mercado
FROM artista ar
LEFT JOIN album al ON ar.id_artista = al.id_artista
LEFT JOIN musica m ON al.id_album = m.id_album
LEFT JOIN historico_reproducao h ON m.id_musica = h.id_musica
WHERE ar.pais_origem IS NOT NULL
GROUP BY ar.pais_origem
HAVING COUNT(DISTINCT ar.id_artista) > 5  -- Apenas países com presença significativa
ORDER BY total_reproducoes DESC, total_artistas DESC;
```

**Métricas complexas:**
1. **total_artistas**: Tamanho do mercado
2. **total_albuns**, **total_musicas**: Produtividade
3. **total_reproducoes**: Popularidade/demanda
4. **reproducoes_por_musica**: Eficiência do catálogo
5. **classificacao_mercado**: Segmentação estratégica

**Técnica NULLIF**: Previne divisão por zero
```sql
NULLIF(COUNT(DISTINCT m.id_musica), 0)
-- Se COUNT = 0, retorna NULL
-- Divisão por NULL = NULL (não erro)
```

**LEFT JOIN vs INNER JOIN aqui:**
- **LEFT JOIN**: Inclui artistas mesmo sem reproduções (catálogo completo)
- **INNER JOIN**: Apenas artistas com reproduções (engajamento)

**Decisão de negócio**: Usar LEFT JOIN para ver catálogo completo, incluindo artistas não populares (oportunidades de promoção).

#### 5.6 Filtros Temporais Avançados

**Caso de uso**: Análise de tendências, sazonalidade, crescimento.

```sql
-- Comparação de engajamento: último mês vs mês anterior
SELECT 
    'Último Mês' AS periodo,
    COUNT(DISTINCT id_usuario) AS usuarios_ativos,
    COUNT(id_historico) AS total_reproducoes,
    ROUND(COUNT(id_historico) * 1.0 / COUNT(DISTINCT id_usuario), 2) AS reproducoes_por_usuario
FROM historico_reproducao
WHERE data_reproducao >= TRUNC(ADD_MONTHS(SYSDATE, -1), 'MM')  -- Primeiro dia do mês passado
  AND data_reproducao < TRUNC(SYSDATE, 'MM')                    -- Primeiro dia do mês atual

UNION ALL

SELECT 
    'Mês Anterior' AS periodo,
    COUNT(DISTINCT id_usuario) AS usuarios_ativos,
    COUNT(id_historico) AS total_reproducoes,
    ROUND(COUNT(id_historico) * 1.0 / COUNT(DISTINCT id_usuario), 2) AS reproducoes_por_usuario
FROM historico_reproducao
WHERE data_reproducao >= TRUNC(ADD_MONTHS(SYSDATE, -2), 'MM')  -- Primeiro dia de 2 meses atrás
  AND data_reproducao < TRUNC(ADD_MONTHS(SYSDATE, -1), 'MM')   -- Primeiro dia do mês passado

ORDER BY periodo DESC;
```

**Função TRUNC com 'MM'**: Trunca data para o primeiro dia do mês
```sql
TRUNC(DATE '2024-03-15', 'MM') → DATE '2024-03-01'
```

**UNION ALL**: Combina resultados de múltiplas consultas
- **UNION ALL**: Mais rápido, mantém duplicatas
- **UNION**: Remove duplicatas (mais lento)

Aqui usamos UNION ALL porque períodos são distintos (sem duplicatas possíveis).

### 6. Otimização de Filtros

#### 6.1 Fundamentos de Otimização de Consultas

**Por que otimização importa:**

Em um banco de dados de produção com milhões de registros:
- **Consulta não otimizada**: 30 segundos, 90% CPU, aplicação trava
- **Consulta otimizada**: 0.5 segundos, 10% CPU, experiência fluida

**Diferença**: 60x mais rápido! Isso determina se uma aplicação é usável ou não.

**Princípios fundamentais:**

1. **Minimizar dados processados**: Filtrar cedo e frequentemente
2. **Usar índices efetivamente**: Permitem busca O(log n) vs O(n)
3. **Evitar operações caras**: Funções, conversões, full scans
4. **Aproveitar estatísticas**: Otimizador Oracle usa estatísticas para planos eficientes

#### 6.2 Uso de Índices - Conceito Aprofundado

**O que são índices:**

Índices são **estruturas de dados auxiliares** (geralmente B-trees) que permitem busca rápida. Analogia: índice de um livro.

- **Sem índice** (Table Full Scan): Ler todas as 1.000.000 linhas (lento)
- **Com índice** (Index Range Scan): Ler apenas 100 linhas relevantes (rápido)

**Estrutura B-tree** (índice padrão Oracle):
```
                [50]
               /    \
         [25]          [75]
        /    \        /    \
    [10,20] [30,40] [60,70] [80,90]
```

Busca O(log n): Para 1.000.000 registros, apenas ~20 comparações!

```sql
-- ✅ BOM: Filtra por coluna indexada (id_artista é PRIMARY KEY = índice automático)
SELECT * FROM artista WHERE id_artista = 5;
-- Plano de execução: INDEX UNIQUE SCAN (muito rápido)

-- ❌ LENTO: Filtra por coluna não indexada
SELECT * FROM artista WHERE biografia LIKE '%rock%';
-- Plano de execução: TABLE FULL SCAN (lê todas as linhas)

-- ✅ Solução: Criar índice (se consulta é frequente)
CREATE INDEX idx_artista_biografia ON artista(biografia);
-- Cuidado: Índice em LIKE '%...%' não ajuda muito!
-- Para texto, considere Oracle Text Index
```

**Verificar uso de índice no SQL Developer:**

1. Selecione a consulta
2. Pressione **F10** (Explain Plan)
3. Procure por:
   - `INDEX RANGE SCAN` / `INDEX UNIQUE SCAN` ✅ (bom)
   - `TABLE FULL SCAN` ❌ (ruim para tabelas grandes)

**Quando NÃO criar índice:**

- Tabelas pequenas (< 10.000 linhas): Full scan pode ser mais rápido
- Colunas raramente filtradas
- Colunas com poucos valores distintos (ex: genero M/F - baixa cardinalidade)
- Inserções muito frequentes: Índices tornam INSERT mais lento

#### 6.3 Ordem das Condições e Seletividade

**Conceito de seletividade**: Percentual de linhas que uma condição retém.

- **Alta seletividade** (< 5% das linhas): Bom para filtrar cedo
- **Baixa seletividade** (> 50% das linhas): Filtra poucas linhas, melhor depois

```sql
-- ✅ BOM: Condição mais seletiva primeiro
SELECT * FROM musica 
WHERE id_album = 1              -- Alta seletividade (ex: 10 músicas de 100.000)
  AND duracao > 180;            -- Baixa seletividade (ex: 50% das músicas)

-- Razão: Oracle processa WHERE da esquerda para direita (historicamente)
-- Reduz conjunto cedo: 100.000 → 10 → 5 (aplicando filtros progressivamente)

-- ❌ MENOS EFICIENTE (mesma lógica, ordem subótima)
SELECT * FROM musica 
WHERE duracao > 180             -- Baixa seletividade (50.000 linhas)
  AND id_album = 1;             -- Alta seletividade (10 linhas)

-- Processa: 100.000 → 50.000 → 10 (mais dados intermediários)
```

**Nota**: Otimizadores modernos (Oracle 12c+) podem reordenar condições automaticamente, MAS:
- Não garantido em todas as versões
- Depende de estatísticas atualizadas
- Melhor ser explícito para clareza e portabilidade

**Como identificar seletividade:**

```sql
-- Calcular seletividade de filtros
SELECT 
    COUNT(*) AS total_linhas,
    COUNT(*) FILTER (WHERE id_album = 1) AS filtro1_linhas,
    ROUND(COUNT(*) FILTER (WHERE id_album = 1) * 100.0 / COUNT(*), 2) AS filtro1_percent,
    COUNT(*) FILTER (WHERE duracao > 180) AS filtro2_linhas,
    ROUND(COUNT(*) FILTER (WHERE duracao > 180) * 100.0 / COUNT(*), 2) AS filtro2_percent
FROM musica;

-- Nota: FILTER cláusula é SQL:2003+, alternativa Oracle:
SELECT 
    COUNT(*) AS total,
    SUM(CASE WHEN id_album = 1 THEN 1 ELSE 0 END) AS filtro1_linhas,
    ROUND(SUM(CASE WHEN id_album = 1 THEN 1 ELSE 0 END) * 100.0 / COUNT(*), 2) AS filtro1_percent
FROM musica;
```

#### 6.4 Evitar Funções em WHERE - Impacto Crítico

**Problema**: Aplicar função a coluna filtrada impede uso de índice.

```sql
-- ❌ LENTO: Função na coluna (sargable = Search ARGument ABLE - violado!)
SELECT * FROM album 
WHERE EXTRACT(YEAR FROM data_lancamento) = 1970;
-- Oracle precisa aplicar EXTRACT a CADA linha (Table Full Scan)
-- Índice em data_lancamento NÃO pode ser usado!

-- ✅ RÁPIDO: Comparação direta (sargable!)
SELECT * FROM album 
WHERE data_lancamento >= DATE '1970-01-01' 
  AND data_lancamento < DATE '1971-01-01';
-- Índice em data_lancamento PODE ser usado (Index Range Scan)
-- Performance: 100x+ mais rápido em tabelas grandes!
```

**Por que índice não funciona com funções:**

Índice armazena:
```
DATE '1970-06-26' → rowid
DATE '1971-03-15' → rowid
...
```

Quando fazemos `WHERE EXTRACT(YEAR FROM data) = 1970`, Oracle precisa:
1. Ler cada data do índice ou tabela
2. Aplicar EXTRACT()
3. Comparar com 1970

Não pode "pular" direto para 1970!

Com `WHERE data >= '1970-01-01' AND data < '1971-01-01'`, Oracle:
1. Usa índice para ir direto para '1970-01-01'
2. Lê sequencialmente até '1971-01-01'
3. Para (Range Scan eficiente)

**Outros exemplos comuns:**

```sql
-- ❌ EVITAR: Função em coluna
WHERE UPPER(nome_artista) = 'THE BEATLES'
WHERE SUBSTR(email, 1, 5) = 'admin'
WHERE TRUNC(data_cadastro) = DATE '2024-01-01'

-- ✅ ALTERNATIVAS:
-- 1. Normalizar dados (armazenar uppercase)
ALTER TABLE artista ADD nome_artista_upper VARCHAR2(200);
UPDATE artista SET nome_artista_upper = UPPER(nome_artista);
CREATE INDEX idx_artista_nome_upper ON artista(nome_artista_upper);
WHERE nome_artista_upper = 'THE BEATLES';

-- 2. Índice funcional (Oracle 8i+)
CREATE INDEX idx_artista_nome_upper ON artista(UPPER(nome_artista));
WHERE UPPER(nome_artista) = 'THE BEATLES';  -- Agora pode usar o índice funcional!

-- 3. Evitar função (quando possível)
WHERE email LIKE 'admin%'
WHERE data_cadastro >= TRUNC(DATE '2024-01-01') 
  AND data_cadastro < TRUNC(DATE '2024-01-01') + 1
```

**Trade-offs de índices funcionais:**
- **Vantagem**: Permitem filtros com funções serem eficientes
- **Desvantagem**: Consomem espaço extra, tornam INSERT/UPDATE mais lentos

#### 6.5 Uso do SQL Developer para Análise de Performance

**Ferramentas built-in:**

1. **Explain Plan (F10)**:
```sql
-- Selecione a consulta e pressione F10
SELECT * FROM musica WHERE id_album = 1 AND duracao > 180;

-- Analise o plano:
-- Operation               | Object Name         | Cost
-- ----------------------- | ------------------- | -----
-- SELECT STATEMENT        |                     | 10
--   TABLE ACCESS BY INDEX | MUSICA              | 10
--     INDEX RANGE SCAN    | IDX_MUSICA_ALBUM    | 2
```

- **Cost**: Estimativa de custo (menor = melhor)
- **INDEX RANGE SCAN**: Ótimo! Usando índice.
- **TABLE FULL SCAN**: Alerta! Pode ser lento para tabelas grandes.

2. **Autotrace**:
```sql
SET AUTOTRACE ON;
SELECT * FROM artista WHERE pais_origem = 'Brasil';
-- Mostra estatísticas: linhas processadas, blocos lidos, etc.
```

3. **SQL Tuning Advisor** (versões Enterprise):
```sql
-- Gera recomendações automáticas
-- Menu: Tools → SQL Tuning Advisor
```

**Best Practices:**

1. **Sempre analise plano ANTES de otimizar**: Não adivinhe, meça!
2. **Atualize estatísticas regularmente**:
```sql
EXEC DBMS_STATS.GATHER_TABLE_STATS(USER, 'MUSICA');
```
3. **Teste com volume real**: Consulta em 100 linhas vs 10M é completamente diferente!

### 7. Exercícios Práticos

Esta seção fornece exercícios progressivos para consolidar o aprendizado de filtros e operadores SQL. Execute-os no Oracle SQL Developer seguindo as instruções anteriores.

#### 7.1 Exercícios Básicos - Operadores de Comparação

**Exercício 1**: Liste todos os artistas brasileiros ordenados por nome.
```sql
-- Sua solução aqui
SELECT nome_artista, pais_origem
FROM artista
WHERE pais_origem = 'Brasil'
ORDER BY nome_artista;
```

**Exercício 2**: Encontre músicas com duração maior que 5 minutos (300 segundos).
```sql
-- Sua solução aqui
SELECT titulo, duracao
FROM musica
WHERE duracao > 300
ORDER BY duracao DESC;
```

**Exercício 3**: Liste álbuns lançados antes de 1980.
```sql
-- Sua solução aqui
SELECT titulo, data_lancamento
FROM album
WHERE data_lancamento < DATE '1980-01-01'
ORDER BY data_lancamento;
```

#### 7.2 Exercícios Intermediários - Operadores Lógicos

**Exercício 4**: Encontre artistas brasileiros OU argentinos com mais de 2 membros.
```sql
-- Sua solução aqui
SELECT nome_artista, pais_origem, numero_membros
FROM artista
WHERE (pais_origem = 'Brasil' OR pais_origem = 'Argentina')
  AND numero_membros > 2
ORDER BY pais_origem, nome_artista;
```

**Exercício 5**: Liste músicas não-explícitas com duração entre 3 e 4 minutos.
```sql
-- Sua solução aqui
SELECT titulo, duracao, explicita
FROM musica
WHERE explicita = 0
  AND duracao BETWEEN 180 AND 240
ORDER BY duracao;
```

**Exercício 6**: Encontre usuários ativos de países específicos (Brasil, Portugal, Angola).
```sql
-- Sua solução aqui
SELECT nome_usuario, pais, ativo
FROM usuario
WHERE ativo = 1
  AND pais IN ('Brasil', 'Portugal', 'Angola')
ORDER BY pais, nome_usuario;
```

#### 7.3 Exercícios Avançados - Operadores Especiais

**Exercício 7**: Encontre artistas cujo nome começa com "The" ou termina com "Band".
```sql
-- Sua solução aqui
SELECT nome_artista
FROM artista
WHERE nome_artista LIKE 'The%'
   OR nome_artista LIKE '%Band'
ORDER BY nome_artista;
```

**Exercício 8**: Liste álbuns sem descrição (campo NULL) de artistas ativos.
```sql
-- Sua solução aqui
SELECT al.titulo, ar.nome_artista, al.descricao
FROM album al
JOIN artista ar ON al.id_artista = ar.id_artista
WHERE al.descricao IS NULL
  AND ar.ativo = 1
ORDER BY ar.nome_artista, al.titulo;
```

**Exercício 9**: Encontre usuários cadastrados na década de 2020 (2020-2029).
```sql
-- Sua solução aqui
SELECT nome_usuario, 
       TO_CHAR(data_cadastro, 'DD/MM/YYYY') AS data_cadastro
FROM usuario
WHERE data_cadastro BETWEEN DATE '2020-01-01' AND DATE '2029-12-31'
ORDER BY data_cadastro;
```

#### 7.4 Exercícios Complexos - Filtros Compostos

**Exercício 10**: Relatório de artistas produtivos: brasileiros, ativos, com pelo menos 2 álbuns e 10 músicas.
```sql
-- Sua solução aqui
SELECT ar.nome_artista,
       COUNT(DISTINCT al.id_album) AS total_albuns,
       COUNT(m.id_musica) AS total_musicas
FROM artista ar
JOIN album al ON ar.id_artista = al.id_artista
JOIN musica m ON al.id_album = m.id_album
WHERE ar.pais_origem = 'Brasil'
  AND ar.ativo = 1
GROUP BY ar.id_artista, ar.nome_artista
HAVING COUNT(DISTINCT al.id_album) >= 2
   AND COUNT(m.id_musica) >= 10
ORDER BY total_musicas DESC;
```

**Exercício 11**: Análise de engajamento: usuários brasileiros ativos nos últimos 15 dias com mais de 10 reproduções.
```sql
-- Sua solução aqui
SELECT u.nome_usuario,
       u.email,
       COUNT(h.id_historico) AS reproducoes,
       MAX(h.data_reproducao) AS ultima_atividade
FROM usuario u
JOIN historico_reproducao h ON u.id_usuario = h.id_usuario
WHERE u.pais = 'Brasil'
  AND u.ativo = 1
  AND h.data_reproducao >= SYSDATE - 15
GROUP BY u.id_usuario, u.nome_usuario, u.email
HAVING COUNT(h.id_historico) > 10
ORDER BY reproducoes DESC;
```

**Exercício 12**: Catálogo premium: álbuns recentes (últimos 3 anos) de artistas internacionais (não Brasil) com avaliação alta.
```sql
-- Sua solução aqui
SELECT ar.nome_artista,
       ar.pais_origem,
       al.titulo,
       TO_CHAR(al.data_lancamento, 'DD/MM/YYYY') AS lancamento,
       al.avaliacao_media
FROM album al
JOIN artista ar ON al.id_artista = ar.id_artista
WHERE ar.pais_origem <> 'Brasil'
  AND ar.pais_origem IS NOT NULL
  AND al.data_lancamento >= ADD_MONTHS(SYSDATE, -36)  -- 3 anos
  AND al.avaliacao_media >= 4.0
ORDER BY al.avaliacao_media DESC, al.data_lancamento DESC;
```

#### 7.5 Desafios de Otimização

**Desafio 1**: Otimize esta consulta lenta:
```sql
-- Consulta original (LENTA)
SELECT * FROM musica
WHERE UPPER(titulo) LIKE '%LOVE%'
  OR YEAR(data_criacao) = 2020;

-- Sua solução otimizada aqui:
-- Dica 1: Evite funções em WHERE
-- Dica 2: Considere usar índices funcionais ou normalização
-- Dica 3: Separe condições OR quando possível
```

**Solução sugerida**:
```sql
-- Opção 1: Usando UNION ALL (pode ser mais rápido)
SELECT * FROM musica
WHERE titulo LIKE '%Love%'
   OR titulo LIKE '%LOVE%'
   OR titulo LIKE '%love%'
UNION ALL
SELECT * FROM musica
WHERE data_criacao >= DATE '2020-01-01'
  AND data_criacao < DATE '2021-01-01'
  AND titulo NOT LIKE '%Love%'
  AND titulo NOT LIKE '%LOVE%'
  AND titulo NOT LIKE '%love%';

-- Opção 2: Criar índice funcional
CREATE INDEX idx_musica_titulo_upper ON musica(UPPER(titulo));
SELECT * FROM musica
WHERE UPPER(titulo) LIKE '%LOVE%'
   OR (data_criacao >= DATE '2020-01-01' 
       AND data_criacao < DATE '2021-01-01');
```

**Desafio 2**: Compare performance de NOT IN vs NOT EXISTS:
```sql
-- Versão 1: NOT IN
SELECT nome_artista
FROM artista
WHERE id_artista NOT IN (
    SELECT id_artista FROM album WHERE data_lancamento >= DATE '2020-01-01'
);

-- Versão 2: NOT EXISTS
SELECT ar.nome_artista
FROM artista ar
WHERE NOT EXISTS (
    SELECT 1 FROM album al
    WHERE al.id_artista = ar.id_artista
      AND al.data_lancamento >= DATE '2020-01-01'
);

-- Execute ambas com F10 (Explain Plan) e compare:
-- 1. Cost
-- 2. Tipo de operação (HASH vs NESTED LOOPS)
-- 3. Tempo de execução real
```

#### 7.6 Projeto Prático Final

**Projeto**: Criar um relatório executivo completo do sistema MusiStream.

**Requisitos**:
1. Estatísticas por país (artistas, álbuns, músicas, reproduções)
2. Top 10 artistas mais ouvidos no último mês
3. Usuários em risco de churn (inativos há mais de 30 dias mas eram ativos)
4. Álbuns lançados recentemente sem reproduções (oportunidade de promoção)
5. Taxa de engajamento por tipo de assinatura

**Estrutura sugerida**:
```sql
-- RELATÓRIO EXECUTIVO MUSISTREAM
-- Data de geração: [SYSDATE]

-- Seção 1: Visão Geral do Catálogo
SELECT 'Catálogo Global' AS secao,
       COUNT(DISTINCT ar.id_artista) AS total_artistas,
       COUNT(DISTINCT al.id_album) AS total_albuns,
       COUNT(DISTINCT m.id_musica) AS total_musicas
FROM artista ar
LEFT JOIN album al ON ar.id_artista = al.id_artista
LEFT JOIN musica m ON al.id_album = m.id_album;

-- Seção 2: Estatísticas por País
-- [Seu código aqui]

-- Seção 3: Top Artistas
-- [Seu código aqui]

-- Seção 4: Análise de Churn
-- [Seu código aqui]

-- Seção 5: Oportunidades de Promoção
-- [Seu código aqui]

-- Seção 6: Engajamento por Assinatura
-- [Seu código aqui]
```

**Critérios de avaliação**:
- Uso correto de filtros e operadores (WHERE, HAVING)
- Otimização (ordem de condições, evitar funções)
- Clareza e legibilidade do código
- Insights de negócio relevantes
- Performance (use Explain Plan para verificar)

Consulte também a pasta `exercicios/` para atividades adicionais e soluções detalhadas.

## Perguntas e Respostas

### 1. Qual a diferença entre operadores de comparação = e LIKE?

**Resposta**:
**Operador = (igualdade exata)**:
```sql
SELECT * FROM artista WHERE nome_artista = 'The Beatles';
```
- Busca correspondência exata
- Case-sensitive na maioria dos SGBDs
- Mais eficiente que LIKE
- Pode usar índices otimamente

**Operador LIKE (correspondência de padrão)**:
```sql
SELECT * FROM artista WHERE nome_artista LIKE 'The%';
```
- Permite wildcards: % (qualquer sequência), _ (um caractere)
- Útil para buscas parciais
- Menos eficiente, especialmente com % no início
- Índices são menos eficazes

**Quando usar cada um**: = para buscas exatas, LIKE para padrões ou buscas parciais.

### 2. Como otimizar consultas com múltiplas condições WHERE?

**Resposta**: Estratégias de otimização:

**Ordem das condições**: Mais seletiva primeiro
```sql
-- ✅ Melhor: condição mais seletiva primeiro
SELECT * FROM musica 
WHERE id_album = 123           -- Muito seletivo
  AND duracao > 180           -- Menos seletivo
  AND titulo LIKE '%love%';   -- Menos seletivo ainda
```

**Uso de índices compostos**:
```sql
-- Criar índice para consulta frequente
CREATE INDEX idx_musica_album_duracao ON musica(id_album, duracao);
```

**Evitar funções em WHERE**:
```sql
-- ❌ Evitar: função impede uso de índice
WHERE UPPER(titulo) = 'HELP!'

-- ✅ Melhor: usar dados já normalizados ou índice funcional
WHERE titulo = 'Help!'
```

### 3. Quando usar IN vs. EXISTS vs. JOIN?

**Resposta**: Escolha baseada no contexto:

**IN**: Para listas pequenas de valores
```sql
-- Para poucos valores conhecidos
SELECT * FROM artista WHERE pais_origem IN ('Brasil', 'Argentina', 'Chile');
```
- **Vantagem**: Simples para listas pequenas
- **Desvantagem**: Performance degrada com listas grandes

**EXISTS**: Para verificar existência em subconsulta
```sql
-- Verificar se artista tem álbuns
SELECT * FROM artista a
WHERE EXISTS (SELECT 1 FROM album WHERE id_artista = a.id_artista);
```
- **Vantagem**: Para lógica de existência, para quando retorna TRUE/FALSE
- **Performance**: Boa para subconsultas correlacionadas

**JOIN**: Para combinar dados
```sql
-- Quando precisar de dados de ambas as tabelas
SELECT a.nome_artista, al.titulo 
FROM artista a
JOIN album al ON a.id_artista = al.id_artista;
```
- **Vantagem**: Melhor performance para grandes volumes, dados de múltiplas tabelas

### 4. Como usar operadores lógicos AND, OR, NOT eficientemente?

**Resposta**: 
**Precedência e parênteses**:
```sql
-- ❌ Ambíguo
SELECT * FROM musica WHERE duracao > 180 OR genero = 'Rock' AND avaliacao > 4;

-- ✅ Claro com parênteses
SELECT * FROM musica WHERE duracao > 180 OR (genero = 'Rock' AND avaliacao > 4);
```

**Distribuição de condições**:
```sql
-- Para consultas complexas, considere separar
SELECT * FROM musica 
WHERE (genero = 'Rock' AND duracao > 240)
   OR (genero = 'Pop' AND avaliacao >= 4)
   OR (artista_id IN (1, 2, 3));
```

**NOT com cuidado**:
```sql
-- ❌ Pode ser ineficiente
WHERE NOT genero = 'Rock'

-- ✅ Melhor usar operador direto quando possível
WHERE genero <> 'Rock'
```

### 5. Qual a diferença prática entre BETWEEN e operadores >= <=?

**Resposta**:
**BETWEEN** (inclusivo):
```sql
SELECT * FROM album WHERE data_lancamento BETWEEN '1960-01-01' AND '1969-12-31';
-- Equivale a: >= '1960-01-01' AND <= '1969-12-31'
```

**Operadores separados**:
```sql
SELECT * FROM album WHERE data_lancamento >= '1960-01-01' 
                     AND data_lancamento <= '1969-12-31';
```

**Considerações**:
- BETWEEN é mais legível para intervalos
- Operadores separados dão mais controle (ex: excluir limites)
- Performance é praticamente idêntica
- Cuidado: BETWEEN inclui ambos os limites

**Para datas**: Cuidado com componente de hora em timestamps.

### 6. Como tratar valores NULL em filtros eficientemente?

**Resposta**: 
**IS NULL vs. IS NOT NULL**:
```sql
-- ✅ Correto
SELECT * FROM artista WHERE biografia IS NULL;
SELECT * FROM artista WHERE biografia IS NOT NULL;

-- ❌ Incorreto: sempre retorna FALSE
SELECT * FROM artista WHERE biografia = NULL;
```

**COALESCE para valor padrão**:
```sql
SELECT nome_artista, COALESCE(biografia, 'Biografia não disponível') as bio
FROM artista;
```

**Filtros com NULL**:
```sql
-- Incluir NULLs explicitamente quando necessário
SELECT * FROM artista 
WHERE pais_origem = 'Brasil' OR pais_origem IS NULL;
```

### 7. Como usar wildcards no LIKE de forma eficiente?

**Resposta**: 
**Padrões eficientes**:
```sql
-- ✅ Eficiente: pode usar índice
WHERE titulo LIKE 'Beatles%'    -- Começa com 'Beatles'

-- ⚠️ Menos eficiente: pode não usar índice bem
WHERE titulo LIKE '%Beatles'    -- Termina com 'Beatles'

-- ❌ Ineficiente: não pode usar índice
WHERE titulo LIKE '%Beatles%'   -- Contém 'Beatles'
```

**Alternativas para buscas por conteúdo**:
- **Full-text search**: Para busca em texto
- **Índices de trigrama**: PostgreSQL para padrões %...%
- **Normalização**: Campos separados para busca comum

**Case sensitivity**:
```sql
-- Para busca insensível a caso (quando necessário)
WHERE UPPER(titulo) LIKE UPPER('%beatles%')
-- Ou usar REGEXP_LIKE com flag 'i' (Oracle)
WHERE REGEXP_LIKE(titulo, 'beatles', 'i')
```

## 💼 Melhores Práticas para Oracle SQL Developer

### Checklist de Boas Práticas

#### Ao Escrever Consultas:
- ✅ Use literais DATE explícitos: `DATE '2024-01-01'` em vez de strings
- ✅ Sempre use parênteses em condições OR complexas
- ✅ Coloque condições mais seletivas primeiro no WHERE
- ✅ Use IS NULL/IS NOT NULL para testar valores nulos (nunca `= NULL`)
- ✅ Prefira BETWEEN para intervalos quando inclusivo
- ✅ Use IN para listas de valores conhecidos
- ✅ Evite funções em colunas filtradas (use índices funcionais se necessário)
- ✅ Comente consultas complexas para documentar intenção

#### Ao Executar no SQL Developer:
- ✅ Configure ambiente antes: `SET SERVEROUTPUT ON`, `ALTER SESSION SET NLS_DATE_FORMAT`
- ✅ Use **Ctrl+Enter** para consultas SELECT individuais (resultado em grade)
- ✅ Use **F10** (Explain Plan) para verificar performance antes de executar
- ✅ Use **F5** (Run Script) para múltiplos comandos ou blocos PL/SQL
- ✅ Verifique aba "Explain Plan" para identificar Table Full Scans
- ✅ Salve consultas úteis como snippets para reutilização
- ✅ Use **Ctrl+Shift+F** para formatar código automaticamente

#### Otimização e Performance:
- ✅ Atualize estatísticas: `EXEC DBMS_STATS.GATHER_TABLE_STATS(USER, 'tabela')`
- ✅ Crie índices em colunas frequentemente filtradas
- ✅ Use índices compostos para consultas com múltiplas condições
- ✅ Considere índices funcionais para filtros com funções inevitáveis
- ✅ Monitore planos de execução para detectar regressões
- ✅ Teste consultas com volume de dados realista
- ✅ Use EXPLAIN PLAN antes de executar consultas caras

### Padrões de Código SQL

**Template para consultas analíticas:**
```sql
-- ============================================
-- Descrição: [Objetivo da consulta]
-- Autor: [Seu nome]
-- Data: [Data de criação]
-- Performance: [Tempo esperado / Volume de dados]
-- ============================================

SELECT 
    -- Colunas principais
    coluna1,
    coluna2,
    
    -- Agregações
    COUNT(*) AS total,
    ROUND(AVG(valor), 2) AS media,
    
    -- Classificações
    CASE 
        WHEN condicao1 THEN 'Categoria 1'
        WHEN condicao2 THEN 'Categoria 2'
        ELSE 'Outros'
    END AS categoria
    
FROM tabela_principal tp
-- JOINs organizados hierarquicamente
JOIN tabela_relacionada tr ON tp.id = tr.id_principal
LEFT JOIN tabela_opcional to ON tp.id = to.id_principal

WHERE 
    -- Filtros mais seletivos primeiro
    tp.id_especifico = 123                    -- Alta seletividade
    AND tp.data >= ADD_MONTHS(SYSDATE, -30)  -- Média seletividade
    AND tp.status IN ('ATIVO', 'PENDENTE')   -- Baixa seletividade
    AND tr.campo IS NOT NULL                  -- Sempre no final

GROUP BY 
    coluna1,
    coluna2

HAVING 
    COUNT(*) > threshold  -- Filtros pós-agregação

ORDER BY 
    total DESC,
    coluna1;
```

### Troubleshooting Comum no SQL Developer

#### Problema: "ORA-00904: invalid identifier"
**Causa**: Nome de coluna ou tabela incorreto, ou objeto não existe no schema.
**Solução**:
```sql
-- Verificar estrutura da tabela
DESCRIBE nome_tabela;

-- Listar todas as tabelas do usuário
SELECT table_name FROM user_tables ORDER BY table_name;

-- Ver todas as colunas de uma tabela
SELECT column_name, data_type, nullable 
FROM user_tab_columns 
WHERE table_name = 'NOME_TABELA'  -- Sempre MAIÚSCULAS
ORDER BY column_id;
```

#### Problema: "ORA-01722: invalid number"
**Causa**: Tentativa de converter texto não-numérico em número.
**Solução**:
```sql
-- Use CAST ou TO_NUMBER com tratamento de erro
SELECT * FROM tabela
WHERE TO_NUMBER(REGEXP_REPLACE(campo, '[^0-9]', '')) > 100;

-- Ou filtre valores inválidos primeiro
WHERE REGEXP_LIKE(campo, '^[0-9]+$')  -- Apenas dígitos
  AND TO_NUMBER(campo) > 100;
```

#### Problema: Consulta muito lenta
**Diagnóstico**:
1. Execute **F10** (Explain Plan)
2. Procure por `TABLE FULL SCAN` em tabelas grandes
3. Verifique se há `CARTESIAN JOIN` (péssimo!)
4. Veja a coluna "Cost" - valores altos indicam problemas

**Soluções**:
```sql
-- 1. Adicionar índice
CREATE INDEX idx_tabela_coluna ON tabela(coluna_filtrada);

-- 2. Reescrever subconsulta como JOIN
-- ❌ Lento:
SELECT * FROM t1 WHERE id IN (SELECT id FROM t2 WHERE condicao);

-- ✅ Mais rápido:
SELECT DISTINCT t1.* 
FROM t1 
JOIN t2 ON t1.id = t2.id 
WHERE t2.condicao;

-- 3. Usar dicas (hints) - último recurso
SELECT /*+ INDEX(tabela idx_nome) */ * FROM tabela WHERE coluna = valor;
```

#### Problema: "ORA-01427: single-row subquery returns more than one row"
**Causa**: Subconsulta retorna múltiplas linhas onde só uma é esperada.
**Solução**:
```sql
-- ❌ Erro se subconsulta retornar > 1 linha:
SELECT * FROM artista 
WHERE pais_origem = (SELECT pais FROM paises WHERE regiao = 'América do Sul');

-- ✅ Correto - use IN:
SELECT * FROM artista 
WHERE pais_origem IN (SELECT pais FROM paises WHERE regiao = 'América do Sul');

-- ✅ Ou limite subconsulta:
SELECT * FROM artista 
WHERE pais_origem = (
    SELECT pais FROM paises WHERE regiao = 'América do Sul' AND ROWNUM = 1
);
```

### Recursos Avançados do SQL Developer

#### 1. SQL Worksheet Features
```sql
-- Bind variables (útil para testes parametrizados)
VARIABLE id_artista NUMBER;
EXEC :id_artista := 5;
SELECT * FROM artista WHERE id_artista = :id_artista;

-- Substituição de variáveis
DEFINE pais = 'Brasil';
SELECT * FROM artista WHERE pais_origem = '&pais';
UNDEFINE pais;
```

#### 2. Exportar Resultados
- Clique direito na grade de resultados → Export
- Formatos: CSV, XML, JSON, SQL INSERT, Excel
- Útil para compartilhar análises ou migrar dados

#### 3. Comparar Planos de Execução
```sql
-- Execute consulta 1
SELECT * FROM musica WHERE duracao > 180;
-- F10 → Salve plano como "Plano1"

-- Execute consulta 2 (otimizada)
SELECT * FROM musica WHERE duracao BETWEEN 181 AND 999999;
-- F10 → Compare com "Plano1"
```

#### 4. Geração de Dados de Teste
```sql
-- Gerar dados realistas para testes
INSERT INTO artista (id_artista, nome_artista, pais_origem)
SELECT 
    ROWNUM + 1000,
    'Artista Teste ' || ROWNUM,
    CASE MOD(ROWNUM, 5)
        WHEN 0 THEN 'Brasil'
        WHEN 1 THEN 'Estados Unidos'
        WHEN 2 THEN 'Reino Unido'
        WHEN 3 THEN 'Argentina'
        ELSE 'Portugal'
    END
FROM dual
CONNECT BY LEVEL <= 100;  -- Gera 100 registros de teste
```

## 📊 Resumo do Módulo

### Conceitos-Chave Aprendidos

1. **Cláusula WHERE**: Mecanismo fundamental de filtragem, essencial para:
   - Reduzir volume de dados processados (eficiência)
   - Extrair informações específicas (precisão)
   - Implementar regras de negócio (lógica)

2. **Operadores de Comparação**: Implementam relações de ordem e igualdade:
   - `=`, `<>`, `<`, `>`, `<=`, `>=`
   - Fundamentais para filtros numéricos, textuais e temporais
   - Base para uso eficiente de índices B-tree

3. **Operadores Lógicos**: Combinam condições via álgebra booleana:
   - **AND**: Conjunção (todas condições TRUE)
   - **OR**: Disjunção (pelo menos uma TRUE)
   - **NOT**: Negação (inverte valor lógico)
   - Essenciais para expressar requisitos complexos

4. **Operadores Especiais**: Atalhos e padrões comuns:
   - **IN**: Lista de valores (alternativa a múltiplos OR)
   - **BETWEEN**: Intervalos inclusivos
   - **LIKE**: Pattern matching com wildcards
   - **IS NULL/IS NOT NULL**: Tratamento de valores ausentes

5. **Otimização**: Performance crítica em bancos grandes:
   - Índices permitem acesso O(log n) vs O(n)
   - Ordem de condições afeta processamento
   - Funções em WHERE impedem uso de índices
   - Explain Plan revela problemas de performance

### Impacto no Mundo Real

**Sem filtros adequados**: Aplicações lentas, usuários insatisfeitos, recursos desperdiçados
**Com filtros otimizados**: Respostas instantâneas, escalabilidade, experiência de usuário superior

**Diferença quantificável**:
- Tempo de resposta: 30s → 0.5s (60x mais rápido)
- Uso de CPU: 90% → 10% (9x mais eficiente)
- Escalabilidade: 100 → 10.000+ usuários simultâneos

### Próximos Passos

1. **Pratique os exercícios** da Seção 7 progressivamente
2. **Experimente no SQL Developer** com dados reais do MusiStream
3. **Analise planos de execução** (F10) para entender otimizações
4. **Compare performance** de diferentes abordagens
5. **Avance para o Módulo 11** sobre operadores aritméticos

**Lembre-se**: Dominar filtros e operadores é fundamental para todo desenvolvedor de banco de dados. Pratique regularmente!

## Referências Bibliográficas

### Livros Fundamentais

1. **Beaulieu, A.** (2020). *Learning SQL: Master SQL Fundamentals*. 3rd Edition. O'Reilly Media. Capítulo 4 - "Filtering".
   - Abordagem prática de filtros e operadores
   - Exemplos didáticos progressivos
   - Cobertura de múltiplos SGBDs

2. **Forta, B.** (2018). *SQL in 10 Minutes, Sams Teach Yourself*. 5th Edition. Sams Publishing. Lições 6-9.
   - Introdução rápida e acessível
   - Foco em produtividade
   - Exemplos concisos e práticos

3. **Date, C.J.** (2012). *SQL and Relational Theory: How to Write Accurate SQL Code*. 2nd Edition. O'Reilly Media. Capítulo 6 - "Predicates and Propositions".
   - Fundamentação teórica em lógica relacional
   - Formalização matemática de operadores
   - Discussão sobre lógica ternária (TRUE/FALSE/NULL)

4. **Ramakrishnan, R. & Gehrke, J.** (2003). *Database Management Systems*. 3rd Edition. McGraw-Hill. Capítulo 5 - "SQL: Queries, Constraints, Triggers".
   - Perspectiva acadêmica completa
   - Álgebra relacional e mapeamento para SQL
   - Otimização de consultas

5. **Garcia-Molina, H., Ullman, J.D. & Widom, J.** (2008). *Database Systems: The Complete Book*. 2nd Edition. Prentice Hall.
   - Cobertura abrangente de teoria de bancos de dados
   - Algoritmos de otimização de queries
   - Estruturas de índices

### Documentação Oficial Oracle

6. **Oracle Corporation** (2021). *Oracle Database SQL Language Reference*. 
   - Seção: "SQL Queries and Subqueries - WHERE Clause"
   - Seção: "Conditions" (operadores de comparação)
   - Seção: "Pattern Matching Conditions" (LIKE, REGEXP_LIKE)
   - Disponível em: https://docs.oracle.com/en/database/oracle/oracle-database/

7. **Oracle Corporation** (2021). *Oracle Database Performance Tuning Guide*.
   - Capítulo: "Optimizer and Query Execution"
   - Seção: "Using Indexes Effectively"
   - Best practices para filtros eficientes

### Artigos Acadêmicos e Técnicos

8. **Codd, E.F.** (1970). "A Relational Model of Data for Large Shared Data Banks". *Communications of the ACM*, 13(6), 377-387.
   - Artigo seminal que definiu o modelo relacional
   - Base teórica para operações de seleção (σ)

9. **Selinger, P.G., et al.** (1979). "Access Path Selection in a Relational Database Management System". *Proceedings of SIGMOD*, pp. 23-34.
   - Algoritmo de otimização de queries (System R)
   - Fundamentos de cost-based optimization

### Recursos Online e Tutoriais

10. **Oracle LiveSQL** (https://livesql.oracle.com/)
    - Ambiente interativo para prática
    - Scripts e tutoriais da comunidade
    - Testes sem necessidade de instalação local

11. **Stack Overflow - Oracle SQL Tag** (https://stackoverflow.com/questions/tagged/oracle)
    - Resolução de problemas práticos
    - Discussões sobre otimização
    - Exemplos da comunidade

12. **Ask TOM** (https://asktom.oracle.com/)
    - Portal oficial de perguntas e respostas Oracle
    - Explicações técnicas detalhadas
    - Melhores práticas recomendadas

### Material Complementar Recomendado

13. **Celko, J.** (2010). *Joe Celko's SQL for Smarties: Advanced SQL Programming*. 4th Edition. Morgan Kaufmann.
    - Técnicas avançadas de otimização
    - Padrões de consulta complexos
    - Soluções para problemas comuns

14. **Kline, K.E., Kline, D. & Hunt, B.** (2008). *SQL in a Nutshell*. 3rd Edition. O'Reilly Media.
    - Referência rápida e abrangente
    - Comparação entre diferentes SGBDs
    - Sintaxe e exemplos práticos

### Cursos e Certificações

15. **Oracle University** - "Oracle Database: SQL Fundamentals"
    - Curso oficial Oracle para certificação
    - Cobertura completa de SQL básico e intermediário
    - Preparação para Oracle Certified Associate (OCA)

---

**Nota sobre as referências**: Este módulo combina teoria acadêmica sólida com práticas de mercado. Recomendamos começar com Beaulieu (2020) e Forta (2018) para fundamentos práticos, depois aprofundar com Date (2012) para compreensão teórica, e finalmente consultar a documentação Oracle para especificidades do SGBD.

---

**Módulo Anterior**: [09 - Controle de Transações e Criação de Relatórios](../09-controle-transacoes-relatorios/README.md)

**Próximo Módulo**: [11 - Relatórios com Operadores Aritméticos](../11-relatorios-operadores-aritmeticos/README.md)

---

## 🎯 Conclusão

Este módulo forneceu uma base sólida em **filtros e operadores SQL**, combinando:
- **Teoria acadêmica**: Lógica proposicional, álgebra relacional, teoria da ordem
- **Prática profissional**: Otimização, índices, patterns de código
- **Ferramental**: Oracle SQL Developer, Explain Plan, troubleshooting

**Impacto no desenvolvimento**:
- Consultas até **60x mais rápidas** com filtros otimizados
- Redução de **85% no uso de recursos** (CPU, memória, rede)
- Código **mais legível e manutenível** com boas práticas

**Dominar filtros e operadores** é essencial para qualquer profissional de banco de dados. Continue praticando com os exercícios propostos e aplicando os conceitos em projetos reais!

**Dica final**: Filtros bem construídos são essenciais para consultas eficientes. Sempre considere a seletividade das condições e o uso apropriado de índices. Use o Explain Plan (F10) como seu melhor amigo para verificar performance!