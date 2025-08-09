# Exercícios - Módulo 08: Manipulação de Dados - Inserindo Dados (Parte II)

## Exercício 1: Inserção Múltipla e Sequências

### Objetivo
Praticar inserção de múltiplos registros e uso de sequências para geração automática de IDs.

### Tarefa
1. **Crie uma sequência** `seq_artista_novo` que comece em 1000, incremente de 1, com cache de 10

2. **Insira 5 artistas brasileiros** de uma só vez usando INSERT com múltiplos VALUES:
   - Gilberto Gil (MPB, 1 membro)
   - Chico Buarque (MPB, 1 membro)  
   - Skank (Rock, 4 membros)
   - Capital Inicial (Rock, 4 membros)
   - Paralamas do Sucesso (Rock, 3 membros)

3. **Use a sequência** para gerar os IDs automaticamente

### Validação
Verifique se todos os artistas foram inseridos corretamente com IDs sequenciais.

---

## Exercício 2: INSERT... SELECT com Transformação

### Objetivo
Usar INSERT... SELECT para criar relatórios e estatísticas.

### Tarefa
1. **Crie uma tabela** `relatorio_genero`:
   - `id_genero` (INTEGER)
   - `nome_genero` (VARCHAR2(50))
   - `total_artistas` (INTEGER)
   - `total_albums` (INTEGER)
   - `total_musicas` (INTEGER)
   - `media_duracao` (NUMBER(8,2))
   - `data_relatorio` (DATE)

2. **Popule a tabela** usando INSERT... SELECT com estatísticas calculadas de cada gênero

3. **Adicione apenas gêneros** que tenham pelo menos 1 música

### Dicas
- Use JOINs para relacionar as tabelas
- Use funções agregadas (COUNT, AVG)
- Use HAVING para filtrar grupos

---

## Exercício 3: INSERT ALL - Múltiplas Tabelas

### Objetivo
Praticar inserção simultânea em múltiplas tabelas relacionadas.

### Tarefa
1. **Use INSERT ALL** para inserir simultaneamente:
   - Um novo artista: "Foo Fighters" (Estados Unidos, 5 membros)
   - Dois álbuns: "The Colour and the Shape" (1997) e "Wasting Light" (2011)
   - Três músicas para o primeiro álbum: "Everlong" (250s), "Monkey Wrench" (217s), "My Hero" (260s)

2. **Mantenha a integridade referencial** durante a inserção

### Desafio
Use sequências diferentes para cada tabela e certifique-se de que os relacionamentos estejam corretos.

---

## Exercício 4: Inserção Condicional

### Objetivo
Implementar inserções que verificam condições antes de executar.

### Tarefa
1. **Crie inserções condicionais** que só executem se:
   - O gênero "Funk Brasileiro" não existir (se não existir, insira-o)
   - O artista "Tim Maia" não existir (se não existir, insira-o no gênero Funk Brasileiro)
   - A playlist "Clássicos do Funk" não existir para o usuário ID 1

2. **Use subconsultas EXISTS/NOT EXISTS** para as verificações

### Validação
Execute o script duas vezes - na segunda execução, nenhum registro deve ser inserido.

---

## Exercício 5: MERGE (UPSERT)

### Objetivo
Implementar operações que inserem ou atualizam dependendo da existência dos dados.

### Tarefa
1. **Crie uma tabela** `estatistica_usuario`:
   - `id_usuario` (INTEGER, PK)
   - `total_playlists` (INTEGER)
   - `total_musicas_favoritas` (INTEGER)
   - `tempo_total_ouvido` (INTEGER) -- em segundos
   - `ultima_atividade` (DATE)
   - `data_atualizacao` (DATE)

2. **Implemente MERGE** que:
   - Insira novos usuários com suas estatísticas
   - Atualize usuários existentes com novos valores
   - Calcule as estatísticas baseado nas tabelas relacionadas

### Dados de Teste
Execute o MERGE várias vezes com dados diferentes para testar ambos os cenários.

---

## Exercício 6: Inserção em Lote com Tratamento de Erros

### Objetivo
Implementar inserção massiva com controle de erros e logging.

### Tarefa
1. **Crie uma tabela** `temp_novos_usuarios` com dados de teste que incluam:
   - Alguns emails duplicados
   - Algumas datas de nascimento inválidas
   - Alguns campos obrigatórios nulos

2. **Implemente um procedimento PL/SQL** que:
   - Tente inserir todos os usuários da tabela temporária
   - Capture e registre erros em uma tabela de log
   - Continue processando mesmo quando encontrar erros
   - Exiba estatísticas finais (sucessos vs erros)

### Log de Erros
- ID do registro com erro
- Tipo de erro
- Mensagem do erro
- Data/hora do erro

---

## Exercício 7: Geração Automática de Dados

### Objetivo
Criar scripts que geram grandes volumes de dados de teste.

### Tarefa
1. **Gere automaticamente**:
   - 1000 usuários com dados variados (nomes, países, idades)
   - 5000 reproduções aleatórias de músicas
   - 200 playlists com nomes criativos

2. **Use técnicas de randomização**:
   - DBMS_RANDOM para valores aleatórios
   - CONNECT BY LEVEL para gerar sequências
   - Funções de data para períodos variados

3. **Mantenha realismo nos dados**:
   - Distribuição geográfica coerente
   - Datas de reprodução nos últimos 12 meses
   - Durações de reprodução variadas (completas, parciais)

---

## Exercício 8: Importação de Dados Externos

### Objetivo
Simular importação de dados de sistemas externos.

### Cenário
Você recebeu dados de um novo parceiro musical em formato CSV simulado por uma tabela staging.

### Tarefa
1. **Crie tabela staging** `staging_catalog`:
   - `artist_name` (VARCHAR2(100))
   - `album_title` (VARCHAR2(200))
   - `track_title` (VARCHAR2(200))
   - `duration_seconds` (INTEGER)
   - `genre_name` (VARCHAR2(50))
   - `release_year` (INTEGER)
   - `record_label` (VARCHAR2(100))

2. **Popule a staging** com dados de teste

3. **Implemente importação** que:
   - Insira novos gêneros se necessário
   - Insira novos artistas se necessário
   - Insira álbuns e músicas
   - Mantenha integridade referencial
   - Registre estatísticas da importação

---

## Exercício 9: Otimização de Inserção

### Objetivo
Comparar diferentes técnicas de inserção para grandes volumes.

### Tarefa
1. **Meça o tempo** de diferentes abordagens para inserir 10.000 registros:
   - INSERT individual em loop
   - INSERT com múltiplos VALUES
   - INSERT... SELECT
   - BULK INSERT (PL/SQL)

2. **Analise performance**:
   - Tempo de execução
   - Uso de recursos
   - Impacto na performance do banco

3. **Documente conclusões** sobre qual método usar em cada situação

### Medição
Use TIMING ON e registre tempos para cada abordagem.

---

## Exercício 10: Scenario Real - Sistema de Recomendações

### Objetivo
Implementar inserção de dados para um sistema de recomendações musicais.

### Cenário
O MusiStream quer implementar recomendações baseadas em:
- Histórico de reprodução
- Gêneros preferidos
- Artistas similares
- Comportamento de usuários similares

### Tarefa
1. **Crie tabelas necessárias**:
   - `usuario_preferencia_genero`
   - `artista_similaridade`
   - `recomendacao_musica`
   - `perfil_auditivo_usuario`

2. **Implemente algoritmos de inserção** que:
   - Calculem preferências de gênero por usuário
   - Identifiquem usuários com gostos similares
   - Gerem recomendações personalizadas
   - Atualizem recomendações periodicamente

3. **Use técnicas avançadas**:
   - Window functions para rankings
   - Correlações estatísticas
   - Inserções condicionais baseadas em thresholds

---

## Exercício Bônus: Data Quality e Validação

### Objetivo
Implementar validações avançadas durante inserção.

### Tarefa
1. **Crie triggers ou procedures** que validem:
   - Formatos de email
   - Consistência de datas
   - Valores dentro de ranges aceitáveis
   - Integridade semântica (ex: duração vs tamanho arquivo)

2. **Implemente sistema de score** de qualidade dos dados

3. **Gere relatórios** de qualidade para dados inseridos

---

## Soluções

As soluções destes exercícios estão disponíveis no arquivo `solucoes.sql` nesta pasta.

## Critérios de Avaliação

- **Sintaxe Correta** (20%): Comandos SQL válidos e funcionais
- **Uso de Técnicas Avançadas** (25%): Aplicação correta de INSERT avançado
- **Eficiência** (20%): Otimização para performance
- **Tratamento de Erros** (15%): Robustez e controle de exceções
- **Criatividade** (20%): Soluções inovadoras e bem pensadas

## Dicas Importantes

1. **Use COMMIT estrategicamente** - não comite a cada INSERT
2. **Monitore performance** - AUTOTRACE e EXPLAIN PLAN
3. **Valide integridade** - sempre verifique FKs e constraints
4. **Use bind variables** - evite SQL injection e melhore cache
5. **Documente complexidade** - comente lógicas não óbvias
6. **Teste com volumes pequenos** antes de produção
7. **Prepare rollback plans** para operações grandes
8. **Use staging tables** para validar antes de inserir