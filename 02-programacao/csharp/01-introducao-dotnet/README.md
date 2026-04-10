# 01 — Introdução ao .NET

> Nesta seção você vai entender o ecossistema .NET, como ele funciona, como configurar seu ambiente e executar seu primeiro programa em C#.

---

## O que é .NET?

**.NET** é uma plataforma de desenvolvimento criada pela Microsoft que permite criar aplicações para múltiplos sistemas operacionais (Windows, Linux, macOS). É composta por:

| Componente | O que faz |
|-----------|-----------|
| **Runtime (CLR)** | Executa o código compilado. Gerencia memória (Garbage Collector), threads e segurança |
| **SDK** | Ferramentas para compilar, publicar e gerenciar projetos (`dotnet` CLI) |
| **BCL** | Base Class Library — biblioteca padrão com milhares de classes prontas |
| **NuGet** | Gerenciador de pacotes (como npm para Node, pip para Python) |

### Linha do tempo — evolução do .NET

```
.NET Framework 1.0  (2002) — Somente Windows
.NET Framework 4.8  (2019) — Última versão do Framework clássico
.NET Core 1.0       (2016) — Multiplataforma, open source
.NET Core 3.1       (2019) — LTS, suporte a desktop Windows
.NET 5              (2020) — Unificação (fim do "Core" no nome)
.NET 6              (2021) — LTS, grande melhoria de performance
.NET 7              (2022) — Melhorias em AOT e performance
.NET 8              (2023) — LTS ATUAL — o que usamos neste curso
```

**Regra prática:** Use sempre a versão **LTS** (Long Term Support) mais recente em projetos reais. Hoje é o **.NET 8**.

---

## Instalação e Verificação

### Windows/macOS/Linux

1. Acesse https://dotnet.microsoft.com/download
2. Baixe o **.NET 8 SDK**
3. Instale e abra um terminal

```bash
# Verificar versão instalada
dotnet --version

# Listar todos os SDKs
dotnet --list-sdks

# Listar todos os runtimes
dotnet --list-runtimes

# Informações do ambiente
dotnet --info
```

---

## A CLI do .NET — Comandos Essenciais

A CLI (Command Line Interface) do .NET é poderosa. Você vai usá-la todo dia:

```bash
# Criar novo projeto console
dotnet new console -n NomeDoProjeto

# Criar projeto web API
dotnet new webapi -n MinhaApi

# Criar projeto de testes xUnit
dotnet new xunit -n MeusProjeto.Tests

# Listar todos os templates disponíveis
dotnet new list

# Compilar o projeto
dotnet build

# Executar o projeto
dotnet run

# Executar em modo watch (reinicia ao salvar)
dotnet watch run

# Publicar para produção
dotnet publish -c Release -o ./publicado

# Adicionar pacote NuGet
dotnet add package Newtonsoft.Json

# Remover pacote
dotnet remove package Newtonsoft.Json

# Restaurar dependências
dotnet restore

# Listar pacotes instalados
dotnet list package
```

---

## Estrutura de um Projeto

Quando você executa `dotnet new console -n HelloWorld`, o .NET cria:

```
HelloWorld/
├── HelloWorld.csproj    ← Arquivo de projeto (configurações, dependências)
├── Program.cs           ← Código-fonte principal
└── obj/                 ← Arquivos temporários (gerado automaticamente)
    └── ...
```

### O arquivo `.csproj`

```xml
<Project Sdk="Microsoft.NET.Sdk">

  <PropertyGroup>
    <!-- Tipo de saída: Exe (executável) ou Library (biblioteca) -->
    <OutputType>Exe</OutputType>

    <!-- Framework alvo: net8.0 é .NET 8 -->
    <TargetFramework>net8.0</TargetFramework>

    <!-- Habilita nullable reference types (C# 8+) -->
    <Nullable>enable</Nullable>

    <!-- Importa namespaces comuns automaticamente -->
    <ImplicitUsings>enable</ImplicitUsings>
  </PropertyGroup>

</Project>
```

### O `Program.cs` moderno (Top-Level Statements)

A partir do C# 9, você não precisa mais escrever a classe `Program` e o método `Main`. O código pode ser direto:

```csharp
// C# MODERNO (C# 9+) — top-level statements
Console.WriteLine("Olá, Mundo!");
```

Isso é equivalente ao estilo clássico:

```csharp
// C# CLÁSSICO — ainda funciona, mas é mais verboso
using System;

namespace HelloWorld
{
    class Program
    {
        static void Main(string[] args)
        {
            Console.WriteLine("Olá, Mundo!");
        }
    }
}
```

> Em projetos reais, você vai encontrar os dois estilos. Em times modernos, top-level statements são preferidos para programas simples.

---

## NuGet — Gerenciador de Pacotes

O NuGet é o repositório central de pacotes .NET. Acesse https://www.nuget.org para explorar.

```bash
# Instalar um pacote popular (JSON serialization)
dotnet add package Newtonsoft.Json --version 13.0.3

# O .csproj é atualizado automaticamente:
# <PackageReference Include="Newtonsoft.Json" Version="13.0.3" />
```

Exemplo de uso:

```csharp
using Newtonsoft.Json;

var produto = new { Nome = "Notebook", Preco = 3500.00 };
string json = JsonConvert.SerializeObject(produto);
Console.WriteLine(json); // {"Nome":"Notebook","Preco":3500.0}
```

---

## Ecossistema de Linguagens

O .NET suporta múltiplas linguagens que compilam para o mesmo bytecode (IL — Intermediate Language):

| Linguagem | Uso principal |
|-----------|--------------|
| **C#** | Linguagem principal, propósito geral |
| **F#** | Programação funcional, data science |
| **VB.NET** | Legacy, ainda usado em empresas antigas |

Este curso foca em **C#**, a linguagem dominante do ecossistema.

---

## Exemplo: Hello World

Veja o código em [`HelloWorld/Program.cs`](./HelloWorld/Program.cs):

```csharp
// Hello World em C# com .NET 8
Console.WriteLine("Olá, .NET!");
Console.WriteLine($"Versão do .NET: {Environment.Version}");
Console.WriteLine($"Sistema Operacional: {Environment.OSVersion}");

string nome = "Estudante";
int ano = DateTime.Now.Year;
Console.WriteLine($"Bem-vindo ao .NET, {nome}! Ano: {ano}");
```

Para executar:

```bash
cd HelloWorld
dotnet run
```

Saída esperada:
```
Olá, .NET!
Versão do .NET: 8.0.x
Sistema Operacional: ...
Bem-vindo ao .NET, Estudante! Ano: 2024
```

---

## Exercícios

1. Instale o .NET 8 SDK e verifique com `dotnet --version`
2. Crie um novo projeto console com `dotnet new console -n MeuHello`
3. Modifique o `Program.cs` para exibir seu nome e a data/hora atual
4. Adicione o pacote `Humanizer.Core` e use-o para exibir um número por extenso
5. Publique o projeto com `dotnet publish -c Release` e execute o binário gerado

---

**Próxima seção:** [02 — Fundamentos do C#](../02-fundamentos-csharp/)
