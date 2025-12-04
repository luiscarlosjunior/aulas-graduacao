/**
 * Teste do Padrão Composite
 * 
 * Demonstra como o Composite permite tratar objetos individuais
 * e composições de forma uniforme
 */
public class TesteComposite {
    
    public static void main(String[] args) {
        System.out.println("=== Padrão Composite - Sistema de Arquivos ===\n");
        
        // Criando arquivos (Leafs)
        Arquivo arquivo1 = new Arquivo("documento.txt", 10);
        Arquivo arquivo2 = new Arquivo("foto.jpg", 500);
        Arquivo arquivo3 = new Arquivo("video.mp4", 5000);
        Arquivo arquivo4 = new Arquivo("musica.mp3", 3000);
        Arquivo arquivo5 = new Arquivo("codigo.java", 5);
        Arquivo arquivo6 = new Arquivo("README.md", 2);
        
        // Criando pastas (Composites)
        Pasta pastaRaiz = new Pasta("Meus Documentos");
        Pasta pastaTrabalho = new Pasta("Trabalho");
        Pasta pastaProjeto = new Pasta("Projeto Java");
        Pasta pastaMultimidia = new Pasta("Multimídia");
        Pasta pastaFotos = new Pasta("Fotos");
        Pasta pastaVideos = new Pasta("Videos");
        
        // Construindo hierarquia
        // Raiz
        //   ├── Trabalho
        //   │     ├── documento.txt
        //   │     └── Projeto Java
        //   │           ├── codigo.java
        //   │           └── README.md
        //   └── Multimídia
        //         ├── musica.mp3
        //         ├── Fotos
        //         │     └── foto.jpg
        //         └── Videos
        //               └── video.mp4
        
        // Construindo estrutura
        pastaRaiz.adicionar(pastaTrabalho);
        pastaRaiz.adicionar(pastaMultimidia);
        
        pastaTrabalho.adicionar(arquivo1);
        pastaTrabalho.adicionar(pastaProjeto);
        
        pastaProjeto.adicionar(arquivo5);
        pastaProjeto.adicionar(arquivo6);
        
        pastaMultimidia.adicionar(arquivo4);
        pastaMultimidia.adicionar(pastaFotos);
        pastaMultimidia.adicionar(pastaVideos);
        
        pastaFotos.adicionar(arquivo2);
        pastaVideos.adicionar(arquivo3);
        
        // Exibindo estrutura completa
        System.out.println("=== Estrutura Completa ===\n");
        pastaRaiz.exibir("");
        
        // Demonstrando uniformidade no tratamento
        System.out.println("\n=== Tratamento Uniforme ===");
        System.out.println("IMPORTANTE: Mesma interface para arquivos e pastas!\n");
        
        // Array polimórfico - arquivos e pastas juntos!
        ElementoSistemaArquivos[] elementos = {
            arquivo1,
            pastaTrabalho,
            arquivo2,
            pastaMultimidia
        };
        
        System.out.println("Iterando sobre arquivos e pastas uniformemente:");
        for (ElementoSistemaArquivos elemento : elementos) {
            // Mesmo código funciona para arquivo ou pasta!
            System.out.println("- " + elemento.getNome() + ": " + elemento.getTamanho() + " KB");
        }
        
        // Operações em subárvores
        System.out.println("\n=== Operações em Subárvores ===\n");
        System.out.println("Exibindo apenas pasta 'Multimídia':");
        pastaMultimidia.exibir("");
        
        System.out.println("\nTamanho total da pasta 'Trabalho': " + 
                          pastaTrabalho.getTamanho() + " KB");
        
        // Modificando estrutura dinamicamente
        System.out.println("\n=== Modificação Dinâmica ===\n");
        System.out.println("Removendo 'Videos' da pasta 'Multimídia'...");
        pastaMultimidia.remover(pastaVideos);
        
        System.out.println("Estrutura atualizada de 'Multimídia':");
        pastaMultimidia.exibir("");
        
        System.out.println("\nNovo tamanho total: " + pastaRaiz.getTamanho() + " KB");
        
        // Vantagens
        System.out.println("\n=== Vantagens do Composite ===");
        System.out.println("1. Trata objetos individuais e composições uniformemente");
        System.out.println("2. Estrutura em árvore natural e intuitiva");
        System.out.println("3. Fácil adicionar novos tipos de componentes");
        System.out.println("4. Cliente não precisa distinguir entre folhas e composites");
        System.out.println("5. Operações recursivas são simples e elegantes");
        
        // Casos de uso
        System.out.println("\n=== Casos de Uso Reais ===");
        System.out.println("1. Sistemas de arquivos (como demonstrado)");
        System.out.println("2. Componentes gráficos (Swing/AWT em Java)");
        System.out.println("3. Estruturas organizacionais (empresas, departamentos)");
        System.out.println("4. Menus e submenus");
        System.out.println("5. Expressões aritméticas (árvore de sintaxe)");
        System.out.println("6. Estruturas XML/HTML (DOM)");
    }
}
