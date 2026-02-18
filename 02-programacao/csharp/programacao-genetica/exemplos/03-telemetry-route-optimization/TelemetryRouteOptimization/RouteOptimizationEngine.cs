using TelemetryRouteOptimization.Models;
using TelemetryRouteOptimization.GP;

namespace TelemetryRouteOptimization.Optimization;

/// <summary>
/// Configuração do motor de otimização de rotas
/// </summary>
public class RouteOptimizationConfig
{
    public int PopulationSize { get; set; } = 100;
    public int MaxGenerations { get; set; } = 100;
    public double CrossoverRate { get; set; } = 0.8;
    public double MutationRate { get; set; } = 0.15;
    public double ElitismRate { get; set; } = 0.1;
    public int TournamentSize { get; set; } = 5;
    public int? RandomSeed { get; set; } = null;
}

/// <summary>
/// Motor de otimização de rotas usando Programação Genética
/// </summary>
public class RouteOptimizationEngine
{
    private readonly RouteOptimizationConfig _config;
    private readonly RouteFitnessCalculator _fitnessCalculator;
    private readonly RouteGeneticOperators _operators;
    private readonly List<Delivery> _deliveries;
    
    public RoutePopulation? CurrentPopulation { get; private set; }
    public RouteIndividual? BestEverIndividual { get; private set; }
    
    public RouteOptimizationEngine(
        RouteOptimizationConfig config,
        List<Delivery> deliveries,
        List<Location> wifiLocations)
    {
        _config = config;
        _deliveries = deliveries;
        _fitnessCalculator = new RouteFitnessCalculator(wifiLocations);
        _operators = new RouteGeneticOperators(_config.RandomSeed);
    }
    
    /// <summary>
    /// Executa o processo de otimização
    /// </summary>
    public RouteIndividual Optimize()
    {
        Console.WriteLine("\n╔═══════════════════════════════════════════════════════════════╗");
        Console.WriteLine("║  Otimização de Rotas com Telemetria - Programação Genética   ║");
        Console.WriteLine("╚═══════════════════════════════════════════════════════════════╝\n");
        
        Console.WriteLine($"Configuração:");
        Console.WriteLine($"  • População: {_config.PopulationSize} indivíduos");
        Console.WriteLine($"  • Gerações: {_config.MaxGenerations}");
        Console.WriteLine($"  • Taxa de Crossover: {_config.CrossoverRate:P0}");
        Console.WriteLine($"  • Taxa de Mutação: {_config.MutationRate:P0}");
        Console.WriteLine($"  • Taxa de Elitismo: {_config.ElitismRate:P0}");
        Console.WriteLine($"  • Entregas a otimizar: {_deliveries.Count}\n");
        
        // 1. Gera população inicial
        Console.WriteLine("Gerando população inicial...");
        CurrentPopulation = _operators.GenerateInitialPopulation(
            _config.PopulationSize, 
            _deliveries);
        
        // Avalia população inicial
        EvaluatePopulation(CurrentPopulation);
        BestEverIndividual = CurrentPopulation.BestIndividual.Clone();
        
        Console.WriteLine($"População inicial criada.");
        Console.WriteLine($"Melhor solução inicial: {BestEverIndividual}\n");
        
        Console.WriteLine("Iniciando evolução...\n");
        
        // 2. Loop evolutivo
        for (int generation = 1; generation <= _config.MaxGenerations; generation++)
        {
            CurrentPopulation = EvolveGeneration(CurrentPopulation);
            CurrentPopulation.Generation = generation;
            
            // Atualiza melhor de todos os tempos
            if (CurrentPopulation.BestFitness > BestEverIndividual.Fitness)
            {
                BestEverIndividual = CurrentPopulation.BestIndividual.Clone();
                Console.WriteLine($"[Gen {generation}] *** NOVA MELHOR SOLUÇÃO ***");
                Console.WriteLine($"  {BestEverIndividual}");
            }
            
            // Log periódico
            if (generation % 10 == 0 || generation == _config.MaxGenerations)
            {
                Console.WriteLine($"[Gen {generation}] {CurrentPopulation.GetStatistics()}");
            }
        }
        
        // 3. Resultado final
        Console.WriteLine("\n╔═══════════════════════════════════════════════════════════════╗");
        Console.WriteLine("║  Otimização Concluída                                         ║");
        Console.WriteLine("╚═══════════════════════════════════════════════════════════════╝\n");
        
        PrintDetailedSolution(BestEverIndividual);
        
        return BestEverIndividual;
    }
    
    /// <summary>
    /// Avalia a fitness de todos os indivíduos
    /// </summary>
    private void EvaluatePopulation(RoutePopulation population)
    {
        foreach (var individual in population.Individuals)
        {
            _fitnessCalculator.Calculate(individual);
        }
    }
    
    /// <summary>
    /// Evolui uma geração
    /// </summary>
    private RoutePopulation EvolveGeneration(RoutePopulation currentPopulation)
    {
        var newPopulation = new RoutePopulation 
        { 
            Generation = currentPopulation.Generation + 1 
        };
        
        // 1. Elitismo - preserva os melhores
        int eliteCount = (int)(_config.PopulationSize * _config.ElitismRate);
        var elite = currentPopulation.Individuals
            .OrderByDescending(i => i.Fitness)
            .Take(eliteCount)
            .Select(i => i.Clone())
            .ToList();
        
        foreach (var individual in elite)
        {
            newPopulation.Add(individual);
        }
        
        // 2. Gera nova população
        Random random;
        if (_config.RandomSeed.HasValue)
        {
            random = new Random(_config.RandomSeed.Value);
        }
        else
        {
            random = new Random();
        }
        
        while (newPopulation.Individuals.Count < _config.PopulationSize)
        {
            // Seleção
            var parent1 = _operators.TournamentSelection(
                currentPopulation, 
                _config.TournamentSize);
            var parent2 = _operators.TournamentSelection(
                currentPopulation, 
                _config.TournamentSize);
            
            RouteIndividual offspring1, offspring2;
            
            // Crossover
            if (random.NextDouble() < _config.CrossoverRate)
            {
                (offspring1, offspring2) = _operators.OrderCrossover(parent1, parent2);
            }
            else
            {
                offspring1 = parent1.Clone();
                offspring2 = parent2.Clone();
            }
            
            // Mutação
            offspring1 = _operators.Mutate(offspring1, _config.MutationRate);
            offspring2 = _operators.Mutate(offspring2, _config.MutationRate);
            
            // Adiciona à nova população
            if (newPopulation.Individuals.Count < _config.PopulationSize)
            {
                newPopulation.Add(offspring1);
            }
            
            if (newPopulation.Individuals.Count < _config.PopulationSize)
            {
                newPopulation.Add(offspring2);
            }
        }
        
        // 3. Avalia nova população
        EvaluatePopulation(newPopulation);
        
        return newPopulation;
    }
    
    /// <summary>
    /// Imprime solução detalhada
    /// </summary>
    private void PrintDetailedSolution(RouteIndividual solution)
    {
        Console.WriteLine("═══════════════════════════════════════════════════════════════");
        Console.WriteLine("MELHOR SOLUÇÃO ENCONTRADA");
        Console.WriteLine("═══════════════════════════════════════════════════════════════\n");
        
        Console.WriteLine("📊 MÉTRICAS GERAIS:");
        Console.WriteLine($"  • Fitness: {solution.Fitness:F4}");
        Console.WriteLine($"  • Distância Total: {solution.TotalDistance:F2} km");
        Console.WriteLine($"  • Tempo Total: {solution.TotalTime:F0} minutos ({solution.TotalTime/60:F1} horas)");
        Console.WriteLine($"  • Consumo de Combustível: {solution.FuelCost:F2} litros");
        Console.WriteLine($"  • Custo de Combustível: R$ {solution.FuelCost * 6.50:F2}");
        Console.WriteLine($"  • Custo de Transmissão de Dados: R$ {solution.DataTransmissionCost:F2}");
        Console.WriteLine($"  • Penalidade por Atraso: R$ {solution.LatePenalty:F2}");
        
        var totalCost = (solution.FuelCost * 6.50) + 
                       solution.DataTransmissionCost + 
                       solution.LatePenalty;
        Console.WriteLine($"  • CUSTO TOTAL: R$ {totalCost:F2}\n");
        
        Console.WriteLine("📡 CONFIGURAÇÃO DE TELEMETRIA:");
        Console.WriteLine($"  • Intervalo de Transmissão: {solution.TelemetryConfig.DataTransmissionInterval} minutos");
        Console.WriteLine($"  • Usar WiFi quando disponível: {(solution.TelemetryConfig.UseWiFiWhenAvailable ? "Sim" : "Não")}");
        Console.WriteLine($"  • Comprimir dados: {(solution.TelemetryConfig.CompressData ? "Sim" : "Não")}");
        Console.WriteLine($"  • Tamanho máximo da fila: {solution.TelemetryConfig.MaxQueueSize} mensagens\n");
        
        Console.WriteLine("🚚 SEQUÊNCIA DE ENTREGAS:");
        Console.WriteLine("─────────────────────────────────────────────────────────────");
        
        var depot = new Location 
        { 
            Name = "Depot (Centro de Distribuição)", 
            Latitude = -23.5505, 
            Longitude = -46.6333 
        };
        
        var currentLocation = depot;
        var currentTime = DateTime.Today.AddHours(8);
        double cumulativeDistance = 0;
        
        Console.WriteLine($"  0. {depot.Name}");
        Console.WriteLine($"     Partida: {currentTime:HH:mm}\n");
        
        for (int i = 0; i < solution.DeliverySequence.Count; i++)
        {
            var delivery = solution.DeliverySequence[i];
            var distance = currentLocation.DistanceTo(delivery.Location);
            var travelTime = (distance / 60.0) * 60; // minutos
            
            cumulativeDistance += distance;
            currentTime = currentTime.AddMinutes(travelTime);
            
            Console.WriteLine($"  {i + 1}. {delivery.Location.Name}");
            Console.WriteLine($"     Distância do ponto anterior: {distance:F2} km");
            Console.WriteLine($"     Chegada prevista: {currentTime:HH:mm}");
            Console.WriteLine($"     Janela de entrega: {delivery.TimeWindowStart:HH:mm} - {delivery.TimeWindowEnd:HH:mm}");
            
            if (currentTime > delivery.TimeWindowEnd)
            {
                var delay = (currentTime - delivery.TimeWindowEnd).TotalMinutes;
                Console.WriteLine($"     ⚠️  ATRASO: {delay:F0} minutos");
            }
            else if (currentTime < delivery.TimeWindowStart)
            {
                Console.WriteLine($"     ✓ Chegada antecipada");
            }
            else
            {
                Console.WriteLine($"     ✓ Dentro da janela de entrega");
            }
            
            Console.WriteLine($"     Prioridade: {delivery.Priority}/5");
            Console.WriteLine($"     Peso: {delivery.Weight:F1} kg\n");
            
            currentLocation = delivery.Location;
            currentTime = currentTime.AddMinutes(10); // Tempo de descarga
        }
        
        // Retorno ao depot
        var returnDistance = currentLocation.DistanceTo(depot);
        var returnTime = (returnDistance / 60.0) * 60;
        cumulativeDistance += returnDistance;
        currentTime = currentTime.AddMinutes(returnTime);
        
        Console.WriteLine($"  {solution.DeliverySequence.Count + 1}. {depot.Name} (Retorno)");
        Console.WriteLine($"     Distância: {returnDistance:F2} km");
        Console.WriteLine($"     Chegada: {currentTime:HH:mm}");
        Console.WriteLine($"     Distância total percorrida: {cumulativeDistance:F2} km\n");
        
        Console.WriteLine("═══════════════════════════════════════════════════════════════\n");
    }
}
