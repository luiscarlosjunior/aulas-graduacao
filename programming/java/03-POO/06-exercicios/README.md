# Exercícios Práticos - Consolidando POO

## 🎯 Objetivo

Esta seção contém exercícios práticos para consolidar todos os conceitos de Programação Orientada a Objetos aprendidos nas aulas anteriores.

## 📚 Pré-requisitos

Antes de fazer estes exercícios, certifique-se de ter estudado:

- [01 - Classes e Objetos](../01-classes-objetos/)
- [02 - Encapsulamento](../02-encapsulamento/)
- [03 - Herança](../03-heranca/)
- [04 - Polimorfismo](../04-polimorfismo/)
- [05 - Abstração](../05-abstracao/)

## 📋 Lista de Exercícios

### 🟢 Nível Básico

#### **Exercício 1 - Sistema de Biblioteca**
**Conceitos**: Classes, Objetos, Encapsulamento

Crie um sistema simples de biblioteca com:
- Classe `Livro` com atributos privados e métodos públicos
- Classe `Biblioteca` para gerenciar livros
- Validações e operações básicas

**Arquivos**: `Livro.java`, `Biblioteca.java`, `TesteBiblioteca.java`

#### **Exercício 2 - Formas Geométricas**
**Conceitos**: Herança, Polimorfismo

Implemente um sistema de formas geométricas:
- Classe base `Forma2D`
- Classes filhas: `Quadrado`, `Triangulo`, `Circulo`
- Métodos para calcular área e perímetro

**Arquivos**: `Forma2D.java`, `Quadrado.java`, `Triangulo.java`, `Circulo.java`, `TesteFormas.java`

### 🟡 Nível Intermediário

#### **Exercício 3 - Sistema Bancário**
**Conceitos**: Encapsulamento, Herança, Polimorfismo

Desenvolva um sistema bancário com:
- Classe abstrata `Conta`
- Subclasses: `ContaCorrente`, `ContaPoupanca`
- Sistema de transferências e relatórios

**Arquivos**: `Conta.java`, `ContaCorrente.java`, `ContaPoupanca.java`, `Banco.java`, `TesteBanco.java`

#### **Exercício 4 - Loja de Produtos**
**Conceitos**: Abstração, Interfaces, Polimorfismo

Crie uma loja virtual com:
- Interface `Produto`
- Classes concretas: `Livro`, `Eletronico`, `Roupa`
- Sistema de carrinho e checkout

**Arquivos**: `Produto.java`, `Livro.java`, `Eletronico.java`, `Roupa.java`, `CarrinhoCompras.java`, `TesteLoja.java`

### 🔴 Nível Avançado

#### **Exercício 5 - Sistema de Funcionários**
**Conceitos**: Todos os conceitos integrados

Implemente um sistema completo de RH:
- Hierarquia de funcionários
- Sistema de salários e bonificações
- Relatórios e estatísticas

**Arquivos**: Múltiplos arquivos com sistema completo

## 🚀 Como Executar

Cada exercício está em sua própria pasta:

```bash
cd exercicio-1/
javac *.java
java TesteBiblioteca
```

## 💡 Dicas de Estudo

1. **Leia os conceitos primeiro**: Revise a teoria antes de fazer os exercícios
2. **Faça na ordem**: Os exercícios estão organizados por dificuldade
3. **Teste seus códigos**: Sempre execute e teste as funcionalidades
4. **Compare soluções**: Veja os exemplos fornecidos após tentar
5. **Experimente modificações**: Adicione suas próprias funcionalidades

## 🎓 Critérios de Avaliação

### Conceitos Fundamentais
- [ ] Uso correto de classes e objetos
- [ ] Encapsulamento adequado (atributos privados, métodos públicos)
- [ ] Implementação correta de construtores
- [ ] Uso apropriado de getters e setters

### Conceitos Avançados
- [ ] Herança bem implementada
- [ ] Polimorfismo funcional
- [ ] Uso correto de classes abstratas
- [ ] Implementação adequada de interfaces
- [ ] Sobrecarga e sobrescrita corretas

### Boas Práticas
- [ ] Código limpo e bem comentado
- [ ] Nomes descritivos para classes, métodos e variáveis
- [ ] Tratamento de casos especiais
- [ ] Organização lógica do código

## 📈 Próximos Passos

Após completar estes exercícios:

1. **Projetos Maiores**: Tente criar sistemas mais complexos
2. **Design Patterns**: Estude padrões de projeto
3. **Frameworks**: Explore Spring, Hibernate, etc.
4. **Projetos Reais**: Contribua para projetos open source

## 🔗 Navegação
[← 05 - Abstração](../05-abstracao/) | [📚 Conceitos Principais](../README.md)