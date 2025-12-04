# Módulo 11 - Relatórios com Operadores Aritméticos

## Objetivos de Aprendizagem

Ao final deste módulo, o aluno será capaz de:
- Utilizar operadores aritméticos em consultas SQL
- Implementar cálculos e expressões matemáticas
- Trabalhar com funções matemáticas
- Criar relatórios com campos calculados
- Aplicar formatação numérica em relatórios
- Resolver problemas práticos usando matemática em SQL

## Conteúdo Teórico

### 1. Operadores Aritméticos Básicos

#### 1.1 Operadores Fundamentais
```sql
-- Adição (+), Subtração (-), Multiplicação (*), Divisão (/)
SELECT 
    titulo,
    duracao as "Duração (segundos)",
    duracao / 60 as "Duração (minutos)",
    duracao * 1.2 as "Duração + 20%",
    ROUND(duracao / 60, 2) as "Minutos (2 casas)"
FROM musica;
```

#### 1.2 Precedência de Operadores
```sql
-- Parenteses alteram precedência
SELECT 
    titulo,
    numero_faixas,
    numero_faixas * 3 + 2 as "Sem Parênteses",
    numero_faixas * (3 + 2) as "Com Parênteses",
    numero_faixas + 10 / 2 as "Divisão Primeiro",
    (numero_faixas + 10) / 2 as "Soma Primeiro"
FROM album;
```

### 2. Funções Matemáticas

#### 2.1 Funções de Arredondamento
```sql
SELECT 
    titulo,
    duracao / 60 as "Minutos Exato",
    ROUND(duracao / 60, 2) as "Arredondado 2 casas",
    CEIL(duracao / 60) as "Arredondado para Cima",
    FLOOR(duracao / 60) as "Arredondado para Baixo",
    TRUNC(duracao / 60, 1) as "Truncado 1 casa"
FROM musica;
```

#### 2.2 Funções Estatísticas
```sql
SELECT 
    nome_genero,
    COUNT(*) as "Total Músicas",
    AVG(duracao) as "Duração Média",
    MIN(duracao) as "Menor Duração",
    MAX(duracao) as "Maior Duração",
    SUM(duracao) as "Duração Total",
    STDDEV(duracao) as "Desvio Padrão",
    VARIANCE(duracao) as "Variância"
FROM musica m
JOIN genero g ON m.id_genero = g.id_genero
GROUP BY nome_genero;
```

### 3. Cálculos Financeiros no Sistema MusiStream

#### 3.1 Receita por Reprodução
```sql
-- Calcular receita baseada em reproduções
SELECT 
    a.nome_artista,
    COUNT(hr.id_historico) as "Total Reproduções",
    COUNT(hr.id_historico) * 0.001 as "Receita ($0.001/reprodução)",
    CASE 
        WHEN COUNT(hr.id_historico) >= 1000000 THEN COUNT(hr.id_historico) * 0.002
        WHEN COUNT(hr.id_historico) >= 100000 THEN COUNT(hr.id_historico) * 0.0015
        ELSE COUNT(hr.id_historico) * 0.001
    END as "Receita com Bônus"
FROM artista a
JOIN album al ON a.id_artista = al.id_artista
JOIN musica m ON al.id_album = m.id_album
JOIN historico_reproducao hr ON m.id_musica = hr.id_musica
GROUP BY a.id_artista, a.nome_artista
ORDER BY "Total Reproduções" DESC;
```

#### 3.2 Análise de Tempo de Reprodução
```sql
-- Calcular porcentagem de música ouvida
SELECT 
    m.titulo,
    m.duracao as "Duração Total",
    AVG(hr.duracao_ouvida) as "Média Ouvida",
    ROUND(AVG(hr.duracao_ouvida) / m.duracao * 100, 1) as "% Média Ouvida",
    COUNT(CASE WHEN hr.duracao_ouvida = m.duracao THEN 1 END) as "Ouvidas Completas",
    ROUND(
        COUNT(CASE WHEN hr.duracao_ouvida = m.duracao THEN 1 END) / 
        COUNT(*) * 100, 1
    ) as "% Completas"
FROM musica m
JOIN historico_reproducao hr ON m.id_musica = hr.id_musica
GROUP BY m.id_musica, m.titulo, m.duracao
HAVING COUNT(*) >= 10
ORDER BY "% Média Ouvida" DESC;
```

### 4. Expressões Condicionais com Cálculos

#### 4.1 CASE com Operadores Aritméticos
```sql
SELECT 
    nome_artista,
    numero_membros,
    CASE 
        WHEN numero_membros = 1 THEN 'Solo - Royalty 100%'
        WHEN numero_membros = 2 THEN 'Dupla - Royalty 50% cada'
        WHEN numero_membros BETWEEN 3 AND 4 THEN 'Banda Pequena - Royalty ' || ROUND(100/numero_membros, 1) || '% cada'
        WHEN numero_membros >= 5 THEN 'Banda Grande - Royalty ' || ROUND(100/numero_membros, 1) || '% cada'
        ELSE 'Indefinido'
    END as "Distribuição Royalty"
FROM artista
WHERE numero_membros IS NOT NULL;
```

### 5. Relatórios Financeiros Avançados

#### 5.1 Dashboard de Performance
```sql
SELECT 
    'Reproduções Hoje' as Métrica,
    COUNT(*) as Valor,
    COUNT(*) * 0.001 as "Receita ($)"
FROM historico_reproducao 
WHERE DATE(data_reproducao) = CURRENT_DATE

UNION ALL

SELECT 
    'Reproduções Esta Semana',
    COUNT(*),
    COUNT(*) * 0.001
FROM historico_reproducao 
WHERE data_reproducao >= CURRENT_DATE - INTERVAL 7 DAY

UNION ALL

SELECT 
    'Reproduções Este Mês',
    COUNT(*),
    COUNT(*) * 0.001
FROM historico_reproducao 
WHERE EXTRACT(MONTH FROM data_reproducao) = EXTRACT(MONTH FROM CURRENT_DATE)
  AND EXTRACT(YEAR FROM data_reproducao) = EXTRACT(YEAR FROM CURRENT_DATE);
```

## Exercícios Práticos

Consulte a pasta `exercicios` para atividades práticas que reforçam os conceitos apresentados.

## Perguntas e Respostas

### 1. Como evitar problemas de divisão por zero em cálculos SQL?

**Resposta**: Use técnicas defensivas:

**CASE para verificação**:
```sql
SELECT 
    nome_artista,
    total_reproducoes,
    total_musicas,
    CASE 
        WHEN total_musicas = 0 THEN 0
        ELSE total_reproducoes / total_musicas
    END as media_por_musica
FROM estatisticas_artista;
```

**NULLIF para converter zero em NULL**:
```sql
SELECT 
    titulo_album,
    duracao_total / NULLIF(numero_faixas, 0) as duracao_media_faixa
FROM album;
-- Se numero_faixas = 0, resultado será NULL em vez de erro
```

**COALESCE para valor padrão**:
```sql
SELECT 
    COALESCE(receita / NULLIF(numero_vendas, 0), 0) as preco_medio
FROM vendas_album;
```

### 2. Qual a diferença entre divisão inteira e divisão decimal?

**Resposta**: Depende dos tipos de dados envolvidos:

**Divisão inteira** (ambos operandos inteiros):
```sql
SELECT 7 / 2;          -- Resultado: 3 (divisão inteira)
SELECT 150 / 60;       -- Resultado: 2 (minutos -> horas)
```

**Divisão decimal** (pelo menos um operando decimal):
```sql
SELECT 7.0 / 2;        -- Resultado: 3.5 (divisão decimal)
SELECT 7 / 2.0;        -- Resultado: 3.5 (divisão decimal)
SELECT CAST(7 AS DECIMAL) / 2;  -- Força divisão decimal
```

**Para duração de músicas**:
```sql
-- Converter segundos para minutos com decimais
SELECT 
    titulo,
    duracao as segundos,
    duracao / 60.0 as minutos_decimais,
    duracao / 60 as minutos_inteiros
FROM musica;
```

### 3. Como usar operadores aritméticos para análises temporais?

**Resposta**: Técnicas para cálculos de tempo:

**Diferença entre datas**:
```sql
-- Idade de uma música em dias
SELECT 
    titulo,
    data_lancamento,
    CURRENT_DATE - data_lancamento as dias_desde_lancamento
FROM album;
```

**Cálculos com duração**:
```sql
-- Tempo total de reprodução em diferentes unidades
SELECT 
    nome_playlist,
    SUM(duracao) as segundos_totais,
    SUM(duracao) / 60 as minutos_totais,
    SUM(duracao) / 3600.0 as horas_totais
FROM playlist p
JOIN playlist_musica pm ON p.id_playlist = pm.id_playlist
JOIN musica m ON pm.id_musica = m.id_musica
GROUP BY p.id_playlist, p.nome_playlist;
```

### 4. Como implementar cálculos percentuais em relatórios?

**Resposta**: Diferentes abordagens para percentuais:

**Percentual simples**:
```sql
-- Percentual de músicas por gênero
SELECT 
    genero,
    COUNT(*) as quantidade,
    COUNT(*) * 100.0 / (SELECT COUNT(*) FROM musica) as percentual
FROM musica m
JOIN album a ON m.id_album = a.id_album
GROUP BY genero;
```

**Usando window functions**:
```sql
-- Percentual com window function
SELECT 
    genero,
    COUNT(*) as quantidade,
    COUNT(*) * 100.0 / SUM(COUNT(*)) OVER() as percentual
FROM musica m
JOIN album a ON m.id_album = a.id_album
GROUP BY genero;
```

**Crescimento percentual**:
```sql
-- Crescimento de reproduções mês a mês
SELECT 
    ano_mes,
    total_reproducoes,
    LAG(total_reproducoes) OVER (ORDER BY ano_mes) as mes_anterior,
    (total_reproducoes - LAG(total_reproducoes) OVER (ORDER BY ano_mes)) * 100.0 
    / LAG(total_reproducoes) OVER (ORDER BY ano_mes) as crescimento_percentual
FROM estatisticas_mensais;
```

### 5. Como arredondar e formatar resultados numéricos adequadamente?

**Resposta**: Funções de formatação:

**ROUND para arredondamento**:
```sql
SELECT 
    titulo,
    preco,
    ROUND(preco, 2) as preco_arredondado,
    ROUND(preco * 1.1, 2) as preco_com_taxa
FROM album;
```

**CEIL e FLOOR para arredondamento direcionado**:
```sql
SELECT 
    duracao_segundos,
    CEIL(duracao_segundos / 60.0) as minutos_arredondado_cima,
    FLOOR(duracao_segundos / 60.0) as minutos_arredondado_baixo
FROM musica;
```

**TRUNCATE para truncamento**:
```sql
-- Remover casas decimais sem arredondar
SELECT 
    TRUNCATE(preco_com_desconto, 2) as preco_truncado
FROM promocoes;
```

### 6. Como usar MOD (módulo) para análises cíclicas?

**Resposta**: MOD é útil para padrões cíclicos:

**Identificar padrões temporais**:
```sql
-- Agrupar reproduções por trimestre
SELECT 
    EXTRACT(MONTH FROM data_reproducao) as mes,
    CASE (EXTRACT(MONTH FROM data_reproducao) - 1) / 3
        WHEN 0 THEN 'Q1'
        WHEN 1 THEN 'Q2' 
        WHEN 2 THEN 'Q3'
        WHEN 3 THEN 'Q4'
    END as trimestre,
    COUNT(*) as total_reproducoes
FROM historico_reproducao
GROUP BY EXTRACT(MONTH FROM data_reproducao);
```

**Distribuição por dia da semana**:
```sql
-- Análise de reproduções por dia da semana
SELECT 
    EXTRACT(DOW FROM data_reproducao) as dia_semana,
    CASE EXTRACT(DOW FROM data_reproducao)
        WHEN 0 THEN 'Domingo'
        WHEN 1 THEN 'Segunda'
        WHEN 2 THEN 'Terça'
        -- ... outros dias
    END as nome_dia,
    COUNT(*) as total
FROM historico_reproducao
GROUP BY EXTRACT(DOW FROM data_reproducao);
```

### 7. Quais cuidados tomar com precisão em cálculos financeiros?

**Resposta**: Cuidados essenciais:

**Use DECIMAL para valores monetários**:
```sql
-- ❌ Evitar FLOAT para dinheiro
CREATE TABLE album (
    preco FLOAT  -- Pode ter problemas de precisão
);

-- ✅ Usar DECIMAL para precisão
CREATE TABLE album (
    preco DECIMAL(10,2)  -- Precisão garantida
);
```

**Arredondamento consistente**:
```sql
-- Cálculo de royalties com precisão
SELECT 
    titulo,
    total_reproducoes,
    valor_por_reproducao,
    ROUND(total_reproducoes * valor_por_reproducao, 2) as royalty_total
FROM relatorio_royalties;
```

**Validação de totais**:
```sql
-- Verificar se soma bate com total esperado
SELECT 
    SUM(ROUND(valor_individual, 2)) as soma_individual,
    ROUND(SUM(valor_individual), 2) as total_arredondado
FROM calculos_financeiros;
-- Verificar se os valores são iguais
```

## Referências Bibliográficas

- **Date, C.J.** (2012). *SQL and Relational Theory*. 2nd Edition. O'Reilly Media.
- **Celko, J.** (2010). *Joe Celko's SQL for Smarties*. 4th Edition. Morgan Kaufmann.

## Próximos Passos

No próximo módulo (12), estudaremos **Relatórios com Funções de Banco de Dados**, explorando funções agregadas e de string.