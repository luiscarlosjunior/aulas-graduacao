# Módulo 13 - Relatórios com Subqueries

## Objetivos de Aprendizagem

Ao final deste módulo, o aluno será capaz de:
- Compreender e implementar subconsultas (subqueries)
- Utilizar subqueries correlacionadas e não-correlacionadas
- Aplicar operadores EXISTS, IN, ANY, ALL
- Criar consultas aninhadas complexas
- Otimizar performance de subqueries
- Resolver problemas complexos usando múltiplos níveis de consultas

## Conteúdo Teórico

### 1. Conceitos de Subqueries

#### 1.1 Subquery Simples (Não-correlacionada)
```sql
-- Encontrar músicas com duração acima da média
SELECT titulo, duracao
FROM musica
WHERE duracao > (
    SELECT AVG(duracao) 
    FROM musica 
    WHERE duracao IS NOT NULL
);
```

#### 1.2 Subquery Correlacionada
```sql
-- Encontrar o álbum mais recente de cada artista
SELECT a.nome_artista, al.titulo, al.ano_lancamento
FROM artista a
JOIN album al ON a.id_artista = al.id_artista
WHERE al.ano_lancamento = (
    SELECT MAX(al2.ano_lancamento)
    FROM album al2
    WHERE al2.id_artista = a.id_artista
);
```

### 2. Operadores com Subqueries

#### 2.1 Operador IN
```sql
-- Artistas que têm músicas no gênero Rock
SELECT nome_artista, pais_origem
FROM artista
WHERE id_artista IN (
    SELECT DISTINCT al.id_artista
    FROM album al
    JOIN musica m ON al.id_album = m.id_album
    JOIN genero g ON m.id_genero = g.id_genero
    WHERE g.nome_genero = 'Rock'
);
```

#### 2.2 Operador EXISTS
```sql
-- Usuários que criaram pelo menos uma playlist
SELECT nome_usuario, email
FROM usuario u
WHERE EXISTS (
    SELECT 1
    FROM playlist p
    WHERE p.id_usuario = u.id_usuario
);

-- Artistas sem nenhum álbum
SELECT nome_artista
FROM artista a
WHERE NOT EXISTS (
    SELECT 1
    FROM album al
    WHERE al.id_artista = a.id_artista
);
```

#### 2.3 Operadores ANY e ALL
```sql
-- Músicas mais longas que QUALQUER música de Rock
SELECT titulo, duracao
FROM musica
WHERE duracao > ANY (
    SELECT m2.duracao
    FROM musica m2
    JOIN genero g ON m2.id_genero = g.id_genero
    WHERE g.nome_genero = 'Rock'
    AND m2.duracao IS NOT NULL
);

-- Músicas mais longas que TODAS as músicas de Pop
SELECT titulo, duracao
FROM musica
WHERE duracao > ALL (
    SELECT m2.duracao
    FROM musica m2
    JOIN genero g ON m2.id_genero = g.id_genero
    WHERE g.nome_genero = 'Pop'
    AND m2.duracao IS NOT NULL
);
```

### 3. Subqueries em Diferentes Cláusulas

#### 3.1 Subquery no SELECT
```sql
-- Relatório com cálculos baseados em subqueries
SELECT 
    a.nome_artista,
    (SELECT COUNT(*) 
     FROM album al 
     WHERE al.id_artista = a.id_artista) as "Total Álbuns",
    (SELECT COUNT(*) 
     FROM album al 
     JOIN musica m ON al.id_album = m.id_album
     WHERE al.id_artista = a.id_artista) as "Total Músicas",
    (SELECT MAX(al.ano_lancamento)
     FROM album al
     WHERE al.id_artista = a.id_artista) as "Último Álbum"
FROM artista a
WHERE a.ativo = 'S';
```

#### 3.2 Subquery no FROM (Tabela Derivada)
```sql
-- Ranking de artistas por popularidade
SELECT 
    ranking.nome_artista,
    ranking.total_reproducoes,
    RANK() OVER (ORDER BY ranking.total_reproducoes DESC) as posicao
FROM (
    SELECT 
        a.nome_artista,
        COUNT(hr.id_historico) as total_reproducoes
    FROM artista a
    JOIN album al ON a.id_artista = al.id_artista
    JOIN musica m ON al.id_album = m.id_album
    JOIN historico_reproducao hr ON m.id_musica = hr.id_musica
    GROUP BY a.id_artista, a.nome_artista
    HAVING COUNT(hr.id_historico) > 100
) ranking
ORDER BY total_reproducoes DESC;
```

### 4. Consultas Complexas do Sistema MusiStream

#### 4.1 Análise de Preferências Musicais
```sql
-- Usuários com gostos similares (que ouviram músicas similares)
SELECT 
    u1.nome_usuario as "Usuário 1",
    u2.nome_usuario as "Usuário 2",
    musicas_comuns.total_comum as "Músicas em Comum"
FROM usuario u1
JOIN usuario u2 ON u1.id_usuario < u2.id_usuario
JOIN (
    SELECT 
        hr1.id_usuario as id_usuario1,
        hr2.id_usuario as id_usuario2,
        COUNT(*) as total_comum
    FROM historico_reproducao hr1
    JOIN historico_reproducao hr2 ON hr1.id_musica = hr2.id_musica
    WHERE hr1.id_usuario < hr2.id_usuario
    GROUP BY hr1.id_usuario, hr2.id_usuario
    HAVING COUNT(*) >= 10
) musicas_comuns ON u1.id_usuario = musicas_comuns.id_usuario1 
                 AND u2.id_usuario = musicas_comuns.id_usuario2
ORDER BY musicas_comuns.total_comum DESC;
```

#### 4.2 Recomendações Baseadas em Comportamento
```sql
-- Músicas para recomendar baseado em artistas favoritos
SELECT DISTINCT
    m.titulo,
    a.nome_artista,
    g.nome_genero
FROM musica m
JOIN album al ON m.id_album = al.id_album
JOIN artista a ON al.id_artista = a.id_artista
JOIN genero g ON m.id_genero = g.id_genero
WHERE a.id_artista IN (
    -- Artistas mais ouvidos pelo usuário
    SELECT TOP 5 al2.id_artista
    FROM historico_reproducao hr
    JOIN musica m2 ON hr.id_musica = m2.id_musica
    JOIN album al2 ON m2.id_album = al2.id_album
    WHERE hr.id_usuario = 1  -- ID do usuário específico
    GROUP BY al2.id_artista
    ORDER BY COUNT(*) DESC
)
AND m.id_musica NOT IN (
    -- Excluir músicas já ouvidas pelo usuário
    SELECT hr2.id_musica
    FROM historico_reproducao hr2
    WHERE hr2.id_usuario = 1
);
```

### 5. Otimização de Subqueries

#### 5.1 Transformação de EXISTS para JOIN
```sql
-- Menos eficiente (subquery correlacionada)
SELECT nome_artista
FROM artista a
WHERE EXISTS (
    SELECT 1
    FROM album al
    WHERE al.id_artista = a.id_artista
    AND al.ano_lancamento >= 2020
);

-- Mais eficiente (JOIN)
SELECT DISTINCT a.nome_artista
FROM artista a
JOIN album al ON a.id_artista = al.id_artista
WHERE al.ano_lancamento >= 2020;
```

#### 5.2 Uso de WITH (Common Table Expressions)
```sql
-- CTE para melhor legibilidade e performance
WITH artistas_populares AS (
    SELECT 
        a.id_artista,
        a.nome_artista,
        COUNT(hr.id_historico) as total_reproducoes
    FROM artista a
    JOIN album al ON a.id_artista = al.id_artista
    JOIN musica m ON al.id_album = m.id_album
    JOIN historico_reproducao hr ON m.id_musica = hr.id_musica
    GROUP BY a.id_artista, a.nome_artista
    HAVING COUNT(hr.id_historico) > 1000
),
generos_populares AS (
    SELECT 
        g.id_genero,
        g.nome_genero,
        COUNT(hr.id_historico) as total_reproducoes
    FROM genero g
    JOIN musica m ON g.id_genero = m.id_genero
    JOIN historico_reproducao hr ON m.id_musica = hr.id_musica
    GROUP BY g.id_genero, g.nome_genero
    HAVING COUNT(hr.id_historico) > 500
)
SELECT 
    ap.nome_artista,
    gp.nome_genero,
    ap.total_reproducoes
FROM artistas_populares ap
JOIN album al ON ap.id_artista = al.id_artista
JOIN musica m ON al.id_album = m.id_album
JOIN generos_populares gp ON m.id_genero = gp.id_genero;
```

### 6. Casos de Uso Avançados

#### 6.1 Análise de Tendências Temporais
```sql
-- Comparar popularidade de gêneros por período
SELECT 
    nome_genero,
    reproducoes_2023,
    reproducoes_2024,
    CASE 
        WHEN reproducoes_2024 > reproducoes_2023 THEN 'Crescendo'
        WHEN reproducoes_2024 < reproducoes_2023 THEN 'Declinando'
        ELSE 'Estável'
    END as tendencia
FROM (
    SELECT 
        g.nome_genero,
        (SELECT COUNT(*)
         FROM historico_reproducao hr2
         JOIN musica m2 ON hr2.id_musica = m2.id_musica
         WHERE m2.id_genero = g.id_genero
         AND EXTRACT(YEAR FROM hr2.data_reproducao) = 2023) as reproducoes_2023,
        (SELECT COUNT(*)
         FROM historico_reproducao hr3
         JOIN musica m3 ON hr3.id_musica = m3.id_musica
         WHERE m3.id_genero = g.id_genero
         AND EXTRACT(YEAR FROM hr3.data_reproducao) = 2024) as reproducoes_2024
    FROM genero g
) comparacao
WHERE reproducoes_2023 > 0 OR reproducoes_2024 > 0
ORDER BY reproducoes_2024 DESC;
```

## Exercícios Práticos

Consulte a pasta `exercicios` para atividades práticas que reforçam os conceitos apresentados.

## Referências Bibliográficas

- **Date, C.J.** (2012). *SQL and Relational Theory*. 2nd Edition. O'Reilly Media.
- **Celko, J.** (2010). *Joe Celko's SQL for Smarties*. 4th Edition. Morgan Kaufmann.

## Próximos Passos

No próximo módulo (14), estudaremos **Relatórios com Múltiplas Tabelas**, explorando JOINs avançados e relacionamentos complexos.