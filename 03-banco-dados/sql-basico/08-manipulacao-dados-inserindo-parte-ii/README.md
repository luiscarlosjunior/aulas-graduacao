# Módulo 08 - Manipulação de Dados - Inserindo Dados (Parte II)

## 📋 Pré-requisitos

Para executar os scripts deste módulo, você precisará de:
- **Oracle Database 11g ou superior** (11g, 12c, 18c, 19c, 21c)
- **Oracle SQL Developer** instalado e configurado
- Estrutura base do Sistema MusiStream já criada (veja seção "Preparação do Ambiente")
- Conhecimento dos comandos INSERT básicos (Módulo 07)

## 🎯 Objetivos de Aprendizagem

Ao final deste módulo, o aluno será capaz de:
- Utilizar comandos INSERT avançados no Oracle SQL Developer
- Realizar inserções em lote (bulk insert) de forma eficiente
- Implementar inserções condicionais com NOT EXISTS
- Trabalhar com INSERT... SELECT para copiar e transformar dados
- Usar SEQUENCE para geração automática de IDs
- Aplicar o comando MERGE (UPSERT) para inserir ou atualizar
- Utilizar INSERT ALL para inserções em múltiplas tabelas
- Gerar dados de teste com funções do Oracle
- Implementar tratamento de erros em PL/SQL
- Aplicar técnicas de otimização para inserção de grandes volumes

## 🔧 Preparação do Ambiente

### Criando a Estrutura Base do Sistema MusiStream

Antes de executar os exemplos avançados de INSERT, você precisa ter a estrutura base criada. Execute os seguintes scripts **na ordem**:

#### Passo 1: Abrir SQL Developer
1. Abra o **Oracle SQL Developer**
2. Conecte-se ao seu banco de dados Oracle
3. Verifique a conexão testando com: `SELECT * FROM dual;`

#### Passo 2: Criar Estrutura Base
Execute o script de estrutura completa localizado em:
```
linguagem-sql/streaming-de-musica/01-estrutura-completa.sql
```

**Como executar no SQL Developer:**
1. Abra o arquivo no SQL Developer (File → Open)
2. Clique no botão **"Run Script" (F5)** ou use o ícone de documento com play
3. Aguarde a conclusão (deve criar tabelas, sequences e inserir dados básicos)

#### Passo 3: Verificar Estrutura Criada
Execute a seguinte consulta para verificar se as tabelas foram criadas:
```sql
SELECT table_name 
FROM user_tables 
WHERE table_name IN ('GENERO', 'ARTISTA', 'ALBUM', 'MUSICA', 'USUARIO', 'PLAYLIST')
ORDER BY table_name;
```

Você deve ver pelo menos estas 6 tabelas listadas.

### Executando os Scripts deste Módulo

Depois de preparar o ambiente, abra o script principal:
```
linguagem-sql/08-manipulacao-dados-inserindo-parte-ii/exemplos/insert_avancado.sql
```

**⚠️ IMPORTANTE: Como Executar no SQL Developer**

Este script contém múltiplos tipos de comandos SQL e PL/SQL. Siga estas orientações:

1. **Para comandos SQL simples (CREATE, INSERT, SELECT):**
   - Selecione o comando que deseja executar
   - Pressione **Ctrl+Enter** ou clique no botão "Run Statement"

2. **Para blocos PL/SQL (BEGIN...END;):**
   - Selecione TODO o bloco incluindo o `/` no final
   - Pressione **F5** ou clique no botão "Run Script"
   - Ou use o comando: **@caminho/do/arquivo.sql**

3. **Para executar o script completo:**
   - Pressione **F5** (Run Script)
   - OU use a janela de comandos: `@insert_avancado.sql`

4. **Habilitar DBMS_OUTPUT (para ver mensagens):**
   ```sql
   SET SERVEROUTPUT ON;
   ```
   Execute isso antes dos blocos PL/SQL que usam `DBMS_OUTPUT.PUT_LINE`

## 💡 Dicas Específicas para Oracle SQL Developer

### Atalhos Úteis
- **Ctrl+Enter**: Executar comando atual (Statement)
- **F5**: Executar como script (Script Output)
- **F9**: Executar e mostrar resultados em grade
- **Ctrl+/**: Comentar/descomentar linha
- **Ctrl+Shift+F**: Formatar código SQL

### Diferenças entre "Run Statement" e "Run Script"
- **Run Statement (Ctrl+Enter)**: 
  - Executa apenas um comando por vez
  - Mostra resultados em grade
  - **Use para**: SELECT, INSERT único, UPDATE, DELETE
  
- **Run Script (F5)**:
  - Executa múltiplos comandos em sequência
  - Mostra saída em texto
  - **Use para**: Scripts com múltiplos comandos, blocos PL/SQL, comandos DDL

### Tratamento de Erros Comuns

#### Erro: "ORA-00001: unique constraint violated"
**Causa**: Tentativa de inserir chave duplicada  
**Solução**: Execute a seção de limpeza no final do script antes de reexecutar

#### Erro: "ORA-02291: integrity constraint violated"
**Causa**: Chave estrangeira referenciando registro inexistente  
**Solução**: Certifique-se de executar os INSERTs na ordem correta (pais antes de filhos)

#### Erro: "PLS-00103: Encountered the symbol..."
**Causa**: Bloco PL/SQL executado com "Run Statement" ao invés de "Run Script"  
**Solução**: Selecione todo o bloco (incluindo `/`) e use **F5**

#### Erro: "SP2-0552: Bind variable not declared"
**Causa**: Variáveis de substituição (&) não funcionam no SQL Developer do mesmo modo que no SQL*Plus  
**Solução**: Substitua `&variavel` por valores fixos ou use janela de prompt

### Configurações Recomendadas

Antes de executar os scripts, configure o SQL Developer:

```sql
-- Habilitar saída de mensagens PL/SQL
SET SERVEROUTPUT ON SIZE UNLIMITED;

-- Configurar formato de data
ALTER SESSION SET NLS_DATE_FORMAT = 'DD/MM/YYYY';

-- Configurar formato de timestamp
ALTER SESSION SET NLS_TIMESTAMP_FORMAT = 'DD/MM/YYYY HH24:MI:SS';

-- Ver todas as mensagens de feedback
SET FEEDBACK ON;
SET ECHO ON;
```

## 📚 Conteúdo Teórico

### 1. Inserção Múltipla de Registros

A inserção múltipla permite adicionar vários registros de uma só vez, otimizando performance e reduzindo overhead de comunicação com o banco de dados.

#### 1.1 INSERT com Múltiplos VALUES

**Conceito**: Inserir múltiplos registros em uma única operação usando múltiplas cláusulas VALUES.

```sql
-- Inserir múltiplos artistas de uma vez
INSERT INTO artista (id_artista, nome_artista, pais_origem, numero_membros)
VALUES 
    (10, 'Pink Floyd', 'Reino Unido', 4),
    (11, 'Led Zeppelin', 'Reino Unido', 4),
    (12, 'The Rolling Stones', 'Reino Unido', 5),
    (13, 'Metallica', 'Estados Unidos', 4),
    (14, 'Iron Maiden', 'Reino Unido', 6);
```

**✅ Vantagens**:
- **Performance superior**: 80-95% mais rápido que inserções individuais
- **Menor overhead de rede**: Uma única transação para múltiplos registros
- **Atomicidade**: Todos os registros são inseridos ou nenhum é inserido
- **Menos logs de transação**: Reduz a quantidade de entradas no log

**❌ Desvantagens**:
- **Limitação de tamanho**: Muitos SGBDs têm limite de tamanho da consulta SQL
- **Menor flexibilidade**: Todos os registros devem ter a mesma estrutura
- **Memória**: Consome mais memória para consultas muito grandes
- **Erro total**: Se um registro falha, toda a operação falha

**🎯 Quando usar**:
- Carregamento de dados de configuração (gêneros musicais, países, etc.)
- Inserção de lotes pequenos a médios (até 1000 registros)
- Dados com estrutura idêntica e conhecidos antecipadamente
- Migrações de dados estruturados

**⚠️ Quando NÃO usar**:
- Lotes muito grandes (>10.000 registros) - preferir INSERT... SELECT
- Quando necessita controle individual de erros por registro
- Dados com estruturas diferentes ou condicionais
- Sistemas com limitações rígidas de memória

#### 1.2 INSERT ALL - Oracle

**Conceito**: Extensão Oracle que permite inserir em múltiplas tabelas em uma única operação, ideal para cenários de desnormalização ou carregamento de dados relacionados.

```sql
-- Inserir em múltiplas tabelas relacionadas
INSERT ALL
    INTO artista (id_artista, nome_artista, pais_origem) VALUES (20, 'U2', 'Irlanda')
    INTO album (id_album, titulo, id_artista, ano_lancamento) VALUES (20, 'The Joshua Tree', 20, 1987)
    INTO album (id_album, titulo, id_artista, ano_lancamento) VALUES (21, 'Achtung Baby', 20, 1991)
SELECT * FROM dual;
```

**✅ Vantagens**:
- **Consistência garantida**: Todas as tabelas são atualizadas atomicamente
- **Eficiência**: Uma única passada pelos dados fonte
- **Integridade referencial**: Mantém relacionamentos consistentes
- **Menos código**: Evita múltiplos comandos INSERT separados

**❌ Desvantagens**:
- **Específico do Oracle**: Não portável para outros SGBDs
- **Complexidade**: Mais difícil de debugar em caso de erros
- **Limitações de sintaxe**: Estrutura menos flexível que comandos separados
- **Rollback complexo**: Desfazer operação afeta múltiplas tabelas

**🎯 Quando usar**:
- Carregamento de dados relacionados (artista + álbuns + músicas)
- Cenários de ETL onde dados relacionados chegam juntos
- Garantir consistência entre tabelas relacionadas
- Otimização de performance em cargas complexas

**⚠️ Quando NÃO usar**:
- Sistemas que requerem portabilidade entre SGBDs
- Quando relações podem falhar individualmente
- Lógica complexa que requer validações intermediárias
- Ambientes onde preferência é por operações simples e auditáveis

### 2. INSERT... SELECT

INSERT... SELECT é uma das técnicas mais poderosas para manipulação de dados, permitindo inserir registros baseados nos resultados de consultas. É essencial para operações ETL, migrações e análise de dados.

#### 2.1 Cópia de Dados Entre Tabelas

**Conceito**: Transferir dados entre tabelas usando consultas SELECT, permitindo filtros e transformações durante o processo.

```sql
-- Criar tabela de backup de artistas
CREATE TABLE backup_artista AS 
SELECT * FROM artista WHERE 1=0; -- estrutura sem dados

-- Copiar artistas ativos para backup
INSERT INTO backup_artista
SELECT * FROM artista 
WHERE ativo = 'S';
```

**✅ Vantagens**:
- **Performance excepcional**: Processamento interno do SGBD, muito mais rápido que loops
- **Escalabilidade**: Pode processar milhões de registros eficientemente
- **Simplicidade**: Uma única operação para transferências complexas
- **Otimização automática**: SGBD otimiza a consulta automaticamente

**❌ Desvantagens**:
- **Consumo de recursos**: Pode sobrecarregar o sistema com grandes volumes
- **Bloqueios**: Pode causar locks em tabelas por períodos prolongados
- **Rollback caro**: Desfazer operações grandes consome muito espaço
- **Menos controle**: Difícil fazer validações registro por registro

**🎯 Quando usar**:
- Migração de dados entre ambientes (desenvolvimento → produção)
- Criação de tabelas de histórico ou backup
- Consolidação de dados de múltiplas fontes
- Carga inicial de data warehouses

**⚠️ Quando NÃO usar**:
- Sistemas com pouca memória ou espaço de rollback limitado
- Quando necessita validação complexa registro por registro
- Operações que requerem interação com sistemas externos
- Cenários onde falha parcial é aceitável

#### 2.2 Inserção com Transformação de Dados

**Conceito**: Combinar inserção com lógica de negócio, cálculos e agregações, criando informações derivadas diretamente na inserção.

```sql
-- Criar estatísticas de reprodução por artista
INSERT INTO estatistica_artista (id_artista, nome_artista, total_reproducoes, media_duracao)
SELECT 
    a.id_artista,
    a.nome_artista,
    COUNT(hr.id_musica) as total_reproducoes,
    ROUND(AVG(m.duracao), 2) as media_duracao
FROM artista a
LEFT JOIN album al ON a.id_artista = al.id_artista
LEFT JOIN musica m ON al.id_album = m.id_album
LEFT JOIN historico_reproducao hr ON m.id_musica = hr.id_musica
GROUP BY a.id_artista, a.nome_artista;
```

**✅ Vantagens**:
- **Processamento único**: Cálculo e inserção em uma operação
- **Performance superior**: Evita múltiplas passadas pelos dados
- **Flexibilidade**: Permite lógica complexa com JOINs, GROUP BY, funções
- **Consistência**: Garante que dados derivados sejam calculados uniformemente

**❌ Desvantagens**:
- **Complexidade**: Consultas podem ficar muito complexas para manutenção
- **Debugging difícil**: Problemas são mais difíceis de isolar
- **Dependência de dados**: Falhas em dados fonte afetam toda operação
- **Menor reutilização**: Lógica fica acoplada à operação de inserção

**🎯 Quando usar**:
- Criação de relatórios ou dashboards (tabelas summary)
- Processamento de dados para análise (data marts)
- Atualização de indicadores de performance (KPIs)
- Cargas de data warehouse com transformações

**⚠️ Quando NÃO usar**:
- Lógica de negócio que muda frequentemente
- Quando transformação pode falhar para subconjuntos dos dados
- Cenários onde auditoria detalhada de transformação é necessária
- Sistemas onde preferência é por ETL em ferramentas especializadas

### 3. Uso de Sequências

As sequências são objetos de banco de dados dedicados à geração de números únicos, essenciais para criação de chaves primárias e controle de versionamento. Compreender seu uso adequado é fundamental para integridade e performance.

#### 3.1 Criação de Sequências

**Conceito**: Definir geradores automáticos de números únicos com controle sobre incremento, limites e cache para otimização de performance.

```sql
-- Sequência para IDs de usuários
CREATE SEQUENCE seq_usuario
START WITH 1
INCREMENT BY 1
MAXVALUE 999999999
NOCACHE
NOCYCLE;

-- Sequência para IDs de playlists
CREATE SEQUENCE seq_playlist
START WITH 100
INCREMENT BY 1
MAXVALUE 999999999
CACHE 20
NOCYCLE;
```

**✅ Vantagens das Sequências**:
- **Unicidade garantida**: Impossível gerar IDs duplicados mesmo em alta concorrência
- **Performance**: Geração muito rápida de IDs, especialmente com cache
- **Portabilidade**: Suporte nativo na maioria dos SGBDs modernos  
- **Controle granular**: Configuração detalhada de comportamento (incremento, limites)
- **Thread-safe**: Funciona corretamente em ambientes multi-usuário

**❌ Desvantagens das Sequências**:
- **Lacunas (gaps)**: Rollbacks e falhas podem criar números pulados
- **Dependência de SGBD**: Sintaxe varia entre diferentes sistemas
- **Não reutilização**: Números deletados não são reaproveitados
- **Ordem sequencial**: Pode ser previsível em alguns contextos de segurança

**🎯 Quando usar Sequências**:
- Geração de chaves primárias numéricas incrementais
- Sistemas com alta concorrência de inserções
- Ambientes que requerem controle rigoroso de unicidade
- Aplicações onde performance de geração de ID é crítica
- Sistemas de auditoria que necessitam ordem temporal

**⚠️ Quando NÃO usar Sequências**:
- Sistemas distribuídos onde ordem global é impossível
- Chaves que precisam ser semanticamente significativas
- Ambientes onde lacunas são inaceitáveis para auditoria
- Sistemas que requerem portabilidade total entre SGBDs diferentes

#### 3.2 Usando Sequências em INSERT

**Conceito**: Integrar sequências diretamente nos comandos INSERT, aproveitando valores atuais (CURRVAL) e próximos (NEXTVAL) para manter relacionamentos.

```sql
-- Inserir usuário com ID automático
INSERT INTO usuario (id_usuario, nome_usuario, email, data_nascimento)
VALUES (seq_usuario.NEXTVAL, 'Ana Costa', 'ana@email.com', DATE '1995-03-10');

-- Inserir playlist com ID automático
INSERT INTO playlist (id_playlist, nome_playlist, id_usuario, descricao)
VALUES (seq_playlist.NEXTVAL, 'Minha Playlist', seq_usuario.CURRVAL, 'Playlist pessoal');
```

**✅ Vantagens do NEXTVAL/CURRVAL**:
- **Relacionamentos automáticos**: CURRVAL permite referenciar ID recém-criado
- **Atomicidade**: Operação integral com geração de ID
- **Eficiência**: Não requer consultas adicionais para obter IDs
- **Consistência**: Mantém integridade referencial automaticamente

**❌ Desvantagens do NEXTVAL/CURRVAL**:
- **Sessão-dependente**: CURRVAL só funciona após NEXTVAL na mesma sessão
- **Complexidade em lotes**: Dificulta inserções em massa com relacionamentos
- **Debugging**: Difícil rastrear qual valor específico foi gerado
- **Limitação de rollback**: NEXTVAL não volta mesmo com ROLLBACK

**🎯 Quando usar NEXTVAL/CURRVAL**:
- Inserção de registros pai-filho em sequência
- Transações simples com poucos relacionamentos
- Aplicações onde controle de sessão é bem definido
- Cenários onde IDs gerados não precisam ser conhecidos antecipadamente

**⚠️ Quando NÃO usar NEXTVAL/CURRVAL**:
- Inserções em lote onde relacionamentos complexos existem
- Sistemas onde IDs precisam ser conhecidos antes da inserção
- Aplicações multi-sessão que compartilham dados relacionais
- Cenários onde rastreabilidade detalhada de IDs é essencial

### 4. Inserção Condicional

A inserção condicional é uma técnica avançada que permite controlar quando dados devem ser inseridos, evitando duplicatas e implementando lógicas de negócio complexas diretamente no nível de banco de dados.

#### 4.1 INSERT quando não existe

**Conceito**: Inserir registros apenas quando certas condições não são atendidas, comumente usado para evitar duplicatas sem gerar erros.

```sql
-- Inserir gênero apenas se não existir
INSERT INTO genero (id_genero, nome_genero, descricao)
SELECT 100, 'Progressive Rock', 'Rock progressivo dos anos 70'
FROM dual
WHERE NOT EXISTS (
    SELECT 1 FROM genero WHERE nome_genero = 'Progressive Rock'
);
```

**✅ Vantagens**:
- **Segurança**: Evita erros de duplicata sem necessidade de tratamento de exceção
- **Performance**: Uma única operação SQL ao invés de SELECT + INSERT
- **Atomicidade**: Verificação e inserção são operação atômica
- **Simplicidade**: Não requer lógica de aplicação adicional

**❌ Desvantagens**:
- **Complexidade da consulta**: Pode tornar SQL mais difícil de ler
- **Performance em tabelas grandes**: NOT EXISTS pode ser lento sem índices adequados
- **Limitação de lógica**: Condições complexas podem tornar manutenção difícil
- **Debugging**: Mais difícil identificar por que um registro não foi inserido

**🎯 Quando usar**:
- Carregamento de dados de configuração (gêneros, países, categorias)
- Operações idempotentes que podem ser executadas múltiplas vezes
- Sincronização de dados entre sistemas
- Cargas incrementais onde duplicatas devem ser evitadas

**⚠️ Quando NÃO usar**:
- Quando erro de duplicata deve ser reportado à aplicação
- Lógica condicional muito complexa que prejudica performance
- Cenários onde auditoria requer saber tentativas de duplicata
- Sistemas onde NOT EXISTS é proibitivamente lento

#### 4.2 MERGE (UPSERT)

**Conceito**: Operação que combina INSERT e UPDATE em uma única ação - "upsert" (update ou insert). Fundamental para sincronização de dados e manutenção de tabelas de cache.

```sql
-- Inserir ou atualizar estatísticas de reprodução
MERGE INTO estatistica_mensal est
USING (
    SELECT 
        m.id_musica,
        EXTRACT(MONTH FROM hr.data_reproducao) as mes,
        EXTRACT(YEAR FROM hr.data_reproducao) as ano,
        COUNT(*) as total_reproducoes
    FROM musica m
    JOIN historico_reproducao hr ON m.id_musica = hr.id_musica
    WHERE hr.data_reproducao >= TRUNC(SYSDATE, 'MM')
    GROUP BY m.id_musica, EXTRACT(MONTH FROM hr.data_reproducao), EXTRACT(YEAR FROM hr.data_reproducao)
) src ON (est.id_musica = src.id_musica AND est.mes = src.mes AND est.ano = src.ano)
WHEN MATCHED THEN
    UPDATE SET est.total_reproducoes = src.total_reproducoes
WHEN NOT MATCHED THEN
    INSERT (id_musica, mes, ano, total_reproducoes)
    VALUES (src.id_musica, src.mes, src.ano, src.total_reproducoes);
```

**✅ Vantagens do MERGE**:
- **Versatilidade máxima**: Uma operação para inserir, atualizar ou até deletar
- **Performance superior**: Uma passada pelos dados ao invés de múltiplas operações
- **Atomicidade completa**: Toda operação é atômica mesmo com milhões de registros
- **Ideal para ETL**: Padrão fundamental em cargas de data warehouse
- **Sincronização**: Perfeito para manter tabelas sincronizadas

**❌ Desvantagens do MERGE**:
- **Complexidade**: Consultas MERGE podem ficar muito complexas
- **Portabilidade limitada**: Sintaxe varia significativamente entre SGBDs
- **Debugging difícil**: Difícil rastrear qual parte (INSERT/UPDATE) foi executada
- **Consumo de recursos**: Pode ser intensivo em CPU e I/O para grandes volumes
- **Locks prolongados**: Pode bloquear tabelas por mais tempo

**🎯 Quando usar MERGE**:
- Sincronização regular entre sistemas (replicação de dados)
- Atualização de tabelas de cache ou summary
- Cargas incrementais em data warehouses
- Integração de dados de múltiplas fontes
- Cenários onde INSERT e UPDATE precisam ser atômicos

**⚠️ Quando NÃO usar MERGE**:
- Operações simples que só precisam de INSERT ou só UPDATE
- Sistemas com requisitos rígidos de portabilidade
- Tabelas com muitos triggers que podem complicar performance
- Cenários onde controle fino sobre INSERT vs UPDATE é necessário
- Ambientes onde consumo de recursos precisa ser minimizado

### 5. Técnicas de Otimização

A otimização de inserções é crucial para sistemas de alto volume. Compreender as diferentes técnicas e seus trade-offs permite escolher a melhor abordagem para cada cenário de performance.

#### 5.1 Inserção em Lote (Batch Insert)

**Conceito**: Agrupar múltiplas inserções usando técnicas específicas do SGBD para maximizar performance, especialmente em operações de grande volume.

```sql
-- Desabilitar logs para inserção massiva (cuidado!)
ALTER TABLE historico_reproducao NOLOGGING;

-- Inserir em lotes usando FORALL (PL/SQL)
DECLARE
    TYPE t_musica_id IS TABLE OF NUMBER;
    TYPE t_usuario_id IS TABLE OF NUMBER;
    TYPE t_data_reprod IS TABLE OF DATE;
    
    v_musica_ids t_musica_id;
    v_usuario_ids t_usuario_id;
    v_datas t_data_reprod;
BEGIN
    -- Carregar dados em arrays
    SELECT id_musica, id_usuario, SYSDATE
    BULK COLLECT INTO v_musica_ids, v_usuario_ids, v_datas
    FROM (
        SELECT m.id_musica, u.id_usuario
        FROM musica m, usuario u
        WHERE ROWNUM <= 10000
    );
    
    -- Inserir em lote
    FORALL i IN 1..v_musica_ids.COUNT
        INSERT INTO historico_reproducao (id_historico, id_musica, id_usuario, data_reproducao)
        VALUES (seq_historico.NEXTVAL, v_musica_ids(i), v_usuario_ids(i), v_datas(i));
END;
/
```

**✅ Vantagens do Batch Insert**:
- **Performance extrema**: 10-50x mais rápido que inserções individuais
- **Redução de overhead**: Menos calls between client-server
- **Otimização de I/O**: SGBD pode otimizar escritas em disco
- **Redução de logs**: Menos entradas no transaction log

**❌ Desvantagens do Batch Insert**:
- **Consumo de memória**: Arrays grandes podem esgotar memória disponível
- **Complexidade**: Requer conhecimento específico do SGBD (PL/SQL, T-SQL, etc.)
- **All-or-nothing**: Se um registro falha, todo o lote pode falhar
- **Debugging difícil**: Difícil identificar registros problemáticos
- **NOLOGGING riscos**: Perda de dados em caso de falha sem backup

**🎯 Quando usar Batch Insert**:
- Cargas iniciais de data warehouse (milhões de registros)
- Migração de dados entre sistemas
- Importação de arquivos grandes (ETL)
- Cenários onde performance é mais crítica que controle de erro
- Ambientes com janelas de manutenção limitadas

**⚠️ Quando NÃO usar Batch Insert**:
- Sistemas OLTP com inserções em tempo real
- Quando controle individual de erros é essencial
- Ambientes com memória limitada
- Dados que requerem validações complexas registro por registro
- Sistemas onde recuperação de falhas deve ser granular

#### 5.2 INSERT com APPEND Hint

**Conceito**: Usar hints específicos do Oracle para forçar inserção direta, evitando buffering e otimizando para grandes volumes.

```sql
-- Usar hint APPEND para inserção direta
INSERT /*+ APPEND */ INTO historico_reproducao_backup
SELECT * FROM historico_reproducao
WHERE data_reproducao < SYSDATE - 365;
```

**✅ Vantagens do APPEND Hint**:
- **Bypass do buffer cache**: Escreve diretamente no disco, liberando memória
- **Parallel processing**: Permite execução paralela mais eficiente
- **Less undo generation**: Gera menos informações de rollback
- **Lock escalation**: Usa exclusive table locks para máxima velocidade

**❌ Desvantagens do APPEND Hint**:
- **Bloqueio da tabela**: Impede outros DML durante a operação
- **Específico do Oracle**: Não portável para outros SGBDs
- **Rollback limitado**: Operações grandes são difíceis de desfazer
- **Sem concurrent access**: Outras sessões não podem acessar tabela

**🎯 Quando usar APPEND Hint**:
- Operações de backup e archiving durante janelas de manutenção
- Cargas bulk onde tabela pode ser bloqueada temporariamente
- Data warehouse loading em tabelas dedicadas
- Cenários onde rollback não é uma preocupação

**⚠️ Quando NÃO usar APPEND Hint**:
- Sistemas 24x7 onde tabela precisa estar sempre disponível
- Operações que podem precisar de rollback
- Ambientes onde portabilidade de código é importante
- Tabelas pequenas onde overhead do hint supera benefícios

### 6. Inserção de Dados Complexos

A inserção de dados complexos envolve lógicas avançadas, subconsultas e funções analíticas. É essencial para cenários de análise de dados e business intelligence onde dados derivados são tão importantes quanto dados brutos.

#### 6.1 Inserção com Subconsultas

**Conceito**: Utilizar subconsultas para criar registros baseados em análises complexas dos dados existentes, permitindo automação de lógicas de negócio.

```sql
-- Criar playlist automática com top 50 músicas do usuário
INSERT INTO playlist_musica (id_playlist, id_musica, ordem_reproducao)
SELECT 
    &playlist_id,
    id_musica,
    ROWNUM
FROM (
    SELECT DISTINCT hr.id_musica
    FROM historico_reproducao hr
    WHERE hr.id_usuario = &usuario_id
    GROUP BY hr.id_musica
    ORDER BY COUNT(*) DESC
)
WHERE ROWNUM <= 50;
```

**✅ Vantagens das Subconsultas**:
- **Lógica sofisticada**: Permite implementar regras de negócio complexas diretamente no SQL
- **Performance otimizada**: SGBD pode otimizar subconsultas automaticamente
- **Automatização**: Reduz necessidade de lógica de aplicação
- **Flexibilidade**: Podem usar qualquer combinação de operadores SQL

**❌ Desvantagens das Subconsultas**:
- **Complexidade de manutenção**: Código SQL complexo é difícil de manter e debugar
- **Performance imprevísível**: Subconsultas complexas podem ter performance inconsistente
- **Portabilidade limitada**: Algumas otimizações são específicas do SGBD
- **Debugging difícil**: Difícil isolar problemas em consultas aninhadas profundas

**🎯 Quando usar Subconsultas**:
- Criação de dados derivados para analytics e BI
- Automação de regras de negócio que raramente mudam
- Cenários onde lógica no banco é mais performática que na aplicação
- Operações batch que processam grandes volumes de dados

**⚠️ Quando NÃO usar Subconsultas**:
- Lógicas de negócio que mudam frequentemente
- Quando debugging e manutenibilidade são prioridades
- Sistemas onde toda lógica deve estar na camada de aplicação
- Cenários com requisitos rigorosos de portabilidade

#### 6.2 Inserção com Funções de Janela

**Conceito**: Combinar inserções com window functions para criar análises avançadas e rankings, especialmente útil para relatórios e dashboards.

```sql
-- Inserir ranking de músicas por gênero
INSERT INTO ranking_musica (id_musica, id_genero, posicao_ranking, pontuacao)
SELECT 
    id_musica,
    id_genero,
    ROW_NUMBER() OVER (PARTITION BY id_genero ORDER BY total_reproducoes DESC) as posicao,
    total_reproducoes
FROM (
    SELECT 
        m.id_musica,
        m.id_genero,
        COUNT(hr.id_historico) as total_reproducoes
    FROM musica m
    LEFT JOIN historico_reproducao hr ON m.id_musica = hr.id_musica
    WHERE hr.data_reproducao >= SYSDATE - 30
    GROUP BY m.id_musica, m.id_genero
    HAVING COUNT(hr.id_historico) > 0
);
```

**✅ Vantagens das Window Functions**:
- **Análise avançada**: Permite cálculos sofisticados como rankings, percentis, médias móveis
- **Performance superior**: Uma passada pelos dados para múltiplos cálculos analíticos
- **Flexibilidade**: Suporta particionamento, ordenação e frames complexos
- **Padrão SQL**: Suporte amplo em SGBDs modernos

**❌ Desvantagens das Window Functions**:
- **Complexidade**: Sintaxe e conceitos podem ser difíceis para desenvolvedores iniciantes
- **Consumo de recursos**: Podem ser intensivas em CPU e memória para grandes datasets
- **Limitações de portabilidade**: Funcionalidades avançadas variam entre SGBDs
- **Debugging complexo**: Difícil debugar lógicas que combinam window functions com outras operações

**🎯 Quando usar Window Functions**:
- Criação de relatórios de ranking e análise competitiva
- Cálculo de métricas de negócio (Top N, percentis, tendências)
- Dashboards que requerem dados agregados e comparativos
- Data warehousing e business intelligence

**⚠️ Quando NÃO usar Window Functions**:
- Operações simples que não requerem análise sofisticada
- Sistemas com recursos computacionais limitados
- Cenários onde simplicidade e manutenibilidade são prioridades
- Ambientes que precisam de compatibilidade com SGBDs legados

**💡 Considerações de Complexidade**:

As inserções com dados complexos representam um trade-off fundamental:

- **Benefício**: Centralização da lógica no banco, performance otimizada, consistência garantida
- **Custo**: Maior complexidade de código, debugging mais difícil, acoplamento com SGBD específico
- **Ponto de equilíbrio**: Use para lógicas estáveis e críticas de performance, evite para regras de negócio que mudam frequentemente

### 7. Tratamento de Erros e Conflitos

O tratamento adequado de erros em inserções é fundamental para sistemas robustos. Compreender as diferentes estratégias permite construir aplicações que lidam graciosamente com falhas, mantendo integridade dos dados e experiência do usuário.

#### 7.1 INSERT com Tratamento de Duplicatas

**Conceito**: Implementar estratégias para lidar com violações de constraints de unicidade sem interromper o processo de inserção.

```sql
-- Inserir ignorando duplicatas
BEGIN
    INSERT INTO usuario_favorito (id_usuario, id_musica, data_adicao)
    VALUES (1, 100, SYSDATE);
EXCEPTION
    WHEN DUP_VAL_ON_INDEX THEN
        NULL; -- Ignorar erro de duplicata
END;
/
```

**✅ Vantagens do Tratamento de Duplicatas**:
- **Robustez**: Aplicação continua funcionando mesmo com dados duplicados
- **Simplicidade**: Evita necessidade de verificações prévias
- **Performance**: Mais rápido que SELECT antes de INSERT
- **Idempotência**: Operações podem ser executadas múltiplas vezes com segurança

**❌ Desvantagens do Tratamento de Duplicatas**:
- **Mascaramento de problemas**: Pode ocultar bugs na lógica de aplicação
- **Auditoria limitada**: Tentativas de duplicação não são registradas
- **Complexidade específica**: Cada SGBD tem sintaxe diferente
- **Performance em alta concorrência**: Muitas exceções podem degradar performance

**🎯 Quando usar Tratamento de Duplicatas**:
- Operações idempotentes (como favoritar uma música)
- Sincronização de dados entre sistemas
- Cargas de dados onde duplicatas são esperadas e aceitáveis
- APIs que podem receber requests duplicados

**⚠️ Quando NÃO usar Tratamento de Duplicatas**:
- Quando duplicatas indicam bugs que devem ser corrigidos
- Sistemas que requerem auditoria completa de tentativas
- Casos onde usuário deve ser informado sobre tentativa de duplicação
- Cenários onde performance de exceções é problemática

#### 7.2 LOG de Erros em Inserções

**Conceito**: Implementar sistema robusto de logging para capturar, classificar e reportar erros durante operações de inserção em lote.

```sql
-- Criar tabela de log de erros
CREATE TABLE log_erros_insercao (
    id_erro NUMBER,
    tabela_destino VARCHAR2(50),
    erro_oracle NUMBER,
    mensagem_erro VARCHAR2(4000),
    dados_tentativa CLOB,
    data_erro DATE
);

-- Inserção com log de erros
DECLARE
    v_erro_count NUMBER := 0;
BEGIN
    FOR rec IN (SELECT * FROM temp_novos_usuarios) LOOP
        BEGIN
            INSERT INTO usuario (id_usuario, nome_usuario, email, data_nascimento)
            VALUES (rec.id_usuario, rec.nome_usuario, rec.email, rec.data_nascimento);
        EXCEPTION
            WHEN OTHERS THEN
                v_erro_count := v_erro_count + 1;
                INSERT INTO log_erros_insercao 
                VALUES (seq_log_erro.NEXTVAL, 'USUARIO', SQLCODE, SQLERRM, 
                       'ID: ' || rec.id_usuario || ', Nome: ' || rec.nome_usuario, SYSDATE);
        END;
    END LOOP;
    
    DBMS_OUTPUT.PUT_LINE('Erros encontrados: ' || v_erro_count);
END;
/
```

**✅ Vantagens do Log de Erros**:
- **Auditoria completa**: Registra todos os erros para análise posterior
- **Debugging**: Facilita identificação de padrões e problemas sistemáticos
- **Continuidade**: Permite processar registros válidos mesmo com alguns erros
- **Histórico**: Mantém registro temporal de problemas de qualidade de dados
- **Análise**: Permite identificar fontes de dados problemáticas

**❌ Desvantagens do Log de Erros**:
- **Overhead**: Processamento adicional para cada erro reduz performance
- **Complexidade de código**: Adiciona lógica significativa às operações de inserção
- **Gestão de log**: Logs podem crescer rapidamente e requerem manutenção
- **Transações longas**: Loop com tratamento de erro pode criar transações extensas
- **Falso senso de segurança**: Pode mascarar problemas fundamentais de qualidade

**🎯 Quando usar Log de Erros**:
- Operações ETL onde qualidade de dados é incerta
- Sistemas que processam dados de múltiplas fontes externas
- Ambientes onde auditoria de falhas é requisito regulatório
- Cargas críticas onde recovery precisa ser preciso e rastreável
- Sistemas onde análise de padrões de erro agrega valor

**⚠️ Quando NÃO usar Log de Erros**:
- Operações simples onde falhas devem interromper o processo
- Sistemas com performance crítica onde overhead não é aceitável
- Cenários onde dados com erro devem ser rejeitados completamente
- Ambientes com recursos de storage limitados

**💡 Estratégias de Error Handling por Cenário**:

- **OLTP (Transacional)**: Falhas devem gerar exceções para rollback imediato
- **ETL (Batch)**: Log erros, continue processando, reporte sumário no final  
- **APIs públicas**: Valide primeiro, trate erros graciosamente, retorne códigos apropriados
- **Data Migration**: Stop on first error, correção manual, restart do ponto de falha
- **Real-time streaming**: Dead letter queue para registros problemáticos

### 8. Padrões Avançados do Sistema MusiStream

Esta seção demonstra aplicações práticas e avançadas dos conceitos apresentados, contextualizando-os em cenários reais de um sistema de streaming de música. Estes padrões ilustram como combinar múltiplas técnicas para resolver problemas complexos de negócio.

#### 8.1 Importação de Catálogo Musical

**Conceito**: Implementar importação completa de catálogo musical de parceiros externos, demonstrando INSERT ALL condicional e gestão de relacionamentos complexos.

```sql
-- Importar catálogo completo de um novo selo
INSERT ALL
    -- Inserir gravadora se não existir
    WHEN gravadora_id IS NOT NULL THEN
        INTO gravadora (id_gravadora, nome_gravadora, pais_origem)
        VALUES (gravadora_id, gravadora_nome, gravadora_pais)
    -- Inserir artista
    WHEN 1=1 THEN
        INTO artista (id_artista, nome_artista, id_gravadora, pais_origem)
        VALUES (artista_id, artista_nome, gravadora_id, artista_pais)
    -- Inserir álbum
    WHEN 1=1 THEN
        INTO album (id_album, titulo, id_artista, ano_lancamento)
        VALUES (album_id, album_titulo, artista_id, album_ano)
SELECT DISTINCT
    g.id_gravadora as gravadora_id,
    g.nome_gravadora as gravadora_nome,
    g.pais_origem as gravadora_pais,
    a.id_artista as artista_id,
    a.nome_artista as artista_nome,
    a.pais_origem as artista_pais,
    al.id_album as album_id,
    al.titulo as album_titulo,
    al.ano_lancamento as album_ano
FROM staging_catalogo_musical scm
JOIN gravadora g ON scm.gravadora_codigo = g.codigo_externo
JOIN artista a ON scm.artista_codigo = a.codigo_externo
JOIN album al ON scm.album_codigo = al.codigo_externo;
```

**✅ Vantagens desta Abordagem**:
- **Atomicidade completa**: Toda hierarquia (gravadora→artista→álbum) é inserida atomicamente
- **Eficiência**: Uma única operação para inserir dados relacionados
- **Integridade referencial**: Garante que relacionamentos sejam criados corretamente
- **Conditional logic**: INSERT ALL permite lógica condicional sofisticada

**❌ Desvantagens desta Abordagem**:
- **Específico do Oracle**: Não é portável para outros SGBDs
- **Debugging complexo**: Difícil rastrear problemas em operações condicionais
- **All-or-nothing**: Falha em uma parte compromete toda a importação
- **Dependency management**: Requer gestão cuidadosa de dependências e ordem

**🎯 Quando usar este Padrão**:
- Integração com sistemas de fornecedores de conteúdo
- Migração inicial de grandes catálogos musicais
- Sincronização periódica com distribuidoras
- Operações onde consistência é mais importante que performance

**⚠️ Quando NÃO usar este Padrão**:
- Importações incrementais e frequentes (preferir upsert)
- Sistemas que requerem portabilidade entre SGBDs
- Cenários onde falha parcial é aceitável
- Ambientes onde cada entidade precisa de validação específica

#### 8.2 Geração de Dados de Teste

**Conceito**: Criar dados sintéticos realistas para teste e desenvolvimento, demonstrando uso de funções aleatórias e lógica de negócio complexa.

```sql
-- Gerar histórico de reprodução realista
INSERT INTO historico_reproducao (id_historico, id_usuario, id_musica, data_reproducao, duracao_ouvida)
SELECT 
    seq_historico.NEXTVAL,
    u.id_usuario,
    m.id_musica,
    SYSDATE - DBMS_RANDOM.VALUE(1, 365), -- Data aleatória no último ano
    CASE 
        WHEN DBMS_RANDOM.VALUE < 0.3 THEN ROUND(m.duracao * 0.2) -- 30% ouvem só 20%
        WHEN DBMS_RANDOM.VALUE < 0.7 THEN ROUND(m.duracao * 0.8) -- 40% ouvem 80%
        ELSE m.duracao -- 30% ouvem completo
    END
FROM (
    SELECT id_usuario FROM usuario ORDER BY DBMS_RANDOM.VALUE
) u,
(
    SELECT id_musica, duracao FROM musica ORDER BY DBMS_RANDOM.VALUE
) m
WHERE ROWNUM <= 100000; -- Gerar 100k reproduções
```

**✅ Vantagens da Geração de Dados de Teste**:
- **Realismo**: Simula padrões reais de comportamento dos usuários
- **Volume**: Pode gerar grandes volumes rapidamente para testes de performance
- **Repetibilidade**: Dados são gerados de forma consistente para testes
- **Flexibilidade**: Facilmente modificável para diferentes cenários de teste

**❌ Desvantagens da Geração de Dados de Teste**:
- **Não reflete realidade**: Dados sintéticos podem não capturar nuances reais
- **GDPR/Compliance**: Pode criar problemas legais se misturado com dados reais
- **Overhead de manutenção**: Scripts precisam ser atualizados conforme modelo evolui
- **Falsa confiança**: Testes com dados sintéticos podem não detectar problemas reais

**🎯 Quando usar Geração de Dados de Teste**:
- Ambientes de desenvolvimento e teste
- Testes de performance e carga
- Demos e protótipos
- Treinamento de algoritmos de machine learning (com cuidados)
- Validação de funcionalidades antes de acesso a dados reais

**⚠️ Quando NÃO usar Geração de Dados de Teste**:
- Testes de integração com sistemas externos
- Validação final antes de produção
- Ambientes onde dados reais (anonimizados) estão disponíveis
- Sistemas onde compliance requer dados reais
- Análises que dependem de padrões específicos de comportamento

**💡 Boas Práticas para Padrões Avançados**:

1. **Documentation**: Documente claramente a lógica de negócio implementada
2. **Error handling**: Implemente tratamento de erro robusto para cenários complexos
3. **Performance testing**: Teste com volumes realistas antes de produção
4. **Rollback strategy**: Sempre tenha plano de rollback para operações complexas
5. **Monitoring**: Implemente logging e monitoramento para operações críticas
6. **Validation**: Crie queries de validação para verificar resultado das operações

## Exercícios Práticos

Consulte a pasta `exercicios` para atividades práticas que reforçam os conceitos apresentados.

## Perguntas e Respostas

### 1. Quando utilizar INSERT... SELECT vs. INSERT com VALUES múltiplos?

**Resposta**:
**INSERT... SELECT**: Para dados baseados em consultas
```sql
-- Copiar dados de uma tabela para outra
INSERT INTO playlist_rock (id_musica, titulo)
SELECT m.id_musica, m.titulo
FROM musica m
JOIN album a ON m.id_album = a.id_album
WHERE a.genero = 'Rock';
```
- **Vantagens**: Baseado em dados existentes, pode processar milhões de registros
- **Uso**: Migração, ETL, criação de visões materializadas

**INSERT com VALUES múltiplos**: Para dados conhecidos/fixos
```sql
INSERT INTO genero (id_genero, nome_genero)
VALUES (1, 'Rock'), (2, 'Pop'), (3, 'Jazz');
```
- **Vantagens**: Dados específicos, controle total
- **Uso**: Dados de configuração, cargas pequenas

### 2. Como implementar inserções condicionais eficientemente?

**Resposta**: Diferentes abordagens:

**Usando WHERE no INSERT... SELECT**:
```sql
-- Inserir apenas artistas que não existem
INSERT INTO artista_backup (id_artista, nome_artista)
SELECT id_artista, nome_artista 
FROM artista a
WHERE NOT EXISTS (
    SELECT 1 FROM artista_backup ab 
    WHERE ab.id_artista = a.id_artista
);
```

**Usando MERGE (quando disponível)**:
```sql
MERGE INTO usuario_stats us
USING (SELECT id_usuario, COUNT(*) as total_playlists FROM playlist GROUP BY id_usuario) p
ON (us.id_usuario = p.id_usuario)
WHEN MATCHED THEN UPDATE SET total_playlists = p.total_playlists
WHEN NOT MATCHED THEN INSERT (id_usuario, total_playlists) VALUES (p.id_usuario, p.total_playlists);
```

### 3. Qual a melhor estratégia para geração de IDs únicos?

**Resposta**: Depende do SGBD:

**Oracle - SEQUENCE**:
```sql
CREATE SEQUENCE seq_usuario START WITH 1 INCREMENT BY 1;
INSERT INTO usuario (id_usuario, nome) VALUES (seq_usuario.NEXTVAL, 'João');
```

**MySQL - AUTO_INCREMENT**:
```sql
CREATE TABLE usuario (
    id_usuario INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100)
);
INSERT INTO usuario (nome) VALUES ('João'); -- ID gerado automaticamente
```

**PostgreSQL - SERIAL ou IDENTITY**:
```sql
CREATE TABLE usuario (
    id_usuario SERIAL PRIMARY KEY,
    nome VARCHAR(100)
);
```

**UUID para sistemas distribuídos**:
```sql
INSERT INTO usuario (id_usuario, nome) VALUES (uuid_generate_v4(), 'João');
```

### 4. Como otimizar inserções em lote para grandes volumes?

**Resposta**: Técnicas de otimização:

**1. Batch size apropriado**:
```sql
-- Em vez de 1 milhão de INSERTs individuais
-- Use lotes de 1000-10000 registros
INSERT INTO historico_reproducao (id_usuario, id_musica, data_reproducao)
VALUES 
    (1, 100, CURRENT_TIMESTAMP),
    (1, 101, CURRENT_TIMESTAMP),
    -- ... até 1000 registros
```

**2. Desabilitar índices temporariamente**:
```sql
-- Para cargas muito grandes
DROP INDEX idx_historico_data;
-- Inserções em lote
CREATE INDEX idx_historico_data ON historico_reproducao(data_reproducao);
```

**3. Usar ferramentas específicas**: Oracle SQL*Loader, MySQL LOAD DATA, PostgreSQL COPY.

### 5. Como tratar conflitos de chave primária/única durante inserções?

**Resposta**: Estratégias por SGBD:

**MySQL - INSERT IGNORE**:
```sql
INSERT IGNORE INTO artista (id_artista, nome_artista)
VALUES (1, 'The Beatles'); -- Ignora se ID já existe
```

**MySQL - ON DUPLICATE KEY UPDATE**:
```sql
INSERT INTO usuario (id_usuario, nome, email)
VALUES (1, 'João Silva', 'joao@email.com')
ON DUPLICATE KEY UPDATE 
    nome = VALUES(nome),
    email = VALUES(email);
```

**PostgreSQL - ON CONFLICT**:
```sql
INSERT INTO artista (id_artista, nome_artista)
VALUES (1, 'The Beatles')
ON CONFLICT (id_artista) DO UPDATE SET
    nome_artista = EXCLUDED.nome_artista;
```

### 6. Qual o impacto de transações em inserções em lote?

**Resposta**: Considerações importantes:

**Transações grandes**:
```sql
BEGIN;
-- 100.000 inserções
COMMIT;
```
- **Vantagem**: Atomicidade total
- **Desvantagem**: Log de transação grande, locks prolongados

**Transações em lotes menores**:
```sql
-- Processar em lotes de 1000
FOR i IN 1..100 LOOP
    BEGIN;
    -- 1000 inserções
    COMMIT;
END LOOP;
```
- **Vantagem**: Menor pressão no log, recovery mais rápido
- **Desvantagem**: Não totalmente atômico

**Recomendação**: Lotes de 1.000-10.000 registros conforme volume e recursos.

### 7. Como validar integridade após inserções em lote?

**Resposta**: Verificações pós-inserção:

**1. Contagem de registros**:
```sql
-- Verificar se todos os registros foram inseridos
SELECT COUNT(*) FROM tabela_origem;
SELECT COUNT(*) FROM tabela_destino;
```

**2. Verificação de integridade referencial**:
```sql
-- Verificar FKs órfãs
SELECT COUNT(*) 
FROM album a 
LEFT JOIN artista ar ON a.id_artista = ar.id_artista
WHERE ar.id_artista IS NULL;
```

**3. Validação de regras de negócio**:
```sql
-- Verificar constraints CHECK
SELECT COUNT(*) FROM musica WHERE duracao <= 0;
```

**4. Análise de estatísticas**:
```sql
-- Verificar distribuição de dados
SELECT genero, COUNT(*) FROM album GROUP BY genero;
```

## 🎓 Guia Prático de Execução no Oracle SQL Developer

### Passo a Passo Completo

Este guia orienta a execução do script `insert_avancado.sql` no Oracle SQL Developer.

#### 1. Preparação Inicial

**1.1. Conectar ao Banco de Dados**
- Abra o Oracle SQL Developer
- Crie ou selecione uma conexão ativa
- Teste a conexão: `SELECT USER FROM dual;`

**1.2. Habilitar Saída de Mensagens**
```sql
SET SERVEROUTPUT ON SIZE UNLIMITED;
```

**1.3. Verificar Estrutura Base**
```sql
-- Verificar se tabelas existem
SELECT COUNT(*) as total_tabelas
FROM user_tables
WHERE table_name IN ('GENERO', 'ARTISTA', 'ALBUM', 'MUSICA', 
                     'USUARIO', 'PLAYLIST', 'HISTORICO_REPRODUCAO');
-- Resultado esperado: 7 ou mais
```

#### 2. Executando o Script por Seções

O script `insert_avancado.sql` está dividido em 13 seções numeradas. Execute cada seção individualmente para melhor compreensão:

**Seção 1: Criação de Sequences**
```sql
-- Selecione apenas os comandos CREATE SEQUENCE da Seção 1
-- Pressione F5 (Run Script)
```

**Seção 2: Inserção Múltipla com VALUES**
```sql
-- Selecione os comandos INSERT INTO genero e INSERT INTO artista
-- Pressione Ctrl+Enter para cada INSERT ou F5 para executar ambos
```

**Seção 3: INSERT ALL**
```sql
-- Selecione o bloco INSERT ALL completo
-- Pressione F5 (Run Script)
-- Note: Pode gerar erro de integridade referencial, use versão alternativa comentada
```

**Seções 4-10: INSERT... SELECT, MERGE, etc.**
```sql
-- Para cada seção:
-- 1. Leia o comentário explicativo
-- 2. Selecione o comando SQL
-- 3. Execute com Ctrl+Enter (comandos simples) ou F5 (scripts)
```

**Seção 11: Tratamento de Erros (PL/SQL)**
```sql
-- IMPORTANTE: Este é um bloco PL/SQL
-- 1. Selecione TODO o bloco de DECLARE até END; incluindo o /
-- 2. Pressione F5 (Run Script) - NÃO use Ctrl+Enter
-- 3. Veja as mensagens no painel "Script Output"
```

**Seção 12: Verificação dos Dados**
```sql
-- Execute os SELECTs de verificação
-- Pressione Ctrl+Enter para cada SELECT
-- Analise os resultados
```

#### 3. Verificação de Sucesso

Após executar todo o script, verifique se os dados foram inseridos:

```sql
-- Contar registros nas tabelas principais
SELECT 'GENERO' as tabela, COUNT(*) as total FROM genero
UNION ALL
SELECT 'ARTISTA', COUNT(*) FROM artista
UNION ALL
SELECT 'ALBUM', COUNT(*) FROM album
UNION ALL
SELECT 'MUSICA', COUNT(*) FROM musica
UNION ALL
SELECT 'USUARIO', COUNT(*) FROM usuario WHERE id_usuario >= 1000
UNION ALL
SELECT 'PLAYLIST', COUNT(*) FROM playlist WHERE id_playlist >= 2000
UNION ALL
SELECT 'HISTORICO', COUNT(*) FROM historico_reproducao
UNION ALL
SELECT 'ESTATISTICAS', COUNT(*) FROM estatistica_artista
UNION ALL
SELECT 'RANKINGS', COUNT(*) FROM ranking_mensal_musica;
```

#### 4. Limpeza (Opcional)

Se desejar remover os dados de teste e reexecutar o script:

```sql
-- ATENÇÃO: Isso apaga dados de teste criados pelo script
-- Descomente e execute cada linha individualmente

DELETE FROM historico_reproducao WHERE id_historico >= 1;
DELETE FROM playlist_musica WHERE id_playlist >= 2000;
DELETE FROM playlist WHERE id_playlist >= 2000;
DELETE FROM usuario WHERE id_usuario >= 1000;
DELETE FROM ranking_mensal_musica;
DELETE FROM estatistica_artista;
DROP TABLE temp_artistas_nacionais;
DROP TABLE log_insercao_erros;
DROP SEQUENCE seq_usuario_teste;
DROP SEQUENCE seq_playlist_teste;
DROP SEQUENCE seq_historico_teste;

COMMIT;
```

### Troubleshooting - Problemas Comuns

#### Problema 1: "Table or view does not exist"
**Causa**: Estrutura base não foi criada  
**Solução**: Execute o script `01-estrutura-completa.sql` da pasta `streaming-de-musica/`

#### Problema 2: "Sequence does not exist"
**Causa**: Sequences não foram criadas  
**Solução**: Execute a Seção 1 do script `insert_avancado.sql` primeiro

#### Problema 3: Blocos PL/SQL não executam
**Causa**: Usado "Run Statement" ao invés de "Run Script"  
**Solução**: 
- Selecione TODO o bloco incluindo `DECLARE`, `BEGIN`, `END;` e `/`
- Pressione **F5** (não Ctrl+Enter)

#### Problema 4: DBMS_OUTPUT não mostra mensagens
**Causa**: SERVEROUTPUT não está habilitado  
**Solução**: Execute `SET SERVEROUTPUT ON SIZE UNLIMITED;` antes do bloco PL/SQL

#### Problema 5: Erros de chave duplicada
**Causa**: Script executado múltiplas vezes  
**Solução**: Execute a seção de limpeza (Seção 13) antes de reexecutar

#### Problema 6: "ORA-02292: integrity constraint violated - child record found"
**Causa**: Tentando deletar registros com dependências  
**Solução**: Delete na ordem correta (filhos antes dos pais):
1. historico_reproducao
2. playlist_musica
3. musica
4. album
5. artista

### Exercícios Práticos Guiados

#### Exercício 1: Inserção Múltipla Personalizada
Crie seu próprio INSERT múltiplo de artistas:

```sql
-- Insira 3 artistas da sua escolha
INSERT INTO artista (id_artista, nome_artista, biografia, data_inicio_carreira, pais_origem, ativo)
VALUES 
    (100, 'Seu Artista 1', 'Biografia...', DATE '2020-01-01', 'Brasil', 'S'),
    (101, 'Seu Artista 2', 'Biografia...', DATE '2019-01-01', 'Portugal', 'S'),
    (102, 'Seu Artista 3', 'Biografia...', DATE '2018-01-01', 'Angola', 'S');

-- Verificar inserção
SELECT id_artista, nome_artista, pais_origem 
FROM artista 
WHERE id_artista BETWEEN 100 AND 102;
```

#### Exercício 2: INSERT... SELECT com Filtros
Crie uma tabela de backup apenas de artistas brasileiros ativos:

```sql
-- Criar tabela de backup
CREATE TABLE artistas_brasileiros_ativos AS
SELECT * FROM artista WHERE 1=0;

-- Copiar dados com filtro
INSERT INTO artistas_brasileiros_ativos
SELECT * FROM artista
WHERE pais_origem = 'Brasil' 
  AND ativo = 'S';

-- Verificar resultado
SELECT COUNT(*) as total FROM artistas_brasileiros_ativos;
```

#### Exercício 3: Usando MERGE
Atualize ou insira estatísticas de reprodução:

```sql
-- Criar tabela de estatísticas se não existir
CREATE TABLE estatistica_reproducao (
    id_usuario INTEGER PRIMARY KEY,
    total_reproducoes INTEGER,
    ultima_atualizacao DATE
);

-- Usar MERGE para atualizar ou inserir
MERGE INTO estatistica_reproducao er
USING (
    SELECT id_usuario, COUNT(*) as total
    FROM historico_reproducao
    GROUP BY id_usuario
) src ON (er.id_usuario = src.id_usuario)
WHEN MATCHED THEN
    UPDATE SET 
        er.total_reproducoes = src.total,
        er.ultima_atualizacao = SYSDATE
WHEN NOT MATCHED THEN
    INSERT (id_usuario, total_reproducoes, ultima_atualizacao)
    VALUES (src.id_usuario, src.total, SYSDATE);

-- Verificar resultado
SELECT * FROM estatistica_reproducao ORDER BY total_reproducoes DESC;
```

## 📖 Referências Bibliográficas (Oracle-Específicas)

### Documentação Oficial Oracle
- **Oracle Corporation** (2023). *Oracle Database SQL Language Reference*. 
  - Capítulo sobre INSERT: [INSERT Statement](https://docs.oracle.com/en/database/oracle/oracle-database/19/sqlrf/INSERT.html)
  - Capítulo sobre MERGE: [MERGE Statement](https://docs.oracle.com/en/database/oracle/oracle-database/19/sqlrf/MERGE.html)
  - Capítulo sobre Sequences: [CREATE SEQUENCE](https://docs.oracle.com/en/database/oracle/oracle-database/19/sqlrf/CREATE-SEQUENCE.html)

- **Oracle Corporation** (2023). *Oracle SQL Developer User's Guide*.
  - Como usar SQL Developer: [Getting Started](https://docs.oracle.com/en/database/oracle/sql-developer/)

### Livros Recomendados
- **Kyte, T.** (2010). *Expert Oracle Database Architecture*. 2nd Edition. Apress. 
  - Capítulos 6-7: DML e Transações
  - Capítulo 9: Redo e Undo
  
- **Lewis, J.** (2006). *Cost-Based Oracle Fundamentals*. Apress. 
  - Capítulo 4: Bulk Operations
  - Capítulo 8: Otimização de DML

- **Feuerstein, S. & Pribyl, B.** (2014). *Oracle PL/SQL Programming*. 6th Edition. O'Reilly.
  - Capítulo 14: DML e Data Retrieval
  - Capítulo 15: Bulk Operations

### Tutoriais Online
- [Oracle Live SQL](https://livesql.oracle.com/) - Ambiente online para testar scripts
- [Oracle Learning Library](https://www.oracle.com/database/technologies/appdev/learning-library.html) - Tutoriais oficiais
- [AskTOM](https://asktom.oracle.com/) - Perguntas e respostas sobre Oracle

## 🔗 Recursos Adicionais

### Scripts Relacionados
- **Módulo 07**: Inserção de Dados (Parte I) - INSERT básico
- **Módulo 09**: Controle de Transações - COMMIT, ROLLBACK, SAVEPOINT
- **Módulo 03**: Interface SQL*Plus - Diferenças entre SQL*Plus e SQL Developer

### Arquivos deste Módulo
```
08-manipulacao-dados-inserindo-parte-ii/
├── README.md (este arquivo)
├── exemplos/
│   └── insert_avancado.sql (script principal com todos os exemplos)
└── exercicios/
    └── README.md (exercícios propostos)
```

## 🎯 Próximos Passos

No próximo módulo (**09 - Controle de Transações e Criação de Relatórios**), você aprenderá:
- Como controlar transações com **COMMIT** e **ROLLBACK**
- Uso de **SAVEPOINT** para rollback parcial
- Níveis de isolamento de transações
- Técnicas de criação de relatórios básicos
- Formatação de saída no SQL Developer

**Prepare-se executando:**
```sql
-- Prática recomendada: sempre fazer commit explícito
COMMIT;

-- Verificar transações pendentes
SELECT * FROM v$transaction;
```

---

## 📝 Notas Importantes

1. **Sempre faça backup** antes de executar scripts que modificam dados
2. **Use transações** - Teste com ROLLBACK antes do COMMIT final
3. **Valide os resultados** - Execute queries de verificação após cada operação
4. **Documente modificações** - Adicione comentários nos seus scripts
5. **Teste em ambiente de desenvolvimento** primeiro, nunca direto em produção

---

*Material desenvolvido para o curso de Banco de Dados - Linguagem SQL*  
*Compatível com Oracle Database 11g, 12c, 18c, 19c, 21c*  
*Otimizado para Oracle SQL Developer 4.x, 19.x, 20.x, 21.x*