// ============================================================
// Hello World em C# com .NET 8
// Demonstra a estrutura básica de um programa C# moderno
// usando top-level statements (sem classe Main explícita)
// ============================================================

// Console.WriteLine escreve uma linha no terminal
Console.WriteLine("Olá, .NET!");

// String interpolation com $"..." — recurso moderno do C#
Console.WriteLine($"Versão do .NET: {Environment.Version}");
Console.WriteLine($"Sistema Operacional: {Environment.OSVersion}");
Console.WriteLine($"Diretório atual: {Environment.CurrentDirectory}");

// Separador visual
Console.WriteLine(new string('-', 40));

// Variáveis locais — C# é fortemente tipado
string nome = "Estudante";
int ano = DateTime.Now.Year;

Console.WriteLine($"Bem-vindo ao .NET, {nome}! Ano: {ano}");

// Aguarda Enter para encerrar (útil ao executar fora do terminal)
Console.WriteLine("\nPressione Enter para sair...");
Console.ReadLine();
