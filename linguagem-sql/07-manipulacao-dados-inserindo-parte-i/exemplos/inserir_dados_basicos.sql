-- =====================================================
-- INSERÇÃO DE DADOS BÁSICOS - SISTEMA MUSISTREAM
-- Módulo 07: Manipulação de Dados - Inserindo Dados (Parte I)
-- =====================================================
-- IMPORTANTE: Execute o script base-script.sql primeiro!
-- Este arquivo demonstra inserções básicas consistentes com a estrutura
-- =====================================================

-- =====================================================
-- 1. CONFIGURAÇÃO DO AMBIENTE
-- =====================================================

SET ECHO ON;
SET TIMING ON;

PROMPT =====================================================;
PROMPT INSERÇÃO DE DADOS BÁSICOS NO SISTEMA MUSISTREAM;
PROMPT =====================================================;

-- =====================================================
-- 2. INSERÇÃO DE GÊNEROS MUSICAIS
-- =====================================================

PROMPT Inserindo gêneros musicais...;

INSERT INTO genero (id_genero, nome_genero, descricao) VALUES 
(seq_genero.NEXTVAL, 'Rock', 'Música caracterizada por guitarras elétricas e bateria forte');

INSERT INTO genero (id_genero, nome_genero, descricao) VALUES 
(seq_genero.NEXTVAL, 'Pop', 'Música popular com melodias cativantes e estrutura simples');

INSERT INTO genero (id_genero, nome_genero, descricao) VALUES 
(seq_genero.NEXTVAL, 'MPB', 'Música Popular Brasileira');

-- Verificar inserção de gêneros
SELECT 'GÊNEROS INSERIDOS:' as status FROM dual;
SELECT id_genero, nome_genero, descricao 
FROM genero 
ORDER BY id_genero;

-- =====================================================
-- 3. INSERÇÃO DE ARTISTAS
-- =====================================================

PROMPT Inserindo artistas...;

-- Artistas Internacionais
INSERT INTO artista (id_artista, nome_artista, biografia, data_inicio_carreira, pais_origem, ativo, numero_membros)
VALUES (seq_artista.NEXTVAL, 'The Beatles', 
        'Banda britânica de rock que revolucionou a música popular.',
        DATE '1960-08-17', 'Reino Unido', 'S', 4);

INSERT INTO artista (id_artista, nome_artista, biografia, data_inicio_carreira, pais_origem, ativo, numero_membros)
VALUES (seq_artista.NEXTVAL, 'Queen', 
        'Banda britânica conhecida por sua teatralidade.',
        DATE '1970-06-27', 'Reino Unido', 'S', 4);

-- Artistas Brasileiros
INSERT INTO artista (id_artista, nome_artista, biografia, data_inicio_carreira, pais_origem, ativo, numero_membros)
VALUES (seq_artista.NEXTVAL, 'Caetano Veloso', 
        'Cantor e compositor brasileiro, pioneiro do tropicalismo.',
        DATE '1965-01-01', 'Brasil', 'S', 1);

-- Verificar inserção de artistas
SELECT 'ARTISTAS INSERIDOS:' as status FROM dual;
SELECT id_artista, nome_artista, pais_origem, numero_membros 
FROM artista 
ORDER BY id_artista;

-- =====================================================
-- 4. INSERÇÃO DE USUÁRIOS
-- =====================================================

PROMPT Inserindo usuários...;

INSERT INTO usuario (id_usuario, nome_usuario, email, senha, data_nascimento)
VALUES (seq_usuario.NEXTVAL, 'Ana Clara Santos', 'ana.santos@email.com', 'senha123', DATE '1995-03-15');

INSERT INTO usuario (id_usuario, nome_usuario, email, senha, data_nascimento)
VALUES (seq_usuario.NEXTVAL, 'Carlos Silva', 'carlos.silva@email.com', 'minhasenha', DATE '1988-07-22');

INSERT INTO usuario (id_usuario, nome_usuario, email, senha, data_nascimento)
VALUES (seq_usuario.NEXTVAL, 'Fernanda Costa', 'fernanda.costa@email.com', 'senha456', DATE '1992-11-08');

-- Verificar inserção de usuários
SELECT 'USUÁRIOS INSERIDOS:' as status FROM dual;
SELECT id_usuario, nome_usuario, email, 
       TRUNC((SYSDATE - data_nascimento)/365.25) as idade
FROM usuario 
ORDER BY id_usuario;

-- =====================================================
-- 5. INSERÇÃO DE ÁLBUNS
-- =====================================================

PROMPT Inserindo álbuns...;

INSERT INTO album (id_album, titulo, data_lancamento, numero_faixas, duracao_total, tipo_album, id_artista)
VALUES (seq_album.NEXTVAL, 'Abbey Road', DATE '1969-09-26', 17, 2887, 'ALBUM', 
        (SELECT id_artista FROM artista WHERE nome_artista = 'The Beatles'));

INSERT INTO album (id_album, titulo, data_lancamento, numero_faixas, duracao_total, tipo_album, id_artista)
VALUES (seq_album.NEXTVAL, 'A Night at the Opera', DATE '1975-11-21', 12, 2583, 'ALBUM', 
        (SELECT id_artista FROM artista WHERE nome_artista = 'Queen'));

INSERT INTO album (id_album, titulo, data_lancamento, numero_faixas, duracao_total, tipo_album, id_artista)
VALUES (seq_album.NEXTVAL, 'Tropicália', DATE '1968-07-01', 12, 2234, 'ALBUM', 
        (SELECT id_artista FROM artista WHERE nome_artista = 'Caetano Veloso'));

-- Verificar inserção de álbuns
SELECT 'ÁLBUNS INSERIDOS:' as status FROM dual;
SELECT al.id_album, al.titulo, ar.nome_artista, al.data_lancamento
FROM album al
JOIN artista ar ON al.id_artista = ar.id_artista
ORDER BY al.id_album;

-- =====================================================
-- 6. INSERÇÃO DE MÚSICAS
-- =====================================================

PROMPT Inserindo músicas...;

-- Músicas com associação a gêneros
INSERT INTO musica (id_musica, titulo, duracao, numero_faixa, explicita, id_album, id_genero)
VALUES (seq_musica.NEXTVAL, 'Come Together', 259, 1, 'N', 
        (SELECT id_album FROM album WHERE titulo = 'Abbey Road'),
        (SELECT id_genero FROM genero WHERE nome_genero = 'Rock'));

INSERT INTO musica (id_musica, titulo, duracao, numero_faixa, explicita, id_album, id_genero)
VALUES (seq_musica.NEXTVAL, 'Bohemian Rhapsody', 355, 1, 'N', 
        (SELECT id_album FROM album WHERE titulo = 'A Night at the Opera'),
        (SELECT id_genero FROM genero WHERE nome_genero = 'Rock'));

INSERT INTO musica (id_musica, titulo, duracao, numero_faixa, explicita, id_album, id_genero)
VALUES (seq_musica.NEXTVAL, 'Tropicália', 234, 1, 'N', 
        (SELECT id_album FROM album WHERE titulo = 'Tropicália'),
        (SELECT id_genero FROM genero WHERE nome_genero = 'MPB'));

-- Verificar inserção de músicas
SELECT 'MÚSICAS INSERIDAS:' as status FROM dual;
SELECT m.id_musica, m.titulo, ar.nome_artista, g.nome_genero,
       TRUNC(m.duracao/60) || ':' || LPAD(MOD(m.duracao,60), 2, '0') as duracao_formatada
FROM musica m
JOIN album al ON m.id_album = al.id_album
JOIN artista ar ON al.id_artista = ar.id_artista
JOIN genero g ON m.id_genero = g.id_genero
ORDER BY m.id_musica;

-- =====================================================
-- 7. INSERÇÃO DE HISTÓRICO DE REPRODUÇÃO (AMOSTRAS)
-- =====================================================

PROMPT Inserindo histórico de reprodução...;

INSERT INTO historico_reproducao (id_historico, id_usuario, id_musica, duracao_ouvida, dispositivo)
VALUES (seq_historico.NEXTVAL, 
        (SELECT id_usuario FROM usuario WHERE nome_usuario = 'Ana Clara Santos'),
        (SELECT id_musica FROM musica WHERE titulo = 'Come Together'),
        259, 'mobile_android');

INSERT INTO historico_reproducao (id_historico, id_usuario, id_musica, duracao_ouvida, dispositivo)
VALUES (seq_historico.NEXTVAL, 
        (SELECT id_usuario FROM usuario WHERE nome_usuario = 'Carlos Silva'),
        (SELECT id_musica FROM musica WHERE titulo = 'Bohemian Rhapsody'),
        355, 'web');

-- Verificar inserção do histórico
SELECT 'HISTÓRICO DE REPRODUÇÃO:' as status FROM dual;
SELECT h.id_historico, u.nome_usuario, m.titulo, ar.nome_artista, 
       h.data_reproducao, h.dispositivo
FROM historico_reproducao h
JOIN usuario u ON h.id_usuario = u.id_usuario
JOIN musica m ON h.id_musica = m.id_musica
JOIN album al ON m.id_album = al.id_album
JOIN artista ar ON al.id_artista = ar.id_artista
ORDER BY h.data_reproducao;

-- =====================================================
-- 8. RESUMO FINAL
-- =====================================================

PROMPT =====================================================;
PROMPT RESUMO DOS DADOS INSERIDOS;
PROMPT =====================================================;

SELECT 
    (SELECT COUNT(*) FROM genero) as total_generos,
    (SELECT COUNT(*) FROM artista) as total_artistas,
    (SELECT COUNT(*) FROM usuario) as total_usuarios,
    (SELECT COUNT(*) FROM album) as total_albums,
    (SELECT COUNT(*) FROM musica) as total_musicas,
    (SELECT COUNT(*) FROM historico_reproducao) as total_reproducoes
FROM dual;

PROMPT Inserção de dados básicos concluída com sucesso!;

-- Resetar configurações
SET ECHO OFF;
SET TIMING OFF;