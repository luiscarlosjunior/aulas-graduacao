using BasicGPFramework;

/// <summary>
/// Demonstração do framework básico de Programação Genética
/// Problema: Regressão Simbólica - descobrir a função f(x) = x^2 + 2*x + 1
/// </summary>
class Program
{
    static void Main(string[] args)
    {
        Console.WriteLine("╔════════════════════════════════════════════════════════════╗");
        Console.WriteLine("║   Framework Básico de Programação Genética em C#          ║");
        Console.WriteLine("║   Exemplo: Regressão Simbólica                             ║");
        Console.WriteLine("╚════════════════════════════════════════════════════════════╝\n");
        
        // Problema: Descobrir f(x) = x^2 + 2*x + 1
        Console.WriteLine("Objetivo: Descobrir a função f(x) = x² + 2x + 1");
        Console.WriteLine("Usando apenas os dados de entrada e saída\n");
        
        // Gera dados de treino
        var trainingData = GenerateTrainingData();
        
        Console.WriteLine("Dados de Treino:");
        Console.WriteLine("x\t| f(x)");
        Console.WriteLine("--------|--------");
        foreach (var (x, y) in trainingData.Take(10))
        {
            Console.WriteLine($"{x:F1}\t| {y:F1}");
        }
        Console.WriteLine($"... ({trainingData.Count} pontos no total)\n");
        
        // Configura GP
        var config = new GPConfig
        {
            PopulationSize = 200,
            MaxGenerations = 100,
            MaxDepth = 6,
            CrossoverRate = 0.8,
            MutationRate = 0.1,
            ElitismRate = 0.1,
            TournamentSize = 3,
            Functions = new List<string> { "+", "-", "*", "/" },
            Terminals = new List<string> { "x" },
            RandomSeed = 42 // Para reprodutibilidade
        };
        
        // Define função de fitness (Erro Quadrático Médio negativo)
        double FitnessFunction(Individual individual)
        {
            double totalError = 0;
            int validEvaluations = 0;
            
            foreach (var (x, expectedY) in trainingData)
            {
                try
                {
                    var predictedY = individual.Evaluate(new Dictionary<string, double> { ["x"] = x });
                    
                    // Verifica se o resultado é válido
                    if (double.IsFinite(predictedY))
                    {
                        totalError += Math.Pow(expectedY - predictedY, 2);
                        validEvaluations++;
                    }
                    else
                    {
                        totalError += 1000; // Penaliza resultados inválidos
                    }
                }
                catch
                {
                    totalError += 1000; // Penaliza erros de avaliação
                }
            }
            
            if (validEvaluations == 0)
                return double.MinValue;
            
            // Retorna MSE negativo (queremos maximizar fitness)
            var mse = totalError / validEvaluations;
            
            // Adiciona penalidade por tamanho (parsimony pressure)
            var sizePenalty = individual.GetSize() * 0.001;
            
            return -(mse + sizePenalty);
        }
        
        // Executa GP
        var engine = new GPEngine(config, FitnessFunction);
        var bestSolution = engine.Run();
        
        // Testa a melhor solução
        Console.WriteLine("\n=== Testando a Melhor Solução ===\n");
        Console.WriteLine("x\t| Real\t| Predito\t| Erro");
        Console.WriteLine("--------|-------|---------------|--------");
        
        var testData = GenerateTrainingData(); // Usa mesmos dados para demonstração
        double totalTestError = 0;
        
        foreach (var (x, realY) in testData.Take(15))
        {
            var predictedY = bestSolution.Evaluate(new Dictionary<string, double> { ["x"] = x });
            var error = Math.Abs(realY - predictedY);
            totalTestError += error;
            
            Console.WriteLine($"{x:F1}\t| {realY:F1}\t| {predictedY:F2}\t\t| {error:F2}");
        }
        
        var mae = totalTestError / 15;
        Console.WriteLine($"\nErro Absoluto Médio (MAE): {mae:F4}");
        
        Console.WriteLine("\n╔════════════════════════════════════════════════════════════╗");
        Console.WriteLine("║   Execução Concluída com Sucesso!                          ║");
        Console.WriteLine("╚════════════════════════════════════════════════════════════╝");
    }
    
    /// <summary>
    /// Gera dados de treino para f(x) = x^2 + 2*x + 1
    /// </summary>
    static List<(double x, double y)> GenerateTrainingData()
    {
        var data = new List<(double, double)>();
        
        // Gera pontos de -10 a 10
        for (double x = -10; x <= 10; x += 0.5)
        {
            double y = x * x + 2 * x + 1; // f(x) = x^2 + 2x + 1
            data.Add((x, y));
        }
        
        return data;
    }
}
