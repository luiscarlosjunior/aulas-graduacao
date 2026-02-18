# Exercícios - Módulo 01: Introdução à Modelagem de Dados

## Exercício 1: Identificação de Entidades e Atributos

**Cenário**: Você foi contratado para projetar um banco de dados para uma biblioteca universitária.

**Questões**:
1. Identifique pelo menos 5 entidades principais para este sistema
2. Para cada entidade, liste pelo menos 4 atributos
3. Identifique qual seria a chave primária de cada entidade
4. Classifique os atributos como simples/compostos e monovalorados/multivalorados

**Entidades sugeridas para reflexão**: Livro, Autor, Usuário, Empréstimo, Categoria

---

## Exercício 2: Relacionamentos

**Baseado no sistema da biblioteca do Exercício 1**:

1. Identifique os relacionamentos entre as entidades
2. Determine a cardinalidade de cada relacionamento (1:1, 1:N, N:M)
3. Justifique suas escolhas de cardinalidade
4. Desenhe um diagrama ER simplificado

**Exemplo de relacionamento para análise**:
- Um AUTOR pode escrever vários LIVROS?
- Um LIVRO pode ter vários AUTORES?
- Qual a cardinalidade entre AUTOR e LIVRO?

---

## Exercício 3: Normalização - Parte I

**Cenário**: Você recebeu a seguinte tabela não normalizada de um sistema de vendas:

```
PEDIDO_CLIENTE
--------------
num_pedido | data_pedido | nome_cliente | telefone_cliente | endereco_cliente | produto | quantidade | preco_unitario
1          | 2023-01-15  | João Silva   | 11999887766     | Rua A, 123       | Mouse   | 2          | 25.00
1          | 2023-01-15  | João Silva   | 11999887766     | Rua A, 123       | Teclado | 1          | 80.00
2          | 2023-01-16  | Maria Santos | 11888776655     | Rua B, 456       | Monitor | 1          | 350.00
```

**Questões**:
1. Esta tabela está na 1FN? Por quê?
2. Identifique os problemas de redundância
3. Proponha uma decomposição em tabelas normalizadas
4. Desenhe o esquema das novas tabelas com suas chaves

---

## Exercício 4: Sistema MusiStream - Expansão

**Baseado no sistema MusiStream apresentado no módulo**:

1. **Adicione 3 novas entidades** que você considera importantes para um sistema de streaming musical
2. **Para cada nova entidade**:
   - Liste seus atributos
   - Defina a chave primária
   - Identifique relacionamentos com entidades existentes
3. **Justifique** por que essas entidades são necessárias

**Sugestões para reflexão**: Playlists, Gêneros Musicais, Histórico de Reprodução, Assinaturas, Avaliações

---

## Exercício 5: Análise de Integridade

**Cenário**: No sistema MusiStream, considere as seguintes situações:

1. **Situação A**: Um usuário tenta deletar sua conta, mas possui playlists criadas
2. **Situação B**: Um álbum é removido do sistema, mas suas músicas ainda existem
3. **Situação C**: Um artista muda de nome artístico

**Questões**:
1. Quais problemas de integridade referencial podem ocorrer?
2. Como você resolveria cada situação?
3. Que tipos de constraints seriam necessárias?
4. Proponha políticas para CASCADE, RESTRICT ou SET NULL

---

## Exercício 6: Estudo de Caso - E-commerce

**Desafio Avançado**: Projete um modelo conceitual para um sistema de e-commerce simples.

**Requisitos mínimos**:
- Produtos organizados em categorias
- Clientes com histórico de compras
- Carrinho de compras
- Sistema de avaliações de produtos
- Controle de estoque

**Deliverables**:
1. Lista de entidades com atributos
2. Diagrama ER
3. Descrição dos relacionamentos
4. Análise de normalização até 3FN
5. Identificação de possíveis problemas de integridade

---

## Exercício 7: Reflexão e Pesquisa

**Questões conceituais**:

1. **Pesquise e explique** a diferença entre:
   - Modelo conceitual vs. modelo lógico vs. modelo físico
   - OLTP vs. OLAP
   - Banco relacional vs. NoSQL

2. **Análise crítica**: Quais são as limitações do modelo relacional para:
   - Dados não estruturados (ex: posts em redes sociais)
   - Relacionamentos complexos (ex: redes sociais)
   - Big Data e análise em tempo real

3. **Casos de uso**: Para cada cenário abaixo, justifique se usaria um banco relacional ou NoSQL:
   - Sistema bancário
   - Rede social
   - Catálogo de produtos
   - Analytics em tempo real
   - Sistema de arquivos

---

## Dicas para Resolução

### Para os Exercícios 1-3:
- Comece identificando as entidades óbvias
- Pense nas ações que o sistema deve realizar
- Considere quem são os usuários e o que eles fazem

### Para os Exercícios 4-6:
- Analise sistemas similares que você conhece
- Pense em casos extremos e exceções
- Considere requisitos futuros de expansão

### Para o Exercício 7:
- Use recursos acadêmicos confiáveis
- Compare diferentes perspectivas de autores
- Relacione teoria com exemplos práticos

---

**Critérios de Avaliação**:
- Correção conceitual
- Completude das respostas
- Justificativas claras
- Criatividade nas soluções
- Aplicação prática dos conceitos

**Tempo Estimado**: 4-6 horas para conclusão completa