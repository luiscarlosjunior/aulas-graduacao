namespace BasicGPFramework;

/// <summary>
/// Classe base abstrata para nós da árvore de expressão
/// </summary>
public abstract class Node
{
    /// <summary>
    /// Avalia o nó com as variáveis fornecidas
    /// </summary>
    public abstract double Evaluate(Dictionary<string, double> variables);
    
    /// <summary>
    /// Cria uma cópia profunda do nó
    /// </summary>
    public abstract Node Clone();
    
    /// <summary>
    /// Retorna a profundidade da subárvore
    /// </summary>
    public abstract int GetDepth();
    
    /// <summary>
    /// Retorna o número total de nós na subárvore
    /// </summary>
    public abstract int GetNodeCount();
    
    /// <summary>
    /// Obtém todos os nós da subárvore em uma lista
    /// </summary>
    public abstract void GetNodes(List<Node> nodes, Node? parent = null);
    
    /// <summary>
    /// Substitui um nó filho por outro
    /// </summary>
    public abstract bool ReplaceChild(Node oldChild, Node newChild);
    
    /// <summary>
    /// Converte a árvore para uma string legível
    /// </summary>
    public abstract override string ToString();
}

/// <summary>
/// Nó de função (operador) - nó interno da árvore
/// </summary>
public class FunctionNode : Node
{
    public string Operator { get; set; }
    public List<Node> Children { get; set; }
    
    public FunctionNode(string op, List<Node> children)
    {
        Operator = op;
        Children = children;
    }
    
    public override double Evaluate(Dictionary<string, double> variables)
    {
        try
        {
            switch (Operator)
            {
                case "+":
                    return Children[0].Evaluate(variables) + Children[1].Evaluate(variables);
                    
                case "-":
                    return Children[0].Evaluate(variables) - Children[1].Evaluate(variables);
                    
                case "*":
                    return Children[0].Evaluate(variables) * Children[1].Evaluate(variables);
                    
                case "/":
                    var denominator = Children[1].Evaluate(variables);
                    if (Math.Abs(denominator) < 1e-10)
                        return 0; // Proteção contra divisão por zero
                    return Children[0].Evaluate(variables) / denominator;
                    
                case "sin":
                    return Math.Sin(Children[0].Evaluate(variables));
                    
                case "cos":
                    return Math.Cos(Children[0].Evaluate(variables));
                    
                case "exp":
                    var expValue = Children[0].Evaluate(variables);
                    if (expValue > 100) return double.MaxValue; // Proteção contra overflow
                    if (expValue < -100) return 0;
                    return Math.Exp(expValue);
                    
                case "log":
                    var logValue = Children[0].Evaluate(variables);
                    if (logValue <= 0) return 0; // Proteção contra log de número negativo
                    return Math.Log(logValue);
                    
                default:
                    throw new InvalidOperationException($"Operador desconhecido: {Operator}");
            }
        }
        catch
        {
            return 0; // Em caso de erro, retorna 0
        }
    }
    
    public override Node Clone()
    {
        return new FunctionNode(
            Operator, 
            Children.Select(c => c.Clone()).ToList()
        );
    }
    
    public override int GetDepth()
    {
        return 1 + (Children.Any() ? Children.Max(c => c.GetDepth()) : 0);
    }
    
    public override int GetNodeCount()
    {
        return 1 + Children.Sum(c => c.GetNodeCount());
    }
    
    public override void GetNodes(List<Node> nodes, Node? parent = null)
    {
        nodes.Add(this);
        foreach (var child in Children)
        {
            child.GetNodes(nodes, this);
        }
    }
    
    public override bool ReplaceChild(Node oldChild, Node newChild)
    {
        for (int i = 0; i < Children.Count; i++)
        {
            if (Children[i] == oldChild)
            {
                Children[i] = newChild;
                return true;
            }
            if (Children[i].ReplaceChild(oldChild, newChild))
            {
                return true;
            }
        }
        return false;
    }
    
    public override string ToString()
    {
        if (Children.Count == 1)
        {
            return $"{Operator}({Children[0]})";
        }
        return $"({Children[0]} {Operator} {Children[1]})";
    }
}

/// <summary>
/// Nó terminal (folha) - variável ou constante
/// </summary>
public class TerminalNode : Node
{
    public string? Symbol { get; set; }
    public double? Value { get; set; }
    
    public TerminalNode(string symbol)
    {
        Symbol = symbol;
        Value = null;
    }
    
    public TerminalNode(double value)
    {
        Symbol = null;
        Value = value;
    }
    
    public override double Evaluate(Dictionary<string, double> variables)
    {
        if (Value.HasValue)
            return Value.Value;
            
        if (Symbol != null && variables.ContainsKey(Symbol))
            return variables[Symbol];
            
        return 0;
    }
    
    public override Node Clone()
    {
        if (Value.HasValue)
            return new TerminalNode(Value.Value);
        return new TerminalNode(Symbol!);
    }
    
    public override int GetDepth()
    {
        return 1;
    }
    
    public override int GetNodeCount()
    {
        return 1;
    }
    
    public override void GetNodes(List<Node> nodes, Node? parent = null)
    {
        nodes.Add(this);
    }
    
    public override bool ReplaceChild(Node oldChild, Node newChild)
    {
        return false; // Terminais não têm filhos
    }
    
    public override string ToString()
    {
        if (Value.HasValue)
            return Value.Value.ToString("F2");
        return Symbol!;
    }
}
