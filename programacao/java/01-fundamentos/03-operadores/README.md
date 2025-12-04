# Operadores em Java

Esta seção aborda todos os tipos de operadores disponíveis em Java para manipulação de dados e controle de fluxo.

## 🎯 Objetivos

- Compreender todos os tipos de operadores em Java
- Aprender precedência e associatividade
- Praticar operações aritméticas, lógicas e bitwise
- Entender operadores de atribuição e comparação

## 📋 Tipos de Operadores

### Operadores Aritméticos
- **Básicos**: `+`, `-`, `*`, `/`, `%`
- **Unários**: `++`, `--`, `+`, `-`

### Operadores de Atribuição
- **Simples**: `=`
- **Compostos**: `+=`, `-=`, `*=`, `/=`, `%=`
- **Bitwise**: `&=`, `|=`, `^=`, `<<=`, `>>=`, `>>>=`

### Operadores de Comparação
- **Igualdade**: `==`, `!=`
- **Relacionais**: `<`, `>`, `<=`, `>=`
- **Tipo**: `instanceof`

### Operadores Lógicos
- **Booleanos**: `&&`, `||`, `!`
- **Bitwise**: `&`, `|`, `^`

### Operadores Bitwise
- **Posição**: `<<`, `>>`, `>>>`
- **Manipulação**: `&`, `|`, `^`, `~`

### Operadores Especiais
- **Ternário**: `? :`
- **Acesso**: `.`
- **Array**: `[]`
- **Cast**: `(tipo)`

## 🖥️ Exemplos

### [OperadoresAritmeticos.java](OperadoresAritmeticos.java)
Demonstra todos os operadores aritméticos com exemplos práticos.

### [OperadoresLogicos.java](OperadoresLogicos.java)
Mostra operadores lógicos e suas tabelas verdade.

### [OperadoresBitwise.java](OperadoresBitwise.java)
Ilustra operações bit a bit com exemplos visuais.

### [OperadoresEspeciais.java](OperadoresEspeciais.java)
Demonstra operadores ternário, instanceof e outros.

### [TesteOperadores.java](TesteOperadores.java)
Programa principal testando todos os operadores.

## 🚀 Como Executar

```bash
javac *.java
java TesteOperadores
```

## 📊 Precedência de Operadores

| Precedência | Operadores | Associatividade |
|-------------|------------|-----------------|
| 1 (maior)   | `[]` `.` `()` | Esquerda → Direita |
| 2           | `++` `--` `+` `-` `~` `!` `(cast)` | Direita → Esquerda |
| 3           | `*` `/` `%` | Esquerda → Direita |
| 4           | `+` `-` | Esquerda → Direita |
| 5           | `<<` `>>` `>>>` | Esquerda → Direita |
| 6           | `<` `<=` `>` `>=` `instanceof` | Esquerda → Direita |
| 7           | `==` `!=` | Esquerda → Direita |
| 8           | `&` | Esquerda → Direita |
| 9           | `^` | Esquerda → Direita |
| 10          | `|` | Esquerda → Direita |
| 11          | `&&` | Esquerda → Direita |
| 12          | `||` | Esquerda → Direita |
| 13          | `? :` | Direita → Esquerda |
| 14 (menor)  | `=` `+=` `-=` etc. | Direita → Esquerda |

## 💡 Dicas Importantes

### Incremento/Decremento
```java
int a = 5;
int b = ++a; // Pré-incremento: a=6, b=6
int c = a++; // Pós-incremento: a=7, c=6
```

### Divisão Inteira vs Decimal
```java
int resultado1 = 5 / 2;        // 2 (divisão inteira)
double resultado2 = 5.0 / 2;   // 2.5 (divisão decimal)
double resultado3 = (double) 5 / 2; // 2.5 (cast)
```

### Short-Circuit Evaluation
```java
boolean resultado = false && metodoQuePodeSerCaro(); // método não é chamado
boolean resultado = true || metodoQuePodeSerCaro();  // método não é chamado
```

### Comparação de Objetos
```java
String s1 = "Hello";
String s2 = "Hello";
String s3 = new String("Hello");

s1 == s2;        // true (mesmo objeto na pool)
s1 == s3;        // false (objetos diferentes)
s1.equals(s3);   // true (mesmo conteúdo)
```

## ⚠️ Armadilhas Comuns

### 1. Precedência Inesperada
```java
int x = 5 + 3 * 2;  // 11, não 16 (multiplicação primeiro)
int y = (5 + 3) * 2; // 16 (parênteses alteram precedência)
```

### 2. Overflow
```java
int max = Integer.MAX_VALUE;
int overflow = max + 1; // Resulta em Integer.MIN_VALUE
```

### 3. Divisão por Zero
```java
int result = 10 / 0;        // ArithmeticException
double result = 10.0 / 0.0; // Infinity
double result = 0.0 / 0.0;  // NaN
```

### 4. Comparação de Ponto Flutuante
```java
double a = 0.1 + 0.2;
double b = 0.3;
boolean igual = a == b; // false! (problemas de precisão)

// Forma correta:
boolean igual = Math.abs(a - b) < 0.0001;
```

## 📝 Exercícios

1. **Calculadora**: Implemente operações básicas usando todos os operadores aritméticos
2. **Verificador de Paridade**: Use operadores bitwise para verificar se número é par/ímpar
3. **Validador Lógico**: Crie expressões complexas com operadores lógicos
4. **Conversor Binário**: Use operadores bitwise para conversões
5. **Expressões Ternárias**: Substitua if-else por operadores ternários

## 🔗 Próximo Passo

Continue para [Controle de Fluxo](../04-controle-fluxo/) para aprender como usar operadores em estruturas condicionais e loops.