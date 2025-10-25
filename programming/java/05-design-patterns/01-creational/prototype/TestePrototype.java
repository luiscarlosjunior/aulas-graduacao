/**
 * Programa de teste para o padrão Prototype
 * 
 * Demonstra clonagem de objetos e uso de registro de protótipos.
 * 
 * @author Aulas Graduação
 */
public class TestePrototype {
    
    public static void main(String[] args) {
        System.out.println("🧬 DEMONSTRAÇÃO DO PADRÃO PROTOTYPE\n");
        
        // Exemplo 1: Clonagem simples de formas
        System.out.println("=".repeat(70));
        System.out.println("📐 EXEMPLO 1: Clonagem Simples de Formas");
        System.out.println("=".repeat(70));
        
        // Criar círculo original
        Circulo circuloOriginal = new Circulo(10);
        circuloOriginal.setCor("Azul");
        circuloOriginal.setPosicao(5, 5);
        
        // Clonar círculo
        Circulo circuloClonado = (Circulo) circuloOriginal.clonar();
        
        // Modificar o clone
        circuloClonado.setCor("Vermelho");
        circuloClonado.setPosicao(15, 15);
        circuloClonado.setRaio(20);
        
        // Exibir ambos
        System.out.println("\n--- Original ---");
        circuloOriginal.exibirInfo();
        
        System.out.println("\n--- Clone Modificado ---");
        circuloClonado.exibirInfo();
        
        // Exemplo 2: Clonagem de retângulos
        System.out.println("\n\n" + "=".repeat(70));
        System.out.println("▭ EXEMPLO 2: Clonagem de Retângulos");
        System.out.println("=".repeat(70));
        
        Retangulo retanguloOriginal = new Retangulo(20, 10);
        retanguloOriginal.setCor("Verde");
        retanguloOriginal.setPosicao(0, 0);
        
        Retangulo retanguloClonado = (Retangulo) retanguloOriginal.clonar();
        retanguloClonado.setDimensoes(30, 15);
        retanguloClonado.setCor("Amarelo");
        
        System.out.println("\n--- Original ---");
        retanguloOriginal.exibirInfo();
        
        System.out.println("\n--- Clone Modificado ---");
        retanguloClonado.exibirInfo();
        
        // Exemplo 3: Clonagem profunda de documentos
        System.out.println("\n\n" + "=".repeat(70));
        System.out.println("📄 EXEMPLO 3: Clonagem Profunda de Documentos");
        System.out.println("=".repeat(70));
        
        Documento docOriginal = new Documento(
            "Relatório Anual 2024",
            "Este é o relatório completo das atividades realizadas durante o ano de 2024...",
            "João Silva"
        );
        docOriginal.adicionarTag("relatório");
        docOriginal.adicionarTag("2024");
        docOriginal.adicionarTag("anual");
        docOriginal.getConfig().setFonte("Times New Roman");
        docOriginal.getConfig().setTamanhoFonte(14);
        
        // Clonar documento
        Documento docClonado = (Documento) docOriginal.clonar();
        docClonado.setTitulo("Relatório Anual 2024 - Cópia Revisada");
        docClonado.setAutor("Maria Santos");
        docClonado.adicionarTag("revisado");
        docClonado.getConfig().setNegrito(true);
        
        System.out.println("\n--- Documento Original ---");
        docOriginal.exibirInfo();
        
        System.out.println("\n--- Documento Clonado ---");
        docClonado.exibirInfo();
        
        // Demonstrar que são objetos independentes
        System.out.println("\n📊 Verificação de Independência:");
        System.out.println("Tags do original: " + docOriginal.getTags());
        System.out.println("Tags do clone: " + docClonado.getTags());
        System.out.println("✅ Os objetos são independentes!");
        
        // Exemplo 4: Usando Registro de Protótipos
        System.out.println("\n\n" + "=".repeat(70));
        System.out.println("📚 EXEMPLO 4: Registro de Protótipos");
        System.out.println("=".repeat(70));
        
        RegistroPrototipos registro = new RegistroPrototipos();
        
        // Criar e registrar protótipos
        Circulo circuloPadrao = new Circulo(5);
        circuloPadrao.setCor("Azul");
        registro.registrar("circulo-padrao", circuloPadrao);
        
        Retangulo retanguloPadrao = new Retangulo(10, 10);
        retanguloPadrao.setCor("Verde");
        registro.registrar("quadrado-padrao", retanguloPadrao);
        
        Documento docModelo = new Documento(
            "Documento Modelo",
            "Este é um documento modelo que pode ser clonado para criar novos documentos...",
            "Sistema"
        );
        docModelo.adicionarTag("modelo");
        registro.registrar("doc-modelo", docModelo);
        
        // Listar protótipos
        registro.listar();
        
        // Clonar a partir do registro
        System.out.println("\n🔄 Clonando protótipos do registro:");
        Circulo novoCirculo1 = (Circulo) registro.clonar("circulo-padrao");
        novoCirculo1.setRaio(15);
        
        Circulo novoCirculo2 = (Circulo) registro.clonar("circulo-padrao");
        novoCirculo2.setRaio(25);
        novoCirculo2.setCor("Vermelho");
        
        Documento novoDoc = (Documento) registro.clonar("doc-modelo");
        novoDoc.setTitulo("Novo Documento Baseado no Modelo");
        novoDoc.setAutor("Pedro Oliveira");
        
        System.out.println("\n📋 Objetos criados a partir dos protótipos:");
        novoCirculo1.exibirInfo();
        novoCirculo2.exibirInfo();
        novoDoc.exibirInfo();
        
        // Exemplo 5: Performance - Criação vs Clonagem
        System.out.println("\n\n" + "=".repeat(70));
        System.out.println("⚡ EXEMPLO 5: Performance - Criação vs Clonagem");
        System.out.println("=".repeat(70));
        
        int quantidade = 1000;
        
        // Medindo criação normal
        long inicioCreation = System.nanoTime();
        for (int i = 0; i < quantidade; i++) {
            Documento doc = new Documento("Doc " + i, "Conteúdo...", "Autor");
        }
        long fimCreation = System.nanoTime();
        long tempoCreation = (fimCreation - inicioCreation) / 1_000_000;
        
        // Medindo clonagem
        Documento docBase = new Documento("Base", "Conteúdo base...", "Autor Base");
        long inicioClone = System.nanoTime();
        for (int i = 0; i < quantidade; i++) {
            Documento clone = (Documento) docBase.clonar();
        }
        long fimClone = System.nanoTime();
        long tempoClone = (fimClone - inicioClone) / 1_000_000;
        
        System.out.println("\n📊 Resultados (criação de " + quantidade + " objetos):");
        System.out.println("Criação normal: " + tempoCreation + " ms");
        System.out.println("Clonagem: " + tempoClone + " ms");
        
        if (tempoClone < tempoCreation) {
            double ganho = ((double)(tempoCreation - tempoClone) / tempoCreation) * 100;
            System.out.println("✅ Clonagem foi " + String.format("%.1f%%", ganho) + " mais rápida!");
        }
        
        // Conclusão
        System.out.println("\n\n" + "=".repeat(70));
        System.out.println("✅ VANTAGENS DO PROTOTYPE:");
        System.out.println("=".repeat(70));
        System.out.println("1. Evita custos de criação de objetos complexos");
        System.out.println("2. Reduz necessidade de subclasses");
        System.out.println("3. Permite adicionar/remover objetos em tempo de execução");
        System.out.println("4. Especifica novos objetos variando valores");
        System.out.println("5. Reduz acoplamento com classes concretas");
        
        System.out.println("\n💡 QUANDO USAR:");
        System.out.println("- Criação de objetos é custosa");
        System.out.println("- Sistema deve ser independente de como produtos são criados");
        System.out.println("- Objetos têm poucos estados diferentes");
        System.out.println("- Quer evitar hierarquia de factory paralela");
    }
}
