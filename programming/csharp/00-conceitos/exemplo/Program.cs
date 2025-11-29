using Microsoft.CodeAnalysis;
using Microsoft.CodeAnalysis.CSharp;
using Microsoft.CodeAnalysis.CSharp.Syntax;

/// <summary>
/// Classe principal do programa que demonstra análise sintática e geração de código em C#
/// </summary>
class Program
{
    /// <summary>
    /// Método principal que executa os exemplos de análise sintática e geração de código
    /// </summary>
    static void Main(string[] args)
    {
        ExemploAnaliseSintatica();
        ExemploGeracaoCodigo();
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
}