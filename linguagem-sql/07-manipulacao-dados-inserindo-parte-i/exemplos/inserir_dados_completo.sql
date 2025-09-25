-- =====================================================
-- INSERÇÃO DE DADOS COMPLETO - SISTEMA MUSISTREAM
-- Módulo 07: Manipulação de Dados - Inserindo Dados (Parte I)
-- =====================================================
-- IMPORTANTE: Execute o script base-script.sql primeiro!
-- Este script demonstra todos os conceitos de INSERT do módulo
-- =====================================================

-- =====================================================
-- 1. CONFIGURAÇÃO DO AMBIENTE
-- =====================================================

SET ECHO ON;
SET TIMING ON;

PROMPT =====================================================;
PROMPT INICIANDO INSERÇÃO COMPLETA DE DADOS NO MUSISTREAM;
PROMPT =====================================================;

-- =====================================================
-- 2. INSERÇÃO DE GÊNEROS MUSICAIS
-- =====================================================

PROMPT Inserindo gêneros musicais...;

-- Inserção usando sequences (recomendado)
INSERT INTO genero (id_genero, nome_genero, descricao) VALUES 
(seq_genero.NEXTVAL, 'Rock', 'Música caracterizada por guitarras elétricas e bateria forte');

INSERT INTO genero (id_genero, nome_genero, descricao) VALUES 
(seq_genero.NEXTVAL, 'Pop', 'Música popular com melodias cativantes e estrutura simples');

INSERT INTO genero (id_genero, nome_genero, descricao) VALUES 
(seq_genero.NEXTVAL, 'Jazz', 'Música com improvisação e harmonias complexas');

INSERT INTO genero (id_genero, nome_genero, descricao) VALUES 
(seq_genero.NEXTVAL, 'MPB', 'Música Popular Brasileira');

INSERT INTO genero (id_genero, nome_genero, descricao) VALUES 
(seq_genero.NEXTVAL, 'Funk', 'Música brasileira com batidas marcantes');

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
        'Banda britânica conhecida por sua teatralidade e pela voz de Freddie Mercury.',
        DATE '1970-06-27', 'Reino Unido', 'S', 4);

-- Artistas Brasileiros
INSERT INTO artista (id_artista, nome_artista, biografia, data_inicio_carreira, pais_origem, ativo, numero_membros)
VALUES (seq_artista.NEXTVAL, 'Caetano Veloso', 
        'Cantor, compositor e escritor brasileiro, pioneiro do tropicalismo.',
        DATE '1965-01-01', 'Brasil', 'S', 1);

INSERT INTO artista (id_artista, nome_artista, biografia, data_inicio_carreira, pais_origem, ativo, numero_membros)
VALUES (seq_artista.NEXTVAL, 'Anitta', 
        'Cantora brasileira, representante do funk e pop nacional.',
        DATE '2010-01-01', 'Brasil', 'S', 1);

-- Verificar inserção de artistas
SELECT 'ARTISTAS INSERIDOS:' as status FROM dual;
SELECT id_artista, nome_artista, pais_origem, numero_membros, ativo
FROM artista 
ORDER BY id_artista;

-- =====================================================
-- 4. INSERÇÃO DE USUÁRIOS
-- =====================================================

PROMPT Inserindo usuários...;

-- Demonstração de inserção múltipla (mais eficiente)
INSERT INTO usuario (id_usuario, nome_usuario, email, senha, data_nascimento, pais)
SELECT seq_usuario.NEXTVAL, nome, email, senha, data_nasc, pais FROM (
    SELECT 'Ana Clara Santos' as nome, 'ana.santos@email.com' as email, 'senha123' as senha, DATE '1995-03-15' as data_nasc, 'Brasil' as pais FROM dual
    UNION ALL
    SELECT 'Carlos Silva', 'carlos.silva@email.com', 'minhasenha', DATE '1988-07-22', 'Brasil' FROM dual
    UNION ALL
    SELECT 'Fernanda Costa', 'fernanda.costa@email.com', 'senha456', DATE '1992-11-08', 'Brasil' FROM dual
    UNION ALL
    SELECT 'John Smith', 'john.smith@email.com', 'password123', DATE '1985-05-30', 'Estados Unidos' FROM dual
    UNION ALL
    SELECT 'Marie Dubois', 'marie.dubois@email.com', 'motdepasse', DATE '1998-09-12', 'França' FROM dual
);

-- Verificar inserção de usuários
SELECT 'USUÁRIOS INSERIDOS:' as status FROM dual;
SELECT id_usuario, nome_usuario, email, pais,
       TRUNC((SYSDATE - data_nascimento)/365.25) as idade
FROM usuario 
ORDER BY id_usuario;

-- =====================================================
-- 5. INSERÇÃO DE ÁLBUNS (COM INTEGRIDADE REFERENCIAL)
-- =====================================================

PROMPT Inserindo álbuns...;

-- Álbuns usando subqueries para garantir integridade referencial
INSERT INTO album (id_album, titulo, data_lancamento, numero_faixas, duracao_total, tipo_album, id_artista)
VALUES (seq_album.NEXTVAL, 'Abbey Road', DATE '1969-09-26', 17, 2887, 'ALBUM', 
        (SELECT id_artista FROM artista WHERE nome_artista = 'The Beatles'));

INSERT INTO album (id_album, titulo, data_lancamento, numero_faixas, duracao_total, tipo_album, id_artista)
VALUES (seq_album.NEXTVAL, 'A Night at the Opera', DATE '1975-11-21', 12, 2583, 'ALBUM', 
        (SELECT id_artista FROM artista WHERE nome_artista = 'Queen'));

INSERT INTO album (id_album, titulo, data_lancamento, numero_faixas, duracao_total, tipo_album, id_artista)
VALUES (seq_album.NEXTVAL, 'Tropicália', DATE '1968-07-01', 12, 2234, 'ALBUM', 
        (SELECT id_artista FROM artista WHERE nome_artista = 'Caetano Veloso'));

INSERT INTO album (id_album, titulo, data_lancamento, numero_faixas, duracao_total, tipo_album, id_artista)
VALUES (seq_album.NEXTVAL, 'Kisses', DATE '2019-04-05', 15, 2445, 'ALBUM', 
        (SELECT id_artista FROM artista WHERE nome_artista = 'Anitta'));

-- Single exemplo
INSERT INTO album (id_album, titulo, data_lancamento, numero_faixas, duracao_total, tipo_album, id_artista)
VALUES (seq_album.NEXTVAL, 'Bohemian Rhapsody', DATE '1975-10-31', 1, 355, 'SINGLE', 
        (SELECT id_artista FROM artista WHERE nome_artista = 'Queen'));

-- Verificar inserção de álbuns
SELECT 'ÁLBUNS INSERIDOS:' as status FROM dual;
SELECT al.id_album, al.titulo, ar.nome_artista, al.tipo_album, al.data_lancamento
FROM album al
JOIN artista ar ON al.id_artista = ar.id_artista
ORDER BY al.id_album;

-- =====================================================
-- 6. INSERÇÃO DE MÚSICAS
-- =====================================================

PROMPT Inserindo músicas...;

-- Músicas do Abbey Road com associação ao gênero
INSERT INTO musica (id_musica, titulo, duracao, numero_faixa, explicita, id_album, id_genero)
VALUES (seq_musica.NEXTVAL, 'Come Together', 259, 1, 'N', 
        (SELECT id_album FROM album WHERE titulo = 'Abbey Road'),
        (SELECT id_genero FROM genero WHERE nome_genero = 'Rock'));

INSERT INTO musica (id_musica, titulo, duracao, numero_faixa, explicita, id_album, id_genero)
VALUES (seq_musica.NEXTVAL, 'Something', 182, 2, 'N', 
        (SELECT id_album FROM album WHERE titulo = 'Abbey Road'),
        (SELECT id_genero FROM genero WHERE nome_genero = 'Rock'));

INSERT INTO musica (id_musica, titulo, duracao, numero_faixa, explicita, id_album, id_genero)
VALUES (seq_musica.NEXTVAL, 'Here Comes the Sun', 185, 7, 'N', 
        (SELECT id_album FROM album WHERE titulo = 'Abbey Road'),
        (SELECT id_genero FROM genero WHERE nome_genero = 'Rock'));

-- Música do Queen
INSERT INTO musica (id_musica, titulo, duracao, numero_faixa, explicita, id_album, id_genero)
VALUES (seq_musica.NEXTVAL, 'Bohemian Rhapsody', 355, 1, 'N', 
        (SELECT id_album FROM album WHERE titulo = 'A Night at the Opera'),
        (SELECT id_genero FROM genero WHERE nome_genero = 'Rock'));

-- Música do Caetano
INSERT INTO musica (id_musica, titulo, duracao, numero_faixa, explicita, id_album, id_genero)
VALUES (seq_musica.NEXTVAL, 'Tropicália', 234, 1, 'N', 
        (SELECT id_album FROM album WHERE titulo = 'Tropicália'),
        (SELECT id_genero FROM genero WHERE nome_genero = 'MPB'));

-- Música da Anitta
INSERT INTO musica (id_musica, titulo, duracao, numero_faixa, explicita, id_album, id_genero)
VALUES (seq_musica.NEXTVAL, 'Downtown', 187, 1, 'N', 
        (SELECT id_album FROM album WHERE titulo = 'Kisses'),
        (SELECT id_genero FROM genero WHERE nome_genero = 'Funk'));

-- Verificar inserção de músicas
SELECT 'MÚSICAS INSERIDAS:' as status FROM dual;
SELECT m.id_musica, m.titulo, ar.nome_artista, al.titulo as album, g.nome_genero,
       TRUNC(m.duracao/60) || ':' || LPAD(MOD(m.duracao,60), 2, '0') as duracao_formatada
FROM musica m
JOIN album al ON m.id_album = al.id_album
JOIN artista ar ON al.id_artista = ar.id_artista
JOIN genero g ON m.id_genero = g.id_genero
ORDER BY m.id_musica;

-- =====================================================
-- 7. INSERÇÃO DE TIPOS DE ASSINATURA
-- =====================================================

PROMPT Inserindo tipos de assinatura...;

INSERT INTO tipo_assinatura (id_tipo_assinatura, nome_plano, preco_mensal, qualidade_audio, downloads_offline, sem_anuncios, descricao)
VALUES (seq_tipo_assinatura.NEXTVAL, 'Gratuito', 0.00, 'Padrão', 'N', 'N', 'Plano gratuito com anúncios');

INSERT INTO tipo_assinatura (id_tipo_assinatura, nome_plano, preco_mensal, qualidade_audio, downloads_offline, sem_anuncios, descricao)
VALUES (seq_tipo_assinatura.NEXTVAL, 'Premium', 19.90, 'Alta', 'S', 'S', 'Plano premium sem anúncios');

INSERT INTO tipo_assinatura (id_tipo_assinatura, nome_plano, preco_mensal, qualidade_audio, downloads_offline, sem_anuncios, descricao)
VALUES (seq_tipo_assinatura.NEXTVAL, 'Família', 29.90, 'Alta', 'S', 'S', 'Plano familiar até 6 contas');

-- =====================================================
-- 8. INSERÇÃO DE ASSINATURAS
-- =====================================================

PROMPT Inserindo assinaturas de usuários...;

-- Demonstração de transação
BEGIN
    -- Ana com plano Premium
    INSERT INTO assinatura (id_assinatura, data_inicio, status_assinatura, metodo_pagamento, valor_pago, id_usuario, id_tipo_assinatura)
    VALUES (seq_assinatura.NEXTVAL, DATE '2023-01-15', 'ATIVA', 'Cartão de Crédito', 19.90,
            (SELECT id_usuario FROM usuario WHERE nome_usuario = 'Ana Clara Santos'),
            (SELECT id_tipo_assinatura FROM tipo_assinatura WHERE nome_plano = 'Premium'));
    
    -- Carlos com plano Família
    INSERT INTO assinatura (id_assinatura, data_inicio, status_assinatura, metodo_pagamento, valor_pago, id_usuario, id_tipo_assinatura)
    VALUES (seq_assinatura.NEXTVAL, DATE '2023-02-01', 'ATIVA', 'Pix', 29.90,
            (SELECT id_usuario FROM usuario WHERE nome_usuario = 'Carlos Silva'),
            (SELECT id_tipo_assinatura FROM tipo_assinatura WHERE nome_plano = 'Família'));
    
    COMMIT;
END;
/

-- =====================================================
-- 9. INSERÇÃO DE PLAYLISTS
-- =====================================================

PROMPT Inserindo playlists...;

INSERT INTO playlist (id_playlist, nome_playlist, descricao, publica, total_musicas, id_usuario)
VALUES (seq_playlist.NEXTVAL, 'Clássicos do Rock', 'Os maiores sucessos do rock mundial', 'S', 0,
        (SELECT id_usuario FROM usuario WHERE nome_usuario = 'Ana Clara Santos'));

INSERT INTO playlist (id_playlist, nome_playlist, descricao, publica, total_musicas, id_usuario)
VALUES (seq_playlist.NEXTVAL, 'MPB Brasileira', 'O melhor da música popular brasileira', 'S', 0,
        (SELECT id_usuario FROM usuario WHERE nome_usuario = 'Carlos Silva'));

-- =====================================================
-- 10. INSERÇÃO DE MÚSICAS NAS PLAYLISTS
-- =====================================================

PROMPT Associando músicas às playlists...;

-- Playlist Clássicos do Rock
INSERT INTO playlist_musica (id_playlist, id_musica, ordem_musica)
VALUES ((SELECT id_playlist FROM playlist WHERE nome_playlist = 'Clássicos do Rock'),
        (SELECT id_musica FROM musica WHERE titulo = 'Come Together'), 1);

INSERT INTO playlist_musica (id_playlist, id_musica, ordem_musica)
VALUES ((SELECT id_playlist FROM playlist WHERE nome_playlist = 'Clássicos do Rock'),
        (SELECT id_musica FROM musica WHERE titulo = 'Bohemian Rhapsody'), 2);

-- Playlist MPB
INSERT INTO playlist_musica (id_playlist, id_musica, ordem_musica)
VALUES ((SELECT id_playlist FROM playlist WHERE nome_playlist = 'MPB Brasileira'),
        (SELECT id_musica FROM musica WHERE titulo = 'Tropicália'), 1);

-- =====================================================
-- 11. INSERÇÃO DE HISTÓRICO DE REPRODUÇÃO
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
        (SELECT id_musica FROM musica WHERE titulo = 'Tropicália'),
        234, 'web');

-- =====================================================
-- 12. VERIFICAÇÃO FINAL E RELATÓRIOS
-- =====================================================

PROMPT =====================================================;
PROMPT VERIFICAÇÃO FINAL DOS DADOS INSERIDOS;
PROMPT =====================================================;

-- Resumo de dados inseridos
SELECT 'RESUMO DOS DADOS:' as status FROM dual;
SELECT 
    (SELECT COUNT(*) FROM genero) as total_generos,
    (SELECT COUNT(*) FROM artista) as total_artistas,
    (SELECT COUNT(*) FROM usuario) as total_usuarios,
    (SELECT COUNT(*) FROM album) as total_albums,
    (SELECT COUNT(*) FROM musica) as total_musicas,
    (SELECT COUNT(*) FROM playlist) as total_playlists,
    (SELECT COUNT(*) FROM assinatura) as total_assinaturas,
    (SELECT COUNT(*) FROM historico_reproducao) as total_reproducoes
FROM dual;

-- Verificar integridade referencial
SELECT 'VERIFICAÇÃO DE INTEGRIDADE:' as status FROM dual;

-- Álbuns sem artistas (deve retornar 0)
SELECT COUNT(*) as albums_sem_artista
FROM album 
WHERE id_artista NOT IN (SELECT id_artista FROM artista);

-- Músicas sem álbuns (deve retornar 0)
SELECT COUNT(*) as musicas_sem_album
FROM musica 
WHERE id_album NOT IN (SELECT id_album FROM album);

-- Relatório por país
SELECT 'ARTISTAS POR PAÍS:' as status FROM dual;
SELECT pais_origem, COUNT(*) as quantidade
FROM artista 
GROUP BY pais_origem
ORDER BY quantidade DESC;

-- Usuários por país
SELECT 'USUÁRIOS POR PAÍS:' as status FROM dual;
SELECT pais, COUNT(*) as quantidade
FROM usuario 
GROUP BY pais
ORDER BY quantidade DESC;

-- Planos de assinatura mais populares
SELECT 'ASSINATURAS POR PLANO:' as status FROM dual;
SELECT ta.nome_plano, COUNT(a.id_assinatura) as total_assinantes
FROM tipo_assinatura ta
LEFT JOIN assinatura a ON ta.id_tipo_assinatura = a.id_tipo_assinatura
GROUP BY ta.nome_plano
ORDER BY total_assinantes DESC;

PROMPT =====================================================;
PROMPT INSERÇÃO DE DADOS CONCLUÍDA COM SUCESSO!;
PROMPT Todas as tabelas foram populadas com dados consistentes;
PROMPT e respeitando a integridade referencial.;
PROMPT =====================================================;

-- Resetar configurações
SET ECHO OFF;
SET TIMING OFF;