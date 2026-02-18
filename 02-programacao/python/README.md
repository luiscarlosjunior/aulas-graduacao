# Programação Python

Este diretório contém exemplos e demonstrações de programação em Python, cobrindo desde conceitos fundamentais até tópicos avançados como Programação Orientada a Objetos e Design Patterns.

## 📚 Estrutura do Repositório

### [00-conceitos/](00-conceitos/) - **Conceitos Fundamentais** ⭐ COMECE AQUI!

Aprenda os fundamentos da programação em Python:

- **[00_hello/](00-conceitos/00_hello/)** - Hello World e primeiro programa
- **[01_tipos_de_dados/](00-conceitos/01_tipos_de_dados/)** - Tipos de dados (int, float, str, bool, list, dict, etc.)
- **[02_controle_fluxo_condicionais/](00-conceitos/02_controle_fluxo_condicionais/)** - If/elif/else, operadores lógicos
- **[03_controle_fluxo_repeticao/](00-conceitos/03_controle_fluxo_repeticao/)** - Loops (for, while), list comprehension
- **[04_excecoes/](00-conceitos/04_excecoes/)** - Try/except, tratamento de erros
- **[05_listas_e_funcoes/](00-conceitos/05_listas_e_funcoes/)** - Listas, funções, *args, **kwargs
- **[06_manipulacao_strings/](00-conceitos/06_manipulacao_strings/)** - Strings, formatação, métodos

**Tempo estimado:** 3-4 semanas

---

### [01-fundamentos/](01-fundamentos/) - **Fundamentos Avançados**

Aprofunde seus conhecimentos nos fundamentos:

- **[01-hello-world/](01-fundamentos/01-hello-world/)** - Hello World detalhado e interativo
- **[02-tipos-dados/](01-fundamentos/02-tipos-dados/)** - Tipos primitivos e conversões
- **[03-operadores/](01-fundamentos/03-operadores/)** - Operadores aritméticos, lógicos, comparação, etc.

**Tempo estimado:** 1-2 semanas

---

### [02-programacao-orientada-objetos/](02-programacao-orientada-objetos/) - **POO**

Domine a Programação Orientada a Objetos em Python:

- **[01-classes-objetos/](02-programacao-orientada-objetos/01-classes-objetos/)** - Classes, objetos, métodos
- **[02-encapsulamento/](02-programacao-orientada-objetos/02-encapsulamento/)** - Atributos privados, getters/setters
- **[03-heranca/](02-programacao-orientada-objetos/03-heranca/)** - Herança, super(), reutilização de código
- **[04-polimorfismo/](02-programacao-orientada-objetos/04-polimorfismo/)** - Polimorfismo, duck typing
- **[05-interfaces/](02-programacao-orientada-objetos/05-interfaces/)** - Protocols (Python 3.8+)
- **[06-classes-abstratas/](02-programacao-orientada-objetos/06-classes-abstratas/)** - ABC (Abstract Base Classes)

**Tempo estimado:** 3-4 semanas

---

### [poo-example/](poo-example/) - **Exemplo Completo de POO**

Demonstração completa de POO implementando:

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

#### Como usar:
```bash
cd programming/python/poo-example/
python3 main.py        # Demonstração completa
python3 teste_poo.py   # Execução dos testes
```

---

## 🚀 Como Começar

### 1. **Verifique se o Python está instalado:**
```bash
python3 --version
```
Recomendado: Python 3.8 ou superior

### 2. **Comece pelos Conceitos Fundamentais:**
```bash
cd programming/python/00-conceitos/00_hello
python3 hello_world.py
```

### 3. **Siga a Ordem Recomendada:**
1. 00-conceitos (fundamentos)
2. 01-fundamentos (aprofundamento)
3. 02-programacao-orientada-objetos (POO)

## 💡 Diferenças Python vs Java

Se você vem do Java, observe estas diferenças importantes:

| Aspecto | Java | Python |
|---------|------|--------|
| **Compilação** | `javac` + `java` | Interpretado diretamente |
| **Sintaxe** | Usa `{}` para blocos | Usa indentação |
| **Tipos** | Estática | Dinâmica |
| **Main** | `public static void main` | `if __name__ == "__main__":` |
| **Print** | `System.out.println()` | `print()` |
| **Arrays** | Arrays fixos | Listas dinâmicas |
| **Null** | `null` | `None` |
| **Boolean** | `true`/`false` | `True`/`False` |
| **Operadores** | `&&`, `||`, `!` | `and`, `or`, `not` |

## 📖 Conceitos Principais Cobertos

### **Conceitos Fundamentais:**
✅ Variáveis e tipos de dados  
✅ Estruturas condicionais (if/elif/else)  
✅ Estruturas de repetição (for/while)  
✅ Funções e parâmetros  
✅ Listas, tuplas, dicionários, sets  
✅ String manipulation  
✅ Tratamento de exceções

### **Programação Orientada a Objetos:**
✅ Classes e objetos  
✅ Encapsulamento  
✅ Herança  
✅ Polimorfismo  
✅ Classes abstratas  
✅ Protocols (interfaces Python)

### **Recursos Pythônicos:**
✅ List comprehension  
✅ F-strings  
✅ *args e **kwargs  
✅ Decorators  
✅ Context managers  
✅ Duck typing

## 🎯 Objetivos de Aprendizado

Após completar este material, você será capaz de:

- ✅ Escrever programas Python do zero
- ✅ Usar estruturas de dados apropriadas
- ✅ Criar funções e organizar código
- ✅ Aplicar conceitos de POO
- ✅ Tratar erros adequadamente
- ✅ Escrever código pythônico (idiomático)
- ✅ Migrar conhecimento de Java para Python

## 🛠️ Requisitos

- Python 3.6 ou superior (recomendado: Python 3.10+)
- Editor de texto ou IDE (VS Code, PyCharm, etc.)
- Terminal/Prompt de comando

**Não há dependências externas** - todos os exemplos usam apenas a biblioteca padrão do Python!

## 📝 Como Executar os Exemplos

Todos os programas seguem o mesmo padrão:

```bash
# Navegue até o diretório do exemplo
cd programming/python/00-conceitos/00_hello

# Execute o programa
python3 hello_world.py

# Alguns programas aceitam argumentos
python3 hello_world.py seu_nome
```

## 🎓 Dicas de Estudo

1. **Siga a ordem recomendada** - Os conceitos são progressivos
2. **Digite o código** - Não copie e cole, isso ajuda a memorizar
3. **Experimente** - Modifique os exemplos para ver o que acontece
4. **Leia os comentários** - Eles explicam cada linha de código
5. **Pratique diariamente** - Consistência é mais importante que quantidade
6. **Faça seus próprios programas** - Aplique o que aprendeu

## 📚 Filosofia Python (Zen of Python)

Execute `python3 -c "import this"` para ver os princípios do Python:

- Bonito é melhor que feio
- Explícito é melhor que implícito
- Simples é melhor que complexo
- Legibilidade conta
- Erros nunca devem passar silenciosamente

## 🌟 Recursos Adicionais

### **Documentação Oficial:**
- [Python.org](https://www.python.org/)
- [Python Tutorial](https://docs.python.org/3/tutorial/)
- [Python Standard Library](https://docs.python.org/3/library/)

### **Ferramentas Online:**
- [Python Tutor](http://pythontutor.com/) - Visualize código Python
- [Repl.it](https://repl.it/languages/python3) - IDE online
- [Python Anywhere](https://www.pythonanywhere.com/) - Hospedagem Python

## 📞 Suporte

Este material é baseado na estrutura Java existente no repositório, adaptado para Python com exemplos equivalentes e explicações das diferenças entre as linguagens.

## Conceitos Demonstrados

Os exemplos demonstram os pilares fundamentais da programação:

1. **Abstração**: Representar conceitos do mundo real
2. **Encapsulamento**: Proteger dados e controlar acesso
3. **Herança**: Reutilizar código entre classes relacionadas
4. **Polimorfismo**: Mesma interface, comportamentos específicos

## Referências

Baseado no diagrama UML disponível em `modelagem/uml-poo/` e nas implementações Java existentes no repositório, adaptado para as idiossincrasias e melhores práticas do Python.

---

**🐍 Pronto para começar sua jornada Python? Vá para [00-conceitos](00-conceitos/) e comece agora!**

*Lembre-se: Python foi projetado para ser fácil de aprender e divertido de usar!*