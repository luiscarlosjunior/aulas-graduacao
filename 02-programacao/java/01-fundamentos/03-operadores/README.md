# Operadores em Java — com Exemplos da Indústria

Esta seção aborda todos os tipos de operadores disponíveis em Java, contextualizados com **cenários reais de sistemas em produção**.

## 🏭 Por que Operadores Importam na Indústria?

Operadores são a base de toda lógica computacional:

| Tipo de Operador | Onde é Usado na Indústria |
|-----------------|--------------------------|
| **Aritmético** | Cálculo de preços, impostos, juros compostos, frete |
| **Lógico** | Controle de acesso, validação de dados, regras de negócio |
| **Bitwise** | Permissões de sistema (como Linux), protocolos de rede, criptografia |
| **Ternário** | Código limpo e conciso para condições simples |
| **instanceof** | Polimorfismo em frameworks (Spring, Hibernate) |

## 🎯 Objetivos

- Compreender todos os tipos de operadores em Java
- Aplicar operadores em contextos reais de desenvolvimento
- Entender precedência e associatividade para evitar bugs
- Usar short-circuit evaluation para performance e segurança
- Aplicar operadores bitwise para sistemas de permissão

## 📄 Arquivos

### [OperadoresAritmeticos.java](OperadoresAritmeticos.java)
Operadores `+`, `-`, `*`, `/`, `%`, `++`, `--` com:
- Cálculo de preço final (e-commerce): ICMS, IPI, desconto
- Divisão inteira vs decimal — o erro clássico que causa bugs silenciosos
- Operador módulo: round-robin, distribuição de carga, paginação
- Juros compostos: fórmula financeira aplicada passo a passo

### [OperadoresLogicos.java](OperadoresLogicos.java)
Operadores `&&`, `||`, `!` com:
- Sistema de controle de acesso (IAM — como Google, AWS, Azure)
- Validação de formulário de cadastro (nomeValido && emailValido && ...)
- Short-circuit evaluation: evitar NullPointerException e otimizar performance
- Regras de negócio de e-commerce com múltiplas condições

### [OperadoresBitwise.java](OperadoresBitwise.java)
Operadores `&`, `|`, `^`, `~`, `<<`, `>>` com:
- Sistema de permissões usando flags de bit (como chmod no Linux)
- Verificar e modificar permissões com AND, OR, XOR
- Deslocamento de bits para multiplicação/divisão eficiente
- Operador ternário `?:` com casos práticos de UI e lógica de frete
- `instanceof` e cast seguro com polimorfismo

### [TesteOperadores.java](TesteOperadores.java)
**Programa integrador**: sistema de checkout de e-commerce que usa TODOS os operadores em conjunto:
- Bitwise para verificar permissões do usuário
- Aritmético para calcular subtotal, desconto, imposto
- Ternário para determinar frete grátis
- Módulo para gerar número do pedido

## 🚀 Como Executar

```bash
# Compilar todos de uma vez
javac *.java

# Executar o integrador (recomendado para começar)
java TesteOperadores

# Executar cada módulo individualmente
java OperadoresAritmeticos
java OperadoresLogicos
java OperadoresBitwise
```

## 🔧 Referência Rápida — Tipos de Operadores

### Operadores Aritméticos
```java
int soma      = 5 + 3;    // 8
int subtracao = 5 - 3;    // 2
int produto   = 5 * 3;    // 15
int divisao   = 10 / 3;   // 3 (inteiro! não 3.33)
int resto     = 10 % 3;   // 1 (módulo)
double div    = 10.0 / 3; // 3.333... (decimal)

// Atribuição composta
int x = 10;
x += 5;  // x = 15
x -= 3;  // x = 12
x *= 2;  // x = 24
x /= 4;  // x = 6
x %= 4;  // x = 2
```

### Operadores Lógicos
```java
boolean a = true, b = false;
a && b   // false (AND: ambos devem ser true)
a || b   // true  (OR: pelo menos um deve ser true)
!a       // false (NOT: inverte o valor)

// Short-circuit: para de avaliar cedo
null != obj && obj.metodo()  // ← se null, metodo() não é chamado!
```

### Operadores Bitwise
```java
int a = 0b1010, b = 0b1100;
a & b    // 0b1000 = 8  (AND: 1 apenas onde ambos têm 1)
a | b    // 0b1110 = 14 (OR: 1 onde pelo menos um tem 1)
a ^ b    // 0b0110 = 6  (XOR: 1 onde são diferentes)
~a       // inverte todos os bits
a << 1   // 0b10100 = 20 (multiplica por 2)
a >> 1   // 0b0101 = 5   (divide por 2)
```

### Operador Ternário e instanceof
```java
// Ternário: condição ? seVerdadeiro : seFalso
String msg = idade >= 18 ? "Maior de idade" : "Menor de idade";

// instanceof: verificar tipo antes de cast
if (objeto instanceof String s) {
    System.out.println(s.length());  // Java 16+: pattern matching
}
```

## 📊 Precedência de Operadores

| Prioridade | Operadores | Associatividade |
|-----------|------------|----------------|
| 1 (maior) | `[]` `.` `()` | Esq → Dir |
| 2 | `++` `--` (unário) `+` `-` `~` `!` `(cast)` | Dir → Esq |
| 3 | `*` `/` `%` | Esq → Dir |
| 4 | `+` `-` | Esq → Dir |
| 5 | `<<` `>>` `>>>` | Esq → Dir |
| 6 | `<` `<=` `>` `>=` `instanceof` | Esq → Dir |
| 7 | `==` `!=` | Esq → Dir |
| 8 | `&` | Esq → Dir |
| 9 | `^` | Esq → Dir |
| 10 | `|` | Esq → Dir |
| 11 | `&&` | Esq → Dir |
| 12 | `||` | Esq → Dir |
| 13 | `? :` (ternário) | Dir → Esq |
| 14 (menor) | `=` `+=` `-=` etc. | Dir → Esq |

> 💡 **Dica**: Use parênteses para deixar a precedência explícita. `(a + b) * c` é sempre mais claro do que confiar na precedência.

## ⚠️ Armadilhas Comuns

### 1. Divisão Inteira Silenciosa
```java
int a = 5, b = 2;
double resultado = a / b;  // 2.0, não 2.5! (divisão inteira primeiro)
double correto = (double) a / b;  // 2.5 ✅
```

### 2. Overflow de Inteiros
```java
int max = Integer.MAX_VALUE;  // 2.147.483.647
int overflow = max + 1;       // -2.147.483.648! (não 2.147.483.648)
// Use long para valores grandes: long big = 2_147_483_648L;
```

### 3. Comparação de Ponto Flutuante
```java
double a = 0.1 + 0.2;
double b = 0.3;
a == b  // false! (problema de representação binária)
Math.abs(a - b) < 0.0001  // ✅ forma correta de comparar doubles
```

### 4. Operador `=` vs `==`
```java
if (x = 5) { }   // ERRO de compilação — atribuição não retorna boolean em Java
if (x == 5) { }  // ✅ comparação
```

## 📝 Exercícios

1. **IMC**: Calcule e classifique usando operadores aritméticos e ternário
2. **Sistema de Notas**: Converta nota numérica em conceito (A, B, C) com operadores
3. **Permissões**: Implemente `adicionarPermissao(perfil, perm)` e `removerPermissao(perfil, perm)` com bitwise
4. **Calculadora**: Implemente +, -, *, / com validação de divisão por zero

## 🔗 Navegação

[← 02 - Tipos de Dados](../02-tipos-dados/) | [04 - Controle de Fluxo →](../04-controle-fluxo/)