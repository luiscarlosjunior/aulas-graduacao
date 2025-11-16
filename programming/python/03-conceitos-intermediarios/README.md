# Conceitos Intermediários de Python

Esta seção apresenta conceitos intermediários de Python para quem já domina os fundamentos. Aqui você aprenderá técnicas mais avançadas e recursos poderosos da linguagem.

## 🎯 Pré-requisitos

Antes de começar esta seção, você deve estar confortável com:
- ✅ Conceitos fundamentais (00-conceitos)
- ✅ Fundamentos avançados (01-fundamentos)
- ✅ Programação Orientada a Objetos (02-POO)

## 📖 Conteúdo

### [01 - Collections Avançadas](01-collections/)
Estruturas de dados além do básico.

**O que você vai aprender:**
- Diferenças entre list, tuple, dict e set
- defaultdict para valores padrão
- Counter para contagem eficiente
- deque para filas de duas pontas
- Quando usar cada estrutura
- Comprehensions avançadas

**Tempo estimado:** 2-3 horas

---

### [02 - Type Hints](02-type-hints/)
Anotações de tipo para código mais robusto.

**O que você vai aprender:**
- Como adicionar type hints
- Tipos básicos e compostos
- Optional, Union, List, Dict
- Type checking com mypy
- Benefícios para IDEs e documentação

**Tempo estimado:** 2-3 horas

---

### [03 - Enumerações](03-enumeracao/)
Criar conjuntos de constantes nomeadas.

**O que você vai aprender:**
- Classe Enum
- IntEnum, Flag, IntFlag
- Comparações e iteração
- Quando usar enumerações

**Tempo estimado:** 1-2 horas

---

### [04 - Decorators](04-decorators/)
Modificar comportamento de funções e classes.

**O que você vai aprender:**
- Criar decorators simples
- Decorators com parâmetros
- @wraps para preservar metadados
- Decorators de classe
- Decorators práticos (timing, logging, cache)

**Tempo estimado:** 3-4 horas

---

## 🎯 Objetivos de Aprendizado

Após concluir esta seção, você será capaz de:

- ✅ Escolher a estrutura de dados apropriada para cada situação
- ✅ Usar type hints para código mais seguro
- ✅ Criar e usar enumerações
- ✅ Criar decorators personalizados
- ✅ Aplicar técnicas pythônicas avançadas
- ✅ Escrever código mais eficiente e elegante

## 💡 Diferenças com Java

| Conceito | Java | Python |
|----------|------|--------|
| **Annotations** | @Override, @Deprecated | Decorators (@decorator) |
| **Generics** | `List<String>` | Type hints: `List[str]` |
| **Collections** | ArrayList, HashMap, HashSet | list, dict, set (built-in) |
| **Enum** | enum class | Enum class |
| **Type System** | Estático obrigatório | Dinâmico + opcional hints |

## 🚀 Como Estudar Esta Seção

### **Ordem Recomendada:**
1. Collections Avançadas (base para tudo)
2. Decorators (muito usado em Python)
3. Type Hints (opcional mas recomendado)
4. Enumerações (quando necessário)

### **Metodologia:**
1. **Entenda o conceito** - Por que existe?
2. **Veja exemplos** - Como se usa?
3. **Pratique** - Crie seus próprios exemplos
4. **Aplique** - Use em projetos reais

## 📚 Recursos Adicionais

### **Documentação:**
- [Python Collections](https://docs.python.org/3/library/collections.html)
- [Python Type Hints](https://docs.python.org/3/library/typing.html)
- [Python Decorators](https://docs.python.org/3/glossary.html#term-decorator)
- [Python Enum](https://docs.python.org/3/library/enum.html)

### **Ferramentas:**
- [mypy](http://mypy-lang.org/) - Type checker
- [pylint](https://www.pylint.org/) - Linter que verifica type hints

## 🎓 Dicas

1. **Collections**: Memorize quando usar cada uma
2. **Type Hints**: Comece aos poucos, adicione gradualmente
3. **Decorators**: Entenda closures primeiro
4. **Practice**: Use esses conceitos em projetos reais

## 🔥 Recursos Avançados Python

Após dominar estes conceitos, você estará pronto para:

1. **Generators e Iterators** - Processamento eficiente de dados
2. **Context Managers** - Gerenciamento de recursos
3. **Metaclasses** - Modificação avançada de classes
4. **Async/Await** - Programação assíncrona
5. **Design Patterns** - Padrões de projeto em Python

---

**🚀 Pronto? Comece por [Collections Avançadas](01-collections/)!**

*Lembre-se: Estes conceitos fazem seu código Python mais "pythônico" e profissional!*
