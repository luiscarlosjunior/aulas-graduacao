using TelemetryRouteOptimization.Models;

namespace TelemetryRouteOptimization.GP;

/// <summary>
/// Representa um indivíduo (solução) no contexto de otimização de rotas
/// </summary>
public class RouteIndividual
{
    public List<Delivery> DeliverySequence { get; set; } = new();
    public TelemetryConfig TelemetryConfig { get; set; } = new();
    public double Fitness { get; set; } = double.MinValue;
    public int Generation { get; set; }
    
    // Métricas da rota
    public double TotalDistance { get; set; }
    public double TotalTime { get; set; }
    public double FuelCost { get; set; }
    public double DataTransmissionCost { get; set; }
    public double LatePenalty { get; set; }
    
    public RouteIndividual()
    {
    }
    
    public RouteIndividual Clone()
    {
        return new RouteIndividual
        {
            DeliverySequence = new List<Delivery>(DeliverySequence),
            TelemetryConfig = new TelemetryConfig
            {
                DataTransmissionInterval = TelemetryConfig.DataTransmissionInterval,
                UseWiFiWhenAvailable = TelemetryConfig.UseWiFiWhenAvailable,
                CompressData = TelemetryConfig.CompressData,
                MaxQueueSize = TelemetryConfig.MaxQueueSize,
                DataPriority = new Dictionary<string, int>(TelemetryConfig.DataPriority)
            },
            Fitness = Fitness,
            Generation = Generation,
            TotalDistance = TotalDistance,
            TotalTime = TotalTime,
            FuelCost = FuelCost,
            DataTransmissionCost = DataTransmissionCost,
            LatePenalty = LatePenalty
        };
    }
    
    public override string ToString()
    {
        return $"Fitness: {Fitness:F2} | " +
               $"Distância: {TotalDistance:F1}km | " +
               $"Tempo: {TotalTime:F0}min | " +
               $"Combustível: {FuelCost:F1}L | " +
               $"Custo Dados: R${DataTransmissionCost:F2} | " +
               $"Entregas: {DeliverySequence.Count}";
    }
}

/// <summary>
/// População de indivíduos de rota
/// </summary>
public class RoutePopulation
{
    public List<RouteIndividual> Individuals { get; set; } = new();
    public int Generation { get; set; }
    
    public RouteIndividual BestIndividual => 
        Individuals.OrderByDescending(i => i.Fitness).First();
    
    public double AverageFitness => 
        Individuals.Average(i => i.Fitness);
    
    public double BestFitness => BestIndividual.Fitness;
    
    public void Add(RouteIndividual individual)
    {
        individual.Generation = Generation;
        Individuals.Add(individual);
    }
    
    public string GetStatistics()
    {
        var best = BestIndividual;
        return $"Gen {Generation}: " +
               $"Melhor Fitness={BestFitness:F2} | " +
               $"Média={AverageFitness:F2} | " +
               $"Melhor Dist={best.TotalDistance:F1}km | " +
               $"Tempo={best.TotalTime:F0}min";
    }
}
