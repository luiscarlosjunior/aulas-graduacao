# Módulo 07 - Manipulação de Dados - Inserindo Dados (Parte I)

## Objetivos de Aprendizagem

Ao final deste módulo, o aluno será capaz de:
- Utilizar o comando INSERT para adicionar dados
- Compreender diferentes formas de inserção
- Aplicar boas práticas na inserção de dados
- Trabalhar com valores padrão e NULL
- Inserir dados relacionados respeitando integridade referencial

## Conteúdo Teórico

### 1. Comando INSERT Básico

O comando INSERT é a operação fundamental para adicionar novos registros em tabelas de banco de dados. Dominar suas diversas formas e sintaxes é essencial para manipular dados de forma eficiente e segura, garantindo a integridade e consistência das informações armazenadas.

**Por que o INSERT é fundamental?**
- É a única forma de adicionar novos dados em tabelas relacionais
- Permite popular bancos de dados com informações necessárias para operação de sistemas
- Base para operações de carga de dados (ETL) e migrações
- Essencial para manter sistemas funcionais com dados atualizados

**Abordagens de inserção**:
1. **Inserção explícita**: Especificar todas as colunas e valores
2. **Inserção parcial**: Fornecer apenas colunas obrigatórias ou necessárias
3. **Inserção implícita**: Usar valores padrão e omitir colunas opcionais

#### 1.1 Sintaxe Fundamental
```sql
INSERT INTO nome_tabela (coluna1, coluna2, ...)
VALUES (valor1, valor2, ...);
```

#### 1.2 Formas de Inserção

Existem três principais formas de executar um INSERT, cada uma adequada para diferentes cenários e necessidades de desenvolvimento.

**✅ Vantagens de especificar colunas**:
- **Clareza**: Código mais legível e auto-documentado
- **Flexibilidade**: Permite inserir apenas colunas necessárias
- **Resiliência**: Não quebra se a estrutura da tabela mudar (novas colunas)
- **Manutenibilidade**: Facilita entendimento e manutenção futura do código

**❌ Desvantagens de não especificar colunas**:
- **Fragilidade**: Qualquer mudança na estrutura quebra o código
- **Rigidez**: Obriga fornecer valores para todas as colunas na ordem exata
- **Menor legibilidade**: Dificulta entender quais valores correspondem a quais colunas
- **Propenso a erros**: Fácil errar a ordem dos valores

**🎯 Quando especificar colunas**:
- Sempre que possível (melhor prática recomendada)
- Código de produção e sistemas críticos
- Quando apenas algumas colunas precisam ser preenchidas
- Sistemas com estrutura de banco sujeita a mudanças

**⚠️ Quando omitir colunas** (uso muito limitado):
- Scripts de migração temporários com estrutura conhecida e fixa
- Tabelas extremamente simples e estáveis
- Quando performance extrema é crítica (marginal)

**Especificando todas as colunas**:
```sql
INSERT INTO artista (id_artista, nome_artista, biografia, data_inicio_carreira, pais_origem, ativo, numero_membros)
VALUES (1, 'The Beatles', 'Banda britânica de rock formada em Liverpool em 1960', DATE '1960-08-17', 'Reino Unido', 'S', 4);
```

**Especificando apenas colunas necessárias**:
```sql
INSERT INTO artista (id_artista, nome_artista, pais_origem)
VALUES (2, 'Caetano Veloso', 'Brasil');
```

**Sem especificar colunas (todas em ordem)**:
```sql
INSERT INTO artista 
VALUES (3, 'Bob Dylan', 'Dylan', DATE '1941-05-24', 'Estados Unidos', 'Cantor e compositor americano', DATE '1961-01-01', 'S', 'www.bobdylan.com', 1);
```

### 2. Valores Especiais

Ao inserir dados, nem sempre todos os valores são conhecidos ou necessários no momento da inserção. SQL oferece recursos especiais para lidar com ausência de dados (NULL), valores automáticos (DEFAULT) e funções de sistema que geram valores dinâmicos.

**Por que valores especiais são importantes?**
- Representam informações ausentes ou desconhecidas de forma adequada
- Permitem automação de valores comuns (datas, timestamps, IDs)
- Melhoram integridade dos dados ao usar defaults apropriados
- Reduzem código repetitivo e chances de erro

**Abordagens para valores especiais**:
1. **NULL**: Para dados opcionais ou desconhecidos temporariamente
2. **DEFAULT**: Para valores padrão definidos na estrutura da tabela
3. **Funções de sistema**: Para valores calculados ou gerados automaticamente

#### 2.1 Trabalhando com NULL
```sql
-- Inserindo com valores NULL explícitos
INSERT INTO artista (id_artista, nome_artista, biografia, data_inicio_carreira, pais_origem)
VALUES (4, 'Artista Desconhecido', NULL, NULL, 'Brasil');

-- Omitindo colunas que permitem NULL
INSERT INTO artista (id_artista, nome_artista, pais_origem)
VALUES (5, 'Novo Artista', 'Portugal');
```

#### 2.2 Valores Padrão (DEFAULT)
```sql
-- Usando DEFAULT explícito
INSERT INTO usuario (id_usuario, nome_usuario, email, senha, data_nascimento, data_cadastro, ativo)
VALUES (1, 'João Silva', 'joao@email.com', 'senha123', DATE '1990-05-15', DEFAULT, DEFAULT);

-- Omitindo colunas com DEFAULT
INSERT INTO usuario (id_usuario, nome_usuario, email, senha, data_nascimento)
VALUES (2, 'Maria Santos', 'maria@email.com', 'senha456', DATE '1985-12-20');
```

#### 2.3 Funções de Sistema
```sql
-- Usando funções para valores automáticos
INSERT INTO usuario (id_usuario, nome_usuario, email, senha, data_nascimento, data_cadastro)
VALUES (3, 'Pedro Oliveira', 'pedro@email.com', 'senha789', DATE '1992-07-08', CURRENT_TIMESTAMP);

-- Cálculos e expressões
INSERT INTO musica (id_musica, titulo, duracao, numero_faixa, id_album)
VALUES (1, 'Hello', 3*60+7, 1, 1); -- 3 minutos e 7 segundos
```

### 3. Inserção de Dados no Sistema MusiStream

Esta seção demonstra a aplicação prática dos conceitos de inserção em um contexto real: o sistema MusiStream, uma plataforma de streaming de música. Os exemplos seguem uma ordem lógica respeitando as dependências entre tabelas (integridade referencial).

**Contexto do sistema**:
O MusiStream gerencia artistas, álbuns, músicas, usuários e suas interações. A inserção de dados deve seguir a hierarquia de dependências: gêneros → artistas → álbuns → músicas, e usuários → playlists → histórico.

**Ordem de inserção recomendada**:
1. **Entidades independentes**: Gêneros, Artistas e Usuários (não dependem de outras tabelas)
2. **Entidades dependentes de nível 1**: Álbuns (dependem de Artistas)
3. **Entidades dependentes de nível 2**: Músicas (dependem de Álbuns e Gêneros)
4. **Entidades de usuário**: Playlists (dependem de Usuários)
5. **Relacionamentos**: Playlist-Música (relacionamento N:M)
6. **Histórico**: Reproduções (dependem de Usuários e Músicas)

**Propósito dos exemplos**:
- Demonstrar inserção respeitando integridade referencial
- Mostrar dados realistas para facilitar compreensão
- Ilustrar boas práticas em contexto prático
- Servir como base para exercícios e experimentação

#### 3.1 Populando Tabela de Gêneros

Gêneros são entidades independentes e devem ser inseridas primeiro, pois as músicas referenciam os gêneros musicais.

```sql
-- Gêneros musicais principais
INSERT INTO genero (id_genero, nome_genero, descricao)
VALUES 
(1, 'Rock', 'Música caracterizada por guitarras elétricas e bateria forte'),
(2, 'Pop', 'Música popular com melodias cativantes e estrutura simples'),
(3, 'Jazz', 'Música com improvisação e harmonias complexas'),
(4, 'Blues', 'Música expressiva com raízes afro-americanas');

-- Gêneros brasileiros
INSERT INTO genero (id_genero, nome_genero, descricao)
VALUES 
(7, 'Reggae', 'Música jamaicana com ritmo característico'),
(8, 'Samba', 'Música brasileira com ritmo sincopado'),
(9, 'MPB', 'Música Popular Brasileira com influências diversas');

-- Gêneros eletrônicos e urbanos
INSERT INTO genero (id_genero, nome_genero, descricao)
VALUES 
(5, 'Eletrônica', 'Música produzida usando equipamentos eletrônicos'),
(6, 'Hip Hop', 'Música urbana com rap e beats marcantes');
```

**Observações importantes**:
- Nome do gênero deve ser único
- A descrição ajuda a caracterizar o estilo musical
- Os IDs serão referenciados posteriormente pelas músicas
- Gêneros são raramente modificados após criação inicial

#### 3.2 Populando Tabela de Artistas
```sql
-- Artistas Internacionais
INSERT INTO artista (id_artista, nome_artista, biografia, data_inicio_carreira, pais_origem, numero_membros)
VALUES 
(1, 'The Beatles', 'Banda britânica de rock que revolucionou a música popular', DATE '1960-08-17', 'Reino Unido', 4),
(2, 'Queen', 'Banda britânica de rock conhecida por sua teatralidade', DATE '1970-06-27', 'Reino Unido', 4),
(3, 'Bob Dylan', 'Cantor e compositor americano, Nobel de Literatura 2016', DATE '1961-01-01', 'Estados Unidos', 1);

-- Artistas Brasileiros
INSERT INTO artista (id_artista, nome_artista, biografia, data_inicio_carreira, pais_origem, numero_membros)
VALUES 
(4, 'Caetano Veloso', 'Cantor, compositor e escritor brasileiro, ícone da MPB', DATE '1965-01-01', 'Brasil', 1),
(5, 'Legião Urbana', 'Banda brasiliense de rock formada em 1982', DATE '1982-03-01', 'Brasil', 4),
(6, 'Anitta', 'Cantora e compositora brasileira de pop e funk', DATE '2010-01-01', 'Brasil', 1);
```

#### 3.3 Populando Tabela de Usuários
```sql
-- Usuários do sistema (senha obrigatória)
INSERT INTO usuario (id_usuario, nome_usuario, email, senha, data_nascimento)
VALUES 
(1, 'Ana Clara Santos', 'ana.santos@email.com', 'senha123', DATE '1995-03-15'),
(2, 'Carlos Eduardo Silva', 'carlos.silva@email.com', 'minhasenha', DATE '1988-07-22'),
(3, 'Fernanda Costa', 'fernanda.costa@email.com', 'senha456', DATE '1992-11-08'),
(4, 'Roberto Mendes', 'roberto.mendes@email.com', 'password', DATE '1985-05-30'),
(5, 'Julia Rodrigues', 'julia.rodrigues@email.com', 'senha789', DATE '1998-09-12');
```

#### 3.4 Populando Álbuns (Respeitando Integridade Referencial)
```sql
-- Álbuns dos Beatles
INSERT INTO album (id_album, titulo, data_lancamento, numero_faixas, duracao_total, tipo_album, id_artista)
VALUES 
(1, 'Abbey Road', DATE '1969-09-26', 17, 2887, 'ALBUM', 1),
(2, 'Sgt. Pepper''s Lonely Hearts Club Band', DATE '1967-06-01', 13, 2389, 'ALBUM', 1),
(3, 'Let It Be', DATE '1970-05-08', 12, 2155, 'ALBUM', 1);

-- Álbuns do Queen
INSERT INTO album (id_album, titulo, data_lancamento, numero_faixas, duracao_total, tipo_album, id_artista)
VALUES 
(4, 'A Night at the Opera', DATE '1975-11-21', 12, 2583, 'ALBUM', 2),
(5, 'Bohemian Rhapsody', DATE '1975-10-31', 1, 355, 'SINGLE', 2);

-- Álbuns Brasileiros
INSERT INTO album (id_album, titulo, data_lancamento, numero_faixas, duracao_total, tipo_album, id_artista)
VALUES 
(6, 'Tropicália', DATE '1968-07-01', 12, 2234, 'ALBUM', 4),
(7, 'Dois', DATE '1986-01-01', 11, 2876, 'ALBUM', 5),
(8, 'Kisses', DATE '2019-04-05', 15, 2445, 'ALBUM', 6);
```

#### 3.5 Populando Tabela de Músicas

Músicas dependem de álbuns e gêneros, portanto devem ser inseridas após essas entidades. Os exemplos demonstram diferentes gêneros e distribuição de faixas em álbuns.

```sql
-- Músicas dos Beatles - Abbey Road
INSERT INTO musica (id_musica, titulo, duracao, numero_faixa, id_album, id_genero)
VALUES 
(1, 'Come Together', 259, 1, 1, 1),
(2, 'Something', 182, 2, 1, 1),
(3, 'Here Comes the Sun', 185, 7, 1, 1);

-- Músicas dos Beatles - Sgt. Pepper's
INSERT INTO musica (id_musica, titulo, duracao, numero_faixa, id_album, id_genero)
VALUES 
(4, 'Lucy in the Sky with Diamonds', 208, 3, 2, 1);

-- Músicas Caetano Veloso - Tropicália
INSERT INTO musica (id_musica, titulo, duracao, numero_faixa, id_album, id_genero)
VALUES 
(5, 'Tropicália', 315, 1, 6, 9),
(6, 'Alegria, Alegria', 175, 2, 6, 9);

-- Músicas Miles Davis - Kind of Blue
INSERT INTO musica (id_musica, titulo, duracao, numero_faixa, id_album, id_genero)
VALUES 
(7, 'So What', 562, 1, 4, 3),
(8, 'All Blues', 693, 4, 4, 3);

-- Músicas Bob Marley - Legend
INSERT INTO musica (id_musica, titulo, duracao, numero_faixa, id_album, id_genero)
VALUES 
(9, 'No Woman No Cry', 252, 1, 5, 7),
(10, 'Three Little Birds', 180, 6, 5, 7),
(11, 'One Love', 171, 9, 5, 7);
```

**Observações importantes**:
- A duração é especificada em segundos
- O número da faixa deve ser único dentro do álbum
- O id_genero referencia gêneros previamente inseridos (Rock=1, MPB=9, Jazz=3, Reggae=7)
- Respeita a constraint de integridade referencial com álbuns

#### 3.6 Populando Tabela de Playlists

Playlists são criadas por usuários e representam coleções personalizadas de músicas. Devem ser inseridas após a criação de usuários.

```sql
-- Playlists de diferentes usuários
INSERT INTO playlist (id_playlist, nome_playlist, descricao, publica, id_usuario)
VALUES 
(1, 'Meus Clássicos', 'Coleção de músicas clássicas atemporais', 'S', 1),
(2, 'Rock Alternativo', 'Playlist com rocks alternativos dos anos 90', 'S', 2),
(3, 'MPB Essencial', 'O melhor da Música Popular Brasileira', 'S', 3);

-- Playlist privada
INSERT INTO playlist (id_playlist, nome_playlist, descricao, publica, id_usuario)
VALUES 
(4, 'Jazz Relaxante', 'Jazz suave para momentos de relaxamento', 'N', 1);

-- Playlist para exercícios
INSERT INTO playlist (id_playlist, nome_playlist, descricao, publica, id_usuario)
VALUES 
(5, 'Workout Hits', 'Músicas energéticas para treinar', 'S', 4);
```

**Observações importantes**:
- Campo `publica` indica se a playlist é pública ('S') ou privada ('N')
- Um usuário pode ter múltiplas playlists (usuário 1 tem playlists 1 e 4)
- As descrições ajudam outros usuários a entender o tema da playlist
- O id_usuario deve referenciar um usuário existente

#### 3.7 Relacionamento Playlist-Música (N:M)

Esta é uma tabela de relacionamento que implementa a relação muitos-para-muitos entre playlists e músicas. Uma playlist pode ter várias músicas, e uma música pode estar em várias playlists.

```sql
-- Playlist "Meus Clássicos" (id=1)
INSERT INTO playlist_musica (id_playlist, id_musica, ordem_musica)
VALUES 
(1, 1, 1),  -- Come Together
(1, 2, 2),  -- Something
(1, 3, 3),  -- Here Comes the Sun
(1, 9, 4);  -- No Woman No Cry

-- Playlist "MPB Essencial" (id=3)
INSERT INTO playlist_musica (id_playlist, id_musica, ordem_musica)
VALUES 
(3, 5, 1),  -- Tropicália
(3, 6, 2);  -- Alegria, Alegria

-- Playlist "Jazz Relaxante" (id=4)
INSERT INTO playlist_musica (id_playlist, id_musica, ordem_musica)
VALUES 
(4, 7, 1),  -- So What
(4, 8, 2);  -- All Blues
```

**Observações importantes**:
- A `ordem_musica` define a sequência de reprodução dentro da playlist
- A mesma música pode aparecer em diferentes playlists (mas não duplicada na mesma playlist)
- A ordem deve ser única dentro de cada playlist
- Ambos os IDs (playlist e música) devem existir nas respectivas tabelas

#### 3.8 Histórico de Reprodução

Esta tabela registra cada vez que um usuário reproduz uma música, permitindo análises de comportamento e recomendações personalizadas.

```sql
-- Reproduções do usuário 1
INSERT INTO historico_reproducao (id_historico, id_usuario, id_musica, duracao_ouvida, dispositivo, data_reproducao)
VALUES 
(1, 1, 1, 259, 'iPhone', TIMESTAMP '2024-03-10 14:30:00'),
(2, 1, 2, 182, 'iPhone', TIMESTAMP '2024-03-10 14:35:00'),
(3, 1, 3, 185, 'iPhone', TIMESTAMP '2024-03-11 10:15:00');

-- Reproduções de diferentes usuários
INSERT INTO historico_reproducao (id_historico, id_usuario, id_musica, duracao_ouvida, dispositivo, data_reproducao)
VALUES 
(4, 2, 5, 315, 'Android', TIMESTAMP '2024-03-10 15:20:00'),
(5, 3, 9, 252, 'Web Player', TIMESTAMP '2024-03-10 16:45:00'),
(6, 4, 1, 259, 'Desktop', TIMESTAMP '2024-03-11 20:00:00');

-- Música ouvida parcialmente
INSERT INTO historico_reproducao (id_historico, id_usuario, id_musica, duracao_ouvida, dispositivo, data_reproducao)
VALUES 
(7, 2, 9, 120, 'Android', TIMESTAMP '2024-03-11 11:30:00');  -- ouviu apenas 120 de 252 segundos
```

**Observações importantes**:
- `duracao_ouvida` registra quantos segundos foram realmente ouvidos (pode ser menor que a duração total)
- O `dispositivo` ajuda a entender o contexto de uso
- Cada reprodução gera um registro separado, mesmo da mesma música
- Útil para análises de popularidade, recomendações e estatísticas de uso
- O timestamp permite análises temporais (horário de pico, dias mais ativos, etc.)

### 4. Tratamento de Erros Comuns

Compreender os erros mais comuns durante inserção de dados é fundamental para desenvolver código robusto e confiável. Cada tipo de erro tem causas específicas e estratégias de prevenção.

**Por que tratar erros é crítico?**
- Evita perda de dados e inconsistências no banco
- Melhora experiência do usuário com mensagens claras
- Facilita debugging e manutenção de sistemas
- Garante integridade e confiabilidade dos dados

**Principais categorias de erros**:
1. **Violações de unicidade**: Chaves primárias ou índices únicos duplicados
2. **Violações de obrigatoriedade**: Campos NOT NULL sem valor
3. **Violações de regras de negócio**: Constraints CHECK não satisfeitas
4. **Violações de relacionamento**: Chaves estrangeiras inválidas

**Estratégias de prevenção**:
- Validar dados antes de inserir (na aplicação)
- Consultar dados existentes para evitar duplicatas
- Usar transações para operações relacionadas
- Implementar tratamento de exceções adequado

#### 4.1 Violação de Chave Primária
```sql
-- ❌ ERRO: Tentativa de inserir ID duplicado
INSERT INTO artista (id_artista, nome_artista, pais_origem)
VALUES (1, 'Artista Duplicado', 'Brasil');
-- Resultado: ERROR: duplicate key value violates unique constraint

-- ✅ CORRETO: Usar ID único
INSERT INTO artista (id_artista, nome_artista, pais_origem)
VALUES (7, 'Novo Artista', 'Brasil');
```

#### 4.2 Violação de Constraint NOT NULL
```sql
-- ❌ ERRO: Campo obrigatório não fornecido
INSERT INTO artista (id_artista, biografia)
VALUES (8, 'Alguma biografia');
-- Resultado: ERROR: null value in column "nome_artista" violates not-null constraint

-- ✅ CORRETO: Fornecer campos obrigatórios
INSERT INTO artista (id_artista, nome_artista, biografia)
VALUES (8, 'Nome do Artista', 'Alguma biografia');
```

#### 4.3 Violação de Constraint CHECK
```sql
-- ❌ ERRO: Violação de regra de negócio
INSERT INTO artista (id_artista, nome_artista, numero_membros)
VALUES (9, 'Banda Grande', 25);
-- Resultado: ERROR: new row violates check constraint "ck_numero_membros"

-- ✅ CORRETO: Respeitar regras de negócio
INSERT INTO artista (id_artista, nome_artista, numero_membros)
VALUES (9, 'Banda Normal', 5);
```

#### 4.4 Violação de Integridade Referencial
```sql
-- ❌ ERRO: Referência a artista inexistente
INSERT INTO album (id_album, titulo, id_artista)
VALUES (10, 'Álbum Órfão', 999);
-- Resultado: ERROR: insert or update violates foreign key constraint

-- ✅ CORRETO: Referenciar artista existente
INSERT INTO album (id_album, titulo, id_artista)
VALUES (10, 'Novo Álbum', 1);
```

### 5. Inserção Múltipla

A inserção múltipla permite adicionar vários registros em uma única operação, otimizando significativamente a performance e reduzindo a complexidade do código.

**Por que usar inserção múltipla?**
- Reduz drasticamente o tempo de execução para múltiplos registros
- Diminui overhead de comunicação com o banco de dados
- Simplifica transações e controle de consistência
- Melhora eficiência de logs e rollback

#### 5.1 Múltiplos VALUES
```sql
-- Inserir várias músicas de uma vez
INSERT INTO musica (id_musica, titulo, duracao, numero_faixa, id_album)
VALUES 
(1, 'Come Together', 259, 1, 1),
(2, 'Something', 182, 2, 1),
(3, 'Maxwell''s Silver Hammer', 207, 3, 1),
(4, 'Oh! Darling', 206, 4, 1),
(5, 'Octopus''s Garden', 171, 5, 1);
```

#### 5.2 Vantagens da Inserção Múltipla
- Melhor performance
- Menos comandos de rede
- Transações mais eficientes
- Rollback unificado

**✅ Vantagens detalhadas**:
- **Performance superior**: 70-90% mais rápido que inserções individuais sequenciais
- **Menor overhead de rede**: Uma única comunicação com o banco para múltiplos registros
- **Atomicidade garantida**: Todos os registros são inseridos ou nenhum é inserido
- **Eficiência de logs**: Reduz quantidade de entradas no log de transações
- **Simplificação de código**: Menos linhas de código, mais legibilidade

**❌ Desvantagens**:
- **Limitação de tamanho**: SGBDs têm limites de tamanho para comandos SQL
- **Controle de erros**: Se um registro falha, toda a operação pode falhar
- **Consumo de memória**: Comandos grandes consomem mais memória
- **Debugging complexo**: Mais difícil identificar qual registro causou erro específico

**🎯 Quando usar**:
- Carregamento inicial de dados (dados de configuração, catálogos)
- Inserção de lotes pequenos a médios (até 1000 registros)
- Dados com estrutura idêntica e validados previamente
- Scripts de migração e sincronização de dados
- Importação de dados de arquivos CSV ou APIs

**⚠️ Quando NÃO usar**:
- Lotes muito grandes (>5000 registros) - considerar INSERT... SELECT
- Quando necessita tratamento individual de erros por registro
- Dados que requerem validação complexa para cada item
- Sistemas com limitações rígidas de memória
- Operações que precisam de feedback incremental ao usuário

### 6. Boas Práticas

Seguir boas práticas na inserção de dados é essencial para criar sistemas robustos, manuteníveis e eficientes. Estas práticas previnem problemas comuns e facilitam evolução do código.

**Por que boas práticas são importantes?**
- Previnem bugs difíceis de detectar e corrigir
- Melhoram performance e escalabilidade do sistema
- Facilitam trabalho em equipe e manutenção futura
- Garantem consistência e integridade dos dados
- Reduzem tempo gasto em debugging e correções

**Princípios fundamentais**:
1. **Clareza sobre brevidade**: Código explícito é melhor que implícito
2. **Validação preventiva**: Prevenir erros é melhor que tratá-los
3. **Consistência transacional**: Operações relacionadas devem ser atômicas
4. **Documentação implícita**: Código deve ser auto-explicativo

#### 6.1 Sempre Especificar Colunas
```sql
-- ✅ BOM: Especificar colunas
INSERT INTO artista (id_artista, nome_artista, pais_origem)
VALUES (10, 'Nome Artista', 'País');

-- ❌ EVITAR: Sem especificar colunas
INSERT INTO artista 
VALUES (10, 'Nome Artista', NULL, NULL, 'País', TRUE, 1);
```

#### 6.2 Validar Dados Antes da Inserção
```sql
-- Verificar se artista existe antes de inserir álbum
SELECT id_artista FROM artista WHERE id_artista = 5;

-- Se existe, inserir álbum
INSERT INTO album (id_album, titulo, id_artista)
VALUES (11, 'Novo Álbum da Legião', 5);
```

#### 6.3 Usar Transações para Inserções Relacionadas
```sql
-- Iniciar transação
BEGIN;

-- Inserir artista
INSERT INTO artista (id_artista, nome_artista, pais_origem)
VALUES (11, 'Novo Artista', 'Brasil');

-- Inserir álbum do artista
INSERT INTO album (id_album, titulo, id_artista)
VALUES (12, 'Primeiro Álbum', 11);

-- Confirmar transação
COMMIT;
```

### 7. Exercícios Práticos

Consulte a pasta `exercicios/` para atividades práticas de inserção de dados.

### 8. Controle de Transações com INSERT

O controle de transações é fundamental quando trabalhamos com inserções de dados, especialmente quando múltiplas operações relacionadas precisam ser tratadas como uma unidade atômica. Os comandos COMMIT e ROLLBACK permitem confirmar ou desfazer um conjunto de operações, garantindo a integridade e consistência dos dados.

**Por que usar transações com INSERT?**
- Garantir que operações relacionadas sejam completadas juntas (atomicidade)
- Permitir reverter mudanças em caso de erro ou inconsistência
- Manter a integridade referencial entre tabelas relacionadas
- Facilitar testes e experimentação sem comprometer dados reais
- Proteger contra falhas parciais que deixariam o banco em estado inconsistente

**Conceitos fundamentais**:
1. **BEGIN/START TRANSACTION**: Inicia uma transação explícita
2. **COMMIT**: Confirma e torna permanentes todas as mudanças da transação
3. **ROLLBACK**: Desfaz todas as mudanças desde o início da transação
4. **Atomicidade**: Todas as operações são executadas ou nenhuma é

#### 8.1 Usando COMMIT - Confirmando Inserções

O comando COMMIT é usado para confirmar e tornar permanentes todas as mudanças realizadas dentro de uma transação. Após o COMMIT, as alterações não podem mais ser desfeitas com ROLLBACK.

**Quando usar COMMIT**:
- Após verificar que todas as inserções relacionadas foram bem-sucedidas
- Quando a integridade dos dados foi validada
- Ao finalizar um conjunto lógico de operações
- Para liberar locks e tornar dados visíveis para outras transações

**Exemplo 1: Inserção de Artista e Álbum**
```sql
-- Iniciar transação explicitamente
BEGIN;

-- Inserir novo artista
INSERT INTO artista (id_artista, nome_artista, pais_origem, numero_membros)
VALUES (20, 'Pink Floyd', 'Reino Unido', 4);

-- Inserir álbum do artista recém-criado
INSERT INTO album (id_album, titulo, data_lancamento, id_artista)
VALUES (30, 'The Dark Side of the Moon', DATE '1973-03-01', 20);

-- Inserir músicas do álbum
INSERT INTO musica (id_musica, titulo, duracao, numero_faixa, id_album, id_genero)
VALUES 
(50, 'Time', 413, 4, 30, 1),
(51, 'Money', 382, 6, 30, 1);

-- Verificar se as inserções estão corretas
SELECT * FROM artista WHERE id_artista = 20;
SELECT * FROM album WHERE id_album = 30;
SELECT * FROM musica WHERE id_album = 30;

-- Confirmar todas as inserções
COMMIT;
```

**Exemplo 2: Criação de Playlist com Músicas**
```sql
-- Iniciar transação
BEGIN;

-- Criar nova playlist para usuário
INSERT INTO playlist (id_playlist, nome_playlist, descricao, publica, id_usuario)
VALUES (10, 'Rock Progressivo', 'Clássicos do rock progressivo dos anos 70', 'S', 1);

-- Adicionar músicas à playlist
INSERT INTO playlist_musica (id_playlist, id_musica, ordem_musica)
VALUES 
(10, 50, 1),  -- Time
(10, 51, 2),  -- Money
(10, 1, 3),   -- Come Together
(10, 2, 4);   -- Something

-- Tudo correto, confirmar transação
COMMIT;

-- Agora a playlist está disponível para o usuário
```

**Exemplo 3: Registro de Múltiplas Reproduções**
```sql
-- Iniciar transação para registro de sessão de escuta
BEGIN;

-- Registrar sequência de reproduções do usuário
INSERT INTO historico_reproducao (id_historico, id_usuario, id_musica, duracao_ouvida, dispositivo, data_reproducao)
VALUES 
(20, 2, 50, 413, 'Web Player', TIMESTAMP '2024-03-15 20:00:00'),
(21, 2, 51, 382, 'Web Player', TIMESTAMP '2024-03-15 20:07:00'),
(22, 2, 1, 259, 'Web Player', TIMESTAMP '2024-03-15 20:13:00');

-- Confirmar histórico de reprodução
COMMIT;
```

#### 8.2 Usando ROLLBACK - Revertendo Inserções

O comando ROLLBACK é usado para desfazer todas as mudanças realizadas desde o início da transação. É especialmente útil quando detectamos erros, inconsistências ou quando queremos cancelar uma operação.

**Quando usar ROLLBACK**:
- Quando detectar erro em uma das inserções da transação
- Se validações de negócio falharem após as inserções
- Durante testes e experimentação com dados
- Ao encontrar violações de integridade referencial
- Quando o usuário cancela uma operação composta

**Exemplo 1: Erro de Integridade Referencial**
```sql
-- Iniciar transação
BEGIN;

-- Inserir álbum para artista inexistente (vai falhar)
INSERT INTO album (id_album, titulo, id_artista)
VALUES (40, 'Álbum Órfão', 999);  -- Artista 999 não existe!

-- O erro será detectado, então reverter
ROLLBACK;

-- Verificar que nada foi inserido
SELECT * FROM album WHERE id_album = 40;  -- Retorna vazio
```

**Exemplo 2: Validação de Negócio Falha**
```sql
-- Iniciar transação para criar artista e álbum
BEGIN;

-- Inserir artista
INSERT INTO artista (id_artista, nome_artista, pais_origem)
VALUES (25, 'Artista Teste', 'Brasil');

-- Inserir álbum
INSERT INTO album (id_album, titulo, id_artista, numero_faixas)
VALUES (45, 'Álbum Teste', 25, 0);  -- Zero faixas não faz sentido!

-- Detectar problema após inserção
-- Validação: álbum deve ter pelo menos 1 faixa
-- Como a validação falhou, reverter tudo
ROLLBACK;

-- Nenhum registro foi salvo
SELECT * FROM artista WHERE id_artista = 25;  -- Retorna vazio
SELECT * FROM album WHERE id_album = 45;      -- Retorna vazio
```

**Exemplo 3: Teste e Experimentação**
```sql
-- Testar inserções sem comprometer banco de dados
BEGIN;

-- Experimentar inserir dados de teste
INSERT INTO usuario (id_usuario, nome_usuario, email, senha)
VALUES (100, 'Usuário Teste', 'teste@email.com', 'senha123');

INSERT INTO playlist (id_playlist, nome_playlist, id_usuario)
VALUES (100, 'Playlist Teste', 100);

-- Visualizar como ficaria
SELECT * FROM usuario WHERE id_usuario = 100;
SELECT * FROM playlist WHERE id_playlist = 100;

-- Decidir não manter os dados de teste
ROLLBACK;

-- Os dados de teste foram removidos
```

**Exemplo 4: Erro em Inserção Múltipla**
```sql
-- Tentar inserir vários artistas
BEGIN;

-- Primeira inserção OK
INSERT INTO artista (id_artista, nome_artista, pais_origem)
VALUES (30, 'Radiohead', 'Reino Unido');

-- Segunda inserção OK
INSERT INTO artista (id_artista, nome_artista, pais_origem)
VALUES (31, 'Nirvana', 'Estados Unidos');

-- Terceira inserção com erro - ID duplicado
INSERT INTO artista (id_artista, nome_artista, pais_origem)
VALUES (1, 'Artista Duplicado', 'Brasil');  -- ID 1 já existe!

-- Erro detectado, reverter TODAS as inserções
ROLLBACK;

-- Nenhum dos 3 artistas foi inserido
-- Garante consistência: ou insere todos ou nenhum
```

#### 8.3 Boas Práticas com Transações

**✅ Sempre use transações para operações relacionadas**:
```sql
-- ✅ CORRETO: Garantir atomicidade
BEGIN;
INSERT INTO artista (id_artista, nome_artista) VALUES (40, 'Artista');
INSERT INTO album (id_album, titulo, id_artista) VALUES (50, 'Álbum', 40);
COMMIT;

-- ❌ EVITAR: Inserções separadas podem deixar dados órfãos
INSERT INTO artista (id_artista, nome_artista) VALUES (40, 'Artista');
-- Se próximo comando falhar, artista fica sem álbum
INSERT INTO album (id_album, titulo, id_artista) VALUES (50, 'Álbum', 40);
```

**✅ Validar antes de COMMIT**:
```sql
BEGIN;

-- Inserir dados
INSERT INTO usuario (id_usuario, nome_usuario, email)
VALUES (200, 'Novo Usuário', 'novo@email.com');

-- Verificar se inserção está correta
SELECT * FROM usuario WHERE id_usuario = 200;

-- Se tudo OK, confirmar
COMMIT;
-- Se algo errado, usar ROLLBACK
```

**✅ Manter transações curtas**:
```sql
-- ✅ BOM: Transação focada e rápida
BEGIN;
INSERT INTO genero (id_genero, nome_genero) VALUES (20, 'Funk');
INSERT INTO genero (id_genero, nome_genero) VALUES (21, 'Soul');
COMMIT;

-- ❌ EVITAR: Transação muito longa bloqueia recursos
BEGIN;
-- Centenas de inserções...
-- Consultas complexas...
-- Aguardar entrada do usuário...
COMMIT;
```

**✅ Documentar decisões de COMMIT/ROLLBACK**:
```sql
BEGIN;

-- Inserção crítica para o sistema
INSERT INTO artista (id_artista, nome_artista, pais_origem)
VALUES (50, 'Artista Principal', 'Brasil');

-- Verificação de integridade
IF EXISTS (SELECT 1 FROM artista WHERE id_artista = 50) THEN
    COMMIT;  -- Sucesso: confirmar inserção
ELSE
    ROLLBACK;  -- Falha: reverter e investigar
END IF;
```

**Cenários práticos de uso**:

| Situação | Comando | Motivo |
|----------|---------|--------|
| Todas inserções bem-sucedidas | COMMIT | Tornar mudanças permanentes |
| Erro de constraint detectado | ROLLBACK | Manter consistência do banco |
| Teste de funcionalidade | ROLLBACK | Não comprometer dados reais |
| Validação de negócio falhou | ROLLBACK | Regras de negócio não satisfeitas |
| Usuário cancelou operação | ROLLBACK | Respeitar decisão do usuário |
| Carga de dados completa | COMMIT | Finalizar importação com sucesso |

## Perguntas e Respostas

### 1. Qual a diferença entre especificar colunas no INSERT vs. não especificar?

**Resposta**:
**Com especificação de colunas** (recomendado):
```sql
INSERT INTO artista (id_artista, nome_artista, pais_origem)
VALUES (1, 'The Beatles', 'Reino Unido');
```
- **Vantagens**: Explícito, resiliente a mudanças de estrutura, permite ordem diferente
- **Flexibilidade**: Não precisa fornecer todas as colunas

**Sem especificação**:
```sql
INSERT INTO artista VALUES (1, 'The Beatles', NULL, NULL, 'Reino Unido', TRUE);
```
- **Riscos**: Frágil a mudanças, ordem rígida, deve incluir todas as colunas
- **Uso**: Apenas quando estrutura é muito estável

### 2. Como lidar adequadamente com valores NULL durante inserção?

**Resposta**: Estratégias para NULL:

**Inserção explícita de NULL**:
```sql
INSERT INTO artista (id_artista, nome_artista, biografia)
VALUES (1, 'The Beatles', NULL); -- biografia não disponível ainda
```

**Aproveitamento de DEFAULT**:
```sql
-- Se data_cadastro tem DEFAULT CURRENT_TIMESTAMP
INSERT INTO usuario (id_usuario, nome_usuario, email)
VALUES (1, 'João', 'joao@email.com'); -- data_cadastro será preenchida automaticamente
```

**Validação antes da inserção**:
- Verificar se campos NOT NULL estão sendo fornecidos
- Usar CASE ou COALESCE para tratar valores opcionais

### 3. Qual a melhor abordagem para inserir dados relacionados?

**Resposta**: Seguir ordem de dependências:

**1. Inserir entidades independentes primeiro**:
```sql
-- Primeiro: artistas (sem dependências)
INSERT INTO artista (id_artista, nome_artista) VALUES (1, 'The Beatles');

-- Segundo: álbuns (dependem de artistas)
INSERT INTO album (id_album, titulo, id_artista) VALUES (1, 'Abbey Road', 1);

-- Terceiro: músicas (dependem de álbuns)
INSERT INTO musica (id_musica, titulo, id_album) VALUES (1, 'Come Together', 1);
```

**2. Usar transações para consistência**:
```sql
BEGIN TRANSACTION;
-- Todas as inserções relacionadas
COMMIT; -- ou ROLLBACK em caso de erro
```

### 4. Como tratar erros de integridade durante INSERT?

**Resposta**: Estratégias de tratamento:

**Prevenção**:
```sql
-- Verificar se FK existe antes de inserir
SELECT COUNT(*) FROM artista WHERE id_artista = 1;
-- Se > 0, pode inserir álbum com id_artista = 1
```

**Tratamento de duplicatas**:
```sql
-- MySQL: INSERT IGNORE (ignora duplicatas)
INSERT IGNORE INTO artista VALUES (1, 'The Beatles', 'Reino Unido');

-- PostgreSQL: ON CONFLICT
INSERT INTO artista VALUES (1, 'The Beatles', 'Reino Unido')
ON CONFLICT (id_artista) DO NOTHING;
```

**Validação em aplicação**: Sempre validar dados antes de enviar para o banco.

### 5. Quando usar INSERT com subconsulta vs. INSERT com VALUES?

**Resposta**:
**INSERT com VALUES**: Para registros específicos
```sql
INSERT INTO artista (id_artista, nome_artista)
VALUES (1, 'The Beatles'), (2, 'Queen'), (3, 'Led Zeppelin');
```
- Ideal para dados conhecidos
- Múltiplos registros simultâneos

**INSERT com subconsulta**: Para dados derivados
```sql
-- Criar playlist com músicas de determinado gênero
INSERT INTO playlist_musica (id_playlist, id_musica)
SELECT 1, id_musica 
FROM musica m
JOIN album a ON m.id_album = a.id_album
WHERE a.genero = 'Rock';
```
- Ideal para migração ou cálculos
- Baseado em dados existentes

### 6. Como otimizar performance para inserções em lote?

**Resposta**: Técnicas de otimização:

**Múltiplos valores em um INSERT**:
```sql
INSERT INTO musica (id_musica, titulo, duracao)
VALUES 
    (1, 'Come Together', 259),
    (2, 'Something', 182),
    (3, 'Maxwell', 207);
-- Mais eficiente que 3 INSERTs separados
```

**Desabilitar constraints temporariamente** (cuidado):
```sql
-- Para cargas grandes, se necessário
ALTER TABLE musica DISABLE CONSTRAINT fk_musica_album;
-- Inserções em lote
ALTER TABLE musica ENABLE CONSTRAINT fk_musica_album;
```

**Usar transações**: Agrupar inserções relacionadas.

### 7. Quais as principais armadilhas a evitar com comando INSERT?

**Resposta**: Armadilhas comuns:

**1. Inserção sem transação em operações relacionadas**:
```sql
-- ❌ Perigoso: Se segunda inserção falhar, primeira fica órfã
INSERT INTO album VALUES (1, 'Album', 1);
INSERT INTO musica VALUES (1, 'Música', 999); -- FK inválida
```

**2. Não validar limites de campos**:
```sql
-- ❌ Pode truncar dados
INSERT INTO artista (nome_artista) VALUES ('Nome muito longo que excede limite...');
```

**3. Ignorar valores DEFAULT úteis**:
```sql
-- ❌ Especificar NULL quando DEFAULT seria melhor
INSERT INTO usuario (id_usuario, nome, data_cadastro)
VALUES (1, 'João', NULL); -- Melhor deixar DEFAULT

-- ✅ Aproveitar DEFAULT
INSERT INTO usuario (id_usuario, nome)
VALUES (1, 'João');
```

**4. Não considerar encoding de caracteres**: Verificar UTF-8 para caracteres especiais.

## Referências Bibliográficas

1. **Beaulieu, A.** (2020). *Learning SQL: Master SQL Fundamentals*. 3rd Edition. O'Reilly Media. Capítulo 3.

2. **Forta, B.** (2018). *SQL in 10 Minutes, Sams Teach Yourself*. 5th Edition. Sams Publishing. Lições 15-16.

3. **Date, C.J.** (2012). *SQL and Relational Theory*. 2nd Edition. O'Reilly Media. Capítulo 4.

4. **Oracle Corporation** (2021). *Oracle Database SQL Language Reference*. Seção INSERT.

---

**Módulo Anterior**: [06 - Alteração de Estrutura de uma Tabela](../06-alteracao-estrutura-tabela/README.md)
**Próximo Módulo**: [08 - Manipulação de Dados - Inserindo Dados (Parte II)](../08-manipulacao-dados-inserindo-parte-ii/README.md)