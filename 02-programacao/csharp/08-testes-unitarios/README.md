# 08 — Testes Unitários com xUnit

> "Código sem testes não é código de produção." — este é o sentimento dominante no mercado. Saber escrever bons testes é uma habilidade que diferencia profissionais no mercado de trabalho.

---

## Por que Testar?

- **Confiança para refatorar** sem quebrar o sistema
- **Documentação viva** — testes mostram como o código é usado
- **Detectar regressões** antes de chegar ao usuário
- **Design melhor** — código testável é código com baixo acoplamento
- **Obrigatório** em equipes sérias: CI/CD bloqueia merge se testes falharem

---

## Pirâmide de Testes

```
         /\
        /  \
       / E2E\      ← Poucos, lentos, caros (Selenium, Playwright)
      /------\
     /  Integ. \   ← Médio volume (banco real, HTTP real)
    /------------\
   / Unit Tests   \ ← Muitos, rápidos, baratos (xUnit, NUnit, MSTest)
  /--------------/
```

> Foco principal: **testes unitários**. São rápidos (ms), não precisam de infra e têm alto ROI.

---

## Configuração — xUnit

```bash
# Criar projeto de testes
dotnet new xunit -n MinhaApp.Tests

# Adicionar referência ao projeto que será testado
dotnet add reference ../MinhaApp/MinhaApp.csproj

# Adicionar Moq (mocking) e FluentAssertions
dotnet add package Moq
dotnet add package FluentAssertions

# Executar testes
dotnet test

# Com saída detalhada
dotnet test --verbosity normal

# Rodar apenas testes de uma classe
dotnet test --filter "FullyQualifiedName~CalculadoraTestes"
```

---

## Padrão AAA — Arrange, Act, Assert

```csharp
[Fact]
public void Somar_DoisPositivos_RetornaSomaCorreta()
{
    // ARRANGE — preparar o cenário
    var calc = new Calculadora();
    double a = 10, b = 5;

    // ACT — executar a ação
    double resultado = calc.Somar(a, b);

    // ASSERT — verificar o resultado
    Assert.Equal(15, resultado);
}
```

---

## [Fact] e [Theory]

```csharp
// [Fact] — teste com dados fixos
[Fact]
public void Dividir_PorZero_LancaExcecao()
{
    var calc = new Calculadora();
    Assert.Throws<DivideByZeroException>(() => calc.Dividir(10, 0));
}

// [Theory] + [InlineData] — teste parametrizado
[Theory]
[InlineData(2,  true)]
[InlineData(3,  true)]
[InlineData(4,  false)]
[InlineData(1,  false)]
public void EhPrimo_NumeroVariados_RetornaCorreto(int numero, bool esperado)
{
    var resultado = new Calculadora().EhPrimo(numero);
    Assert.Equal(esperado, resultado);
}
```

---

## Convenção de Nomes

```
NomeMetodo_Cenario_ResultadoEsperado
```

Exemplos:
```
Somar_DoisPositivos_RetornaSomaCorreta
Dividir_PorZero_LancaDivideByZeroException
EhPrimo_NumeroNegativo_RetornaFalse
Salvar_ProdutoValido_InserteNoBanco
BuscarPorId_IdInexistente_RetornaNull
```

---

## FluentAssertions — Mais Legível

```csharp
// Assert padrão do xUnit
Assert.Equal(15, resultado);
Assert.True(lista.Count > 0);
Assert.NotNull(objeto);

// FluentAssertions — mais expressivo e erros mais claros
resultado.Should().Be(15);
lista.Should().NotBeEmpty();
objeto.Should().NotBeNull();

// Para coleções
lista.Should().HaveCount(3);
lista.Should().Contain("item");
lista.Should().BeInAscendingOrder();
lista.Should().OnlyContain(x => x > 0);

// Para exceções
Action acao = () => calc.Dividir(10, 0);
acao.Should().Throw<DivideByZeroException>()
    .WithMessage("*zero*");

// Para strings
nome.Should().StartWith("Jo");
nome.Should().HaveLength(5);
nome.Should().NotBeNullOrEmpty();
```

---

## Mocking com Moq

```csharp
// Interface a ser mockada
public interface IEmailService
{
    Task EnviarAsync(string para, string assunto);
}

// No teste:
[Fact]
public async Task ProcessarPedido_PedidoValido_EnviaEmailDeConfirmacao()
{
    // Arrange
    var emailMock = new Mock<IEmailService>();
    var servico   = new PedidoService(emailMock.Object);

    // Act
    await servico.ProcessarPedidoAsync(new Pedido { ClienteEmail = "joao@email.com" });

    // Assert — verifica que o método foi chamado
    emailMock.Verify(
        e => e.EnviarAsync("joao@email.com", It.IsAny<string>()),
        Times.Once
    );
}

// Setup — define o que o mock retorna
emailMock.Setup(e => e.EnviarAsync(It.IsAny<string>(), It.IsAny<string>()))
         .ReturnsAsync(true);

// Setup com exceção
emailMock.Setup(e => e.EnviarAsync(It.IsAny<string>(), It.IsAny<string>()))
         .ThrowsAsync(new SmtpException("Servidor indisponível"));
```

---

## Exemplo Prático

O exemplo [`01-xUnit-Basico/`](./exemplos/01-xUnit-Basico/) contém dois projetos:

1. **`Calculadora/`** — biblioteca com a lógica a ser testada
2. **`Calculadora.Tests/`** — projeto de testes com xUnit

```bash
cd exemplos/01-xUnit-Basico/Calculadora.Tests
dotnet test

# Saída esperada:
# Passed! - Failed: 0, Passed: XX, Skipped: 0, Total: XX
```

---

## Exercícios

1. Adicione testes para uma classe `BancoService` (saque, depósito, transferência)
2. Mock a interface `IProdutoRepository` e teste o `ProdutoService`
3. Crie testes para casos de borda: lista vazia, valor nulo, número máximo de int
4. Use `[MemberData]` com classe `TheoryData<>` para alimentar testes com dados complexos
5. Configure o pipeline de CI (GitHub Actions) para rodar `dotnet test` automaticamente

---

**Seção anterior:** [07 — Entity Framework Core](../07-entity-framework/)  
**Próxima seção:** [09 — IA & Programação Genética](../09-ia-programacao-genetica/)
