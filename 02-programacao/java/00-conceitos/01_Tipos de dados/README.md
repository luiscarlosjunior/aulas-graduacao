# Tipos de Dados em Java

Esta seção apresenta os tipos de dados fundamentais em Java, incluindo tipos primitivos e a classe String para manipulação de texto.

## 🎯 Objetivos

- Compreender os tipos primitivos em Java
- Aprender sobre a classe String
- Entender conversões entre tipos
- Conhecer o escopo de variáveis

## 📋 Tipos Primitivos em Java

Java possui 8 tipos primitivos divididos em categorias:

### 1. Tipos Inteiros

| Tipo  | Tamanho | Faixa de Valores                    | Exemplo      |
|-------|---------|-------------------------------------|--------------|
| `byte`  | 8 bits  | -128 a 127                         | `byte x = 100;` |
| `short` | 16 bits | -32.768 a 32.767                   | `short y = 32000;` |
| `int`   | 32 bits | -2.147.483.648 a 2.147.483.647     | `int idade = 25;` |
| `long`  | 64 bits | -9.223.372.036.854.775.808 a ...   | `long z = 123L;` |

### 2. Tipos de Ponto Flutuante

| Tipo     | Tamanho | Precisão        | Exemplo             |
|----------|---------|-----------------|---------------------|
| `float`  | 32 bits | ~7 dígitos      | `float altura = 1.85f;` |
| `double` | 64 bits | ~15 dígitos     | `double pi = 3.141592653589793;` |

### 3. Tipo Caractere

| Tipo   | Tamanho | Descrição                  | Exemplo           |
|--------|---------|----------------------------|-------------------|
| `char` | 16 bits | Caractere Unicode (UTF-16) | `char letra = 'A';` |

### 4. Tipo Lógico

| Tipo      | Valores          | Exemplo                |
|-----------|------------------|------------------------|
| `boolean` | `true` ou `false` | `boolean ativo = true;` |

## 📄 Análise dos Exemplos

### [TiposDados.java](TiposDados.java)
Demonstra declaração e uso dos tipos primitivos básicos:

```java
// Tipos numéricos
int idade = 5;                    // Inteiro
float altura = 1.85f;             // Ponto flutuante (note o 'f')
double numeroPI = 3.1415926562;   // Dupla precisão

// Caractere e lógico
char umaLetra = 'D';              // Caractere único
boolean souPessoa = true;         // Valor lógico

// String (classe, não primitivo)
String meuTexto = "Hello";        // Texto
```

### [TestaCaracteres.java](TestaCaracteres.java)
Explora as peculiaridades do tipo `char`:

```java
char letra = 'a';        // Caractere literal
char valor = 65;         // Valor ASCII (resultado: 'A')
```

**Conceitos importantes:**
- `char` pode armazenar valores numéricos (0 a 65535)
- Conversão automática de números para caracteres Unicode
- Operações aritméticas com char requerem cast explícito

### [TestaConversao.java](TestaConversao.java)
Demonstra conversões entre tipos (casting):

```java
// Conversão implícita (widening)
int numero = 10;
double decimal = numero;  // int -> double (automático)

// Conversão explícita (narrowing)
double valor = 3.14;
int inteiro = (int) valor;  // double -> int (precisa cast)
```

### [TestaPontoFlutuante.java](TestaPontoFlutuante.java)
Explora características dos números decimais:

- Precisão limitada de `float` vs `double`
- Problemas de arredondamento
- Comparação de números decimais

### [TestaEscopo.java](TestaEscopo.java)
Demonstra o escopo de variáveis:

```java
public class Exemplo {
    static int global = 10;  // Escopo da classe
    
    public static void main(String[] args) {
        int local = 20;      // Escopo do método
        
        if (true) {
            int bloco = 30;  // Escopo do bloco
        }
        // 'bloco' não é acessível aqui
    }
}
```

## 🔧 Conceitos Importantes

### 1. Inicialização de Variáveis
```java
int x;           // Declaração
x = 10;          // Inicialização
int y = 20;      // Declaração + inicialização
```

### 2. Literais Especiais
```java
float f = 3.14f;     // 'f' indica float
long l = 123L;       // 'L' indica long
double d = 2.5d;     // 'd' indica double (opcional)
```

### 3. Valores Padrão
- Números: `0` (int, float, double) ou `0L` (long)
- boolean: `false`
- char: `'\u0000'` (caractere nulo)
- Referências (String, objetos): `null`

### 4. Conversões (Casting)

**Implícitas (automáticas):**
```
byte → short → int → long → float → double
char → int
```

**Explícitas (requerem cast):**
```java
double d = 3.14;
int i = (int) d;     // i = 3 (perde a parte decimal)
```

## 🚀 Como Executar os Exemplos

```bash
# Navegar até o diretório
cd "01_Tipos de dados"

# Compilar um exemplo
javac TiposDados.java

# Executar
java TiposDados
```

## 💡 Experimentos Sugeridos

1. **Overflow**: Tente atribuir valores maiores que o limite do tipo
2. **Precisão**: Compare operações com `float` e `double`
3. **Conversões**: Experimente diferentes tipos de casting
4. **Unicode**: Use valores numéricos para criar caracteres especiais

### Exemplo de Overflow:
```java
byte b = 127;       // Valor máximo
b++;                // Resulta em -128 (overflow)
```

## ❗ Erros Comuns

1. **Esquecer o 'f' em float**: `float x = 3.14;` (erro - precisa ser `3.14f`)
2. **Perda de precisão sem cast**: `int x = 3.14;` (erro de compilação)
3. **Comparação de decimais**: `if(0.1 + 0.2 == 0.3)` (pode ser false devido à precisão)

## 📚 Conceitos Relacionados

- **Wrapper Classes**: `Integer`, `Double`, `Boolean`, etc.
- **Autoboxing/Unboxing**: Conversão automática entre primitivos e wrappers
- **String Pool**: Como Java otimiza strings
- **Imutabilidade**: Strings são imutáveis em Java

---

**Próximo**: [Controle de Fluxo (if)](../02_Controle%20de%20Fluxo%20%28if%29/) - Aprenda sobre estruturas condicionais.