/**
 * Classe de Teste do Padrão Flyweight
 * 
 * Demonstra o padrão Flyweight através de um editor de texto
 * que compartilha estilos entre múltiplos caracteres.
 * 
 * OBJETIVO:
 * Mostrar como o Flyweight economiza memória ao compartilhar
 * objetos comuns entre múltiplas instâncias.
 * 
 * CENÁRIO:
 * Editor de texto com milhares de caracteres e poucos estilos.
 * Sem Flyweight: Cada caractere teria seu próprio objeto de estilo.
 * Com Flyweight: Caracteres compartilham objetos de estilo.
 */
public class TesteFlyweight {
    
    public static void main(String[] args) {
        System.out.println("╔═══════════════════════════════════════════════════════════════╗");
        System.out.println("║       DEMONSTRAÇÃO DO PADRÃO FLYWEIGHT                        ║");
        System.out.println("║       Sistema de Editor de Texto                              ║");
        System.out.println("╚═══════════════════════════════════════════════════════════════╝");
        
        // ========================================
        // PARTE 1: Demonstração Básica
        // ========================================
        demonstracaoBasica();
        
        // ========================================
        // PARTE 2: Demonstração de Compartilhamento
        // ========================================
        demonstracaoCompartilhamento();
        
        // ========================================
        // PARTE 3: Comparação de Memória
        // ========================================
        demonstracaoEconomiaMemoria();
        
        // ========================================
        // PARTE 4: Caso Realista (Documento Grande)
        // ========================================
        demonstracaoDocumentoGrande();
        
        // ========================================
        // PARTE 5: Comparação Com vs Sem Flyweight
        // ========================================
        comparacaoComESemFlyweight();
    }
    
    /**
     * Demonstração básica do funcionamento do Flyweight
     */
    private static void demonstracaoBasica() {
        System.out.println("\n");
        System.out.println("═══════════════════════════════════════════════════════════════");
        System.out.println("  PARTE 1: Demonstração Básica");
        System.out.println("═══════════════════════════════════════════════════════════════");
        
        System.out.println("\n--- Criando Editor de Texto ---");
        TextEditor editor = new TextEditor("Documento1.txt");
        
        // Adiciona texto com diferentes estilos
        System.out.println("\n📝 Adicionando texto: 'Olá' (Arial, 12pt, Preto)");
        editor.addText("Olá", "Arial", 12, "#000000");
        
        System.out.println("\n📝 Adicionando texto: ' Mundo' (Arial, 12pt, Preto)");
        editor.addText(" Mundo", "Arial", 12, "#000000");
        
        System.out.println("\n📝 Adicionando texto: '!' (Arial, 14pt, Vermelho, Negrito)");
        editor.addText("!", "Arial", 14, "#FF0000", true, false);
        
        // Renderiza parte do documento
        System.out.println("\n--- Renderizando primeiros 5 caracteres ---");
        editor.renderRange(0, 5);
        
        // Estatísticas
        editor.printMemoryStatistics();
    }
    
    /**
     * Demonstra como estilos são compartilhados
     */
    private static void demonstracaoCompartilhamento() {
        System.out.println("\n");
        System.out.println("═══════════════════════════════════════════════════════════════");
        System.out.println("  PARTE 2: Demonstração de Compartilhamento");
        System.out.println("═══════════════════════════════════════════════════════════════");
        
        System.out.println("\nCriando editor e adicionando texto com MESMO estilo...");
        TextEditor editor = new TextEditor("Compartilhamento.txt");
        
        System.out.println("\n1️⃣ Primeira palavra 'Java' - Criará novo estilo");
        editor.addText("Java", "Arial", 12, "#000000");
        
        System.out.println("\n2️⃣ Segunda palavra 'Python' - REUTILIZARÁ estilo!");
        editor.addText("Python", "Arial", 12, "#000000");
        
        System.out.println("\n3️⃣ Terceira palavra 'Ruby' - REUTILIZARÁ estilo!");
        editor.addText("Ruby", "Arial", 12, "#000000");
        
        System.out.println("\n4️⃣ Quarta palavra 'Go' - REUTILIZARÁ estilo!");
        editor.addText("Go", "Arial", 12, "#000000");
        
        System.out.println("\n✅ Resultado: 4 palavras (14 caracteres) compartilham 1 único estilo!");
        
        // Mostra estatísticas
        StyleFactory factory = editor.getStyleFactory();
        factory.printAllStyles();
        factory.printStatistics();
    }
    
    /**
     * Demonstra economia massiva de memória
     */
    private static void demonstracaoEconomiaMemoria() {
        System.out.println("\n");
        System.out.println("═══════════════════════════════════════════════════════════════");
        System.out.println("  PARTE 3: Comparação de Memória");
        System.out.println("═══════════════════════════════════════════════════════════════");
        
        TextEditor editor = new TextEditor("Artigo.txt");
        
        // Simula um artigo com múltiplos estilos
        System.out.println("\n📄 Criando artigo com múltiplos estilos...\n");
        
        // Título
        editor.addText("Padrões de Design em Java", "Arial", 18, "#000000", true, false);
        editor.addText("\n\n", "Arial", 12, "#000000");
        
        // Parágrafo normal (muitos caracteres, mesmo estilo)
        String paragrafo1 = "O padrão Flyweight é um padrão estrutural que permite " +
                           "economizar memória compartilhando objetos comuns. ";
        editor.addText(paragrafo1, "Arial", 12, "#000000");
        
        // Palavra em destaque
        editor.addText("Este padrão", "Arial", 12, "#FF0000", true, false);
        
        // Mais texto normal
        String paragrafo2 = " é especialmente útil quando há grande quantidade de " +
                           "objetos similares que podem compartilhar estado.";
        editor.addText(paragrafo2, "Arial", 12, "#000000");
        
        // Subtítulo
        editor.addText("\n\nAplicações Práticas", "Arial", 14, "#0000FF", true, false);
        editor.addText("\n\n", "Arial", 12, "#000000");
        
        // Lista
        editor.addText("1. Editores de texto\n", "Arial", 12, "#000000");
        editor.addText("2. Jogos 3D\n", "Arial", 12, "#000000");
        editor.addText("3. Sistemas de UI\n", "Arial", 12, "#000000");
        
        // Mostra resultados
        System.out.println("📊 Artigo criado!");
        System.out.println("Caracteres totais: " + editor.getCharacterCount());
        System.out.println("Estilos únicos: " + editor.getUniqueStyleCount());
        
        editor.printMemoryStatistics();
    }
    
    /**
     * Simula documento grande para mostrar impacto real
     */
    private static void demonstracaoDocumentoGrande() {
        System.out.println("\n");
        System.out.println("═══════════════════════════════════════════════════════════════");
        System.out.println("  PARTE 4: Documento Grande (Caso Realista)");
        System.out.println("═══════════════════════════════════════════════════════════════");
        
        TextEditor editor = new TextEditor("DocumentoGrande.txt");
        
        System.out.println("\n📚 Criando documento com 1000 caracteres...");
        System.out.println("Usando apenas 5 estilos diferentes...\n");
        
        // Define 5 estilos que serão reutilizados
        String[][] styles = {
            {"Arial", "12", "#000000"},      // Estilo 1: Texto normal
            {"Arial", "14", "#FF0000"},      // Estilo 2: Destaque vermelho
            {"Arial", "12", "#0000FF"},      // Estilo 3: Azul
            {"Times New Roman", "12", "#000000"}, // Estilo 4: Serif
            {"Courier", "10", "#008800"}     // Estilo 5: Code
        };
        
        // Adiciona 1000 caracteres alternando entre os 5 estilos
        for (int i = 0; i < 200; i++) { // 200 blocos de 5 chars = 1000 chars
            for (String[] style : styles) {
                char ch = (char)('A' + (i % 26)); // A-Z
                editor.addText(String.valueOf(ch), 
                             style[0], 
                             Integer.parseInt(style[1]), 
                             style[2]);
            }
        }
        
        System.out.println("✅ Documento criado!\n");
        
        // Estatísticas impressionantes
        editor.printMemoryStatistics();
        
        // Análise de compartilhamento
        int totalChars = editor.getCharacterCount();
        int uniqueStyles = editor.getUniqueStyleCount();
        int charsPerStyle = totalChars / uniqueStyles;
        
        System.out.println("\n📈 Análise de Compartilhamento:");
        System.out.println("Cada estilo é compartilhado por " + charsPerStyle + " caracteres!");
        System.out.printf("Taxa de reuso: %d:1%n", charsPerStyle);
        System.out.println("\n💡 Isso significa que cada objeto de estilo é referenciado");
        System.out.println("   por " + charsPerStyle + " objetos Character diferentes!");
    }
    
    /**
     * Compara implementação COM e SEM Flyweight
     */
    private static void comparacaoComESemFlyweight() {
        System.out.println("\n");
        System.out.println("═══════════════════════════════════════════════════════════════");
        System.out.println("  PARTE 5: Comparação COM vs SEM Flyweight");
        System.out.println("═══════════════════════════════════════════════════════════════");
        
        // Simula cenário SEM flyweight
        System.out.println("\n❌ SEM Flyweight:");
        System.out.println("   Cada caractere tem seu próprio objeto de estilo");
        CharacterWithoutFlyweight[] semFlyweight = new CharacterWithoutFlyweight[1000];
        for (int i = 0; i < 1000; i++) {
            semFlyweight[i] = new CharacterWithoutFlyweight(
                'A', "Arial", 12, "#000000", false, false, i
            );
        }
        long memorySem = 1000L * 126; // ~126 bytes por caractere
        System.out.printf("   Memória estimada: %,d bytes (~%d KB)%n", 
                         memorySem, memorySem / 1024);
        
        // Cenário COM flyweight
        System.out.println("\n✅ COM Flyweight:");
        System.out.println("   Caracteres compartilham objetos de estilo");
        TextEditor editor = new TextEditor("Teste.txt");
        for (int i = 0; i < 1000; i++) {
            editor.addText("A", "Arial", 12, "#000000");
        }
        long memoryCom = editor.estimateMemoryWithFlyweight();
        System.out.printf("   Memória estimada: %,d bytes (~%d KB)%n", 
                         memoryCom, memoryCom / 1024);
        
        // Comparação
        System.out.println("\n📊 RESULTADO:");
        long economizado = memorySem - memoryCom;
        double percentual = (economizado * 100.0) / memorySem;
        System.out.printf("   Economia: %,d bytes (~%d KB)%n", 
                         economizado, economizado / 1024);
        System.out.printf("   Percentual: %.1f%%%n", percentual);
        
        System.out.println("\n💡 CONCLUSÃO:");
        System.out.println("   Flyweight reduziu uso de memória em " + (int)percentual + "%!");
        System.out.println("   Em um documento de 1 milhão de caracteres, isso seria");
        System.out.println("   uma economia de ~120 MB de RAM!");
        
        System.out.println("\n");
        System.out.println("═══════════════════════════════════════════════════════════════");
        System.out.println("  BENEFÍCIOS DO FLYWEIGHT DEMONSTRADOS:");
        System.out.println("═══════════════════════════════════════════════════════════════");
        System.out.println("  ✅ Economia massiva de memória (80-95%)");
        System.out.println("  ✅ Melhor performance de cache (menos objetos)");
        System.out.println("  ✅ Menos trabalho para Garbage Collector");
        System.out.println("  ✅ Aplicação pode escalar para mais dados");
        System.out.println("  ✅ Compartilhamento transparente para o cliente");
        System.out.println("═══════════════════════════════════════════════════════════════");
        
        System.out.println("\n🎯 QUANDO USAR FLYWEIGHT:");
        System.out.println("  • Grande quantidade de objetos similares");
        System.out.println("  • Memória é limitante");
        System.out.println("  • Objetos podem compartilhar estado");
        System.out.println("  • Identidade dos objetos não importa");
        
        System.out.println("\n📚 EXEMPLOS REAIS:");
        System.out.println("  • Microsoft Word: Estilos de caracteres");
        System.out.println("  • Jogos 3D: Modelos e texturas compartilhadas");
        System.out.println("  • Java String Pool: Strings literais");
        System.out.println("  • Navegadores: Fontes e recursos CSS");
        System.out.println("  • Photoshop: Pincéis e ferramentas");
        System.out.println("═══════════════════════════════════════════════════════════════");
    }
    
    /**
     * Classe auxiliar para simular caractere SEM flyweight
     * Usada apenas para comparação na demonstração
     */
    private static class CharacterWithoutFlyweight {
        // Estado completo em CADA instância (sem compartilhamento)
        @SuppressWarnings("unused")
        private char caractere;
        @SuppressWarnings("unused")
        private String font;
        @SuppressWarnings("unused")
        private int size;
        @SuppressWarnings("unused")
        private String color;
        @SuppressWarnings("unused")
        private boolean bold;
        @SuppressWarnings("unused")
        private boolean italic;
        @SuppressWarnings("unused")
        private int position;
        
        public CharacterWithoutFlyweight(char caractere, String font, int size, 
                                        String color, boolean bold, boolean italic, 
                                        int position) {
            this.caractere = caractere;
            this.font = font;
            this.size = size;
            this.color = color;
            this.bold = bold;
            this.italic = italic;
            this.position = position;
            // Total: ~126 bytes por objeto (muita duplicação!)
        }
    }
}
