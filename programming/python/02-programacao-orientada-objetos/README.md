# Programação Orientada a Objetos em Python

Esta seção cobre os conceitos fundamentais de Programação Orientada a Objetos (POO) em Python. Aprenda a criar código organizado, reutilizável e manutenível usando os pilares da POO.

## 🎯 Para Quem É Esta Seção?

- Programadores que já dominam os fundamentos de Python
- Desenvolvedores vindos de Java que querem aprender POO em Python
- Estudantes que precisam entender os conceitos de POO
- Profissionais que querem escrever código mais profissional

## 📖 Conteúdo (Ordem Recomendada)

### [01 - Classes e Objetos](01-classes-objetos/)
Fundamentos de POO - criar e usar classes.

**O que você vai aprender:**
- Definir classes com `class`
- Criar objetos (instâncias)
- Método `__init__` (construtor)
- Atributos de instância
- Métodos de instância
- `self` - referência ao objeto

**Tempo estimado:** 2-3 horas

---

### [02 - Encapsulamento](02-encapsulamento/)
Proteger dados e controlar acesso.

**O que você vai aprender:**
- Atributos públicos, protegidos (_) e privados (__)
- Getters e setters
- Propriedades com `@property`
- Name mangling
- Quando usar encapsulamento

**Tempo estimado:** 2-3 horas

---

### [03 - Herança](03-heranca/)
Reutilizar código entre classes relacionadas.

**O que você vai aprender:**
- Criar subclasses
- Usar `super()` para chamar superclasse
- Sobrescrever métodos
- Herança múltipla
- Method Resolution Order (MRO)
- `isinstance()` e `issubclass()`

**Tempo estimado:** 3-4 horas

---

### [04 - Polimorfismo](04-polimorfismo/)
Mesma interface, comportamentos diferentes.

**O que você vai aprender:**
- Polimorfismo de método
- Duck typing
- Sobrescrita de métodos
- Interfaces implícitas
- Quando usar polimorfismo

**Tempo estimado:** 2-3 horas

---

### [05 - Interfaces](05-interfaces/)
Definir contratos que classes devem seguir.

**O que você vai aprender:**
- Duck typing (jeito pythônico)
- Protocols (Python 3.8+)
- Abstract Base Classes
- Diferenças com interfaces Java
- Quando usar cada abordagem

**Tempo estimado:** 2-3 horas

---

### [06 - Classes Abstratas](06-classes-abstratas/)
Classes que não podem ser instanciadas diretamente.

**O que você vai aprender:**
- Módulo `abc` (Abstract Base Classes)
- Decorador `@abstractmethod`
- Criar classes abstratas
- Forçar implementação de métodos
- Quando usar classes abstratas

**Tempo estimado:** 2-3 horas

---

## 🎯 Objetivos de Aprendizado

### Após concluir esta seção, você será capaz de:

**Nível Básico:**
- ✅ Criar classes e objetos
- ✅ Definir atributos e métodos
- ✅ Usar construtores (`__init__`)

**Nível Intermediário:**
- ✅ Encapsular dados adequadamente
- ✅ Usar herança para reutilizar código
- ✅ Aplicar polimorfismo

**Nível Avançado:**
- ✅ Criar interfaces e classes abstratas
- ✅ Projetar hierarquias de classes
- ✅ Aplicar princípios SOLID
- ✅ Escrever código orientado a objetos profissional

## 💡 Diferenças Python vs Java (POO)

| Aspecto | Java | Python |
|---------|------|--------|
| **Construtor** | Nome da classe | `__init__(self)` |
| **Atributos privados** | `private int x` | `self.__x` (name mangling) |
| **Interface** | `interface` keyword | Duck typing ou Protocol |
| **Classe abstrata** | `abstract class` | `ABC` + `@abstractmethod` |
| **Herança** | `extends`, `implements` | Apenas `class Child(Parent)` |
| **this** | `this` | `self` |
| **Múltipla herança** | Não suporta | Suporta (com MRO) |
| **Métodos estáticos** | `static` | `@staticmethod` |
| **Métodos de classe** | Não tem equivalente direto | `@classmethod` |

## 🚀 Como Estudar Esta Seção

### 📅 **Cronograma Sugerido (3-4 semanas)**

**Semana 1:**
- Classes e Objetos
- Encapsulamento
- Praticar criando suas próprias classes

**Semana 2:**
- Herança
- Polimorfismo
- Projeto: sistema com hierarquia de classes

**Semana 3:**
- Interfaces
- Classes Abstratas
- Projeto: framework com interfaces

**Semana 4:**
- Revisão e integração
- Projeto final da seção

### 📚 **Metodologia:**

1. **📖 Entenda o conceito** - Por que existe?
2. **👀 Veja o exemplo** - Como funciona?
3. **✋ Digite o código** - Não copie e cole
4. **🔧 Modifique** - Experimente variações
5. **💪 Crie o seu** - Implemente do zero
6. **🔄 Revise** - Compare com o exemplo

## 📋 Pré-requisitos

**Você DEVE saber:**
- ✅ Fundamentos de Python (variáveis, tipos, funções)
- ✅ Estruturas de dados (listas, dicionários)
- ✅ Controle de fluxo (if, loops)

**Você NÃO precisa saber:**
- ❌ POO em outras linguagens (mas ajuda!)
- ❌ Conceitos avançados de Python
- ❌ Design patterns

## 🔧 Como Executar os Exemplos

```bash
# Navegue até o exemplo
cd programming/python/02-programacao-orientada-objetos/01-classes-objetos

# Execute o programa
python3 classes_objetos.py
```

## 🎓 Os 4 Pilares da POO

1. **Abstração** 🎨
   - Representar conceitos do mundo real
   - Esconder complexidade

2. **Encapsulamento** 🔒
   - Proteger dados
   - Controlar acesso

3. **Herança** 👨‍👦
   - Reutilizar código
   - Criar hierarquias

4. **Polimorfismo** 🎭
   - Mesma interface
   - Comportamentos diferentes

## 💡 Dicas de Ouro

### **🎯 Quando Usar POO:**
- ✅ Modelar entidades do mundo real
- ✅ Código com muita reutilização
- ✅ Sistemas grandes e complexos
- ✅ Bibliotecas e frameworks

### **🚫 Quando NÃO Usar POO:**
- ❌ Scripts simples
- ❌ Processamento de dados simples
- ❌ Quando funcional é mais simples

### **🔧 Boas Práticas:**
1. **Nomes de classes**: PascalCase (`MinhaClasse`)
2. **Nomes de métodos**: snake_case (`meu_metodo`)
3. **Um propósito por classe**: Princípio da Responsabilidade Única
4. **Prefira composição à herança**: Quando possível
5. **Docstrings**: Documente suas classes e métodos

## 🏆 Marcos de Progresso

- [ ] **Primeira classe criada e instanciada**
- [ ] **Primeiro atributo privado encapsulado**
- [ ] **Primeira herança implementada**
- [ ] **Primeiro polimorfismo aplicado**
- [ ] **Primeira interface definida**
- [ ] **Primeira classe abstrata criada**
- [ ] **Projeto completo usando POO**

## 🎉 O Que Vem Depois?

Após dominar POO, você estará pronto para:

1. **Design Patterns** - Padrões de projeto
2. **SOLID Principles** - Princípios de design
3. **Frameworks** - Django, Flask, FastAPI
4. **Arquitetura** - Clean Architecture, DDD
5. **Testes** - Unit tests, TDD

---

**🚀 Pronto para começar? Vá para [Classes e Objetos](01-classes-objetos/) e dê o primeiro passo na POO Python!**

**Lembre-se:** POO não é sobre usar classes em tudo, é sobre organizar código de forma que faça sentido! 🐍

---

**Próximo**: [Conceitos Intermediários](../03-conceitos-intermediarios/) - Quando dominar POO
