-- =====================================================
-- INSERÇÃO DE DADOS BÁSICOS - SISTEMA MUSISTREAM
-- Módulo 07: Manipulação de Dados - Inserindo Dados (Parte I)
-- =====================================================

-- =====================================================
-- 1. CONFIGURAÇÃO DO AMBIENTE
-- =====================================================

SET ECHO ON;
SET TIMING ON;

-- Limpar dados existentes (se necessário)
-- CUIDADO: Isso removerá todos os dados das tabelas!
/*
DELETE FROM historico_reproducao;
DELETE FROM musica;
DELETE FROM album;
DELETE FROM artista;
DELETE FROM usuario;
*/

-- =====================================================
-- 2. INSERÇÃO DE ARTISTAS
-- =====================================================

-- Artistas Internacionais Clássicos
INSERT INTO artista (id_artista, nome_artista, biografia, data_formacao, pais_origem, ativo, numero_membros)
VALUES (1, 'The Beatles', 
        'Banda britânica de rock formada em Liverpool em 1960. Considerada uma das bandas mais influentes da história da música popular.',
        '1960-08-17', 'Reino Unido', TRUE, 4);

INSERT INTO artista (id_artista, nome_artista, biografia, data_formacao, pais_origem, ativo, numero_membros)
VALUES (2, 'Queen', 
        'Banda britânica de rock formada em Londres em 1970, conhecida por sua teatralidade e pela voz de Freddie Mercury.',
        '1970-06-27', 'Reino Unido', TRUE, 4);

INSERT INTO artista (id_artista, nome_artista, biografia, data_formacao, pais_origem, ativo, numero_membros)
VALUES (3, 'Bob Dylan', 
        'Cantor, compositor e escritor americano. Vencedor do Prêmio Nobel de Literatura em 2016.',
        '1961-01-01', 'Estados Unidos', TRUE, 1);

-- Artistas Brasileiros
INSERT INTO artista (id_artista, nome_artista, biografia, data_formacao, pais_origem, ativo, numero_membros)
VALUES (4, 'Caetano Veloso', 
        'Cantor, compositor, escritor e político brasileiro. Um dos pioneiros do movimento tropicalista.',
        '1965-01-01', 'Brasil', TRUE, 1);

INSERT INTO artista (id_artista, nome_artista, biografia, data_formacao, pais_origem, ativo, numero_membros)
VALUES (5, 'Legião Urbana', 
        'Banda brasiliense de rock formada em 1982 por Renato Russo, Dado Villa-Lobos e Marcelo Bonfá.',
        '1982-03-01', 'Brasil', FALSE, 4);

INSERT INTO artista (id_artista, nome_artista, biografia, data_formacao, pais_origem, ativo, numero_membros)
VALUES (6, 'Anitta', 
        'Cantora, compositora, atriz e empresária brasileira. Uma das principais representantes do funk e pop brasileiro.',
        '2010-01-01', 'Brasil', TRUE, 1);

-- Verificar inserção de artistas
SELECT 'ARTISTAS INSERIDOS:' as status;
SELECT id_artista, nome_artista, pais_origem, numero_membros 
FROM artista 
ORDER BY id_artista;

-- =====================================================
-- 3. INSERÇÃO DE USUÁRIOS
-- =====================================================

-- Usuários diversos
INSERT INTO usuario (id_usuario, nome_usuario, email, data_nascimento)
VALUES (1, 'Ana Clara Santos', 'ana.santos@email.com', '1995-03-15');

INSERT INTO usuario (id_usuario, nome_usuario, email, data_nascimento)
VALUES (2, 'Carlos Eduardo Silva', 'carlos.silva@email.com', '1988-07-22');

INSERT INTO usuario (id_usuario, nome_usuario, email, data_nascimento)
VALUES (3, 'Fernanda Costa', 'fernanda.costa@email.com', '1992-11-08');

INSERT INTO usuario (id_usuario, nome_usuario, email, data_nascimento)
VALUES (4, 'Roberto Mendes', 'roberto.mendes@email.com', '1985-05-30');

INSERT INTO usuario (id_usuario, nome_usuario, email, data_nascimento)
VALUES (5, 'Julia Rodrigues', 'julia.rodrigues@email.com', '1998-09-12');

-- Verificar inserção de usuários
SELECT 'USUÁRIOS INSERIDOS:' as status;
SELECT id_usuario, nome_usuario, email, 
       FLOOR(DATEDIFF(CURRENT_DATE, data_nascimento)/365) as idade
FROM usuario 
ORDER BY id_usuario;

-- =====================================================
-- 4. INSERÇÃO DE ÁLBUNS
-- =====================================================

-- Álbuns dos Beatles
INSERT INTO album (id_album, titulo, data_lancamento, numero_faixas, duracao_total, tipo_album, id_artista)
VALUES (1, 'Abbey Road', '1969-09-26', 17, 2887, 'album', 1);

INSERT INTO album (id_album, titulo, data_lancamento, numero_faixas, duracao_total, tipo_album, id_artista)
VALUES (2, 'Sgt. Pepper''s Lonely Hearts Club Band', '1967-06-01', 13, 2389, 'album', 1);

INSERT INTO album (id_album, titulo, data_lancamento, numero_faixas, duracao_total, tipo_album, id_artista)
VALUES (3, 'Let It Be', '1970-05-08', 12, 2155, 'album', 1);

-- Álbuns do Queen
INSERT INTO album (id_album, titulo, data_lancamento, numero_faixas, duracao_total, tipo_album, id_artista)
VALUES (4, 'A Night at the Opera', '1975-11-21', 12, 2583, 'album', 2);

INSERT INTO album (id_album, titulo, data_lancamento, numero_faixas, duracao_total, tipo_album, id_artista)
VALUES (5, 'News of the World', '1977-10-28', 11, 2342, 'album', 2);

-- Álbum do Bob Dylan
INSERT INTO album (id_album, titulo, data_lancamento, numero_faixas, duracao_total, tipo_album, id_artista)
VALUES (6, 'Highway 61 Revisited', '1965-08-30', 9, 2592, 'album', 3);

-- Álbuns Brasileiros
INSERT INTO album (id_album, titulo, data_lancamento, numero_faixas, duracao_total, tipo_album, id_artista)
VALUES (7, 'Tropicália', '1968-07-01', 12, 2234, 'album', 4);

INSERT INTO album (id_album, titulo, data_lancamento, numero_faixas, duracao_total, tipo_album, id_artista)
VALUES (8, 'Dois', '1986-01-01', 11, 2876, 'album', 5);

INSERT INTO album (id_album, titulo, data_lancamento, numero_faixas, duracao_total, tipo_album, id_artista)
VALUES (9, 'Kisses', '2019-04-05', 15, 2445, 'album', 6);

-- Verificar inserção de álbuns
SELECT 'ÁLBUNS INSERIDOS:' as status;
SELECT al.id_album, al.titulo, ar.nome_artista, al.data_lancamento, al.numero_faixas
FROM album al
JOIN artista ar ON al.id_artista = ar.id_artista
ORDER BY al.id_album;

-- =====================================================
-- 5. INSERÇÃO DE MÚSICAS
-- =====================================================

-- Músicas do Abbey Road (The Beatles)
INSERT INTO musica (id_musica, titulo, duracao, numero_faixa, explicita, id_album)
VALUES 
(1, 'Come Together', 259, 1, FALSE, 1),
(2, 'Something', 182, 2, FALSE, 1),
(3, 'Maxwell''s Silver Hammer', 207, 3, FALSE, 1),
(4, 'Oh! Darling', 206, 4, FALSE, 1),
(5, 'Octopus''s Garden', 171, 5, FALSE, 1);

-- Músicas do Sgt. Pepper's (The Beatles)
INSERT INTO musica (id_musica, titulo, duracao, numero_faixa, explicita, id_album)
VALUES 
(6, 'Sgt. Pepper''s Lonely Hearts Club Band', 122, 1, FALSE, 2),
(7, 'With a Little Help from My Friends', 164, 2, FALSE, 2),
(8, 'Lucy in the Sky with Diamonds', 208, 3, FALSE, 2),
(9, 'Getting By', 147, 4, FALSE, 2);

-- Músicas do Queen
INSERT INTO musica (id_musica, titulo, duracao, numero_faixa, explicita, id_album)
VALUES 
(10, 'Bohemian Rhapsody', 355, 1, FALSE, 4),
(11, 'You''re My Best Friend', 172, 2, FALSE, 4),
(12, 'I''m in Love with My Car', 182, 3, FALSE, 4),
(13, 'We Will Rock You', 122, 1, FALSE, 5),
(14, 'We Are the Champions', 179, 2, FALSE, 5);

-- Músicas Brasileiras
INSERT INTO musica (id_musica, titulo, duracao, numero_faixa, explicita, id_album)
VALUES 
(15, 'Tropicália', 234, 1, FALSE, 7),
(16, 'Clarice', 198, 2, FALSE, 7),
(17, 'Tempo Perdido', 298, 1, FALSE, 8),
(18, 'Índios', 356, 2, FALSE, 8),
(19, 'Downtown', 187, 1, FALSE, 9),
(20, 'Me Gusta', 141, 2, FALSE, 9);

-- Verificar inserção de músicas
SELECT 'MÚSICAS INSERIDAS:' as status;
SELECT m.id_musica, m.titulo, ar.nome_artista, al.titulo as album, 
       CONCAT(FLOOR(m.duracao/60), ':', LPAD(m.duracao%60, 2, '0')) as duracao_formatada
FROM musica m
JOIN album al ON m.id_album = al.id_album
JOIN artista ar ON al.id_artista = ar.id_artista
ORDER BY m.id_musica;

-- =====================================================
-- 6. INSERÇÃO DE HISTÓRICO DE REPRODUÇÃO (AMOSTRAS)
-- =====================================================

-- Histórico de reprodução de alguns usuários
INSERT INTO historico_reproducao (id_historico, id_usuario, id_musica, data_reproducao, duracao_ouvida, dispositivo)
VALUES 
(1, 1, 1, '2023-08-01 10:30:00', 259, 'mobile_android'),
(2, 1, 10, '2023-08-01 10:35:00', 355, 'mobile_android'),
(3, 2, 15, '2023-08-01 14:20:00', 234, 'web'),
(4, 3, 17, '2023-08-01 16:45:00', 298, 'desktop'),
(5, 1, 2, '2023-08-02 09:15:00', 182, 'mobile_android');

-- Verificar inserção do histórico
SELECT 'HISTÓRICO DE REPRODUÇÃO:' as status;
SELECT h.id_historico, u.nome_usuario, m.titulo, ar.nome_artista, 
       h.data_reproducao, h.dispositivo
FROM historico_reproducao h
JOIN usuario u ON h.id_usuario = u.id_usuario
JOIN musica m ON h.id_musica = m.id_musica
JOIN album al ON m.id_album = al.id_album
JOIN artista ar ON al.id_artista = ar.id_artista
ORDER BY h.data_reproducao;

-- =====================================================
-- 7. VERIFICAÇÃO GERAL DOS DADOS
-- =====================================================

-- Resumo de dados inseridos
SELECT 'RESUMO DOS DADOS INSERIDOS:' as status;
SELECT 
    (SELECT COUNT(*) FROM artista) as total_artistas,
    (SELECT COUNT(*) FROM usuario) as total_usuarios,
    (SELECT COUNT(*) FROM album) as total_albums,
    (SELECT COUNT(*) FROM musica) as total_musicas,
    (SELECT COUNT(*) FROM historico_reproducao) as total_reproducoes;

-- =====================================================
-- 8. EXEMPLOS DE CONSULTAS BÁSICAS
-- =====================================================

-- Artistas por país
SELECT 'ARTISTAS POR PAÍS:' as status;
SELECT pais_origem, COUNT(*) as quantidade
FROM artista 
GROUP BY pais_origem
ORDER BY quantidade DESC;

-- Álbuns por década
SELECT 'ÁLBUNS POR DÉCADA:' as status;
SELECT 
    CONCAT(FLOOR(YEAR(data_lancamento)/10)*10, 's') as decada,
    COUNT(*) as quantidade
FROM album 
GROUP BY FLOOR(YEAR(data_lancamento)/10)
ORDER BY decada;

-- Usuários mais jovens
SELECT 'USUÁRIOS MAIS JOVENS:' as status;
SELECT nome_usuario, data_nascimento,
       FLOOR(DATEDIFF(CURRENT_DATE, data_nascimento)/365) as idade
FROM usuario 
ORDER BY data_nascimento DESC
LIMIT 3;

-- =====================================================
-- 9. DEMONSTRAÇÃO DE ERROS COMUNS
-- =====================================================

-- Exemplo de erro de chave primária duplicada
SELECT 'DEMONSTRAÇÃO DE ERROS:' as status;
/*
-- Este comando gerará erro
INSERT INTO artista (id_artista, nome_artista, pais_origem)
VALUES (1, 'Artista Duplicado', 'Brasil');
*/

-- Exemplo de erro de chave estrangeira
/*
-- Este comando gerará erro
INSERT INTO album (id_album, titulo, id_artista)
VALUES (999, 'Álbum Órfão', 999);
*/

-- Exemplo de erro de constraint CHECK
/*
-- Este comando gerará erro
INSERT INTO artista (id_artista, nome_artista, numero_membros)
VALUES (999, 'Banda Impossível', 25);
*/

-- =====================================================
-- 10. COMANDOS DE VERIFICAÇÃO
-- =====================================================

-- Verificar integridade referencial
SELECT 'VERIFICAÇÃO DE INTEGRIDADE:' as status;

-- Álbuns sem artistas (deve retornar 0 registros)
SELECT COUNT(*) as albums_sem_artista
FROM album 
WHERE id_artista NOT IN (SELECT id_artista FROM artista);

-- Músicas sem álbuns (deve retornar 0 registros)
SELECT COUNT(*) as musicas_sem_album
FROM musica 
WHERE id_album NOT IN (SELECT id_album FROM album);

-- Histórico sem usuários ou músicas (deve retornar 0 registros)
SELECT COUNT(*) as historico_inconsistente
FROM historico_reproducao h
WHERE h.id_usuario NOT IN (SELECT id_usuario FROM usuario)
   OR h.id_musica NOT IN (SELECT id_musica FROM musica);

SELECT 'Inserção de dados básicos concluída com sucesso!' as status_final;

-- Resetar configurações
SET ECHO OFF;
SET TIMING OFF;