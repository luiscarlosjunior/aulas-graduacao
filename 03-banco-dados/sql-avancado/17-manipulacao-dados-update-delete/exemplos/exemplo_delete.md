# EXEMPLOS PRÁTICOS: DELETE
**Módulo 17 - Manipulação de Dados**

Este arquivo contém exemplos práticos do comando DELETE usando o banco de dados MusiStream.

⚠️ **AVISO CRÍTICO**: DELETE é uma operação DESTRUTIVA!  
Execute estes comandos APENAS em ambiente de teste!  
**SEMPRE** use `ROLLBACK` para restaurar dados após testes.

---

## SEÇÃO 1: DELETE BÁSICO

### Exemplo 1: Deletar um registro específico
```sql
DELETE FROM usuario
WHERE id_usuario = 10;

SELECT * FROM usuario WHERE id_usuario = 10;
ROLLBACK;
```

### Exemplo 2: Deletar por email
```sql
DELETE FROM usuario
WHERE email = 'sofia@email.com';

SELECT COUNT(*) FROM usuario WHERE email = 'sofia@email.com';
ROLLBACK;
```

### Exemplo 3: Deletar playlist
```sql
DELETE FROM playlist
WHERE id_playlist = 8;

SELECT * FROM playlist WHERE id_playlist = 8;
ROLLBACK;
```

---

## SEÇÃO 2: DELETE COM CONDIÇÕES MÚLTIPLAS

### Exemplo 4: DELETE com AND
```sql
DELETE FROM usuario
WHERE ativo = 'N'
  AND pais = 'Brasil'
  AND data_cadastro < TO_DATE('2024-01-01', 'YYYY-MM-DD');

SELECT * FROM usuario 
WHERE ativo = 'N' AND pais = 'Brasil';
ROLLBACK;
```

### Exemplo 5: DELETE com IN
```sql
DELETE FROM musica
WHERE id_musica IN (26, 27, 28, 29);

SELECT * FROM musica WHERE id_musica IN (26, 27, 28, 29);
ROLLBACK;
```

---

## SEÇÃO 3: DELETE COM SUBCONSULTAS

### Exemplo 8: DELETE com subconsulta simples
```sql
DELETE FROM musica
WHERE id_album IN (
    SELECT a.id_album
    FROM album a
    JOIN artista ar ON a.id_artista = ar.id_artista
    WHERE ar.pais_origem = 'Alemanha'
);

SELECT COUNT(*) as musicas_alemanha
FROM musica m
JOIN album a ON m.id_album = a.id_album
JOIN artista ar ON a.id_artista = ar.id_artista
WHERE ar.pais_origem = 'Alemanha';
ROLLBACK;
```

---

## SEÇÃO 4: DELETE EM MASSA

### Exemplo 12: Limpeza de dados antigos
```sql
DELETE FROM historico_reproducao
WHERE data_reproducao < ADD_MONTHS(SYSDATE, -12);

SELECT COUNT(*) as historico_antigo
FROM historico_reproducao
WHERE data_reproducao < ADD_MONTHS(SYSDATE, -12);
ROLLBACK;
```

---

## SEÇÃO 5: DELETE COM INTEGRIDADE REFERENCIAL

### Exemplo 17: DELETE de usuário e relacionamentos
```sql
DELETE FROM historico_reproducao WHERE id_usuario = 9;
DELETE FROM assinatura WHERE id_usuario = 9;
DELETE FROM playlist_musica 
WHERE id_playlist IN (SELECT id_playlist FROM playlist WHERE id_usuario = 9);
DELETE FROM playlist WHERE id_usuario = 9;
DELETE FROM usuario WHERE id_usuario = 9;

ROLLBACK;
```

---

## SEÇÃO 6: PADRÃO DE DELETE SEGURO

### Passos para um DELETE seguro
1. **Visualizar o que será deletado**:
   ```sql
   SELECT * FROM usuario WHERE id_usuario = 8;
   ```

2. **Contar registros**:
   ```sql
   SELECT COUNT(*) FROM usuario WHERE id_usuario = 8;
   ```

3. **Verificar dependências**:
   ```sql
   SELECT 'playlists' as tabela, COUNT(*) as total
   FROM playlist WHERE id_usuario = 8
   UNION ALL
   SELECT 'assinaturas', COUNT(*)
   FROM assinatura WHERE id_usuario = 8;
   ```

4. **Executar DELETE**:
   ```sql
   DELETE FROM usuario WHERE id_usuario = 8;
   ```

5. **Verificar resultado**:
   ```sql
   SELECT COUNT(*) as registros_restantes FROM usuario WHERE id_usuario = 8;
   ```

6. **Confirmar ou reverter**:
   ```sql
   COMMIT; -- Se correto
   -- ROLLBACK; -- Se errado
   ```

---

## SEÇÃO 7: DELETE COM BACKUP

### Exemplo 18: Criar backup antes de DELETE em massa
```sql
CREATE TABLE musica_backup AS
SELECT *
FROM musica
WHERE id_album IN (1, 2);

DELETE FROM musica
WHERE id_album IN (1, 2);

SELECT COUNT(*) FROM musica WHERE id_album IN (1, 2);
ROLLBACK;
```

---

## SEÇÃO 8: CASOS DE USO PRÁTICOS

### Exemplo 19: Implementar direito ao esquecimento (LGPD)
```sql
DELETE FROM historico_reproducao WHERE id_usuario = 7;
DELETE FROM assinatura WHERE id_usuario = 7;
DELETE FROM playlist_musica 
WHERE id_playlist IN (SELECT id_playlist FROM playlist WHERE id_usuario = 7);
DELETE FROM playlist WHERE id_usuario = 7;
DELETE FROM usuario WHERE id_usuario = 7;

ROLLBACK;
```

---

## SEÇÃO 9: DELETE INCREMENTAL (GRANDES VOLUMES)

### Exemplo 23: Deletar em lotes para evitar locks longos
```sql
DELETE FROM historico_reproducao
WHERE data_reproducao < TO_DATE('2022-01-01', 'YYYY-MM-DD')
  AND ROWNUM <= 1000;

SELECT SQL%ROWCOUNT as linhas_deletadas FROM DUAL;
ROLLBACK;
```

---

## COMPARAÇÃO: DELETE vs TRUNCATE

- **DELETE**: Seletivo, transacional, pode fazer ROLLBACK.
- **TRUNCATE**: Remove tudo, rápido, NÃO pode fazer ROLLBACK (em muitos DBs).

```sql
DELETE FROM historico_reproducao
WHERE data_reproducao < TO_DATE('2023-01-01', 'YYYY-MM-DD');
ROLLBACK;
```

```sql
-- TRUNCATE TABLE historico_reproducao;
-- ROLLBACK; -- NÃO funciona em muitos bancos!
```

---

## REGRAS DE SEGURANÇA

1. **SEMPRE** use `WHERE` no DELETE (exceto se realmente quer tudo).
2. **SEMPRE** teste com `SELECT COUNT` primeiro.
3. **SEMPRE** use transações (`COMMIT` ou `ROLLBACK`).
4. **SEMPRE** verifique dependências antes de deletar.
5. **SEMPRE** faça backup em operações críticas.
6. **NUNCA** execute DELETE sem revisar `WHERE` cuidadosamente.
7. Prefira **soft delete** (`UPDATE ativo='N'`) quando possível.
8. DELETE sem `WHERE` deleta TODOS os registros!

Lembre-se: DELETE é PERMANENTE após `COMMIT`!
