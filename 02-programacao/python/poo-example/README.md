# Demonstração de POO em Python

Este projeto demonstra os conceitos fundamentais de Programação Orientada a Objetos (POO) em Python, implementando as classes **Conta**, **ContaPoupanca** e **Cliente** baseadas no diagrama UML fornecido.

## Conceitos Demonstrados

### 1. **Herança (Inheritance)**
- `ContaPoupanca` herda de `Conta`
- A classe filha reutiliza todos os métodos e atributos da classe pai
- Adiciona funcionalidades específicas através do método `ver_lucro()`

### 2. **Associação (Association)**
- `Cliente` possui uma referência para `ContaPoupanca`
- Demonstra relacionamento "tem um" (has-a relationship)
- Um cliente pode ter uma conta poupança

### 3. **Encapsulamento (Encapsulation)**
- Atributos privados (prefixo `_`) com métodos getter/setter
- Controle de acesso aos dados internos das classes
- Validação através de métodos como `ver_saldo()` com senha

## Estrutura das Classes

### Classe Conta
```python
class Conta:
    - _agencia: str
    - _conta: str  
    - _saldo: float
    
    + get_saldo(): float
    + get_agencia(): str
    + get_conta(): str
    + set_agencia(agencia: str): void
    + set_conta(conta: str): void
    + depositar(deposito: float): void
    + ver_saldo(): float
```

### Classe ContaPoupanca (herda de Conta)
```python
class ContaPoupanca(Conta):
    - _dia_deposito: float
    
    + get_dia_deposito(): float
    + set_dia_deposito(dia_deposito: float): void
    + ver_lucro(): float
```

### Classe Cliente
```python
class Cliente:
    + cpf: str
    + nome: str
    + telefone: str
    + endereco: str
    + conta_poupanca: ContaPoupanca
    
    + criar_conta_poupanca(): ContaPoupanca
    + mostrar_cpf(): str
    + ver_saldo(senha: int): float
    + get_conta_poupanca(): ContaPoupanca
```

## Como Executar

1. Navegue até o diretório do projeto:
```bash
cd programming/python/poo-example/
```

2. Execute o script de demonstração:
```bash
python3 main.py
```

## Exemplo de Uso

```python
# Criando um cliente
cliente = Cliente()
cliente.nome = "João"
cliente.cpf = "123.456.789-00"

# Criando conta poupança (associação)
conta = cliente.criar_conta_poupanca()
conta.set_agencia("0001")
conta.set_conta("123456")

# Realizando operações (herança)
conta.depositar(1000.0)
saldo = cliente.ver_saldo(1234)  # senha correta
lucro = conta.ver_lucro()  # método específico de ContaPoupanca
```

## Saída Esperada

O script `main.py` produz uma saída demonstrando:
1. Criação de clientes
2. Associação de contas poupança aos clientes  
3. Operações de depósito utilizando herança
4. Consulta de saldos com encapsulamento
5. Métodos específicos da classe ContaPoupanca
6. Validação de segurança

## Correspondência com Java

Esta implementação Python corresponde à estrutura UML também implementada em Java no diretório `modelagem/uml-poo/`, demonstrando os mesmos conceitos de POO em linguagens diferentes.