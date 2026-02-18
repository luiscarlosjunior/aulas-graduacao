-- =====================================================
-- SISTEMA MUSISTREAM - ROTEIRO COMPLETO GUIADO
-- =====================================================
-- Script guiado que passa por todos os tópicos do curso
-- Demonstra a aplicação prática de todos os módulos
-- =====================================================

-- Configurações iniciais
SET ECHO ON
SET FEEDBACK ON
SET PAGESIZE 40
SET LINESIZE 150
SET SERVEROUTPUT ON

PROMPT =====================================================
PROMPT BEM-VINDO AO ROTEIRO COMPLETO DO MUSISTREAM!
PROMPT =====================================================
PROMPT Este script demonstra na prática todos os conceitos
PROMPT dos 16 módulos do curso de SQL através de um sistema
PROMPT de streaming de música similar ao Spotify.
PROMPT =====================================================

-- =====================================================
-- MÓDULO 01: INTRODUÇÃO À MODELAGEM DE DADOS
-- =====================================================

PROMPT
PROMPT ===== MÓDULO 01: MODELAGEM DE DADOS =====
PROMPT
PROMPT O MusiStream foi modelado seguindo os princípios de:
PROMPT - Normalização (1FN, 2FN, 3FN)
PROMPT - Relacionamentos bem definidos
PROMPT - Integridade referencial
PROMPT - Entidades e atributos claros

-- Mostrar estrutura das principais entidades
SELECT 
    'USUÁRIO' AS entidade,
    'Dados pessoais, preferências e assinatura' AS descricao
FROM DUAL
UNION ALL SELECT 'ARTISTA', 'Informações dos criadores de conteúdo'
UNION ALL SELECT 'ÁLBUM', 'Agrupamento de músicas'
UNION ALL SELECT 'MÚSICA', 'Conteúdo principal da plataforma'
UNION ALL SELECT 'PLAYLIST', 'Coleções personalizadas pelos usuários'
UNION ALL SELECT 'HISTÓRICO', 'Log de todas as reproduções'
UNION ALL SELECT 'ASSINATURA', 'Planos e pagamentos dos usuários';

PROMPT
PROMPT Relacionamentos principais:
PROMPT - USUÁRIO (1:N) PLAYLIST
PROMPT - USUÁRIO (1:N) HISTÓRICO
PROMPT - ARTISTA (1:N) ÁLBUM
PROMPT - ÁLBUM (1:N) MÚSICA
PROMPT - PLAYLIST (N:M) MÚSICA

-- =====================================================
-- MÓDULO 02: HISTÓRIA E EVOLUÇÃO DO SQL
-- =====================================================

PROMPT
PROMPT ===== MÓDULO 02: HISTÓRIA DO SQL =====
PROMPT
PROMPT O MusiStream utiliza recursos SQL modernos:
PROMPT - SQL-86: SELECT, INSERT, UPDATE, DELETE básicos
PROMPT - SQL-92: JOINs externos, funções de string, CASE
PROMPT - SQL-99: Expressões regulares, arrays
PROMPT - SQL:2003: Window functions, recursos XML
PROMPT - SQL:2016: Suporte JSON (para metadados)

-- Demonstrar evolução com exemplo prático
SELECT 
    -- SQL básico (SQL-86)
    u.nome_usuario,
    -- SQL-92: CASE e JOINs
    CASE 
        WHEN a.status_assinatura = 'ATIVA' THEN 'Assinante'
        ELSE 'Não assinante'
    END AS status,
    -- SQL:2003: Window function
    RANK() OVER (ORDER BY COUNT(hr.id_historico) DESC) AS ranking_atividade
FROM usuario u
LEFT JOIN assinatura a ON u.id_usuario = a.id_usuario
LEFT JOIN historico_reproducao hr ON u.id_usuario = hr.id_usuario
GROUP BY u.id_usuario, u.nome_usuario, a.status_assinatura
ORDER BY ranking_atividade;

-- =====================================================
-- MÓDULO 03: INTERFACE SQL PLUS, TABELAS E REGRAS
-- =====================================================

PROMPT
PROMPT ===== MÓDULO 03: CRIAÇÃO DE TABELAS E REGRAS =====
PROMPT
PROMPT Demonstração das estruturas criadas:

-- Mostrar algumas constraints importantes
SELECT 
    constraint_name,
    constraint_type,
    table_name,
    CASE constraint_type
        WHEN 'P' THEN 'Primary Key'
        WHEN 'R' THEN 'Foreign Key'
        WHEN 'C' THEN 'Check Constraint'
        WHEN 'U' THEN 'Unique'
    END AS tipo_constraint
FROM user_constraints
WHERE table_name IN ('USUARIO', 'MUSICA', 'PLAYLIST', 'HISTORICO_REPRODUCAO')
  AND constraint_type IN ('P', 'R', 'C', 'U')
ORDER BY table_name, constraint_type;

PROMPT
PROMPT Exemplo de regras de negócio implementadas:
PROMPT - Duração de música entre 0 e 3600 segundos
PROMPT - Email deve conter @ e ponto
PROMPT - Status de assinatura com valores válidos
PROMPT - Idade mínima de 13 anos para usuários

-- =====================================================
-- MÓDULO 04: TRABALHANDO COM ESTRUTURA DE TABELAS
-- =====================================================

PROMPT
PROMPT ===== MÓDULO 04: ESTRUTURA DE TABELAS =====
PROMPT
PROMPT Análise das estruturas criadas:

-- Mostrar estrutura de tabela importante
DESCRIBE usuario;

PROMPT
PROMPT Tipos de dados utilizados:
SELECT 
    column_name,
    data_type,
    data_length,
    nullable,
    data_default
FROM user_tab_columns
WHERE table_name = 'MUSICA'
ORDER BY column_id;

-- =====================================================
-- MÓDULO 05: RELACIONAMENTOS E CONSTRAINTS
-- =====================================================

PROMPT
PROMPT ===== MÓDULO 05: RELACIONAMENTOS =====
PROMPT
PROMPT Demonstração de integridade referencial:

-- Tentar inserir música em álbum inexistente (deve falhar)
BEGIN
    INSERT INTO musica (id_musica, titulo, duracao, numero_faixa, id_album)
    VALUES (999, 'Teste Integridade', 180, 1, 99999);
    DBMS_OUTPUT.PUT_LINE('ERRO: Inserção deveria ter falhado!');
EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('CORRETO: Integridade referencial funcionando - ' || SQLERRM);
END;
/

-- Mostrar relacionamentos ativos
SELECT 
    a.constraint_name,
    a.table_name AS tabela_filha,
    a.column_name AS coluna_filha,
    c_pk.table_name AS tabela_pai,
    c_pk.column_name AS coluna_pai
FROM user_cons_columns a
JOIN user_constraints b ON a.constraint_name = b.constraint_name
JOIN user_constraints b_pk ON b.r_constraint_name = b_pk.constraint_name
JOIN user_cons_columns c_pk ON b_pk.constraint_name = c_pk.constraint_name
WHERE b.constraint_type = 'R'
  AND a.table_name IN ('MUSICA', 'ALBUM', 'PLAYLIST_MUSICA', 'HISTORICO_REPRODUCAO')
ORDER BY a.table_name;

-- =====================================================
-- MÓDULO 06: ALTERAÇÃO DE ESTRUTURA
-- =====================================================

PROMPT
PROMPT ===== MÓDULO 06: ALTERAÇÃO DE ESTRUTURA =====
PROMPT
PROMPT Demonstrando DDL para alterações:

-- Adicionar coluna temporária para demonstração
ALTER TABLE usuario ADD (coluna_temp VARCHAR2(50));

-- Verificar que foi adicionada
SELECT column_name 
FROM user_tab_columns 
WHERE table_name = 'USUARIO' 
  AND column_name = 'COLUNA_TEMP';

-- Remover a coluna
ALTER TABLE usuario DROP COLUMN coluna_temp;

PROMPT Coluna temporária adicionada e removida com sucesso!

-- =====================================================
-- MÓDULO 07: MANIPULAÇÃO DE DADOS - INSERT PARTE I
-- =====================================================

PROMPT
PROMPT ===== MÓDULO 07: INSERÇÃO DE DADOS BÁSICA =====
PROMPT
PROMPT Demonstrando diferentes formas de INSERT:

-- INSERT simples
INSERT INTO genero (id_genero, nome_genero, descricao)
VALUES (seq_genero.NEXTVAL, 'Exemplo Temporário', 'Gênero para demonstração');

-- INSERT com SELECT
INSERT INTO genero (id_genero, nome_genero, descricao)
SELECT seq_genero.NEXTVAL, 'Exemplo 2', 'Outro exemplo'
FROM DUAL
WHERE NOT EXISTS (SELECT 1 FROM genero WHERE nome_genero = 'Exemplo 2');

-- Verificar inserções
SELECT COUNT(*) AS generos_exemplo
FROM genero 
WHERE nome_genero LIKE 'Exemplo%';

-- Limpar dados de exemplo
DELETE FROM genero WHERE nome_genero LIKE 'Exemplo%';

-- =====================================================
-- MÓDULO 08: MANIPULAÇÃO DE DADOS - INSERT PARTE II
-- =====================================================

PROMPT
PROMPT ===== MÓDULO 08: INSERÇÃO AVANÇADA =====
PROMPT
PROMPT Demonstrando INSERT com múltiplas tabelas:

-- Simular adição de um novo artista com álbum e música
SAVEPOINT antes_insercao_complexa;

-- Inserir artista
INSERT INTO artista (id_artista, nome_artista, pais_origem)
VALUES (seq_artista.NEXTVAL, 'Artista Demo', 'Brasil');

-- Capturar ID do artista inserido
SELECT seq_artista.CURRVAL AS ultimo_artista_id FROM DUAL;

-- Inserir álbum
INSERT INTO album (id_album, titulo, data_lancamento, numero_faixas, duracao_total, id_artista)
VALUES (seq_album.NEXTVAL, 'Album Demo', SYSDATE, 1, 240, seq_artista.CURRVAL);

-- Inserir música
INSERT INTO musica (id_musica, titulo, duracao, numero_faixa, id_album, id_genero)
VALUES (seq_musica.NEXTVAL, 'Música Demo', 240, 1, seq_album.CURRVAL, 
        (SELECT id_genero FROM genero WHERE nome_genero = 'Pop'));

PROMPT Inserção complexa realizada com sucesso!

-- Verificar dados inseridos
SELECT 
    ar.nome_artista,
    al.titulo AS album,
    m.titulo AS musica
FROM artista ar
JOIN album al ON ar.id_artista = al.id_artista
JOIN musica m ON al.id_album = m.id_album
WHERE ar.nome_artista = 'Artista Demo';

-- Reverter para não poluir base
ROLLBACK TO antes_insercao_complexa;

-- =====================================================
-- MÓDULO 09: CONTROLE DE TRANSAÇÕES
-- =====================================================

PROMPT
PROMPT ===== MÓDULO 09: CONTROLE DE TRANSAÇÕES =====
PROMPT
PROMPT Demonstrando controle transacional:

-- Transação com controle de erro
SAVEPOINT inicio_transacao;

DECLARE
    v_erro NUMBER := 0;
BEGIN
    -- Simular operação que pode falhar
    INSERT INTO usuario (id_usuario, nome_usuario, email, senha)
    VALUES (seq_usuario.NEXTVAL, 'Usuario Teste', 'teste@email.com', 'senha123');
    
    -- Verificar se inserção foi bem-sucedida
    IF SQL%ROWCOUNT = 1 THEN
        DBMS_OUTPUT.PUT_LINE('Usuário inserido com sucesso');
        -- Em cenário real, continuaríamos com outras operações
        COMMIT;
    ELSE
        DBMS_OUTPUT.PUT_LINE('Falha na inserção');
        ROLLBACK TO inicio_transacao;
    END IF;
    
EXCEPTION
    WHEN DUP_VAL_ON_INDEX THEN
        DBMS_OUTPUT.PUT_LINE('Email já existe no sistema');
        ROLLBACK TO inicio_transacao;
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Erro inesperado: ' || SQLERRM);
        ROLLBACK TO inicio_transacao;
END;
/

-- Limpar usuário de teste
DELETE FROM usuario WHERE email = 'teste@email.com';

-- =====================================================
-- MÓDULO 10: RELATÓRIOS COM FILTROS E OPERADORES
-- =====================================================

PROMPT
PROMPT ===== MÓDULO 10: FILTROS E OPERADORES =====
PROMPT
PROMPT Demonstrando operadores e filtros:

-- Operadores relacionais e lógicos
SELECT 
    nome_usuario,
    pais,
    data_cadastro,
    CASE 
        WHEN data_cadastro >= SYSDATE - 30 THEN 'Novo'
        WHEN data_cadastro >= SYSDATE - 90 THEN 'Recente'
        ELSE 'Antigo'
    END AS categoria_usuario
FROM usuario
WHERE ativo = 'S'
  AND pais IN ('Brasil', 'Portugal')
  AND data_cadastro IS NOT NULL
ORDER BY data_cadastro DESC;

-- Operadores LIKE e BETWEEN
SELECT 
    titulo,
    nome_artista,
    duracao
FROM vw_musicas_completas
WHERE titulo LIKE '%Love%'
   OR nome_artista LIKE 'B%'
  AND duracao BETWEEN 180 AND 300
ORDER BY duracao;

-- =====================================================
-- MÓDULO 11: OPERADORES ARITMÉTICOS
-- =====================================================

PROMPT
PROMPT ===== MÓDULO 11: OPERADORES ARITMÉTICOS =====
PROMPT
PROMPT Cálculos e expressões matemáticas:

-- Análise financeira com cálculos
SELECT 
    t.nome_plano,
    t.preco_mensal,
    COUNT(a.id_assinatura) AS assinantes,
    t.preco_mensal * COUNT(a.id_assinatura) AS receita_mensal,
    t.preco_mensal * COUNT(a.id_assinatura) * 12 AS receita_anual_estimada,
    ROUND(
        t.preco_mensal * COUNT(a.id_assinatura) * 100.0 / 
        SUM(t.preco_mensal * COUNT(a.id_assinatura)) OVER (), 2
    ) AS percentual_receita
FROM tipo_assinatura t
LEFT JOIN assinatura a ON t.id_tipo_assinatura = a.id_tipo_assinatura 
    AND a.status_assinatura = 'ATIVA'
GROUP BY t.id_tipo_assinatura, t.nome_plano, t.preco_mensal
ORDER BY receita_mensal DESC;

-- Cálculos de tempo e duração
SELECT 
    nome_playlist,
    total_musicas,
    duracao_total,
    FLOOR(duracao_total / 3600) AS horas,
    FLOOR(MOD(duracao_total, 3600) / 60) AS minutos,
    MOD(duracao_total, 60) AS segundos,
    ROUND(duracao_total / 60.0 / total_musicas, 2) AS duracao_media_minutos
FROM playlist
WHERE total_musicas > 0
ORDER BY duracao_total DESC;

-- =====================================================
-- MÓDULO 12: FUNÇÕES DE BANCO DE DADOS
-- =====================================================

PROMPT
PROMPT ===== MÓDULO 12: FUNÇÕES DE BANCO =====
PROMPT
PROMPT Demonstrando diferentes tipos de funções:

-- Funções de string
SELECT 
    UPPER(nome_artista) AS nome_maiusculo,
    LOWER(pais_origem) AS pais_minusculo,
    INITCAP(nome_artista) AS nome_formatado,
    LENGTH(nome_artista) AS tamanho_nome,
    SUBSTR(nome_artista, 1, 10) AS primeiros_10_chars
FROM artista
WHERE nome_artista IS NOT NULL
ORDER BY tamanho_nome DESC;

-- Funções de data
SELECT 
    nome_usuario,
    data_cadastro,
    SYSDATE AS data_atual,
    TRUNC(SYSDATE - data_cadastro) AS dias_cadastrado,
    MONTHS_BETWEEN(SYSDATE, data_cadastro) AS meses_cadastrado,
    TO_CHAR(data_cadastro, 'DD/MM/YYYY') AS data_formatada,
    TO_CHAR(data_cadastro, 'Day') AS dia_semana_cadastro
FROM usuario
WHERE data_cadastro IS NOT NULL
ORDER BY data_cadastro;

-- Funções agregadas
SELECT 
    'Estatísticas Gerais' AS categoria,
    COUNT(*) AS total_musicas,
    AVG(duracao) AS duracao_media,
    MIN(duracao) AS menor_duracao,
    MAX(duracao) AS maior_duracao,
    SUM(total_reproducoes) AS reproducoes_totais,
    STDDEV(duracao) AS desvio_padrao_duracao
FROM musica;

-- =====================================================
-- MÓDULO 13: SUBCONSULTAS
-- =====================================================

PROMPT
PROMPT ===== MÓDULO 13: SUBCONSULTAS =====
PROMPT
PROMPT Diferentes tipos de subconsultas:

-- Subconsulta escalar
SELECT 
    nome_usuario,
    pais,
    (SELECT COUNT(*) 
     FROM historico_reproducao hr 
     WHERE hr.id_usuario = u.id_usuario) AS total_reproducoes,
    (SELECT COUNT(*) 
     FROM playlist p 
     WHERE p.id_usuario = u.id_usuario) AS total_playlists
FROM usuario u
ORDER BY total_reproducoes DESC;

-- Subconsulta com EXISTS
SELECT nome_artista, pais_origem
FROM artista ar
WHERE EXISTS (
    SELECT 1 
    FROM album al 
    JOIN musica m ON al.id_album = m.id_album
    WHERE al.id_artista = ar.id_artista 
    AND m.total_reproducoes > 3
)
ORDER BY nome_artista;

-- Subconsulta com IN
SELECT titulo, nome_artista
FROM vw_musicas_completas
WHERE id_musica IN (
    SELECT id_musica 
    FROM historico_reproducao
    GROUP BY id_musica
    HAVING COUNT(*) >= 2
)
ORDER BY total_reproducoes DESC;

-- =====================================================
-- MÓDULO 14: MÚLTIPLAS TABELAS E JOINS
-- =====================================================

PROMPT
PROMPT ===== MÓDULO 14: JOINS =====
PROMPT
PROMPT Demonstrando diferentes tipos de JOIN:

-- INNER JOIN
SELECT 
    u.nome_usuario,
    p.nome_playlist,
    COUNT(pm.id_musica) AS musicas_na_playlist
FROM usuario u
INNER JOIN playlist p ON u.id_usuario = p.id_usuario
INNER JOIN playlist_musica pm ON p.id_playlist = pm.id_playlist
GROUP BY u.id_usuario, u.nome_usuario, p.id_playlist, p.nome_playlist
ORDER BY musicas_na_playlist DESC;

-- LEFT JOIN (mostra todos os usuários, mesmo sem playlists)
SELECT 
    u.nome_usuario,
    COUNT(p.id_playlist) AS total_playlists,
    COUNT(hr.id_historico) AS total_reproducoes
FROM usuario u
LEFT JOIN playlist p ON u.id_usuario = p.id_usuario
LEFT JOIN historico_reproducao hr ON u.id_usuario = hr.id_usuario
GROUP BY u.id_usuario, u.nome_usuario
ORDER BY total_playlists DESC, total_reproducoes DESC;

-- FULL OUTER JOIN (exemplo conceitual)
SELECT 
    COALESCE(g.nome_genero, 'Sem gênero') AS genero,
    COUNT(m.id_musica) AS total_musicas
FROM genero g
FULL OUTER JOIN musica m ON g.id_genero = m.id_genero
GROUP BY g.id_genero, g.nome_genero
ORDER BY total_musicas DESC;

-- =====================================================
-- MÓDULO 15: OPERAÇÕES COM CONJUNTOS
-- =====================================================

PROMPT
PROMPT ===== MÓDULO 15: OPERAÇÕES COM CONJUNTOS =====
PROMPT
PROMPT Demonstrando UNION, INTERSECT, EXCEPT:

-- UNION: Combinar diferentes consultas
SELECT 'Artista' AS tipo, nome_artista AS nome, pais_origem AS pais FROM artista
UNION
SELECT 'Usuário' AS tipo, nome_usuario AS nome, pais AS pais FROM usuario
ORDER BY tipo, nome;

-- INTERSECT: Países que têm tanto artistas quanto usuários
SELECT pais_origem AS pais FROM artista WHERE pais_origem IS NOT NULL
INTERSECT
SELECT pais FROM usuario WHERE pais IS NOT NULL;

-- MINUS/EXCEPT: Países com usuários mas sem artistas
SELECT pais FROM usuario WHERE pais IS NOT NULL
MINUS
SELECT pais_origem FROM artista WHERE pais_origem IS NOT NULL;

-- =====================================================
-- MÓDULO 16: VIEWS
-- =====================================================

PROMPT
PROMPT ===== MÓDULO 16: VIEWS =====
PROMPT
PROMPT Demonstrando uso das views criadas:

-- Consultar view de dashboard
SELECT * FROM vw_dashboard_executivo;

-- Consultar view de top músicas
SELECT * FROM vw_top_musicas WHERE ROWNUM <= 5;

-- Consultar view de estatísticas de usuários
SELECT 
    nome_usuario,
    total_playlists,
    musicas_diferentes_ouvidas,
    total_reproducoes
FROM vw_stats_usuarios
WHERE total_reproducoes > 0
ORDER BY total_reproducoes DESC;

-- =====================================================
-- CENÁRIO PRÁTICO: ANÁLISE DE NEGÓCIO COMPLETA
-- =====================================================

PROMPT
PROMPT ===== CENÁRIO PRÁTICO: ANÁLISE COMPLETA =====
PROMPT
PROMPT Simulando reunião executiva com dados do MusiStream:

-- 1. KPIs Principais
PROMPT
PROMPT 1. INDICADORES PRINCIPAIS:
SELECT 
    'Total de Usuários Ativos' AS kpi,
    TO_CHAR(COUNT(*)) AS valor
FROM usuario WHERE ativo = 'S'
UNION ALL
SELECT 'Receita Mensal Estimada (R$)', 
       TO_CHAR(SUM(t.preco_mensal), 'FM999G999D00')
FROM assinatura a 
JOIN tipo_assinatura t ON a.id_tipo_assinatura = t.id_tipo_assinatura
WHERE a.status_assinatura = 'ATIVA'
UNION ALL
SELECT 'Reproduções Última Semana',
       TO_CHAR(COUNT(*))
FROM historico_reproducao 
WHERE data_reproducao >= SYSDATE - 7
UNION ALL
SELECT 'Músicas no Catálogo',
       TO_CHAR(COUNT(*))
FROM musica;

-- 2. Top Conteúdos
PROMPT
PROMPT 2. TOP CONTEÚDOS:
SELECT 
    'Top Música' AS tipo,
    titulo AS nome,
    TO_CHAR(total_reproducoes) AS metrica
FROM musica 
WHERE total_reproducoes = (SELECT MAX(total_reproducoes) FROM musica)
UNION ALL
SELECT 'Top Artista',
       ar.nome_artista,
       TO_CHAR(SUM(m.total_reproducoes))
FROM artista ar
JOIN album al ON ar.id_artista = al.id_artista
JOIN musica m ON al.id_album = m.id_album
GROUP BY ar.id_artista, ar.nome_artista
ORDER BY TO_NUMBER(metrica) DESC
FETCH FIRST 1 ROWS ONLY;

-- 3. Análise de Usuários
PROMPT
PROMPT 3. ANÁLISE DE USUÁRIOS:
SELECT 
    pais,
    COUNT(*) AS total_usuarios,
    ROUND(COUNT(*) * 100.0 / SUM(COUNT(*)) OVER (), 2) AS percentual,
    COUNT(CASE WHEN ativo = 'S' THEN 1 END) AS usuarios_ativos
FROM usuario
GROUP BY pais
ORDER BY total_usuarios DESC;

-- 4. Tendências Temporais
PROMPT
PROMPT 4. ATIVIDADE POR DIA (última semana):
SELECT 
    TO_CHAR(data_reproducao, 'DD/MM') AS dia,
    COUNT(*) AS reproducoes
FROM historico_reproducao
WHERE data_reproducao >= SYSDATE - 7
GROUP BY TO_CHAR(data_reproducao, 'DD/MM')
ORDER BY dia;

-- =====================================================
-- CONCLUSÃO E PRÓXIMOS PASSOS
-- =====================================================

PROMPT
PROMPT =====================================================
PROMPT ROTEIRO COMPLETO CONCLUÍDO COM SUCESSO!
PROMPT =====================================================
PROMPT
PROMPT Conceitos demonstrados:
PROMPT ✓ Modelagem de dados (Módulo 1)
PROMPT ✓ História SQL (Módulo 2) 
PROMPT ✓ Criação de tabelas (Módulo 3)
PROMPT ✓ Estrutura de dados (Módulo 4)
PROMPT ✓ Relacionamentos (Módulo 5)
PROMPT ✓ Alteração DDL (Módulo 6)
PROMPT ✓ Inserção básica (Módulo 7)
PROMPT ✓ Inserção avançada (Módulo 8)
PROMPT ✓ Transações (Módulo 9)
PROMPT ✓ Filtros e operadores (Módulo 10)
PROMPT ✓ Operadores aritméticos (Módulo 11)
PROMPT ✓ Funções de banco (Módulo 12)
PROMPT ✓ Subconsultas (Módulo 13)
PROMPT ✓ Múltiplas tabelas (Módulo 14)
PROMPT ✓ Operações conjuntos (Módulo 15)
PROMPT ✓ Views (Módulo 16)
PROMPT
PROMPT Próximos passos recomendados:
PROMPT 1. Explore scripts específicos (01-06)
PROMPT 2. Modifique consultas para seus cenários
PROMPT 3. Crie suas próprias análises
PROMPT 4. Pratique com dados reais
PROMPT 5. Consulte pasta 'solucoes/' para detalhes
PROMPT
PROMPT Parabéns por completar o curso de SQL!
PROMPT =====================================================