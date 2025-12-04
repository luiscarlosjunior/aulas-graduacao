# Módulo 16 - Criando VIEWS (Visões)

## Objetivos de Aprendizagem

Ao final deste módulo, o aluno será capaz de:
- Compreender o conceito e propósito das VIEWS
- Criar, modificar e remover views
- Utilizar views para simplificar consultas complexas
- Implementar segurança através de views
- Otimizar performance com views materializadas
- Aplicar boas práticas no design de views

## Conteúdo Teórico

### 1. Conceitos Fundamentais de VIEWS

#### 1.1 O que é uma VIEW?
Uma **VIEW** (visão) é uma tabela virtual baseada no resultado de uma consulta SQL. É uma "janela" para os dados que não armazena dados fisicamente, mas apresenta dados de uma ou mais tabelas de forma organizada.

**Características principais**:
- Não armazena dados (exceto views materializadas)
- Sempre reflete dados atuais das tabelas base
- Pode ser usada como uma tabela em consultas
- Simplifica consultas complexas
- Fornece camada de abstração e segurança

#### 1.2 Sintaxe Básica
```sql
CREATE VIEW nome_da_view AS
SELECT colunas
FROM tabelas
WHERE condições;
```

#### 1.3 Vantagens das VIEWS
1. **Simplicidade**: Consultas complexas tornam-se simples
2. **Reutilização**: Lógica de negócio centralizada
3. **Segurança**: Controle de acesso granular
4. **Abstração**: Oculta complexidade do modelo de dados
5. **Compatibilidade**: Mantém interfaces estáveis

### 2. Criando VIEWS Básicas

#### 2.1 VIEW Simples - Catálogo Básico
```sql
-- VIEW: Catálogo simples de músicas
CREATE VIEW vw_catalogo_musicas AS
SELECT m.id_musica,
       m.titulo AS musica,
       ar.nome_artista AS artista,
       al.titulo AS album,
       CONCAT(FLOOR(m.duracao/60), ':', LPAD(m.duracao%60, 2, '0')) AS duracao_formatada,
       al.data_lancamento
FROM musica m
JOIN album al ON m.id_album = al.id_album
JOIN artista ar ON al.id_artista = ar.id_artista
ORDER BY ar.nome_artista, al.data_lancamento, m.numero_faixa;
```

**Usando a VIEW**:
```sql
-- Consulta simples usando a view
SELECT * FROM vw_catalogo_musicas 
WHERE artista LIKE 'The%';

-- Filtrar por período
SELECT * FROM vw_catalogo_musicas 
WHERE data_lancamento BETWEEN '1960-01-01' AND '1970-12-31';
```

#### 2.2 VIEW com Cálculos - Estatísticas de Artistas
```sql
-- VIEW: Estatísticas completas de artistas
CREATE VIEW vw_estatisticas_artistas AS
SELECT ar.id_artista,
       ar.nome_artista,
       ar.pais_origem,
       ar.numero_membros,
       COUNT(DISTINCT al.id_album) AS total_albums,
       COUNT(DISTINCT m.id_musica) AS total_musicas,
       ROUND(AVG(m.duracao), 2) AS duracao_media_musicas,
       SUM(m.duracao) AS duracao_total_segundos,
       MIN(al.data_lancamento) AS primeiro_album,
       MAX(al.data_lancamento) AS ultimo_album,
       COUNT(DISTINCT h.id_usuario) AS usuarios_unicos_ouvintes,
       COUNT(h.id_historico) AS total_reproducoes
FROM artista ar
LEFT JOIN album al ON ar.id_artista = al.id_artista
LEFT JOIN musica m ON al.id_album = m.id_album
LEFT JOIN historico_reproducao h ON m.id_musica = h.id_musica
GROUP BY ar.id_artista, ar.nome_artista, ar.pais_origem, ar.numero_membros;
```

### 3. VIEWS para Relatórios de Negócio

#### 3.1 VIEW de Ranking de Popularidade
```sql
-- VIEW: Ranking de músicas mais populares
CREATE VIEW vw_ranking_popularidade AS
SELECT m.id_musica,
       m.titulo AS musica,
       ar.nome_artista AS artista,
       al.titulo AS album,
       COUNT(h.id_historico) AS total_reproducoes,
       COUNT(DISTINCT h.id_usuario) AS usuarios_unicos,
       ROUND(AVG(h.duracao_ouvida), 2) AS media_tempo_ouvido,
       ROUND((AVG(h.duracao_ouvida) / m.duracao) * 100, 2) AS percentual_medio_ouvido,
       MAX(h.data_reproducao) AS ultima_reproducao
FROM musica m
JOIN album al ON m.id_album = al.id_album
JOIN artista ar ON al.id_artista = ar.id_artista
LEFT JOIN historico_reproducao h ON m.id_musica = h.id_musica
GROUP BY m.id_musica, m.titulo, ar.nome_artista, al.titulo, m.duracao
HAVING total_reproducoes > 0
ORDER BY total_reproducoes DESC, usuarios_unicos DESC;
```

#### 3.2 VIEW de Análise de Usuários
```sql
-- VIEW: Perfil completo de usuários
CREATE VIEW vw_perfil_usuarios AS
SELECT u.id_usuario,
       u.nome_usuario,
       u.email,
       YEAR(CURRENT_DATE) - YEAR(u.data_nascimento) AS idade,
       DATEDIFF(CURRENT_DATE, u.data_cadastro) AS dias_desde_cadastro,
       COUNT(DISTINCT h.id_historico) AS total_reproducoes,
       COUNT(DISTINCT m.id_musica) AS musicas_diferentes_ouvidas,
       COUNT(DISTINCT ar.id_artista) AS artistas_diferentes_ouvidos,
       COUNT(DISTINCT ar.pais_origem) AS paises_diferentes,
       SUM(h.duracao_ouvida) AS tempo_total_ouvindo_segundos,
       ROUND(SUM(h.duracao_ouvida) / 3600, 2) AS tempo_total_ouvindo_horas,
       MAX(h.data_reproducao) AS ultima_atividade,
       CASE 
           WHEN MAX(h.data_reproducao) >= DATE_SUB(CURRENT_DATE, INTERVAL 7 DAY) THEN 'Ativo'
           WHEN MAX(h.data_reproducao) >= DATE_SUB(CURRENT_DATE, INTERVAL 30 DAY) THEN 'Moderado'
           WHEN MAX(h.data_reproducao) IS NOT NULL THEN 'Inativo'
           ELSE 'Nunca usou'
       END AS status_atividade
FROM usuario u
LEFT JOIN historico_reproducao h ON u.id_usuario = h.id_usuario
LEFT JOIN musica m ON h.id_musica = m.id_musica
LEFT JOIN album al ON m.id_album = al.id_album
LEFT JOIN artista ar ON al.id_artista = ar.id_artista
GROUP BY u.id_usuario, u.nome_usuario, u.email, u.data_nascimento, u.data_cadastro;
```

### 4. VIEWS para Segurança e Controle de Acesso

#### 4.1 VIEW de Dados Públicos de Usuários
```sql
-- VIEW: Informações públicas de usuários (sem dados sensíveis)
CREATE VIEW vw_usuarios_publico AS
SELECT u.id_usuario,
       u.nome_usuario,
       CASE 
           WHEN u.data_nascimento IS NOT NULL 
           THEN YEAR(CURRENT_DATE) - YEAR(u.data_nascimento)
           ELSE NULL
       END AS idade_aproximada,
       COUNT(h.id_historico) AS total_reproducoes,
       CASE 
           WHEN COUNT(h.id_historico) > 1000 THEN 'Expert'
           WHEN COUNT(h.id_historico) > 100 THEN 'Avançado'
           WHEN COUNT(h.id_historico) > 10 THEN 'Intermediário'
           ELSE 'Iniciante'
       END AS nivel_usuario
FROM usuario u
LEFT JOIN historico_reproducao h ON u.id_usuario = h.id_usuario
WHERE u.ativo = TRUE
GROUP BY u.id_usuario, u.nome_usuario, u.data_nascimento;
```

#### 4.2 VIEW de Relatório Executivo
```sql
-- VIEW: Dashboard executivo (dados agregados)
CREATE VIEW vw_dashboard_executivo AS
SELECT 
    -- Métricas de conteúdo
    (SELECT COUNT(*) FROM artista WHERE ativo = TRUE) AS total_artistas_ativos,
    (SELECT COUNT(*) FROM album) AS total_albums,
    (SELECT COUNT(*) FROM musica) AS total_musicas,
    
    -- Métricas de usuários
    (SELECT COUNT(*) FROM usuario WHERE ativo = TRUE) AS usuarios_ativos,
    (SELECT COUNT(DISTINCT id_usuario) FROM historico_reproducao 
     WHERE data_reproducao >= DATE_SUB(CURRENT_DATE, INTERVAL 30 DAY)) AS usuarios_ativos_mes,
    
    -- Métricas de engajamento
    (SELECT COUNT(*) FROM historico_reproducao) AS total_reproducoes,
    (SELECT COUNT(*) FROM historico_reproducao 
     WHERE data_reproducao >= DATE_SUB(CURRENT_DATE, INTERVAL 30 DAY)) AS reproducoes_ultimo_mes,
    
    -- Métricas de tempo
    (SELECT ROUND(SUM(duracao_ouvida) / 3600, 2) FROM historico_reproducao) AS horas_totais_ouvidas,
    (SELECT ROUND(AVG(duracao), 2) FROM musica) AS duracao_media_musicas,
    
    -- Top países
    (SELECT pais_origem FROM artista GROUP BY pais_origem ORDER BY COUNT(*) DESC LIMIT 1) AS pais_mais_artistas,
    
    -- Data do relatório
    CURRENT_TIMESTAMP AS data_geracao_relatorio;
```

### 5. VIEWS Complexas com Subconsultas

#### 5.1 VIEW de Recomendações Musicais
```sql
-- VIEW: Sistema de recomendação baseado em similaridade
CREATE VIEW vw_recomendacoes AS
SELECT DISTINCT
    u.id_usuario,
    u.nome_usuario,
    m.id_musica,
    m.titulo AS musica_recomendada,
    ar.nome_artista,
    al.titulo AS album,
    popularidade.total_reproducoes AS popularidade_geral,
    similaridade.usuarios_similares
FROM usuario u
CROSS JOIN musica m
JOIN album al ON m.id_album = al.id_album
JOIN artista ar ON al.id_artista = ar.id_artista
-- Subconsulta: Popularidade geral da música
JOIN (
    SELECT id_musica, COUNT(*) AS total_reproducoes
    FROM historico_reproducao
    GROUP BY id_musica
    HAVING COUNT(*) >= 2
) popularidade ON m.id_musica = popularidade.id_musica
-- Subconsulta: Usuários com gostos similares
JOIN (
    SELECT h1.id_usuario, h2.id_musica, COUNT(DISTINCT h2.id_usuario) AS usuarios_similares
    FROM historico_reproducao h1
    JOIN historico_reproducao h2 ON h1.id_musica = h2.id_musica AND h1.id_usuario != h2.id_usuario
    GROUP BY h1.id_usuario, h2.id_musica
    HAVING usuarios_similares >= 2
) similaridade ON u.id_usuario = similaridade.id_usuario AND m.id_musica = similaridade.id_musica
-- Excluir músicas já ouvidas pelo usuário
WHERE NOT EXISTS (
    SELECT 1 FROM historico_reproducao h 
    WHERE h.id_usuario = u.id_usuario AND h.id_musica = m.id_musica
)
ORDER BY u.id_usuario, popularidade.total_reproducoes DESC, similaridade.usuarios_similares DESC;
```

### 6. Gerenciamento de VIEWS

#### 6.1 Modificar VIEWS
```sql
-- Opção 1: DROP e CREATE
DROP VIEW IF EXISTS vw_catalogo_musicas;
CREATE VIEW vw_catalogo_musicas AS
-- Nova definição aqui

-- Opção 2: CREATE OR REPLACE (MySQL, PostgreSQL)
CREATE OR REPLACE VIEW vw_catalogo_musicas AS
SELECT m.id_musica,
       m.titulo AS musica,
       ar.nome_artista AS artista,
       al.titulo AS album,
       m.duracao,
       m.explicita,  -- Nova coluna adicionada
       al.data_lancamento
FROM musica m
JOIN album al ON m.id_album = al.id_album
JOIN artista ar ON al.id_artista = ar.id_artista;

-- Opção 3: ALTER VIEW (SQL Server)
ALTER VIEW vw_catalogo_musicas AS
-- Nova definição
```

#### 6.2 Remover VIEWS
```sql
-- Remover uma view específica
DROP VIEW vw_catalogo_musicas;

-- Remover se existir (evita erro)
DROP VIEW IF EXISTS vw_catalogo_musicas;

-- Remover múltiplas views
DROP VIEW IF EXISTS vw_estatisticas_artistas, vw_ranking_popularidade, vw_perfil_usuarios;
```

#### 6.3 Verificar VIEWS Existentes
```sql
-- MySQL
SELECT TABLE_NAME AS view_name, TABLE_COMMENT 
FROM INFORMATION_SCHEMA.VIEWS 
WHERE TABLE_SCHEMA = DATABASE()
ORDER BY TABLE_NAME;

-- PostgreSQL
SELECT schemaname, viewname, definition
FROM pg_views 
WHERE schemaname = 'public'
ORDER BY viewname;

-- Oracle
SELECT view_name, text
FROM user_views
ORDER BY view_name;

-- SQL Server
SELECT name AS view_name, create_date, modify_date
FROM sys.views
ORDER BY name;
```

### 7. VIEWS Materializadas

#### 7.1 Conceito (PostgreSQL, Oracle)
Views materializadas armazenam fisicamente o resultado da consulta, oferecendo melhor performance para consultas complexas.

```sql
-- PostgreSQL: Criar view materializada
CREATE MATERIALIZED VIEW mv_estatisticas_diarias AS
SELECT 
    DATE(h.data_reproducao) AS data,
    COUNT(*) AS total_reproducoes,
    COUNT(DISTINCT h.id_usuario) AS usuarios_unicos,
    COUNT(DISTINCT h.id_musica) AS musicas_diferentes,
    SUM(h.duracao_ouvida) AS tempo_total_ouvido
FROM historico_reproducao h
GROUP BY DATE(h.data_reproducao)
ORDER BY data;

-- Atualizar dados da view materializada
REFRESH MATERIALIZED VIEW mv_estatisticas_diarias;

-- Criar índice na view materializada
CREATE INDEX idx_mv_estatisticas_data ON mv_estatisticas_diarias(data);
```

### 8. Boas Práticas para VIEWS

#### 8.1 Nomenclatura
```sql
-- ✅ BOM: Prefixo identificando views
CREATE VIEW vw_relatorio_vendas AS ...
CREATE VIEW vw_dashboard_executivo AS ...
CREATE VIEW vw_catalogo_produtos AS ...

-- ❌ EVITAR: Nomes confusos com tabelas
CREATE VIEW vendas AS ...  -- Pode confundir com tabela vendas
CREATE VIEW dados AS ...   -- Muito genérico
```

#### 8.2 Documentação
```sql
-- Adicionar comentários nas views
CREATE VIEW vw_ranking_popularidade AS
-- Esta view calcula a popularidade das músicas baseada em:
-- 1. Número total de reproduções
-- 2. Número de usuários únicos que ouviram
-- 3. Percentual médio da música ouvida
SELECT ...;

-- Comentário na view (alguns SGBDs)
COMMENT ON VIEW vw_ranking_popularidade IS 
'Ranking de popularidade das músicas baseado em reproduções e engajamento';
```

#### 8.3 Performance
```sql
-- ✅ BOM: Filtros específicos
CREATE VIEW vw_artistas_ativos AS
SELECT * FROM artista WHERE ativo = TRUE;

-- ❌ CUIDADO: Views muito complexas
CREATE VIEW vw_super_complexa AS
SELECT ... FROM tabela1
JOIN (SELECT ... FROM tabela2 JOIN tabela3 ...) sub1
JOIN (SELECT ... FROM tabela4 JOIN tabela5 ...) sub2
-- Pode ser muito lenta
```

### 9. Casos de Uso Avançados

#### 9.1 VIEW para Auditoria
```sql
-- VIEW: Log de atividades recentes
CREATE VIEW vw_auditoria_atividades AS
SELECT 
    'REPRODUCAO' AS tipo_evento,
    h.data_reproducao AS data_evento,
    u.nome_usuario AS usuario,
    CONCAT('Ouviu: ', m.titulo, ' de ', ar.nome_artista) AS descricao,
    h.dispositivo AS detalhes_adicionais
FROM historico_reproducao h
JOIN usuario u ON h.id_usuario = u.id_usuario
JOIN musica m ON h.id_musica = m.id_musica
JOIN album al ON m.id_album = al.id_album
JOIN artista ar ON al.id_artista = ar.id_artista
WHERE h.data_reproducao >= DATE_SUB(CURRENT_DATE, INTERVAL 7 DAY)

UNION ALL

SELECT 
    'CADASTRO_USUARIO' AS tipo_evento,
    u.data_cadastro AS data_evento,
    u.nome_usuario AS usuario,
    'Novo usuário cadastrado' AS descricao,
    u.email AS detalhes_adicionais
FROM usuario u
WHERE u.data_cadastro >= DATE_SUB(CURRENT_DATE, INTERVAL 7 DAY)

ORDER BY data_evento DESC;
```

### 10. Exercícios Práticos

Consulte a pasta `exercicios/` para atividades práticas de criação e uso de views.

## Perguntas e Respostas

### 1. Qual a diferença fundamental entre VIEW e tabela física?

**Resposta**:
**VIEW (tabela virtual)**:
```sql
CREATE VIEW vw_artistas_brasileiros AS
SELECT id_artista, nome_artista, data_formacao
FROM artista 
WHERE pais_origem = 'Brasil';
```
- **Armazenamento**: Apenas a definição SQL é salva
- **Dados**: Sempre atuais (consulta executada a cada acesso)
- **Espaço**: Não ocupa espaço adicional para dados
- **Performance**: Pode ser mais lenta para consultas complexas

**Tabela física**:
- **Armazenamento**: Dados físicamente armazenados
- **Dados**: Estáticos até serem atualizados
- **Espaço**: Ocupa espaço proporcional aos dados
- **Performance**: Geralmente mais rápida para consultas simples

### 2. Quando usar views simples vs. views materializadas?

**Resposta**:
**Views simples (virtuais)**: Para abstração e simplificação
```sql
-- View para simplificar consultas frequentes
CREATE VIEW vw_estatisticas_artista AS
SELECT 
    a.nome_artista,
    COUNT(al.id_album) as total_albums,
    COUNT(m.id_musica) as total_musicas
FROM artista a
LEFT JOIN album al ON a.id_artista = al.id_artista
LEFT JOIN musica m ON al.id_album = m.id_album
GROUP BY a.id_artista, a.nome_artista;
```
- **Uso**: Consultas que mudam frequentemente
- **Vantagem**: Sempre dados atuais
- **Desvantagem**: Recalculada a cada consulta

**Views materializadas** (quando disponível):
```sql
-- PostgreSQL/Oracle
CREATE MATERIALIZED VIEW mv_estatisticas_artista AS
SELECT 
    a.nome_artista,
    COUNT(al.id_album) as total_albums,
    SUM(hr.total_reproducoes) as total_reproducoes
FROM artista a
LEFT JOIN album al ON a.id_artista = al.id_artista
LEFT JOIN (
    SELECT id_musica, COUNT(*) as total_reproducoes
    FROM historico_reproducao
    GROUP BY id_musica
) hr ON m.id_musica = hr.id_musica
GROUP BY a.id_artista, a.nome_artista;
```
- **Uso**: Consultas complexas e custosas
- **Vantagem**: Performance superior
- **Desvantagem**: Necessita refresh periódico

### 3. Como implementar segurança usando views?

**Resposta**: Views como camada de abstração e controle:

**Restrição de colunas sensíveis**:
```sql
-- View sem informações sensíveis
CREATE VIEW vw_usuario_publico AS
SELECT 
    id_usuario,
    nome_usuario,
    data_cadastro,
    pais_origem
FROM usuario;
-- Não expõe email, senha, dados pessoais
```

**Filtros de segurança por contexto**:
```sql
-- View que só mostra playlists públicas
CREATE VIEW vw_playlists_publicas AS
SELECT 
    p.id_playlist,
    p.nome_playlist,
    u.nome_usuario as criador,
    p.data_criacao
FROM playlist p
JOIN usuario u ON p.id_usuario = u.id_usuario
WHERE p.publica = TRUE 
  AND p.ativo = TRUE;
```

**Controle de acesso por perfil**:
```sql
-- View para usuários básicos (limitada)
CREATE VIEW vw_musicas_preview AS
SELECT 
    id_musica,
    titulo,
    CASE 
        WHEN duracao > 30 THEN 30 
        ELSE duracao 
    END as duracao_preview
FROM musica
WHERE ativo = TRUE;
```

### 4. Como otimizar performance de views complexas?

**Resposta**: Estratégias de otimização:

**Filtros eficientes na view**:
```sql
-- ✅ Filtros na definição da view
CREATE VIEW vw_musicas_populares AS
SELECT m.titulo, COUNT(hr.id_reproducao) as reproducoes
FROM musica m
JOIN historico_reproducao hr ON m.id_musica = hr.id_musica
WHERE hr.data_reproducao >= CURRENT_DATE - INTERVAL '30 days'
GROUP BY m.id_musica, m.titulo
HAVING COUNT(hr.id_reproducao) > 100;
```

**Índices nas tabelas base**:
```sql
-- Garantir índices apropriados
CREATE INDEX idx_historico_data_musica ON historico_reproducao(data_reproducao, id_musica);
CREATE INDEX idx_musica_ativo ON musica(ativo);
```

**Views hierárquicas** (quebrar complexidade):
```sql
-- View base simples
CREATE VIEW vw_reproducoes_mes AS
SELECT 
    id_musica,
    COUNT(*) as total_reproducoes
FROM historico_reproducao
WHERE data_reproducao >= CURRENT_DATE - INTERVAL '30 days'
GROUP BY id_musica;

-- View que usa a anterior
CREATE VIEW vw_ranking_mensal AS
SELECT 
    m.titulo,
    rm.total_reproducoes,
    RANK() OVER (ORDER BY rm.total_reproducoes DESC) as posicao
FROM vw_reproducoes_mes rm
JOIN musica m ON rm.id_musica = m.id_musica;
```

### 5. Como criar views atualizáveis (updatable views)?

**Resposta**: Requisitos para views atualizáveis:

**View simples atualizável**:
```sql
-- View que permite INSERT/UPDATE/DELETE
CREATE VIEW vw_artistas_ativos AS
SELECT id_artista, nome_artista, biografia, pais_origem
FROM artista
WHERE ativo = TRUE;

-- Operações permitidas
UPDATE vw_artistas_ativos 
SET biografia = 'Nova biografia' 
WHERE id_artista = 1;
```

**Requisitos para ser atualizável**:
- Baseada em uma única tabela
- Sem DISTINCT, GROUP BY, HAVING
- Sem funções agregadas
- Sem UNION, INTERSECT, EXCEPT
- Sem subqueries no SELECT

**WITH CHECK OPTION para consistência**:
```sql
CREATE VIEW vw_artistas_brasileiros AS
SELECT id_artista, nome_artista, pais_origem
FROM artista
WHERE pais_origem = 'Brasil'
WITH CHECK OPTION;

-- ❌ Esta operação falhará:
UPDATE vw_artistas_brasileiros 
SET pais_origem = 'Argentina' 
WHERE id_artista = 1;
```

### 6. Como usar views para análise de dados e reporting?

**Resposta**: Views como camada de análise:

**Agregações pré-calculadas**:
```sql
-- Dashboard de estatísticas gerais
CREATE VIEW vw_dashboard_geral AS
SELECT 
    'Usuários' as metrica,
    COUNT(*) as total,
    COUNT(CASE WHEN ativo = TRUE THEN 1 END) as ativos
FROM usuario
UNION ALL
SELECT 
    'Artistas' as metrica,
    COUNT(*) as total,
    COUNT(CASE WHEN ativo = TRUE THEN 1 END) as ativos
FROM artista
UNION ALL
SELECT 
    'Músicas' as metrica,
    COUNT(*) as total,
    COUNT(CASE WHEN ativo = TRUE THEN 1 END) as ativos
FROM musica;
```

**Métricas de negócio**:
```sql
-- KPIs de engajamento
CREATE VIEW vw_kpis_engajamento AS
SELECT 
    DATE_TRUNC('month', hr.data_reproducao) as mes,
    COUNT(DISTINCT hr.id_usuario) as usuarios_ativos,
    COUNT(*) as total_reproducoes,
    COUNT(*) / COUNT(DISTINCT hr.id_usuario) as reproducoes_por_usuario,
    COUNT(DISTINCT hr.id_musica) as musicas_unicas_tocadas
FROM historico_reproducao hr
WHERE hr.data_reproducao >= CURRENT_DATE - INTERVAL '12 months'
GROUP BY DATE_TRUNC('month', hr.data_reproducao)
ORDER BY mes;
```

### 7. Quais as limitações e cuidados ao usar views?

**Resposta**: Considerações importantes:

**Limitações de performance**:
```sql
-- ❌ Evitar views com muitos JOINs aninhados
CREATE VIEW vw_complexa_demais AS
SELECT /* muitos campos */
FROM tabela1 t1
JOIN tabela2 t2 ON /* condição */
JOIN (
    SELECT /* subconsulta complexa */
    FROM tabela3 t3
    JOIN tabela4 t4 ON /* condição */
    GROUP BY /* múltiplas colunas */
) sub ON /* condição */
WHERE /* múltiplas condições */;
```

**Dependências e manutenção**:
```sql
-- Cuidado: alterar tabela base pode quebrar view
-- Se remover coluna de artista.biografia:
CREATE VIEW vw_artista_info AS
SELECT nome_artista, biografia  -- Falhará se biografia for removida
FROM artista;
```

**Limitações para atualizações**:
- Views com JOINs são geralmente read-only
- INSTEAD OF triggers podem contornar limitações
- Atualizações podem ser ambíguas

**Boas práticas**:
- Nomeação consistente (prefixo vw_)
- Documentação da finalidade
- Monitoramento de performance
- Revisão periódica de uso
- Evitar views sobre views em excesso

## Referências Bibliográficas

1. **Date, C.J.** (2012). *SQL and Relational Theory*. 2nd Edition. O'Reilly Media. Capítulo 9.

2. **Beaulieu, A.** (2020). *Learning SQL: Master SQL Fundamentals*. 3rd Edition. O'Reilly Media. Capítulo 14.

3. **Celko, J.** (2010). *Joe Celko's SQL for Smarties*. 4th Edition. Morgan Kaufmann. Capítulos sobre Views.

4. **Oracle Corporation** (2021). *Oracle Database SQL Language Reference*. Seção CREATE VIEW.

5. **PostgreSQL Global Development Group** (2023). *PostgreSQL Documentation*. Seção sobre Views e Materialized Views.

---

**Módulo Anterior**: [15 - Operações com Conjuntos](../15-operacoes-conjuntos/README.md)

**Parabéns!** Você concluiu o curso completo de SQL. Views são uma ferramenta poderosa que encapsula a complexidade e fornece interfaces simples para dados complexos. Continue praticando e explorando recursos avançados de seu SGBD específico.