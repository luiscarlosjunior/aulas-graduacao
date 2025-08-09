# Exemplos de OOP - Sistema Bancário

## BancoExemplo

Este projeto demonstra os principais conceitos de Programação Orientada a Objetos (POO) em C# através de um sistema bancário simples.

### Como executar:

```bash
cd BancoExemplo
dotnet run
```

### O que o programa demonstra:

1. **Herança**: ContaPoupanca herda de Conta
2. **Composição**: Cliente tem uma ContaPoupanca  
3. **Encapsulamento**: Propriedades privadas e validações
4. **Polimorfismo**: Sobrescrita de métodos

### Saída esperada:

O programa criará dois clientes com contas poupança, realizará operações bancárias e mostrará como os conceitos de POO funcionam na prática, incluindo:

- Criação de contas
- Depósitos e saques
- Aplicação de rendimentos
- Consulta de saldos
- Demonstração de polimorfismo

### Estrutura das Classes:

```
Models/
├── Conta.cs          # Classe base com operações básicas
├── ContaPoupanca.cs  # Herda de Conta, adiciona rendimento
└── Cliente.cs        # Composto por ContaPoupanca
```