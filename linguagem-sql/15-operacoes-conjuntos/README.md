# Módulo 15 - Operações com Conjuntos

## Objetivos de Aprendizagem

Ao final deste módulo, o aluno será capaz de:
- Compreender e aplicar operadores de conjuntos em SQL
- Utilizar UNION, UNION ALL para combinar resultados
- Implementar INTERSECT para encontrar interseções
- Usar EXCEPT/MINUS para diferenças entre conjuntos
- Criar relatórios complexos combinando múltiplas consultas
- Otimizar consultas com operadores de conjuntos

## Conteúdo Teórico

### 1. Conceitos de Operações com Conjuntos

#### 1.1 Teoria de Conjuntos em SQL
Os operadores de conjuntos permitem combinar resultados de duas ou mais consultas SELECT:
- **UNION**: Une resultados, removendo duplicatas
- **UNION ALL**: Une resultados, mantendo duplicatas
- **INTERSECT**: Retorna registros comuns a ambas consultas
- **EXCEPT/MINUS**: Retorna registros da primeira consulta que não estão na segunda

#### 1.2 Regras para Operações com Conjuntos
```sql
-- As consultas devem ter:
-- 1. Mesmo número de colunas
-- 2. Tipos de dados compatíveis
-- 3. Mesma ordem das colunas

-- Exemplo básico de estrutura
SELECT coluna1, coluna2 FROM tabela1
UNION
SELECT coluna1, coluna2 FROM tabela2;
```

### 2. UNION e UNION ALL

#### 2.1 UNION (Remove Duplicatas)
```sql
-- Listar todos os nomes únicos (artistas e usuários)
SELECT nome_artista as nome, 'Artista' as tipo FROM artista
UNION
SELECT nome_usuario as nome, 'Usuário' as tipo FROM usuario
ORDER BY nome;
```

#### 2.2 UNION ALL (Mantém Duplicatas)
```sql
-- Histórico completo de atividades do sistema
SELECT 
    data_cadastro as data_evento,
    'Usuário Cadastrado: ' || nome_usuario as evento,
    'Sistema' as origem
FROM usuario
WHERE data_cadastro IS NOT NULL

UNION ALL

SELECT 
    data_reproducao,
    'Música Reproduzida: ID ' || id_musica,
    'Player'
FROM historico_reproducao
WHERE data_reproducao >= CURRENT_DATE - INTERVAL 7 DAY

ORDER BY data_evento DESC;
```

### 3. INTERSECT (Interseção)

#### 3.1 Encontrar Elementos Comuns
```sql
-- Usuários que são também artistas (mesmo nome)
SELECT nome_usuario as nome FROM usuario
INTERSECT
SELECT nome_artista as nome FROM artista;
```

#### 3.2 Análise de Preferências Comuns
```sql
-- Músicas populares tanto no Brasil quanto nos EUA
SELECT m.id_musica, m.titulo
FROM musica m
JOIN historico_reproducao hr ON m.id_musica = hr.id_musica
JOIN usuario u ON hr.id_usuario = u.id_usuario
WHERE u.pais = 'Brasil'
GROUP BY m.id_musica, m.titulo
HAVING COUNT(*) >= 50

INTERSECT

SELECT m.id_musica, m.titulo
FROM musica m
JOIN historico_reproducao hr ON m.id_musica = hr.id_musica
JOIN usuario u ON hr.id_usuario = u.id_usuario
WHERE u.pais = 'Estados Unidos'
GROUP BY m.id_musica, m.titulo
HAVING COUNT(*) >= 50;
```

### 4. EXCEPT/MINUS (Diferença)

#### 4.1 Encontrar Diferenças
```sql
-- Artistas que não têm nenhuma música reproduzida
SELECT a.id_artista, a.nome_artista
FROM artista a
JOIN album al ON a.id_artista = al.id_artista
JOIN musica m ON al.id_album = m.id_album

EXCEPT

SELECT DISTINCT a.id_artista, a.nome_artista
FROM artista a
JOIN album al ON a.id_artista = al.id_artista
JOIN musica m ON al.id_album = m.id_album
JOIN historico_reproducao hr ON m.id_musica = hr.id_musica;
```

#### 4.2 Análise de Gaps no Catálogo
```sql
-- Gêneros que existem mas não têm músicas
SELECT id_genero, nome_genero FROM genero
EXCEPT
SELECT DISTINCT g.id_genero, g.nome_genero
FROM genero g
JOIN musica m ON g.id_genero = m.id_genero;
```

### 5. Casos de Uso Práticos no MusiStream

#### 5.1 Dashboard Unificado
```sql
-- Relatório unificado de estatísticas
SELECT 'Artistas Ativos' as categoria, COUNT(*) as quantidade
FROM artista WHERE ativo = 'S'

UNION ALL

SELECT 'Artistas Inativos', COUNT(*)
FROM artista WHERE ativo = 'N'

UNION ALL

SELECT 'Total Álbuns', COUNT(*)
FROM album

UNION ALL

SELECT 'Total Músicas', COUNT(*)
FROM musica

UNION ALL

SELECT 'Usuários Ativos', COUNT(*)
FROM usuario WHERE ativo = 'S'

UNION ALL

SELECT 'Playlists Públicas', COUNT(*)
FROM playlist WHERE publica = 'S'

ORDER BY categoria;
```

#### 5.2 Análise de Popularidade por Região
```sql
-- Top 10 músicas no Brasil vs Top 10 músicas globalmente
WITH brasil_top AS (
    SELECT 
        m.titulo,
        a.nome_artista,
        COUNT(*) as reproducoes,
        'Brasil' as regiao
    FROM musica m
    JOIN album al ON m.id_album = al.id_album
    JOIN artista a ON al.id_artista = a.id_artista
    JOIN historico_reproducao hr ON m.id_musica = hr.id_musica
    JOIN usuario u ON hr.id_usuario = u.id_usuario
    WHERE u.pais = 'Brasil'
    GROUP BY m.id_musica, m.titulo, a.nome_artista
    ORDER BY COUNT(*) DESC
    LIMIT 10
),
global_top AS (
    SELECT 
        m.titulo,
        a.nome_artista,
        COUNT(*) as reproducoes,
        'Global' as regiao
    FROM musica m
    JOIN album al ON m.id_album = al.id_album
    JOIN artista a ON al.id_artista = a.id_artista
    JOIN historico_reproducao hr ON m.id_musica = hr.id_musica
    GROUP BY m.id_musica, m.titulo, a.nome_artista
    ORDER BY COUNT(*) DESC
    LIMIT 10
)
SELECT titulo, nome_artista, reproducoes, regiao FROM brasil_top
UNION ALL
SELECT titulo, nome_artista, reproducoes, regiao FROM global_top
ORDER BY regiao, reproducoes DESC;
```

### 6. Otimização com Operadores de Conjuntos

#### 6.1 Quando Usar Cada Operador
```sql
-- Use UNION quando precisar de resultados únicos
SELECT email FROM usuario
UNION
SELECT email FROM artista; -- Remove emails duplicados

-- Use UNION ALL quando duplicatas são aceitáveis ou desejadas
SELECT nome_usuario FROM usuario
UNION ALL
SELECT nome_artista FROM artista; -- Mantém todos os nomes

-- Use INTERSECT para encontrar sobreposições
SELECT pais_origem FROM artista
INTERSECT
SELECT pais FROM usuario; -- Países que têm tanto artistas quanto usuários

-- Use EXCEPT para exclusões
SELECT pais FROM usuario
EXCEPT
SELECT pais_origem FROM artista; -- Países com usuários mas sem artistas
```

### 7. Relatórios Complexos com Múltiplos Conjuntos

#### 7.1 Análise Completa de Engajamento
```sql
-- Relatório completo de engajamento por tipo de conteúdo
SELECT 
    'Rock - Alta Popularidade' as segmento,
    COUNT(*) as total_itens,
    'Músicas' as tipo
FROM musica m
JOIN genero g ON m.id_genero = g.id_genero
JOIN historico_reproducao hr ON m.id_musica = hr.id_musica
WHERE g.nome_genero = 'Rock'
GROUP BY m.id_musica
HAVING COUNT(*) > 100

UNION ALL

SELECT 
    'Pop - Média Popularidade',
    COUNT(*),
    'Músicas'
FROM musica m
JOIN genero g ON m.id_genero = g.id_genero
JOIN historico_reproducao hr ON m.id_musica = hr.id_musica
WHERE g.nome_genero = 'Pop'
GROUP BY m.id_musica
HAVING COUNT(*) BETWEEN 50 AND 100

UNION ALL

SELECT 
    'Outros Gêneros - Baixa Popularidade',
    COUNT(*),
    'Músicas'
FROM musica m
JOIN genero g ON m.id_genero = g.id_genero
JOIN historico_reproducao hr ON m.id_musica = hr.id_musica
WHERE g.nome_genero NOT IN ('Rock', 'Pop')
GROUP BY m.id_musica
HAVING COUNT(*) < 50

ORDER BY segmento;
```

### 8. Validação de Integridade com Conjuntos

#### 8.1 Detecção de Inconsistências
```sql
-- Encontrar playlists com músicas inexistentes
SELECT 'Playlist com Música Inexistente' as problema, COUNT(*) as quantidade
FROM (
    SELECT DISTINCT pm.id_musica
    FROM playlist_musica pm
    EXCEPT
    SELECT id_musica FROM musica
) inconsistencias

UNION ALL

-- Encontrar histórico com usuários inexistentes
SELECT 'Histórico com Usuário Inexistente', COUNT(*)
FROM (
    SELECT DISTINCT hr.id_usuario
    FROM historico_reproducao hr
    EXCEPT
    SELECT id_usuario FROM usuario
) inconsistencias2

UNION ALL

-- Encontrar álbuns sem músicas
SELECT 'Álbuns sem Músicas', COUNT(*)
FROM (
    SELECT id_album FROM album
    EXCEPT
    SELECT DISTINCT id_album FROM musica WHERE id_album IS NOT NULL
) inconsistencias3;
```

## Exercícios Práticos

Consulte a pasta `exercicios` para atividades práticas que reforçam os conceitos apresentados.

## Referências Bibliográficas

- **Date, C.J.** (2012). *SQL and Relational Theory*. 2nd Edition. O'Reilly Media.
- **Codd, E.F.** (1970). *A Relational Model of Data for Large Shared Data Banks*. Communications of the ACM.
- **Garcia-Molina, H., Ullman, J. D., & Widom, J.** (2013). *Database Systems: The Complete Book*. 2nd Edition. Pearson.

## Próximos Passos

No próximo módulo (16), estudaremos **Criando VIEWS**, explorando visões e consultas reutilizáveis para simplificar consultas complexas.