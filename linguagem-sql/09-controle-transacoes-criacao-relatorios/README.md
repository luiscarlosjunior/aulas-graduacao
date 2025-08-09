# Módulo 09 - Controle de Transações e Criação de Relatórios

## Objetivos de Aprendizagem

Ao final deste módulo, o aluno será capaz de:
- Compreender e aplicar conceitos de transações em bancos de dados
- Utilizar comandos COMMIT, ROLLBACK e SAVEPOINT
- Implementar controle de concorrência e isolamento
- Criar relatórios básicos usando SELECT
- Aplicar formatação e organização em relatórios
- Trabalhar com níveis de isolamento de transações
- Detectar e resolver problemas de concorrência

## Conteúdo Teórico

### 1. Conceitos de Transações

#### 1.1 Propriedades ACID
- **Atomicidade**: Transação é indivisível (tudo ou nada)
- **Consistência**: Dados ficam em estado válido
- **Isolamento**: Transações não interferem entre si
- **Durabilidade**: Mudanças persistem após COMMIT

#### 1.2 Estados de uma Transação
```sql
-- Transação iniciada implicitamente com primeiro DML
INSERT INTO usuario (id_usuario, nome_usuario, email) 
VALUES (100, 'João', 'joao@email.com');

-- Transação em andamento
UPDATE usuario SET nome_usuario = 'João Silva' WHERE id_usuario = 100;

-- Finalizar com sucesso
COMMIT;
-- OU desfazer
-- ROLLBACK;
```

### 2. Comandos de Controle de Transação

#### 2.1 COMMIT - Confirmar Mudanças
```sql
-- Inserir um novo artista
INSERT INTO artista (id_artista, nome_artista, pais_origem) 
VALUES (200, 'Novos Artistas', 'Brasil');

-- Confirmar a inserção
COMMIT;

-- Agora a mudança está persistente
```

#### 2.2 ROLLBACK - Desfazer Mudanças
```sql
-- Iniciar uma transação
INSERT INTO album (id_album, titulo, id_artista) 
VALUES (300, 'Álbum Teste', 200);

UPDATE artista SET nome_artista = 'Nome Errado' WHERE id_artista = 200;

-- Desfazer todas as mudanças da transação
ROLLBACK;

-- Verificar que mudanças foram desfeitas
SELECT * FROM artista WHERE id_artista = 200;
SELECT * FROM album WHERE id_album = 300;
```

#### 2.3 SAVEPOINT - Pontos de Salvamento
```sql
-- Iniciar transação
INSERT INTO usuario (id_usuario, nome_usuario, email) 
VALUES (101, 'Maria', 'maria@email.com');

-- Criar ponto de salvamento
SAVEPOINT user_inserted;

-- Fazer mais alterações
INSERT INTO playlist (id_playlist, nome_playlist, id_usuario) 
VALUES (201, 'Playlist Test', 101);

-- Criar outro savepoint
SAVEPOINT playlist_inserted;

-- Fazer alteração problemática
UPDATE usuario SET email = NULL WHERE id_usuario = 101; -- Pode violar constraint

-- Voltar ao savepoint anterior
ROLLBACK TO playlist_inserted;

-- Confirmar até aqui
COMMIT;
```

### 3. Autocommit e Controle Manual

#### 3.1 Configuração de Autocommit
```sql
-- Verificar status atual
SHOW AUTOCOMMIT;

-- Desabilitar autocommit para controle manual
SET AUTOCOMMIT OFF;

-- Habilitar autocommit (cada comando faz commit automático)
SET AUTOCOMMIT ON;
```

#### 3.2 Transações Explícitas
```sql
-- Iniciar transação explicitamente (alguns SGBDs)
START TRANSACTION; -- ou BEGIN TRANSACTION;

INSERT INTO genero (id_genero, nome_genero) VALUES (150, 'Test Genre');
UPDATE genero SET descricao = 'Gênero de teste' WHERE id_genero = 150;

-- Finalizar transação
COMMIT;
-- ou ROLLBACK;
```

### 4. Níveis de Isolamento

#### 4.1 Configuração de Isolamento
```sql
-- Verificar nível atual
SELECT * FROM V$TRANSACTION;

-- Definir nível de isolamento para transação
SET TRANSACTION ISOLATION LEVEL READ COMMITTED;
-- Ou outros níveis: READ UNCOMMITTED, REPEATABLE READ, SERIALIZABLE
```

#### 4.2 Problemas de Concorrência

**Dirty Read** - Ler dados não commitados:
```sql
-- Sessão 1
UPDATE artista SET nome_artista = 'Nome Temporário' WHERE id_artista = 1;
-- (não faz commit)

-- Sessão 2 (com READ UNCOMMITTED)
SELECT nome_artista FROM artista WHERE id_artista = 1;
-- Pode ver "Nome Temporário" mesmo sem commit
```

**Non-Repeatable Read** - Leituras diferentes na mesma transação:
```sql
-- Sessão 1
SET TRANSACTION ISOLATION LEVEL READ COMMITTED;
SELECT nome_artista FROM artista WHERE id_artista = 1; -- "The Beatles"

-- Sessão 2
UPDATE artista SET nome_artista = 'Beatles' WHERE id_artista = 1;
COMMIT;

-- Sessão 1 (mesma transação)
SELECT nome_artista FROM artista WHERE id_artista = 1; -- "Beatles" (diferente!)
```

### 5. Locks e Concorrência

#### 5.1 Tipos de Locks
```sql
-- Lock exclusivo (para UPDATE/DELETE)
SELECT * FROM artista WHERE id_artista = 1 FOR UPDATE;

-- Lock compartilhado (para SELECT consistente)
SELECT * FROM artista WHERE id_artista = 1 FOR SHARE;

-- Lock com timeout
SELECT * FROM artista WHERE id_artista = 1 FOR UPDATE WAIT 10;

-- Lock sem espera
SELECT * FROM artista WHERE id_artista = 1 FOR UPDATE NOWAIT;
```

#### 5.2 Detecção de Deadlocks
```sql
-- Monitorar locks ativos
SELECT 
    s.sid,
    s.serial#,
    s.username,
    o.object_name,
    l.mode_held,
    l.mode_requested
FROM v$locked_object l
JOIN dba_objects o ON l.object_id = o.object_id
JOIN v$session s ON l.session_id = s.sid;
```

### 6. Criação de Relatórios Básicos

#### 6.1 Relatório de Artistas por País
```sql
-- Relatório básico formatado
SELECT 
    pais_origem as "País",
    COUNT(*) as "Total Artistas",
    COUNT(CASE WHEN ativo = 'S' THEN 1 END) as "Ativos",
    COUNT(CASE WHEN ativo = 'N' THEN 1 END) as "Inativos"
FROM artista 
WHERE pais_origem IS NOT NULL
GROUP BY pais_origem
ORDER BY COUNT(*) DESC;
```

#### 6.2 Relatório de Albums por Década
```sql
-- Relatório com agrupamento por década
SELECT 
    CASE 
        WHEN ano_lancamento BETWEEN 1960 AND 1969 THEN '1960s'
        WHEN ano_lancamento BETWEEN 1970 AND 1979 THEN '1970s'
        WHEN ano_lancamento BETWEEN 1980 AND 1989 THEN '1980s'
        WHEN ano_lancamento BETWEEN 1990 AND 1999 THEN '1990s'
        WHEN ano_lancamento BETWEEN 2000 AND 2009 THEN '2000s'
        WHEN ano_lancamento BETWEEN 2010 AND 2019 THEN '2010s'
        WHEN ano_lancamento >= 2020 THEN '2020s'
        ELSE 'Outros'
    END as "Década",
    COUNT(*) as "Total Álbuns",
    ROUND(AVG(numero_faixas), 1) as "Média Faixas"
FROM album 
WHERE ano_lancamento IS NOT NULL
GROUP BY 
    CASE 
        WHEN ano_lancamento BETWEEN 1960 AND 1969 THEN '1960s'
        WHEN ano_lancamento BETWEEN 1970 AND 1979 THEN '1970s'
        WHEN ano_lancamento BETWEEN 1980 AND 1989 THEN '1980s'
        WHEN ano_lancamento BETWEEN 1990 AND 1999 THEN '1990s'
        WHEN ano_lancamento BETWEEN 2000 AND 2009 THEN '2000s'
        WHEN ano_lancamento BETWEEN 2010 AND 2019 THEN '2010s'
        WHEN ano_lancamento >= 2020 THEN '2020s'
        ELSE 'Outros'
    END
ORDER BY "Década";
```

#### 6.3 Relatório de Top Músicas por Gênero
```sql
-- Relatório com ranking
SELECT 
    g.nome_genero as "Gênero",
    m.titulo as "Música",
    a.nome_artista as "Artista",
    COUNT(hr.id_historico) as "Reproduções",
    RANK() OVER (PARTITION BY g.nome_genero ORDER BY COUNT(hr.id_historico) DESC) as "Rank"
FROM genero g
JOIN musica m ON g.id_genero = m.id_genero
JOIN album al ON m.id_album = al.id_album
JOIN artista a ON al.id_artista = a.id_artista
LEFT JOIN historico_reproducao hr ON m.id_musica = hr.id_musica
GROUP BY g.nome_genero, m.titulo, a.nome_artista, m.id_musica
HAVING COUNT(hr.id_historico) > 0
ORDER BY g.nome_genero, "Reproduções" DESC;
```

### 7. Formatação de Relatórios

#### 7.1 Configuração de Formato
```sql
-- Configurar formato da página
SET PAGESIZE 50;
SET LINESIZE 120;
SET FEEDBACK OFF;
SET HEADING ON;

-- Definir títulos de colunas
COLUMN nome_artista FORMAT A30 HEADING 'Nome do Artista';
COLUMN total_albums FORMAT 999,999 HEADING 'Total|Álbuns';
COLUMN media_duracao FORMAT 99.99 HEADING 'Duração|Média(min)';
```

#### 7.2 Relatório com Formatação Avançada
```sql
-- Relatório bem formatado
SET PAGESIZE 60;
SET LINESIZE 150;
COLUMN artista FORMAT A25;
COLUMN albums FORMAT 999;
COLUMN musicas FORMAT 999;
COLUMN tempo_total FORMAT A12;
COLUMN status FORMAT A8;

TTITLE CENTER 'RELATÓRIO DE ARTISTAS - MUSISTREAM' SKIP 2;
BTITLE CENTER 'Gerado em: &_DATE';

SELECT 
    a.nome_artista as "Artista",
    COUNT(DISTINCT al.id_album) as "Albums",
    COUNT(m.id_musica) as "Músicas",
    CASE 
        WHEN SUM(m.duracao) IS NULL THEN 'N/A'
        ELSE TO_CHAR(FLOOR(SUM(m.duracao)/3600), '990') || 'h ' ||
             TO_CHAR(MOD(FLOOR(SUM(m.duracao)/60), 60), '00') || 'm'
    END as "Tempo Total",
    CASE WHEN a.ativo = 'S' THEN 'Ativo' ELSE 'Inativo' END as "Status"
FROM artista a
LEFT JOIN album al ON a.id_artista = al.id_artista  
LEFT JOIN musica m ON al.id_album = m.id_album
GROUP BY a.id_artista, a.nome_artista, a.ativo
HAVING COUNT(DISTINCT al.id_album) > 0
ORDER BY COUNT(DISTINCT al.id_album) DESC, a.nome_artista;

CLEAR COLUMNS;
CLEAR BREAKS;
TTITLE OFF;
BTITLE OFF;
```

### 8. Transações em Cenários Práticos

#### 8.1 Transferência de Playlist
```sql
-- Transação para transferir músicas entre playlists
SAVEPOINT inicio_transferencia;

-- Remover músicas da playlist origem
DELETE FROM playlist_musica 
WHERE id_playlist = 10 AND id_musica IN (1, 2, 3);

-- Verificar se remoção foi bem sucedida
IF SQL%ROWCOUNT = 0 THEN
    ROLLBACK TO inicio_transferencia;
    DBMS_OUTPUT.PUT_LINE('Erro: Nenhuma música removida');
ELSE
    -- Adicionar músicas na playlist destino
    INSERT INTO playlist_musica (id_playlist, id_musica, ordem_reproducao)
    SELECT 20, id_musica, ROWNUM + (SELECT MAX(ordem_reproducao) FROM playlist_musica WHERE id_playlist = 20)
    FROM (SELECT 1 as id_musica FROM dual UNION SELECT 2 FROM dual UNION SELECT 3 FROM dual);
    
    -- Confirmar transação
    COMMIT;
    DBMS_OUTPUT.PUT_LINE('Transferência concluída com sucesso');
END IF;
```

#### 8.2 Atualização de Estatísticas Consistente
```sql
-- Atualizar estatísticas de forma atômica
BEGIN
    -- Recalcular total de reproduções por usuário
    UPDATE usuario u
    SET (total_reproducoes, ultima_reproducao) = (
        SELECT COUNT(*), MAX(data_reproducao)
        FROM historico_reproducao hr
        WHERE hr.id_usuario = u.id_usuario
    );
    
    -- Recalcular estatísticas de playlists
    UPDATE playlist p
    SET (numero_musicas, duracao_total) = (
        SELECT COUNT(*), SUM(m.duracao)
        FROM playlist_musica pm
        JOIN musica m ON pm.id_musica = m.id_musica
        WHERE pm.id_playlist = p.id_playlist
    );
    
    -- Verificar consistência
    IF SQL%ROWCOUNT > 0 THEN
        COMMIT;
        DBMS_OUTPUT.PUT_LINE('Estatísticas atualizadas com sucesso');
    ELSE
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Erro na atualização de estatísticas');
    END IF;
END;
/
```

## Exercícios Práticos

Consulte a pasta `exercicios` para atividades práticas que reforçam os conceitos apresentados.

## Referências Bibliográficas

- **Garcia-Molina, H., Ullman, J. D., & Widom, J.** (2013). *Database Systems: The Complete Book*. 2nd Edition. Pearson. Capítulos sobre Transactions.
- **Gray, J. & Reuter, A.** (1992). *Transaction Processing: Concepts and Techniques*. Morgan Kaufmann.
- **Oracle Corporation** (2021). *Oracle Database Concepts*. Capítulo sobre Transaction Management.

## Próximos Passos

No próximo módulo (10), estudaremos **Relatórios com Filtros e Operadores**, explorando claúsulas WHERE avançadas e operadores relacionais e lógicos.