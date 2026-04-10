using TelemetryRouteOptimization.Models;
using TelemetryRouteOptimization.GP;

namespace TelemetryRouteOptimization.Optimization;

/// <summary>
/// Operadores genéticos especializados para otimização de rotas
/// </summary>
public class RouteGeneticOperators
{
    private readonly Random _random;
    
    public RouteGeneticOperators(int? seed = null)
    {
        _random = seed.HasValue ? new Random(seed.Value) : new Random();
    }
    
    /// <summary>
    /// Order Crossover (OX) - preserva ordem relativa das entregas
    /// </summary>
    public (RouteIndividual, RouteIndividual) OrderCrossover(
        RouteIndividual parent1, 
        RouteIndividual parent2)
    {
        var size = parent1.DeliverySequence.Count;
        
        if (size <= 2)
        {
            return (parent1.Clone(), parent2.Clone());
        }
        
        var offspring1 = new RouteIndividual();
        var offspring2 = new RouteIndividual();
        
        // Seleciona dois pontos de corte
        int point1 = _random.Next(size);
        int point2 = _random.Next(point1 + 1, size);
        
        // Offspring 1
        offspring1.DeliverySequence = CreateOffspringWithOX(
            parent1.DeliverySequence, 
            parent2.DeliverySequence, 
            point1, point2);
        
        // Offspring 2
        offspring2.DeliverySequence = CreateOffspringWithOX(
            parent2.DeliverySequence, 
            parent1.DeliverySequence, 
            point1, point2);
        
        // Herda configuração de telemetria (média dos pais)
        offspring1.TelemetryConfig = InheritTelemetryConfig(
            parent1.TelemetryConfig, 
            parent2.TelemetryConfig);
        
        offspring2.TelemetryConfig = InheritTelemetryConfig(
            parent2.TelemetryConfig, 
            parent1.TelemetryConfig);
        
        return (offspring1, offspring2);
    }
    
    /// <summary>
    /// Cria offspring usando Order Crossover
    /// </summary>
    private List<Delivery> CreateOffspringWithOX(
        List<Delivery> parent1, 
        List<Delivery> parent2, 
        int point1, 
        int point2)
    {
        var offspring = new List<Delivery>(new Delivery[parent1.Count]);
        var used = new HashSet<int>();
        
        // Copia segmento do pai 1
        for (int i = point1; i < point2; i++)
        {
            offspring[i] = parent1[i];
            used.Add(parent1[i].Id);
        }
        
        // Preenche restante com ordem do pai 2
        int currentPos = point2;
        for (int i = 0; i < parent2.Count; i++)
        {
            int checkPos = (point2 + i) % parent2.Count;
            
            if (!used.Contains(parent2[checkPos].Id))
            {
                offspring[currentPos % offspring.Count] = parent2[checkPos];
                currentPos++;
            }
        }
        
        return offspring;
    }
    
    /// <summary>
    /// Herda configuração de telemetria combinando pais
    /// </summary>
    private TelemetryConfig InheritTelemetryConfig(
        TelemetryConfig parent1, 
        TelemetryConfig parent2)
    {
        return new TelemetryConfig
        {
            // Média dos intervalos de transmissão
            DataTransmissionInterval = (parent1.DataTransmissionInterval + 
                                       parent2.DataTransmissionInterval) / 2,
            
            // Escolhe aleatoriamente
            UseWiFiWhenAvailable = _random.NextDouble() < 0.5 
                ? parent1.UseWiFiWhenAvailable 
                : parent2.UseWiFiWhenAvailable,
            
            CompressData = _random.NextDouble() < 0.5 
                ? parent1.CompressData 
                : parent2.CompressData,
            
            MaxQueueSize = (parent1.MaxQueueSize + parent2.MaxQueueSize) / 2,
            
            DataPriority = new Dictionary<string, int>(parent1.DataPriority)
        };
    }
    
    /// <summary>
    /// Operador de mutação especializado para rotas
    /// </summary>
    public RouteIndividual Mutate(RouteIndividual individual, double mutationRate)
    {
        if (_random.NextDouble() > mutationRate)
            return individual;
        
        var mutated = individual.Clone();
        
        // Escolhe tipo de mutação
        var mutationType = _random.Next(4);
        
        switch (mutationType)
        {
            case 0: // Swap Mutation - troca duas entregas
                SwapMutation(mutated);
                break;
            
            case 1: // Inversion Mutation - inverte um segmento
                InversionMutation(mutated);
                break;
            
            case 2: // Telemetry Config Mutation
                TelemetryConfigMutation(mutated);
                break;
            
            case 3: // Insert Mutation - remove e insere em nova posição
                InsertMutation(mutated);
                break;
        }
        
        return mutated;
    }
    
    /// <summary>
    /// Troca duas entregas aleatórias de posição
    /// </summary>
    private void SwapMutation(RouteIndividual individual)
    {
        if (individual.DeliverySequence.Count < 2) return;
        
        int pos1 = _random.Next(individual.DeliverySequence.Count);
        int pos2 = _random.Next(individual.DeliverySequence.Count);
        
        var temp = individual.DeliverySequence[pos1];
        individual.DeliverySequence[pos1] = individual.DeliverySequence[pos2];
        individual.DeliverySequence[pos2] = temp;
    }
    
    /// <summary>
    /// Inverte a ordem de um segmento da rota
    /// </summary>
    private void InversionMutation(RouteIndividual individual)
    {
        if (individual.DeliverySequence.Count < 2) return;
        
        int point1 = _random.Next(individual.DeliverySequence.Count);
        int point2 = _random.Next(point1 + 1, individual.DeliverySequence.Count + 1);
        
        individual.DeliverySequence.Reverse(point1, point2 - point1);
    }
    
    /// <summary>
    /// Move uma entrega para uma nova posição
    /// </summary>
    private void InsertMutation(RouteIndividual individual)
    {
        if (individual.DeliverySequence.Count < 2) return;
        
        int fromPos = _random.Next(individual.DeliverySequence.Count);
        int toPos = _random.Next(individual.DeliverySequence.Count);
        
        var delivery = individual.DeliverySequence[fromPos];
        individual.DeliverySequence.RemoveAt(fromPos);
        individual.DeliverySequence.Insert(toPos, delivery);
    }
    
    /// <summary>
    /// Muta a configuração de telemetria
    /// </summary>
    private void TelemetryConfigMutation(RouteIndividual individual)
    {
        var config = individual.TelemetryConfig;
        
        var mutationType = _random.Next(4);
        
        switch (mutationType)
        {
            case 0: // Ajusta intervalo de transmissão
                var delta = _random.Next(-3, 4);
                config.DataTransmissionInterval = Math.Max(1, 
                    Math.Min(30, config.DataTransmissionInterval + delta));
                break;
            
            case 1: // Toggle WiFi usage
                config.UseWiFiWhenAvailable = !config.UseWiFiWhenAvailable;
                break;
            
            case 2: // Toggle compression
                config.CompressData = !config.CompressData;
                break;
            
            case 3: // Ajusta tamanho da fila
                config.MaxQueueSize = Math.Max(10, 
                    config.MaxQueueSize + _random.Next(-20, 21));
                break;
        }
    }
    
    /// <summary>
    /// Seleção por torneio
    /// </summary>
    public RouteIndividual TournamentSelection(
        RoutePopulation population, 
        int tournamentSize)
    {
        var tournament = new List<RouteIndividual>();
        
        for (int i = 0; i < tournamentSize; i++)
        {
            var randomIndex = _random.Next(population.Individuals.Count);
            tournament.Add(population.Individuals[randomIndex]);
        }
        
        return tournament.OrderByDescending(i => i.Fitness).First();
    }
    
    /// <summary>
    /// Gera população inicial com sequências aleatórias
    /// </summary>
    public RoutePopulation GenerateInitialPopulation(
        int populationSize, 
        List<Delivery> deliveries)
    {
        var population = new RoutePopulation { Generation = 0 };
        
        for (int i = 0; i < populationSize; i++)
        {
            var individual = new RouteIndividual
            {
                DeliverySequence = new List<Delivery>(deliveries),
                TelemetryConfig = GenerateRandomTelemetryConfig()
            };
            
            // Embaralha a sequência de entregas
            ShuffleList(individual.DeliverySequence);
            
            population.Add(individual);
        }
        
        return population;
    }
    
    /// <summary>
    /// Gera uma configuração de telemetria aleatória
    /// </summary>
    private TelemetryConfig GenerateRandomTelemetryConfig()
    {
        return new TelemetryConfig
        {
            DataTransmissionInterval = _random.Next(3, 15),
            UseWiFiWhenAvailable = _random.NextDouble() > 0.2, // 80% usam WiFi
            CompressData = _random.NextDouble() > 0.3, // 70% comprimem
            MaxQueueSize = _random.Next(50, 150)
        };
    }
    
    /// <summary>
    /// Embaralha uma lista usando Fisher-Yates
    /// </summary>
    private void ShuffleList<T>(List<T> list)
    {
        for (int i = list.Count - 1; i > 0; i--)
        {
            int j = _random.Next(i + 1);
            var temp = list[i];
            list[i] = list[j];
            list[j] = temp;
        }
    }
}
