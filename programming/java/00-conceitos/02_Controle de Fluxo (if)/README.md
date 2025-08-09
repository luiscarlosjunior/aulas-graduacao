# Controle de Fluxo - Estruturas Condicionais

Esta seção apresenta as estruturas condicionais em Java, que permitem que o programa tome decisões baseadas em condições específicas.

## 🎯 Objetivos

- Compreender a estrutura `if-else`
- Aprender sobre condicionais aninhadas
- Conhecer operadores relacionais e lógicos
- Entender a estrutura `switch-case`
- Aplicar boas práticas em estruturas condicionais

## 📋 Estruturas Condicionais

### 1. Estrutura if

A estrutura mais básica para tomada de decisões:

```java
if (condicao) {
    // código executado se a condição for verdadeira
}
```

### 2. Estrutura if-else

Permite executar código alternativo quando a condição é falsa:

```java
if (condicao) {
    // código se verdadeiro
} else {
    // código se falso
}
```

### 3. Estrutura if-else if

Para múltiplas condições:

```java
if (condicao1) {
    // código para condição 1
} else if (condicao2) {
    // código para condição 2
} else {
    // código padrão
}
```

## 📄 Análise dos Exemplos

### [TestaCondicional.java](TestaCondicional.java)
Demonstra estruturas condicionais básicas:

```java
int idade = 18;
int quantidadePessoas = 3;

if (idade >= 18) {
    System.out.println("Você tem mais que 18 anos");
    System.out.println("Seja bem vindo");
} else {
    if (quantidadePessoas >= 2) {
        System.out.println("você não tem 18, mas pode entrar, " 
                + "pois está acompanhado");
    } else {
        System.out.println("infelizmente você não pode entrar");
    }
}
```

**Conceitos demonstrados:**
- Condição simples com operador `>=`
- Estruturas condicionais aninhadas
- Múltiplas instruções dentro de blocos condicionais

### [TestaCondicional2.java](TestaCondicional2.java)
Exemplos mais elaborados de condicionais.

### [ExemplosControleFluxo.java](ExemplosControleFluxo.java)
Diversos exemplos de estruturas condicionais.

### [ExemploIR2019.java](ExemploIR2019.java)
Aplicação prática: cálculo de imposto de renda com múltiplas faixas.

**Conceitos demonstrados:**
- Lógica de negócio real
- Condições sequenciais
- Cálculos baseados em faixas de valores

## 🔧 Operadores para Condições

### Operadores Relacionais

| Operador | Descrição           | Exemplo        |
|----------|---------------------|----------------|
| `==`     | Igual               | `a == b`       |
| `!=`     | Diferente           | `a != b`       |
| `>`      | Maior que           | `a > b`        |
| `<`      | Menor que           | `a < b`        |
| `>=`     | Maior ou igual      | `a >= b`       |
| `<=`     | Menor ou igual      | `a <= b`       |

### Operadores Lógicos

| Operador | Descrição    | Exemplo                |
|----------|--------------|------------------------|
| `&&`     | E lógico     | `(a > 5) && (b < 10)`  |
| `\|\|`   | OU lógico    | `(a == 0) \|\| (b == 0)` |
| `!`      | NÃO lógico   | `!(a == b)`            |

### Exemplo de Combinação:

```java
int idade = 20;
boolean temCarteira = true;
boolean temCarro = false;

if (idade >= 18 && temCarteira && temCarro) {
    System.out.println("Pode dirigir!");
} else if (idade >= 18 && temCarteira) {
    System.out.println("Pode dirigir, mas precisa de um carro!");
} else {
    System.out.println("Não pode dirigir ainda.");
}
```

## 🎲 Estrutura Switch-Case

Para comparações de igualdade com múltiplos valores:

```java
int dia = 3;
String nomeDia;

switch (dia) {
    case 1:
        nomeDia = "Segunda-feira";
        break;
    case 2:
        nomeDia = "Terça-feira";
        break;
    case 3:
        nomeDia = "Quarta-feira";
        break;
    default:
        nomeDia = "Dia inválido";
        break;
}
```

**Importante:**
- `break` é necessário para evitar "fall-through"
- `default` é opcional, mas recomendado
- Funciona com: `int`, `char`, `String`, `enum`

## 🚀 Como Executar os Exemplos

```bash
# Navegar até o diretório
cd "02_Controle de Fluxo (if)"

# Compilar um exemplo
javac TestaCondicional.java

# Executar
java TestaCondicional
```

## 💡 Boas Práticas

### 1. Use chaves mesmo para uma linha
```java
// Evite:
if (condicao)
    System.out.println("Mensagem");

// Prefira:
if (condicao) {
    System.out.println("Mensagem");
}
```

### 2. Condições positivas são mais legíveis
```java
// Evite:
if (!usuario.naoEstaLogado()) {
    // ...
}

// Prefira:
if (usuario.estaLogado()) {
    // ...
}
```

### 3. Evite condicionais muito aninhadas
```java
// Evite:
if (a) {
    if (b) {
        if (c) {
            // muito aninhado
        }
    }
}

// Prefira early return:
if (!a) return;
if (!b) return;
if (!c) return;
// código principal aqui
```

### 4. Use switch para múltiplas comparações de igualdade
```java
// Ao invés de:
if (status == 1) {
    // ...
} else if (status == 2) {
    // ...
} else if (status == 3) {
    // ...
}

// Use:
switch (status) {
    case 1: /* ... */ break;
    case 2: /* ... */ break;
    case 3: /* ... */ break;
}
```

## 💡 Experimentos Sugeridos

1. **Calculadora simples**: Use switch para operações (+, -, *, /)
2. **Classificação de notas**: A, B, C, D, F baseado em pontuação
3. **Validação de dados**: Verificar se email, telefone, etc. são válidos
4. **Sistema de desconto**: Diferentes descontos baseados na quantidade comprada

## ❗ Erros Comuns

1. **Usar `=` ao invés de `==`**: `if (x = 5)` (atribuição ao invés de comparação)
2. **Esquecer `break` no switch**: Causa fall-through indesejado
3. **Condições sempre verdadeiras/falsas**: `if (true)` ou `if (false)`
4. **Comparar Strings com `==`**: Use `equals()` para Strings

## 📚 Conceitos Relacionados

- **Operador Ternário**: `condicao ? valorSeVerdadeiro : valorSeFalso`
- **Short-circuit evaluation**: `&&` e `||` param se o resultado já é determinado
- **Precedência de operadores**: Ordem de avaliação das expressões
- **Boolean algebra**: Leis de De Morgan e simplificação lógica

---

**Próximo**: [Controle de Fluxo (Repetição)](../03_Controle%20de%20Fluxo%20%28repeticao%29/) - Aprenda sobre loops e estruturas de repetição.