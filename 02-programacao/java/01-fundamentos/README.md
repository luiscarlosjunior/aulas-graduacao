# Fundamentos de Java

Esta seção cobre os conceitos fundamentais da linguagem Java com **exemplos práticos da indústria**, mostrando onde e por que cada conceito é usado no dia a dia de um desenvolvedor profissional.

## 🏭 Por que aprender do jeito certo desde o início?

Os exemplos aqui são baseados em cenários reais de sistemas em produção:
e-commerce, bancos, logística, APIs. Aprender com contexto real acelera o aprendizado e prepara você para entrevistas técnicas.

## 📖 Conteúdo

### [01 - Hello World](01-hello-world/)
Seu primeiro programa em Java — entendendo a estrutura básica, JVM, bytecode e como uma aplicação Java é compilada e executada.

### [02 - Tipos de Dados](02-tipos-dados/)
Tipos primitivos, classes wrapper, declaração de variáveis, conversões e **constantes** com exemplos de sistemas financeiros e e-commerce.

**Destaques:**
- Por que `int` vs `long` importa para sistemas bancários
- Constantes nomeadas vs "números mágicos" — impacto em manutenção
- Convenções de nomenclatura obrigatórias em times profissionais
- `ConstantesVariaveis.java` — sistema de e-commerce com impostos e status de pedido

### [03 - Operadores](03-operadores/)
Operadores aritméticos, lógicos, bitwise e especiais com **contextos industriais reais**.

**Destaques:**
- `OperadoresAritmeticos.java` — cálculos de preço, ICMS, juros compostos
- `OperadoresLogicos.java` — controle de acesso IAM, validação de formulário
- `OperadoresBitwise.java` — sistema de permissões com flags de bit (como Linux)
- `TesteOperadores.java` — sistema integrado de checkout de e-commerce

### [04 - Controle de Fluxo](04-controle-fluxo/)
Estruturas condicionais e loops com **cenários reais de tomada de decisão**.

**Destaques:**
- `ControleFluxoCondicional.java` — análise de crédito, processamento de pagamento, Early Return (Clean Code)
- `EstruturaRepeticao.java` — batch processing, retry com backoff exponencial, paginação de resultados

### [05 - Tratamento de Exceções](05-tratamento-excecoes/)
Try-catch, finally, exceções customizadas com **práticas de sistemas em produção**.

**Destaques:**
- Por que NullPointerException é o erro #1 em produção Java
- Exceções de domínio: `EstoqueInsuficienteException`, `ProdutoBloqueadoException`
- try-with-resources — gerenciamento automático de recursos
- Boas práticas: o que fazer e o que nunca fazer

## 🎯 Objetivos de Aprendizado

Ao completar esta seção, você será capaz de:
- ✅ Escrever e executar programas Java com estrutura profissional
- ✅ Escolher o tipo de dado correto para cada contexto
- ✅ Usar operadores para cálculos financeiros e lógica de negócio
- ✅ Modelar decisões de negócio com estruturas condicionais limpas
- ✅ Processar coleções com loops adequados para cada situação
- ✅ Tratar erros de forma robusta como sistemas em produção fazem

## 🚀 Como Estudar

1. **Sequência**: Siga a ordem numérica das pastas (01 → 05)
2. **Leia o README primeiro**: Entenda o contexto antes do código
3. **Execute**: `javac *.java && java NomeClasse`
4. **Modifique**: Altere valores para ver como o comportamento muda
5. **Pergunte**: "Onde eu usaria isso no trabalho?"

## ⚙️ Como Executar Cada Módulo

```bash
# Módulo 01 - Hello World
cd 01-hello-world/ && javac HelloWorld.java && java HelloWorld

# Módulo 02 - Tipos e Constantes
cd 02-tipos-dados/ && javac TiposPrimitivos.java && java TiposPrimitivos
cd 02-tipos-dados/ && javac ConstantesVariaveis.java && java ConstantesVariaveis

# Módulo 03 - Operadores (compile todos juntos)
cd 03-operadores/ && javac *.java && java TesteOperadores
java OperadoresAritmeticos  # exemplos focados em aritmética
java OperadoresLogicos      # exemplos focados em lógica
java OperadoresBitwise      # exemplos focados em bits e especiais

# Módulo 04 - Controle de Fluxo
cd 04-controle-fluxo/ && javac *.java
java ControleFluxoCondicional
java EstruturaRepeticao

# Módulo 05 - Exceções
cd 05-tratamento-excecoes/ && javac TratamentoExcecoes.java && java TratamentoExcecoes
```

## 📊 Mapa de Onde Cada Conceito É Usado

| Conceito | Onde na Indústria |
|----------|-------------------|
| `int`, `double`, `long` | Sistemas bancários, cálculos financeiros |
| Constantes `final` | Configurações de sistema, regras de negócio |
| Operadores aritméticos | E-commerce, fintech, logística |
| Operadores lógicos | Controle de acesso, validação, regras de negócio |
| Operadores bitwise | Permissões, protocolos de rede, criptografia |
| `if-else` / `switch` | Toda tomada de decisão em sistemas |
| `for` / `while` | Batch processing, relatórios, processamento de dados |
| `try-catch` | Resiliência em integrações, APIs, banco de dados |
| Exceções customizadas | APIs REST, microsserviços, domínio do negócio |

## 📚 Recursos Adicionais

- [Documentação Oficial Java 17](https://docs.oracle.com/en/java/javase/17/)
- [Oracle Java Tutorials](https://docs.oracle.com/javase/tutorial/java/)
- [Clean Code — Robert C. Martin](https://www.amazon.com.br/Clean-Code-Handbook-Software-Craftsmanship/dp/0132350882) (livro essencial)
- [Effective Java — Joshua Bloch](https://www.amazon.com.br/Effective-Java-Joshua-Bloch/dp/0134685997) (leitura obrigatória)

---

**Anterior**: [Java — Visão Geral](../README.md) | **Próximo**: [Programação Orientada a Objetos](../02-programacao-orientada-objetos/)