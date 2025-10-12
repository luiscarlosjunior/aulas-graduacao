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

#### 4.1 IN - Lista de Valores
```sql
-- Artistas de países específicos
SELECT nome_artista, pais_origem
FROM artista
WHERE pais_origem IN ('Brasil', 'Reino Unido', 'Estados Unidos')
ORDER BY pais_origem, nome_artista;

-- Músicas de álbuns específicos
SELECT m.titulo, al.titulo AS album, ar.nome_artista
FROM musica m
JOIN album al ON m.id_album = al.id_album
JOIN artista ar ON al.id_artista = ar.id_artista
WHERE al.id_album IN (1, 2, 4, 7)
ORDER BY ar.nome_artista, al.titulo;

-- Equivalente com OR (menos eficiente)
WHERE al.id_album = 1 OR al.id_album = 2 OR al.id_album = 4 OR al.id_album = 7;
```

#### 4.2 BETWEEN - Intervalo de Valores
```sql
-- Músicas com duração entre 3 e 5 minutos
SELECT titulo, duracao,
       CONCAT(FLOOR(duracao/60), ':', LPAD(duracao%60, 2, '0')) AS duracao_formatada
FROM musica
WHERE duracao BETWEEN 180 AND 300
ORDER BY duracao;

-- Álbuns lançados na década de 70
SELECT ar.nome_artista, al.titulo, al.data_lancamento
FROM album al
JOIN artista ar ON al.id_artista = ar.id_artista
WHERE al.data_lancamento BETWEEN '1970-01-01' AND '1979-12-31'
ORDER BY al.data_lancamento;

-- Usuários por faixa etária (20 a 40 anos)
SELECT nome_usuario, data_nascimento,
       FLOOR(DATEDIFF(CURRENT_DATE, data_nascimento)/365) AS idade
FROM usuario
WHERE data_nascimento BETWEEN 
    DATE_SUB(CURRENT_DATE, INTERVAL 40 YEAR) AND 
    DATE_SUB(CURRENT_DATE, INTERVAL 20 YEAR)
ORDER BY data_nascimento DESC;
```

#### 4.3 LIKE - Padrões de Texto

**Wildcards básicos**:
- `%`: Qualquer sequência de caracteres (zero ou mais)
- `_`: Exatamente um caractere

```sql
-- Artistas que começam com "The"
SELECT nome_artista, pais_origem
FROM artista
WHERE nome_artista LIKE 'The%'
ORDER BY nome_artista;

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
WHERE nome_artista LIKE '____'  -- 4 underscores
ORDER BY nome_artista;
```

**Padrões mais complexos**:
```sql
-- Álbuns que terminam com número
SELECT titulo, data_lancamento
FROM album
WHERE titulo LIKE '%[0-9]'  -- Sintaxe varia por SGBD
ORDER BY titulo;

-- Case insensitive (depende do SGBD)
SELECT nome_artista
FROM artista
WHERE UPPER(nome_artista) LIKE 'QUEEN%'
ORDER BY nome_artista;
```

#### 4.4 IS NULL / IS NOT NULL - Valores Nulos

```sql
-- Artistas sem biografia
SELECT nome_artista, pais_origem, data_formacao
FROM artista
WHERE biografia IS NULL
ORDER BY nome_artista;

-- Usuários com data de nascimento cadastrada
SELECT nome_usuario, email, data_nascimento
FROM usuario
WHERE data_nascimento IS NOT NULL
ORDER BY data_nascimento;

-- Músicas sem letra cadastrada
SELECT m.titulo, ar.nome_artista, al.titulo AS album
FROM musica m
JOIN album al ON m.id_album = al.id_album
JOIN artista ar ON al.id_artista = ar.id_artista
WHERE m.letra IS NULL
ORDER BY ar.nome_artista, m.titulo;
```

### 5. Filtros Complexos e Relatórios Específicos

#### 5.1 Análise de Popularidade
```sql
-- Artistas com mais de 3 músicas no catálogo
SELECT ar.nome_artista,
       ar.pais_origem,
       COUNT(m.id_musica) AS total_musicas,
       ROUND(AVG(m.duracao), 2) AS duracao_media
FROM artista ar
JOIN album al ON ar.id_artista = al.id_artista
JOIN musica m ON al.id_album = m.id_album
WHERE ar.ativo = TRUE
GROUP BY ar.id_artista, ar.nome_artista, ar.pais_origem
HAVING COUNT(m.id_musica) > 3
ORDER BY total_musicas DESC;
```

#### 5.2 Relatório de Engajamento de Usuários
```sql
-- Usuários ativos (com reproduções nos últimos 30 dias)
SELECT u.nome_usuario,
       u.email,
       COUNT(h.id_historico) AS reproducoes_recentes,
       MAX(h.data_reproducao) AS ultima_atividade
FROM usuario u
JOIN historico_reproducao h ON u.id_usuario = h.id_usuario
WHERE h.data_reproducao >= DATE_SUB(CURRENT_DATE, INTERVAL 30 DAY)
  AND u.ativo = TRUE
GROUP BY u.id_usuario, u.nome_usuario, u.email
HAVING reproducoes_recentes >= 5
ORDER BY reproducoes_recentes DESC;
```

#### 5.3 Catálogo por Características
```sql
-- Álbuns compactos e recentes
SELECT ar.nome_artista,
       al.titulo,
       al.data_lancamento,
       al.numero_faixas,
       ROUND(al.duracao_total/60, 2) AS duracao_minutos
FROM album al
JOIN artista ar ON al.id_artista = ar.id_artista
WHERE al.numero_faixas BETWEEN 8 AND 15
  AND al.data_lancamento >= '2000-01-01'
  AND al.duracao_total BETWEEN 1800 AND 4200  -- 30 a 70 minutos
ORDER BY al.data_lancamento DESC;
```

### 6. Otimização de Filtros

#### 6.1 Uso de Índices
```sql
-- ✅ BOM: Filtra por coluna indexada
SELECT * FROM artista WHERE id_artista = 5;

-- ❌ LENTO: Filtra por coluna não indexada
SELECT * FROM artista WHERE biografia LIKE '%rock%';

-- Solução: Criar índice se necessário
CREATE INDEX idx_artista_biografia ON artista(biografia);
```

#### 6.2 Ordem das Condições
```sql
-- ✅ BOM: Condição mais seletiva primeiro
SELECT * FROM musica 
WHERE id_album = 1          -- Mais seletivo
  AND duracao > 180;        -- Menos seletivo

-- ❌ MENOS EFICIENTE: Condição menos seletiva primeiro
SELECT * FROM musica 
WHERE duracao > 180         -- Menos seletivo
  AND id_album = 1;         -- Mais seletivo
```

#### 6.3 Evitar Funções em WHERE
```sql
-- ❌ LENTO: Função na coluna
SELECT * FROM album 
WHERE YEAR(data_lancamento) = 1970;

-- ✅ RÁPIDO: Comparação direta
SELECT * FROM album 
WHERE data_lancamento >= '1970-01-01' 
  AND data_lancamento <= '1970-12-31';
```

### 7. Exercícios Práticos

Consulte a pasta `exercicios/` para atividades que reforçam o uso de filtros e operadores.

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
-- Ou usar ILIKE (PostgreSQL)
WHERE titulo ILIKE '%beatles%'
```

## Referências Bibliográficas

1. **Beaulieu, A.** (2020). *Learning SQL: Master SQL Fundamentals*. 3rd Edition. O'Reilly Media. Capítulo 4.

2. **Forta, B.** (2018). *SQL in 10 Minutes, Sams Teach Yourself*. 5th Edition. Sams Publishing. Lições 6-9.

3. **Date, C.J.** (2012). *SQL and Relational Theory*. 2nd Edition. O'Reilly Media. Capítulo 6.

4. **Oracle Corporation** (2021). *Oracle Database SQL Language Reference*. Seção sobre WHERE Clause.

---

**Módulo Anterior**: [09 - Controle de Transações e Criação de Relatórios](../09-controle-transacoes-relatorios/README.md)
**Próximo Módulo**: [11 - Relatórios com Operadores Aritméticos](../11-relatorios-operadores-aritmeticos/README.md)

**Dica de Performance**: Filtros bem construídos são essenciais para consultas eficientes. Sempre considere a seletividade das condições e o uso apropriado de índices.