# Exercícios - Módulo 05: Estrutura das Tabelas, Regras e Relacionamentos

## Exercício 1: Criação de Tabelas com Constraints Básicas

### Objetivo
Criar tabelas do sistema MusiStream com todas as constraints apropriadas.

### Tarefa
Crie as seguintes tabelas com as constraints especificadas:

1. **Tabela `gravadora`**:
   - `id_gravadora` (INTEGER, chave primária)
   - `nome_gravadora` (VARCHAR2(100), não nulo, único)
   - `pais_origem` (VARCHAR2(50), padrão 'Brasil')
   - `ano_fundacao` (INTEGER, entre 1900 e ano atual)
   - `ativa` (CHAR(1), padrão 'S', valores 'S' ou 'N')
   - `website` (VARCHAR2(200))

2. **Tabela `produtor`**:
   - `id_produtor` (INTEGER, chave primária)
   - `nome_produtor` (VARCHAR2(100), não nulo)
   - `especialidade` (VARCHAR2(50))
   - `anos_experiencia` (INTEGER, maior que 0)
   - `email` (VARCHAR2(150), único)
   - `ativo` (CHAR(1), padrão 'S', valores 'S' ou 'N')

### Dicas
- Use nomes descritivos para as constraints
- Lembre-se de definir valores padrão apropriados
- Considere quais campos devem ser únicos

---

## Exercício 2: Relacionamentos e Chaves Estrangeiras

### Objetivo
Implementar relacionamentos entre tabelas usando chaves estrangeiras.

### Tarefa
1. Modifique a tabela `album` para incluir:
   - `id_gravadora` (INTEGER, referência à tabela gravadora)
   - `id_produtor` (INTEGER, referência à tabela produtor)

2. Crie a tabela `musica_artista` para relacionamento N:N entre músicas e artistas (para colaborações):
   - `id_musica` (INTEGER, chave estrangeira)
   - `id_artista` (INTEGER, chave estrangeira)
   - `tipo_participacao` (VARCHAR2(50), valores: 'Principal', 'Featuring', 'Colaboração')
   - `data_gravacao` (DATE)
   - Chave primária composta (id_musica, id_artista)

### Dicas
- Defina as ações apropriadas para ON DELETE
- Considere se alguns relacionamentos são obrigatórios ou opcionais

---

## Exercício 3: Constraints de Verificação Avançadas

### Objetivo
Implementar regras de negócio usando constraints CHECK.

### Tarefa
Crie uma tabela `assinatura` com as seguintes especificações:

- `id_assinatura` (INTEGER, chave primária)
- `tipo_plano` (VARCHAR2(20), valores: 'FREE', 'PREMIUM', 'FAMILY')
- `preco_mensal` (NUMBER(6,2), maior que 0 para planos pagos, 0 para FREE)
- `limite_downloads` (INTEGER, FREE=0, PREMIUM=100, FAMILY=200)
- `qualidade_maxima` (VARCHAR2(10), valores: 'BAIXA', 'MEDIA', 'ALTA')
- `data_inicio` (DATE, não nulo, não pode ser no futuro)
- `data_fim` (DATE, deve ser maior que data_inicio)
- `ativa` (CHAR(1), padrão 'S')
- `id_usuario` (INTEGER, chave estrangeira para usuario)

### Regras Especiais
- Se tipo_plano = 'FREE', então preco_mensal = 0
- Se tipo_plano = 'PREMIUM', então limite_downloads = 100
- Se tipo_plano = 'FAMILY', então limite_downloads = 200

---

## Exercício 4: Criação de Índices

### Objetivo
Otimizar consultas através da criação de índices apropriados.

### Tarefa
Analise as consultas abaixo e crie os índices necessários:

```sql
-- Consulta 1: Busca de músicas por título
SELECT * FROM musica WHERE UPPER(titulo) LIKE '%LOVE%';

-- Consulta 2: Álbuns de um artista em um período
SELECT * FROM album 
WHERE id_artista = 1 AND ano_lancamento BETWEEN 1960 AND 1970;

-- Consulta 3: Usuários por país e cidade
SELECT * FROM usuario WHERE pais = 'Brasil' AND cidade = 'São Paulo';

-- Consulta 4: Playlists públicas ordenadas por data
SELECT * FROM playlist WHERE publica = 'S' ORDER BY data_criacao DESC;
```

Crie os índices mais eficientes para cada consulta.

---

## Exercício 5: Validação de Integridade

### Objetivo
Testar e validar as constraints implementadas.

### Tarefa
Execute os comandos abaixo e explique por que cada um falha:

```sql
-- Teste 1
INSERT INTO artista (id_artista, nome_artista, numero_membros) 
VALUES (100, 'Banda Teste', 0);

-- Teste 2
INSERT INTO album (id_album, titulo, ano_lancamento, id_artista) 
VALUES (100, 'Álbum Futuro', 2030, 1);

-- Teste 3
INSERT INTO usuario (id_usuario, nome_usuario, email, senha, data_nascimento) 
VALUES (100, 'João', 'joao.silva@email.com', 'senha123', DATE '2015-01-01');

-- Teste 4
INSERT INTO musica (id_musica, titulo, duracao, bitrate) 
VALUES (100, 'Música Teste', -30, 999);
```

Para cada erro, proponha uma correção.

---

## Exercício 6: Scenario Real - Sistema de Avaliações

### Objetivo
Implementar um subsistema completo de avaliações de músicas.

### Tarefa
Crie as tabelas necessárias para um sistema onde usuários podem:
- Avaliar músicas (nota de 1 a 5)
- Avaliar álbuns (nota de 1 a 5)
- Comentar sobre músicas
- Reportar conteúdo inadequado

### Requisitos
1. Um usuário pode avaliar uma música apenas uma vez
2. Notas devem estar entre 1 e 5
3. Comentários não podem estar vazios
4. Data de avaliação é obrigatória
5. Reportes devem ter motivo e descrição

### Tabelas Sugeridas
- `avaliacao_musica`
- `avaliacao_album`  
- `comentario_musica`
- `reporte_conteudo`

---

## Exercício 7: Otimização e Performance

### Objetivo
Analisar e otimizar a estrutura das tabelas.

### Tarefa
1. **Análise**: Identifique possíveis problemas de performance nas tabelas criadas
2. **Otimização**: Sugira melhorias na estrutura
3. **Índices**: Crie índices adicionais para consultas frequentes
4. **Particionamento**: Sugira estratégias de particionamento para tabelas grandes

### Cenários para Análise
- Tabela `historico_reproducao` com milhões de registros
- Consultas frequentes por período (última semana, último mês)
- Relatórios de músicas mais tocadas por região
- Buscas por texto em títulos de músicas e artistas

---

## Soluções

As soluções destes exercícios estão disponíveis no arquivo `solucoes.sql` nesta pasta.

## Critérios de Avaliação

- **Sintaxe Correta** (20%): Comandos SQL válidos
- **Constraints Apropriadas** (25%): Uso correto de restrições
- **Relacionamentos** (20%): Implementação correta de FKs
- **Nomenclatura** (15%): Nomes descritivos e consistentes
- **Otimização** (20%): Índices e estruturas eficientes

## Dicas Gerais

1. **Sempre nomeie suas constraints** - facilita manutenção
2. **Use padrões consistentes** - prefixos para tipos de constraint
3. **Documente regras complexas** - comentários no código
4. **Teste todas as constraints** - valide com dados de exemplo
5. **Considere performance** - índices para consultas frequentes