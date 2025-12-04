using TelemetryRouteOptimization.Models;
using TelemetryRouteOptimization.GP;

namespace TelemetryRouteOptimization.Optimization;

/// <summary>
/// Calcula a fitness de uma rota considerando múltiplos objetivos
/// </summary>
public class RouteFitnessCalculator
{
    // Pesos para cada componente da fitness
    private const double WEIGHT_DISTANCE = 0.30;
    private const double WEIGHT_TIME = 0.25;
    private const double WEIGHT_FUEL = 0.25;
    private const double WEIGHT_DATA_COST = 0.15;
    private const double WEIGHT_PENALTY = 0.05;
    
    // Custos
    private const double FUEL_PRICE_PER_LITER = 6.50; // R$/L
    private const double DATA_COST_4G_PER_MB = 0.02; // R$/MB
    private const double DATA_COST_WIFI = 0.0; // Grátis
    private const double LATE_DELIVERY_PENALTY = 100.0; // R$ por hora de atraso
    
    // Parâmetros do veículo
    private const double AVG_SPEED = 60.0; // km/h
    private const double FUEL_CONSUMPTION = 35.0; // L/100km
    private const double TELEMETRY_DATA_SIZE_MB = 0.5; // MB por transmissão
    
    private readonly List<Location> _wifiLocations;
    
    public RouteFitnessCalculator(List<Location> wifiLocations)
    {
        _wifiLocations = wifiLocations;
    }
    
    /// <summary>
    /// Calcula a fitness de um indivíduo de rota
    /// </summary>
    public double Calculate(RouteIndividual individual)
    {
        if (individual.DeliverySequence.Count == 0)
            return double.MinValue;
        
        // 1. Calcula distância total
        individual.TotalDistance = CalculateTotalDistance(individual.DeliverySequence);
        
        // 2. Calcula tempo total
        individual.TotalTime = individual.TotalDistance / AVG_SPEED * 60; // minutos
        
        // 3. Calcula custo de combustível
        individual.FuelCost = (individual.TotalDistance / 100.0) * FUEL_CONSUMPTION;
        var fuelCostMoney = individual.FuelCost * FUEL_PRICE_PER_LITER;
        
        // 4. Calcula custo de transmissão de dados
        individual.DataTransmissionCost = CalculateDataTransmissionCost(
            individual.DeliverySequence, 
            individual.TelemetryConfig);
        
        // 5. Calcula penalidades por atraso
        individual.LatePenalty = CalculateLatePenalty(
            individual.DeliverySequence, 
            individual.TotalTime);
        
        // 6. Normaliza e combina componentes
        var normalizedDistance = individual.TotalDistance / 500.0; // Assume máx 500km
        var normalizedTime = individual.TotalTime / 600.0; // Assume máx 10h
        var normalizedFuelCost = fuelCostMoney / 500.0; // Assume máx R$500
        var normalizedDataCost = individual.DataTransmissionCost / 50.0; // Assume máx R$50
        var normalizedPenalty = individual.LatePenalty / 300.0; // Assume máx R$300
        
        // Fitness (negativa porque queremos minimizar custos)
        var fitness = -(
            WEIGHT_DISTANCE * normalizedDistance +
            WEIGHT_TIME * normalizedTime +
            WEIGHT_FUEL * normalizedFuelCost +
            WEIGHT_DATA_COST * normalizedDataCost +
            WEIGHT_PENALTY * normalizedPenalty
        );
        
        individual.Fitness = fitness;
        return fitness;
    }
    
    /// <summary>
    /// Calcula a distância total da rota
    /// </summary>
    private double CalculateTotalDistance(List<Delivery> deliveries)
    {
        if (deliveries.Count == 0) return 0;
        
        double totalDistance = 0;
        
        // Assume ponto de partida (depot)
        var depot = new Location 
        { 
            Name = "Depot", 
            Latitude = -23.5505, 
            Longitude = -46.6333 
        };
        
        var currentLocation = depot;
        
        foreach (var delivery in deliveries)
        {
            totalDistance += currentLocation.DistanceTo(delivery.Location);
            currentLocation = delivery.Location;
        }
        
        // Retorna ao depot
        totalDistance += currentLocation.DistanceTo(depot);
        
        return totalDistance;
    }
    
    /// <summary>
    /// Calcula o custo de transmissão de dados
    /// </summary>
    private double CalculateDataTransmissionCost(
        List<Delivery> deliveries, 
        TelemetryConfig config)
    {
        if (deliveries.Count == 0) return 0;
        
        double totalCost = 0;
        var depot = new Location 
        { 
            Name = "Depot", 
            Latitude = -23.5505, 
            Longitude = -46.6333 
        };
        
        var currentLocation = depot;
        double traveledDistance = 0;
        
        foreach (var delivery in deliveries)
        {
            var segmentDistance = currentLocation.DistanceTo(delivery.Location);
            traveledDistance += segmentDistance;
            
            // Calcula quantas transmissões ocorrem neste segmento
            var travelTimeMinutes = (segmentDistance / AVG_SPEED) * 60;
            var transmissionsCount = (int)(travelTimeMinutes / config.DataTransmissionInterval);
            
            for (int i = 0; i < transmissionsCount; i++)
            {
                // Verifica se está perto de WiFi
                var isNearWiFi = IsNearWiFiPoint(currentLocation);
                
                if (isNearWiFi && config.UseWiFiWhenAvailable)
                {
                    totalCost += DATA_COST_WIFI;
                }
                else
                {
                    var dataSize = TELEMETRY_DATA_SIZE_MB;
                    if (config.CompressData)
                    {
                        dataSize *= 0.6; // 40% de compressão
                    }
                    totalCost += dataSize * DATA_COST_4G_PER_MB;
                }
            }
            
            currentLocation = delivery.Location;
        }
        
        return totalCost;
    }
    
    /// <summary>
    /// Verifica se uma localização está próxima de um ponto WiFi
    /// </summary>
    private bool IsNearWiFiPoint(Location location)
    {
        const double WIFI_RANGE_KM = 0.5; // 500m
        
        foreach (var wifiLocation in _wifiLocations)
        {
            if (location.DistanceTo(wifiLocation) <= WIFI_RANGE_KM)
            {
                return true;
            }
        }
        
        return false;
    }
    
    /// <summary>
    /// Calcula penalidade por entregas atrasadas
    /// </summary>
    private double CalculateLatePenalty(List<Delivery> deliveries, double totalTime)
    {
        double penalty = 0;
        double currentTime = 0; // minutos desde o início
        
        var depot = new Location 
        { 
            Name = "Depot", 
            Latitude = -23.5505, 
            Longitude = -46.6333 
        };
        
        var currentLocation = depot;
        var startTime = DateTime.Today.AddHours(8); // Começa às 8h
        
        foreach (var delivery in deliveries)
        {
            var distance = currentLocation.DistanceTo(delivery.Location);
            var travelTime = (distance / AVG_SPEED) * 60; // minutos
            currentTime += travelTime;
            
            var arrivalTime = startTime.AddMinutes(currentTime);
            
            // Verifica se chegou após a janela de entrega
            if (arrivalTime > delivery.TimeWindowEnd)
            {
                var hoursLate = (arrivalTime - delivery.TimeWindowEnd).TotalHours;
                penalty += hoursLate * LATE_DELIVERY_PENALTY * delivery.Priority;
            }
            
            currentLocation = delivery.Location;
            
            // Tempo de descarga (10 minutos)
            currentTime += 10;
        }
        
        return penalty;
    }
}
