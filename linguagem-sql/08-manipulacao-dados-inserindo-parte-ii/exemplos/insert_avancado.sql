-- =====================================================
-- INSERÇÃO AVANÇADA DE DADOS - SISTEMA MUSISTREAM
-- Módulo 08: Comandos INSERT Avançados para Oracle
-- =====================================================
--
-- OBJETIVO: Demonstrar técnicas avançadas de inserção de dados
-- SGBD: Oracle Database 11g ou superior  
-- FERRAMENTA: Oracle SQL Developer
-- DEPENDÊNCIAS: Estrutura base criada (streaming-de-musica/01-estrutura-completa.sql)
--
-- COMO EXECUTAR NO SQL DEVELOPER:
-- 1. Certifique-se de que a estrutura base está criada
-- 2. Habilite SERVEROUTPUT: SET SERVEROUTPUT ON SIZE UNLIMITED;
-- 3. Execute seção por seção usando F5 (Run Script)
-- 4. Para blocos PL/SQL, selecione TODO o bloco incluindo o /
--
-- =====================================================

-- Configurações iniciais para SQL Developer
SET SERVEROUTPUT ON SIZE UNLIMITED;
SET FEEDBACK ON;
SET ECHO ON;

-- Configurar formato de data para evitar problemas
ALTER SESSION SET NLS_DATE_FORMAT = 'YYYY-MM-DD';

PROMPT =====================================================
PROMPT Iniciando script de Inserção Avançada
PROMPT Data/Hora: 
SELECT TO_CHAR(SYSDATE, 'DD/MM/YYYY HH24:MI:SS') as data_execucao FROM dual;
PROMPT =====================================================

-- =====================================================
-- SEÇÃO 1: CONFIGURAÇÃO E CRIAÇÃO DE SEQUENCES
-- =====================================================
-- Sequences são objetos Oracle para gerar IDs únicos automaticamente
-- São essenciais para chaves primárias em ambientes multi-usuário

PROMPT 
PROMPT ========== SEÇÃO 1: Criando Sequences ==========
PROMPT

-- Verificar se sequences já existem e removê-las
DECLARE
    v_count NUMBER;
BEGIN
    SELECT COUNT(*) INTO v_count FROM user_sequences WHERE sequence_name = 'SEQ_USUARIO_TESTE';
    IF v_count > 0 THEN
        EXECUTE IMMEDIATE 'DROP SEQUENCE seq_usuario_teste';
        DBMS_OUTPUT.PUT_LINE('Sequence seq_usuario_teste removida');
    END IF;
    
    SELECT COUNT(*) INTO v_count FROM user_sequences WHERE sequence_name = 'SEQ_PLAYLIST_TESTE';
    IF v_count > 0 THEN
        EXECUTE IMMEDIATE 'DROP SEQUENCE seq_playlist_teste';
        DBMS_OUTPUT.PUT_LINE('Sequence seq_playlist_teste removida');
    END IF;
    
    SELECT COUNT(*) INTO v_count FROM user_sequences WHERE sequence_name = 'SEQ_HISTORICO_TESTE';
    IF v_count > 0 THEN
        EXECUTE IMMEDIATE 'DROP SEQUENCE seq_historico_teste';
        DBMS_OUTPUT.PUT_LINE('Sequence seq_historico_teste removida');
    END IF;
END;
/

-- Criar sequences para geração automática de IDs
-- START WITH: valor inicial
-- INCREMENT BY: incremento a cada chamada
-- CACHE: número de valores pré-alocados em memória (melhora performance)
-- NOCYCLE: não volta ao início após atingir MAXVALUE

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

PROMPT Sequences criadas com sucesso!
PROMPT - seq_usuario_teste: inicia em 1000
PROMPT - seq_playlist_teste: inicia em 2000  
PROMPT - seq_historico_teste: inicia em 1
PROMPT

-- =====================================================
-- SEÇÃO 2: INSERÇÃO MÚLTIPLA COM VALUES
-- =====================================================
-- Técnica: Inserir múltiplos registros em um único comando INSERT
-- Vantagens: Muito mais rápido que múltiplos INSERTs individuais
-- Performance: 80-95% mais rápido que inserções individuais
-- Ideal para: Dados de configuração, cargas pequenas (<1000 registros)

PROMPT 
PROMPT ========== SEÇÃO 2: Inserção Múltipla de Registros ==========
PROMPT

-- Exemplo 1: Inserir múltiplos gêneros musicais de uma vez
-- Todos os registros são inseridos em uma única transação atômica
INSERT INTO genero (id_genero, nome_genero, descricao)
VALUES 
    (50, 'Progressive Rock', 'Rock progressivo com composições complexas e longas'),
    (51, 'Blues Rock', 'Fusão de blues tradicional com rock elétrico'),
    (52, 'Hard Rock', 'Rock pesado com guitarras distorcidas e vocais potentes'),
    (53, 'Folk Rock', 'Combinação de música folk com instrumentação rock'),
    (54, 'Psychedelic Rock', 'Rock psicodélico dos anos 60 e 70'),
    (55, 'Grunge', 'Movimento musical alternativo dos anos 90'),
    (56, 'Indie Rock', 'Rock independente com estética alternativa');

PROMPT Inseridos 7 gêneros musicais
SELECT COUNT(*) as total_generos FROM genero WHERE id_genero BETWEEN 50 AND 56;

-- Exemplo 2: Inserir artistas internacionais famosos
-- Note o uso de DATE para datas e aspas simples duplicadas para apóstrofos
INSERT INTO artista (id_artista, nome_artista, biografia, data_inicio_carreira, pais_origem, numero_membros, ativo)
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

PROMPT Inseridos 6 artistas internacionais
SELECT id_artista, nome_artista, pais_origem FROM artista WHERE id_artista BETWEEN 50 AND 55;
PROMPT

-- =====================================================
-- SEÇÃO 3: INSERT ALL - INSERÇÃO EM MÚLTIPLAS TABELAS
-- =====================================================
-- Técnica específica do Oracle: INSERT ALL
-- Permite inserir dados em múltiplas tabelas com um único comando
-- Vantagens: Atomicidade, eficiência, mantém integridade referencial
-- CUIDADO: Requer atenção especial à ordem de inserção devido a FKs

PROMPT 
PROMPT ========== SEÇÃO 3: INSERT ALL (Múltiplas Tabelas) ==========
PROMPT

-- IMPORTANTE: INSERT ALL no Oracle não garante ordem de execução
-- Isso pode causar problemas de integridade referencial
-- SOLUÇÃO: Dividir em dois INSERTs ALL - primeiro tabelas pais, depois filhas

-- Passo 1: Inserir artista e álbuns (sem músicas ainda)
INSERT ALL
    INTO artista (id_artista, nome_artista, pais_origem, numero_membros, ativo)
    VALUES (60, 'Arctic Monkeys', 'Reino Unido', 4, 'S')
    INTO album (id_album, titulo, id_artista, data_lancamento, numero_faixas)
    VALUES (60, 'AM', 60, DATE '2013-09-09', 12)
    INTO album (id_album, titulo, id_artista, data_lancamento, numero_faixas)
    VALUES (61, 'Whatever People Say I Am, That''s What I''m Not', 60, DATE '2006-01-23', 13)
SELECT * FROM dual;

PROMPT Passo 1 concluído: Artista e álbuns inseridos

-- Passo 2: Agora inserir as músicas (tabela filha de album)
INSERT ALL
    INTO musica (id_musica, titulo, duracao, numero_faixa, id_album)
    VALUES (600, 'Do I Wanna Know?', 263, 1, 60)
    INTO musica (id_musica, titulo, duracao, numero_faixa, id_album)
    VALUES (601, 'R U Mine?', 201, 2, 60)
    INTO musica (id_musica, titulo, duracao, numero_faixa, id_album)
    VALUES (602, 'Arabella', 207, 3, 60)
    INTO musica (id_musica, titulo, duracao, numero_faixa, id_album)
    VALUES (603, 'I Bet You Look Good on the Dancefloor', 173, 1, 61)
SELECT * FROM dual;

PROMPT Passo 2 concluído: Músicas inseridas

-- Verificar resultado
SELECT 
    a.nome_artista,
    al.titulo as album,
    m.titulo as musica,
    m.duracao as duracao_seg
FROM artista a
JOIN album al ON a.id_artista = al.id_artista
LEFT JOIN musica m ON al.id_album = m.id_album
WHERE a.id_artista = 60
ORDER BY al.id_album, m.numero_faixa;

PROMPT

-- =====================================================
-- SEÇÃO 4: INSERT... SELECT - COPIAR DADOS
-- =====================================================
-- Técnica: Inserir dados baseados em consultas SELECT
-- Ideal para: Migrações, backups, ETL, consolidação de dados
-- Performance: Processamento interno do Oracle, muito eficiente

PROMPT 
PROMPT ========== SEÇÃO 4: INSERT... SELECT ==========
PROMPT

-- Exemplo 1: Criar tabela temporária e copiar dados com filtro
-- CREATE TABLE... AS SELECT cria estrutura E copia dados em um comando
-- WHERE 1=0 cria apenas a estrutura sem copiar dados

CREATE TABLE temp_artistas_nacionais AS
SELECT * FROM artista WHERE 1=0; -- Apenas estrutura

PROMPT Tabela temp_artistas_nacionais criada (apenas estrutura)

-- Agora copiar artistas brasileiros para a tabela temporária
INSERT INTO temp_artistas_nacionais
SELECT * FROM artista 
WHERE pais_origem = 'Brasil';

-- Verificar quantos foram copiados
SELECT 
    'Artistas copiados' as descricao,
    COUNT(*) as total 
FROM temp_artistas_nacionais;

-- Ver os dados copiados
PROMPT Artistas brasileiros copiados:
SELECT id_artista, nome_artista, pais_origem 
FROM temp_artistas_nacionais
ORDER BY nome_artista;

PROMPT

-- =====================================================
-- SEÇÃO 5: INSERT... SELECT COM TRANSFORMAÇÃO
-- =====================================================
-- Técnica: Calcular e agregar dados durante a inserção
-- Uso: Criar tabelas de estatísticas, relatórios, data marts
-- Vantagens: Uma operação faz cálculo + inserção

PROMPT 
PROMPT ========== SEÇÃO 5: INSERT... SELECT com Agregações ==========
PROMPT

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
    data_calculo DATE,
    CONSTRAINT pk_estat_artista PRIMARY KEY (id_artista)
);

PROMPT Tabela estatistica_artista criada

-- Inserir estatísticas calculadas para todos os artistas
-- Usa JOINs, agregações (COUNT, SUM, AVG) e funções (COALESCE)
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
    MIN(EXTRACT(YEAR FROM al.data_lancamento)) as primeiro_album,
    MAX(EXTRACT(YEAR FROM al.data_lancamento)) as ultimo_album,
    SYSDATE as data_calculo
FROM artista a
LEFT JOIN album al ON a.id_artista = al.id_artista
LEFT JOIN musica m ON al.id_album = m.id_album
GROUP BY a.id_artista, a.nome_artista
HAVING COUNT(DISTINCT al.id_album) > 0; -- Apenas artistas com álbuns

-- Exibir estatísticas calculadas
PROMPT Estatísticas calculadas:
SELECT 
    nome_artista,
    total_albums as albums,
    total_musicas as musicas,
    ROUND(duracao_total_segundos/60, 2) as duracao_min,
    media_duracao_musicas as media_seg,
    primeiro_album as ano_inicio,
    ultimo_album as ano_fim
FROM estatistica_artista
ORDER BY total_albums DESC, total_musicas DESC;

PROMPT

-- =====================================================
-- SEÇÃO 6: USANDO SEQUENCES EM INSERÇÕES
-- =====================================================
-- NEXTVAL: obtém próximo valor da sequence
-- CURRVAL: obtém valor atual da sequence (último NEXTVAL da sessão)
-- Uso: Geração automática de IDs, manter relacionamentos

PROMPT 
PROMPT ========== SEÇÃO 6: Usando Sequences para IDs Automáticos ==========
PROMPT

-- Exemplo 1: Inserir usuários usando sequence para ID
-- seq_usuario_teste.NEXTVAL gera automaticamente o próximo ID

INSERT INTO usuario (id_usuario, nome_usuario, email, senha, data_nascimento, pais)
VALUES (seq_usuario_teste.NEXTVAL, 'Carlos Silva', 'carlos.silva1@email.com', 
        'hash123', DATE '1990-05-15', 'Brasil');

INSERT INTO usuario (id_usuario, nome_usuario, email, senha, data_nascimento, pais)
VALUES (seq_usuario_teste.NEXTVAL, 'Ana Santos', 'ana.santos1@email.com', 
        'hash456', DATE '1985-12-20', 'Brasil');

PROMPT 2 usuários inseridos com IDs automáticos

-- Exemplo 2: Usar CURRVAL para manter relacionamento
-- CURRVAL retorna o último valor gerado por NEXTVAL na mesma sessão
-- Útil para inserir dados relacionados (pai-filho)

INSERT INTO playlist (id_playlist, nome_playlist, descricao, publica, id_usuario)
VALUES (seq_playlist_teste.NEXTVAL, 
        'Clássicos do Rock', 
        'Melhores músicas de rock clássico', 
        'S', 
        seq_usuario_teste.CURRVAL); -- Usa ID do último usuário inserido

PROMPT Playlist criada para o último usuário inserido

-- Verificar resultado - playlist vinculada ao usuário correto
SELECT 
    u.id_usuario,
    u.nome_usuario as nome_completo,
    p.id_playlist,
    p.nome_playlist
FROM usuario u
JOIN playlist p ON u.id_usuario = p.id_usuario
WHERE u.id_usuario >= 1000
ORDER BY u.id_usuario;

PROMPT

-- =====================================================
-- SEÇÃO 7: INSERÇÃO CONDICIONAL (INSERT COM NOT EXISTS)
-- =====================================================
-- Técnica: Inserir apenas se registro não existe
-- Vantagens: Evita erro de duplicata, operação idempotente
-- Uso: Dados de configuração, sincronização entre sistemas

PROMPT 
PROMPT ========== SEÇÃO 7: Inserção Condicional ==========
PROMPT

-- Exemplo 1: Inserir gênero apenas se não existir
-- NOT EXISTS verifica se já existe antes de inserir
INSERT INTO genero (id_genero, nome_genero, descricao)
SELECT 100, 'Bossa Nova', 'Estilo musical brasileiro suave e sofisticado'
FROM dual
WHERE NOT EXISTS (
    SELECT 1 FROM genero WHERE nome_genero = 'Bossa Nova'
);

PROMPT Tentativa de inserir Bossa Nova (inserido se não existia)

-- Exemplo 2: Inserir múltiplos gêneros condicionalmente
-- Cada registro é verificado individualmente
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

PROMPT Gêneros brasileiros inseridos (apenas os que não existiam)

-- Verificar todos os gêneros brasileiros
SELECT id_genero, nome_genero, descricao
FROM genero
WHERE nome_genero IN ('Bossa Nova', 'Samba', 'Forró', 'Pagode')
ORDER BY id_genero;

PROMPT

-- =====================================================
-- SEÇÃO 8: MERGE (UPSERT) - INSERIR OU ATUALIZAR
-- =====================================================
-- MERGE: Comando Oracle para INSERT ou UPDATE condicional
-- Também conhecido como UPSERT (Update + Insert)
-- Uso: Sincronização de dados, atualização de caches, ETL

PROMPT 
PROMPT ========== SEÇÃO 8: MERGE (UPSERT) ==========
PROMPT

-- Criar tabela de rankings mensais
CREATE TABLE ranking_mensal_musica (
    id_musica INTEGER,
    mes INTEGER,
    ano INTEGER,
    posicao INTEGER,
    total_reproducoes INTEGER,
    data_atualizacao DATE,
    CONSTRAINT pk_ranking PRIMARY KEY (id_musica, mes, ano)
);

PROMPT Tabela ranking_mensal_musica criada

-- MERGE para atualizar ou inserir ranking
-- Se existe: atualiza (WHEN MATCHED)
-- Se não existe: insere (WHEN NOT MATCHED)

/*
EXPLICAÇÃO DO MERGE:
1. MERGE INTO: tabela destino
2. USING: consulta fonte (pode ser tabela ou subquery)
3. ON: condição de match (chaves primárias/únicas)
4. WHEN MATCHED: o que fazer se encontrar registro existente
5. WHEN NOT MATCHED: o que fazer se não encontrar
*/

MERGE INTO ranking_mensal_musica rmm
USING (
    -- Subquery que calcula estatísticas do último mês
    SELECT 
        m.id_musica,
        EXTRACT(MONTH FROM hr.data_reproducao) as mes,
        EXTRACT(YEAR FROM hr.data_reproducao) as ano,
        COUNT(*) as total_reproducoes,
        -- ROW_NUMBER cria posição no ranking (1º, 2º, 3º...)
        ROW_NUMBER() OVER (
            PARTITION BY EXTRACT(MONTH FROM hr.data_reproducao), 
                         EXTRACT(YEAR FROM hr.data_reproducao)
            ORDER BY COUNT(*) DESC
        ) as posicao
    FROM musica m
    JOIN historico_reproducao hr ON m.id_musica = hr.id_musica
    -- Apenas músicas reproduzidas no último mês
    WHERE hr.data_reproducao >= TRUNC(SYSDATE, 'MM') - INTERVAL '1' MONTH
    GROUP BY m.id_musica, 
             EXTRACT(MONTH FROM hr.data_reproducao), 
             EXTRACT(YEAR FROM hr.data_reproducao)
) src 
ON (rmm.id_musica = src.id_musica 
    AND rmm.mes = src.mes 
    AND rmm.ano = src.ano)
WHEN MATCHED THEN
    -- Se encontrou: atualiza valores
    UPDATE SET 
        rmm.posicao = src.posicao,
        rmm.total_reproducoes = src.total_reproducoes,
        rmm.data_atualizacao = SYSDATE
WHEN NOT MATCHED THEN
    -- Se não encontrou: insere novo registro
    INSERT (id_musica, mes, ano, posicao, total_reproducoes, data_atualizacao)
    VALUES (src.id_musica, src.mes, src.ano, src.posicao, 
            src.total_reproducoes, SYSDATE);

PROMPT Ranking mensal atualizado/inserido via MERGE

-- Verificar rankings criados/atualizados
SELECT 
    mes,
    ano,
    COUNT(*) as total_musicas_rankeadas,
    MAX(posicao) as posicoes_no_ranking
FROM ranking_mensal_musica
GROUP BY mes, ano
ORDER BY ano DESC, mes DESC;

PROMPT

-- =====================================================
-- SEÇÃO 9: INSERÇÃO COM SUBCONSULTAS COMPLEXAS
-- =====================================================
-- Técnica: Usar subconsultas avançadas com window functions
-- Uso: Criar dados derivados automaticamente, rankings, top N
-- Ideal para: Analytics, BI, dashboards

PROMPT 
PROMPT ========== SEÇÃO 9: Inserção com Subconsultas Complexas ==========
PROMPT

-- Exemplo 1: Criar playlists automáticas por gênero
-- Cria uma playlist "Top 20 - [Gênero]" para cada gênero que tem músicas

INSERT INTO playlist (id_playlist, nome_playlist, descricao, publica, id_usuario)
SELECT 
    seq_playlist_teste.NEXTVAL,
    'Top 20 - ' || g.nome_genero,
    'Playlist automática com as 20 músicas mais tocadas do gênero ' || g.nome_genero,
    'S',
    1001 -- ID de usuário admin/sistema
FROM genero g
WHERE EXISTS (
    -- Apenas gêneros que têm músicas
    SELECT 1 FROM musica m 
    WHERE m.id_genero = g.id_genero
);

PROMPT Playlists automáticas criadas por gênero

-- Exemplo 2: Povoar playlists com músicas mais populares
-- Usa window function ROW_NUMBER para ranking
-- Insere top 20 de cada gênero nas respectivas playlists

INSERT INTO playlist_musica (id_playlist, id_musica, ordem_musica, data_adicao)
SELECT 
    p.id_playlist,
    ranked_songs.id_musica,
    ranked_songs.ranking,
    SYSDATE
FROM playlist p
JOIN (
    -- Subconsulta que cria ranking de músicas por gênero
    SELECT 
        g.id_genero,
        m.id_musica,
        -- ROW_NUMBER cria ranking por gênero
        ROW_NUMBER() OVER (
            PARTITION BY g.id_genero 
            ORDER BY COUNT(hr.id_historico) DESC
        ) as ranking
    FROM genero g
    JOIN musica m ON g.id_genero = m.id_genero
    LEFT JOIN historico_reproducao hr ON m.id_musica = hr.id_musica
    GROUP BY g.id_genero, m.id_musica
) ranked_songs 
ON p.nome_playlist = 'Top 20 - ' || (
    SELECT nome_genero FROM genero WHERE id_genero = ranked_songs.id_genero
)
WHERE ranked_songs.ranking <= 20  -- Apenas top 20
  AND p.nome_playlist LIKE 'Top 20 - %';

PROMPT Playlists populadas com top 20 músicas de cada gênero

-- Verificar resultados
SELECT 
    p.nome_playlist,
    COUNT(*) as total_musicas
FROM playlist p
JOIN playlist_musica pm ON p.id_playlist = pm.id_playlist
WHERE p.nome_playlist LIKE 'Top 20 - %'
GROUP BY p.nome_playlist
ORDER BY p.nome_playlist;

PROMPT

-- =====================================================
-- SEÇÃO 10: GERAÇÃO AUTOMÁTICA DE DADOS DE TESTE
-- =====================================================
-- Técnica: Usar funções Oracle para gerar dados sintéticos
-- Funções: LEVEL (hierarquia), DBMS_RANDOM, MOD, CASE
-- Uso: Testes, desenvolvimento, demos, prova de conceito

PROMPT 
PROMPT ========== SEÇÃO 10: Geração Automática de Dados de Teste ==========
PROMPT

-- Exemplo 1: Gerar 100 usuários de teste automaticamente
-- CONNECT BY LEVEL: gera linhas numeradas de 1 até N
-- MOD: distribui valores ciclicamente
-- || : concatenação de strings no Oracle

INSERT INTO usuario (id_usuario, nome_usuario, email, senha, data_nascimento, pais)
SELECT 
    seq_usuario_teste.NEXTVAL,
    'Usuario' || LEVEL,  -- Usuario1, Usuario2, ...
    'usuario' || LEVEL || '@teste.com',  -- usuario1@teste.com, ...
    'senha' || LEVEL,  -- senha1, senha2, ...
    DATE '1980-01-01' + MOD(LEVEL * 137, 365 * 30), -- Datas variadas (30 anos)
    CASE MOD(LEVEL, 5)  -- Distribui países ciclicamente
        WHEN 0 THEN 'Brasil'
        WHEN 1 THEN 'Estados Unidos'
        WHEN 2 THEN 'Reino Unido'
        WHEN 3 THEN 'Canadá'
        ELSE 'França'
    END
FROM dual
CONNECT BY LEVEL <= 100; -- Gera 100 usuários

PROMPT 100 usuários de teste gerados

-- Verificar distribuição por país
SELECT 
    pais,
    COUNT(*) as total_usuarios
FROM usuario
WHERE id_usuario >= 1000
GROUP BY pais
ORDER BY total_usuarios DESC;

-- Exemplo 2: Gerar histórico de reprodução aleatório
-- DBMS_RANDOM.VALUE: gera números aleatórios
-- Simula diferentes padrões de escuta (completa, parcial, skip)

PROMPT
PROMPT Gerando 10.000 reproduções aleatórias...
PROMPT (Isso pode levar alguns segundos)
PROMPT

INSERT INTO historico_reproducao (id_historico, id_usuario, id_musica, data_reproducao, duracao_ouvida)
SELECT 
    seq_historico_teste.NEXTVAL,
    u.id_usuario,
    m.id_musica,
    SYSDATE - DBMS_RANDOM.VALUE(1, 365), -- Data aleatória no último ano
    CASE 
        -- Simula diferentes comportamentos de escuta
        WHEN DBMS_RANDOM.VALUE < 0.2 THEN ROUND(m.duracao * 0.1) -- 20% ouvem só 10%
        WHEN DBMS_RANDOM.VALUE < 0.5 THEN ROUND(m.duracao * 0.5) -- 30% ouvem metade
        WHEN DBMS_RANDOM.VALUE < 0.8 THEN ROUND(m.duracao * 0.9) -- 30% ouvem quase tudo
        ELSE m.duracao -- 20% ouvem completo
    END
FROM (
    -- Seleciona usuários em ordem aleatória
    SELECT id_usuario FROM usuario 
    WHERE id_usuario >= 1000
    ORDER BY DBMS_RANDOM.VALUE
) u,
(
    -- Seleciona músicas em ordem aleatória
    SELECT id_musica, duracao FROM musica 
    WHERE duracao IS NOT NULL
    ORDER BY DBMS_RANDOM.VALUE
) m
WHERE ROWNUM <= 10000; -- Limita a 10.000 reproduções

PROMPT 10.000 reproduções geradas com sucesso!

-- Estatísticas das reproduções geradas
SELECT 
    'Total de reproduções' as metrica,
    COUNT(*) as valor
FROM historico_reproducao
WHERE id_historico >= 1
UNION ALL
SELECT 
    'Usuários únicos',
    COUNT(DISTINCT id_usuario)
FROM historico_reproducao
WHERE id_historico >= 1
UNION ALL
SELECT 
    'Músicas únicas reproduzidas',
    COUNT(DISTINCT id_musica)
FROM historico_reproducao
WHERE id_historico >= 1;

PROMPT

-- =====================================================
-- SEÇÃO 11: TRATAMENTO DE ERROS COM PL/SQL
-- =====================================================
-- Técnica: Usar blocos PL/SQL para tratar erros durante inserção
-- IMPORTANTE: Execute este bloco com F5 (Run Script), não Ctrl+Enter
-- Exceptions: DUP_VAL_ON_INDEX (duplicata), OTHERS (qualquer outro erro)

PROMPT 
PROMPT ========== SEÇÃO 11: Tratamento de Erros em Inserções ==========
PROMPT

-- Criar tabela de log de erros para rastreabilidade
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

PROMPT Tabela log_insercao_erros criada
PROMPT
PROMPT Executando inserções com tratamento de erros...
PROMPT (Alguns erros são esperados e serão tratados)
PROMPT

-- Bloco PL/SQL para inserção segura com tratamento de erros
-- ATENÇÃO: Selecione TODO o bloco até o / e execute com F5
DECLARE
    v_count_sucesso NUMBER := 0;
    v_count_erro NUMBER := 0;
BEGIN
    -- Tentar inserir 50 usuários (alguns podem gerar conflitos)
    FOR i IN 1..50 LOOP
        BEGIN
            INSERT INTO usuario (id_usuario, nome_usuario, email, senha, data_nascimento)
            VALUES (2000 + i, 'TestUser' || i, 'test' || i || '@domain.com', 'pass123', SYSDATE);
            
            v_count_sucesso := v_count_sucesso + 1;
            
        EXCEPTION
            WHEN DUP_VAL_ON_INDEX THEN
                -- Captura erro de chave duplicada
                INSERT INTO log_insercao_erros VALUES (
                    i, 'USUARIO', 'INSERT', SQLCODE, SQLERRM,
                    'ID: ' || (2000 + i) || ', Email: test' || i || '@domain.com',
                    USER, SYSDATE
                );
                v_count_erro := v_count_erro + 1;
                
            WHEN OTHERS THEN
                -- Captura qualquer outro erro
                INSERT INTO log_insercao_erros VALUES (
                    i, 'USUARIO', 'INSERT', SQLCODE, SQLERRM,
                    'ID: ' || (2000 + i) || ', Erro inesperado',
                    USER, SYSDATE
                );
                v_count_erro := v_count_erro + 1;
        END;
    END LOOP;
    
    -- Exibir resumo
    DBMS_OUTPUT.PUT_LINE('==========================================');
    DBMS_OUTPUT.PUT_LINE('RESUMO DO PROCESSAMENTO:');
    DBMS_OUTPUT.PUT_LINE('Inserções bem-sucedidas: ' || v_count_sucesso);
    DBMS_OUTPUT.PUT_LINE('Erros capturados: ' || v_count_erro);
    DBMS_OUTPUT.PUT_LINE('==========================================');
    
    COMMIT;
END;
/

PROMPT
PROMPT Processamento concluído!
PROMPT Verifique os erros capturados:
PROMPT

-- Ver erros registrados
SELECT 
    id_log,
    tabela_destino,
    erro_codigo,
    SUBSTR(erro_mensagem, 1, 60) as mensagem_erro,
    TO_CHAR(data_erro, 'DD/MM/YYYY HH24:MI:SS') as quando
FROM log_insercao_erros
ORDER BY id_log;

PROMPT

-- =====================================================
-- SEÇÃO 12: VERIFICAÇÃO E VALIDAÇÃO DOS DADOS
-- =====================================================
-- Após inserir dados, sempre valide os resultados
-- Verifique: contagens, integridade, qualidade dos dados

PROMPT 
PROMPT ========== SEÇÃO 12: Verificação dos Dados Inseridos ==========
PROMPT

-- Verificação 1: Estatísticas de inserção
PROMPT 
PROMPT [1] Estatísticas de Inserção:
PROMPT

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

-- Verificação 2: Qualidade dos dados
PROMPT 
PROMPT [2] Verificação de Qualidade dos Dados:
PROMPT

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

-- Verificação 3: Integridade Referencial
PROMPT 
PROMPT [3] Verificação de Integridade Referencial:
PROMPT

SELECT 
    'Playlists sem usuario' as problema,
    COUNT(*) as total
FROM playlist p
LEFT JOIN usuario u ON p.id_usuario = u.id_usuario
WHERE u.id_usuario IS NULL
UNION ALL
SELECT 
    'Historico sem musica',
    COUNT(*)
FROM historico_reproducao hr
LEFT JOIN musica m ON hr.id_musica = m.id_musica
WHERE m.id_musica IS NULL
UNION ALL
SELECT 
    'Historico sem usuario',
    COUNT(*)
FROM historico_reproducao hr
LEFT JOIN usuario u ON hr.id_usuario = u.id_usuario
WHERE u.id_usuario IS NULL;

-- Verificação 4: Estatísticas Gerais
PROMPT 
PROMPT [4] Estatísticas Gerais do Sistema:
PROMPT

SELECT 
    'Total de Artistas' as metrica,
    COUNT(*) as valor
FROM artista
UNION ALL
SELECT 'Total de Álbuns', COUNT(*) FROM album
UNION ALL
SELECT 'Total de Músicas', COUNT(*) FROM musica
UNION ALL
SELECT 'Total de Usuários', COUNT(*) FROM usuario
UNION ALL
SELECT 'Total de Playlists', COUNT(*) FROM playlist
UNION ALL
SELECT 'Total de Reproduções', COUNT(*) FROM historico_reproducao;

PROMPT
PROMPT ==========================================
PROMPT VERIFICAÇÃO CONCLUÍDA!
PROMPT ==========================================
PROMPT Se todos os problemas retornaram 0,
PROMPT os dados foram inseridos corretamente.
PROMPT ==========================================
PROMPT

-- =====================================================
-- SEÇÃO 13: LIMPEZA (OPCIONAL)
-- =====================================================
-- Use esta seção para remover dados de teste e reexecutar o script
-- ATENÇÃO: Isso apagará TODOS os dados inseridos neste script!

PROMPT 
PROMPT ========== SEÇÃO 13: Limpeza (OPCIONAL) ==========
PROMPT
PROMPT ATENÇÃO: Esta seção está COMENTADA por segurança.
PROMPT Para executar a limpeza, remova os comentários /* */
PROMPT e execute cada comando individualmente.
PROMPT

/*
-- ==================================================
-- INSTRUÇÕES PARA LIMPEZA:
-- ==================================================
-- 1. Remova os comentários /* e */ desta seção
-- 2. Execute cada comando DELETE/DROP individualmente
-- 3. Verifique os resultados antes de continuar
-- 4. Execute COMMIT apenas ao final
-- ==================================================

PROMPT Removendo dados de teste...

-- Passo 1: Deletar tabelas filhas primeiro (para evitar erros de FK)
DELETE FROM historico_reproducao WHERE id_historico >= 1;
PROMPT Histórico de reprodução removido

DELETE FROM playlist_musica WHERE id_playlist >= 2000;
PROMPT Músicas de playlists removidas

DELETE FROM playlist WHERE id_playlist >= 2000;
PROMPT Playlists removidas

DELETE FROM usuario WHERE id_usuario >= 1000;
PROMPT Usuários de teste removidos

-- Passo 2: Deletar tabelas de estatísticas
DELETE FROM ranking_mensal_musica;
PROMPT Rankings removidos

DELETE FROM estatistica_artista;
PROMPT Estatísticas removidas

-- Passo 3: Remover tabelas temporárias
DROP TABLE temp_artistas_nacionais;
PROMPT Tabela temp_artistas_nacionais removida

DROP TABLE log_insercao_erros;
PROMPT Tabela log_insercao_erros removida

-- Passo 4: Remover sequences de teste
DROP SEQUENCE seq_usuario_teste;
DROP SEQUENCE seq_playlist_teste;
DROP SEQUENCE seq_historico_teste;
PROMPT Sequences removidas

-- Passo 5: Commit das alterações
COMMIT;
PROMPT
PROMPT ==========================================
PROMPT LIMPEZA CONCLUÍDA!
PROMPT ==========================================
PROMPT Todos os dados de teste foram removidos.
PROMPT Você pode reexecutar o script novamente.
PROMPT ==========================================
*/

-- ==================================================
-- FIM DO SCRIPT
-- ==================================================

PROMPT
PROMPT =====================================================
PROMPT SCRIPT CONCLUÍDO COM SUCESSO!
PROMPT =====================================================
PROMPT
PROMPT Resumo do que foi executado:
PROMPT - Criação de sequences para IDs automáticos
PROMPT - Inserção múltipla de registros
PROMPT - INSERT ALL em múltiplas tabelas
PROMPT - INSERT... SELECT para cópia de dados
PROMPT - INSERT... SELECT com agregações e transformações
PROMPT - Uso de NEXTVAL/CURRVAL para relacionamentos
PROMPT - Inserção condicional com NOT EXISTS
PROMPT - MERGE (UPSERT) para atualizar ou inserir
PROMPT - Subconsultas complexas com window functions
PROMPT - Geração automática de dados de teste
PROMPT - Tratamento de erros com PL/SQL
PROMPT - Verificação e validação dos dados
PROMPT
PROMPT =====================================================
PROMPT PRÓXIMOS PASSOS:
PROMPT =====================================================
PROMPT 1. Revise os resultados das verificações (Seção 12)
PROMPT 2. Execute queries personalizadas para explorar os dados
PROMPT 3. Pratique modificando os exemplos
PROMPT 4. Consulte o README.md para exercícios adicionais
PROMPT 5. Estude o Módulo 09 sobre Controle de Transações
PROMPT =====================================================
PROMPT
PROMPT Para salvar permanentemente os dados: COMMIT;
PROMPT Para desfazer todas as alterações: ROLLBACK;
PROMPT
PROMPT Data/Hora de conclusão:
SELECT TO_CHAR(SYSDATE, 'DD/MM/YYYY HH24:MI:SS') as fim_execucao FROM dual;
PROMPT
PROMPT =====================================================