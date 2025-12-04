# Exercícios - Módulo 07: Manipulação de Dados - Inserindo Dados (Parte I)

## ⚠️ Preparação Inicial

**Antes de começar os exercícios, execute o script base:**

```sql
@base-script.sql
```

Este script cria todas as tabelas do sistema MusiStream com a estrutura completa necessária para os exercícios de INSERT. Ele inclui sequences, constraints e todas as colunas necessárias.

**Estrutura das tabelas principais:**
- `artista`: inclui campos como `numero_membros`, `data_inicio_carreira`
- `usuario`: inclui campo obrigatório `senha`  
- `album`: inclui campos como `tipo_album`, `data_lancamento`
- `musica`: inclui campos como `explicita`, `letra`

---

## Exercício 1: Inserção Básica de Artistas

**Cenário**: Você é responsável por alimentar o banco de dados MusiStream com novos artistas.

**Tarefa**: Insira os seguintes artistas na tabela `artista`:

1. **Coldplay**
   - ID: 7
   - País: Reino Unido
   - Formação: 1996-09-04
   - Membros: 4
   - Biografia: "Banda britânica de rock alternativo formada em Londres"

2. **Ed Sheeran**
   - ID: 8
   - País: Reino Unido
   - Formação: 2004-01-01
   - Membros: 1
   - Biografia: "Cantor e compositor britânico de pop folk"

3. **Marília Mendonça**
   - ID: 9
   - País: Brasil
   - Formação: 2014-01-01
   - Membros: 1
   - Biografia: "Cantora brasileira, rainha da sofrência"

**Questões**:
1. Escreva os comandos INSERT para cada artista
2. Quais campos são obrigatórios?
3. Como verificar se a inserção foi bem-sucedida?

---

## Exercício 2: Trabalhando com Valores DEFAULT e NULL

**Cenário**: Novos usuários estão se cadastrando na plataforma com informações incompletas.

**Dados para inserir**:
```
Usuário 6: Maria Fernanda, email: maria.fernanda@email.com, sem data de nascimento
Usuário 7: Pedro Santos, email: pedro.santos@email.com, nascido em 15/08/1999
Usuário 8: Ana Beatriz, email: ana.beatriz@email.com, nascida em 22/03/1987
```

**Questões**:
1. Insira os usuários respeitando campos obrigatórios
2. Como lidar com a data de nascimento ausente do usuário 6?
3. Verifique quais usuários têm `data_cadastro` automático
4. Liste todos os usuários ordenados por data de cadastro

---

## Exercício 3: Integridade Referencial

**Cenário**: Você precisa inserir álbuns para os artistas recém-cadastrados.

**Dados para inserir**:
```
Para Coldplay (ID 7):
- Álbum 10: "Parachutes", lançado em 10/07/2000, 10 faixas, 2579 segundos

Para Ed Sheeran (ID 8):
- Álbum 11: "÷ (Divide)", lançado em 03/03/2017, 16 faixas, 3765 segundos

Para Marília Mendonça (ID 9):
- Álbum 12: "Todos os Cantos", lançado em 15/06/2019, 22 faixas, 4234 segundos
```

**Questões**:
1. Escreva os comandos INSERT para os álbuns
2. O que acontece se você tentar inserir um álbum para artista ID 999?
3. Teste este erro e explique a mensagem retornada
4. Como verificar quais artistas ainda não têm álbuns?

---

## Exercício 4: Inserção Múltipla

**Cenário**: Você recebeu uma lista de músicas do álbum "Parachutes" do Coldplay para inserir.

**Músicas do álbum "Parachutes" (ID 10)**:
```
1. "Don't Panic" - 2:17 (137 segundos) - Faixa 1
2. "Shiver" - 4:59 (299 segundos) - Faixa 2  
3. "Spies" - 5:18 (318 segundos) - Faixa 3
4. "Sparks" - 3:47 (227 segundos) - Faixa 4
5. "Yellow" - 4:29 (269 segundos) - Faixa 5
```

**Questões**:
1. Use um único comando INSERT com múltiplos VALUES
2. Atribua IDs sequenciais começando em 21
3. Todas as músicas são não-explícitas
4. Verifique a inserção listando as músicas por álbum

---

## Exercício 5: Tratamento de Erros

**Cenário**: Teste diferentes tipos de erros de inserção.

**Testes para realizar**:

1. **Teste 1**: Tente inserir um artista com ID duplicado
```sql
-- Seu comando aqui
```

2. **Teste 2**: Tente inserir um usuário sem nome
```sql
-- Seu comando aqui
```

3. **Teste 3**: Tente inserir uma música com duração negativa
```sql
-- Seu comando aqui
```

4. **Teste 4**: Tente inserir um álbum para artista inexistente
```sql
-- Seu comando aqui
```

**Questões**:
1. Execute cada teste e anote a mensagem de erro
2. Explique o tipo de constraint violada em cada caso
3. Como corrigir cada erro?

---

## Exercício 6: Histórico de Reprodução

**Cenário**: Simule atividade de usuários na plataforma.

**Dados para inserir**:
```
- Usuário 1 ouviu "Yellow" (ID 25) hoje às 14:30 no celular Android, completou a música
- Usuário 2 ouviu "Don't Panic" (ID 21) ontem às 20:15 no desktop, ouviu apenas 60 segundos
- Usuário 3 ouviu "Shiver" (ID 22) hoje às 09:45 na web, completou a música
- Usuário 1 ouviu "Sparks" (ID 24) hoje às 15:00 no celular Android, ouviu 180 segundos
```

**Questões**:
1. Insira os registros de histórico (use IDs sequenciais a partir de 6)
2. Use timestamp adequado para "hoje" e "ontem"
3. Como verificar se a duração ouvida não excede a duração da música?
4. Liste o histórico completo com nomes de usuários e músicas

---

## Exercício 7: Validação de Dados

**Cenário**: Antes de inserir dados, você precisa validá-los.

**Dados questionáveis para analisar**:
```
1. Artista: "Banda X", país: "PaísInexistente", 0 membros
2. Usuário: email "emailinvalido", nascido em 2030
3. Álbum: 0 faixas, duração -100 segundos
4. Música: duração 7200 segundos (2 horas)
```

**Questões**:
1. Identifique problemas em cada registro
2. Quais serão rejeitados por constraints?
3. Quais são questionáveis mas tecnicamente válidos?
4. Proponha melhorias para validação

---

## Exercício 8: Inserção com Transações

**Cenário**: Você precisa inserir um artista com seu álbum e músicas em uma operação atômica.

**Dados**:
```
Artista: Imagine Dragons (ID 10)
País: Estados Unidos
Formação: 2008-01-01

Álbum: Night Visions (ID 13)
Data: 04/09/2012
Faixas: 3 (para simplificar)

Músicas:
1. "Radioactive" - 3:06 (186 segundos) - Faixa 1
2. "Demons" - 2:57 (177 segundos) - Faixa 2
3. "It's Time" - 4:00 (240 segundos) - Faixa 3
```

**Questões**:
1. Escreva os comandos INSERT dentro de uma transação
2. Use BEGIN/COMMIT apropriadamente
3. Teste o rollback inserindo dados inválidos no meio
4. Verifique a atomicidade da operação

---

## Exercício 9: Relatório de Inserções

**Cenário**: Crie um relatório das inserções realizadas.

**Questões**:
1. Conte quantos registros existem em cada tabela
2. Liste os 3 artistas inseridos mais recentemente
3. Identifique usuários que se cadastraram hoje
4. Mostre álbuns que ainda não têm músicas
5. Calcule a duração total de música por artista

---

## Exercício 10: Desafio - Sistema de Gêneros

**Cenário**: Expanda o sistema criando uma tabela de gêneros musicais.

**Tarefas**:
1. **Crie a tabela `genero`**:
   - id_genero (PK)
   - nome_genero (único, obrigatório)
   - descricao

2. **Crie a tabela `musica_genero`** (relacionamento N:M):
   - id_musica (FK)
   - id_genero (FK)
   - PK composta

3. **Insira gêneros**:
   - Rock, Pop, Jazz, Blues, Electronic, Hip-Hop

4. **Associe músicas aos gêneros**:
   - Beatles: Rock
   - Coldplay: Rock/Pop
   - Ed Sheeran: Pop

5. **Consulte**:
   - Músicas por gênero
   - Gêneros por artista
   - Gêneros mais populares

---

## Critérios de Avaliação

### Exercícios 1-3: Básico (30 pontos)
- Sintaxe correta dos comandos INSERT
- Compreensão de campos obrigatórios/opcionais
- Tratamento adequado de NULL e DEFAULT

### Exercícios 4-6: Intermediário (40 pontos)
- Inserção múltipla eficiente
- Compreensão de integridade referencial
- Simulação realista de dados

### Exercícios 7-8: Avançado (20 pontos)
- Validação de dados
- Uso de transações
- Tratamento de erros

### Exercício 9-10: Desafio (10 pontos)
- Análise de dados inseridos
- Extensão do modelo de dados
- Consultas complexas

---

## Dicas para Resolução

1. **Execute os exercícios em ordem** - cada um prepara para o próximo
2. **Teste sempre** - execute consultas para verificar inserções
3. **Use transações** - para poder fazer rollback em caso de erro
4. **Documente erros** - anote mensagens para aprender sobre constraints
5. **Seja criativo** - nos exercícios abertos, explore diferentes possibilidades

---

**Tempo Estimado**: 3-4 horas para exercícios completos
**Pré-requisitos**: Módulos 1-6 concluídos, esquema MusiStream criado