# Exemplo Completo: Sistema de E-commerce

## 📋 Visão Geral

Este exemplo demonstra um processo completo de modelagem de dados para um sistema de e-commerce, passando pelas três fases fundamentais:

1. **Modelagem Conceitual** - Identificação de entidades e relacionamentos
2. **Modelagem Lógica** - Definição da estrutura das tabelas
3. **Modelagem Física** - Implementação em SQL com dados de exemplo

## 🏪 Cenário de Negócio

A **TechStore** é uma loja virtual especializada em produtos eletrônicos que precisa de um sistema completo para gerenciar:

- **Clientes** com múltiplos endereços e formas de pagamento
- **Catálogo de produtos** organizados em categorias hierárquicas
- **Carrinho de compras** e processamento de pedidos
- **Sistema de avaliações** de produtos pelos clientes
- **Controle de estoque** e gestão de fornecedores
- **Cupons de desconto** e promoções

## 📁 Estrutura dos Arquivos

```
exemplo-ecommerce/
├── README.md                     # Este arquivo
├── modelagem-conceitual.md       # Análise de requisitos e modelo conceitual
├── modelagem-logica.md          # Estrutura lógica das tabelas
├── 01-estrutura-completa.sql    # Criação de todas as tabelas
├── 02-inserir-dados.sql         # Inserção de dados de exemplo
├── 03-consultas-basicas.sql     # Consultas fundamentais
├── 04-consultas-avancadas.sql   # Consultas complexas e relatórios
└── validar-scripts.sh           # Script de validação
```

## 🚀 Como Usar Este Exemplo

### 1. **Estudar a Modelagem**
```bash
# Leia primeiro a documentação conceitual
cat modelagem-conceitual.md

# Em seguida, a modelagem lógica
cat modelagem-logica.md
```

### 2. **Executar os Scripts SQL**
```bash
# Criar todas as tabelas
mysql -u usuario -p < 01-estrutura-completa.sql

# Inserir dados de exemplo
mysql -u usuario -p < 02-inserir-dados.sql

# Testar consultas básicas
mysql -u usuario -p < 03-consultas-basicas.sql

# Executar consultas avançadas
mysql -u usuario -p < 04-consultas-avancadas.sql
```

### 3. **Validar a Implementação**
```bash
# Executar todos os testes
bash validar-scripts.sh
```

## 🎯 Objetivos de Aprendizado

Ao concluir este exemplo, você será capaz de:

- ✅ **Identificar entidades** e relacionamentos em um cenário real
- ✅ **Aplicar normalização** até a 3ª Forma Normal
- ✅ **Definir constraints** para garantir integridade dos dados
- ✅ **Criar índices** para otimizar consultas
- ✅ **Implementar relacionamentos** N:M com tabelas associativas
- ✅ **Escrever consultas complexas** com JOINs e subconsultas
- ✅ **Gerar relatórios** de vendas e análise de dados

## 📊 Principais Conceitos Demonstrados

### Modelagem
- Análise de requisitos funcionais e não-funcionais
- Identificação de entidades, atributos e relacionamentos
- Normalização de dados (1FN, 2FN, 3FN)
- Definição de chaves primárias e estrangeiras

### Implementação SQL
- CREATE TABLE com constraints
- Relacionamentos 1:1, 1:N e N:M
- Índices para otimização de consultas
- Triggers para regras de negócio
- Views para simplificar consultas complexas

### Consultas Avançadas
- JOINs entre múltiplas tabelas
- Subconsultas correlacionadas
- Funções de agregação
- Relatórios com GROUP BY e HAVING
- Análise de dados de vendas

## 💡 Dicas para Estudo

1. **Comece pelo conceitual**: Entenda o problema de negócio antes de partir para a implementação
2. **Analise as tabelas**: Observe como os relacionamentos conceituais se transformam em foreign keys
3. **Execute passo a passo**: Não execute todos os scripts de uma vez, vá por etapas
4. **Modifique os dados**: Experimente inserir seus próprios dados de teste
5. **Crie suas consultas**: Baseado nos exemplos, crie suas próprias consultas

## 🔗 Recursos Relacionados

- [Módulo 01 - Introdução à Modelagem](../01-introducao-modelagem-dados/)
- [Módulo 04 - Estrutura de Tabelas](../04-trabalhando-estrutura-tabelas/)
- [Módulo 05 - Relacionamentos](../05-estrutura-tabelas-regras-relacionamentos/)
- [Exemplo Streaming de Música](../streaming-de-musica/)
- [Exemplo Barbearia](../exemplo-barbearia/)

---

## 📝 Exercícios Propostos

Após estudar este exemplo, tente implementar estas funcionalidades:

1. **Sistema de Wishlist**: Permitir que clientes salvem produtos favoritos
2. **Histórico de Preços**: Registrar mudanças de preços dos produtos
3. **Sistema de Frete**: Calcular frete baseado no CEP e peso dos produtos
4. **Programa de Fidelidade**: Pontos por compras e recompensas
5. **Relatório de Abandono**: Identificar carrinhos abandonados

**Boa sorte com seus estudos!** 🚀