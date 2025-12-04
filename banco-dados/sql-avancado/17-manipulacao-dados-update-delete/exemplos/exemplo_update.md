# Exemplos Práticos: Comando UPDATE

## Módulo 17 - Manipulação de Dados

Este arquivo contém exemplos práticos do comando `UPDATE` usando o banco de dados **MusiStream**.

> **IMPORTANTE**: Execute estes comandos em ambiente de teste!  
> Sempre use transações e faça `ROLLBACK` para restaurar dados.

---

## Seção 1: UPDATE Básico

### Exemplo 1: Atualizar nome de usuário
```sql
UPDATE usuario
SET nome_usuario = 'Carlos Silva Júnior'
WHERE id_usuario = 1;

SELECT * FROM usuario WHERE id_usuario = 1;
ROLLBACK; -- Desfazer mudança
```

### Exemplo 2: Atualizar múltiplas colunas
```sql
UPDATE usuario
SET nome_usuario = 'João Pedro Silva',
    email = 'joao.pedro@email.com',
    pais = 'Brasil',
    ultimo_acesso = SYSTIMESTAMP
WHERE id_usuario = 3;

SELECT * FROM usuario WHERE id_usuario = 3;
ROLLBACK;
```

### Exemplo 3: Atualizar com NULL
```sql
UPDATE artista
SET website = NULL,
    biografia = 'Biografia será atualizada em breve'
WHERE id_artista = 5;
ROLLBACK;
```

---

## Seção 2: UPDATE com Expressões

### Exemplo 4: Incrementar contador
```sql
UPDATE musica
SET total_reproducoes = total_reproducoes + 1
WHERE id_musica = 1;

SELECT id_musica, titulo, total_reproducoes 
FROM musica 
WHERE id_musica = 1;
ROLLBACK;
```

### Exemplo 5: Aplicar desconto percentual
```sql
UPDATE tipo_assinatura
SET preco_mensal = preco_mensal * 0.90
WHERE nome_plano = 'Premium';

SELECT id_tipo_assinatura, nome_plano, preco_mensal 
FROM tipo_assinatura 
WHERE nome_plano = 'Premium';
ROLLBACK;
```

### Exemplo 6: Calcular com funções
```sql
UPDATE musica
SET duracao = duracao + 10
WHERE id_album = 1 AND duracao < 300;

SELECT id_musica, titulo, duracao 
FROM musica 
WHERE id_album = 1;
ROLLBACK;
```

---

## Seção 3: UPDATE com Subconsultas

### Exemplo 7: Atualizar baseado em outra tabela
```sql
UPDATE musica
SET id_genero = (
    SELECT id_genero 
    FROM album 
    WHERE album.id_album = musica.id_album
)
WHERE id_genero IS NULL;

SELECT id_musica, titulo, id_genero 
FROM musica 
WHERE id_musica IN (1, 2, 3);
ROLLBACK;
```

### Exemplo 8: UPDATE com agregação
```sql
UPDATE playlist
SET total_musicas = (
    SELECT COUNT(*)
    FROM playlist_musica
    WHERE playlist_musica.id_playlist = playlist.id_playlist
);

SELECT id_playlist, nome_playlist, total_musicas 
FROM playlist;
ROLLBACK;
```

### Exemplo 9: UPDATE com EXISTS
```sql
UPDATE artista
SET ativo = 'S'
WHERE EXISTS (
    SELECT 1
    FROM album
    WHERE album.id_artista = artista.id_artista
      AND album.data_lancamento > TO_DATE('2010-01-01', 'YYYY-MM-DD')
);

SELECT id_artista, nome_artista, ativo, pais_origem 
FROM artista;
ROLLBACK;
```

---

## Seção 4: UPDATE com CASE

### Exemplo 11: UPDATE condicional com CASE
```sql
UPDATE assinatura
SET status_assinatura = CASE
    WHEN data_fim IS NULL THEN 'ATIVA'
    WHEN data_fim < SYSDATE THEN 'EXPIRADA'
    WHEN data_fim >= SYSDATE THEN 'ATIVA'
    ELSE 'DESCONHECIDA'
END;

SELECT id_assinatura, status_assinatura, data_inicio, data_fim 
FROM assinatura;
ROLLBACK;
```

### Exemplo 12: Aplicar diferentes ajustes por categoria
```sql
UPDATE tipo_assinatura
SET preco_mensal = CASE
    WHEN nome_plano = 'Premium' THEN preco_mensal * 0.90
    WHEN nome_plano = 'Família' THEN preco_mensal * 0.85
    WHEN nome_plano = 'Estudante' THEN preco_mensal * 0.95
    ELSE preco_mensal
END
WHERE ativo = 'S';

SELECT nome_plano, preco_mensal 
FROM tipo_assinatura 
WHERE ativo = 'S';
ROLLBACK;
```

---

## Seção 5: UPDATE em Massa

### Exemplo 14: Atualizar múltiplos registros
```sql
UPDATE usuario
SET ativo = 'S'
WHERE pais = 'Brasil'
  AND data_cadastro >= TO_DATE('2024-01-01', 'YYYY-MM-DD');

SELECT id_usuario, nome_usuario, ativo, pais 
FROM usuario 
WHERE pais = 'Brasil';
ROLLBACK;
```

### Exemplo 15: Atualização global com filtro
```sql
UPDATE musica
SET explicita = 'N'
WHERE explicita = 'S' OR explicita IS NULL;

SELECT id_musica, titulo, explicita 
FROM musica;
ROLLBACK;
```

---

## Padrão de UPDATE Seguro

1. **Consultar dados atuais**:
   ```sql
   SELECT id_usuario, nome_usuario, email, ativo
   FROM usuario
   WHERE id_usuario = 5;
   ```

2. **Executar UPDATE**:
   ```sql
   UPDATE usuario
   SET nome_usuario = 'Pedro Costa Silva',
       email = 'pedro.costa@email.com',
       ativo = 'S'
   WHERE id_usuario = 5;
   ```

3. **Verificar mudanças**:
   ```sql
   SELECT id_usuario, nome_usuario, email, ativo
   FROM usuario
   WHERE id_usuario = 5;
   ```

4. **Confirmar ou reverter**:
   ```sql
   COMMIT; -- Se estiver correto
   ROLLBACK; -- Se estiver errado
   ```

---

> **Dicas Finais**:
> - Sempre use `WHERE` no `UPDATE`.
> - Teste com `SELECT` antes de executar.
> - Use transações para garantir segurança.
> - Faça backup antes de operações críticas.
