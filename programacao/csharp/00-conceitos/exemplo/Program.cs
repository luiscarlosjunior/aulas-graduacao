using Microsoft.CodeAnalysis;
using Microsoft.CodeAnalysis.CSharp;
using Microsoft.CodeAnalysis.CSharp.Syntax;

/// <summary>
/// Classe principal do programa que demonstra análise sintática e geração de código em C#
/// </summary>
public class Program
{
    /// <summary>
    /// Método principal que executa os exemplos de análise sintática e geração de código
    /// </summary>
    static void Main(string[] args)
    {
        ExemploAnaliseSintatica();
        ExemploGeracaoCodigo();
        ExemploExtrairMetodos();
        ExemploUsoReal();
    }

    /// <summary>
    /// Demonstra como fazer parsing de código C# e exibir a árvore de sintaxe
    /// </summary>
    private static void ExemploAnaliseSintatica()
    {
        // Define uma string com código C# de exemplo
        string code = @"
        using System;
        class HelloWorld
        {
            static void Main()
            {
                Console.WriteLine(""Olá, mundo!"");
            }
        }";

        // Parse o código-fonte em uma árvore de sintaxe
        var tree = CSharpSyntaxTree.ParseText(code);

        // Obtenha a raiz da árvore de sintaxe
        var root = tree.GetRoot();

        // Exiba os nós da árvore de sintaxe no console
        Console.WriteLine("Árvore de Sintaxe:");
        Console.WriteLine(root.ToFullString());
    }

    /// <summary>
    /// Demonstra como fazer parsing de código C# com namespace e exibir a árvore sintática completa
    /// </summary>
    private static void ExemploGeracaoCodigo()
    {
        // Define uma string com código C# contendo um namespace e uma classe
        string code = @"
        using System;

        namespace HelloWorld
        {
            class Program
            {
                static void Main(string[] args)
                {
                    Console.WriteLine(""Hello, World!"");
                }
            }
        }";

        // Parse o código-fonte em uma árvore de sintaxe
        SyntaxTree tree = CSharpSyntaxTree.ParseText(code);

        // Obtenha a raiz da árvore de sintaxe como CompilationUnitSyntax
        CompilationUnitSyntax root = tree.GetCompilationUnitRoot();

        // Exiba a árvore sintática completa no console
        Console.WriteLine("Parsed Syntax Tree: ");
        Console.WriteLine(root.ToFullString());
    }

    /// <summary>
    /// Demonstra como extrair todos os métodos de uma classe usando a árvore de sintaxe
    /// </summary>
    private static void ExemploExtrairMetodos()
    {
        // Define uma string com código C# contendo uma classe com múltiplos métodos
        string code = @"
        using System;

        public class Calculadora
        {
            public int Somar(int a, int b)
            {
                return a + b;
            }

            public int Subtrair(int a, int b)
            {
                return a - b;
            }

            public int Multiplicar(int a, int b)
            {
                return a * b;
            }
        }";

        // Parse o código-fonte em uma árvore de sintaxe
        SyntaxTree tree = CSharpSyntaxTree.ParseText(code);
        CompilationUnitSyntax root = tree.GetCompilationUnitRoot();

        // Encontre todas as classes na árvore de sintaxe
        var classes = root.DescendantNodes().OfType<ClassDeclarationSyntax>();

        Console.WriteLine("\n=== Métodos Extraídos ===");
        foreach (var classe in classes)
        {
            Console.WriteLine($"Classe: {classe.Identifier.ValueText}");

            // Extraia todos os métodos da classe
            var metodos = classe.Members.OfType<MethodDeclarationSyntax>();

            foreach (var metodo in metodos)
            {
                Console.WriteLine($"  - Método: {metodo.Identifier.ValueText}");
                Console.WriteLine($"    Tipo de retorno: {metodo.ReturnType}");
            }
        }
    }
    
    /// <summary>
    /// Exemplo simples e prático: Encontrar todas as propriedades de uma classe
    /// </summary>
    private static void ExemploUsoReal()
    {
        // Código de uma classe com propriedades
        string code = @"
        public class Pessoa
        {
            public string Nome { get; set; }
            public int Idade { get; set; }
            public string Email { get; set; }
        }";

        // Faz o parsing do código
        SyntaxTree tree = CSharpSyntaxTree.ParseText(code);
        CompilationUnitSyntax root = tree.GetCompilationUnitRoot();

        // Encontra todas as propriedades
        var propriedades = root.DescendantNodes().OfType<PropertyDeclarationSyntax>();

        Console.WriteLine("\n=== EXEMPLO PRÁTICO: Gerar Getters e Setters ===");
        foreach (var prop in propriedades)
        {
            string nomeProp = prop.Identifier.ValueText;
            string tipo = prop.Type.ToString();
            
            // Gera código automaticamente
            Console.WriteLine($"public {tipo} Get{nomeProp}() => this.{nomeProp};");
            Console.WriteLine($"public void Set{nomeProp}({tipo} value) => this.{nomeProp} = value;");
            Console.WriteLine();
        }
    }
}