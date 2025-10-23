

-- Total de músicas por gênero (somente gêneros com mais de 10 músicas)

SELECT 
    g.nome_genero,
    COUNT(m.id_musica) AS total_musicas
FROM musica m
JOIN genero g ON m.id_genero = g.id_genero
GROUP BY g.nome_genero
HAVING COUNT(m.id_musica) > 10
ORDER BY total_musicas DESC;


-- Artistas com mais de 3 álbuns lançados

SELECT 
    a.nome_artista,
    COUNT(al.id_album) AS total_albuns
FROM artista a
JOIN album al ON a.id_artista = al.id_artista
GROUP BY a.nome_artista
HAVING COUNT(al.id_album) > 3
ORDER BY total_albuns DESC;


-- Média de duração das músicas por álbum (apenas álbuns com média > 240 segundos)

SELECT 
    al.titulo AS titulo_album,
    ROUND(AVG(m.duracao), 2) AS media_duracao_segundos
FROM album al
JOIN musica m ON al.id_album = m.id_album
GROUP BY al.titulo
HAVING AVG(m.duracao) > 240
ORDER BY media_duracao_segundos DESC;


-- Países com mais de 5 artistas ativos

SELECT 
    a.pais_origem,
    COUNT(a.id_artista) AS total_artistas_ativos
FROM artista a
WHERE a.ativo = 'S'
GROUP BY a.pais_origem
HAVING COUNT(a.id_artista) > 5
ORDER BY total_artistas_ativos DESC;


-- Usuários com mais de 3 playlists criadas

SELECT 
    u.nome_usuario,
    COUNT(p.id_playlist) AS total_playlists
FROM usuario u
JOIN playlist p ON u.id_usuario = p.id_usuario
GROUP BY u.nome_usuario
HAVING COUNT(p.id_playlist) > 3
ORDER BY total_playlists DESC;


-- Planos de assinatura com receita total acima de 1000 reais

SELECT 
    t.nome_plano,
    SUM(a.valor_pago) AS receita_total
FROM tipo_assinatura t
JOIN assinatura a ON t.id_tipo_assinatura = a.id_tipo_assinatura
GROUP BY t.nome_plano
HAVING SUM(a.valor_pago) > 1000
ORDER BY receita_total DESC;


-- Músicas com mais de 500 reproduções em média por usuário

SELECT 
    m.titulo,
    ROUND(AVG(h.duracao_ouvida)) AS media_duracao_ouvida
FROM musica m
JOIN historico_reproducao h ON m.id_musica = h.id_musica
GROUP BY m.titulo
HAVING AVG(h.duracao_ouvida) > 500
ORDER BY media_duracao_ouvida DESC;


-- Usuários com gasto médio por assinatura acima de R$50

SELECT 
    u.nome_usuario,
    ROUND(AVG(a.valor_pago), 2) AS gasto_medio
FROM usuario u
JOIN assinatura a ON u.id_usuario = a.id_usuario
GROUP BY u.nome_usuario
HAVING AVG(a.valor_pago) > 50
ORDER BY gasto_medio DESC;

