-------------------------
-- TABELA GENERO
-------------------------
INSERT INTO genero (id_genero, nome_genero, descricao) VALUES (1, 'Rock', 'Guitarras elétricas e bateria intensa');
INSERT INTO genero (id_genero, nome_genero, descricao) VALUES (2, 'Pop', 'Músicas populares e cativantes');
INSERT INTO genero (id_genero, nome_genero, descricao) VALUES (3, 'Jazz', 'Improvisação e harmonias complexas');
INSERT INTO genero (id_genero, nome_genero, descricao) VALUES (4, 'Eletrônica', 'Músicas com sintetizadores e batidas eletrônicas');
INSERT INTO genero (id_genero, nome_genero, descricao) VALUES (5, 'Clássica', 'Composições orquestrais tradicionais');

-------------------------
-- TABELA USUARIO
-------------------------
INSERT INTO usuario (id_usuario, nome_usuario, email, senha, data_nascimento, pais) 
VALUES (1, 'Carlos Silva', 'carlos@email.com', '1234', TO_DATE('1990-05-12','YYYY-MM-DD'), 'Brasil');
INSERT INTO usuario (id_usuario, nome_usuario, email, senha, data_nascimento, pais) 
VALUES (2, 'Ana Souza', 'ana@email.com', 'abcd', TO_DATE('1988-09-21','YYYY-MM-DD'), 'Portugal');
INSERT INTO usuario (id_usuario, nome_usuario, email, senha, data_nascimento, pais) 
VALUES (3, 'John Smith', 'john@email.com', 'pass123', TO_DATE('1995-01-15','YYYY-MM-DD'), 'EUA');
INSERT INTO usuario (id_usuario, nome_usuario, email, senha, data_nascimento, pais) 
VALUES (4, 'Maria Oliveira', 'maria@email.com', 'senha@123', TO_DATE('2000-03-10','YYYY-MM-DD'), 'Brasil');
INSERT INTO usuario (id_usuario, nome_usuario, email, senha, data_nascimento, pais) 
VALUES (5, 'Pedro Costa', 'pedro@email.com', 'qwerty', TO_DATE('1992-07-08','YYYY-MM-DD'), 'Espanha');

-------------------------
-- TABELA ARTISTA
-------------------------
INSERT INTO artista (id_artista, nome_artista, nome_real, data_nascimento, pais_origem, data_inicio_carreira, numero_membros) 
VALUES (1, 'The Rockers', NULL, NULL, 'EUA', TO_DATE('2005-01-01','YYYY-MM-DD'), 4);
INSERT INTO artista (id_artista, nome_artista, nome_real, data_nascimento, pais_origem, data_inicio_carreira, numero_membros) 
VALUES (2, 'Ana Pop', 'Ana Pereira', TO_DATE('1994-06-22','YYYY-MM-DD'), 'Brasil', TO_DATE('2012-01-01','YYYY-MM-DD'), 1);
INSERT INTO artista (id_artista, nome_artista, nome_real, data_nascimento, pais_origem, data_inicio_carreira, numero_membros) 
VALUES (3, 'Jazz Band', NULL, NULL, 'França', TO_DATE('1999-01-01','YYYY-MM-DD'), 6);
INSERT INTO artista (id_artista, nome_artista, nome_real, data_nascimento, pais_origem, data_inicio_carreira, numero_membros) 
VALUES (4, 'DJ Electro', 'David Johnson', TO_DATE('1985-11-03','YYYY-MM-DD'), 'EUA', TO_DATE('2008-01-01','YYYY-MM-DD'), 1);
INSERT INTO artista (id_artista, nome_artista, nome_real, data_nascimento, pais_origem, data_inicio_carreira, numero_membros) 
VALUES (5, 'Orquestra Clássica', NULL, NULL, 'Alemanha', TO_DATE('1980-01-01','YYYY-MM-DD'), 20);

-------------------------
-- TABELA ALBUM
-------------------------
INSERT INTO album (id_album, titulo, data_lancamento, ano_lancamento, numero_faixas, duracao_total, id_artista, id_genero) 
VALUES (1, 'Rock Forever', TO_DATE('2010-05-10','YYYY-MM-DD'), 2010, 10, 3600, 1, 1);
INSERT INTO album (id_album, titulo, data_lancamento, ano_lancamento, numero_faixas, duracao_total, id_artista, id_genero) 
VALUES (2, 'Ana Hits', TO_DATE('2015-03-20','YYYY-MM-DD'), 2015, 8, 2400, 2, 2);
INSERT INTO album (id_album, titulo, data_lancamento, ano_lancamento, numero_faixas, duracao_total, id_artista, id_genero) 
VALUES (3, 'Jazz Nights', TO_DATE('2001-09-01','YYYY-MM-DD'), 2001, 12, 4800, 3, 3);
INSERT INTO album (id_album, titulo, data_lancamento, ano_lancamento, numero_faixas, duracao_total, id_artista, id_genero) 
VALUES (4, 'Electro Beats', TO_DATE('2018-06-15','YYYY-MM-DD'), 2018, 15, 5400, 4, 4);
INSERT INTO album (id_album, titulo, data_lancamento, ano_lancamento, numero_faixas, duracao_total, id_artista, id_genero) 
VALUES (5, 'Sinfonia nº1', TO_DATE('1990-01-01','YYYY-MM-DD'), 1990, 5, 7200, 5, 5);

-------------------------
-- TABELA MUSICA
-------------------------
-------------------------
-- MUSICA (5 músicas por álbum)
-------------------------
-- Álbum 1: Rock Forever
INSERT INTO musica VALUES (1, 'Rock Anthem', 300, 1, NULL, NULL, 0, SYSDATE, 'N', 1, 1);
INSERT INTO musica VALUES (2, 'Guitar Hero', 280, 2, NULL, NULL, 0, SYSDATE, 'N', 1, 1);
INSERT INTO musica VALUES (3, 'Drum Solo', 250, 3, NULL, NULL, 0, SYSDATE, 'N', 1, 1);
INSERT INTO musica VALUES (4, 'Wild Energy', 330, 4, NULL, NULL, 0, SYSDATE, 'N', 1, 1);
INSERT INTO musica VALUES (5, 'Final Rock', 340, 5, NULL, NULL, 0, SYSDATE, 'N', 1, 1);

-- Álbum 2: Ana Hits
INSERT INTO musica VALUES (6, 'Love Song', 240, 1, NULL, NULL, 0, SYSDATE, 'N', 2, 2);
INSERT INTO musica VALUES (7, 'Dancing Night', 260, 2, NULL, NULL, 0, SYSDATE, 'N', 2, 2);
INSERT INTO musica VALUES (8, 'Pop Forever', 230, 3, NULL, NULL, 0, SYSDATE, 'N', 2, 2);
INSERT INTO musica VALUES (9, 'Summer Vibes', 250, 4, NULL, NULL, 0, SYSDATE, 'N', 2, 2);
INSERT INTO musica VALUES (10, 'Dreams', 220, 5, NULL, NULL, 0, SYSDATE, 'N', 2, 2);

-- Álbum 3: Jazz Nights
INSERT INTO musica VALUES (11, 'Smooth Jazz', 360, 1, NULL, NULL, 0, SYSDATE, 'N', 3, 3);
INSERT INTO musica VALUES (12, 'Blue Notes', 340, 2, NULL, NULL, 0, SYSDATE, 'N', 3, 3);
INSERT INTO musica VALUES (13, 'Improvisation', 380, 3, NULL, NULL, 0, SYSDATE, 'N', 3, 3);
INSERT INTO musica VALUES (14, 'Jazz Mood', 360, 4, NULL, NULL, 0, SYSDATE, 'N', 3, 3);
INSERT INTO musica VALUES (15, 'Late Night Jazz', 360, 5, NULL, NULL, 0, SYSDATE, 'N', 3, 3);

-- Álbum 4: Electro Beats
INSERT INTO musica VALUES (16, 'Electronic Vibes', 420, 1, NULL, NULL, 0, SYSDATE, 'N', 4, 4);
INSERT INTO musica VALUES (17, 'Bass Drop', 400, 2, NULL, NULL, 0, SYSDATE, 'N', 4, 4);
INSERT INTO musica VALUES (18, 'Synth Wave', 380, 3, NULL, NULL, 0, SYSDATE, 'N', 4, 4);
INSERT INTO musica VALUES (19, 'Electro Night', 400, 4, NULL, NULL, 0, SYSDATE, 'N', 4, 4);
INSERT INTO musica VALUES (20, 'Future Beat', 400, 5, NULL, NULL, 0, SYSDATE, 'N', 4, 4);

-- Álbum 5: Sinfonia nº1
INSERT INTO musica VALUES (21, 'Sinfonia Abertura', 600, 1, NULL, NULL, 0, SYSDATE, 'N', 5, 5);
INSERT INTO musica VALUES (22, 'Movimento I', 720, 2, NULL, NULL, 0, SYSDATE, 'N', 5, 5);
INSERT INTO musica VALUES (23, 'Movimento II', 780, 3, NULL, NULL, 0, SYSDATE, 'N', 5, 5);
INSERT INTO musica VALUES (24, 'Movimento III', 720, 4, NULL, NULL, 0, SYSDATE, 'N', 5, 5);
INSERT INTO musica VALUES (25, 'Finale', 780, 5, NULL, NULL, 0, SYSDATE, 'N', 5, 5);

-------------------------
-- PLAYLIST
-------------------------
INSERT INTO playlist VALUES (1, 'Favoritas Rock', 'Melhores músicas de rock', 'S', SYSDATE, SYSTIMESTAMP, 0, 0, 1);
INSERT INTO playlist VALUES (2, 'Relax Jazz', 'Jazz para relaxar', 'N', SYSDATE, SYSTIMESTAMP, 0, 0, 2);
INSERT INTO playlist VALUES (3, 'Treino Pesado', 'Músicas para academia', 'S', SYSDATE, SYSTIMESTAMP, 0, 0, 3);
INSERT INTO playlist VALUES (4, 'Clássicos Eternos', 'Obras-primas da música clássica', 'N', SYSDATE, SYSTIMESTAMP, 0, 0, 4);
INSERT INTO playlist VALUES (5, 'Party Mix', 'Batidas eletrônicas para festas', 'S', SYSDATE, SYSTIMESTAMP, 0, 0, 5);

-------------------------
-- PLAYLIST_MUSICA
-------------------------
INSERT INTO playlist_musica VALUES (1, 1, 1);
INSERT INTO playlist_musica VALUES (1, 2, 2);
INSERT INTO playlist_musica VALUES (2, 11, 1);
INSERT INTO playlist_musica VALUES (2, 12, 2);
INSERT INTO playlist_musica VALUES (3, 6, 1);
INSERT INTO playlist_musica VALUES (3, 7, 2);
INSERT INTO playlist_musica VALUES (4, 21, 1);
INSERT INTO playlist_musica VALUES (4, 22, 2);
INSERT INTO playlist_musica VALUES (5, 16, 1);
INSERT INTO playlist_musica VALUES (5, 18, 2);

-------------------------
-- TABELA TIPO_ASSINATURA
-------------------------
INSERT INTO tipo_assinatura (id_tipo_assinatura, nome_plano, preco_mensal, qualidade_audio, downloads_offline, pulos_ilimitados, sem_anuncios, descricao) 
VALUES (1, 'Free', 0, 'Média', 'N', 'N', 'N', 'Plano gratuito com anúncios');
INSERT INTO tipo_assinatura (id_tipo_assinatura, nome_plano, preco_mensal, qualidade_audio, downloads_offline, pulos_ilimitados, sem_anuncios, descricao) 
VALUES (2, 'Premium', 29.90, 'Alta', 'S', 'S', 'S', 'Plano premium sem anúncios');
INSERT INTO tipo_assinatura (id_tipo_assinatura, nome_plano, preco_mensal, qualidade_audio, downloads_offline, pulos_ilimitados, sem_anuncios, descricao) 
VALUES (3, 'Família', 49.90, 'Alta', 'S', 'S', 'S', 'Plano familiar para até 6 contas');
INSERT INTO tipo_assinatura (id_tipo_assinatura, nome_plano, preco_mensal, qualidade_audio, downloads_offline, pulos_ilimitados, sem_anuncios, descricao) 
VALUES (4, 'Estudante', 14.90, 'Alta', 'S', 'S', 'S', 'Plano com desconto para estudantes');
INSERT INTO tipo_assinatura (id_tipo_assinatura, nome_plano, preco_mensal, qualidade_audio, downloads_offline, pulos_ilimitados, sem_anuncios, descricao) 
VALUES (5, 'HiFi', 39.90, 'Lossless', 'S', 'S', 'S', 'Plano para audiófilos');

-------------------------
-- TABELA ASSINATURA
-------------------------
INSERT INTO assinatura (id_assinatura, data_inicio, metodo_pagamento, valor_pago, id_usuario, id_tipo_assinatura) 
VALUES (1, TO_DATE('2024-01-01','YYYY-MM-DD'), 'Cartão', 29.90, 1, 2);
INSERT INTO assinatura (id_assinatura, data_inicio, metodo_pagamento, valor_pago, id_usuario, id_tipo_assinatura) 
VALUES (2, TO_DATE('2024-02-10','YYYY-MM-DD'), 'Pix', 14.90, 2, 4);
INSERT INTO assinatura (id_assinatura, data_inicio, metodo_pagamento, valor_pago, id_usuario, id_tipo_assinatura) 
VALUES (3, TO_DATE('2024-03-05','YYYY-MM-DD'), 'Cartão', 0, 3, 1);
INSERT INTO assinatura (id_assinatura, data_inicio, metodo_pagamento, valor_pago, id_usuario, id_tipo_assinatura) 
VALUES (4, TO_DATE('2024-04-15','YYYY-MM-DD'), 'Paypal', 49.90, 4, 3);
INSERT INTO assinatura (id_assinatura, data_inicio, metodo_pagamento, valor_pago, id_usuario, id_tipo_assinatura) 
VALUES (5, TO_DATE('2024-05-01','YYYY-MM-DD'), 'Cartão', 39.90, 5, 5);

-------------------------
-- TABELA HISTORICO_REPRODUCAO
-------------------------
INSERT INTO historico_reproducao (id_historico, duracao_ouvida, dispositivo, localizacao, qualidade_reproduzida, id_usuario, id_musica) 
VALUES (1, 200, 'iPhone', 'São Paulo - Brasil', 'Alta', 1, 1);
INSERT INTO historico_reproducao (id_historico, duracao_ouvida, dispositivo, localizacao, qualidade_reproduzida, id_usuario, id_musica) 
VALUES (2, 150, 'Android', 'Lisboa - Portugal', 'Média', 2, 3);
INSERT INTO historico_reproducao (id_historico, duracao_ouvida, dispositivo, localizacao, qualidade_reproduzida, id_usuario, id_musica) 
VALUES (3, 240, 'PC', 'Nova York - EUA', 'Alta', 3, 2);
INSERT INTO historico_reproducao (id_historico, duracao_ouvida, dispositivo, localizacao, qualidade_reproduzida, id_usuario, id_musica) 
VALUES (4, 600, 'Tablet', 'Madrid - Espanha', 'Alta', 4, 5);
INSERT INTO historico_reproducao (id_historico, duracao_ouvida, dispositivo, localizacao, qualidade_reproduzida, id_usuario, id_musica) 
VALUES (5, 420, 'Smart TV', 'Rio de Janeiro - Brasil', 'Lossless', 5, 4);
