# Exemplos de Programação Orientada a Objetos em C#

Este diretório contém exemplos práticos de POO (Programação Orientada a Objetos) em C#, demonstrando os principais conceitos através de um sistema bancário simples.

## 📁 Estrutura dos Exemplos

### 1. Exemplo Moderno (`oop-exemplos/BancoExemplo/`)
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