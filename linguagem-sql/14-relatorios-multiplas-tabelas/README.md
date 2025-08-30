# Módulo 14 - Relatórios utilizando Múltiplas Tabelas (JOINs)

## Objetivos de Aprendizagem

Ao final deste módulo, o aluno será capaz de:
- Compreender os conceitos de JOIN e relacionamentos entre tabelas
- Utilizar diferentes tipos de JOIN (INNER, LEFT, RIGHT, FULL OUTER)
- Construir consultas complexas envolvendo múltiplas tabelas
- Aplicar boas práticas em JOINs para otimização de performance
- Resolver problemas reais usando relacionamentos de dados

## Conteúdo Teórico

### 1. Fundamentos dos JOINs

#### 1.1 Por que usar JOINs?
Os bancos relacionais armazenam dados em tabelas separadas para evitar redundância. Os JOINs permitem combinar dados de múltiplas tabelas baseados em relacionamentos.

**Exemplo sem JOIN (dados redundantes)**:
```sql
-- ❌ Estrutura ruim com redundância
CREATE TABLE musica_completa (
    id_musica INTEGER,
    titulo_musica VARCHAR(150),
    nome_artista VARCHAR(100), -- redundante
    titulo_album VARCHAR(150), -- redundante
    pais_artista VARCHAR(50)   -- redundante
);
```

**Exemplo com JOIN (dados normalizados)**:
```sql
-- ✅ Estrutura boa normalizada
-- Dados distribuídos em artista, album, musica
-- Combinados via JOIN quando necessário
```

#### 1.2 Tipos de Relacionamentos
- **1:N (Um para Muitos)**: Um artista → vários álbuns
- **N:M (Muitos para Muitos)**: Playlists ↔ Músicas  
- **1:1 (Um para Um)**: Usuário ↔ Perfil detalhado

### 2. INNER JOIN

#### 2.1 Sintaxe e Conceito
```sql
SELECT colunas
FROM tabela1 t1
INNER JOIN tabela2 t2 ON t1.chave = t2.chave;
```

O INNER JOIN retorna apenas registros que têm correspondência em ambas as tabelas.

#### 2.2 Exemplos Práticos - Sistema MusiStream

**Listar músicas com seus álbuns**:
```sql
SELECT m.titulo AS musica,
       a.titulo AS album,
       m.duracao
FROM musica m
INNER JOIN album a ON m.id_album = a.id_album
ORDER BY a.titulo, m.numero_faixa;
```

**Listar álbuns com seus artistas**:
```sql
SELECT ar.nome_artista,
       al.titulo AS album,
       al.data_lancamento,
       al.numero_faixas
FROM album al
INNER JOIN artista ar ON al.id_artista = ar.id_artista
ORDER BY ar.nome_artista, al.data_lancamento;
```

**Triplo JOIN - Músicas, Álbuns e Artistas**:
```sql
SELECT ar.nome_artista,
       al.titulo AS album,
       m.titulo AS musica,
       CONCAT(FLOOR(m.duracao/60), ':', LPAD(m.duracao%60, 2, '0')) AS duracao
FROM musica m
INNER JOIN album al ON m.id_album = al.id_album
INNER JOIN artista ar ON al.id_artista = ar.id_artista
ORDER BY ar.nome_artista, al.titulo, m.numero_faixa;
```

### 3. LEFT JOIN (LEFT OUTER JOIN)

#### 3.1 Conceito
O LEFT JOIN retorna todos os registros da tabela à esquerda, mesmo que não tenham correspondência na tabela à direita.

#### 3.2 Exemplos Práticos

**Todos os artistas e seus álbuns (incluindo artistas sem álbuns)**:
```sql
SELECT ar.nome_artista,
       ar.pais_origem,
       COUNT(al.id_album) AS total_albums,
       COALESCE(MIN(al.data_lancamento), 'Sem álbums') AS primeiro_album
FROM artista ar
LEFT JOIN album al ON ar.id_artista = al.id_artista
GROUP BY ar.id_artista, ar.nome_artista, ar.pais_origem
ORDER BY total_albums DESC;
```

**Usuários e suas reproduções (incluindo usuários que nunca ouviram música)**:
```sql
SELECT u.nome_usuario,
       u.email,
       COUNT(h.id_historico) AS total_reproducoes,
       MAX(h.data_reproducao) AS ultima_reproducao
FROM usuario u
LEFT JOIN historico_reproducao h ON u.id_usuario = h.id_usuario
GROUP BY u.id_usuario, u.nome_usuario, u.email
ORDER BY total_reproducoes DESC;
```

### 4. RIGHT JOIN (RIGHT OUTER JOIN)

#### 4.1 Conceito
O RIGHT JOIN é o oposto do LEFT JOIN - retorna todos os registros da tabela à direita.

#### 4.2 Exemplo Prático
```sql
-- Todas as músicas e possíveis reproduções
SELECT m.titulo,
       ar.nome_artista,
       COUNT(h.id_historico) AS vezes_tocada
FROM historico_reproducao h
RIGHT JOIN musica m ON h.id_musica = m.id_musica
LEFT JOIN album al ON m.id_album = al.id_album
LEFT JOIN artista ar ON al.id_artista = ar.id_artista
GROUP BY m.id_musica, m.titulo, ar.nome_artista
ORDER BY vezes_tocada DESC;
```

### 5. FULL OUTER JOIN

#### 5.1 Conceito
Combina LEFT e RIGHT JOIN - retorna registros quando há correspondência em qualquer uma das tabelas.

#### 5.2 Exemplo (nem todos os SGBDs suportam)
```sql
-- Artistas e usuários (todos os registros)
SELECT COALESCE(ar.nome_artista, 'N/A') AS artista,
       COALESCE(u.nome_usuario, 'N/A') AS usuario,
       ar.pais_origem,
       u.data_cadastro
FROM artista ar
FULL OUTER JOIN usuario u ON ar.pais_origem = 'Brasil' AND u.id_usuario IS NOT NULL;
```

### 6. CROSS JOIN

#### 6.1 Conceito
Produto cartesiano - combina cada linha da primeira tabela com cada linha da segunda.

#### 6.2 Exemplo (use com cuidado!)
```sql
-- Todas as combinações possíveis de usuários e dispositivos
SELECT u.nome_usuario,
       d.dispositivo
FROM usuario u
CROSS JOIN (
    SELECT 'web' AS dispositivo
    UNION SELECT 'mobile_android'
    UNION SELECT 'mobile_ios'
    UNION SELECT 'desktop'
) d
ORDER BY u.nome_usuario, d.dispositivo;
```

### 7. Consultas Complexas com Múltiplos JOINs

#### 7.1 Relatório de Popularidade por Artista
```sql
-- Ranking de artistas por número de reproduções
SELECT ar.nome_artista,
       ar.pais_origem,
       COUNT(DISTINCT al.id_album) AS total_albums,
       COUNT(DISTINCT m.id_musica) AS total_musicas,
       COUNT(h.id_historico) AS total_reproducoes,
       ROUND(AVG(m.duracao), 2) AS duracao_media_musicas
FROM artista ar
LEFT JOIN album al ON ar.id_artista = al.id_artista
LEFT JOIN musica m ON al.id_album = m.id_album
LEFT JOIN historico_reproducao h ON m.id_musica = h.id_musica
GROUP BY ar.id_artista, ar.nome_artista, ar.pais_origem
HAVING total_musicas > 0
ORDER BY total_reproducoes DESC, total_musicas DESC;
```

#### 7.2 Análise de Engajamento de Usuários
```sql
-- Perfil de consumo musical dos usuários
SELECT u.nome_usuario,
       YEAR(CURRENT_DATE) - YEAR(u.data_nascimento) AS idade,
       COUNT(DISTINCT h.id_historico) AS total_reproducoes,
       COUNT(DISTINCT ar.id_artista) AS artistas_diferentes,
       COUNT(DISTINCT ar.pais_origem) AS paises_diferentes,
       ROUND(AVG(h.duracao_ouvida), 2) AS media_duracao_ouvida,
       h_recente.data_reproducao AS ultima_reproducao
FROM usuario u
LEFT JOIN historico_reproducao h ON u.id_usuario = h.id_usuario
LEFT JOIN musica m ON h.id_musica = m.id_musica
LEFT JOIN album al ON m.id_album = al.id_album
LEFT JOIN artista ar ON al.id_artista = ar.id_artista
LEFT JOIN (
    SELECT id_usuario, MAX(data_reproducao) AS data_reproducao
    FROM historico_reproducao
    GROUP BY id_usuario
) h_recente ON u.id_usuario = h_recente.id_usuario
GROUP BY u.id_usuario, u.nome_usuario, u.data_nascimento, h_recente.data_reproducao
ORDER BY total_reproducoes DESC;
```

#### 7.3 Descoberta Musical - Recomendações
```sql
-- Encontrar músicas populares que um usuário específico ainda não ouviu
SELECT ar.nome_artista,
       al.titulo AS album,
       m.titulo AS musica,
       COUNT(h_outros.id_historico) AS popularidade
FROM musica m
INNER JOIN album al ON m.id_album = al.id_album
INNER JOIN artista ar ON al.id_artista = ar.id_artista
LEFT JOIN historico_reproducao h_outros ON m.id_musica = h_outros.id_musica
LEFT JOIN historico_reproducao h_usuario ON m.id_musica = h_usuario.id_musica 
    AND h_usuario.id_usuario = 1  -- Usuário específico
WHERE h_usuario.id_historico IS NULL  -- Não ouviu ainda
GROUP BY m.id_musica, ar.nome_artista, al.titulo, m.titulo
HAVING popularidade > 0
ORDER BY popularidade DESC
LIMIT 10;
```

### 8. Otimização e Boas Práticas

#### 8.1 Uso de Aliases
```sql
-- ✅ BOM: Aliases claros e consistentes
SELECT ar.nome_artista,
       al.titulo,
       m.titulo
FROM artista ar
INNER JOIN album al ON ar.id_artista = al.id_artista
INNER JOIN musica m ON al.id_album = m.id_album;

-- ❌ RUIM: Sem aliases
SELECT artista.nome_artista,
       album.titulo,
       musica.titulo
FROM artista
INNER JOIN album ON artista.id_artista = album.id_artista
INNER JOIN musica ON album.id_album = musica.id_album;
```

#### 8.2 Especificar Colunas
```sql
-- ✅ BOM: Especificar tabela.coluna
SELECT ar.nome_artista,
       al.titulo AS album_titulo,
       m.titulo AS musica_titulo
FROM artista ar
INNER JOIN album al ON ar.id_artista = al.id_artista
INNER JOIN musica m ON al.id_album = m.id_album;
```

#### 8.3 Filtros Eficientes
```sql
-- ✅ BOM: Filtrar antes do JOIN quando possível
SELECT ar.nome_artista,
       al.titulo
FROM artista ar
INNER JOIN album al ON ar.id_artista = al.id_artista
WHERE ar.pais_origem = 'Brasil'
  AND al.data_lancamento >= '2000-01-01';
```

#### 8.4 Índices para JOINs
```sql
-- Criar índices nas colunas de JOIN
CREATE INDEX idx_album_id_artista ON album(id_artista);
CREATE INDEX idx_musica_id_album ON musica(id_album);
CREATE INDEX idx_historico_id_usuario ON historico_reproducao(id_usuario);
CREATE INDEX idx_historico_id_musica ON historico_reproducao(id_musica);
```

### 9. Problemas Comuns e Soluções

#### 9.1 Produto Cartesiano Acidental
```sql
-- ❌ ERRO: Esqueceu a condição ON
SELECT ar.nome_artista, al.titulo
FROM artista ar, album al;  -- Vai retornar artistas * albums registros

-- ✅ CORRETO: Com condição ON
SELECT ar.nome_artista, al.titulo
FROM artista ar
INNER JOIN album al ON ar.id_artista = al.id_artista;
```

#### 9.2 Ambiguidade de Colunas
```sql
-- ❌ ERRO: Coluna ambígua
SELECT titulo
FROM album
INNER JOIN musica ON album.id_album = musica.id_album;
-- ERROR: Column 'titulo' is ambiguous

-- ✅ CORRETO: Especificar tabela
SELECT album.titulo AS album_titulo,
       musica.titulo AS musica_titulo
FROM album
INNER JOIN musica ON album.id_album = musica.id_album;
```

### 10. Exercícios Avançados

Consulte a pasta `exercicios/` para desafios práticos que envolvem múltiplas tabelas e JOINs complexos.

## Perguntas e Respostas

### 1. Qual a diferença prática entre INNER JOIN, LEFT JOIN, RIGHT JOIN e FULL OUTER JOIN?

**Resposta**: Cada tipo tem comportamento específico:

**INNER JOIN**: Apenas registros que existem em ambas as tabelas
```sql
-- Apenas artistas que têm álbuns
SELECT a.nome_artista, al.titulo
FROM artista a
INNER JOIN album al ON a.id_artista = al.id_artista;
```
- **Resultado**: Só artistas com álbuns aparecem
- **Uso**: Relacionamentos obrigatórios

**LEFT JOIN**: Todos da tabela à esquerda + matches da direita
```sql
-- Todos os artistas, com álbuns se tiverem
SELECT a.nome_artista, al.titulo
FROM artista a
LEFT JOIN album al ON a.id_artista = al.id_artista;
```
- **Resultado**: Todos artistas, álbuns NULL se não tiverem
- **Uso**: "Mostrar todos X, com Y se existir"

**RIGHT JOIN**: Todos da tabela à direita + matches da esquerda
```sql
-- Todos os álbuns, com informações do artista
SELECT a.nome_artista, al.titulo
FROM artista a
RIGHT JOIN album al ON a.id_artista = al.id_artista;
```
- **Resultado**: Todos álbuns, mesmo órfãos
- **Uso**: Menos comum, LEFT JOIN é preferido

**FULL OUTER JOIN**: Todos os registros de ambas as tabelas
```sql
-- Todos artistas e todos álbuns
SELECT a.nome_artista, al.titulo
FROM artista a
FULL OUTER JOIN album al ON a.id_artista = al.id_artista;
```
- **Resultado**: União completa, NULLs onde não há match
- **Uso**: Análises de completude

### 2. Como otimizar performance em JOINs complexos?

**Resposta**: Estratégias essenciais:

**Índices nas colunas de JOIN**:
```sql
-- Garantir índices em ambas as colunas
CREATE INDEX idx_album_artista ON album(id_artista);
CREATE INDEX idx_artista_id ON artista(id_artista);  -- Geralmente já existe (PK)
```

**Ordem dos JOINs** (tabela menor primeiro):
```sql
-- ✅ Melhor: começar com tabela mais seletiva
SELECT u.nome, p.nome_playlist, m.titulo
FROM usuario u                              -- 1000 usuários
JOIN playlist p ON u.id_usuario = p.id_usuario AND p.ativo = TRUE  -- 100 playlists ativas
JOIN playlist_musica pm ON p.id_playlist = pm.id_playlist
JOIN musica m ON pm.id_musica = m.id_musica;
```

**Filtros antes dos JOINs quando possível**:
```sql
-- Aplicar WHERE que reduz dataset antes do JOIN
SELECT a.nome_artista, al.titulo
FROM artista a
JOIN album al ON a.id_artista = al.id_artista
WHERE a.pais_origem = 'Brasil'  -- Filtro reduz dataset
  AND al.data_lancamento >= '2020-01-01';
```

### 3. Como usar JOINs para resolver problemas de relacionamentos muitos-para-muitos?

**Resposta**: Use tabela de ligação (ponte):

**Estrutura típica**:
```sql
-- Playlist pode ter múltiplas músicas
-- Música pode estar em múltiplas playlists
SELECT 
    p.nome_playlist,
    u.nome_usuario,
    m.titulo,
    pm.ordem,
    pm.data_adicionada
FROM playlist p
JOIN usuario u ON p.id_usuario = u.id_usuario
JOIN playlist_musica pm ON p.id_playlist = pm.id_playlist  -- Tabela ponte
JOIN musica m ON pm.id_musica = m.id_musica
WHERE p.ativo = TRUE
ORDER BY p.nome_playlist, pm.ordem;
```

**Análises agregadas**:
```sql
-- Estatísticas de playlists
SELECT 
    p.nome_playlist,
    COUNT(pm.id_musica) as total_musicas,
    SUM(m.duracao) as duracao_total,
    u.nome_usuario
FROM playlist p
JOIN usuario u ON p.id_usuario = u.id_usuario
LEFT JOIN playlist_musica pm ON p.id_playlist = pm.id_playlist
LEFT JOIN musica m ON pm.id_musica = m.id_musica
GROUP BY p.id_playlist, p.nome_playlist, u.nome_usuario;
```

### 4. Como tratar casos onde JOIN retorna resultados duplicados?

**Resposta**: Estratégias de deduplico:

**DISTINCT para remoção de duplicatas**:
```sql
-- Evitar duplicatas quando um artista tem múltiplos álbuns
SELECT DISTINCT a.nome_artista, a.pais_origem
FROM artista a
JOIN album al ON a.id_artista = al.id_artista
WHERE al.data_lancamento >= '2020-01-01';
```

**GROUP BY para agregação**:
```sql
-- Agrupar para estatísticas
SELECT 
    a.nome_artista,
    COUNT(al.id_album) as total_albums,
    MIN(al.data_lancamento) as primeiro_album,
    MAX(al.data_lancamento) as ultimo_album
FROM artista a
JOIN album al ON a.id_artista = al.id_artista
GROUP BY a.id_artista, a.nome_artista;
```

**EXISTS em vez de JOIN quando só precisa verificar existência**:
```sql
-- Só verificar se artista tem álbuns
SELECT nome_artista
FROM artista a
WHERE EXISTS (SELECT 1 FROM album WHERE id_artista = a.id_artista);
```

### 5. Como implementar JOINs condicionais e filtros complexos?

**Resposta**: Técnicas avançadas:

**JOIN com múltiplas condições**:
```sql
-- JOIN com condições adicionais
SELECT a.nome_artista, al.titulo
FROM artista a
JOIN album al ON a.id_artista = al.id_artista 
              AND al.data_lancamento >= '2020-01-01'
              AND al.ativo = TRUE;
```

**CASE em JOINs**:
```sql
-- JOIN condicional baseado em regra de negócio
SELECT 
    u.nome_usuario,
    p.nome_playlist,
    CASE u.tipo_assinatura
        WHEN 'premium' THEN 'Acesso Total'
        WHEN 'basico' THEN 'Acesso Limitado'
        ELSE 'Sem Acesso'
    END as nivel_acesso
FROM usuario u
LEFT JOIN playlist p ON u.id_usuario = p.id_usuario
    AND (u.tipo_assinatura = 'premium' OR p.publica = TRUE);
```

### 6. Como usar self-joins eficientemente?

**Resposta**: Para relacionamentos dentro da mesma tabela:

**Hierarquias ou comparações**:
```sql
-- Encontrar músicas do mesmo álbum com duração similar
SELECT 
    m1.titulo as musica1,
    m2.titulo as musica2,
    m1.duracao,
    m2.duracao
FROM musica m1
JOIN musica m2 ON m1.id_album = m2.id_album
              AND m1.id_musica < m2.id_musica  -- Evita duplicatas
              AND ABS(m1.duracao - m2.duracao) < 30;  -- Diferença < 30s
```

**Sequências ou relacionamentos temporais**:
```sql
-- Álbuns consecutivos do mesmo artista
SELECT 
    a.nome_artista,
    al1.titulo as album_anterior,
    al1.data_lancamento as data_anterior,
    al2.titulo as proximo_album,
    al2.data_lancamento as data_posterior
FROM album al1
JOIN album al2 ON al1.id_artista = al2.id_artista
              AND al2.data_lancamento > al1.data_lancamento
JOIN artista a ON al1.id_artista = a.id_artista
WHERE NOT EXISTS (
    SELECT 1 FROM album al3 
    WHERE al3.id_artista = al1.id_artista
      AND al3.data_lancamento > al1.data_lancamento 
      AND al3.data_lancamento < al2.data_lancamento
);
```

### 7. Como estruturar JOINs para relatórios complexos com múltiplas dimensões?

**Resposta**: Abordagem sistemática:

**Identificar entidades principais e relacionamentos**:
```sql
-- Relatório completo de performance de artistas
SELECT 
    a.nome_artista,
    a.pais_origem,
    COUNT(DISTINCT al.id_album) as total_albums,
    COUNT(DISTINCT m.id_musica) as total_musicas,
    SUM(COALESCE(hr.reproducoes, 0)) as total_reproducoes,
    AVG(m.duracao) as duracao_media,
    COUNT(DISTINCT p.id_playlist) as aparicoes_playlists
FROM artista a
LEFT JOIN album al ON a.id_artista = al.id_artista
LEFT JOIN musica m ON al.id_album = m.id_album
LEFT JOIN (
    SELECT id_musica, COUNT(*) as reproducoes
    FROM historico_reproducao
    GROUP BY id_musica
) hr ON m.id_musica = hr.id_musica
LEFT JOIN playlist_musica pm ON m.id_musica = pm.id_musica
LEFT JOIN playlist p ON pm.id_playlist = p.id_playlist
GROUP BY a.id_artista, a.nome_artista, a.pais_origem
HAVING total_albums > 0  -- Apenas artistas com álbuns
ORDER BY total_reproducoes DESC;
```

**Usar CTEs para clareza**:
```sql
-- Quebrar consulta complexa em partes
WITH artista_stats AS (
    SELECT 
        a.id_artista,
        a.nome_artista,
        COUNT(al.id_album) as total_albums
    FROM artista a
    LEFT JOIN album al ON a.id_artista = al.id_artista
    GROUP BY a.id_artista, a.nome_artista
),
reproducao_stats AS (
    SELECT 
        al.id_artista,
        COUNT(hr.id_reproducao) as total_reproducoes
    FROM album al
    JOIN musica m ON al.id_album = m.id_album
    JOIN historico_reproducao hr ON m.id_musica = hr.id_musica
    GROUP BY al.id_artista
)
SELECT 
    ast.nome_artista,
    ast.total_albums,
    COALESCE(rs.total_reproducoes, 0) as total_reproducoes
FROM artista_stats ast
LEFT JOIN reproducao_stats rs ON ast.id_artista = rs.id_artista
ORDER BY total_reproducoes DESC;
```

## Referências Bibliográficas

1. **Beaulieu, A.** (2020). *Learning SQL: Master SQL Fundamentals*. 3rd Edition. O'Reilly Media. Capítulos 5-10.

2. **Date, C.J.** (2012). *SQL and Relational Theory*. 2nd Edition. O'Reilly Media. Capítulo 7.

3. **Forta, B.** (2018). *SQL in 10 Minutes, Sams Teach Yourself*. 5th Edition. Sams Publishing. Lições 12-15.

4. **Celko, J.** (2010). *Joe Celko's SQL for Smarties: Advanced SQL Programming*. 4th Edition. Morgan Kaufmann. Capítulos sobre JOINs.

---

**Módulo Anterior**: [13 - Relatórios utilizando Subqueries](../13-relatorios-subqueries/README.md)
**Próximo Módulo**: [15 - Operações com Conjuntos](../15-operacoes-conjuntos/README.md)

**Dica de Performance**: JOINs bem estruturados com índices adequados são a base para consultas eficientes em bancos relacionais. Pratique diferentes tipos de JOIN para dominar esta técnica fundamental.