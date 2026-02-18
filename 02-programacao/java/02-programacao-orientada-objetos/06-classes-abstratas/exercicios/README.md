# Exercícios - Classes Abstratas

Este arquivo contém exercícios práticos para consolidar o aprendizado sobre classes abstratas em Java.

## 📝 Exercício 1: Sistema de Veículos de Transporte

**Objetivo**: Implementar um sistema de gerenciamento de veículos de transporte com diferentes tipos.

### Requisitos:

1. Crie uma classe abstrata `VeiculoTransporte` com:
   - Atributos: `placa`, `capacidadePassageiros`, `quilometragem`
   - Métodos abstratos: `calcularTarifa()`, `exibirInformacoes()`
   - Métodos concretos: `adicionarQuilometragem()`, `getPlaca()`

2. Crie as seguintes subclasses:
   - `Onibus`: tarifa baseada na distância (R$ 4,50 base + R$ 0,10 por km)
   - `Taxi`: tarifa com bandeirada (R$ 5,00) + R$ 2,50 por km
   - `VanEscolar`: tarifa mensal fixa (R$ 350,00)

3. Crie uma classe `TesteVeiculosTransporte` que:
   - Cria 3 veículos de tipos diferentes
   - Simula viagens adicionando quilometragem
   - Calcula e exibe as tarifas de cada veículo
   - Mostra total de quilometragem de todos os veículos

### Diagrama de Classes:

```
             VeiculoTransporte (abstract)
             ├── placa: String
             ├── capacidadePassageiros: int
             ├── quilometragem: double
             ├── + calcularTarifa(): double (abstract)
             ├── + exibirInformacoes(): void (abstract)
             └── + adicionarQuilometragem(double): void
                        │
        ┌───────────────┼───────────────┐
        │               │               │
     Onibus         Taxi          VanEscolar
```

### Dicas:
- Use o Template Method Pattern
- Implemente validações nos métodos
- Formate os valores monetários com 2 casas decimais

---

## 📝 Exercício 2: Sistema de Produtos de E-commerce

**Objetivo**: Criar um sistema de produtos com diferentes categorias e formas de cálculo de preço.

### Requisitos:

1. Crie uma classe abstrata `Produto` com:
   - Atributos: `codigo`, `nome`, `precoBase`, `estoque`
   - Métodos abstratos: `calcularPrecoFinal()`, `aplicarDesconto()`
   - Métodos concretos: `exibirDetalhes()`, `adicionarEstoque()`, `removerEstoque()`

2. Crie as seguintes subclasses:
   - `ProdutoEletronico`: 
     * Atributos: `garantiaMeses`, `voltagem`
     * Preço final = preço base + taxa de importação (15%)
     * Desconto máximo: 20%
   
   - `ProdutoAlimenticio`:
     * Atributos: `dataValidade`, `perecivel`
     * Preço final = preço base (sem taxa)
     * Desconto especial se próximo da validade (30%)
   
   - `ProdutoVestuario`:
     * Atributos: `tamanho`, `material`
     * Preço final = preço base + custo de embalagem (R$ 5,00)
     * Desconto sazonal variável (10-40%)

3. Crie uma classe `TesteEcommerce` que:
   - Cria um catálogo com 6 produtos (2 de cada tipo)
   - Aplica descontos em alguns produtos
   - Simula vendas removendo do estoque
   - Calcula o valor total do catálogo

### Dicas:
- Implemente verificação de estoque antes de remover
- Use LocalDate para trabalhar com datas de validade
- Crie constantes para as taxas e valores fixos

---

## 📝 Exercício 3: Sistema de Investimentos Financeiros

**Objetivo**: Modelar diferentes tipos de investimentos com cálculos específicos de rendimento.

### Requisitos:

1. Crie uma classe abstrata `Investimento` com:
   - Atributos: `valorInicial`, `dataInicio`, `prazoMeses`
   - Métodos abstratos: `calcularRendimento()`, `calcularValorFinal()`
   - Métodos concretos: `exibirResumo()`, `getTempoDecorrido()`

2. Crie as seguintes subclasses:
   - `Poupanca`: 
     * Rendimento: 0,5% ao mês
     * Sem taxas
   
   - `CDB`:
     * Rendimento: 1,2% ao mês
     * Taxa de administração: 0,3% ao mês
   
   - `AcoesBolsa`:
     * Rendimento variável simulado (entre -5% e +10% ao mês)
     * Taxa de corretagem: R$ 10,00 por operação

3. Crie uma classe `TesteInvestimentos` que:
   - Cria uma carteira com 5 investimentos
   - Simula 12 meses de rendimentos
   - Mostra evolução mensal de cada investimento
   - Calcula rentabilidade total da carteira

### Desafio Extra:
- Implemente um método que compara dois investimentos
- Crie um relatório visual (ASCII art) da evolução

---

## 📝 Exercício 4: Sistema de Notificações

**Objetivo**: Implementar um sistema de envio de notificações por diferentes canais.

### Requisitos:

1. Crie uma classe abstrata `Notificacao` com:
   - Atributos: `destinatario`, `mensagem`, `dataHora`, `prioridade`
   - Métodos abstratos: `enviar()`, `validar()`
   - Métodos concretos: `preparar()`, `registrarLog()`

2. Crie as seguintes subclasses:
   - `NotificacaoEmail`:
     * Atributos: `assunto`, `anexos`
     * Validação: verifica formato do email
   
   - `NotificacaoSMS`:
     * Atributos: `numeroTelefone`, `operadora`
     * Validação: verifica formato do telefone e limite de caracteres (160)
   
   - `NotificacaoPush`:
     * Atributos: `appId`, `icone`
     * Validação: verifica se app está instalado

3. Implemente o Template Method Pattern no método `processar()`:
   ```
   processar() {
       validar()
       preparar()
       enviar()
       registrarLog()
   }
   ```

4. Crie uma classe `TesteNotificacoes` que:
   - Envia notificações por todos os canais
   - Simula falhas de validação
   - Mostra histórico de notificações enviadas

---

## 📝 Exercício 5: Sistema de Relatórios (Desafio Avançado)

**Objetivo**: Criar um sistema flexível de geração de relatórios em diferentes formatos.

### Requisitos:

1. Crie uma classe abstrata `Relatorio` com:
   - Atributos: `titulo`, `autor`, `dataGeracao`, `dados`
   - Métodos abstratos: `gerarCabecalho()`, `gerarCorpo()`, `gerarRodape()`
   - Método concreto com Template Method: `gerar()`

2. Crie as seguintes subclasses:
   - `RelatorioHTML`: gera HTML formatado
   - `RelatorioPDF`: simula geração de PDF
   - `RelatorioCSV`: gera arquivo CSV
   - `RelatorioJSON`: gera JSON estruturado

3. Implemente:
   - Formatação específica para cada tipo
   - Validação de dados antes de gerar
   - Cálculo de estatísticas (total, média, máximo, mínimo)

4. Crie uma classe `TesteSistemaRelatorios` que:
   - Gera o mesmo relatório em 4 formatos
   - Compara o tamanho/estrutura de cada formato
   - Simula salvamento em arquivo

### Desafio:
- Adicione um novo formato `RelatorioMarkdown`
- Implemente exportação real para arquivo
- Adicione suporte a gráficos simples (ASCII art)

---

## 🎯 Critérios de Avaliação

Para cada exercício, avalie:

- ✅ **Uso correto de abstração** (30 pontos)
  - Classes abstratas bem definidas
  - Métodos abstratos apropriados
  - Hierarquia coerente

- ✅ **Implementação das subclasses** (30 pontos)
  - Implementação correta dos métodos abstratos
  - Funcionalidade específica de cada tipo
  - Validações e tratamento de erros

- ✅ **Uso de Template Method** (20 pontos)
  - Fluxo de execução consistente
  - Métodos finais quando apropriado
  - Reutilização de código

- ✅ **Qualidade do código** (20 pontos)
  - Nomenclatura clara
  - Comentários apropriados
  - Organização e formatação

---

## 💡 Dicas Gerais

1. **Comece pelo diagrama**: Desenhe antes de codificar
2. **Teste incrementalmente**: Teste cada classe antes de avançar
3. **Use polimorfismo**: Aproveite arrays/listas de tipos abstratos
4. **Documente**: Adicione JavaDoc às classes e métodos
5. **Valide entrada**: Sempre valide dados antes de processar

---

## 📚 Recursos Adicionais

- Revisite o README.md da seção para conceitos
- Consulte os exemplos em `exemplos/`
- Use os diagramas como referência
- Pratique refatoração: transforme código duplicado em métodos abstratos

---

**Boa sorte com os exercícios! 🚀**
