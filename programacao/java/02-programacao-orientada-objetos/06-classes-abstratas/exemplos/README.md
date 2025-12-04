# Exemplos Completos - Classes Abstratas

Este diretório contém quatro sistemas completos implementados com classes abstratas, demonstrando conceitos avançados de POO.

## 🎯 Sistema 1: Gerenciamento de Funcionários

### Arquivos:
- `Funcionario.java` - Classe abstrata base
- `Gerente.java` - Funcionário com bônus e níveis
- `Vendedor.java` - Funcionário com comissão sobre vendas
- `Desenvolvedor.java` - Funcionário com bônus por projeto
- `TesteSistemaFuncionarios.java` - Classe de teste

### Diagrama de Classes:

![Sistema de Funcionários](../img/sistema-funcionarios.png)

### Como executar:

```bash
# Compilar todos os arquivos
javac Funcionario.java Gerente.java Vendedor.java Desenvolvedor.java TesteSistemaFuncionarios.java

# Executar o teste
java TesteSistemaFuncionarios
```

### Conceitos Demonstrados:

1. **Classe Abstrata Base** (`Funcionario`)
   - Define estrutura comum para todos os funcionários
   - Atributos protegidos: `nome`, `salarioBase`, `departamento`
   - Métodos abstratos: `calcularSalario()`, `exibirDetalhes()`
   - Método concreto: `exibirInformacoes()` - Template Method

2. **Polimorfismo**
   - Array de `Funcionario[]` contém diferentes tipos
   - Cada tipo calcula salário de forma específica
   - Todos compartilham comportamento comum

3. **Encapsulamento**
   - Atributos protegidos na classe base
   - Atributos privados nas subclasses
   - Getters públicos para acesso controlado

4. **Template Method Pattern**
   - `exibirInformacoes()` define o fluxo geral
   - Chama métodos abstratos implementados pelas subclasses
   - Garante estrutura consistente na apresentação

### Saída Esperada:

```
╔══════════════════════════════════════════════════════════╗
║      SISTEMA DE GESTÃO DE FUNCIONÁRIOS - EMPRESA XYZ     ║
╚══════════════════════════════════════════════════════════╝

=== REGISTRANDO ATIVIDADES DO MÊS ===
[...]
=== FOLHA DE PAGAMENTO DO MÊS ===
[...]
╔══════════════════════════════════════════════════════════╗
║                    RESUMO FINANCEIRO                     ║
[...]
```

---

## 🎮 Sistema 2: Gerenciamento de Jogos

### Arquivos:
- `Jogo.java` - Classe abstrata base com Template Method
- `JogoCartas.java` - Jogo de cartas com baralho e rodadas
- `JogoTabuleiro.java` - Jogo de tabuleiro com dados e casas
- `JogoEletronico.java` - Jogo eletrônico com níveis e pontuação
- `TesteSistemaJogos.java` - Classe de teste

### Diagrama de Classes:

![Sistema de Jogos](../img/sistema-jogos.png)

### Como executar:

```bash
# Compilar todos os arquivos
javac Jogo.java JogoCartas.java JogoTabuleiro.java JogoEletronico.java TesteSistemaJogos.java

# Executar o teste
java TesteSistemaJogos
```

### Conceitos Demonstrados:

1. **Template Method Pattern (Avançado)**
   - Método `executarJogo()` é `final` - não pode ser sobrescrito
   - Define fluxo fixo: iniciar → jogar → terminar
   - Cada subclasse implementa as etapas específicas
   - Garante que todos os jogos sigam o mesmo processo

2. **Abstração de Comportamento**
   - Comportamentos comuns na classe base
   - Comportamentos específicos nas subclasses
   - Interface consistente para todos os tipos

3. **Estado Gerenciado**
   - Atributo `emAndamento` controlado pelo template method
   - Evita inconsistências no fluxo de execução

4. **Flexibilidade e Extensibilidade**
   - Fácil adicionar novos tipos de jogos
   - Basta implementar os 3 métodos abstratos
   - O fluxo de execução já está definido

### Saída Esperada:

```
╔════════════════════════════════════════════════════════════╗
║          🎮 PLATAFORMA DE JOGOS - GAME CENTER 🎮          ║
║                  Sistema de Gerenciamento                  ║
╚════════════════════════════════════════════════════════════╝
[...]
```

---

## 🏦 Sistema 3: Sistema Bancário

### Arquivos:
- `ContaBancaria.java` - Classe abstrata base para todas as contas
- `ContaCorrente.java` - Conta com limite especial e taxas
- `ContaPoupanca.java` - Conta com rendimento e saques gratuitos
- `ContaInvestimento.java` - Conta com alto rendimento e saldo mínimo
- `TesteSistemaBancario.java` - Classe de teste completa

### Como executar:

```bash
# Compilar todos os arquivos
javac ContaBancaria.java ContaCorrente.java ContaPoupanca.java ContaInvestimento.java TesteSistemaBancario.java

# Executar o teste
java TesteSistemaBancario
```

### Conceitos Demonstrados:

1. **Métodos Concretos Compartilhados**
   - `depositar()`, `sacar()`, `transferir()` - comuns a todas contas
   - Código reutilizado evita duplicação
   - Lógica centralizada facilita manutenção

2. **Métodos Abstratos com Lógica Específica**
   - `calcularRendimento()` - cada conta calcula diferente
   - `calcularTaxaSaque()` - taxas variam por tipo
   - `getTipoConta()` - identificação específica

3. **Template Method Pattern**
   - `exibirExtrato()` usa métodos abstratos
   - Fluxo padronizado de exibição
   - Informações específicas delegadas às subclasses

4. **Sobrescrita Inteligente**
   - `ContaCorrente` sobrescreve `sacar()` para incluir limite especial
   - Mantém compatibilidade polimórfica
   - Adiciona comportamento sem quebrar a base

5. **Polimorfismo Financeiro**
   - Array `ContaBancaria[]` gerencia diferentes tipos
   - Operações uniformes (depositar, transferir)
   - Comportamentos específicos (rendimentos, taxas)

### Saída Esperada:

```
╔════════════════════════════════════════════════════╗
║       SISTEMA BANCÁRIO - CLASSES ABSTRATAS         ║
║         Demonstração de POO com Java               ║
╚════════════════════════════════════════════════════╝

═══════════════════════════════════════════════════
  PARTE 1: EXIBINDO EXTRATOS INICIAIS
[...]
  PARTE 2: OPERAÇÕES BANCÁRIAS
[...]
  PARTE 3: TRANSFERÊNCIAS ENTRE CONTAS
[...]
```

### Por que Classes Abstratas aqui?

✅ **Código Compartilhado**: Operações básicas (depositar, sacar, transferir) são idênticas  
✅ **Estado Comum**: Todas contas têm saldo, titular, número de conta  
✅ **Variação Controlada**: Cada tipo calcula rendimento e taxas diferente  
✅ **Hierarquia Natural**: ContaCorrente "É UMA" ContaBancaria  
✅ **Construtor com Validação**: Inicialização comum garantida

---

## 📄 Sistema 4: Sistema de Processamento de Documentos

### Arquivos:
- `ProcessadorDocumento.java` - Classe abstrata com Template Method
- `ProcessadorPDF.java` - Processa PDFs (páginas, metadados, texto)
- `ProcessadorExcel.java` - Processa planilhas (abas, fórmulas, células)
- `ProcessadorImagem.java` - Processa imagens (dimensões, qualidade, EXIF)
- `TesteProcessadorDocumentos.java` - Demonstração completa

### Como executar:

```bash
# Compilar todos os arquivos
javac ProcessadorDocumento.java ProcessadorPDF.java ProcessadorExcel.java ProcessadorImagem.java TesteProcessadorDocumentos.java

# Executar o teste
java TesteProcessadorDocumentos
```

### Conceitos Demonstrados:

1. **Template Method Avançado**
   - Método `processar()` é `final` e define fluxo fixo
   - Garante sequência: validar → abrir → ler → processar → fechar
   - Impossível quebrar o fluxo em subclasses
   - Cada etapa pode ser implementada diferentemente

2. **Validação em Camadas**
   - Validação comum na classe abstrata
   - Validação específica em cada tipo
   - Falha rápida antes de processar

3. **Abstração de Complexidade**
   - Lógica de abertura/fechamento encapsulada
   - Subclasses implementam apenas operações específicas
   - Cliente usa interface simples (`processar()`)

4. **Polimorfismo de Exportação**
   - Cada tipo exporta para formatos diferentes
   - Método abstrato `exportar()` com implementações únicas
   - PDF → TXT/HTML, Excel → CSV/JSON, Imagem → PNG/WebP

5. **Factory Method Implícito**
   - Método auxiliar `criarProcessador()` cria tipo correto
   - Baseado na extensão do arquivo
   - Facilita processamento em lote

### Saída Esperada:

```
╔════════════════════════════════════════════════════════════╗
║   SISTEMA DE PROCESSAMENTO DE DOCUMENTOS                   ║
║        Template Method Pattern com Classes Abstratas       ║
╚════════════════════════════════════════════════════════════╝

[1/5] Validando documento...
[2/5] Abrindo arquivo...
[3/5] Lendo conteúdo...
[4/5] Processando conteúdo específico...
[5/5] Finalizando...
```

### Por que Classes Abstratas aqui?

✅ **Fluxo Padronizado**: Todos documentos seguem mesma sequência de processamento  
✅ **Código de Infraestrutura**: Validação e fechamento comuns  
✅ **Template Method Garantido**: Método `final` evita alterações no fluxo  
✅ **Extensibilidade**: Fácil adicionar novos tipos (Word, PowerPoint, etc.)  
✅ **Manutenção Centralizada**: Mudanças no fluxo afetam todos os tipos

---

## 🔍 Análise Comparativa dos Exemplos

### Sistema de Funcionários
- **Foco**: Cálculos específicos por tipo
- **Pattern**: Template Method simples
- **Variação**: Lógica de cálculo de salário
- **Uso**: Sistemas de RH, folha de pagamento

### Sistema de Jogos
- **Foco**: Fluxo de execução consistente
- **Pattern**: Template Method com método final
- **Variação**: Implementação das etapas do jogo
- **Uso**: Engines de jogos, simuladores

### Sistema Bancário
- **Foco**: Compartilhamento de operações financeiras
- **Pattern**: Métodos concretos + abstratos
- **Variação**: Cálculo de rendimento e taxas
- **Uso**: Sistemas financeiros, fintechs

### Sistema de Documentos
- **Foco**: Processamento padronizado com variações
- **Pattern**: Template Method complexo + Factory
- **Variação**: Formato e extração de dados específicos
- **Uso**: Sistemas DMS, conversores, processadores

---

## 💡 Lições Aprendidas

### Quando usar Classes Abstratas:

✅ **Use quando:**
- Há código comum a ser compartilhado
- Existe uma hierarquia natural "é-um"
- Precisa controlar o fluxo de execução
- Quer garantir que subclasses implementem certos métodos
- Tem estado (atributos) que devem ser compartilhados
- Precisa de construtor para inicialização comum

❌ **Evite quando:**
- Não há código comum (use interface)
- Precisa de múltipla herança (use interfaces)
- A hierarquia não é natural
- Subclasses são muito diferentes entre si
- Quer apenas definir contratos (use interface)

### Comparação dos 4 Sistemas:

| Sistema | Código Comum | Abstração | Template Method | Melhor Para |
|---------|-------------|-----------|-----------------|-------------|
| **Funcionários** | Baixo | Média | Simples | Cálculos específicos |
| **Jogos** | Médio | Alta | Complexo (final) | Fluxos fixos |
| **Bancário** | Alto | Média | Moderado | Operações compartilhadas |
| **Documentos** | Alto | Alta | Complexo (final) | Processamento padronizado |

### Boas Práticas Observadas:

1. **Método Template Final**
   ```java
   public final void executarJogo() {
       // Garante fluxo consistente
   }
   ```

2. **Atributos Protegidos**
   ```java
   protected String nome;
   protected int numeroJogadores;
   ```

3. **Métodos Abstratos Bem Definidos**
   ```java
   public abstract void iniciar();
   public abstract void jogar();
   public abstract void terminar();
   ```

4. **Documentação Clara**
   - JavaDoc em todas as classes
   - Comentários explicativos nos métodos
   - Exemplos de uso nas classes de teste

---

## 🚀 Próximos Passos

Após estudar estes exemplos:

1. **Execute todos os 4 sistemas** e observe as saídas diferentes
2. **Compare as implementações** - veja como cada sistema usa abstrações
3. **Modifique os valores** e veja o impacto nas saídas
4. **Adicione novos tipos**:
   - Sistema Funcionários: `Estagiario`, `Terceirizado`
   - Sistema Jogos: `JogoRPG`, `JogoEsporte`
   - Sistema Bancário: `ContaSalario`, `ContaEmpresarial`
   - Sistema Documentos: `ProcessadorWord`, `ProcessadorPowerPoint`
5. **Implemente os exercícios propostos** no diretório de exercícios
6. **Crie seus próprios sistemas** aplicando classes abstratas

### Desafios Extras:

1. **Desafio 1**: No Sistema Bancário, adicione um método `cobrarTarifas()` que cobra diferentes tarifas baseadas no tipo de conta
2. **Desafio 2**: No Sistema de Documentos, implemente compressão de arquivos antes do processamento
3. **Desafio 3**: Crie um novo sistema: Processador de Pagamentos (Cartão, Boleto, PIX, Transferência)
4. **Desafio 4**: Adicione logging em todos os sistemas para rastrear operações

---

## 📚 Referências

- [README principal](../README.md) - Conceitos teóricos
- [Exercícios](../exercicios/README.md) - Desafios práticos
- [Diagramas UML](../img/) - Visualização da arquitetura

---

**Desenvolvido para o Curso de Programação Orientada a Objetos**  
**Universidade Nove de Julho - 2024**
