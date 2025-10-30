# Exercícios - Módulo 09: Controle de Transações e Criação de Relatórios

## Exercício 1: Transações Básicas
Pratique comandos COMMIT, ROLLBACK e SAVEPOINT com cenários do MusiStream.

## Exercício 2: Controle de Concorrência
Simule situações de múltiplos usuários acessando dados simultaneamente.

## Exercício 3: Relatórios Formatados
Crie relatórios bem formatados usando configurações de página e colunas.

## Exercício 4: Transações Complexas
Implemente transações que envolvem múltiplas tabelas e validações.

## Exercício 5: Níveis de Isolamento
Teste diferentes níveis de isolamento e seus efeitos.

# ✅ **Exercício 1 — Usuários Ativos e Inativos (com JOINs e HAVING)**

## 🎯 **Objetivo do exercício**

Você irá identificar:

* Usuários que ouviram música
* Usuários que NÃO ouviram nada no período
* Filtrar apenas aqueles que ouviram **mais de 3 músicas diferentes**

O foco é praticar:

* `LEFT JOIN`
* `HAVING` com `COUNT(DISTINCT)`
* `ORDER BY`

## 🧾 **Instruções para o aluno**

Você deve escrever uma consulta que:

1. Traga o nome do usuário e o título das músicas que ele ouviu.
2. Inclua também usuários que **não ouviram nenhuma música**.

   * Dica: isso exige `LEFT JOIN` entre `usuario` e `historico_reproducao`.
3. Agrupe os resultados por usuário.
4. Use `HAVING` para mostrar apenas usuários que ouviram **mais de 3 músicas diferentes**.
5. Ordene por nome do usuário.

> **Dica importante:**
> Não tente resolver de uma vez — primeiro faça funcionar para listar todos, depois adicione o `GROUP BY`, depois o `HAVING`.

## ✅ **Modelo de resposta esperada (não entregue ao aluno imediatamente)**

```sql
SELECT 
    u.nome AS usuario
FROM usuario u
LEFT JOIN historico_reproducao h 
    ON u.id_usuario = h.id_usuario
LEFT JOIN musica m
    ON h.id_musica = m.id_musica
GROUP BY u.nome
HAVING COUNT(DISTINCT m.id_musica) > 3
ORDER BY u.nome;
```

---

# ✅ **Exercício 2 — Músicas Descobertas em Playlists**

## 🎯 **Objetivo do exercício**

Encontrar músicas que o usuário ouviu e que também estavam em uma playlist dele, identificando **se foram descobertas esse ano**.

O foco é praticar:

* `INNER JOIN`
* `RIGHT JOIN` ou `LEFT JOIN`
* Subconsulta simples (`EXISTS`)
* Filtragem por data

## 🧾 **Instruções para o aluno**

Você deve escrever uma consulta que:

1. Mostre usuário, música e a data em que ele ouviu.
2. Traga apenas músicas ouvidas nos **últimos 12 meses**.
3. Garanta que a música também está em uma playlist criada por esse mesmo usuário.

   * Dica: isso é um `INNER JOIN` com `playlist` e `playlist_musica`.
4. Inclua músicas que estão nas playlists **mesmo que não tenham sido ouvidas**.

   * Para isso, você deve usar `RIGHT JOIN` (ou `LEFT`, dependendo da sua abordagem).
5. Adicione uma coluna indicando:

   * `"Descoberta este ano"` se a primeira vez da música for dentro dos últimos 12 meses
   * `"Já conhecida"` caso contrário
6. Ordene pela data mais recente primeiro.

> **Dica importante:**
> Para saber se a música foi descoberta este ano, faça uma subconsulta simples que retorne a primeira vez que o usuário ouviu aquela música.

## ✅ **Modelo de resposta esperada (não entregue ao aluno imediatamente)**

```sql
WITH primeira_vez AS (
    SELECT 
        id_usuario, 
        id_musica, 
        MIN(data_reproducao) AS primeira_reproducao
    FROM historico_reproducao
    GROUP BY id_usuario, id_musica
)
SELECT
    u.nome AS usuario,
    m.titulo AS musica,
    h.data_reproducao,
    CASE 
        WHEN p.primeira_reproducao >= ADD_MONTHS(SYSDATE, -12)
            THEN 'Descoberta este ano'
        ELSE 'Já conhecida'
    END AS status_musica
FROM historico_reproducao h
INNER JOIN usuario u ON u.id_usuario = h.id_usuario
INNER JOIN musica m ON m.id_musica = h.id_musica
INNER JOIN playlist p2 ON p2.id_usuario = u.id_usuario
RIGHT JOIN playlist_musica pm ON pm.id_musica = m.id_musica
INNER JOIN primeira_vez p
    ON p.id_usuario = u.id_usuario AND p.id_musica = m.id_musica
WHERE h.data_reproducao >= ADD_MONTHS(SYSDATE, -12)
ORDER BY h.data_reproducao DESC;
```

---

## 🎓 **Mensagem final ao aluno (para colocar no exercício)**

> **Importante:** Não se preocupe em acertar a consulta de primeira.
> Construa aos poucos:
>
> 1. Junte tabelas
> 2. Filtre por data
> 3. Adicione condição para playlists
> 4. Depois coloque a descoberta do ano
> 5. Por último, organize o resultado
>
> Se travar, volte e recomece passo a passo.
> A prática — e não a pressa — forma um bom analista SQL.
