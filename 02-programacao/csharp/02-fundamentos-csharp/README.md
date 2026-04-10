# 02 — Fundamentos do C#

> Domine os blocos de construção da linguagem. Aqui você aprende os tipos, operadores, controle de fluxo e coleções que são a base de todo código C# profissional.

---

## Tipos de Dados

C# é uma linguagem **fortemente tipada** e **estaticamente tipada**: o tipo de cada variável é verificado em tempo de compilação.

### Tipos Primitivos

| Tipo | Tamanho | Range | Uso |
|------|---------|-------|-----|
| `byte` | 8 bits | 0 a 255 | IDs pequenos, bytes |
| `int` | 32 bits | ±2 bilhões | Uso geral |
| `long` | 64 bits | ±9 quintilhões | IDs grandes, timestamps |
| `float` | 32 bits | 7 dígitos | Gráficos (Unity) |
| `double` | 64 bits | 15-17 dígitos | Cálculos gerais |
| `decimal` | 128 bits | 28-29 dígitos | **Dinheiro/finanças** |
| `bool` | 1 bit | true/false | Flags, condições |
| `char` | 16 bits | Um caractere Unicode | Processamento de texto |
| `string` | variável | Texto | Texto geral |

```csharp
int     quantidade = 100;
double  preco      = 9.99;
decimal valorTotal = 9.99m;  // m = decimal literal
bool    ativo      = true;
char    inicial    = 'J';
string  nome       = "João";
```

### Inferência de Tipo com `var`

```csharp
var numero    = 42;          // int — ainda fortemente tipado!
var pi        = 3.14;        // double
var mensagem  = "Olá";       // string
var hoje      = DateTime.Now; // DateTime
```

> **var** não é `dynamic` — o tipo é resolvido em compilação. Use `var` para reduzir verbosidade, mas seja explícito quando clareza importa.

### Nullable Types

```csharp
int?    idade  = null;       // int pode ser nulo
string? email  = null;       // string pode ser nulo (C# 8+ com #nullable enable)

// Null-coalescing: use valor padrão se null
string nome = email ?? "Anônimo";

// Null-conditional: não lança exceção se null
int? tamanho = email?.Length;

// Null-coalescing assignment
email ??= "padrao@exemplo.com";
```

---

## Operadores

### Aritméticos
```csharp
int a = 10, b = 3;
Console.WriteLine(a + b);  // 13
Console.WriteLine(a - b);  // 7
Console.WriteLine(a * b);  // 30
Console.WriteLine(a / b);  // 3 (divisão inteira — trunca!)
Console.WriteLine(a % b);  // 1 (módulo/resto)
Console.WriteLine((double)a / b); // 3.333... (divisão real)
```

### Operador Ternário
```csharp
int idade = 20;
string tipo = idade >= 18 ? "Adulto" : "Menor";
```

### Switch Expression (C# 8+)
```csharp
string categoria = nota switch
{
    >= 90 => "Excelente",
    >= 70 => "Bom",
    >= 50 => "Regular",
    _     => "Insuficiente"
};
```

### Operadores Null
```csharp
string? s = null;
string resultado = s ?? "padrão";    // "padrão"
s ??= "atribuído";                   // s = "atribuído"
int? len = s?.Length;                // não lança NullReferenceException
```

---

## Controle de Fluxo

### If/Else
```csharp
if (temperatura > 35)
    Console.WriteLine("Muito quente!");
else if (temperatura > 25)
    Console.WriteLine("Quente");
else
    Console.WriteLine("Agradável");
```

### Loops
```csharp
// for
for (int i = 0; i < 10; i++)
    Console.WriteLine(i);

// foreach — preferido para iterar coleções
foreach (string nome in lista)
    Console.WriteLine(nome);

// while
while (condicao) { ... }

// do-while — executa pelo menos uma vez
do { ... } while (condicao);
```

---

## Strings — Recursos Essenciais

```csharp
// Interpolação (preferida)
string msg = $"Olá, {nome}! Você tem {idade} anos.";

// Verbatim — sem escape sequences
string caminho = @"C:\Users\joao\arquivo.txt";

// Raw string literal (C# 11+)
string json = """
    { "nome": "João" }
    """;

// Métodos úteis
texto.ToUpper();
texto.ToLower();
texto.Trim();
texto.Contains("palavra");
texto.Replace("velho", "novo");
texto.Split(',');
texto.StartsWith("pre");
texto.Substring(0, 5);
string.Join(", ", lista);
```

---

## Arrays e Coleções

```csharp
// Array fixo
int[] numeros = { 1, 2, 3, 4, 5 };

// List<T> — dinâmica, mais comum
var lista = new List<string>();
lista.Add("item");
lista.Remove("item");

// Dictionary<K,V>
var dict = new Dictionary<string, int>
{
    ["um"] = 1,
    ["dois"] = 2
};

// HashSet<T> — sem duplicatas
var conjunto = new HashSet<int> { 1, 2, 3 };
```

---

## Exemplos Práticos

Os exemplos estão em `exemplos/`:

| Pasta | O que demonstra |
|-------|----------------|
| [01-TiposDados](./exemplos/01-TiposDados/) | Todos os tipos, conversões, strings |
| [02-OperadoresEExpressoes](./exemplos/02-OperadoresEExpressoes/) | Todos os operadores |
| [03-ControleDeFluxo](./exemplos/03-ControleDeFluxo/) | if/else, switch, loops, pattern matching |
| [04-ArraysEColecoes](./exemplos/04-ArraysEColecoes/) | Array, List, Dictionary, Queue, Stack |

Para executar qualquer exemplo:
```bash
cd exemplos/01-TiposDados
dotnet run
```

---

## Exercícios

1. Crie um programa que calcula IMC (peso / altura²) e classifica: abaixo do peso, normal, sobrepeso, obeso
2. Escreva um conversor de temperatura (Celsius ↔ Fahrenheit ↔ Kelvin)
3. Implemente uma calculadora simples com switch expression
4. Crie um programa que lê N números e exibe: soma, média, máximo e mínimo
5. Implemente uma agenda de contatos com `Dictionary<string, string>` (nome → telefone)
6. Escreva o algoritmo FizzBuzz: para 1 a 100, imprima "Fizz" se divisível por 3, "Buzz" se por 5, "FizzBuzz" se por ambos

---

**Seção anterior:** [01 — Introdução ao .NET](../01-introducao-dotnet/)  
**Próxima seção:** [03 — Orientação a Objetos](../03-orientacao-objetos/)
