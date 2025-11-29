using System;
using Microsoft.CodeAnalysis;
using Microsoft.CodeAnalysis.CSharp;
using Microsoft.CodeAnalysis.CSharp.Syntax;

class Program
{
    static void Main(string[] args)
    {
        ExemploAnaliseSintatica();
        //ExemploGeracaoCodigo();
    }

    private static void ExemploAnaliseSintatica()
    {
        string code = @"
        using System;
        class HelloWorld
        {
            static void Main()
            {
                Console.WriteLine(""Olá, mundo!"");
            }
        }";

        // Parse o código-fonte
        var tree = CSharpSyntaxTree.ParseText(code);

        // Obtenha a raiz da árvore de sintaxe
        var root = tree.GetRoot();

        // Exiba os nós da árvore de sintaxe
        Console.WriteLine("Árvore de Sintaxe:");
        Console.WriteLine(root.ToFullString());
    }

    private static void ExemploGeracaoCodigo()
    {
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

        SyntaxTree tree = CSharpSyntaxTree.ParseText(code);
        CompilationUnitSyntax root = tree.GetCompilationUnitRoot();

        Console.WriteLine("Parsed Syntax Tree: ");
        Console.WriteLine(root.ToFullString());
    }
}