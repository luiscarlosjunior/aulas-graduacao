# Exercícios - Conceitos Fundamentais

Esta seção contém exercícios práticos que consolidam os conceitos fundamentais aprendidos em Java, incluindo tipos de dados, controle de fluxo e estruturas de repetição.

## 🎯 Objetivos

- Aplicar conceitos de tipos de dados e operadores
- Praticar estruturas condicionais (if-else)
- Exercitar estruturas de repetição (for, while)
- Desenvolver lógica de programação
- Integrar diferentes conceitos em soluções completas

## 📄 Análise dos Exercícios

### [Exercicios.java](Exercicios.java)

Este arquivo contém uma coleção de exercícios organizados por categoria e nível de dificuldade.

## 📋 Lista de Exercícios

### **Atividade 01 - Conversão de Temperatura**
```java
private static void ativ01()
```

**Objetivo**: Converter temperatura de Celsius para Fahrenheit.

**Conceitos aplicados**:
- Entrada de dados com `Scanner`
- Operações aritméticas
- Tipos de dados `double`

**Fórmula**: `F = (9*C + 160)/5`

**Exemplo de execução**:
```
Informe o valor em graus Celsius: 25
Fahrenheit: 77.0
```

---

### **Atividade 02 - Estruturas Condicionais**

#### **02a - Diferença entre dois números**
```java
private static void ativ02a()
```

**Objetivo**: Calcular a diferença absoluta entre dois números.

**Conceitos aplicados**:
- Estruturas condicionais (if-else)
- Operações aritméticas
- Lógica de comparação

#### **02b - Módulo de um número**
```java
private static void ativ02b()
```

**Objetivo**: Calcular o módulo (valor absoluto) de um número.

**Conceitos aplicados**:
- Entrada de dados
- Estruturas condicionais
- Operações com números negativos

#### **02c - Validação de faixa**
```java
private static void ativ02c()
```

**Objetivo**: Verificar se um número está na faixa de 1 a 9.

**Conceitos aplicados**:
- Validação de entrada
- Tratamento de exceções básico
- Operadores lógicos (`&&`)

#### **02d - Ordenação de três números**
```java
private static void ativ02d()
```

**Objetivo**: Ordenar três números em ordem crescente.

**Conceitos aplicados**:
- Estruturas condicionais aninhadas
- Lógica de comparação múltipla
- Formatação de saída com `System.out.format()`

#### **02e - Filtro de números divisíveis**
```java
private static void ativ02e()
```

**Objetivo**: Filtrar números que são divisíveis por 2 ou por 3.

**Conceitos aplicados**:
- Operador módulo (`%`)
- Operadores lógicos (`&&`, `!`)
- Estruturas condicionais múltiplas

---

### **Atividade 03 - Estruturas de Repetição**

#### **03a - Tabuada**
```java
private static void ativ03a()
```

**Objetivo**: Gerar a tabuada de um número informado pelo usuário.

**Conceitos aplicados**:
- Loop `for`
- Entrada de dados
- Operações aritméticas em loop

**Exemplo de execução**:
```
Informe um número: 5
A tabuada do numero 5 é:
5 X 1 = 5
5 X 2 = 10
...
5 X 10 = 50
```

#### **03c - Maior e menor número**
```java
private static void ativ03c()
```

**Objetivo**: Encontrar o maior e menor número de uma sequência (termina com número negativo).

**Conceitos aplicados**:
- Loop `while`
- Variáveis de controle
- Comando `break`
- Lógica de comparação em loop

#### **03d - Fatorial de números ímpares**
```java
private static void ativ03d()
```

**Objetivo**: Calcular o fatorial dos números ímpares de 1 a 9.

**Conceitos aplicados**:
- Loop `for` aninhado com `while`
- Cálculo de fatorial
- Incremento com passo (`i += 2`)

## 🚀 Como Executar os Exercícios

```bash
# Navegar até o diretório de exercícios
cd "exercicios"

# Compilar o arquivo
javac Exercicios.java

# Executar (modifique o método main para chamar diferentes exercícios)
java Exercicios
```

### Para testar exercícios específicos:

No método `main`, descomente a linha do exercício desejado:

```java
public static void main(String[] args) {
    // ativ01();     // Conversão de temperatura
    // ativ02a();    // Diferença entre números
    // ativ02b();    // Módulo de número
    // ativ02c();    // Validação de faixa
    // ativ02d();    // Ordenação de três números
    // ativ02e();    // Filtro de divisíveis
    // ativ03a();    // Tabuada
    // ativ03c();    // Maior e menor
    ativ03d();       // Fatorial ímpares (ativo)
}
```

## 💡 Conceitos Demonstrados

### 1. **Entrada de Dados**
```java
Scanner ler = new Scanner(System.in);
double temperatura = ler.nextDouble();
int numero = ler.nextInt();
```

### 2. **Estruturas Condicionais**
```java
if (condicao) {
    // código se verdadeiro
} else {
    // código se falso
}
```

### 3. **Operadores Lógicos**
```java
if (numero >= 1 && numero <= 9) {  // E lógico
    // dentro da faixa
}

if (!(A%2 != 0 && A%3 != 0)) {     // Negação lógica
    // divisível por 2 ou 3
}
```

### 4. **Loops com Diferentes Propósitos**
```java
// For com contador
for (int i = 0; i < 10; i++) {
    // tabuada
}

// While com condição de parada
while (number >= 0) {
    // processar até número negativo
}
```

## 🔧 Análise de Complexidade

| Exercício | Tipo | Complexidade | Conceitos Principais |
|-----------|------|--------------|---------------------|
| 01 | Básico | O(1) | Entrada, cálculo, saída |
| 02a-02e | Intermediário | O(1) | Condicionais, lógica |
| 03a | Intermediário | O(n) | Loop simples |
| 03c | Intermediário | O(n) | Loop com entrada variável |
| 03d | Avançado | O(n²) | Loops aninhados |

## 💡 Experimentos Sugeridos

### Para Iniciantes:
1. **Calculadora simples**: Operações básicas (+, -, *, /)
2. **Conversor de unidades**: Metro/pé, kg/libra, etc.
3. **Classificador de notas**: A, B, C, D, F baseado em pontuação

### Para Intermediários:
1. **Jogo de adivinhação**: Computador escolhe número, usuário adivinha
2. **Validador de CPF**: Verificar dígitos verificadores
3. **Contador de vogais**: Em uma string fornecida pelo usuário

### Para Avançados:
1. **Números primos**: Verificar se um número é primo
2. **Sequência de Fibonacci**: Gerar n termos da sequência
3. **Conversor de bases**: Decimal para binário, octal, hexadecimal

## ❗ Problemas Identificados e Correções

### 1. **Problema no Exercício 03d (Fatorial)**
O código atual tem um bug no cálculo do fatorial:

**Problema**:
```java
while(j > 1) {          // Para no 2, não no 1
    fat *= j;
    j--;
}
```

**Correção sugerida**:
```java
while(j >= 1) {         // Vai até 1
    fat *= j;
    j--;
}
```

### 2. **Melhoria no Exercício 03c**
Inicialização incorreta da variável `menor`:

**Problema**:
```java
int menor = 0;  // Deveria ser Integer.MAX_VALUE
```

**Correção sugerida**:
```java
int menor = Integer.MAX_VALUE;
boolean primeiroNumero = true;

// No loop:
if (primeiroNumero) {
    maior = menor = number;
    primeiroNumero = false;
}
```

## 📚 Conceitos Relacionados

- **Algoritmos de ordenação**: Bubble sort, selection sort
- **Validação de dados**: Expressões regulares, try-catch
- **Estruturas de dados**: Arrays, listas para coleções de dados
- **Métodos auxiliares**: Quebrar código em funções menores

## 🎓 Próximos Passos

Após dominar estes exercícios:

1. **Métodos e funções**: Organizar código em métodos reutilizáveis
2. **Arrays**: Trabalhar com coleções de dados
3. **Orientação a objetos**: Classes, objetos, encapsulamento
4. **Tratamento de exceções**: Validação robusta de entrada

---

**Próximo**: [POO - Programação Orientada a Objetos](../../03-POO/) - Evolua para conceitos de orientação a objetos.