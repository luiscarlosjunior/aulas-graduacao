-- =====================================================
-- TESTE DE VALIDAÇÃO - MÓDULO 07
-- Valida se o base-script.sql está funcionando corretamente
-- =====================================================

-- Execute: @base-script.sql primeiro

-- Teste 1: Inserir gênero usando sequence
INSERT INTO genero (id_genero, nome_genero, descricao) 
VALUES (seq_genero.NEXTVAL, 'Teste Rock', 'Gênero para teste');

-- Teste 2: Inserir artista com todos os novos campos
INSERT INTO artista (id_artista, nome_artista, biografia, data_inicio_carreira, pais_origem, ativo, numero_membros)
VALUES (seq_artista.NEXTVAL, 'Banda Teste', 'Bio teste', DATE '2020-01-01', 'Brasil', 'S', 4);

-- Teste 3: Inserir usuário com senha obrigatória
INSERT INTO usuario (id_usuario, nome_usuario, email, senha, data_nascimento)
VALUES (seq_usuario.NEXTVAL, 'Usuário Teste', 'teste@email.com', 'senha123', DATE '1990-01-01');

-- Teste 4: Inserir álbum com tipo_album
INSERT INTO album (id_album, titulo, data_lancamento, numero_faixas, duracao_total, tipo_album, id_artista)
VALUES (seq_album.NEXTVAL, 'Álbum Teste', DATE '2023-01-01', 10, 3000, 'ALBUM', 
        (SELECT id_artista FROM artista WHERE nome_artista = 'Banda Teste'));

-- Teste 5: Inserir música com campo explicita
INSERT INTO musica (id_musica, titulo, duracao, numero_faixa, explicita, id_album, id_genero)
VALUES (seq_musica.NEXTVAL, 'Música Teste', 180, 1, 'N', 
        (SELECT id_album FROM album WHERE titulo = 'Álbum Teste'),
        (SELECT id_genero FROM genero WHERE nome_genero = 'Teste Rock'));

-- Teste 6: Inserir histórico com novos campos
INSERT INTO historico_reproducao (id_historico, id_usuario, id_musica, duracao_ouvida, dispositivo, localizacao)
VALUES (seq_historico.NEXTVAL,
        (SELECT id_usuario FROM usuario WHERE nome_usuario = 'Usuário Teste'),
        (SELECT id_musica FROM musica WHERE titulo = 'Música Teste'),
        180, 'web', 'São Paulo, Brasil');

-- Verificação final
SELECT 'TESTE CONCLUÍDO COM SUCESSO' AS resultado FROM dual;
SELECT 
    (SELECT COUNT(*) FROM genero) AS generos,
    (SELECT COUNT(*) FROM artista) AS artistas,
    (SELECT COUNT(*) FROM usuario) AS usuarios,
    (SELECT COUNT(*) FROM album) AS albums,
    (SELECT COUNT(*) FROM musica) AS musicas,
    (SELECT COUNT(*) FROM historico_reproducao) AS historico
FROM dual;

-- Limpeza dos dados de teste
DELETE FROM historico_reproducao WHERE dispositivo = 'web';
DELETE FROM musica WHERE titulo = 'Música Teste';
DELETE FROM album WHERE titulo = 'Álbum Teste';
DELETE FROM usuario WHERE nome_usuario = 'Usuário Teste';
DELETE FROM artista WHERE nome_artista = 'Banda Teste';
DELETE FROM genero WHERE nome_genero = 'Teste Rock';

COMMIT;