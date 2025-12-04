# Exemplos de C# - Programação e Inteligência Artificial

Este diretório contém exemplos práticos de C#, desde conceitos básicos até aplicações avançadas de inteligência artificial.

## 📁 Estrutura dos Exemplos

### 1. Conceitos Básicos (`00-conceitos/`)
Exemplos fundamentais da linguagem C#

### 2. Programação Orientada a Objetos (`oop-exemplos/`)
Exemplo completo e moderno utilizando .NET 8.0 com todas as boas práticas:

#### Classes Implementadas:
- **`Conta`** (classe base)
  - Propriedades: Agencia, NumeroConta, Saldo (encapsulado)
  - Métodos: Depositar(), Sacar(), ConsultarSaldo()
  - Demonstra: **Encapsulamento** e **Métodos virtuais**

- **`ContaPoupanca`** (herda de Conta)
  - Propriedades adicionais: DiaAniversario, TaxaRendimento
  - Métodos específicos: CalcularRendimento(), AplicarRendimento()
  - Demonstra: **Herança** e **Polimorfismo**

- **`Cliente`**
  - Propriedades: Nome, CPF, Telefone, Endereco
  - Composição: Possui uma ContaPoupanca
  - Demonstra: **Composição/Agregação**

#### Como executar:
```bash
cd oop-exemplos/BancoExemplo
dotnet run
```

### 3. 🧬 Programação Genética (`programacao-genetica/`)
**NOVO!** Seção completa sobre Programação Genética (Genetic Programming) em C# com:

- 📚 **README Acadêmico Completo**: Mais de 40 páginas de conteúdo cobrindo:
  - Fundamentos teóricos e matemáticos
  - História e contexto acadêmico
  - Conceitos fundamentais (população, cromossomos, fitness, seleção, crossover, mutação)
  - Algoritmos Genéticos vs Programação Genética
  - Arquitetura de sistemas GP
  - Operadores genéticos detalhados
  - Implementação em C#
  - Controle de bloat
  - Melhores práticas
  - Referências acadêmicas extensivas

- 💻 **Exemplos Práticos Funcionais**:
  
  #### Exemplo 1: Framework Básico de GP
  - Implementação completa de um motor de Programação Genética
  - Representação em árvore de expressões
  - Operadores genéticos (crossover, mutação, seleção)
  - Demonstração: Regressão Simbólica (descobre f(x) = x² + 2x + 1)
  - **Executar**: `cd programacao-genetica/exemplos/01-basic-framework/BasicGPFramework && dotnet run`
  
  #### Exemplo 3: Otimização de Rotas com Telemetria (Caminhão-AWS) 🚚📡
  - **Caso de uso real**: Sistema de entregas com caminhões IoT
  - **Integração cloud**: Preparado para AWS IoT Core
  - **Multi-objetivo**: Otimiza distância, tempo, combustível, custo de dados e atrasos
  - **Telemetria**: Considera custos de transmissão 4G vs WiFi
  - **Resultado**: Economia de 40%+ em custos operacionais
  - **Executar**: `cd programacao-genetica/exemplos/03-telemetry-route-optimization/TelemetryRouteOptimization && dotnet run`

#### Aplicações Demonstradas:
- ✅ Regressão simbólica (descoberta automática de fórmulas)
- ✅ Otimização de rotas com restrições (VRP com janelas de tempo)
- ✅ Telemetria IoT e integração cloud (AWS)
- ✅ Otimização multi-objetivo
- ✅ Problemas do mundo real da indústria

#### Conceitos de IA/GP Cobertos:
- 🧬 Algoritmos Evolutivos
- 🌳 Representação em árvore (AST)
- 🎯 Funções de fitness multi-objetivo
- 🔄 Operadores genéticos especializados
- 📊 Análise de convergência
- 🛡️ Controle de bloat (parsimony pressure)
- 🏆 Elitismo e seleção por torneio
- 📈 Otimização combinatória

**[📖 Acesse o README completo de Programação Genética](programacao-genetica/README.md)**

## 🎯 Conceitos de POO Demonstrados

### 1. **Encapsulamento**
- Campos privados (`_saldo`)
- Propriedades com validação
- Controle de acesso aos dados

```csharp
private decimal _saldo;
public decimal Saldo 
{ 
    get { return _saldo; }
    protected set 
    {
        if (value >= 0)
            _saldo = value;
    }
}
```

### 2. **Herança**
- `ContaPoupanca` herda de `Conta`
- Reutilização de código da classe base
- Especialização de comportamentos

```csharp
public class ContaPoupanca : Conta
{
    public ContaPoupanca(int agencia, int numeroConta, decimal saldoInicial = 0) 
        : base(agencia, numeroConta, saldoInicial)
    {
        // Código específico da conta poupança
    }
}
```

### 3. **Polimorfismo**
- Sobrescrita de métodos (`override`)
- Métodos virtuais (`virtual`)
- Comportamento diferente para mesma interface

```csharp
// Na classe base
public virtual void ConsultarSaldo() { ... }

// Na classe derivada
public override void ConsultarSaldo() 
{
    Console.WriteLine("=== CONTA POUPANÇA ===");
    base.ConsultarSaldo();
    // Informações específicas da poupança
}
```

### 4. **Composição/Agregação**
- `Cliente` "tem uma" `ContaPoupanca`
- Relacionamento "tem um" ao invés de "é um"

```csharp
public class Cliente
{
    public ContaPoupanca? ContaPoupanca { get; set; }
    
    public void CriarContaPoupanca(int agencia, int numeroConta)
    {
        ContaPoupanca = new ContaPoupanca(agencia, numeroConta);
    }
}
```

## 🔄 Relacionamentos entre Classes

```
    Cliente
       |
       | tem uma (1:1)
       ↓
  ContaPoupanca
       |
       | herda de (is-a)
       ↓
     Conta
```

## 💡 Funcionalidades Demonstradas

1. **Operações Bancárias**: Depósito, saque, consulta de saldo
2. **Rendimento da Poupança**: Cálculo e aplicação automática
3. **Validações**: Valores negativos, saldo insuficiente
4. **Relatórios**: Informações detalhadas do cliente e conta
5. **Polimorfismo em Ação**: Mesma referência, comportamentos diferentes

## 🛠️ Requisitos

- .NET 8.0 ou superior
- Sistema operacional: Windows, Linux ou macOS

## 📚 Para Estudantes

Este exemplo foi criado para demonstrar como os conceitos teóricos de POO se aplicam na prática:

- **Analise o código**: Veja como cada conceito é implementado
- **Execute os exemplos**: Observe o comportamento em runtime
- **Modifique o código**: Adicione novas funcionalidades
- **Experimente**: Crie novos tipos de conta ou operações

### Exercícios Sugeridos:

1. Criar uma classe `ContaCorrente` que também herda de `Conta`
2. Adicionar limite de crédito à conta corrente
3. Implementar taxa de manutenção mensal
4. Criar um sistema de histórico de transações
5. Adicionar validação real de CPF

## 📖 Recursos Adicionais

- [Documentação oficial do C#](https://docs.microsoft.com/pt-br/dotnet/csharp/)
- [POO em C# - Microsoft Learn](https://docs.microsoft.com/pt-br/dotnet/csharp/fundamentals/object-oriented/)
- [Padrões de Design em C#](https://refactoring.guru/pt-br/design-patterns/csharp)