# Exercícios - Módulo 06: Alteração de Estrutura de uma Tabela

## ⚠️ Preparação Inicial

**Antes de começar os exercícios, execute o script base:**

```sql
@base-tables.sql
```

Este script cria todas as tabelas do sistema MusiStream com dados de exemplo, fornecendo a base necessária para praticar os comandos ALTER TABLE dos exercícios abaixo.

---

## Exercício 1: Evolução da Estrutura Básica

### Objetivo
Praticar adição e modificação de colunas em tabelas existentes.

### Cenário
Você foi contratado para melhorar o sistema MusiStream. As tabelas básicas já existem, mas precisam de novos campos.

### Tarefa
1. **Adicione à tabela `artista`**:
   - `biografia_resumida` (VARCHAR2(500))
   - `numero_seguidores` (INTEGER, padrão 0)
   - `verificado` (CHAR(1), padrão 'N', valores 'S' ou 'N')
   - `data_verificacao` (DATE)

2. **Modifique na tabela `usuario`**:
   - Aumente `nome_usuario` para VARCHAR2(150)
   - Adicione constraint NOT NULL ao campo `data_nascimento`
   - Altere o padrão de `ativo` para 'S'

### Dicas
- Lembre-se de que adicionar NOT NULL a uma coluna com dados requer valor padrão
- Use nomes descritivos para constraints

---

## Exercício 2: Sistema de Avaliações

### Objetivo
Implementar um sistema de avaliações adicionando campos às tabelas existentes.

### Tarefa
1. **Adicione à tabela `musica`**:
   - `nota_media` (NUMBER(3,2), entre 0 e 5)
   - `total_avaliacoes` (INTEGER, padrão 0, maior ou igual a 0)
   - `data_ultima_avaliacao` (DATE)

2. **Adicione à tabela `album`**:
   - `nota_critica` (NUMBER(3,2), entre 0 e 10)
   - `fonte_critica` (VARCHAR2(100))
   - `premiado` (CHAR(1), padrão 'N')

3. **Crie constraints apropriadas** para validar as notas

### Validação
Teste inserindo dados que violem as constraints para verificar se estão funcionando.

---

## Exercício 3: Otimização de Performance

### Objetivo
Adicionar campos para otimização e cache de dados frequentemente consultados.

### Tarefa
1. **Adicione à tabela `playlist`**:
   - `total_reproducoes` (INTEGER, padrão 0)
   - `ultima_reproducao` (DATE)
   - `tags` (VARCHAR2(500)) -- para busca textual
   - `trending` (CHAR(1), padrão 'N')

2. **Adicione à tabela `artista`**:
   - `total_reproducoes_mensal` (INTEGER, padrão 0)
   - `ranking_nacional` (INTEGER)
   - `ranking_mundial` (INTEGER)

3. **Crie índices** apropriados para os novos campos

### Desafio Extra
Implemente uma constraint que garanta que `ranking_nacional` e `ranking_mundial` sejam sempre positivos quando não nulos.

---

## Exercício 4: Migração Segura de Dados

### Objetivo
Praticar alteração de tipos de dados usando estratégia segura.

### Cenário
O campo `duracao` na tabela `musica` está em segundos (INTEGER), mas precisa ser alterado para minutos com decimais (NUMBER(6,2)).

### Tarefa
1. **Implemente a estratégia de migração segura**:
   - Adicione nova coluna `duracao_minutos` NUMBER(6,2)
   - Migre os dados (dividindo segundos por 60)
   - Valide a migração
   - Remova coluna antiga
   - Renomeie nova coluna

2. **Documente cada passo** com comentários

### Validação
- Verifique se todos os dados foram migrados corretamente
- Confirme que não houve perda de dados

---

## Exercício 5: Sistema de Auditoria

### Objetivo
Implementar campos de auditoria em todas as tabelas principais.

### Tarefa
1. **Adicione a todas as tabelas principais** (`artista`, `album`, `musica`, `usuario`, `playlist`):
   - `criado_por` (VARCHAR2(100), padrão USER)
   - `criado_em` (DATE, padrão SYSDATE)
   - `modificado_por` (VARCHAR2(100), padrão USER)
   - `modificado_em` (DATE, padrão SYSDATE)
   - `versao` (INTEGER, padrão 1)

2. **Crie constraints** apropriadas para os campos de auditoria

### Automatização
Escreva um script que adicione esses campos a múltiplas tabelas de uma vez.

---

## Exercício 6: Gerenciamento de Constraints

### Objetivo
Praticar adição, remoção e modificação de constraints.

### Tarefa
1. **Adicione constraints nomeadas**:
   - Chave única composta (email + pais) na tabela `usuario`
   - Check constraint para validar formato de email
   - Foreign key entre `musica` e nova tabela `compositor`

2. **Modifique constraints existentes**:
   - Desabilite temporariamente uma foreign key
   - Adicione dados que violem a constraint
   - Tente reabilitar (deve falhar)
   - Corrija os dados e reabilite

3. **Renomeie constraints** para seguir padrão de nomenclatura consistente

---

## Exercício 7: Reestruturação Complexa

### Objetivo
Realizar mudanças complexas na estrutura do banco.

### Cenário
O sistema cresceu e precisa de reestruturação. A tabela `usuario` está muito grande e alguns campos precisam ser movidos para uma tabela separada.

### Tarefa
1. **Crie nova tabela `perfil_usuario`**:
   - `id_usuario` (chave estrangeira)
   - Mova campos relacionados a preferências
   - Estabeleça relacionamento 1:1

2. **Migre dados existentes** da tabela `usuario` para `perfil_usuario`

3. **Remova campos migrados** da tabela original

### Desafio
Mantenha a integridade referencial durante toda a migração.

---

## Exercício 8: Otimização de Schema

### Objetivo
Analisar e otimizar a estrutura das tabelas.

### Tarefa
1. **Analise as tabelas atuais** e identifique:
   - Campos que podem ser removidos
   - Tipos de dados que podem ser otimizados
   - Constraints desnecessárias

2. **Implemente otimizações**:
   - Reduza tamanhos de VARCHAR onde apropriado
   - Converta campos para tipos mais eficientes
   - Remova constraints redundantes

3. **Documente impacto** das mudanças na performance

---

## Exercício 9: Versionamento de Schema

### Objetivo
Implementar um sistema de versionamento para controlar mudanças no banco.

### Tarefa
1. **Crie tabela `schema_version`**:
   - `versao` (VARCHAR2(10), chave primária)
   - `descricao` (VARCHAR2(500))
   - `data_aplicacao` (DATE)
   - `scripts_executados` (CLOB)

2. **Documente todas as alterações** feitas nos exercícios anteriores

3. **Crie script de rollback** para reverter mudanças se necessário

---

## Exercício 10: Cenário Real - Expansão Internacional

### Objetivo
Adaptar o sistema para operação internacional.

### Cenário
O MusiStream vai operar globalmente e precisa suportar múltiplas moedas, idiomas e fusos horários.

### Tarefa
1. **Modifique estruturas para suportar**:
   - Múltiplas moedas para preços
   - Campos de texto em múltiplos idiomas
   - Fusos horários para datas

2. **Adicione tabelas de localização**:
   - `pais`
   - `moeda`
   - `idioma`

3. **Estabeleça relacionamentos** apropriados

### Considerações
- Como manter compatibilidade com dados existentes?
- Quais constraints são necessárias para dados internacionais?

---

## Soluções

As soluções destes exercícios estão disponíveis no arquivo `solucoes.sql` nesta pasta.

## Critérios de Avaliação

- **Sintaxe Correta** (20%): Comandos ALTER TABLE válidos
- **Estratégia de Migração** (25%): Uso de métodos seguros
- **Gerenciamento de Constraints** (20%): Adição/remoção correta
- **Planejamento** (15%): Consideração de impactos
- **Documentação** (20%): Comentários e justificativas

## Dicas Importantes

1. **Sempre faça backup** antes de alterações estruturais
2. **Teste em ambiente de desenvolvimento** primeiro
3. **Considere o impacto em aplicações** que usam o banco
4. **Use transações** para operações complexas
5. **Monitore performance** após mudanças
6. **Documente todas as alterações** para auditoria
7. **Valide dados** após migrações
8. **Planeje rollback** para casos de erro