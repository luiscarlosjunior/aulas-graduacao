-- =====================================================
-- CONTROLE DE TRANSAÇÕES E RELATÓRIOS - SISTEMA MUSISTREAM
-- Módulo 09: SELECT, Filtros, JOINs, COMMIT, ROLLBACK, SAVEPOINT e Relatórios
-- =====================================================

-- =====================================================
-- PARTE 1: CONSULTAS SELECT - DO SIMPLES AO AVANÇADO
-- =====================================================

-- =====================================================
-- 1. CONSULTAS SIMPLES (UMA TABELA)
-- =====================================================

-- 1.1 Selecionar todas as colunas
SELECT * FROM artista;
SELECT * FROM genero;
SELECT * FROM album;

-- 1.2 Selecionar colunas específicas
SELECT nome_artista, pais_origem FROM artista;
SELECT titulo, ano_lancamento FROM album;
SELECT nome_usuario, email FROM usuario;

-- 1.3 Usar aliases para colunas
SELECT 
    nome_artista AS "Nome do Artista",
    pais_origem AS "País",
    data_formacao AS "Ano de Formação"
FROM artista;

-- 1.4 Ordenar resultados
SELECT nome_artista, pais_origem
FROM artista
ORDER BY nome_artista;

SELECT titulo, ano_lancamento
FROM album
ORDER BY ano_lancamento DESC;

-- 1.5 Eliminar duplicatas
SELECT DISTINCT pais_origem
FROM artista
ORDER BY pais_origem;

-- 1.6 Limitar resultados
SELECT nome_artista, pais_origem
FROM artista
WHERE ROWNUM <= 10;

-- =====================================================
-- 2. CONSULTAS COM FILTROS (WHERE)
-- =====================================================

-- 2.1 Operadores de comparação
-- Igualdade
SELECT nome_artista, pais_origem
FROM artista
WHERE pais_origem = 'Brasil';

-- Diferente
SELECT nome_artista, pais_origem
FROM artista
WHERE pais_origem <> 'Brasil';

-- Maior que
SELECT titulo, ano_lancamento
FROM album
WHERE ano_lancamento > 2000;

-- Menor ou igual
SELECT titulo, duracao
FROM musica
WHERE duracao <= 180;

-- 2.2 Operadores lógicos (AND, OR, NOT)
-- AND: todas as condições devem ser verdadeiras
SELECT nome_artista, pais_origem, data_formacao
FROM artista
WHERE pais_origem = 'Brasil' 
  AND data_formacao > TO_DATE('2000-01-01', 'YYYY-MM-DD');

-- OR: pelo menos uma condição deve ser verdadeira
SELECT nome_artista, pais_origem
FROM artista
WHERE pais_origem = 'Brasil' OR pais_origem = 'Portugal';

-- NOT: inverte a condição
SELECT nome_artista, pais_origem
FROM artista
WHERE NOT (pais_origem = 'Brasil');

-- 2.3 Operador IN (lista de valores)
SELECT nome_artista, pais_origem
FROM artista
WHERE pais_origem IN ('Brasil', 'Portugal', 'Argentina');

SELECT titulo, ano_lancamento
FROM album
WHERE ano_lancamento IN (1990, 1995, 2000, 2005);

-- 2.4 Operador BETWEEN (intervalo)
SELECT titulo, ano_lancamento
FROM album
WHERE ano_lancamento BETWEEN 2000 AND 2010
ORDER BY ano_lancamento;

SELECT titulo, duracao
FROM musica
WHERE duracao BETWEEN 180 AND 300;

-- 2.5 Operador LIKE (busca por padrões)
-- Começa com 'The'
SELECT nome_artista
FROM artista
WHERE nome_artista LIKE 'The%';

-- Termina com 'Band'
SELECT nome_artista
FROM artista
WHERE nome_artista LIKE '%Band';

-- Contém 'Rock'
SELECT nome_artista
FROM artista
WHERE nome_artista LIKE '%Rock%';

-- 2.6 Operador IS NULL / IS NOT NULL
SELECT nome_artista, data_formacao
FROM artista
WHERE data_formacao IS NULL;

SELECT nome_artista, data_formacao
FROM artista
WHERE data_formacao IS NOT NULL
ORDER BY data_formacao;

-- =====================================================
-- 3. CONSULTAS COM JOINS (MÚLTIPLAS TABELAS)
-- =====================================================

-- 3.1 INNER JOIN - correspondência em ambas as tabelas
-- Álbuns com nome do artista
SELECT 
    a.nome_artista AS "Artista",
    al.titulo AS "Álbum",
    al.ano_lancamento AS "Ano"
FROM artista a
INNER JOIN album al ON a.id_artista = al.id_artista
ORDER BY a.nome_artista, al.ano_lancamento;

-- Músicas com álbum e artista
SELECT 
    ar.nome_artista AS "Artista",
    al.titulo AS "Álbum",
    m.titulo AS "Música",
    m.duracao AS "Duração"
FROM musica m
INNER JOIN album al ON m.id_album = al.id_album
INNER JOIN artista ar ON al.id_artista = ar.id_artista
ORDER BY ar.nome_artista, al.titulo, m.numero_faixa;

-- Músicas de um gênero específico
SELECT 
    m.titulo AS "Música",
    g.nome_genero AS "Gênero",
    m.duracao AS "Duração"
FROM musica m
INNER JOIN genero g ON m.id_genero = g.id_genero
WHERE g.nome_genero = 'Rock'
ORDER BY m.titulo;

-- 3.2 LEFT JOIN - todos da esquerda, mesmo sem correspondência
-- Todos os artistas com contagem de álbuns (incluindo sem álbuns)
SELECT 
    a.nome_artista AS "Artista",
    COUNT(al.id_album) AS "Total Álbuns"
FROM artista a
LEFT JOIN album al ON a.id_artista = al.id_artista
GROUP BY a.id_artista, a.nome_artista
ORDER BY COUNT(al.id_album) DESC;

-- Artistas sem álbuns cadastrados
SELECT 
    a.nome_artista AS "Artista",
    a.pais_origem AS "País"
FROM artista a
LEFT JOIN album al ON a.id_artista = al.id_artista
WHERE al.id_album IS NULL;

-- Usuários com total de playlists
SELECT 
    u.nome_usuario AS "Usuário",
    COUNT(p.id_playlist) AS "Total Playlists"
FROM usuario u
LEFT JOIN playlist p ON u.id_usuario = p.id_usuario
GROUP BY u.id_usuario, u.nome_usuario
ORDER BY COUNT(p.id_playlist) DESC;

-- 3.3 RIGHT JOIN - todos da direita
SELECT 
    g.nome_genero AS "Gênero",
    COUNT(m.id_musica) AS "Total Músicas"
FROM musica m
RIGHT JOIN genero g ON m.id_genero = g.id_genero
GROUP BY g.id_genero, g.nome_genero
ORDER BY COUNT(m.id_musica) DESC;

-- 3.4 Consultas complexas com múltiplos JOINs
-- Relatório completo de músicas
SELECT 
    ar.nome_artista AS "Artista",
    al.titulo AS "Álbum",
    m.titulo AS "Música",
    g.nome_genero AS "Gênero",
    m.duracao AS "Duração",
    COUNT(hr.id_historico) AS "Reproduções"
FROM musica m
INNER JOIN album al ON m.id_album = al.id_album
INNER JOIN artista ar ON al.id_artista = ar.id_artista
INNER JOIN genero g ON m.id_genero = g.id_genero
LEFT JOIN historico_reproducao hr ON m.id_musica = hr.id_musica
GROUP BY ar.nome_artista, al.titulo, m.titulo, g.nome_genero, m.duracao
ORDER BY COUNT(hr.id_historico) DESC;

-- Top 10 músicas mais tocadas
SELECT 
    ar.nome_artista AS "Artista",
    m.titulo AS "Música",
    g.nome_genero AS "Gênero",
    COUNT(hr.id_historico) AS "Reproduções"
FROM musica m
INNER JOIN album al ON m.id_album = al.id_album
INNER JOIN artista ar ON al.id_artista = ar.id_artista
INNER JOIN genero g ON m.id_genero = g.id_genero
INNER JOIN historico_reproducao hr ON m.id_musica = hr.id_musica
GROUP BY ar.nome_artista, m.titulo, g.nome_genero, m.id_musica
ORDER BY COUNT(hr.id_historico) DESC
FETCH FIRST 10 ROWS ONLY;

-- =====================================================
-- PARTE 2: TRANSAÇÕES E CONTROLE
-- =====================================================

-- =====================================================
-- 4. DEMONSTRAÇÃO DE TRANSAÇÕES BÁSICAS
-- =====================================================

-- Exemplo de transação com COMMIT
BEGIN
    INSERT INTO usuario (id_usuario, nome_usuario, email, senha)
    VALUES (9001, 'Teste Transacao', 'teste@email.com', 'senha123');
    
    -- Verificar se inserção foi bem sucedida
    IF SQL%ROWCOUNT = 1 THEN
        COMMIT;
        DBMS_OUTPUT.PUT_LINE('Usuário inserido com sucesso');
    ELSE
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Erro na inserção do usuário');
    END IF;
END;
/

-- Exemplo com SAVEPOINT
SAVEPOINT inicio_operacao;

INSERT INTO playlist (id_playlist, nome_playlist, id_usuario, publica)
VALUES (9001, 'Playlist Teste', 9001, 'N');

SAVEPOINT playlist_criada;

-- Tentativa de operação que pode falhar
INSERT INTO playlist_musica (id_playlist, id_musica, ordem_reproducao)
VALUES (9001, 99999, 1); -- Música que pode não existir

-- Se houve erro, voltar ao savepoint
-- ROLLBACK TO playlist_criada;

-- =====================================================
-- 5. RELATÓRIOS FORMATADOS
-- =====================================================

-- Configuração de formato
SET PAGESIZE 50;
SET LINESIZE 120;
COLUMN nome_artista FORMAT A25 HEADING 'Nome do Artista';
COLUMN total_albums FORMAT 999 HEADING 'Álbuns';
COLUMN total_musicas FORMAT 9999 HEADING 'Músicas';

-- Relatório de produtividade de artistas
SELECT 
    a.nome_artista,
    COUNT(DISTINCT al.id_album) as total_albums,
    COUNT(m.id_musica) as total_musicas,
    ROUND(AVG(m.duracao)/60, 1) as duracao_media_min
FROM artista a
LEFT JOIN album al ON a.id_artista = al.id_artista
LEFT JOIN musica m ON al.id_album = m.id_album
GROUP BY a.id_artista, a.nome_artista
HAVING COUNT(DISTINCT al.id_album) > 0
ORDER BY total_albums DESC, total_musicas DESC;

-- Limpeza da formatação
CLEAR COLUMNS;

COMMIT;