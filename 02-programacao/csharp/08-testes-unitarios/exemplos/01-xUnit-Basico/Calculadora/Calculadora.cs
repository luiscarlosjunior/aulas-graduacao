// ============================================================
// Calculadora — Biblioteca a ser testada
// ============================================================

namespace Calculadora;

public class Calculadora
{
    public double Somar(double a, double b) => a + b;

    public double Subtrair(double a, double b) => a - b;

    public double Multiplicar(double a, double b) => a * b;

    public double Dividir(double a, double b)
    {
        if (b == 0)
            throw new DivideByZeroException("Não é possível dividir por zero.");
        return a / b;
    }

    public double Potencia(double base_, double expoente) =>
        Math.Pow(base_, expoente);

    public double RaizQuadrada(double numero)
    {
        if (numero < 0)
            throw new ArgumentException("Não é possível calcular raiz de número negativo.");
        return Math.Sqrt(numero);
    }

    public bool EhPrimo(int numero)
    {
        if (numero < 2) return false;
        for (int i = 2; i <= Math.Sqrt(numero); i++)
            if (numero % i == 0) return false;
        return true;
    }

    public double CalcularMedia(IEnumerable<double> numeros)
    {
        var lista = numeros.ToList();
        if (!lista.Any())
            throw new ArgumentException("Lista não pode ser vazia.");
        return lista.Average();
    }

    public int Fibonacci(int n)
    {
        if (n < 0) throw new ArgumentException("n deve ser maior ou igual a zero.");
        if (n == 0) return 0;
        if (n == 1) return 1;
        return Fibonacci(n - 1) + Fibonacci(n - 2);
    }
}

// ============================================================
// Serviço com dependência externa (para demonstrar Mocking)
// ============================================================

public interface ICalculadoraLogger
{
    void LogOperacao(string operacao, double resultado);
    IReadOnlyList<string> ObterHistorico();
}

public class CalculadoraComLog
{
    private readonly Calculadora _calc;
    private readonly ICalculadoraLogger _logger;

    public CalculadoraComLog(Calculadora calc, ICalculadoraLogger logger)
    {
        _calc   = calc;
        _logger = logger;
    }

    public double SomarComLog(double a, double b)
    {
        double resultado = _calc.Somar(a, b);
        _logger.LogOperacao($"Soma({a}, {b})", resultado);
        return resultado;
    }

    public IReadOnlyList<string> ObterHistorico() =>
        _logger.ObterHistorico();
}
