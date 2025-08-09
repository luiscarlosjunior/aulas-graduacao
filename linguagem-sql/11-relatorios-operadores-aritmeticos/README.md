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

## Referências Bibliográficas

- **Date, C.J.** (2012). *SQL and Relational Theory*. 2nd Edition. O'Reilly Media.
- **Celko, J.** (2010). *Joe Celko's SQL for Smarties*. 4th Edition. Morgan Kaufmann.

## Próximos Passos

No próximo módulo (12), estudaremos **Relatórios com Funções de Banco de Dados**, explorando funções agregadas e de string.