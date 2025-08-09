-- =====================================================
-- CONTROLE DE TRANSAÇÕES E RELATÓRIOS - SISTEMA MUSISTREAM
-- Módulo 09: COMMIT, ROLLBACK, SAVEPOINT e Relatórios
-- =====================================================

-- =====================================================
-- 1. DEMONSTRAÇÃO DE TRANSAÇÕES BÁSICAS
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
-- 2. RELATÓRIOS FORMATADOS
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