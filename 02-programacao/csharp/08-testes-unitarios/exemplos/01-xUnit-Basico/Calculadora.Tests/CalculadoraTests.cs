// ============================================================
// Testes xUnit — Padrão AAA (Arrange, Act, Assert)
// ============================================================

using Calculadora;
using FluentAssertions;
using Moq;
using Xunit;

namespace Calculadora.Tests;

// ============================================================
// TESTES BÁSICOS — [Fact]
// ============================================================

public class CalculadoraTestes
{
    private readonly Calculadora _calc = new();

    // Nomenclatura: Metodo_Cenario_ResultadoEsperado
    [Fact]
    public void Somar_DoisNumerosPositivos_RetornaSomaCorreta()
    {
        // Arrange — preparar o cenário
        double a = 10, b = 5;

        // Act — executar a ação
        double resultado = _calc.Somar(a, b);

        // Assert — verificar o resultado
        Assert.Equal(15, resultado);
    }

    [Fact]
    public void Somar_NumeroNegativoEPositivo_RetornaDiferenca()
    {
        // Arrange & Act (compacto quando simples)
        var resultado = _calc.Somar(-3, 7);

        // Assert
        Assert.Equal(4, resultado);
    }

    [Fact]
    public void Dividir_PorZero_LancaDivideByZeroException()
    {
        // Assert que uma exceção é lançada
        Assert.Throws<DivideByZeroException>(() => _calc.Dividir(10, 0));
    }

    [Fact]
    public void Dividir_PorZero_MensagemDeErroCorreta()
    {
        var ex = Assert.Throws<DivideByZeroException>(() => _calc.Dividir(10, 0));
        Assert.Contains("zero", ex.Message, StringComparison.OrdinalIgnoreCase);
    }

    [Fact]
    public void RaizQuadrada_NumeroNegativo_LancaArgumentException()
    {
        Action acao = () => _calc.RaizQuadrada(-1);
        Assert.Throws<ArgumentException>(acao);
    }

    [Fact]
    public void RaizQuadrada_Quatro_RetornaDois()
    {
        var resultado = _calc.RaizQuadrada(4);
        Assert.Equal(2.0, resultado);
    }
}

// ============================================================
// TESTES PARAMETRIZADOS — [Theory] + [InlineData]
// ============================================================

public class CalculadoraTeoriasTestes
{
    private readonly Calculadora _calc = new();

    [Theory]
    [InlineData(1,  2,  3)]   // 1 + 2 = 3
    [InlineData(0,  0,  0)]   // 0 + 0 = 0
    [InlineData(-5, 3, -2)]   // -5 + 3 = -2
    [InlineData(100, 200, 300)]
    public void Somar_VariosInputs_RetornaResultadoCorreto(double a, double b, double esperado)
    {
        var resultado = _calc.Somar(a, b);
        Assert.Equal(esperado, resultado);
    }

    [Theory]
    [InlineData(10, 5,  2)]
    [InlineData(9,  3,  3)]
    [InlineData(7,  2,  3.5)]
    public void Dividir_NumerosPorOutros_RetornaResultadoCorreto(double a, double b, double esperado)
    {
        var resultado = _calc.Dividir(a, b);
        Assert.Equal(esperado, resultado, precision: 5);
    }

    [Theory]
    [InlineData(2,  true)]
    [InlineData(3,  true)]
    [InlineData(5,  true)]
    [InlineData(7,  true)]
    [InlineData(11, true)]
    [InlineData(1,  false)]
    [InlineData(4,  false)]
    [InlineData(10, false)]
    [InlineData(0,  false)]
    public void EhPrimo_VariosNumeros_RetornaCorreto(int numero, bool esperado)
    {
        var resultado = _calc.EhPrimo(numero);
        Assert.Equal(esperado, resultado);
    }

    [Theory]
    [InlineData(0, 0)]
    [InlineData(1, 1)]
    [InlineData(2, 1)]
    [InlineData(3, 2)]
    [InlineData(4, 3)]
    [InlineData(5, 5)]
    [InlineData(10, 55)]
    public void Fibonacci_VariosTermos_RetornaCorreto(int n, int esperado)
    {
        var resultado = _calc.Fibonacci(n);
        Assert.Equal(esperado, resultado);
    }
}

// ============================================================
// TESTES COM FLUENT ASSERTIONS — Sintaxe mais legível
// ============================================================

public class CalculadoraFluentTestes
{
    private readonly Calculadora _calc = new();

    [Fact]
    public void Multiplicar_CincoVezesQuatro_DeveSerVinte()
    {
        var resultado = _calc.Multiplicar(5, 4);

        // FluentAssertions — muito mais legível que Assert.Equal!
        resultado.Should().Be(20);
    }

    [Fact]
    public void CalcularMedia_ListaDeNumeros_RetornaMediaCorreta()
    {
        var numeros = new[] { 1.0, 2.0, 3.0, 4.0, 5.0 };

        var media = _calc.CalcularMedia(numeros);

        media.Should().Be(3.0);
        media.Should().BeGreaterThan(0);
        media.Should().BeLessThan(10);
    }

    [Fact]
    public void CalcularMedia_ListaVazia_DeveLancarExcecao()
    {
        var listaVazia = Enumerable.Empty<double>();

        // FluentAssertions para exceções
        Action acao = () => _calc.CalcularMedia(listaVazia);

        acao.Should().Throw<ArgumentException>()
            .WithMessage("*vazia*");
    }

    [Fact]
    public void Potencia_DoisAoCubo_DeveSerOito()
    {
        var resultado = _calc.Potencia(2, 3);
        resultado.Should().Be(8);
    }

    [Fact]
    public void EhPrimo_ListaDePrimos_TodosDevemSerPrimos()
    {
        var primos = new[] { 2, 3, 5, 7, 11, 13 };

        // Assert em coleção com FluentAssertions
        primos.Should().OnlyContain(n => _calc.EhPrimo(n));
        primos.Should().HaveCount(6);
        primos.Should().BeInAscendingOrder();
    }
}

// ============================================================
// TESTES COM MOCK — Simulando dependências externas
// ============================================================

public class CalculadoraComLogTestes
{
    [Fact]
    public void SomarComLog_ChamaLoggerComResultadoCorreto()
    {
        // Arrange
        var calc       = new Calculadora();
        var loggerMock = new Mock<ICalculadoraLogger>(); // Cria mock da interface

        var calcComLog = new CalculadoraComLog(calc, loggerMock.Object);

        // Act
        double resultado = calcComLog.SomarComLog(10, 5);

        // Assert — verifica se o método foi chamado
        Assert.Equal(15, resultado);

        // Verifica que LogOperacao foi chamado exatamente 1 vez
        loggerMock.Verify(
            l => l.LogOperacao(It.IsAny<string>(), 15.0),
            Times.Once
        );
    }

    [Fact]
    public void SomarComLog_VerificaParametrosDoLog()
    {
        // Arrange
        var calc       = new Calculadora();
        var loggerMock = new Mock<ICalculadoraLogger>();
        var calcComLog = new CalculadoraComLog(calc, loggerMock.Object);

        // Act
        calcComLog.SomarComLog(3, 7);

        // Assert — verifica parâmetros específicos
        loggerMock.Verify(l => l.LogOperacao(
            It.Is<string>(s => s.Contains("3") && s.Contains("7")),
            10.0
        ), Times.Once);
    }

    [Fact]
    public void ObterHistorico_RetornaHistoricoDoLogger()
    {
        // Arrange
        var calc       = new Calculadora();
        var loggerMock = new Mock<ICalculadoraLogger>();

        // Setup do mock — configura o que retorna
        var historico = new List<string> { "Soma(1, 2) = 3", "Soma(5, 5) = 10" };
        loggerMock.Setup(l => l.ObterHistorico())
                  .Returns(historico.AsReadOnly());

        var calcComLog = new CalculadoraComLog(calc, loggerMock.Object);

        // Act
        var resultado = calcComLog.ObterHistorico();

        // Assert
        resultado.Should().HaveCount(2);
        resultado.Should().ContainInOrder("Soma(1, 2) = 3", "Soma(5, 5) = 10");
    }
}

// ============================================================
// FIXTURES E DADOS EXTERNOS — [MemberData] e [ClassData]
// ============================================================

public class DivisaoData : TheoryData<double, double, double>
{
    public DivisaoData()
    {
        Add(10, 2,   5);
        Add(9,  3,   3);
        Add(1,  4,   0.25);
        Add(-8, 2,  -4);
    }
}

public class CalculadoraMemberDataTestes
{
    private readonly Calculadora _calc = new();

    [Theory]
    [ClassData(typeof(DivisaoData))]
    public void Dividir_UsandoClassData_RetornaResultadoCorreto(double a, double b, double esperado)
    {
        var resultado = _calc.Dividir(a, b);
        resultado.Should().BeApproximately(esperado, precision: 0.0001);
    }
}
