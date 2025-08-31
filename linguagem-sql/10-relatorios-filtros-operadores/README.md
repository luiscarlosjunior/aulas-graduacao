# Módulo 10 - Relatórios com Filtros e Operadores

## Objetivos de Aprendizagem

Ao final deste módulo, o aluno será capaz de:
- Utilizar a cláusula WHERE para filtrar dados
- Aplicar operadores de comparação (=, <>, <, >, <=, >=)
- Combinar condições com operadores lógicos (AND, OR, NOT)
- Usar operadores especiais (IN, BETWEEN, LIKE, IS NULL)
- Construir filtros complexos para relatórios específicos
- Otimizar consultas com filtros eficientes

## Conteúdo Teórico

### 1. Fundamentos da Cláusula WHERE

#### 1.1 Sintaxe Básica
```sql
SELECT colunas
FROM tabela
WHERE condição;
```

A cláusula WHERE permite filtrar registros baseados em condições específicas, retornando apenas as linhas que atendem aos critérios definidos.

#### 1.2 Posicionamento na Consulta
```sql
-- Ordem correta das cláusulas
SELECT colunas
FROM tabela
WHERE condição        -- Filtra linhas
GROUP BY colunas      -- Agrupa resultados
HAVING condição       -- Filtra grupos
ORDER BY colunas      -- Ordena resultado final
LIMIT número;         -- Limita quantidade
```

### 2. Operadores de Comparação

#### 2.1 Operadores Básicos

**Igualdade e Diferença**:
```sql
-- Igualdade (=)
SELECT nome_artista, pais_origem
FROM artista
WHERE pais_origem = 'Brasil';

-- Diferença (<> ou !=)
SELECT nome_artista, pais_origem
FROM artista
WHERE pais_origem <> 'Brasil';
-- Ou usando !=
WHERE pais_origem != 'Brasil';
```

**Operadores de Magnitude**:
```sql
-- Maior que (>)
SELECT titulo, duracao
FROM musica
WHERE duracao > 300; -- Músicas com mais de 5 minutos

-- Menor que (<)
SELECT titulo, numero_faixas
FROM album
WHERE numero_faixas < 10;

-- Maior ou igual (>=)
SELECT nome_usuario, data_nascimento
FROM usuario
WHERE data_nascimento >= '1990-01-01';

-- Menor ou igual (<=)
SELECT titulo, data_lancamento
FROM album
WHERE data_lancamento <= '2000-12-31';
```

#### 2.2 Exemplos Práticos no Sistema MusiStream

**Filtrar artistas por país**:
```sql
-- Artistas brasileiros
SELECT nome_artista, data_formacao, numero_membros
FROM artista
WHERE pais_origem = 'Brasil'
ORDER BY data_formacao;
```

**Músicas por duração**:
```sql
-- Músicas longas (mais de 4 minutos)
SELECT m.titulo, 
       CONCAT(FLOOR(m.duracao/60), ':', LPAD(m.duracao%60, 2, '0')) AS duracao_formatada,
       al.titulo AS album,
       ar.nome_artista
FROM musica m
JOIN album al ON m.id_album = al.id_album
JOIN artista ar ON al.id_artista = ar.id_artista
WHERE m.duracao > 240
ORDER BY m.duracao DESC;
```

**Álbuns por período**:
```sql
-- Álbuns dos anos 60
SELECT ar.nome_artista,
       al.titulo,
       al.data_lancamento,
       al.numero_faixas
FROM album al
JOIN artista ar ON al.id_artista = ar.id_artista
WHERE al.data_lancamento >= '1960-01-01' 
  AND al.data_lancamento <= '1969-12-31'
ORDER BY al.data_lancamento;
```

### 3. Operadores Lógicos

#### 3.1 AND - Múltiplas Condições Verdadeiras
```sql
-- Artistas brasileiros com mais de 1 membro
SELECT nome_artista, pais_origem, numero_membros
FROM artista
WHERE pais_origem = 'Brasil' 
  AND numero_membros > 1;

-- Músicas específicas
SELECT m.titulo, m.duracao, al.titulo AS album
FROM musica m
JOIN album al ON m.id_album = al.id_album
WHERE m.duracao >= 180 
  AND m.duracao <= 240
  AND m.explicita = FALSE;
```

#### 3.2 OR - Pelo Menos Uma Condição Verdadeira
```sql
-- Artistas do Reino Unido ou Estados Unidos
SELECT nome_artista, pais_origem, data_formacao
FROM artista
WHERE pais_origem = 'Reino Unido' 
   OR pais_origem = 'Estados Unidos'
ORDER BY pais_origem, nome_artista;

-- Álbuns antigos ou recentes
SELECT titulo, data_lancamento, 
       CASE 
           WHEN data_lancamento < '1980-01-01' THEN 'Clássico'
           WHEN data_lancamento > '2010-01-01' THEN 'Moderno'
           ELSE 'Intermediário'
       END AS categoria
FROM album
WHERE data_lancamento < '1980-01-01' 
   OR data_lancamento > '2010-01-01'
ORDER BY data_lancamento;
```

#### 3.3 NOT - Negação de Condição
```sql
-- Artistas que não são do Brasil
SELECT nome_artista, pais_origem
FROM artista
WHERE NOT pais_origem = 'Brasil';
-- Equivalente a: WHERE pais_origem <> 'Brasil'

-- Usuários sem reproduções
SELECT u.nome_usuario, u.email
FROM usuario u
WHERE NOT EXISTS (
    SELECT 1 FROM historico_reproducao h 
    WHERE h.id_usuario = u.id_usuario
);
```

#### 3.4 Precedência e Uso de Parênteses
```sql
-- ❌ AMBÍGUO: Sem parênteses
SELECT nome_artista
FROM artista
WHERE pais_origem = 'Brasil' OR pais_origem = 'Portugal' AND numero_membros = 1;

-- ✅ CLARO: Com parênteses
SELECT nome_artista
FROM artista
WHERE (pais_origem = 'Brasil' OR pais_origem = 'Portugal') 
  AND numero_membros = 1;
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