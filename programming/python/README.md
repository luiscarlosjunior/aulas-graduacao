# Programação Python

Este diretório contém exemplos e demonstrações de programação em Python, incluindo conceitos fundamentais e aplicações práticas.

## Estrutura

### `/poo-example/`
Demonstração completa de Programação Orientada a Objetos (POO) em Python, implementando:

- **Herança**: `ContaPoupanca` herda de `Conta`
- **Associação**: `Cliente` possui `ContaPoupanca`  
- **Encapsulamento**: Atributos privados com getters/setters
- **Polimorfismo**: Métodos comuns em diferentes classes

#### Arquivos:
- `conta.py` - Classe base Conta
- `conta_poupanca.py` - Classe ContaPoupanca (herda de Conta)
- `cliente.py` - Classe Cliente (associação com ContaPoupanca)
- `main.py` - Script de demonstração completa
- `teste_poo.py` - Testes de validação dos conceitos
- `README.md` - Documentação detalhada

#### Como usar:
```bash
cd programming/python/poo-example/
python3 main.py        # Demonstração completa
python3 teste_poo.py   # Execução dos testes
```

## Requisitos

- Python 3.6 ou superior
- Não há dependências externas

## Conceitos Demonstrados

Este exemplo demonstra os pilares fundamentais da POO:

1. **Abstração**: Classes representam entidades do mundo real
2. **Encapsulamento**: Dados privados acessados via métodos
3. **Herança**: Reutilização de código entre classes relacionadas
4. **Polimorfismo**: Mesma interface, comportamentos específicos

## Referências

Baseado no diagrama UML disponível em `modelagem/uml-poo/` e nas implementações Java existentes no repositório.