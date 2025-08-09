# Módulo 12 - Relatórios com Funções de Banco de Dados

## Objetivos de Aprendizagem

Ao final deste módulo, o aluno será capaz de:
- Utilizar funções agregadas (COUNT, SUM, AVG, MIN, MAX)
- Trabalhar com funções de string e manipulação de texto
- Aplicar funções de data e tempo
- Usar funções de conversão e formatação
- Implementar funções analíticas (window functions)
- Criar relatórios complexos usando múltiplas funções

## Conteúdo Teórico

### 1. Funções Agregadas

#### 1.1 Funções Básicas
```sql
-- Estatísticas gerais do catálogo musical
SELECT 
    COUNT(*) as "Total Músicas",
    COUNT(DISTINCT id_artista) as "Total Artistas Únicos",
    AVG(duracao) as "Duração Média",
    MIN(duracao) as "Música Mais Curta",
    MAX(duracao) as "Música Mais Longa",
    SUM(duracao) as "Duração Total Catálogo"
FROM musica m
JOIN album al ON m.id_album = al.id_album;
```

#### 1.2 Funções com GROUP BY
```sql
-- Estatísticas por gênero musical
SELECT 
    g.nome_genero,
    COUNT(m.id_musica) as "Quantidade",
    ROUND(AVG(m.duracao), 2) as "Duração Média",
    SUM(m.duracao) as "Duração Total",
    COUNT(DISTINCT al.id_artista) as "Artistas Únicos"
FROM genero g
LEFT JOIN musica m ON g.id_genero = m.id_genero
LEFT JOIN album al ON m.id_album = al.id_album
GROUP BY g.id_genero, g.nome_genero
ORDER BY "Quantidade" DESC;
```

### 2. Funções de String

#### 2.1 Manipulação de Texto
```sql
-- Limpeza e formatação de nomes
SELECT 
    nome_artista as "Original",
    UPPER(nome_artista) as "Maiúsculo",
    LOWER(nome_artista) as "Minúsculo",
    INITCAP(nome_artista) as "Primeira Maiúscula",
    LENGTH(nome_artista) as "Tamanho",
    TRIM(nome_artista) as "Sem Espaços",
    SUBSTR(nome_artista, 1, 10) as "Primeiros 10",
    REPLACE(nome_artista, ' ', '_') as "Underscore"
FROM artista
WHERE nome_artista IS NOT NULL;
```

#### 2.2 Busca e Extração
```sql
-- Análise de títulos de músicas
SELECT 
    titulo,
    CASE 
        WHEN INSTR(UPPER(titulo), 'LOVE') > 0 THEN 'Contém LOVE'
        WHEN INSTR(UPPER(titulo), 'HEART') > 0 THEN 'Contém HEART'
        WHEN INSTR(UPPER(titulo), 'TIME') > 0 THEN 'Contém TIME'
        ELSE 'Outros temas'
    END as "Tema",
    REGEXP_COUNT(titulo, '[aeiouAEIOU]') as "Vogais no Título"
FROM musica
WHERE titulo IS NOT NULL;
```

### 3. Funções de Data e Tempo

#### 3.1 Manipulação de Datas
```sql
-- Análise temporal de reproduções
SELECT 
    EXTRACT(YEAR FROM data_reproducao) as "Ano",
    EXTRACT(MONTH FROM data_reproducao) as "Mês",
    TO_CHAR(data_reproducao, 'Day') as "Dia da Semana",
    COUNT(*) as "Total Reproduções",
    COUNT(DISTINCT id_usuario) as "Usuários Únicos"
FROM historico_reproducao
WHERE data_reproducao >= CURRENT_DATE - INTERVAL 365 DAY
GROUP BY 
    EXTRACT(YEAR FROM data_reproducao),
    EXTRACT(MONTH FROM data_reproducao),
    TO_CHAR(data_reproducao, 'Day')
ORDER BY "Ano", "Mês";
```

#### 3.2 Cálculos Temporais
```sql
-- Idade dos álbuns e atividade dos artistas
SELECT 
    a.nome_artista,
    al.titulo,
    al.ano_lancamento,
    EXTRACT(YEAR FROM CURRENT_DATE) - al.ano_lancamento as "Idade do Álbum",
    CASE 
        WHEN al.ano_lancamento >= EXTRACT(YEAR FROM CURRENT_DATE) - 5 THEN 'Recente'
        WHEN al.ano_lancamento >= EXTRACT(YEAR FROM CURRENT_DATE) - 10 THEN 'Moderado'
        ELSE 'Clássico'
    END as "Classificação Temporal"
FROM artista a
JOIN album al ON a.id_artista = al.id_artista
WHERE al.ano_lancamento IS NOT NULL
ORDER BY al.ano_lancamento DESC;
```

### 4. Window Functions (Funções Analíticas)

#### 4.1 Ranking e Numeração
```sql
-- Top músicas por gênero com ranking
SELECT 
    g.nome_genero,
    m.titulo,
    a.nome_artista,
    COUNT(hr.id_historico) as "Reproduções",
    ROW_NUMBER() OVER (PARTITION BY g.nome_genero ORDER BY COUNT(hr.id_historico) DESC) as "Posição",
    RANK() OVER (PARTITION BY g.nome_genero ORDER BY COUNT(hr.id_historico) DESC) as "Rank",
    PERCENT_RANK() OVER (PARTITION BY g.nome_genero ORDER BY COUNT(hr.id_historico) DESC) as "Percentil"
FROM genero g
JOIN musica m ON g.id_genero = m.id_genero
JOIN album al ON m.id_album = al.id_album
JOIN artista a ON al.id_artista = a.id_artista
LEFT JOIN historico_reproducao hr ON m.id_musica = hr.id_musica
GROUP BY g.id_genero, g.nome_genero, m.id_musica, m.titulo, a.nome_artista
HAVING COUNT(hr.id_historico) > 0
ORDER BY g.nome_genero, "Reproduções" DESC;
```

#### 4.2 Agregações com Janelas
```sql
-- Análise de tendências de reprodução
SELECT 
    DATE(data_reproducao) as "Data",
    COUNT(*) as "Reproduções do Dia",
    SUM(COUNT(*)) OVER (ORDER BY DATE(data_reproducao) ROWS BETWEEN 6 PRECEDING AND CURRENT ROW) as "Média 7 Dias",
    LAG(COUNT(*), 1) OVER (ORDER BY DATE(data_reproducao)) as "Dia Anterior",
    COUNT(*) - LAG(COUNT(*), 1) OVER (ORDER BY DATE(data_reproducao)) as "Diferença"
FROM historico_reproducao
WHERE data_reproducao >= CURRENT_DATE - INTERVAL 30 DAY
GROUP BY DATE(data_reproducao)
ORDER BY "Data";
```

### 5. Funções de Conversão

#### 5.1 Conversão de Tipos
```sql
-- Relatório formatado com conversões
SELECT 
    nome_artista,
    TO_CHAR(COUNT(*), '999,999') as "Total Álbuns",
    TO_CHAR(AVG(numero_faixas), '999.99') as "Média Faixas",
    TO_CHAR(MIN(ano_lancamento)) as "Primeiro Álbum",
    TO_CHAR(MAX(ano_lancamento)) as "Último Álbum"
FROM artista a
JOIN album al ON a.id_artista = al.id_artista
GROUP BY a.id_artista, a.nome_artista
HAVING COUNT(*) > 1
ORDER BY COUNT(*) DESC;
```

### 6. Relatórios Complexos com Múltiplas Funções

#### 6.1 Dashboard Executivo
```sql
-- Dashboard completo do MusiStream
SELECT 
    'Usuários' as "Métrica",
    TO_CHAR(COUNT(*), '999,999') as "Total",
    TO_CHAR(COUNT(CASE WHEN ativo = 'S' THEN 1 END), '999,999') as "Ativos",
    TO_CHAR(ROUND(COUNT(CASE WHEN ativo = 'S' THEN 1 END) / COUNT(*) * 100, 1), '999.9') || '%' as "% Ativos"
FROM usuario

UNION ALL

SELECT 
    'Artistas',
    TO_CHAR(COUNT(*), '999,999'),
    TO_CHAR(COUNT(CASE WHEN ativo = 'S' THEN 1 END), '999,999'),
    TO_CHAR(ROUND(COUNT(CASE WHEN ativo = 'S' THEN 1 END) / COUNT(*) * 100, 1), '999.9') || '%'
FROM artista

UNION ALL

SELECT 
    'Catálogo Musical',
    TO_CHAR(COUNT(*), '999,999'),
    TO_CHAR(SUM(numero_faixas), '999,999'),
    TO_CHAR(ROUND(AVG(numero_faixas), 1), '999.9') || ' faixas/álbum'
FROM album;
```

## Exercícios Práticos

Consulte a pasta `exercicios` para atividades práticas que reforçam os conceitos apresentados.

## Referências Bibliográficas

- **Celko, J.** (2010). *Joe Celko's SQL for Smarties*. 4th Edition. Morgan Kaufmann.
- **Oracle Corporation** (2021). *Oracle Database SQL Language Reference*. Capítulo sobre Functions.

## Próximos Passos

No próximo módulo (13), estudaremos **Relatórios com Subqueries**, explorando subconsultas e consultas aninhadas.