# C# e .NET — Trilha de Aprendizado Completa

> **De zero a profissional**: este guia foi estruturado por um professor com experiência no mercado para levar você do básico ao avançado em C# e .NET, com foco direto nas habilidades exigidas pelas empresas.

---

## 🎯 Por que aprender C# e .NET?

O **.NET** é uma das plataformas mais utilizadas no mercado corporativo brasileiro e mundial. Com C# você pode construir:

- **APIs REST** com ASP.NET Core (back-end de aplicações web e mobile)
- **Aplicações desktop** com WPF/WinForms/MAUI
- **Microsserviços** e sistemas distribuídos
- **Jogos** com Unity
- **Aplicações cloud** no Azure

Segundo o Stack Overflow Developer Survey, C# está consistentemente entre as **10 linguagens mais usadas no mundo** e o .NET entre os frameworks mais populares. A demanda no mercado brasileiro por desenvolvedores .NET é enorme, especialmente em fintechs, bancos, consultorias e grandes empresas.

---

## Pré-requisitos

- Conhecimento básico de lógica de programação
- Familiaridade com linha de comando (terminal/prompt)
- Vontade de aprender

---

## Configuração do Ambiente

### 1. Instalar o .NET SDK 8

Acesse https://dotnet.microsoft.com/download e baixe o **.NET 8 SDK**.

Verifique a instalação:
```bash
dotnet --version
# Saída esperada: 8.x.x

dotnet --list-sdks
# Lista todos os SDKs instalados
```

### 2. Editor recomendado

- **Visual Studio 2022 Community** (gratuito, Windows) — IDE completa
- **Visual Studio Code** + extensão **C# Dev Kit** — leve, multiplataforma
- **JetBrains Rider** (pago, mas excelente) — muito usado em empresas

### 3. Primeiro teste

```bash
dotnet new console -n MeuPrimeiroProjeto
cd MeuPrimeiroProjeto
dotnet run
# Saída: Hello, World!
```

---

## Trilha de Aprendizado

| # | Seção | O que você vai aprender | Tempo estimado |
|---|-------|------------------------|----------------|
| 01 | [Introdução ao .NET](./01-introducao-dotnet/) | Runtime, SDK, CLI, NuGet, ecossistema | 2–3 horas |
| 02 | [Fundamentos do C#](./02-fundamentos-csharp/) | Tipos, operadores, controle de fluxo, coleções | 8–12 horas |
| 03 | [Orientação a Objetos](./03-orientacao-objetos/) | Classes, herança, interfaces, polimorfismo | 10–15 horas |
| 04 | [C# Avançado](./04-csharp-avancado/) | Generics, LINQ, async/await, delegates | 12–18 horas |
| 05 | [.NET Core & DI](./05-dotnet-core/) | Dependency Injection, Host, Configuration | 6–8 horas |
| 06 | [ASP.NET Core](./06-aspnet-core/) | Minimal API, REST, Swagger, Middleware | 15–20 horas |
| 07 | [Entity Framework Core](./07-entity-framework/) | ORM, Code First, Migrations, CRUD | 10–15 horas |
| 08 | [Testes Unitários](./08-testes-unitarios/) | xUnit, AAA pattern, Moq, boas práticas | 8–12 horas |
| 09 | [IA & Programação Genética](./09-ia-programacao-genetica/) | Algoritmos evolutivos, árvores de expressão | 20+ horas |

**Tempo total estimado:** 90–120 horas para completar toda a trilha com prática.

---

## Dicas para Entrevistas de Emprego

Os tópicos mais cobrados em entrevistas para vagas .NET:

1. **Diferença entre `class` e `struct`** — value type vs reference type
2. **O que são delegates, Action e Func?** — fundamentos de LINQ e eventos
3. **Como funciona o Garbage Collector?** — gerenciamento de memória
4. **`async/await` vs threads** — programação assíncrona
5. **Princípios SOLID** — especialmente S, O e D
6. **Diferença entre `IEnumerable` e `IQueryable`** — crítico para EF Core
7. **O que é Dependency Injection?** — obrigatório para .NET moderno
8. **Quando usar `ref`, `out` e `in`?** — passagem de parâmetros
9. **Nullable reference types** — C# 8+ feature importante
10. **Diferença entre `.FirstOrDefault()` e `.SingleOrDefault()`** — LINQ

---

## Recursos Complementares

- [Documentação oficial .NET](https://docs.microsoft.com/dotnet/)
- [C# Language Reference](https://docs.microsoft.com/dotnet/csharp/language-reference/)
- [ASP.NET Core docs](https://docs.microsoft.com/aspnet/core/)
- [EF Core docs](https://docs.microsoft.com/ef/core/)
- [Padrões de design em C#](https://refactoring.guru/design-patterns/csharp)

---

*Desenvolvido para a disciplina de Programação — Curso de Ciência da Computação*
