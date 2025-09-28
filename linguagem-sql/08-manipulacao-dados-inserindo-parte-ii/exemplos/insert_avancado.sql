-- =====================================================
-- INSERÇÃO AVANÇADA DE DADOS - SISTEMA MUSISTREAM
-- Módulo 08: Comandos INSERT Avançados
-- =====================================================

/*
DROP TABLE IF NOT EXISTS estatistica_artista CASCADE CONSTRAINTS;

*/

-- =====================================================
-- 1. CONFIGURAÇÃO E SEQUÊNCIAS
-- =====================================================

-- Criar sequências para geração automática de IDs
CREATE SEQUENCE seq_usuario_teste
START WITH 1000
INCREMENT BY 1
MAXVALUE 999999999
CACHE 20
NOCYCLE;

CREATE SEQUENCE seq_playlist_teste
START WITH 2000
INCREMENT BY 1
MAXVALUE 999999999
CACHE 20
NOCYCLE;

CREATE SEQUENCE seq_historico_teste
START WITH 1
INCREMENT BY 1
MAXVALUE 999999999999
CACHE 100
NOCYCLE;

-- =====================================================
-- 2. INSERÇÃO MÚLTIPLA COM VALUES
-- =====================================================

-- Inserir múltiplos gêneros musicais de uma vez
INSERT INTO genero (id_genero, nome_genero, descricao)
VALUES 
    (50, 'Progressive Rock', 'Rock progressivo com composições complexas e longas'),
    (51, 'Blues Rock', 'Fusão de blues tradicional com rock elétrico'),
    (52, 'Hard Rock', 'Rock pesado com guitarras distorcidas e vocais potentes'),
    (53, 'Folk Rock', 'Combinação de música folk com instrumentação rock'),
    (54, 'Psychedelic Rock', 'Rock psicodélico dos anos 60 e 70'),
    (55, 'Grunge', 'Movimento musical alternativo dos anos 90'),
    (56, 'Indie Rock', 'Rock independente com estética alternativa');

-- Inserir artistas internacionais famosos
INSERT INTO artista (id_artista, nome_artista, biografia, DATA_INICIO_CARREIRA, pais_origem, numero_membros, ativo)
VALUES 
    (50, 'Pink Floyd', 'Banda britânica pioneira do rock progressivo e psicodélico', 
     DATE '1965-01-01', 'Reino Unido', 4, 'N'),
    (51, 'Led Zeppelin', 'Uma das bandas de hard rock mais influentes da história', 
     DATE '1968-09-07', 'Reino Unido', 4, 'N'),
    (52, 'The Rolling Stones', 'Lendária banda de rock britânica ainda em atividade', 
     DATE '1962-07-12', 'Reino Unido', 4, 'S'),
    (53, 'Metallica', 'Banda pioneira do thrash metal americano', 
     DATE '1981-10-28', 'Estados Unidos', 4, 'S'),
    (54, 'Nirvana', 'Banda grunge que revolucionou o rock nos anos 90', 
     DATE '1987-01-01', 'Estados Unidos', 3, 'N'),
    (55, 'Radiohead', 'Banda britânica experimental e inovadora', 
     DATE '1985-01-01', 'Reino Unido', 5, 'S');

-- =====================================================
-- 3. INSERT ALL - MÚLTIPLAS TABELAS
-- =====================================================

-- Inserir dados relacionados em múltiplas tabelas simultaneamente
-- Observacao - A execucao abaixo pode nao ser suportada por todos os SGBDs
-- pois o oracle nao garante a ordem de execucao dos inserts o que pode 
-- afetar a integridade referencial
/*
INSERT ALL
    INTO artista (id_artista, nome_artista, pais_origem, numero_membros, ativo)
    VALUES (60, 'Arctic Monkeys', 'Reino Unido', 4, 'S')
    INTO album (id_album, titulo, id_artista, ano_lancamento, numero_faixas)
    VALUES (60, 'AM', 60, 2013, 12)
    INTO album (id_album, titulo, id_artista, ano_lancamento, numero_faixas)
    VALUES (61, 'Whatever People Say I Am, That''s What I''m Not', 60, 2006, 13)
    INTO musica (id_musica, titulo, duracao, numero_faixa, id_album)
    VALUES (600, 'Do I Wanna Know?', 263, 1, 60)
    INTO musica (id_musica, titulo, duracao, numero_faixa, id_album)
    VALUES (601, 'R U Mine?', 201, 2, 60)
SELECT * FROM dual;
*/

-- Se houver problemas acima, execute o debaixo
-- Primeiro insere artista e albuns
INSERT ALL
    INTO artista (id_artista, nome_artista, pais_origem, numero_membros, ativo)
    VALUES (60, 'Arctic Monkeys', 'Reino Unido', 4, 'S')
    INTO album (id_album, titulo, id_artista, ano_lancamento, numero_faixas)
    VALUES (60, 'AM', 60, 2013, 12)
    INTO album (id_album, titulo, id_artista, ano_lancamento, numero_faixas)
    VALUES (61, 'Whatever People Say I Am, That''s What I''m Not', 60, 2006, 13)
SELECT * FROM dual;

-- Depois insere as músicas
INSERT ALL
    INTO musica (id_musica, titulo, duracao, numero_faixa, id_album)
    VALUES (600, 'Do I Wanna Know?', 263, 1, 60)
    INTO musica (id_musica, titulo, duracao, numero_faixa, id_album)
    VALUES (601, 'R U Mine?', 201, 2, 60)
SELECT * FROM dual;

-- =====================================================
-- 4. INSERT... SELECT BÁSICO
-- =====================================================

-- Criar tabela temporária para demonstração
CREATE TABLE temp_artistas_nacionais AS
SELECT * FROM artista WHERE 1=0; -- só estrutura

-- Copiar artistas brasileiros para tabela temporária
INSERT INTO temp_artistas_nacionais
SELECT * FROM artista 
WHERE pais_origem = 'Brasil';

-- Verificar dados copiados
SELECT id_artista, nome_artista, pais_origem 
FROM temp_artistas_nacionais;

-- =====================================================
-- 5. INSERT... SELECT COM TRANSFORMAÇÃO
-- =====================================================

-- Criar tabela de estatísticas de artistas
CREATE TABLE estatistica_artista (
    id_artista INTEGER,
    nome_artista VARCHAR2(100),
    total_albums INTEGER,
    total_musicas INTEGER,
    duracao_total_segundos INTEGER,
    media_duracao_musicas NUMBER(8,2),
    primeiro_album INTEGER,
    ultimo_album INTEGER,
    data_calculo DATE
);

-- Inserir estatísticas calculadas
INSERT INTO estatistica_artista (
    id_artista, nome_artista, total_albums, total_musicas, 
    duracao_total_segundos, media_duracao_musicas, 
    primeiro_album, ultimo_album, data_calculo
)
SELECT 
    a.id_artista,
    a.nome_artista,
    COUNT(DISTINCT al.id_album) as total_albums,
    COUNT(m.id_musica) as total_musicas,
    SUM(COALESCE(m.duracao, 0)) as duracao_total,
    ROUND(AVG(m.duracao), 2) as media_duracao,
    MIN(al.ano_lancamento) as primeiro_album,
    MAX(al.ano_lancamento) as ultimo_album,
    SYSDATE as data_calculo
FROM artista a
LEFT JOIN album al ON a.id_artista = al.id_artista
LEFT JOIN musica m ON al.id_album = m.id_album
GROUP BY a.id_artista, a.nome_artista
HAVING COUNT(DISTINCT al.id_album) > 0;

-- =====================================================
-- 6. USANDO SEQUÊNCIAS EM INSERÇÕES
-- =====================================================

-- Inserir usuários usando sequência para ID
INSERT INTO usuario (id_usuario, nome_usuario, email, senha, data_nascimento, pais)
VALUES (seq_usuario_teste.NEXTVAL, 'Carlos', 'Silva', 'carlos.silva1@email.com', 
        'hash123', DATE '1990-05-15', 'Brasil');

INSERT INTO usuario (id_usuario, nome_usuario, email, senha, data_nascimento, pais)
VALUES (seq_usuario_teste.NEXTVAL, 'Ana', 'Santos', 'ana.santos1@email.com', 
        'hash456', DATE '1985-12-20', 'Brasil');

-- Inserir playlist para o último usuário inserido
INSERT INTO playlist (id_playlist, nome_playlist, descricao, publica, id_usuario)
VALUES (seq_playlist_teste.NEXTVAL, 'Clássicos do Rock', 
        'Melhores músicas de rock clássico', 'S', seq_usuario_teste.CURRVAL);

-- =====================================================
-- 7. INSERÇÃO CONDICIONAL
-- =====================================================

-- Inserir gênero apenas se não existir
INSERT INTO genero (id_genero, nome_genero, descricao)
SELECT 100, 'Bossa Nova', 'Estilo musical brasileiro suave e sofisticado'
FROM dual
WHERE NOT EXISTS (
    SELECT 1 FROM genero WHERE nome_genero = 'Bossa Nova'
);

-- Inserir múltiplos gêneros condicionalmente
INSERT INTO genero (id_genero, nome_genero, descricao)
SELECT id_gen, nome_gen, desc_gen
FROM (
    SELECT 101 as id_gen, 'Samba' as nome_gen, 'Gênero musical brasileiro tradicional' as desc_gen FROM dual
    UNION ALL
    SELECT 102, 'Forró', 'Música popular do nordeste brasileiro' FROM dual
    UNION ALL  
    SELECT 103, 'Pagode', 'Subgênero do samba brasileiro' FROM dual
) novos_generos
WHERE NOT EXISTS (
    SELECT 1 FROM genero g WHERE g.nome_genero = novos_generos.nome_gen
);

-- =====================================================
-- 8. MERGE (UPSERT) - INSERIR OU ATUALIZAR
-- =====================================================

-- Criar tabela de rankings mensais
CREATE TABLE ranking_mensal_musica (
    id_musica INTEGER,
    mes INTEGER,
    ano INTEGER,
    posicao INTEGER,
    total_reproducoes INTEGER,
    data_atualizacao DATE,
    PRIMARY KEY (id_musica, mes, ano)
);

-- MERGE para atualizar ou inserir ranking
/*
-- Esse script atualiza e insere dados em uma tabela chamada ranking_mensal_musica 
--(provavelmente usada para armazenar o ranking de músicas por mês).

-- Pega os dados do histórico de reproduções (historico_reproducao) e calcula:
-- * quantas vezes cada música foi reproduzida no mês anterior,
-- * sua posição no ranking (1º, 2º, 3º, …),
-- * e grava/atualiza isso no ranking mensal.

--O MERGE serve para fazer UPSERT no Oracle ou seja:
-- * UPDATE se o registro já existe (música já tem ranking para aquele mês/ano),
-- * INSERT se não existe (nova música aparece no ranking do mês).
*/
MERGE INTO ranking_mensal_musica rmm
USING (
    SELECT 
        m.id_musica,
        EXTRACT(MONTH FROM hr.data_reproducao) as mes,
        EXTRACT(YEAR FROM hr.data_reproducao) as ano,
        -- conta quantas vezes cada música foi ouvida
        COUNT(*) as total_reproducoes,
        ROW_NUMBER() OVER (
            PARTITION BY EXTRACT(MONTH FROM hr.data_reproducao), EXTRACT(YEAR FROM hr.data_reproducao)
            ORDER BY COUNT(*) DESC
        ) as posicao
    FROM musica m
    JOIN historico_reproducao hr ON m.id_musica = hr.id_musica
    -- pega todas as músicas reproduzidas no último mês
    WHERE hr.data_reproducao >= TRUNC(SYSDATE, 'MM') - INTERVAL '1' MONTH
    GROUP BY m.id_musica, EXTRACT(MONTH FROM hr.data_reproducao), EXTRACT(YEAR FROM hr.data_reproducao)
) src ON (rmm.id_musica = src.id_musica AND rmm.mes = src.mes AND rmm.ano = src.ano)
WHEN MATCHED THEN
    UPDATE SET 
        rmm.posicao = src.posicao,
        rmm.total_reproducoes = src.total_reproducoes,
        rmm.data_atualizacao = SYSDATE
WHEN NOT MATCHED THEN
    INSERT (id_musica, mes, ano, posicao, total_reproducoes, data_atualizacao)
    VALUES (src.id_musica, src.mes, src.ano, src.posicao, src.total_reproducoes, SYSDATE);

-- =====================================================
-- 9. INSERÇÃO COM SUBCONSULTAS COMPLEXAS
-- =====================================================

-- Criar playlists automáticas baseadas em preferências
INSERT INTO playlist (id_playlist, nome_playlist, descricao, publica, id_usuario)
SELECT 
    seq_playlist_teste.NEXTVAL,
    'Top 20 - ' || g.nome_genero,
    'Playlist automática com as 20 músicas mais tocadas do gênero ' || g.nome_genero,
    'S',
    1001 -- usuário admin
FROM genero g
WHERE EXISTS (
    SELECT 1 FROM musica m 
    WHERE m.id_genero = g.id_genero
);

-- Povoar as playlists criadas com as músicas mais populares
INSERT INTO playlist_musica (id_playlist, id_musica, ordem_musica, data_adicao)
SELECT 
    p.id_playlist,
    ranked_songs.id_musica,
    ranked_songs.ranking,
    SYSDATE
FROM playlist p
JOIN (
    SELECT 
        g.id_genero,
        m.id_musica,
        ROW_NUMBER() OVER (PARTITION BY g.id_genero ORDER BY COUNT(hr.id_historico) DESC) as ranking
    FROM genero g
    JOIN musica m ON g.id_genero = m.id_genero
    LEFT JOIN historico_reproducao hr ON m.id_musica = hr.id_musica
    GROUP BY g.id_genero, m.id_musica
) ranked_songs 
ON p.nome_playlist = 'Top 20 - ' || (
    SELECT nome_genero FROM genero WHERE id_genero = ranked_songs.id_genero
)
WHERE ranked_songs.ranking <= 20
  AND p.nome_playlist LIKE 'Top 20 - %';

-- =====================================================
-- 10. INSERÇÃO DE DADOS DE TESTE (GERAÇÃO AUTOMÁTICA)
-- =====================================================

-- Gerar usuários de teste
INSERT INTO usuario (id_usuario, nome_usuario, email, senha, data_nascimento, pais)
SELECT 
    seq_usuario_teste.NEXTVAL,
    'Usuario' || LEVEL,
    'usuario' || LEVEL || '@teste.com',
    'senha' || LEVEL,
    DATE '1980-01-01' + MOD(LEVEL * 137, 365 * 30), -- Idades variadas
    CASE MOD(LEVEL, 5)
        WHEN 0 THEN 'Brasil'
        WHEN 1 THEN 'Estados Unidos'
        WHEN 2 THEN 'Reino Unido'
        WHEN 3 THEN 'Canadá'
        ELSE 'França'
    END
FROM dual
CONNECT BY LEVEL <= 100; -- Gerar 100 usuários

-- Gerar histórico de reprodução aleatório
INSERT INTO historico_reproducao (id_historico, id_usuario, id_musica, data_reproducao, duracao_ouvida)
SELECT 
    seq_historico_teste.NEXTVAL,
    u.id_usuario,
    m.id_musica,
    SYSDATE - DBMS_RANDOM.VALUE(1, 365), -- Data aleatória no último ano
    CASE 
        WHEN DBMS_RANDOM.VALUE < 0.2 THEN ROUND(m.duracao * 0.1) -- 20% ouvem só 10%
        WHEN DBMS_RANDOM.VALUE < 0.5 THEN ROUND(m.duracao * 0.5) -- 30% ouvem metade
        WHEN DBMS_RANDOM.VALUE < 0.8 THEN ROUND(m.duracao * 0.9) -- 30% ouvem quase tudo
        ELSE m.duracao -- 20% ouvem completo
    END
FROM (
    SELECT id_usuario FROM usuario 
    WHERE id_usuario >= 1000
    ORDER BY DBMS_RANDOM.VALUE
) u,
(
    SELECT id_musica, duracao FROM musica 
    WHERE duracao IS NOT NULL
    ORDER BY DBMS_RANDOM.VALUE
) m
WHERE ROWNUM <= 10000; -- Gerar 10k reproduções

-- =====================================================
-- 11. INSERÇÃO COM TRATAMENTO DE ERROS
-- =====================================================

-- Criar tabela de log de erros
CREATE TABLE log_insercao_erros (
    id_log INTEGER PRIMARY KEY,
    tabela_destino VARCHAR2(50),
    operacao VARCHAR2(20),
    erro_codigo NUMBER,
    erro_mensagem VARCHAR2(4000),
    dados_tentativa CLOB,
    usuario_sistema VARCHAR2(100),
    data_erro DATE
);

-- Inserção segura com tratamento de erros
DECLARE
    v_count_sucesso NUMBER := 0;
    v_count_erro NUMBER := 0;
BEGIN
    -- Tentar inserir dados que podem gerar conflitos
    FOR i IN 1..50 LOOP
        BEGIN
            INSERT INTO usuario (id_usuario, nome_usuario, email, senha, data_nascimento)
            VALUES (2000 + i, 'TestUser' || i, 'test' || i || '@domain.com', 'pass123', SYSDATE);
            
            v_count_sucesso := v_count_sucesso + 1;
            
        EXCEPTION
            WHEN DUP_VAL_ON_INDEX THEN
                -- Log do erro de duplicata
                INSERT INTO log_insercao_erros VALUES (
                    i, 'USUARIO', 'INSERT', SQLCODE, SQLERRM,
                    'ID: ' || (2000 + i) || ', Email: test' || i || '@domain.com',
                    USER, SYSDATE
                );
                v_count_erro := v_count_erro + 1;
            WHEN OTHERS THEN
                -- Log de outros erros
                INSERT INTO log_insercao_erros VALUES (
                    i, 'USUARIO', 'INSERT', SQLCODE, SQLERRM,
                    'ID: ' || (2000 + i) || ', Erro inesperado',
                    USER, SYSDATE
                );
                v_count_erro := v_count_erro + 1;
        END;
    END LOOP;
    
    DBMS_OUTPUT.PUT_LINE('Sucessos: ' || v_count_sucesso || ', Erros: ' || v_count_erro);
    COMMIT;
END;
/

-- =====================================================
-- 12. VERIFICAÇÃO DOS DADOS INSERIDOS
-- =====================================================

-- Verificar estatísticas das inserções
SELECT 'USUARIOS' as tabela, COUNT(*) as total FROM usuario
WHERE id_usuario >= 1000
UNION ALL
SELECT 'PLAYLISTS', COUNT(*) FROM playlist 
WHERE id_playlist >= 2000
UNION ALL
SELECT 'HISTORICO', COUNT(*) FROM historico_reproducao
WHERE id_historico >= 1
UNION ALL
SELECT 'ESTATISTICAS', COUNT(*) FROM estatistica_artista
UNION ALL
SELECT 'RANKINGS', COUNT(*) FROM ranking_mensal_musica;

-- Verificar qualidade dos dados inseridos
SELECT 
    'Usuarios sem email' as verificacao,
    COUNT(*) as problemas
FROM usuario 
WHERE email IS NULL OR email = ''
UNION ALL
SELECT 
    'Historico com duracao maior que musica',
    COUNT(*)
FROM historico_reproducao hr
JOIN musica m ON hr.id_musica = m.id_musica
WHERE hr.duracao_ouvida > m.duracao
UNION ALL
SELECT 
    'Playlists vazias',
    COUNT(*)
FROM playlist p
WHERE NOT EXISTS (
    SELECT 1 FROM playlist_musica pm WHERE pm.id_playlist = p.id_playlist
);

-- =====================================================
-- 13. LIMPEZA (OPCIONAL)
-- =====================================================

-- Comentar para manter os dados ou executar para limpar
/*
-- Limpar dados de teste
DELETE FROM historico_reproducao WHERE id_historico >= 1;
DELETE FROM playlist_musica WHERE id_playlist >= 2000;
DELETE FROM playlist WHERE id_playlist >= 2000;
DELETE FROM usuario WHERE id_usuario >= 1000;
DELETE FROM ranking_mensal_musica;
DELETE FROM estatistica_artista;
DROP TABLE temp_artistas_nacionais;
DROP TABLE log_insercao_erros;

-- Remover sequências
DROP SEQUENCE seq_usuario_teste;
DROP SEQUENCE seq_playlist_teste;
DROP SEQUENCE seq_historico_teste;
*/

COMMIT;