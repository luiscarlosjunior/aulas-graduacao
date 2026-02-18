# Tipos de Dados em Java

## 📋 Visão Geral

Os tipos de dados são fundamentais em qualquer linguagem de programação. Em Java, eles definem que tipo de valor uma variável pode armazenar e quais operações podem ser realizadas com esses valores. Java é uma linguagem fortemente tipada, o que significa que toda variável deve ter um tipo declarado.

## 🎯 Objetivos de Aprendizado

Ao completar este tópico, você será capaz de:

- ✅ Identificar e usar todos os tipos primitivos do Java
- ✅ Compreender as diferenças entre tipos primitivos e objetos
- ✅ Escolher o tipo apropriado para diferentes situações
- ✅ Realizar conversões entre tipos (casting)
- ✅ Entender os conceitos de escopo de variáveis
- ✅ Trabalhar com literais e inicialização de variáveis

## 📚 Tipos Primitivos em Java

Java possui 8 tipos primitivos, divididos em categorias:

### 🔢 Tipos Numéricos Inteiros

#### 1. byte (8 bits)
```java
byte idade = 25;
byte temperatura = -10;
```
- **Faixa**: -128 a 127
- **Uso**: Economia de memória, processamento de dados binários
- **Tamanho**: 1 byte

#### 2. short (16 bits)
```java
short ano = 2024;
short populacaoCidade = 32000;
```
- **Faixa**: -32,768 a 32,767
- **Uso**: Valores pequenos onde int seria desperdício
- **Tamanho**: 2 bytes

#### 3. int (32 bits) - **PADRÃO**
```java
int populacaoBrasil = 215000000;
int numeroUsuarios = 1500;
```
- **Faixa**: -2,147,483,648 a 2,147,483,647
- **Uso**: Tipo padrão para números inteiros
- **Tamanho**: 4 bytes

#### 4. long (64 bits)
```java
long distanciaEspaco = 384400000L;  // Note o 'L'
long timestamp = System.currentTimeMillis();
```
- **Faixa**: -9,223,372,036,854,775,808 a 9,223,372,036,854,775,807
- **Uso**: Números muito grandes, timestamps
- **Tamanho**: 8 bytes

### 🔢 Tipos Numéricos Decimais

#### 5. float (32 bits)
```java
float altura = 1.75f;  // Note o 'f'
float preco = 29.99f;
```
- **Precisão**: ~7 dígitos decimais
- **Uso**: Quando a precisão não é crítica
- **Tamanho**: 4 bytes

#### 6. double (64 bits) - **PADRÃO**
```java
double pi = 3.141592653589793;
double salario = 5500.50;
```
- **Precisão**: ~15 dígitos decimais
- **Uso**: Tipo padrão para números decimais
- **Tamanho**: 8 bytes

### 📝 Tipo Caractere

#### 7. char (16 bits)
```java
char letra = 'A';
char simbolo = '@';
char unicode = '\u0041';  // 'A' em Unicode
```
- **Faixa**: 0 a 65,535 (caracteres Unicode)
- **Uso**: Caracteres individuais
- **Tamanho**: 2 bytes

### ✅ Tipo Lógico

#### 8. boolean
```java
boolean ativo = true;
boolean temPermissao = false;
```
- **Valores**: `true` ou `false`
- **Uso**: Controle de fluxo, flags
- **Tamanho**: 1 bit (teoricamente)

## 🏗️ Tipos de Referência

### String (Classe)
```java
String nome = "João Silva";
String mensagem = "Olá, mundo!";
```
- **Observação**: String NÃO é um tipo primitivo
- **Uso**: Sequências de caracteres
- **Características**: Imutável, pool de strings

## 🔄 Conversões de Tipos (Casting)

### Conversão Implícita (Widening)
```java
int numero = 100;
long numeroGrande = numero;  // int → long (automático)
double decimal = numeroGrande;  // long → double (automático)
```

### Conversão Explícita (Narrowing)
```java
double decimal = 9.7;
int inteiro = (int) decimal;  // double → int (manual)
// Resultado: inteiro = 9 (perde a parte decimal)
```

### Tabela de Conversões Automáticas
```
byte → short → int → long → float → double
      char   → int → long → float → double
```

## 📦 Declaração e Inicialização

### Declaração
```java
int numero;           // Declaração sem inicialização
String nome;          // Declaração de referência
```

### Inicialização
```java
int numero = 42;      // Declaração com inicialização
numero = 100;         // Atribuição posterior
```

### Múltiplas Declarações
```java
int a, b, c;          // Múltiplas variáveis do mesmo tipo
int x = 1, y = 2;     // Com inicialização
```

## 🎯 Escopo de Variáveis

### Variáveis Locais
```java
public void metodo() {
    int local = 10;   // Visível apenas dentro do método
}
```

### Variáveis de Instância
```java
public class Exemplo {
    private int instancia = 5;  // Visível em toda a classe
}
```

### Variáveis de Classe (Estáticas)
```java
public class Exemplo {
    public static final int CONSTANTE = 100;  // Compartilhada entre todas as instâncias
}
```

## 💡 Literais e Valores Especiais

### Literais Inteiros
```java
int decimal = 123;
int hexadecimal = 0xFF;      // 255 em decimal
int binario = 0b1111;        // 15 em decimal
int octal = 0123;            // 83 em decimal
```

### Literais Decimais
```java
double d1 = 123.45;
double d2 = 1.23e2;          // 123.0 (notação científica)
float f1 = 123.45f;
```

### Literais de Caractere
```java
char c1 = 'A';
char c2 = '\n';              // Quebra de linha
char c3 = '\u0041';          // Unicode para 'A'
char c4 = 65;                // Valor ASCII para 'A'
```

### Literais Especiais
```java
boolean verdadeiro = true;
boolean falso = false;
String nulo = null;          // Para tipos de referência
```

## ⚠️ Armadilhas Comuns

### 1. Overflow/Underflow
```java
int maximo = Integer.MAX_VALUE;
int overflow = maximo + 1;    // Resulta em Integer.MIN_VALUE!
```

### 2. Divisão de Inteiros
```java
int a = 10, b = 3;
int resultado = a / b;        // 3 (não 3.33!)
double correto = (double) a / b;  // 3.333...
```

### 3. Comparação de Decimais
```java
double a = 0.1 + 0.2;
double b = 0.3;
System.out.println(a == b);   // false! (imprecisão de ponto flutuante)
```

### 4. Inicialização Obrigatória
```java
int numero;
System.out.println(numero);  // ERRO! Variável não inicializada
```

## 🛠️ Utilitários e Métodos Úteis

### Wrappers Classes
```java
Integer intObj = Integer.valueOf(123);
int intPrim = intObj.intValue();

// Autoboxing/Unboxing (Java 5+)
Integer auto = 123;           // Autoboxing
int prim = auto;              // Unboxing
```

### Métodos de Conversão
```java
String str = "123";
int numero = Integer.parseInt(str);
double decimal = Double.parseDouble("12.34");

String textoInt = Integer.toString(456);
String textoDouble = Double.toString(78.9);
```

### Verificações de Valores
```java
System.out.println(Integer.MAX_VALUE);    // 2147483647
System.out.println(Double.POSITIVE_INFINITY);
System.out.println(Double.isNaN(0.0/0.0));
```

## 🧪 Exercícios Práticos

### Nível Iniciante
1. Declare variáveis de cada tipo primitivo
2. Teste conversões entre tipos compatíveis
3. Calcule área de figuras geométricas usando tipos apropriados

### Nível Intermediário
1. Implemente uma calculadora que trabalhe com diferentes tipos
2. Crie um programa que demonstre overflow/underflow
3. Desenvolva um conversor entre diferentes sistemas numéricos

### Nível Avançado
1. Implemente um sistema de tipos personalizado
2. Crie validadores para entrada de dados por tipo
3. Desenvolva um analisador de performance entre tipos

## 🎯 Boas Práticas

1. **Use `int` para números inteiros** na maioria dos casos
2. **Use `double` para números decimais** na maioria dos casos
3. **Use nomes descritivos** para variáveis
4. **Inicialize variáveis** antes de usar
5. **Cuidado com conversões** que podem perder dados
6. **Use constantes** para valores que não mudam
7. **Prefira tipos primitivos** quando possível (performance)

## 📖 Próximos Passos

Após dominar os tipos de dados, você estará pronto para:
- [Operadores](../operadores/) - Realizar operações com os dados
- [Controle de Fluxo](../controle_fluxo_condicionais/) - Tomar decisões no código
- [Arrays](../arrays_e_metodos/) - Trabalhar com coleções de dados

## 📚 Recursos Adicionais

- [Oracle Java Primitives](https://docs.oracle.com/javase/tutorial/java/nutsandbolts/datatypes.html)
- [Java Language Specification](https://docs.oracle.com/javase/specs/jls/se17/html/jls-4.html)
- [IEEE 754 Floating Point](https://en.wikipedia.org/wiki/IEEE_754) - Para entender decimais

---

**💡 Dica**: Escolher o tipo correto desde o início evita problemas futuros. Quando em dúvida, use `int` para inteiros e `double` para decimais!