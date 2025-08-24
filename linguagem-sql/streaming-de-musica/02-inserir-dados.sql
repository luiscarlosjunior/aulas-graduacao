-- =====================================================
-- SISTEMA MUSISTREAM - INSERÇÃO DE DADOS DE EXEMPLO
-- =====================================================
-- Script para inserir dados realistas no sistema
-- Demonstra operações CREATE (CRUD)
-- =====================================================

-- Configurações iniciais
SET ECHO ON
SET FEEDBACK ON

PROMPT =====================================================
PROMPT INICIANDO INSERÇÃO DE DADOS NO MUSISTREAM
PROMPT =====================================================

-- =====================================================
-- SEÇÃO 1: DADOS BÁSICOS (GÊNEROS)
-- =====================================================

PROMPT Inserindo gêneros musicais...

INSERT INTO genero (id_genero, nome_genero, descricao) VALUES 
(seq_genero.NEXTVAL, 'Rock', 'Música caracterizada por guitarras elétricas e bateria forte');

INSERT INTO genero (id_genero, nome_genero, descricao) VALUES 
(seq_genero.NEXTVAL, 'Pop', 'Música popular com melodias cativantes e estrutura simples');

INSERT INTO genero (id_genero, nome_genero, descricao) VALUES 
(seq_genero.NEXTVAL, 'Jazz', 'Música com improvisação e harmonias complexas');

INSERT INTO genero (id_genero, nome_genero, descricao) VALUES 
(seq_genero.NEXTVAL, 'Blues', 'Música expressiva com raízes afro-americanas');

INSERT INTO genero (id_genero, nome_genero, descricao) VALUES 
(seq_genero.NEXTVAL, 'Eletrônica', 'Música produzida usando equipamentos eletrônicos');

INSERT INTO genero (id_genero, nome_genero, descricao) VALUES 
(seq_genero.NEXTVAL, 'Hip Hop', 'Música urbana com rap e beats marcantes');

INSERT INTO genero (id_genero, nome_genero, descricao) VALUES 
(seq_genero.NEXTVAL, 'Reggae', 'Música jamaicana com ritmo característico');

INSERT INTO genero (id_genero, nome_genero, descricao) VALUES 
(seq_genero.NEXTVAL, 'Samba', 'Música brasileira com ritmo sincopado');

INSERT INTO genero (id_genero, nome_genero, descricao) VALUES 
(seq_genero.NEXTVAL, 'MPB', 'Música Popular Brasileira com influências diversas');

INSERT INTO genero (id_genero, nome_genero, descricao) VALUES 
(seq_genero.NEXTVAL, 'Clássica', 'Música erudita tradicional');

COMMIT;

-- =====================================================
-- SEÇÃO 2: TIPOS DE ASSINATURA
-- =====================================================

PROMPT Inserindo tipos de assinatura...

INSERT INTO tipo_assinatura (
    id_tipo_assinatura, nome_plano, preco_mensal, qualidade_audio,
    downloads_offline, pulos_ilimitados, sem_anuncios, descricao
) VALUES (
    seq_tipo_assinatura.NEXTVAL, 'Gratuito', 0.00, 'Normal',
    'N', 'N', 'N', 'Plano gratuito com anúncios e limitações'
);

INSERT INTO tipo_assinatura (
    id_tipo_assinatura, nome_plano, preco_mensal, qualidade_audio,
    downloads_offline, pulos_ilimitados, sem_anuncios, descricao
) VALUES (
    seq_tipo_assinatura.NEXTVAL, 'Premium', 16.90, 'Alta',
    'S', 'S', 'S', 'Plano premium completo sem limitações'
);

INSERT INTO tipo_assinatura (
    id_tipo_assinatura, nome_plano, preco_mensal, qualidade_audio,
    downloads_offline, pulos_ilimitados, sem_anuncios, descricao
) VALUES (
    seq_tipo_assinatura.NEXTVAL, 'Família', 26.90, 'Alta',
    'S', 'S', 'S', 'Plano para até 6 membros da família'
);

INSERT INTO tipo_assinatura (
    id_tipo_assinatura, nome_plano, preco_mensal, qualidade_audio,
    downloads_offline, pulos_ilimitados, sem_anuncios, descricao
) VALUES (
    seq_tipo_assinatura.NEXTVAL, 'Estudante', 8.45, 'Alta',
    'S', 'S', 'S', 'Plano com desconto para estudantes'
);

COMMIT;

-- =====================================================
-- SEÇÃO 3: USUÁRIOS
-- =====================================================

PROMPT Inserindo usuários...

INSERT INTO usuario (
    id_usuario, nome_usuario, email, senha, data_nascimento, pais
) VALUES (
    seq_usuario.NEXTVAL, 'João Silva', 'joao.silva@email.com', 'senha123',
    DATE '1990-05-15', 'Brasil'
);

INSERT INTO usuario (
    id_usuario, nome_usuario, email, senha, data_nascimento, pais
) VALUES (
    seq_usuario.NEXTVAL, 'Maria Santos', 'maria.santos@email.com', 'minhasenha',
    DATE '1985-12-03', 'Brasil'
);

INSERT INTO usuario (
    id_usuario, nome_usuario, email, senha, data_nascimento, pais
) VALUES (
    seq_usuario.NEXTVAL, 'Pedro Oliveira', 'pedro.oliveira@email.com', 'pedro456',
    DATE '1995-08-22', 'Brasil'
);

INSERT INTO usuario (
    id_usuario, nome_usuario, email, senha, data_nascimento, pais
) VALUES (
    seq_usuario.NEXTVAL, 'Ana Costa', 'ana.costa@email.com', 'ana789',
    DATE '1988-03-10', 'Portugal'
);

INSERT INTO usuario (
    id_usuario, nome_usuario, email, senha, data_nascimento, pais
) VALUES (
    seq_usuario.NEXTVAL, 'Carlos Mendes', 'carlos.mendes@email.com', 'carlos321',
    DATE '1992-11-07', 'Brasil'
);

INSERT INTO usuario (
    id_usuario, nome_usuario, email, senha, data_nascimento, pais
) VALUES (
    seq_usuario.NEXTVAL, 'Lucia Ferreira', 'lucia.ferreira@email.com', 'lucia654',
    DATE '1987-06-18', 'Brasil'
);

INSERT INTO usuario (
    id_usuario, nome_usuario, email, senha, data_nascimento, pais
) VALUES (
    seq_usuario.NEXTVAL, 'Roberto Lima', 'roberto.lima@email.com', 'roberto987',
    DATE '1993-09-25', 'Argentina'
);

INSERT INTO usuario (
    id_usuario, nome_usuario, email, senha, data_nascimento, pais
) VALUES (
    seq_usuario.NEXTVAL, 'Sofia Rodriguez', 'sofia.rodriguez@email.com', 'sofia456',
    DATE '1991-01-14', 'Espanha'
);

COMMIT;

-- =====================================================
-- SEÇÃO 4: ARTISTAS
-- =====================================================

PROMPT Inserindo artistas...

INSERT INTO artista (
    id_artista, nome_artista, nome_real, data_nascimento, pais_origem,
    biografia, data_inicio_carreira, website
) VALUES (
    seq_artista.NEXTVAL, 'The Beatles', NULL, NULL, 'Reino Unido',
    'Banda de rock britânica formada em Liverpool em 1960.', 
    DATE '1960-01-01', 'www.thebeatles.com'
);

INSERT INTO artista (
    id_artista, nome_artista, nome_real, data_nascimento, pais_origem,
    biografia, data_inicio_carreira, website
) VALUES (
    seq_artista.NEXTVAL, 'Caetano Veloso', 'Caetano Emanuel Viana Teles Veloso',
    DATE '1942-08-07', 'Brasil',
    'Cantor, compositor e escritor brasileiro, um dos principais nomes da MPB.',
    DATE '1965-01-01', 'www.caetanoveloso.com.br'
);

INSERT INTO artista (
    id_artista, nome_artista, nome_real, data_nascimento, pais_origem,
    biografia, data_inicio_carreira
) VALUES (
    seq_artista.NEXTVAL, 'Miles Davis', 'Miles Dewey Davis III',
    DATE '1926-05-26', 'Estados Unidos',
    'Trompetista e compositor americano, pioneiro do jazz moderno.',
    DATE '1944-01-01'
);

INSERT INTO artista (
    id_artista, nome_artista, nome_real, data_nascimento, pais_origem,
    biografia, data_inicio_carreira
) VALUES (
    seq_artista.NEXTVAL, 'Bob Marley', 'Robert Nesta Marley',
    DATE '1945-02-06', 'Jamaica',
    'Cantor e compositor jamaicano, lenda do reggae.',
    DATE '1962-01-01'
);

INSERT INTO artista (
    id_artista, nome_artista, nome_real, data_nascimento, pais_origem,
    biografia, data_inicio_carreira
) VALUES (
    seq_artista.NEXTVAL, 'Daft Punk', NULL, NULL, 'França',
    'Dupla francesa de música eletrônica formada em 1993.',
    DATE '1993-01-01'
);

INSERT INTO artista (
    id_artista, nome_artista, nome_real, data_nascimento, pais_origem,
    biografia, data_inicio_carreira
) VALUES (
    seq_artista.NEXTVAL, 'Eminem', 'Marshall Bruce Mathers III',
    DATE '1972-10-17', 'Estados Unidos',
    'Rapper, produtor e ator americano, um dos mais influentes do hip hop.',
    DATE '1996-01-01'
);

INSERT INTO artista (
    id_artista, nome_artista, nome_real, data_nascimento, pais_origem,
    biografia, data_inicio_carreira
) VALUES (
    seq_artista.NEXTVAL, 'Anitta', 'Larissa de Macedo Machado',
    DATE '1993-03-30', 'Brasil',
    'Cantora, compositora e empresária brasileira.',
    DATE '2010-01-01'
);

INSERT INTO artista (
    id_artista, nome_artista, nome_real, data_nascimento, pais_origem,
    biografia, data_inicio_carreira
) VALUES (
    seq_artista.NEXTVAL, 'Gilberto Gil', 'Gilberto Passos Gil Moreira',
    DATE '1942-06-26', 'Brasil',
    'Cantor, compositor e ex-ministro da Cultura do Brasil.',
    DATE '1967-01-01'
);

COMMIT;

-- =====================================================
-- SEÇÃO 5: ÁLBUNS
-- =====================================================

PROMPT Inserindo álbuns...

-- Álbuns dos Beatles
INSERT INTO album (
    id_album, titulo, data_lancamento, numero_faixas, duracao_total, tipo_album, id_artista
) VALUES (
    seq_album.NEXTVAL, 'Abbey Road', DATE '1969-09-26', 17, 2874, 'ALBUM', 1
);

INSERT INTO album (
    id_album, titulo, data_lancamento, numero_faixas, duracao_total, tipo_album, id_artista
) VALUES (
    seq_album.NEXTVAL, 'Sgt. Peppers Lonely Hearts Club Band', DATE '1967-06-01', 13, 2391, 'ALBUM', 1
);

-- Álbuns Caetano Veloso
INSERT INTO album (
    id_album, titulo, data_lancamento, numero_faixas, duracao_total, tipo_album, id_artista
) VALUES (
    seq_album.NEXTVAL, 'Tropicália', DATE '1968-07-01', 12, 2520, 'ALBUM', 2
);

-- Álbuns Miles Davis
INSERT INTO album (
    id_album, titulo, data_lancamento, numero_faixas, duracao_total, tipo_album, id_artista
) VALUES (
    seq_album.NEXTVAL, 'Kind of Blue', DATE '1959-08-17', 5, 2724, 'ALBUM', 3
);

-- Álbuns Bob Marley
INSERT INTO album (
    id_album, titulo, data_lancamento, numero_faixas, duracao_total, tipo_album, id_artista
) VALUES (
    seq_album.NEXTVAL, 'Legend', DATE '1984-05-08', 14, 4320, 'COMPILACAO', 4
);

-- Álbuns Daft Punk
INSERT INTO album (
    id_album, titulo, data_lancamento, numero_faixas, duracao_total, tipo_album, id_artista
) VALUES (
    seq_album.NEXTVAL, 'Random Access Memories', DATE '2013-05-17', 13, 4469, 'ALBUM', 5
);

-- Álbuns Eminem
INSERT INTO album (
    id_album, titulo, data_lancamento, numero_faixas, duracao_total, tipo_album, id_artista
) VALUES (
    seq_album.NEXTVAL, 'The Marshall Mathers LP', DATE '2000-05-23', 18, 4320, 'ALBUM', 6
);

-- Álbuns Anitta
INSERT INTO album (
    id_album, titulo, data_lancamento, numero_faixas, duracao_total, tipo_album, id_artista
) VALUES (
    seq_album.NEXTVAL, 'Kisses', DATE '2019-04-05', 15, 2700, 'ALBUM', 7
);

COMMIT;

-- =====================================================
-- SEÇÃO 6: MÚSICAS
-- =====================================================

PROMPT Inserindo músicas...

-- Músicas dos Beatles - Abbey Road
INSERT INTO musica (id_musica, titulo, duracao, numero_faixa, id_album, id_genero) VALUES
(seq_musica.NEXTVAL, 'Come Together', 259, 1, 1, 1);

INSERT INTO musica (id_musica, titulo, duracao, numero_faixa, id_album, id_genero) VALUES
(seq_musica.NEXTVAL, 'Something', 182, 2, 1, 1);

INSERT INTO musica (id_musica, titulo, duracao, numero_faixa, id_album, id_genero) VALUES
(seq_musica.NEXTVAL, 'Here Comes the Sun', 185, 7, 1, 1);

-- Músicas dos Beatles - Sgt. Peppers
INSERT INTO musica (id_musica, titulo, duracao, numero_faixa, id_album, id_genero) VALUES
(seq_musica.NEXTVAL, 'Lucy in the Sky with Diamonds', 208, 3, 2, 1);

-- Músicas Caetano Veloso
INSERT INTO musica (id_musica, titulo, duracao, numero_faixa, id_album, id_genero) VALUES
(seq_musica.NEXTVAL, 'Tropicália', 315, 1, 3, 9);

INSERT INTO musica (id_musica, titulo, duracao, numero_faixa, id_album, id_genero) VALUES
(seq_musica.NEXTVAL, 'Alegria, Alegria', 175, 2, 3, 9);

-- Músicas Miles Davis
INSERT INTO musica (id_musica, titulo, duracao, numero_faixa, id_album, id_genero) VALUES
(seq_musica.NEXTVAL, 'So What', 562, 1, 4, 3);

INSERT INTO musica (id_musica, titulo, duracao, numero_faixa, id_album, id_genero) VALUES
(seq_musica.NEXTVAL, 'All Blues', 693, 4, 4, 3);

-- Músicas Bob Marley
INSERT INTO musica (id_musica, titulo, duracao, numero_faixa, id_album, id_genero) VALUES
(seq_musica.NEXTVAL, 'No Woman No Cry', 252, 1, 5, 7);

INSERT INTO musica (id_musica, titulo, duracao, numero_faixa, id_album, id_genero) VALUES
(seq_musica.NEXTVAL, 'Three Little Birds', 180, 6, 5, 7);

INSERT INTO musica (id_musica, titulo, duracao, numero_faixa, id_album, id_genero) VALUES
(seq_musica.NEXTVAL, 'One Love', 171, 9, 5, 7);

-- Músicas Daft Punk
INSERT INTO musica (id_musica, titulo, duracao, numero_faixa, id_album, id_genero) VALUES
(seq_musica.NEXTVAL, 'Get Lucky', 367, 8, 6, 5);

INSERT INTO musica (id_musica, titulo, duracao, numero_faixa, id_album, id_genero) VALUES
(seq_musica.NEXTVAL, 'Instant Crush', 337, 5, 6, 5);

-- Músicas Eminem
INSERT INTO musica (id_musica, titulo, duracao, numero_faixa, id_album, id_genero) VALUES
(seq_musica.NEXTVAL, 'The Real Slim Shady', 284, 7, 7, 6);

INSERT INTO musica (id_musica, titulo, duracao, numero_faixa, id_album, id_genero) VALUES
(seq_musica.NEXTVAL, 'Stan', 404, 3, 7, 6);

-- Músicas Anitta
INSERT INTO musica (id_musica, titulo, duracao, numero_faixa, id_album, id_genero) VALUES
(seq_musica.NEXTVAL, 'Medicina', 192, 1, 8, 2);

INSERT INTO musica (id_musica, titulo, duracao, numero_faixa, id_album, id_genero) VALUES
(seq_musica.NEXTVAL, 'Rosa', 201, 5, 8, 2);

COMMIT;

-- =====================================================
-- SEÇÃO 7: ASSINATURAS DOS USUÁRIOS
-- =====================================================

PROMPT Inserindo assinaturas dos usuários...

INSERT INTO assinatura (
    id_assinatura, data_inicio, status_assinatura, metodo_pagamento,
    valor_pago, renovacao_automatica, id_usuario, id_tipo_assinatura
) VALUES (
    seq_assinatura.NEXTVAL, DATE '2024-01-15', 'ATIVA', 'Cartão de Crédito',
    16.90, 'S', 1, 2
);

INSERT INTO assinatura (
    id_assinatura, data_inicio, status_assinatura, metodo_pagamento,
    valor_pago, renovacao_automatica, id_usuario, id_tipo_assinatura
) VALUES (
    seq_assinatura.NEXTVAL, DATE '2024-02-01', 'ATIVA', 'PIX',
    26.90, 'S', 2, 3
);

INSERT INTO assinatura (
    id_assinatura, data_inicio, status_assinatura, metodo_pagamento,
    valor_pago, renovacao_automatica, id_usuario, id_tipo_assinatura
) VALUES (
    seq_assinatura.NEXTVAL, DATE '2024-01-10', 'ATIVA', 'Cartão de Débito',
    8.45, 'S', 3, 4
);

-- Usuários com plano gratuito
INSERT INTO assinatura (
    id_assinatura, data_inicio, status_assinatura, metodo_pagamento,
    valor_pago, renovacao_automatica, id_usuario, id_tipo_assinatura
) VALUES (
    seq_assinatura.NEXTVAL, DATE '2024-03-01', 'ATIVA', NULL,
    0.00, 'N', 4, 1
);

INSERT INTO assinatura (
    id_assinatura, data_inicio, status_assinatura, metodo_pagamento,
    valor_pago, renovacao_automatica, id_usuario, id_tipo_assinatura
) VALUES (
    seq_assinatura.NEXTVAL, DATE '2024-02-20', 'ATIVA', 'Cartão de Crédito',
    16.90, 'S', 5, 2
);

COMMIT;

-- =====================================================
-- SEÇÃO 8: PLAYLISTS
-- =====================================================

PROMPT Inserindo playlists...

INSERT INTO playlist (
    id_playlist, nome_playlist, descricao, publica, id_usuario
) VALUES (
    seq_playlist.NEXTVAL, 'Meus Clássicos', 
    'Coleção de músicas clássicas atemporais', 'S', 1
);

INSERT INTO playlist (
    id_playlist, nome_playlist, descricao, publica, id_usuario
) VALUES (
    seq_playlist.NEXTVAL, 'Rock Alternativo', 
    'Playlist com rocks alternativos dos anos 90', 'S', 2
);

INSERT INTO playlist (
    id_playlist, nome_playlist, descricao, publica, id_usuario
) VALUES (
    seq_playlist.NEXTVAL, 'MPB Essencial', 
    'O melhor da Música Popular Brasileira', 'S', 3
);

INSERT INTO playlist (
    id_playlist, nome_playlist, descricao, publica, id_usuario
) VALUES (
    seq_playlist.NEXTVAL, 'Jazz Relaxante', 
    'Jazz suave para momentos de relaxamento', 'N', 1
);

INSERT INTO playlist (
    id_playlist, nome_playlist, descricao, publica, id_usuario
) VALUES (
    seq_playlist.NEXTVAL, 'Workout Hits', 
    'Músicas energéticas para treinar', 'S', 4
);

COMMIT;

-- =====================================================
-- SEÇÃO 9: RELACIONAMENTO PLAYLIST-MÚSICA
-- =====================================================

PROMPT Inserindo músicas nas playlists...

-- Playlist "Meus Clássicos"
INSERT INTO playlist_musica (id_playlist, id_musica, ordem_musica) VALUES (1, 1, 1); -- Come Together
INSERT INTO playlist_musica (id_playlist, id_musica, ordem_musica) VALUES (1, 2, 2); -- Something
INSERT INTO playlist_musica (id_playlist, id_musica, ordem_musica) VALUES (1, 3, 3); -- Here Comes the Sun
INSERT INTO playlist_musica (id_playlist, id_musica, ordem_musica) VALUES (1, 9, 4); -- No Woman No Cry

-- Playlist "MPB Essencial"
INSERT INTO playlist_musica (id_playlist, id_musica, ordem_musica) VALUES (3, 5, 1); -- Tropicália
INSERT INTO playlist_musica (id_playlist, id_musica, ordem_musica) VALUES (3, 6, 2); -- Alegria, Alegria

-- Playlist "Jazz Relaxante"
INSERT INTO playlist_musica (id_playlist, id_musica, ordem_musica) VALUES (4, 7, 1); -- So What
INSERT INTO playlist_musica (id_playlist, id_musica, ordem_musica) VALUES (4, 8, 2); -- All Blues

-- Playlist "Workout Hits"
INSERT INTO playlist_musica (id_playlist, id_musica, ordem_musica) VALUES (5, 12, 1); -- Get Lucky
INSERT INTO playlist_musica (id_playlist, id_musica, ordem_musica) VALUES (5, 14, 2); -- The Real Slim Shady
INSERT INTO playlist_musica (id_playlist, id_musica, ordem_musica) VALUES (5, 16, 3); -- Medicina

COMMIT;

-- =====================================================
-- SEÇÃO 10: HISTÓRICO DE REPRODUÇÃO
-- =====================================================

PROMPT Inserindo histórico de reprodução...

-- Simulando reproduções dos últimos dias
INSERT INTO historico_reproducao (
    id_historico, data_reproducao, duracao_ouvida, dispositivo,
    localizacao, origem_reproducao, id_usuario, id_musica
) VALUES (
    seq_historico.NEXTVAL, TIMESTAMP '2024-03-10 14:30:00', 259, 
    'iPhone', 'São Paulo, Brasil', 'playlist', 1, 1
);

INSERT INTO historico_reproducao (
    id_historico, data_reproducao, duracao_ouvida, dispositivo,
    localizacao, origem_reproducao, id_usuario, id_musica
) VALUES (
    seq_historico.NEXTVAL, TIMESTAMP '2024-03-10 14:35:00', 182, 
    'iPhone', 'São Paulo, Brasil', 'playlist', 1, 2
);

INSERT INTO historico_reproducao (
    id_historico, data_reproducao, duracao_ouvida, dispositivo,
    localizacao, origem_reproducao, id_usuario, id_musica
) VALUES (
    seq_historico.NEXTVAL, TIMESTAMP '2024-03-10 15:20:00', 315, 
    'Android', 'Rio de Janeiro, Brasil', 'busca', 2, 5
);

INSERT INTO historico_reproducao (
    id_historico, data_reproducao, duracao_ouvida, dispositivo,
    localizacao, origem_reproducao, id_usuario, id_musica
) VALUES (
    seq_historico.NEXTVAL, TIMESTAMP '2024-03-10 16:45:00', 252, 
    'Web Player', 'Lisboa, Portugal', 'recomendacao', 4, 9
);

INSERT INTO historico_reproducao (
    id_historico, data_reproducao, duracao_ouvida, dispositivo,
    localizacao, origem_reproducao, id_usuario, id_musica
) VALUES (
    seq_historico.NEXTVAL, TIMESTAMP '2024-03-10 18:20:00', 367, 
    'Desktop', 'São Paulo, Brasil', 'playlist', 3, 12
);

-- Mais reproduções para criar estatísticas interessantes
INSERT INTO historico_reproducao (
    id_historico, data_reproducao, duracao_ouvida, dispositivo,
    localizacao, origem_reproducao, id_usuario, id_musica
) VALUES (
    seq_historico.NEXTVAL, TIMESTAMP '2024-03-11 10:15:00', 185, 
    'iPhone', 'São Paulo, Brasil', 'busca', 1, 3
);

INSERT INTO historico_reproducao (
    id_historico, data_reproducao, duracao_ouvida, dispositivo,
    localizacao, origem_reproducao, id_usuario, id_musica
) VALUES (
    seq_historico.NEXTVAL, TIMESTAMP '2024-03-11 11:30:00', 252, 
    'Android', 'Rio de Janeiro, Brasil', 'playlist', 2, 9
);

INSERT INTO historico_reproducao (
    id_historico, data_reproducao, duracao_ouvida, dispositivo,
    localizacao, origem_reproducao, id_usuario, id_musica
) VALUES (
    seq_historico.NEXTVAL, TIMESTAMP '2024-03-11 14:45:00', 192, 
    'Web Player', 'Brasília, Brasil', 'recomendacao', 5, 16
);

-- Usuários ouvindo as mesmas músicas (para análise de similaridade)
INSERT INTO historico_reproducao (
    id_historico, data_reproducao, duracao_ouvida, dispositivo,
    localizacao, origem_reproducao, id_usuario, id_musica
) VALUES (
    seq_historico.NEXTVAL, TIMESTAMP '2024-03-11 20:00:00', 259, 
    'Smart TV', 'São Paulo, Brasil', 'album', 3, 1
);

INSERT INTO historico_reproducao (
    id_historico, data_reproducao, duracao_ouvida, dispositivo,
    localizacao, origem_reproducao, id_usuario, id_musica
) VALUES (
    seq_historico.NEXTVAL, TIMESTAMP '2024-03-11 20:30:00', 367, 
    'iPhone', 'Buenos Aires, Argentina', 'radio', 7, 12
);

COMMIT;

-- =====================================================
-- SEÇÃO 11: VERIFICAÇÃO DOS DADOS INSERIDOS
-- =====================================================

PROMPT =====================================================
PROMPT DADOS INSERIDOS COM SUCESSO!
PROMPT =====================================================

-- Mostra contagem de registros por tabela
SELECT 'Gêneros' AS tabela, COUNT(*) AS total FROM genero
UNION ALL
SELECT 'Tipos de Assinatura', COUNT(*) FROM tipo_assinatura
UNION ALL
SELECT 'Usuários', COUNT(*) FROM usuario
UNION ALL
SELECT 'Artistas', COUNT(*) FROM artista
UNION ALL
SELECT 'Álbuns', COUNT(*) FROM album
UNION ALL
SELECT 'Músicas', COUNT(*) FROM musica
UNION ALL
SELECT 'Playlists', COUNT(*) FROM playlist
UNION ALL
SELECT 'Assinaturas', COUNT(*) FROM assinatura
UNION ALL
SELECT 'Playlist-Música', COUNT(*) FROM playlist_musica
UNION ALL
SELECT 'Histórico Reprodução', COUNT(*) FROM historico_reproducao;

PROMPT =====================================================
PROMPT Próximo passo: Execute 03-consultas-basicas.sql
PROMPT =====================================================